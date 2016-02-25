package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefQueue;
import com.sinoway.base.service.BCfgdefQueueService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefQueueCache")
public class BCfgdefQueueCache extends LRUCache{
	@Value("${BCfgdefQueueCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefQueueCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefQueueCache.defaultExpire:0}")
	private long defaultExpire;

	@Autowired
	private BCfgdefQueueService queueService;
	@Autowired
	private ICacheMapper cacheMapper; 
	
	@PostConstruct  
	public synchronized  void init() {
		initCacheParam();
		if(isOpen){
			loadAll();
			cacheMapper.putCache(this, defaultExpire);
		}
	}

	public void initCacheParam(){
		setCacheSize(cacheSize);
		setDefaultExpire(defaultExpire);
		setCacheMap(getCacheMap());
	}
	
	public synchronized  void loadAll() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sta", "1");
		List<BCfgdefQueue> li = queueService.find(map);
		for(BCfgdefQueue queue : li){
			put(queue.getQuecod(), queue);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefQueue getNewData(Object key) throws CacheException {
		BCfgdefQueue queue = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("quecod",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefQueue> li = queueService.find(map);
		if(li.size()>0){
			queue = li.get(0);
		}
		return queue;
	}

}
