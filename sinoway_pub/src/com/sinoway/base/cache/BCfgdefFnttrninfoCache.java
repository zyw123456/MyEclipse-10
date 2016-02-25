package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefFnttrninfo;
import com.sinoway.base.service.BCfgdefFnttrninfoService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefFnttrninfoCache")
public class BCfgdefFnttrninfoCache extends LRUCache{
	@Value("${BCfgdefFnttrninfoCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefFnttrninfoCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefFnttrninfoCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgdefFnttrninfoService fnttrninfoService;
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
		List<BCfgdefFnttrninfo> li = fnttrninfoService.find(map);
		for(BCfgdefFnttrninfo fnttrn : li){
			put(fnttrn.getTrncod(), fnttrn);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefFnttrninfo getNewData(Object key) throws CacheException {
		BCfgdefFnttrninfo  fnttrninfo  = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("trncod",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefFnttrninfo> li = fnttrninfoService.find(map);
		if(li.size()>0){
			fnttrninfo = li.get(0);
		}
		return fnttrninfo;
	}

}
