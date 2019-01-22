package com.fast.cloud.mongo.service;

import com.fast.cloud.bean.AbstractRequest;
import com.fast.cloud.bean.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2018-11-20 16:22
 */
public abstract class BaseService<M extends BaseEntity, N extends Serializable> implements IService<M, N> {
    private static final Logger logger = LoggerFactory.getLogger(BaseService.class);

    protected String collectionName;

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired(required = false)
    protected MongoRepository<M, N> repository;

    private Class<M> entityClass;

    public BaseService() {
        try {
            ResolvableType resolvableType = ResolvableType.forClass(getClass());
            entityClass = (Class<M>) resolvableType.getSuperType().getGeneric().resolve();
            if (this.entityClass != null) {
                this.collectionName = resolveCollectionName(this.entityClass);
            }
        } catch (Exception e) {
            logger.error(getClass().getName() + "定义的model不正确,未能获取到entityClass", e);
        }
    }

    private Class<M> getClazz() {
        if (this.entityClass == null) {
            ParameterizedType type = (ParameterizedType) getClass()
                    .getGenericSuperclass();
            this.entityClass = ((Class) type.getActualTypeArguments()[0]);
        }
        return this.entityClass;
    }

    @Override
    public <R, T> PageImpl<R> findPage(AbstractRequest<T> pageable, Query query, Class<R> response) {
        //查询出一共的条数
        Long count = mongoTemplate.count(query, response);
        //查询
        List<R> list = mongoTemplate.find(query.with(pageable), response);
        //将集合与分页结果封装
        PageImpl<R> rs = new PageImpl<>(list, pageable, count);
        return rs;
    }

    @Override
    public <R> List<R> find(Query query, Class<R> response) {
        return mongoTemplate.find(query, response);
    }

    @Override
    public List<M> findAll() {
        return repository.findAll();
    }

    @Override
    public List<M> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public List<M> findAll(Example<M> example) {
        return repository.findAll(example);
    }

    @Override
    public List<M> findAll(Example<M> example, Sort sort) {
        return repository.findAll(example, sort);
    }

    @Override
    public Page<M> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<M> findList(N... ids) {
        List<M> result = new ArrayList<>();
        if (ids != null) {
            for (N id : ids) {
                M entity = findOne(id);
                if (entity != null) {
                    result.add(entity);
                }
            }
        }
        return result;
    }

    @Override
    public M findOne(N id) {
        return mongoTemplate.findById(id, entityClass);
    }

    @Override
    public M findOne(Example<M> example) {
        return repository.findOne(example).get();
    }

    @Override
    public M insert(M entity) {
        return repository.insert(entity);
    }

    @Override
    public List<M> insert(Iterable<M> entities) {
        return repository.insert(entities);
    }

    @Override
    public M save(M entity) {
        return repository.save(entity);
    }

    @Override
    public Iterable<M> save(Iterable<M> entities) {
        entities.forEach(i -> mongoTemplate.save(i));
        return entities;
    }

    @Override
    public void updateFirst(Query query, Update update, Class clazz) {
        mongoTemplate.updateFirst(query, update, clazz);
    }

    @Override
    public boolean exists(N id) {
        return mongoTemplate.exists(Query.query(Criteria.where("id").is(id)), entityClass);
    }

    @Override
    public boolean exists(Example<M> example) {
        return repository.exists(example);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public long count(Example<M> example) {
        return repository.count(example);
    }

    @Override
    public void delete(N id) {
        mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), entityClass);
    }

    @Override
    public void delete(N... ids) {
        if (ids != null) {
            for (N id : ids) {
                delete(this.findOne(id));
            }
        }
    }

    @Override
    public void delete(M entity) {
        repository.delete(entity);
    }

    @Override
    public void delete(Iterable<? extends M> entities) {
        repository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public <O> AggregationResults<O> aggregate(Aggregation aggregation, Class<O> outputType) {
        return mongoTemplate.aggregate(aggregation, collectionName, outputType);
    }

    @Override
    public AggregationResults<M> aggregate(Aggregation aggregation) {
        logger.debug("class aggregation {}->{}", entityClass, aggregation);
        return mongoTemplate.aggregate(aggregation, collectionName, entityClass);
    }


    protected String resolveCollectionName(Class clz) {
        Document annotation = AnnotationUtils.findAnnotation(clz, Document.class);
        if (annotation != null) {
            return annotation.collection();
        }
        return null;
    }

    protected ParameterizedType resolveReturnedClassFromGernericType(Class<?> clazz) {
        Object genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            Type rawtype = parameterizedType.getRawType();
            if (BaseService.class.equals(rawtype)) {
                return parameterizedType;
            }
        }
        return resolveReturnedClassFromGernericType(clazz.getSuperclass());
    }

}
