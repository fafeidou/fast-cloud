package com.fast.cloud.biz.mongo.domain.request;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2018-12-10 16:18
 */
public class MongoRequest<T> extends AbstractRequest<T> {

    public Query getQuery(Collection<String> needFieldList) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        //添加sort
        if (this.getSort() != null) {
            query.with(this.getSort());
        }

        MongoRequest request = this;
        if (request.getRequestModel() == null) {
            return query;
        }
        Class clazz = request.getRequestModel().getClass();

        //获取查询类Query的所有字段,包括父类字段
        List<Field> fields = getAllFieldsWithRoot(clazz);

        for (Field field : fields) {

            //获取字段上的@QueryWord注解
            QueryCondition qw = field.getAnnotation(QueryCondition.class);
            if (qw == null) {
                continue;
            }

            // 获取字段名
            String column = qw.column();
            //如果主注解上colume为默认值"",则以field为准
            if (column.equals("")) {
                column = field.getName();
            }

            field.setAccessible(true);

            try {
                Object value = field.get(request.getRequestModel());
                //如果值为null,注解未标注nullable,跳过
                if (value == null && !qw.nullable()) {
                    continue;
                }

                // can be empty
                if (value != null && String.class.isAssignableFrom(value.getClass())) {
                    String s = (String) value;
                    //如果值为"",且注解未标注emptyable,跳过
                    if (s.equals("") && !qw.emptyable()) {
                        continue;
                    }
                }

                //通过注解上func属性,构建路径表达式

                switch (qw.func()) {
                    case equal:
                        criteria.and(column).is(value);
                        break;
                    case like:
                        query.addCriteria(Criteria.where(column).regex(Pattern
                                .compile("^.*" + value + ".*$", Pattern.CASE_INSENSITIVE)));
                        break;
                    case gt:
                        criteria.and(column).gt(value);
                        break;
                    case lt:
                        criteria.and(column).lt(value);
                        break;
                    case ge:
                        criteria.and(column).gte(value);
                        break;
                    case le:
                        criteria.and(column).lte(value);
                        break;
                    case notEqual:
                        criteria.and(column).ne(value);
                        break;
                    case notLike:
                        query.addCriteria(Criteria.where(column).regex(Pattern.
                                compile("\\b((?!" + value + ")\\w)+\\b", Pattern.CASE_INSENSITIVE)));
                        break;
                }


            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        query.addCriteria(criteria);
        //设置fields
        if (!CollectionUtils.isEmpty(needFieldList)) {
            org.springframework.data.mongodb.core.query.Field needFields = query.fields();
            for (String str : needFieldList) {
                needFields.include(str);
            }

        }
        return query;
    }


    //获取类clazz的所有Field，包括其父类的Field
    private List<Field> getAllFieldsWithRoot(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        Field[] dFields = clazz.getDeclaredFields();//获取本类所有字段
        if (null != dFields && dFields.length > 0) {
            fieldList.addAll(Arrays.asList(dFields));
        }

        // 若父类是Object，则直接返回当前Field列表
        Class<?> superClass = clazz.getSuperclass();
        if (superClass == Object.class) {
            return Arrays.asList(dFields);
        }

        // 递归查询父类的field列表
        List<Field> superFields = getAllFieldsWithRoot(superClass);

        if (null != superFields && !superFields.isEmpty()) {
            superFields.stream().
                    filter(field -> !fieldList.contains(field)).//不重复字段
                    forEach(field -> fieldList.add(field));
        }
        return fieldList;
    }
}
