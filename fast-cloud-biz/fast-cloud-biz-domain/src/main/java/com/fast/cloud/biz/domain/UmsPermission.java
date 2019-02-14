package com.fast.cloud.biz.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 后台用户权限表
 * 
 * @author batman.qin
 * @email xx@.com
 * @date 2019-02-14 13:42:26
 */
public class UmsPermission  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//父级权限id
	private Long pid;
	//名称
	private String name;
	//权限值
	private String value;
	//图标
	private String icon;
	//权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
	private Integer type;
	//前端资源路径
	private String uri;
	//启用状态；0->禁用；1->启用
	private Integer status;
	//创建时间
	private Date createTime;
	//排序
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
	 * 设置：父级权限id
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}
	/**
	 * 获取：父级权限id
	 */
	public Long getPid() {
		return pid;
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
	 * 设置：权限值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：权限值
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置：图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * 获取：图标
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * 设置：权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：前端资源路径
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	/**
	 * 获取：前端资源路径
	 */
	public String getUri() {
		return uri;
	}
	/**
	 * 设置：启用状态；0->禁用；1->启用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：启用状态；0->禁用；1->启用
	 */
	public Integer getStatus() {
		return status;
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
	 * 设置：排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序
	 */
	public Integer getSort() {
		return sort;
	}
}
