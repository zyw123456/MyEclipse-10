package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefChnltrnauth;
import com.sinoway.base.service.BCfgdefChnltrnauthService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefChnltrnauthCache")
public class BCfgdefChnltrnauthCache extends LRUCache{
	@Value("${BCfgdefChnltrnauthCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefChnltrnauthCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefChnltrnauthCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgdefChnltrnauthService ChnltrnauthService;
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
		List<BCfgdefChnltrnauth> li = ChnltrnauthService.find(map);
		for(BCfgdefChnltrnauth Chnltrnauth : li){
			put(Chnltrnauth.getChnlcod(), Chnltrnauth);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefChnltrnauth getNewData(Object key) throws CacheException {
		BCfgdefChnltrnauth Chnltrnauth = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("chnlcod",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefChnltrnauth> li = ChnltrnauthService.find(map);
		if(li.size()>0){
			Chnltrnauth = li.get(0);
		}
		return Chnltrnauth;
	}

}
