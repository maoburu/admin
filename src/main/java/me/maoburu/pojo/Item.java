package me.maoburu.pojo;

import java.util.Date;

public class Item {
	private String id;
	private String name;
	private String keyword;//关键字
	private Date onlineTime;//上线时间
	private Date offlineTime;//下线时间
	private String charge;//免付费标识
	private String status;//状态
	private String memo;//简介
	private String update;//最新集数
	private String area;//产地
	private String type;//类型
	private String guest;//演员（嘉宾）
	private String poster;//海报
	private Integer fileCount;//总集数
	private String finishStatus;//完结状态
	private String director;//导演
	private String year;//上映年份
	private String language;//上映年份
	private String viewPoint;//看点
	private String category;//标签
	private String score;//总分
	private Integer like;//收藏人数
	private String corner;//角标
	private Integer pointPerson;//评分人数
	private Date updateTime;//更新时间
	private Integer position;//排序顺序
	
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public Integer getFileCount() {
		return fileCount;
	}
	public void setFileCount(Integer fileCount) {
		this.fileCount = fileCount;
	}
	public String getFinishStatus() {
		return finishStatus;
	}
	public void setFinishStatus(String finishStatus) {
		this.finishStatus = finishStatus;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getViewPoint() {
		return viewPoint;
	}
	public void setViewPoint(String viewPoint) {
		this.viewPoint = viewPoint;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public Integer getLike() {
		return like;
	}
	public void setLike(Integer like) {
		this.like = like;
	}
	public String getCorner() {
		return corner;
	}
	public void setCorner(String corner) {
		this.corner = corner;
	}
	public Integer getPointPerson() {
		return pointPerson;
	}
	public void setPointPerson(Integer pointPerson) {
		this.pointPerson = pointPerson;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Override
	public String toString() {
		return "Item [area=" + area + ", category=" + category + ", charge="
				+ charge + ", corner=" + corner + ", director=" + director
				+ ", fileCount=" + fileCount + ", finishStatus=" + finishStatus
				+ ", guest=" + guest + ", id=" + id + ", keyword=" + keyword
				+ ", like=" + like + ", memo=" + memo + ", name=" + name
				+ ", offlineTime=" + offlineTime + ", onlineTime=" + onlineTime
				+ ", pointPerson=" + pointPerson + ", position=" + position
				+ ", poster=" + poster + ", score=" + score + ", status="
				+ status + ", type=" + type + ", update=" + update
				+ ", updateTime=" + updateTime + ", viewPoint=" + viewPoint
				+ ", year=" + year + "]";
	}
	
}
