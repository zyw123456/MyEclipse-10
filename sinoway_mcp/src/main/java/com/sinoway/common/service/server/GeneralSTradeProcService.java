package com.sinoway.common.service.server;

import com.sinoway.base.entity.BCfgdefFnttrnaddr;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.exception.STradeProcessException;

/**
 * 通用子交易处理服务接口
 * @author Liuzhen
 * @version 1.0
 * 2015-11-25
 */
public interface GeneralSTradeProcService {

	/**
	 * 子交易业务发起
	 * @param entity
	 * @return GeneralBusEntity
	 * @throws STradeProcessException
	 */
	public GeneralBusEntity busLaunch(GeneralBusEntity entity) throws STradeProcessException;
	
	/**
	 * 接收子交易响应结果
	 * @param entity
	 * @throws STradeProcessException
	 */
	public GeneralBusEntity resRecive(GeneralBusEntity entity) throws STradeProcessException;
	
	/**
	 * 获取子交易响应结果
	 * @param entity
	 * @throws STradeProcessException
	 */
	public GeneralBusEntity getRes(GeneralBusEntity entity) throws STradeProcessException;
	
	/**
	 * 初始化
	 * @param entity
	 */
	public void initCfg(BCfgdefFnttrnaddr entity);

}
