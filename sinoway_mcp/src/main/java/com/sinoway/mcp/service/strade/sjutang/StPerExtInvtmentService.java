package com.sinoway.mcp.service.strade.sjutang;

import java.io.UnsupportedEncodingException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinoway.base.entity.BCfgdefFnttrnaddr;
import com.sinoway.common.constant.ServerConstant.ResSta;
import com.sinoway.common.constant.SystemConstant;
import com.sinoway.common.constant.SystemConstant.STradeResFlg;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.exception.STradeProcessException;
import com.sinoway.common.service.server.GeneralSTradeProcService;
import com.sinoway.common.util.DocumentUtil;
import com.sinoway.common.util.HttpsUtils;
import com.sinoway.common.util.McpLogger;
import com.sinoway.common.util.StringUtil;
import com.sinoway.mcp.service.strade.sjutang.Util.SjtUtil;
import com.sun.org.apache.xpath.internal.XPathAPI;

/**
 * 数据堂个人对外投资信息
 * 
 * @author Liuzhen
 * 
 */
public class StPerExtInvtmentService implements GeneralSTradeProcService {
	private McpLogger logger = McpLogger.getLogger(getClass());

	@Override
	public GeneralBusEntity busLaunch(GeneralBusEntity entity)
			throws STradeProcessException {

		try {
			byte[] data = entity.getCoreMsg();

			String sendstr = new String(data, SystemConstant.DEFAULT_CHARSET);

			JSONObject sendjson = (JSONObject) JSONObject.parse(sendstr);

			JSONObject params = sendjson.getJSONArray("bodys").getJSONObject(0);

			logger.debug("核心请求数据：：：" + params);
			if (params == null || params.equals(null)) {
				throw new Exception("核心请求报文参数为空！");
			}

			// 通过httpget返回的字符串串
			String perSinviriment = null;
			try {
				// 连接url地址
				String strUrl = "http://apidata.datatang.com/data/credit/queryEntByPerson";
				// dtkey 通过页面申请的API KEY。(必须项目)
				String strKey = "f3e99ebcde110f60c221bc736643efb7";
				// rettype 需要返回的格式（支持xml）(必须项目)
				String strRettype = "xml";
				// 通过APIKey进行对参数加密
				SjtUtil desEncrypter = new SjtUtil(strKey);
				// 加密后的参数
				String enparam = "key=" + params.get("idcard").toString();
				String param = desEncrypter.encrypt(enparam);
				StringBuffer url = new StringBuffer();
				url.append(strUrl).append("?").append("apikey=").append(strKey)
						.append("&").append("rettype=").append(strRettype)
						.append("&").append("encryptParam=").append(param);
				entity.setUpMsg(url.toString().getBytes(SystemConstant.DEFAULT_CHARSET));
				perSinviriment = HttpsUtils.executeGet(url.toString(),SystemConstant.DEFAULT_CHARSET,10000,60000);
			} catch (Exception e) {
				entity.setResSta(ResSta.F.getValue());
				entity.setErrMsg(e.getMessage());
				// 判断如果是超时 或者是出现异常
				if (e.getMessage().equals("Read timed out")) {
					entity.getStrdflw().setRespflg(STradeResFlg.SUPTMOUT.getValue());
				} else {
					entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
				}
				// 给核心的响应状态和描述
				entity.getCoreHeader().setStatus(ResSta.F.getValue());
				entity.getCoreHeader().setResult(e.getMessage());
				throw new STradeProcessException(e);
			}
//			logger.info("返回的json" + perSinviriment);
			entity.setDownMsg(perSinviriment.getBytes(SystemConstant.DEFAULT_CHARSET));
			// 拼接响应回来的转化报文
			JSONObject repObj = new JSONObject();

	        Document document = DocumentUtil.strToDomByNoDecode(perSinviriment);

	      Element elStatus = (Element) XPathAPI.selectSingleNode(document,"/DATA/ORDERLIST/ITEM/STATUS");
          
	      
	      if(elStatus == null){
    		Element message = (Element) XPathAPI.selectSingleNode(document,"/root/message");
    		String errMsg = "";
	    		if(message != null){
	    			errMsg = message.getTextContent();
	    		}
				entity.setResSta(ResSta.F.getValue());
				entity.setErrMsg(errMsg);
				entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
				entity.getCoreHeader().setStatus(ResSta.F.getValue());
				entity.getCoreHeader().setResult(errMsg);
	        	return entity;
           }
			// 转化后高管的jsonarray
			JSONArray executiveInf = new JSONArray();
			// 转化后法人的jsonarray
			JSONArray lealpersonInf = new JSONArray();
			if (elStatus.getTextContent().equals("1")) {
				
				entity.setResSta(ResSta.S.getValue());
				entity.setErrMsg("SUCCESS");
				entity.getStrdflw().setRespflg(STradeResFlg.SUCCESS.getValue());
				entity.getCoreHeader().setStatus(ResSta.S.getValue());
				entity.getCoreHeader().setResult("SUCCESS");
				Element ryposfr = (Element) XPathAPI.selectSingleNode(document,
						"/DATA/RYPOSPER");
				if (ryposfr !=null && ryposfr.hasChildNodes()) {
					NodeList nodeList = ryposfr.getChildNodes();
					for (int i = 0; i < nodeList.getLength(); i++) {
						Node pnode = nodeList.item(i);
						if (pnode.getNodeType() == 3) {
							continue;
						}
						NodeList childList = pnode.getChildNodes();
						JSONObject ryposper = new JSONObject();
						for (int j = 0; j < childList.getLength(); j++) {
							Node node = childList.item(j);
							if (node.getNodeType() == 3) {
								continue;
							}
							if (node.getNodeName().equals("ENTNAME")) {
								ryposper.put("compNam", node.getTextContent());
							} else if (node.getNodeName().equals("REGNO")) {
								ryposper.put("compRegistNo",node.getTextContent());
							} else if (node.getNodeName().equals("REGCAP")) {
								ryposper.put("compRegistCaptl",node.getTextContent());
							} else if (node.getNodeName().equals("ENTTYPE")) {
								ryposper.put("compTyp", node.getTextContent());
							} else if (node.getNodeName().equals("REGCAPCUR")) {
								ryposper.put("cny", node.getTextContent());
							} else if (node.getNodeName().equals("ENTSTATUS")) {
								ryposper.put("compSta", node.getTextContent());
							} else if (node.getNodeName().equals("POSITION")) {
								ryposper.put("compDuties",node.getTextContent());
							}
						}
						executiveInf.add(ryposper);
						
					}
				} else {
					throw new Exception("获取个人对外投资高管信息为空");
				}

				logger.debug("高管JSONObject"
						+ lealpersonInf.toJSONString());
				// 获取法人节点
				Element lealperso = (Element) XPathAPI.selectSingleNode(document, "/DATA/RYPOSFR");
			    
				if (lealperso != null && lealperso.hasChildNodes()) {
					NodeList nodeList = lealperso.getChildNodes();
					for (int i = 0; i < nodeList.getLength(); i++) {
						Node pnode = nodeList.item(i);
						if (pnode.getNodeType() == 3) {
							continue;
						}
						NodeList childList = pnode.getChildNodes();
						JSONObject ryposper = new JSONObject();
						for (int j = 0; j < childList.getLength(); j++) {
							Node node = childList.item(j);
							if (node.getNodeType() == 3) {
								continue;
							}
							if (node.getNodeName().equals("ENTNAME")) {
								ryposper.put("compNam", node.getTextContent());
							} else if (node.getNodeName().equals("REGNO")) {
								ryposper.put("compRegistNo",node.getTextContent());
							} else if (node.getNodeName().equals("REGCAP")) {
								ryposper.put("compRegistCaptl",node.getTextContent());
							} else if (node.getNodeName().equals("ENTTYPE")) {
								ryposper.put("compTyp", node.getTextContent());
							} else if (node.getNodeName().equals("REGCAPCUR")) {
								ryposper.put("cny", node.getTextContent());
							} else if (node.getNodeName().equals("ENTSTATUS")) {
								ryposper.put("compSta", node.getTextContent());
							}
						}
						lealpersonInf.add(ryposper);
					}
				} else {
					throw new Exception("获取个人对外投资法人信息为空");
				}
				logger.debug("法人JSONObject+++"
						+ executiveInf.toJSONString());
				repObj.put("executiveInf", executiveInf);
				repObj.put("lealpersonInf", lealpersonInf);
//				entity.setUpMsg(params.toString().getBytes(SystemConstant.DEFAULT_CHARSET));// 转化后的请求数据
				entity.setCoreMsg(repObj.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));// 给核心的数据

			  } else {
			  JSONObject ryposper = new JSONObject();
				executiveInf.add(ryposper);
				JSONObject leraposper = new JSONObject();
				lealpersonInf.add(leraposper);  
				repObj.put("executiveInf", executiveInf);
				repObj.put("lealpersonInf", lealpersonInf);
				entity.setResSta(ResSta.S.getValue());
				entity.setErrMsg("未查询到信息");
				entity.getStrdflw().setRespflg(STradeResFlg.SUCCESS.getValue());
				entity.getCoreHeader().setStatus(ResSta.S.getValue());
				entity.getCoreHeader().setResult("未查询到信息");
				entity.setUpMsg(params.toString().getBytes(SystemConstant.DEFAULT_CHARSET));// 转化后的请求数据
				entity.setCoreMsg(repObj.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));// 给核心的数据
				entity.setDownMsg(perSinviriment.getBytes(SystemConstant.DEFAULT_CHARSET));// 转换前的响应数据
			   }

		} catch (Throwable e) {
			entity.setResSta(ResSta.F.getValue());
			entity.setErrMsg(e.getMessage());
			entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
			entity.getCoreHeader().setStatus(ResSta.F.getValue());
			entity.getCoreHeader().setResult(e.getMessage());
			String errMsg = StringUtil.NullToString(e.getMessage());
			try {
				entity.setDownMsg(errMsg.equals("") ? null : errMsg.getBytes(SystemConstant.DEFAULT_CHARSET));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return entity;
	}

	@Override
	public GeneralBusEntity resRecive(GeneralBusEntity entity)
			throws STradeProcessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralBusEntity getRes(GeneralBusEntity entity)
			throws STradeProcessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initCfg(BCfgdefFnttrnaddr entity) {
		// TODO Auto-generated method stub

	}

}
