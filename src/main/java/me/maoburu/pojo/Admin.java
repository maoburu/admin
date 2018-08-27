package me.maoburu.pojo;

import java.util.Date;

public class Admin {
	
	private String id;
	private String name;//登录名
	private String realName;//真实姓名
	private String password;
	private String phone;
	private Integer role;//权限
	private int checkTime;//密码错误次数
	private int valid;//状态 0：冻结 1：解冻
	private Date updateTime;//上次修改密码时间
	private String sessionId;//sessionId
	private String ip;//ip
	private String desc;
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public int getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(int checkTime) {
		this.checkTime = checkTime;
	}
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Admin [checkTime=" + checkTime + ", desc=" + desc + ", id="
				+ id + ", ip=" + ip + ", name=" + name + ", password="
				+ password + ", phone=" + phone + ", realName=" + realName
				+ ", role=" + role + ", sessionId=" + sessionId
				+ ", updateTime=" + updateTime + ", valid=" + valid + "]";
	}
	
}
