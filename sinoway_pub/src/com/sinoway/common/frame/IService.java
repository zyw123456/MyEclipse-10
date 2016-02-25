package com.sinoway.common.frame;

import java.util.List;

public interface IService {
    /**
     * 分页查询
     * @param statementId
     * @param entity
     * @param skipResults
     * @param maxResults
     * @return
     */
//	public List queryForPagination(String statementId, Object entity, int skipResults,
//			int maxResults);
	
	public int countByObject(String statementId, Object entity);
	
    /**
     * 查询单个对象
     * @param statementId
     * @param param
     * @return
     */
	public Object queryForObject(String statementId, Object param);
	
	/**
	 * 查询多个值
	 * @param statementId
	 * @param params
	 * @return
	 */
	public List query(String statementId, Object params);

	/**
	 * 分页查询
	 * @param statementId
	 * @param params
	 * @return
	 */
	public List queryByPage(String statementId, Object params ,int pageNum ,int pageSize);

	
	
	/**
	 * 更新
	 * @param statementId
	 * @param entity
	 * @return
	 */
	public int update(String statementId, Object entity);

	/**
	 * 删除
	 * @param statementId
	 * @param entity
	 * @return
	 */
	public int delete(String statementId, Object entity);
	
	/**
	 * 插入
	 * @param string
	 * @param entity
	 * @return
	 */
	public Object insert(String string, Object entity);
	
	/**
	 * 批量更新
	 * @param statementId
	 * @param list
	 */
	public void batchUpdate(String statementId,  List list)throws Exception;
	
	/**
	 * 批量插入
	 * @param statementId
	 * @param list
	 */
	public void batchInsert(String statementId,  List list)throws Exception;
	
	/**
	 * 批量删除
	 * @param statementId
	 * @param list
	 */
	public void batchDel(String statementId,  List list)throws Exception;
	
}
