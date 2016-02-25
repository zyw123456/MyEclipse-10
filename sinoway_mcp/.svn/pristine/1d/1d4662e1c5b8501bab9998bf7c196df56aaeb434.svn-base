package com.sinoway.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinoway.base.entity.BCfgdefChnltrd;
import com.sinoway.base.entity.BCfgdefFntsrvinfo;
import com.sinoway.common.constant.SystemConstant;
import com.sinoway.common.constant.Constant.MsgType;
import com.sinoway.common.constant.MsgTransfConstant.MsgTemplateType;
import com.sinoway.common.constant.ServerConstant.ServiceBean;
import com.sinoway.common.constant.SystemConstant.IsOrNot;
import com.sinoway.common.constant.SystemConstant.LogType;
import com.sinoway.common.constant.SystemConstant.SOPtrnTypt;
import com.sinoway.common.constant.SystemConstant.SysParamCode;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.entity.GeneralMsgHeader;
import com.sinoway.common.entity.Product;
import com.sinoway.common.entity.Trade;
import com.sinoway.common.entity.TradeLogEntity;
import com.sinoway.common.exception.TradeProcException;
import com.sinoway.common.frame.SpringContextUtil;
import com.sinoway.common.service.processer.GeneralProcesserService;
import com.sinoway.common.service.server.authen.AuthenTemplateFactory;
import com.sinoway.common.service.template.GeneralTemplateService;
import com.sinoway.mcp.queue.entity.KafkaQueEntity;
import com.sinoway.mcp.queue.service.IQueueOperator;
import com.sinoway.mcp.queue.service.KafkaQueOperator;
import com.yzj.wf.com.ibank.common.server.ThreadPoolException;

/**
 * 系统操作工具类 
 * @author Liuzhen
 * @version 1.0
 * 2015-12-1
 */
public  class SystemOperateUtil {
	private static McpLogger logger = McpLogger.getLogger(SystemOperateUtil.class);
	private static String reqMsgCachePath = "";// 请求报文缓存路径
	private static String resMsgCachePath = "";
	private static String logQueTopic = "";// 日志队列topic
	
	public void init(){
		// 保存报文头数据
		BCfgdefFntsrvinfo serInf = SystemCfgUtil.getSerInfoByIp(GetComputerCon.getInstance().getIp());
		if(serInf != null){
			// 请求报文缓存路径
			reqMsgCachePath = serInf.getReqmsgurl();
			// 响应报文缓存路径
			resMsgCachePath = serInf.getResmsgurl();
			
			// 获取日志队列topic
			logQueTopic = SystemCfgUtil.getSysParmValByCode(SysParamCode.LOG_QUE_TOPIC.getValue());
		}
		
	}
	
	/**
	 * 计算超时时间
	 * @param tmOut
	 * @return
	 */
	public static int getTrdTmOut(String tmOut){
		int out = 0;
		try{
			out = Integer.parseInt(tmOut) * 1000;
			return out; 
		}catch(Exception e){
			out = Integer.parseInt(SystemCfgUtil.getSysParmValByCode(SysParamCode.TRADE_DEF_TMOUT
					.getValue()));
		}
		
		return out;
	}
	
	/**
	 * 获取超时次数
	 * @param tmnum
	 * @return
	 */
	public static int getTrdTmOutNum(String tmnum){
		
		int num = 0;
		try{
			num = Integer.parseInt(tmnum);
			return num; 
		}catch(Exception e){
			num = Integer.parseInt(SystemCfgUtil.getSysParmValByCode(SysParamCode.TRADE_DEF_TMNUM
					.getValue()));
		}
		
		return num;
	}
	
	/**
	 * 检查报文头
	 * @param header
	 * @param logId
	 * @param port
	 * @throws Exception
	 */
	public static void chkHeader(GeneralMsgHeader header,String logId,String port) throws Exception{
		String prdCode = header.getPrdcod();
		String trdCod = header.getOuttrncod();
		String chnlCode = header.getChnlcod();
		// 非渠道号
		if(SystemConstant.NOT_CHNLCODE.equals(chnlCode)){
			logger.error(logId + "：交易处理失败：渠道号不能为00000000，端口号：" + port + "，渠道号：" + chnlCode);
			throw new Exception("交易处理失败：渠道号不能为00000000");
		}
		// 单笔提交时交易码  产品码不能同时为非
		if(IsOrNot.NOT.getValue().equals(header.getIsbatch())){
			if(SystemConstant.NOT_TRCODE.equals(trdCod) && SystemConstant.NOT_PRDCODE.equals(prdCode)){
				logger.error(logId + "：交易处理失败：交易码/产品码不能同时为非，端口号：" + port + "，交易码：" + trdCod + "，产品码：" + prdCode);
				throw new Exception("交易处理失败：交易码/产品码不能同时为非");
			}
		}
		// 交易码与产品码不能同时有值
		if(!SystemConstant.NOT_TRCODE.equals(trdCod) && !SystemConstant.NOT_PRDCODE.equals(prdCode)){
			logger.error(logId + "：交易处理失败：交易码/产品码不能同时不为非，端口号：" + port + "，交易码：" + trdCod + "，产品码：" + prdCode);
			throw new Exception("交易处理失败：交易码/产品码不能同时有值");
		}
	}
	/**
	 * 获取业务处理bean
	 * @param header
	 * @param busEntity
	 * @param authtemplFactoryService
	 * @param templateService
	 * @param ip
	 * @param port
	 * @param logId
	 * @return
	 * @throws Exception
	 */
	public static GeneralProcesserService getBusProcesser(
							GeneralMsgHeader header,
							GeneralBusEntity busEntity,
							AuthenTemplateFactory authtemplFactoryService,
							GeneralTemplateService templateService,
							String ip,String port,
							String logId) 
		throws Exception{
		
		
		String tradeCode = header.getOuttrncod();// 交易码
		String prdCode = header.getPrdcod();// 产品码
		String chnlCode = header.getChnlcod();// 渠道号
		Trade trade = null;	// 交易模板
		Product product = null;// 产品模板
		GeneralProcesserService  processer = null;
		// 获取内部交易码
		if(!SystemConstant.NOT_TRCODE.equals(tradeCode)){
			logger.info(logId + "：本次交易为原交易请求，交易码:" + tradeCode + "，渠道号：" + chnlCode + "，端口号：" + port);
			//外部交易码转换内部交易码
			tradeCode = SystemCfgUtil.getIntrByOutTr(tradeCode, SOPtrnTypt.O);
			header.setIntrncod(tradeCode);
			logger.info(logId + "：交易认证......，交易码:" + tradeCode + "，渠道号：" + chnlCode + "，端口号：" + port);
			try{
				// 交易认证不通过
				if(!authtemplFactoryService.isChnlIpTradeCanDo(chnlCode, ip, tradeCode)){
					logger.error(logId + "：交易处理失败：渠道 ip 交易码认证不通过，端口号：" + port + "，渠道号：" + chnlCode + "，IP:" + ip +  "，交易码：" + tradeCode);
					throw new Exception("交易处理失败：渠道 ip 交易码认证不通过");
				}
			}catch(Exception e){
				logger.error(logId + "：交易处理失败：交易认证出现异常，端口号：" + port,e);
				throw new Exception("交易处理失败：交易认证出现异常",e);
			}
			
			
			logger.info(logId + "：交易认证通过，交易码:" + tradeCode + "，渠道号：" + chnlCode + "，端口号：" + port);
			
			// 获取交易配置模板
			try {
				trade = templateService.getTradeByTemplate(chnlCode, tradeCode);
			} catch (Exception e) {
				logger.error(logId + "：交易处理失败：获取交易配置模板异常，端口号：" + port,e);
				throw new Exception("交易处理失败：未找到该交易配置信息");
			}
			
			if(trade == null){
				logger.error(logId + "：交易处理失败：交易模板未配置，端口号：" + port + "，渠道号：" + chnlCode + "，IP:" + ip +  "，交易码：" + tradeCode);
				throw new Exception("交易处理失败：未找到该交易配置信息");
			}

			// 获取业务处理服务
			processer = (GeneralProcesserService)SpringContextUtil.getBean(trade.getParamValueByName(ServiceBean.PROCESSER.getValue()));
			
			if(processer == null){
				logger.error(logId + "：交易处理失败：业务处理服务不能为空，端口号：" + port + "，渠道号：" + chnlCode + "，IP:" + ip +  "，交易码：" + tradeCode);
				throw new Exception("交易处理失败：交易处理失败");
			}
			
			// 设置交易类型
			busEntity.setTrnType(SOPtrnTypt.O.getValue());
		}else if(!SystemConstant.NOT_PRDCODE.equals(prdCode)){
			logger.info(logId + "：本次交易为产品请求，产品码:" + prdCode + "，渠道号：" + chnlCode + "，端口号：" + port);
			logger.info(logId + "：产品认证......，产品码:" + prdCode + "，渠道号：" + chnlCode + "，端口号：" + port);
			try{
				// 产品交易认证不通过
				if(!authtemplFactoryService.isChnlIpPrdCanDo(chnlCode, ip, prdCode)){
					logger.error(logId + "：交易处理失败：渠道 ip 产品码认证不通过，端口号：" + port + "，渠道号：" + chnlCode + "，IP:" + ip +  "，产品码：" + prdCode);
					throw new Exception("交易处理失败：渠道 ip 产品码认证不通过");
				}
			}catch(Exception e){
				logger.error(logId + "：交易处理失败：交易认证出现异常，端口号：" + port,e);
				throw new Exception("交易处理失败：交易认证出现异常",e);
			}
			
			logger.info(logId + "：产品认证通过，产品码:" + prdCode + "，渠道号：" + chnlCode + "，端口号：" + port);
			
			// 获取产品配置模板
			try {
				product = templateService.getPrdByTemplate(chnlCode, prdCode);
			} catch (Exception e) {
				logger.error(logId + "：交易处理失败：获取产品配置模板异常，端口号：" + port,e);
				throw new ThreadPoolException("交易处理失败：未找到该产品配置信息",e);
			}
			
			if(product == null){
				logger.error(logId + "：交易处理失败：产品模板未配置，端口号：" + port + "，渠道号：" + chnlCode + "，IP:" + ip +  "，产品码：" + prdCode);
				throw new Exception("交易处理失败：未找到该产品配置信息");
			}

				// 获取业务处理服务
			processer = (GeneralProcesserService)SpringContextUtil.getBean(product.getParamValueByName(ServiceBean.PROCESSER.getValue()));
			
			if(processer == null){
				logger.error(logId + "：交易处理失败：业务处理服务不能为空，端口号：" + port + "，渠道号：" + chnlCode + "，IP:" + ip +  "，产品码：" + prdCode);
				throw new Exception("交易处理失败：不能处理该产品");
			}
			
			// 设置交易类型
			busEntity.setTrnType(SOPtrnTypt.P.getValue());
		}else{

			logger.info(logId + "：本次交易为批量请求，产品码:" + prdCode + "，渠道号：" + chnlCode + "，端口号：" + port);

			// 获取渠道配置信息
			BCfgdefChnltrd  chnlEntity = SystemCfgUtil.getChnlCfgInf(header.getChnlcod(), null, null);
			if(chnlEntity == null || StringUtil.NullToString(chnlEntity.getBeannam()).equals("")){
				logger.error(logId + "：交易处理失败：批量发起时渠道配置或bean配置不能为空，端口号：" + port + "，渠道号：" + chnlCode + "，IP:" + ip +  "，产品码：" + prdCode);
				throw new Exception("交易处理失败：不能处理本次请求，批量发起时渠道配置不能为空");
			}
			// 获取业务处理服务
			processer = (GeneralProcesserService)SpringContextUtil.getBean(chnlEntity.getBeannam());
			
			if(processer == null){
				logger.error(logId + "：交易处理失败：业务处理服务不能为空，端口号：" + port + "，渠道号：" + chnlCode + "，IP:" + ip +  "，产品码：" + prdCode);
				throw new Exception("交易处理失败：不能处理本次请求，批量发起时渠道配置不能为空");
			}
		}
		
		return processer;
	}
	
	/**
	 * 拼接异步异常返回信息
	 * @param sta
	 * @param errMsg
	 * @param entity
	 */
	public static void makeErrAnsRes(String sta,String errMsg,GeneralBusEntity entity){
		entity.setProcSta(sta);
		entity.setProcReslut(errMsg);
		if(entity.getCoreHeader() != null){
			entity.getCoreHeader().setStatus(sta);
			entity.getCoreHeader().setResult(errMsg);
		}
		
		String desKey =  null;
		if(entity.getDesKeyInf() != null)
			desKey = entity.getDesKeyInf().getKey();
		
		String chnlCod = entity.getHeader().getChnlcod();
		
		try {
			JSONObject json = (JSONObject) JSON.toJSON(entity.getCoreHeader());
			JSONObject finishJson = new JSONObject();
			finishJson.put("header", json);
			byte[] bs = finishJson.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET);
			entity.setUpMsg(bs);
			entity.getHeader().setBytes(bs);
			entity.getHeader().setMsglen(bs.length);
			
			String chkCod = null;
			if(desKey !=null){
				chkCod = MD5Utils.hash(chnlCod+desKey+finishJson.toJSONString(), "MD5");
				if(chkCod != null)
					entity.getHeader().setCheckcod(chkCod);
			}
			
			entity.getHeader().setMsgtype(MsgTemplateType.ASYNSRES.getValue());
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
//		try{
//			transferService.transToAsynLResMsg(entity);
//		}catch(Exception e){
//			logger.error(logId + ":交易处理异常：拼接异常返回信息错误",e);
//		}
	}
	/**
	 * 
	 * @param logId 日志id
	 * @param from 发起方
	 * @param to 目的方
	 * @param type 操作类型
	 * @param resOrReq 请求或响应 req  res
	 * @param entity 交易实体
	 * @param bytes 存储内容
	 * @throws Exception
	 */
	public static String saveTradeMsgFile(String logId,String from,String to,String type,String resOrReq,
			GeneralBusEntity entity,byte[] bytes) throws Exception{
		StringBuffer filePath = new StringBuffer(); 
		try{
			if(bytes == null)
				throw new Exception("要存储的数据不能为空");
			String datePath = DateUtil.getDateToPath(entity.getTrnddate());
			StringBuffer fileName = new StringBuffer();
			fileName.append("log").append("_").append(resOrReq).append("_").append(logId).append("_").append(from).append("_").append(to)
			.append("_").append(type).append(".txt");
			
			if("req".equals(resOrReq)){
				filePath.append(reqMsgCachePath);
			}else if("res".equals(resOrReq)){
				filePath.append(resMsgCachePath);
			}
			String trnType = entity.getTrnType();
			String code = "";
			if(SOPtrnTypt.O.getValue().equals(trnType)){
				code = entity.getHeader().getIntrncod();
			}else if(SOPtrnTypt.P.getValue().equals(trnType)){
				code = entity.getHeader().getPrdcod();
			}else if(SOPtrnTypt.S.getValue().equals(trnType)){
				code = entity.getCoreHeader().getIntrncod();
			}
			filePath.append(File.separator).append(entity.getHeader().getChnlcod()).append(File.separator).append(trnType).append(File.separator)
			.append(code).append(File.separator).append(datePath).append(File.separator).append(fileName.toString());
			
			ByteUtil.byteArrayToFile(filePath.toString(),bytes);
		}catch(Exception e){
			logger.error("存储交易报文数据失败，日志ID：" + logId,e);
			throw e;
		}
		return filePath.toString();
	}
	
	/**
	 * 存储交易报文
	 * @param date
	 * @param port
	 * @param hOrB
	 * @param resOrReq
	 * @param bytes
	 * @throws Exception
	 */
	public static void savePortFile(Date date,String port,String hOrB,String resOrReq,byte[] bytes) throws Exception{
		StringBuffer filePath = new StringBuffer(); 
		try{
			
			// 拼接文件存储路径
			String datePath = DateUtil.getDateToPath(date);
			StringBuffer fileName = new StringBuffer();
			fileName.append(hOrB).append("_").append(port).append("_").append(resOrReq).append("_").append(DateUtil.dateToString(date, "yyyyMMddHHmmssSSS")).append(".txt");
			if("req".equals(resOrReq)){
				filePath.append(reqMsgCachePath);
			}else if("res".equals(resOrReq)){
				filePath.append(resMsgCachePath);
			}
			filePath.append(File.separator).append(port).append(File.separator).append(datePath).append(File.separator).append(fileName.toString());
			
			ByteUtil.byteArrayToFile(filePath.toString(),bytes);
		}catch(Exception e){
			logger.error("存储交互数据失败，端口号：" + port + "，路径：" + filePath.toString(),e);
			throw e;
		}
	}
	
	/**
	 * 发送日志
	 * @param logId
	 * @param from
	 * @param to
	 * @param trnType
	 * @param opType
	 * @param reqPath
	 * @param resPath
	 * @param flg
	 * @param logType
	 * @param entity
	 * @throws TradeProcException
	 */
	public static void sendLog(String logId,String from,
			 String to,String trnType,
			 String opType,String reqPath,
			 String resPath,String flg,
			 String logType,String memo,GeneralBusEntity entity) 
	{
		try{
			// 交易日志实体 
			Date date = new Date();
			TradeLogEntity logEntity = new TradeLogEntity();
			logEntity.setFnttrnjrn(entity.getFrntJrn());// 前置交易流水
			logEntity.setTrntype(trnType);// 交易类型
			logEntity.setRegdat(DateUtil.dateToString(date, "yyyyMMdd"));// 登记日志
			logEntity.setRegtim(DateUtil.dateToString(date, "HHmmssSSS"));// 登记时间
			logEntity.setTrndte(entity.getCoreHeader().getFnttrndte());// 交易日期
			logEntity.setTrntim(entity.getCoreHeader().getFnttrntim());// 交易时间
			logEntity.setLogtype(logType);// 日志类型
			// 交易日志
			if(LogType.TRADE.getValue().equals(logType)){
				logEntity.setRegid(logId); // 登记ID
				logEntity.setLaunch(from);// 发起方
				logEntity.setDestination(to);// 目的方
				logEntity.setOptype(opType);// 操作类型
				logEntity.setReqmsg(reqPath);// 请求报文
				logEntity.setResmsg(resPath);// 响应报文
				logEntity.setResflg(flg);
				logEntity.setMemo(memo);
				
			}else if(LogType.TIMEOUT.getValue().equals(logType)){
				logEntity.setRegid(GUIDGenerator.generateId()); // 登记ID
				logEntity.setRetrnto(to);// 发送目的方
			}
			
			if(logEntity == null){
				logger.error("发送日志异常，日志实体不能为空");
				throw new Exception("发送日志异常，日志实体不能为空");
			}
			if(logQueTopic == null || "".equals(logQueTopic)){
				logger.error("发送日志异常，日志队列主题不能为空");
				throw new Exception("发送日志异常，日志队列主题不能为空");
			}
			JSONObject logJson = null;
			try{
				logJson = (JSONObject)JSON.toJSON(logEntity);
			}catch(Exception e){
				logger.error("发送日志异常，把日志实体转换成json失败",e);
				throw new Exception("发送日志异常，把日志实体转换成json失败",e);

			}
			try{
				IQueueOperator queOperator = new KafkaQueOperator();
				// 队列实体
				KafkaQueEntity kfkQueEntity = new KafkaQueEntity();
				kfkQueEntity.setTopic(logQueTopic);
				kfkQueEntity.setMessage(logJson.toString());
				queOperator.offer(kfkQueEntity);
			}catch(Exception e){
				logger.error("发送日志异常，发送日志队列出现异常",e);
				throw new Exception("发送日志异常，发送日志队列出现异常",e);
			}
		}catch(Throwable e){
			logger.error("发送日志异常，发送日志队列出现异常",e);
		}
		
	}
	
	public static boolean isMsgTampered(String chnlCode,String key,byte[] downMsg,String checkCode) throws Exception {
		try{
			String str = new String(downMsg,SystemConstant.DEFAULT_CHARSET);
			StringBuffer sb = new StringBuffer(chnlCode);
			sb.append(key);
			sb.append(str);
			String c = MD5Utils.hash(sb.toString().getBytes(SystemConstant.DEFAULT_CHARSET), "MD5");
			if(checkCode.equals(c)){
				return false;
			}
		}catch(Exception e){
			logger.error("校验报文异常！！！",e);
			throw new Exception("校验报文异常！！！",e);
		}
		return true;
	}
	/**
	 * 判断是否是数据库唯一性写入错误
	 * @param errMsg
	 * @return
	 */
	public static boolean isUniqueErr(String errMsg){
		if(errMsg != null){
			if(errMsg.indexOf("-803") != -1)
				return true;
		}
		
		return false;
	}
	
	/**
	 *  文件转换json
	 * @param args
	 */
	public static JSONObject fileToJson(String path,String encode) {
		try {
			FileInputStream in = new FileInputStream(path);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int len = 0;
			byte[] buffer = new byte[1024];
			while((len = in.read(buffer)) != -1){
				bos.write(buffer, 0, len);
			}
			JSONObject json = (JSONObject)JSON.parse(new String(bos.toByteArray(),encode));
			return json;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
