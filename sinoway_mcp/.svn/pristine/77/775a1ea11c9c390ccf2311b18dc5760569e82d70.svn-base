package com.sinoway.mcp.service.strade.gzt.service;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
 * 国政通身份认证子交易
 * @author zhangyanei
 * 1.0
 * 2016-1-11
 */
public class StQueryValidatorService implements  GeneralSTradeProcService {
	private McpLogger logger = McpLogger.getLogger(getClass());
	@Override
	public GeneralBusEntity busLaunch(GeneralBusEntity entity)
			throws STradeProcessException {
		DesUtil desUtil = DesUtil.getInstance();
		
		try{
			
			byte[] coremsg = entity.getCoreMsg();
			
			String coremsgstr = new String(coremsg,SystemConstant.DEFAULT_CHARSET); 
			JSONObject jo =(JSONObject) JSONObject.parse(coremsgstr);
			//拼接供应商的报文
			JSONObject supplyJson = jo.getJSONArray("bodys").getJSONObject(0);
			
			//拼接转换后的报文（请求时）
			StringBuffer sendBefore = new StringBuffer();
			sendBefore.append(supplyJson.getString("prsnnam"))
			          .append(",")
			          .append(supplyJson.getString("idcard"));
			
			String username = desUtil.encrypt(AccountConstant.GZT_WS_USERNAME,AccountConstant.GZT_WS_DESKEY,AccountConstant.GZT_WS_DESIV, SystemConstant.CHARSET_GBK);
			String password = desUtil.encrypt(AccountConstant.GZT_WS_PWD,AccountConstant.GZT_WS_DESKEY ,AccountConstant.GZT_WS_DESIV,SystemConstant.CHARSET_GBK); 
			String dataSource =desUtil.encrypt(AccountConstant.GZT_WS_STQUERYCODE,AccountConstant.GZT_WS_DESKEY ,AccountConstant.GZT_WS_DESIV,SystemConstant.CHARSET_GBK); 
			String params = desUtil.encrypt(sendBefore.toString(),AccountConstant.GZT_WS_DESKEY ,AccountConstant.GZT_WS_DESIV ,SystemConstant.CHARSET_GBK); 
			entity.setUpMsg(sendBefore.toString().getBytes(SystemConstant.DEFAULT_CHARSET));
			logger.info("向供应商发起身份认证请求....");
			logger.info("发送的请求参数：" + sendBefore.toString());
//			QueryValidatorServicesService service = new QueryValidatorServicesService();
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
//			  gws.querySingle(username, password, dataSource, params);
//			   System.out.println(querySingle);
//			QueryValidatorServices s = gztWebService.getQueryValidatorServices();
			
			
			//解密后的串	
			 String  decodestr  = null;
			try{
				decodestr  = gws.querySingle(username,password,dataSource, params);
			}catch(Exception e){
				entity.setResSta(ResSta.F.getValue());
				entity.setErrMsg(e.getMessage());
				//判断如果是超时 或者是出现异常
				if(e.getMessage().equals("Read timed out") ){
					entity.getStrdflw().setRespflg(STradeResFlg.SUPTMOUT.getValue());
				}else{
				 entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
				}
				//给核心的响应状态和描述
				entity.getCoreHeader().setStatus(ResSta.F.getValue());
				entity.getCoreHeader().setResult(e.getMessage());
				entity.setDownMsg(e.getMessage().getBytes());
				throw new STradeProcessException(e);
			}
			logger.info("向供应商发起身份认证结束....");
			entity.setDownMsg(decodestr.getBytes(SystemConstant.DEFAULT_CHARSET));// 转换前的响应数据(还没有解密)
			String  resptr = null;
			try{ 
			  resptr = desUtil.decrypt(decodestr, "12345678", "12345678", SystemConstant.CHARSET_GBK);
			}catch(Exception e){
				entity.setResSta(ResSta.F.getValue());
				entity.setErrMsg(e.getMessage());
				entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
				entity.getCoreHeader().setStatus(ResSta.F.getValue());
				entity.getCoreHeader().setResult(e.getMessage());
				throw new STradeProcessException(e);
			}
			entity.setDownMsg(resptr.getBytes(SystemConstant.DEFAULT_CHARSET));// 转换前的响应数据
 			Document document = DocumentUtil.strToDomByDefDecode(resptr);
 			logger.info("返回数据:"+resptr);
 			//Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("D:\\userCheck.xml");
 		
 			//请求message 状态和描述
 			Element reqstatus = (Element)XPathAPI.selectSingleNode(document, "/data/message/status");
 			Element reqvalue = (Element)XPathAPI.selectSingleNode(document, "/data/message/value");
 			
 			//查询message 状态和描述
 			Element status = (Element)XPathAPI.selectSingleNode(document, "/data/policeCheckInfos/policeCheckInfo/message/status");
 			Element value = (Element)XPathAPI.selectSingleNode(document, "/data/policeCheckInfos/policeCheckInfo/message/value");
 			
 			if(reqstatus.getTextContent().equals("0") && status.getTextContent().equals("0")){
 				entity.setResSta(ResSta.S.getValue());
				entity.setErrMsg(reqvalue.getTextContent());
				entity.getStrdflw().setRespflg(STradeResFlg.SUCCESS.getValue());
				entity.getCoreHeader().setStatus(ResSta.S.getValue());
				entity.getCoreHeader().setResult(value.getTextContent());
				Element compResult = (Element)XPathAPI.selectSingleNode(document, "/data/policeCheckInfos/policeCheckInfo/compResult");
				//实体参数
	 			Element name = (Element)XPathAPI.selectSingleNode(document, "/data/policeCheckInfos/policeCheckInfo/name");
	 			Element sex2 = (Element)XPathAPI.selectSingleNode(document, "/data/policeCheckInfos/policeCheckInfo/sex2");
	 			Element birthday2 = (Element)XPathAPI.selectSingleNode(document, "/data/policeCheckInfos/policeCheckInfo/birthday2");
	 			Element compStatus = (Element)XPathAPI.selectSingleNode(document, "/data/policeCheckInfos/policeCheckInfo/compStatus");
	 			Element policeadd = (Element)XPathAPI.selectSingleNode(document, "/data/policeCheckInfos/policeCheckInfo/policeadd");
	 			Element identitycard = (Element)XPathAPI.selectSingleNode(document, "/data/policeCheckInfos/policeCheckInfo/identitycard");
	 		   //拼接响应后的转化报文
	 			
	 			
	 			JSONObject repObj = new JSONObject();
				repObj.put("prsnnam", name == null ? "" : name.getTextContent());
				repObj.put("sex", sex2 == null ? "" : sex2.getTextContent());  
				repObj.put("birthday", birthday2 == null ? "" :birthday2.getTextContent());  
				repObj.put("idcradChkRes", compResult == null ? "" : compResult.getTextContent()); 
				repObj.put("cretOrg", policeadd == null ? "" :policeadd.getTextContent());
				repObj.put("idcard", identitycard == null ? "" :identitycard.getTextContent());
				JSONObject personalInf =  new JSONObject ();
				personalInf.put("personalInf", repObj);
				entity.setUpMsg(sendBefore.toString().getBytes(SystemConstant.DEFAULT_CHARSET));// 转化后的请求数据
				entity.setCoreMsg(personalInf.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));// 给核心的数据
				entity.setDownMsg(resptr.getBytes(SystemConstant.DEFAULT_CHARSET));// 转换前的响应数据
			
			
 			}else{
 				entity.setResSta(ResSta.F.getValue());
				entity.setErrMsg(reqvalue == null ? "返回报文解析异常" : reqvalue.getTextContent());
				entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
				entity.getCoreHeader().setStatus(ResSta.F.getValue());
				entity.getCoreHeader().setResult(value == null ? "返回报文解析异常" :value.getTextContent());	
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

	@Override
	public GeneralBusEntity resRecive(GeneralBusEntity entity)
			throws STradeProcessException {
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

		
	}
	
	public static void main(String[] args) {
		GeneralBusEntity i = new GeneralBusEntity();
		StQueryValidatorService  service = new StQueryValidatorService();
		try {
			service.busLaunch(i);
		} catch (STradeProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
