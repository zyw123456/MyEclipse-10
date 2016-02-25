package com.sinoway.app.dao;

import com.yzj.wf.common.WFException;
import com.yzj.wf.common.WFLogger;
import com.yzj.wf.common.db.dao.BaseDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
/**
 * 应用管理
 * @author xiehao
 *
 */
public class AppDao extends BaseDao{
	private final static WFLogger logger = WFLogger.getLogger(AppDao.class.getName());
	@Override
	public Object execSqlQuery(String QuerySql) throws WFException {
		final String sql;
		sql = QuerySql;
		try {
			return getHibernateTemplate().execute(new HibernateCallback<Object>() {
				public Object doInHibernate(Session session) throws HibernateException {
					String querySentence = sql;
					Query query = session.createSQLQuery(querySentence);
					//重写父类方法,以map形式返回查询数据
					query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					return query.list();
				}
			});
		} catch (DataAccessException e) {
			logger.error(e);
			throw new WFException("DAO", 0, 0, new Exception("执行本地Sql失败 Sql=" + sql, e));
		}
	}
}
