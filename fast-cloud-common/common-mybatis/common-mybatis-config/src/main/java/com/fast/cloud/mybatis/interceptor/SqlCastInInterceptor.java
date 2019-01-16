package com.fast.cloud.mybatis.interceptor;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Statement;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.regex.Matcher;

@Component
@Profile({"dev", "test"})
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})})
@Slf4j
public class SqlCastInInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();

        long startTime = System.currentTimeMillis();
        StatementHandler statementHandler = (StatementHandler) target;
        int size = 0;
        try {
            Object o = invocation.proceed();
            if (isList(o.getClass())) {
                size = ((List) o).size();
            }
            return o;
        } finally {
            long endTime = System.currentTimeMillis();
            long sqlCost = endTime - startTime;

            BoundSql boundSql = statementHandler.getBoundSql();
            String sql = boundSql.getSql();
            Object parameterObject = boundSql.getParameterObject();
            List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();

            // 格式化Sql语句，去除换行符，替换参数
            sql = formatSql(sql, parameterObject, parameterMappingList);

            log.debug("SQL：[" + sql + "]执行耗时[" + sqlCost + "ms]结果集[" + size + "]");
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        log.info("set properties");
    }

    /**
     * @param sql                  没花钱sql
     * @param parameterObject      参数
     * @param parameterMappingList 参数map
     * @return 美化后sql
     * 增加代码可以处理通用mapper的Example参数美化sql
     */
    @SuppressWarnings("unchecked")
    private String formatSql(String sql, Object parameterObject, List<ParameterMapping> parameterMappingList) {
        // 输入sql字符串空判断
        if (sql == null || sql.length() == 0) {
            return "";
        }

        // 美化sql
        sql = beautifySql(sql);

        // 不传参数的场景，直接把Sql美化一下返回出去
        if (parameterObject == null || CollectionUtils.isEmpty(parameterMappingList)) {
            return sql;
        }

        // 定义一个没有替换过占位符的sql，用于出异常时返回
        String sqlWithoutReplacePlaceholder = sql;

        final PageExample pageExample = tryPageExample(parameterObject);

        try {
            Class<?> parameterObjectClass = parameterObject.getClass();

            // 如果参数是StrictMap且Value类型为Collection，获取key="list"的属性，这里主要是为了处理<foreach>循环时传入List这种参数的占位符替换
            // 例如select * from xxx where id in <foreach collection="list">...</foreach>
            if (Objects.nonNull(pageExample)) {
                sql = this.handlePageExampleParameter(sql, pageExample, parameterMappingList);
            } else if (isStrictMap(parameterObjectClass)) {
                DefaultSqlSession.StrictMap<Collection<?>> strictMap = (DefaultSqlSession.StrictMap<Collection<?>>) parameterObject;

                if (isList(strictMap.get("list").getClass())) {
                    sql = handleListParameter(sql, strictMap.get("list"), parameterMappingList);
                }
            } else if (isMap(parameterObjectClass)) {
                // 如果参数是Map则直接强转，通过map.get(key)方法获取真正的属性值
                // 这里主要是为了处理<insert>、<delete>、<update>、<select>时传入parameterType为map的场景
                Map<?, ?> paramMap = (Map<?, ?>) parameterObject;
                sql = handleMapParameter(sql, paramMap, parameterMappingList);
            } else if (parameterObject instanceof Example) {
                sql = handleExampleParameter(sql, (Example) parameterObject, parameterMappingList);
            } else {
                // 通用场景，比如传的是一个自定义的对象或者八种基本数据类型之一或者String
                sql = handleCommonParameter(sql, parameterMappingList, parameterObjectClass, parameterObject);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            // 占位符替换过程中出现异常，则返回没有替换过占位符但是格式美化过的sql，这样至少保证sql语句比BoundSql中的sql更好看
            return sqlWithoutReplacePlaceholder;
        }

        return sql;
    }

    /**
     * 美化Sql
     */
    private String beautifySql(String sql) {
        sql = sql.replaceAll("[\\s\n ]+", " ");
        return sql;
    }

    /**
     * @param sql                  美化前sql
     * @param example              通用mapper tk Example
     * @param parameterMappingList 参数List
     * @return 美化含参数sql
     * @throws IllegalAccessException, IntrospectionException, InvocationTargetException
     *                                 处理tk Example 参数的sql
     */
    private String handleExampleParameter(String sql, Example example, List<ParameterMapping> parameterMappingList) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        final List<Example.Criteria> oredCriteria = example.getOredCriteria();
        sql = handleCriteria(sql, parameterMappingList, oredCriteria);
        return sql;
    }

    private String handlePageExampleParameter(String sql, PageExample example, List<ParameterMapping> parameterMappingList) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        final List<Example.Criteria> oredCriteria = example.getOredCriteria();
        sql = handleCriteria(sql, parameterMappingList, oredCriteria);
        sql = sql.replaceFirst("\\?", example.getFirstPageHelper().toString() + "," + example.getSecondPageHelper().toString());
        return sql;
    }

    private String handleCriteria(String sql, List<ParameterMapping> parameterMappingList, List<Example.Criteria> oredCriteria) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        for (Example.Criteria criteria : oredCriteria) {
            for (Example.Criterion criterion : criteria.getCriteria()) {
                final Object value = criterion.getValue();
                if (Objects.isNull(value)) {
                    continue;
                }
                if (isList(value.getClass())) {
                    sql = handleListParameter(sql, (List) criterion.getValue(), parameterMappingList);
                } else {
                    sql = handleCommonParameter(sql, parameterMappingList, value.getClass(), value);
                }
            }
        }
        return sql;
    }


    /**
     * 处理参数为List的场景
     */
    private String handleListParameter(String sql, Collection<?> col, List<ParameterMapping> parameterMappingList) {
        final Object[] objects = col.toArray();
        final int groupSize = parameterMappingList.size() / objects.length;
        for (int i = 0; i < objects.length; i++) {
            sql = this.formatSql(sql, objects[i], parameterMappingList.subList(i * groupSize, (i + 1) * groupSize - 1));
        }
        return sql;
    }

    /**
     * 处理参数为Map的场景
     */
    private String handleMapParameter(String sql, Map<?, ?> paramMap, List<ParameterMapping> parameterMappingList) {
        List<ParameterMapping> current = Lists.newArrayList();
        final HashMap<Object, Object> currentMap = Maps.newHashMap();
        currentMap.putAll(paramMap);
        for (ParameterMapping parameterMapping : parameterMappingList) {
            final String property = parameterMapping.getProperty();
            final String fieldName = property.startsWith("__frch_record_") ? property.substring(property.indexOf(".") + 1) : property;
            Object propertyValue = currentMap.get(fieldName);
            current.add(parameterMapping);
            sql = this.formatSql(sql, propertyValue, current);
            current.clear();
        }

        return sql;
    }

    /**
     * 处理通用的场景
     */
    private String handleCommonParameter(String sql, List<ParameterMapping> parameterMappingList, Class<?> parameterObjectClass,
                                         Object parameterObject) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        String propertyValue = "null";
        // 基本数据类型或者基本数据类型的包装类，直接toString即可获取其真正的参数值，其余直接取paramterMapping中的property属性即可
        if (isPrimitiveOrPrimitiveWrapper(parameterObjectClass)) {
            propertyValue = parameterObject.toString();
        } else if (String.class.isAssignableFrom(parameterObjectClass)) {
            propertyValue = "\"" + parameterObject + "\"";
        } else if (Enum.class.isAssignableFrom(parameterObjectClass)) { //枚举存int值
            propertyValue = String.valueOf(((Enum) parameterObject).ordinal());
        } else if (parameterObject instanceof String[]) { //自定义的TypeHandler处理数组
            propertyValue = String.join(",", ((String[]) parameterObject));
        } else if (parameterObject instanceof Date || Temporal.class.isAssignableFrom(parameterObjectClass)) { //日期处理
            propertyValue = "\"" + parameterObject.toString() + "\"";
        } else if (!CollectionUtils.isEmpty(parameterMappingList)) {
            for (ParameterMapping parameterMapping : parameterMappingList) {
                final String property = parameterMapping.getProperty();
                final String fieldName = property.startsWith("__frch_record_") ? property.substring(property.indexOf(".") + 1) : property;
                final PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, parameterObjectClass);
                final Method readMethod = propertyDescriptor.getReadMethod();
                final Object invoke = readMethod.invoke(parameterObject);
                if (Objects.isNull(invoke)) {
                    sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(propertyValue));

                } else {
                    sql = this.formatSql(sql, ImmutableMap.of(fieldName, invoke), parameterMappingList);
                }
            }
        }

        if (!StringUtils.isEmpty(propertyValue)) {
            sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(propertyValue));
        }

        return sql;
    }

    /**
     * 是否基本数据类型或者基本数据类型的包装类
     */
    private boolean isPrimitiveOrPrimitiveWrapper(Class<?> parameterObjectClass) {
        return parameterObjectClass.isPrimitive() ||
                (parameterObjectClass.isAssignableFrom(Byte.class) || parameterObjectClass.isAssignableFrom(Short.class) ||
                        parameterObjectClass.isAssignableFrom(Integer.class) || parameterObjectClass.isAssignableFrom(Long.class) ||
                        parameterObjectClass.isAssignableFrom(Double.class) || parameterObjectClass.isAssignableFrom(Float.class) ||
                        parameterObjectClass.isAssignableFrom(Character.class) || parameterObjectClass.isAssignableFrom(Boolean.class));
    }

    /**
     * 是否DefaultSqlSession的内部类StrictMap
     */
    private boolean isStrictMap(Class<?> parameterObjectClass) {
        return DefaultSqlSession.StrictMap.class.isAssignableFrom(parameterObjectClass);
    }

    /**
     * 是否List的实现类
     */
    private boolean isList(Class<?> clazz) {
        return Collection.class.isAssignableFrom(clazz);
    }

    /**
     * 是否Map的实现类
     */
    private boolean isMap(Class<?> parameterObjectClass) {
        return Map.class.isAssignableFrom(parameterObjectClass);
    }

    @SuppressWarnings("unchecked")
    private PageExample tryPageExample(Object o) {
        try {


            if (!isMap(o.getClass())) {
                return null;
            }
            final Map map = (Map) o;
            final Integer firstPageHelper = (Integer) map.get("First_PageHelper");
            final Integer secondPageHelper = (Integer) map.get("Second_PageHelper");
            final List<Example.Criteria> oredCriteria = (List<Example.Criteria>) map.get("oredCriteria");
            if (Objects.isNull(firstPageHelper) || Objects.isNull(secondPageHelper) || Objects.isNull(oredCriteria)) {
                return null;
            }

            return PageExample.builder()
                    .firstPageHelper(firstPageHelper)
                    .oredCriteria(oredCriteria)
                    .secondPageHelper(secondPageHelper).build();
        } catch (Exception e) {
            log.info("Not PageExample");
        }
        return null;
    }

    @Data
    @Builder
    private static class PageExample {

        private List<Example.Criteria> oredCriteria;

        private Integer firstPageHelper;

        private Integer secondPageHelper;
    }
}

