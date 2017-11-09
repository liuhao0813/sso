package com.ovu.sso.server.user.service;

import java.util.List;

import com.ovu.sso.server.user.vo.UserVO;

public interface IUserService {
	
	/**
	 * 按登录账号查询用户信息
	 * @param parameter
	 * @return
	 */
	public UserVO findUser(String uname);
	
	
	public List<UserVO> findUserList(UserVO userVO);
	
	public int addUser(UserVO userVO);
	
	public int delUser(String username);
	
	public int updateUser(UserVO userVO);


	public void updateLoginToken(String loginName, String lt) throws Exception;
	

}
