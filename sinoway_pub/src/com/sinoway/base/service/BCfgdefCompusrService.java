package com.sinoway.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinoway.base.entity.BCfgdefCompusr;
import com.sinoway.common.frame.ServiceImpl;

@Service("bCfgdefCompusrService")
public class BCfgdefCompusrService extends ServiceImpl{
	
	/**
	 * 通过map查询符合条件的数据条目
	 * @param map
	 * @return
	 */
	public int count(Map<String,Object> map){
		return countByObject("BCfgdefCompusr.countByMap", map);
	}
	
	
	/**
	 * 通过对象属性查询数据条目
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public int count(BCfgdefCompusr bCfgdefCompusr){
		return countByObject("BCfgdefCompusr.countByEntity", bCfgdefCompusr);
	}
	
	/**
	 * 批量保存对象
	 * @param list
	 * @throws Exception
	 */
	public void save(List<BCfgdefCompusr> list) throws Exception{
		batchInsert("BCfgdefCompusr.batchInsert", list);
	}
	
	
	
	/**
	 * 保存单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void save(BCfgdefCompusr bCfgdefCompusr){
		insert("BCfgdefCompusr.insertByEntity",bCfgdefCompusr);
	}
	
	/**
	 * 保存对象(传入map)
	 * @param map
	 */
	public void save(Map<String,Object> map){
		insert("BCfgdefCompusr.insertByMap",map);
	}
	
	
	/**
	 * 通过map删除对象
	 * @param map
	 */
	public void del(Map<String,Object> map){
		delete("BCfgdefCompusr.deleteByMap", map);
	}
	
	/**
	 * 通过对象属性删除对象
	 * @param bCfgdefRoleinfo
	 */
	public void del(BCfgdefCompusr bCfgdefCompusr){
		delete("BCfgdefCompusr.deleteByEntity", bCfgdefCompusr);
	}
	
	
	/**
	 * 批量删除数据(ids或beans)
	 * @param list
	 * @throws Exception
	 */
	public void del(List<BCfgdefCompusr> list) throws Exception{
		batchDel("BCfgdefCompusr.batchDelete", list);
	}
	

	/**
	 * 通过对象查询对象
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public List find(BCfgdefCompusr bCfgdefCompusr){
		return (List) query("BCfgdefCompusr.selectByEntity", bCfgdefCompusr);
	}

	/**
	 * 通过map条件查询返回所有的对象
	 * @param map
	 * @return
	 */
	public List find(Map<String,Object> map){
		return query("BCfgdefCompusr.selectByMap", map);
	}
	

	/**
	 * 分页查询(map)
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(Map<String,Object> map,int pageNum,int pageSize){
		return queryByPage("BCfgdefCompusr.selectByEntity", map, pageNum, pageSize);
	}
	
	/**
	 * 分页查询 根据对象属性查找对象集
	 * @param bCfgdefRoleinfo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(BCfgdefCompusr bCfgdefCompusr,int pageNum,int pageSize){
		return queryByPage("BCfgdefCompusr.selectByEntity", bCfgdefCompusr, pageNum, pageSize);
	}
	
	
	/**
	 * 通过对象主键更新单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void update(BCfgdefCompusr bCfgdefCompusr){
		update("BCfgdefCompusr.updateByEntity", bCfgdefCompusr);
	}
	
	/**
	 * 通过map主键更新单个对象
	 * @param map
	 * @throws Exception
	 */
	public void update(Map<String,Object> map) throws Exception{
		update("BCfgdefCompusr.updateByMap", map);
	}
	
	/**
	 * 通过list批量更新数据(未实现)
	 * @param list
	 * @throws Exception
	 */
	public int update(List list) throws Exception{
		//batchUpdate("BCfgdefCompusr.batchUpdate", list);
		return 0;
	}
	
}
