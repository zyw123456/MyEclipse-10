package com.sinoway.common.service.processer;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinoway.base.entity.BCfgdefChnltrd;
import com.sinoway.base.entity.BCfgdefFntsrvinfo;
import com.sinoway.base.entity.BCfgdefFnttrnaddr;
import com.sinoway.base.entity.BCfgdefFnttrninfo;
import com.sinoway.base.entity.BCfgdefQueue;
import com.sinoway.common.constant.SystemConstant;
import com.sinoway.common.constant.MsgTransfConstant.MsgTemplateType;
import com.sinoway.common.constant.ServerConstant.ResSta;
import com.sinoway.common.constant.ServerConstant.ServiceBean;
import com.sinoway.common.constant.SystemConstant.AddrProtocl;
import com.sinoway.common.constant.SystemConstant.BusProcSta;
import com.sinoway.common.constant.SystemConstant.InterMode;
import com.sinoway.common.constant.SystemConstant.InterOpType;
import com.sinoway.common.constant.SystemConstant.IsOrNot;
import com.sinoway.common.constant.SystemConstant.LogType;
import com.sinoway.common.constant.SystemConstant.ProcSide;
import com.sinoway.common.constant.SystemConstant.QueType;
import com.sinoway.common.constant.SystemConstant.ResResType;
import com.sinoway.common.constant.SystemConstant.SOPtrnTypt;
import com.sinoway.common.constant.SystemConstant.SysParamCode;
import com.sinoway.common.constant.SystemConstant.TradeLogSta;
import com.sinoway.common.constant.SystemConstant.TradeResFlg;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.entity.Product;
import com.sinoway.common.entity.Trade;
import com.sinoway.common.exception.TradeProcException;
import com.sinoway.common.frame.SpringContextUtil;
import com.sinoway.common.service.server.GeneralResService;
import com.sinoway.common.service.template.GeneralTemplateService;
import com.sinoway.common.service.transfer.GeneralTransferService;
import com.sinoway.common.util.GUIDGenerator;
import com.sinoway.common.util.GetComputerCon;
import com.sinoway.common.util.JRNGenerator;
import com.sinoway.common.util.MD5Utils;
import com.sinoway.common.util.McpLogger;
import com.sinoway.common.util.StringUtil;
import com.sinoway.common.util.SystemCfgUtil;
import com.sinoway.common.util.SystemOperateUtil;
import com.sinoway.mcp.entity.FDatPrdinfoflw;
import com.sinoway.mcp.entity.FDatPrdtrnflw;
import com.sinoway.mcp.entity.FLogTrnflw;
import com.sinoway.mcp.exception.TradeMsgTransfException;
import com.sinoway.mcp.queue.entity.KafkaQueEntity;
import com.sinoway.mcp.queue.exception.QueueOperatException;
import com.sinoway.mcp.queue.service.IQueueOperator;
import com.sinoway.mcp.service.FDatPrdinfoflwService;
import com.sinoway.mcp.service.FDatPrdtrnflwService;
import com.sinoway.mcp.service.FLogTrnflwService;

/**
 * 通用业务逻辑处理
 * 
 * @author Liuzhen
 * @version 1.0 2015-11-20
 */
public class GeneralProcesser implements GeneralProcesserService {
	private McpLogger logger = McpLogger.getLogger(getClass());
	/*
	 * 注入服务
	 */
	private GeneralTransferService transferService = null;// 报文转换服务
	private FDatPrdinfoflwService pInfoService = null;// 产品流水服务
	private FDatPrdtrnflwService oInfoService = null;// 原交易流水服务
	private FLogTrnflwService logServcie = null;// 日志流水服务
	private IQueueOperator queOperator = null; // 队列操作服务
	private GeneralTemplateService templateService = null;// 交易模板服务

	private String logId = "";
	private Trade trade = null;
	private Product product = null;
	private String recReqLogId = null;// 接收请求日志uuid
	private String senCoreLogId = null;// 发送核心请求日志uuid
	private String interMode = "";// 交互模式
	private String trnType = ""; // 交易类型
	private String reqCoreMsgPath = "";// 请求核心报文
	private String coreResMsgPath = "";// 核心响应报文
	private String clientReqMsgPath = "";// 客户端请求报文存储路径
	private String resClientMsgPath = "";// 响应客户端报文路径
	private int tmout = 0;// 超时时间
	private int tmnum = 0;// 超时次数
	private int toCoreNum = 0;// 发送核心的次数
	private String resSta = "";

	@Override
	public GeneralBusEntity excuteToClient(GeneralBusEntity entity)
			throws TradeProcException {
		logId = entity.getLogId();// 日志ID
		String chnlCod = entity.getCoreHeader().getChnlcod();// 渠道号
		String trnType = entity.getTrnType();// 交易类型
		String flwCode = entity.getCoreHeader().getFnttrnjrn();// 前置流水号
		String prdCode = entity.getCoreHeader().getPrdcod();// 产品号
		String tradeCode = entity.getCoreHeader().getIntrncod();// 内部交易码
		try {
			logger.info(logId + "：接收到交易响应请求，流水号:" + flwCode + "，开始处理......");
			String cResLogId = GUIDGenerator.generateId();// 响应客户端日志ID
			// 服务器配置信息
			BCfgdefFntsrvinfo serInf = SystemCfgUtil
					.getSerInfoByIp(GetComputerCon.getInstance().getIp());

			// 响应客户端日志
			String resClientId = GUIDGenerator.generateId();
			// 原交易处理
			if (SOPtrnTypt.O.getValue().equals(trnType)) {
				try{
					// 获取数据库中交易流水信息
					List list = oInfoService.findByFlwCode(entity.getOtrdflw());
					if (list != null && list.size() > 0) {
						String respMsgPath = entity.getOtrdflw().getRespmsg();
						entity.setOtrdflw((FDatPrdtrnflw) list.get(0));
						String curSta = entity.getOtrdflw().getRespflg();
						// 交易已经处理完成
						if (TradeResFlg.COREOUT.getValue().equals(curSta)
								|| TradeResFlg.OTHERR.getValue().equals(curSta)
								|| TradeResFlg.RESFAIL.getValue().equals(curSta)
								|| TradeResFlg.SUCESS.getValue().equals(curSta)){
							
							logger.info(logId + ":该笔流水已经处理完成，流水号：" + entity.getOtrdflw().getFnttrnjrn());
							return entity;
						}
						entity.getCoreHeader().setClntjrn(entity.getOtrdflw().getClntjrn());// 重新设置客户端流水
						entity.getCoreHeader().setClnttrndte(entity.getOtrdflw().getClnttrndte());// 重新设置客户端交易日期
						entity.getCoreHeader().setClnttrntime(entity.getOtrdflw().getClnttrntime());// 重新设置客户端交易时间
						entity.getCoreHeader().setDatori(entity.getOtrdflw().getDatori());// 设置数据来源
						entity.getOtrdflw().setRespmsg(respMsgPath);
						entity.getOtrdflw().setMastjrn(entity.getCoreHeader().getMastjrn());
						entity.getOtrdflw().setMasttrndte(entity.getCoreHeader().getMasttrndte());
						entity.getOtrdflw().setMasttrntim(entity.getCoreHeader().getMasttrntim());
						
					}else{
						logger.error(logId + ":不存在此交易流水，流水号：" + entity.getCoreHeader().getFnttrnjrn());
						// TODO
						return entity;
					}
				}catch(Exception e){
					logger.error(logId + ":向客户端返回响应信息异常，流水号：" + entity.getCoreHeader().getFnttrnjrn(),e);
					e.printStackTrace();
					throw e;
				}
				// 获取渠道 交易配置信息
				BCfgdefChnltrd chnlTradeCfg = SystemCfgUtil.getChnlCfgInf(
						chnlCod, tradeCode, trnType);
				
				if (chnlTradeCfg == null) {
					logger.error(logId + "：原交易响应处理异常，流水号:" + flwCode
							+ "，渠道交易配置信息不存在");
					entity.getOtrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
					// 更新流水
					oInfoService.update(entity.getOtrdflw());
					
					// 发送失败日志
					SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
							ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
							InterOpType.RESRES.getValue(), coreResMsgPath, "", TradeLogSta.F.getValue(),
							LogType.TRADE.getValue(), "渠道交易配置信息不存在", entity);
					throw new TradeProcException("原交易响应请求处理，流水号:" + flwCode
							+ "，渠道交易配置信息不存在");
				}
				
				// 结果响应方式
				String resResType = chnlTradeCfg.getResrestype();
				if (resResType == null || "".equals(resResType)) {
					logger.error(logId + "：原交易响应请求处理，流水号:" + flwCode
							+ "，结果响应方式不能为空");
					
					entity.getOtrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
					// 更新流水
					oInfoService.update(entity.getOtrdflw());
					
					// 发送失败日志
					SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
							ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
							InterOpType.RESRES.getValue(), coreResMsgPath, "", TradeLogSta.F.getValue(),
							LogType.TRADE.getValue(), "结果响应方式不能为空", entity);
					
					throw new TradeProcException("原交易响应请求处理，流水号:" + flwCode
							+ "，结果响应方式不能为空");
				}

				// 主动响应
				if (ResResType.ACTIVE.getValue().equals(resResType)) {

					logger.info(logId + "：原交易响应请求处理，流水号:" + flwCode
							+ "，结果响应方式：主动");

					logger.info(logId + "：原交易响应请求处理，流水号:" + flwCode
							+ "，开始转换响应报文......");
					try {
						// 转换成响应客户端报文
						transferService.transToResCMsg(entity);
						logger.info(logId + "：原交易响应请求处理，流水号:" + flwCode
								+ "，转换响应报文成功");
					} catch (Exception e) {
						logger.error(logId + "：原交易响应请求处理，流水号:" + flwCode
								+ "，转换响应报文失败", e);
						
						entity.getOtrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
						// 更新流水
						oInfoService.update(entity.getOtrdflw());
						
						// 发送失败日志
						SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
								ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
								InterOpType.RESRES.getValue(), coreResMsgPath, "", TradeLogSta.F.getValue(),
								LogType.TRADE.getValue(), "转换响应报文失败", entity);
						
						throw new TradeProcException("原交易响应请求处理，流水号:" + flwCode
								+ "，转换响应报文失败", e);
					}
					
					byte[] upMsg = entity.getUpMsg();
					if (upMsg == null || upMsg.length == 0) {
						logger.error(logId + "：原交易响应请求处理，流水号:" + flwCode
								+ "，响应报文不能为空");
						
						entity.getOtrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
						// 更新流水
						oInfoService.update(entity.getOtrdflw());
						
						// 发送失败日志
						SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
								ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
								InterOpType.RESRES.getValue(), coreResMsgPath, "", TradeLogSta.F.getValue(),
								LogType.TRADE.getValue(), "响应报文不能为空", entity);
						
						throw new TradeProcException("原交易响应请求处理，流水号:" + flwCode
								+ "，响应报文不能为空");
					}
					
					logger.info(logId + "：原交易响应请求处理，流水号:" + flwCode
							+ "，转换响应报文成功,内容：" + new String(upMsg,SystemConstant.DEFAULT_CHARSET));
					entity.setUpMsg(upMsg);
					
					entity.getHeader().setMsglen(upMsg.length);
					entity.getHeader().setCheckcod(MD5Utils.hash(chnlCod + entity.getDesKeyInf().getKey() + new String(upMsg,SystemConstant.DEFAULT_CHARSET), "MD5"));
					String resCMsgPath = "";
					// 存储响应客户端报文
					try {
						
						resCMsgPath = SystemOperateUtil.saveTradeMsgFile(resClientId, ProcSide.LOCAL.getValue(),
								ProcSide.CLIENT.getValue(), InterOpType.RESRES.getValue(), "res", entity, upMsg);

					} catch (Exception e) {
						logger.error(logId + "：原交易响应处理异常：存储响应客户端报文错误，流水号：" + flwCode,e);
					}

					String interMode = chnlTradeCfg.getIntermode();
					if (interMode == null || "".equals(interMode)) {
						logger.error(logId + "：原交易响应请求处理，流水号:" + flwCode
								+ "，交互方式信息不存在");
						
						entity.getOtrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
						// 更新流水
						oInfoService.update(entity.getOtrdflw());
						
						// 发送失败日志
						SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
								ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
								InterOpType.RESRES.getValue(), resCMsgPath, "", TradeLogSta.F.getValue(),
								LogType.TRADE.getValue(), "交互方式信息不存在", entity);
						
						throw new TradeProcException("原交易响应请求处理，流水号:" + flwCode
								+ "，交互方式信息不存在");
					}
					// 异步短链
					if (InterMode.ASYNSHORT.getValue().equals(interMode)) {
						logger.info(logId + "：原交易响应请求处理，流水号:" + flwCode
								+ "，使用异步短链方式向客户端进行响应");
						// 请求响应地址
						String resUrlCode = chnlTradeCfg.getResresurl();
						if (resUrlCode == null || "".equals(resUrlCode)) {
							logger.error(logId + "：原交易响应请求处理，流水号:" + flwCode
									+ "，异步结果响应时响应地址必须存在");
							
							entity.getOtrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
							// 更新流水
							oInfoService.update(entity.getOtrdflw());
							
							// 发送失败日志
							SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
									ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
									InterOpType.RESRES.getValue(), resCMsgPath, "", TradeLogSta.F.getValue(),
									LogType.TRADE.getValue(), "异步结果响应时响应地址必须存在", entity);
							
							throw new TradeProcException("原交易响应请求处理，流水号:"
									+ flwCode + "，异步结果响应时响应地址必须存在");
						}

						// 获取响应地址
						BCfgdefFnttrnaddr urlEntity = SystemCfgUtil
								.getAddrInfByCode(resUrlCode);

						if (urlEntity == null) {
							logger.error(logId + "：原交易响应请求处理，流水号:" + flwCode
									+ "，异步结果响应时响应地址必须存在");
							
							entity.getOtrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
							// 更新流水
							oInfoService.update(entity.getOtrdflw());
							
							// 发送失败日志
							SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
									ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
									InterOpType.RESRES.getValue(), resCMsgPath, "", TradeLogSta.F.getValue(),
									LogType.TRADE.getValue(), "异步结果响应时响应地址必须存在", entity);
							
							throw new TradeProcException("原交易响应请求处理，流水号:"
									+ flwCode + "，异步结果响应时响应地址必须存在");
						}
						// 协议
						String protocol = urlEntity.getProtocol();

						if (protocol == null || "".equals(protocol)) {
							logger.error(logId + "：原交易响应请求处理，流水号:" + flwCode
									+ "，地址协议不能为空");
							
							entity.getOtrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
							// 更新流水
							oInfoService.update(entity.getOtrdflw());
							
							// 发送失败日志
							SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
									ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
									InterOpType.RESRES.getValue(), resCMsgPath, "", TradeLogSta.F.getValue(),
									LogType.TRADE.getValue(), "地址协议不能为空", entity);
							
							throw new TradeProcException("原交易响应请求处理，流水号:"
									+ flwCode + "，地址协议不能为空");
						}

						// 获取交易配置模板
						trade = templateService.getTradeByTemplate(chnlCod,
								tradeCode);

						if (trade == null) {
							logger.error(logId + "：原交易响应请求处理，流水号:" + flwCode
									+ "，交易模板配置不存在");
							entity.getOtrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
							// 更新流水
							oInfoService.update(entity.getOtrdflw());
							
							// 发送失败日志
							SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
									ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
									InterOpType.RESRES.getValue(), resCMsgPath, "", TradeLogSta.F.getValue(),
									LogType.TRADE.getValue(), "交易模板配置不存在", entity);
							throw new TradeProcException("原交易响应请求处理，流水号:"
									+ flwCode + "，交易模板配置不存在");
						}

						logger.info(logId + "：原交易响应请求处理，流水号:" + flwCode
								+ "，获取异步响应处理BEAN");

						GeneralResService resService = null;
						
						// HTTP
						if(AddrProtocl.HTTP.getValue().equals(protocol)){
							resService = (GeneralResService) SpringContextUtil.getBean(trade.getParamValueByName(ServiceBean.ASNYRES_HTTP_SERVICE
												.getValue()));
						//HTTPS
						}else if(AddrProtocl.HTTPS.getValue().equals(protocol)){
							resService = (GeneralResService) SpringContextUtil.getBean(trade.getParamValueByName(ServiceBean.ASNYRES_HTTPS_SERVICE
									.getValue()));
						// SOCKET
						}else if(AddrProtocl.SOCKET.getValue().equals(protocol)){
							resService = (GeneralResService) SpringContextUtil.getBean(trade.getParamValueByName(ServiceBean.ASNYRES_SCOKET_SERVICE
									.getValue()));
						// WEBSERVICE
						}else if(AddrProtocl.WEBSERVICE.getValue().equals(protocol)){
							resService = (GeneralResService) SpringContextUtil.getBean(trade.getParamValueByName(ServiceBean.ASNYRES_WEBSERVICE_SERVICE
									.getValue()));
							
						}else{
							logger.info(logId + "：原交易响应请求处理，流水号:" + flwCode
									+ "，获取异步响应处理BEAN异常，不支持的协议类型");
							entity.getOtrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
							// 更新流水
							oInfoService.update(entity.getOtrdflw());
							
							// 发送失败日志
							SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
									ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
									InterOpType.RESRES.getValue(), resCMsgPath, "", TradeLogSta.F.getValue(),
									LogType.TRADE.getValue(), "不支持的地址协议类型", entity);
						}

						if (resService == null) {
							logger.error(logId + "：原交易响应请求处理，流水号:" + flwCode
									+ "，交易响应服务不存在");
							
							entity.getOtrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
							// 更新流水
							oInfoService.update(entity.getOtrdflw());
							
							// 发送失败日志
							SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
									ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
									InterOpType.RESRES.getValue(), resCMsgPath, "", TradeLogSta.F.getValue(),
									LogType.TRADE.getValue(), "未获取到原交易响应服务bean", entity);
							
							throw new TradeProcException("原交易响应请求处理，流水号:"
									+ flwCode + "，交易响应服务不存在");
						}
						try {
							// 初始化
							resService.initCfg(urlEntity);
							entity = resService.excuteToClient(entity);
						} catch (Exception e) {
							logger.error(logId + "：原交易响应请求处理，流水号:" + flwCode
									+ "，响应客户端失败");
							
							entity.getOtrdflw().setRespflg(TradeResFlg.RESFAIL.getValue());
							// 更新流水
							oInfoService.update(entity.getOtrdflw());
							
							// 发送失败日志
							SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
									ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
									InterOpType.RESRES.getValue(), resCMsgPath, "", TradeLogSta.F.getValue(),
									LogType.TRADE.getValue(), "响应客户端失败" + e.getMessage(), entity);
							
							throw new TradeProcException("原交易响应请求处理，流水号:"
									+ flwCode + "，响应客户端失败");

						}
						String resSta = entity.getResSta();
						// 客户端响应报文
						String cResMsgPath = "";
						// 响应成功
						if (ResSta.S.getValue().equals(resSta)) {
							logger.info(logId + "：原交易响应请求处理，流水号:" + flwCode
									+ "，向客户端异步响应完成，结果：成功");
							// 保存报文
							byte[] downMsg = entity.getDownMsg();
							// 存储响应客户端报文
							if (downMsg != null) {
								try {
									cResMsgPath = SystemOperateUtil.saveTradeMsgFile(cResLogId, ProcSide.LOCAL.getValue(), 
											ProcSide.CLIENT.getValue(), InterOpType.RESRES.getValue(), "res", entity, downMsg);
								} catch (Exception e) {
									logger.error(logId+ "：原交易响应处理异常：存储客户端响应报文错误，流水号：" + flwCode,e);
									e.printStackTrace();
								}

							}
							logger.info(logId + "：原交易响应请求处理，流水号:" + flwCode
									+ "，开始更新流水状态");
							
							entity.getOtrdflw().setRespflg(TradeResFlg.SUCESS.getValue());

							// 更新流水表
							try {
								oInfoService.update(entity.getOtrdflw());
								logger.info(logId + "：原交易响应请求处理，流水号:" + flwCode
										+ "，更新流水状态完成");
							} catch (Exception e) {
								logger.error(logId + "：原交易响应请求处理，流水号:"
										+ flwCode + "，更新流水表失败");
								// 发送日志
								SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
										ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
										InterOpType.RESRES.getValue(), resCMsgPath, cResMsgPath, TradeLogSta.S.getValue(),
										LogType.TRADE.getValue(), "更新流水表异常" + e.getMessage(), entity);
								
								throw new TradeProcException("原交易响应请求处理，流水号:"
										+ flwCode + "，更新流水表失败");
							}
							// 记录日志 
							SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
									ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
									InterOpType.RESRES.getValue(), resCMsgPath, cResMsgPath, TradeLogSta.S.getValue(),
									LogType.TRADE.getValue(), "", entity);

						// 响应失败
						} else {
							entity.getOtrdflw().setRespflg(TradeResFlg.RESFAIL.getValue());

							// 更新流水表
							try {
								oInfoService.update(entity.getOtrdflw());
							} catch (Exception e) {
								logger.error(logId + "：原交易响应请求处理，流水号:"
										+ flwCode + "，更新流水表失败");
								// 发送日志
								SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
										ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
										InterOpType.RESRES.getValue(), resCMsgPath, cResMsgPath, TradeLogSta.F.getValue(),
										LogType.TRADE.getValue(), "更新流水表异常" + e.getMessage(), entity);
								throw new TradeProcException("原交易响应请求处理，流水号:"
										+ flwCode + "，更新流水表失败");
							}

							// 发送日志
							SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
									ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
									InterOpType.RESRES.getValue(), resCMsgPath, cResMsgPath, TradeLogSta.S.getValue(),
									LogType.TRADE.getValue(), "", entity);
						}

					// 长连接
					} else if (InterMode.ASYNLONG.getValue().equals(interMode)) {
						// TODO 暂不支持长连接
					} else {
						logger.error(logId + "：原交易响应请求处理，流水号:" + flwCode
								+ "，不支持的交互方式：" + interMode);
						
						entity.getOtrdflw().setRespflg(TradeResFlg.RESFAIL.getValue());
						// 更新流水
						oInfoService.update(entity.getOtrdflw());
						
						// 发送失败日志
						SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
								ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
								InterOpType.RESRES.getValue(), resCMsgPath, "", TradeLogSta.F.getValue(),
								LogType.TRADE.getValue(), "不支持的交互模式", entity);
						
						throw new TradeProcException("原交易响应请求处理，流水号:" + flwCode
								+ "，不支持的交互方式：" + interMode);
					}

					// 被动响应
				} else if (ResResType.PASSIVITY.getValue().equals(resResType)) {
					logger.debug("原交易响应请求处理，流水号:" + flwCode + "，结果响应方式：被动");
					// TODO
				} else {

					logger.error(logId + "：原交易响应请求处理，流水号:" + flwCode
							+ "，不支持的结果响应方式");
					entity.getOtrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
					// 更新流水
					oInfoService.update(entity.getOtrdflw());
					
					// 发送失败日志
					SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
							ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
							InterOpType.RESRES.getValue(), coreResMsgPath, "", TradeLogSta.F.getValue(),
							LogType.TRADE.getValue(), "不支持的结果响应方式", entity);
					throw new TradeProcException("原交易响应请求处理，流水号:" + flwCode
							+ "，不支持的结果响应方式");
				}

			// 产品处理
			} else if (SOPtrnTypt.P.getValue().equals(trnType)) {

				try{
					// 获取数据库中交易流水信息
					List list = pInfoService.findByFlwCode(entity.getPrdflw());
					if (list != null && list.size() > 0) {
						String respMsgPath = entity.getPrdflw().getRespmsg();
						entity.setPrdflw((FDatPrdinfoflw) list.get(0));
						String curSta = entity.getPrdflw().getRespflg();
						// 交易已经处理完成
						if (TradeResFlg.COREOUT.getValue().equals(curSta)
								|| TradeResFlg.OTHERR.getValue().equals(curSta)
								|| TradeResFlg.RESFAIL.getValue().equals(curSta)
								|| TradeResFlg.SUCESS.getValue().equals(curSta)){
							
							logger.info(logId + ":该笔流水已经处理完成，流水号：" + entity.getPrdflw().getFnttrnjrn());
							return entity;
						}
						entity.getCoreHeader().setClntjrn(entity.getPrdflw().getClntjrn());// 重新设置客户端流水
						entity.getCoreHeader().setClnttrndte(entity.getPrdflw().getClnttrndte());// 重新设置客户端交易日期
						entity.getCoreHeader().setClnttrntime(entity.getPrdflw().getClnttrntime());// 重新设置客户端交易时间
						entity.getCoreHeader().setDatori(entity.getPrdflw().getDatori());// 设置数据来源
						entity.getPrdflw().setRespmsg(respMsgPath);
						entity.getPrdflw().setMastjrn(entity.getCoreHeader().getMastjrn());
						entity.getPrdflw().setMasttrndte(entity.getCoreHeader().getMasttrndte());
						entity.getPrdflw().setMasttrntim(entity.getCoreHeader().getMasttrntim());
						
					}else{
						logger.error(logId + ":不存在此交易流水，流水号：" + entity.getCoreHeader().getFnttrnjrn());
						// TODO
						return entity;
					}
				}catch(Exception e){
					logger.error(logId + ":向客户端返回响应信息异常，流水号：" + entity.getCoreHeader().getFnttrnjrn(),e);
					e.printStackTrace();
					throw e;
				}
				// 获取渠道 交易配置信息
				BCfgdefChnltrd chnlTradeCfg = SystemCfgUtil.getChnlCfgInf(
						chnlCod, prdCode, trnType);
				
				if (chnlTradeCfg == null) {
					logger.error(logId + "：产品响应处理异常，流水号:" + flwCode
							+ "，渠道配置信息不存在");
					entity.getPrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
					// 更新流水
					pInfoService.update(entity.getPrdflw());
					
					// 发送失败日志
					SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
							ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
							InterOpType.RESRES.getValue(), coreResMsgPath, "", TradeLogSta.F.getValue(),
							LogType.TRADE.getValue(), "渠道配置信息不存在", entity);
					throw new TradeProcException("产品响应请求处理，流水号:" + flwCode
							+ "，渠道配置信息不存在");
				}
				
				// 结果响应方式
				String resResType = chnlTradeCfg.getResrestype();
				if (resResType == null || "".equals(resResType)) {
					logger.error(logId + "：产品响应请求处理，流水号:" + flwCode
							+ "，结果响应方式不能为空");
					
					entity.getPrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
					// 更新流水
					pInfoService.update(entity.getPrdflw());
					
					// 发送失败日志
					SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
							ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
							InterOpType.RESRES.getValue(), coreResMsgPath, "", TradeLogSta.F.getValue(),
							LogType.TRADE.getValue(), "结果响应方式不能为空", entity);
					
					throw new TradeProcException("产品响应请求处理，流水号:" + flwCode
							+ "，结果响应方式不能为空");
				}

				// 主动响应
				if (ResResType.ACTIVE.getValue().equals(resResType)) {

					logger.info(logId + "：产品响应请求处理，流水号:" + flwCode
							+ "，结果响应方式：主动");

					logger.info(logId + "：产品响应请求处理，流水号:" + flwCode
							+ "，开始转换响应报文......");
					try {
						// 转换成响应客户端报文
						transferService.transToResCMsg(entity);
						logger.info(logId + "：产品响应请求处理，流水号:" + flwCode
								+ "，转换响应报文成功");
					} catch (Exception e) {
						logger.error(logId + "：产品响应请求处理，流水号:" + flwCode
								+ "，转换响应报文失败", e);
						
						entity.getPrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
						// 更新流水
						pInfoService.update(entity.getPrdflw());
						
						// 发送失败日志
						SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
								ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
								InterOpType.RESRES.getValue(), coreResMsgPath, "", TradeLogSta.F.getValue(),
								LogType.TRADE.getValue(), "转换响应报文失败", entity);
						
						throw new TradeProcException("原交易响应请求处理，流水号:" + flwCode
								+ "，转换响应报文失败", e);
					}
					
					byte[] upMsg = entity.getUpMsg();
					
					if (upMsg == null || upMsg.length == 0) {
						logger.error(logId + "：产品响应请求处理，流水号:" + flwCode
								+ "，响应报文不能为空");
						
						entity.getPrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
						// 更新流水
						pInfoService.update(entity.getPrdflw());
						
						// 发送失败日志
						SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
								ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
								InterOpType.RESRES.getValue(), coreResMsgPath, "", TradeLogSta.F.getValue(),
								LogType.TRADE.getValue(), "响应报文不能为空", entity);
						
						throw new TradeProcException("产品响应请求处理，流水号:" + flwCode
								+ "，响应报文不能为空");
					}
					logger.info(logId + "：产品响应请求处理，流水号:" + flwCode
							+ "，转换响应报文成功,内容：" + new String(upMsg,SystemConstant.DEFAULT_CHARSET));
					entity.setUpMsg(upMsg);
					entity.getHeader().setMsglen(upMsg.length);
					entity.getHeader().setCheckcod(MD5Utils.hash(chnlCod + entity.getDesKeyInf().getKey() + new String(upMsg,SystemConstant.DEFAULT_CHARSET), "MD5"));
					String resCMsgPath = "";
					// 存储响应客户端报文
					try {
						
						resCMsgPath = SystemOperateUtil.saveTradeMsgFile(resClientId, ProcSide.LOCAL.getValue(),
								ProcSide.CLIENT.getValue(), InterOpType.RESRES.getValue(), "res", entity, upMsg);

					} catch (Exception e) {
						logger.error(logId + "：产品响应处理异常：存储响应客户端报文错误，流水号：" + flwCode,e);
					}

					String interMode = chnlTradeCfg.getIntermode();
					if (interMode == null || "".equals(interMode)) {
						logger.error(logId + "：产品响应请求处理，流水号:" + flwCode
								+ "，交互方式信息不存在");
						
						entity.getPrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
						// 更新流水
						pInfoService.update(entity.getPrdflw());
						
						// 发送失败日志
						SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
								ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
								InterOpType.RESRES.getValue(), resCMsgPath, "", TradeLogSta.F.getValue(),
								LogType.TRADE.getValue(), "交互方式信息不存在", entity);
						
						throw new TradeProcException("产品响应请求处理，流水号:" + flwCode
								+ "，交互方式信息不存在");
					}
					// 异步短链
					if (InterMode.ASYNSHORT.getValue().equals(interMode)) {
						logger.info(logId + "：产品响应请求处理，流水号:" + flwCode
								+ "，使用异步短链方式向客户端进行响应");
						// 请求响应地址
						String resUrlCode = chnlTradeCfg.getResresurl();
						if (resUrlCode == null || "".equals(resUrlCode)) {
							logger.error(logId + "：产品响应请求处理，流水号:" + flwCode
									+ "，异步结果响应时响应地址必须存在");
							
							entity.getPrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
							// 更新流水
							pInfoService.update(entity.getPrdflw());
							
							// 发送失败日志
							SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
									ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
									InterOpType.RESRES.getValue(), resCMsgPath, "", TradeLogSta.F.getValue(),
									LogType.TRADE.getValue(), "异步结果响应时响应地址必须存在", entity);
							
							throw new TradeProcException("产品响应请求处理，流水号:"
									+ flwCode + "，异步结果响应时响应地址必须存在");
						}

						// 获取响应地址
						BCfgdefFnttrnaddr urlEntity = SystemCfgUtil
								.getAddrInfByCode(resUrlCode);

						if (urlEntity == null) {
							logger.error(logId + "：产品响应请求处理，流水号:" + flwCode
									+ "，异步结果响应时响应地址必须存在");
							
							entity.getOtrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
							// 更新流水
							pInfoService.update(entity.getPrdflw());
							
							// 发送失败日志
							SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
									ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
									InterOpType.RESRES.getValue(), resCMsgPath, "", TradeLogSta.F.getValue(),
									LogType.TRADE.getValue(), "异步结果响应时响应地址必须存在", entity);
							
							throw new TradeProcException("产品响应请求处理，流水号:"
									+ flwCode + "，异步结果响应时响应地址必须存在");
						}
						// 协议
						String protocol = urlEntity.getProtocol();

						if (protocol == null || "".equals(protocol)) {
							logger.error(logId + "：产品响应请求处理，流水号:" + flwCode
									+ "，地址协议不能为空");
							
							entity.getPrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
							// 更新流水
							pInfoService.update(entity.getPrdflw());
							
							// 发送失败日志
							SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
									ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
									InterOpType.RESRES.getValue(), resCMsgPath, "", TradeLogSta.F.getValue(),
									LogType.TRADE.getValue(), "地址协议不能为空", entity);
							
							throw new TradeProcException("产品响应请求处理，流水号:"
									+ flwCode + "，地址协议不能为空");
						}

						// 获取交易配置模板
						product = templateService.getPrdByTemplate(chnlCod, prdCode);

						if (product == null) {
							logger.error(logId + "：产品响应请求处理，流水号:" + flwCode
									+ "，产品模板配置不存在");
							entity.getPrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
							// 更新流水
							pInfoService.update(entity.getPrdflw());
							
							// 发送失败日志
							SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
									ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
									InterOpType.RESRES.getValue(), resCMsgPath, "", TradeLogSta.F.getValue(),
									LogType.TRADE.getValue(), "产品模板配置不存在", entity);
							throw new TradeProcException("产品响应请求处理，流水号:"
									+ flwCode + "，产品模板配置不存在");
						}

						logger.info(logId + "：产品响应请求处理，流水号:" + flwCode
								+ "，获取异步响应处理BEAN");

						GeneralResService resService = null;
						
						// HTTP
						if(AddrProtocl.HTTP.getValue().equals(protocol)){
							resService = (GeneralResService) SpringContextUtil.getBean(product.getParamValueByName(ServiceBean.ASNYRES_HTTP_SERVICE
												.getValue()));
						//HTTPS
						}else if(AddrProtocl.HTTPS.getValue().equals(protocol)){
							resService = (GeneralResService) SpringContextUtil.getBean(product.getParamValueByName(ServiceBean.ASNYRES_HTTPS_SERVICE
									.getValue()));
						// SOCKET
						}else if(AddrProtocl.SOCKET.getValue().equals(protocol)){
							resService = (GeneralResService) SpringContextUtil.getBean(product.getParamValueByName(ServiceBean.ASNYRES_SCOKET_SERVICE
									.getValue()));
						// WEBSERVICE
						}else if(AddrProtocl.WEBSERVICE.getValue().equals(protocol)){
							resService = (GeneralResService) SpringContextUtil.getBean(product.getParamValueByName(ServiceBean.ASNYRES_WEBSERVICE_SERVICE
									.getValue()));
							
						}else{
							logger.info(logId + "：产品响应请求处理，流水号:" + flwCode
									+ "，获取异步响应处理BEAN异常，不支持的协议类型");
							entity.getPrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
							// 更新流水
							pInfoService.update(entity.getPrdflw());
							
							// 发送失败日志
							SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
									ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
									InterOpType.RESRES.getValue(), resCMsgPath, "", TradeLogSta.F.getValue(),
									LogType.TRADE.getValue(), "不支持的地址协议类型", entity);
						}

						if (resService == null) {
							logger.error(logId + "：产品响应请求处理，流水号:" + flwCode
									+ "，产品响应服务不存在");
							
							entity.getPrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
							// 更新流水
							pInfoService.update(entity.getPrdflw());
							
							// 发送失败日志
							SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
									ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
									InterOpType.RESRES.getValue(), resCMsgPath, "", TradeLogSta.F.getValue(),
									LogType.TRADE.getValue(), "未获取到产品响应服务bean", entity);
							
							throw new TradeProcException("产品响应请求处理，流水号:"
									+ flwCode + "，产品响应服务不存在");
						}
						try {
							// 初始化
							resService.initCfg(urlEntity);
							entity = resService.excuteToClient(entity);
						} catch (Exception e) {
							logger.error(logId + "：产品响应请求处理，流水号:" + flwCode
									+ "，响应客户端失败");
							
							entity.getPrdflw().setRespflg(TradeResFlg.RESFAIL.getValue());
							// 更新流水
							pInfoService.update(entity.getPrdflw());
							
							// 发送失败日志
							SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
									ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
									InterOpType.RESRES.getValue(), resCMsgPath, "", TradeLogSta.F.getValue(),
									LogType.TRADE.getValue(), "响应客户端失败" + e.getMessage(), entity);
							
							throw new TradeProcException("产品响应请求处理，流水号:"
									+ flwCode + "，响应客户端失败");

						}
						String resSta = entity.getResSta();
						// 客户端响应报文
						String cResMsgPath = "";
						// 响应成功
						if (ResSta.S.getValue().equals(resSta)) {
							logger.info(logId + "：产品响应请求处理，流水号:" + flwCode
									+ "，向客户端异步响应完成，结果：成功");
							// 保存报文
							byte[] downMsg = entity.getDownMsg();
							// 存储响应客户端报文
							if (downMsg != null) {
								try {
									cResMsgPath = SystemOperateUtil.saveTradeMsgFile(cResLogId, ProcSide.LOCAL.getValue(), 
											ProcSide.CLIENT.getValue(), InterOpType.RESRES.getValue(), "res", entity, downMsg);
								} catch (Exception e) {
									logger.error(logId+ "：产品响应处理异常：存储客户端响应报文错误，流水号：" + flwCode,e);
									e.printStackTrace();
								}

							}
							logger.info(logId + "：产品响应请求处理，流水号:" + flwCode
									+ "，开始更新流水状态");
							
							entity.getPrdflw().setRespflg(TradeResFlg.SUCESS.getValue());

							// 更新流水表
							try {
								pInfoService.update(entity.getPrdflw());
								logger.info(logId + "：产品响应请求处理，流水号:" + flwCode
										+ "，更新流水状态完成");
							} catch (Exception e) {
								logger.error(logId + "：产品响应请求处理，流水号:"
										+ flwCode + "，更新流水表失败");
								// 发送日志
								SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
										ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
										InterOpType.RESRES.getValue(), resCMsgPath, cResMsgPath, TradeLogSta.S.getValue(),
										LogType.TRADE.getValue(), "更新流水表异常" + e.getMessage(), entity);
								
								throw new TradeProcException("产品响应请求处理，流水号:"
										+ flwCode + "，更新流水表失败");
							}
							// 记录日志 
							SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
									ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
									InterOpType.RESRES.getValue(), resCMsgPath, cResMsgPath, TradeLogSta.S.getValue(),
									LogType.TRADE.getValue(), "", entity);

						// 响应失败
						} else {
							entity.getPrdflw().setRespflg(TradeResFlg.RESFAIL.getValue());

							// 更新流水表
							try {
								pInfoService.update(entity.getPrdflw());
							} catch (Exception e) {
								logger.error(logId + "：产品响应请求处理，流水号:"
										+ flwCode + "，更新流水表失败");
								// 发送日志
								SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
										ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
										InterOpType.RESRES.getValue(), resCMsgPath, cResMsgPath, TradeLogSta.F.getValue(),
										LogType.TRADE.getValue(), "更新流水表异常" + e.getMessage(), entity);
								throw new TradeProcException("产品响应请求处理，流水号:"
										+ flwCode + "，更新流水表失败");
							}

							// 发送日志
							SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
									ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
									InterOpType.RESRES.getValue(), resCMsgPath, cResMsgPath, TradeLogSta.S.getValue(),
									LogType.TRADE.getValue(), "", entity);
						}

					// 长连接
					} else if (InterMode.ASYNLONG.getValue().equals(interMode)) {
						// TODO 暂不支持长连接
					} else {
						logger.error(logId + "：产品响应请求处理，流水号:" + flwCode
								+ "，不支持的交互方式：" + interMode);
						
						entity.getPrdflw().setRespflg(TradeResFlg.RESFAIL.getValue());
						// 更新流水
						pInfoService.update(entity.getPrdflw());
						
						// 发送失败日志
						SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
								ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
								InterOpType.RESRES.getValue(), resCMsgPath, "", TradeLogSta.F.getValue(),
								LogType.TRADE.getValue(), "不支持的交互模式", entity);
						
						throw new TradeProcException("产品响应请求处理，流水号:" + flwCode
								+ "，不支持的交互方式：" + interMode);
					}

					// 被动响应
				} else if (ResResType.PASSIVITY.getValue().equals(resResType)) {
					logger.debug("产品响应请求处理，流水号:" + flwCode + "，结果响应方式：被动");
					// TODO
				} else {

					logger.error(logId + "：产品响应请求处理，流水号:" + flwCode
							+ "，不支持的结果响应方式");
					entity.getPrdflw().setRespflg(TradeResFlg.OTHERR.getValue());
					// 更新流水
					pInfoService.update(entity.getPrdflw());
					
					// 发送失败日志
					SystemOperateUtil.sendLog(resClientId, ProcSide.LOCAL.getValue(),
							ProcSide.CLIENT.getValue(),  entity.getTrnType(), 
							InterOpType.RESRES.getValue(), coreResMsgPath, "", TradeLogSta.F.getValue(),
							LogType.TRADE.getValue(), "不支持的结果响应方式", entity);
					throw new TradeProcException("产品响应请求处理，流水号:" + flwCode
							+ "，不支持的结果响应方式");
				}
			}

		} catch (Exception e) {
			logger.error(logId + "：交易请求处理失败，流水号：" + flwCode, e);
			// TODO 更新流水 记录异常日志 记录返回日志
		}
		return entity;
	}

	@Override
	public GeneralBusEntity excuteToCore(GeneralBusEntity entity)
			throws TradeProcException {

		logId = entity.getLogId();// 日志ID

		try {
			logger.info(logId + ":开始向核心发起交易请求......");

			// 业务实体校验
			busEntityCheck(entity);

			logger.info(logId + ":业务实体校验成功");

			recReqLogId = GUIDGenerator.generateId();// 接收请求日志uuid

			senCoreLogId = GUIDGenerator.generateId();// 发送核心请求日志uuid

			trnType = entity.getTrnType();// 交易类型

			String isBatch = entity.getHeader().getIsbatch();
			// 原交易处理
			if (SOPtrnTypt.O.getValue().equals(trnType)) {
				// 批量
				if (IsOrNot.IS.getValue().equals(isBatch)) {
					procOtrades(entity);
					// 单笔
				} else {
					procOtrade(entity);
				}
				// 产品处理
			} else if (SOPtrnTypt.P.getValue().equals(trnType)) {

				// 批量
				if (IsOrNot.IS.getValue().equals(isBatch)) {
					procPtrades(entity);
					// 单笔
				} else {
					procPtrade(entity);
				}

			} else {
				SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(),
						"不支持的交易类型：" + trnType, entity);
				entity.setRecordFlw(false);
				logger.error(logId + ":向核心发起交易请求异常:不支持的交易类型，交易类型：" + trnType);
				throw new TradeProcException("向核心发起交易请求异常:不支持的交易类型，交易类型："
						+ trnType);
			}
		} catch (Exception e) {
			SystemOperateUtil.makeErrAnsRes(BusProcSta.F.getValue(),
					e.getMessage(), entity);
			logger.error(logId + ":向核心发起交易请求异常:" + e.getMessage(), e);
			e.printStackTrace();
			throw new TradeProcException("向核心发起交易请求异常:" + e.getMessage(), e);
		}

		return entity;
	}

	@Override
	public void getTradeRes(GeneralBusEntity entity) throws TradeProcException {
		// TODO Auto-generated method stub

	}

	/**
	 * 处理原交易 单笔
	 * 
	 * @throws TradeProcException
	 */
	private void procOtrade(GeneralBusEntity entity) throws TradeProcException {
		try {
			entity.getCoreHeader().setPrdcod("");
			// 获取渠道交易配置信息
			BCfgdefChnltrd chnlTrdEntity = SystemCfgUtil.getChnlCfgInf(entity
					.getHeader().getChnlcod(),
					entity.getHeader().getIntrncod(), SOPtrnTypt.O.getValue());
			// 获取交易配置信息
			BCfgdefFnttrninfo tradeEntity = SystemCfgUtil
					.getTradeCfgByCode(entity.getHeader().getIntrncod());

			if (chnlTrdEntity == null) {
				logger.error(logId + ":向核心发起交易异常：渠道交易配置不存在");
				entity.setRecordFlw(false);// 不发送日志 不记录流水
				throw new TradeProcException("交易配置不存在");
			}

			if (tradeEntity == null) {
				logger.error(logId + ":向核心发起交易异常：交易配置不存在");
				entity.setRecordFlw(false);// 不发送日志 不记录流水
				throw new TradeProcException("交易配置不存在");
			}

			String stmnum = "".equals(StringUtil.NullToString(chnlTrdEntity.getDelovernum())) ? tradeEntity.getDelovernum() : chnlTrdEntity.getDelovernum();
			String stmOut = "".equals(StringUtil.NullToString(chnlTrdEntity.getDelover())) ? tradeEntity.getDelover() : chnlTrdEntity.getDelover();
			try {
				tmout = SystemOperateUtil.getTrdTmOut(stmOut);
				tmnum = SystemOperateUtil.getTrdTmOutNum(stmnum);
			} catch (Exception e) {
				logger.error(logId + ":向核心发起交易异常：交易超时时长或超时次数必须是数字", e);
				entity.setRecordFlw(false);// 不发送日志 不记录流水
				throw new TradeProcException("向核心发起交易异常：交易超时时长或超时次数必须是数字", e);
			}
			// 交互模式
			interMode = chnlTrdEntity.getIntermode();

			// 报文类型
			String msgType = entity.getHeader().getMsgtype();

			// 原交易请求发起
			if (MsgTemplateType.REQ.getValue().equals(msgType)) {
				logger.info(logId + ":本次请求为原交易业务发起");
				// 获取业务流水号
				String flwCode = JRNGenerator.generateJrn(
						SystemConstant.SYS_CODE, entity.getTrnType(), entity
								.getHeader().getIntrncod());
				logger.info(logId + ":生成前置流水号成功，前置流水号:" + flwCode);
				entity.setFrntJrn(flwCode);

				// 存储客户端请求报文
				try {
					clientReqMsgPath = SystemOperateUtil.saveTradeMsgFile(
							recReqLogId, ProcSide.CLIENT.getValue(),
							ProcSide.LOCAL.getValue(),
							InterOpType.REQSEND.getValue(), "req", entity,
							entity.getDownMsg());
				} catch (Exception e) {
					logger.error(logId + "：向核心发起交易异常：存储客户端请求报文错误");
				}
				try {
					logger.info(logId + ":开始拼装请求核心报文......，前置流水号:" + flwCode);
					// 拼装请求核心报文
					transferService.transToReqCoreMsg(entity);
					logger.info(logId + ":拼装请求核心报文成功，前置流水号:" + flwCode + "，内容："
							+ new String(entity.getCoreMsg(), SystemConstant.DEFAULT_CHARSET));
				} catch (TradeMsgTransfException e) {
					logger.error(logId + ":向核心发起交易异常：报文转换错误", e);
					entity.setRecordFlw(false);
					throw new TradeProcException("向核心发起交易异常：报文转换错误", e);
				} catch (Exception e) {
					logger.error(logId + ":向核心发起交易异常：报文转换错误", e);
					entity.setRecordFlw(false);
					throw new TradeProcException("向核心发起交易异常：报文转换错误", e);
				}

				// 存储请求核心报文
				try {
					reqCoreMsgPath = SystemOperateUtil.saveTradeMsgFile(
							senCoreLogId, ProcSide.LOCAL.getValue(),
							ProcSide.CORE.getValue(),
							InterOpType.REQSEND.getValue(), "req", entity,
							entity.getCoreMsg());
				} catch (Exception e) {
					logger.error(logId + "：向核心发起交易异常：存储客户端请求报文错误");
				}
				// 生成入库实体
				FDatPrdtrnflw flwEntity = new FDatPrdtrnflw();
				try {
					BeanUtils.copyProperties(flwEntity, entity.getCoreHeader());
				} catch (Exception e) {
					logger.error(logId + "：向核心发起交易异常：转换入库实体bean异常，流水号："
							+ flwCode);
					entity.setRecordFlw(false);
					throw new TradeProcException("向核心发起交易异常：转换入库实体bean异常，流水号："
							+ flwCode);
				}
				entity.setOtrdflw(flwEntity);
				flwEntity.setOuttrncod(entity.getHeader().getOuttrncod());// 外部交易码
				flwEntity.setRespflg(TradeResFlg.NORES.getValue());// 处理状态 没有结果
				flwEntity.setReqmsg(reqCoreMsgPath); // 请求报文路径

				// 异步交互时计算超时时间
				if (InterMode.ASYNSHORT.getValue().equals(interMode)
						|| InterMode.ASYNLONG.getValue().equals(interMode)) {
					Date date = new Date();
					flwEntity.setTmout(date.getTime() + tmout + "");
				}
				boolean flg = true;
				try {
					// 流水入库
					oInfoService.save(entity.getOtrdflw());
					logger.info(logId + ":向核心发起交易：存储交易流水成功，前置流水号:" + flwCode);
				} catch (Exception e) {
					logger.error(
							logId + ":向核心发起交易异常：存储交易流水异常，前置流水号:" + flwCode, e);
					flg = false;
					try {
						// 重发交易
						if (SystemOperateUtil.isUniqueErr(e.getCause()
								.getMessage())) {
							procTmOut(entity);
						} else {
							entity.setRecordFlw(false);
							throw new TradeProcException(
									"向核心发起交易异常：存储报文交易流水失败", e);
						}
					} catch (Exception e1) {
						logger.error(logId + "：向核心发起交易异常：重发处理失败。", e);
						entity.setRecordFlw(false);
						throw new TradeProcException("向核心发起交易异常：重发处理失败。", e);
					}
				}

				if (flg) {
					// 异步
					if (InterMode.ASYNSHORT.getValue().equals(interMode)
							|| InterMode.ASYNLONG.getValue().equals(interMode)) {
						// 向核心发送请求
						try {
							excuteTradeToCoreAsny(entity);
						} catch (Exception e) {
							logger.error(logId + ":向核心发起原交易发生异常：队列发送消息异常", e);
							// 更新数据库
							entity.getOtrdflw().setRespflg(
									TradeResFlg.OTHERR.getValue());
							oInfoService.update(entity.getOtrdflw());
							throw new TradeProcException(
									"向核心发起原交易发生异常：队列发送消息异常", e);
						}

						// 同步
					} else if (InterMode.SYNLONG.getValue().equals(interMode)
							|| InterMode.SYNSHORT.getValue().equals(interMode)) {
						// 向核心发送请求
						try {
							if (!excuteTradeToCoreSny(entity)) {
								entity.getOtrdflw().setRespflg(resSta);
								oInfoService.update(entity.getOtrdflw());
								logger.error(logId + ":向核心发起原交易发生异常：交易处理异常");
								throw new TradeProcException("交易处理异常");
							}

						} catch (Exception e) {
							// 更新数据库
							entity.getOtrdflw().setRespflg(
									TradeResFlg.OTHERR.getValue());
							oInfoService.update(entity.getOtrdflw());
							logger.error(logId + ":向核心发起原交易发生异常：同步");
							throw new TradeProcException("交易处理异常");
						}
					} else {
						// 更新数据库
						entity.getOtrdflw().setRespflg(
								TradeResFlg.OTHERR.getValue());
						oInfoService.update(entity.getOtrdflw());
						logger.error(logId + ":向核心发起原交易发生异常：不支持的交互类型。交互类型："
								+ interMode);
						throw new TradeProcException(
								"向核心发起原交易发生异常：不支持的交互类型。交互类型：" + interMode);
					}
				}
			} else {
				logger.error(logId + ":向核心发起原交易发生异常：不支持的报文类型，报文类型：" + msgType);
				entity.setRecordFlw(false);// 不发送日志 不记录流水
				throw new TradeProcException("向核心发起原交易发生异常：不支持的报文类型，报文类型："
						+ msgType);
			}
		} catch (Exception e) {
			logger.error(logId + ":向核心发起原交易发生异常：未知", e);
			throw new TradeProcException(e.getMessage(), e);
		}
	}

	/**
	 * 处理原交易 批量
	 * 
	 * @throws TradeProcException
	 */
	private void procOtrades(GeneralBusEntity entity) throws TradeProcException {

		try {
			logger.info(logId + ":本次交易为批量原交易发起");

			try {
				logger.info(logId + ":开始初步解析批量发起报文");
				transferService.initBatchMsg(entity);
				logger.info(logId + ":初步解析批量发起报文完成");
			} catch (Exception e) {

			}
		} catch (Exception e) {
			logger.error(logId + ":处理批量原交易异常：未知", e);
			throw new TradeProcException("处理批量原交易异常：未知", e);
		}

	}

	/**
	 * 处理产品 单笔
	 * 
	 * @param entity
	 * @throws TradeProcException
	 */
	private void procPtrade(GeneralBusEntity entity) throws TradeProcException {
		try {
			entity.getCoreHeader().setIntrncod("");
			// 获取交易配置信息
			BCfgdefChnltrd chnlTrdEntity = SystemCfgUtil.getChnlCfgInf(entity
					.getHeader().getChnlcod(), entity.getHeader().getPrdcod(),
					SOPtrnTypt.P.getValue());

			if (chnlTrdEntity == null) {
				logger.error(logId + ":向核心发起产品交易异常：产品配置不存在");
				entity.setRecordFlw(false);// 不发送日志 不记录流水
				throw new TradeProcException("向核心发起产品交易异常：产品配置不存在");
			}

//			String stmnum = "".equals(StringUtil.NullToString(chnlTrdEntity.getDelovernum())) ? tradeEntity.getDelovernum() : chnlTrdEntity.getDelovernum();
//			String stmOut = "".equals(StringUtil.NullToString(chnlTrdEntity.getDelover())) ? tradeEntity.getDelover() : chnlTrdEntity.getDelover();
			try {
				tmout = SystemOperateUtil.getTrdTmOut(chnlTrdEntity.getDelover());
				tmnum = 3;
			} catch (Exception e) {
				logger.error(logId + ":向核心发起产品交易异常：产品超时时长或超时次数必须是数字");
				entity.setRecordFlw(false);// 不发送日志 不记录流水
				throw new TradeProcException("向核心发起产品交易异常：产品超时时长或超时次数必须是数字");
			}

			// 交互模式
			interMode = chnlTrdEntity.getIntermode();

			// 报文类型
			String msgType = entity.getHeader().getMsgtype();

			// 请求发起
			if (MsgTemplateType.REQ.getValue().equals(msgType)) {
				logger.info(logId + ":本次请求为产品交易发起");
				String flwCode = JRNGenerator.generateJrn(
						SystemConstant.SYS_CODE, entity.getTrnType(), entity
								.getHeader().getPrdcod());
				logger.info(logId + ":生成交易流水号成功，前置流水号:" + flwCode);

				entity.setFrntJrn(flwCode);

				// 存储客户端请求报文
				try {
					clientReqMsgPath = SystemOperateUtil.saveTradeMsgFile(
							recReqLogId, ProcSide.CLIENT.getValue(),
							ProcSide.LOCAL.getValue(),
							InterOpType.REQSEND.getValue(), "req", entity,
							entity.getDownMsg());
				} catch (Exception e) {
					logger.error(logId + "：业务处理异常：存储客户端请求报文错误");
				}

				try {
					logger.info(logId + ":开始拼装请求核心报文......，前置流水号:" + flwCode);
					// 拼装请求核心报文
					transferService.transToReqCoreMsg(entity);
					logger.info(logId + ":拼装请求核心报文成功，前置流水号:" + flwCode + "，内容："
							+ new String(entity.getCoreMsg(), SystemConstant.DEFAULT_CHARSET));
				} catch (TradeMsgTransfException e) {
					logger.error(logId + ":向核心发起产品交易异常：报文转换错误", e);
					entity.setRecordFlw(false);// 不发送日志 不记录流水
					throw new TradeProcException("向核心发起产品交易异常：报文转换错误", e);
				}

				// 存储请求核心报文
				try {
					reqCoreMsgPath = SystemOperateUtil.saveTradeMsgFile(
							senCoreLogId, ProcSide.LOCAL.getValue(),
							ProcSide.CORE.getValue(),
							InterOpType.REQSEND.getValue(), "req", entity,
							entity.getCoreMsg());
				} catch (Exception e) {
					logger.error(logId + "：业务处理异常：存储客户端请求报文错误");
				}

				// 生成入库实体
				FDatPrdinfoflw flwEntity = new FDatPrdinfoflw();

				try {
					BeanUtils.copyProperties(flwEntity, entity.getCoreHeader());
				} catch (Exception e) {
					logger.error(logId + "：向核心发起交易异常：转换入库实体bean异常，流水号："
							+ flwCode);
					entity.setRecordFlw(false);
					throw new TradeProcException("向核心发起交易异常：转换入库实体bean异常，流水号："
							+ flwCode);
				}
				entity.setPrdflw(flwEntity);
				flwEntity.setPrdcod(entity.getHeader().getPrdcod());// 产品码
				flwEntity.setRespflg(TradeResFlg.NORES.getValue());// 处理状态 没有结果
				// 报文路径
				flwEntity.setReqmsg(reqCoreMsgPath); // 请求报文路径

				// 异步交互时计算超时时间
				if (InterMode.ASYNSHORT.getValue().equals(interMode)
						|| InterMode.ASYNLONG.getValue().equals(interMode)) {
					Date date = new Date();
					flwEntity.setTmout(date.getTime() + tmout + "");
				}
				boolean flg = true;
				try {
					// 流水入库
					pInfoService.save(entity.getPrdflw());
					logger.info(logId + ":存储交易流水成功，前置流水号:" + flwCode);
				} catch (Exception e) {
					logger.error("向核心发起交易异常：存储报文交易流水异常", e);
					flg = false;
					try {
						// 重发交易
						if (SystemOperateUtil.isUniqueErr(e.getCause()
								.getMessage())) {
							procTmOut(entity);
						} else {
							entity.setRecordFlw(false);
							throw new TradeProcException(
									"向核心发起产品交易异常：存储报文交易流水失败", e);
						}
					} catch (Exception e1) {
						logger.error(logId + "：向核心发起产品交易异常：重发处理失败。", e);
						entity.setRecordFlw(false);
						throw new TradeProcException("向核心发起产品交易异常：重发处理失败。", e);
					}
				}

				if (flg) {
					// 异步处理
					if (InterMode.ASYNSHORT.getValue().equals(interMode)
							|| InterMode.ASYNLONG.getValue().equals(interMode)) {
						// 向核心发送请求
						try {
							excutePrdToCoreAsny(entity);
						} catch (Exception e) {
							logger.error(
									logId + ":向核心发起产品交易交易发生异常："
											+ e.getMessage(), e);
							// 更新数据库
							entity.getPrdflw().setRespflg(
									TradeResFlg.OTHERR.getValue());
							pInfoService.update(entity.getPrdflw());
							throw new TradeProcException("向核心发起产品交易发生异常："
									+ e.getMessage(), e);
						}
						// 同步
					} else if (InterMode.SYNLONG.getValue().equals(interMode)
							|| InterMode.SYNSHORT.getValue().equals(interMode)) {
						// 向核心发送请求
						try {
							if (!excuteTradeToCoreSny(entity)) {
								entity.getPrdflw().setRespflg(resSta);
								pInfoService.update(entity.getPrdflw());
								logger.error(logId + ":向核心发起产品发生异常：交易处理异常");
								throw new TradeProcException("交易处理异常");
							}
						} catch (Exception e) {
							logger.error(logId + ":向核心发起产品交易发生异常：重新发送交易异常");
							// 更新数据库
							entity.getPrdflw().setRespflg(
									TradeResFlg.OTHERR.getValue());
							pInfoService.update(entity.getPrdflw());
							throw new TradeProcException("向核心发起产品交易发生异常："
									+ e.getMessage());
						}
					} else {
						// 更新数据库
						entity.getPrdflw().setRespflg(
								TradeResFlg.OTHERR.getValue());
						pInfoService.update(entity.getPrdflw());
						logger.error(logId + ":向核心发起产品交易发生异常：不支持的交互类型。交互类型："
								+ interMode);
						throw new TradeProcException(
								"向核心发起产品交易发生异常：不支持的交互类型。交互类型：" + interMode);
					}
				}

			} else {
				logger.error(logId + ":向核心发起产品交易发生异常：不支持的报文类型，报文类型：" + msgType);
				throw new TradeProcException("向核心发起产品交易发生异常：不支持的报文类型，报文类型："
						+ msgType);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TradeProcException(e.getMessage(), e);
		}
	}

	/**
	 * 处理产品 批量
	 * 
	 * @param entity
	 * @throws TradeProcException
	 */
	private void procPtrades(GeneralBusEntity entity) throws TradeProcException {
		// TODO
	}

	@Override
	public void afterExcute(GeneralBusEntity entity) throws TradeProcException {

		String sta = "";

		// 发送日志
		try {
			SystemOperateUtil.sendLog(recReqLogId, ProcSide.CLIENT.getValue(),
					ProcSide.LOCAL.getValue(), trnType,
					InterOpType.REQSEND.getValue(), clientReqMsgPath,
					resClientMsgPath, entity.getResSta(),
					LogType.TRADE.getValue(), entity.getErrMsg(), entity);
		} catch (Exception e) {
			logger.error(logId + ":服务处理异常:返回客户端后的后续逻辑操作出现异常,记录日志异常,流水号："
					+ entity.getFrntJrn(), e);
			e.printStackTrace();
		}

		// 更新流水
		try {
			// 同步
			if (InterMode.SYNLONG.getValue().equals(interMode)
					|| InterMode.SYNSHORT.getValue().equals(interMode)) {
				// 处理成功
				if (BusProcSta.S.getValue().equals(entity.getProcSta())) {
					// 响应成功
					if (ResSta.S.getValue().equals(entity.getResSta())) {
						sta = TradeResFlg.SUCESS.getValue();
					} else {
						sta = TradeResFlg.OTHERR.getValue();
					}
				}
				// 异步
			} else if (InterMode.ASYNLONG.getValue().equals(interMode)
					|| InterMode.ASYNSHORT.getValue().equals(interMode)) {
				// 处理成功
				if (BusProcSta.S.getValue().equals(entity.getProcSta())) {
					// 响应失败
					if (ResSta.F.getValue().equals(entity.getResSta())) {
						sta = TradeResFlg.OTHERR.getValue();
					}
				}
			}

			// 原交易
			if (SOPtrnTypt.O.getValue().equals(trnType)) {
				if(!sta.equals(""))
				entity.getOtrdflw().setRespflg(sta);

				oInfoService.update(entity.getOtrdflw());
				// 产品
			} else if (SOPtrnTypt.P.getValue().equals(trnType)) {
				if(!sta.equals(""))
				entity.getPrdflw().setRespflg(sta);

				pInfoService.update(entity.getPrdflw());
			}
		} catch (Exception e) {
			logger.error(logId + ":服务处理异常:返回客户端后的后续逻辑操作出现异常,更新流水异常,流水号："
					+ entity.getFrntJrn(), e);
			e.printStackTrace();
			throw new TradeProcException("服务处理异常:返回客户端后的后续逻辑操作出现异常,更新流水异常");
		}
	}

	/**
	 * 校验业务实体
	 * 
	 * @param entity
	 * @throws TradeProcException
	 */
	private void busEntityCheck(GeneralBusEntity entity)
			throws TradeProcException {

		if (entity == null)
			throw new TradeProcException("业务处理异常：业务实体不能为空");

		if (entity.getDownMsg() == null || entity.getDownMsg().length == 0)
			throw new TradeProcException("业务处理异常：请求数据不能为空");

		if (entity.getTrnType() == null || "".equals(entity.getTrnType()))
			throw new TradeProcException("业务处理异常：交易类型不能为空");
	}

	/**
	 * 超时处理
	 * 
	 * @param entity
	 * @throws TradeProcException
	 */
	private void procTmOut(GeneralBusEntity entity) throws TradeProcException {
		try {
			// 客户端流水号 和渠道号不能为空
			String clnJrn = StringUtil.NullToString(entity.getCoreHeader()
					.getClntjrn());// 客户端流水号

			if ("".equals(clnJrn)) {
				logger.error(logId + ":向核心发起原交易发生异常：重发交易时客户端流水号不能为空。");
				throw new TradeProcException("向核心发起原交易发生异常：重发交易时客户端流水号不能为空。");
			}

			// 日志实体 若异步的话 查看是否已向核心发送过了（条件：交易流水号 发送方：本地 ，目的方：核心 ，操作类型：请求发起
			// ），若已发送过了则不再发送，同步直接再次向核心发送
			FLogTrnflw logEntity = new FLogTrnflw();
			logEntity.setOptype(InterOpType.REQSEND.getValue());
			logEntity.setLaunch(ProcSide.LOCAL.getValue());
			logEntity.setDestination(ProcSide.CORE.getValue());
			logEntity.setResflg(ResSta.S.getValue());

			// 原交易
			if (SOPtrnTypt.O.getValue().equals(trnType)) {

				// 查看流水表中的交易信息 若不存在报错
				FDatPrdtrnflw flwEntity = new FDatPrdtrnflw();
				flwEntity.setChnlcod(entity.getHeader().getChnlcod());
				flwEntity.setClntjrn(clnJrn);
				List list = null;
				try {
					list = oInfoService.find(flwEntity);
				} catch (Exception e) {
					logger.error(logId + ":向核心发起原交易发生异常：获取交易内容失败。");
					throw new TradeProcException("向核心发起原交易发生异常：获取交易内容失败。");
				}
				if (list != null && list.size() > 0) {
					flwEntity = (FDatPrdtrnflw) list.get(0);
					list = null;
					// 重新设置交易流水号
					entity.getCoreHeader().setFnttrnjrn(
							flwEntity.getFnttrnjrn());
					entity.getCoreHeader().setFnttrndte(
							flwEntity.getFnttrndte());
					entity.getCoreHeader().setFnttrntim(
							flwEntity.getFnttrntim());
					entity.setFrntJrn(flwEntity.getFnttrnjrn());
					entity.setTrnDate(flwEntity.getFnttrndte());
					entity.setTrnTime(flwEntity.getFnttrntim());
					entity.setOtrdflw(flwEntity);
					// 发送重发日志 目的方：本地
					try {
						SystemOperateUtil.sendLog(null, null,
								ProcSide.LOCAL.getValue(),
								SOPtrnTypt.O.getValue(), null, null, null,
								null, LogType.TIMEOUT.getValue(), null, entity);
					} catch (Exception e) {
						logger.error(logId + ":向核心发起原交易发生异常：发送超时日志异常。");
						throw new TradeProcException("向核心发起原交易发生异常：发送超时日志异常。");
					}

					// 获取交易日志
					logEntity.setFnttrnjrn(flwEntity.getFnttrnjrn());
					logEntity.setTrntype(SOPtrnTypt.O.getValue());
					try {
						list = logServcie.find(logEntity);
					} catch (Exception e) {
						logger.error(logId + ":向核心发起原交易发生异常：获取交易日志异常。");
						throw new TradeProcException("向核心发起原交易发生异常：获取交易日志异常。");
					}

					// 异步
					if (InterMode.ASYNSHORT.getValue().equals(interMode)
							|| InterMode.ASYNLONG.getValue().equals(interMode)) {
						// 交易日志不为空 不需再向核心发送请求
						if (list != null && list.size() > 0) {
							logger.info(logId + ":已经向核心发送过请求，不再发送");
						} else {
							// 向核心发送请求
							try {
								excuteTradeToCoreAsny(entity);
							} catch (Exception e) {
								logger.error(logId + ":向核心发起原交易发生异常：重新发送交易异常");
								throw new TradeProcException(
										"向核心发起原交易发生异常：重新发送交易异常。交互类型："
												+ interMode);
							}

						}

						// 同步
					} else if (InterMode.SYNLONG.getValue().equals(interMode)
							|| InterMode.SYNSHORT.getValue().equals(interMode)) {

						// 向核心发送请求
						try {
							excuteTradeToCoreSny(entity);
						} catch (Exception e) {
							logger.error(logId + ":向核心发起原交易发生异常：重新发送交易异常");
							throw new TradeProcException(
									"向核心发起原交易发生异常：重新发送交易异常。交互类型：" + interMode);
						}
					} else {
						logger.error(logId + ":向核心发起原交易发生异常：不支持的交互类型。交互类型："
								+ interMode);
						throw new TradeProcException(
								"向核心发起原交易发生异常：不支持的交互类型。交互类型：" + interMode);
					}
				} else {
					logger.error(logId + ":向核心发起原交易发生异常：重发交易异常，获取不到原交易内容。");
					throw new TradeProcException(
							"向核心发起原交易发生异常：重发交易异常，获取不到原交易内容。");
				}

				// 产品
			} else if (SOPtrnTypt.P.getValue().equals(trnType)) {

				// 获取产品交易流水信息
				FDatPrdinfoflw flwEntity = new FDatPrdinfoflw();
				flwEntity.setChnlcod(entity.getHeader().getChnlcod());
				flwEntity.setClntjrn(clnJrn);

				List list = null;
				try {
					list = pInfoService.find(flwEntity);
				} catch (Exception e) {
					logger.error(logId + ":向核心发起原交易发生异常：获取交易内容失败。");
					throw new TradeProcException("向核心发起原交易发生异常：获取交易内容失败。");
				}
				// 产品交易流水信息必须存在
				if (list != null && list.size() > 0) {
					flwEntity = (FDatPrdinfoflw) list.get(0);
					entity.getCoreHeader().setFnttrnjrn(
							flwEntity.getFnttrnjrn());
					entity.getCoreHeader().setFnttrndte(
							flwEntity.getFnttrndte());
					entity.getCoreHeader().setFnttrntim(
							flwEntity.getFnttrntim());
					entity.setTrnDate(flwEntity.getFnttrndte());
					entity.setTrnTime(flwEntity.getFnttrntim());
					entity.setFrntJrn(flwEntity.getFnttrnjrn());
					entity.setPrdflw(flwEntity);
					list = null;

					// 发送超时重发日志 目的方：本地
					// 发送重发日志 目的方：本地
					try {
						SystemOperateUtil.sendLog(null, null,
								ProcSide.LOCAL.getValue(),
								SOPtrnTypt.P.getValue(), null, null, null,
								null, LogType.TIMEOUT.getValue(), null, entity);
					} catch (Exception e) {
						logger.error(logId + ":向核心发起原交易发生异常：发送超时日志异常。");
						throw new TradeProcException("向核心发起原交易发生异常：发送超时日志异常。");
					}

					// 获取交易日志
					logEntity.setFnttrnjrn(flwEntity.getFnttrnjrn());
					logEntity.setTrntype(SOPtrnTypt.P.getValue());
					try {
						list = logServcie.find(logEntity);
					} catch (Exception e) {
						logger.error(logId + ":向核心发起原交易发生异常：获取交易日志异常。");
						throw new TradeProcException("向核心发起原交易发生异常：获取交易日志异常。");
					}

					// 异步
					if (InterMode.ASYNSHORT.getValue().equals(interMode)
							|| InterMode.ASYNLONG.getValue().equals(interMode)) {

						// 交易日志不存在 不需再向核心发送
						if (list != null && list.size() > 0) {
							logger.info(logId + ":已经向核心发送过请求，不再发送");
						} else {
							// 向核心发送请求
							try {
								excutePrdToCoreAsny(entity);
							} catch (Exception e) {
								logger.error(logId + ":向核心发起原交易发生异常：重新发送交易异常");
								throw new TradeProcException(
										"向核心发起原交易发生异常：重新发送交易异常。交互类型："
												+ interMode);
							}

						}

						// 同步
					} else if (InterMode.ASYNSHORT.getValue().equals(interMode)
							|| InterMode.ASYNLONG.getValue().equals(interMode)) {

						// 向核心发送请求
						try {
							excuteTradeToCoreSny(entity);
						} catch (Exception e) {
							logger.error(logId + ":向核心发起原交易发生异常：重新发送交易异常");
							throw new TradeProcException(
									"向核心发起原交易发生异常：重新发送交易异常。交互类型：" + interMode);
						}
					} else {
						logger.error(logId + ":向核心发起原交易发生异常：不支持的交互类型。交互类型："
								+ interMode);
						throw new TradeProcException(
								"向核心发起原交易发生异常：不支持的交互类型。交互类型：" + interMode);
					}
				} else {
					logger.error(logId + ":向核心发起原交易发生异常：获取不到原交易内容。");
					throw new TradeProcException("向核心发起原交易发生异常：获取不到原交易内容。");
				}

			}

		} catch (Exception e) {
			logger.error(logId + ":向核心发起原交易发生异常：重发处理异常，未知。");
			throw new TradeProcException("向核心发起原交易发生异常：重发处理异常，未知。");
		}
	}

	/**
	 * 向核心发起异步交易请求
	 * 
	 * @param entity
	 */
	private void excuteTradeToCoreAsny(GeneralBusEntity entity)
			throws TradeProcException {
		boolean flg = false;
		String errMsg = "";
		// 异步处理
		try {
			logger.info(logId + ":本次交易模式为异步，开始向队列发送交易请求......，前置流水号:"
					+ entity.getFrntJrn());
			// 发送核心
			BCfgdefQueue queEntity = SystemCfgUtil.getQueInfoByTrCode(entity
					.getHeader().getChnlcod(),
					entity.getHeader().getIntrncod(), SOPtrnTypt.O.getValue(),
					QueType.REQ.getValue());
			// 交易队列未配置
			if (queEntity == null || queEntity.getQuetopic() == null
					|| "".equals(queEntity.getQuetopic())) {
				logger.error(logId + ":向核心发送交易异常：队列信息为空");
				throw new TradeProcException("向核心发送交易异常：队列信息为空");
			}

			JSONObject finisJson = null;
			try {
				// 转换报文头
				finisJson = transferHeader(entity);
			} catch (Exception e) {
				logger.error(logId + ":向核心发送交易异常：组装报文头异常，", e);
				throw new TradeProcException("向核心发送交易异常：组装报文头异常");
			}

			String topic = queEntity.getQuetopic();

			// 队列实体
			KafkaQueEntity kfkQueEntity = new KafkaQueEntity();
			kfkQueEntity.setTopic(topic);
			kfkQueEntity.setMessage(finisJson.toString());
			queOperator.offer(kfkQueEntity);

			// 发送请求成功核心日志
			try {
				SystemOperateUtil.sendLog(senCoreLogId,
						ProcSide.LOCAL.getValue(), ProcSide.CORE.getValue(),
						SOPtrnTypt.O.getValue(),
						InterOpType.REQSEND.getValue(), reqCoreMsgPath,
						coreResMsgPath, TradeLogSta.S.getValue(),
						LogType.TRADE.getValue(), "", entity);
			} catch (Exception e) {
				logger.error(logId + ":向核心发送交易异常：发送交易日志失败", e);
			}
		} catch (QueueOperatException e) {
			logger.error(logId + ":向核心发送交易异常：向队列发送任务失败", e);
			errMsg = e.getClass().toString() + ":" + e.getMessage();
			flg = true;
		} catch (Exception e) {
			logger.error(logId + ":向核心发送交易异常：未知", e);
			errMsg = e.getClass().toString() + ":" + e.getMessage();
			flg = true;
		}

		if (flg) {
			// 发送请求核心异常日志
			try {
				SystemOperateUtil.sendLog(senCoreLogId,
						ProcSide.LOCAL.getValue(), ProcSide.CORE.getValue(),
						SOPtrnTypt.O.getValue(),
						InterOpType.REQSEND.getValue(), reqCoreMsgPath,
						coreResMsgPath, TradeLogSta.F.getValue(),
						LogType.TRADE.getValue(), errMsg, entity);
			} catch (Exception e) {
				logger.error(logId + ":向核心发送交易异常：发送交易日志失败", e);
				throw new TradeProcException("向核心发送交易异常：向队列发送任务失败", e);
			}
			throw new TradeProcException("向核心发送交易异常：向队列发送任务失败");
		}

		logger.info(logId + ":向队列发送交易请求成功，前置流水号:" + entity.getFrntJrn());
		// 处理状态成功
		entity.setProcSta(BusProcSta.S.getValue());
		entity.setProcReslut("接收请求成功");
		entity.getCoreHeader().setStatus(BusProcSta.S.getValue());
		try {
			try {
				// 开始拼接返回报文
				transferService.transToAsynLResMsg(entity);
			} catch (TradeMsgTransfException e) {
				logger.error(logId + ":向核心发送交易异常：拼接返回信息异常", e);
				throw new TradeProcException("向核心发送交易异常：拼接返回信息异常", e);
			}


			byte[] upMsg = entity.getUpMsg();
			entity.getHeader().setMsglen(upMsg.length);
			entity.getHeader().setMsgtype(MsgTemplateType.ASYNSRES.getValue());
			entity.getHeader().setCheckcod(
					MD5Utils.hash(entity.getHeader().getChnlcod()
							+ entity.getDesKeyInf().getKey()
							+ new String(upMsg, SystemConstant.DEFAULT_CHARSET), "MD5"));
		} catch (Exception e) {
			logger.error(logId + ":向核心发送交易异常：拼接返回信息异常", e);
			throw new TradeProcException("向核心发送交易异常：拼接返回信息异常", e);
		}

	}

	/**
	 * 向核心发起同步交易请求
	 * 
	 * @param entity
	 */
	private boolean excuteTradeToCoreSny(GeneralBusEntity entity)
			throws Exception {
		// 同步处理
		OutputStream os = null;
		InputStream in = null;
		ByteArrayOutputStream bos = null;
		toCoreNum = toCoreNum + 1; // 发送次数加1
		// 如果超过最大次数 直接返回
		if (toCoreNum > tmnum) {
			return false;
		}

		if (toCoreNum > 1) {
			// 重新生成发送核心日志ID
			senCoreLogId = GUIDGenerator.generateId();
			// 发送重发日志 目的方：核心
			try {
				SystemOperateUtil.sendLog(null, null, ProcSide.CORE.getValue(),
						entity.getTrnType(), null, null, null, null,
						LogType.TIMEOUT.getValue(), null, entity);
			} catch (Exception e) {
				logger.error(logId + ":向核心发起原交易发生异常：发送日志异常。", e);
				e.printStackTrace();
			}
		}

		// 获取同步交易地址
		String coreSynUrl = StringUtil.NullToString(SystemCfgUtil
				.getSysParmValByCode(SysParamCode.CORE_SYN_URL.getValue()));
		if ("".equals(coreSynUrl)) {
			logger.error(logId + ":向核心发送交易异常：核心同步请求地址不存在");
			resSta = TradeResFlg.OTHERR.getValue();
			try {
				SystemOperateUtil.sendLog(senCoreLogId,
						ProcSide.LOCAL.getValue(), ProcSide.CORE.getValue(),
						SOPtrnTypt.O.getValue(),
						InterOpType.REQSEND.getValue(), reqCoreMsgPath, "",
						TradeLogSta.F.getValue(), LogType.TRADE.getValue(),
						"核心同步请求地址不存在", entity);
			} catch (Exception e) {
				logger.error(logId + ":向核心发起原交易发生异常：发送日志异常。", e);
				e.printStackTrace();
			}
			return false;
		}

		JSONObject finisJson = null;
		try {
			// 转换报文头
			finisJson = transferHeader(entity);
		} catch (Exception e) {
			resSta = TradeResFlg.OTHERR.getValue();
			e.printStackTrace();
			try {
				SystemOperateUtil.sendLog(senCoreLogId,
						ProcSide.LOCAL.getValue(), ProcSide.CORE.getValue(),
						SOPtrnTypt.O.getValue(),
						InterOpType.REQSEND.getValue(), reqCoreMsgPath, "",
						TradeLogSta.F.getValue(), LogType.TRADE.getValue(),
						"转换请求核心报文异常", entity);
			} catch (Exception e1) {
				logger.error(logId + ":向核心发起原交易发生异常：发送日志异常。", e1);
				e1.printStackTrace();
			}

			return false;
		}

		try {
			URL url = new URL(coreSynUrl);
			HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection();
			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);
			urlConn.setConnectTimeout(tmout);// 超时时间
			urlConn.setReadTimeout(tmout);
			os = urlConn.getOutputStream();
			os.write(finisJson.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));
			os.flush();

			in = urlConn.getInputStream();
		} catch (Exception e) {
			logger.error(logId + ":向核心发送交易异常：连接异常", e);
			resSta = TradeResFlg.OTHERR.getValue();

			e.printStackTrace();
			try {
				SystemOperateUtil.sendLog(senCoreLogId,
						ProcSide.LOCAL.getValue(), ProcSide.CORE.getValue(),
						SOPtrnTypt.O.getValue(),
						InterOpType.REQSEND.getValue(), reqCoreMsgPath, "",
						TradeLogSta.F.getValue(), LogType.TRADE.getValue(),
						e.getMessage(), entity);
			} catch (Exception e1) {
				logger.error(logId + ":向核心发起原交易发生异常：发送日志异常。", e1);
				e1.printStackTrace();
			}
			return excuteTradeToCoreSny(entity);

		}

		byte[] buffer = new byte[100];
		bos = new ByteArrayOutputStream();
		int len = 0;

		try {
			while ((len = in.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
		} catch (Exception e) {
			logger.error(logId + ":向核心发送交易异常：读取返回内容异常,第" + toCoreNum + "次请求", e);
			resSta = TradeResFlg.OTHERR.getValue();
			e.printStackTrace();
			try {
				SystemOperateUtil.sendLog(senCoreLogId,
						ProcSide.LOCAL.getValue(), ProcSide.CORE.getValue(),
						SOPtrnTypt.O.getValue(),
						InterOpType.REQSEND.getValue(), reqCoreMsgPath, "",
						TradeLogSta.F.getValue(), LogType.TRADE.getValue(),
						"读取返回内容失败", entity);
			} catch (Exception e1) {
				logger.error(logId + ":向核心发起原交易发生异常：发送日志异常。", e1);
				e1.printStackTrace();
			}

			return excuteTradeToCoreSny(entity);
		}

		// 核心返回的数据
		byte[] bs = bos.toByteArray();
		if (bs == null || bs.length == 0) {
			logger.error(logId + ":向核心发送交易异常：核心返回数据为空,第" + toCoreNum + "次请求");
			resSta = TradeResFlg.OTHERR.getValue();
			try {
				SystemOperateUtil.sendLog(senCoreLogId,
						ProcSide.LOCAL.getValue(), ProcSide.CORE.getValue(),
						SOPtrnTypt.O.getValue(),
						InterOpType.REQSEND.getValue(), reqCoreMsgPath, "",
						TradeLogSta.F.getValue(), LogType.TRADE.getValue(),
						"核心返回数据为空", entity);
			} catch (Exception e1) {
				logger.error(logId + ":向核心发起原交易发生异常：发送日志异常。", e1);
				e1.printStackTrace();
			}

			return excuteTradeToCoreSny(entity);
		}

		// 存储核心返回报文
		entity.setCoreMsg(bs);
		// 保存核心返回报文
		try {
			coreResMsgPath = SystemOperateUtil.saveTradeMsgFile(senCoreLogId,
					ProcSide.LOCAL.getValue(), ProcSide.CORE.getValue(),
					InterOpType.REQSEND.getValue(), "res", entity, bs);
		} catch (Exception e) {
			logger.error(logId + "：向核心发起交易异常：存储客户端请求报文错误", e);
			e.printStackTrace();
		}

		// 发送请求核心成功日志
		try {
			SystemOperateUtil.sendLog(senCoreLogId, ProcSide.LOCAL.getValue(),
					ProcSide.CORE.getValue(), SOPtrnTypt.O.getValue(),
					InterOpType.REQSEND.getValue(), reqCoreMsgPath,
					coreResMsgPath, TradeLogSta.S.getValue(),
					LogType.TRADE.getValue(), "", entity);
		} catch (Exception e) {
			logger.error(logId + "：向核心发起交易异常：存储交易日志失败", e);
			e.printStackTrace();
		}

		// 更新数据库
		try {
			// 更新数据库状态：核心已返回结果
			if (entity.getTrnType().equals(SOPtrnTypt.O.getValue())) {

				entity.getOtrdflw().setRespflg(TradeResFlg.CORERES.getValue());
				oInfoService.update(entity.getOtrdflw());
			} else if (entity.getTrnType().equals(SOPtrnTypt.P.getValue())) {
				entity.getPrdflw().setRespflg(TradeResFlg.CORERES.getValue());
				pInfoService.update(entity.getPrdflw());
			}
		} catch (Exception e) {
			logger.error(logId + ":向核心发送交易异常：更新数据库流水状态异常", e);
			resSta = TradeResFlg.OTHERR.getValue();
			return false;
		}

		// 拼接返回客户端报文
		try {
			// 转换成响应客户端报文
			transferService.transToResCMsg(entity);
			logger.info(logId + "：交易响应请求处理，流水号:" + entity.getFrntJrn()
					+ "，转换响应报文成功");
		} catch (Exception e) {
			logger.error(logId + "：交易响应请求处理，流水号:" + entity.getFrntJrn()
					+ "，转换响应报文失败", e);

			resSta = TradeResFlg.OTHERR.getValue();
			e.printStackTrace();
			return false;
		}

		byte[] upMsg = entity.getUpMsg();
		entity.getHeader().setMsglen(upMsg.length);
		entity.getHeader().setMsgtype(MsgTemplateType.RES.getValue());
		entity.getHeader().setCheckcod(
				MD5Utils.hash(entity.getHeader().getCheckcod()
						+ entity.getDesKeyInf().getKey() + new String(upMsg),
						"MD5"));

		return true;
	}

	/**
	 * 向核心发起异步产品请求
	 * 
	 * @param entity
	 */
	private void excutePrdToCoreAsny(GeneralBusEntity entity)
			throws TradeProcException {
		boolean flg = false;
		String errMsg = "";
		try {
			logger.info(logId + ":本次交易模式为异步，开始向队列发送交易请求......，前置流水号:"
					+ entity.getFrntJrn());
			// 发送核心
			BCfgdefQueue queEntity = SystemCfgUtil.getQueInfoByTrCode(entity
					.getHeader().getChnlcod(), entity.getHeader().getPrdcod(),
					SOPtrnTypt.P.getValue(), QueType.REQ.getValue());
			// 交易队列未配置
			if (queEntity == null || queEntity.getQuetopic() == null
					|| "".equals(queEntity.getQuetopic())) {
				logger.error(logId + ":向核心发送交易异常：队列信息为空");
				throw new TradeProcException("向核心发送交易异常：队列信息为空");
			}

			JSONObject finisJson = null;
			try {
				// 转换报文头
				finisJson = transferHeader(entity);
			} catch (Exception e) {
				logger.error(logId + ":向核心发送交易异常：组装报文头异常，", e);
				throw new TradeProcException("向核心发送交易异常：组装报文头异常");
			}

			String topic = queEntity.getQuetopic();

			// 队列实体
			KafkaQueEntity kfkQueEntity = new KafkaQueEntity();
			kfkQueEntity.setTopic(topic);
			kfkQueEntity.setMessage(finisJson.toJSONString());
			queOperator.offer(kfkQueEntity);

			// 发送请求核心日志
			try {
				SystemOperateUtil.sendLog(senCoreLogId,
						ProcSide.LOCAL.getValue(), ProcSide.CORE.getValue(),
						SOPtrnTypt.P.getValue(),
						InterOpType.REQSEND.getValue(), reqCoreMsgPath,
						coreResMsgPath, TradeLogSta.S.getValue(),
						LogType.TRADE.getValue(), "", entity);
			} catch (Exception e) {
				logger.error(logId + ":向核心发送交易异常：发送交易日志失败", e);
			}

		} catch (QueueOperatException e) {
			logger.error(logId + ":向核心发送产品交易异常：向队列发送任务失败", e);
		} catch (Exception e) {
			logger.error(logId + ":向核心发送产品交易异常：未知", e);
		}

		if (flg) {
			// 发送请求核心异常日志
			try {
				SystemOperateUtil.sendLog(senCoreLogId,
						ProcSide.LOCAL.getValue(), ProcSide.CORE.getValue(),
						SOPtrnTypt.P.getValue(),
						InterOpType.REQSEND.getValue(), reqCoreMsgPath,
						coreResMsgPath, TradeLogSta.F.getValue(),
						LogType.TRADE.getValue(), errMsg, entity);
			} catch (Exception e) {
				logger.error(logId + ":向核心发送交易异常：发送交易日志失败", e);
				throw new TradeProcException("向核心发送交易异常：向队列发送任务失败", e);
			}
			throw new TradeProcException("向核心发送交易异常：向队列发送任务失败");
		}

		// 处理状态成功
		entity.setProcSta(BusProcSta.S.getValue());
		entity.setProcReslut("接收请求成功");
		entity.getCoreHeader().setStatus(BusProcSta.S.getValue());
		try {
			logger.info(logId + ":向队列发送请求成功，前置流水号:" + entity.getFrntJrn());

			try {
				// 开始拼接返回报文
				transferService.transToAsynLResMsg(entity);
			} catch (TradeMsgTransfException e) {
				logger.error(logId + ":向核心发送产品交易异常：拼接返回信息异常", e);
				throw new TradeProcException("向核心发送产品交易异常：拼接返回信息异常", e);
			}


			byte[] upMsg = entity.getUpMsg();
			entity.getHeader().setMsglen(upMsg.length);
			entity.getHeader().setMsgtype(MsgTemplateType.ASYNSRES.getValue());
			entity.getHeader().setCheckcod(
					MD5Utils.hash(entity.getHeader().getChnlcod()
							+ entity.getDesKeyInf().getKey()
							+ new String(upMsg, SystemConstant.DEFAULT_CHARSET), "MD5"));
		} catch (Exception e) {
			logger.error(logId + ":向核心发送交易异常：拼接返回信息异常", e);
			throw new TradeProcException("向核心发送交易异常：拼接返回信息异常", e);
		}
	}

	/**
	 * 组装报文头
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	private JSONObject transferHeader(GeneralBusEntity entity) throws Exception {
		JSONObject finisJson = null;
		try {
			finisJson = (JSONObject) JSON.parse(new String(entity.getCoreMsg(),
					SystemConstant.DEFAULT_CHARSET));
			// 组装报文
			JSONObject header = (JSONObject) JSON
					.toJSON(entity.getCoreHeader());
			finisJson.put("header", header);
		} catch (Exception e) {
			logger.error(logId + ":向核心发送交易异常：组装报文头异常，", e);
			throw new TradeProcException("向核心发送交易异常：组装报文头异常");
		}
		return finisJson;
	}

	/*
	 * GETTER AND SETTER
	 */
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

	public FLogTrnflwService getLogServcie() {
		return logServcie;
	}

	public void setLogServcie(FLogTrnflwService logServcie) {
		this.logServcie = logServcie;
	}
}
