package com.sinoway.mcp.service.strade.telecom;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.conn.ConnectTimeoutException;

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
import com.sinoway.common.util.HttpsUtils;
import com.sinoway.common.util.MD5Utils;
import com.sinoway.common.util.McpLogger;
import com.sinoway.mcp.service.strade.util.Utils;

/**
 * 电信手机号认证服务
 * @author jintao
 *
 * @date 2016-1-16 下午12:46:05
 */
public class StTelecomValidatorService implements GeneralSTradeProcService{
	private McpLogger logger = McpLogger.getLogger(getClass());
	@Override
	public GeneralBusEntity busLaunch(GeneralBusEntity entity)
			throws STradeProcessException {
		logger.info("接收核心发送的电信手机号验证请求");
		byte[] coremsg = entity.getCoreMsg();
		String errMsg = "";
//		if(null == coremsg || coremsg.length == 0)
//				throw new  STradeProcessException("接收核心发送数据异常：coremsg不能为空");
		try {
			JSONObject coreJson = JSONObject.parseObject(new String(coremsg,SystemConstant.DEFAULT_CHARSET));
			JSONObject obj = coreJson.getJSONArray("bodys").getJSONObject(0);
			String prsnnam = obj.getString("prsnnam");
			String idcard = obj.getString("idcard");
			String mobile = obj.getString("mobile");
			String url = AccountConstant.CHINATELECOM_POSTURL+AccountConstant.CHINATELECOM_PRODUCT+AccountConstant.CHINATELECOM_VERIFYUSER+AccountConstant.CHINATELECOM_AIPKEY+"/"+Utils.gettoken()+".json";
			url+="?mdn="+mobile;
			url+="&type=clear";
			url+="&idType=idCard";
			url+="&idNoHash="+MD5Utils.MD5(idcard);
			url+="&nameHash="+MD5Utils.MD5(prsnnam);
			//这里没有用post 设置map只是为了保存
			Map<String,String> params = new HashMap<String, String>();
			params.put("mdn", mobile);
			params.put("type", "clear");
			params.put("idType", "idCard");
			params.put("idNoHash", MD5Utils.MD5(idcard));
			params.put("nameHash", MD5Utils.MD5(prsnnam));
			
			
			logger.info("向上游发送请求，参数："+new String(Utils.map2json(params),SystemConstant.DEFAULT_CHARSET));
//			//设置请求参数
			entity.setUpMsg(Utils.map2json(params));
			String response = HttpsUtils.executeGet(url,HttpOvertime.HTTP_OVERTIME_CONNTIMEOUT.getCode(),HttpOvertime.HTTP_OVERTIME_SOCKETTIMEOUT.getCode());
			logger.info("接收到上游返回数据："+response);
			//解析返回数据
			entity.setDownMsg(response.getBytes(SystemConstant.DEFAULT_CHARSET));
			JSONObject resJson = JSONObject.parseObject(response);
			JSONObject json  = new JSONObject();
			JSONObject mobileChkResJson = new JSONObject();
			if(resJson.containsKey("code") && resJson.containsKey("status")){
				if(resJson.getString("code") .equals("200") && resJson.getString("status").equals("SUCCEED")){ //成功接收请求
					//解析电信返回数据
					json = resJson.getJSONObject("data");
					if(json.getInteger("idTypeCheckResult") == 0 ){
						
//						json.getInteger("idNoCheckResult") == 0 && json.getInteger("nameCheckResult") == 0
						if(json.getInteger("idNoCheckResult") == 0 && json.getInteger("nameCheckResult") == 0){
							json.put("mobileChkRes", "一致");
							
						}else if(json.getInteger("idNoCheckResult") == 0 && json.getInteger("nameCheckResult") == 1 ){
							json.put("mobileChkRes", "手机号码 验证一致，姓名不一致");
						}
						
						//验证成功
						mobileChkResJson.put("mobileChkRes", json);
//						设置发送给核心数据
						entity.setResSta(ResSta.S.getValue());// 成功 失败状态
						entity.setErrMsg(errMsg);   
						entity.setCoreMsg(mobileChkResJson.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));
						entity.getStrdflw().setRespflg(STradeResFlg.SUCCESS.getValue());
						entity.getCoreHeader().setStatus(ResSta.S.getValue());
						entity.getCoreHeader().setResult(resJson.getString("code"));
					}else{
						json.put("mobileChkRes", "不一致");
						mobileChkResJson.put("mobileChkRes", json);
						entity.setResSta(ResSta.S.getValue());// 成功 失败状态
						entity.setErrMsg(errMsg);
						entity.setCoreMsg(mobileChkResJson.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));
						entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
						entity.getCoreHeader().setStatus(ResSta.F.getValue());
						entity.getCoreHeader().setResult(resJson.getString("code"));
					}
				}else{//验证失败
					entity.setResSta(ResSta.F.getValue());
					entity.setErrMsg("请求返回异常");
					entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
					entity.getCoreHeader().setStatus(ResSta.F.getValue());
					entity.getCoreHeader().setResult(resJson.getString("message") == null ? "请求返回异常" :resJson.getString("message"));
				}
			}
			
		} catch (Exception e) {
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
