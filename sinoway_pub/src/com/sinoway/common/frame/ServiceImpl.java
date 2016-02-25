package com.sinoway.common.frame;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author THINK
 */
@Component ("serviceImpl")
public class ServiceImpl implements IService {
	/**
	 */
	@Autowired
	//@Qualifier("dao")
	IbatisDao dao;

	public int countByObject(String statementId,Object object){
		List list = dao.query(statementId, object);
		if(list != null && list.size()>0){
			return (Integer)list.get(0);
		}else{
			return 0;
		}
	}

//	public List queryForPagination(String statementId, Object object, int skipResults,
//			int maxResults) {
//		return dao.queryForPagination(statementId, object, skipResults, maxResults);
//	}
	public List query(String statementId, Object params) {
		return dao.query(statementId, params);
	}
	public Object queryForObject(String statementId, Object param) {
		return dao.queryForObject(statementId, param);
	}
	
	/**
	 * 分页查询
	 */
	public List queryByPage(String statementId, Object params, int pageNum,
			int pageSize) {
		return dao.queryByPage(statementId, params, pageNum, pageSize);
	}
	
	public int update(String statementId, Object entity) {
		return dao.update(statementId, entity);
		
	}
	
	/**
	 * 记录不存在时插入，记录存在则进行更新
	 * @param statementId
	 * @param entity
	 * @return
	 */
	public int saveOrUpdate(String statementId, Object entity){
		return dao.update(statementId, entity);
	}
	
	public int delete(String statementId, Object entity) {
		return dao.delete(statementId, entity);
	}
	public Object insert(String statementId, Object entity) {
		return dao.insert(statementId, entity);
	}
    
	/**
	 * 批量操作
	 */
	public void batchInsert(String statementId, List list) throws Exception{
    	dao.batchSave(statementId, list);
    }
	
	public void batchUpdate(String statementId, List list) throws Exception{
		dao.batchUpdate(statementId, list);
	}
	public void batchDel(String statementId, List list) throws Exception{
		dao.batchRemove(statementId, list);
	}
	
	
	

	
	/**
	 * @return
	 * @uml.property  name="dao"
	 */
	public IbatisDao getDao() {
		return dao;
	}

	/**
	 * @param dao
	 * @uml.property  name="dao"
	 */
	public void setDao(IbatisDao dao) {
		this.dao = dao;
	}

	
}
