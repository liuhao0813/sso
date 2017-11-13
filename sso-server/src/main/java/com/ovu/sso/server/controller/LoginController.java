package com.ovu.sso.server.controller;

import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ovu.sso.client.filter.SsoFilter;
import com.ovu.sso.common.util.CookieUtils;
import com.ovu.sso.common.util.SessionUtils;
import com.ovu.sso.common.util.StringUtils;
import com.ovu.sso.server.common.TokenManager;
import com.ovu.sso.server.config.SsoConfig;
import com.ovu.sso.server.model.Credential;
import com.ovu.sso.server.model.LoginUser;


/**
 * @author admin
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	

	@Autowired
	private TokenManager tokenManager;
	
	
	@Autowired
    private SsoConfig config;

	@RequestMapping(method = RequestMethod.GET)
	public String login(String backUrl,HttpServletRequest request) {
		
		String token = CookieUtils.getCookie(request, "token");
		if (token == null) {
			return goLoginPath(backUrl, request);
		}
		else {
			LoginUser loginUser = tokenManager.validate(token);
			if (loginUser != null) {
				return "redirect:" + StringUtils.appendUrlParameter(backUrl, SsoFilter.SSO_TOKEN_NAME, token);
			}
			else {
				return goLoginPath(backUrl, request);
			}
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public String login(String backUrl,HttpServletRequest request, HttpServletResponse response,ModelMap map) throws Exception {
		
		final Map<String, String[]> params = request.getParameterMap();

        Credential credential = new Credential() {

            @Override
            public String getParameter(String name) {
                String[] tmp = params.get(name);
                return tmp != null && tmp.length > 0 ? tmp[0] : null;
            }

            @Override
            public String[] getParameterValue(String name) {
                return params.get(name);
            }
        };

        LoginUser loginUser = config.getAuthenticationHandler().authenticate(credential);

        if (loginUser == null) {
            map.put("errorMsg", credential.getError());
            return config.getLoginViewName();
        } else {
        	
        	String token = CookieUtils.getCookie(request, "token");
			if (StringUtils.isBlank(token) || tokenManager.validate(token) == null) {// 没有登录的情况
				token = createToken(loginUser);
				addTokenInCookie(token, request, response);
			}else {
				//token存在且认证通过，表示有多用户登陆，需要进行更新，以后面登陆的用户为准
				updateToken(loginUser,token,request);
			}
			// 跳转到原请求
			backUrl = URLDecoder.decode(backUrl, "utf-8");
			return "redirect:" + StringUtils.appendUrlParameter(backUrl, SsoFilter.SSO_TOKEN_NAME, token);
        }
	}
	
	private void updateToken(LoginUser loginUser, String token,HttpServletRequest request) {
		tokenManager.remove(token);
		// 缓存中添加token对应User
		tokenManager.addToken(token, loginUser);
		SessionUtils.invalidate(request);
		
	}

	private String goLoginPath(String backUrl, HttpServletRequest request) {
		request.setAttribute("backUrl", backUrl);
		return config.getLoginViewName();
	}


	private String createToken(LoginUser loginUser) {
		// 生成token
		String token = StringUtils.uniqueKey();

		// 缓存中添加token对应User
		tokenManager.addToken(token, loginUser);
		return token;
	}
	
	private void addTokenInCookie(String token, HttpServletRequest request, HttpServletResponse response) {
		// Cookie添加token
		Cookie cookie = new Cookie("token", token);
		cookie.setPath("/");
		if ("https".equals(request.getScheme())) {
			cookie.setSecure(true);
		}
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
	}
}