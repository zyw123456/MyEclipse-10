/**   
* @Title: FadAction.java 
* @Package com.sinoway.fad.action 
* @Description: TODO(用一句话描述该文件做什么) 
* @author Leo May the source be with you!   
* @date 2015-12-15 下午2:24:52 
* @version V1.0   
*/
package com.sinoway.common.action;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONObject;


import com.sinoway.common.entity.HPFrontObj;
import com.sinoway.common.entity.HPRefreshObj;
import com.sinoway.common.exception.HPException;
import com.sinoway.common.service.HPCache;
import com.sinoway.common.util.Constant;
import com.sinoway.common.util.DateUtil;
import com.sinoway.common.util.JsonBinder;
import com.sinoway.rpt.entity.WfDatCreditrpt;
import com.sinoway.rpt.entity.WfDatCreditrptUtil;
import com.sinoway.wrn.entity.WfDatCerditWarnDtel;
import com.yzj.wf.base.action.common.WebResponseJson;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.WFLogger;

/** 
 * @ClassName: FadAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Leo May the source be with you! 
 * @date 2015-12-15 下午2:24:52 
 *  
 */
public class HPAction extends HPBaseAction {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = -2459960882399932855L;
	private WFLogger logger = WFLogger.getLogger(HPAction.class);
	private String frontObjStr;
	private String versionNumber;
	
	
	
	public String getFrontObjStr() {
		return frontObjStr;
	}

	public void setFrontObjStr(String frontObjStr) {
		this.frontObjStr = frontObjStr;
	}


	public String refreshHP() throws HPException, WFException, IllegalAccessException, InvocationTargetException {
		String userID = null == super.getParentPeopleCode()? getCurrentPeopleCode() : super.getParentPeopleCode();
		//从缓存获取需要刷新的数据
		HPRefreshObj hPRefreshObj = HPCache.getInstance().loadHPRefreshObj(userID);
		//比较对象vcode,如果vcode未发生变化则返回属性为空
		if(hPRefreshObj.getAcc_alarm_v().equals(super.getAccAlarmV())) {
			hPRefreshObj.setAcc_alarm(null);
		} 
		
		if(hPRefreshObj.getAcc_report_v().equals(super.getAccRptV())) {
			hPRefreshObj.setAcc_report(null);
		}
		
		if(hPRefreshObj.getPlat_alarmDetail_v().equals(super.getPlatAlarmDetailV())) {
			hPRefreshObj.setPlat_alarmDetail(null);
		}
		WebResponseJson webResponseJson = WebResponseJson.getNormalInstance();
		webResponseJson.setCode("001");
		webResponseJson.setData(hPRefreshObj);
		super.ajaxResponseMessage(webResponseJson);
		return null;
	}
	/** 
	* @Title: getInitData
	* @Description: 获取初始化统计数据
	* @return string    返回类型 
	*/
	public String getInitData(){
	
		String userId = getParentPeopleCode()==null?getCurrentPeopleCode():getParentPeopleCode();
		HPRefreshObj hpobj;
		HPFrontObj frontObj = new HPFrontObj();
		Map<String,String> frontMap = new HashMap<String,String>();
		String versionNo=null;
		try {
			hpobj = HPCache.getInstance().getCacheHPUser(userId + "_" + DateUtil.getCurrentDate8Len());
			if(null == hpobj){
				hpobj = HPCache.getInstance().loadHPRefreshObj(userId);
			}
			long accAlarmNo =  hpobj.getAcc_alarmNo();
			long accAlarmDetailNo =  hpobj.getAcc_alarmDetailNo();
			frontMap.put("accMonitor", String.valueOf(accAlarmNo));
			frontMap.put("accAlarm",String.valueOf(accAlarmDetailNo));
			long platAlarmNo =  hpobj.getPlat_alarmNo();
			long platAlarmDetailNo =  hpobj.getPlat_alarmDetailNo();
			frontMap.put("platMonitor", String.valueOf(platAlarmNo));
			frontMap.put("platAlarm", String.valueOf(platAlarmDetailNo));
			frontMap.put("plat_alarmNo_v", hpobj.getPlat_alarmNo_v());
			frontMap.put("acc_report_v",hpobj.getAcc_report_v());
			frontMap.put("acc_alarmNo_v",hpobj.getAcc_alarm_v());
			frontMap.put("plat_alarmDetail_v",hpobj.getPlat_alarmDetail_v());
			frontMap.put("acc_dailyDataUsage_v", hpobj.getAcc_dailyDataUsage_v());
			List<WfDatCreditrptUtil> rptlist = formatList(hpobj.getPlat_creditReport());
			List<WfDatCerditWarnDtel> warnlist = hpobj.getPlat_alarmDetail();
			frontObj.setFrontMap(frontMap);
			frontObj.setWarnlist(warnlist);
			frontObj.setRptlist(rptlist);
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			frontObj.setErrorCode("查询统计数据失败!");
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
			frontObj.setErrorCode("查询统计数据失败!");
		} catch (HPException e1) {
			e1.printStackTrace();
			frontObj.setErrorCode("查询统计数据失败!");
		} catch (WFException e1) {
			e1.printStackTrace();
			frontObj.setErrorCode("查询统计数据失败!");
		}
		frontObjStr = JsonBinder.toJson(frontObj);
		return SUCCESS;
	}
	/** 
	* @Title: getAccAlarmNo
	* @Description: 获取账号监控统计
	* @return string    返回类型 
	*/
	public String getAccAlarmNo(){
	
		String userId = getParentPeopleCode()==null?getCurrentPeopleCode():getParentPeopleCode();
		HPRefreshObj hpobj;
		HPFrontObj frontObj = new HPFrontObj();
		Map<String,String> frontMap = new HashMap<String,String>();
		String versionNo=null;
		try {
			hpobj = HPCache.getInstance().getCacheHPUser(userId + "_" + DateUtil.getCurrentDate8Len());
			if(null == hpobj){
				hpobj = HPCache.getInstance().loadHPRefreshObj(userId);
			}
		
			long accAlarmNo =  hpobj.getAcc_alarmNo();
			long accAlarmDetailNo =  hpobj.getAcc_alarmDetailNo();
			frontMap.put("accMonitor", String.valueOf(accAlarmNo));
			frontMap.put("accAlarm",String.valueOf(accAlarmDetailNo));
			frontObj.setFrontMap(frontMap);
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			frontObj.setErrorCode("查询账号监视统计数据失败!");
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
			frontObj.setErrorCode("查询账号监视统计数据失败!");
		} catch (HPException e1) {
			e1.printStackTrace();
			frontObj.setErrorCode("查询账号监视统计数据失败!");
		} catch (WFException e1) {
			e1.printStackTrace();
			frontObj.setErrorCode("查询账号监视统计数据失败!");
		}
		frontObjStr = JsonBinder.toJson(frontObj);
		return SUCCESS;
	}
	
	
	/** 
	* @Title: getPlatAlarmNo
	* @Description: 获取平台监控预警统计
	* @param @param userID
	* @param @return    设定文件 
	* @return string    返回类型 
	* @throws 
	*/
	public String getPlatAlarmNo(){
		String userId = getParentPeopleCode()==null?getCurrentPeopleCode():getParentPeopleCode();
		HPRefreshObj hpobj;
		HPFrontObj frontObj = new HPFrontObj();
		Map<String,String> frontMap = new HashMap<String,String>();
		String versionNo = null;
		try {
			hpobj = HPCache.getInstance().getCacheHPUser(userId + "_" + DateUtil.getCurrentDate8Len());
			if(null == hpobj){
				hpobj = HPCache.getInstance().loadHPRefreshObj(userId);
			}
			
			long platAlarmNo =  hpobj.getPlat_alarmNo();
			long platAlarmDetailNo =  hpobj.getPlat_alarmDetailNo();
			frontMap.put("platMonitor", String.valueOf(platAlarmNo));
			frontMap.put("platAlarm", String.valueOf(platAlarmDetailNo));
			
			frontObj.setFrontMap(frontMap);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			frontObj.setErrorCode("查询平台监视统计数据失败!");
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			frontObj.setErrorCode("查询平台监视统计数据失败!");
		} catch (HPException e) {
			e.printStackTrace();
			frontObj.setErrorCode("查询平台监视统计数据失败!");
		} catch (WFException e) {
			e.printStackTrace();
			frontObj.setErrorCode("查询平台监视统计数据失败!");
		} 
		frontObjStr = JsonBinder.toJson(frontObj);
		return SUCCESS;
	}
	
	
	
	/**
	* @Title: getTopAccRpt
	* @Description: 获取账号实时报告更新数据
	* @param idName    排序字段
	* @throws 
	*/
	public String getTopAccRpt() {
		String userId = getParentPeopleCode()==null?getCurrentPeopleCode():getParentPeopleCode();
		HPRefreshObj hpobj;
		HPFrontObj frontObj = new HPFrontObj();
		String versionNo = null;
		try {
			hpobj = HPCache.getInstance().getCacheHPUser(userId + "_" + DateUtil.getCurrentDate8Len());
			if(null == hpobj){
				hpobj = HPCache.getInstance().loadHPRefreshObj(userId);
			}
			List<WfDatCreditrptUtil> rptlist = formatList(hpobj.getPlat_creditReport());
			frontObj.setRptlist(rptlist);
			
		} catch (HPException e) {
			e.printStackTrace();
			frontObj.setErrorCode("查询账号实时报告更新数据失败!");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			frontObj.setErrorCode("查询账号实时报告更新数据失败!");
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			frontObj.setErrorCode("查询账号实时报告更新数据失败!");
		} catch (WFException e) {
			e.printStackTrace();
			frontObj.setErrorCode("查询账号实时报告更新数据失败!");
		}  
		frontObjStr = JsonBinder.toJson(frontObj);
		return SUCCESS;
	}
	/** 
	* @Title: getTopPlatAlarmDetail
	* @Description: 获取实时预警更新数据
	* @param maxResults  获取最新更新数据的条数
	* @param idName    排序字段
	* @throws 
	*/
	public String getTopPlatAlarmDetail(){
		String userId = getParentPeopleCode()==null?getCurrentPeopleCode():getParentPeopleCode();
		HPRefreshObj hpobj =null;
		HPFrontObj frontObj = new HPFrontObj();
		String versionNo = null;
		try {
			hpobj = HPCache.getInstance().getCacheHPUser(userId + "_" + DateUtil.getCurrentDate8Len());
			if(null == hpobj){
				hpobj = HPCache.getInstance().loadHPRefreshObj(userId);
			}
			List<WfDatCerditWarnDtel> warnlist = hpobj.getPlat_alarmDetail();
			frontObj.setWarnlist(warnlist);
		} catch (HPException e) {
			e.printStackTrace();
			frontObj.setErrorCode("查询实时预警更新数据失败!");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			frontObj.setErrorCode("查询实时预警更新数据失败!");
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			frontObj.setErrorCode("查询实时预警更新数据失败!");
		} catch (WFException e) {
			e.printStackTrace();
			frontObj.setErrorCode("查询实时预警更新数据失败!");
		}
		frontObjStr = JsonBinder.toJson(frontObj);
		return SUCCESS;
	}

	/**
	 * 反欺诈云流量统计
	 * @return
	 * @throws WFException 
	 * @throws HPException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public String antiFraudFlowStatistics(){
		String userId = getParentPeopleCode()==null?getCurrentPeopleCode():getParentPeopleCode();
		HPRefreshObj hpobj =null;
		try {
			hpobj = HPCache.getInstance().getCacheHPUser(userId + "_" + DateUtil.getCurrentDate8Len());
			if(null == hpobj){
				hpobj = HPCache.getInstance().loadHPRefreshObj(userId);
			}
		} catch (IllegalAccessException e) {
			logger.error("读取错误信息", e);  
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			logger.error("读取错误信息", e);
			e.printStackTrace();
		} catch (HPException e) {
			logger.error("读取错误信息", e);  
			e.printStackTrace();
		} catch (WFException e) {
			logger.error("读取错误信息", e);  
			e.printStackTrace();
		}
		JSONObject map = new JSONObject();
		map.put("porvingTerraceFlow", String.valueOf(hpobj.getPlat_verifiedUsage()));//验证平台使用流量
		map.put("individualAbnormalFlow", String.valueOf(hpobj.getPlat_fraudUsage()));//个人异常使用流量
		ajaxResponseMessage(map.toString());
		return null;
		
	}
	/**
	 * 个人征信报告流量统计
	 * @return
	 * @throws WFException 
	 * @throws HPException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public String personalReportFlowStatistics()  {  
		String userId = getParentPeopleCode()==null?getCurrentPeopleCode():getParentPeopleCode();
		HPRefreshObj hpobj =null;
		try {
			hpobj = HPCache.getInstance().getCacheHPUser(userId + "_" + DateUtil.getCurrentDate8Len());
			if(null == hpobj){
				hpobj = HPCache.getInstance().loadHPRefreshObj(userId);
			}
		} catch (IllegalAccessException e) {
			logger.error("读取错误信息", e);
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			logger.error("读取错误信息", e);
			e.printStackTrace();
		} catch (HPException e) {
			logger.error("读取错误信息", e);
			e.printStackTrace();
		} catch (WFException e) {
			logger.error("读取错误信息", e);
			e.printStackTrace();
		}
		JSONObject map = new JSONObject();
		map.put("platformUploadRpt", String.valueOf(hpobj.getDatcmitori_platUsage()));//平台上传报告
		map.put("interfaceUploadRpt", String.valueOf(hpobj.getDatcmitori_interfaceUsage()));//接口上传报告
		map.put("publicUploadRpt", String.valueOf(hpobj.getDatcmitori_wechatUsage()));//公从号上传报告(即微信数据)
		ajaxResponseMessage(map.toString());
		return null;
	}
	
	/**
	 * 首页底部的流量统计
	 * @return
	 * @throws WFException 
	 * @throws HPException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public String homePageFlowStatistics(){
		String userId = getParentPeopleCode()==null?getCurrentPeopleCode():getParentPeopleCode();
		HPRefreshObj hpobj =null;
		try {
			hpobj = HPCache.getInstance().getCacheHPUser(userId + "_" + DateUtil.getCurrentDate8Len());
			if(null == hpobj){
				hpobj = HPCache.getInstance().loadHPRefreshObj(userId);
			}
		} catch (IllegalAccessException e) {
			logger.error("读取错误信息", e);
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			logger.error("读取错误信息", e);
			e.printStackTrace();
		} catch (HPException e) {
			logger.error("读取错误信息", e);
			e.printStackTrace();
		} catch (WFException e) {
			logger.error("读取错误信息", e);  
			e.printStackTrace();
		}
		JSONObject map = new JSONObject();
		long[] nearlyThreeDaysData = null == hpobj.getAcc_recent3DaysUsage()? new long[]{0,0,0}:hpobj.getAcc_recent3DaysUsage();
		map.put("todayFlow", hpobj.getAcc_dailyDataUsage());//今日流量
		map.put("yesterdayData", nearlyThreeDaysData[2]);//近三日流量第一天流量
		map.put("berfareYesterdayData", nearlyThreeDaysData[1]);//近三日流量第二天流量
		map.put("threeDaysAgoData", nearlyThreeDaysData[0]);//近三日流量第三天流量
		ajaxResponseMessage(map.toString());
		return null;
	}

	
	
	/**
	 * 显示底部流量统计
	 * @return
	 * @throws WFException 
	 * @throws HPException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public String showUsages() throws HPException, WFException, IllegalAccessException, InvocationTargetException{
		String userId = getParentPeopleCode()==null?getCurrentPeopleCode():getParentPeopleCode();
		HPRefreshObj hpobj = HPCache.getInstance().getCacheHPUser(userId + "_" + DateUtil.getCurrentDate8Len());
		if(null == hpobj){
			hpobj = HPCache.getInstance().loadHPRefreshObj(userId);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dailyDataUsage", hpobj.getAcc_dailyDataUsage());
		map.put("recent3DaysUsage", null == hpobj.getAcc_recent3DaysUsage()? new long[]{0,0,0}:hpobj.getAcc_recent3DaysUsage());
		ajaxResponseMessage(map);
		return null;
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

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}
	
}