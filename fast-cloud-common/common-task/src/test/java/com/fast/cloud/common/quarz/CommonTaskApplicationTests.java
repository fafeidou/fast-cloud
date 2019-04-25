package com.fast.cloud.common.quarz;

import com.fast.cloud.common.quarz.dao.ScheduleJobDao;
import com.fast.cloud.common.quarz.entity.ScheduleJobEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonTaskApplicationTests {
    @Autowired
    private ScheduleJobDao scheduleJobDao;
    @Test
    public void contextLoads() {
        ScheduleJobEntity scheduleJobEntity = scheduleJobDao.selectById(1);
        System.out.println();
    }

}
