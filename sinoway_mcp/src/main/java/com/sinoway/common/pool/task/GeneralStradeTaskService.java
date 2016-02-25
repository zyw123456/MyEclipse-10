package com.sinoway.common.pool.task;

import java.util.Date;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinoway.base.entity.BCfgdefQueue;
import com.sinoway.common.constant.SystemConstant;
import com.sinoway.common.constant.SystemConstant.BusProcSta;
import com.sinoway.common.constant.SystemConstant.InterOpType;
import com.sinoway.common.constant.SystemConstant.LogType;
import com.sinoway.common.constant.SystemConstant.ProcSide;
import com.sinoway.common.constant.SystemConstant.QueType;
import com.sinoway.common.constant.SystemConstant.SOPtrnTypt;
import com.sinoway.common.constant.SystemConstant.TradeLogSta;
import com.sinoway.common.entity.CoreMsgHeader;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.entity.GeneralMsgHeader;
import com.sinoway.common.service.processer.GeneralSTradeProcesserService;
import com.sinoway.common.util.DateUtil;
import com.sinoway.common.util.GUIDGenerator;
import com.sinoway.common.util.JRNGenerator;
import com.sinoway.common.util.StringUtil;
import com.sinoway.common.util.SystemCfgUtil;
import com.sinoway.common.util.SystemOperateUtil;
import com.sinoway.mcp.queue.entity.KafkaQueEntity;
import com.sinoway.mcp.queue.service.IQueueOperator;
import com.sinoway.mcp.queue.service.KafkaQueOperator;

public class GeneralStradeTaskService<T extends String> extends AbstractRunnablePoolTask<T> {
	
	/*
	 * 注入服务
	 */
	private GeneralSTradeProcesserService processer = null;
	private String logId = "";// 日志ID
	private String coreMsgPath = "";
	
	public GeneralStradeTaskService() {
		super();
	}

	@Override
	public void excuteTask(String msg) throws Exception {
		try{
			String logId = GUIDGenerator.generateId();// 日志ID
			logger.info(logId + "：接收到子交易请求......,线程ID:" + Thread.currentThread().getId() + ",内容:" + msg);
			// 接收时间
			Date date = new Date();
			// 初始化业务处理实体
			GeneralBusEntity busEntity = new GeneralBusEntity();
			GeneralMsgHeader msgHeader = new GeneralMsgHeader();
			busEntity.setHeader(msgHeader);
			busEntity.setTrnddate(date);
			busEntity.setLogId(logId);
			// 设置交易日期
			busEntity.setTrnDate(DateUtil.dateToString(date, "yyyyMMdd"));
			// 设置交易时间
			busEntity.setTrnTime(DateUtil.dateToString(date, "HHmmssSSS"));
			String flwCode = null;
			CoreMsgHeader coreHeader = new CoreMsgHeader();
			JSONObject msgJson,cHeader = null;
			JSONArray bodys = null;
			try{
				logger.info(logId + "：开始初步解析核心JSon消息......流水号：" + flwCode);

				// 核心响应报文转换成json
				 msgJson = (JSONObject) JSON.parse(msg);
				
				// 获取报文头
				 cHeader = msgJson.getJSONObject("header");
				
				// 获取报文体
				 bodys = msgJson.getJSONArray("bodys");
				 
				 String tmoutFlg = "".equals(cHeader.getString("tmoutFlg")) ? "0" : cHeader.getString("tmoutFlg");
				 
				 busEntity.setTmoutFlg(tmoutFlg);
				 
				 logger.info(logId + "：初步解析核心JSon消息成功！！！流水号：" + flwCode);
			}catch(Exception e){
				e.printStackTrace();
				logger.error(logId + "：子交易请求处理失败:初步解析JSON失败，消息内容：" + msg,e);
				throw e;
			}
			
			try{
				logger.info(logId + "：开始转换核心报文头......流水号：" + flwCode);
				 // 报文头转换
				 coreHeader = JSON.toJavaObject(cHeader, CoreMsgHeader.class);
				 busEntity.setCoreHeader(coreHeader);
				 logger.info(logId + "：核心报文头转换成功！！！流水号：" + flwCode);
			}catch(Exception e){
				logger.error(logId + "：子交易请求处理失败:报文头转换失败，消息内容：" + msg,e);
				e.printStackTrace();
				throw e;
			}
			
			// 核心报文校验
			try{
				logger.info(logId + "：开始校验报文头信息......");
				checkCoreHeader(coreHeader);
				logger.info(logId + "：报文头信息校验通过......");
			 }catch(Exception e){
				logger.error(logId + "：子交易请求处理处理失败：核心报文头校验不通过",e);
				e.printStackTrace();
				// TODO存储异常报文
				throw e;
			 }
			 
			 // 存储核心请求报文 --->  核心请求报文路径  coreMsg  TODO
			
			 String tradeCode = coreHeader.getIntrncod();//内部交易码
			 flwCode = JRNGenerator.generateJrn(SystemConstant.SYS_CODE, SOPtrnTypt.S.getValue(), tradeCode);// 前置流水号
			 coreHeader.setFnttrnjrn(flwCode);
			 coreHeader.setFnttrndte(busEntity.getTrnDate());
			 coreHeader.setFnttrntim(busEntity.getTrnTime());
			 busEntity.setFrntJrn(flwCode);
			 busEntity.setCoreMsg(msgJson.toString().getBytes(SystemConstant.DEFAULT_CHARSET));
			 busEntity.setCoreLogId(logId);
			 busEntity.setTrnType(SOPtrnTypt.S.getValue());
			 busEntity.getHeader().setChnlcod(coreHeader.getChnlcod());
			 busEntity.getHeader().setIntrncod(coreHeader.getIntrncod());

			 
			 try{
				 coreMsgPath = SystemOperateUtil.saveTradeMsgFile(logId, ProcSide.CORE.getValue(), ProcSide.LOCAL.getValue(), InterOpType.REQSEND.getValue(), "req", busEntity, busEntity.getCoreMsg());
				 // 设置核心请求报文路径
				 busEntity.setCoreReqMsg(StringUtil.NullToString(coreMsgPath));
			}catch(Exception e){
				logger.error(logId  + ": 子交易处理异常，存储核心请求报文失败......");
				
				e.printStackTrace();
			}
			if(processer == null){
				// 给核心响应 --> 生成响应报文  {header:{status:,result}}--->存储请求报文路径  存储响应报文 
				// 记录log ---> 核心---前置交互log  请求报文  响应报文
				sendErrMsgToCore(logId, busEntity, coreHeader, "未找到相关业务处理bean");
				logger.error(logId + "：子交易请求处理失败:未找到相关业务处理bean");
				
				throw new Exception("子交易请求处理失败:未找到相关业务处理bean,交易码：" + tradeCode + "，流水号：" + flwCode);
			}
			try{
				logger.info(logId + "：调用子交易业务处理，流水号：" + flwCode);
				// 调用业务处理
				
				processer.busLaunch(busEntity);
			}catch(Exception e){
				logger.error(logId + "：子交易请求处理失败:业务处理异常，流水号：" + flwCode,e);
				e.printStackTrace();
			}
		}catch(Exception e){
			logger.error(logId + "：子交易请求处理失败:初步解析JSON失败，消息内容：" + msg);
			e.printStackTrace();
		}
		
	}
	
	private void sendErrMsgToCore(String logId,GeneralBusEntity busEntity,CoreMsgHeader coreHeader,String errorMsg) throws Exception{
		String filePath =  "";
		try {
			SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(), errorMsg, busEntity);
			filePath = SystemOperateUtil.saveTradeMsgFile(logId, ProcSide.CORE.getValue(), ProcSide.LOCAL.getValue(), InterOpType.REQSEND.getValue(), "res", busEntity, busEntity.getUpMsg());
			IQueueOperator queOperator = new KafkaQueOperator();
			BCfgdefQueue queInfoByTrCode = SystemCfgUtil.getQueInfoByTrCode(coreHeader.getChnlcod(),coreHeader.getChnlcod(), SOPtrnTypt.S.getValue(), QueType.RES.getValue());
			//拼接异常信息
			// 队列实体
			String coreMsg = new String(busEntity.getUpMsg(),SystemConstant.DEFAULT_CHARSET);
			KafkaQueEntity kfkQueEntity = new KafkaQueEntity();
			kfkQueEntity.setTopic(queInfoByTrCode.getQuetopic());
			kfkQueEntity.setMessage(coreMsg);
			queOperator.offer(kfkQueEntity);
			// 给核心响应 --> 生成响应报文  {header:{status:,result}}--->存储请求报文路径  存储响应报文 
			// 记录log ---> 核心---前置交互log  请求报文  响应报文
			SystemOperateUtil.sendLog(logId, ProcSide.CORE.getValue(), ProcSide.LOCAL.getValue(), SOPtrnTypt.S.getValue(), InterOpType.REQSEND.getValue(),coreMsgPath, filePath, TradeLogSta.F.getValue(), LogType.TRADE.getValue(), errorMsg, busEntity);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(logId+"发送异常信息到核心异常："+e.getMessage());
			SystemOperateUtil.sendLog(logId, ProcSide.CORE.getValue(), ProcSide.LOCAL.getValue(), SOPtrnTypt.S.getValue(), InterOpType.REQSEND.getValue(),coreMsgPath, filePath, TradeLogSta.F.getValue(), LogType.TRADE.getValue(), e.getMessage(), busEntity);

			throw e;
		}
		
	}
	

	/**
	 * 检查核心报文头
	 * @param coreHeader
	 * @throws Exception
	 */
	private void checkCoreHeader(CoreMsgHeader coreHeader) throws Exception{

		if(coreHeader.getIntrncod() == null || "".equals(coreHeader.getIntrncod())){
			logger.error(logId + "：子交易请求处理失败:交易码不能为空");
			throw new Exception("子交易请求处理失败:交易码不能为空");
		}
		if( coreHeader.getMasttrndte() == null || "".equals(coreHeader.getMasttrndte())){
			logger.error(logId + "：子交易请求处理失败：核心交易日期不能为空");
			throw new Exception("子交易请求失败:核心交易日期不能为空");
		}
		if(coreHeader.getMastjrn() == null || "".equals(coreHeader.getMastjrn())){
			logger.error(logId + "：子交易请求失败：核心交易流水不能为空");
			throw new Exception("子交易请求失败:核心交易流水不能为空");
		}
		if(coreHeader.getMasttrntim() == null || "".equals(coreHeader.getMasttrntim())){
			logger.error(logId + "：子交易请求失败：核心交易时间不能为空");
			throw new Exception("子交易请求失败:核心交易时间不能为空");
		}
		if(coreHeader.getChnlcod() == null || "".equals(coreHeader.getChnlcod())){
			logger.error(logId + "：子交易请求失败：渠道号不能为空");
			throw new Exception("子交易请求失败:渠道号不能为空");
		}
	}

	/*
	 *   GETTER  AND  SETTER
	 */
	public GeneralSTradeProcesserService getProcesser() {
		return processer;
	}

	public void setProcesser(GeneralSTradeProcesserService processer) {
		this.processer = processer;
	}
	
}
