/**
 * 
 */
package com.yzj.ibank.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.yzj.wf.com.ibank.common.IBank;
import com.yzj.wf.com.ibank.common.TradeSet;

/**
 * 创建于:2012-5-26<br>
 * 版权所有(C) 2012 深圳市银之杰科技股份有限公司<br>
 * Ibank客户端测试程序
 * 
 * @author WangXue
 * @version 1.0.0
 */
public class IBankClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String[] local = { "config/bean-com.xml" };
			ApplicationContext appContext = new FileSystemXmlApplicationContext(local);
			IBank iBank = (IBank) appContext.getBean("IBank");
			TradeSet tradeSet = new TradeSet("1111");
			tradeSet.put("Field1", "1111");
			tradeSet.put("Field2", "2222");
			tradeSet.put("Field3", "3333");
			System.out.println("发送的报文内容:" + tradeSet.getUpParams());
			tradeSet = iBank.execTrade(tradeSet);
			System.out.println("返回的报文内容:" + tradeSet.getDownParams());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
