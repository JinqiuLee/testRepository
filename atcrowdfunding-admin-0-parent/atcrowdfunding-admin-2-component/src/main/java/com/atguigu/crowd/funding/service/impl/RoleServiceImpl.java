package com.atguigu.crowd.funding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crowd.funding.entity.Role;
import com.atguigu.crowd.funding.entity.RoleExample;
import com.atguigu.crowd.funding.mapper.RoleMapper;
import com.atguigu.crowd.funding.service.api.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public PageInfo<Role> queryForKeywordWithPage(Integer pageNum,
			Integer pageSize, String keyword) {
		PageHelper.startPage(pageNum, pageSize);
		List<Role> list=roleMapper.selectForKeywordSearch(keyword);
		return new PageInfo<>(list);
	}

	@Override
	public Integer saveRole(Role role) {
		Integer affectRows=roleMapper.insert(role);
		return affectRows;
	}

	@Override
	public Integer batchRemove(List<Integer> roleIdList) {
		Integer affectRows=roleMapper.batchRemove(roleIdList);
		return affectRows;
	}

	@Override
	public Integer updateRole(Role role) {
		Integer affectRows=roleMapper.updateByPrimaryKey(role);
		return affectRows;
	}

	@Override
	public List<Role> getRoleListByIdList(List<Integer> roleIdList) {
		// ����ʵ����Role��Ӧ��Example����
		RoleExample roleExample = new RoleExample();
		
		// ��Example�����з�װ��ѯ����
		roleExample.createCriteria().andIdIn(roleIdList);
		
		// ִ�в�ѯ
		return roleMapper.selectByExample(roleExample);

	}
}
