package com.ovu.sso.server.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ovu.sso.common.util.CookieUtils;
import com.ovu.sso.common.util.SessionUtils;
import com.ovu.sso.common.util.StringUtils;
import com.ovu.sso.server.common.TokenManager;

/**
 * @author admin
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	@Resource
	private TokenManager tokenManager;

	@RequestMapping(method = RequestMethod.GET)
	public String logout(String backUrl, HttpServletRequest request) {
		String token = CookieUtils.getCookie(request, "token");
		if (StringUtils.isNotBlank(token)) {
			tokenManager.remove(token);
		}
		SessionUtils.invalidate(request);
		return "redirect:" + (StringUtils.isBlank(backUrl) ? "/admin" : backUrl);
	}
}