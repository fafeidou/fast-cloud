package com.fast.cloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

/**
 * @author Batman.qin
 * @create 2018-11-23 19:36
 */
@FeignClient(value = "service-hi-react")
public interface SchedualServiceHi {
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    Mono<String> sayHiFromClientOne(@RequestParam(value = "name") String name);
}
