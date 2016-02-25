package com.sinoway.cache.memcache;

import java.util.Date;

import com.sinoway.cache.ICache;
import com.sinoway.cache.memcache.utils.MemcachConnectConfig;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;


/**
 * @author xiefei (xiefei_work@163.com)
 *	ICache接口的memcache实现类
 */
public class MemcacheImpl implements ICache{
	
	private  MemcachConnectConfig memcacheConfig=new MemcachConnectConfig();
	private  SockIOPool pool;
	
	/**
	 * 无参构造函数：初始化链接池（按默认配置文件）
	 */
	public MemcacheImpl(){
		
//		synchronized (this) {
//			
//			pool=SockIOPool.getInstance("default");
//			pool.setServers(memcacheConfig.getHostList());
//			pool.setWeights(memcacheConfig.getWeights());
//			pool.setInitConn(memcacheConfig.getInitConn());
//			pool.setMinConn(memcacheConfig.getMinConn());
//			pool.setMaxConn(memcacheConfig.getMaxConn());
//			pool.setMaxIdle(memcacheConfig.getMaxIdle());
//			pool.setMaintSleep(memcacheConfig.getMaintSleep());
//			pool.setSocketTO(memcacheConfig.getSocketTO());
//			pool.setSocketConnectTO(memcacheConfig.getSocketConnectTO());
//			pool.setHashingAlg(memcacheConfig.getHashingAlg());
//			pool.initialize();
//			
//			try {
//				Thread.sleep(300);//等待300毫秒，以确保链接池初始化与MemCachedClient方法在多线程下异步执行而无法获取链接的Bug
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			this.client= new MemCachedClient("default");
//		}
		
		
	}
	

	private  MemCachedClient client;

	/**
	 * @param config 配置文件对象MemcachConnectConfig
	 * 无参构造函数：初始化链接池（按配置文件对象）
	 */
	public MemcacheImpl(MemcachConnectConfig config) {
		
		synchronized (this) {
			pool=SockIOPool.getInstance("config");
			pool.setServers(config.getHostList());
			pool.setWeights(config.getWeights());
			pool.setInitConn(config.getInitConn());
			pool.setMinConn(config.getMinConn());
			pool.setMaxConn(config.getMaxConn());
			pool.setMaxIdle(config.getMaxIdle());
			pool.setMaintSleep(config.getMaintSleep());
			pool.setSocketTO(config.getSocketTO());
			pool.setSocketConnectTO(config.getSocketConnectTO());
			pool.setHashingAlg(config.getHashingAlg());
			pool.initialize();
			
			try {
				Thread.sleep(300);//等待300毫秒，以确保链接池初始化与MemCachedClient方法在多线程下异步执行而无法获取链接的Bug
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.client= new MemCachedClient("config");
		}
		

	}

	/**
	 * @param config 配置文件对象MemcachConnectConfig
	 * 设置自定义配置文件，为spring属性注入提供入口
	 */
	public void setMemcacheConfig(MemcachConnectConfig config) {
		
		synchronized (this) {
			System.out.println(config);
			pool=SockIOPool.getInstance("config");
			pool.setServers(config.getHostList());
			pool.setWeights(config.getWeights());
			pool.setInitConn(config.getInitConn());
			pool.setMinConn(config.getMinConn());
			pool.setMaxConn(config.getMaxConn());
			pool.setMaxIdle(config.getMaxIdle());
			pool.setMaintSleep(config.getMaintSleep());
			pool.setSocketTO(config.getSocketTO());
			pool.setSocketConnectTO(config.getSocketConnectTO());
			pool.setHashingAlg(config.getHashingAlg());
			pool.initialize();
			
			try {
				Thread.sleep(300);//等待300毫秒，以确保链接池初始化与MemCachedClient方法在多线程下异步执行而无法获取链接的Bug
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.client= new MemCachedClient("config");
		}
		
	}



	//==============▼接口方法实现▼========================
	
	
	

	@Override
	public boolean save(String key, String value) {
			return client.add(key, value);
	}

	@Override
	public boolean save(String key, String value, long time) {
		return client.add(key, value,new Date(time*1000));
	}

	@Override
	public boolean saveOrUpdate(String key, String value) {
		return client.set(key, value);
	}

	@Override
	public boolean saveOrUpdate(String key, String value, long time) {
		return client.set(key, value,new Date(time*1000));
	}

	@Override
	public boolean update(String key, String value) {
		return client.replace(key, value);
	}

	@Override
	public boolean update(String key, String value, long time) {
		return client.replace(key, value,new Date(time*1000));
	}

	@SuppressWarnings("unchecked")
	@Override
	public  String get(String key) {
		return (String)client.get(key);
	}

	@Override
	public boolean remove(String key) {
		 return client.delete(key);
	}
	
	@Override
	public boolean exist(String key) {
		return client.keyExists(key);
	}

	
}
