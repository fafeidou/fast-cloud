package com.fast.cloud.biz.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by villiam on 16/7/20.
 */
public class BaseResponse<T> implements Serializable {
    private String status;
    private String statusCode;
    private String msg;
    private long timestamp;
    private String aign;
    private T result;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getAign() {
        return aign;
    }

    public void setAign(String aign) {
        this.aign = aign;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public static <T> BaseResponse<T> successResponse(T result) {
        BaseResponse<T> response = new BaseResponse<T>();
        response.setTimestamp(new Date().getTime());
        response.setStatusCode("200");
        response.setStatus("true");
        response.setMsg("成功");
        response.setResult(result);
        return response;
    }

    public static BaseResponse errorResponse() {
        BaseResponse response = new BaseResponse();
        response.setTimestamp(new Date().getTime());
        response.setStatusCode("500");
        response.setStatus("false");
        response.setMsg("失败");
        response.setResult("0");
        return response;
    }

    public static <T> BaseResponse<T> errorResponse(T message) {
        BaseResponse response = new BaseResponse();
        response.setStatusCode("500");
        response.setStatus("false");
        response.setMsg("失败");
        response.setResult(message);
        return response;
    }

    public static <T> BaseResponse<T> exceptionResponse(String code, T message) {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(code);
        response.setStatus("false");
        response.setMsg(message.toString());
        response.setTimestamp(new Date().getTime());
        response.setResult(message);
        return response;
    }

    public static <T> BaseResponse<T> exceptionResponse(String code, String message) {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(code);
        response.setStatus("false");
        response.setMsg(message);
        response.setTimestamp(new Date().getTime());
        return response;
    }

}
