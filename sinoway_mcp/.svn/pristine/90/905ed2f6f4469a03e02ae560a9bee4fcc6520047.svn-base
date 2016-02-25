package com.sinoway.mcp.service.strade.fh;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
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
import com.sinoway.common.util.McpLogger;
import com.sinoway.mcp.service.strade.util.Utils;
/**
 * 法海个人司法认证服务
 * @author jintao
 *
 * @date 2016-1-14 下午3:32:47
 */
public class JustizsacheValidatorService implements GeneralSTradeProcService{
	private McpLogger logger = McpLogger.getLogger(getClass());
	@Override
	public GeneralBusEntity busLaunch(GeneralBusEntity entity)
			throws STradeProcessException {
		logger.info("接收核心发送的个人司法认证请求");
		byte[] coremsg = entity.getCoreMsg();
		String errMsg = "";
		try {
			JSONObject coreJson = JSONObject.parseObject(new String(coremsg,SystemConstant.DEFAULT_CHARSET));
			JSONObject obj = coreJson.getJSONArray("bodys").getJSONObject(0);
			String prsnnam = obj.getString("prsnnam");
			String idcard  = obj.getString("idcard");
			String url = AccountConstant.FH_OPSTURL+AccountConstant.FH_REG+"&q=pname:"+URLEncoder.encode(prsnnam,SystemConstant.DEFAULT_CHARSET)+"@idcardNo:"+idcard;
			Map<String,String> params = new HashMap<String, String>();
			
			//发送请求
			logger.info("像上游发送请求，参数："+new String(Utils.map2json(params),SystemConstant.DEFAULT_CHARSET));
			//设置请求参数
			entity.setUpMsg(Utils.map2json(params));
			String response = HttpsUtils.httpPost(url, params,HttpOvertime.HTTP_OVERTIME_CONNTIMEOUT.getCode(),HttpOvertime.HTTP_OVERTIME_SOCKETTIMEOUT.getCode());
			logger.info("接收到上游返回数据："+response.trim());
			//解析返回数据
			entity.setDownMsg(response.getBytes(SystemConstant.DEFAULT_CHARSET));
			JSONObject resJson = JSONObject.parseObject(response);
			JSONObject justjson = new JSONObject();
			JSONArray  parseJsonArray = new JSONArray();
			if(resJson.containsKey("code")){
				if(resJson.getString("code") .equals("s")){ //成功
					parseJsonArray = Utils.parseJsonArray(resJson.getJSONArray("allList"));
					justjson.put("caseLawInf", parseJsonArray);
//					设置发送给核心数据
					entity.setResSta(ResSta.S.getValue());// 成功 失败状态
					entity.setErrMsg(errMsg);   
					entity.setCoreMsg(justjson.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));
					entity.getStrdflw().setRespflg(STradeResFlg.SUCCESS.getValue());
					entity.getCoreHeader().setStatus(ResSta.S.getValue());
					entity.getCoreHeader().setResult(resJson.getString("code"));
				}else{//验证失败
//					justjson.put("caseLawInf", parseJsonArray);
					entity.setResSta(ResSta.F.getValue());// 成功 失败状态
					entity.setErrMsg(errMsg);
					entity.setCoreMsg(justjson.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));
					entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
					entity.getCoreHeader().setStatus(ResSta.F.getValue());
					entity.getCoreHeader().setResult(resJson.getString("code"));
				}
			}else{
				entity.setResSta(ResSta.F.getValue());// 成功 失败状态
				entity.setErrMsg("解析返回结果失败");
				entity.setCoreMsg(justjson.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));
				entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
				entity.getCoreHeader().setStatus(ResSta.F.getValue());
				entity.getCoreHeader().setResult(resJson.getString("code")== null ? "解析返回结果失败" :resJson.getString("code"));
			}
			
		}
		 catch (Exception e) {
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
