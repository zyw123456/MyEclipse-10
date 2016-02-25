package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgrefInoutele;
import com.sinoway.base.service.BCfgrefInouteleService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgrefInouteleCache")
public class BCfgrefInouteleCache extends LRUCache{
	@Value("${BCfgrefInouteleCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgrefInouteleCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgrefInouteleCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgrefInouteleService inouteleService;
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
		List<BCfgrefInoutele> li = inouteleService.find(map);
		for(BCfgrefInoutele inoutele : li){
			put(inoutele.getInelecod()+"_"+inoutele.getOutelecod(), inoutele);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgrefInoutele getNewData(Object key) throws CacheException {
		BCfgrefInoutele inoutele  = null;
		Map<String,Object> map = new HashMap<String,Object>();
		String[] keys = String.valueOf(key).split("_");
		map.put("inelecod",keys[0]);
		map.put("outelecod",keys[1]);
		map.put("sta", "1");
		List<BCfgrefInoutele> li = inouteleService.find(map);
		if(li.size()>0){
			inoutele = li.get(0);
		}
		return inoutele;
	}

}
