package com.fast.cloud.biz.bean.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-01-15 9:26
 */
@Data
public class CityRequest {
    @NotEmpty(message = "不能为空值")
    @NotBlank(message="年龄不能为空")
    private String name;
}
