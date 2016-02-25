package com.sinoway.mcp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinoway.mcp.entity.FLogTrnflw;
import com.sinoway.common.frame.ServiceImpl;

@Service("fLogTrnflwService")
public class FLogTrnflwService extends ServiceImpl{
	
	/**
	 * 通过map查询符合条件的数据条目
	 * @param map
	 * @return
	 */
	public int count(Map<String,Object> map){
		return countByObject("FLogTrnflw.countByMap", map);
	}
	
	
	/**
	 * 通过对象属性查询数据条目
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public int count(FLogTrnflw fLogTrnflw){
		return countByObject("FLogTrnflw.countByEntity", fLogTrnflw);
	}
	
	/**
	 * 批量保存对象
	 * @param list
	 * @throws Exception
	 */
	public void save(List<FLogTrnflw> list) throws Exception{
		batchInsert("FLogTrnflw.batchInsert", list);
	}
	
	
	
	/**
	 * 保存单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void save(FLogTrnflw fLogTrnflw){
		insert("FLogTrnflw.insertByEntity",fLogTrnflw);
	}
	
	/**
	 * 保存对象(传入map)
	 * @param map
	 */
	public void save(Map<String,Object> map){
		insert("FLogTrnflw.insertByMap",map);
	}
	
	
	/**
	 * 通过map删除对象
	 * @param map
	 */
	public void del(Map<String,Object> map){
		delete("FLogTrnflw.deleteByMap", map);
	}
	
	/**
	 * 通过对象属性删除对象
	 * @param bCfgdefRoleinfo
	 */
	public void del(FLogTrnflw fLogTrnflw){
		delete("FLogTrnflw.deleteByEntity", fLogTrnflw);
	}
	
	
	/**
	 * 批量删除数据(ids或beans)
	 * @param list
	 * @throws Exception
	 */
	public void del(List<FLogTrnflw> list) throws Exception{
		batchDel("FLogTrnflw.batchDelete", list);
	}
	

	/**
	 * 通过对象查询对象
	 * @param bCfgdefRoleinfo
	 * @return
	 */
	public List find(FLogTrnflw fLogTrnflw){
		return   query("FLogTrnflw.selectByEntity", fLogTrnflw);
	}

	/**
	 * 通过map条件查询返回所有的对象
	 * @param map
	 * @return
	 */
	public List find(Map<String,Object> map){
		return query("FLogTrnflw.selectByMap", map);
	}
	

	/**
	 * 分页查询(map)
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(Map<String,Object> map,int pageNum,int pageSize){
		return queryByPage("FLogTrnflw.selectByEntity", map, pageNum, pageSize);
	}
	
	/**
	 * 分页查询 根据对象属性查找对象集
	 * @param bCfgdefRoleinfo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List findByPage(FLogTrnflw fLogTrnflw,int pageNum,int pageSize){
		return queryByPage("FLogTrnflw.selectByEntity", fLogTrnflw, pageNum, pageSize);
	}
	
	
	/**
	 * 通过对象主键更新单个对象
	 * @param bCfgdefRoleinfo
	 */
	public void update(FLogTrnflw fLogTrnflw){
		update("FLogTrnflw.updateByEntity", fLogTrnflw);
	}
	
	/**
	 * 通过map主键更新单个对象
	 * @param map
	 * @throws Exception
	 */
	public void update(Map<String,Object> map) throws Exception{
		update("FLogTrnflw.updateByMap", map);
	}
	
	/**
	 * 通过list批量更新数据(未实现)
	 * @param list
	 * @throws Exception
	 */
	public int update(List list) throws Exception{
		//batchUpdate("FLogTrnflw.batchUpdate", list);
		return 0;
	}
	
}
