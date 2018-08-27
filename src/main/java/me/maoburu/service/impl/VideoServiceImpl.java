package me.maoburu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import me.maoburu.dao.VideoDao;
import me.maoburu.pojo.Page;
import me.maoburu.pojo.Video;
import me.maoburu.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
	
	@Resource
	private VideoDao videoDao;

	@Override
	public Page listVideo(String name, String status, int pageNum, int size) {
		PageHelper.startPage(pageNum, size);
		List<Video> videoList = videoDao.listVideo(name, status);
		long total = new PageInfo(videoList).getTotal();
		Page page = new Page();
		page.setRows(videoList);
		page.setTotal(total);
		return page;
	}

	@Override
	public Video getVideoById(String id) {
		return videoDao.getVideoById(id);
	}

	@Override
	public int addOrUpdateVideo(Video video, String operation) {
		return videoDao.updateVideo(video);
	}

	@Override
	public int ajaxUpdateVideoStatus(String id, String status) {
		return videoDao.updateVideoStatus(id, status);
	}
	
}
