package com.sinoway.common.util;

import java.io.File;

import javax.xml.transform.TransformerException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;

import com.sinoway.common.exception.DomParseException;
import com.sun.org.apache.xpath.internal.XPathAPI;

import junit.framework.TestCase;

public class DocumentUtilTest1 extends TestCase {

	public final void testFilePToDomByDefDecode() {
		try {
			String[] str = {null,"","D:/core.xml","D:/234.txt","asd","D:/log","D:/1.txt","D:/123.bmp","D:/null.txt"};
			Document dom = DocumentUtil.filePToDomByDefDecode(str[3]);
			System.out.println(XPathAPI.selectSingleNode(dom, "/obj").getTextContent());
		} catch (DomParseException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public final void testFilePToDomByDecode() {
		try {
			String[] str = {null,"","D:/core.xml","D:/234.txt","asd","D:/log","D:/1.txt","D:/123.bmp","D:/null.txt"};
			Document dom = DocumentUtil.filePToDomByDecode(str[3],"UTF-8");
			System.out.println(XPathAPI.selectSingleNode(dom, "/obj").getTextContent());
		} catch (DomParseException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public final void testFilePToDomByNoDecode() {
		try {
			String[] str = {null,"","D:/core.xml","D:/234.txt","asd","D:/log","D:/1.txt","D:/123.bmp","D:/null.txt"};
			Document dom = DocumentUtil.filePToDomByNoDecode(str[2]);
			System.out.println(XPathAPI.selectSingleNode(dom, "/obj").getTextContent());
		} catch (DomParseException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public final void testFileToDomByDefDecode() {
		try {
			String[] str = {"","D:/core.xml","D:/234.txt","asd","D:/log","D:/1.txt","D:/123.bmp","D:/null.txt"};
			File file = new File(str[1]);
			Document dom = DocumentUtil.fileToDomByDefDecode(file);
			System.out.println(XPathAPI.selectSingleNode(dom, "/obj").getTextContent());
		} catch (DomParseException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public final void testFileToDomByDecode() {
		try {
			String[] str = {"","D:/core.xml","D:/234.txt","asd","D:/log","D:/1.txt","D:/123.bmp","D:/null.txt"};
			File file = new File(str[1]);
			Document dom = DocumentUtil.fileToDomByDecode(file,"asd");
			System.out.println(XPathAPI.selectSingleNode(dom, "/obj").getTextContent());
		} catch (DomParseException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public final void testFileToDomByNoDecode() {
		try {
			String[] str = {"","D:/core.xml","D:/234.txt","asd","D:/log","D:/1.txt","D:/123.bmp","D:/null.txt"};
			File file = new File(str[1]);
			Document dom = DocumentUtil.fileToDomByNoDecode(file);
			System.out.println(XPathAPI.selectSingleNode(dom, "/obj").getTextContent());
		} catch (DomParseException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public final void testStrToDomByDefDecode() {
		String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><obj><key from=\"a|b\" extend=\"1\">a</key><obj key=\"b\" from=\"g|h\"><key from=\"c|d\">d</key><obj key=\"e\"><key from=\"e|f\">f</key><list key=\"g\" from=\"g|h\"><key from=\"i\" extend=\"true\">h</key><obj key=\"i\"><key from=\"j|k\" extend=\"true\">j</key><key from=\"j|l\" extend=\"true\">k</key><list key=\"l\" from=\"j|m\" extend=\"true\"><key extend=\"false\" from=\"n\">m</key><key extend=\"true\" from=\"\">n</key><key>o</key></list></obj></list></obj><list key=\"p\"><key>q</key><obj key=\"r\"><key>s</key><key>t</key><list key=\"u\"><key>v</key><key>w</key><key>x</key></list></obj></list></obj><list key=\"c\"><key>y</key><obj key=\"z\"><key>1</key><key>2</key><list key=\"3\"><key>4</key><key>5</key><key>6</key></list></obj></list></obj>";
		String str1 = "%3C%3Fxml+version%3D%221.0%22+encoding%3D%22UTF-8%22%3F%3E%3Cobj%3E%3Ca%3E%E6%88%91%E6%98%AF%E8%B0%81%3C%2Fa%3E%3Cc%3Enishishui%3C%2Fc%3E%3Cd%3E1234567890%3C%2Fd%3E%3C%2Fobj%3E";
		Document dom;
		try {
			dom = DocumentUtil.strToDomByDefDecode(str1);
			System.out.println(XPathAPI.selectSingleNode(dom, "/obj").getTextContent());
		} catch (DomParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public final void testStrToDomByDecode() {
		String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><obj><key from=\"a|b\" extend=\"1\">a</key><obj key=\"b\" from=\"g|h\"><key from=\"c|d\">d</key><obj key=\"e\"><key from=\"e|f\">f</key><list key=\"g\" from=\"g|h\"><key from=\"i\" extend=\"true\">h</key><obj key=\"i\"><key from=\"j|k\" extend=\"true\">j</key><key from=\"j|l\" extend=\"true\">k</key><list key=\"l\" from=\"j|m\" extend=\"true\"><key extend=\"false\" from=\"n\">m</key><key extend=\"true\" from=\"\">n</key><key>o</key></list></obj></list></obj><list key=\"p\"><key>q</key><obj key=\"r\"><key>s</key><key>t</key><list key=\"u\"><key>v</key><key>w</key><key>x</key></list></obj></list></obj><list key=\"c\"><key>y</key><obj key=\"z\"><key>1</key><key>2</key><list key=\"3\"><key>4</key><key>5</key><key>6</key></list></obj></list></obj>";
		String str1 = "%3C%3Fxml+version%3D%221.0%22+encoding%3D%22UTF-8%22%3F%3E%3Cobj%3E%3Ca%3E%E6%88%91%E6%98%AF%E8%B0%81%3C%2Fa%3E%3Cc%3Enishishui%3C%2Fc%3E%3Cd%3E1234567890%3C%2Fd%3E%3C%2Fobj%3E";
		Document dom;
		try {
			dom = DocumentUtil.strToDomByDecode(str1,"UTF-8");
			System.out.println("::" + XPathAPI.selectSingleNode(dom, "/obj").getTextContent());
		} catch (DomParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public final void testStrToDomByNoDecode() {
		String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><obj><key from=\"a|b\" extend=\"1\">a</key><obj key=\"b\" from=\"g|h\"><key from=\"c|d\">d</key><obj key=\"e\"><key from=\"e|f\">f</key><list key=\"g\" from=\"g|h\"><key from=\"i\" extend=\"true\">h</key><obj key=\"i\"><key from=\"j|k\" extend=\"true\">j</key><key from=\"j|l\" extend=\"true\">k</key><list key=\"l\" from=\"j|m\" extend=\"true\"><key extend=\"false\" from=\"n\">m</key><key extend=\"true\" from=\"\">n</key><key>o</key></list></obj></list></obj><list key=\"p\"><key>q</key><obj key=\"r\"><key>s</key><key>t</key><list key=\"u\"><key>v</key><key>w</key><key>x</key></list></obj></list></obj><list key=\"c\"><key>y</key><obj key=\"z\"><key>1</key><key>2</key><list key=\"3\"><key>4</key><key>5</key><key>6</key></list></obj></list></obj>";
		String str1 = "%3C%3Fxml+version%3D%221.0%22+encoding%3D%22UTF-8%22%3F%3E%3Cobj%3E%3Ca%3E%E6%88%91%E6%98%AF%E8%B0%81%3C%2Fa%3E%3Cc%3Enishishui%3C%2Fc%3E%3Cd%3E1234567890%3C%2Fd%3E%3C%2Fobj%3E";
		Document dom;
		try {
			dom = DocumentUtil.strToDomByNoDecode(str);
			System.out.println("::" + XPathAPI.selectSingleNode(dom, "/obj").getTextContent());
		} catch (DomParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
