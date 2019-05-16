package com.imooc.service.impl;


import java.util.Set;

import com.imooc.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.imooc.domain.User;
import com.imooc.service.IUserService;

@Service("userService")
public class UserService implements IUserService {
	@Qualifier("userDao")
	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getPasswordByUserName(String userName) {
		User passwordByUserName = userDao.getPasswordByUserName(userName);
		return passwordByUserName;
	}

	@Override
	public Set<String> getRolesByUserName(String userName) {
		Set<String> roles=userDao.getRolesByUserName(userName);
		return roles;
	}

	@Override
	public Set<String> getPermissionsByUserName(String userName) {
		Set<String> permissions=userDao.getPermissionsByUserName(userName);
		return permissions;
	}
}
