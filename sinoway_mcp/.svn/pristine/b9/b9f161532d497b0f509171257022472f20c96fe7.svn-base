package com.sinoway.common.pool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.sinoway.common.constant.ServerConstant.OPtradePoolPam;
import com.sinoway.common.constant.ServerConstant.OPtradeResPoolPam;
import com.sinoway.common.constant.ServerConstant.StradePoolPam;
import com.sinoway.common.constant.ServerConstant.TradePoolType;

/**
 * 线程池工厂
 * @author Liuzhen
 * @version 1.0
 * 2016-1-26
 */
public class ThreadPoolFactory {

	private static Map<String,McpThreadPool> pools = null;// 线程池集合
    private Map<String,String> poolIdMap = null;

	public  ThreadPoolFactory(){
//		init();
	}
	
	/**
	 * 初始化线程池工厂
	 */
	public void init(){
		pools = new HashMap<String,McpThreadPool>();
		if(poolIdMap != null){
			Set<String> keys = poolIdMap.keySet();
			for(String key : keys){
				String poolId = poolIdMap.get(key);
				if(TradePoolType.otrade_proc_pool.getValue().equals(poolId)){
					newPool(poolId,(int)OPtradePoolPam.coreTNum.getValue(),(int)OPtradePoolPam.maxTNum.getValue(),(int)OPtradePoolPam.cacheNum.getValue(),OPtradePoolPam.keepAlive.getValue());
				}else if(TradePoolType.otrade_res_proc_pool.getValue().equals(poolId)){
					newPool(poolId,(int)OPtradeResPoolPam.coreTNum.getValue(),(int)OPtradeResPoolPam.maxTNum.getValue(),(int)OPtradeResPoolPam.cacheNum.getValue(),OPtradeResPoolPam.keepAlive.getValue());
				}else if(TradePoolType.strade_proc_pool.getValue().equals(poolId)){
					newPool(poolId,(int)StradePoolPam.coreTNum.getValue(),(int)StradePoolPam.maxTNum.getValue(),(int)StradePoolPam.cacheNum.getValue(),StradePoolPam.keepAlive.getValue());
				}
			}
		}
	}
	
	/**
	 * 创建一个线程池
	 * @param id
	 * @param coreTNum
	 * @param maxTNum
	 * @param cacheNum
	 * @param keepAlive
	 * @return
	 */
	public synchronized static McpThreadPool newPool(String id,int coreTNum,int maxTNum,int cacheNum,long keepAlive){
		if(pools.containsKey(id))
			return pools.get(id);
		
		McpThreadPool pool = new McpThreadPool();
		pool.init(id, coreTNum,maxTNum,cacheNum, keepAlive);
		pools.put(id, pool);
		return pool;
	}
	
	/**
	 * 通过Id 获取线程池
	 * @param id
	 * @return
	 */
	public static McpThreadPool getPool(String id){
		return pools.get(id);
	}
	
	/**
	 * 关闭所有线程池
	 */
	public static void stopAllPool(){
		synchronized (pools) {
			Set<String> set = pools.keySet();
			Iterator<String> it = set.iterator();
			
			while(it.hasNext()){
				String key = it.next();
				pools.get(key).stop();
			}
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
