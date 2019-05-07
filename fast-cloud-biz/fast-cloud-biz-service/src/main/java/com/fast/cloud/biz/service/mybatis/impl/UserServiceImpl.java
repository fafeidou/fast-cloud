package com.fast.cloud.biz.service.mybatis.impl;

import com.fast.cloud.biz.bean.request.UserRequest;
import com.fast.cloud.biz.bean.response.CollectionWithPaginationAndAbstractResponse;
import com.fast.cloud.biz.bean.vo.UserVo;
import com.fast.cloud.biz.domain.UserModel;
import com.fast.cloud.biz.service.mybatis.UserService;
import com.fast.cloud.mybatis.bean.request.MyBatisRequest;
import com.fast.cloud.mybatis.service.BaseService;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-01-15 9:50
 */
@Service
public class UserServiceImpl extends BaseService<UserModel> implements UserService {
    @Override
    public Boolean add(UserVo userVo) {
        return mapper.insertSelective(userModelConverter().apply(userVo)) > 1 ? true : false;
    }

    @Override
    public UserVo findById(String id) {
        return userVoConverter().apply(mapper.selectByPrimaryKey(id));
    }

    @Override
    public UserVo update(UserVo userVo) {
        return mapper.updateByPrimaryKeySelective(userModelConverter().apply(userVo)) > 1 ? userVo : null;
    }

    @Override
    public List<UserVo> queryAll() {
        List<UserModel> userModels = mapper.selectAll();
        if (CollectionUtils.isEmpty(userModels)) {
            return Collections.EMPTY_LIST;
        }
        return userModels
                .stream()
                .map(i -> userVoConverter().apply(i))
                .collect(toList());
    }

    @Override
    public CollectionWithPaginationAndAbstractResponse<UserVo> findPage(MyBatisRequest<UserRequest> request) {
        ISelect select = () -> mapper.selectByExample(request.getExample(UserModel.class, "id", "username"));

        Page<UserModel> userModelPage = PageHelper.startPage(request.getPageNumber(), request.getPageSize())
                .doSelectPage(select);

        CollectionWithPaginationAndAbstractResponse<UserVo> response = new CollectionWithPaginationAndAbstractResponse<>();

        if (!CollectionUtils.isEmpty(userModelPage.getResult())) {
            response.setDetails(userModelPage.getResult()
                    .stream()
                    .map(i -> userVoConverter().apply(i))
                    .collect(toList()));
            response.setTotal((int) userModelPage.getTotal());
        } else {
            response.setDetails(Collections.emptyList());
            response.setTotal(0);
        }
        return response;
    }

    @Override
    public List<UserVo> query(MyBatisRequest<UserRequest> request) {
        return Optional
                .ofNullable(mapper.selectByExample(request.getExample(UserModel.class, "id", "username")))
                .get()
                .stream()
                .map(i -> userVoConverter().apply(i))
                .collect(toList());
    }

    public Function<UserVo, UserModel> userModelConverter() {
        return (userVo -> {
            UserModel userModel = new UserModel();
            userModel.setId(userVo.getId());
            userModel.setUserName(userVo.getUserName());
            userModel.setPassword(userVo.getPassword());
            return userModel;
        }
        );
    }

    public Function<UserModel, UserVo> userVoConverter() {
        return (userModel -> {
            UserVo userVo = new UserVo();
            userVo.setId(userModel.getId());
            userVo.setUserName(userModel.getUserName());
            userVo.setPassword(userModel.getPassword());
            return userVo;
        }
        );
    }
}
