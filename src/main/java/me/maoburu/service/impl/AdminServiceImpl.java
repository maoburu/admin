package me.maoburu.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import me.maoburu.constant.Constants;
import me.maoburu.dao.AdminDao;
import me.maoburu.pojo.Admin;
import me.maoburu.pojo.Page;
import me.maoburu.service.AdminService;
import me.maoburu.util.Utils;

@Service
public class AdminServiceImpl implements AdminService {

	@Resource
	private AdminDao adminDao;
	
	@Override
	public String index() {
		String admin = adminDao.index();
		return admin;
	}

	@Override
	public Admin getAdminByName(String name) {
		Admin admin = adminDao.getAdminByName(name);
		return admin;
	}


	@Override
	@Transactional
	public int updateCheckTime(String id, int checkTime) {
		return adminDao.updateCheckTime(id, checkTime);
	}

	@Override
	public Page listAdmin(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Admin> adminList = adminDao.listAdmin();
		long total = new PageInfo(adminList).getTotal();
		Page page = new Page();
		page.setRows(adminList);
		page.setTotal(total);
		return page;
	}

	@Override
	@Transactional
	public int updateAdminStatus(Admin admin) {
		return adminDao.updateAdminStatus(admin);
	}

	@Override
	public Admin getAdmin(Admin admin) {
		Admin trueAdmin = adminDao.getAdmin(admin);
		return trueAdmin;
	}

	@Override
	@Transactional
	public int addOrUpdateAdmin(Admin admin, String operation) {
		if ("update".equals(operation)) {
			return adminDao.updateAdmin(admin);
		} else {
			if (adminDao.getAdminByName(admin.getName()) != null) {
				return -1;
			} else {
				admin.setId(UUID.randomUUID().toString().replace("-", ""));
				admin.setPassword(Utils.getMD5Str(Constants.DEFAULT_PASSWORD));
				
				Date date;
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(Constants.DEFAULT_REGIST_DATE);
					admin.setUpdateTime(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return adminDao.addAdmin(admin);
			}
		}
	}

	@Override
	public int deleteAdmin(String id) {
		return adminDao.deleteAdmin(id);
	}

	@Override
	public int resetPassword(String id, String newPassword) {
		newPassword = Utils.getMD5Str(newPassword);
		return adminDao.updatePassword(id, newPassword);
	}
	
	
}
