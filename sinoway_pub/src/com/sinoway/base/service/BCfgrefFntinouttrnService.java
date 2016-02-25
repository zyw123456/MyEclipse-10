package com.sinoway.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinoway.base.entity.BCfgrefFntinouttrn;
import com.sinoway.common.frame.ServiceImpl;

@Service("bCfgrefFntinouttrnService")
public class BCfgrefFntinouttrnService extends ServiceImpl{
	
	/**
	 * 通过map查询符合条件的数据条目
	 * @param map
	 * @return
	 */
	public int count(Map<String,Object> map){
		return countByObject("BCfgrefFntinouttrn.countByMap", map);
	}
	
	
	/**
	 * 通过对象属性查询数据条目
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public int count(BCfgrefFntinouttrn bCfgrefFntinouttrn){
		return countByObject("BCfgrefFntinouttrn.countByEntity", bCfgrefFntinouttrn);
	}
	
	/**
	 * 批量保存对象
	 * @param list
	 * @throws Exception
	 */
	public void save(List<BCfgrefFntinouttrn> list) throws Exception{
		batchInsert("BCfgrefFntinouttrn.batchInsert", list);
	}
	
	
	
	/**
	 * 保存单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void save(BCfgrefFntinouttrn bCfgrefFntinouttrn){
		insert("BCfgrefFntinouttrn.insertByEntity",bCfgrefFntinouttrn);
	}
	
	/**
	 * 保存对象(传入map)
	 * @param map
	 */
	public void save(Map<String,Object> map){
		insert("BCfgrefFntinouttrn.insertByMap",map);
	}
	
	
	/**
	 * 通过map删除对象
	 * @param map
	 */
	public void del(Map<String,Object> map){
		delete("BCfgrefFntinouttrn.deleteByMap", map);
	}
	
	/**
	 * 通过对象属性删除对象
	 * @param bCfgdefRoleinfo
	 */
	public void del(BCfgrefFntinouttrn bCfgrefFntinouttrn){
		delete("BCfgrefFntinouttrn.deleteByEntity", bCfgrefFntinouttrn);
	}
	
	
	/**
	 * 批量删除数据(ids或beans)
	 * @param list
	 * @throws Exception
	 */
	public void del(List<BCfgrefFntinouttrn> list) throws Exception{
		batchDel("BCfgrefFntinouttrn.batchDelete", list);
	}
	

	/**
	 * 通过对象查询对象
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public List find(BCfgrefFntinouttrn bCfgrefFntinouttrn){
		return (List) query("BCfgrefFntinouttrn.selectByEntity", bCfgrefFntinouttrn);
	}

	/**
	 * 通过map条件查询返回所有的对象
	 * @param map
	 * @return
	 */
	public List find(Map<String,Object> map){
		return query("BCfgrefFntinouttrn.selectByMap", map);
	}
	

	/**
	 * 分页查询(map)
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(Map<String,Object> map,int pageNum,int pageSize){
		return queryByPage("BCfgrefFntinouttrn.selectByEntity", map, pageNum, pageSize);
	}
	
	/**
	 * 分页查询 根据对象属性查找对象集
	 * @param bCfgdefRoleinfo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(BCfgrefFntinouttrn bCfgrefFntinouttrn,int pageNum,int pageSize){
		return queryByPage("BCfgrefFntinouttrn.selectByEntity", bCfgrefFntinouttrn, pageNum, pageSize);
	}
	
	
	/**
	 * 通过对象主键更新单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void update(BCfgrefFntinouttrn bCfgrefFntinouttrn){
		update("BCfgrefFntinouttrn.updateByEntity", bCfgrefFntinouttrn);
	}
	
	/**
	 * 通过map主键更新单个对象
	 * @param map
	 * @throws Exception
	 */
	public void update(Map<String,Object> map) throws Exception{
		update("BCfgrefFntinouttrn.updateByMap", map);
	}
	
	/**
	 * 通过list批量更新数据(未实现)
	 * @param list
	 * @throws Exception
	 */
	public int update(List list) throws Exception{
		//batchUpdate("BCfgrefFntinouttrn.batchUpdate", list);
		return 0;
	}
	
}