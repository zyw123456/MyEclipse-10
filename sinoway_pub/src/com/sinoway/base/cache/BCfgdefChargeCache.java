package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.struts2.views.freemarker.tags.SetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefCharge;
import com.sinoway.base.service.BCfgdefChargeService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefChargeCache")
public class BCfgdefChargeCache extends LRUCache{
	@Value("${BCfgdefChargeCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefChargeCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefChargeCache.defaultExpire:0}")
	private long defaultExpire;

	@Autowired
	private BCfgdefChargeService chargeService;
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
		List<BCfgdefCharge> li = chargeService.find(map);
		for(BCfgdefCharge charge : li){
			put(charge.getChargecod(), charge);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefCharge getNewData(Object key) throws CacheException {
		BCfgdefCharge charge = null; 
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("chargecod",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefCharge> li = chargeService.find(map);
		if(li.size()>0){
			charge = li.get(0);
		}
		return charge;
	}

}
