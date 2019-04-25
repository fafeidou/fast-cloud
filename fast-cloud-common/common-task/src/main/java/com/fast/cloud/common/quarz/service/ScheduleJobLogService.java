/**  * @author Batman.qin  */

package com.fast.cloud.common.quarz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fast.cloud.common.quarz.entity.ScheduleJobLogEntity;
import com.fast.cloud.common.quarz.utils.PageUtils;

import java.util.Map;

/**
 * 定时任务日志
 *
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
