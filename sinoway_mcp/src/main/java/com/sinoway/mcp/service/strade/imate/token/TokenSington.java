package com.sinoway.mcp.service.strade.imate.token;

import java.io.UnsupportedEncodingException;
import org.apache.http.conn.ConnectTimeoutException;
import com.alibaba.fastjson.JSONObject;
import com.sinoway.mcp.service.strade.imate.ws.HDGetAccessToken;
import com.sinoway.mcp.service.strade.imate.ws.HDGetAccessTokenSoap;

/**
 * 亿美获取token方法
 * @author zhangyanwei
 *
 */
public class TokenSington {
	public static ImToken imtoken = null;
	
	public  static String gettoken() throws UnsupportedEncodingException, ConnectTimeoutException{
		  if (imtoken == null){
			  imtoken  = ImToken.getInstance();
		  }
		  synchronized (imtoken) {
			
		  //2小时过期 这里1小时50分钟获取一次
		   if(null == imtoken.getTokenid() || System.currentTimeMillis() - imtoken.getTime()>1000*60*110 ){
			   HDGetAccessToken accessToken = new HDGetAccessToken();
			   HDGetAccessTokenSoap hdGetAccessTokenSoap = accessToken.getHDGetAccessTokenSoap();
			   String json = hdGetAccessTokenSoap.getACCESSTOKEN("4461AD8DW69B4W4184W8A0AWD983894746B8", "F1545BBCL782FL40F6LBBADL2365A8E4DF87", "F1624FF5H6BCDH418AHA2D5H056AF162DA1B");
			   JSONObject obj =(JSONObject) JSONObject.parse(json);
			   //authtoken 10秒失效 获取成功后10秒内无法再次获取
			   imtoken.setTokenid(obj.getString("access_token"));
			   imtoken.setTime(System.currentTimeMillis());
		   }
		   
		  }
		   
		   return imtoken.getTokenid();
		   
	   } 		    

}
