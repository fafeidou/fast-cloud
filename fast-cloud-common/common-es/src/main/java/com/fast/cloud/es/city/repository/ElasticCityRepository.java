package com.fast.cloud.es.city.repository;

import com.fast.cloud.es.city.entity.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticCityRepository extends ElasticsearchRepository<City, Long> {

}
