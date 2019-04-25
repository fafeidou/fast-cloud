/**  * @author Batman.qin  */

package com.fast.cloud.common.quarz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fast.cloud.common.quarz.entity.ScheduleJobEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 定时任务
 *
 */
@Mapper
public interface ScheduleJobDao extends BaseMapper<ScheduleJobEntity> {

    /**
     * 批量更新状态
     */
    int updateBatch(Map<String, Object> map);
}
