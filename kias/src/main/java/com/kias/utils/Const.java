package com.kias.utils;

import org.springframework.context.ApplicationContext;

public class Const {
	//用户存放的session的名称
	public static final String SESSION_USER="sessionUser";
	//真实姓名
	public static final String SESSION_REALNAME="realName";
	//存放用登录的科室代码
	public static final String SESSION_CURRENT_DEPART="curDepart";
	
	
	
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER_RIGHTS = "sessionUserRights";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)).*";	//不对匹配该值的访问路径拦截（正则）
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化
}