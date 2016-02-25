/**   
* @Title: FadDao.java 
* @Package com.sinoway.fad.dao 
* @Description: TODO(用一句话描述该文件做什么) 
* @author Leo May the source be with you!   
* @date 2015-12-15 下午5:03:14 
* @version V1.0   
*/
package com.sinoway.common.dao;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;

import com.sinoway.common.exception.HPException;
import com.sinoway.common.util.Constant;
import com.sinoway.rpt.entity.WfDatCreditrpt;
import com.sinoway.wrn.entity.WfDatCerditWarnDtel;
import com.sinoway.wrn.entity.WfDatCerditwarn;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.db.dao.BaseDao;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;

/** 
 * @ClassName: FadDao 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Leo May the source be with you! 
 * @date 2015-12-15 下午5:03:14 
 *  
 */
public class HPDao extends BaseDao {

	/**
	 * @throws WFException  
	* @Title: getTopPlatAlarmDetail
	* @Description: 获取实时预警更新数据
	* @param maxResults  获取最新更新数据的条数
	* @param idName    排序字段
	* @throws 
	*/
	@SuppressWarnings("unchecked")
	public List<WfDatCerditWarnDtel> getTopAccAlarm(int maxResults,XPeopleInfo curPeople) throws HPException, WFException {
		List<WfDatCerditWarnDtel> list = null;
		StringBuffer hql = new StringBuffer();
		hql.append("select warnDtel from WfDatCerditWarnDtel warnDtel where warnDtel.credtNo in (");
		hql.append(" select prsn.prsncod from WfDatCerditPrsn prsn ");
		if(curPeople.getParntCode() == null){
			hql.append(" where prsn.parntcode ='" +curPeople.getPeopleCode()+"' or prsn.peoplecode='"+curPeople.getPeopleCode()+"')");
		}else{
			hql.append(" where prsn.parntcode='"+curPeople.getParntCode()+"' or prsn.peoplecode='"+curPeople.getParntCode()+"')");
		}
			list = (List<WfDatCerditWarnDtel>) this.findbyPage(hql.toString(), 0, maxResults);
		return list;
	}
	
	/**
	 * 获取平台预警明细中的前N条数据
	 * @param maxResults
	 * @return
	 * @throws HPException
	 */
	public List<WfDatCerditWarnDtel> getTopPlatAlarmDetail(int maxResults) throws HPException {
//		StringBuffer hql = new StringBuffer();
//		hql.append(" select warndtl from WfDatCerditWarnDtel warndtl ");
//		hql.append(" order by warndtl.recvDte desc, warndtl.warnTim desc");
//		return this.getSession().createQuery(hql.toString()).setMaxResults(maxResults).list();
		return new LinkedList<WfDatCerditWarnDtel>(getTop(WfDatCerditWarnDtel.class,"warnDte", "warnTim",maxResults));
	}
	
	
	public List<WfDatCreditrpt> getTopPlatCreditRpt(int maxResults) throws HPException{
		return new LinkedList<WfDatCreditrpt>(getTop(WfDatCreditrpt.class, WfDatCreditrpt.CREDITRPT_RPTMODDTE, WfDatCreditrpt.CREDITRPT_RPTMODTIM, maxResults));
	}
	
	
	/**
	 * @throws WFException  
	* @Title: getTopAccRpt
	* @Description: 获取账号实时报告更新数据
	* @param maxResults  获取最新更新数据的条数
	* @param idName    排序字段
	* @throws 
	*/
	@SuppressWarnings("unchecked")
	public List<WfDatCreditrpt> getTopAccRpt(int maxResults,XPeopleInfo curPeople) throws HPException, WFException {
		List<WfDatCreditrpt> list = null;
		StringBuffer hql = new StringBuffer();
		hql.append("from WfDatCreditrpt where rptsta !='").append(Constant.RptStatus.STATIS_DELETE.getCode()).append("'");
		//除了删除的报告，所有操作的报告显示20160128
//		if(curPeople.getParntCode() == null){
//			hql.append(" and (parntcode ='" +curPeople.getPeopleCode()+"' or peoplecode='"+curPeople.getPeopleCode()+"')");
//		}else{
//			hql.append(" and (parntcode='"+curPeople.getParntCode()+"' or peoplecode='"+curPeople.getParntCode()+"')");
//		}
		hql.append(" order by rptmoddte desc,rptmodtim desc");
		list = (List<WfDatCreditrpt>) this.findbyPage(hql.toString(), 0, maxResults);
		return list;
	}
    
    /**
     * 
     * @param userID
     * @param maxResults
     * @return
     * @throws HPException
     */
	public LinkedList<WfDatCerditwarn> getTopAccAlarm(String userID, int maxResults) throws HPException {
		//return getTop(WfDatCerditwarn.class, "warnid", maxResults);
		return null;
	}
	
	/** 
	* @Title: getTop通用类
	* @Description: 获取实时更新数据
	* @param maxResults  获取最新更新数据的条数
	* @param idName    排序字段
	* @throws 
	*/
	public <T> LinkedList<T> getTop(Class<T> T, String dateName, String timeName,int maxResults) throws HPException {
		try {
			String hql = "from " + T.getSimpleName() + " as e order by e." + dateName + " desc,e." +timeName+" desc";
			getHibernateTemplate().setMaxResults(maxResults);
			@SuppressWarnings("unchecked")
			List<T> results = getHibernateTemplate().find(hql);
			return new LinkedList<T>(results);
		} catch (DataAccessException e) {
			throw new HPException(e.getMessage());
		}
	}

	/** 
	* @Title: getAccAlarmNo 
	* @Description: 获取账号委托监控统计
	* @param @param userID
	* @param @return    设定文件 
	* @return long    返回类型 
	* @throws 
	*/
	public long getAccAlarmNo(String UserID) {
		return 0;
	}
	@SuppressWarnings("unchecked")
	public long getAccAlarmNo(XPeopleInfo curPeople) throws WFException {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(warnDtel.id) from WfDatCerditWarnDtel warnDtel where warnDtel.credtNo in (");
		hql.append(" select prsn.prsncod from WfDatCerditPrsn prsn ");
		if(curPeople.getParntCode() == null){
			hql.append(" where prsn.parntcode ='" +curPeople.getPeopleCode()+"' or prsn.peoplecode='"+curPeople.getPeopleCode()+"')");
		}else{
			hql.append(" where prsn.parntcode='"+curPeople.getParntCode()+"' or prsn.peoplecode='"+curPeople.getParntCode()+"')");
		}
		List<Long> countlist = null;
		countlist=(List<Long>) this.find(hql.toString());
		if(countlist.size()==0){
			return 0;
		}else{
			return countlist.get(0);
		}
	}

	/** 
	* @Title: getAccAlarmDetailNo 
	* @Description: 获取账号监控预警统计
	* @param @param userID
	* @param @return    设定文件 
	* @return long    返回类型 
	* @throws 
	*/
	public long getAccAlarmDetailNo(String userID) {
		return 0;
	}
	@SuppressWarnings("unchecked")
	public long getAccAlarmDetailNo(XPeopleInfo curPeople) throws WFException {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(warnid) from WfDatCerditwarn  where sta='1' ");
		if(curPeople.getParntCode() == null){
			hql.append(" and parntcode ='" +curPeople.getPeopleCode()+"' or peoplecode='"+curPeople.getPeopleCode()+"')");
		}else{
			hql.append(" and parntcode='"+curPeople.getParntCode()+"' or peoplecode='"+curPeople.getParntCode()+"')");
		}
		List<Long> countlist = null;
			countlist=(List<Long>) this.find(hql.toString());
		if(countlist.size()==0){
			return 0;
		}else{
			return countlist.get(0);
		}
	}

	
	/**
	 * @throws WFException  
	* @Title: getPlatAlarmDetailNo 
	* @Description: 获取平台总监控统计
	* @param @return    设定文件 
	* @return long    返回类型 
	* @throws 
	*/
	@SuppressWarnings("unchecked")
	public long getPlatAlarmDetailNo() throws WFException {
		String hql = "select count(id) from WfDatCerditWarnDtel ";
		List<Long> countlist = null;
			countlist=(List<Long>) this.find(hql);
		if(countlist.size()==0){
			return 0;
		}else{
			return countlist.get(0);
		}
		
	}

	/**
	 * @throws WFException  
	* @Title: getPlatAlarmNo 
	* @Description: 获取平台监控预警统计
	* @param @return    设定文件 
	* @return long    返回类型 
	* @throws 
	*/
	@SuppressWarnings("unchecked")
	public long getPlatAlarmNo() throws WFException {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(warnid) ");
		hql.append(" from WfDatCerditwarn ");
//		hql.append(" where sta='").append(Constant.RptStatus.STATIS_QUERYSUCCESS.getCode()).append("' ");
		List<Long> countlist = null;
		countlist=(List<Long>) this.find(hql.toString());
		if(countlist.size()==0){
			return 0;
		}else{
			return countlist.get(0);
		}
	}
	/**
	 * 查询当前登录人账号及其子账号拼接而成的字符串
	 */
	@SuppressWarnings("unchecked")
	public String getCurOrgAndChildOrg(XPeopleInfo curPeople){
		String orgnoStr= "";
		StringBuffer hql = new StringBuffer();
		hql.append("with peo(peoplecode,parntcode) as (select peoplecode,parntcode from po_peopleinfo where parntcode='"+curPeople.getPeopleCode()+"' union all select a.peoplecode,");
		hql.append("a.parntcode from po_peopleinfo a,peo b where a.parntcode = b.peoplecode ) select peoplecode from peo");
		try {
			List<Object[]> list = (List<Object[]>)this.execSqlQuery(hql.toString());
			for (int i = 0; i < list.size(); i++) {
					orgnoStr += "'"+list.get(i)[0] + "',";
			}
		} catch (WFException e) {
			e.printStackTrace();
		}
		orgnoStr += "'" +curPeople.getPeopleCode()+"'";
		return orgnoStr;
	}

	/**
	 * 当前主账户日使用流量
	 * @param userID
	 * @return
	 */
//	public long getDailyDataUasge(String userID) {
//		
//		return 0;
//	}


	/**
	 * 获取当前主账户当日产品预警数
	 * @param userID
	 * @param currDate
	 * @return
	 */
	public long getCurUserDailyAlarmNo(String userID, String currDate) {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(warnid) from WfDatCerditwarn wrn ");
		hql.append(" where wrn.sta='").append(Constant.RptStatus.STATIS_QUERYSUCCESS.getCode()).append("' ");
		hql.append(" and wrn.warnmoddte='").append(currDate).append("' ");
		hql.append(" and (wrn.parntcode ='").append(userID).append("' or wrn.peoplecode='").append(userID).append("')");
		List<Long> countlist = null;
		try {
			countlist = (List<Long>) this.find(hql.toString());
		} catch (WFException e) {
			e.printStackTrace();
		}
		if(countlist.size()==0){
			return 0;
		}else{
			return countlist.get(0);
		}
	}

	/**
	 * 获取当前主账户当日报告总数
	 * @param userID
	 * @param currDate
	 * @return
	 */
	public long getCurUserDailyReport(String userID, String currDate) {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(rptid) from WfDatCreditrpt rpt");
		hql.append(" where rpt.rptsta='").append(Constant.RptStatus.STATIS_QUERYSUCCESS.getCode()).append("' ");
		hql.append(" and rpt.rptmoddte='").append(currDate).append("' ");
		hql.append(" and (rpt.parntcode ='").append(userID).append("' or rpt.peoplecode='").append(userID).append("')");
		
		List<Long> countlist = null;
		try {
			countlist = (List<Long>) this.find(hql.toString());
		} catch (WFException e) {
			e.printStackTrace();
		}
		if(countlist.size()==0){
			return 0;
		}else{
			return countlist.get(0);
		}
	}

	/**
	 * 查询近3天预警流量
	 * @param userID
	 * @param currDate
	 * @return
	 * @throws WFException 
	 */
	public Object[] getCurRecent3DaysAlarmUsage(String userID, String currDate) throws WFException {
		StringBuffer sql = new StringBuffer();
		sql.append(" select COALESCE(t1.CC,0) as cnt  from ( ");
		sql.append(" SELECT ");
		sql.append(" to_char( CURRENT_DATE - 3 DAY + (ROW_NUMBER() OVER (ORDER BY 1) )  DAY , 'YYYYMMDD') AS dayresult ");
		sql.append(" FROM SYSIBM.SYSCOLUMNS ");
		sql.append(" fetch first 3 rows only) as t ");
		sql.append(" left join ");
		sql.append(" (select WARNMODDTE as cntDate,count(1) as cc ");
		sql.append(" from WF_DAT_CERDITWARN wrn ");
		sql.append(" where wrn.WARNMODDTE > to_char(current date - 3 day,'YYYYMMDD')  ");
		sql.append(" and wrn.sta = '").append(Constant.RptStatus.STATIS_QUERYSUCCESS.getCode()).append("' ");
		sql.append(" and (wrn.parntcode='").append(userID).append("' or wrn.peoplecode = '").append(userID).append("') ");
		sql.append( " group by WARNMODDTE ");
		sql.append(" ) as t1 ");
		sql.append(" on  t1.cntDate = t.dayresult "); 
		sql.append(" order by t.dayresult");
		return this.getSession().createSQLQuery(sql.toString()).list().toArray();
	}
	
	/**
	 * 查询近3天报告流量
	 * @param userID
	 * @param currDate
	 * @return
	 * @throws WFException
	 */
	public Object[] getCurRecent3DaysReportUsage(String userID, String currDate) throws WFException {
		StringBuffer sql = new StringBuffer();
		sql.append(" select COALESCE(t1.CC,0) as cnt  from ( ");
		sql.append(" SELECT ");
		sql.append(" to_char( CURRENT_DATE - 3 DAY + (ROW_NUMBER() OVER (ORDER BY 1) )  DAY , 'YYYYMMDD') AS dayresult ");
		sql.append(" FROM SYSIBM.SYSCOLUMNS ");
		sql.append(" fetch first 3 rows only) as t ");
		sql.append(" left join ");
		sql.append(" (select RPTMODDTE as cntDate,count(1) as cc ");
		sql.append(" from WF_DAT_CREDITRPT rpt");
		sql.append(" where RPTMODDTE > to_char(current date - 3 day,'YYYYMMDD')  ");
		sql.append(" and rpt.rptsta = '").append(Constant.RptStatus.STATIS_QUERYSUCCESS.getCode()).append("' ");
		sql.append(" and (rpt.parntcode='").append(userID).append("' or rpt.peoplecode = '").append(userID).append("') ");
		sql.append( " group by RPTMODDTE ");
		sql.append(" ) as t1 ");
		sql.append(" on  t1.cntDate = t.dayresult "); 
		sql.append(" order by t.dayresult");
		return this.getSession().createSQLQuery(sql.toString()).list().toArray();
	}

	
	/**
	 * 验证平台流量
	 * @param userID
	 * @param currDate
	 * @return
	 */
	public long getPlatVerifiedUsage(String userID, String currDate) {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(rptid) from WfDatCreditrpt rpt");
		hql.append(" where rpt.rptsta='").append(Constant.RptStatus.STATIS_QUERYSUCCESS.getCode()).append("' ");
		hql.append(" and rpt.rptmoddte='").append(currDate).append("' ");
		hql.append(" and (rpt.parntcode ='").append(userID).append("' or rpt.peoplecode='").append(userID).append("')");
		hql.append(" and rpt.rpttyp='").append(Constant.RptTyp.RPTTYP_VERIFIED.getCode()).append("'");
		List<Long> countlist = null;
		try {
			countlist = (List<Long>) this.find(hql.toString());
		} catch (WFException e) {
			e.printStackTrace();
		}
		if(countlist.size()==0){
			return 0;
		}else{
			return countlist.get(0);
		}
	}

	/**
	 * 个人异常流量: 反欺诈报告数
	 * @param userID
	 * @param currDate
	 * @return
	 */
	public long getPlatFraudUsage(String userID, String currDate) {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(rptid) from WfDatCreditrpt rpt");
		hql.append(" where rpt.rptsta='").append(Constant.RptStatus.STATIS_QUERYSUCCESS.getCode()).append("' ");
		hql.append(" and rpt.rptmoddte='").append(currDate).append("' ");
		hql.append(" and (rpt.parntcode ='").append(userID).append("' or rpt.peoplecode='").append(userID).append("')");
		hql.append(" and rpt.rpttyp='").append(Constant.RptTyp.RPTTYP_FRAUD.getCode()).append("'");
		List<Long> countlist = null;
		try {
			countlist = (List<Long>) this.find(hql.toString());
		} catch (WFException e) {
			e.printStackTrace();
		}
		if(countlist.size()==0){
			return 0;
		}else{
			return countlist.get(0);
		}
	}

	
	/**
	 * 当日平台上传流量:平台上传--- 报告类型--个人征信报告  数据来源-- 平台
	 * @param userID
	 * @param currDate
	 * @return
	 */
	public long getDatcmitoriPlatUsage(String userID, String currDate) {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(rptid) from WfDatCreditrpt rpt");
		hql.append(" where rpt.rptsta='").append(Constant.RptStatus.STATIS_QUERYSUCCESS.getCode()).append("' ");
		hql.append(" and rpt.rptmoddte='").append(currDate).append("' ");
		hql.append(" and (rpt.parntcode ='").append(userID).append("' or rpt.peoplecode='").append(userID).append("')");
		hql.append(" and rpt.rpttyp='").append(Constant.RptTyp.RPTTYP_PRSN_CREDIT.getCode()).append("'");
		hql.append(" and rpt.datcmitori='").append(Constant.DatCmitori.DatCmitori_PLANT.getCode()).append("'");
		
		List<Long> countlist = null;
		try {
			countlist = (List<Long>) this.find(hql.toString());
		} catch (WFException e) {
			e.printStackTrace();
		}
		if(countlist.size()==0){
			return 0;
		}else{
			return countlist.get(0);
		}
	}

	
	/**
	 * 平台上传--- 报告类型--个人征信报告  数据来源-- 接口
	 * @param userID
	 * @param currDate
	 * @return
	 */
	public long getDatcmitoriInterfaceUsage(String userID, String currDate) {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(rptid) from WfDatCreditrpt rpt");
		hql.append(" where rpt.rptsta='").append(Constant.RptStatus.STATIS_QUERYSUCCESS.getCode()).append("' ");
		hql.append(" and rpt.rptmoddte='").append(currDate).append("' ");
		hql.append(" and (rpt.parntcode ='").append(userID).append("' or rpt.peoplecode='").append(userID).append("')");
		hql.append(" and rpt.rpttyp='").append(Constant.RptTyp.RPTTYP_PRSN_CREDIT.getCode()).append("'");
		hql.append(" and rpt.datcmitori='").append(Constant.DatCmitori.DatCmitori_INTERFACE.getCode()).append("'");
		
		List<Long> countlist = null;
		try {
			countlist = (List<Long>) this.find(hql.toString());
		} catch (WFException e) {
			e.printStackTrace();
		}
		if(countlist.size()==0){
			return 0;
		}else{
			return countlist.get(0);
		}
	}

	
	/**
	 * 平台上传--- 报告类型--个人征信报告  数据来源-- 公众号上传
	 * @param userID
	 * @param currDate
	 * @return
	 */
	public long getDatcmitoriWechatUsage(String userID, String currDate) {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(rptid) from WfDatCreditrpt rpt ");
		hql.append(" where rpt.rptsta='").append(Constant.RptStatus.STATIS_QUERYSUCCESS.getCode()).append("' ");
		hql.append(" and rpt.rptmoddte='").append(currDate).append("' ");
		hql.append(" and (rpt.parntcode ='").append(userID).append("' or rpt.peoplecode='").append(userID).append("') ");
		hql.append(" and rpt.rpttyp='").append(Constant.RptTyp.RPTTYP_PRSN_CREDIT.getCode()).append("' ");
		hql.append(" and rpt.datcmitori='").append(Constant.DatCmitori.DatCmitori_WECHAT.getCode()).append("' ");
		
		List<Long> countlist = null;
		try {
			countlist = (List<Long>) this.find(hql.toString());
		} catch (WFException e) {
			e.printStackTrace();
		}
		if(countlist.size()==0){
			return 0;
		}else{
			return countlist.get(0);
		}
	}

	
	/**
	 * 日总监控流量-- 当前主账户下 的产品预警数
	 * @param userID
	 * @param currDate
	 * @return
	 */
	public long getAccDailyAlarmNo(String userID, String currDate) {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(warnid) ");
		hql.append(" from WfDatCerditwarn ");
		hql.append(" where sta='").append(Constant.RptStatus.STATIS_QUERYSUCCESS.getCode()).append("' ");
		hql.append(" and warnmoddte='").append(currDate).append("' ");
		hql.append(" and (parntcode ='").append(userID).append("' or peoplecode='").append(userID).append("') ");
		List<Long> countlist = null;
		try {
			countlist=(List<Long>) this.find(hql.toString());
		} catch (WFException e) {
			e.printStackTrace();
		}
		if(countlist.size()==0){
			return 0;
		}else{
			return countlist.get(0);
		}
	}
}
