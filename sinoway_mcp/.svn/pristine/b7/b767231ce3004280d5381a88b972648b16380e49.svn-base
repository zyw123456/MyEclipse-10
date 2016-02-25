package com.sinoway.common.pool.task;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

import com.sinoway.common.constant.MsgTransfConstant.MsgTemplateType;
import com.sinoway.common.constant.ServerConstant.ResSta;
import com.sinoway.common.constant.SystemConstant;
import com.sinoway.common.constant.SystemConstant.BusProcSta;
import com.sinoway.common.entity.CoreMsgHeader;
import com.sinoway.common.entity.DesEntity;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.entity.GeneralMsgHeader;
import com.sinoway.common.exception.MsgHeaderParseException;
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

public class GeneralSocketOPtradeTaskService<T extends Socket> extends AbstractRunnablePoolTask<T> {
	
	private GeneralMsgHeaderService msgHeaderService;// 通用报文头解析服务
	private AuthenTemplateFactory authtemplFactoryService;//Ip 渠道 交易认证模板工厂
	private GeneralTemplateService templateService = null;// 交易模板服务
	private String port;
	public GeneralSocketOPtradeTaskService() {
		super();
	}

	@Override
	public void excuteTask(Socket taskSocket) throws Exception {
		String logId = GUIDGenerator.generateId();// 日志ID
		logger.info(logId + "：开始处理交易请求，端口号：" + port);
		OutputStream os = null;
		InputStream is = null;
		GeneralProcesserService processer = null;
		
		try {
			String ip = taskSocket.getInetAddress().getHostAddress();
			
			//输入输出流
			is = taskSocket.getInputStream();
			os = taskSocket.getOutputStream();
			// 初始化业务处理实体
			GeneralBusEntity busEntity = new GeneralBusEntity(); 
			GeneralMsgHeader header = new GeneralMsgHeader();  
			CoreMsgHeader coreHeader = new CoreMsgHeader();
			busEntity.setCoreHeader(coreHeader);
			busEntity.setHeader(header);
			// 判断是否接收该ip的请求
			if(authtemplFactoryService != null){
				if(!authtemplFactoryService.isIpCanDo(ip)){
					logger.error("不接收该Ip的请求：ip" + ip + "，端口号：" + port);
					SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), "ip认证不通过", busEntity);
					responseMsg(os,busEntity,logId);
					return;
				}
			}
			
			logger.info(logId + "：开始接收报文头信息，端口号：" + port);
			
			// 接收报文头信息
			byte[] hBytes = null;
			try{
				header = msgHeaderService.reciveMsgHeader(is);
				busEntity.setHeader(header);
				hBytes = header.getBytes();
				if(hBytes == null || hBytes.length == 0){
					logger.error(logId + "：服务处理异常，接收报文头信息错误，报文头信息不能为空。端口号：" + port);
					SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), "报文头不能为空", busEntity);
					responseMsg(os,busEntity,logId);
					return;
				}
			}catch(Exception e){
				logger.error(logId + "：服务处理异常，接收报文头信息错误。端口号：" + port,e);
				SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), "报文头解析异常", busEntity);
				responseMsg(os,busEntity,logId);
				return;
			}
				
			logger.info(logId + "：接收报文头信息成功，内容：" + new String(hBytes) + "端口号：" + port);

			// 接收时间
			Date date = new Date();
			
			busEntity.setTrnddate(date);
			// 设置交易日期
			busEntity.setTrnDate(DateUtil.dateToString(date, "yyyyMMdd"));
			// 设置交易时间
			busEntity.setTrnTime(DateUtil.dateToString(date, "HHmmssSSS"));
			
			try{
				if(hBytes!=null)
				SystemOperateUtil.savePortFile(date, port, "h", "req", hBytes);
			}catch(Exception e){
				logger.error(logId + "：服务处理异常：请求报文头存储失败，端口号：" + port);
			}
			
			// 渠道号
			String chnlCode = header.getChnlcod();
			
			// 校验报文头
			try{
				SystemOperateUtil.chkHeader(header, logId, port);
			}catch(Exception e){
				SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), e.getMessage(), busEntity);
				responseMsg(os,busEntity,logId);
				e.printStackTrace();
				return;
			}
			
			// 获取业务处理bean
			try{
				processer = SystemOperateUtil.getBusProcesser(header, busEntity, authtemplFactoryService, templateService, ip, port, logId);
			}catch(Exception e){
				e.printStackTrace();
				SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), e.getMessage(), busEntity);
				responseMsg(os,busEntity,logId);
				return;
			}
			
			// 接收报文体
			int msgLen = header.getMsglen();
			
			logger.info(logId + "：接收报文体数据......，端口号：" + port);
			byte[] msgBody = null;
			try {
				if(msgLen > 0){
					msgBody = ByteUtil.readFixBytesFromInput(is, msgLen);
					if(msgBody == null || msgBody.length == 0){
						logger.error(logId + "：服务处理异常: 报文体内容不能为空，端口号：" + port);
						SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), "报文体信息不能为空", busEntity);
						responseMsg(os,busEntity,logId);
						return;
					}
				}
				
			} catch (Exception e) {
				logger.error(logId + "服务处理异常: 读取报文体异常，端口号：" + port);
				SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), "读取报文体信息异常", busEntity);
				responseMsg(os,busEntity,logId);
				return;
			}
		
			logger.info(logId + "：接收报文体数据完成，内容：" + new String(msgBody,SystemConstant.DEFAULT_CHARSET) + "端口号：" + port);
			
			// 存储报文体
			try{
				SystemOperateUtil.savePortFile(date, port, "b", "req", msgBody);
			}catch(Exception e){
				logger.error(logId + ":服务处理异常：请求报文体存储失败，端口号：" + port,e);
			}
				
			// 设置报文体
			busEntity.setDownMsg(msgBody);
			
			//获取密钥信息
			DesEntity keyEntity = SystemCfgUtil.getDesKeyInfByChnl(chnlCode);
			if(keyEntity == null || StringUtil.NullToString(keyEntity.getKey()).equals("")){
				logger.error(logId + ":服务处理异常：密钥信息不能为空，端口号：" + port +  "，渠道号：" + chnlCode);
				SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), "找不到该渠道密钥配置信息", busEntity);
				responseMsg(os,busEntity,logId);
				return;
			}
			
			busEntity.setDesKeyInf(keyEntity);// 设置密钥信息
			
			String key = keyEntity.getKey();
			
			boolean isMsgTampered = true;
			try{
				isMsgTampered = SystemOperateUtil.isMsgTampered(chnlCode,key, msgBody, header.getCheckcod());
				if(isMsgTampered){
					logger.error(logId + ":服务处理异常：报文被篡改过，端口号：" + port);
					SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), "报文校验不通过", busEntity);
					responseMsg(os,busEntity,logId);
					return;	
				}
			}catch(Exception e){
				logger.error(logId + ":服务处理异常：校验报文异常，端口号：" + port);
				SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), "报文校验不通过", busEntity);
				responseMsg(os,busEntity,logId);
				return;		
			}
			try{
				
				String msgType = header.getMsgtype();
				// 业务发起报文
				if(MsgTemplateType.REQ.getValue().equals(msgType)){
					logger.info(logId + "：开始发起交易......，端口号：" + port);
					// 开启业务处理
					busEntity = processer.excuteToCore(busEntity);
					
					logger.info(logId + "：交易发起完成，前置流水:" +busEntity.getFrntJrn()+ ",端口号：" + port);
				// 结果获取
				}else if(MsgTemplateType.ASYNRESREQ.getValue().equals(msgType)){
					logger.info(logId + ":开始获取结果......，端口号：" + port);
					// 开启获取结果
//					busEntity = processer.excuteToCore(busEntity);
					logger.info(logId + ":获取结果获取结果完成，端口号：" + port);
				}else{
					logger.error(logId + ":服务处理异常：不支持的报文类型，端口号：" + port +  "，报文类型：" + msgType);
					SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), "服务处理异常：不支持的报文类型", busEntity);
					responseMsg(os,busEntity,logId);
					return;	
//					throw new ThreadPoolException("服务处理异常：不支持的报文类型，端口号：" + port + "，报文类型：" + msgType);
				}
			}catch(Exception e){
				logger.error(logId + ":服务处理异常：业务处理过程中出现异常，端口号：" + port,e);
//				throw new ThreadPoolException("服务处理异常：业务处理过程中出现异常，端口号：" + port,e);
			}
			
			logger.info(logId + "：开始返回响应报文......，前置流水:" +busEntity.getFrntJrn()+ ",端口号：" + port);
			
			try{
				responseMsg(os,busEntity,logId);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			// 返回客户端后的逻辑
			try{
				if(busEntity.isRecordFlw())
					processer.afterExcute(busEntity);
			}catch(TradeProcException e){
				logger.error(logId + ":服务处理异常:返回客户端后的后续逻辑操作出现异常，端口号：" + port,e);
				throw new Exception("服务处理异常:返回客户端后的后续逻辑操作出现异常，端口号：" + port,e);
			}catch(Exception e){
				logger.error(logId + ":服务处理异常:返回客户端后的后续逻辑操作出现异常，端口号：" + port,e);
				throw new Exception("服务处理异常:返回客户端后的后续逻辑操作出现异常，端口号：" + port,e);
			}
		
		} catch (IOException e) {
			logger.error(logId + ":服务处理异常:服务处理出现异常，IO错误，端口号：" + port,e);
			throw new Exception("服务处理异常: I/O解析异常，端口号：" + port, e);
		}catch(Exception e){
			logger.error(logId + ":服务处理异常:未知，端口号：" + port,e);
			throw new Exception("服务处理异常: 未知，端口号：" + port, e);
		}finally{
			if(os != null){
				try {
					os.close();
					os = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(is != null){
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(taskSocket != null){
				try {
					taskSocket.close();
					taskSocket = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void responseMsg(OutputStream os,GeneralBusEntity busEntity,String logId) throws Exception{
		//返回报文头
		byte[] upHeader =  null;
		try{
			upHeader = msgHeaderService.transHeaderToByte(busEntity.getHeader());
			if(upHeader == null || upHeader.length == 0){
				logger.error(logId + ":服务处理异常：返回报文头信息不能为空，端口号：" + port);
				busEntity.setResSta(ResSta.F.getValue());
				busEntity.setErrMsg("服务处理异常：返回报文头信息不能为空，端口号：" + port);
				throw new Exception();
			}
		}catch(MsgHeaderParseException e){
			logger.error(logId + ":服务处理异常：转换报文头失败，端口号：" + port,e);
			busEntity.setResSta(ResSta.F.getValue());
			busEntity.setErrMsg(e.getClass().toString() + ":" + e.getMessage());
			throw e;
		}catch(Exception e){
			logger.error(logId + ":服务处理异常：转换报文头失败，端口号：" + port,e);
			busEntity.setResSta(ResSta.F.getValue());
			busEntity.setErrMsg(e.getClass().toString() + ":" + e.getMessage());
			throw e;
		}
		
		try{
			// 返回报文体
			byte[] upMsg = busEntity.getUpMsg();
			
//			if(upMsg == null || upMsg.length == 0){
//				logger.error(logId + ":服务处理异常：返回报文体信息不能为空，端口号：" + port);
////				throw new ThreadPoolException("服务处理异常：返回报文体信息不能为空，端口号：" + port);
//			}
			// 存储返回报文头与报文体
			try{
				SystemOperateUtil.savePortFile(busEntity.getTrnddate(),port,"h", "res", upHeader);
				if(upMsg != null && upMsg.length > 0)
					SystemOperateUtil.savePortFile(busEntity.getTrnddate(),port,"", "res", upMsg);
			}catch(Exception e){
				logger.error(logId + ":服务处理异常：存储响应报文信息失败，端口号：" + port,e);
			}
			logger.info(logId + "：开始写回报文头，前置流水:" +busEntity.getFrntJrn()+ ",内容：" + new String(upHeader) + "端口号：" + port);
			// 报文头写回
			os.write(upHeader);
			
			os.flush();
			// 报文体写回
			if(upMsg != null && upMsg.length > 0){
				logger.info(logId + "：开始写回报文体，前置流水:" +busEntity.getFrntJrn()+ ",内容：" + new String(upMsg,SystemConstant.DEFAULT_CHARSET) + "端口号：" + port);
				os.write(upMsg);
				os.flush();
			}
			
			logger.info(logId + "：本次交易处理完成，前置流水:" +busEntity.getFrntJrn()+ ",端口号：" + port);
		}catch(IOException e){
			logger.error(logId + ":服务处理异常：返回客户端出现I/O异常，端口号：" + port,e);
			busEntity.setResSta(ResSta.F.getValue());
			busEntity.setErrMsg(e.getClass().toString() + ":" + e.getMessage());
			throw e;
		}catch(Exception e){
			logger.error(logId + ":服务处理异常：返回客户端出现异常，端口号：" + port,e);
			busEntity.setResSta(ResSta.F.getValue());
			busEntity.setErrMsg(e.getClass().toString() + ":" + e.getMessage());
			throw e;
		}
	}

	/*
	 *   GETTER  AND  SETTER
	 */
	public GeneralMsgHeaderService getMsgHeaderService() {
		return msgHeaderService;
	}

	public void setMsgHeaderService(GeneralMsgHeaderService msgHeaderService) {
		this.msgHeaderService = msgHeaderService;
	}

	public AuthenTemplateFactory getAuthtemplFactoryService() {
		return authtemplFactoryService;
	}

	public void setAuthtemplFactoryService(
			AuthenTemplateFactory authtemplFactoryService) {
		this.authtemplFactoryService = authtemplFactoryService;
	}

	public GeneralTemplateService getTemplateService() {
		return templateService;
	}

	public void setTemplateService(GeneralTemplateService templateService) {
		this.templateService = templateService;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
}
