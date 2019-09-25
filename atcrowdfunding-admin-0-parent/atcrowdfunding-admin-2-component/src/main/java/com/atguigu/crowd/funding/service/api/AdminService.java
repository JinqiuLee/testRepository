package com.atguigu.crowd.funding.service.api;

import java.util.List;

import com.atguigu.crowd.funding.entity.Admin;
import com.atguigu.crowd.funding.entity.Role;
import com.github.pagehelper.PageInfo;

public interface AdminService {

	List<Admin> getAll();
	
	void updateAdmin();

	Integer updateAdmin(Admin admin);

	String getPasswordByUsername(String userName);

	PageInfo<Admin> queryForKeywordSearch(Integer pageNum, Integer pageSize,
			String keyword);

	void batchRemove(List<Integer> adminIdList);

	void saveAdmin(Admin admin);

	Admin getAdminById(Integer adminId);
}
