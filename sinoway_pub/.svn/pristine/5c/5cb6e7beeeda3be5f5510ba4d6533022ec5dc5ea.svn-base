package com.sinoway.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinoway.base.entity.BCfgdefExpinfo;
import com.sinoway.common.frame.ServiceImpl;

@Service("bCfgdefExpinfoService")
public class BCfgdefExpinfoService extends ServiceImpl{
	
	/**
	 * 通过map查询符合条件的数据条目
	 * @param map
	 * @return
	 */
	public int count(Map<String,Object> map){
		return countByObject("BCfgdefExpinfo.countByMap", map);
	}
	
	
	/**
	 * 通过对象属性查询数据条目
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public int count(BCfgdefExpinfo bCfgdefExpinfo){
		return countByObject("BCfgdefExpinfo.countByEntity", bCfgdefExpinfo);
	}
	
	/**
	 * 批量保存对象
	 * @param list
	 * @throws Exception
	 */
	public void save(List<BCfgdefExpinfo> list) throws Exception{
		batchInsert("BCfgdefExpinfo.batchInsert", list);
	}
	
	
	
	/**
	 * 保存单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void save(BCfgdefExpinfo bCfgdefExpinfo){
		insert("BCfgdefExpinfo.insertByEntity",bCfgdefExpinfo);
	}
	
	/**
	 * 保存对象(传入map)
	 * @param map
	 */
	public void save(Map<String,Object> map){
		insert("BCfgdefExpinfo.insertByMap",map);
	}
	
	
	/**
	 * 通过map删除对象
	 * @param map
	 */
	public void del(Map<String,Object> map){
		delete("BCfgdefExpinfo.deleteByMap", map);
	}
	
	/**
	 * 通过对象属性删除对象
	 * @param bCfgdefRoleinfo
	 */
	public void del(BCfgdefExpinfo bCfgdefExpinfo){
		delete("BCfgdefExpinfo.deleteByEntity", bCfgdefExpinfo);
	}
	
	
	/**
	 * 批量删除数据(ids或beans)
	 * @param list
	 * @throws Exception
	 */
	public void del(List<BCfgdefExpinfo> list) throws Exception{
		batchDel("BCfgdefExpinfo.batchDelete", list);
	}
	

	/**
	 * 通过对象查询对象
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public List find(BCfgdefExpinfo bCfgdefExpinfo){
		return (List) query("BCfgdefExpinfo.selectByEntity", bCfgdefExpinfo);
	}

	/**
	 * 通过map条件查询返回所有的对象
	 * @param map
	 * @return
	 */
	public List find(Map<String,Object> map){
		return query("BCfgdefExpinfo.selectByMap", map);
	}
	

	/**
	 * 分页查询(map)
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(Map<String,Object> map,int pageNum,int pageSize){
		return queryByPage("BCfgdefExpinfo.selectByEntity", map, pageNum, pageSize);
	}
	
	/**
	 * 分页查询 根据对象属性查找对象集
	 * @param bCfgdefRoleinfo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(BCfgdefExpinfo bCfgdefExpinfo,int pageNum,int pageSize){
		return queryByPage("BCfgdefExpinfo.selectByEntity", bCfgdefExpinfo, pageNum, pageSize);
	}
	
	
	/**
	 * 通过对象主键更新单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void update(BCfgdefExpinfo bCfgdefExpinfo){
		update("BCfgdefExpinfo.updateByEntity", bCfgdefExpinfo);
	}
	
	/**
	 * 通过map主键更新单个对象
	 * @param map
	 * @throws Exception
	 */
	public void update(Map<String,Object> map) throws Exception{
		update("BCfgdefExpinfo.updateByMap", map);
	}
	
	/**
	 * 通过list批量更新数据(未实现)
	 * @param list
	 * @throws Exception
	 */
	public int update(List list) throws Exception{
		//batchUpdate("BCfgdefExpinfo.batchUpdate", list);
		return 0;
	}
	
}
