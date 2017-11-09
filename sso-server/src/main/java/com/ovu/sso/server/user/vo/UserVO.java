package com.ovu.sso.server.user.vo;

import java.io.Serializable;

/**
 * User对象sso_user_t
 * @author admin
 *
 */
public class UserVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String username;
	private String disable;
	private Integer age;
	private String email;
	private String expried;
	private String password;
	private String address;
	private String loginToken;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDisable() {
		return disable;
	}
	public void setDisable(String disable) {
		this.disable = disable;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getExpried() {
		return expried;
	}
	public void setExpried(String expried) {
		this.expried = expried;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLoginToken() {
		return loginToken;
	}
	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}
	
	
}
