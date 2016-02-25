package com.sinoway.common.service.consumer;

import java.net.Socket;
import java.util.Map;

import com.sinoway.common.constant.ServerConstant.TradePoolType;
import com.sinoway.common.frame.SpringContextUtil;
import com.sinoway.common.pool.McpThreadPool;
import com.sinoway.common.pool.ThreadPoolFactory;
import com.sinoway.common.pool.task.GeneralSocketOPtradeTaskService;
import com.sinoway.common.pool.task.GeneralStradeTaskService;
import com.sinoway.common.pool.task.IPoolTask;
import com.sinoway.common.util.GUIDGenerator;


/**
 * 通用子交易消费者服务默认实现
 * @author Liuzhen
 * @version 1.0
 * 2015-11-24
 */
public class GeneralSConsumerService extends DefaultConsumerService {

	private Map<String,String> poolIdMap = null;
    private int coreTNum = 20;
    private int maxTNum = 20;
    private int cacheNum = 20;
    private long keepAlive = 60*1000;
    
	public GeneralSConsumerService(String topic, String groupId) {
		super(topic, groupId);
	}
	public GeneralSConsumerService() {
		super();
	}

	/**
	 * 消息处理
	 */
	@Override
	public void excuteMsg(String msg) {
		McpThreadPool pool = ThreadPoolFactory.getPool(poolIdMap.get(TradePoolType.strade_proc_pool.getValue()));
		if(pool == null){
			pool = ThreadPoolFactory.newPool(TradePoolType.strade_proc_pool.getValue(), coreTNum, maxTNum, cacheNum, keepAlive);
		}
		
		GeneralStradeTaskService<String> task = (GeneralStradeTaskService)SpringContextUtil.getBean(GeneralStradeTaskService.class);
		
		task.init(GUIDGenerator.generateId(), pool, msg);
		try{
			pool.excute(task);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	 *   GETTER  AND  SETTER
	 */
	public Map<String, String> getPoolIdMap() {
		return poolIdMap;
	}

	public void setPoolIdMap(Map<String, String> poolIdMap) {
		this.poolIdMap = poolIdMap;
	}
}