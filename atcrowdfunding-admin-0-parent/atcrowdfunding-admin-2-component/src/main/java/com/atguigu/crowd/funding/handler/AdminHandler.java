package com.atguigu.crowd.funding.handler;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crowd.funding.entity.Admin;
import com.atguigu.crowd.funding.service.api.AdminService;
import com.atguigu.crowd.funding.util.CrowdFundingConstant;
import com.github.pagehelper.PageInfo;

@Controller
public class AdminHandler {
	@Autowired
	private AdminService adminService;

	@RequestMapping("/admin/get/all")
	public String getAll(Model model) {

		List<Admin> list = adminService.getAll();

		model.addAttribute("list", list);

		return "admin-target";
	}

	@RequestMapping("/admin/logout")
	public String logout(HttpSession session) {

		// session.invalidate();

		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "redirect:/index.html";
	}

	@RequestMapping("/admin/do/login")
	public String doLogin(Admin admin, Model model, HttpSession session) {

		// 调用adminService的login方法执行登录业务逻辑，返回查询到的Admin对象
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(
				admin.getLoginAcct(), admin.getUserPswd());
		try {
			subject.login(token);
		} catch (UnknownAccountException ae) {
			model.addAttribute("MESSAGE", "用户名或密码错误！");
			return "admin-login";
		} catch (IncorrectCredentialsException ice) {
			model.addAttribute("MESSAGE", "用户名或密码错误！");
			return "admin-login";
		}

		session.setAttribute("LOGIN-ADMIN", admin);

		return "redirect:/admin/to/main/page.html";
	}

	@RequestMapping("/admin/query/for/search")
	public String queryForSearch(

			// 如果页面上没有提供对应的请求参数，那么可以使用defaultValue指定默认值
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "keyword", defaultValue = "") String keyword,
			Model model) {

		PageInfo<Admin> pageInfo = adminService.queryForKeywordSearch(pageNum,
				pageSize, keyword);

		model.addAttribute(CrowdFundingConstant.ATTR_NAME_PAGE_INFO, pageInfo);

		return "admin-page";
	}

	// handler方法
	// 将当前handler方法的返回值作为响应体返回，不经过视图解析器
	@ResponseBody
	@RequestMapping("/admin/batch/remove")
	public String batchRemove(@RequestBody List<Integer> adminIdList) {
		adminService.batchRemove(adminIdList);
		return null;
	}

	@RequestMapping("admin/save")
	public String saveAdmin(Admin admin, Model model) {

		try {
			adminService.saveAdmin(admin);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				model.addAttribute("MESSAGE",
						CrowdFundingConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
				return "admin-add";
			}
		}
		return "redirect:/admin/query/for/search.html";
	}

	@RequestMapping("/admin/to/edit/page")
	public String toEditPage(@RequestParam("adminId") Integer adminId,
			Model model) {

		Admin admin = adminService.getAdminById(adminId);

		model.addAttribute("admin", admin);

		return "admin-edit";
	}

	@RequestMapping("/admin/update")
	public String updateAdmin(Admin admin, @RequestParam Integer pageNum,
			Model model) {

		try {
			adminService.updateAdmin(admin);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				throw new RuntimeException(
						CrowdFundingConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
			}
		}
		return "redirect:/admin/query/for/search.html?pageNum=" + pageNum;
	}

}
