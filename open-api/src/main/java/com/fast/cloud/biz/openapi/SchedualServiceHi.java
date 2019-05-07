package com.fast.cloud.biz.openapi;

import feign.hystrix.FallbackFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 *
 * @author Batman.qin
 * @create 2018-11-23 19:36
 */
@Api("SchedualServiceHi")
@FeignClient(value = "service-hi", fallbackFactory = SchedualServiceHi.HiFallbackFactory.class)
public interface SchedualServiceHi {
    Logger logger = LoggerFactory.getLogger(SchedualServiceHi.class);

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    @ApiOperation(value = "sayHiFromClientOne", notes = "sayHiFromClientOne")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/page")
    @ApiOperation(value = "getPage", notes = "getPage")
    PageImpl<String> getPage(@RequestBody User user);

    @Component
    class HiFallbackFactory implements FallbackFactory<SchedualServiceHi> {
        Logger logger = LoggerFactory.getLogger(getClass());

        @Override
        public SchedualServiceHi create(Throwable throwable) {
            logger.info("SchedualServiceHi exception:" + throwable.getMessage());
            return new SchedualServiceHi() {
                @Override
                public String sayHiFromClientOne(String name) {
                    return "iiiiiiiiiiiiiiiiiiiiiiii";
                }

                @Override
                public PageImpl<String> getPage(User user) {
                    return null;
                }
            };
        }
    }
}
