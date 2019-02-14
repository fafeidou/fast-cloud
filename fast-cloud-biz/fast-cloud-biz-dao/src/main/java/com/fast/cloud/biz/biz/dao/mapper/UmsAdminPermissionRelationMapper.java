package com.fast.cloud.biz.biz.dao.mapper;

import com.fast.cloud.biz.domain.UmsAdminPermissionRelation;
import com.fast.cloud.mybatis.mapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限)
 *
 * @author batman.qin
 * @email xx@.com
 * @date 2019-02-14 13:42:26
 */
@Mapper
public interface UmsAdminPermissionRelationMapper extends MyMapper<UmsAdminPermissionRelation> {
}
