/**  * @author Batman.qin  */

package com.fast.cloud.mybatis.plus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fast.cloud.mybatis.plus.domain.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户
 *
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {

}
