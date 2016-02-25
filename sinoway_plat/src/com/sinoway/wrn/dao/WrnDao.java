package com.sinoway.wrn.dao;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;

import com.sinoway.common.util.Constant;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.db.dao.BaseDao;

public class WrnDao extends BaseDao {

	/**
	 * 分页查询
	 */
	@Override
	public List findbyPage(String queryString, int StartRow, int PageSize)
			throws WFException {
		String sql;
		List warnList = null;
		int endRowNum = StartRow + PageSize ;
		sql = "select a.*, ROWNUM from(select row_number() over() as ROWNUM,"
				+ queryString.substring(6) + ") a  where   ROWNUM >"
				+ StartRow + " and ROWNUM <=" + endRowNum;
		warnList = (List) this.execSqlQuery(sql);
		return warnList;
	}

}
