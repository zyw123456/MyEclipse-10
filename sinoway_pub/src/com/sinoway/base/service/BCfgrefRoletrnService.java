package com.sinoway.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinoway.base.entity.BCfgrefRoletrn;
import com.sinoway.common.frame.ServiceImpl;

@Service("bCfgrefRoletrnService")
public class BCfgrefRoletrnService extends ServiceImpl{
	
	/**
	 * 通过map查询符合条件的数据条目
	 * @param map
	 * @return
	 */
	public int count(Map<String,Object> map){
		return countByObject("BCfgrefRoletrn.countByMap", map);
	}
	
	
	/**
	 * 通过对象属性查询数据条目
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public int count(BCfgrefRoletrn bCfgrefRoletrn){
		return countByObject("BCfgrefRoletrn.countByEntity", bCfgrefRoletrn);
	}
	
	/**
	 * 批量保存对象
	 * @param list
	 * @throws Exception
	 */
	public void save(List<BCfgrefRoletrn> list) throws Exception{
		batchInsert("BCfgrefRoletrn.batchInsert", list);
	}
	
	
	
	/**
	 * 保存单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void save(BCfgrefRoletrn bCfgrefRoletrn){
		insert("BCfgrefRoletrn.insertByEntity",bCfgrefRoletrn);
	}
	
	/**
	 * 保存对象(传入map)
	 * @param map
	 */
	public void save(Map<String,Object> map){
		insert("BCfgrefRoletrn.insertByMap",map);
	}
	
	
	/**
	 * 通过map删除对象
	 * @param map
	 */
	public void del(Map<String,Object> map){
		delete("BCfgrefRoletrn.deleteByMap", map);
	}
	
	/**
	 * 通过对象属性删除对象
	 * @param bCfgdefRoleinfo
	 */
	public void del(BCfgrefRoletrn bCfgrefRoletrn){
		delete("BCfgrefRoletrn.deleteByEntity", bCfgrefRoletrn);
	}
	
	
	/**
	 * 批量删除数据(ids或beans)
	 * @param list
	 * @throws Exception
	 */
	public void del(List<BCfgrefRoletrn> list) throws Exception{
		batchDel("BCfgrefRoletrn.batchDelete", list);
	}
	

	/**
	 * 通过对象查询对象
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public List find(BCfgrefRoletrn bCfgrefRoletrn){
		return (List) query("BCfgrefRoletrn.selectByEntity", bCfgrefRoletrn);
	}

	/**
	 * 通过map条件查询返回所有的对象
	 * @param map
	 * @return
	 */
	public List find(Map<String,Object> map){
		return query("BCfgrefRoletrn.selectByMap", map);
	}
	

	/**
	 * 分页查询(map)
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(Map<String,Object> map,int pageNum,int pageSize){
		return queryByPage("BCfgrefRoletrn.selectByEntity", map, pageNum, pageSize);
	}
	
	/**
	 * 分页查询 根据对象属性查找对象集
	 * @param bCfgdefRoleinfo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(BCfgrefRoletrn bCfgrefRoletrn,int pageNum,int pageSize){
		return queryByPage("BCfgrefRoletrn.selectByEntity", bCfgrefRoletrn, pageNum, pageSize);
	}
	
	
	/**
	 * 通过对象主键更新单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void update(BCfgrefRoletrn bCfgrefRoletrn){
		update("BCfgrefRoletrn.updateByEntity", bCfgrefRoletrn);
	}
	
	/**
	 * 通过map主键更新单个对象
	 * @param map
	 * @throws Exception
	 */
	public void update(Map<String,Object> map) throws Exception{
		update("BCfgrefRoletrn.updateByMap", map);
	}
	
	/**
	 * 通过list批量更新数据(未实现)
	 * @param list
	 * @throws Exception
	 */
	public int update(List list) throws Exception{
		//batchUpdate("BCfgrefRoletrn.batchUpdate", list);
		return 0;
	}
	
}
