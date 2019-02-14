package com.fast.cloud.biz.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限)
 * 
 * @author batman.qin
 * @email xx@.com
 * @date 2019-02-14 13:42:26
 */
public class UmsAdminPermissionRelation  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private Long adminId;
	//
	private Long permissionId;
	//
	private Integer type;

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
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	/**
	 * 获取：
	 */
	public Long getAdminId() {
		return adminId;
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
	/**
	 * 设置：
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public Integer getType() {
		return type;
	}
}
