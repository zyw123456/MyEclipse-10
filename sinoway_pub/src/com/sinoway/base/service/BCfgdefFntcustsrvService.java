package com.sinoway.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinoway.base.entity.BCfgdefFntcustsrv;
import com.sinoway.common.frame.ServiceImpl;

@Service("bCfgdefFntcustsrvService")
public class BCfgdefFntcustsrvService extends ServiceImpl{
	
	/**
	 * 通过map查询符合条件的数据条目
	 * @param map
	 * @return
	 */
	public int count(Map<String,Object> map){
		return countByObject("BCfgdefFntcustsrv.countByMap", map);
	}
	
	
	/**
	 * 通过对象属性查询数据条目
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public int count(BCfgdefFntcustsrv bCfgdefFntcustsrv){
		return countByObject("BCfgdefFntcustsrv.countByEntity", bCfgdefFntcustsrv);
	}
	
	/**
	 * 批量保存对象
	 * @param list
	 * @throws Exception
	 */
	public void save(List<BCfgdefFntcustsrv> list) throws Exception{
		batchInsert("BCfgdefFntcustsrv.batchInsert", list);
	}
	
	
	
	/**
	 * 保存单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void save(BCfgdefFntcustsrv bCfgdefFntcustsrv){
		insert("BCfgdefFntcustsrv.insertByEntity",bCfgdefFntcustsrv);
	}
	
	/**
	 * 保存对象(传入map)
	 * @param map
	 */
	public void save(Map<String,Object> map){
		insert("BCfgdefFntcustsrv.insertByMap",map);
	}
	
	
	/**
	 * 通过map删除对象
	 * @param map
	 */
	public void del(Map<String,Object> map){
		delete("BCfgdefFntcustsrv.deleteByMap", map);
	}
	
	/**
	 * 通过对象属性删除对象
	 * @param bCfgdefRoleinfo
	 */
	public void del(BCfgdefFntcustsrv bCfgdefFntcustsrv){
		delete("BCfgdefFntcustsrv.deleteByEntity", bCfgdefFntcustsrv);
	}
	
	
	/**
	 * 批量删除数据(ids或beans)
	 * @param list
	 * @throws Exception
	 */
	public void del(List<BCfgdefFntcustsrv> list) throws Exception{
		batchDel("BCfgdefFntcustsrv.batchDelete", list);
	}
	

	/**
	 * 通过对象查询对象
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public List find(BCfgdefFntcustsrv bCfgdefFntcustsrv){
		return (List) query("BCfgdefFntcustsrv.selectByEntity", bCfgdefFntcustsrv);
	}

	/**
	 * 通过map条件查询返回所有的对象
	 * @param map
	 * @return
	 */
	public List find(Map<String,Object> map){
		return query("BCfgdefFntcustsrv.selectByMap", map);
	}
	

	/**
	 * 分页查询(map)
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(Map<String,Object> map,int pageNum,int pageSize){
		return queryByPage("BCfgdefFntcustsrv.selectByEntity", map, pageNum, pageSize);
	}
	
	/**
	 * 分页查询 根据对象属性查找对象集
	 * @param bCfgdefRoleinfo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(BCfgdefFntcustsrv bCfgdefFntcustsrv,int pageNum,int pageSize){
		return queryByPage("BCfgdefFntcustsrv.selectByEntity", bCfgdefFntcustsrv, pageNum, pageSize);
	}
	
	
	/**
	 * 通过对象主键更新单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void update(BCfgdefFntcustsrv bCfgdefFntcustsrv){
		update("BCfgdefFntcustsrv.updateByEntity", bCfgdefFntcustsrv);
	}
	
	/**
	 * 通过map主键更新单个对象
	 * @param map
	 * @throws Exception
	 */
	public void update(Map<String,Object> map) throws Exception{
		update("BCfgdefFntcustsrv.updateByMap", map);
	}
	
	/**
	 * 通过list批量更新数据(未实现)
	 * @param list
	 * @throws Exception
	 */
	public int update(List list) throws Exception{
		//batchUpdate("BCfgdefFntcustsrv.batchUpdate", list);
		return 0;
	}
	
}
