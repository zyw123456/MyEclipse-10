package com.sinoway.mcp.service.strade.gzt.service;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinoway.base.entity.BCfgdefFnttrnaddr;
import com.sinoway.common.constant.AccountConstant;
import com.sinoway.common.constant.HttpConstant.HttpOvertime;
import com.sinoway.common.constant.ServerConstant.ResSta;
import com.sinoway.common.constant.SystemConstant;
import com.sinoway.common.constant.SystemConstant.STradeResFlg;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.exception.STradeProcessException;
import com.sinoway.common.service.server.GeneralSTradeProcService;
import com.sinoway.common.util.DesUtil;
import com.sinoway.common.util.DocumentUtil;
import com.sinoway.common.util.McpLogger;
import com.sinoway.mcp.service.strade.gzt.ws.QueryValidatorServices;
import com.sun.org.apache.xpath.internal.XPathAPI;

/**
 * 国政通学历认证子交易
 * @author zhangyanwei
 * 1.0
 * 2016-1-13
 */
public class StEduQueryValidatorService  implements  GeneralSTradeProcService {

	private McpLogger logger = McpLogger.getLogger(getClass());
	
	public GeneralBusEntity busLaunch(GeneralBusEntity entity)
			throws STradeProcessException {
		DesUtil desUtil = DesUtil.getInstance();
		try{
			
			byte[] coremsg = entity.getCoreMsg();
			String coremsgstr = new String(coremsg,SystemConstant.DEFAULT_CHARSET); 
			JSONObject jo =(JSONObject) JSONObject.parse(coremsgstr);
			//拼接供应商的报文
			JSONObject supplyJson =jo.getJSONArray("bodys").getJSONObject(0);
			
			//拼接转换后的报文（请求时）
			StringBuffer sendBefore = new StringBuffer();
			sendBefore.append(supplyJson.getString("prsnnam"))
			          .append(",")
			          .append(supplyJson.getString("idcard"));
			
			String username = desUtil.encrypt(AccountConstant.GZT_WS_USERNAME,AccountConstant.GZT_WS_DESKEY,AccountConstant.GZT_WS_DESIV, SystemConstant.CHARSET_GBK);
			String password = desUtil.encrypt(AccountConstant.GZT_WS_PWD,AccountConstant.GZT_WS_DESKEY ,AccountConstant.GZT_WS_DESIV,SystemConstant.CHARSET_GBK); 
			String dataSource =desUtil.encrypt(AccountConstant.GZT_WS_STEDUCODE,AccountConstant.GZT_WS_DESKEY,AccountConstant.GZT_WS_DESIV ,SystemConstant.CHARSET_GBK); 
			String params = desUtil.encrypt(sendBefore.toString(),AccountConstant.GZT_WS_DESKEY,AccountConstant.GZT_WS_DESIV ,SystemConstant.CHARSET_GBK); ;
			logger.info("向供应商发起学历信息请求....");
			logger.info("发送数据："+sendBefore.toString());
			entity.setUpMsg(params.getBytes(SystemConstant.DEFAULT_CHARSET));
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.setServiceClass(QueryValidatorServices.class);
			factory.setAddress(AccountConstant.GZT_WS_URL);
			
			QueryValidatorServices gws = (QueryValidatorServices) factory.create();
			   Client proxy = ClientProxy.getClient(gws);
			   HTTPConduit conduit = (HTTPConduit) proxy.getConduit();
			   HTTPClientPolicy policy = new HTTPClientPolicy();
			   policy.setConnectionTimeout(HttpOvertime.HTTP_OVERTIME_CONNTIMEOUT.getCode()); //连接超时时间
			   policy.setReceiveTimeout(HttpOvertime.HTTP_OVERTIME_SOCKETTIMEOUT.getCode());//请求超时时间.
			   conduit.setClient(policy);
			
			
			//解密后的串	
			 String  decodestr  = null;
			try{
				decodestr  = gws.querySingle(username,password,dataSource, params);
			}catch(Exception e){
				entity.setResSta(ResSta.F.getValue());
				entity.setErrMsg(e.getMessage());
				//判断如果是超时 或者是出现异常
				if(e.getMessage().equals("Read timed out")){
				 entity.getStrdflw().setRespflg(STradeResFlg.SUPTMOUT.getValue());
				}else{
				 entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
				}
				//给核心的响应状态和描述
				entity.getCoreHeader().setStatus(ResSta.F.getValue());
				entity.getCoreHeader().setResult(e.getMessage());
				throw new Exception(e);
			}
			logger.info("向供应商发起学历信息证结束....");
			entity.setDownMsg(decodestr.getBytes(SystemConstant.DEFAULT_CHARSET));// 转换前的响应数据(解密前的)
			String  resptr = null;
			try{ 
			  resptr = desUtil.decrypt(decodestr, "12345678", "12345678", SystemConstant.CHARSET_GBK);
			}catch(Exception e){
				entity.setResSta(ResSta.F.getValue());
				entity.setErrMsg(e.getMessage());
				entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
				entity.getCoreHeader().setStatus(ResSta.F.getValue());
				entity.getCoreHeader().setResult(e.getMessage());
				entity.setDownMsg(e.getMessage().getBytes());
				throw new Exception(e);
			  	
			}
			entity.setDownMsg(resptr.getBytes(SystemConstant.DEFAULT_CHARSET));
			logger.info("接收到返回信息："+resptr);
 			Document document = DocumentUtil.strToDomByDefDecode(resptr);
 			//Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("D:\\userCheck.xml");
 			//请求message 状态和描述
 			Element reqstatus = (Element)XPathAPI.selectSingleNode(document, "/data/message/status");
 			Element reqvalue = (Element)XPathAPI.selectSingleNode(document, "/data/message/value");
 			//查询message 状态和描述
 			Element status = (Element)XPathAPI.selectSingleNode(document, "/data/eduInfos/eduInfo/message/status");
 			Element value = (Element)XPathAPI.selectSingleNode(document, "/data/eduInfos/eduInfo/message/value");
 			//解析响应报文后的状态和描述
 			if(reqstatus!=null && reqstatus.getTextContent().equals("0")){
 				entity.setResSta(ResSta.S.getValue());
				entity.setErrMsg(reqvalue.getTextContent());
				entity.getStrdflw().setRespflg(STradeResFlg.SUCCESS.getValue());
				entity.getCoreHeader().setStatus(ResSta.S.getValue());
				entity.getCoreHeader().setResult(value.getTextContent());
				
				//实体参数
//	 			Element identityCard = (Element)XPathAPI.selectSingleNode(document, "/data/eduInfos/eduInfo/identityCard");
//	 			Element identityInput = (Element)XPathAPI.selectSingleNode(document, "/data/eduInfos/eduInfo/identityInput");
				JSONObject personalInf = new JSONObject();
				JSONArray array = new JSONArray();
		 		if(status.getTextContent().equals("0")){
		 			NodeList nodeList = XPathAPI.selectNodeList(document, "/data/eduInfos/eduInfo");
		 			for (int i = 0; i < nodeList.getLength(); i++) {
						Node item = nodeList.item(i);
						Element userName = (Element) XPathAPI.selectSingleNode(item, "userName");
						Element graduate = (Element)XPathAPI.selectSingleNode(item, "graduate");
						Element educationDegree = (Element)XPathAPI.selectSingleNode(item, "educationDegree");
						Element enrolDate = (Element)XPathAPI.selectSingleNode(item, "enrolDate");
						Element specialityName = (Element)XPathAPI.selectSingleNode(item, "specialityName");
						Element graduateTime = (Element)XPathAPI.selectSingleNode(item, "graduateTime");
						Element studyResult = (Element)XPathAPI.selectSingleNode(item, "studyResult");
						Element studyStyle = (Element)XPathAPI.selectSingleNode(item, "studyStyle");
						Element photo = (Element)XPathAPI.selectSingleNode(item, "photo");
						JSONObject repObj = new JSONObject();
						repObj.put("eduSchool", graduate == null ? "" : graduate.getTextContent());
						repObj.put("education", educationDegree== null ? "" :educationDegree.getTextContent()); 
						repObj.put("eduLevel", studyStyle== null ? "" :studyStyle.getTextContent());  
						repObj.put("eduMajor", specialityName == null ? "" :specialityName.getTextContent()); 
						repObj.put("enrolDte", enrolDate == null ? "" : enrolDate.getTextContent() ); 
						repObj.put("gradDte", graduateTime == null ? "" :graduateTime.getTextContent());
						repObj.put("eduResult", studyResult == null ? "" :studyResult.getTextContent() );
						array.add(repObj);
					}
		 				
		 			}
		 			personalInf.put("eduInf", array);
					entity.setCoreMsg(personalInf.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));// 给核心的数据
					entity.setDownMsg(resptr.getBytes(SystemConstant.DEFAULT_CHARSET));// 转换前的响应数据
 			}else{
 				entity.setResSta(ResSta.F.getValue());
				entity.setErrMsg(reqvalue == null ? "解析返回报文异常" :reqvalue.getTextContent());
				entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
				entity.getCoreHeader().setStatus(ResSta.F.getValue());
				entity.getCoreHeader().setResult(value == null ? "解析返回报文异常" : value.getTextContent());	
 			}
 			
		
		}catch(Throwable e){
			entity.setResSta(ResSta.F.getValue());
			entity.setErrMsg(e.getMessage());
			entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
			entity.getCoreHeader().setStatus(ResSta.F.getValue());
			entity.getCoreHeader().setResult(e.getMessage());
			entity.setDownMsg(e.getMessage().getBytes());
			e.printStackTrace();
		}
		return entity;
	}
	

	public GeneralBusEntity resRecive(GeneralBusEntity entity)
			throws STradeProcessException {
		return null;
	}

	public GeneralBusEntity getRes(GeneralBusEntity entity)
			throws STradeProcessException {
		return null;
	}

	public void initCfg(BCfgdefFnttrnaddr entity) {
		
	}

	public static void main(String[] args) {
		GeneralBusEntity i = new GeneralBusEntity();
		StEduQueryValidatorService  service = new StEduQueryValidatorService();
		try {
			service.busLaunch(i);
		} catch (STradeProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	
	
}
