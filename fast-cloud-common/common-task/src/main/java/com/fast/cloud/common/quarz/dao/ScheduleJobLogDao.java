/**  * @author Batman.qin  */

package com.fast.cloud.common.quarz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fast.cloud.common.quarz.entity.ScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志
 *
 */
@Mapper
public interface ScheduleJobLogDao extends BaseMapper<ScheduleJobLogEntity> {

}
