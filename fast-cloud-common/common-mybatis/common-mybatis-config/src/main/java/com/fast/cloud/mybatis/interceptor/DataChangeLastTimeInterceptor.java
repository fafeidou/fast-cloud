//package com.fast.cloud.mybatis.interceptor;
//
//import com.fast.cloud.core.utils.reflect.ReflectionUtils;
//import org.apache.ibatis.executor.Executor;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.plugin.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.Properties;
//
//
///**
// * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
// *
// * @author Batman.qin
// * @create 2019-04-11 11:29
// */
//@Component
//@Intercepts({
//        @Signature(type = Executor.class, method = DataChangeLastTimeInterceptor.METHOD_UPDATE, args = {
//                MappedStatement.class, Object.class})})
//public class DataChangeLastTimeInterceptor implements Interceptor {
//    Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    public static final String METHOD_UPDATE = "update";
//    public static final String[] METHOD_SET_DATA_CHANGE_LAST_TIME = new String[]{"setDatachangeLasttime", "setDataChange_LastTime"};
//
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        String methodName = invocation.getMethod().getName();
//        if (methodName.equalsIgnoreCase(DataChangeLastTimeInterceptor.METHOD_UPDATE)) {
//            Object parameter = invocation.getArgs()[1];
//            Date empty = null;
//            try {
//                for (String s : METHOD_SET_DATA_CHANGE_LAST_TIME) {
//                    ReflectionUtils.callMethod(parameter, s, true, empty);
//                }
//            } catch (Exception e) {
//                logger.warn("setDatachangeLasttime error:" + e.getMessage() + ",class:" + parameter.getClass());
//            }
//        }
//        return invocation.proceed();
//    }
//
//    @Override
//    public Object plugin(Object o) {
//        return Plugin.wrap(o, this);
//    }
//
//    @Override
//    public void setProperties(Properties properties) {
//
//    }
//}
