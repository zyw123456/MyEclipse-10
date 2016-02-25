package com.sinoway.common.service.transfer;
import org.w3c.dom.Element;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinoway.common.constant.SystemConstant;
import com.sinoway.common.constant.MsgTransfConstant.MsgTemplateType;
import com.sinoway.common.constant.MsgTransfConstant.MsgType;
import com.sinoway.common.constant.SystemConstant.SOPtrnTypt;
import com.sinoway.common.entity.CoreMsgHeader;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.entity.Message;
import com.sinoway.common.entity.Product;
import com.sinoway.common.entity.Trade;
import com.sinoway.common.parse.GeneralJsonMsgParser;
import com.sinoway.common.service.parse.GeneralJsonTradeMsgTransfer;
import com.sinoway.common.service.template.GeneralTemplateService;
import com.sinoway.common.util.McpLogger;
import com.sinoway.common.util.SystemCfgUtil;
import com.sinoway.mcp.exception.TradeMsgTransfException;

/**
 * 通用报文转换服务
 * @author Liuzhen
 * @version 1.0
 * 2015-11-23
 */
public class GeneralTransferServiceImpl implements GeneralTransferService{
	private McpLogger logger = McpLogger.getLogger(getClass());

	private GeneralTemplateService templateService = null;// 交易模板服务
	private GeneralJsonTradeMsgTransfer jsonTransfer = null;// json格式报文转换服务

	@Override
	public void transToReqSupMsg(GeneralBusEntity entity)
			throws TradeMsgTransfException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void transToResCoreMsg(GeneralBusEntity entity)
			throws TradeMsgTransfException {
		try{
			// 获取交易模板
			Trade trade = templateService.getTradeByTemplate(entity.getHeader().getChnlcod(), entity.getHeader().getIntrncod());
			if(trade == null)
				throw new TradeMsgTransfException("转换响应核心报文错误:获取交易配置模板错误，交易配置不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
			
			// 报文类别  xml或json
			String msgType = trade.getMsgType();
			if(msgType == null || "".equals(msgType))
				throw new TradeMsgTransfException("转换响应核心报文错误:报文类型不支持，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
			
//			String type  = MsgTemplateType.RES.getValue();
			
//			Message msg = trade.getMsgMap().get(type);
//			if(msg == null || msg.getMsgEl() == null)
//				throw new TradeMsgTransfException("转换响应核心报文错误:获取交易配置模板错误，请求核心报文模板不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
			
//			// 报文模板
//			Element el = msg.getMsgEl();
//			if(el == null)
//				throw new TradeMsgTransfException("转换响应核心报文错误:获取交易配置模板错误，请求核心报文模板不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
			
			// 与客户端交互使用JSON格式
			if(MsgType.JSON.getValue().equals(msgType)){
				
				JSONObject header= null;
				JSONObject msgJson = new JSONObject();
				try{
					if(entity.getCoreMsg() != null)
						msgJson = (JSONObject)JSON.parse(new String(entity.getCoreMsg(),SystemConstant.DEFAULT_CHARSET));
				}catch(Exception e){
					throw new TradeMsgTransfException("转换响应核心报文错误:数据不是JSON格式，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod(),e);
				}	
				
				JSONObject finsihJSon  = new JSONObject();
				
				try{
					
					 header  = (JSONObject)JSON.toJSON(entity.getCoreHeader());
				}catch(Exception e){
					logger.error("转换响应核心报文错误：转换报文头失败",e);
					throw new TradeMsgTransfException("转换响应核心报文错误:转换报文头失败，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod(),e);
				}
				
				JSONArray bodys = new JSONArray();
				bodys.add(msgJson);
				
				finsihJSon.put("header", header);
				
				if(entity.getCoreMsg()  != null)
					finsihJSon.put("bodys", bodys);
				
				entity.setCoreMsg(finsihJSon.toString().getBytes(SystemConstant.DEFAULT_CHARSET));

			}else if(MsgType.XML.getValue().equals(msgType)){
				// TODO xml报文处理
			
			}else{
				throw new TradeMsgTransfException("转换请求核心报文错误:报文类型不支持，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
			}
		}catch(Exception e){
			logger.error("转换成响应核心报文错误",e);
			throw new TradeMsgTransfException("转换请求核心报文错误:" +e.getMessage()+ "，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
		}
	}
	@Override
	public void transToReqCoreMsg(GeneralBusEntity entity)
			throws TradeMsgTransfException {
		try{
			// 交易类型
			String trnType = entity.getTrnType();

			// 交易类型不能为空
			if(trnType == null || "".equals(trnType))
				throw new TradeMsgTransfException("转换请求核心报文错误：交易类型不能为空，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
			
			// 原交易
			if(SOPtrnTypt.O.getValue().equals(trnType)){
			
				try {
					// 获取交易模板
					Trade trade = templateService.getTradeByTemplate(entity.getHeader().getChnlcod(), entity.getHeader().getIntrncod());
					if(trade == null)
						throw new TradeMsgTransfException("转换请求核心报文错误:获取交易配置模板错误，交易配置不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
					
					// 报文类别  xml或json
					String msgType = trade.getMsgType();
					if(msgType == null || "".equals(msgType))
						throw new TradeMsgTransfException("转换请求核心报文错误:报文类型不支持，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
					
					String type  = MsgTemplateType.REQ.getValue();
					
					Message msg = trade.getMsgMap().get(type);
					if(msg == null || msg.getMsgEl() == null)
						throw new TradeMsgTransfException("转换请求核心报文错误:获取交易配置模板错误，请求核心报文模板不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
					
					// 报文模板
					Element el = msg.getMsgEl();
					if(el == null)
						throw new TradeMsgTransfException("转换请求核心报文错误:获取交易配置模板错误，请求核心报文模板不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
					
					// 与客户端交互使用JSON格式
					if(MsgType.JSON.getValue().equals(msgType)){
						JSONObject cBody = null;
						if(entity.isTransferHeader()){
							JSONObject msgJson= null;
							try{
								msgJson = (JSONObject)JSON.parse(new String(entity.getDownMsg(),SystemConstant.DEFAULT_CHARSET));
							}catch(Exception e){
								throw new TradeMsgTransfException("转换请求核心报文错误:客户端请求数据不是JSON格式，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod(),e);
							}	
							
							try{
								cBody = msgJson.getJSONObject("body");// 客户端报文体
							}catch(Exception e){
								throw new TradeMsgTransfException("转换请求核心报文错误:客户端请求报文体不是JSON格式，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod(),e);
							}	
							
							// 转换核心报文头
							transCHeaderToCHeaderJson(msgJson,entity);
							
						}else{
							try{
								cBody = (JSONObject)JSON.parse(new String(entity.getDownMsg(),SystemConstant.DEFAULT_CHARSET));
							}catch(Exception e){
								throw new TradeMsgTransfException("转换请求核心报文错误:客户端请求数据不是JSON格式，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod(),e);
							}
						}
						// 转换核心报文
						transTradeToReqCoreMsgJson(el,cBody, entity);
						
						
					}else if(MsgType.XML.getValue().equals(msgType)){
						// TODO xml报文处理
					
					}else{
						throw new TradeMsgTransfException("转换请求核心报文错误:报文类型不支持，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
					}
					
				} catch (CloneNotSupportedException e) {
					throw new TradeMsgTransfException("转换请求核心报文错误:获取交易配置模板错误，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod(),e);
				}catch(Exception e){
					throw new TradeMsgTransfException("转换请求核心报文错误:转换过程中出现异常，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod(),e);
				} 
				
			// 产品
			}else if(SOPtrnTypt.P.getValue().equals(trnType)){
				// 获取产品配置节点
				Product prd = templateService.getPrdByTemplate(entity.getHeader().getChnlcod(), entity.getHeader().getPrdcod());
				
				if(prd == null)
					throw new TradeMsgTransfException("转换请求核心报文错误:获取产品配置模板错误，产品配置不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
				
				String msgType = prd.getParamValueByName("msgType");
				
				// 与客户端交互使用JSON格式
				if(MsgType.JSON.getValue().equals(msgType)){
					JSONObject cBody = null;
					// json 报文解析配置
					if(entity.isTransferHeader()){
						JSONObject msgJson = null;
						try{
							msgJson = (JSONObject)JSON.parse(new String(entity.getDownMsg(),SystemConstant.DEFAULT_CHARSET));
						}catch(Exception e){
							logger.error("转换请求核心报文错误:客户端请求数据不是JSON格式，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
							throw new TradeMsgTransfException("转换请求核心报文错误:客户端请求数据不是JSON格式，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
						}
						
						try{
							cBody = msgJson.getJSONObject("body");// 客户端报文体
						}catch(Exception e){
							throw new TradeMsgTransfException("转换请求核心报文错误:客户端请求报文体不是JSON格式，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
						}
						
						// 转换核心报文头
						transCHeaderToCHeaderJson(msgJson,entity);
					}else{
						try{
							cBody = (JSONObject)JSON.parse(new String(entity.getDownMsg(),SystemConstant.DEFAULT_CHARSET));
						}catch(Exception e){
							logger.error("转换请求核心报文错误:客户端请求数据不是JSON格式，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
							throw new TradeMsgTransfException("转换请求核心报文错误:客户端请求数据不是JSON格式，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
						}
					}
					// 转换请求核心报文
					transPrdToReqCoreMsgJson(cBody, entity);
				}else if(MsgType.XML.getValue().equals(msgType)){
					
				}else{
					throw new TradeMsgTransfException("转换请求核心报文错误:报文类型不支持，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
				}

			}else{
				throw new TradeMsgTransfException("转换请求核心报文错误：交易类型不支持，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod() + "产品码:" + entity.getHeader().getPrdcod());
			}
		}catch(Exception e){
			throw new TradeMsgTransfException("转换请求核心报文错误：" + e.getMessage() + "，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod(),e);
		}
	}
	
	@Override
	public void transToResCMsg(GeneralBusEntity entity)
			throws TradeMsgTransfException {
		try{
			String trnType = entity.getTrnType();//交易类型
			
			// 交易类型不能为空
			if(trnType == null || "".equals(trnType))
				throw new TradeMsgTransfException("转换响应客户端报文错误：交易类型不能为空，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
			
			// 原交易处理
			if(SOPtrnTypt.O.getValue().equals(trnType)){

				
				try {
					// 获取交易模板
					Trade trade = templateService.getTradeByTemplate(entity.getHeader().getChnlcod(), entity.getHeader().getIntrncod());
					if(trade == null)
						throw new TradeMsgTransfException("转换响应客户端报文错误:获取交易配置模板错误，交易配置不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
					
					// 报文类别  xml或json
					String msgType = trade.getMsgType();
					if(msgType == null || "".equals(msgType))
						throw new TradeMsgTransfException("转换响应客户端报文错误:报文类型不支持，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
					
					Message msg = trade.getMsgMap().get(MsgTemplateType.RES.getValue());
					if(msg == null || msg.getMsgEl() == null)
						throw new TradeMsgTransfException("转换响应客户端报文错误:获取交易配置模板错误，请求核心报文模板不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
					
					// 报文模板
					Element el = msg.getMsgEl();
					if(el == null)
						throw new TradeMsgTransfException("转换响应客户端报文错误:获取交易配置模板错误，请求核心报文模板不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
					
					// 与客户端交互使用JSON格式
					if(MsgType.JSON.getValue().equals(msgType)){
						
						JSONObject msgJson,cBody = null;
						JSONArray bodys = null;
						try{
							msgJson = (JSONObject)JSON.parse(new String(entity.getCoreMsg(),SystemConstant.DEFAULT_CHARSET));
						}catch(Exception e){
							throw new TradeMsgTransfException("转换响应客户端报文错误:核心响应数据不是JSON格式，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod(),e);
						}	
						
						try{
							bodys = msgJson.getJSONArray("bodys");// 核心报文提
						}catch(Exception e){
							throw new TradeMsgTransfException("转换响应客户端报文错误:核心响应数据报文体不是JSONArray格式，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod(),e);
						}
						if(bodys != null && bodys.size() > 0){
							try{
								cBody = bodys.getJSONObject(0);
							}catch(Exception e){
								throw new TradeMsgTransfException("转换响应客户端报文错误:核心响应数据报文体不是JSONObject格式，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
							}
						}
						
						// 转换响应客户端报文
						transTradeToResClientMsgJson(el,cBody, entity);
						
					}else if(MsgType.XML.getValue().equals(msgType)){
						// TODO xml报文处理
					
					}else{
						throw new TradeMsgTransfException("转换响应客户端报文错误:报文类型不支持，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
					}
					
				} catch (CloneNotSupportedException e) {
					throw new TradeMsgTransfException("转换响应客户端报文错误:获取交易配置模板错误，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod(),e);
				}catch(Exception e){
					throw new TradeMsgTransfException("转换响应客户端报文错误:转换过程中出现异常，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod(),e);
				} 
				
			// 产品处理
			}else if(SOPtrnTypt.P.getValue().equals(trnType)){

				// 获取产品配置节点
				Product prd = templateService.getPrdByTemplate(entity.getHeader().getChnlcod(), entity.getHeader().getPrdcod());
				
				if(prd == null)
					throw new TradeMsgTransfException("转换响应客户端报文错误:获取产品配置模板错误，产品配置不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
				
				String msgType = prd.getParamValueByName("msgType");
				
				// 与客户端交互使用JSON格式
				if(MsgType.JSON.getValue().equals(msgType)){
					// XML 报文解析配置
					
					JSONObject msgJson = null;
					JSONArray cBodys = null;
					try{
						msgJson = (JSONObject)JSON.parse(new String(entity.getCoreMsg(),SystemConstant.DEFAULT_CHARSET));
					}catch(Exception e){
						throw new TradeMsgTransfException("转换响应客户端报文错误:核心响应数据不是JSON格式，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod(),e);
					}
					
					try{
						cBodys = msgJson.getJSONArray("bodys");// 客户端报文体
					}catch(Exception e){
						throw new TradeMsgTransfException("转换响应客户端报文错误:核心响应报文体不是JSONARRAY格式，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod(),e);
					}
					if(cBodys == null || cBodys.size() == 0){
						throw new TradeMsgTransfException("转换响应客户端报文错误:核心响应报文体不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
					}
					// 转换请求核心报文
					transPrdToResClientMsgJson(cBodys, entity);
				}else if(MsgType.XML.getValue().equals(msgType)){
					
				}else{
					throw new TradeMsgTransfException("转换响应客户端报文错误:报文类型不支持，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
				}

			
			}else{
				throw new TradeMsgTransfException("转换响应客户端报文异常:不支持的交易类型");

			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new TradeMsgTransfException("转换响应客户端报文异常",e);
		}
		
	}

	@Override
	public void transToAsynLResMsg(GeneralBusEntity entity)
			throws TradeMsgTransfException {
		try{
			// 交易类型
			String trnType = entity.getTrnType();

			// 交易类型不能为空
			if(trnType == null || "".equals(trnType))
				throw new TradeMsgTransfException("转换本地即时响应报文错误：交易类型不能为空，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
			
			// 原交易
			if(SOPtrnTypt.O.getValue().equals(trnType)){
			
				try {
					// 获取交易模板
					Trade trade = templateService.getTradeByTemplate(entity.getHeader().getChnlcod(), entity.getHeader().getIntrncod());
					if(trade == null)
						throw new TradeMsgTransfException("转换本地即时响应报文错误:获取交易配置模板错误，交易配置不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
					
					// 报文类别  xml或json
					String msgType = trade.getMsgType();
					if(msgType == null || "".equals(msgType))
						throw new TradeMsgTransfException("转换本地即时响应报文错误:报文类型不支持，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
					
//					Message msg = trade.getMsgMap().get(MsgTemplateType.ASYNSRES.getValue());
//					if(msg == null || msg.getMsgEl() == null)
//						throw new TradeMsgTransfException("转换本地即时响应报文错误:获取交易配置模板错误，本地即时响应报文模板不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
					
//					// 报文模板
//					Element el = msg.getMsgEl();
//					if(el == null)
//						throw new TradeMsgTransfException("转换本地即时响应报文错误:获取交易配置模板错误，本地即时响应报文模板不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
					
					// 与客户端交互使用JSON格式
					if(MsgType.JSON.getValue().equals(msgType)){
						
						// 转换本地即时响应报文
						transToAsynLResMsgJson(entity);
						
					}else if(MsgType.XML.getValue().equals(msgType)){
						// TODO xml报文处理
					
					}else{
						throw new TradeMsgTransfException("转换本地即时响应报文错误:报文类型不支持，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
					}
					
				} catch (CloneNotSupportedException e) {
					throw new TradeMsgTransfException("转换本地即时响应报文错误:获取交易配置模板错误，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod(),e);
				}catch(Exception e){
					throw new TradeMsgTransfException("转换本地即时响应报文错误:转换过程中出现异常，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod(),e);
				} 
				
			// 产品
			}else if(SOPtrnTypt.P.getValue().equals(trnType)){
				// 获取产品配置节点
				Product prd = templateService.getPrdByTemplate(entity.getHeader().getChnlcod(), entity.getHeader().getPrdcod());
				
				if(prd == null)
					throw new TradeMsgTransfException("转换本地即时响应报文错误:获取产品配置模板错误，产品配置不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
				
//				Message msg = prd.getMsgMap().get(MsgTemplateType.ASYNSRES.getValue());
//				if(msg == null || msg.getMsgEl() == null)
//					throw new TradeMsgTransfException("转换本地即时响应报文错误:获取交易配置模板错误，本地即时响应报文模板不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
				
//				// 报文模板
//				Element el = msg.getMsgEl();
//				if(el == null)
//					throw new TradeMsgTransfException("转换本地即时响应报文错误:获取交易配置模板错误，本地即时响应报文模板不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
				
				String msgType = prd.getParamValueByName("msgType");
				
				// 与客户端交互使用JSON格式
				if(MsgType.JSON.getValue().equals(msgType)){
					// 转换本地即时响应报文
					transToAsynLResMsgJson(entity);
				}else if(MsgType.XML.getValue().equals(msgType)){
					
				}else{
					throw new TradeMsgTransfException("转换本地即时响应报文错误:报文类型不支持，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
				}

			}else{
				throw new TradeMsgTransfException("转换本地即时响应报文错误：交易类型不支持，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
			}
		}catch(Exception e){
			throw new TradeMsgTransfException("转换本地即时响应报文错误：未知错误，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
		}
	}

	/**
	 * 转换异步本地即时响应报文
	 * @param el
	 * @param entity
	 * @throws TradeMsgTransfException
	 */
	private void transToAsynLResMsgJson(GeneralBusEntity entity) throws TradeMsgTransfException{
		try{
//			// 开始转换
//			JSONObject transferedJson = jsonTransfer.transfJsonMsgByJTemp(el,null,null,entity.getHeader().getIntrncod(),false,entity);
			
			// 最终的json
			JSONObject finishJson = new JSONObject();
			
//			finishJson.put("body", transferedJson);
			
			// header
			JSONObject header = (JSONObject)JSON.toJSON(entity.getCoreHeader());
			
			
			finishJson.put("header", header);
			
			entity.setUpMsg(finishJson.toString().getBytes(SystemConstant.DEFAULT_CHARSET));
			
		}catch(Exception e){
			throw new TradeMsgTransfException("转换异步本地即时响应报文错误:拼装异常，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod(),e);
		}
	
	}
	/**
	 * 转换成请求核心该报文 产品
	 * @param el
	 * @param entity
	 * @throws TradeMsgTransfException
	 */
	private void transPrdToReqCoreMsgJson(JSONObject cBody,GeneralBusEntity entity) throws TradeMsgTransfException{
		try{
			// 交易码集合
			String trncods = cBody.getString(SystemConstant.CLIENT_TRNCODE_KEY);
			JSONObject trnInfo = cBody.getJSONObject(SystemConstant.CLIENT_TRNINFO_KEY);
			
			if(trnInfo == null)
				throw new TradeMsgTransfException("转换请求核心报文错误:客户端请求报文内容空，渠道号：" + entity.getHeader().getChnlcod());
			
			if(trncods == null){
				throw new TradeMsgTransfException("转换请求核心报文错误:交易码为空，渠道号：" + entity.getHeader().getChnlcod());

			}
			
			// 交易码集合
			String[] trcodArr = trncods.split(",");
			
			if(trcodArr.length== 0 || (trcodArr.length == 1 && "".equals(trcodArr[0])))
				throw new TradeMsgTransfException("转换请求核心报文错误:客户端交易码不能为空，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());

			// 最终的json
			JSONObject finishJson = new JSONObject();
			// bodys
			JSONArray bodysJsa = new JSONArray();
			
			for(int i = 0; i < trcodArr.length; i++){
				String key = trcodArr[i];
				// 获取外部交易码
				String inTrCode = SystemCfgUtil.getIntrByOutTr(key, SOPtrnTypt.O);
				if(inTrCode == null || "".equals(inTrCode))
					throw new TradeMsgTransfException("转换请求核心报文错误:内部交易码不能为空，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + inTrCode);

				try {
					// 获取交易模板
					Trade trade = templateService.getTradeByTemplate(entity.getHeader().getChnlcod(), key);
					if(trade == null)
						throw new TradeMsgTransfException("转换请求核心报文错误:获取交易配置模板错误，交易配置不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + inTrCode);
					
					// 报文类别  xml或json
					String msgType = trade.getMsgType();
					if(msgType == null || "".equals(msgType))
						throw new TradeMsgTransfException("转换请求核心报文错误:报文类型不支持，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + inTrCode);
					
					Message msg = trade.getMsgMap().get(MsgTemplateType.REQ.getValue());
					if(msg == null || msg.getMsgEl() == null)
						throw new TradeMsgTransfException("转换请求核心报文错误:获取交易配置模板错误，请求核心报文模板不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + inTrCode);
					
					// 报文模板
					Element el = msg.getMsgEl();
					if(el == null)
						throw new TradeMsgTransfException("转换请求核心报文错误:获取交易配置模板错误，请求核心报文模板不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + inTrCode);
					
					// 转换核心报文
					JSONObject transferedJson = jsonTransfer.transfJsonMsgByJTemp(el,trnInfo,trnInfo,key,true,entity);
					
					bodysJsa.add(transferedJson);
				
				}catch(Exception e){
					throw new TradeMsgTransfException("转换请求核心报文错误:报文拼装异常，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + inTrCode);
				}
			}
			
			finishJson.put("bodys", bodysJsa);
			
			entity.setCoreMsg(finishJson.toString().getBytes(SystemConstant.DEFAULT_CHARSET));
		}catch(Exception e){
			throw new TradeMsgTransfException("转换请求核心报文错误:报文拼装异常，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
		}
	}
	
	/**
	 * 转换成响应客户端报文 产品
	 * @param el
	 * @param entity
	 * @throws TradeMsgTransfException
	 */
	private void transPrdToResClientMsgJson(JSONArray cBodys,GeneralBusEntity entity) throws TradeMsgTransfException{
		try{
			
			// 最终的json
			JSONObject finishJson = new JSONObject();
			// bodys
			JSONObject bodys = new JSONObject();
			
			for(int i = 0; i < cBodys.size(); i++){
				JSONObject body = null;
				try{
					body = cBodys.getJSONObject(i);
				}catch(Exception e){
					throw new TradeMsgTransfException("转换响应客户端报文错误:核心响应报文体不是JSON格式，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
				}
				String inTrCode = null;
				try{
					inTrCode = (String)GeneralJsonMsgParser.getJsonValueByKey(body, SystemConstant.CORE_TRNCODE_KEY);
				}catch(Exception e){
					throw new TradeMsgTransfException("转换响应客户端报文错误:未获取到交易码，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());

				}
				
				// 获取外部交易码
				String oTrCode = SystemCfgUtil.getOuttrByInTr(inTrCode, SOPtrnTypt.O);
				if(oTrCode == null || "".equals(oTrCode))
					oTrCode = inTrCode;
				
				try {
					// 获取交易模板
					Trade trade = templateService.getTradeByTemplate(entity.getHeader().getChnlcod(), inTrCode);
					if(trade == null)
						throw new TradeMsgTransfException("转换响应客户端报文错误:获取交易配置模板错误，交易配置不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + inTrCode);
					
					// 报文类别  xml或json
					String msgType = trade.getMsgType();
					if(msgType == null || "".equals(msgType))
						throw new TradeMsgTransfException("转换响应客户端报文错误:报文类型不支持，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + inTrCode);
					
					Message msg = trade.getMsgMap().get(MsgTemplateType.RES.getValue());
					if(msg == null || msg.getMsgEl() == null)
						throw new TradeMsgTransfException("转换响应客户端报文错误:获取交易配置模板错误，请求核心报文模板不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + inTrCode);
					
					// 报文模板
					Element el = msg.getMsgEl();
					if(el == null)
						throw new TradeMsgTransfException("转换响应客户端报文错误:获取交易配置模板错误，请求核心报文模板不存在，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + inTrCode);
					
					// 转换核心报文
					JSONObject transferedJson = jsonTransfer.transfJsonMsgByJTemp(el,body,body,inTrCode,false,entity);
					
					bodys.put(oTrCode, transferedJson);
				
				}catch(Exception e){
					e.printStackTrace();
					throw new TradeMsgTransfException("转换响应客户端报文错误:报文拼装异常，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + inTrCode);
				}
			}
//			JSONArray jsa = new JSONArray();
//			jsa.add(bodys);
			finishJson.put("body", bodys);
			
			// header
			JSONObject header = (JSONObject)JSON.toJSON(entity.getCoreHeader());
			
			finishJson.put("header", header);
			
			entity.setUpMsg(finishJson.toString().getBytes(SystemConstant.DEFAULT_CHARSET));
			
		}catch(Exception e){
			e.printStackTrace();
			throw new TradeMsgTransfException("转换响应客户端报文错误:报文拼装异常，渠道号：" + entity.getHeader().getChnlcod() +  "，产品码:" + entity.getHeader().getPrdcod());
		}
	}
	
	/**
	 * 转换成请求核心该报文  原交易
	 * @param el
	 * @param entity
	 * @throws TradeMsgTransfException
	 */
	private void transTradeToReqCoreMsgJson(Element el,JSONObject cBody,GeneralBusEntity entity) throws TradeMsgTransfException{
		
		try{
			if(cBody == null)
				throw new TradeMsgTransfException("转换请求核心报文错误：报文数据不能为空，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
			
			// 开始转换
			JSONObject transferedJson = jsonTransfer.transfJsonMsgByJTemp(el,cBody,cBody,entity.getHeader().getIntrncod(),true,entity);
			
			// 最终的json
			JSONObject finishJson = new JSONObject();
			
			// bodys
			JSONArray bodysJsa = new JSONArray();
			
			bodysJsa.add(transferedJson);
			
			finishJson.put("bodys", bodysJsa);
			
			entity.setCoreMsg(finishJson.toString().getBytes(SystemConstant.DEFAULT_CHARSET));
			
		}catch(Exception e){
			throw new TradeMsgTransfException("转换请求核心报文错误:拼装异常，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod(),e);
		}
	}
	
	/**
	 * 转换成响应客户端报文  原交易
	 * @param el
	 * @param entity
	 * @throws TradeMsgTransfException
	 */
	private void transTradeToResClientMsgJson(Element el,JSONObject cBody,GeneralBusEntity entity) throws TradeMsgTransfException{
		
		try{
			JSONObject transferedJson = null;
			if(cBody != null){
			  // 开始转换
				transferedJson = jsonTransfer.transfJsonMsgByJTemp(el,cBody,cBody,entity.getHeader().getIntrncod(),false,entity);
			}
			
			
			// 最终的json
			JSONObject finishJson = new JSONObject();
			
			if(transferedJson != null)
				finishJson.put("body", transferedJson);
			
			// header
			JSONObject header = (JSONObject)JSON.toJSON(entity.getCoreHeader());
			
			
			finishJson.put("header", header);
			
			entity.setUpMsg(finishJson.toString().getBytes(SystemConstant.DEFAULT_CHARSET));
			
		}catch(Exception e){
			throw new TradeMsgTransfException("转换请求核心报文错误:拼装异常，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod(),e);
		}
	}
	
	private void transCHeaderToCHeaderJson(JSONObject msgJson,GeneralBusEntity entity) throws TradeMsgTransfException{
		
		// 组装核心报文头实体
		CoreMsgHeader coreHeader = new CoreMsgHeader();
		
		JSONObject cHeader = null;
		try{
			cHeader = msgJson.getJSONObject("header");// 客户端报文头
		}catch(Exception e){
			throw new TradeMsgTransfException("转换请求核心报文错误:客户端请求头不是JSON格式，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod(),e);
		}
			
		
		if(cHeader != null){
			try{
				coreHeader = (CoreMsgHeader)JSON.toJavaObject(cHeader, CoreMsgHeader.class);
			}catch(Exception e){
				throw new TradeMsgTransfException("转换请求核心报文错误:转换核心报文体错误，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod(),e);
			}
		}else{
			throw new TradeMsgTransfException("转换请求核心报文错误:客户端报文头不能为空，渠道号：" + entity.getHeader().getChnlcod() +  "，交易码:" + entity.getHeader().getIntrncod());
		}
		
		coreHeader.setChnlcod(entity.getHeader().getChnlcod());// 设置渠道号
		coreHeader.setIntrncod(entity.getHeader().getIntrncod());// 内部交易码
		coreHeader.setFnttrndte(entity.getTrnDate());//前置交易日期
		coreHeader.setFnttrntim(entity.getTrnTime());// 前置交易时间
		coreHeader.setFnttrnjrn(entity.getFrntJrn());// 前置流水号
		coreHeader.setPrdcod(entity.getHeader().getPrdcod());// 产品号
		entity.setCoreHeader(coreHeader);
	}
   
	@Override
	public void initBatchMsg(GeneralBusEntity entity)
			throws TradeMsgTransfException {
		// TODO Auto-generated method stub
		
	}
	/*
	 *   GETTER   AND  SERTTER
	 */
	public GeneralTemplateService getTemplateService() {
		return templateService;
	}

	public void setTemplateService(GeneralTemplateService templateService) {
		this.templateService = templateService;
	}

	public GeneralJsonTradeMsgTransfer getJsonTransfer() {
		return jsonTransfer;
	}

	public void setJsonTransfer(GeneralJsonTradeMsgTransfer jsonTransfer) {
		this.jsonTransfer = jsonTransfer;
	}


}
