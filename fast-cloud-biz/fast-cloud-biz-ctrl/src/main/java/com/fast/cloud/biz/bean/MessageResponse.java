package com.fast.cloud.biz.bean;

import com.fast.cloud.biz.bean.response.ApiResponse;
import com.fast.cloud.biz.config.SpringContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;

import java.util.Locale;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2018-12-28 13:38
 */
public class MessageResponse<T> extends ApiResponse {

    public static String getMessage(String messageCode) {
        MessageSource messageSource = SpringContext.getBean(MessageSource.class);
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(messageCode, null, locale);
    }

    /**
     * 参数验证失败使用
     *
     * @param result 错误信息
     */
    public static ApiResponse validateFailed(BindingResult result) {
        return validateFailed(result.getFieldError().getDefaultMessage());
    }

    /**
     * 参数验证失败使用
     *
     * @param message 错误信息
     */
    public static ApiResponse validateFailed(String message) {
        ApiResponse response = new ApiResponse();
        response.setCode(VALIDATE_FAILED);
        response.setMessage(getMessage(message));
        return response;
    }

}
