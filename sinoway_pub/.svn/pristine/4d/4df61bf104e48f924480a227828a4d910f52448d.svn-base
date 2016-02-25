package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefTrnblack;
import com.sinoway.base.service.BCfgdefTrnblackService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefTrnblackCache")
public class BCfgdefTrnblackCache extends LRUCache{
	@Value("${BCfgdefTrnblackCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefTrnblackCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefTrnblackCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgdefTrnblackService trnblackService;
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
		List<BCfgdefTrnblack> li = trnblackService.find(map);
		for(BCfgdefTrnblack trnBlack : li){
			put(trnBlack.getTrncod(), trnBlack);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefTrnblack getNewData(Object key) throws CacheException {
		BCfgdefTrnblack trnblack = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("usrid",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefTrnblack> li = trnblackService.find(map);
		if(li.size()>0){
			trnblack = li.get(0);
		}
		return trnblack;
	}

}
