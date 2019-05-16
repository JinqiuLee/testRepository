package com.imooc.shiro.realm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.imooc.domain.User;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.imooc.service.IUserService;

public class CustomRealm extends AuthorizingRealm {
	
	@Qualifier("userService")
	@Autowired
	private IUserService userService;
	
	
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	//Map<String, String> userMap = new HashMap<>();
	Map<String, String> roleMap = new HashMap<>();
	Map<String, String> permissionMap = new HashMap<>();
	Set<String> roleSet = new HashSet<>();
/*	{
		userMap.put("31494327", "5306e9e756c6c48109af58383b712cf6");
		super.setName("custtomRealm");
	}*/

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();
		Set<String> roles = userService.getRolesByUserName(userName);
		Set<String> permissions = userService.getPermissionsByUserName(userName);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(roles);
		info.setStringPermissions(permissions);
		return info;
	}

	private Set<String> getPermissionsByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	private Set<String> getRolesByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// 从主体传过来的认证信息中，获得用户名
		String userName = (String) token.getPrincipal();
		// 通过用户名到数据库中获取凭证
		User user = userService.getPasswordByUserName(userName);
		if (user== null) {
			//System.out.println("账号不存�?");
			return null;
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				userName, user.getPassword(), "customRealm");
		info.setCredentialsSalt(ByteSource.Util.bytes(userName));
		return info;
	}

	/*private String getPasswordByUsername(String userName) {
		return null;
	}*/

}
