package com.sinoway.common.parse;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.alibaba.fastjson.JSONObject;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.exception.DomParseException;
import com.sinoway.common.service.parse.GeneralJsonTradeMsgTransfer;
import com.sinoway.common.util.DocumentUtil;
import com.sinoway.mcp.exception.JsonMsgParseException;
import com.sinoway.mcp.exception.TradeMsgTransfException;
import com.sun.org.apache.xpath.internal.XPathAPI;

public class TestTradeParseP000003 {

	
	
	public static  void  in (){
    GeneralJsonTradeMsgTransfer   trade = new GeneralJsonTradeMsgTransfer();
	try {
		
		JSONObject json = new JSONObject();
		json.put("prsnnam", "张彦伟");
		json.put("idcard", "23534658");
		json.put("passport", "353454");
		
		Document  dom = DocumentUtil.filePToDomByDecode("E:/XML_IBankServerMapsTemplate1.xml", "UTF-8");
		Element   el  = (Element) XPathAPI.selectSingleNode(dom, "/TRADETEMPLATE/TRADE[@ID='P0000003']/MESSAGE[@TYPE='1']/OBJ");
		
		
		System.out.println("转化后输出的 模板;;;"+trade.transfJsonMsgByJTemp(el, json, json,"", true, new GeneralBusEntity()));
		
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
	
	
	public static void  out(){
		GeneralJsonTradeMsgTransfer trade= new GeneralJsonTradeMsgTransfer();
		try {
			
			JSONObject json = new JSONObject();
			JSONObject airFlyInf = new JSONObject();
			
			airFlyInf.put("airFlyAllCnt", "airFlyAllCnt");
			airFlyInf.put("airBusyMth", "airBusyMth");
			airFlyInf.put("airBusyMthCnt", "airBusyMthCnt");
			airFlyInf.put("airFstClasCnt", "airFstClasCnt");
			airFlyInf.put("airBusinessCnt", "airBusinessCnt");
			airFlyInf.put("airCochClasCnt", "airCochClasCnt");
			airFlyInf.put("airFreqFrmCity", "airFreqFrmCity");
			airFlyInf.put("airFreqArvCity", "airFreqArvCity");
			airFlyInf.put("airCompRideMost", "airCompRideMost");
			airFlyInf.put("airDomesticCnt", "airDomesticCnt");
			airFlyInf.put("airInternalCnt", "airInternalCnt");
			airFlyInf.put("airAllMileage", "airAllMileage");
			airFlyInf.put("airPriceAvg", "airPriceAvg");
			airFlyInf.put("airFreePassCnt", "airFreePassCnt");
			airFlyInf.put("airTickBefDayAvg", "airTickBefDayAvg");
			airFlyInf.put("lstAirFrmCity", "lstAirFrmCity");
			airFlyInf.put("lstAirArvCity", "lstAirArvCity");
			airFlyInf.put("airDis1YearAvg", "airDis1YearAvg");
			airFlyInf.put("airDelayTims", "airDelayTims");
			airFlyInf.put("airDelayTimsAvg", "airDelayTimsAvg");
	
			json.put("airFlyInf", airFlyInf);
			JSONObject bnkcrdChkInf = new JSONObject();
			bnkcrdChkInf.put("bnkcrdChkRes", "bnkcrdChkRes");
			json.put("bnkcrdChkInf", bnkcrdChkInf);
			Document  dom = DocumentUtil.filePToDomByDecode("E:/XML_IBankServerMapsTemplate1.xml", "utf-8");
			
			Element el    = (Element) XPathAPI.selectSingleNode(dom, "/TRADETEMPLATE/TRADE[@ID='P0000003']/MESSAGE[@TYPE='2']/OBJ");
			
			
			System.out.println("转化前的 json ::"+json.toJSONString());
			
			System.out.println("通过模板转化后的json +"+trade.transfJsonMsgByJTemp(el, json, json, "", true, new GeneralBusEntity()));
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
	//  in();
		
		
	    out();	
	}

}
