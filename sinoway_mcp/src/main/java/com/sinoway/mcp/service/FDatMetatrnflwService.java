package com.sinoway.mcp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinoway.mcp.entity.FDatMetatrnflw;
import com.sinoway.common.frame.ServiceImpl;

@Service("fDatMetatrnflwService")
public class FDatMetatrnflwService extends ServiceImpl{
	
	/**
	 * 通过map查询符合条件的数据条目
	 * @param map
	 * @return
	 */
	public int count(Map<String,Object> map){
		return countByObject("FDatMetatrnflw.countByMap", map);
	}
	
	
	/**
	 * 通过对象属性查询数据条目
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public int count(FDatMetatrnflw fDatMetatrnflw){
		return countByObject("FDatMetatrnflw.countByEntity", fDatMetatrnflw);
	}
	
	/**
	 * 批量保存对象
	 * @param list
	 * @throws Exception
	 */
	public void save(List<FDatMetatrnflw> list) throws Exception{
		batchInsert("FDatMetatrnflw.batchInsert", list);
	}
	
	
	
	/**
	 * 保存单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void save(FDatMetatrnflw fDatMetatrnflw){
		insert("FDatMetatrnflw.insertByEntity",fDatMetatrnflw);
	}
	
	/**
	 * 保存对象(传入map)
	 * @param map
	 */
	public void save(Map<String,Object> map){
		insert("FDatMetatrnflw.insertByMap",map);
	}
	
	
	/**
	 * 通过map删除对象
	 * @param map
	 */
	public void del(Map<String,Object> map){
		delete("FDatMetatrnflw.deleteByMap", map);
	}
	
	/**
	 * 通过对象属性删除对象
	 * @param bCfgdefRoleinfo
	 */
	public void del(FDatMetatrnflw fDatMetatrnflw){
		delete("FDatMetatrnflw.deleteByEntity", fDatMetatrnflw);
	}
	
	
	/**
	 * 批量删除数据(ids或beans)
	 * @param list
	 * @throws Exception
	 */
	public void del(List<FDatMetatrnflw> list) throws Exception{
		batchDel("FDatMetatrnflw.batchDelete", list);
	}
	

	/**
	 * 通过对象查询对象
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public List find(FDatMetatrnflw fDatMetatrnflw){
		return  query("FDatMetatrnflw.selectByEntity", fDatMetatrnflw);
	}
	
	/**
	 * 通过对象查询对象
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public List findTmOutData(String tmout){
		return  query("FDatMetatrnflw.selectTmOut", tmout);
	}

	/**
	 * 通过map条件查询返回所有的对象
	 * @param map
	 * @return
	 */
	public List find(Map<String,Object> map){
		return query("FDatMetatrnflw.selectByMap", map);
	}
	

	/**
	 * 分页查询(map)
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(Map<String,Object> map,int pageNum,int pageSize){
		return queryByPage("FDatMetatrnflw.selectByEntity", map, pageNum, pageSize);
	}
	
	/**
	 * 分页查询 根据对象属性查找对象集
	 * @param bCfgdefRoleinfo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(FDatMetatrnflw fDatMetatrnflw,int pageNum,int pageSize){
		return queryByPage("FDatMetatrnflw.selectByEntity", fDatMetatrnflw, pageNum, pageSize);
	}
	
	
	/**
	 * 通过对象主键更新单个对象
	 * @param bCfgdefRoleinfo
	 */
	public int update(FDatMetatrnflw fDatMetatrnflw){
		return update("FDatMetatrnflw.updateByEntity", fDatMetatrnflw);
	}
	
	/**
	 * 通过流水号及状态 更新实体
	 * @param bCfgdefRoleinfo
	 */
	public int updateByFlwAndSta(FDatMetatrnflw fDatMetatrnflw){
		
		try{
			return update("FDatMetatrnflw.updateByFlwAndSta", fDatMetatrnflw);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 通过流水号及状态 更新超时时间
	 * @param bCfgdefRoleinfo
	 */
	public int updateTmoutByFlwAndSta(FDatMetatrnflw fDatMetatrnflw){
		return update("FDatMetatrnflw.updateTmoutByFlwAndSta", fDatMetatrnflw);
	}
	
	/**
	 * 通过流水号及状态 更新响应报文路径
	 * @param bCfgdefRoleinfo
	 */
	public int updateResMsgByFlwAndSta(FDatMetatrnflw fDatMetatrnflw){
		try{
			return update("FDatMetatrnflw.updateResMsgByFlwAndSta", fDatMetatrnflw);
			
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	
	
	/**
	 * 通过map主键更新单个对象
	 * @param map
	 * @throws Exception
	 */
	public void update(Map<String,Object> map) throws Exception{
		update("FDatMetatrnflw.updateByMap", map);
	}
	
	/**
	 * 通过list批量更新数据(未实现)
	 * @param list
	 * @throws Exception
	 */
	public int update(List list) throws Exception{
		//batchUpdate("FDatMetatrnflw.batchUpdate", list);
		return 0;
	}
	
}
