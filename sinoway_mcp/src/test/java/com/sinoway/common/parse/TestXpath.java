package com.sinoway.common.parse;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.sinoway.common.exception.DomParseException;
import com.sinoway.common.util.DocumentUtil;
import com.sun.org.apache.xpath.internal.XPathAPI;

public class TestXpath {

	public static void main(String[] args) {
		try {
			Document dom = DocumentUtil.filePToDomByNoDecode("D:/XML_IBankServerMapsTemplate.xml");
			NodeList nodes = XPathAPI.selectNodeList(dom, "//TRADE");
			System.out.println(nodes.getClass());
			for(int i = 0 ; i < nodes.getLength(); i ++){
				
			}
			
		} catch (DomParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
