package com.fast.cloud.biz.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 后台用户和角色关系表
 * 
 * @author batman.qin
 * @email xx@.com
 * @date 2019-02-14 13:42:26
 */
public class UmsAdminRoleRelation  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private Long adminId;
	//
	private Long roleId;

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
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	/**
	 * 获取：
	 */
	public Long getRoleId() {
		return roleId;
	}
}
