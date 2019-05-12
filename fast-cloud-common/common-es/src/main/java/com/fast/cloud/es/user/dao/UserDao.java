package com.fast.cloud.es.user.dao;

import com.fast.cloud.es.user.domain.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Title: UserDao
 * Description:
 * spring-data-es 查询接口
 * Version:1.0.0
 *
 * @author pancm
 * @date 2018年4月25日
 */
public interface UserDao extends ElasticsearchRepository<User, Long> {

}
