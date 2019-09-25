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
				"123123", "����", "harry@qq.com", null));
		if (1 == 1) {
			throw new RuntimeException("����ʱ�쳣");

		}
		adminMapper.updateByPrimaryKeySelective(new Admin(2, "potter333",
				"123123", "����", "potter@qq.com", null));
	}

	@Override
	public String getPasswordByUsername(String userName) {
		String selectLoginAcct = adminMapper.selectLoginAcct(userName);
		return selectLoginAcct;
	}

	@Override
	public PageInfo<Admin> queryForKeywordSearch(Integer pageNum,
			Integer pageSize, String keyword) {
		// 1.����PageHelper�Ĺ��߷�����������ҳ����
		PageHelper.startPage(pageNum, pageSize);

		// 2.ִ�з�ҳ��ѯ
		List<Admin> list = adminMapper.selectAdminListByKeyword(keyword);

		for (Admin admin : list) {
			System.out.println(admin);
		}
		
		// 3.��list��װ��PageInfo������
		return new PageInfo<>(list);

	}

	// Service����
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
