package com.atguigu.crowd.funding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crowd.funding.entity.Admin;
import com.atguigu.crowd.funding.entity.AdminExample;
import com.atguigu.crowd.funding.entity.AdminExample.Criteria;
import com.atguigu.crowd.funding.entity.Role;
import com.atguigu.crowd.funding.mapper.AdminMapper;
import com.atguigu.crowd.funding.service.api.AdminService;
import com.atguigu.crowd.funding.util.CrowdFundingConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public List<Admin> getAll() {
		return adminMapper.selectByExample(new AdminExample());
	}

	@Override
	public void updateAdmin() {
		adminMapper.updateByPrimaryKeySelective(new Admin(1, "harry333",
				"123123", "哈利", "harry@qq.com", null));
		if (1 == 1) {
			throw new RuntimeException("运行时异常");

		}
		adminMapper.updateByPrimaryKeySelective(new Admin(2, "potter333",
				"123123", "波特", "potter@qq.com", null));
	}

	@Override
	public String getPasswordByUsername(String userName) {
		String selectLoginAcct = adminMapper.selectLoginAcct(userName);
		return selectLoginAcct;
	}

	@Override
	public PageInfo<Admin> queryForKeywordSearch(Integer pageNum,
			Integer pageSize, String keyword) {
		// 1.调用PageHelper的工具方法，开启分页功能
		PageHelper.startPage(pageNum, pageSize);

		// 2.执行分页查询
		List<Admin> list = adminMapper.selectAdminListByKeyword(keyword);

		for (Admin admin : list) {
			System.out.println(admin);
		}
		
		// 3.将list封装到PageInfo对象中
		return new PageInfo<>(list);

	}

	// Service方法
	@Override
	public void batchRemove(List<Integer> adminIdList) {
		adminMapper.batchRemove(adminIdList);
	}

	@Override
	public void saveAdmin(Admin admin) {
		adminMapper.insert(admin);
	}

	@Override
	public Admin getAdminById(Integer adminId) {
		// TODO Auto-generated method stub
		return adminMapper.selectByPrimaryKey(adminId);
	}

	@Override
	public Integer updateAdmin(Admin admin) {
		Integer affectedRows= adminMapper.updateByPrimaryKey(admin);
		if(affectedRows==null||Integer.valueOf(0).equals(affectedRows)){
			throw new RuntimeException(
					CrowdFundingConstant.MESSAGE_DATA_MISSED);
		}
		return affectedRows;
	}
}
