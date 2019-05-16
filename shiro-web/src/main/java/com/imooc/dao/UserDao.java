package com.imooc.dao;

import java.util.Set;

import org.springframework.stereotype.Repository;

import com.imooc.domain.User;

@Repository("userDao")
public interface UserDao {
	public User getPasswordByUserName(String userName);

	public Set<String> getRolesByUserName(String userName);

	public Set<String> getPermissionsByUserName(String userName);
}
