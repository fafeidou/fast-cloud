package com.fast.cloud.biz;

import com.fast.cloud.biz.bean.request.UserRequest;
import com.fast.cloud.biz.bean.response.CollectionWithPaginationAndAbstractResponse;
import com.fast.cloud.biz.bean.vo.UserVo;
import com.fast.cloud.biz.biz.dao.mapper.UmsAdminMapper;
import com.fast.cloud.biz.domain.UmsAdmin;
import com.fast.cloud.biz.service.mybatis.UserService;
import com.fast.cloud.core.utils.date.DateUtil;
import com.fast.cloud.core.utils.gson.GsonUtil;
import com.fast.cloud.mybatis.bean.request.MyBatisRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastCloudBizStarterApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    JdbcTemplate jdbcTemplate;

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

    @Test
    public void test2() {
        Runnable noArguments = () -> System.out.println("Hello World");

        noArguments.run();

        Runnable bbb = () -> {
            System.out.println("sdf");
            System.out.println("sdf");
        };
        int num = 2;
        Runnable aaa = new Runnable() {
            @Override
            public void run() {
                System.out.println(num);
                System.out.println("hello world");
            }
        };

        BinaryOperator<Long> add = (x, y) -> x + y;

        Long apply = add.apply(1l, 2l);
        System.out.println(apply);

        BinaryOperator<Integer> addExplicit = (x, y) -> x + y;
        int apply1 = addExplicit.apply(1, 2);
        System.out.println(apply1);

        int result = Stream.of(1, 2, 3, 4)
                .reduce(0, (acc, element) -> acc + element);
        assertEquals(10, result);
    }

    private static Lock lock = new ReentrantLock();
    private static List<Integer> list2 = new ArrayList<>();
    private static List<Integer> list3 = new ArrayList<>();

    @Test
    public void test3() {
        IntStream.range(0, 10000).parallel().forEach(list2::add);
        IntStream.range(0, 10000).forEach(i -> {
//            lock.lock();
            try {
                list3.add(i);
            } finally {
//                lock.unlock();
            }
        });
        System.out.println("并行执行的大小：" + list2.size());
        System.out.println("加锁并行执行的大小：" + list3.size());
    }

    @Test
    public void testJdbc() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from ums_admin");
        System.out.println(GsonUtil.toJson(maps));
    }

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Test
    public void testAdmin() {
//        List<UmsAdmin> umsAdmins = umsAdminMapper.selectAll();
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setId(1l);
        umsAdmin.setEmail("sdfsdfs");
        umsAdminMapper.updateByPrimaryKeySelective(umsAdmin);
//        System.out.println(GsonUtil.toJson(umsAdmins));
    }

    @Test
    public void testInsertQuery() {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setId(123231l);
        umsAdmin.setCreateTime(new Date());
        umsAdminMapper.insertSelective(umsAdmin);
        UmsAdmin umsAdmin1 = umsAdminMapper.selectByPrimaryKey(123231l);
        System.out.println(DateUtil.format(umsAdmin1.getCreateTime(), DateUtil.FORMAT_DATE_YMDHMS));

    }

}

