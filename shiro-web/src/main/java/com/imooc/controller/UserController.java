package com.imooc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

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
		} catch (IncorrectCredentialsException ice) {
			return "密码错误";
		}
		if (subject.hasRole("admin")) {
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

	@RequestMapping(value = "/getMusicList.do", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String getMusicList(String key_word) throws IOException {
		System.out.println(key_word);
		URL realurl = new URL(
				"https://c.y.qq.com/soso/fcgi-bin/client_search_cp?ct=24&qqmusic_ver=1298&new_json=1&remoteplace=txt.yqq.center&searchid=47124617419832380&t=0&aggr=1&cr=1&catZhida=1&lossless=0&flag_qc=0&p=1&n=10&w="
						+ key_word
						+ "&g_tk=5381&loginUin=0&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0");
		
		
		return "redirect:"+realurl;
	}
}
