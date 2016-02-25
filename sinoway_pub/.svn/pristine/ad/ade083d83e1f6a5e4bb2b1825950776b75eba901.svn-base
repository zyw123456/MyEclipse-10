package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefChnlblack;
import com.sinoway.base.service.BCfgdefChnlblackService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefChnlblackCache")
public class BCfgdefChnlblackCache extends LRUCache{
	@Value("${BCfgdefChnlblackCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefChnlblackCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefChnlblackCache.defaultExpire:0}")
	private long defaultExpire;
	
	@Autowired
	private BCfgdefChnlblackService chnlblackService;
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
		List<BCfgdefChnlblack> li = chnlblackService.find(map);
		for(BCfgdefChnlblack chnlblack : li){
			put(chnlblack.getChnlcod(), chnlblack);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefChnlblack getNewData(Object key) throws CacheException {
		BCfgdefChnlblack chnlblack = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("chnlno",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefChnlblack> li = chnlblackService.find(map);
		if(li.size()>0){
			chnlblack = li.get(0);
		}
		return chnlblack;
	}


}
