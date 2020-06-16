package com.fast.cloud.biz.redis.controller;

import com.fast.cloud.biz.redis.bean.UserVO;
import com.fast.cloud.biz.redis.service.CacheRedisCaffeineService;
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
public class CacheRedisCaffeineController {

    @Resource
    private CacheRedisCaffeineService cacheRedisCaffeineService;

    @GetMapping("id/{id}")
    public UserVO get(@PathVariable long id) {
        return cacheRedisCaffeineService.get(id);
    }

    @GetMapping("name/{name}")
    public UserVO get(@PathVariable String name) {
        return cacheRedisCaffeineService.get(name);
    }

    @GetMapping("update/{id}")
    public UserVO update(@PathVariable long id) {
        UserVO user = cacheRedisCaffeineService.get(id);
        cacheRedisCaffeineService.update(user);
        return user;
    }

    @GetMapping("delete/{id}")
    public void delete(@PathVariable long id) {
        cacheRedisCaffeineService.delete(id);
    }
}
