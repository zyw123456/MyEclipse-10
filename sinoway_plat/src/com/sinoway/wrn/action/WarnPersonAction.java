package com.sinoway.wrn.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.Request;

import com.sinoway.common.util.Constant;
import com.sinoway.wrn.entity.WfDatCerditPrsn;
import com.sinoway.wrn.service.IWarnPersonService;
import com.sinoway.wrn.service.impl.WarnPersonService;
import com.yzj.wf.base.action.BaseAction;
import com.yzj.wf.common.ErrCode;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;

/**
 * 
 * @author LBC
 * 时间：2016-1-5下午8:05:19
 * 说明：添加预警人员
 */
public class WarnPersonAction extends  BaseAction {	
	private static final long serialVersionUID = 1L;
	private IWarnPersonService warnPersonService;

	
	public void setWarnPersonService(WarnPersonService warnPersonService) {
		this.warnPersonService = warnPersonService;
	}
	private String prsnnam;
	private String prsncod;
	private String oldprdcod;
	private String loansrtdte;
	private String loanenddte;
	private String telno;
	private String loantyp;
	private String loanamt;
	private String loanlmt;
	private String repaytyp;
	private String repayamt;
	private String prdcod;
	private String repaydte;
	private List list;
	private String errCode;
	private String bugeCode;
	public String getBugeCode() {
		return bugeCode;
	}
	public void setBugeCode(String bugeCode) {
		this.bugeCode = bugeCode;
	}
	public String getPrsnnam() {
		return prsnnam;
	}
	public void setPrsnnam(String prsnnam) {
		this.prsnnam = prsnnam;
	}
	public String getPrsncod() {
		return prsncod;
	}
	public void setPrsncod(String prsncod) {
		this.prsncod = prsncod;
	}
	public String getOldprdcod() {
		return oldprdcod;
	}
	public void setOldprdcod(String oldprdcod) {
		this.oldprdcod = oldprdcod;
	}
	public String getLoansrtdte() {
		return loansrtdte;
	}
	public void setLoansrtdte(String loansrtdte) {
		this.loansrtdte = loansrtdte;
	}
	public String getLoanenddte() {
		return loanenddte;
	}
	public void setLoanenddte(String loanenddte) {
		this.loanenddte = loanenddte;
	}
	
	public String getLoantyp() {
		return loantyp;
	}
	public void setLoantyp(String loantyp) {
		this.loantyp = loantyp;
	}
	public String getLoanamt() {
		return loanamt;
	}
	public void setLoanamt(String loanamt) {
		this.loanamt = loanamt;
	}
	public String getLoanlmt() {
		return loanlmt;
	}
	public void setLoanlmt(String loanlmt) {
		this.loanlmt = loanlmt;
	}

	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public String getRepaytyp() {
		return repaytyp;
	}
	public void setRepaytyp(String repaytyp) {
		this.repaytyp = repaytyp;
	}
	public String getRepayamt() {
		return repayamt;
	}
	public void setRepayamt(String repayamt) {
		this.repayamt = repayamt;
	}
	public String getRepaydte() {
		return repaydte;
	}
	public void setRepaydte(String repaydte) {
		this.repaydte = repaydte;
	}
	public String getPrdcod() {
		return prdcod;
	}
	public void setPrdcod(String prdcod) {
		this.prdcod = prdcod;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	/**
	 * 得到监控策略
	 * @return Json
	 */
	public String loadPrdcod(){
		XPeopleInfo cp= this.getCurrentPeople();
		String Code=cp.getPeopleCode();
		String Pcode=cp.getParntCode();
//		查询当前用户的所有策略
//		得到天警云对应的编码
		String Appcode=Constant.AppCod.APPINFOCODEE_WRN.getCode();
//		测试用kill
		List listPolicy= warnPersonService.findPolicyList(Appcode,Code);		
		if (null==listPolicy||listPolicy.size()==0) {
			errCode="您的策略可能被主账号清空,或者本来就不存在啦！！！";
		}
			this.setList(listPolicy);
			return SUCCESS;
	}
	/**
	 * 添加监控对象， 
	 * @return
	 */
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=false, rollbackFor=Exception.class)
	public String addWarnPerson(){
		WfDatCerditPrsn wfDatCerditPrsn=new WfDatCerditPrsn();
		wfDatCerditPrsn.setPrsnnam(prsnnam);
//		预留下的字段
//		wfDatCerditPrsn.setCredtyp(credtyp);
//		wfDatCerditPrsn.setCredtno(credtno);
		wfDatCerditPrsn.setPrsncod(prsncod);
		wfDatCerditPrsn.setLoantyp(loantyp);
		wfDatCerditPrsn.setLoanamt(loanamt);
		wfDatCerditPrsn.setLoanlmt(loanlmt);
		wfDatCerditPrsn.setTelno(telno);
		wfDatCerditPrsn.setRepaydte(repaydte);
		wfDatCerditPrsn.setRepaytyp(repaytyp);
		wfDatCerditPrsn.setRepayamt(repayamt);
		XPeopleInfo cpp= this.getCurrentPeople();
		wfDatCerditPrsn.setPrdcod(prdcod);
//		正常状态为1
		String sta="1";
		String Appcode=Constant.AppCod.APPINFOCODEE_WRN.getCode();
		List pNameList=warnPersonService.findPrdnamByprdcod(prdcod,sta,Appcode);
//		如果返回的结果pNameList，size()==0；说明产品已经下架，不可保存
		if (null==pNameList||pNameList.size()==0) {
			errCode="产品已经下架，不可保存";
			bugeCode="产品已经下架，不可保存";
			return SUCCESS;
		}
		wfDatCerditPrsn.setPrdnam(pNameList.get(0).toString());
		wfDatCerditPrsn.setLoanenddte(loanenddte);
		wfDatCerditPrsn.setLoansrtdte(loansrtdte);
		wfDatCerditPrsn.setPeoplecode(cpp.getPeopleCode());
		wfDatCerditPrsn.setOrgno(cpp.getOrgNo());
		wfDatCerditPrsn.setParntcode(cpp.getParntCode());	
		wfDatCerditPrsn.setSta("1");
		wfDatCerditPrsn.setUsrid(cpp.getUsrId());
		wfDatCerditPrsn.setP_usrid(cpp.getP_usrId());
		wfDatCerditPrsn.setOrgno(cpp.getOrgNo());
		wfDatCerditPrsn.setCorgno(cpp.getCorgNo());
		wfDatCerditPrsn.setCporgno(cpp.getCporgNo());
		warnPersonService.addWranPerson(wfDatCerditPrsn);
		return SUCCESS;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	
}
