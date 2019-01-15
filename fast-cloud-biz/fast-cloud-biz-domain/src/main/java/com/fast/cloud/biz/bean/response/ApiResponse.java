package com.fast.cloud.biz.bean.response;

import lombok.Data;

import java.io.Serializable;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-01-15 9:16
 */
@Data
public class ApiResponse<T> implements Serializable {
    private T data;
    private Integer code;

    public static <T> ApiResponse<T> success(T result) {
        ApiResponse<T> response = new ApiResponse<T>();
        response.setData(result);
        response.setCode(0);
        return response;
    }


}
