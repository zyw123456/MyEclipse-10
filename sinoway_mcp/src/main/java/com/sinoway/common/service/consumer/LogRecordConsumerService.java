package com.sinoway.common.service.consumer;

import org.apache.commons.beanutils.BeanUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinoway.common.constant.SystemConstant.LogType;
import com.sinoway.common.entity.TradeLogEntity;
import com.sinoway.common.util.GUIDGenerator;
import com.sinoway.common.util.McpLogger;
import com.sinoway.mcp.entity.FDatExptrnlog;
import com.sinoway.mcp.entity.FDatOvertrnlog;
import com.sinoway.mcp.entity.FLogTrnflw;
import com.sinoway.mcp.service.FDatExptrnlogService;
import com.sinoway.mcp.service.FDatOvertrnlogService;
import com.sinoway.mcp.service.FLogTrnflwService;

/**
 * 对消息队列的日志进行处理
 * @author zhangyanwei
 *
 */
public class LogRecordConsumerService extends DefaultConsumerService {

	
	private McpLogger logger = McpLogger.getLogger(getClass());
	private FLogTrnflwService fLogTrnflwService ;// 交易日志service
	private FDatOvertrnlogService fDatOvertrnlogService ;// 超时重发交易日志service
	private FDatExptrnlogService fDatExptrnlogService ;// 异常交易日志service

	@Override
	public void excuteMsg(String msg) throws Exception {

		String logId = GUIDGenerator.generateId();// 日志ID
		logger.info(logId + "：接收到日志......,内容:" + msg);
		TradeLogEntity   entity  = null;
	 try{
		JSONObject  json = (JSONObject) JSONObject.parse(msg);
		
		//将json转化成对象
	    entity = JSON.toJavaObject(json, TradeLogEntity.class);
	 }catch(Exception e){
			logger.error(logId + "接收到的日志 转化为 TradeLogEntity 的时候失败:" + msg);
			e.printStackTrace();
			throw e;
	 }
     try{
	    if(entity.getLogtype().equals(LogType.TRADE.getValue())){
	    	//交易日志
		  FLogTrnflw  flw = new FLogTrnflw();
		  BeanUtils.copyProperties(flw, entity);
			 // 生成交易日志
		  fLogTrnflwService.save(flw);
		  logger.info(logId + "：接收到日志......类型:" + entity.getLogtype());
	    }else if(entity.getLogtype().equals(LogType.TIMEOUT.getValue())){
	    	//超时重发日志
		  FDatOvertrnlog overlog = new FDatOvertrnlog();
	      BeanUtils.copyProperties(overlog, entity);
	      fDatOvertrnlogService.save(overlog);
	      logger.info(logId + "：接收到日志......类型:" + entity.getLogtype());
	    }else if(entity.getLogtype().equals(LogType.ERROR.getValue())){
	    	//异常交易日志
		  FDatExptrnlog  execLog  = new FDatExptrnlog();
	      BeanUtils.copyProperties(execLog, entity);
	      fDatExptrnlogService.save(execLog);
	      logger.info(logId + "：接收到日志......类型:" + entity.getLogtype());
	    }
		
		}catch(Exception e){
			logger.error(logId + "：新增日志信息出错，流水号："+entity.getRegid(),e);
		   throw e;
		}
		
	}
     public FLogTrnflwService getfLogTrnflwService() {
 		return fLogTrnflwService;
 	}

 	public void setfLogTrnflwService(FLogTrnflwService fLogTrnflwService) {
 		this.fLogTrnflwService = fLogTrnflwService;
 	}

	public FDatOvertrnlogService getfDatOvertrnlogService() {
		return fDatOvertrnlogService;
	}
	public FDatExptrnlogService getfDatExptrnlogService() {
		return fDatExptrnlogService;
	}
	public void setfDatExptrnlogService(FDatExptrnlogService fDatExptrnlogService) {
		this.fDatExptrnlogService = fDatExptrnlogService;
	}
	public void setfDatOvertrnlogService(FDatOvertrnlogService fDatOvertrnlogService) {
		this.fDatOvertrnlogService = fDatOvertrnlogService;
	}
	



}