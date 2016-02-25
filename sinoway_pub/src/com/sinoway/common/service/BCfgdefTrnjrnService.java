package com.sinoway.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinoway.common.entity.BCfgdefTrnjrn;
import com.sinoway.common.frame.ServiceImpl;

@Service("bCfgdefTrnjrnService")
public class BCfgdefTrnjrnService extends ServiceImpl{
	
	/**
	 * 通过map查询符合条件的数据条目
	 * @param map
	 * @return
	 */
	public int count(Map<String,Object> map){
		return countByObject("BCfgdefTrnjrn.countByMap", map);
	}
	
	
	/**
	 * 通过对象属性查询数据条目
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public int count(BCfgdefTrnjrn bCfgdefInele){
		return countByObject("BCfgdefTrnjrn.countByEntity", bCfgdefInele);
	}
	
	/**
	 * 批量保存对象
	 * @param list
	 * @throws Exception
	 */
	public void save(List<BCfgdefTrnjrn> list) throws Exception{
		batchInsert("BCfgdefTrnjrn.batchInsert", list);
	}
	
	
	
	/**
	 * 保存单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void save(BCfgdefTrnjrn bCfgdefInele){
		insert("BCfgdefTrnjrn.insertByEntity",bCfgdefInele);
	}
	
	/**
	 * 保存对象(传入map)
	 * @param map
	 */
	public void save(Map<String,Object> map){
		insert("BCfgdefTrnjrn.insertByMap",map);
	}
	
	
	/**
	 * 通过map删除对象
	 * @param map
	 */
	public void del(Map<String,Object> map){
		delete("BCfgdefTrnjrn.deleteByMap", map);
	}
	
	/**
	 * 通过对象属性删除对象
	 * @param bCfgdefRoleinfo
	 */
	public void del(BCfgdefTrnjrn bCfgdefTrnjrn){
		delete("BCfgdefTrnjrn.deleteByEntity", bCfgdefTrnjrn);
	}
	
	
	/**
	 * 批量删除数据(ids或beans)
	 * @param list
	 * @throws Exception
	 */
	public void del(List<BCfgdefTrnjrn> list) throws Exception{
		batchDel("BCfgdefTrnjrn.batchDelete", list);
	}
	

	/**
	 * 通过对象查询对象
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public List find(BCfgdefTrnjrn bCfgdefTrnjrn){
		return (List) query("BCfgdefTrnjrn.selectByEntity", bCfgdefTrnjrn);
	}

	/**
	 * 通过map条件查询返回所有的对象
	 * @param map
	 * @return
	 */
	public List find(Map<String,Object> map){
		return query("BCfgdefTrnjrn.selectByMap", map);
	}
	

	/**
	 * 分页查询(map)
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(Map<String,Object> map,int pageNum,int pageSize){
		return queryByPage("BCfgdefTrnjrn.selectByEntity", map, pageNum, pageSize);
	}
	
	/**
	 * 分页查询 根据对象属性查找对象集
	 * @param bCfgdefRoleinfo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(BCfgdefTrnjrn bCfgdefTrnjrn,int pageNum,int pageSize){
		return queryByPage("BCfgdefTrnjrn.selectByEntity", bCfgdefTrnjrn, pageNum, pageSize);
	}
	
	
	/**
	 * 通过对象主键更新单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void update(BCfgdefTrnjrn bCfgdefTrnjrn){
		update("BCfgdefTrnjrn.updateByEntity", bCfgdefTrnjrn);
	}
	
	
	/**
	 * 通过对象主键以及条件更新单个对象
	 * @param bCfgdefRoleinfo
	 */
	public int updateByConditions(BCfgdefTrnjrn bCfgdefTrnjrn,String jrnval){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("trnjrn", bCfgdefTrnjrn);
		map.put("jrnval", jrnval);
		return update("BCfgdefTrnjrn.updateByEntityProperties", map);
	}
	
	/**
	 * 通过map主键更新单个对象
	 * @param map
	 * @throws Exception
	 */
	public void update(Map<String,Object> map) throws Exception{
		update("BCfgdefTrnjrn.updateByMap", map);
	}
	
	/**
	 * 通过list批量更新数据(未实现)
	 * @param list
	 * @throws Exception
	 */
	public int update(List list) throws Exception{
		//batchUpdate("BCfgdefTrnjrn.batchUpdate", list);
		return 0;
	}
	
}
