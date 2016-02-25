package com.sinoway.app.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.sinoway.common.util.Constant;
import com.yzj.wf.common.cache.Cache;
import com.yzj.wf.common.cache.CacheEnum;
import com.yzj.wf.common.cache.CacheFactory;
import com.yzj.wf.plat.entity.WfCfgdefPrdinfo;

public class CacheTestAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8820028048106976426L;

	public String excute(){
		//获得缓存 方式一
		//PrdInfoCache cache1 = PrdInfoCache.getInstance();
		//获得缓存 方式二
		Cache cache = CacheFactory.getCacheInstance(CacheEnum.PrdInfoCache.getValue());
		//获得所有的实体
		List<WfCfgdefPrdinfo> prdinfos= (List<WfCfgdefPrdinfo>) cache.getRealValues();
		//获得复制的缓存对象(对该对象的修改不会更新缓存中内容)
		String prdcod = "001";
		WfCfgdefPrdinfo  prdinfo = (WfCfgdefPrdinfo) cache.getCloneObject(prdcod);
		//获得缓存对象(对该对象的修改会更新缓存中的内容)
		WfCfgdefPrdinfo  prdinfo2 = (WfCfgdefPrdinfo) cache.getRealObject(prdcod);
		//向缓存中加入对象
		WfCfgdefPrdinfo  prdinfo3 = new WfCfgdefPrdinfo();
		cache.putRealObject(prdcod,prdinfo3);//查看缓存的key
		//移除缓存中对象
		WfCfgdefPrdinfo  prdinfo4 = new WfCfgdefPrdinfo();
		cache.removeRealObject(prdinfo4);
		//重新加载缓存
		cache.setAvailable(false);//下次从cache中获取对象时,会重新load数据
		//清除缓存中所有内容
		cache.clear();
		//
		return SUCCESS;
	}
}
