package com.test.lhp;

import java.util.HashMap;
import java.util.Map;

import com.test.util.Constant;
import com.test.util.HttpClientUtils;
import com.test.util.MD5Util;
import com.test.util.Utils;

public class LhpServers {
	
	public String overdue(String realName,String cardId,String cardType) throws Exception{

		Map<String,String> param = new HashMap<String, String>();
		long timestmp = System.currentTimeMillis();
		String all = "timeunit=" + timestmp +"appkey="+Constant.LHP_APPKEY;
		param.put("appId", Constant.LHP_APPID);
		param.put("timeunit", String.valueOf(timestmp));
		System.out.println(Utils.gzt_bank_md5(all));
		param.put("token", Utils.gzt_bank_md5(all));
		param.put("userId",Constant.LHP_APPID+"_jttest");
		param.put("realName", realName);
		param.put("cardId", cardId);
		param.put("cardType", cardType);
		String httpPost = HttpClientUtils.httpPost(Constant.LHP_URL+Constant.LHP_OVERDUE_URL, param);
		
		return new String(httpPost.getBytes(),"utf-8");
	}

}
