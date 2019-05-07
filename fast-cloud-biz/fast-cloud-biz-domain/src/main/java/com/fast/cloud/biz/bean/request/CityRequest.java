package com.fast.cloud.biz.bean.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-01-15 9:26
 */
@Data
public class CityRequest {
    @NotBlank(message = "hello")
    private String name;
}
