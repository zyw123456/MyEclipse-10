package com.sinoway.common.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sinoway.common.dao.HPDao;
import com.sinoway.common.exception.HPException;
import com.sinoway.common.service.IHPService;
import com.sinoway.common.util.Constant;
import com.sinoway.common.util.DateUtil;
import com.sinoway.rpt.entity.WfDatCreditrpt;
import com.sinoway.rpt.entity.WfDatCreditrptUtil;
import com.sinoway.wrn.entity.WfDatCerditWarnDtel;
import com.yzj.wf.common.WFException;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;

public class HPService implements IHPService {
	private HPDao hPDao;

	
	public HPDao gethPDao() {
		return hPDao;
	}

	public void sethPDao(HPDao hPDao) {
		this.hPDao = hPDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public long getAccAlarmNo(XPeopleInfo curPeople) throws WFException {
		return hPDao.getAccAlarmNo(curPeople);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public long getAccAlarmDetailNo(XPeopleInfo curPeople) throws WFException{
		return hPDao.getAccAlarmDetailNo( curPeople);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public long getPlatAlarmDetailNo() throws WFException {
		return hPDao.getPlatAlarmDetailNo();
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public long getPlatAlarmNo() throws WFException {
		return hPDao.getPlatAlarmNo();
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List<WfDatCerditWarnDtel> getTopPlatAlarmDetail(int maxResults,XPeopleInfo curPeople)
			throws HPException, WFException {
		List<WfDatCerditWarnDtel> list = hPDao.getTopAccAlarm(maxResults,curPeople);
		List<WfDatCerditWarnDtel> warnlist = new ArrayList<WfDatCerditWarnDtel>();
		for (WfDatCerditWarnDtel warnDtel : list) {
			String warndte = warnDtel.getWarnDte();
			String warntim = warnDtel.getWarnTim();
			String time = warntim.substring(0, 6);
			String hour = time.substring(0, 2);
			String min = time.substring(2, 4);
			String second = time.substring(4);
			time = hour + ":" + min + ":" + second;
			String date = warndte;
			String year = date.substring(0, 4);
			String month = date.substring(4, 6);
			String day = date.substring(6);
			date = year+"."+month+"."+day;
			warnDtel.setWarnTim(date + " " + time);
			warnlist.add(warnDtel);
		}
		return warnlist;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List<WfDatCreditrptUtil> getTopAccRpt(int maxResults,
			XPeopleInfo curPeople) throws HPException, WFException {
		List<WfDatCreditrpt> list =  hPDao.getTopAccRpt(maxResults, curPeople);
		return formatList(list);
	}

	
	/**
	 * 格式化list 返回格式化后的值
	 * @param list
	 * @return
	 */
	private List<WfDatCreditrptUtil> formatList(List<WfDatCreditrpt> list){
		List<WfDatCreditrptUtil> rptlist = new ArrayList<WfDatCreditrptUtil>();
		for (WfDatCreditrpt rpt : list) {
			WfDatCreditrptUtil rptUtil = new WfDatCreditrptUtil();
			rptUtil.setRptid(rpt.getRptid());
			rptUtil.setPrsnnam(rpt.getPrsnnam());
			String prsncod = rpt.getPrsncod();
			if(null != prsncod && prsncod.length()>14){
				prsncod = prsncod.substring(0, 3)+"***********"+prsncod.substring(prsncod.length()-4);
			}
			rptUtil.setPrsncod(prsncod);
			rptUtil.setRpttyp(rpt.getRpttyp());
			
			rptUtil.setRptmodtim(DateUtil.formatDateStrYMDHMSS2YMDHMS17LenString(rpt.getRptmoddte()+rpt.getRptmodtim()));
			if(Constant.RptTyp.RPTTYP_VERIFIED.getCode().equals(rpt.getRpttyp())){
				rptUtil.setRpttyp(Constant.RptTyp.RPTTYP_VERIFIED.getValue());
			}else if(Constant.RptTyp.RPTTYP_FRAUD.getCode().equals(rpt.getRpttyp())){
				rptUtil.setRpttyp(Constant.RptTyp.RPTTYP_FRAUD.getValue());
			}else if(Constant.RptTyp.RPTTYP_PRSN_CREDIT.getCode().equals(rpt.getRpttyp())){
				rptUtil.setRpttyp(Constant.RptTyp.RPTTYP_PRSN_CREDIT.getValue());
			}
			rptlist.add(rptUtil);
		}
		return rptlist;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	public <T> LinkedList<T> getTop(Class<T> T, String dateName, String timeName,int maxResults)
			throws HPException {
		return hPDao.getTop(T, dateName, timeName, maxResults);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List<WfDatCerditWarnDtel> getTopPlatAlarmDetail(int maxResults) throws HPException {
		return null;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List<WfDatCreditrptUtil> getTopPlatRpt(int topNMaxResult) throws HPException {
		List<WfDatCreditrpt> list = getTop(WfDatCreditrpt.class, WfDatCreditrpt.CREDITRPT_RPTMODDTE, WfDatCreditrpt.CREDITRPT_RPTMODTIM, topNMaxResult);
		return formatList(list);
	}
	
	
		
}
