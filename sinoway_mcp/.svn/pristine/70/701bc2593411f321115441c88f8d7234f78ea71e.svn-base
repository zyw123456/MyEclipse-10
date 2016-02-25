package com.sinoway.mcp.service.strade.gzt.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


import com.alibaba.fastjson.JSONObject;
import com.sinoway.base.entity.BCfgdefFnttrnaddr;
import com.sinoway.common.constant.AccountConstant;
import com.sinoway.common.constant.ServerConstant.ResSta;
import com.sinoway.common.constant.SystemConstant;
import com.sinoway.common.constant.SystemConstant.STradeResFlg;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.exception.STradeProcessException;
import com.sinoway.common.service.server.GeneralSTradeProcService;
import com.sinoway.common.util.HttpsUtils;
import com.sinoway.common.util.MD5Utils;
import com.sinoway.common.util.McpLogger;
import com.sinoway.common.util.UUID32;
import com.sinoway.mcp.service.strade.util.Utils;
import com.sun.corba.se.impl.orbutil.threadpool.TimeoutException;
/**
 * 国政通银行卡验证接口
 * @author jintao
 *
 * @date 2016-1-14 上午10:13:10
 */
public class BankCardValidatorService implements  GeneralSTradeProcService{
	private McpLogger logger = McpLogger.getLogger(getClass());
	@Override
	public GeneralBusEntity busLaunch(GeneralBusEntity entity) throws STradeProcessException {
		logger.info("接收核心发送的银行卡验证请求");
		byte[] coremsg = entity.getCoreMsg();
		String errMsg = "";
//		if(null == coremsg || coremsg.length == 0)
//				throw new  STradeProcessException("接收核心发送数据异常：coremsg不能为空");
		try {
			JSONObject coreJson = JSONObject.parseObject(new String(coremsg,SystemConstant.DEFAULT_CHARSET));
			JSONObject obj = coreJson.getJSONArray("bodys").getJSONObject(0);
			String prsnnam = obj.getString("prsnnam");
			String bnkCrdNo  = obj.getString("bnkCrdNo");
			String url = AccountConstant.GZT_BANKCARD_AUTH_TEST_URL+"cardNameCheck_"+AccountConstant.GZT_ICPCODE;
			//订单号
			String order_id = UUID32.getUUID();
			String sign = AccountConstant.GZT_STID+bnkCrdNo+prsnnam+order_id+AccountConstant.GZT_BANK_AUTH_KEY;
			//拼接请求数据
			String code = MD5Utils.str2MD5(sign);
			//设置请求参数
			Map<String,String> params = new HashMap<String, String>();
			params.put("stid", AccountConstant.GZT_STID);
			params.put("cardnum",bnkCrdNo);
			params.put("name", prsnnam);
			params.put("order_id", order_id);
			params.put("code", code);
			logger.info("上游发送请求，参数："+new String(Utils.map2json(params),SystemConstant.DEFAULT_CHARSET));
			entity.setUpMsg(Utils.map2json(params));
			//发送请求
			String response = HttpsUtils.sendPostSSLRequest(url, params, SystemConstant.DEFAULT_CHARSET,10000,60000);
			logger.info("接收到上游返回数据："+response);
			entity.setDownMsg(response.getBytes(SystemConstant.DEFAULT_CHARSET));
			//解析返回数据
			
			JSONObject json  = new JSONObject();
			JSONObject bnkcrdChkInfJson = new JSONObject();
			
			
			if(null != response && response.equals("0")){
				json.put("bnkcrdChkRes", "无记录");
				bnkcrdChkInfJson.put("bnkcrdChkInf", json);
				entity.setResSta(ResSta.S.getValue());// 成功 失败状态
				entity.setErrMsg(errMsg);
				entity.setCoreMsg(bnkcrdChkInfJson.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));
				entity.getStrdflw().setRespflg(STradeResFlg.SUCCESS.getValue());
				entity.getCoreHeader().setStatus(ResSta.S.getValue());
				entity.getCoreHeader().setResult("1");
			}else{
			
			JSONObject resJson = JSONObject.parseObject(response);
			
			if(resJson.containsKey("code")){
				String resCode = resJson.getString("code");
				if(resCode.equals("1")){ //成功
					json.put("bnkcrdChkRes", "一致");
					bnkcrdChkInfJson.put("bnkcrdChkInf", json);
//					设置发送给核心数据
					entity.setResSta(ResSta.S.getValue());// 成功 失败状态
					entity.setErrMsg(errMsg);   
					entity.setCoreMsg(bnkcrdChkInfJson.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));
					entity.getStrdflw().setRespflg(STradeResFlg.SUCCESS.getValue());
					entity.getCoreHeader().setStatus(ResSta.S.getValue());
					entity.getCoreHeader().setResult(resJson.getString("code"));
				}else if(resCode.equals("0")){//验证失败
					json.put("bnkcrdChkRes", "无记录");
					bnkcrdChkInfJson.put("bnkcrdChkInf", json);
					entity.setResSta(ResSta.F.getValue());// 成功 失败状态
					entity.setErrMsg(errMsg);
					entity.setCoreMsg(bnkcrdChkInfJson.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));
					entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
					entity.getCoreHeader().setStatus(ResSta.F.getValue());
					entity.getCoreHeader().setResult(resJson.getString("code"));
				}else{
					json.put("bnkcrdChkRes", "不一致");
					bnkcrdChkInfJson.put("bnkcrdChkInf", json);
					entity.setResSta(ResSta.S.getValue());// 成功 失败状态
					entity.setErrMsg(errMsg);
					entity.setCoreMsg(bnkcrdChkInfJson.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));
					entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
					entity.getCoreHeader().setStatus(ResSta.F.getValue());
					entity.getCoreHeader().setResult(resJson.getString("code"));
				}
			}
			else{
				
					entity.setResSta(ResSta.F.getValue());// 成功 失败状态
					entity.setErrMsg("返回结果解析失败");
					entity.setCoreMsg(bnkcrdChkInfJson.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));
					entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
					entity.getCoreHeader().setStatus(ResSta.F.getValue());
					entity.getCoreHeader().setResult(resJson.getString("code"));
				
				
			}
		}
		} catch (Exception e) {
			entity.setDownMsg(e.getMessage().getBytes());
			entity.setResSta(ResSta.F.getValue());
			entity.setErrMsg(e.getMessage());
			entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
			entity.getCoreHeader().setStatus(ResSta.F.getValue());
			entity.getCoreHeader().setResult(e.getMessage());
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
