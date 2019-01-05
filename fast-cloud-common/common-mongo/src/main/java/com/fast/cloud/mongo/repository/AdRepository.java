package com.fast.cloud.mongo.repository;

import com.fast.cloud.mongo.domain.entity.Ad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends MongoRepository<Ad, String> {
}
