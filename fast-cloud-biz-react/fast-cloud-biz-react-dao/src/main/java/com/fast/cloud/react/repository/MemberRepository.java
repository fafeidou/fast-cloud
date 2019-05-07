package com.fast.cloud.react.repository;

import com.fast.cloud.react.domain.EsMember;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-05-06 17:58
 */
@Repository
public interface MemberRepository extends MongoRepository<EsMember, String> {
}
