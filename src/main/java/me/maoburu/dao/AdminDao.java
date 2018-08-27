package me.maoburu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import me.maoburu.pojo.Admin;

@Component
public interface AdminDao {
	
	String index();

	Admin getAdminByName(String name);

	List<Admin> listAdmin();

	int updateAdminStatus(Admin admin);

	Admin getAdmin(Admin admin);

	int updateAdmin(Admin admin);

	int addAdmin(Admin admin);

	int deleteAdmin(String id);

	int updateCheckTime(@Param("id")String id, 
			@Param("checkTime")int checkTime);

	int updatePassword(@Param("id")String id, 
			@Param("password")String password);
}
