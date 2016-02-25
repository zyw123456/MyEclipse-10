package com.sinoway.common.service.transfer;

import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.mcp.exception.TradeMsgTransfException;

/**
 * 通用报文转换服务
 * @author Liuzhen
 * @version 1.0
 * 2015-11-23
 */
public  interface GeneralTransferService {
	
	/**
	 * 把客户端报文转换成请求核心报文
	 * @param entity
	 * @throws TradeMsgTransfException
	 */
	public void transToReqCoreMsg(GeneralBusEntity entity) throws TradeMsgTransfException;
	/**
	 * 异步即时响应客户端报文
	 * @param entity
	 * @throws TradeMsgTransfException
	 */
	public void transToAsynLResMsg(GeneralBusEntity entity) throws TradeMsgTransfException;
	/**
	 * 响应客户端报文
	 * @param entity
	 * @throws TradeMsgTransfException
	 */
	public void transToResCMsg(GeneralBusEntity entity) throws TradeMsgTransfException;
	
	/**
	 * 请求供应商报文
	 * @param entity
	 * @throws TradeMsgTransfException
	 */
	public void transToReqSupMsg(GeneralBusEntity entity) throws TradeMsgTransfException;
	
	/**
	 * 转换成响应核心报文
	 * @param entity
	 * @throws TradeMsgTransfException
	 */
	public void transToResCoreMsg(GeneralBusEntity entity)throws TradeMsgTransfException;
	
	/**
	 * 初步解析批量报文
	 * @param entity
	 * @throws TradeMsgTransfException
	 */
	public void initBatchMsg(GeneralBusEntity entity)throws TradeMsgTransfException;
}
