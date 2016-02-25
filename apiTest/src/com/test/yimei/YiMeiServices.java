package com.test.yimei;

import java.text.ParseException;

import net.sf.json.JSONObject;

/**
 * ����ͳһ������
 * @author jintao
 *
 */
public class YiMeiServices {

	public static TokenEntity token;

	
	public static String getAccess_token(){
		//TODO ���ܻ�����쳣 ��������ʽʹ���Ż�Ҳ����д�����ݿ���߻�����������key-value
			if(token == null ){
				token = new TokenEntity();
			}
			synchronized (token) {
				//��ǰ�����ӻ�ȡ�µ�tooken 
				if(token.getTooken() == null || token.getTime()-System.currentTimeMillis()>7080000){
					HDGetAccessToken accessToken = new HDGetAccessToken();
					HDGetAccessTokenSoap hdGetAccessTokenSoap = accessToken.getHDGetAccessTokenSoap();
					String json = hdGetAccessTokenSoap.getACCESSTOKEN("8AC20B7CW6D98W4369WB876WCCA3E36EDB3B", "32C82793L7B2CL41FFL9F67L6D75BF2CC46D", "4F0DF96BH6608H4C14H9867HC801263B1304");
					JSONObject obj =JSONObject.fromObject(json);
					token.setTooken(obj.getString("access_token"));
					token.setTime(System.currentTimeMillis());
				}
				
				return token.getTooken();
			}
	}
	
	public static  String queryOverdue(String phone,String cycle){
		
		UserCheckService checkService = new UserCheckService();
		UserCheckServiceSoap checkServiceSoap = checkService.getUserCheckServiceSoap();
		return checkServiceSoap.getBeOverdue(phone, cycle, getAccess_token());
		
		
		
	}
	public static void main(String[] args) throws ParseException {
		String phone = "13811075842";
		String cycle = "12";
		System.out.println(queryOverdue(phone, cycle));
		
	}
	
	
	
	
}