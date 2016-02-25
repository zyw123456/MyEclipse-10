package com.sinoway.mcp.service.strade.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.conn.ConnectTimeoutException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinoway.common.constant.AccountConstant;
import com.sinoway.common.constant.HttpConstant.HttpOvertime;
import com.sinoway.common.util.HttpsUtils;
import com.sinoway.common.util.MD5Utils;


/**
 * 
 * @author jintao
 *
 * @date 2016-1-14 下午1:40:53
 */

public class Utils {
	/**
	 * 法海id对应的类型
	 */
	static Map<String,String> classifyMap = new HashMap<String, String>();
	public static Token token = null;
	static{
		classifyMap.put("cpwsId", "裁判文书");
		classifyMap.put("zxggId", "执行公告");
		classifyMap.put("sxggId", "失信公告");
		classifyMap.put("ktggId", "开庭公告");
		classifyMap.put("fyggId", "法院公告");
		classifyMap.put("wdhmdId", "网贷黑名单");
		classifyMap.put("ajlcId", "案件流程信息");
		
	}
	
	/**
	 * 请求参数转换成json byet[]
	 * @param params
	 * @return
	 */
	public static byte[] map2json(Map<String,String> params){
		JSONObject obj = new JSONObject();
		for (String key : params.keySet()) {
			obj.put(key, params.get(key));
		}
		return obj.toJSONString().getBytes();
	}
	/**
	 * 解析量化派json
	 * @param array
	 * @return
	 */
	public static JSONObject parseLhpJsonArray(JSONArray array){
		JSONObject json = new JSONObject();
		json.put("loanOvrChkRes", "否");
		json.put("loanOvrCert", "80%");
		if(array.size()>=0){
			for (int i = 0; i < array.size(); i++) {
				JSONObject obj = array.getJSONObject(i);
				String grade = obj.getString("GRADE");
				if(!grade.equals("") && Integer.valueOf(grade)>=5){
					json.put("loanOvrChkRes", "是");
					json.put("loanOvrCert", "80%");
					break;
				}
			}
		}
		
		return json;
		
	}
	
	/**
	* 将一个字符串转化为输入流
	*/
	public static InputStream getStringStream(String sInputString){
	if (sInputString != null && !sInputString.trim().equals("")){
	try{
	ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
	return tInputStringStream;
	}catch (Exception ex){
	ex.printStackTrace();
	}
	}
	return null;
	}
	
	
	/**
	 * 参数转换成json对象byte[]
	 * @param obj
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws ConnectTimeoutException 
	 */
//	public static byte[] obj2json(Object[] ...obj){
//		JSONObject json = new JSONObject();
//		for (int i = 0; i < obj.length; i++) {
//			json.put(key, value)
//		}
//		
//		return null;
//	}
	
	/**
	 * 解析法海返回的json数据
	 * @param aray
	 * @return
	 */
	public static JSONArray parseJsonArray(JSONArray array){
		JSONArray json = new JSONArray();
		for (int i = 0; i < array.size(); i++) {
			JSONObject obj = array.getJSONObject(i);
			JSONObject classifyJson = classifyJson(obj);
			json.add(classifyJson);
		}
		return json;
	}
	
	/**
	 * 对法海数据进行归类
	 * @return
	 */
	public static JSONObject classifyJson(JSONObject obj){
		JSONObject json = new JSONObject();
		for (String key : obj.keySet()) {
			if(classifyMap.get(key)!=null){
				json.put("caseLawTyp", classifyMap.get(key));
			}
		}
		json.put("caseNo", obj.get("caseNo"));
		json.put("regstrDate", convert(Long.valueOf(obj.get("sortTime").toString()),"yyyy/MM/dd"));
		String yjCode = obj.getString("yjCode") == null ? "" :",案号：" +obj.getString("yjCode");
		String caseLawMrk = "法院："+obj.getString("court")+ yjCode;
		json.put("caseLawMrk",  caseLawMrk);
		json.put("caseLawCret", "90%");
		return json;
	}
	
	/**
	 * 时间戳转换日期
	 * @param mill
	 * @return
	 */
	
	public static String convert(long mill,String format){
		Date date=new Date(mill);
		String strs="";
		try {
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		strs=sdf.format(date);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return strs;
	}
	
	public  static String gettoken() throws UnsupportedEncodingException, ConnectTimeoutException{
		  
		  if (token == null){
			  token  = Token.getInstance();
		  }
		  synchronized (token) {
			
		  //19分钟过期 这里18分钟获取一次
		   if(null == token.getTokenid() || System.currentTimeMillis() - token.getTime()>1000*60*18 ){
			   String url = AccountConstant.CHINATELECOM_POSTURL+"/system/publicKey.json?apiKey="+AccountConstant.CHINATELECOM_AIPKEY;
			   String httpPost = HttpsUtils.executeGet(url,HttpOvertime.HTTP_OVERTIME_CONNTIMEOUT.getCode(),HttpOvertime.HTTP_OVERTIME_SOCKETTIMEOUT.getCode());
			   JSONObject json = JSONObject.parseObject(httpPost);
			   //authtoken 10秒失效 获取成功后10秒内无法再次获取
			   if(json.containsKey("code") && json.getString("code").equals("200")){ // 成功
				   String authCode = json.getString("data");
				   String sign = MD5Utils.MD5(AccountConstant.CHINATELECOM_AIPKEY+AccountConstant.CHINATELECOM_PWD+authCode);
				   String str  = AccountConstant.CHINATELECOM_POSTURL+"/system/token.json?apiKey="+AccountConstant.CHINATELECOM_AIPKEY+"&authCode="+authCode+"&sign="+sign;
				   String tokenid = HttpsUtils.executeGet(str,HttpOvertime.HTTP_OVERTIME_CONNTIMEOUT.getCode(),HttpOvertime.HTTP_OVERTIME_SOCKETTIMEOUT.getCode());
				   JSONObject obj = JSONObject.parseObject(tokenid);
				   if (obj.containsKey("code") && obj.getString("code").equals("200")) { //成功
					   JSONObject data = JSONObject.parseObject(obj.getString("data"));
					   token.setTokenid(data.getString("token"));
					   token.setTime(System.currentTimeMillis());
				}else{
					//获取失败之后authtoken 需要等待10秒之后再获取 因为authtoken只能使用一次 10秒之内不能重新获取
					
				}
				   
				   
			   }else{
                   
			   }
		   }
		   
		  }
		   
		   return token.getTokenid();
		   
	   } 		    

}
