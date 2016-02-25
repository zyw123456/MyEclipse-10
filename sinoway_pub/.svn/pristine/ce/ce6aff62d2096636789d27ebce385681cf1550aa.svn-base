package com.sinoway.common.cache;

import com.sinoway.common.exception.CacheException;

/**
 * cache缓存有效地存储临时对象，以提高一个应用的性能
 * @author xiehao
 *
 * @param <K>
 * @param <V>
 */
public interface Cache<K,V> {
	/**
	 * 获取缓存中指定key下的值，当不存在时，返回为空
	 *
	 * @param 在设置缓存时指定的key
	 * @return 缓存的对象或者为空
	 * @throws 当访问出现问题时，抛出CacheException异常
	 */
	public V get(K key) throws CacheException;

	/**
	 * 添加一个缓存实体
	 *
	 * @param key   用来标识放入缓存的实体
	 * @param value 存放缓存中实体的值
	 * @throws 当访问出现问题时，抛出CacheException异常
	 */
	public void put(K key, V value) throws CacheException;

	/**
	 * 从缓存中，移除指定key的实体
	 *
	 * @param 在添加缓存时设置的key
	 * @return 返回这个key下，之前存放的实体，当不存在时，返回空
	 * @throws 当访问出现问题时，抛出CacheException异常
	 */
	public void remove(K key) throws CacheException;

	/**
	 * 清空缓存
	 *
	 * @throws 当访问出现问题时，抛出CacheException异常
	 */
	public void clear() throws CacheException;

	/**
	 * 返回在缓存中，存放实体的个数
	 *
	 * @return 在缓存中，存放实体的个数
	 */
	public int size() throws CacheException;

	
    /**
     * 缓存是否已经满
     * @return
     */
    public boolean isFull() throws CacheException;
    
    /**
     * 返回缓存大小
     * 
     * @return  
     */
    public int getCacheSize() throws CacheException;
    
    /**
     * 缓存中是否为空
     */
    public boolean isEmpty() throws CacheException;
    
    /**
     * 加载所有
     * @return
     * @throws CacheException
     */
    public void loadAll() throws CacheException;
    
    /**
     * 重新加载
     * @return
     * @throws CacheException
     */
    public void reloadAll() throws CacheException;
    
}
