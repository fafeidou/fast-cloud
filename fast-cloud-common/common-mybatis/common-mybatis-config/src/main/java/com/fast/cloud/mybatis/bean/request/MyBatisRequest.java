package com.fast.cloud.mybatis.bean.request;

import com.fast.cloud.core.bean.AbstractRequest;
import com.fast.cloud.core.bean.QueryCondition;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.util.List;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-01-16 11:31
 */
public class MyBatisRequest<T> extends AbstractRequest<T> {

    public Example getExample(Class<?> entityClass, String... needFieldList) {
        Example example = new Example(entityClass);
        Example.Criteria criteria = example.createCriteria();
        //添加sort
        if (this.getSort() != null) {
            example.setOrderByClause(this.sortName + " " + (this.sortOrder == 1 ? "ASC" : "DESC"));
        }

        MyBatisRequest request = this;
        if (request.getRequestModel() == null) {
            return example;
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
                        criteria.andEqualTo(column, value);
                        break;
                    case like:
                        criteria.andLike(column, "%" + value + "%");
                        break;
                    case gt:
                        criteria.andGreaterThan(column, value);
                        break;
                    case lt:
                        criteria.andLessThan(column, value);
                        break;
                    case ge:
                        criteria.andGreaterThanOrEqualTo(column, value);
                        break;
                    case le:
                        criteria.andLessThanOrEqualTo(column, value);
                        break;
                    case notEqual:
                        criteria.andNotEqualTo(column, value);
                        break;
                    case notLike:
                        criteria.andNotLike(column, "%" + value + "%");
                        break;
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //设置fields
        if (needFieldList.length > 0 && needFieldList != null) {
            example.selectProperties(needFieldList);
        }
        return example;
    }
}
