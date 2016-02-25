package com.sinoway.common.service.tmout;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.sinoway.base.entity.BCfgdefChnltrd;
import com.sinoway.base.entity.BCfgdefFnttrninfo;
import com.sinoway.common.constant.ServerConstant.TradePoolType;
import com.sinoway.common.constant.SystemConstant;
import com.sinoway.common.constant.SystemConstant.SOPtrnTypt;
import com.sinoway.common.constant.SystemConstant.STradeResFlg;
import com.sinoway.common.exception.STradeProcessException;
import com.sinoway.common.frame.SpringContextUtil;
import com.sinoway.common.pool.McpThreadPool;
import com.sinoway.common.pool.ThreadPoolFactory;
import com.sinoway.common.pool.task.GeneralStradeTaskService;
import com.sinoway.common.pool.task.IPoolTask;
import com.sinoway.common.util.GUIDGenerator;
import com.sinoway.common.util.McpLogger;
import com.sinoway.common.util.StringUtil;
import com.sinoway.common.util.SystemCfgUtil;
import com.sinoway.common.util.SystemOperateUtil;
import com.sinoway.mcp.entity.FDatMetatrnflw;
import com.sinoway.mcp.service.FDatMetatrnflwService;

public class GeneralStradeTmoutMonitor extends TimerTask {
	private McpLogger logger = McpLogger.getLogger(getClass());
	/*
	 * 注入服务
	 */
	private Map<String,String> poolIdMap = null;
	private FDatMetatrnflwService sFlwService = null;// 子交易流水服务
    private int coreTNum = 20;
    private int maxTNum = 20;
    private int cacheNum = 20;
    private long keepAlive = 60*1000;
    
    public void init(){
    	// 启动定时器
    	Timer timer = new Timer();
    	timer.schedule(this, 10*1000,5*1000);
    }
	@Override
	public void run() {
		try{
//			logger.info("开始检测子交易超时任务......");
			String curTm = System.currentTimeMillis() + "";
			List tmDataList =  sFlwService.findTmOutData(curTm);
			if(tmDataList != null && tmDataList.size() > 0){
				for(int i = 0 ; i < tmDataList.size(); i++){
					try{
						FDatMetatrnflw tmEntity = (FDatMetatrnflw)tmDataList.get(i);
						submitTmoutTask(tmEntity);
					}catch(Exception e){
						logger.error("提交子交易超时任务到线程池失败",e);
					}
				}
			}
			tmDataList = null;
//			logger.info("检测子交易超时任务完成");
		}catch(Throwable e){
			logger.error("检测子交易超时任务失败....",e);
		}
	}
	
	private void submitTmoutTask(FDatMetatrnflw tmEntity) throws Exception{
		logger.info("检测到流水号" + tmEntity.getFnttrnjrn() + "超时，开始重发.......");
		String chnlCod = tmEntity.getChnlcod();
		String tradeCode = tmEntity.getIntrncod();
		// 获取渠道交易配置信息
		BCfgdefChnltrd chnlTrdEntity = SystemCfgUtil.getChnlCfgInf(chnlCod,tradeCode, SOPtrnTypt.S.getValue());
		// 获取交易配置信息
		BCfgdefFnttrninfo tradeEntity = SystemCfgUtil.getTradeCfgByCode(tradeCode);
					
		// 计算超时时间
		int tmout = 0;// 超时时间 
		int tmnum = 3;// 超时次数
		String stmnum = "".equals(StringUtil.NullToString(chnlTrdEntity.getDelovernum())) ? tradeEntity.getDelovernum() : chnlTrdEntity.getDelovernum();
		String stmOut = "".equals(StringUtil.NullToString(chnlTrdEntity.getDelover())) ? tradeEntity.getDelover() : chnlTrdEntity.getDelover();
		tmout = SystemOperateUtil.getTrdTmOut(stmOut);
		tmnum = SystemOperateUtil.getTrdTmOutNum(stmnum);
		
		// 异步交互时计算超时时间
		Date date = new Date();
		
		tmEntity.setTmout(date.getTime() + tmout + "");
		
		tmEntity.setWhererespflg(tmEntity.getRespflg());
		
		// 更新流水状态到超时处理
		if(1 != sFlwService.updateByFlwAndSta(tmEntity))
			return;
		
		JSONObject json = SystemOperateUtil.fileToJson(tmEntity.getReqmsg(), SystemConstant.DEFAULT_CHARSET);
		json.getJSONObject("header").put("tmoutFlg", "1");
		McpThreadPool pool = ThreadPoolFactory.getPool(poolIdMap.get(TradePoolType.strade_proc_pool.getValue()));
		if(pool == null){
			pool = ThreadPoolFactory.newPool(TradePoolType.strade_proc_pool.getValue(), coreTNum, maxTNum, cacheNum, keepAlive);
		}
		// 提交超时任务到线程池中
		GeneralStradeTaskService<String> task = (GeneralStradeTaskService)SpringContextUtil.getBean(GeneralStradeTaskService.class);
		task.init(GUIDGenerator.generateId(), pool, json.toString());
		pool.excute(task);
		logger.info("流水号" + tmEntity.getFnttrnjrn() + "超时重发完成.......");
	}

	/*
	 *  GETTER   AND  SETTER 
	 */
	public Map<String, String> getPoolIdMap() {
		return poolIdMap;
	}

	public void setPoolIdMap(Map<String, String> poolIdMap) {
		this.poolIdMap = poolIdMap;
	}
	public FDatMetatrnflwService getsFlwService() {
		return sFlwService;
	}
	public void setsFlwService(FDatMetatrnflwService sFlwService) {
		this.sFlwService = sFlwService;
	}

}
