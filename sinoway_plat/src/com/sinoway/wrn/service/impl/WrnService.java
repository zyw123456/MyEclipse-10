package com.sinoway.wrn.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sinoway.common.entity.PageUtil;
import com.sinoway.rpt.service.impl.WfDatCreditrptService;
import com.sinoway.wrn.dao.WrnDao;
import com.sinoway.wrn.service.IWrnService;
import com.yzj.wf.common.WFException;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;

public class WrnService implements IWrnService {

	private WrnDao wrnDao;
	private WfDatCreditrptService wdcpService;

	public void setWdcpService(WfDatCreditrptService wdcpService) {
		this.wdcpService = wdcpService;
	}

	public void setWrnDao(WrnDao wrnDao) {
		this.wrnDao = wrnDao;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	public List queryAbnormalWarns(Map<String, String> parameters,
			PageUtil pageModel, XPeopleInfo cp) {
		// 查询异常预警的结果
		List warnList = null;
		// 组织机构拼接字段
		String orgno = wdcpService.getCurOrgAndChildOrg(cp);
		// 日期
		String strdate;
		// 时间
		String strtime;

		try {
			// 监控模块列表
			String[] trnnamList = parameters.get("trnnam").split(",");
			// 查询权限内的异常预警记录sql语句
			StringBuffer sql = new StringBuffer(
					"select warn.warnid,warn.prsnnam,warn.prsncod,warn.warndte,warn.warntim,warn.loantyp,warnDtel.trnNam ");
			sql.append(" from WF_DAT_CERDITWARNDTEL warnDtel,WF_DAT_CERDITWARN warn, WF_DAT_CERDITWARNDTELREF warnDtelRef ");
			sql.append(" where warnDtel.warnDtlJrn = warnDtelRef.warnDtlJrn and warn.sta='1'");
			sql.append(" and warn.WARNID = warnDtelRef.warnId");
			sql.append(" and warn.orgno in (" + orgno + ")");

			// 根据条件匹配sql语句
			if (parameters.get("prsnnam") != null
					&& !parameters.get("prsnnam").equals(""))
				sql.append(" and warn.prsnnam like '%"
						+ parameters.get("prsnnam") + "%'");
			if (parameters.get("prsncod") != null
					&& !parameters.get("prsncod").equals(""))
				sql.append(" and warn.prsncod like '%"
						+ parameters.get("prsncod") + "%'");
			if (parameters.get("warntim") != null
					&& !parameters.get("warntim").equals("")) {
				strdate = parameters.get("warntim").substring(0, 8);
				strtime = parameters.get("warntim").substring(8, 14);
				sql.append(" and warn.warndte like '%" + strdate + "%'");
				sql.append(" and warn.warntim like '%" + strtime + "%'");
			}
			if (parameters.get("trnnam") != null
					&& !parameters.get("trnnam").equals("")) {
				sql.append(" and warnDtel.trncod in (");
				for (int i = 0; i < trnnamList.length; i++) {
					sql.append("'" + trnnamList[i] + "',");
				}
				sql = new StringBuffer(sql.substring(0, sql.length() - 1));
				sql.append(")");
			}
			if (parameters.get("loantype") != null
					&& !parameters.get("loantype").equals("")
					&& !parameters.get("loantype").equals("-1"))
				sql.append(" and warn.loantyp like '%"
						+ parameters.get("loantype") + "%'");
			if (parameters.get("orderby") != null
					&& !parameters.get("orderby").equals("")) {
				if (parameters.get("orderby").equals("1"))
					sql.append(" order by warn.warndte asc,warn.warntim asc");
				else
					sql.append(" order by warn.warndte desc,warn.warntim desc");
			}

			// 开始的记录行号
			int currentRow = pageModel.getFromIndex();
			warnList = (List) wrnDao.findbyPage(sql.toString(), currentRow,
					pageModel.getPageSize());
		} catch (WFException e) {
			e.printStackTrace();
		}
		return warnList;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	public int abnormalWarnsCount(Map<String, String> parameters, XPeopleInfo cp) {
		// 异常预警列表
		List warnList = null;
		// 日期
		String strdate;
		// 时间
		String strtime;
		// 查询记录条数
		int recordCount = 0;
		// 组织机构拼接字段
		String orgno = wdcpService.getCurOrgAndChildOrg(cp);

		try {
			// 监控模块列表
			String[] trnnamList = parameters.get("trnnam").split(",");
			// 查询权限内的异常预警记录sql语句
			StringBuffer sql = new StringBuffer("select count(warn.warnid) ");
			sql.append(" from WF_DAT_CERDITWARNDTEL warnDtel,WF_DAT_CERDITWARN warn, WF_DAT_CERDITWARNDTELREF warnDtelRef ");
			sql.append(" where warnDtel.warnDtlJrn = warnDtelRef.warnDtlJrn and warn.sta='1'");
			sql.append(" and warn.WARNID = warnDtelRef.warnId");
			sql.append(" and warn.orgno in (" + orgno + ")");
			// 根据条件匹配sql语句
			if (parameters.get("prsnnam") != null
					&& !parameters.get("prsnnam").equals(""))
				sql.append(" and warn.prsnnam like '%"
						+ parameters.get("prsnnam") + "%'");
			if (parameters.get("prsncod") != null
					&& !parameters.get("prsncod").equals(""))
				sql.append(" and warn.prsncod like '%"
						+ parameters.get("prsncod") + "%'");
			if (parameters.get("warntim") != null
					&& !parameters.get("warntim").equals("")) {
				strdate = parameters.get("warntim").substring(0, 8);
				strtime = parameters.get("warntim").substring(8, 14);
				sql.append(" and warn.warndte like '%" + strdate + "%'");
				sql.append(" and warn.warntim like '%" + strtime + "%'");
			}
			if (parameters.get("trnnam") != null
					&& !parameters.get("trnnam").equals("")) {
				sql.append(" and warnDtel.trncod in (");
				for (int i = 0; i < trnnamList.length; i++) {
					sql.append("'" + trnnamList[i] + "',");
				}
				sql = new StringBuffer(sql.substring(0, sql.length() - 1));
				sql.append(")");
			}
			if (parameters.get("loantype") != null
					&& !parameters.get("loantype").equals("")
					&& !parameters.get("loantype").equals("-1"))
				sql.append(" and warn.loantyp like '%"
						+ parameters.get("loantype") + "%'");

			warnList = (List) wrnDao.execSqlQuery(sql.toString());
			if (warnList != null)
				recordCount = Integer.valueOf(warnList.get(0).toString());
		} catch (WFException e) {
			e.printStackTrace();
		}
		return recordCount;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	public List queryTrnNameList(String peopleCode) {
		// 交易名称,交易码 列表
		List trnNameList = null;
		// 查询当前用户的 天警云 固定模板下的 交易名称,交易码 列表
		StringBuffer sql = new StringBuffer(
				"select prdtrn.TRNNAM,prdtrn.TRNCOD ");
		sql.append("from WF_CFGREF_COMPUSRPRD userprd,WF_CFGDEF_PRDINFO prdinfo,WF_CFGREF_PRDDETIL prddtl,WF_CFGDEF_PRDTRN prdtrn ");
		sql.append("where userprd.PRDCOD = prdinfo.PRDCOD and prdinfo.PRDCOD= prddtl.PRDCOD ");
		sql.append("and prddtl.TRNCOD = prdtrn.TRNCOD ");
		sql.append("and prdinfo.APPCOD ='wrn' and prdinfo.ISDEFULT='1' ");
		if (peopleCode != null && !peopleCode.equals("")) {
			sql.append("and userprd.PEOPLECODE='" + peopleCode + "'");
		}
		try {
			trnNameList = (List) wrnDao.execSqlQuery(sql.toString());
		} catch (WFException e) {
			e.printStackTrace();
		}
		return trnNameList;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	public List queryTrnList(String warnid) {
		// 交易名称,交易码 列表
		List trnList = null;
		// 查询当前预警报告的 原交易名称,交易码,出现的次数 列表
		StringBuffer sql = new StringBuffer(
				"select warndtl.TRNCOD,warndtl.TRNNAM,count(warndtl.TRNCOD) as num ");
		sql.append("from WF_DAT_CERDITWARNDTEL warndtl,WF_DAT_CERDITWARNDTELREF warnref ");
		sql.append("where warnref.WARNDTLJRN = warndtl.WARNDTLJRN ");
		if (!warnid.equals("") && warnid != null)
			sql.append("and warnref.WARNID = '" + warnid + "' ");
		sql.append("group by warndtl.TRNCOD,warndtl.TRNNAM ");
		sql.append("order by warndtl.TRNCOD ");
		try {
			trnList = (List) wrnDao.execSqlQuery(sql.toString());
		} catch (WFException e) {
			e.printStackTrace();
		}
		return trnList;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	public List queryWarnDetailTitle(String warnid) {
		// 交易码,要素编码,要素名称 列表
		List trnEleList = null;
		// 统计当前预警报告的 原交易码，出现的次数 列表
		StringBuffer sql = new StringBuffer(
				"select distinct warndtl.TRNCOD,outele.ELECOD,outele.ELENAM ");
		sql.append("from WF_DAT_CERDITWARNDTEL warndtl,WF_DAT_CERDITWARNDTELREF warnref,WF_CFGREF_TRNOUTELE outele ");
		sql.append("where warnref.WARNDTLJRN = warndtl.WARNDTLJRN and warndtl.TRNCOD = outele.TRNCOD ");
		if (!warnid.equals("") && warnid != null)
			sql.append("and warnref.WARNID = '" + warnid + "' ");
		sql.append("order by warndtl.TRNCOD ");
		try {
			trnEleList = (List) wrnDao.execSqlQuery(sql.toString());
		} catch (WFException e) {
			e.printStackTrace();
		}
		return trnEleList;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
	public List queryWarnDetailContent(String warnid, String trnCod) {
		// 预警数据内容列表
		List contentList = new ArrayList();
		// 预警明细列表
		List warnDtls = null;
		// 统计当前预警报告的 原交易码，出现的次数 列表
		StringBuffer sql = new StringBuffer("select warndtl.WARNDTLS ");
		sql.append("from WF_DAT_CERDITWARNDTEL warndtl,WF_DAT_CERDITWARNDTELREF warnref ");
		sql.append("where warnref.WARNDTLJRN = warndtl.WARNDTLJRN ");
		if (!warnid.equals("") && warnid != null)
			sql.append("and warnref.WARNID = '" + warnid + "' ");
		if (!trnCod.equals("") && trnCod != null)
			sql.append("and warndtl.TRNCOD = '" + trnCod + "' ");
		sql.append("order by warndtl.TRNCOD ");
		try {
			warnDtls = (List) wrnDao.execSqlQuery(sql.toString());
		} catch (WFException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < warnDtls.size(); i++) {
			String fileName = warnDtls.get(i).toString();
			File file = new File(fileName);
			String result = "";
			// 判断文件是否存在
			if (file.isFile() && file.exists()) {
				try {
					// 构造一个BufferedReader类来读取文件
					BufferedReader br = new BufferedReader(new FileReader(file));
					String s = null;
					while ((s = br.readLine()) != null) {
						// 使用readLine方法，一次读一行
						result = result + s;
					}
					contentList.add(result);
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return contentList;
	}

}
