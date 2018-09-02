package me.maoburu.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import me.maoburu.constant.ResultInfo;
import me.maoburu.pojo.Item;
import me.maoburu.pojo.Page;
import me.maoburu.service.ItemService;
import me.maoburu.util.StringUtils;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Resource
	private ItemService itemService;
	
	@RequestMapping("/index.do")
	public String index() {
		return "item/index";
	}
	
	@RequestMapping("/ajaxListItem.do")
	@ResponseBody
	public Page ajaxListItem(String name, String status, String charge, String type, String pageNumber, String pageSize, HttpServletResponse response) throws IOException {
		int pageNum = StringUtils.isblank(pageNumber)? 1 : Integer.valueOf(pageNumber);
		int size = StringUtils.isblank(pageNumber)? 1 : Integer.valueOf(pageSize);
		Page page = itemService.listItem(name, status, charge, type, pageNum, size);
		return page;
	}
	
	@RequestMapping("/listRelateVideo.do")
	public String listRelateVideo(String id,String name, HttpServletRequest request) throws UnsupportedEncodingException {
		name = new String(name.getBytes("iso8859-1"),"UTF-8");
		request.setAttribute("name", name);
		request.setAttribute("id", id);
		return "item/video_list";
	}
	
	@RequestMapping("/ajaxListRelateVideo.do")
	public void ajaxListRelateVideo(String id, String name, String status, String pageNumber, String pageSize, HttpServletResponse response) throws IOException {
		int pageNum = StringUtils.isblank(pageNumber)? 1 : Integer.valueOf(pageNumber);
		int size = StringUtils.isblank(pageNumber)? 1 : Integer.valueOf(pageSize);
		name = new String(name.getBytes("iso8859-1"),"UTF-8");
		Page page = itemService.ajaxListRelateVideo(id, name, status, pageNum, size);
		response.getWriter().write(new Gson().toJson(page));
	}
	
	@RequestMapping("/edit.do")
	public String edit(String id, HttpServletRequest request) {
		if (id != null) {
			Item item = itemService.getItemById(id);
			request.setAttribute("item", item);
			request.setAttribute("operation", "update");
		}
		return "item/edit";
	}
	
	@RequestMapping("/addOrUpdateItem.do")
	public String addOrUpdateItem(Item item, String operation, HttpServletRequest request) {
		int result = itemService.addOrUpdateItem(item, operation);
		if (result < 0) {
			request.setAttribute("notify", ResultInfo.ADMIN_NOT_EXIST);
		} else {
			request.setAttribute("notify", ResultInfo.EDIT_SUCCESS);
		}
		return "item/index";
	}
	
	@RequestMapping("/relateVideo.do")
	public String relateVideo(String parentId, HttpServletRequest request) {
		request.setAttribute("parentId", parentId);
		return "item/relate_video";
	}
	
	@RequestMapping("/ajaxListVideo.do")
	public void ajaxListVideo(String name, String pageNumber, String pageSize, HttpServletResponse response) throws UnsupportedEncodingException {
		int pageNum = StringUtils.isblank(pageNumber)? 1 : Integer.valueOf(pageNumber);
		int size = StringUtils.isblank(pageNumber)? 1 : Integer.valueOf(pageSize);
		name = new String(name.getBytes("iso8859-1"),"UTF-8");
		Page page = itemService.listUnrelateVideo(name, pageNum, size);
		try {
			response.getWriter().write(new Gson().toJson(page));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/ajaxRelateVideo.do")
	public void ajaxRelateItem(String id, String parentId, HttpServletResponse response) {
		int result = itemService.relateVideo(id, parentId);
		try {
			response.getWriter().write(new Gson().toJson(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/ajaxRelateVideoList.do")
	public void ajaxRelateItemList(String parentId,HttpServletRequest request, HttpServletResponse response) {
		String[] idList = request.getParameterValues("idList[]");
		int result = 0;
		for (String id : idList) {
			result = itemService.relateVideo(id, parentId);
			if (result == -1) {
				break;
			}
		}
		try {
			response.getWriter().write(new Gson().toJson(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/upVideo.do")
	@ResponseBody
	public String upVideo(String id, String parentId, int episode) {
		int result = itemService.upVideo(id, parentId, episode);
		return new Gson().toJson(result);
	}
	
	@RequestMapping("/downVideo.do")
	@ResponseBody
	public String downVideo(String id, String parentId, int episode) {
		int result = itemService.downVideo(id, parentId, episode);
		return new Gson().toJson(result);
	}
	
	@RequestMapping("/removeRelateVideo.do")
	@ResponseBody
	public String removeRelateVideo(String id) {
		int result = itemService.removeRelateVideo(id);
		return new Gson().toJson(result);
	}
	
	@RequestMapping("/ajaxUpdateItemStatus.do")
	@ResponseBody
	public String ajaxUpdateItemStatus(String id, String status) {
		int result = itemService.ajaxUpdateItemStatus(id, status);
		return new Gson().toJson(result);
	}
	
	@RequestMapping("/ajaxUpdateItemCharge.do")
	@ResponseBody
	public String ajaxUpdateItemCharge(String id, String charge) {
		int result = itemService.ajaxUpdateItemCharge(id, charge);
		return new Gson().toJson(result);
	}
	
	@RequestMapping("/editPoster.do")
	public String ajaxUpdateItemCharge(String id, HttpServletRequest request) {
		Item item = itemService.getItemById(id);
		String poster = item.getPoster();
		request.setAttribute("poster", poster);
		request.setAttribute("id", id);
		return "item/edit_poster";
	}
	
	
	@RequestMapping("/ajaxUpdatePoster.do")
	@ResponseBody
	public String ajaxUpdatePoster(String id, String poster) {
		int result = itemService.ajaxUpdatePoster(id, poster);
		return new Gson().toJson(result);
	}
	
	@RequestMapping("/ajaxChangeItemStatusList.do")
	@ResponseBody
	public String ajaxChangeItemStatusList(String status, HttpServletRequest request) {
		String[] idList = request.getParameterValues("idList[]");
		int result = 0;
		for (String id : idList) {
			result = itemService.ajaxUpdateItemStatus(id, status);
			if (result == -1) {
				break;
			}
		}
		return new Gson().toJson(result);
	}
	
}
