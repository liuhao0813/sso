package com.ovu.sso.server.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ovu.sso.client.service.AuthenticationRpcService;
import com.ovu.sso.common.model.RpcUser;
import com.ovu.sso.server.common.TokenManager;
import com.ovu.sso.server.model.LoginUser;

@Service("authenticationRpcService")
public class AuthenticationRpcServiceImpl implements AuthenticationRpcService {

	@Resource
	private TokenManager tokenManager;

	@Override
	public boolean validate(String token) {
		return tokenManager.validate(token) != null;
	}
	
	@Override
	public RpcUser findAuthInfo(String token) {
		LoginUser user = tokenManager.validate(token);
		if (user != null) {
			return new RpcUser(user.getAccount());
		}
		return null;
	}
	
}
