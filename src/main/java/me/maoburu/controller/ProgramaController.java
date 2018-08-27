package me.maoburu.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;

import me.maoburu.constant.Constants;
import me.maoburu.constant.ResultInfo;
import me.maoburu.pojo.Page;
import me.maoburu.pojo.Programa;
import me.maoburu.service.ProgramaService;
import me.maoburu.util.StringUtils;

@Controller
@RequestMapping("/programa")
public class ProgramaController {
	
	@Resource
	private ProgramaService programaService;
	
	@RequestMapping("/index.do")
	public String index() {
		return "programa/index";
	}
	
	@RequestMapping("/ajaxListMainPrograma.do")
	public void ajaxListMainPrograma(String pageNumber, String pageSize, HttpServletResponse response) throws IOException {
		int pageNum = StringUtils.isblank(pageNumber)? 1 : Integer.valueOf(pageNumber);
		int size = StringUtils.isblank(pageNumber)? 1 : Integer.valueOf(pageSize);
		
		Page page = programaService.listMainPrograma(pageNum, size);
		response.getWriter().write(new Gson().toJson(page));
	}
	
	@RequestMapping("/ajaxListPrograma.do")
	public void ajaxListPrograma(String pageNumber, String pageSize, HttpServletResponse response) throws IOException {
		int pageNum = StringUtils.isblank(pageNumber)? 1 : Integer.valueOf(pageNumber);
		int size = StringUtils.isblank(pageNumber)? 1 : Integer.valueOf(pageSize);
		Page page = programaService.list(pageNum, size);
		response.getWriter().write(new Gson().toJson(page));
	}
	
	@RequestMapping("/secondPrograma.do")
	public String secondPrograma(Programa programa, HttpServletRequest request) {
		try {
			programa.setName(new String(programa.getName().getBytes("iso8859-1"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setAttribute("programa", programa);
		return "programa/second";
	}
	
	@RequestMapping("/ajaxListSecondPrograma.do")
	public void ajaxListSecondPrograma(String id, String pageNumber, String pageSize, HttpServletResponse response) throws IOException {
		int pageNum = StringUtils.isblank(pageNumber)? 1 : Integer.valueOf(pageNumber);
		int size = StringUtils.isblank(pageNumber)? 1 : Integer.valueOf(pageSize);
		
		Page page = programaService.listPrograma(id, pageNum, size);
		response.getWriter().write(new Gson().toJson(page));
	}
	
	@RequestMapping("/listItem.do")
	public String listItem(Programa programa, HttpServletRequest request) {
		try {
			programa.setName(new String(programa.getName().getBytes("iso8859-1"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setAttribute("programa", programa);
		return "programa/item_list";
	}
	
	@RequestMapping("/ajaxListRelateItem.do")
	public void ajaxListItem(String id,String name, String status, String pageNumber, String pageSize, HttpServletResponse response) throws IOException {
		int pageNum = StringUtils.isblank(pageNumber)? 1 : Integer.valueOf(pageNumber);
		int size = StringUtils.isblank(pageNumber)? 1 : Integer.valueOf(pageSize);
		name = new String(name.getBytes("iso8859-1"),"UTF-8");
		Page page = programaService.listRelateItem(id, name, status, pageNum, size);
		
		response.getWriter().write(new Gson().toJson(page));
	}
	
	@RequestMapping("/edit.do")
	public String edit(String id, HttpServletRequest request) {
		if (id != null) {
			Programa programa = programaService.getProgramaById(id);
			request.setAttribute("programa", programa);
			request.setAttribute("operation", "update");
		}
		return "programa/edit";
	}
	
	@RequestMapping("addOrUpdatePrograma.do")
	public String addOrUpdatePrograma(Programa programa, String operation,
			HttpServletRequest request) {
		int result = programaService.addOrUpdatePrograma(programa, operation);
			
		if (result < 0) {
			request.setAttribute("notify", ResultInfo.ADMIN_NOT_EXIST);
		} else {
			request.setAttribute("notify", ResultInfo.EDIT_SUCCESS);
		}
		return "programa/index";
		
	}
	
	@RequestMapping("/uploadPicture.do")
	public void uploadPicture(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="file", required = false) CommonsMultipartFile file
			) {
		if (file != null && !file.isEmpty()) {
			String basePath = request.getSession().getServletContext().getRealPath("/");
			basePath = (basePath + "uploadImg").replace("\\", "/");
			File storeFile = new File(basePath);
			if (!storeFile.exists()) {
				storeFile.mkdirs();
			}
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String newFileName = UUID.randomUUID().toString().replace("-", "") + suffix;
			try {
				file.transferTo(new File(storeFile, newFileName));
				Map result = new HashMap();
				result.put("status", 1);
				result.put("fileName", newFileName);
				result.put("path",Constants.UPLOADIMG_PATH );
				response.getWriter().write(new Gson().toJson(result));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping("/relatePrograma.do")
	public String relatePrograma(String parentId, HttpServletRequest request) {
		request.setAttribute("parent_id", parentId);
		return "programa/relate_programa";
	}
	
	@RequestMapping("/ajaxRelatePrograma.do")
	public void ajaxRelatePrograma(String id, String parentId,HttpServletResponse response) {
		int result = programaService.relatePrograma(id, parentId);
		try {
			response.getWriter().write(new Gson().toJson(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	@RequestMapping("/ajaxRelateProgramaList.do")
	public void ajaxRelateProgramaList(String parentId,HttpServletRequest request, HttpServletResponse response) {
		String[] idList = request.getParameterValues("idList[]");
		int result = 0;
		for (String id : idList) {
			result = programaService.relatePrograma(id, parentId);
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
	
	@RequestMapping("/relateItem.do")
	public String relateItem(String parentId, HttpServletRequest request) {
		request.setAttribute("parent_id", parentId);
		return "programa/relate_item";
	}
	
	@RequestMapping("/ajaxListItem.do")
	public void ajaxListItem(String name, String pageNumber, String pageSize, HttpServletResponse response) throws UnsupportedEncodingException {
		int pageNum = StringUtils.isblank(pageNumber)? 1 : Integer.valueOf(pageNumber);
		int size = StringUtils.isblank(pageNumber)? 1 : Integer.valueOf(pageSize);
		name = new String(name.getBytes("iso8859-1"),"UTF-8");
		Page page = programaService.listItem(name, pageNum, size);
		try {
			response.getWriter().write(new Gson().toJson(page));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/ajaxRelateItem.do")
	public void ajaxRelateItem(String id, String parentId, HttpServletResponse response) {
		int result = programaService.relateItem(id, parentId);
		try {
			response.getWriter().write(new Gson().toJson(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/ajaxRelateItemList.do")
	public void ajaxRelateItemList(String parentId,HttpServletRequest request, HttpServletResponse response) {
		String[] idList = request.getParameterValues("idList[]");
		int result = 0;
		for (String id : idList) {
			result = programaService.relateItem(id, parentId);
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
	
	@RequestMapping("/upPrograma.do")
	@ResponseBody
	public String upPrograma(String id, String parentId, int position) {
		int result = programaService.upPrograma(id, parentId, position);
		return new Gson().toJson(result);
	}
	
	@RequestMapping("/downPrograma.do")
	@ResponseBody
	public String downPrograma(String id, String parentId, int position) {
		int result = programaService.downPrograma(id, parentId, position);
		return new Gson().toJson(result);
	}
	
	@RequestMapping("/removeRelatePrograma.do")
	@ResponseBody
	public String removeRelatePrograma(String id, String parentId, int position) {
		String markId = "0";
		int result = programaService.removeRelate(id, parentId, position, markId);
		return new Gson().toJson(result);
	}
	
	@RequestMapping("/upItem.do")
	@ResponseBody
	public String upItem(String id, String parentId, int position) {
		int result = programaService.upItem(id, parentId, position);
		return new Gson().toJson(result);
	}
	
	@RequestMapping("/downItem.do")
	@ResponseBody
	public String downItem(String id, String parentId, int position) {
		int result = programaService.downItem(id, parentId, position);
		return new Gson().toJson(result);
	}
	
	@RequestMapping("/removeRelateItem.do")
	@ResponseBody
	public String removeRelateItem(String id, String parentId, int position) {
		String markId = "1";
		int result = programaService.removeRelate(id, parentId, position, markId);
		return new Gson().toJson(result);
	}
	
	@RequestMapping("/editPoster.do")
	public String editPoster(String id, String parentId,String type, HttpServletRequest request) {
		
		String poster = programaService.getItemById(id, parentId, type);
		request.setAttribute("poster", poster);
		
		request.setAttribute("id", id);
		request.setAttribute("parentId", parentId);
		request.setAttribute("type", type);
		
		return "programa/edit_poster";
	}
	
	@RequestMapping("/updateRelatePoster.do")
	@ResponseBody
	public String updateRelatePoster(String id, String parentId, String type, String poster) {
		int result = programaService.updateRelatePoster(id, parentId, type, poster);
		return new Gson().toJson(result);
	}
}
