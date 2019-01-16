package com.fast.cloud.mongo.service;

import com.fast.cloud.bean.AbstractRequest;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.Serializable;
import java.util.List;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 * 通用service
 *
 * @param <M> model
 * @param <N> model 的类型
 * @author Batman.qin
 * @create 2018-11-20 16:20
 */
public interface IService<M, N extends Serializable> {
    /**
     * @param pageable 封装对象
     * @param query    查询类
     * @param response 返回model的类型
     * @param <R>      返回类型
     * @param <T>      请求类型
     * @return
     */
    <R, T> PageImpl<R> findPage(AbstractRequest<T> pageable, Query query, Class<R> response);

    <R> List<R> find(Query query, Class<R> response);

    List<M> findAll();

    List<M> findAll(Sort sort);

    List<M> findAll(Example<M> example);

    List<M> findAll(Example<M> example, Sort sort);

    Page<M> findAll(Pageable pageable);

    List<M> findList(N... ids);

    M findOne(N id);

    M findOne(Example<M> example);

    M insert(M entity);

    List<M> insert(Iterable<M> entities);

    M save(M entity);

    Iterable<M> save(Iterable<M> entities);

    boolean exists(N id);

    boolean exists(Example<M> example);

    long count();

    long count(Example<M> example);

    void delete(N id);

    void delete(N... ids);

    void delete(M entity);

    void delete(Iterable<? extends M> entities);

    void deleteAll();

    void updateFirst(Query query, Update update, Class clazz);

    <O> AggregationResults<O> aggregate(Aggregation aggregation, Class<O> outputType);

    AggregationResults<M> aggregate(Aggregation aggregation);
}
