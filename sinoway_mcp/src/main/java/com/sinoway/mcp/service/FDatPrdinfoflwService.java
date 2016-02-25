package com.sinoway.mcp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinoway.mcp.entity.FDatPrdinfoflw;
import com.sinoway.common.frame.ServiceImpl;

@Service("fDatPrdinfoflwService")
public class FDatPrdinfoflwService extends ServiceImpl{
	
	/**
	 * 通过map查询符合条件的数据条目
	 * @param map
	 * @return
	 */
	public int count(Map<String,Object> map){
		return countByObject("FDatPrdinfoflw.countByMap", map);
	}
	
	
	/**
	 * 通过对象属性查询数据条目
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public int count(FDatPrdinfoflw fDatPrdinfoflw){
		return countByObject("FDatPrdinfoflw.countByEntity", fDatPrdinfoflw);
	}
	
	/**
	 * 批量保存对象
	 * @param list
	 * @throws Exception
	 */
	public void save(List<FDatPrdinfoflw> list) throws Exception{
		batchInsert("FDatPrdinfoflw.batchInsert", list);
	}
	
	
	
	/**
	 * 保存单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void save(FDatPrdinfoflw fDatPrdinfoflw){
		insert("FDatPrdinfoflw.insertByEntity",fDatPrdinfoflw);
	}
	
	/**
	 * 保存对象(传入map)
	 * @param map
	 */
	public void save(Map<String,Object> map){
		insert("FDatPrdinfoflw.insertByMap",map);
	}
	
	
	/**
	 * 通过map删除对象
	 * @param map
	 */
	public void del(Map<String,Object> map){
		delete("FDatPrdinfoflw.deleteByMap", map);
	}
	
	/**
	 * 通过对象属性删除对象
	 * @param bCfgdefRoleinfo
	 */
	public void del(FDatPrdinfoflw fDatPrdinfoflw){
		delete("FDatPrdinfoflw.deleteByEntity", fDatPrdinfoflw);
	}
	
	
	/**
	 * 批量删除数据(ids或beans)
	 * @param list
	 * @throws Exception
	 */
	public void del(List<FDatPrdinfoflw> list) throws Exception{
		batchDel("FDatPrdinfoflw.batchDelete", list);
	}
	

	/**
	 * 通过对象查询对象
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public List find(FDatPrdinfoflw fDatPrdinfoflw){
		return   query("FDatPrdinfoflw.selectByEntity", fDatPrdinfoflw);
	}
	
	/**
	 * 通过流水号查询对象
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public List findByFlwCode(FDatPrdinfoflw fDatPrdinfoflw){
		return   query("FDatPrdinfoflw.findByFlwCode", fDatPrdinfoflw);
	}

	/**
	 * 通过map条件查询返回所有的对象
	 * @param map
	 * @return
	 */
	public List find(Map<String,Object> map){
		return query("FDatPrdinfoflw.selectByMap", map);
	}
	

	/**
	 * 分页查询(map)
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(Map<String,Object> map,int pageNum,int pageSize){
		return queryByPage("FDatPrdinfoflw.selectByEntity", map, pageNum, pageSize);
	}
	
	/**
	 * 分页查询 根据对象属性查找对象集
	 * @param bCfgdefRoleinfo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(FDatPrdinfoflw fDatPrdinfoflw,int pageNum,int pageSize){
		return queryByPage("FDatPrdinfoflw.selectByEntity", fDatPrdinfoflw, pageNum, pageSize);
	}
	
	
	/**
	 * 通过对象主键更新单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void update(FDatPrdinfoflw fDatPrdinfoflw){
		update("FDatPrdinfoflw.updateByEntity", fDatPrdinfoflw);
	}
	
	/**
	 * 通过map主键更新单个对象
	 * @param map
	 * @throws Exception
	 */
	public void update(Map<String,Object> map) throws Exception{
		update("FDatPrdinfoflw.updateByMap", map);
	}
	
	/**
	 * 通过list批量更新数据(未实现)
	 * @param list
	 * @throws Exception
	 */
	public int update(List list) throws Exception{
		//batchUpdate("FDatPrdinfoflw.batchUpdate", list);
		return 0;
	}
	
}
