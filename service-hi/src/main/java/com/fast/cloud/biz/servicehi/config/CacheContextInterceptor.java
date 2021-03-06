package com.fast.cloud.biz.servicehi.config;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 *
 * @author Batman.qin
 * @create 2018-12-26 11:06
 */
public class CacheContextInterceptor implements HandlerInterceptor {

    private HystrixRequestContext context;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse respone, Object arg2) throws Exception {
        context = HystrixRequestContext.initializeContext();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse respone, Object arg2, ModelAndView arg3)
            throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse respone, Object arg2, Exception arg3)
            throws Exception {
        context.shutdown();
    }

}
