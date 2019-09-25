package com.atguigu.crowd.funding.service.api;

import java.util.List;

import com.atguigu.crowd.funding.entity.Role;
import com.github.pagehelper.PageInfo;

public interface RoleService {

	PageInfo<Role> queryForKeywordWithPage(Integer pageNum, Integer pageSize,
			String keyword);

	Integer saveRole(Role role);

	Integer batchRemove(List<Integer> roleIdList);

	Integer updateRole(Role role);

	List<Role> getRoleListByIdList(List<Integer> roleIdList);

}
