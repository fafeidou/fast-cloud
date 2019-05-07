package com.fast.cloud.biz.biz.dao.mongo;

import com.fast.cloud.biz.domain.CityModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-01-14 17:34
 */
@Repository
public interface CityRepository extends MongoRepository<CityModel, String> {
}
