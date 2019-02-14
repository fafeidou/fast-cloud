package com.fast.cloud.biz.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 后台用户角色表
 * 
 * @author batman.qin
 * @email xx@.com
 * @date 2019-02-14 13:42:26
 */
public class UmsRole  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//名称
	private String name;
	//描述
	private String description;
	//后台用户数量
	private Integer adminCount;
	//创建时间
	private Date createTime;
	//启用状态：0->禁用；1->启用
	private Integer status;
	//
	private Integer sort;

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
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：后台用户数量
	 */
	public void setAdminCount(Integer adminCount) {
		this.adminCount = adminCount;
	}
	/**
	 * 获取：后台用户数量
	 */
	public Integer getAdminCount() {
		return adminCount;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：启用状态：0->禁用；1->启用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：启用状态：0->禁用；1->启用
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：
	 */
	public Integer getSort() {
		return sort;
	}
}
