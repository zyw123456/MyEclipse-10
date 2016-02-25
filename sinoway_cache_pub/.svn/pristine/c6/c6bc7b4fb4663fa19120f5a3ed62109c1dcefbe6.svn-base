package com.sinoway.cache.simple;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinoway.cache.ICache;

/**
 * 简单缓存实现类，仅供Demo使用，后续使用memcache替代
 * @author THINK
 *
 */
public class SimpleCache implements ICache{
	
	private final static Logger log = LoggerFactory.getLogger(SimpleCache.class);
	
	private static Map<String,Object> cache = new ConcurrentHashMap<String,Object>();
	/**
	 * 构造函数
	 */
	public SimpleCache(){}
	 
//	private static SimpleCache instance = new SimpleCache();
//	
//	public static SimpleCache getInstance(){
//		return instance;
//	}

	@Override
	public boolean save(String key, String value) {
		log.info("save缓存信息：key"+key+",value:"+value);
		try{
			if(cache.containsKey(key)){
				return false;
			}else{
				cache.put(key, value);
			}
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public boolean save(String key, String value, long time) {
		log.info("save缓存信息：key"+key+",value:"+value);
		try{
			if(cache.containsKey(key)){
				return false;
			}else{
				cache.put(key, value);
			}
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(String key, String value) {
		log.info("saveOrUpdate缓存信息：key"+key+",value:"+value);
		try{
			cache.put(key, value);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(String key, String value, long time) {
		log.info("saveOrUpdate缓存信息：key"+key+",value:"+value);
		// TODO Auto-generated method stub
		return saveOrUpdate(key, value);
	}

	@Override
	public boolean update(String key, String value) {
		log.info("update缓存信息：key"+key+",value:"+value);
		try{
			cache.put(key, value);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public boolean update(String key, String value, long time) {
		log.info("update缓存信息：key"+key+",value:"+value);
		try{
			cache.put(key, value);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public boolean remove(String key) {
		log.info("remove缓存信息：key"+key);
		try{
			if(cache.containsKey(key)){
				return false;
			}else{
				cache.remove(key);
				return true;
			}
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean exist(String key) {
		return cache.containsKey(key);
	}

	@Override
	public String get(String key) {
		
		if(cache.containsKey(key)){
			log.info("get缓存信息：key"+key+",value:"+(String)cache.get(key));
			return (String)cache.get(key);
		}else{
			return null;
		}
	}
	
}	

