package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgrefTrninele;
import com.sinoway.base.service.BCfgrefTrnineleService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgrefTrnineleCache")
public class BCfgrefTrnineleCache extends LRUCache{
	@Value("${BCfgrefTrnineleCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgrefTrnineleCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgrefTrnineleCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgrefTrnineleService trnineleService;
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
		List<BCfgrefTrninele> li = trnineleService.find(map);
		for(BCfgrefTrninele trninele : li){
			put(trninele.getTrncod()+"_"+trninele.getElecod(), trninele);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgrefTrninele getNewData(Object key) throws CacheException {
		BCfgrefTrninele trninele = null;
		Map<String,Object> map = new HashMap<String,Object>();
		String[] keys = String.valueOf(key).split("_");
		map.put("trncod",keys[0]);
		map.put("elecod",keys[1]);
		map.put("sta", "1");
		List<BCfgrefTrninele> li = trnineleService.find(map);
		if(li.size()>0){
			trninele = li.get(0);
		}
		return trninele;
	}
	
}
