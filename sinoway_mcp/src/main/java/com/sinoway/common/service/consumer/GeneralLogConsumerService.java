package com.sinoway.common.service.consumer;

import org.apache.commons.beanutils.BeanUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinoway.common.constant.SystemConstant.LogType;
import com.sinoway.common.entity.TradeLogEntity;
import com.sinoway.common.util.GUIDGenerator;
import com.sinoway.common.util.McpLogger;
import com.sinoway.common.util.StringUtil;
import com.sinoway.mcp.entity.FDatExptrnlog;
import com.sinoway.mcp.entity.FDatOvertrnlog;
import com.sinoway.mcp.entity.FLogTrnflw;
import com.sinoway.mcp.service.FDatExptrnlogService;
import com.sinoway.mcp.service.FDatOvertrnlogService;
import com.sinoway.mcp.service.FLogTrnflwService;


/**
 * 通用日志消费者服务默认实现
 * @author Liuzhen
 * @version 1.0
 * 2015-11-24
 */
public class GeneralLogConsumerService extends DefaultConsumerService {

	private McpLogger logger = McpLogger.getLogger(getClass());
	
	/*
	 * 注入服务
	 */
	FLogTrnflwService fLogTrnflwService = null;
	FDatExptrnlogService fDatExptrnlogService = null;
	FDatOvertrnlogService fDatOvertrnlogService = null;
	
	private String logId = "";// 日志ID
	
	public GeneralLogConsumerService(String topic, String groupId) {
		super(topic, groupId);
	}
	public GeneralLogConsumerService() {
		super();
	}

	/**
	 * 消息处理
	 */
	@Override
	public void excuteMsg(String msg) {
		try{
			logger.info(logId + "：接收到日志处理请求，开始处理......，内容:" + msg);
			String logId = GUIDGenerator.generateId();// 日志ID
			JSONObject logInfo = (JSONObject)JSON.parse(msg);
			TradeLogEntity logEntity = JSON.toJavaObject(logInfo, TradeLogEntity.class);
			String regId = "";// 登记ID
			if(logEntity != null){
				
				if(!checkEntity(logEntity))
					return;
				
				regId = logEntity.getRegid();// 登记ID
				String fntjrn = logEntity.getFnttrnjrn();// 交易流水
				// 交易日志
				if(LogType.TRADE.getValue().equals(logEntity.getLogtype())){
					FLogTrnflw entity = new FLogTrnflw();
					
					BeanUtils.copyProperties(entity, logEntity);
					
					fLogTrnflwService.save(entity);
					
					logger.info(logId + "：记录日志成功，登记id：" + regId + "，交易流水号：" + fntjrn + "，类型： 交易日志");
					
				// 超时日志
				}else if(LogType.TIMEOUT.getValue().equals(logEntity.getLogtype())){
					FDatOvertrnlog fDatOvertrnlog = new FDatOvertrnlog();
					BeanUtils.copyProperties(fDatOvertrnlog, logEntity);
					fDatOvertrnlogService.save(fDatOvertrnlog);
					logger.info(logId + "：记录日志成功，登记id：" + regId + "，交易流水号：" + fntjrn + "，重发目的方：" + fDatOvertrnlog.getRetrnto()+ "，类型： 超时日志");
				// 异常日志
				}else if(LogType.TRADE.getValue().equals(logEntity.getLogtype())){
					
					FDatExptrnlog fDatExptrnlog = new FDatExptrnlog();
					BeanUtils.copyProperties(fDatExptrnlog, logEntity);
					fDatExptrnlogService.save(fDatExptrnlog);
					logger.info(logId + "：记录日志成功，登记id：" + regId + "，交易流水号：" + fntjrn + "，异常信息：" + fDatExptrnlog.getExpreason()+ "，类型：异常日志");
				}
			}
			logger.info(logId + "：日志处理请求完成，登记Id:" + regId);
		}catch(Exception e){
			logger.error(logId + "：记录日志异常，内容：" + msg,e);
		}
		
	}
	
	/**
	 * 交易日志实体
	 * @param logEntity
	 * @return
	 */
	private boolean checkEntity(TradeLogEntity logEntity){
		if(StringUtil.NullToString(logEntity.getRegid()).equals(""))
			return false;
		if(StringUtil.NullToString(logEntity.getFnttrnjrn()).equals(""))
			return false;
		if(StringUtil.NullToString(logEntity.getTrntype()).equals(""))
			return false;
		if(StringUtil.NullToString(logEntity.getTrndte()).equals(""))
			return false;
		if(StringUtil.NullToString(logEntity.getTrntim()).equals(""))
			return false;
		if(StringUtil.NullToString(logEntity.getLogtype()).equals(""))
			return false;
		
		return true;
	}
	
	/*
	 *   GETTER   AND   SETTER
	 */
	public FLogTrnflwService getfLogTrnflwService() {
		return fLogTrnflwService;
	}
	public void setfLogTrnflwService(FLogTrnflwService fLogTrnflwService) {
		this.fLogTrnflwService = fLogTrnflwService;
	}
	public FDatExptrnlogService getfDatExptrnlogService() {
		return fDatExptrnlogService;
	}
	public void setfDatExptrnlogService(FDatExptrnlogService fDatExptrnlogService) {
		this.fDatExptrnlogService = fDatExptrnlogService;
	}
	public FDatOvertrnlogService getfDatOvertrnlogService() {
		return fDatOvertrnlogService;
	}
	public void setfDatOvertrnlogService(FDatOvertrnlogService fDatOvertrnlogService) {
		this.fDatOvertrnlogService = fDatOvertrnlogService;
	}
	
	
}
