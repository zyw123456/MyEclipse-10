package com.sinoway.mcp.service.strade.imate.service;


import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.alibaba.fastjson.JSONObject;
import com.sinoway.base.entity.BCfgdefFnttrnaddr;
import com.sinoway.common.constant.ServerConstant.ResSta;
import com.sinoway.common.constant.SystemConstant;
import com.sinoway.common.constant.SystemConstant.STradeResFlg;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.exception.STradeProcessException;
import com.sinoway.common.service.server.GeneralSTradeProcService;
import com.sinoway.mcp.entity.FDatMetatrnflw;
import com.sinoway.mcp.service.strade.imate.token.TokenSington;
import com.sinoway.mcp.service.strade.imate.ws.UserCheckService;
import com.sinoway.mcp.service.strade.imate.ws.UserCheckServiceSoap;


/**
 * 亿美 是否是高危人群
 * @author zhangyanwei
 * 1.0
 * 2016-1-13
 */
public class StExceedQueryUsercheckService  implements  GeneralSTradeProcService {

		
	public GeneralBusEntity busLaunch(GeneralBusEntity entity)
			throws STradeProcessException {
		try{
			
			byte[] coremsg = entity.getCoreMsg();
			String coremsgstr = new String(coremsg,SystemConstant.DEFAULT_CHARSET); 
			JSONObject jo =(JSONObject) JSONObject.parse(coremsgstr);
			//拼接供应商的报文
			JSONObject supplyJson =jo.getJSONArray("bodys").getJSONObject(0);
			
			
			
			//解密后的串	
			 String  decodestr  = null;
			try{
				JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
				factory.setServiceClass(UserCheckServiceSoap.class);
				factory.setAddress("http://116.58.219.253:9099/HD_Check/User_CheckService.asmx?wsdl");
				UserCheckServiceSoap imws = (UserCheckServiceSoap) factory.create();
				   Client proxy = ClientProxy.getClient(imws);
				   HTTPConduit conduit = (HTTPConduit) proxy.getConduit();
				   HTTPClientPolicy policy = new HTTPClientPolicy();
				   policy.setConnectionTimeout(10000); //连接超时时间
				   policy.setReceiveTimeout(60000);//请求超时时间.
				   conduit.setClient(policy);
				entity.setUpMsg(supplyJson.getString("mobile").getBytes());
				decodestr = imws.getBeOverdue(supplyJson.getString("mobile"), "12",	TokenSington.gettoken());
				entity.setDownMsg(decodestr.getBytes());
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
 	        JSONObject   checkuserJson  = (JSONObject) JSONObject.parse(decodestr);
 	        
 			//解析响应报文后的状态和描述
 			if(checkuserJson.getString("CODE").equals("200")){
 				entity.setResSta(ResSta.S.getValue());
				entity.setErrMsg("SUCCESS");
				entity.getStrdflw().setRespflg(STradeResFlg.SUCCESS.getValue());
				entity.getCoreHeader().setStatus(ResSta.S.getValue());
				entity.getCoreHeader().setResult("SUCCESS");
	 		   //拼接响应后的转化报文
	 			JSONObject repObj = new JSONObject();
				repObj.put("highRiskPsnRes", Integer.valueOf(checkuserJson.getString("COUNTS")) >0 ? "是":"否" );                  //逾期次数
				repObj.put("highRiskPsnCert", "40%");                     //逾期贷款验证可信度
				JSONObject obj  = new JSONObject();
				obj.put("highRiskPsnChkInf", repObj);
				entity.setCoreMsg(obj.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));// 给核心的数据
 			}else{
 				entity.setResSta(ResSta.F.getValue());
				entity.setErrMsg("解析返回报文异常");
				entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
				entity.getCoreHeader().setStatus(ResSta.F.getValue());
				entity.getCoreHeader().setResult(checkuserJson.getString("errmsg") == null ? "解析返回报文异常": checkuserJson.getString("errmsg"));	
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
		StExceedQueryUsercheckService  service = new StExceedQueryUsercheckService();
		try {
			service.busLaunch(i);
		} catch (STradeProcessException e) {
			e.printStackTrace();
		}
	}
}
