package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgrefCompwal;
import com.sinoway.base.service.BCfgrefCompwalService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgrefCompwalCache")
public class BCfgrefCompwalCache extends LRUCache{
	@Value("${BCfgrefCompwalCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgrefCompwalCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgrefCompwalCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgrefCompwalService compwalService;
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
		List<BCfgrefCompwal> li = compwalService.find(map);
		for(BCfgrefCompwal compwal : li){
			put(compwal.getUsrid()+"_"+compwal.getWalid(), compwal);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgrefCompwal getNewData(Object key) throws CacheException {
		BCfgrefCompwal compwal = null;
		Map<String,Object> map = new HashMap<String,Object>();
		String[] keys = String.valueOf(key).split("_");
		map.put("usrid",keys[0]);
		map.put("walid",keys[1]);
		map.put("sta", "1");
		List<BCfgrefCompwal> li = compwalService.find(map);
		if(li.size()>0){
			compwal = li.get(0);
		}
		return compwal;
	}

}
