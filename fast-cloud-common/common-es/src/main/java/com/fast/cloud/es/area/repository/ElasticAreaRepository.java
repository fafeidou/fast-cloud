package com.fast.cloud.es.area.repository;

import com.fast.cloud.es.area.entity.Area;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticAreaRepository extends ElasticsearchRepository<Area, Integer> {

}
