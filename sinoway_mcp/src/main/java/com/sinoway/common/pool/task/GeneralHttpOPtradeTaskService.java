package com.sinoway.common.pool.task;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sinoway.common.constant.MsgTransfConstant.MsgTemplateType;
import com.sinoway.common.constant.ServerConstant.ResSta;
import com.sinoway.common.constant.SystemConstant;
import com.sinoway.common.constant.SystemConstant.BusProcSta;
import com.sinoway.common.entity.CoreMsgHeader;
import com.sinoway.common.entity.DesEntity;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.entity.GeneralMsgHeader;
import com.sinoway.common.exception.MsgHeaderParseException;
import com.sinoway.common.exception.TradeAuthException;
import com.sinoway.common.exception.TradeProcException;
import com.sinoway.common.service.parse.GeneralMsgHeaderService;
import com.sinoway.common.service.processer.GeneralProcesserService;
import com.sinoway.common.service.server.authen.AuthenTemplateFactory;
import com.sinoway.common.service.template.GeneralTemplateService;
import com.sinoway.common.util.ByteUtil;
import com.sinoway.common.util.DateUtil;
import com.sinoway.common.util.GUIDGenerator;
import com.sinoway.common.util.StringUtil;
import com.sinoway.common.util.SystemCfgUtil;
import com.sinoway.common.util.SystemOperateUtil;
import com.yzj.wf.com.ibank.common.server.ThreadPoolException;

public class GeneralHttpOPtradeTaskService<E extends String,T extends Map> extends AbstractCallablePoolTask<E,T> {
	
	private GeneralMsgHeaderService msgHeaderService;// 通用报文头解析服务
	private AuthenTemplateFactory authtemplFactoryService;//Ip 渠道 交易认证模板工厂
	private GeneralTemplateService templateService = null;// 交易模板服务
	private HttpServletRequest request;
	private HttpServletResponse response;
	public GeneralHttpOPtradeTaskService() {
		super();
	}

	@Override
	public String excuteTask(Map parm) throws Exception {
		request = (HttpServletRequest)parm.get("request");
		response = (HttpServletResponse)parm.get("response");
		String ip = request.getRemoteAddr();
		ip = "127.0.0.1";
		String port =String.valueOf(request.getRemotePort()) ;
		logger.info("获取到http请求 ip地址："+ip);
		// 初始化业务处理实体
		GeneralProcesserService processer = null;
		GeneralBusEntity busEntity = new GeneralBusEntity(); 
		String logId = GUIDGenerator.generateId();// 日志ID
		busEntity.setLogId(logId);
		CoreMsgHeader coreHeader = new CoreMsgHeader();
		GeneralMsgHeader header = new GeneralMsgHeader();
		busEntity.setCoreHeader(coreHeader);
		busEntity.setHeader(header);
		// 接收时间
		Date date = new Date();
		
		busEntity.setTrnddate(date);
		// 设置交易日期
		busEntity.setTrnDate(DateUtil.dateToString(date, "yyyyMMdd"));
		// 设置交易时间
		busEntity.setTrnTime(DateUtil.dateToString(date, "HHmmssSSS"));
		try {
			//判断ip是否可以访问	
			if(authtemplFactoryService != null){
				if(!authtemplFactoryService.isIpCanDo(ip)){
					logger.error(logId + "：不接收该Ip的请求：ip" + ip + "：" + request.getRemotePort());
					SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), "ip认证不通过", busEntity);
					responseMsg(busEntity,header,logId,port);
					return "0";
				}
			} 
		}catch (TradeAuthException e) {
			logger.error(logId + "：发生认证异常："+e.getMessage());
			SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), "发生认证异常", busEntity);
			responseMsg(busEntity,header,logId,port);
			e.printStackTrace();
		}
		//获取报文体
		byte[] msgBody = null;
		logger.info(logId + "：接收报文体数据......，端口号：" + port);
		try {
			InputStream inputStream = request.getInputStream();
			if(null == inputStream){
				logger.error(logId + "：服务处理异常: 报文体内容不能为空，端口号：" + port);
				SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), "报文体信息不能为空", busEntity);
				responseMsg(busEntity,header,logId,port);
				return "0";
			}
			msgBody = ByteUtil.input2byte(inputStream);
			if(msgBody == null || msgBody.length == 0){
				logger.error(logId + "：服务处理异常: 报文体内容不能为空" );
				SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), "报文体不能为空", busEntity);
				responseMsg(busEntity,header,logId,port);
				throw new ThreadPoolException("服务处理异常: 报文体内容不能为空");	
			}
			logger.info(logId + "：接收报文体数据完成，内容：" + new String(msgBody,SystemConstant.DEFAULT_CHARSET));
			busEntity.setDownMsg(msgBody);
		} catch (Exception e) {
			logger.error(logId + "服务处理异常: 读取报文体异常，端口号：" + port);
//					SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), "读取报文体信息异常", busEntity);
//					responseMsg(busEntity,header,logId,port);
			e.printStackTrace();
			return "0";
		}
				
		logger.info(logId + "：开始接收报文头信息，端口号：" + port);

		try {
			header = msgHeaderService.reciveMsgHeader(request);
		} catch (MsgHeaderParseException e) {
			logger.error(logId + "：服务处理异常，接收报文头信息错误" +e.getMessage());
			SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), "接收报文头信息错误", busEntity);
			responseMsg(busEntity,header,logId,port);
			e.printStackTrace();
			return "0";
		}
		
		//TODO 具体存储方式待定
		byte[] hBytes = null;
		try {
			hBytes = msgHeaderService.transHeaderToByte(header);
			
		} catch (MsgHeaderParseException e1) {
			e1.printStackTrace();
		}
//				if(hBytes == null || hBytes.length == 0){
//					logger.error("：服务处理异常，接收报文头信息错误，报文头信息不能为空。端口号：" + port);
//					throw new ThreadPoolException("服务处理异常，接收报文头信息错误，报文头信息不能为空：" + port);
//				}
		try{
			if(hBytes!=null){
				logger.info(logId + "：接收报文头信息成功，内容：" + new String(hBytes) + "端口号：" + port);

				SystemOperateUtil.savePortFile(date,port,  "h", "req", hBytes);
			}
		}catch(Exception e){
			logger.error(logId + "：服务处理异常：请求报文头存储失败：" + port);
		}
		
		busEntity.setHeader(header);	
		// 渠道号
		String chnlCode = header.getChnlcod();
		// 校验报文头
		try{
			SystemOperateUtil.chkHeader(header, logId, port);
		}catch(Exception e){
			SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), e.getMessage(), busEntity);
			responseMsg(busEntity,header,logId,port);
			e.printStackTrace();
			return "0";
		}
		
		
		// 获取业务处理bean
		try{
			processer = SystemOperateUtil.getBusProcesser(header, busEntity, authtemplFactoryService, templateService, ip, port, logId);
		}catch(Exception e){
			e.printStackTrace();
			SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), e.getMessage(), busEntity);
			responseMsg(busEntity,header,logId,port);
			return "0";
		}
		
		try {
			logger.info(logId + "：接收报文体数据完成，内容：" + new String(msgBody,SystemConstant.DEFAULT_CHARSET) + "端口号：" + port);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 存储报文体
		try{
			SystemOperateUtil.savePortFile(date, port, "b", "req", msgBody);
		}catch(Exception e){
			logger.error(logId + "：服务处理异常：请求报文体存储失败",e);
		}
		
		DesEntity keyEntity = SystemCfgUtil.getDesKeyInfByChnl(chnlCode);
		if(keyEntity == null || StringUtil.NullToString(keyEntity.getKey()).equals("")){
			logger.error(logId + "：服务处理异常：密钥信息不能为空" +  "，渠道号：" + chnlCode);
			SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), "密钥信息不能为空", busEntity);
			responseMsg(busEntity,header,logId,port);
			throw new ThreadPoolException("服务处理异常：密钥信息不能为空" + "，渠道号：" + chnlCode);
		}
		
		busEntity.setDesKeyInf(keyEntity);// 设置密钥信息
		
		String key = keyEntity.getKey();
		
		boolean isMsgTampered = true;
		try{
			isMsgTampered = SystemOperateUtil.isMsgTampered(chnlCode,key, msgBody, header.getCheckcod());
		}catch(Exception e){
			logger.error(logId + "：服务处理异常：校验报文异常");
			SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), "校验报文异常", busEntity);
			responseMsg(busEntity,header,logId,port);
			throw new ThreadPoolException("服务处理异常：校验报文异常");
		}
		if(isMsgTampered){
			logger.error(logId + "：服务处理异常：报文校验不通过");
			SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), "报文校验失败", busEntity);
			responseMsg(busEntity, header,logId,port);
			throw new ThreadPoolException("服务处理异常：报文被篡改过");
		}
		try{
			
			String msgType = header.getMsgtype();
			// 业务发起报文
			if(MsgTemplateType.REQ.getValue().equals(msgType)){
				logger.info(logId + "：开始发起交易......");
				// 开启业务处理
				busEntity = processer.excuteToCore(busEntity);
				
				logger.info(logId + "：交易发起完成，前置流水:" +busEntity.getFrntJrn());
			// 结果获取
			}else if(MsgTemplateType.ASYNRESREQ.getValue().equals(msgType)){
				logger.info(logId + "：开始获取结果......");
				// 开启获取结果
				busEntity = processer.excuteToCore(busEntity);
				logger.info(logId + "：获取结果获取结果完成");
			}else{
				logger.error(logId + "：服务处理异常：不支持的报文类型" +  "，报文类型：" + msgType);
				SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), "报文校验失败", busEntity);
				responseMsg(busEntity, header,logId,port);
				throw new ThreadPoolException("服务处理异常：不支持的报文类型" + "，报文类型：" + msgType);
			}
		}catch(Exception e){
			logger.error(logId + "：服务处理异常：业务处理过程中出现异常",e);
			SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(),e.getMessage(), busEntity);
			responseMsg(busEntity,header,logId,port);
			throw new ThreadPoolException("服务处理异常：业务处理过程中出现异常",e);
		}
		
		logger.info(logId + "：开始返回响应报文......，前置流水:" +busEntity.getFrntJrn());
		
		//返回报文头
		try{
			responseMsg(busEntity, header,logId,port);
			logger.info(logId + "：返回响应报文成功......，前置流水:" +busEntity.getFrntJrn());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// 返回客户端后的逻辑
		try{
			if(busEntity.isRecordFlw())
				processer.afterExcute(busEntity);
		}catch(TradeProcException e){
			logger.error(logId + "：服务处理异常:返回客户端后的后续逻辑操作出现异常",e);
			throw new ThreadPoolException("服务处理异常:返回客户端后的后续逻辑操作出现异常",e);
		}catch(Exception e){
			logger.error(logId + "：服务处理异常:返回客户端后的后续逻辑操作出现异常",e);
			throw new ThreadPoolException("服务处理异常:返回客户端后的后续逻辑操作出现异常",e);
		}
		
		return "1";
	  
	}
	
	/**
	 * 向客户端响应消息
	 * @param busEntity
	 * @param header
	 * @param logId
	 * @param port
	 */
	private void responseMsg(GeneralBusEntity busEntity,GeneralMsgHeader header,String logId,String port){
		byte[] upHeader =  null;
		
		try{
			upHeader = msgHeaderService.transHeaderToByte(header);
			if(upHeader == null || upHeader.length == 0){
				logger.error(logId + "：服务处理异常：返回报文头信息不能为空");
				busEntity.setResSta(ResSta.F.getValue());
				busEntity.setErrMsg(logId + "：服务处理异常：返回报文头信息不能为空");
			}
		}catch(MsgHeaderParseException e){
			logger.error(logId + "：服务处理异常：转换报文头失败",e);
			busEntity.setResSta(ResSta.F.getValue());
			busEntity.setErrMsg(e.getClass().toString() + ":" + e.getMessage());
		}catch(Exception e){
			logger.error(logId + "：服务处理异常：转换报文头失败",e);
			busEntity.setResSta(ResSta.F.getValue());
			busEntity.setErrMsg(e.getClass().toString() + ":" + e.getMessage());
		}
			try{
				// 返回报文体
				byte[] upMsg = busEntity.getUpMsg();
				
//				if(upMsg == null || upMsg.length == 0){
//					logger.error(logId + ":服务处理异常：返回报文体信息不能为空");
//					throw new ThreadPoolException("服务处理异常：返回报文体信息不能为空");
//				}
				
				//TODO 存储问题待定
				// 存储返回报文头与报文体
				try{
					SystemOperateUtil.savePortFile(busEntity.getTrnddate(),port,"h", "res", upHeader);
					if(upMsg != null && upMsg.length > 0)
						SystemOperateUtil.savePortFile(busEntity.getTrnddate(),port,"b", "res", upMsg);
				}catch(Exception e){
					logger.error("服务处理异常：存储响应报文信息失败",e);
				}
				logger.info("开始写回报文头，前置流水:" +busEntity.getFrntJrn()+ ",内容：" + new String(upHeader));
				response.setCharacterEncoding(SystemConstant.DEFAULT_CHARSET);
				// 报文头写回
				response.setHeader("checkcod", busEntity.getHeader().getCheckcod());
				
				if(upMsg != null && upMsg.length > 0){
					logger.info(logId + ":开始写回报文体，前置流水:" +busEntity.getFrntJrn()+ ",内容：" + new String(upMsg,SystemConstant.DEFAULT_CHARSET));
					response.getOutputStream().write(upMsg);
				}

				// 报文体写e回
				logger.info(logId + ":本次交易处理完成，前置流水:" +busEntity.getFrntJrn());
			}catch(IOException e){
				logger.error(logId + ":服务处理异常：返回客户端出现I/O异常",e);
				busEntity.setResSta(ResSta.F.getValue());
				busEntity.setErrMsg(e.getClass().toString() + ":" + e.getMessage());
			}catch(Exception e){
				logger.error(logId + ":服务处理异常：返回客户端出现异常",e);
				busEntity.setResSta(ResSta.F.getValue());
				busEntity.setErrMsg(e.getClass().toString() + ":" + e.getMessage());
			}
		
	}

	/*
	 *   GETTER  AND  SETTER
	 */
	public AuthenTemplateFactory getAuthtemplFactoryService() {
		return authtemplFactoryService;
	}
	public void setAuthtemplFactoryService(
			AuthenTemplateFactory authtemplFactoryService) {
		this.authtemplFactoryService = authtemplFactoryService;
	}
	public GeneralMsgHeaderService getMsgHeaderService() {
		return msgHeaderService;
	}
	public void setMsgHeaderService(GeneralMsgHeaderService msgHeaderService) {
		this.msgHeaderService = msgHeaderService;
	}
	public GeneralTemplateService getTemplateService() {
		return templateService;
	}
	public void setTemplateService(GeneralTemplateService templateService) {
		this.templateService = templateService;
	}
	
}
