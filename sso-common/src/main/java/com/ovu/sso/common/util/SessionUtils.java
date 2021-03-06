package com.ovu.sso.common.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.WebUtils;

import com.ovu.sso.common.SessionUser;

/**
 * 当前已登录用户Session
 * 
 * @author Joe
 */
public class SessionUtils {
	/**
	 * 用户信息
	 */
	public static final String SESSION_USER = "_sessionUser";

	/**
	 * 用户权限
	 */
	public static final String SESSION_USER_PERMISSION = "_sessionUserPermission";

	public static SessionUser getSessionUser(HttpServletRequest request) {
		return (SessionUser) WebUtils.getSessionAttribute(request, SESSION_USER);
	}

	public static void setSessionUser(HttpServletRequest request, SessionUser sessionUser) {
		WebUtils.setSessionAttribute(request, SESSION_USER, sessionUser);
	}

	public static void invalidate(HttpServletRequest request){
		setSessionUser(request, null);
	}
}