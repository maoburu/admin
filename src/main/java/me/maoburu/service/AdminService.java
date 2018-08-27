package me.maoburu.service;

import me.maoburu.pojo.Admin;
import me.maoburu.pojo.Page;

public interface AdminService {
	
	/**
	 * 首页
	 * @return
	 */
	String index();

	/**
	 * 通过用户名获得用户
	 * @param name
	 * @return
	 */
	Admin getAdminByName(String name);
	
	/**
	 * 更新密码错误次数
	 * @param id
	 */
	int updateCheckTime(String id, int checkTime);
	
	/**
	 * 查询所有用户（分页）
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Page listAdmin(int pageNum, int pageSize);
	
	/**
	 *̬ 更新用户状态
	 * @param id
	 * @param status
	 * @return
	 */
	int updateAdminStatus(Admin admin);
	
	/**
	 * 获得用户
	 * @param admin
	 * @return
	 */
	Admin getAdmin(Admin admin);
	
	/**
	 * 添加/更新用户
	 * @param admin
	 * @param operation
	 */
	int addOrUpdateAdmin(Admin admin, String operation);
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	int deleteAdmin(String id);

	int resetPassword(String id, String newPassword);
}
