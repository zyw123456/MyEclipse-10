/**
 * 
 */
package com.yzj.ibank.server;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.yzj.wf.com.ibank.standard.server.IBankSocketControl;


/**
 * 创建于:2012-5-26<br>
 * 版权所有(C) 2012 深圳市银之杰科技股份有限公司<br>
 * xml报文测试服务端
 * 
 * @author WangXue
 * @version 1.0.0
 */
public class IBankServerXMLTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String[] local = { "conf/bean-com-xml.xml" };
			ApplicationContext appContext = new FileSystemXmlApplicationContext(local);
			IBankSocketControl socketControl = (IBankSocketControl) appContext.getBean("SocketControlForXML");
			socketControl.start();
		} catch (BeansException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main4(String[] args) {
		
//		File file = new File("D:/core.xml");
//		IBankServerXMLTest t = new IBankServerXMLTest();
//		Document doc;
//		try {
//			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
//			Element root = doc.getDocumentElement();
//			System.out.println(t.parseXmlToJson(root));
//		} catch (SAXException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParserConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
	}
	
}
