package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefChanl;
import com.sinoway.base.service.BCfgdefChanlService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefChanlCache")
public class BCfgdefChanlCache extends LRUCache{
	@Value("${BCfgdefChanlCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefChanlCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefChanlCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgdefChanlService chanlService;
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
		List<BCfgdefChanl> li = chanlService.find(map);
		for(BCfgdefChanl chanl : li){
			put(chanl.getChnlcod(),chanl);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefChanl getNewData(Object key) throws CacheException {
		BCfgdefChanl chanl = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("chnlcod",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefChanl> li = chanlService.find(map);
		if(li.size()>0){
			chanl = li.get(0);
		}
		return chanl;
	}
	
}
