package com.sinoway.common.service;
/**
 * 用户维护上层接口 
 * @author Liuzhen
 * @version 1.0
 * 2015-12-24
 */
public interface UserService {

	/**
	 * 添加用户
	 * @param usrInfo 用户信息
	 * @return 返回响应信息
	 * @throws Exception
	 */
	public Object addusrInfo(Object usrInfo ) throws Exception;
	
	/**
	 * 修改用户
	 * @param usrInfo 用户信息
	 * @return 返回响应信息
	 * @throws Exception
	 */
	public Object editusrInfo(Object usrInfo ) throws Exception;
	
	/**
	 * 删除用户
	 * @param usrInfo 用户信息
	 * @return 返回响应信息
	 * @throws Exception
	 */
	public Object delusrInfo(Object usrInfo ) throws Exception;
}
