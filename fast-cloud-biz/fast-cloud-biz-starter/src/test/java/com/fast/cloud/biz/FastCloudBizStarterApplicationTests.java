package com.fast.cloud.biz;

import com.fast.cloud.biz.bean.request.UserRequest;
import com.fast.cloud.biz.bean.response.CollectionWithPaginationAndAbstractResponse;
import com.fast.cloud.biz.bean.vo.UserVo;
import com.fast.cloud.biz.service.mybatis.UserService;
import com.fast.cloud.mybatis.bean.request.MyBatisRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastCloudBizStarterApplicationTests {
    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {
        for (int i = 0; i < 100; i++) {
            UserVo user = new UserVo();
            user.setId(UUID.randomUUID().toString());
            user.setUserName("user1" + i);
            user.setPassword("password1" + i);
            userService.add(user);
        }
    }

    @Test
    public void testQuery() {
        MyBatisRequest<UserRequest> request = new MyBatisRequest<>();
        request.setSortName("user_name");
        request.setSortOrder(1);
        UserRequest userRequest = new UserRequest();
        userRequest.setUserName("user128");
        userRequest.setPassword("password");
        request.setRequestModel(userRequest);
        List<UserVo> query = userService.query(request);
        System.out.println(query.size());
    }

    @Test
    public void testPage() {
        MyBatisRequest<UserRequest> request = new MyBatisRequest<>();
        request.setPageNumber(1);
        request.setPageSize(10);
        request.setSortName("user_name");
        request.setSortOrder(1);
        UserRequest userRequest = new UserRequest();
        userRequest.setUserName("user128");
        userRequest.setPassword("password");
        request.setRequestModel(userRequest);
        CollectionWithPaginationAndAbstractResponse<UserVo> page = userService.findPage(request);
        System.out.println(page);
    }

}

