package me.maoburu.pojo;

import java.util.Date;

public class Programa {
	
	private String id;
	private String name;//节目名
	private Date onlineTime;//上线时间
	private Date offlineTime;//下线时间
	private String status;//状态
	private String url;//跳转url
	private String memo;//描述
	private String background;//背景
	private Integer position;//排序位置
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}
	public Date getOfflineTime() {
		return offlineTime;
	}
	public void setOfflineTime(Date offlineTime) {
		this.offlineTime = offlineTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	
	@Override
	public String toString() {
		return "Programa [background=" + background + ", id=" + id + ", memo="
				+ memo + ", name=" + name + ", offlineTime=" + offlineTime
				+ ", onlineTime=" + onlineTime + ", position=" + position
				+ ", status=" + status + ", url=" + url + "]";
	}
}
