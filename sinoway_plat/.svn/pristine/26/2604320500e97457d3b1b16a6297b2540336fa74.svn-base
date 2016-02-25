package com.sinoway.wrn.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.ActionSupport;
import com.sinoway.app.service.IAppService;
import com.sinoway.common.entity.PageModel;
import com.sinoway.common.entity.PageUtil;
import com.sinoway.wrn.service.IWrnService;
import com.sinoway.wrn.service.impl.WrnService;
import com.yzj.wf.base.action.BaseAction;
import com.yzj.wf.common.WFException;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;
/**
 * 天警云模块
 * @author wanglongmei
 *
 */
public class WrnManagerAction extends BaseAction {

	private static final long serialVersionUID = -1868564700178299710L;
	private IAppService appService;

	private IWrnService wrnService;

	private String prsnnam;
	private String prsncod;
	private String warntim;
	private String trnnam;
	private String currentPage;
	private String recordCount;
	private String pageSize;
	private String orderby;
	private String loantype;
	private String warnid;
	private String trnCod;
	
	private List list;
	private List trnEleList;
	private PageUtil pageModel;
	private List contentList;
	/**
	 * 查询异常预警列表
	 * @return
	 */
	public String searchAbnormalWarns() {
		try {
			XPeopleInfo cp = this.getCurrentPeople();
			pageModel = new PageUtil();
			if (recordCount == null || recordCount.equals(""))
				pageModel.setRecordCount(0);
			else
				pageModel.setRecordCount(Integer.parseInt(recordCount));
			if (currentPage == null || currentPage.equals(""))
				pageModel.setCurrentPage(1);
			else
				pageModel.setCurrentPage(Integer.parseInt(currentPage));
			
			Map<String,String> parameters = new HashMap<String,String>();
			parameters.put("prsnnam", prsnnam);
			parameters.put("prsncod", prsncod);
			parameters.put("warntim", warntim);
			parameters.put("trnnam", trnnam);
			parameters.put("orderby", orderby);
			parameters.put("loantype", loantype);
			List warnList = wrnService.queryAbnormalWarns(parameters, pageModel,cp);
			this.setList(warnList);
			pageModel.setRecordCount(wrnService.abnormalWarnsCount(parameters,cp));
			pageModel.setPageCount(pageModel.getPageCount());
			this.setPageModel(pageModel);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询原交易名称列表
	 * @return
	 */
	public String queryTrnNameList(){
		try {
			XPeopleInfo cp = this.getCurrentPeople();
			this.setList(wrnService.queryTrnNameList(cp.getPeopleCode()));
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 查询征信预警产品明细信息：   原交易信息分类，原交易码分类统计，及详细信息查询
	 * @return
	 */
	public String queryTrnList(){
		try {
			XPeopleInfo cp = this.getCurrentPeople();
			// 原交易信息分类，原交易码分类统计
			this.setList(wrnService.queryTrnList(warnid));
			// 原交易信息分类 详细信息表头获取
			this.setTrnEleList(wrnService.queryWarnDetailTitle(warnid));
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询一个预警报告下的 每种原交易下的 列表展现内容
	 * @return
	 */
	public String queryWarnDetailContent(){
		try {
		this.setContentList(wrnService.queryWarnDetailContent(warnid, trnCod));
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public void setAppService(IAppService appService) {
		this.appService = appService;
	}

	public void setWrnService(IWrnService wrnService) {
		this.wrnService = wrnService;
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

	public String getWarntim() {
		return warntim;
	}

	public void setWarntim(String warntim) {
		this.warntim = warntim;
	}

	public String getTrnnam() {
		return trnnam;
	}

	public void setTrnnam(String trnnam) {
		this.trnnam = trnnam;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public PageUtil getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageUtil pageModel) {
		this.pageModel = pageModel;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getLoantype() {
		return loantype;
	}

	public void setLoantype(String loantype) {
		this.loantype = loantype;
	}

	public String getWarnid() {
		return warnid;
	}

	public void setWarnid(String warnid) {
		this.warnid = warnid;
	}

	public List getTrnEleList() {
		return trnEleList;
	}

	public void setTrnEleList(List trnEleList) {
		this.trnEleList = trnEleList;
	}

	public List getContentList() {
		return contentList;
	}

	public void setContentList(List contentList) {
		this.contentList = contentList;
	}

	public String getTrnCod() {
		return trnCod;
	}

	public void setTrnCod(String trnCod) {
		this.trnCod = trnCod;
	}
	
}
