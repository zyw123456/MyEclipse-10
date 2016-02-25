package com.sinoway.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinoway.base.entity.BCfgrefInoutele;
import com.sinoway.common.frame.ServiceImpl;

@Service("bCfgrefInouteleService")
public class BCfgrefInouteleService extends ServiceImpl{
	
	/**
	 * 通过map查询符合条件的数据条目
	 * @param map
	 * @return
	 */
	public int count(Map<String,Object> map){
		return countByObject("BCfgrefInoutele.countByMap", map);
	}
	
	
	/**
	 * 通过对象属性查询数据条目
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public int count(BCfgrefInoutele bCfgrefInoutele){
		return countByObject("BCfgrefInoutele.countByEntity", bCfgrefInoutele);
	}
	
	/**
	 * 批量保存对象
	 * @param list
	 * @throws Exception
	 */
	public void save(List<BCfgrefInoutele> list) throws Exception{
		batchInsert("BCfgrefInoutele.batchInsert", list);
	}
	
	
	
	/**
	 * 保存单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void save(BCfgrefInoutele bCfgrefInoutele){
		insert("BCfgrefInoutele.insertByEntity",bCfgrefInoutele);
	}
	
	/**
	 * 保存对象(传入map)
	 * @param map
	 */
	public void save(Map<String,Object> map){
		insert("BCfgrefInoutele.insertByMap",map);
	}
	
	
	/**
	 * 通过map删除对象
	 * @param map
	 */
	public void del(Map<String,Object> map){
		delete("BCfgrefInoutele.deleteByMap", map);
	}
	
	/**
	 * 通过对象属性删除对象
	 * @param bCfgdefRoleinfo
	 */
	public void del(BCfgrefInoutele bCfgrefInoutele){
		delete("BCfgrefInoutele.deleteByEntity", bCfgrefInoutele);
	}
	
	
	/**
	 * 批量删除数据(ids或beans)
	 * @param list
	 * @throws Exception
	 */
	public void del(List<BCfgrefInoutele> list) throws Exception{
		batchDel("BCfgrefInoutele.batchDelete", list);
	}
	

	/**
	 * 通过对象查询对象
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public List find(BCfgrefInoutele bCfgrefInoutele){
		return (List) query("BCfgrefInoutele.selectByEntity", bCfgrefInoutele);
	}

	/**
	 * 通过map条件查询返回所有的对象
	 * @param map
	 * @return
	 */
	public List find(Map<String,Object> map){
		return query("BCfgrefInoutele.selectByMap", map);
	}
	

	/**
	 * 分页查询(map)
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(Map<String,Object> map,int pageNum,int pageSize){
		return queryByPage("BCfgrefInoutele.selectByEntity", map, pageNum, pageSize);
	}
	
	/**
	 * 分页查询 根据对象属性查找对象集
	 * @param bCfgdefRoleinfo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(BCfgrefInoutele bCfgrefInoutele,int pageNum,int pageSize){
		return queryByPage("BCfgrefInoutele.selectByEntity", bCfgrefInoutele, pageNum, pageSize);
	}
	
	
	/**
	 * 通过对象主键更新单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void update(BCfgrefInoutele bCfgrefInoutele){
		update("BCfgrefInoutele.updateByEntity", bCfgrefInoutele);
	}
	
	/**
	 * 通过map主键更新单个对象
	 * @param map
	 * @throws Exception
	 */
	public void update(Map<String,Object> map) throws Exception{
		update("BCfgrefInoutele.updateByMap", map);
	}
	
	/**
	 * 通过list批量更新数据(未实现)
	 * @param list
	 * @throws Exception
	 */
	public int update(List list) throws Exception{
		//batchUpdate("BCfgrefInoutele.batchUpdate", list);
		return 0;
	}
	
}