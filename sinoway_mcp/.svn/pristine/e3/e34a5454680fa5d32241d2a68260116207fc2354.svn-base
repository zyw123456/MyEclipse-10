package com.yzj.ibank.prosser;

import java.util.Map;

import com.yzj.wf.com.ibank.common.TradeSet;
import com.yzj.wf.com.ibank.common.server.IBankProcess;
import com.yzj.wf.com.ibank.common.server.IBankProcessException;

/**
 * 创建于:2014-7-16<br>
 * 版权所有(C) 2014 深圳市银之杰科技股份有限公司<br>
 * TODO
 * 
 * @author WangXue
 * @version 1.0.0
 */
public class ExampleProcess implements IBankProcess {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yzj.wf.com.ibank.common.server.IBankProcess#execTrade(com.yzj.wf.
	 * com.ibank.common.TradeSet)
	 */
	@Override
	public TradeSet execTrade(TradeSet tradeSet) throws IBankProcessException {
		Map<String, Object> downMap = tradeSet.getDownParams();
		System.out.println("收到的报文内容：" + downMap);

		Map<String, Object> upMap = tradeSet.getUpParams();
		upMap.put("Field4", "4444");
		upMap.put("Field5", "5555");
		upMap.put("Field6", "6666");

		return tradeSet;
	}

}
