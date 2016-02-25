package com.sinoway.wrn.dao;

import java.util.List;

import com.sinoway.common.util.Constant;
import com.sinoway.common.util.GUIDGenerator;
import com.sinoway.rpt.service.impl.WfDatCreditrptService;
import com.sinoway.wrn.entity.WfDatCerditPrsn;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.db.dao.BaseDao;
import com.yzj.wf.common.util.StringUtils;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;
/** 
 * @ClassName: WarnMonitorAction 
 * @Description: (天警云监控人员) 
 * @author 于辉
 * @date 2015-12-29
 *  
 */
public class WarnMonitorDao extends BaseDao{
	
	/** 
	* @Title: findMonitorNameList 
	* @Description:(查询监控人员名单) 
	* @return List     
	* @throws 于辉
	*/
	public List findMonitorNameList(WfDatCerditPrsn wdcp,String trncod){
		List list = null;
		try {
//			String[] paramName = {"prsnnam","prsncod","loansrtdte","loanenddte","sqlwhere"};
//			Object[] paramValue = {wdcp.getPrsnnam(),wdcp.getPrsncod(),wdcp.getLoansrtdte(),wdcp.getLoanenddte(),wdcp.getSqlWhere()};
	//		String[] paramName = {"prsnnam","trncod","prsncod","loansrtdte","loanenddte"};
	//		Object[] paramValue = {wdcp.getPrsnnam(),wdcp.getTrncod(),wdcp.getPrsncod(),wdcp.getLoansrtdte(),wdcp.getLoanenddte()};
					
			//list = this.findByNamedQueryAndNamedParamArr("warnMonitorDao.findMonitorNameList", paramName, paramValue);
			String prsname = wdcp.getPrsnnam().equals("")?"''":wdcp.getPrsnnam();
			String prsncod = wdcp.getPrsncod().equals("")?"''":wdcp.getPrsncod();
			String loansrt = wdcp.getLoansrtdte().equals("")?"''":wdcp.getLoansrtdte();
			String loanent = wdcp.getLoanenddte().equals("")?"''":wdcp.getLoanenddte();
			String loantyp = wdcp.getLoantyp().equals("")?"''":wdcp.getLoantyp();
			if(!trncod.equals("")){
				trncod = " and wcp.prdcod in (select distinct w.prdcod from WF_CFGREF_PRDDETIL w " +
						 " where w.trncod in("+trncod+")) ";
			}
			StringBuffer sql = new StringBuffer();
			sql.append("select wcp.prsnnam,wcp.prsncod,wcp.loansrtdte||'-'||wcp.loanenddte loansrtdte,");
			sql.append(" decode(wcp.loantyp,'0','消费贷款','1','汽车贷款','2','购房贷款'),( select count(1) from WF_DAT_CERDITWARNDTEL wcw where wcp.prsnnam=wcw.prsnnam and wcp.prsncod=wcw.credtno) ");
			sql.append(" warncount,id,prdcod,prdnam from WF_DAT_CERDITPRSN wcp  where " );
			sql.append(" wcp.corgno in ("+ wdcp.getOrgno() +") ");
			sql.append(" and wcp.sta="+Constant.WrnMonitorStatus.WRNSTATUS_MONITOR.getValue());
			sql.append(" and instr(wcp.loantyp,"+loantyp+")>0");
			sql.append(" and instr(wcp.prsnnam,"+prsname+")>0");
			sql.append(" and instr(wcp.prsncod,"+prsncod+")>0");
			sql.append(" and instr(wcp.loansrtdte,"+loansrt+")>0");
			sql.append(" and instr(wcp.loanenddte,"+loanent+")>0"+trncod);
			sql.append(" order by wcp.id asc ");
			list = (List) this.execSqlQuery(sql.toString());
//			list = this.findByNamedQuery("warnMonitorDao.findMonitorNameList");
		} catch (WFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/** 
	* @Title: findWCPrdtrnList 
	* @Description:(查询监控模块原交易 ) 
	* @return List     
	* @throws 于辉
	*/
	public List findWCPrdtrnList(){
		List list = null;
			try {
				list = this.findByNamedQuery("warnMonitorDao.findWCPrdtrnList");
			} catch (WFException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}
	/** 
	* @Title: updateMonitorName 
	* @Description:(修改监控人员 ) 
	* @throws 于辉
	*/
	public String updateMonitorName(WfDatCerditPrsn wfcp){
		String flag = "0";
		String[] paramName = {"id","sta"};
		Object[] paramValue = {wfcp.getId(),wfcp.getSta()};		
		try {
			this.execNameQueryAndNamedParamArr("warnMonitorDao.updateMonitorName", paramName, paramValue);
			flag = "1";
		} catch (WFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	/** 
	* @Title: addMonitorName 
	* @Description:(增加监控人员 ) 
	* @throws 于辉
	*/
	public String addMonitorName(WfDatCerditPrsn wfcp){
		String flag = "0";
		String[] paramName = {"id","prdcod","prdnam","sta","weid","peoplecode","orgno","parntcode","usrid","p_usrid","corgno","cporgno"};
		Object[] paramValue = {GUIDGenerator.generateId(),wfcp.getPrsncod(),wfcp.getPrsnnam(),
							   Constant.WrnMonitorStatus.WRNSTATUS_MONITOR.getValue(),wfcp.getId(),
							   wfcp.getPeoplecode(),wfcp.getOrgno(),wfcp.getParntcode(),wfcp.getUsrid(),wfcp.getP_usrid(),wfcp.getCorgno(),wfcp.getCporgno()};
		try {
			this.execNameQueryAndNamedParamArr("warnMonitorDao.addMonitorName", paramName, paramValue);
			flag = "1";
		} catch (WFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
