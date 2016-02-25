package com.yzj.ibank.prosser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.yzj.wf.com.ibank.common.TradeSet;
import com.yzj.wf.com.ibank.common.server.IBankProcess;
import com.yzj.wf.com.ibank.common.server.IBankProcessException;

/**
 * 创建于:2015-4-16<br>
 * 版权所有(C) 2014 深圳市银之杰科技股份有限公司<br>
 * TODO
 * 
 * @author chenhuang
 * @version 1.0.0
 */
public class XMLExampleProcess implements IBankProcess {

	@SuppressWarnings("rawtypes")
	public TradeSet execTrade(TradeSet tradeSet) throws IBankProcessException {
		System.out.println("服务端接收报文:【" + tradeSet.getTradeId() + "】的数据开始...");
		// 详细数据如下
		Map<String, Object> downMap = tradeSet.getDownParams();
		System.out.println("普通报文详细数据打印开始...");
		for (Entry<String, Object> entry : downMap.entrySet()) {
			System.out.println(entry.getKey() + " , " + entry.getValue());
		}
		System.out.println("普通报文详细数据打印结束...");
		System.out.println("循环报文详细数据打印开始...");
		Map<String, List<Map>> downListMap = tradeSet.getDownListParams();
		System.out.println("downListMap" + downListMap);
		for (Entry<String, List<Map>> entry : downListMap.entrySet()) {
			System.out.println(entry.getKey() + " , " + entry.getValue());
		}
		System.out.println("循环报文详细数据打印结束...");
		System.out.println("服务端接收报文:【" + tradeSet.getTradeId() + "】的数据结束...");

		// 设置返回数据
		Map<String, Object> upMap = tradeSet.getUpParams();
		upMap.put("Document|MSG|BatchHead3111|TradeNo", "111122223333");
		upMap.put("Document|MSG|BatchHead3111|TradeDate", "20150416");

		// 设置循环返回数据
		List<Map> listMap = new ArrayList<Map>();
		Map<String, String> map = new HashMap<String, String>();

		map.put("DealNo", "111111111111");
		map.put("DealDate", "20150416");
		listMap.add(map);

		Map<String, String> map2 = new HashMap<String, String>();

		map.put("DealNo", "222222222222");
		map.put("DealDate", "20150417");
		listMap.add(map2);

		Map<String, String> map3 = new HashMap<String, String>();

		map3.put("DealNo", "333333333333");
		map3.put("DealDate", "20150415");
		listMap.add(map3);
		tradeSet.putList("Document|MSG|BatchHead3111|AllNum", listMap);
		return tradeSet;
	}
}
