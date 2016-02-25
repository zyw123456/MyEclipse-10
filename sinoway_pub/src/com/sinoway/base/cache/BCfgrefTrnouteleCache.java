package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgrefTrnoutele;
import com.sinoway.base.service.BCfgrefTrnouteleService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgrefTrnouteleCache")
public class BCfgrefTrnouteleCache extends LRUCache{
	@Value("${BCfgrefTrnouteleCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgrefTrnouteleCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgrefTrnouteleCache.defaultExpire:0}")
	private long defaultExpire;
	
	@Autowired
	private BCfgrefTrnouteleService trnouteleService;
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
		List<BCfgrefTrnoutele> li = trnouteleService.find(map);
		for(BCfgrefTrnoutele trnoutele : li){
			put(trnoutele.getTrncod()+"_"+trnoutele.getElecod(), trnoutele);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	public synchronized BCfgrefTrnoutele getNewData(Object key) throws CacheException {
		BCfgrefTrnoutele trnoutele  = null;
		Map<String,Object> map = new HashMap<String,Object>();
		String[] keys = String.valueOf(key).split("_");
		map.put("trncod",keys[0]);
		map.put("elecod",keys[1]);
		map.put("sta", "1");
		List<BCfgrefTrnoutele> li = trnouteleService.find(map);
		if(li.size()>0){
			trnoutele = li.get(0);
		}
		return trnoutele;
	}
	
}
