package com.sinoway.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinoway.base.entity.BDatTrnrightchk;
import com.sinoway.common.frame.ServiceImpl;

@Service("bDatTrnrightchkService")
public class BDatTrnrightchkService extends ServiceImpl{
	
	/**
	 * 通过map查询符合条件的数据条目
	 * @param map
	 * @return
	 */
	public int count(Map<String,Object> map){
		return countByObject("BDatTrnrightchk.countByMap", map);
	}
	
	
	/**
	 * 通过对象属性查询数据条目
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public int count(BDatTrnrightchk bDatTrnrightchk){
		return countByObject("BDatTrnrightchk.countByEntity", bDatTrnrightchk);
	}
	
	/**
	 * 批量保存对象
	 * @param list
	 * @throws Exception
	 */
	public void save(List<BDatTrnrightchk> list) throws Exception{
		batchInsert("BDatTrnrightchk.batchInsert", list);
	}
	
	
	
	/**
	 * 保存单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void save(BDatTrnrightchk bDatTrnrightchk){
		insert("BDatTrnrightchk.insertByEntity",bDatTrnrightchk);
	}
	
	/**
	 * 保存对象(传入map)
	 * @param map
	 */
	public void save(Map<String,Object> map){
		insert("BDatTrnrightchk.insertByMap",map);
	}
	
	
	/**
	 * 通过map删除对象
	 * @param map
	 */
	public void del(Map<String,Object> map){
		delete("BDatTrnrightchk.deleteByMap", map);
	}
	
	/**
	 * 通过对象属性删除对象
	 * @param bCfgdefRoleinfo
	 */
	public void del(BDatTrnrightchk bDatTrnrightchk){
		delete("BDatTrnrightchk.deleteByEntity", bDatTrnrightchk);
	}
	
	
	/**
	 * 批量删除数据(ids或beans)
	 * @param list
	 * @throws Exception
	 */
	public void del(List<BDatTrnrightchk> list) throws Exception{
		batchDel("BDatTrnrightchk.batchDelete", list);
	}
	

	/**
	 * 通过对象查询对象
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public List find(BDatTrnrightchk bDatTrnrightchk){
		return (List) query("BDatTrnrightchk.selectByEntity", bDatTrnrightchk);
	}

	/**
	 * 通过map条件查询返回所有的对象
	 * @param map
	 * @return
	 */
	public List find(Map<String,Object> map){
		return query("BDatTrnrightchk.selectByMap", map);
	}
	

	/**
	 * 分页查询(map)
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(Map<String,Object> map,int pageNum,int pageSize){
		return queryByPage("BDatTrnrightchk.selectByEntity", map, pageNum, pageSize);
	}
	
	/**
	 * 分页查询 根据对象属性查找对象集
	 * @param bCfgdefRoleinfo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(BDatTrnrightchk bDatTrnrightchk,int pageNum,int pageSize){
		return queryByPage("BDatTrnrightchk.selectByEntity", bDatTrnrightchk, pageNum, pageSize);
	}
	
	
	/**
	 * 通过对象主键更新单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void update(BDatTrnrightchk bDatTrnrightchk){
		update("BDatTrnrightchk.updateByEntity", bDatTrnrightchk);
	}
	
	/**
	 * 通过map主键更新单个对象
	 * @param map
	 * @throws Exception
	 */
	public void update(Map<String,Object> map) throws Exception{
		update("BDatTrnrightchk.updateByMap", map);
	}
	
	/**
	 * 通过list批量更新数据(未实现)
	 * @param list
	 * @throws Exception
	 */
	public int update(List list) throws Exception{
		//batchUpdate("BDatTrnrightchk.batchUpdate", list);
		return 0;
	}
	
}
