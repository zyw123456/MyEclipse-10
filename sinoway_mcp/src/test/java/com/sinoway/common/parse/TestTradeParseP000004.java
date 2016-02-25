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

public class TestTradeParseP000004 {

	
	public  static void in(){
		
		GeneralJsonTradeMsgTransfer  trade = new GeneralJsonTradeMsgTransfer();
	 try {
		Document dom = DocumentUtil.filePToDomByDecode("E:/XML_IBankServerMapsTemplate1.xml", "UTF-8");
		
		Element el = (Element) XPathAPI.selectSingleNode(dom, "/TRADETEMPLATE/TRADE[@ID='P0000004']/MESSAGE[@TYPE='1']/OBJ");
		
		
		JSONObject json = new JSONObject();
		  json.put("idcard","idcard");
		System.out.println("输出的模板内容：：："+trade.transfJsonMsgByJTemp(el, json, json, "", true, new GeneralBusEntity()));
		
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
	
	public static void out (){
		GeneralJsonTradeMsgTransfer  trade  = new GeneralJsonTradeMsgTransfer();
		
		try {
			
			Document dom = DocumentUtil.filePToDomByDecode("E:/XML_IBankServerMapsTemplate1.xml", "utf-8");
			
			Element el = (Element) XPathAPI.selectSingleNode(dom, "/TRADETEMPLATE/TRADE[@ID='P0000004']/MESSAGE[@TYPE='2']/OBJ");
			
			
			JSONObject  json = new  JSONObject ();
			JSONObject  lealpersonInf = new JSONObject();
			lealpersonInf.put("compNam", "compNam");
			lealpersonInf.put("compRegistNo", "compRegistNo");
			lealpersonInf.put("compRegistCaptl", "compRegistCaptl");
			lealpersonInf.put("compTyp", "compTyp");
			lealpersonInf.put("cny", "cny");
			lealpersonInf.put("compSta", "compSta");
			
			json.put("lealpersonInf", lealpersonInf);
			JSONArray    executiveInf =  new JSONArray();
			
			JSONObject  test1  =  new JSONObject();
			test1.put("compNam", "compNam1");
			test1.put("compRegistNo", "compRegistNo1");
			test1.put("compRegistCaptl", "compRegistCaptl");
			test1.put("compTyp", "compTyp1");
			test1.put("cny", "cny1");
			test1.put("compSta", "compSta1");
			test1.put("compDuties", "compDuties1");
			executiveInf.add(test1);
			
			JSONObject test2 = new  JSONObject();
			test2.put("compNam", "compNam2");
			test2.put("compRegistNo", "compRegistNo2");
			test2.put("compRegistCaptl", "compRegistCaptl2");
			test2.put("compTyp", "compTyp2");
			test2.put("cny", "cny2");
			test2.put("compSta", "compSta2");
			test2.put("compDuties", "compDuties2");
			executiveInf.add(test2);
			json.put("executiveInf", executiveInf);
			
			System.out.println("输出后的 转化报文：：：；"+trade.transfJsonMsgByJTemp(el, json, json, "", true, new GeneralBusEntity()));
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
	public static void main(String[] args) {
	//in();
		out();
	}

}
