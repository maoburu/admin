package me.maoburu.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import me.maoburu.constant.ResultInfo;
import me.maoburu.pojo.Page;
import me.maoburu.pojo.Video;
import me.maoburu.pojo.VideoChildren;
import me.maoburu.service.VideoService;
import me.maoburu.util.StringUtils;

@Controller
@RequestMapping("/video")
public class VideoController {
	
	@Resource
	private VideoService videoService;
	
	@RequestMapping("/index.do")
	public String index() {
		return "video/index";
	}
	
	@RequestMapping("/ajaxListVideo.do")
	public void ajaxListVideo(String name, String status, String pageNumber, String pageSize, HttpServletResponse response) throws IOException {
		int pageNum = StringUtils.isblank(pageNumber)? 1 : Integer.valueOf(pageNumber);
		int size = StringUtils.isblank(pageNumber)? 1 : Integer.valueOf(pageSize);
		name = new String(name.getBytes("iso8859-1"),"UTF-8");
		Page page = videoService.listVideo(name, status, pageNum, size);
		response.getWriter().write(new Gson().toJson(page));
	}
	
	@RequestMapping("/edit.do")
	public String edit(String id, HttpServletRequest request) {
		if (id != null) {
			Video video = videoService.getVideoById(id);
			request.setAttribute("video", video);
			request.setAttribute("operation", "update");
		}
		return "video/edit";
	}
	
	@RequestMapping("/addOrUpdateVideo.do")
	public String addOrUpdateVideo(Video video,String fileUrl, String playUrl, String operation, HttpServletRequest request) {
		VideoChildren videoChildren = new VideoChildren();
		videoChildren.setPlayUrl(playUrl);
		videoChildren.setFileUrl(fileUrl);
		video.setVideoChildren(videoChildren);
		int result = videoService.addOrUpdateVideo(video, operation);
		if (result < 0) {
			request.setAttribute("notify", ResultInfo.ADMIN_NOT_EXIST);
		} else {
			request.setAttribute("notify", ResultInfo.EDIT_SUCCESS);
		}
		return "video/index";
	}
	
	@RequestMapping("/ajaxUpdateVideoStatus.do")
	@ResponseBody
	public String ajaxUpdateVideoStatus(String id, String status) {
		int result = videoService.ajaxUpdateVideoStatus(id, status);
		return new Gson().toJson(result);
	}
	
	@RequestMapping("/ajaxchangeVideoStatusList.do")
	@ResponseBody
	public String ajaxchangeVideoStatusList(String status, HttpServletRequest request) {
		String[] idList = request.getParameterValues("idList[]");
		int result = 0;
		for (String id : idList) {
			result = videoService.ajaxUpdateVideoStatus(id, status);
			if (result == -1) {
				break;
			}
		}
		return new Gson().toJson(result);
	}
}
