package com.sinoway.common.aop;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import org.aspectj.lang.JoinPoint;

import com.sinoway.common.entity.HPRefreshObj;
import com.sinoway.common.exception.HPException;
import com.sinoway.common.service.HPCache;
import com.sinoway.common.util.Constant;
import com.sinoway.common.util.Constant.DatCmitori;
import com.sinoway.common.util.Constant.RptTyp;
import com.sinoway.common.util.DateUtil;
import com.sinoway.common.util.HPVCodeGenerator;
import com.sinoway.rpt.entity.WfDatCreditrpt;
import com.sinoway.wrn.entity.WfDatCerditwarn;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.WFLogger;


/**
 * 修改HPCache
 * @author miles
 *
 */
public class AopHpCache {
	
	private WFLogger logger = WFLogger.getLogger(AopHpCache.class);
	
	
	 //方法执行后返回调用  
	/**
	 * 拦截核心系统返回云平台的切面
	 * @param point
	 * @param result
	 * @throws Throwable
	 */
    public synchronized void   runOnAfterReturning(JoinPoint point,Object result) throws Throwable{  
    	logger.debug("开始处理保存切面方法:  "+ point.getSignature().toLongString());    	
		
    	Object[] obj = point.getArgs();
		if(obj.length == 1){
			if(null == obj[0]){
				logger.error("传入切面参数为空: " + point.getSignature().toShortString());
				return;
			}else if (obj[0] instanceof WfDatCreditrpt) {
				logger.debug("处理报告实体类:  "+ WfDatCreditrpt.class);  
				WfDatCreditrpt rpt = (WfDatCreditrpt) obj[0];
				String userId = rpt.getParntcode();
				String currDate = DateUtil.getCurrentDate8Len();
				String key = userId + "_" + currDate;
				//TODO
				HPRefreshObj refreshObj = HPCache.getInstance().getCacheHPUser(key);
				
				//平台日使用流量统计
				refreshObj.setAcc_dailyDataUsage(refreshObj.getAcc_dailyDataUsage()+ Constant.getUsagePointIncrement());
				//平台近3日流量
				long[] days3usage = refreshObj.getAcc_recent3DaysUsage();
				if(null != days3usage && days3usage.length == 3){
					days3usage[2] = days3usage[2] + Constant.getUsagePointIncrement();
				}
				refreshObj.setAcc_recent3DaysUsage(refreshObj.getAcc_recent3DaysUsage());
				//设置日使用统计版本
				refreshObj.setAcc_dailyDataUsage_v(HPVCodeGenerator.getVCode());
				
				
				if(RptTyp.RPTTYP_VERIFIED.getCode().equals(rpt.getRpttyp())){
					refreshObj.setPlat_verifiedUsage(refreshObj.getPlat_verifiedUsage() + Constant.getUsagePointIncrement());
				}else if(RptTyp.RPTTYP_FRAUD.getCode().equals(rpt.getRpttyp())){
					refreshObj.setPlat_fraudUsage(refreshObj.getPlat_fraudUsage() + Constant.getUsagePointIncrement());
				}else if(RptTyp.RPTTYP_PRSN_CREDIT.getCode().equals(rpt.getRpttyp())){
					//TODO 个人报告
					//数据来源数
					if(DatCmitori.DatCmitori_PLANT.getCode().equals(rpt.getDatcmitori())){
						refreshObj.setDatcmitori_platUsage(refreshObj.getDatcmitori_platUsage() + Constant.getUsagePointIncrement());
					}else if(DatCmitori.DatCmitori_INTERFACE.getCode().equals(rpt.getDatcmitori())){
						refreshObj.setDatcmitori_interfaceUsage(refreshObj.getDatcmitori_interfaceUsage() + Constant.getUsagePointIncrement());
					}else if(DatCmitori.DatCmitori_APP.getCode().equals(rpt.getDatcmitori())){
						refreshObj.setDatcmitori_appUsage(refreshObj.getDatcmitori_appUsage() + Constant.getUsagePointIncrement());
					}else if(DatCmitori.DatCmitori_WECHAT.getCode().equals(rpt.getDatcmitori())){
						refreshObj.setDatcmitori_wechatUsage(refreshObj.getDatcmitori_wechatUsage() + Constant.getUsagePointIncrement());
					}
					//设置个人征信的
					refreshObj.setDatcmitori_usage_v(HPVCodeGenerator.getVCode());
				}
				HPCache.getInstance().setCacheHPUser(key, refreshObj);
				
			}
		}else{
			logger.error("传入切面参数为空: " + point.getSignature().toShortString());
		}
    } 
    
    
    
    
    /**
     * 报告保存后操作
     * @param point
     * @param result
     * @throws Throwable
     */
    public synchronized void runOnAfterSaveCreditRpt(JoinPoint point,Object result) throws Throwable{  
    	logger.debug("开始处理保存切面方法:  "+ point.getSignature().toLongString());  
    	
    	Object[] obj = point.getArgs();
		if(obj.length == 1){
			if(null == obj[0]){
				logger.error("传入切面参数为空: " + point.getSignature().toShortString());
				return;
			}else if (obj[0] instanceof WfDatCreditrpt) {
				logger.debug("处理报告实体类:  "+ WfDatCreditrpt.class);  
				WfDatCreditrpt rpt = (WfDatCreditrpt) obj[0];
				String userId = rpt.getParntcode();
				String currDate = DateUtil.getCurrentDate8Len();
				String key = userId + "_" + currDate;
				//TODO
				HPRefreshObj refreshObj = HPCache.getInstance().getCacheHPUser(key);
				HPRefreshObj cacheObj = HPCache.getInstance().getCacheHPPlat();
				if(null == refreshObj){
					refreshObj = HPCache.getInstance().loadHPRefreshObj(userId);
				}else{
					LinkedList<WfDatCreditrpt> list = (LinkedList<WfDatCreditrpt>) refreshObj.getPlat_creditReport();
					if(rpt != null){
						int size = list.size();
						list.push(rpt);
						if(Constant.getMaxPlatrptcount() < size){
							list.pollLast();
						}
						cacheObj.setPlat_creditReport(list);
						cacheObj.setPlat_report_v(HPVCodeGenerator.getVCode());
						
						refreshObj.setPlat_creditReport(list);
						refreshObj.setPlat_report_v(cacheObj.getPlat_report_v());
						refreshObj.setAcc_report(list);
						refreshObj.setAcc_report_v(cacheObj.getPlat_report_v());
					}
				}
				
				HPCache.getInstance().setCacheHPPlat(cacheObj);
				HPCache.getInstance().setCacheHPUser(key, refreshObj);
			}
		}else{
			logger.error("传入切面参数为空: " + point.getSignature().toShortString());
		}
    }
    
    
    /**
     * @throws WFException 
     * @throws HPException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException  
	* @Title: updateMonitor 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userID
	* @param @param count
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	public synchronized boolean updateAlarm(List<WfDatCerditwarn> wfDatCerditwarnList) throws IllegalAccessException, InvocationTargetException, HPException, WFException {
//		String userID ;
//		HPRefreshObj tempHPRefreshObj = HPCache.getCacheHPUser(userID);
//		Map<String, HPRefreshObj> cache_user = new HashMap<String, HPRefreshObj>();
//		HPRefreshObj cache_plat = null;
//		cache_plat.setPlat_alarmNo(cache_plat.getPlat_alarmNo() + wfDatCerditwarnList.size());
//		
//		
//		for(WfDatCerditwarn warn : wfDatCerditwarnList) {
//			userID = warn.getP_usrid();
//			tempHPRefreshObj = cache_user.get(userID+"_"+DateUtil.getCurrentDate8Len());			
//			
//			//如果缓存中没有就不更新账户信息，直到有人来查询过该账户并进行了缓存再更新
//			if(null != tempHPRefreshObj) {
//				((LinkedList)(tempHPRefreshObj.getAcc_alarm())).push(warn);
//				if(tempHPRefreshObj.getAcc_alarm().size() > Constant.getMaxAccalarmcount()) {
//					((LinkedList)(tempHPRefreshObj.getAcc_alarm())).poll();
//				}
//				tempHPRefreshObj.setAcc_alarm_v(HPVCodeGenerator.getVCode());
//				tempHPRefreshObj.setAcc_alarmNo(tempHPRefreshObj.getAcc_alarmNo() + 1);			
//			}
//		}
		return true;
	}
	
	/** 
	* @Title: updateAlarmDetail 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wfDatCerditwarnList
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	public synchronized boolean updateAlarmDetail(List<WfDatCerditwarn> wfDatCerditwarnList) {
//		String userID;
//		HPRefreshObj tempHPRefreshObj;
//		
//		cache_plat.setPlat_alarmDetailNo(cache_plat.getPlat_alarmDetailNo() + wfDatCerditwarnList.size());	
//		cache_plat.setPlat_alarmDetail_v(HPVCodeGenerator.getVCode());
		
//		for(WfDatCerditwarn warn : wfDatCerditwarnList) {
//			userID = warn.getP_usrid();
//			tempHPRefreshObj = cache_user.get(userID+"_"+DateUtil.getCurrentDate8Len());
//			
//			//更新平台总体的报警明细信息
//			((LinkedList)(cache_plat.getPlat_alarmDetail())).push(warn);
//			if(cache_plat.getPlat_alarmDetail().size() > max_PlatAlarmCount) {
//				((LinkedList)(cache_plat.getPlat_alarmDetail())).poll();
//			}
//			
//			//如果缓存中没有就不更新账户信息，直到有人来查询过该账户并进行了缓存再更新
//			if(null != tempHPRefreshObj) {
//				tempHPRefreshObj.setAcc_alarmDetailNo(tempHPRefreshObj.getAcc_alarmDetailNo() + 1);			
//			}
//		}
		return true;
	}
	
	/** 
	* @Title: updateRpt 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wfDatCreditrptList
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	public synchronized boolean updateRpt(List<WfDatCreditrpt> wfDatCreditrptList) {
		
//		String userID;
//		HPRefreshObj tempHPRefreshObj;
//		for(WfDatCreditrpt rpt : wfDatCreditrptList) {
//			userID = rpt.getP_usrid();
//			tempHPRefreshObj = cache_user.get(userID);
//			
//			//如果缓存中没有就不更新账户信息，直到有人来查询过该账户并进行了缓存再更新
//			if(null != tempHPRefreshObj) {
//				((LinkedList)(tempHPRefreshObj.getAcc_report())).push(rpt);
//				if(tempHPRefreshObj.getAcc_report().size() > max_AccRptCount) {
//					((LinkedList)(tempHPRefreshObj.getAcc_report())).poll();
//				}
//				tempHPRefreshObj.setAcc_report_v(HPVCodeGenerator.getVCode());		
//			}
//		}
		return true;
	}
    
}
