package com.sinoway.common.parse;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.exception.STradeProcessException;
import com.sinoway.mcp.service.strade.sjutang.StPerExtInvtmentService;
import com.sinoway.mcp.service.strade.sjutang.Util.SjtUtil;
import com.sun.org.apache.xpath.internal.XPathAPI;

public class TestWs {
	public static void main(String[] args) {
		
	//	StExceedQueryUsercheckService  ws = new StExceedQueryUsercheckService();
		StPerExtInvtmentService ws  =  new StPerExtInvtmentService();
		GeneralBusEntity   entity = new GeneralBusEntity();
		
		JSONObject json = new JSONObject();
		
		JSONArray  body = new JSONArray();
		
		JSONObject param = new JSONObject();
		param.put("phone", "13811075842");
		
		param.put("cycle", "12");
		
		param.put("idcard", "142724198306261624");
		 Document document = null;
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("D:\\userCheck.xml");
			
		  
			
			
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	       System.out.println(document.toString());
		
		body.add(param);
		
		json.put("bodys", body);
		
		entity.setCoreMsg(json.toJSONString().getBytes());
		try {
			ws.busLaunch(entity);
		} catch (STradeProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main2(String[] args) {
		try {
			SjtUtil ss = new SjtUtil("f3e99ebcde110f60c221bc736643efb7");
			System.out.println(ss.encrypt("142724198306261624"));
			System.out.println(ss.decrypt("tbA452VoOJ9xISgqWaMJQdpDmNxAtzOZ"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
