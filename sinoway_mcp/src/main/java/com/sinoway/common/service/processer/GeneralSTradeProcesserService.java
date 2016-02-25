package com.sinoway.common.service.processer;

import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.exception.STradeProcessException;

/**
 * 通用子交易处理接口
 * @author Liuzhen
 * @version 1.0
 *  2015-11-25
 */
public interface GeneralSTradeProcesserService {
	
	/**
	 * 子交易业务发起
	 * @param entity
	 * @return
	 * @throws STradeProcessException
	 */
	public void busLaunch(GeneralBusEntity entity) throws STradeProcessException;
	
	/**
	 * 接收子交易响应结果
	 * @param entity
	 * @throws STradeProcessException
	 */
	public void resRecive(GeneralBusEntity entity) throws STradeProcessException;
	
	/**
	 * 获取子交易响应结果
	 * @param entity
	 * @throws STradeProcessException
	 */
	public void getRes(GeneralBusEntity entity) throws STradeProcessException;

}
