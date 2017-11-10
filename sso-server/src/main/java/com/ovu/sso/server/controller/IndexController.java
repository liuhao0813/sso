package com.ovu.sso.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.ovu.sso.common.SessionUser;
import com.ovu.sso.common.util.SessionUtils;

@Controller
public class IndexController {
	
	@GetMapping("/admin")
	public String Index(HttpServletRequest request,ModelMap model) {
		SessionUser sessionUser = SessionUtils.getSessionUser(request);
		model.addAttribute("userName", sessionUser.getAccount());
		
		return "index";
	}
}
