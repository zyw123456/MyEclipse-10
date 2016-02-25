package com.sinoway.common.service.socket;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinoway.common.service.consumer.GeneralOPConsumerService;


public class ORes {

	
	public static void main(String[] args) {
		ApplicationContext ac = new FileSystemXmlApplicationContext("conf/spring/applicationContext.xml");
		GeneralOPConsumerService s =  (GeneralOPConsumerService)ac.getBean("generalOPConsumerService");
		JSONObject json = new JSONObject();
		JSONObject header = new JSONObject();
		
		header.put("intrncod", "P0000001");
		header.put("fnttrnjrn", "F0P000000116012000241000012001");
		header.put("frnttrndte", "20160120");
		header.put("frnttrntim", "002410239");
		header.put("masttrndte", "20160121");
		header.put("masttrntim", "12333333");
		header.put("mastjrn", "mastjrn");
		header.put("status", "1");
		header.put("result", "aaa");
		header.put("chnlcod", "50000001");
		json.put("header", header);
		
		JSONArray jsa = new JSONArray();
		JSONObject body = new JSONObject();
		body.put("prsnnam", "234");
		body.put("idcard", "123");
		jsa.add(body);
		json.put("bodys", jsa);

		try {
			s.excuteMsg(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
