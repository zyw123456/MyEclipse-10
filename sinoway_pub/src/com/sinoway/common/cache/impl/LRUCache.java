package com.sinoway.common.cache.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sinoway.common.cache.Cache;
 
/**
 * 未采用LRU实现,临时使用HashMap
 * @author xiehao
 *
 * @param <K>
 * @param <V>
 */
public abstract class LRUCache<K, V> implements Cache<K,V>{
 	
	/**
	 * 缓存读取不到数据时或者数据过期,从数据库中获取最新的数据返回
	 * @param key
	 * @return
	 */
	protected Map<K,V> cacheMap;
	protected int cacheSize = 0;      // 缓存大小 , 0 -> 无限制
	protected long defaultExpire;     // 默认刷新时间, 0 -> 永不刷新
	protected boolean lock = false;	  // 缓存锁,用于缓存刷新期间判断缓存刷新的状态
	/**
	 * 获取最新的数据,交由子类实现
	 * @param key
	 * @return
	 */
	protected abstract V getNewData(K key);
	
    public synchronized List<V> values(){
    	Iterator<V> iterator = cacheMap.values().iterator();
    	List<V> li = new ArrayList<V>(); 
		while(iterator.hasNext()){
			V value = iterator.next();
			li.add(value);
		}
    	return li;
    }
	/**
	 * 获得cacheMap中的keys
	 * @return
	 */
	public synchronized List<K> keys(){
		Iterator<K> iterator = cacheMap.keySet().iterator();
		List<K> li = new ArrayList<K>(); 
		while(iterator.hasNext()){
			K value = iterator.next();
			li.add(value);
		}
    	return li;
	}

	public synchronized void put(K key,V value){
		//TODO 判断是否已满,清理数据
		cacheMap.put(key, value);
	}
	
	public  V get(K key){
		V value = cacheMap.get(key);
		if(null == value){
			value = getNewData(key);
		}
		return value;
	}
	
	public  boolean containsKey(K key){
		return cacheMap.containsKey(key);
	}
	
	public  boolean containsValue(V value){
		return cacheMap.containsValue(value);
	}
	
	public boolean isFull() {
		if (cacheSize == 0) {//o -> 无限制
			return false;
		}
		return cacheMap.size() >= cacheSize;
	}

	public synchronized void remove(K key) {
		cacheMap.remove(key);
	}

	public synchronized void clear() {
		cacheMap.clear();
	}

	public int size() {
		return cacheMap.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int getCacheSize() {
		return cacheSize;
	}

	public void setCacheSize(int cacheSize) {
		this.cacheSize = cacheSize;
	}
	public long getDefaultExpire() {
		return defaultExpire;
	}
	public void setDefaultExpire(long defaultExpire) {
		this.defaultExpire = defaultExpire;
	}
	public void setCacheMap(Map<K, V> cacheMap) {
		this.cacheMap = cacheMap;
	}
	//实例化一个map
	public Map<K, V> getCacheMap() {
		if(null == cacheMap){
			cacheMap = new HashMap<K, V>();
		}
	    return cacheMap;
	}


	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}	
}