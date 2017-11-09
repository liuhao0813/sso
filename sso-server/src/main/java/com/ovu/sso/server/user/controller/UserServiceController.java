package com.ovu.sso.server.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ovu.sso.server.user.service.IUserService;
import com.ovu.sso.server.user.vo.UserVO;


/**
 * 对外提供用户操作接口
 * @author admin
 *
 */
@RestController
@RequestMapping("user")
public class UserServiceController {

	@Autowired
	private IUserService userService;
	
	/**
	 * 添加用户对象
	 * @param userVO
	 * @return
	 */
	@PostMapping("/add")
	public int createUser(UserVO userVO) {
		return userService.addUser(userVO);
	}
	
	/**
	 * 根据条件返回用户列表信息，进行分页
	 * @param userVO
	 * @return
	 */
	@GetMapping("/list/user")
	public List<UserVO> getUserList(@ModelAttribute UserVO userVO) {
		return userService.findUserList(userVO);
	}
	
}
