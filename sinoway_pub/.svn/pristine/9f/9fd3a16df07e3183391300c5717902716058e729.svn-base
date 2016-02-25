package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgrefCompusrprd;
import com.sinoway.base.service.BCfgrefCompusrprdService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgrefCompusrprdCache")
public class BCfgrefCompusrprdCache extends LRUCache{
	
	@Value("${BCfgrefCompusrprdCache.isOpen:false}")
	private boolean isOpen = false;
	@Value("${BCfgrefCompusrprdCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgrefCompusrprdCache.defaultExpire:0}")
	private long defaultExpire;

	@Autowired
	private BCfgrefCompusrprdService compusrprdService;
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
	
	public void loadAll(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sta", "1");
		List<BCfgrefCompusrprd> li = compusrprdService.find(map);
		for(BCfgrefCompusrprd compusrprd : li){
			put(compusrprd.getUsrid()+"_"+compusrprd.getPrdcod(), compusrprd);
		}
	}
	
	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgrefCompusrprd getNewData(Object key) throws CacheException {
		BCfgrefCompusrprd compusrprd = null;
		Map<String,Object> map = new HashMap<String,Object>();
		String[] keys = String.valueOf(key).split("_");
		map.put("usrid",keys[0]);
		map.put("prdcod", keys[1]);
		map.put("sta", "1");
		List<BCfgrefCompusrprd> li = compusrprdService.find(map);
		if(li.size()>0){
			compusrprd = li.get(0);
			cacheMap.put(String.valueOf(key),compusrprd);
		}
		return compusrprd;
	}

}
