/**
 * 
 */
package com.yzj.ibank.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.yzj.wf.com.ibank.common.IBank;
import com.yzj.wf.com.ibank.common.TradeSet;

/**
 * 创建于:2012-5-26<br>
 * 版权所有(C) 2012 深圳市银之杰科技股份有限公司<br>
 * Ibank客户端XML报文测试程序
 * 
 * @author WangXue
 * @version 1.0.0
 */
public class IBankClientXMLTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String[] local = { "conf/bean-com-xml.xml" };
			ApplicationContext appContext = new FileSystemXmlApplicationContext(local);
			IBank iBank = (IBank) appContext.getBean("IBank");

			TradeSet tradeSet = new TradeSet("3111");
			long businessId = 3111;
			tradeSet.setBusinessId(businessId);

			// 设置交易数据
			tradeSet.put("a", "1.0");
			tradeSet.put("Document|HEAD|VER", "1.0");
			tradeSet.put("Document|HEAD|SRC", 11);

			tradeSet.put("Document|HEAD|DES", "00");
			tradeSet.put("Document|HEAD|APP", "PLT");
			tradeSet.put("Document|HEAD|MsgNo", 3111);
			tradeSet.put("Document|HEAD|MsgID", "01201312101234563111");
			tradeSet.put("Document|HEAD|MsgRef", "01201312101234563111");
			tradeSet.put("Document|HEAD|WorkDate", 20131210);
			tradeSet.put("Document|HEAD|Reserve", "testReserve");

			tradeSet.put("Document|MSG|BatchHead3111|AllNum", 3);
			tradeSet.put("Document|MSG|BatchHead3111|AllAmt", 1);

			tradeSet.put("Document|MSG|BatchHead3111|BatchNo", 123456);
			tradeSet.put("Document|MSG|BatchHead3111|TermNo", "121212121212");

			// 设置循环交易数据
			List<Map> listMap = new ArrayList<Map>();
			Map<String, String> map = new HashMap<String, String>();

			map.put("OldNo", "1111");
			map.put("State", "aa");
			listMap.add(map);

			Map<String, String> map2 = new HashMap<String, String>();

			map2.put("OldNo", "2222");
			map2.put("State", "bb");
			listMap.add(map2);

			Map<String, String> map3 = new HashMap<String, String>();

			map3.put("OldNo", "3333");
			map3.put("State", "cc");
			listMap.add(map3);
			tradeSet.putList("Document|MSG|BatchHead3111|AllNum", listMap);

			// 发送报文
			iBank.execTrade(tradeSet);

			// 接受返回数据
			// 详细数据如下
			System.out.println("普通报文详细数据打印开始...");
			Map<String, Object> downMap = tradeSet.getDownParams();
			for (Entry<String, Object> entry : downMap.entrySet()) {
				System.out.println(entry.getKey() + " , " + entry.getValue());
			}
			System.out.println("普通报文详细数据打印结束...");
			System.out.println("循环报文详细数据打印开始...");
			Map<String, List<Map>> downListMap = tradeSet.getDownListParams();
			for (Entry<String, List<Map>> entry : downListMap.entrySet()) {
				System.out.println(entry.getKey() + " , " + entry.getValue());
			}
			System.out.println("循环报文详细数据打印结束...");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
