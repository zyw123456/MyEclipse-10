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
 * 测试类
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
	 * 身份证验证 单次批量查询
	 * @throws Exception
	 */
//	@Test
	@Ignore
	public void authentiaction() throws Exception{
		//由于测试次数有限，所以使用不一致或者不存在的身份证号码
		String authentiaction = servers.getGztServers( Constant.GZT_CODE_AUTHENTIACTION, "米嘉勋,440201198010274933;坚壁,371327198211073494");
		System.out.println(authentiaction);
	}
	/**
	 * 学历审核
	 * @throws Exception
	 */
	@Ignore
	public void Education() throws Exception{
		String education = servers.getGztServers( Constant.GZT_CODE_EDUCATION, "米嘉勋,440201198010274933;坚壁,371327198211073494");
		System.out.println(education);
	}
	/**
	 * 固话综合查询
	 * @throws Exception
	 */
	@Ignore
	public void Nvq() throws Exception{
		String nvq = servers.getGztServers( Constant.GZT_CODE_MOBILE, "01082512114,北京华瑞网研科技有限公司,北京海淀区,123456");
		System.out.println(nvq);
	}
	/**
	 * 银行卡姓名卡号验证
	 * @throws Exception
	 */
	@Ignore
	public void bank_auth() throws Exception{
		String bankCard = servers.BankCard();
		System.out.println(bankCard);
	}
	
	/**
	 * 乘机人价值分析
	 * @throws Exception 
	 */
	@Ignore
	public void plane_analyze() throws Exception{
		String passengerStatid = servers.passengerStatid();
		System.out.println(passengerStatid);
	}
	/**
	 * 量化派金融测试
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
	 * 联通手机号码认证
	 * @throws Exception
	 */
	@Ignore
	public void shortPhone() throws Exception{
		String phone_number = "18501373321";
		String certType = "0101";
		String certCode = "23012519890808311X";
		String username = "刘振";
		String shortPhone = servers.shortPhone(phone_number, certType, certCode, username);
		System.out.println(shortPhone);
	}
	
	/**
	 * 电信手机号码认证
	 * @throws Exception 
	 */
	@Ignore
	public void chinaTelecom() throws Exception{
		String phone_number = "15300038822";
		String certType = "0101";
		String certCode = "152104199101123517";
		String username = "靳涛";
		String shortPhone = servers.shortPhone(phone_number, certType, certCode, username);
		System.out.println(shortPhone);
	}
	
	@Ignore
	public void fhTest() throws UnsupportedEncodingException{
		String name = "吕少兵";
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
