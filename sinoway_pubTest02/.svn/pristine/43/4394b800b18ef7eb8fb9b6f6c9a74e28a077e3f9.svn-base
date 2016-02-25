package com.sinoway.base.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.sinoway.base.entity.BCfgdefOrginfo;
import com.sinoway.base.entity.BCfgdefPrsnusr;
import com.sinoway.base.facde.RightChkFacde;
import com.sinoway.base.service.BCfgdefOrginfoService;
import com.sinoway.base.service.BCfgdefPrsnusrService;
import com.sinoway.common.util.GUIDGenerator;

public class TestDefRole {
	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("conf/spring/applicationContext.xml");
		//BCfgdefOrginfoService os = (BCfgdefOrginfoService) context.getBean("bCfgdefOrginfoService");
		BCfgdefPrsnusrService ps = (BCfgdefPrsnusrService) context.getBean("bCfgdefPrsnusrService");
		BCfgdefOrginfo orginfo = new BCfgdefOrginfo();
		//orginfo.setId(GUIDGenerator.generateId());
		//orginfo.setOrgno("testorg");
		//os.save(orginfo);
		BCfgdefPrsnusr bCfgdefPrsnusr = new BCfgdefPrsnusr();
		bCfgdefPrsnusr.setId(GUIDGenerator.generateId());
		bCfgdefPrsnusr.setOrgno("testorg");
		bCfgdefPrsnusr.setUsrid("1234567890");
		bCfgdefPrsnusr.setUsrnam("xiehao");
		ps.save(bCfgdefPrsnusr);
		
	}
}
