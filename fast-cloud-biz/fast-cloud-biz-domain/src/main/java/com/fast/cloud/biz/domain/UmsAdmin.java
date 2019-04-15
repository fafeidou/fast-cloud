package com.fast.cloud.biz.domain;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;



/**
 * 后台用户表
 * 
 * @author batman.qin
 * @email xx@.com
 * @date 2019-02-14 13:42:26
 */
public class UmsAdmin  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	@Id
	private Long id;
	//
	private String username;
	//
	private String password;
	//头像
	private String icon;
	//邮箱
	private String email;
	//昵称
	private String nickName;
	//备注信息
	private String note;
	//创建时间
	private Date createTime;
	//最后登录时间
	private Date loginTime;
	//帐号启用状态：0->禁用；1->启用
	private Integer status;

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
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：头像
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * 获取：头像
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：昵称
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * 获取：昵称
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * 设置：备注信息
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * 获取：备注信息
	 */
	public String getNote() {
		return note;
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
	 * 设置：最后登录时间
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	/**
	 * 获取：最后登录时间
	 */
	public Date getLoginTime() {
		return loginTime;
	}
	/**
	 * 设置：帐号启用状态：0->禁用；1->启用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：帐号启用状态：0->禁用；1->启用
	 */
	public Integer getStatus() {
		return status;
	}
}
