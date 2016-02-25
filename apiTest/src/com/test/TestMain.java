package com.test;

import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.xml.namespace.QName;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.test.fh.Fh_servers;
import com.test.gzt.Gzt_servers;
import com.test.gzt.QueryValidatorServices;
import com.test.gzt.QueryValidatorServicesService;
import com.test.lhp.LhpServers;
import com.test.util.Constant;
import com.test.yimei.YiMeiServices;
/**
 * ������
 * @author jintao
 *
 */
public class TestMain {
	URL wsdlURL;
	QueryValidatorServicesService ss;
	QueryValidatorServices port;
	Gzt_servers servers ;
	LhpServers lhpServers;
	Fh_servers fhServers;
	YiMeiServices ymServers;
	private static final QName SERVICE_NAME = new QName("http://app.service.validator.businesses.gboss.id5.cn", "QueryValidatorServicesService");
	@Before
	public void init(){
		 wsdlURL = QueryValidatorServicesService.WSDL_LOCATION;
		 ss = new QueryValidatorServicesService(wsdlURL, SERVICE_NAME);
	     port = ss.getQueryValidatorServices();
	     servers = new Gzt_servers(port);
	     lhpServers = new LhpServers();
	     fhServers = new Fh_servers();
	     ymServers = new YiMeiServices();
	}
	/**
	 * ����֤��֤ ����������ѯ
	 * @throws Exception
	 */
//	@Test
	@Ignore
	public void authentiaction() throws Exception{
		//���ڲ��Դ������ޣ�����ʹ�ò�һ�»��߲����ڵ�����֤����
		String authentiaction = servers.getGztServers( Constant.GZT_CODE_AUTHENTIACTION, "�׼�ѫ,440201198010274933;���,371327198211073494");
		System.out.println(authentiaction);
	}
	/**
	 * ѧ�����
	 * @throws Exception
	 */
	@Ignore
	public void Education() throws Exception{
		String education = servers.getGztServers( Constant.GZT_CODE_EDUCATION, "�׼�ѫ,440201198010274933;���,371327198211073494");
		System.out.println(education);
	}
	/**
	 * �̻��ۺϲ�ѯ
	 * @throws Exception
	 */
	@Ignore
	public void Nvq() throws Exception{
		String nvq = servers.getGztServers( Constant.GZT_CODE_MOBILE, "01082512114,�����������пƼ����޹�˾,����������,123456");
		System.out.println(nvq);
	}
	/**
	 * ���п�����������֤
	 * @throws Exception
	 */
	@Ignore
	public void bank_auth() throws Exception{
		String bankCard = servers.BankCard();
		System.out.println(bankCard);
	}
	
	/**
	 * �˻��˼�ֵ����
	 * @throws Exception 
	 */
	@Ignore
	public void plane_analyze() throws Exception{
		String passengerStatid = servers.passengerStatid();
		System.out.println(passengerStatid);
	}
	/**
	 * �����ɽ��ڲ���
	 * @throws Exception
	 */
	@Ignore
	public void overdue() throws Exception{
		String realName = "";
		String cardId = "";
		String cardType = "0";
		String overdue = lhpServers.overdue(realName, cardId, cardType);
		System.out.println(new String(overdue.getBytes(),"gbk"));
	}
	
	/**
	 * ��ͨ�ֻ�������֤
	 * @throws Exception
	 */
	@Ignore
	public void shortPhone() throws Exception{
		String phone_number = "18501373321";
		String certType = "0101";
		String certCode = "23012519890808311X";
		String username = "����";
		String shortPhone = servers.shortPhone(phone_number, certType, certCode, username);
		System.out.println(shortPhone);
	}
	
	/**
	 * �����ֻ�������֤
	 * @throws Exception 
	 */
	@Ignore
	public void chinaTelecom() throws Exception{
		String phone_number = "15300038822";
		String certType = "0101";
		String certCode = "152104199101123517";
		String username = "����";
		String shortPhone = servers.shortPhone(phone_number, certType, certCode, username);
		System.out.println(shortPhone);
	}
	
	@Ignore
	public void fhTest() throws UnsupportedEncodingException{
		String name = "���ٱ�";
		String idCard = "342401197606205431";
		System.out.println(fhServers.fhFisk(name, idCard));
	}
	
	
	@Test
	public void queryOverdue(){
		String phone = "18618257313";
		String cycle = "12";
		System.out.println(ymServers.queryOverdue(phone, cycle));
		
	}
	
public static void main(String[] args) {
	TestMain main = new TestMain();
	try {
		main.shortPhone();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}