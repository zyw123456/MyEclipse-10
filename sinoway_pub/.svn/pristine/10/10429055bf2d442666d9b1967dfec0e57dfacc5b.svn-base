package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefSysparam;
import com.sinoway.base.service.BCfgdefSysparamService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefSysparamCache")
public class BCfgdefSysparamCache extends LRUCache{
	@Value("${BCfgdefSysparamCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefSysparamCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefSysparamCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgdefSysparamService sysparamService;
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
		List<BCfgdefSysparam> li = sysparamService.find(map);
		for(BCfgdefSysparam sysparam : li){
			put(sysparam.getParamcod(), sysparam);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefSysparam getNewData(Object key) throws CacheException {
		BCfgdefSysparam sysparam = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("paramcod",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefSysparam> li = sysparamService.find(map);
		if(li.size()>0){
			sysparam = li.get(0);
		}
		return sysparam;
	}

}
