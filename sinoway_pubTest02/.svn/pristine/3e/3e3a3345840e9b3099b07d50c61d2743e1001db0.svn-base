package com.sinoway.base.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.sinoway.base.bean.AuthorValidate;
import com.sinoway.base.facde.RightChkFacde;

public class TestRightChk {
	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("conf/spring/applicationContext.xml");
		RightChkFacde rcf = (RightChkFacde) context.getBean("rightChkFacde");
		AuthorValidate bean = new AuthorValidate();
		bean.setUsercod("100000000000000000000001");
		bean.setTrncod("10000002");
		bean.setPrdcod("10000001");
		bean.setOrgno("1000000000001");
		bean.setChnlcod("10000001");
		bean.setFnttrnjrn("2015103022110422");
		System.out.println(rcf.rightChkRes(bean));
		System.out.println(rcf.rightChkRes(bean));
	}
}
