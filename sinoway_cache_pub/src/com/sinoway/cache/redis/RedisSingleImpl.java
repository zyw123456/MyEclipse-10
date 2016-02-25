package com.sinoway.cache.redis;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

import com.sinoway.cache.ICache;
import com.sinoway.cache.redis.utils.RedisConnectConfig;
import com.sinoway.cache.utils.JsonUtils;

/**
 * @author xiefei (xiefei_work@163.com) ICache接口的单节点redis服务器实现类
 */
public class RedisSingleImpl implements ICache {
	private Jedis Jedis;
	private ReentrantLock lock = new ReentrantLock();

	public RedisSingleImpl() {

		// RedisConnectConfig rc = new RedisConnectConfig();
		// Set<HostAndPort> nodes = rc.getNodes();
		// Iterator<HostAndPort> iterator = nodes.iterator();
		// HostAndPort next = iterator.next();
		// this.Jedis= new Jedis(next.getHost(), next.getPort());

	}

	public RedisSingleImpl(RedisConnectConfig redisConnectConfig) {

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
		Iterator<HostAndPort> iterator = nodesList.iterator();
		HostAndPort next = iterator.next();// 如果有多个，默认只取第一个
		this.Jedis = new Jedis(next.getHost(), next.getPort(),1000,1000);
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
		Iterator<HostAndPort> iterator = nodesList.iterator();
		HostAndPort next = iterator.next();// 如果有多个，默认只取第一个
		this.Jedis = new Jedis(next.getHost(), next.getPort(),1000,1000);
	
		lock.unlock();

	}

	// ==============▼接口方法实现▼========================

	@Override
	public boolean save(String key, String value) {

		if (!this.Jedis.exists(key)) // 如果不存在，则保存,默认有效期为this.redisConfig.getDataDefaultTime()的值
			return this.Jedis.setex(key,
					Integer.valueOf(this.redisConfig.getDataDefaultTime()),
					value).equals("OK") ? true : false;

		return false;
	}

	@Override
	public boolean save(String key, String value, long time) {

		if (!this.Jedis.exists(key))// 如果不存在，则保存,,默认有效期为this.redisConfig.getDataDefaultTime()的值
			return this.Jedis.setex(key, new Long(time).intValue(), value)
					.equals("OK") ? true : false;

		return false;
	}

	@Override
	public boolean saveOrUpdate(String key, String value) {

		return this.Jedis.setex(key,
				Integer.valueOf(this.redisConfig.getDataDefaultTime()), value)
				.equals("OK") ? true : false;
	}

	@Override
	public boolean saveOrUpdate(String key, String value, long time) {

		return this.Jedis.setex(key, new Long(time).intValue(), value).equals(
				"OK") ? true : false;
	}

	@Override
	public boolean update(String key, String value) {

		if (this.Jedis.exists(key)) // 如果存在，则更新，并返回true
			return this.Jedis.set(key, value).equals("OK") ? true : false;

		return false;
	}

	@Override
	public boolean update(String key, String value, long time) {

		if (this.Jedis.exists(key)) // 如果存在，则更新值和有效期，并返回true,
			return this.Jedis.setex(key, new Long(time).intValue(), value)
					.equals("OK") ? true : false;

		return false;
	}

	@Override
	public String get(String key) {

		return this.Jedis.get(key);
	}

	@Override
	public boolean remove(String key) {

		return this.Jedis.del(key) == 1L ? true : false;
	}

	@Override
	public boolean exist(String key) {

		return this.Jedis.exists(key);
	}

}
