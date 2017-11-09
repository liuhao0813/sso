package com.ovu.sso.server.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ovu.sso.server.user.dao.IUserDao;
import com.ovu.sso.server.user.service.IUserService;
import com.ovu.sso.server.user.vo.UserVO;

@Service
public class UserServiceImpl implements IUserService{

	
	@Autowired
	private IUserDao userDao;
	
	@Override
	public UserVO findUser(String uname) {
		return userDao.findUser(uname);
	}

	@Override
	public List<UserVO> findUserList(UserVO userVO) {
		PageHelper.offsetPage(1, 2);
		return userDao.findUserList(userVO);
	}

	@Override
	public int addUser(UserVO userVO) {
		return userDao.addUser(userVO);
	}

	@Override
	public int delUser(String username) {
		return userDao.delUser(username);
	}

	@Override
	public int updateUser(UserVO userVO) {
		return userDao.updateUser(userVO);
	}

	@Override
	public void updateLoginToken(String loginName, String lt) throws Exception {
		userDao.updateLoginToken(loginName,lt);
	}
	
	

}
