package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefFnttrnaddr;
import com.sinoway.base.service.BCfgdefFnttrnaddrService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefFnttrnaddrCache")
public class BCfgdefFnttrnaddrCache extends LRUCache{
	@Value("${BCfgdefFnttrnaddrCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefFnttrnaddrCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefFnttrnaddrCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgdefFnttrnaddrService fnttrnaddrService;
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
		List<BCfgdefFnttrnaddr> li = fnttrnaddrService.find(map);
		for(BCfgdefFnttrnaddr fnttrn : li){
			put(fnttrn.getTrnaddrcod(), fnttrn);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	public synchronized BCfgdefFnttrnaddr getNewData(Object key) throws CacheException {
		BCfgdefFnttrnaddr fnttrnaddr = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("trnaddrcod",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefFnttrnaddr> li = fnttrnaddrService.find(map);
		if(li.size()>0){
			fnttrnaddr = li.get(0);
		}
		return fnttrnaddr;
	}
}
