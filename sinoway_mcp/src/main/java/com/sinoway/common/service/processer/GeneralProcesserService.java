package com.sinoway.common.service.processer;

import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.exception.TradeProcException;

/**
 * 通用业务处理接口
 * @author Liuzhen
 * @version 1.0
 * 2015-11-19
 */
public interface GeneralProcesserService {

	/** 对方--我方（原交易  产品）
	 * 业务逻辑处理
	 * @param entity
	 * @return
	 * @throws TradeProcException
	 */
	public GeneralBusEntity excuteToCore(GeneralBusEntity entity) throws TradeProcException;
	
	/** 我方--对方（原交易  产品）
	 * 业务逻辑处理
	 * @param entity
	 * @return
	 * @throws TradeProcException
	 */
	public GeneralBusEntity excuteToClient(GeneralBusEntity entity) throws TradeProcException;
	
	/**
	 * 返回客户端后的逻辑操作
	 * @param entity
	 * @return
	 * @throws TradeProcException
	 */
	public void afterExcute(GeneralBusEntity entity) throws TradeProcException;
	
	/**
	 * 获取交易响应结果
	 * @param entity
	 * @return
	 * @throws TradeProcException
	 */
	public void getTradeRes(GeneralBusEntity entity) throws TradeProcException;
}
