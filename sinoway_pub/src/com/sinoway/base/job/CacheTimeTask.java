package com.sinoway.base.job;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.sinoway.common.cache.impl.ICacheMapper;

@Component("cacheTimeTask")
public class CacheTimeTask {
	@Autowired
	private ICacheMapper cacheMapper;
	@PostConstruct
	public void execute(){
		try {
			System.out.println("等待系统加载信息...");
			Thread.sleep(2);
			System.out.println("开始系统更新缓存...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<Object,Long> map = cacheMapper.getCacheMap();
		Map<Long,List<Object>> timMap = new HashMap<Long,List<Object>>();
		List<Object> li = null;
		for(Object key:map.keySet()){
			if(!timMap.containsKey(map.get(key))){
				li = new ArrayList<Object>();
				li.add(key);
				timMap.put(map.get(key),li);
			}else{
				li = timMap.get(map.get(key));
				li.add(key);
				timMap.put(map.get(key),li);
			}
		}
		//按时间分类,分为几个timer
		Timer timer = null;
		long delay = 0;
		long period = 0;
		for(long i:timMap.keySet()){
			if(i == 0){
				//永不刷新--修改为凌晨刷新
				period = 86400000;//24小时
				delay = 86400000 - DateUtils.getFragmentInMilliseconds(Calendar.getInstance(), Calendar.DATE);
			}else{
				period = i*60*1000;
				delay = 0;
			}
			timer = new Timer();
	        timer.schedule(new CacheManager(timMap.get(i)),delay,period);
		}
	}
	

}
