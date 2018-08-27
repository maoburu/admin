package me.maoburu.pojo;

import java.util.Date;

public class Video {
	
	private String id;
	private String name;
	private String charge;
	private String status;
	private Date onlineTime;
	private Date offlineTime;
	private String url;//播放地址
	private int episode;//集数
	private int period;//期数
	private String itemId;
	private VideoChildren videoChildren;
	
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
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getPlayUrl() {
		return url;
	}
	public void setPlayUrl(String url) {
		this.url = url;
	}
	public int getEpisode() {
		return episode;
	}
	public void setEpisode(int episode) {
		this.episode = episode;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public VideoChildren getVideoChildren() {
		return videoChildren;
	}
	public void setVideoChildren(VideoChildren videoChildren) {
		this.videoChildren = videoChildren;
	}
	
}
