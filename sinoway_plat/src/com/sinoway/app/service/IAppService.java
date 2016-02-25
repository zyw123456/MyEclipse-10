package com.sinoway.app.service;

import java.util.List;


public interface IAppService {

	/**
	 * 根据应用编码查询该应用下所有交易码信息
	 * @param appcod
	 * @return
	 */
	public List<?> getPrdTrns(String appcod);
	
	/**
	 * 根据应用编码和用户编码查询该应用下当前用户的所有交易码信息
	 * @param appcod
	 * @param peoplecode
	 * @return
	 */
	public List<?> getPrdTrns(String appcod,String peoplecode);
	public List findTrncodByAppcod(String appcode);
}
