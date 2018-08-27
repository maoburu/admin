package me.maoburu.service;

import me.maoburu.pojo.Page;
import me.maoburu.pojo.Video;

public interface VideoService {

	/**
	 * 获得视频列表（分页）
	 * @param status 
	 * @param name 
	 * @param pageNum
	 * @param size
	 * @return
	 */
	Page listVideo(String name, String status, int pageNum, int size);
	
	/**
	 * 根据id获得视频
	 * @param id
	 * @return
	 */
	Video getVideoById(String id);
	
	/**
	 * 添加或更新视频(添加已禁用)
	 * @param video
	 * @param operation
	 * @return
	 */
	int addOrUpdateVideo(Video video, String operation);

	int ajaxUpdateVideoStatus(String id, String status);
	
}
