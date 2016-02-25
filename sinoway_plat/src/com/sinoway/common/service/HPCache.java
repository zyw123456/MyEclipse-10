/**   
* @Title: FadServer.java 
* @Package com.sinoway.fad.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author Leo May the source be with you!   
* @date 2015-12-15 下午6:41:18 
* @version V1.0   
*/
package com.sinoway.common.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinoway.common.dao.HPDao;
import com.sinoway.common.entity.HPRefreshObj;
import com.sinoway.common.exception.HPException;
import com.sinoway.common.util.Constant;
import com.sinoway.common.util.DateUtil;
import com.sinoway.common.util.HPVCodeGenerator;
import com.sinoway.wrn.entity.WfDatCerditWarnDtel;
import com.yzj.wf.base.util.SpringContextHelper;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.WFLogger;

/**
 * @ClassName: HPCache
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Leo May the source be with you!
 * @date 2015-12-16 上午10:59:30
 * 
 */
@Component("hPCache")
public class HPCache {
	
	private WFLogger logger = WFLogger.getLogger(HPCache.class);

	private static HPRefreshObj cacheHPPlat = new HPRefreshObj();

	private static Map<String, HPRefreshObj> cacheHPUser = new HashMap<String, HPRefreshObj>();

	private HPCache() {
	}

	/**
	 * 私有构造函数
	 */
	private static class HPCacheHolder {
		private static final HPCache INSTANCE = new HPCache();

	}

	public static final HPCache getInstance() {
		return HPCacheHolder.INSTANCE;
	}

	@Autowired
	private HPDao hPDao;

	/**
	 * @throws WFException 
	 * @Title: init @Description: TODO(这里用一句话描述这个方法的作用) @param @throws
	 * HPException 设定文件 @return void 返回类型 @throws
	 */
	@PostConstruct
	public void init() throws HPException, WFException {
		logger.info("初始化HPCache...");
		// 获取平台总监控数
		cacheHPPlat.setPlat_alarmDetailNo(hPDao.getPlatAlarmDetailNo());
		
		// 获取平台监控预警统计
		cacheHPPlat.setPlat_alarmNo(hPDao.getPlatAlarmNo());

		//平台监控预警统计版本号
		cacheHPPlat.setPlat_alarmNo_v(HPVCodeGenerator.getVCode());

		// 获取监控预警明细前N条
		cacheHPPlat.setPlat_alarmDetail(hPDao.getTopPlatAlarmDetail(Constant.getMaxPlatalarmcount()));
		// 获取监控预警明细版本号
		cacheHPPlat.setPlat_alarmDetail_v(HPVCodeGenerator.getVCode());

		// 获取平台报告前N条
		cacheHPPlat.setPlat_creditReport(hPDao.getTopPlatCreditRpt(Constant.getMaxPlatrptcount()));

		// 获取平台报告版本号
		cacheHPPlat.setPlat_report_v(HPVCodeGenerator.getVCode());
		
		logger.info("初始化HPCache结束...");

	}

	/**
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws WFException 
	 * @Title: getFadRefreshObj 当前主账户下 
	 * @Description:
	 * TODO(这里用一句话描述这个方法的作用)
	 * @param
	 *  @param userID 
	 *  @param 
	 *  @return 
	 *  @param 
	 *  @throws HPException 设定文件 
	 *  @return HPRefreshObj 返回类型 @throws
	 */
	public synchronized HPRefreshObj loadHPRefreshObj(String userID)
			throws HPException, WFException, IllegalAccessException, InvocationTargetException {
		HPRefreshObj result = new HPRefreshObj();
		HPRefreshObj tempHPRefreshObj;
		String currDate = DateUtil.getCurrentDate8Len();
		String key = userID + "_" + currDate;
		long alarmCount = 0;
		long rptCount = 0;
		hPDao = (HPDao) SpringContextHelper.getBean("hPDao");
		// 获取整个平台相关数据，直接从缓存中取
		if(null != cacheHPPlat){
			// 判断缓存中是否有账户数据
			tempHPRefreshObj = cacheHPUser.get(key);
			// 解决跨天和日切问题
			if (null == tempHPRefreshObj || !cacheHPUser.containsKey(key)) {
				logger.info("当前用户首次加载....");
				
				// 获取账户相关数据
				tempHPRefreshObj = new HPRefreshObj();
				
				
				// 当前主账户监控预警数
				tempHPRefreshObj.setAcc_alarmNo(hPDao.getAccAlarmNo(userID));
	
				// 当前主账户委托监控数
				tempHPRefreshObj.setAcc_alarmDetailNo(hPDao.getAccAlarmDetailNo(userID));
	
				// 当日当前主账户产品预警数
				alarmCount = hPDao.getCurUserDailyAlarmNo(userID, currDate);
	
				// 当日当前主账户报告总数
				rptCount = hPDao.getCurUserDailyReport(userID, currDate);
				tempHPRefreshObj.setAcc_dailyDataUsage(alarmCount + rptCount);
	
				Object[] alarm3Days = hPDao.getCurRecent3DaysAlarmUsage(userID, currDate);
				Object[] rpt3Days = hPDao.getCurRecent3DaysReportUsage(userID, currDate);
				long[] recent3Daystotal = new long[3];
				for (int i = 0; i < alarm3Days.length && i < rpt3Days.length; i++) {
					recent3Daystotal[i] = Long.parseLong(String.valueOf(alarm3Days[i]))
							+ Long.parseLong(String.valueOf(rpt3Days[i]));
				}
				tempHPRefreshObj.setAcc_recent3DaysUsage(recent3Daystotal);
				// 验证平台当日流量
				tempHPRefreshObj.setPlat_verifiedUsage(hPDao.getPlatVerifiedUsage(userID, currDate));
	
				// 反欺诈报告当日流量
				tempHPRefreshObj.setPlat_fraudUsage(hPDao.getPlatFraudUsage(userID, currDate));
	
				// 当日平台上传流量
				tempHPRefreshObj.setDatcmitori_platUsage(hPDao.getDatcmitoriPlatUsage(userID, currDate));
	
				// 当日接口上传流量
				tempHPRefreshObj.setDatcmitori_interfaceUsage(hPDao.getDatcmitoriInterfaceUsage(userID, currDate));
	
				// 当日公众号上传流量
				tempHPRefreshObj.setDatcmitori_wechatUsage(hPDao.getDatcmitoriWechatUsage(userID, currDate));
	
				// 当日总监控流量
				tempHPRefreshObj.setAcc_DailyAlarmNo(hPDao.getAccDailyAlarmNo(userID, currDate));

				//为各个版本号赋值
				//日使用流量版本号
				tempHPRefreshObj.setAcc_dailyDataUsage_v(HPVCodeGenerator.getVCode());
				
				//反欺诈使用版本号
				tempHPRefreshObj.setPlat_fraudUsage_v(HPVCodeGenerator.getVCode());
				
				//个人征信页面的渠道号统计
				tempHPRefreshObj.setDatcmitori_usage_v(HPVCodeGenerator.getVCode());
				
				//预警数版本号
				tempHPRefreshObj.setAcc_alarmNo_v(HPVCodeGenerator.getVCode());
				
				String prevDay = DateUtil.getPrevDay();
				//删除cacheHPUser中含有用户前一天的数据
				for(String k : cacheHPUser.keySet()){
					if(StringUtils.isNotBlank(k)){
						if(k.indexOf(userID + "_")!= -1 || k.indexOf("_" + prevDay) != -1){
							cacheHPUser.remove(k);
						}
					}
				}
			}
			
			//用户预警明细版本号
			if(StringUtils.isNotBlank(tempHPRefreshObj.getPlat_alarmDetail_v()) && !cacheHPPlat.getPlat_alarmDetail_v().equals(tempHPRefreshObj.getPlat_alarmDetail_v())){
				// 获取监控预警明细前N条
				cacheHPPlat.setPlat_alarmDetail(hPDao.getTopPlatAlarmDetail(Constant.getMaxPlatalarmcount()));
				// 获取监控预警明细版本号
				cacheHPPlat.setPlat_alarmDetail_v(HPVCodeGenerator.getVCode());
			}
			//平台预警明细监控预警明细表中前N条
			tempHPRefreshObj.setPlat_alarmDetail(new ArrayList<WfDatCerditWarnDtel>(cacheHPPlat.getPlat_alarmDetail()));
			tempHPRefreshObj.setAcc_alarmDtl(new ArrayList<WfDatCerditWarnDtel>(cacheHPPlat.getPlat_alarmDetail()));
			tempHPRefreshObj.setPlat_alarmDetail_v(cacheHPPlat.getPlat_alarmDetail_v());
			tempHPRefreshObj.setAcc_alarm_v(tempHPRefreshObj.getPlat_alarmDetail_v());
			
			//实时报告更新版本号
			if(StringUtils.isNotBlank(tempHPRefreshObj.getPlat_report_v()) && !cacheHPPlat.getPlat_report_v().equals(tempHPRefreshObj.getPlat_report_v())){
				// 获取平台报告前N条
				cacheHPPlat.setPlat_creditReport(hPDao.getTopPlatCreditRpt(Constant.getMaxPlatrptcount()));
				// 获取平台报告版本号
				cacheHPPlat.setPlat_report_v(HPVCodeGenerator.getVCode());
			}
			// 报告表中的前N条
			tempHPRefreshObj.setPlat_creditReport(cacheHPPlat.getPlat_creditReport());
			tempHPRefreshObj.setPlat_report_v(cacheHPPlat.getPlat_report_v());
			tempHPRefreshObj.setAcc_report_v(tempHPRefreshObj.getPlat_report_v());
			tempHPRefreshObj.setAcc_report(cacheHPPlat.getPlat_creditReport());
			
			if(StringUtils.isNotBlank(tempHPRefreshObj.getPlat_alarmNo_v()) && !cacheHPPlat.getPlat_alarmNo_v().equals(tempHPRefreshObj.getPlat_alarmNo_v())){
				// 获取平台总监控数
				cacheHPPlat.setPlat_alarmDetailNo(hPDao.getPlatAlarmDetailNo());
				
				// 获取平台监控预警统计
				cacheHPPlat.setPlat_alarmNo(hPDao.getPlatAlarmNo());

				//平台监控预警统计版本号
				cacheHPPlat.setPlat_alarmNo_v(HPVCodeGenerator.getVCode());
			}
			// 监控预警数
			tempHPRefreshObj.setPlat_alarmNo(cacheHPPlat.getPlat_alarmNo());
			// 总监控数
			tempHPRefreshObj.setPlat_alarmDetailNo(cacheHPPlat.getPlat_alarmDetailNo());
			//平台预警数版本号
			tempHPRefreshObj.setPlat_alarmNo_v(cacheHPPlat.getPlat_alarmNo_v());
			
			
			tempHPRefreshObj.setPlat_creditReport(cacheHPPlat.getPlat_creditReport());
			
			//平台报告版本号
			tempHPRefreshObj.setPlat_report_v(cacheHPPlat.getPlat_report_v());
			
			// 加入缓存，由于是企业主用户，数量有限，全部缓存
			cacheHPUser.put(key, tempHPRefreshObj);
			BeanUtils.copyProperties(result, tempHPRefreshObj);
			
		}
		return result;
	}

	public HPDao gethPDao() {
		return hPDao;
	}

	public void sethPDao(HPDao hPDao) {
		this.hPDao = hPDao;
	}

	/**
	 * 获取用户缓存
	 * 
	 * @param key
	 * @return
	 * @throws WFException 
	 * @throws HPException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public HPRefreshObj getCacheHPUser(String key) throws IllegalAccessException, InvocationTargetException, HPException, WFException {
		logger.info("读取统计结果缓存,key:" + key);
		try {
			if(hasHPRefreshObj(key)){
				return (HPRefreshObj) cacheHPUser.get(key);
			}
		} catch (NullPointerException e) {
			logger.error("获取缓存出错,未获取到对应的值");
		}
		logger.info("读取结果为空");
		return null;
	}

	

	/**
	 * 返回平台实体类
	 * 
	 * @return
	 */
	public synchronized HPRefreshObj getCacheHPPlat() {
		try {
			return cacheHPPlat;
		} catch (NullPointerException e) {
			return null;
		}
	}

	/**
	 * 设置缓存的用户实体
	 * 
	 * @param key
	 * @param hPUser
	 * @return
	 */
	public synchronized boolean setCacheHPUser(String key, HPRefreshObj hPUser) {
		cacheHPUser.put(key, hPUser);
		return true;
	}

	/**
	 * 设置平台缓存
	 * 
	 * @param hPPlat
	 * @return
	 */
	public synchronized boolean setCacheHPPlat(HPRefreshObj hPPlat) {
		cacheHPPlat = hPPlat;
		return true;
	}

	/**
	 * 得到缓存。同步静态方法
	 * 
	 * @param key
	 * @return
	 */
	private synchronized HPRefreshObj getHPRefreshObj(String key) {
		return (HPRefreshObj) cacheHPUser.get(key);
	}

	/**
	 * 判断是否存在
	 * 
	 * @param key
	 * @return
	 */
	private synchronized boolean hasHPRefreshObj(String key) {
		return cacheHPUser.containsKey(key);
	}

	/**
	 * 清除所有缓存
	 */
	public synchronized void clearAll() {
		cacheHPUser.clear();
	}

	
	/**
	 * 清除指定缓存,用于日切时,清除当日数据
	 * @param type
	 */
	public synchronized void clearAll(String type) {
		Iterator<Entry<String, HPRefreshObj>> iterator = cacheHPUser.entrySet().iterator();
		String key;
		ArrayList<String> keyList = new ArrayList<String>();
		while (iterator.hasNext()) {
			Map.Entry<String, HPRefreshObj> entry = (Map.Entry<String, HPRefreshObj>) iterator.next();
			key = entry.getKey();
			if (key.startsWith(type)) {
				keyList.add(key);
			}
		}

		for (int k = 0; k < keyList.size(); k++) {
			clearOnly(keyList.get(k));
		}
	}
	
	
	/**
	 * 清除指定缓存
	 * @param key
	 */
	public synchronized void clearOnly(String key){
		cacheHPUser.remove(key);
	}
	
	
	
	/**
	 *  设置缓存
	 * @param key
	 * @param hpUser
	 */
	public synchronized void putHPRefreshObj(String key, HPRefreshObj hpUser){
		cacheHPUser.put(key, hpUser);
	}
	
	
	/**
	 * 获取缓存信息
	 * @param key
	 * @return
	 */
	public HPRefreshObj getHPRefreshObjDetail(String key){
		if(hasHPRefreshObj(key)){
			HPRefreshObj refreshObj = getHPRefreshObj(key);
			return refreshObj;
		}else{
			return null;
		}
	}
	

}
