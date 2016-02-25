package com.sinoway.common.pool.task;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinoway.common.constant.SystemConstant;
import com.sinoway.common.constant.MsgTransfConstant.MsgTemplateType;
import com.sinoway.common.constant.ServerConstant.ServiceBean;
import com.sinoway.common.constant.SystemConstant.InterOpType;
import com.sinoway.common.constant.SystemConstant.LogType;
import com.sinoway.common.constant.SystemConstant.ProcSide;
import com.sinoway.common.constant.SystemConstant.SOPtrnTypt;
import com.sinoway.common.constant.SystemConstant.TradeLogSta;
import com.sinoway.common.constant.SystemConstant.TradeResFlg;
import com.sinoway.common.entity.CoreMsgHeader;
import com.sinoway.common.entity.DesEntity;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.entity.GeneralMsgHeader;
import com.sinoway.common.entity.Product;
import com.sinoway.common.entity.Trade;
import com.sinoway.common.frame.SpringContextUtil;
import com.sinoway.common.service.processer.GeneralProcesserService;
import com.sinoway.common.service.template.GeneralTemplateService;
import com.sinoway.common.util.DateUtil;
import com.sinoway.common.util.GUIDGenerator;
import com.sinoway.common.util.McpLogger;
import com.sinoway.common.util.StringUtil;
import com.sinoway.common.util.SystemCfgUtil;
import com.sinoway.common.util.SystemOperateUtil;
import com.sinoway.mcp.entity.FDatPrdinfoflw;
import com.sinoway.mcp.entity.FDatPrdtrnflw;
import com.sinoway.mcp.service.FDatPrdinfoflwService;
import com.sinoway.mcp.service.FDatPrdtrnflwService;

/**
 * 通用原交易 产品响应任务
 * @author Liuzhen
 * @versioin 1.0
 * 2016-1-27
 */
public class GeneraOPtradeResTaskService<T extends String> extends AbstractRunnablePoolTask<T> {
	
	private McpLogger logger = McpLogger.getLogger(getClass());
	
	/*
	 * 注入服务
	 */
	private GeneralTemplateService templateService = null;// 交易模板服务
	private FDatPrdinfoflwService pInfoService = null;// 产品流水服务
	private FDatPrdtrnflwService  oInfoService = null;// 原交易流水服务
	
	private String logId = "";

	public GeneraOPtradeResTaskService() {
		super();
	}

	@Override
	public void excuteTask(String msg) throws Exception {

		try{
			logId = GUIDGenerator.generateId();// 日志ID

			logger.info(logId + "：接收到交易响应报文，内容:" + msg);
			
			logger.info(logId + "：接收到交易响应报文，开始解析.......");
			// 接收时间
			Date date = new Date();
			// 初始化业务处理实体
			GeneralBusEntity busEntity = new GeneralBusEntity();
			busEntity.setLogId(logId);
			GeneralMsgHeader msgHeader = new GeneralMsgHeader();
			busEntity.setHeader(msgHeader);
			busEntity.setTrnddate(date);
			// 设置交易日期
			busEntity.setTrnDate(DateUtil.dateToString(date, "yyyyMMdd"));
			// 设置交易时间
			busEntity.setTrnTime(DateUtil.dateToString(date, "HHmmssSSS"));
			CoreMsgHeader coreHeader = new CoreMsgHeader();
			JSONObject msgJson,cHeader = null;
			JSONArray bodys = null;
			try{
				 logger.info(logId + "：开始初步解析核心JSon消息......");

				 // 核心响应报文转换成json
				 msgJson = (JSONObject) JSON.parse(msg);
				
				 // 获取报文头
				 cHeader = msgJson.getJSONObject("header");
				
				 // 获取报文体
				 bodys = msgJson.getJSONArray("bodys");
				 
				 logger.info(logId + "：初步解析核心JSon消息成功！！！");
			}catch(Exception e){
				logger.error(logId + "：交易响应处理失败:初步解析JSON失败，消息内容：" + msg);
				throw e;
			}
			
			try{
				logger.info(logId + "：开始转换核心报文头......");
				 // 报文头转换
				 coreHeader = JSON.toJavaObject(cHeader, CoreMsgHeader.class);
				 busEntity.setCoreHeader(coreHeader);
				 logger.info(logId + "：核心报文头转换成功！！！");
			}catch(Exception e){
				logger.error(logId + "：交易响应处理失败:报文头转换失败，消息内容：" + msg);
				e.printStackTrace();
				throw e;
			}
			
			
			 String  prdCode = coreHeader.getPrdcod();// 产品码
			 String  tradeCode = coreHeader.getIntrncod();//内部交易码
			 String  flwCode = coreHeader.getFnttrnjrn();// 前置流水号
			 String  chnlCode = coreHeader.getChnlcod();//渠道号
			 
			 busEntity.setFrntJrn(flwCode);
			 byte[] bs = msgJson.toString().getBytes(SystemConstant.DEFAULT_CHARSET);
			 busEntity.setCoreMsg(bs);
			 
			// 核心报文校验
			 try{
				 checkCoreHeader(coreHeader);
			 }catch(Exception e){
				logger.error(logId + "：交易响应处理失败：" + e.getMessage() + "，流水号：" + flwCode);
				e.printStackTrace();
				throw e;
			 }
			
			 // 核心响应日志ID
			 String coreLogId = GUIDGenerator.generateId();
			 String coreResMsgPath = "";
			 
			// 原交易处理
			if(tradeCode != null && !"".equals(tradeCode)){
				// 获取原交易外部原交易码
				String oTradeCode = SystemCfgUtil.getOuttrByInTr(tradeCode, SOPtrnTypt.O);
				if(oTradeCode == null || "".equals(oTradeCode))
					oTradeCode = tradeCode;
				msgHeader.setChnlcod(chnlCode);
				msgHeader.setIntrncod(tradeCode);
				msgHeader.setMsgtype(MsgTemplateType.RES.getValue());
				msgHeader.setIsbatch("0");
				msgHeader.setOuttrncod(oTradeCode);
				msgHeader.setPrdcod(SystemConstant.NOT_PRDCODE);
				busEntity.setTrnType(SOPtrnTypt.O.getValue());
				FDatPrdtrnflw otrdFlw = new FDatPrdtrnflw();
				otrdFlw.setFnttrnjrn(flwCode);
				
				busEntity.setOtrdflw(otrdFlw);
				// 存储核心响应报文
				 try{
					 coreResMsgPath = SystemOperateUtil.saveTradeMsgFile(coreLogId, ProcSide.CORE.getValue(), 
							 ProcSide.CLIENT.getValue(), InterOpType.RESRES.getValue(), "res", busEntity, bs);
					 otrdFlw.setRespmsg(coreResMsgPath);
				 }catch(Exception e){
					 logger.error(logId + "存储核心响应报文异常，流水号：" + flwCode,e);
					 e.printStackTrace();
				 }
				
				 // 发送核心响应日志
				 try{
					 SystemOperateUtil.sendLog(coreLogId, ProcSide.CORE.getValue(),
								ProcSide.LOCAL.getValue(),  busEntity.getTrnType(), 
								InterOpType.RESRES.getValue(), "", coreResMsgPath, TradeLogSta.S.getValue(),
								LogType.TRADE.getValue(), "", busEntity);
				 }catch(Exception e){
					 logger.error(logId + "发送核心响应日志异常，流水号：" + flwCode,e);
					 e.printStackTrace();
				 }
				
				// 获取交易配置模板
				Trade trade = templateService.getTradeByTemplate(chnlCode, tradeCode);
				
				if(trade == null){
					otrdFlw.setRespflg(TradeResFlg.OTHERR.getValue());
					// 更新流水
					oInfoService.update(otrdFlw);
					
					// 发送失败日志
					SystemOperateUtil.sendLog(GUIDGenerator.generateId(), ProcSide.LOCAL.getValue(),
							ProcSide.CLIENT.getValue(),  busEntity.getTrnType(), 
							InterOpType.RESRES.getValue(), coreResMsgPath, "", TradeLogSta.F.getValue(),
							LogType.TRADE.getValue(), "未找到交易配置模板", busEntity);
					logger.error(logId + "：交易响应处理失败：未找到交易配置模板，交易码：" + tradeCode + "，流水号：" + flwCode);
					throw new Exception("交易响应处理失败：未找到交易配置模板，交易码：" + tradeCode + "，流水号：" + flwCode);
				}
				
				// 获取业务处理Bean
				GeneralProcesserService processer = null; 
				
				try{
					// 查找业务处理bean
					processer = (GeneralProcesserService)SpringContextUtil.getBean(trade.getParamValueByName(ServiceBean.PROCESSER.getValue()));
				}catch(Exception e){
					logger.error(logId + "：交易响应处理失败:查找相关业务处理bean异常",e);

					otrdFlw.setRespflg(TradeResFlg.OTHERR.getValue());
					// 更新流水
					oInfoService.update(otrdFlw);
					
					// 发送失败日志
					SystemOperateUtil.sendLog(GUIDGenerator.generateId(), ProcSide.LOCAL.getValue(),
							ProcSide.CLIENT.getValue(),  busEntity.getTrnType(), 
							InterOpType.RESRES.getValue(), coreResMsgPath, "", TradeLogSta.F.getValue(),
							LogType.TRADE.getValue(), "获取业务处理bean失败", busEntity);
					e.printStackTrace();
				}
				
				if(processer == null){
					otrdFlw.setRespflg(TradeResFlg.OTHERR.getValue());
					// 更新流水
					oInfoService.update(otrdFlw);
					
					// 发送失败日志
					SystemOperateUtil.sendLog(GUIDGenerator.generateId(), ProcSide.LOCAL.getValue(),
							ProcSide.CLIENT.getValue(),  busEntity.getTrnType(), 
							InterOpType.RESRES.getValue(), coreResMsgPath, "", TradeLogSta.F.getValue(),
							LogType.TRADE.getValue(), "未找到相关业务处理bean", busEntity);
					
					logger.error(logId + "：交易响应处理失败:未找到相关业务处理bean");
					
					throw new Exception("交易响应处理失败:未找到相关业务处理bean,交易码：" + tradeCode + "，流水号：" + flwCode);
				}
				try{
					//获取密钥信息
					DesEntity keyEntity = SystemCfgUtil.getDesKeyInfByChnl(chnlCode);
					
					if(keyEntity == null || StringUtil.NullToString(keyEntity.getKey()).equals("")){
						otrdFlw.setRespflg(TradeResFlg.OTHERR.getValue());
						// 更新流水
						oInfoService.update(otrdFlw);
						
						// 发送失败日志
						SystemOperateUtil.sendLog(GUIDGenerator.generateId(), ProcSide.LOCAL.getValue(),
								ProcSide.CLIENT.getValue(),  busEntity.getTrnType(), 
								InterOpType.RESRES.getValue(), coreResMsgPath, "", TradeLogSta.F.getValue(),
								LogType.TRADE.getValue(), "未找到相关渠道的密钥信息", busEntity);
						
						logger.error(logId + "：交易响应处理失败:未找到相关渠道的密钥信息");
						
						throw new Exception("交易响应处理失败:未找到相关渠道的密钥信息,交易码：" + tradeCode + "，流水号：" + flwCode);
					}
					busEntity.setDesKeyInf(keyEntity);
					
					// 调用业务处理
					processer.excuteToClient(busEntity);
				}catch(Exception e){
					logger.error(logId + "：交易响应处理失败:业务处理异常，流水号：" + flwCode,e);
					e.printStackTrace();
				}
				
			// 产品处理
			}else if(prdCode != null && !"".equals(prdCode)){
				msgHeader.setChnlcod(chnlCode);
				msgHeader.setIntrncod(SystemConstant.NOT_TRCODE);
				msgHeader.setMsgtype(MsgTemplateType.RES.getValue());
				msgHeader.setIsbatch("0");
				msgHeader.setOuttrncod(SystemConstant.NOT_TRCODE);
				msgHeader.setPrdcod(prdCode);
				busEntity.setTrnType(SOPtrnTypt.P.getValue());
				FDatPrdinfoflw prdinfoflw = new FDatPrdinfoflw();
				prdinfoflw.setFnttrnjrn(flwCode);
				busEntity.setPrdflw(prdinfoflw);
				
				// 存储核心响应报文
				 try{
					 coreResMsgPath = SystemOperateUtil.saveTradeMsgFile(coreLogId, ProcSide.CORE.getValue(), 
							 ProcSide.CLIENT.getValue(), InterOpType.RESRES.getValue(), "res", busEntity, bs);
					
					 prdinfoflw.setRespmsg(coreResMsgPath);
				 }catch(Exception e){
					 logger.error(logId + "存储核心响应报文异常，流水号：" + flwCode,e);
					 e.printStackTrace();
				 }
				 
				 // 发送核心响应日志
				 try{
					 SystemOperateUtil.sendLog(coreLogId, ProcSide.CORE.getValue(),
								ProcSide.LOCAL.getValue(),  busEntity.getTrnType(), 
								InterOpType.RESRES.getValue(), "", coreResMsgPath, TradeLogSta.S.getValue(),
								LogType.TRADE.getValue(), "", busEntity);
				 }catch(Exception e){
					 logger.error(logId + "发送核心响应日志异常，流水号：" + flwCode,e);
					 e.printStackTrace();
				 }
				
				// 获取产品配置模板
				Product product = templateService.getPrdByTemplate(chnlCode, prdCode);
				
				if(product == null){
					prdinfoflw.setRespflg(TradeResFlg.OTHERR.getValue());
					// 更新流水
					pInfoService.update(prdinfoflw);
					
					// 发送失败日志
					SystemOperateUtil.sendLog(GUIDGenerator.generateId(), ProcSide.LOCAL.getValue(),
							ProcSide.CLIENT.getValue(),  busEntity.getTrnType(), 
							InterOpType.RESRES.getValue(), coreResMsgPath, "", TradeLogSta.F.getValue(),
							LogType.TRADE.getValue(), "未找到交易配置模板", busEntity);
					logger.error(logId + "：产品响应处理失败：未找到交易配置模板，产品码：" + prdCode + "，流水号：" + flwCode);
					throw new Exception("产品响应处理失败：未找到交易配置模板，产品码：" + prdCode + "，流水号：" + flwCode);
				}

				// 获取业务处理Bean
				GeneralProcesserService processer = null;
				
				try{
					// 查找业务处理bean
					processer = (GeneralProcesserService)SpringContextUtil.getBean(product.getParamValueByName(ServiceBean.PROCESSER.getValue()));
				}catch(Exception e){
					
					prdinfoflw.setRespflg(TradeResFlg.OTHERR.getValue());
					// 更新流水
					pInfoService.update(prdinfoflw);
					
					// 发送失败日志
					SystemOperateUtil.sendLog(GUIDGenerator.generateId(), ProcSide.LOCAL.getValue(),
							ProcSide.CLIENT.getValue(),  busEntity.getTrnType(), 
							InterOpType.RESRES.getValue(), coreResMsgPath, "", TradeLogSta.F.getValue(),
							LogType.TRADE.getValue(), "获取相关业务处理bean失败", busEntity);
					logger.error(logId + "：产品响应处理失败：获取相关业务处理bean失败，产品码：" + prdCode + "，流水号：" + flwCode);
					throw new Exception("产品响应处理失败：获取相关业务处理bean失败，产品码：" + prdCode + "，流水号：" + flwCode);
				}
				
				if(processer == null){
					prdinfoflw.setRespflg(TradeResFlg.OTHERR.getValue());
					// 更新流水
					pInfoService.update(prdinfoflw);
					
					// 发送失败日志
					SystemOperateUtil.sendLog(GUIDGenerator.generateId(), ProcSide.LOCAL.getValue(),
							ProcSide.CLIENT.getValue(),  busEntity.getTrnType(), 
							InterOpType.RESRES.getValue(), coreResMsgPath, "", TradeLogSta.F.getValue(),
							LogType.TRADE.getValue(), "未找到相关业务处理bean", busEntity);
					logger.error(logId + "：产品响应处理失败：未找到相关业务处理bean，产品码：" + prdCode + "，流水号：" + flwCode);
					throw new Exception("产品响应处理失败：未找到相关业务处理bean，产品码：" + prdCode + "，流水号：" + flwCode);
				}
				
				try{
					
					//获取密钥信息
					DesEntity keyEntity = SystemCfgUtil.getDesKeyInfByChnl(chnlCode);
					
					if(keyEntity == null || StringUtil.NullToString(keyEntity.getKey()).equals("")){
						prdinfoflw.setRespflg(TradeResFlg.OTHERR.getValue());
						// 更新流水
						pInfoService.update(prdinfoflw);
						
						// 发送失败日志
						SystemOperateUtil.sendLog(GUIDGenerator.generateId(), ProcSide.LOCAL.getValue(),
								ProcSide.CLIENT.getValue(),  busEntity.getTrnType(), 
								InterOpType.RESRES.getValue(), coreResMsgPath, "", TradeLogSta.F.getValue(),
								LogType.TRADE.getValue(), "未找到相关渠道的密钥信息", busEntity);
						logger.error(logId + "：产品响应处理失败：未找到相关渠道的密钥信息，产品码：" + prdCode + "，流水号：" + flwCode);
						throw new Exception("产品响应处理失败：未找到相关渠道的密钥信息，产品码：" + prdCode + "，流水号：" + flwCode);
					}
					busEntity.setDesKeyInf(keyEntity);
					// 调用业务处理
					processer.excuteToClient(busEntity);
				}catch(Exception e){
					logger.error(logId + "：产品响应处理失败:业务处理异常，" +e.getMessage()+ "流水号：" + flwCode,e);
					e.printStackTrace();
				}
				
			}else{
				logger.error(logId + "：产品响应处理失败:不能解析产品号或交易码，消息内容：" + msg);
			}
			
		}catch(Exception e){
			logger.error(logId + "：交易响应处理失败：" +  e);
			e.printStackTrace();
		}
	
	}
	/**
	 * 检测消息头合法性
	 * @param coreHeader
	 * @throws Exception
	 */
	private void checkCoreHeader(CoreMsgHeader coreHeader) throws Exception{
		if((coreHeader.getPrdcod() == null || "".equals(coreHeader.getPrdcod())) && (coreHeader.getIntrncod() == null || "".equals(coreHeader.getIntrncod()))){
			logger.error(logId + "：交易响应处理失败:产品码与交易码不能同时为空");
			throw new Exception("交易响应处理失败:产品码与交易码不能同时为空");
		}

		if(coreHeader.getFnttrnjrn() == null || "".equals(coreHeader.getFnttrnjrn())){
			logger.error(logId + "：交易响应处理失败:前置流水号不能为空");
			throw new Exception("交易响应处理失败:前置流水号不能为空");
		}
		if( coreHeader.getMasttrndte() == null || "".equals(coreHeader.getMasttrndte())){
			logger.error(logId + "：交易响应处理失败：核心交易日期不能为空");
			throw new Exception("交易响应处理失败:核心交易日期不能为空");
		}
		if(coreHeader.getMastjrn() == null || "".equals(coreHeader.getMastjrn())){
			logger.error(logId + "：交易响应处理失败：核心交易流水不能为空");
			throw new Exception("交易响应处理失败:核心交易流水不能为空");
		}
		if(coreHeader.getMasttrntim() == null || "".equals(coreHeader.getMasttrntim())){
			logger.error(logId + "：交易响应处理失败：核心交易时间不能为空");
			throw new Exception("交易响应处理失败:核心交易时间不能为空");
		}
		if(coreHeader.getChnlcod() == null || "".equals(coreHeader.getChnlcod())){
			logger.error(logId + "：交易响应处理失败：渠道号不能为空");
			throw new Exception("交易响应处理失败:渠道号不能为空");
		}
	}


	/*
	 *   GETTER  AND  SETTER
	 */
	
	public GeneralTemplateService getTemplateService() {
		return templateService;
	}

	public void setTemplateService(GeneralTemplateService templateService) {
		this.templateService = templateService;
	}

	public FDatPrdinfoflwService getpInfoService() {
		return pInfoService;
	}

	public void setpInfoService(FDatPrdinfoflwService pInfoService) {
		this.pInfoService = pInfoService;
	}

	public FDatPrdtrnflwService getoInfoService() {
		return oInfoService;
	}

	public void setoInfoService(FDatPrdtrnflwService oInfoService) {
		this.oInfoService = oInfoService;
	}
}
