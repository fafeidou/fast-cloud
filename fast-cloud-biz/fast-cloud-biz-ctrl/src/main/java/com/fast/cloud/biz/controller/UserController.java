package com.fast.cloud.biz.controller;

import com.fast.cloud.biz.bean.request.UserRequest;
import com.fast.cloud.biz.bean.response.ApiResponse;
import com.fast.cloud.biz.bean.response.CollectionWithPaginationAndAbstractResponse;
import com.fast.cloud.biz.bean.vo.UserVo;
import com.fast.cloud.mybatis.bean.request.MyBatisRequest;
import com.fast.cloud.biz.service.mybatis.UserService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-01-15 9:51
 */
@RestController
public class UserController {
    public static final String ROOT = "admin/user/";

    @Autowired
    private UserService userService;

    @RequestMapping(value = ROOT + "add", method = RequestMethod.POST)
    public ApiResponse<Boolean> add(UserVo userVo) {
        return ApiResponse.success(userService.add(userVo));
    }

    @RequestMapping(value = ROOT + "get/{id}", method = RequestMethod.GET)
    public ApiResponse<UserVo> get(String id) {
        return ApiResponse.success(userService.findById(id));
    }

    @RequestMapping(value = ROOT + "update", method = RequestMethod.POST)
    public ApiResponse<UserVo> update(UserVo userVo) {
        return ApiResponse.success(userService.update(userVo));
    }

    @RequestMapping(value = ROOT + "remove/{ids}", method = RequestMethod.GET)
    public ApiResponse<Boolean> remove(String ids) {
        if (StringUtils.isNotBlank(ids)) {
//            userService.delete(ids.split(","));
        }
        return ApiResponse.success(true);
    }

    @RequestMapping(value = ROOT + "/all", method = RequestMethod.GET)
    public ApiResponse<List<UserVo>> list() {
        return ApiResponse.success(userService.queryAll());
    }

    @RequestMapping(value = ROOT + "/findPage")
    public ApiResponse<CollectionWithPaginationAndAbstractResponse<UserVo>> findPage(@RequestBody MyBatisRequest<UserRequest> request) {
        return ApiResponse.success(userService.findPage(request));
    }

    @RequestMapping(value = ROOT + "find")
    public ApiResponse<List<UserVo>> find(@RequestBody MyBatisRequest<UserRequest> request) {
        return ApiResponse.success(userService.query(request));
    }
}
