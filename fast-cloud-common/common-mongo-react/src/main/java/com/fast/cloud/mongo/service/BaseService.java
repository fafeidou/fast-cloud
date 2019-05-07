package com.fast.cloud.mongo.service;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-01-21 9:59
 */
@Service
public class BaseService<T, ID extends Serializable> implements IService<T, ID> {
    private static final Logger logger = LoggerFactory.getLogger(BaseService.class);

    protected String collectionName;

    @Autowired
    protected ReactiveMongoTemplate mongoTemplate;

    @Autowired(required = false)
    protected ReactiveMongoRepository<T, ID> repository;

    private Class<T> entityClass;

    public BaseService() {
        try {
            ResolvableType resolvableType = ResolvableType.forClass(getClass());
            entityClass = (Class<T>) resolvableType.getSuperType().getGeneric().resolve();
            if (this.entityClass != null) {
                this.collectionName = resolveCollectionName(this.entityClass);
            }
        } catch (Exception e) {
            logger.error(getClass().getName() + "定义的model不正确,未能获取到entityClass", e);
        }
    }

    private Class<T> getClazz() {
        if (this.entityClass == null) {
            ParameterizedType type = (ParameterizedType) getClass()
                    .getGenericSuperclass();
            this.entityClass = ((Class) type.getActualTypeArguments()[0]);
        }
        return this.entityClass;
    }

    @Override
    public <S extends T> Mono<S> save(S entity) {
        return mongoTemplate.save(entity);
    }

    @Override
    public Flux<T> saveAll(Collection<T> entities) {
        return mongoTemplate.insertAll(entities);
    }

    @Override
    public <S extends T> Flux<S> saveAll(Publisher<S> entityStream) {
        return repository.saveAll(entityStream);
    }

    @Override
    public Mono<T> findById(ID id) {
        return mongoTemplate.findById(id, entityClass);
    }

    @Override
    public Mono<T> findById(Publisher<ID> id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Boolean> existsById(ID id) {
        return repository.existsById(id);
    }

    @Override
    public Mono<Boolean> existsById(Publisher<ID> id) {
        return repository.existsById(id);
    }

    @Override
    public Flux<T> findAll() {
        return mongoTemplate.findAll(entityClass);
    }

    @Override
    public Flux<T> findAllById(Iterable<ID> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public Flux<T> findAllById(Publisher<ID> idStream) {
        return repository.findAllById(idStream);
    }

    @Override
    public Mono<Long> count() {
        return repository.count();
    }

    @Override
    public Mono<Void> deleteById(ID id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Void> deleteById(Publisher<ID> id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Void> delete(T entity) {
        return repository.delete(entity);
    }

    @Override
    public Mono<Void> deleteAll(Iterable<? extends T> entities) {
        return repository.deleteAll(entities);
    }

    @Override
    public Mono<Void> deleteAll(Publisher<? extends T> entityStream) {
        return repository.deleteAll(entityStream);
    }

    @Override
    public Mono<Void> deleteAll() {
        return repository.deleteAll();
    }

    @Override
    public <O> Flux<O> aggregate(Aggregation aggregation, Class<O> outputType) {
        return mongoTemplate.aggregate(aggregation, collectionName, outputType);
    }

    @Override
    public Flux<T> aggregate(Aggregation aggregation) {
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
