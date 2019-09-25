package com.atguigu.crowd.funding.util;

public class CrowdFundingConstant {
	public static final String ATTR_NAME_MESSAGE = "MESSAGE";
	public static final String ATTR_NAME_LOGIN_ADMIN = "LOGIN-ADMIN";
	public static final String ATTR_NAME_PAGE_INFO = "PAGE-INFO";

	public static final String MESSAGE_LOGIN_FAILED = "登录账号或密码不正确！请核对后再登录！";
	public static final String MESSAGE_CODE_INVALID = "明文不是有效字符串，请核对后再操作！";
	public static final String MESSAGE_LOGIN_ACCT_ALREADY_IN_USE = "登录账号已存在，请更换其他账号注册！";
	public static final String MESSAGE_DATA_MISSED = "您操作的数据在提交前可能已在其他终端删除，建议刷新页面查看数据是否存在。";
	public static final String MESSAGE_DATA_EXIST = "已存在相同的数据，请更换其他数据进行操作！";
	public static final String MESSAGE_NO_UPDATE = "未能更改任何数据，建议查看是否未更改任何消息进行了保存操作，或者刷新页面查看数据是否还在。";
	public static final String MISSING_SERVLET_REQUESTPARAMETER="您有必要参数未填写，不允许进行此操作！";
	public static final String REJECT_DANGER_OPERATION="后台检测到您进行一项极为危险的操作，且操作不可逆，拒绝执行此操作！";
	public static final String UNKNOW_ERROR="未知错误，请联系开发人员";
}
