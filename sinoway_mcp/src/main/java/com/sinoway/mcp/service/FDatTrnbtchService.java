package com.sinoway.mcp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinoway.mcp.entity.FDatTrnbtch;
import com.sinoway.common.frame.ServiceImpl;

@Service("fDatTrnbtchService")
public class FDatTrnbtchService extends ServiceImpl{
	
	/**
	 * 通过map查询符合条件的数据条目
	 * @param map
	 * @return
	 */
	public int count(Map<String,Object> map){
		return countByObject("FDatTrnbtch.countByMap", map);
	}
	
	
	/**
	 * 通过对象属性查询数据条目
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public int count(FDatTrnbtch fDatTrnbtch){
		return countByObject("FDatTrnbtch.countByEntity", fDatTrnbtch);
	}
	
	/**
	 * 批量保存对象
	 * @param list
	 * @throws Exception
	 */
	public void save(List<FDatTrnbtch> list) throws Exception{
		batchInsert("FDatTrnbtch.batchInsert", list);
	}
	
	
	
	/**
	 * 保存单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void save(FDatTrnbtch fDatTrnbtch){
		insert("FDatTrnbtch.insertByEntity",fDatTrnbtch);
	}
	
	/**
	 * 保存对象(传入map)
	 * @param map
	 */
	public void save(Map<String,Object> map){
		insert("FDatTrnbtch.insertByMap",map);
	}
	
	
	/**
	 * 通过map删除对象
	 * @param map
	 */
	public void del(Map<String,Object> map){
		delete("FDatTrnbtch.deleteByMap", map);
	}
	
	/**
	 * 通过对象属性删除对象
	 * @param bCfgdefRoleinfo
	 */
	public void del(FDatTrnbtch fDatTrnbtch){
		delete("FDatTrnbtch.deleteByEntity", fDatTrnbtch);
	}
	
	
	/**
	 * 批量删除数据(ids或beans)
	 * @param list
	 * @throws Exception
	 */
	public void del(List<FDatTrnbtch> list) throws Exception{
		batchDel("FDatTrnbtch.batchDelete", list);
	}
	

	/**
	 * 通过对象查询对象
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public List find(FDatTrnbtch fDatTrnbtch){
		return   query("FDatTrnbtch.selectByEntity", fDatTrnbtch);
	}

	/**
	 * 通过map条件查询返回所有的对象
	 * @param map
	 * @return
	 */
	public List find(Map<String,Object> map){
		return query("FDatTrnbtch.selectByMap", map);
	}
	

	/**
	 * 分页查询(map)
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(Map<String,Object> map,int pageNum,int pageSize){
		return queryByPage("FDatTrnbtch.selectByEntity", map, pageNum, pageSize);
	}
	
	/**
	 * 分页查询 根据对象属性查找对象集
	 * @param bCfgdefRoleinfo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(FDatTrnbtch fDatTrnbtch,int pageNum,int pageSize){
		return queryByPage("FDatTrnbtch.selectByEntity", fDatTrnbtch, pageNum, pageSize);
	}
	
	
	/**
	 * 通过对象主键更新单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void update(FDatTrnbtch fDatTrnbtch){
		update("FDatTrnbtch.updateByEntity", fDatTrnbtch);
	}
	
	/**
	 * 通过map主键更新单个对象
	 * @param map
	 * @throws Exception
	 */
	public void update(Map<String,Object> map) throws Exception{
		update("FDatTrnbtch.updateByMap", map);
	}
	
	/**
	 * 通过list批量更新数据(未实现)
	 * @param list
	 * @throws Exception
	 */
	public int update(List list) throws Exception{
		//batchUpdate("FDatTrnbtch.batchUpdate", list);
		return 0;
	}
	
}
