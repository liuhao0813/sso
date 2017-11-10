package com.ovu.sso.server.auth.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ovu.sso.common.util.MD5;
import com.ovu.sso.server.auth.IAuthenticationHandler;
import com.ovu.sso.server.model.Credential;
import com.ovu.sso.server.model.LoginUser;
import com.ovu.sso.server.user.service.IUserService;
import com.ovu.sso.server.user.vo.UserVO;

/**
 * 示例性的鉴权处理器，当用户名和密码都为admin时授权通过，返回的也是一个示例性Credential实例
 * 
 * @author Administrator
 *
 */
public class UsernameAndPasswordAuthenticationHandler implements IAuthenticationHandler {

	@Autowired
	private IUserService userServiceImpl;
	
	@Override
	public LoginUser authenticate(Credential credential) throws Exception {
		UserVO userVO = userServiceImpl.findUser(credential.getParameter("name"));
		
		if(userVO==null) {
			credential.setError("账号不存在，请重新输入!");
			return null;
		}else {
			String password = MD5.encode(credential.getParameter("passwd"));
			if(userVO.getPassword().equals(password)) {
				LoginUser user = new LoginUser(userVO.getId(),userVO.getUsername());
				return user;
			}else {
				credential.setError("密码错误，请重新输入!");
				return null;
			}
		}
	}
	
}
