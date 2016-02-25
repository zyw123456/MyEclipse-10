package com.sinoway.base.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.sinoway.base.cache.BCfgdefChanlCache;
import com.sinoway.base.cache.BCfgdefChargeCache;
import com.sinoway.base.entity.BCfgdefChanl;

public class TestCache {
	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("conf/spring/applicationContext.xml");
		//BCfgdefChanlCache obc = (BCfgdefChanlCache) context.getBean("bCfgdefChanlCache");
		//BCfgdefChargeCache cc = (BCfgdefChargeCache) context.getBean("bCfgdefChargeCache");
		//System.out.println(obc.getDefaultExpire());
		//System.err.println(obc.getCacheSize());
		//System.out.println(obc.values());
		//System.out.println(obc.get("1234"));
//		BCfgdefChanl chanl1 = new BCfgdefChanl();
//		chanl1.setChnlcod("1234");
//		BCfgdefChanl chanl2 = new BCfgdefChanl();
//		chanl2.setChnlcod("2234");
//		obc.put("1234",chanl1);
//		//System.out.println(obc.values().size());
//		obc.put("2234",chanl2);
//		System.out.println(obc.values().size());
		//System.out.println(obc.get("1234")+"------------");
		//System.out.println(obc.get("2234")+"************");
//		System.out.println();
//		BCfgdefCharge cct = new BCfgdefCharge();
//		cct.setChargecod("123456677");
//		System.out.println(cc.size());
//		cc.put("5689", cct);
//		System.out.println(cc.size());
		//System.out.println(obc.values());
		//List<String> li = obc.keys();
//		List<BCfgdefChanl> l = new ArrayList<BCfgdefChanl>();
//		for(String chanl: li){
//			System.out.println("111111");
//			System.out.println(chanl);
//			System.out.println((BCfgdefChanl) obc.get(chanl));
//		}
		
//		Iterator it = obc.keys().iterator();
//		System.out.println(obc.size());
//		while(it.hasNext()){
//			obc.get(it.next());
//		}
//		System.out.println(obc.size());
//		
	}
}
