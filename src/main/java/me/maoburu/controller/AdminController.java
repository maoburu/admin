package me.maoburu.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.maoburu.constant.Constants;
import me.maoburu.constant.ResultInfo;
import me.maoburu.pojo.Admin;
import me.maoburu.pojo.Page;
import me.maoburu.service.AdminService;
import me.maoburu.util.StringUtils;
import me.maoburu.util.Utils;
/**
 * 用户管理
 * @author xarrow
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Resource
	private AdminService adminService;
	
	@RequestMapping("/login")
	public String login(Admin admin,HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		String name = admin.getName();
		String password = admin.getPassword();
		
		Admin trueAdmin = adminService.getAdminByName(name);
		if (name == null || password == null || trueAdmin == null) {
			request.setAttribute("notify", "用户名或密码错误");
			return "login";
		}
		if (trueAdmin.getCheckTime() >= Constants.VALID_TIME) {
			trueAdmin.setValid(0);
			adminService.updateAdminStatus(trueAdmin);
			request.setAttribute("notify", "该用户已锁定");
			return "login";
		}
		if (trueAdmin.getValid() == 0) {
			request.setAttribute("notify", "该用户已锁定");
			return "login";
		}
		Calendar now = Calendar.getInstance();
		Calendar updateTime = Calendar.getInstance();
		updateTime.setTime(trueAdmin.getUpdateTime());
		updateTime.add(Calendar.DATE, Constants.VALID_DATE);
		if (updateTime.before(now)) {
			request.setAttribute("notify", "该用户距离上次修改密码超过30天，请修改密码后再尝试登陆");
			return "login";
		}
		if (trueAdmin.getPassword().equals(Utils.getMD5Str(password))) {
			trueAdmin.setPassword("");
			reGenerateSessionId(request);
			adminService.updateCheckTime(trueAdmin.getId(), 0);
			request.getSession().setAttribute("admin", trueAdmin);
			return "index";
		} else {
			adminService.updateCheckTime(trueAdmin.getId(), trueAdmin.getCheckTime() + 1);
			request.setAttribute("notify", "用户名或密码错误");
			return "admin/login";
		}
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "login";
	}
	
	@RequestMapping("/index.do")
	public String index(HttpServletRequest request) {
		return "admin/index";
	}
	
	@GetMapping("/ajaxListAdmin.do")
	@ResponseBody
	public Page ajaxListAdmin(String pageNumber, String pageSize, HttpServletResponse response) throws IOException {
		int pageNum = StringUtils.isblank(pageNumber)? 1 : Integer.valueOf(pageNumber);
		int size = StringUtils.isblank(pageNumber)? 1 : Integer.valueOf(pageSize);
		
		Page page = adminService.listAdmin(pageNum, size);
		
		return page;
	}
	
	@RequestMapping("/updateAdminStatus.do")
	public void updateAdminStatus(Admin admin, HttpServletResponse response) {
		adminService.updateAdminStatus(admin);
		try {
			response.getWriter().write("success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/addOrUpdateAdmin.do")
	public String addOrUpdateAdmin(Admin admin, HttpServletRequest request) {
		Admin trueAdmin = new Admin();
		if (admin.getId() != null) {
			trueAdmin = adminService.getAdmin(admin);
			request.setAttribute("adminInfo", trueAdmin);
			request.setAttribute("operation", "update");
		}
		return "admin/edit";
	}
	
	@RequestMapping("/addOrUpdateAdminTrue.do")
	public String addOrUpdateAdminTrue(Admin admin, String operation, HttpServletRequest request) {
		int result = adminService.addOrUpdateAdmin(admin, operation);
		if (result < 0) {
			request.setAttribute("notify", ResultInfo.ADMIN_NOT_EXIST);
		}else {
			request.setAttribute("notify", ResultInfo.EDIT_SUCCESS);
		}
		return "admin/index";
	}
	
	@RequestMapping("/deleteAdmin.do")
	public String deleteAdmin(String id, HttpServletRequest request) {
		if (Constants.SUPER_ADMIN.equals(id)) {
			request.setAttribute("notify", "超管账号无法删除");
			return "admin/index";
		}
		adminService.deleteAdmin(id);
		request.setAttribute("notify", "删除成功");
		return "admin/index";
	}
	
	@RequestMapping("/resetPassword.do")
	public String resetPassword() {
		return "admin/reset_password";
	}
	
	@RequestMapping("/resetPasswordTrue.do")
	public String resetPasswordTrue(String originalPassword, String newPassword, 
			String repeatPassword, HttpServletRequest request) {
		
		if (!repeatPassword.equals(newPassword)) {
			request.setAttribute("notify", "两次密码输入不一致");
			return "admin/reset_password";
		}
		if (StringUtils.isblank(originalPassword) 
				|| StringUtils.isblank(newPassword) || StringUtils.isblank(repeatPassword)) {
			request.setAttribute("notify", "密码错误");
			return "admin/reset_password";
		}
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		String id = admin.getId();
		Admin resultAdmin = adminService.getAdmin(admin);
		if (resultAdmin == null) {
			request.setAttribute("notify", "密码错误");
			return "admin/reset_password";
		}
		if (!resultAdmin.getPassword().equals(Utils.getMD5Str(originalPassword))) {
			request.setAttribute("notify", "密码错误");
			return "admin/reset_password";
		}
		int result = adminService.resetPassword(id, newPassword);
		if (result < 0) {
			request.setAttribute("notify", "用户密码更新失败");
			return "admin/reset_password";
		}
		request.setAttribute("notify", "用户密码更新成功");
		return "admin/index";
	}
	
	private void reGenerateSessionId(HttpServletRequest request){  
	    HttpSession session = request.getSession();
	    //将session信息存储到临时map
	    Map<String,Object> tempMap = new HashMap<String,Object>();
	    Enumeration<String> sessionNames = session.getAttributeNames();
	    while(sessionNames.hasMoreElements()){
	        String sessionName = sessionNames.nextElement();
	        tempMap.put(sessionName, session.getAttribute(sessionName));
	    }
	      
	    //删除原有session 
	    session.invalidate();  
	      
	    //将临时map中信息存储至新session 
	    session = request.getSession();
	    //设置session超时时间
	    session.setMaxInactiveInterval(Constants.SESSION_INVALID_TIME);
	    for(Map.Entry<String, Object> entry : tempMap.entrySet()){  
	        session.setAttribute(entry.getKey(), entry.getValue());  
	    }  
	}
}
