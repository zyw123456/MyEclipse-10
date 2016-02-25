package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgrefPrdtrnchnl;
import com.sinoway.base.service.BCfgrefPrdtrnchnlService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgrefPrdtrnchnlCache")
public class BCfgrefPrdtrnchnlCache extends LRUCache{
	@Value("${BCfgrefPrdtrnchnlCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgrefPrdtrnchnlCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgrefPrdtrnchnlCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgrefPrdtrnchnlService prdtrnchnlService;
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
		List<BCfgrefPrdtrnchnl> li = prdtrnchnlService.find(map);
		for(BCfgrefPrdtrnchnl prdtrnchnl : li){
			put(prdtrnchnl.getPrdtrncod()+"_"+prdtrnchnl.getMtatrncod(), prdtrnchnl);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgrefPrdtrnchnl getNewData(Object key) throws CacheException {
		BCfgrefPrdtrnchnl prdtrnchnl = null;
		Map<String,Object> map = new HashMap<String,Object>();
		String[] keys = String.valueOf(key).split("_");
		map.put("prdtrncod",keys[0]);
		map.put("mtatrncod",keys[1]);
		map.put("sta", "1");
		List<BCfgrefPrdtrnchnl> li = prdtrnchnlService.find(map);
		if(li.size()>0){
			prdtrnchnl = li.get(0);
		}
		return prdtrnchnl;
	}

}
