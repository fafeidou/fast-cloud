package com.fast.cloud.redis.guava.controller;

import com.fast.cloud.redis.guava.bean.UserVO;
import com.fast.cloud.redis.guava.service.CacheRedisGuavaService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qinfuxiang
 */
@RestController
@RequestMapping("user")
public class CacheRedisGuavaController {

    @Resource
    private CacheRedisGuavaService cacheRedisGuavaService;

    @GetMapping("id/{id}")
    public UserVO get(@PathVariable long id) {
        return cacheRedisGuavaService.get(id);
    }

    @GetMapping("name/{name}")
    public UserVO get(@PathVariable String name) {
        return cacheRedisGuavaService.get(name);
    }

    @GetMapping("update/{id}")
    public UserVO update(@PathVariable long id) {
        UserVO user = cacheRedisGuavaService.get(id);
        cacheRedisGuavaService.update(user);
        return user;
    }

    @GetMapping("delete/{id}")
    public void delete(@PathVariable long id) {
        cacheRedisGuavaService.delete(id);
    }
}
