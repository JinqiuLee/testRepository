package com.imooc.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.domain.User;

@Controller
public class UserController {
	@RequestMapping(value = "/subLogin.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String subLogin(User user) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(
				user.getUsername(), user.getPassword());
		try {
			subject.login(token);
		} catch (UnknownAccountException ae) {
			return "账户不存在";
		}catch(IncorrectCredentialsException ice){
			return "密码错误";
		}
		if(subject.hasRole("admin")){
		return "有";	
		}
		return "无";
	}
	@RequiresRoles("admin")
	@RequestMapping(value = "/testRole.do", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String testRole() {
		return "admin用户可以访问";
	}
	@RequiresRoles("user")
	@RequestMapping(value = "/testRole1.do", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String testRole1() {
		return "user可以访问";
	}
}
