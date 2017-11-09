package com.ovu.sso.server.user.dao;

import java.util.List;

import com.ovu.sso.server.user.vo.UserVO;

/**
 * 登录用户信息持久化服务，相当于DAO对象的模拟
 * @author preach
 *
 */
public interface IUserDao {

	/**
	 * 更新当前登录用户的lt标识
	 * @param loginName
	 * @param lt
	 * @throws Exception 
	 */
	public void updateLoginToken(String loginName, String loginToken) throws Exception ;

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
	

}
