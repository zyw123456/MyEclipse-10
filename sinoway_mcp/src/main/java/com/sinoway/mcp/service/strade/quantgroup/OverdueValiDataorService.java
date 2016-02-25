package com.sinoway.mcp.service.strade.quantgroup;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.conn.ConnectTimeoutException;

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
import com.sinoway.common.util.HttpsUtils;
import com.sinoway.common.util.MD5Utils;
import com.sinoway.common.util.McpLogger;
import com.sinoway.mcp.service.strade.util.Utils;

/**
 * 量化派服务
 * @author jintao
 *
 * @date 2016-1-14 下午5:06:03
 */
public class OverdueValiDataorService implements  GeneralSTradeProcService {
	private McpLogger logger = McpLogger.getLogger(getClass());
	@Override
	public GeneralBusEntity busLaunch(GeneralBusEntity entity)
			throws STradeProcessException {
		logger.info("接收核心发送的逾期查询请求");
		byte[] coremsg = entity.getCoreMsg();
		String errMsg = "";
//		if(null == coremsg || coremsg.length == 0)
//				throw new  STradeProcessException("接收核心发送数据异常：coremsg不能为空");
		try {
			JSONObject coreJson = JSONObject.parseObject(new String(coremsg,SystemConstant.DEFAULT_CHARSET));
			JSONObject obj = coreJson.getJSONArray("bodys").getJSONObject(0);
			String prsnnam = obj.getString("prsnnam");
			String idcard  = obj.getString("idcard");
			long timestmp = System.currentTimeMillis();
			String all = "timeunit=" + timestmp +"appkey="+AccountConstant.LHP_APPKEY;
			String url = AccountConstant.LHP_URL+AccountConstant.LHP_OVERDUE_URL;
			//拼接请求数据
			Map<String,String> params = new HashMap<String, String>();
			params.put("appId", AccountConstant.LHP_APPID);
			params.put("timeunit", String.valueOf(timestmp));
			params.put("token", MD5Utils.str2MD5(all));
			params.put("userId",AccountConstant.LHP_APPID+"_jttest"); //TODO 换成公司的
			params.put("realName", prsnnam);
			params.put("cardId", idcard);
			params.put("cardType", "0");
			//发送请求
			logger.info("向上游发送请求，参数："+new String(Utils.map2json(params),SystemConstant.DEFAULT_CHARSET));
			//设置请求参数
			entity.setUpMsg(Utils.map2json(params));
			String response = HttpsUtils.sendPostSSLRequest(url, params, SystemConstant.DEFAULT_CHARSET,HttpOvertime.HTTP_OVERTIME_CONNTIMEOUT.getCode(),HttpOvertime.HTTP_OVERTIME_SOCKETTIMEOUT.getCode());
			logger.info("接收到上游返回数据："+response);
			//解析返回数据
			entity.setDownMsg(response.getBytes(SystemConstant.DEFAULT_CHARSET));
			JSONObject resJson = JSONObject.parseObject(response);
			JSONObject json  = new JSONObject();
			JSONObject  loanOvrChkJson = new JSONObject();
			if(resJson.containsKey("RESULT")){
				if(resJson.getString("RESULT") .equals("0")){ //成功
					JSONArray array = resJson.getJSONArray("DATA");
					json = Utils.parseLhpJsonArray(array);
//					json.put("bnkcrdChkRes", "一致");
					loanOvrChkJson.put("loanOvrChkInf", json);
//					设置发送给核心数据
					entity.setResSta(ResSta.S.getValue());// 成功 失败状态
					entity.setErrMsg(errMsg);   
					entity.setCoreMsg(loanOvrChkJson.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));
					entity.getStrdflw().setRespflg(STradeResFlg.SUCCESS.getValue());
					entity.getCoreHeader().setStatus(ResSta.S.getValue());
					entity.getCoreHeader().setResult(resJson.getString("code"));
				}else{//验证失败
					json.put("loanOvrChkInf", "无记录");
					loanOvrChkJson.put("bnkcrdChkInf", json);
					entity.setResSta(ResSta.S.getValue());// 成功 失败状态
					entity.setErrMsg(errMsg);
					entity.setCoreMsg(loanOvrChkJson.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));
					entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
					entity.getCoreHeader().setStatus(ResSta.F.getValue());
					entity.getCoreHeader().setResult(resJson.getString("code"));
				}
			}else{
				errMsg = "返回结果异常";
				entity.setResSta(ResSta.F.getValue());// 成功 失败状态
				entity.setErrMsg(errMsg);
				entity.setCoreMsg(errMsg.getBytes(SystemConstant.DEFAULT_CHARSET));
				entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
				entity.getCoreHeader().setStatus(ResSta.F.getValue());
				entity.getCoreHeader().setResult(resJson.getString("code") == null ? errMsg :resJson.getString("code"));
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
