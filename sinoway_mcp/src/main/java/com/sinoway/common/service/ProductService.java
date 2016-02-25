package com.sinoway.common.service;

/**
 * 产品维护上层接口 
 * @author Liuzhen
 * @version 1.0
 * 2015-12-24
 */
public interface ProductService {

	/**
	 * 添加产品
	 * @param prdInfo 产品信息
	 * @return 返回响应信息
	 * @throws Exception
	 */
	public Object addprdInfo(Object prdInfo ) throws Exception;
	
	/**
	 * 修改产品
	 * @param prdInfo 产品信息
	 * @return 返回响应信息
	 * @throws Exception
	 */
	public Object editprdInfo(Object prdInfo ) throws Exception;
	
	/**
	 * 删除产品
	 * @param prdInfo 产品信息
	 * @return 返回响应信息
	 * @throws Exception
	 */
	public Object delprdInfo(Object prdInfo ) throws Exception;
}
