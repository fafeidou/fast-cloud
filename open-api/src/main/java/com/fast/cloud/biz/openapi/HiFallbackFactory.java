//package com.fast.cloud.biz.openapi;
//
//import feign.hystrix.FallbackFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.stereotype.Component;
//
///**
// * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
// *
// * @author Batman.qin
// * @create 2019-02-25 9:32
// */
//@Component
//public class HiFallbackFactory implements FallbackFactory<SchedualServiceHi> {
//    Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Override
//    public SchedualServiceHi create(Throwable throwable) {
//        logger.info("SchedualServiceHi exception:" + throwable.getMessage());
//        return new SchedualServiceHi() {
//            @Override
//            public String sayHiFromClientOne(String name) {
//                return "iiiiiiiiiiiiiiiiiiiiiiii";
//            }
//
//            @Override
//            public PageImpl<String> getPage(User user) {
//                return null;
//            }
//        };
//    }
//}
