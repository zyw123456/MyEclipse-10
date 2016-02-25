package com.sinoway.stm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.sinoway.acc.entity.PeopleShowInfo;
import com.sinoway.common.entity.HttpCommonEntity;
import com.sinoway.common.entity.HttpResponseCommonEntity;
import com.sinoway.common.exception.HttpException;
import com.sinoway.common.service.IHttpProviderService;
import com.sinoway.common.util.Constant;
import com.sinoway.stm.entity.StmResMsg;
import com.sinoway.stm.service.IStmMsgtrnService;

/**
 * 报文传输服务
 * @author xiehao
 *
 */
public class StmMsgtrnService implements IStmMsgtrnService{
	private IHttpProviderService httpProviderService;//报文组件

	public StmResMsg addOrUpdatePrd(String oprator,String appcod,String prdcod,String prdnam,List<Map<String,String>> trns,List<PeopleShowInfo> peoples){
		HttpCommonEntity entity = new HttpCommonEntity(Constant.getConfigUrl());//请求报文实体
		//entity.setUrl("http://127.0.0.1:8080/sinoway_plat/McpHttpServlet");
		entity.setMethod(Constant.HttpMethod.HTTP_METHOD_POST.getCode());
		JSONObject params = new JSONObject();//请求的json对象,需转为string
		//注入json参数
		params.put("operator", oprator);
		params.put("appcod", appcod);
		if(null != prdcod){
			params.put("prdcod", prdcod);
			params.put("msgcode", Constant.MsgCode.MSGCODE_UPDATEPRD.getValue());
		}else{
			String usrids = getUsrids(peoples);
			params.put("usrids", usrids);
			params.put("msgcode", Constant.MsgCode.MSGCODE_ADDPRD.getValue());
		}
		params.put("prdnam", prdnam);
		String trncods = getTrnCods(appcod,trns);
		params.put("trncods", trncods);
		try {
			entity.setParams(httpProviderService.parseStringToListParam(params.toString()));
			entity = httpProviderService.httpCommunicate(entity);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获得回复的信息
		//JSONObject resJson =JSONObject.fromObject(entity.getResponse());
		HttpResponseCommonEntity resEntity = entity.getResponse();
		StmResMsg resmsg = null;
		if(resEntity == null){
			resmsg = new StmResMsg();
			resmsg.setPrdcod(null);
		}else{
			String message = (String) resEntity.getReturnObj();
			if(message == null || "".equals(message)){
				resmsg = new StmResMsg();
				resmsg.setPrdcod(null);
			}else{
				JSONObject resJson =JSONObject.fromObject(message);
				//回复信息
				//String prdcod = String.valueOf(TempIdGenerator.getInstance().getID());
				resmsg = new StmResMsg();
				resmsg.setPrdcod((String)resJson.get("prdcod"));
				resmsg.setMsgcode((String)resJson.get("msgcode"));
				resmsg.setOprator((String)resJson.get("oprator"));
				resmsg.setResult((String)resJson.get("result"));
				resmsg.setStates((String)resJson.get("states"));
			}
		}
		return resmsg;
	}

	private String getTrnCods(String appcod,List<Map<String,String>> trns){
		String trncods ="";
		if(!"003".equals(appcod)){
			for(Map<String,String> map:trns){
				trncods += (map.get("trncod")+",");
			}
			trncods = trncods.substring(0, trncods.length()-1);
		}else{
			List<String> tmpTrncods = new ArrayList<String>();
			JSONObject json ;
			for(Map<String,String> map:trns){
				json=new JSONObject();
				json.put("trncod", map.get("trncod"));
				json.put("trnnam", map.get("trnnam"));
				json.put("dayexpcnt", map.get("dayexpcnt"));
				json.put("monexpcnt", map.get("monexpcnt"));
				json.put("mon3expcnt", map.get("mon3expcnt"));
				tmpTrncods.add(json.toString());
			}
			trncods = tmpTrncods.toString();  
			System.out.println(trncods);
		}
		return trncods;
	}


	private String getUsrids(List<PeopleShowInfo> peoples) {
		String usrids = "";
		if(null!= peoples && peoples.size()>0){
			for(PeopleShowInfo p:peoples){
				usrids+=p.getUsrId()+",";
			}
			if(null != usrids && usrids.length()>1){
				usrids = usrids.substring(0, usrids.length()-1);
			}
		}
		return usrids;
	}


	public void setHttpProviderService(IHttpProviderService httpProviderService) {
		this.httpProviderService = httpProviderService;
	}


	@Override
	public StmResMsg delPrd(String oprator, String prdcod,String appcod) {
		HttpCommonEntity entity = new HttpCommonEntity(Constant.getConfigUrl());//请求报文实体
		//entity.setUrl("http://127.0.0.1:8080/sinoway_plat/McpHttpServlet");
		entity.setMethod(Constant.HttpMethod.HTTP_METHOD_POST.getCode());
		JSONObject params = new JSONObject();//请求的json对象,需转为string
		//注入json参数
		params.put("msgcode", Constant.MsgCode.MSGCODE_DELPRD.getValue());
		params.put("operator", oprator);
		params.put("prdcod", prdcod);
		params.put("appcod", appcod);
		try {
			entity.setParams(httpProviderService.parseStringToListParam(params.toString()));
			entity = httpProviderService.httpCommunicate(entity);
			entity.getResponse().getReponse().close();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				throw new Exception();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		//获得回复的信息
		//JSONObject resJson =JSONObject.fromObject(entity.getResponse());
		HttpResponseCommonEntity resEntity = entity.getResponse();
		StmResMsg resmsg = null;
		if(resEntity == null){
			resmsg = new StmResMsg();
			resmsg.setPrdcod(null);
		}else{
			String message = (String) resEntity.getReturnObj();
			if(message == null || "".equals(message)){
				resmsg = new StmResMsg();
				resmsg.setPrdcod(null);
			}else{
				JSONObject resJson =JSONObject.fromObject(message);
				//回复信息
				resmsg = new StmResMsg();
				resmsg.setMsgcode((String)resJson.get("msgcode"));
				resmsg.setOprator((String)resJson.get("oprator"));
				resmsg.setResult((String)resJson.get("result"));
				resmsg.setStates((String)resJson.get("states"));
			}
		}
		return resmsg;
	}


}
