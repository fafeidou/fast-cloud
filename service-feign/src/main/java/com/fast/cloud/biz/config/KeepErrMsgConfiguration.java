//package com.fast.cloud.biz.config;
//
//import com.charleskeith.ckcommonmodel.model.BaseResponse;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import feign.Response;
//import feign.Util;
//import feign.codec.ErrorDecoder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.StringUtils;
//
//import java.io.IOException;
//
///**
// *
// * 该类是将Feign异常返回原来的模样
// * @author Batman.qin
// * @create 2019-04-15 14:42
// */
//@Configuration
//public class KeepErrMsgConfiguration {
//    private static ObjectMapper objectMapper = new ObjectMapper();
//
//    @Bean
//    public ErrorDecoder errorDecoder() {
//        return new UserErrorDecoder();
//    }
//
//    /**
//     * 自定义错误
//     */
//    public class UserErrorDecoder implements ErrorDecoder {
//        private Logger logger = LoggerFactory.getLogger(getClass());
//
//        @Override
//        public Exception decode(String methodKey, Response response) {
//            Exception exception = null;
//            try {
//                // 获取原始的返回内容
//                String json = Util.toString(response.body().asReader());
//                exception = new RuntimeException(json);
//                // 将返回内容反序列化为Result，这里应根据自身项目作修改
//
//                BaseResponse result = jsonToObject(json, BaseResponse.class);
//                // 业务异常抛出简单的 RuntimeException，保留原来错误信息
//                if ("false".equals(result.getStatus())) {
//                    exception = new RuntimeException(result.getMsg());
//                }
//            } catch (IOException ex) {
//                logger.error(ex.getMessage(), ex);
//            }
//            return exception;
//        }
//    }
//
//    public static <T> T jsonToObject(String src, Class<T> clazz) {
//        if (StringUtils.isEmpty(src) || clazz == null) {
//            return null;
//        }
//        try {
//            return clazz.equals(String.class) ? (T) src : objectMapper.readValue(src, clazz);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//}
