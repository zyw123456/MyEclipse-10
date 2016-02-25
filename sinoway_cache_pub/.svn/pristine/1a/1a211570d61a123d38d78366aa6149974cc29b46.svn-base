package com.sinoway.cache.redis;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import com.sinoway.cache.ICache;
import com.sinoway.cache.redis.utils.RedisConnectConfig;

/**
 * @author xiefei (xiefei_work@163.com) ICache接口的redis 3.0 cluster集群实现类
 */
public class RedisClusterImpl implements ICache {

	private JedisCluster jedisCluster;
	private ReentrantLock lock = new ReentrantLock();

	public RedisClusterImpl() {

	}

	public RedisClusterImpl(RedisConnectConfig redisConnectConfig) {

		lock.lock();
		this.redisConfig = redisConnectConfig;
		String hostLists = redisConfig.getHostLists();
		Set<HostAndPort> nodesList = new HashSet<HostAndPort>();
		if (StringUtils.isNoneBlank(hostLists)) {
			String[] node = hostLists.split(",");
			for (int i = 0; i < node.length; i++) {
				String[] split = node[i].split(":");
				HostAndPort hostPort = new HostAndPort(split[0],
						Integer.parseInt(split[1]));
				nodesList.add(hostPort);
			}
		}
		
		GenericObjectPoolConfig config= new GenericObjectPoolConfig();
		config.setMinIdle(50);
		config.setMaxIdle(100);
		config.setMaxTotal(1000);
		config.setMaxWaitMillis(1000*180);
		this.jedisCluster=new JedisCluster(nodesList, 300000, 300000, Integer.MAX_VALUE, config);
		lock.unlock();

	}

	/**
	 * redis配置类，默认初始化加载配置文件配置，也可以spring注入新配置(通过set方法)
	 */
	private RedisConnectConfig redisConfig = new RedisConnectConfig();

	public void setRedisConfig(RedisConnectConfig redisConfig) {

		lock.lock();
		this.redisConfig = redisConfig;
		String hostLists = redisConfig.getHostLists();
		Set<HostAndPort> nodesList = new HashSet<HostAndPort>();
		if (StringUtils.isNoneBlank(hostLists)) {
			String[] node = hostLists.split(",");
			for (int i = 0; i < node.length; i++) {
				String[] split = node[i].split(":");
				HostAndPort hostPort = new HostAndPort(split[0],
						Integer.parseInt(split[1]));
				nodesList.add(hostPort);
			}
		}
		GenericObjectPoolConfig config= new GenericObjectPoolConfig();
		config.setMinIdle(50);
		config.setMaxIdle(100);
		config.setMaxTotal(1000);
		config.setMaxWaitMillis(1000*180);
		this.jedisCluster=new JedisCluster(nodesList, 300000, 300000, Integer.MAX_VALUE, config);
		lock.unlock();
	}

	// ==============▼接口方法实现▼========================

	@Override
	public boolean save(String key, String value) {
		if (!this.jedisCluster.exists(key)) // 如果不存在，则保存,默认有效期为this.redisConfig.getDataDefaultTime()的值
			return this.jedisCluster.setex(key,
					Integer.valueOf(this.redisConfig.getDataDefaultTime()),
					value).equals("OK") ? true : false;

		return false;
	}

	@Override
	public boolean save(String key, String value, long time) {
		if (!this.jedisCluster.exists(key))// 如果不存在，则保存,,默认有效期为this.redisConfig.getDataDefaultTime()的值
			return this.jedisCluster.setex(key, new Long(time).intValue(),
					value).equals("OK") ? true : false;

		return false;
	}

	@Override
	public boolean saveOrUpdate(String key, String value) {
		return this.jedisCluster.setex(key,
				Integer.valueOf(this.redisConfig.getDataDefaultTime()), value)
				.equals("OK") ? true : false;
	}

	@Override
	public boolean saveOrUpdate(String key, String value, long time) {
		return this.jedisCluster.setex(key, new Long(time).intValue(), value)
				.equals("OK") ? true : false;
	}

	@Override
	public boolean update(String key, String value) {
		if (this.jedisCluster.exists(key)) // 如果存在，则更新，并返回true
			return this.jedisCluster.set(key, value).equals("OK") ? true
					: false;
		return false;
	}

	@Override
	public boolean update(String key, String value, long time) {
		if (this.jedisCluster.exists(key)) // 如果存在，则更新值和有效期，并返回true,
			return this.jedisCluster.setex(key, new Long(time).intValue(),
					value).equals("OK") ? true : false;
		return false;
	}

	@Override
	public String get(String key) {
		return this.jedisCluster.get(key);
	}

	@Override
	public boolean remove(String key) {
		return this.jedisCluster.del(key) == 1L ? true : false;
	}

	@Override
	public boolean exist(String key) {
		return this.jedisCluster.exists(key);
	}

}
