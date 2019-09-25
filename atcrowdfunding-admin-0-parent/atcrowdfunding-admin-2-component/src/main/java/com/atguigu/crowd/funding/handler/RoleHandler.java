package com.atguigu.crowd.funding.handler;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crowd.funding.entity.ResultEntity;
import com.atguigu.crowd.funding.entity.Role;
import com.atguigu.crowd.funding.service.api.RoleService;
import com.atguigu.crowd.funding.util.CrowdFundingConstant;
import com.github.pagehelper.PageInfo;

@Controller
public class RoleHandler {
	@Autowired
	private RoleService roleService;

	@ResponseBody
	@RequestMapping("/role/search/by/keyword")
	public ResultEntity<PageInfo<Role>> search(
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "keyword", defaultValue = "") String keyword) {

		// 1.��ѯ�õ�PageInfo����
		PageInfo<Role> pageInfo = roleService.queryForKeywordWithPage(pageNum,
				pageSize, keyword);

		// 2.��װ������󷵻�
		return ResultEntity.successWithData(pageInfo);
	}

	@ResponseBody
	@RequestMapping("/role/save/role")
	public ResultEntity<String> saveRole(
			@RequestParam("roleName") String roleName) {

		Integer affectRows = null;
		try {
			affectRows = roleService.saveRole(new Role(null, roleName));
		} catch (DuplicateKeyException e) {
			return ResultEntity.failedWithData(
					CrowdFundingConstant.ATTR_NAME_MESSAGE,
					CrowdFundingConstant.MESSAGE_DATA_EXIST);
		}
		if (Objects.equals(affectRows, 0)) {
			return ResultEntity
					.successWithoutUpdate(CrowdFundingConstant.MESSAGE_NO_UPDATE);
		}
		// 2.��װ������󷵻�
		return ResultEntity.successWithoutData();
	}

	@ResponseBody
	@RequestMapping("/role/batch/remove")
	public ResultEntity<String> removeRole(@RequestBody List<Integer> roleIdList) {
		
		//��û��ѡ���κμ�¼���ύ�˵��������ֱ����ձ�Ϊ�����������������ɱ���Ĳ��������ύ��id�б���Ϊ0���ܾ�ִ��ɾ������
		if (roleIdList.size() == 0) {
			return ResultEntity
					.successWithoutUpdate(CrowdFundingConstant.REJECT_DANGER_OPERATION);
		}
		Integer affectRows = roleService.batchRemove(roleIdList);
		if (Objects.equals(affectRows, 0)) {
			return ResultEntity
					.successWithoutUpdate(CrowdFundingConstant.MESSAGE_NO_UPDATE);
		}
		// int a = 2/0;
		// 2.��װ������󷵻�
		return ResultEntity.successWithoutData();
	}

	@ResponseBody
	@RequestMapping("/role/update/role")
	public ResultEntity<String> updateRole(@RequestBody Role role) {
		Integer affectRows = null;
		try {
			affectRows = roleService.updateRole(role);
		} catch (DuplicateKeyException e) {
			return ResultEntity
					.successWithoutUpdate(CrowdFundingConstant.MESSAGE_DATA_EXIST);
		}
		
		if (Objects.equals(affectRows, 0) || Objects.equals(affectRows, null)) {
			return ResultEntity
					.successWithoutUpdate(CrowdFundingConstant.MESSAGE_NO_UPDATE);
		}
		// int a = 2/0;
		// 2.��װ������󷵻�
		return ResultEntity.successWithoutData();
	}

	@ResponseBody
	@RequestMapping(value = "/role/get/list/by/id/list", method = RequestMethod.POST)
	public ResultEntity<List<Role>> getRoleListByIdList(
			@RequestBody List<Integer> roleIdList) {

		List<Role> roleList = roleService.getRoleListByIdList(roleIdList);

		return ResultEntity.successWithData(roleList);
	}

}
