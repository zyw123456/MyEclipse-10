package com.sinoway.mcp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinoway.mcp.entity.FDatExptrnlog;
import com.sinoway.common.frame.ServiceImpl;

@Service("fDatExptrnlogService")
public class FDatExptrnlogService extends ServiceImpl{
	
	/**
	 * 通过map查询符合条件的数据条目
	 * @param map
	 * @return
	 */
	public int count(Map<String,Object> map){
		return countByObject("FDatExptrnlog.countByMap", map);
	}
	
	
	/**
	 * 通过对象属性查询数据条目
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public int count(FDatExptrnlog fDatExptrnlog){
		return countByObject("FDatExptrnlog.countByEntity", fDatExptrnlog);
	}
	
	/**
	 * 批量保存对象
	 * @param list
	 * @throws Exception
	 */
	public void save(List<FDatExptrnlog> list) throws Exception{
		batchInsert("FDatExptrnlog.batchInsert", list);
	}
	
	
	
	/**
	 * 保存单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void save(FDatExptrnlog fDatExptrnlog){
		insert("FDatExptrnlog.insertByEntity",fDatExptrnlog);
	}
	
	/**
	 * 保存对象(传入map)
	 * @param map
	 */
	public void save(Map<String,Object> map){
		insert("FDatExptrnlog.insertByMap",map);
	}
	
	
	/**
	 * 通过map删除对象
	 * @param map
	 */
	public void del(Map<String,Object> map){
		delete("FDatExptrnlog.deleteByMap", map);
	}
	
	/**
	 * 通过对象属性删除对象
	 * @param bCfgdefRoleinfo
	 */
	public void del(FDatExptrnlog fDatExptrnlog){
		delete("FDatExptrnlog.deleteByEntity", fDatExptrnlog);
	}
	
	
	/**
	 * 批量删除数据(ids或beans)
	 * @param list
	 * @throws Exception
	 */
	public void del(List<FDatExptrnlog> list) throws Exception{
		batchDel("FDatExptrnlog.batchDelete", list);
	}
	

	/**
	 * 通过对象查询对象
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public List find(FDatExptrnlog fDatExptrnlog){
		return  query("FDatExptrnlog.selectByEntity", fDatExptrnlog);
	}

	/**
	 * 通过map条件查询返回所有的对象
	 * @param map
	 * @return
	 */
	public List find(Map<String,Object> map){
		return query("FDatExptrnlog.selectByMap", map);
	}
	

	/**
	 * 分页查询(map)
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(Map<String,Object> map,int pageNum,int pageSize){
		return queryByPage("FDatExptrnlog.selectByEntity", map, pageNum, pageSize);
	}
	
	/**
	 * 分页查询 根据对象属性查找对象集
	 * @param bCfgdefRoleinfo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(FDatExptrnlog fDatExptrnlog,int pageNum,int pageSize){
		return queryByPage("FDatExptrnlog.selectByEntity", fDatExptrnlog, pageNum, pageSize);
	}
	
	
	/**
	 * 通过对象主键更新单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void update(FDatExptrnlog fDatExptrnlog){
		update("FDatExptrnlog.updateByEntity", fDatExptrnlog);
	}
	
	/**
	 * 通过map主键更新单个对象
	 * @param map
	 * @throws Exception
	 */
	public void update(Map<String,Object> map) throws Exception{
		update("FDatExptrnlog.updateByMap", map);
	}
	
	/**
	 * 通过list批量更新数据(未实现)
	 * @param list
	 * @throws Exception
	 */
	public int update(List list) throws Exception{
		//batchUpdate("FDatExptrnlog.batchUpdate", list);
		return 0;
	}
	
}
