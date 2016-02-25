package com.sinoway.common.parse;

import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.exception.DomParseException;
import com.sinoway.common.service.parse.GeneralJsonTradeMsgTransfer;
import com.sinoway.common.util.DocumentUtil;
import com.sinoway.mcp.exception.JsonMsgParseException;
import com.sinoway.mcp.exception.TradeMsgTransfException;
import com.sun.org.apache.xpath.internal.XPathAPI;

public class TestTradeParseP0000001 {

	public static void main(String[] args) {
		//in();
		out();
		
	}
	
	public static void in(){
		GeneralJsonTradeMsgTransfer a = new GeneralJsonTradeMsgTransfer();
		
		try {
			Document dom = DocumentUtil.filePToDomByDecode("E:/XML_IBankServerMapsTemplate1.xml", "utf-8");
			
			Element el = (Element)XPathAPI.selectSingleNode(dom, "/TRADETEMPLATE/TRADE[@ID='P0000001']/MESSAGE[@TYPE='1']/OBJ");
			
			System.out.println(el.getTextContent());
			
			JSONObject json = new JSONObject();
			
			json.put("prsnnam", "张彦伟");
			json.put("idcard", "123456");
			
			System.out.println(a.transfJsonMsgByJTemp(el, json, json, "", true, new GeneralBusEntity()));
		} catch (DomParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMsgParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TradeMsgTransfException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void out(){
		GeneralJsonTradeMsgTransfer a = new GeneralJsonTradeMsgTransfer();
		
		try {
			Document dom = DocumentUtil.filePToDomByDecode("E:/XML_IBankServerMapsTemplate1.xml", "utf-8");
			
			Element el = (Element)XPathAPI.selectSingleNode(dom, "/TRADETEMPLATE/TRADE[@ID='P0000001']/MESSAGE[@TYPE='2']/OBJ");
			
			System.out.println(el.getTextContent());
			
			JSONObject json = new JSONObject();
			
			JSONObject personalInf = new JSONObject();
			
			personalInf.put("prsnnam", "aaa");
			personalInf.put("sex", "bbb");
			personalInf.put("birthday", "ccc");
			personalInf.put("idcard", "ddd");
			personalInf.put("cretOrg", "eee");
			personalInf.put("idcradChkRes", "fff");
			
			json.put("personalInf", personalInf);
			
			JSONArray eduInf = new JSONArray();
			
			JSONObject eduInf1 = new JSONObject();
			eduInf1.put("eduSchool", "eduSchool1");
			eduInf1.put("education", "education1");
			eduInf1.put("eduLevel", "eduLevel1");
			eduInf1.put("caseLawMrk", "caseLawMrk1");


			eduInf.add(eduInf1);
			
			
			JSONObject eduInf2 = new JSONObject();
			eduInf2.put("eduSchool", "eduSchool2");
			eduInf2.put("education", "education3");
			eduInf2.put("eduLevel", "eduLevel2");
			eduInf2.put("caseLawMrk", "caseLawMrk2");


			eduInf.add(eduInf2);

			json.put("eduInf", eduInf);
			
			
			
			JSONObject professionInf = new JSONObject();
			
			professionInf.put("certNo", "hhh");
			professionInf.put("certNam", "iii");
			professionInf.put("certLevel", "jjjj");
			professionInf.put("reptUnit", "kkk");
			professionInf.put("testMark", "lll");
			professionInf.put("operaMark", "mmm");
			professionInf.put("certAwardDte", "nnn");

			
			json.put("professionInf", professionInf);
			
			
			System.out.println("输出json：：" + json.toJSONString());
			
			System.out.println( "转化后json::" + a.transfJsonMsgByJTemp(el, json, json, "", true, new GeneralBusEntity()));
		} catch (DomParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMsgParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TradeMsgTransfException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}