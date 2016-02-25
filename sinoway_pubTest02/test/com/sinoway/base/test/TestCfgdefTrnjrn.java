package com.sinoway.base.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.sinoway.common.exception.GeneratorException;
import com.sinoway.common.frame.SpringUtil;
import com.sinoway.common.util.JRNGenerator;

public class TestCfgdefTrnjrn {
	public static void main(String[] args) {
		//ApplicationContext context = new FileSystemXmlApplicationContext("conf/spring/applicationContext.xml");
		//JRNGenerator rcf = (JRNGenerator) context.getBean("jRNGenerator");
		//JRNGenerator rcf =(JRNGenerator) SpringUtil.getBean("jRNGenerator");
		String sysCod="C";
		String sysTyp="F";
		String trnCod="10100001";
	
		for(int i=0;i<2000;i++){
		//System.out.println("流水号位数："+JRNGenerator.getInstance().generateJrn(sysCod, sysTyp, trnCod).length());
		try {
			System.out.println("流水号："+JRNGenerator.getInstance().generateJrn(sysCod, sysTyp, trnCod));
		} catch (GeneratorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	}
}
