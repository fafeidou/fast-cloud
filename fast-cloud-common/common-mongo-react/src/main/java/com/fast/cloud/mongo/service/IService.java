package com.fast.cloud.mongo.service;

import org.reactivestreams.Publisher;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.Collection;

public interface IService<T, ID extends Serializable> {
    <S extends T> Mono<S> save(S entity);

    Flux<T> saveAll(Collection<T> entities);

    <S extends T> Flux<S> saveAll(Publisher<S> entityStream);

    Mono<T> findById(ID id);

    Mono<T> findById(Publisher<ID> id);

    Mono<Boolean> existsById(ID id);

    Mono<Boolean> existsById(Publisher<ID> id);

    Flux<T> findAll();

    Flux<T> findAllById(Iterable<ID> ids);

    Flux<T> findAllById(Publisher<ID> idStream);

    Mono<Long> count();

    Mono<Void> deleteById(ID id);

    Mono<Void> deleteById(Publisher<ID> id);

    Mono<Void> delete(T entity);

    Mono<Void> deleteAll(Iterable<? extends T> entities);

    Mono<Void> deleteAll(Publisher<? extends T> entityStream);

    Mono<Void> deleteAll();

    <O> Flux<O> aggregate(Aggregation aggregation, Class<O> outputType);

    Flux<T> aggregate(Aggregation aggregation);
}
