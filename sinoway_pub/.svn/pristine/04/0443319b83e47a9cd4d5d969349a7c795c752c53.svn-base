package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefUsrtrnblack;
import com.sinoway.base.service.BCfgdefUsrtrnblackService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefUsrTrnblackCache")
public class BCfgdefUsrTrnblackCache extends LRUCache{
	@Value("${BCfgdefUsrTrnblackCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefUsrTrnblackCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefUsrTrnblackCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgdefUsrtrnblackService usrtrnblackService;
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
		List<BCfgdefUsrtrnblack> li = usrtrnblackService.find(map);
		for(BCfgdefUsrtrnblack usrtrnBlack : li){
			//以下划线分割检索条件
			put(usrtrnBlack.getUsrid()+"_"+usrtrnBlack.getTrncod(), usrtrnBlack);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefUsrtrnblack getNewData(Object key) throws CacheException {
		BCfgdefUsrtrnblack usrtrnblack = null;
		Map<String,Object> map = new HashMap<String,Object>();
		//对key(搜索条件)进行拆分
		String[] keys = String.valueOf(key).split("_");
		map.put("usrid",keys[0]);
		map.put("trncod",keys[1]);
		map.put("sta", "1");
		List<BCfgdefUsrtrnblack> li = usrtrnblackService.find(map);
		if(li.size()>0){
			usrtrnblack = li.get(0);
			cacheMap.put(String.valueOf(key),usrtrnblack);
		}
		return usrtrnblack;
	}

}
