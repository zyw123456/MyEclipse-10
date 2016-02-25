package com.sinoway.rpt.dao;

import java.util.List;

import com.sinoway.common.util.Constant;
import com.sinoway.rpt.entity.WfDatCreditrptUtil;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.WFLogger;
import com.yzj.wf.common.db.dao.BaseDao;

public class RptDao extends BaseDao{
	private final static WFLogger logger = WFLogger.getLogger(RptDao.class.getName());
	
	/** 
	* @Title: addCredItRpt 
	* @Description: (新增征信报告) 
	* @throws 于辉
	*/
	public String addCredItRpt(WfDatCreditrptUtil wfdatUtil) {
		//  Auto-generated method stub
		String retFlag = "0";
			try {
				String[] paramName = {"rptid","redorptid","prsnnam","prsncod","telno","prdcod","prdnam","rptdte","rpttim",
									  "peoplecode","orgno","parntcode","reqadrr","credtyp","rpttyp","rptnam","fntjrn","rptsta",
									  "usrid","pusrid","corgno","cporgno","rptmoddte","rptmodtim","datcmitori"}; 
				Object[] paramValue = {wfdatUtil.getRptid(),wfdatUtil.getRedorptid(),wfdatUtil.getPrsnnam(),wfdatUtil.getPrsncod(),
									   wfdatUtil.getTelno(),wfdatUtil.getPrdcod(),wfdatUtil.getPrdnam(),wfdatUtil.getRptdte(),wfdatUtil.getRpttim(),
						               wfdatUtil.getPeoplecode(),wfdatUtil.getOrgno(),wfdatUtil.getParntcode(),wfdatUtil.getReqadrr(),wfdatUtil.getCredtyp(),
						               wfdatUtil.getRpttyp(),wfdatUtil.getRptnam(),wfdatUtil.getFntjrn(),Constant.RptStatus.STATIS_QUERYIN.getCode(),
						               wfdatUtil.getUsrid(),wfdatUtil.getPusrid(),wfdatUtil.getCorgno(),wfdatUtil.getCporgno(),wfdatUtil.getRptmoddte(),wfdatUtil.getRptmodtim(),wfdatUtil.getDatcmitori()};
			    this.execNameQueryAndNamedParamArr("rtpservice.addRpt", paramName, paramValue);
			    retFlag = "1";
			} catch (WFException e) {
				//  Auto-generated catch block
				logger.error(wfdatUtil+":报告库新增报错:",e);
				e.printStackTrace();
			}
			return retFlag;
	}
	
	/** 
	* @Title: findCredItRpt 
	* @Description: (查询产品数据) 
	* @param  prdcod 用户编号 
	* @return List     
	* @throws 于辉
	*/
	public List findCreadItRpt(String peoplecode,String appcod) {
		//  Auto-generated method stub
		List listRpt = null;
		try {
			String[] paramName = {"peoplecode","appcod","sta"};
			Object[] paramValue = {peoplecode,appcod == null?"":appcod,Constant.Sta.STA_NORMAl.getCode()};
		 listRpt =  this.findByNamedQueryAndNamedParamArr("rtpservice.findCreadItRpt", paramName, paramValue);
		} catch (WFException e) {
			//  Auto-generated catch block
			logger.error(peoplecode+":查询产品报错:",e);
			e.printStackTrace();
		}
		return listRpt;
	}
	/** 
	* @Title: findCredItRpt 
	* @Description: (查询产品数据去掉原有的产品) 
	* @param  prdcod 用户编号 
	* @return List     
	* @throws 于辉
	*/
	public List findCreadItRptOld(String peoplecode,String appcod,String oldprdcod) {
		//  Auto-generated method stub
		List listRpt = null;
		try {
			String[] paramName = {"peoplecode","appcod","sta","oldprdcod"};
			Object[] paramValue = {peoplecode,appcod == null?"":appcod,Constant.Sta.STA_NORMAl.getCode(),oldprdcod};
		 listRpt =  this.findByNamedQueryAndNamedParamArr("rtpservice.findCreadItRptOld", paramName, paramValue);
		} catch (WFException e) {
			//  Auto-generated catch block
			logger.error(peoplecode+":查询产品报错:",e);
			e.printStackTrace();
		}
		return listRpt;
	}
	public List findPrdDetil(String prdcod){
		List listPrdDetil = null;
		try {
			listPrdDetil = this.findByNamedQueryAndNamedParam("rtpservice.findPrdDetil", "prdcod", prdcod);
		} catch (WFException e) {
			logger.error(prdcod+":查询原交易报错:",e);
			e.printStackTrace();
		}
		return listPrdDetil;
	}
}
