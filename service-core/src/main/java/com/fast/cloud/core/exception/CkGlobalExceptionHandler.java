package com.fast.cloud.core.exception;

import com.fast.cloud.model.BaseResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2018-11-29 15:01
 */
@RestController
@ControllerAdvice
public class CkGlobalExceptionHandler {


    private Logger logger = LoggerFactory.getLogger(CkGlobalExceptionHandler.class);

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public BaseResponse baseErrorHandler(HttpServletRequest req, BaseException e) {
        logger.error("---BaseException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        if (StringUtils.isNotBlank(e.getCode()) && StringUtils.isNotBlank(e.getMessage())) {
            return BaseResponse.exceptionResponse(e.getCode(), e.getMessage());
        }
        return BaseResponse.errorResponse();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse defaultErrorHandler(HttpServletRequest req, Exception e) {
        logger.error("---DefaultException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        if (StringUtils.isNotBlank(e.getMessage())) {
            return BaseResponse.exceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage());
        }
        return BaseResponse.errorResponse();
    }

}
