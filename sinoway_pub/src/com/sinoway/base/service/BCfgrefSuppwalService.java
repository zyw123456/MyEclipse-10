package com.sinoway.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinoway.base.entity.BCfgrefSuppwal;
import com.sinoway.common.frame.ServiceImpl;

@Service("bCfgrefSuppwalService")
public class BCfgrefSuppwalService extends ServiceImpl{
	
	/**
	 * 通过map查询符合条件的数据条目
	 * @param map
	 * @return
	 */
	public int count(Map<String,Object> map){
		return countByObject("BCfgrefSuppwal.countByMap", map);
	}
	
	
	/**
	 * 通过对象属性查询数据条目
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public int count(BCfgrefSuppwal bCfgrefSuppwal){
		return countByObject("BCfgrefSuppwal.countByEntity", bCfgrefSuppwal);
	}
	
	/**
	 * 批量保存对象
	 * @param list
	 * @throws Exception
	 */
	public void save(List<BCfgrefSuppwal> list) throws Exception{
		batchInsert("BCfgrefSuppwal.batchInsert", list);
	}
	
	
	
	/**
	 * 保存单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void save(BCfgrefSuppwal bCfgrefSuppwal){
		insert("BCfgrefSuppwal.insertByEntity",bCfgrefSuppwal);
	}
	
	/**
	 * 保存对象(传入map)
	 * @param map
	 */
	public void save(Map<String,Object> map){
		insert("BCfgrefSuppwal.insertByMap",map);
	}
	
	
	/**
	 * 通过map删除对象
	 * @param map
	 */
	public void del(Map<String,Object> map){
		delete("BCfgrefSuppwal.deleteByMap", map);
	}
	
	/**
	 * 通过对象属性删除对象
	 * @param bCfgdefRoleinfo
	 */
	public void del(BCfgrefSuppwal bCfgrefSuppwal){
		delete("BCfgrefSuppwal.deleteByEntity", bCfgrefSuppwal);
	}
	
	
	/**
	 * 批量删除数据(ids或beans)
	 * @param list
	 * @throws Exception
	 */
	public void del(List<BCfgrefSuppwal> list) throws Exception{
		batchDel("BCfgrefSuppwal.batchDelete", list);
	}
	

	/**
	 * 通过对象查询对象
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public List find(BCfgrefSuppwal bCfgrefSuppwal){
		return (List) query("BCfgrefSuppwal.selectByEntity", bCfgrefSuppwal);
	}

	/**
	 * 通过map条件查询返回所有的对象
	 * @param map
	 * @return
	 */
	public List find(Map<String,Object> map){
		return query("BCfgrefSuppwal.selectByMap", map);
	}
	

	/**
	 * 分页查询(map)
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(Map<String,Object> map,int pageNum,int pageSize){
		return queryByPage("BCfgrefSuppwal.selectByEntity", map, pageNum, pageSize);
	}
	
	/**
	 * 分页查询 根据对象属性查找对象集
	 * @param bCfgdefRoleinfo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(BCfgrefSuppwal bCfgrefSuppwal,int pageNum,int pageSize){
		return queryByPage("BCfgrefSuppwal.selectByEntity", bCfgrefSuppwal, pageNum, pageSize);
	}
	
	
	/**
	 * 通过对象主键更新单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void update(BCfgrefSuppwal bCfgrefSuppwal){
		update("BCfgrefSuppwal.updateByEntity", bCfgrefSuppwal);
	}
	
	/**
	 * 通过map主键更新单个对象
	 * @param map
	 * @throws Exception
	 */
	public void update(Map<String,Object> map) throws Exception{
		update("BCfgrefSuppwal.updateByMap", map);
	}
	
	/**
	 * 通过list批量更新数据(未实现)
	 * @param list
	 * @throws Exception
	 */
	public int update(List list) throws Exception{
		//batchUpdate("BCfgrefSuppwal.batchUpdate", list);
		return 0;
	}
	
}
