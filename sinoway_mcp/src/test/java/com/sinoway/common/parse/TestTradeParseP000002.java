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

public class TestTradeParseP000002 {

	
	public static void  in (){
		GeneralJsonTradeMsgTransfer f = new GeneralJsonTradeMsgTransfer();
		
		try {
			JSONObject json = new JSONObject();
			json.put("prsnnam", "zhangyanwei");
			json.put("idcard", "234234");
			json.put("mobile", "124346");
			json.put("bnkCrdNo", "2534");
			Document dom = DocumentUtil.filePToDomByDecode("E:/XML_IBankServerMapsTemplate1.xml", "utf-8");
			
			Element el = (Element) XPathAPI.selectSingleNode(dom, "/TRADETEMPLATE/TRADE[@ID='P0000002']/MESSAGE[@TYPE='1']/OBJ");
			
			

			System.out.println(el.getTextContent());
			try {
				System.out.println(f.transfJsonMsgByJTemp(el, json, json, "", true, new GeneralBusEntity()));
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
			
		} catch (DomParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	public  static void out (){
		GeneralJsonTradeMsgTransfer  fr= new GeneralJsonTradeMsgTransfer();
		try {
			
			JSONObject  json = new JSONObject();
			 
			
		    JSONObject  mobileChkInf = new JSONObject();
			 
		    mobileChkInf.put("mobileChkRes", "mobileChkRes1");
		    json.put("mobileChkInf", mobileChkInf);
		    JSONObject bnkcrdChkInf = new JSONObject();
		    bnkcrdChkInf.put("bnkcrdChkRes", "bnkcrdChkRes1");
		     json.put("bnkcrdChkInf",bnkcrdChkInf);
		     JSONArray   caseLawInf  = new JSONArray();
		     JSONObject  test1  = new  JSONObject();
		     
		     test1.put("caseLawTyp", "caseLawTyp1");
		     test1.put("caseNo", "caseNo1");
		     test1.put("regstrDate", "regstrDate1");
		     test1.put("reptUnit", "reptUnit1");
		     test1.put("caseNo", "caseNo1");
		     test1.put("testMark", "testMark1");
		     test1.put("operaMark", "operaMark1");
		     test1.put("certAwardDte", "certAwardDte1");
		     caseLawInf.add(test1);
             JSONObject  test2  = new  JSONObject();
		     
             test2.put("caseLawTyp", "caseLawTyp2");
             test2.put("caseNo", "caseNo2");
             test2.put("regstrDate", "regstrDate2");
             test2.put("reptUnit", "reptUnit2");
             test2.put("testMark", "testMark2");
             test2.put("caseNo", "caseNo2");
		     test2.put("operaMark", "operaMark2");
		     test2.put("certAwardDte", "certAwardDte12");
		     caseLawInf.add(test2);
		     
		     json.put("caseLawInf", caseLawInf);
		     
		     JSONObject  loanOvrChkInf = new JSONObject();
		    
		     loanOvrChkInf.put("loanOvrChkRes", "loanOvrChkRes111");
		     loanOvrChkInf.put("loanOvrCert", "loanOvrCert222");

		
		     JSONObject highRiskPsnChkInf = new JSONObject();
		     highRiskPsnChkInf.put("highRiskPsnRes", "highRiskPsnRes1");
		     highRiskPsnChkInf.put("highRiskPsnCert", "highRiskPsnCert1");
		     json.put("highRiskPsnChkInf", highRiskPsnChkInf);
		     json.put("loanOvrChkInf", loanOvrChkInf);
		     
		     
		     System.out.println("输出的json ：："+ json.toJSONString());
			Document dom = DocumentUtil.filePToDomByDecode("E:/XML_IBankServerMapsTemplate1.xml", "utf-8");
			
			Element el = (Element) XPathAPI.selectSingleNode(dom,"/TRADETEMPLATE/TRADE[@ID='P0000002']/MESSAGE[@TYPE='2']/OBJ");
			
			System.out.println(el.getTextContent());
			
			System.out.println("转化后的输出模板"+fr.transfJsonMsgByJTemp(el, json, json, "", true, new GeneralBusEntity()));
			
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	//	TestTradeParseP000002.in();
		
		out();
	}

}