package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgrefFntinouttrn;
import com.sinoway.base.service.BCfgrefFntinouttrnService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgrefFntinouttrnCache")
public class BCfgrefFntinouttrnCache extends LRUCache{
	@Value("${BCfgrefFntinouttrnCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgrefFntinouttrnCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgrefFntinouttrnCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgrefFntinouttrnService fntinouttrnService;
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
		List<BCfgrefFntinouttrn> li = fntinouttrnService.find(map);
		for(BCfgrefFntinouttrn fntinouttrn : li){
			put(fntinouttrn.getIntrncod()+"_"+fntinouttrn.getOuttrncod(), fntinouttrn);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgrefFntinouttrn getNewData(Object key) throws CacheException {
		BCfgrefFntinouttrn fntinouttrn = null;
		Map<String,Object> map = new HashMap<String,Object>();
		String[] keys = String.valueOf(key).split("_");
		map.put("intrncod",keys[0]);
		map.put("outtrncod",keys[1]);
		map.put("sta", "1");
		List<BCfgrefFntinouttrn> li = fntinouttrnService.find(map);
		if(li.size()>0){
			fntinouttrn = li.get(0);
		}
		return fntinouttrn;
	}

}
