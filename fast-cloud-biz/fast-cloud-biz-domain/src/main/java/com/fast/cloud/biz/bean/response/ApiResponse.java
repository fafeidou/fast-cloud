package com.fast.cloud.biz.bean.response;

import com.fast.cloud.core.utils.gson.GsonUtil;
import lombok.Data;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;

import java.io.Serializable;
import java.util.Locale;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-01-15 9:16
 */
@Data
public class ApiResponse<T> implements Serializable {
    private T data;
    private Integer code;
    private String message;
    //参数校验失败
    public static final int VALIDATE_FAILED = 404;

    public static <T> ApiResponse<T> success(T result) {
        ApiResponse<T> response = new ApiResponse<T>();
        response.setData(result);
        response.setCode(0);
        return response;
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
        response.setMessage(message);
        return response;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this);
    }

}
