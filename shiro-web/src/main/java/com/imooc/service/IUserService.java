package com.imooc.service;

import java.util.Set;

import com.imooc.domain.User;

public interface IUserService {

	User getPasswordByUserName(String userName);

	Set<String> getRolesByUserName(String userName);

	Set<String> getPermissionsByUserName(String userName);

}
