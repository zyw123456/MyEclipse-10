package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgrefSuppwal;
import com.sinoway.base.service.BCfgrefSuppwalService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgrefSuppwalCache")
public class BCfgrefSuppwalCache extends LRUCache{
	@Value("${BCfgrefSuppwalCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgrefSuppwalCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgrefSuppwalCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgrefSuppwalService suppwalService;
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
		List<BCfgrefSuppwal> li = suppwalService.find(map);
		for(BCfgrefSuppwal suppwal : li){
			put(suppwal.getUsrid()+"_"+suppwal.getWalid(), suppwal);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgrefSuppwal getNewData(Object key) throws CacheException {
		BCfgrefSuppwal suppwal = null;
		Map<String,Object> map = new HashMap<String,Object>();
		String[] keys = String.valueOf(key).split("_");
		map.put("usrid",keys[0]);
		map.put("walid",keys[1]);
		map.put("sta", "1");
		List<BCfgrefSuppwal> li = suppwalService.find(map);
		if(li.size()>0){
			suppwal = li.get(0);
		}
		return suppwal;
	}

}
