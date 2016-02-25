package com.sinoway.common.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import com.sinoway.common.exception.DomParseException;

import junit.framework.TestCase;

public class DocumentUtilTest2 extends TestCase {

	public final void testDomToStrByNoDecode() {
		try {
			String[] str = {"D:/core.xml","D:/234.txt"};
			Document dom = DocumentUtil.filePToDomByDefDecode(str[1]);
			
			System.out.println(DocumentUtil.domToStrByNoDecode(dom));
			
		} catch (DomParseException e) {
			e.printStackTrace();
		}
	}

	public final void testDomToStrByDecode() {
		try {
			String[] str = {"D:/core.xml","D:/234.txt"};
			Document dom = DocumentUtil.filePToDomByDefDecode(str[1]);
			Document dom1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			System.out.println(DocumentUtil.domToStrByDecode(dom,"UTF-8"));
			
		} catch (DomParseException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public final void testDomToStrByDefDecode() {
		try {
			String[] str = {"D:/core.xml","D:/234.txt"};
			Document dom = DocumentUtil.filePToDomByDefDecode(str[0]);
			Document dom1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			System.out.println(DocumentUtil.domToStrByDefDecode(dom));
			
		} catch (DomParseException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public final void testStrToFile() {
		
		try {
			String[] str = {null,"","<aa>刘振<、？asdfff>\""};
			
			String fielP = "D:/aa.txt";
			File file = new File(fielP);
			System.out.println(DocumentUtil.strToFile(str[2],file));
			
		} catch (DomParseException e) {
			e.printStackTrace();
		} 
	}

	public final void testStrToFileP() {
		try {
			String[] str = {null,"","<aa>刘振<、？asdfff>\""};
			
			String fielP = "D:/aa.txt";
			
			System.out.println(DocumentUtil.strToFileP(str[2],fielP));
			
		} catch (DomParseException e) {
			e.printStackTrace();
		}
	}

	public final void testDomToFileByNoDecode() {
		
		try {
			String[] str = {"D:/core.xml","D:/234.txt"};
			Document dom = DocumentUtil.filePToDomByDefDecode(str[1]);
			Document dom1= DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			String fileP = "D:/aa.txt";
			File file = new File(fileP);
			System.out.println(DocumentUtil.domToFileByNoDecode(dom,file));
			
		} catch (DomParseException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public final void testDomToFileByDecode() {
		
		try {
			String[] str = {"D:/core.xml","D:/234.txt"};
			Document dom = DocumentUtil.filePToDomByDefDecode(str[0]);
			Document dom1= DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			String fileP = "D:/aa1.txt";
			File file = new File(fileP);
			System.out.println(DocumentUtil.domToFileByDecode(dom,file,"gbk"));
			
		} catch (DomParseException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public final void testDomToFileByDefDecode() {
		try {
			String[] str = {"D:/core.xml","D:/234.txt"};
			Document dom = DocumentUtil.filePToDomByDefDecode(str[0]);
			Document dom1= DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			String fileP = "D:/aa2.txt";
			File file = new File(fileP);
			System.out.println(DocumentUtil.domToFileByDefDecode(dom,file));
			
		} catch (DomParseException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public final void testDomToFilePByNoDecode() {
		try {
			String[] str = {"D:/core.xml","D:/234.txt"};
			Document dom = DocumentUtil.filePToDomByDefDecode(str[0]);
			Document dom1= DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			String[] fileP = {null,"","D:/aa3.txt","D:/DBFiles"};
			System.out.println(DocumentUtil.domToFilePByNoDecode(dom,fileP[2]));
			
		} catch (DomParseException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public final void testDomToFilePByDecode() {
		try {
			String[] str = {"D:/core.xml","D:/234.txt"};
			Document dom = DocumentUtil.filePToDomByDefDecode(str[0]);
			Document dom1= DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			String[] fileP = {null,"","D:/aa4.txt","D:/DBFiles"};
			System.out.println(DocumentUtil.domToFilePByDecode(dom,fileP[2],"utf-8"));
			
		} catch (DomParseException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public final void testDomToFilePByDefDecode() {
		
		try {
			String[] str = {"D:/core.xml","D:/234.txt"};
			Document dom = DocumentUtil.filePToDomByDefDecode(str[0]);
			Document dom1= DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			String[] fileP = {null,"","D:/aa5.txt","D:/DBFiles"};
			System.out.println(DocumentUtil.domToFilePByDefDecode(dom,fileP[2]));
			
		} catch (DomParseException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
