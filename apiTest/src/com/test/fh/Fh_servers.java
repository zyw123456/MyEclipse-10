package com.test.fh;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.test.util.Constant;
import com.test.util.HttpClientUtils;

/**
 * 调用法海接口服务类
 * @author jintao
 *
 */
public class Fh_servers {
	
	public String fhFisk(String name,String idCard) throws UnsupportedEncodingException{
		Map<String,String> params = new HashMap<String, String>();
		String url = "http://app.fahaicc.com/fhfk/query.jsp?authCode="+Constant.FH_REG+"&q=pname:"+URLEncoder.encode(name,"UTF-8")+"@idcardNo:"+idCard;
//		params.put("authCode",Constant.FH_REG);
//		params.put("q","pname:"+URLEncoder.encode(name,"utf-8")+"@idcardNo:"+idCard);
//		http://app.fahaicc.com/fhfk/query.jsp?authCode=pwl54yCgSncJmDTgo9X6&q=pname:%E5%90%95%E5%B0%91%E5%85%B5@idcardNo:342401197606205431
//		http://app.fahaicc.com/fhfk/query.jsp?authCode=pwl54yCgSncJmDTgo9X6&q=pname:%E5%90%95%E5%B0%91%E5%85%B5@idcardNO:342401197606205431
		return HttpClientUtils.httpPost(url, params);
	}

}
