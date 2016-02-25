package com.sinoway.mcp.service.strade.unicom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSONObject;
import com.sinoway.base.entity.BCfgdefFnttrnaddr;
import com.sinoway.common.constant.HttpConstant.HttpOvertime;
import com.sinoway.common.constant.ServerConstant.ResSta;
import com.sinoway.common.constant.SystemConstant;
import com.sinoway.common.constant.SystemConstant.STradeResFlg;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.exception.STradeProcessException;
import com.sinoway.common.service.server.GeneralSTradeProcService;
import com.sinoway.common.util.DateUtil;
import com.sinoway.common.util.HttpsUtils;
import com.sinoway.common.util.MD5Utils;
import com.sinoway.mcp.entity.FDatMetatrnflw;
import com.sinoway.mcp.service.strade.util.Utils;


/**
 * 联通手机号验证
 * @author zhangyanei
 * 1.0
 * 2016-1-11
 */
public class StLinkPhoneValidatorService implements  GeneralSTradeProcService {
 
	

	@Override
	public GeneralBusEntity busLaunch(GeneralBusEntity entity)
			throws STradeProcessException {
		
		try{
			
			byte[] coremsg = entity.getCoreMsg();
			
			String coremsgstr = new String(coremsg,SystemConstant.DEFAULT_CHARSET); 
			JSONObject jo =(JSONObject) JSONObject.parse(coremsgstr);
			//拼接供应商的报文
			JSONObject supplyJson = jo.getJSONArray("bodys").getJSONObject(0);
			
			//拼接转换后的报文（请求时）
			Map<String,String> params = new HashMap<String,String>();
		 	String curTime  = DateUtil.dateToString(new Date(), "yyyyMMddHHmmss");
			String sequence = this.getCharAndNumr(8);
			String url = "http://zx.wopaper.com/crp/uniquery/userIdentity.do";
			String all = supplyJson.getString("mobile")+"_chinadatagroup_chinadatagroup)(*&_"+curTime+"_"+sequence;
//			18501373321_chinadatagroup_chinadatagroup)(*&_20160125162656_4Ge9pLFD
//			18618257313_chinadatagroup_chinadatagroup)(*&_20160125162727_u45ukeBS
			params.put("sendTelNo", supplyJson.getString("mobile"));
			params.put("certType","0101");//0101代表身份证
			params.put("certCode", supplyJson.getString("idcard"));
			params.put("userName", supplyJson.getString("prsnnam"));
			params.put("orgCode","chinadatagroup");
			params.put("CurTime",curTime);
			params.put("sequence", sequence);
			params.put("orgSeq",MD5Utils.MD5(all));
			entity.setUpMsg(Utils.map2json(params));
			//解密后的串	
			 String  decodestr  = null;
			try{
				decodestr  = HttpsUtils.httpPost(url, params,HttpOvertime.HTTP_OVERTIME_CONNTIMEOUT.getCode(),HttpOvertime.HTTP_OVERTIME_SOCKETTIMEOUT.getCode());
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
				throw new STradeProcessException(e);
			}
			
			JSONObject repObj = new JSONObject();
			entity.setDownMsg(decodestr.getBytes(SystemConstant.DEFAULT_CHARSET));// 转换前的响应数据(还没有解密)
			JSONObject  resptr = null;
			try{ 
				resptr =(JSONObject) JSONObject.parse(decodestr);
			}catch(Exception e){
				entity.setResSta(ResSta.F.getValue());
				entity.setErrMsg(e.getMessage());
				entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
				entity.getCoreHeader().setStatus(ResSta.F.getValue());
				entity.getCoreHeader().setResult(e.getMessage());
				throw new STradeProcessException(e);
			}
			
 			if(null!=resptr && resptr.getString("status").equals("1")){
 				entity.setResSta(ResSta.S.getValue());
				entity.setErrMsg(resptr.getString("errorDesc"));
				FDatMetatrnflw sEntity = entity.getStrdflw();
				sEntity.setRespflg(STradeResFlg.SUCCESS.getValue());
				entity.getCoreHeader().setStatus(ResSta.S.getValue());
				entity.getCoreHeader().setResult(resptr.getString("errorDesc"));
	 		   //拼接响应后的转化报文
				JSONObject backObj = new JSONObject();
				String status = "";
				String checkResult = resptr.getString("checkResult");
				if(checkResult.equals("00")){
					status = "一致";
				}else if(checkResult.equals("01")){
					status = " 手机号一致，证件号和姓名不一致";
				}else if(checkResult.equals("02")){
					status = "手机号和证件号一致，姓名不一致";
				}else if(checkResult.equals("03")){
					status  = "手机号和姓名一致，证件号不一致";
				}else {
					status = "无此号码";
				}
				backObj.put("mobileChkRes",status);
	 			repObj.put("mobileChkInf", backObj);
//				entity.setDownMsg(decodestr.getBytes(SystemConstant.DEFAULT_CHARSET));// 转换前的响应数据
			
 			}else{
 				entity.setResSta(ResSta.F.getValue());
				entity.setErrMsg("报文返回异常");
				entity.getStrdflw().setRespflg(STradeResFlg.ERROR.getValue());
				entity.getCoreHeader().setStatus(ResSta.F.getValue());
				entity.getCoreHeader().setResult(resptr.getString("errorDesc") == null ? "报文返回异常" : resptr.getString("errorDesc"));	
 			}
// 			byte[] bs = resptr.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET);
//			entity.setDownMsg(bs);// 转换前的响应数据
 			entity.setCoreMsg(repObj.toJSONString().getBytes(SystemConstant.DEFAULT_CHARSET));// 给核心的数据
 			
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
		StLinkPhoneValidatorService  service = new StLinkPhoneValidatorService();
		try {
			service.busLaunch(i);
		} catch (STradeProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	 private  String getCharAndNumr(int length) {
	        String val = "";
	        Random random = new Random();
	        for (int i = 0; i < length; i++) {
	            // 输出字母还是数字
	            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; 
	            // 字符串
	            if ("char".equalsIgnoreCase(charOrNum)) {
	                // 取得大写字母还是小写字母
	                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; 
	                val += (char) (choice + random.nextInt(26));
	            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
	                val += String.valueOf(random.nextInt(10));
	            }
	        }
	        return val;
	    }
}
