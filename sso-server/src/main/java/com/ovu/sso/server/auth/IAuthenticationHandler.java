package com.ovu.sso.server.auth;

import com.ovu.sso.server.common.LoginUser;
import com.ovu.sso.server.model.Credential;

/**
 * 鉴权处理器
 * 
 * @author Administrator
 *
 */
public interface IAuthenticationHandler {

	/**
	 * 鉴权
	 * 
	 * @param params
	 *            页面传递过来的参数
	 * @param sessionAttr
	 *            特定session属性值，这个值是在跳到login页面前，loginPreHandler通过setSessionVal()
	 *            方法写入的
	 * @param errors
	 *            授权失败时，将失败信息写入此对象
	 * @return 授权成功返回Credentail, 否则返回null
	 */
	public LoginUser authenticate(Credential credential) throws Exception;

}
