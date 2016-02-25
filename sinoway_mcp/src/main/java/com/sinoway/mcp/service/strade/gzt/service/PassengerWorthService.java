package com.sinoway.mcp.service.strade.gzt.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.sinoway.common.util.DateUtil;
import com.sinoway.common.util.DocumentUtil;
import com.sinoway.common.util.HttpsUtils;
import com.sinoway.common.util.MD5Utils;
import com.sinoway.common.util.McpLogger;
import com.sinoway.mcp.service.strade.util.Utils;

public class PassengerWorthService implements GeneralSTradeProcService{
	private McpLogger logger = McpLogger.getLogger(getClass());
	@Override
	public GeneralBusEntity busLaunch(GeneralBusEntity entity)
			throws STradeProcessException {
		

		logger.info("接收核心发送的乘机人信息请求");
		byte[] coremsg = entity.getCoreMsg();
		String errMsg = "";
//		if(null == coremsg || coremsg.length == 0)
//				throw new  STradeProcessException("接收核心发送数据异常：coremsg不能为空");
		try {
			JSONObject coreJson = JSONObject.parseObject(new String(coremsg,SystemConstant.DEFAULT_CHARSET));
			JSONObject obj = coreJson.getJSONArray("bodys").getJSONObject(0);
			String time = DateUtil.dateToString(new Date(), "yyyyMMdd");
			String prsnnam = obj.getString("prsnnam");
			String idcard  = obj.getString("idcard");
			String passport = obj.containsKey("passport")?obj.getString("passport"):"";
			int imonth = 6; //TODO 这里可以设置成 3 ，6,12 如果需要设置 要加参数
			//拼接MD5校验字符串
			String md5 =passport+AccountConstant.GZT_PASSENGERSTATID_HASHCODE+imonth+prsnnam+idcard+AccountConstant.GZT_PASSENGERSTATID_KEY+time;
			String url = AccountConstant.GZT_PLANE_URL+AccountConstant.GZT_PASSENGERSTATID_URL;
			//设置请求参数
			Map<String,String> params = new HashMap<String, String>();
			params.put("HashCode", AccountConstant.GZT_PASSENGERSTATID_HASHCODE);
			params.put("name", prsnnam);
			params.put("pid",idcard);
			params.put("gid", passport);
			params.put("imonth", ""+imonth);
			params.put("sign", MD5Utils.str2MD5(md5));
			entity.setUpMsg(Utils.map2json(params));
			logger.info("像上游发送请求，参数："+new String(Utils.map2json(params),SystemConstant.DEFAULT_CHARSET));
			//发送请求
			String response = HttpsUtils.sendPostSSLRequest(url, params, SystemConstant.DEFAULT_CHARSET,HttpOvertime.HTTP_OVERTIME_CONNTIMEOUT.getCode(),HttpOvertime.HTTP_OVERTIME_SOCKETTIMEOUT.getCode());
			logger.info("接收到上游返回数据："+response);
			//解析返回数据
			entity.setDownMsg(response.getBytes());
			//解析xml
			Map<String,Object> map = DocumentUtil.readxml(Utils.getStringStream(response), SystemConstant.CHARSET_GBK);
			String errorcode = null;
			JSONObject resJson = new JSONObject();
			JSONObject jsonObj = new JSONObject();
			if(map!= null && map.containsKey("Err_code")){
				errorcode = (String) map.get("Err_code");
				if(errorcode.equals("200")){//成功
					//转换核心报文
					jsonObj.put("airFlyAllCnt", map.get("flighttimes") == null ? "" :map.get("flighttimes"));
					jsonObj.put("airBusyMth", map.get("flightMonth") == null ? "" : map.get("flightMonth"));
					jsonObj.put("airBusyMthCnt", map.get("flighttimes") == null ? "" : map.get("flighttimes"));
					jsonObj.put("airFstClasCnt", map.get("Fcabin") == null ? "" : map.get("Fcabin"));
					jsonObj.put("airBusinessCnt", map.get("Ccabin") == null ? "" : map.get("Ccabin"));
					jsonObj.put("airCochClasCnt", map.get("Ycabin") == null ? "" : map.get("Ycabin"));
					jsonObj.put("airFreqFrmCity", map.get("fromcity") == null ? "" : map.get("fromcity"));
					jsonObj.put("airFreqArvCity", map.get("destcity") == null ? "" : map.get("destcity"));
					jsonObj.put("airCompRideMost", map.get("airline") == null ? "" :map.get("airline") );
					jsonObj.put("airDomesticCnt", map.get("Cncount") == null ? "" : map.get("Cncount"));
					jsonObj.put("airInternalCnt", map.get("Intercount") == null ? "" : map.get("Intercount"));
					jsonObj.put("airAllMileage", map.get("flytotaltpm") == null ? "" : map.get("flytotaltpm"));
					jsonObj.put("airPriceAvg", map.get("Avgprice") == null ? "" : map.get("Avgprice"));
					jsonObj.put("airFreePassCnt", map.get("freeCount") == null ? "" : map.get("freeCount") );
					jsonObj.put("airTickBefDayAvg", map.get("AvgTicketday") == null ? "" :  map.get("AvgTicketday"));
					jsonObj.put("lstAirFlyDte", map.get("lastflightdate") == null ? "" : map.get("lastflightdate"));
					jsonObj.put("lstAirFrmCity", map.get("lastfromcity") == null ? "" : map.get("lastfromcity"));
					jsonObj.put("lstAirArvCity", map.get("lastdestcity") == null ? "" : map.get("lastdestcity"));
					jsonObj.put("airDis1YearAvg", map.get("Avgdiscount") == null ? "" : map.get("Avgdiscount") );
					jsonObj.put("airDelayTims",map.get("tsdelay")  == null ? "" : map.get("tsdelay"));
					jsonObj.put("airDelayTimsAvg", map.get("Avgdelay") == null ? "" : map.get("Avgdelay") );
					resJson.put("airFlyInf", jsonObj);
					entity.setResSta(ResSta.S.getValue());// 成功 失败状态
					entity.setErrMsg(errMsg);   
					entity.setCoreMsg(resJson.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));
					entity.getStrdflw().setRespflg(STradeResFlg.SUCCESS.getValue());
					entity.getCoreHeader().setStatus(ResSta.S.getValue());
					entity.getCoreHeader().setResult(resJson.getString("code"));
				}else if(errorcode.equals("404")){
					resJson.put("airFlyInf", jsonObj);
					entity.setResSta(ResSta.S.getValue());// 成功 失败状态
					entity.setErrMsg(errMsg);   
					entity.setCoreMsg(resJson.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));
					entity.getStrdflw().setRespflg(STradeResFlg.SUCCESS.getValue());
					entity.getCoreHeader().setStatus(ResSta.S.getValue());
					entity.getCoreHeader().setResult(resJson.getString("code"));
				}
				
				else{
					entity.setResSta(ResSta.F.getValue());
					entity.setErrMsg(map.get("Err_content") == null ? "无法解析返回数据" :map.get("Err_content").toString());
					entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
					entity.getCoreHeader().setStatus(ResSta.F.getValue());
					entity.getCoreHeader().setResult(map.get("Err_content") == null ? "无法解析返回数据" :map.get("Err_content").toString());
				}
			}else{
				entity.setResSta(ResSta.F.getValue());
				entity.setErrMsg("无法获取xml数据");
				entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
				entity.getCoreHeader().setStatus(ResSta.F.getValue());
				entity.getCoreHeader().setResult("无法获取xml数据");
				
			}
				
			
		}
//		
		catch (Exception e) {
			entity.setResSta(ResSta.F.getValue());
			entity.setErrMsg(e.getMessage());
			entity.setDownMsg(e.getMessage().getBytes());
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
