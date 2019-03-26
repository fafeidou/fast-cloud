package com.fast.cloud.rest.repository;

import com.fast.cloud.rest.domain.Ad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @author Batman.qin
 */
@RepositoryRestResource(collectionResourceRel = "ad", path = "ad")
public interface AdRepository extends MongoRepository<Ad, String> {
    List<Ad> findByTitle(String title);
}
