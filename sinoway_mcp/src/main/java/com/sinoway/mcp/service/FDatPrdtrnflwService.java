package com.sinoway.mcp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinoway.mcp.entity.FDatPrdtrnflw;
import com.sinoway.common.frame.ServiceImpl;

@Service("fDatPrdtrnflwService")
public class FDatPrdtrnflwService extends ServiceImpl{
	
	/**
	 * 通过map查询符合条件的数据条目
	 * @param map
	 * @return
	 */
	public int count(Map<String,Object> map){
		return countByObject("FDatPrdtrnflw.countByMap", map);
	}
	
	
	/**
	 * 通过对象属性查询数据条目
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public int count(FDatPrdtrnflw fDatPrdtrnflw){
		return countByObject("FDatPrdtrnflw.countByEntity", fDatPrdtrnflw);
	}
	
	/**
	 * 批量保存对象
	 * @param list
	 * @throws Exception
	 */
	public void save(List<FDatPrdtrnflw> list) throws Exception{
		batchInsert("FDatPrdtrnflw.batchInsert", list);
	}
	
	
	
	/**
	 * 保存单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void save(FDatPrdtrnflw fDatPrdtrnflw){
		insert("FDatPrdtrnflw.insertByEntity",fDatPrdtrnflw);
	}
	
	/**
	 * 保存对象(传入map)
	 * @param map
	 */
	public void save(Map<String,Object> map){
		insert("FDatPrdtrnflw.insertByMap",map);
	}
	
	
	/**
	 * 通过map删除对象
	 * @param map
	 */
	public void del(Map<String,Object> map){
		delete("FDatPrdtrnflw.deleteByMap", map);
	}
	
	/**
	 * 通过对象属性删除对象
	 * @param bCfgdefRoleinfo
	 */
	public void del(FDatPrdtrnflw fDatPrdtrnflw){
		delete("FDatPrdtrnflw.deleteByEntity", fDatPrdtrnflw);
	}
	
	
	/**
	 * 批量删除数据(ids或beans)
	 * @param list
	 * @throws Exception
	 */
	public void del(List<FDatPrdtrnflw> list) throws Exception{
		batchDel("FDatPrdtrnflw.batchDelete", list);
	}
	

	/**
	 * 通过对象查询对象
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public List find(FDatPrdtrnflw fDatPrdtrnflw){
		return   query("FDatPrdtrnflw.selectByEntity", fDatPrdtrnflw);
	}
	
	/**
	 * 通过对象查询对象
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public List findByFlwCode(FDatPrdtrnflw fDatPrdtrnflw){
		return   query("FDatPrdtrnflw.selectFlwCode", fDatPrdtrnflw);
	}
	

	/**
	 * 通过map条件查询返回所有的对象
	 * @param map
	 * @return
	 */
	public List find(Map<String,Object> map){
		return query("FDatPrdtrnflw.selectByMap", map);
	}
	

	/**
	 * 分页查询(map)
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(Map<String,Object> map,int pageNum,int pageSize){
		return queryByPage("FDatPrdtrnflw.selectByEntity", map, pageNum, pageSize);
	}
	
	/**
	 * 分页查询 根据对象属性查找对象集
	 * @param bCfgdefRoleinfo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(FDatPrdtrnflw fDatPrdtrnflw,int pageNum,int pageSize){
		return queryByPage("FDatPrdtrnflw.selectByEntity", fDatPrdtrnflw, pageNum, pageSize);
	}
	
	
	/**
	 * 通过对象主键更新单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void update(FDatPrdtrnflw fDatPrdtrnflw){
		update("FDatPrdtrnflw.updateByEntity", fDatPrdtrnflw);
	}
	
	/**
	 * 通过map主键更新单个对象
	 * @param map
	 * @throws Exception
	 */
	public void update(Map<String,Object> map) throws Exception{
		update("FDatPrdtrnflw.updateByMap", map);
	}
	
	/**
	 * 通过list批量更新数据(未实现)
	 * @param list
	 * @throws Exception
	 */
	public int update(List list) throws Exception{
		//batchUpdate("FDatPrdtrnflw.batchUpdate", list);
		return 0;
	}
	
}
