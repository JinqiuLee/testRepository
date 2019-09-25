package com.atguigu.crowd.funding.realm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

import com.atguigu.crowd.funding.service.api.AdminService;

public class CustomRealm extends AuthorizingRealm {
	Map<String, String> roleMap = new HashMap<>();
	Map<String, String> permissionMap = new HashMap<>();
	Set<String> roleSet = new HashSet<>();
	

	@Autowired
	private AdminService adminService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();
		Set<String> roles = getRolesByUserName(userName);
		Set<String> permissions = getPermissionsByUserName(userName);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(roles);
		return null;
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
		String password = adminService.getPasswordByUsername(userName);
		if (password == null) {
			System.out.println("账号不存在");
			return null;
		}else{
			System.out.println("真实密码"+password+"---"+"验证密码");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				userName, password, "customRealm");
		//info.setCredentialsSalt(ByteSource.Util.bytes("Owen"));
		return info;
	}

	

}
