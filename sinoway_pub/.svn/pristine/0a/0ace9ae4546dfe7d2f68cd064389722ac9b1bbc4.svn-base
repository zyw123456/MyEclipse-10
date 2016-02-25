package com.sinoway.base.job;

import java.util.List;
import java.util.TimerTask;

import com.sinoway.common.cache.impl.LRUCache;

public class CacheManager extends TimerTask{
	private List<Object> classNames;
	
	public CacheManager(List<Object> classNames) {
		super();
		this.classNames = classNames;
	}

	@Override
	public void run() {
		if(null != classNames && classNames.size()>0){
			LRUCache cache = null;
			for(Object className:classNames){
				cache = (LRUCache)className;
				if(null != cache){
					System.out.println("刷新缓存:"+cache.getClass().getName());
					if(!cache.isLock()){
						cache.setLock(true);
						cache.reloadAll();
						cache.setLock(false);
					}else{
						System.out.println("缓存仍在刷新,舍弃当前任务状态:"+cache.getClass().getName());
					}
				}else{
					//TODO 运行异常的处理
					System.out.println("未找到cache对象...");
				}
			}
		}
	}

	public List<Object> getClassNames() {
		return classNames;
	}

	public void setClassNames(List<Object> classNames) {
		this.classNames = classNames;
	}
	
}
