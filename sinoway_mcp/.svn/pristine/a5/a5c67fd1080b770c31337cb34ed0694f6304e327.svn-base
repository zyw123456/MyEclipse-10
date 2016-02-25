package com.sinoway.common.service.processer;

import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import com.alibaba.fastjson.JSONObject;
import com.sinoway.base.entity.BCfgdefChnltrd;
import com.sinoway.base.entity.BCfgdefFnttrninfo;
import com.sinoway.base.entity.BCfgdefQueue;
import com.sinoway.common.constant.ServerConstant;
import com.sinoway.common.constant.ServerConstant.ResSta;
import com.sinoway.common.constant.SystemConstant;
import com.sinoway.common.constant.SystemConstant.BusProcSta;
import com.sinoway.common.constant.SystemConstant.InterMode;
import com.sinoway.common.constant.SystemConstant.InterOpType;
import com.sinoway.common.constant.SystemConstant.LogType;
import com.sinoway.common.constant.SystemConstant.ProcSide;
import com.sinoway.common.constant.SystemConstant.QueType;
import com.sinoway.common.constant.SystemConstant.SOPtrnTypt;
import com.sinoway.common.constant.SystemConstant.STradeResFlg;
import com.sinoway.common.constant.SystemConstant.TradeLogSta;
import com.sinoway.common.entity.CoreMsgHeader;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.entity.Trade;
import com.sinoway.common.exception.STradeProcessException;
import com.sinoway.common.frame.SpringContextUtil;
import com.sinoway.common.service.server.GeneralSTradeProcService;
import com.sinoway.common.service.template.GeneralTemplateService;
import com.sinoway.common.service.transfer.GeneralTransferService;
import com.sinoway.common.util.DateUtil;
import com.sinoway.common.util.GUIDGenerator;
import com.sinoway.common.util.McpLogger;
import com.sinoway.common.util.StringUtil;
import com.sinoway.common.util.SystemCfgUtil;
import com.sinoway.common.util.SystemOperateUtil;
import com.sinoway.mcp.entity.FDatMetatrnflw;
import com.sinoway.mcp.entity.FDatOvertrnlog;
import com.sinoway.mcp.queue.entity.KafkaQueEntity;
import com.sinoway.mcp.queue.service.IQueueOperator;
import com.sinoway.mcp.service.FDatMetatrnflwService;
import com.sinoway.mcp.service.FDatOvertrnlogService;

/**
 * 通用子交易业务处理类
 * @author Liuzhen
 * @version 1.0
 * 2015-11-25
 */
public class GeneralSTradeProcess implements GeneralSTradeProcesserService{
	private McpLogger logger = McpLogger.getLogger(getClass());

	/*
	 * 注入服务
	 */
	private FDatMetatrnflwService sFlwService = null;// 子交易流水服务
	private GeneralTransferService transferService=null;//报文转换服务
	private IQueueOperator queOperator = null; //队列操作服务
	private GeneralTemplateService templateService = null;// 交易模板服务
	private FDatOvertrnlogService tmoutService = null;
	
	/*
	 * 类变量
	 */
	private String logId = "";
	private String mTocLogid = "";  //日志id
	private String reqSupLogPath = ""; //子交易请求日志路径
	private String supResLogPath = ""; // 子交易返回日志路径
	private boolean saveFlg = true;
	@Override
	public void busLaunch(GeneralBusEntity entity)
			throws STradeProcessException {
		
		logId = entity.getLogId();// 日志ID
		
		CoreMsgHeader coreHeader = entity.getCoreHeader();// 核心报文头实体
		String chnlCod =coreHeader.getChnlcod();//渠道号
		String tradeCode = coreHeader.getIntrncod();// 内部交易码
		String flwCode = entity.getFrntJrn();
		try{
			 logger.info(logId + "：子交易处理开始......流水号：" + flwCode);
			 
			 //预处理
			 if(!preparProc(entity, flwCode, chnlCod, tradeCode))
				 return;
			 
			 // 获取外部 交易码
			 String oTradeCode = SystemCfgUtil.getOuttrByInTr(tradeCode, SOPtrnTypt.S);
			 // 默认内外子交易码相等
			 if("".equals(StringUtil.NullToString(oTradeCode)))
				oTradeCode = tradeCode;
			 
			// 获取渠道交易配置信息
			BCfgdefChnltrd chnlTrdEntity = SystemCfgUtil.getChnlCfgInf(chnlCod,tradeCode, SOPtrnTypt.S.getValue());
			// 获取交易配置信息
			BCfgdefFnttrninfo tradeEntity = SystemCfgUtil.getTradeCfgByCode(tradeCode);
			
			if (chnlTrdEntity == null) {
				logger.error(logId + ":发起子交易异常：渠道交易配置不存在");
				throw new STradeProcessException("发起子交易异常：渠道交易配置不存在");
			}

			if (tradeEntity == null) {
				logger.error(logId + ":发起子交易异常：交易配置不存在");
				throw new STradeProcessException("交易配置不存在");
			}
			
			// 计算超时时间
			int tmout = 0;// 超时时间 
			int tmnum = 3;// 超时次数
			String stmnum = "".equals(StringUtil.NullToString(chnlTrdEntity.getDelovernum())) ? tradeEntity.getDelovernum() : chnlTrdEntity.getDelovernum();
			String stmOut = "".equals(StringUtil.NullToString(chnlTrdEntity.getDelover())) ? tradeEntity.getDelover() : chnlTrdEntity.getDelover();
			try {
				tmout = SystemOperateUtil.getTrdTmOut(stmOut);
				tmnum = SystemOperateUtil.getTrdTmOutNum(stmnum);
			} catch (Exception e) {
				logger.error(logId + ":发起子交易异常：超时时长或超时次数必须是数字");
				throw new STradeProcessException("发起子交易异常：超时时长或超时次数必须是数字");
			}
			
			// 生成流水入库  
			FDatMetatrnflw strdflw = new FDatMetatrnflw();
			
			// 异步交互时计算超时时间
			Date date = new Date();
			
			strdflw.setTmout(date.getTime() + tmout + "");
			
			try {
				entity.setStrdflw(strdflw);
				BeanUtils.copyProperties(strdflw, coreHeader);
				strdflw.setReqmsg(entity.getCoreReqMsg()); // 核心请求报文路径
				strdflw.setOuttrncod(oTradeCode);// 外部交易编码
				strdflw.setRespflg(STradeResFlg.NORES.getValue());// 响应结果标示
			} catch (Exception e) {
				logger.error(logId + "：子交易请求处理处理失败：生成子交易流水实体失败，交易码：" + tradeCode + "，流水号：" + flwCode,e);
				throw new STradeProcessException("生成子交易流水实体失败",e);
			}
			
			 try{
				 // 流水入库
				 if(saveFlg){
					 sFlwService.save(strdflw);
				 }
//				 else{
//					 // 设置 状态 条件
//					 entity.getStrdflw().setRespflg(entity.getStrdflw().getRespflg());
//					 // 更新超时时间
//					 if(1!=sFlwService.updateTmoutByFlwAndSta(entity.getStrdflw()));
//					 	return;
//				 }
					 
				 logger.info(logId + "：子交易处理：存储子交易流水成功，流水号：" + flwCode);
			 }catch(Exception e){
				 logger.info(logId + "：子交易处理：存储子交易流水失败，流水号：" + flwCode);
				 if(SystemOperateUtil.isUniqueErr(e.getMessage())){
					//预处理
					 if(!preparProc(entity, flwCode, chnlCod, tradeCode))
						 return;
				 }else{
					 logger.error(logId + "：子交易请求处理处理失败：存储交易流水失败，交易码：" + tradeCode + "，流水号：" + flwCode,e);
					 throw  new STradeProcessException("存储流水失败,数据库异常");
				 }
			 }
			 // 设置 状态 条件
			 entity.getStrdflw().setRespflg(entity.getStrdflw().getRespflg());
			 
			// 获取交易配置模板
			Trade trade = null;
			
			try{
				trade = templateService.getTradeByTemplate(chnlCod, tradeCode);
			}catch(Exception e){
				logger.error(logId + "：子交易请求处理失败:获取交易配置模板错误，流水号：" + flwCode,e);
				entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
				if(1==sFlwService.updateByFlwAndSta(entity.getStrdflw()))
					throw new STradeProcessException(logId + "：子交易请求处理失败:获取交易配置模板错误，流水号：" + flwCode);
				return;
				 
			}
			
			if(trade == null){
				logger.error(logId + "：子交易请求处理失败:交易配置模板不能为空，流水号：" + flwCode);
				entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
				if(1==sFlwService.updateByFlwAndSta(entity.getStrdflw()))
					throw new STradeProcessException("交易配置模板不能为空");
				return;
			}
			
			logger.info(logId + "：获取子交易处理服务，流水号：" + flwCode);
			GeneralSTradeProcService procService = null; 
			
			try{
				String a = trade.getParamValueByName(ServerConstant.ServiceBean.STRADEPROCESSER.getValue());
				procService = (GeneralSTradeProcService)SpringContextUtil.getBean(a);
			
			}catch(Exception e){
				logger.error(logId + ":子交易处理异常，获取子交易业务处理bean异常,流水号：" + flwCode,e);
				entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
				if(1==sFlwService.updateByFlwAndSta(entity.getStrdflw()))
					throw new STradeProcessException("获取子交易业务处理bean异常");
				return;
			}
			
			if(procService == null){
				logger.error(logId + "：子交易请求处理失败:业务处理服务不能为空，流水号：" + flwCode);
				entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
				if(1==sFlwService.updateByFlwAndSta(entity.getStrdflw()))
					throw new STradeProcessException("业务处理服务不能为空");
				
				return;
			}
			
			// 向供应商发起请求
			try{
				logger.info(logId + "：向供应商发起请求，流水号：" + flwCode);
//				procService.initCfg(reqUrlEntity);
				entity = procService.busLaunch(entity);
			}catch(Exception e){
				logger.error(logId + "：子交易请求处理失败:向供应商发起子交易失败，流水号：" + flwCode,e);
				return;
			}
			
			String resSta = entity.getResSta();// 处理状态
			// 存储报文--->  upMsg  DownMsg  跟交互的报文  放在 前置---核心交互 log里
			try{
				// 存储请求供应商报文
				if(entity.getUpMsg() !=null)
					reqSupLogPath   = 	SystemOperateUtil.saveTradeMsgFile(logId, ProcSide.LOCAL.getValue(), ProcSide.SUPPLY.getValue(), InterOpType.REQSEND.getValue(), "req", entity, entity.getUpMsg());
				// 存储供应商响应报文
				if(entity.getDownMsg() !=null)
					supResLogPath   =   SystemOperateUtil.saveTradeMsgFile(logId, ProcSide.SUPPLY.getValue(), ProcSide.LOCAL.getValue(), InterOpType.RESRES.getValue(), "res", entity, entity.getDownMsg());
				
			}catch(Exception e){
				logger.error(logId + ":子交易处理异常，存储供应商报文异常,流水号：" + flwCode,e);
			}
			String interMode = chnlTrdEntity.getIntermode();
			// 处理成功
			if(ResSta.S.getValue().equals(resSta)){
				logger.info(logId + "：请求供应商成功，流水号：" + flwCode);
				// 前置--数据源
				SystemOperateUtil.sendLog(GUIDGenerator.generateId(), ProcSide.LOCAL.getValue(), ProcSide.SUPPLY.getValue(), SOPtrnTypt.S.getValue(), InterOpType.RESRES.getValue(), reqSupLogPath,supResLogPath, TradeLogSta.S.getValue(), LogType.TRADE.getValue(), "", entity);
				
				// 同步
				if(InterMode.SYNLONG.getValue().equals(interMode) || InterMode.SYNSHORT.getValue().equals(interMode)){
					try{
						logger.info(logId + "：开始转换响应核心报文......，流水号：" + flwCode);
						// 转换响应核心报文
						transferService.transToResCoreMsg(entity);
						logger.info(logId + "：转换响应核心报文成功，流水号：" + flwCode);
					}catch(Exception e){
						logger.error(logId + "：子交易请求处理失败:转换成响应核心报文错误报文错误",e);
						entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
						if(1==sFlwService.updateByFlwAndSta(entity.getStrdflw()))
							throw new STradeProcessException("转换成响应核心报文错误报文错误");
						
						return;				
					}
					
					// 向核心响应消息
					if(sendMsgToCore(chnlCod, entity, tradeCode)){
						if(1==sFlwService.update(entity.getStrdflw())){
							logger.info(logId + "：更新流水状态成功，流水号：" + flwCode);
						}else{
							logger.error(logId + "：子交易处理成功，但更新流水状态失败，流水号：" + flwCode);
						}
					}else{
						logger.info(logId + "：子交易处理成功，但向核心响应消息失败，流水号：" + flwCode);
						// 更新数据库
						if(!"".equals(StringUtil.NullToString(entity.getStrdflw().getReqmsg()))){
							entity.getStrdflw().setRespflg(STradeResFlg.SUPRES.getValue());// 状态供应商已响应
							sFlwService.updateResMsgByFlwAndSta(entity.getStrdflw());// 更新响应报文
						}
					}
				}else{
					// 更新数据库
					entity.getStrdflw().setRespflg(STradeResFlg.SENSUP.getValue());
					sFlwService.update(entity.getStrdflw());
				}
				
			}else{
				// 发起失败
//				sendErrMsg(entity, entity.getErrMsg(), coreHeader,true);
				logger.error(logId+"发起子交易请求失败");
				try{
					
					SystemOperateUtil.sendLog(GUIDGenerator.generateId(), ProcSide.LOCAL.getValue(), ProcSide.SUPPLY.getValue(), SOPtrnTypt.S.getValue(), InterOpType.REQSEND.getValue(), reqSupLogPath, supResLogPath, TradeLogSta.F.getValue(), LogType.TRADE.getValue(), entity.getErrMsg(), entity);
				}catch(Exception e){
				
				}
				
			}
			
			
		}catch(Exception e){
			sendErrMsg(entity, entity.getErrMsg(), coreHeader,false);
			e.printStackTrace();
		}
	}
	
	/**
	 * 前期预处理
	 * @param entity
	 * @param flwCode
	 * @param chnlCod
	 * @param tradeCode
	 * @return
	 * @throws Exception
	 */
	private boolean preparProc(GeneralBusEntity entity,String flwCode,String chnlCod,String tradeCode) throws Exception{
		
		 try{
			 FDatMetatrnflw fDatMetatrnflw = new FDatMetatrnflw();
			 fDatMetatrnflw.setMastjrn(entity.getCoreHeader().getMastjrn());
			 List find = sFlwService.find(fDatMetatrnflw);
			 if(null != find && find.size() == 1){
				 saveFlg =false;
				 logger.info(logId + "：子交易处理：通过核心流水查找子交易流水成功流水号：" + flwCode);
				
				 fDatMetatrnflw = (FDatMetatrnflw) find.get(0);
				 
				 flwCode = fDatMetatrnflw.getFnttrnjrn();
				 entity.getCoreHeader().setFnttrnjrn(fDatMetatrnflw.getFnttrnjrn()); // 前置流水
				 entity.getCoreHeader().setFnttrndte(fDatMetatrnflw.getFnttrndte());// 前置日期
				 entity.getCoreHeader().setFnttrntim(fDatMetatrnflw.getFnttrntim());// 前置时间
				 entity.setStrdflw(fDatMetatrnflw);// 设置前置流水实体
				 entity.setTrnDate(fDatMetatrnflw.getFnttrndte()); // 设置前置日期
				 entity.setTrnTime(fDatMetatrnflw.getFnttrntim());// 设置前置时间
				 String trnddateStr = fDatMetatrnflw.getFnttrndte()+fDatMetatrnflw.getFnttrntim();
				 entity.setTrnddate(DateUtil.timeToData(trnddateStr, "yyyyMMddHHmmss"));
				 entity.setFrntJrn(fDatMetatrnflw.getFnttrnjrn());
				 // 判断重发次数供应商次数
				 FDatOvertrnlog overEntity = new FDatOvertrnlog();
				 overEntity.setFnttrnjrn(flwCode);
				 overEntity.setRetrnto(ProcSide.SUPPLY.getValue());
				 try{
					 List list = tmoutService.find(overEntity);
					 // 已过超时次数
					 if(list != null && list.size() >= 2){
						 entity.getStrdflw().setWhererespflg(entity.getStrdflw().getRespflg());
						 entity.getStrdflw().setRespflg(STradeResFlg.SUPTMOUT.getValue());
						 if(1 == sFlwService.updateByFlwAndSta(fDatMetatrnflw)){
							 entity.getCoreHeader().setStatus(BusProcSta.S.getValue());
							 entity.getCoreHeader().setResult("供应商超时");
							 transferService.transToResCoreMsg(entity);
							 sendMsgToCore(chnlCod, entity, tradeCode);
						 }
					 }
						 
				 }catch(Exception e){
					 logger.error(logId + ":向核心响应子交易超时信息异常,流水号：" + flwCode,e);
				 }
				 
				 
				 // 核心重发
				 if("0".equals(entity.getTmoutFlg()) || "".equals(StringUtil.NullToString(entity.getTmoutFlg()))){
					 // 发送日志
					 try{
						 // 记录日志
						 SystemOperateUtil.sendLog(entity.getCoreLogId(), ProcSide.CORE.getValue(), ProcSide.LOCAL.getValue(),
								 SOPtrnTypt.S.getValue(), InterOpType.REQSEND.getValue(), entity.getCoreReqMsg(), "",TradeLogSta.S.getValue() , LogType.TRADE.getValue(),
								 "", entity);
						 // 超时日志
						 SystemOperateUtil.sendLog(GUIDGenerator.generateId(), ProcSide.CORE.getValue(),
								 ProcSide.LOCAL.getValue(), SOPtrnTypt.S.getValue(), InterOpType.REQSEND.getValue(), "", "", "", LogType.TIMEOUT.getValue(), "", entity);
					 }catch(Exception e1){
						 logger.error(logId + "：子交易处理异常，发送核心超时日志异常");
						 // 超时日志 前置---供应商
						 SystemOperateUtil.sendLog(GUIDGenerator.generateId(), ProcSide.LOCAL.getValue(),
								 ProcSide.SUPPLY.getValue(), SOPtrnTypt.S.getValue(), InterOpType.REQSEND.getValue(), "", "", "", LogType.TIMEOUT.getValue(), "", entity);
					 }
				 }else{
					 // 重发供应商日志
					 SystemOperateUtil.sendLog(GUIDGenerator.generateId(), ProcSide.LOCAL.getValue(),
							 ProcSide.SUPPLY.getValue(), SOPtrnTypt.S.getValue(), InterOpType.REQSEND.getValue(), "", "", "", LogType.TIMEOUT.getValue(), "", entity);
				 }
				 
				 
				 String resFlg = fDatMetatrnflw.getRespflg();
				 if(STradeResFlg.ERROR.getValue().equals(resFlg) ||
							STradeResFlg.SUPTMOUT.getValue().equals(resFlg) )
				 {
					 logger.error(logId + "：子交易处理异常，该交易已经处理完成，但过程中出现异常");
					 throw new STradeProcessException("该交易已经处理完成，但过程中出现异常");
				 }else if(STradeResFlg.SUCCESS.getValue().equals(fDatMetatrnflw.getRespflg()) ||
						 STradeResFlg.SUPRES.getValue().equals(fDatMetatrnflw.getRespflg())
						 ){
					 String filePath = StringUtil.NullToString(fDatMetatrnflw.getRespmsg());
					 if(!"".equals(filePath)){
						 boolean flg = false;
						 try{
							 
							 JSONObject json = SystemOperateUtil.fileToJson(filePath, SystemConstant.DEFAULT_CHARSET);
//							 JSONArray jsa = json.getJSONArray("bodys");
//							 JSONObject reJson = new JSONObject();
//							 if(jsa != null && jsa.size()>0)
//								 reJson = (JSONObject)jsa.get(0);
							 entity.setCoreMsg(json.toString().getBytes(SystemConstant.DEFAULT_CHARSET));
//							 transferService.transToResCoreMsg(entity);
							 flg = sendMsgToCore(chnlCod, entity, tradeCode);
						 }catch(Exception e2){
							 logger.error(logId + ":发送消息给核心异常",e2);
						 }
						 if(STradeResFlg.SUCCESS.getValue().equals(fDatMetatrnflw.getRespflg())){
							 return false; 
						 }else{
							entity.getStrdflw().setRespflg(STradeResFlg.SUCCESS.getValue());
							if(flg){
								try{
									sFlwService.update(entity.getStrdflw());
								}catch(Exception e){
									e.printStackTrace();
								}
								
							}
						 }
					 }else{
						 throw new STradeProcessException("该交易已经处理完成，但未找到响应信息");
					 }
				 }
				 return true;
			 }else{
				 // 记录日志
				 SystemOperateUtil.sendLog(entity.getCoreLogId(), ProcSide.CORE.getValue(), ProcSide.LOCAL.getValue(),
						 SOPtrnTypt.S.getValue(), InterOpType.REQSEND.getValue(), entity.getCoreReqMsg(), "",TradeLogSta.S.getValue() , LogType.TRADE.getValue(),
						 "", entity); 
				 return true;
			 }
		 }catch(Exception e){
			 throw e;
		 }
	}
	
	/**
	 * 发送消息给核心
	 * @param chnlCod
	 * @param entity
	 * @param tradeCode
	 */
	public boolean sendMsgToCore(String chnlCod,GeneralBusEntity entity,String tradeCode){
		try{
			
			String errMsg = "";
			String lId = GUIDGenerator.generateId();
			String logPath = "";
			if(entity.getCoreMsg() == null)
				return false;
			// 存储响应报文
			
			try{
				logPath = SystemOperateUtil.saveTradeMsgFile(lId,  ProcSide.LOCAL.getValue(), ProcSide.CORE.getValue(), InterOpType.RESRES.getValue(),  "res", entity, entity.getCoreMsg());
				
			}catch(Exception e){
				logger.error(logId + ":子交易处理异常，存储响应核心报文异常，流水号：" + entity.getFrntJrn());
				e.printStackTrace();
			}
			
			entity.getStrdflw().setRespmsg(logPath);
			BCfgdefQueue queInfoByTrCode = SystemCfgUtil.getQueInfoByTrCode(entity.getCoreHeader().getChnlcod(),entity.getCoreHeader().getIntrncod(), SOPtrnTypt.S.getValue(), QueType.RES.getValue());
			String sta = TradeLogSta.S.getValue();
			boolean flg = true;// 返回标示
			if(queInfoByTrCode != null && !StringUtil.NullToString(queInfoByTrCode.getQuetopic()).equals("")){
				// 队列实体
				String coreMsg = new String(entity.getCoreMsg(),SystemConstant.DEFAULT_CHARSET);
				KafkaQueEntity kfkQueEntity = new KafkaQueEntity();
				kfkQueEntity.setTopic(queInfoByTrCode.getQuetopic());
				kfkQueEntity.setMessage(coreMsg);
				
				try{
					logger.info(logId + "：向子交易响应队列中发送响应报文，流水号：" + entity.getFrntJrn() + ",主题：" + kfkQueEntity.getTopic() + ",内容：" + kfkQueEntity.getMessage());
					queOperator.offer(kfkQueEntity);
				}catch(Exception e){
					sta = TradeLogSta.F.getValue();
					logger.error(logId + ":子交易处理异常，向核心响应消息失败");
					flg = false;
					e.printStackTrace();
				}
			}else{
				sta = TradeLogSta.F.getValue();
				errMsg = "找不到子交易队列配置信息";
				flg = false;
			}
			// 前置--核心 log
			SystemOperateUtil.sendLog(lId, ProcSide.LOCAL.getValue(), ProcSide.CORE.getValue(), SOPtrnTypt.S.getValue(), InterOpType.RESRES.getValue(), entity.getCoreReqMsg(), logPath, sta, LogType.TRADE.getValue(), errMsg, entity);
			return flg;
		}catch(Throwable e){
			logger.error(logId + ":子交易处理异常，发送核心失败",e);
		}
		return false;
	}
	/**
	 * 发送异常消息给核心
	 * @param entity
	 * @param errMsg
	 * @param coreHeader
	 * @param flg
	 */
	private void sendErrMsg(GeneralBusEntity entity,String errMsg,CoreMsgHeader coreHeader,boolean flg){
		
		
		try {
			
			SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(),errMsg, entity);
			
			String logPath = "";
			String lId = GUIDGenerator.generateId();
			try{
				logPath = SystemOperateUtil.saveTradeMsgFile(lId,  ProcSide.LOCAL.getValue(), ProcSide.CORE.getValue(), InterOpType.RESRES.getValue(),  "res", entity, entity.getUpMsg());
			}catch(Exception e){
				logger.error(logId + ":子交易处理异常，存储响应核心报文异常，流水号：" + entity.getFrntJrn());
				e.printStackTrace();
			}
			BCfgdefQueue queInfoByTrCode = SystemCfgUtil.getQueInfoByTrCode(coreHeader.getChnlcod(),coreHeader.getIntrncod(), SOPtrnTypt.S.getValue(), QueType.RES.getValue());
			String sta = TradeLogSta.S.getValue();
			if(queInfoByTrCode != null && !StringUtil.NullToString(queInfoByTrCode.getQuetopic()).equals("")){
				// 队列实体
				String coreMsg = new String(entity.getUpMsg(),SystemConstant.DEFAULT_CHARSET);
				KafkaQueEntity kfkQueEntity = new KafkaQueEntity();
				kfkQueEntity.setTopic(queInfoByTrCode.getQuetopic());
				kfkQueEntity.setMessage(coreMsg);
				
				try{
					queOperator.offer(kfkQueEntity);
				}catch(Exception e){
					sta = TradeLogSta.F.getValue();
					logger.error(logId + ":子交易处理异常，向核心响应消息失败");
					e.printStackTrace();
				}
			}else{
				sta = TradeLogSta.F.getValue();
				errMsg = "找不到子交易队列配置信息";
			}
			
			// 前置--核心 log
			SystemOperateUtil.sendLog(lId, ProcSide.LOCAL.getValue(), ProcSide.CORE.getValue(), SOPtrnTypt.S.getValue(), InterOpType.RESRES.getValue(), entity.getCoreReqMsg(), logPath, sta, LogType.TRADE.getValue(), errMsg, entity);
			
			// 前置---数据源的log  报文
			if(flg){
				SystemOperateUtil.sendLog(GUIDGenerator.generateId(), ProcSide.LOCAL.getValue(), ProcSide.SUPPLY.getValue(), SOPtrnTypt.S.getValue(), InterOpType.REQSEND.getValue(), reqSupLogPath, supResLogPath, TradeLogSta.F.getValue(), LogType.TRADE.getValue(), errMsg, entity);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(logId+"发送异常信息到核心异常："+e.getMessage());
		}
	}

	@Override
	public void getRes(GeneralBusEntity entity) throws STradeProcessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resRecive(GeneralBusEntity entity)
			throws STradeProcessException {
		// TODO Auto-generated method stub
		
	}

	/*
	 *   GETTER  AND  SETTER 
	 */
	public GeneralTransferService getTransferService() {
		return transferService;
	}

	public void setTransferService(GeneralTransferService transferService) {
		this.transferService = transferService;
	}

	public IQueueOperator getQueOperator() {
		return queOperator;
	}

	public void setQueOperator(IQueueOperator queOperator) {
		this.queOperator = queOperator;
	}

	public GeneralTemplateService getTemplateService() {
		return templateService;
	}

	public void setTemplateService(GeneralTemplateService templateService) {
		this.templateService = templateService;
	}

	public FDatMetatrnflwService getsFlwService() {
		return sFlwService;
	}

	public void setsFlwService(FDatMetatrnflwService sFlwService) {
		this.sFlwService = sFlwService;
	}

	public String getmTocLogid() {
		return mTocLogid;
	}

	public void setmTocLogid(String mTocLogid) {
		this.mTocLogid = mTocLogid;
	}

	public FDatOvertrnlogService getTmoutService() {
		return tmoutService;
	}

	public void setTmoutService(FDatOvertrnlogService tmoutService) {
		this.tmoutService = tmoutService;
	}
	
}
