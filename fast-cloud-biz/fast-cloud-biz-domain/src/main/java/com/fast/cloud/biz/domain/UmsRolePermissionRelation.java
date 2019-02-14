package com.fast.cloud.biz.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 后台用户角色和权限关系表
 * 
 * @author batman.qin
 * @email xx@.com
 * @date 2019-02-14 13:42:26
 */
public class UmsRolePermissionRelation  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private Long roleId;
	//
	private Long permissionId;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	/**
	 * 获取：
	 */
	public Long getRoleId() {
		return roleId;
	}
	/**
	 * 设置：
	 */
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	/**
	 * 获取：
	 */
	public Long getPermissionId() {
		return permissionId;
	}
}
