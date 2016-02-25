package com.sinoway.common.frame;


import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;


@Component("dao")
public class IbatisDao extends SqlSessionDaoSupport {
	
	public List query(String statementId, Object entity) {
		return getSqlSession().selectList(statementId, entity);
	}
	
	public List queryByPage(String statementId, Object entity,int pageNum,int pageSize) {
		return getSqlSession().selectList(statementId, entity,new RowBounds(pageNum,pageSize));
	}
	
//	public List queryForPagination(String statementId, Object entity, int start, int end) {
//		return getSqlSession().selectList(statementId, entity, start, end);
//	}
	
	public <T> T queryForObject(String statementId, Object param) {
		return getSqlSession().selectOne(statementId, param);
	}
	public Object insert(String statementId, Object param) {
		return getSqlSession().insert(statementId, param);
	}
	
	public int update(String statementId, Object param) {
		return getSqlSession().update(statementId, param);
	}
	public int delete(String statementId, Object entity){
		return getSqlSession().delete(statementId, entity);
	}

	
	/**
	 * 批量更新，分批次更新，每次更新100条记录
	 * @Title:	batchUpdate 
	 * @Description:			 
	 * @param	@param statementId
	 * @param	@param list
	 * @return	void
	 * @throws
	 */
	public void batchUpdate(final String statementId, final List entities) {
		SqlSession batchSession= getSqlSession();
		int eachBatchCount = 100;//每个批次的个数
		int totalCount = entities.size();//总个数
		int batchNum = totalCount/eachBatchCount;//完整批次的个数
		int lastBatchCount = totalCount%eachBatchCount;//最后一个批次的个数
		for(int i=0; i<batchNum ;i++){
			batchSession.update(statementId, entities.subList(eachBatchCount*i, eachBatchCount*(i+1)));
		}
		if(lastBatchCount != 0){
			batchSession.update(statementId, entities.subList(totalCount-lastBatchCount, totalCount));
		}
	}
	/**
	 * 批量保存
	 * @param statementId
	 * @param entities
	 * @throws Exception
	 */
	public void batchSave(final String statementId,final List entities) throws Exception {
		SqlSession batchSession= getSqlSession();
		int eachBatchCount = 100;//每个批次的个数
		int totalCount = entities.size();//总个数
		int batchNum = totalCount/eachBatchCount;//完整批次的个数
		int lastBatchCount = totalCount%eachBatchCount;//最后一个批次的个数
		for(int i=0; i<batchNum ;i++){
			batchSession.insert(statementId, entities.subList(eachBatchCount*i, eachBatchCount*(i+1)));
		}
		if(lastBatchCount != 0){
			batchSession.insert(statementId, entities.subList(totalCount-lastBatchCount, totalCount));
		}
	}
	/**
	 * 批量移除
	 * @param statementId
	 * @param entities
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void batchRemove(final String statementId,final List entities) throws Exception {
		SqlSession batchSession= getSqlSession();
		int eachBatchCount = 100;//每个批次的个数
		int totalCount = entities.size();//总个数
		int batchNum = totalCount/eachBatchCount;//完整批次的个数
		int lastBatchCount = totalCount%eachBatchCount;//最后一个批次的个数
		for(int i=0; i<batchNum ;i++){
			batchSession.delete(statementId, entities.subList(eachBatchCount*i, eachBatchCount*(i+1)));
		}
		if(lastBatchCount != 0){
			batchSession.delete(statementId, entities.subList(totalCount-lastBatchCount, totalCount));
		}
	}
	
}
