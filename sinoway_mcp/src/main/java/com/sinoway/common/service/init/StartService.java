package com.sinoway.common.service.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import com.sinoway.base.cache.BCfgdefQueueCache;
import com.sinoway.base.entity.BCfgdefFntsrvport;
import com.sinoway.base.entity.BCfgdefQueue;
import com.sinoway.cache.ICache;
import com.sinoway.common.constant.SystemConstant;
import com.sinoway.common.constant.SystemConstant.QueType;
import com.sinoway.common.constant.SystemConstant.SOPtrnTypt;
import com.sinoway.common.constant.SystemConstant.SysParamCode;
import com.sinoway.common.frame.SpringContextUtil;
import com.sinoway.common.service.consumer.GeneralConsumerService;
import com.sinoway.common.service.server.socket.GeneralSocketService;
import com.sinoway.common.util.GetComputerCon;
import com.sinoway.common.util.McpLogger;
import com.sinoway.common.util.StringUtil;
import com.sinoway.common.util.SystemCfgUtil;
import com.yzj.wf.com.ibank.common.server.IBankControlException;

/**
 * 启动服务
 * @author Liuzhen
 * @version 1.0
 * 2015-11-20
 */
public class StartService {
	
	private BCfgdefQueueCache queInfoCache = null; //队列信息缓存
	private McpLogger logger = McpLogger.getLogger(getClass());
	private Map<String,GeneralSocketService> socketServiceMap = new HashMap<String,GeneralSocketService>();
	private Map<String,GeneralConsumerService> consumerServiceMap = new HashMap<String,GeneralConsumerService>();
	
	public void init(){
//		String a = "{\"header\":{\"chnlcod\":\"10000002\",\"subusrid\":\"1601252130275278fa3e8a8081de0002\",\"intrncod\":\"M0000001\",\"masttrntim\":\"150635119\",\"masttrndte\":\"20160126\",\"mastjrn\":\"CSM000000120160126527b00020025\",\"orgno\":\"OP201601255278fa00010000\",\"usrid\":\"SQCW000001\"},\"bodys\":[{\"prsnnam\":\"俞品元\",\"idcard\":\"320582198001200315\"}]}";
//		
//		McpThreadPool pool = ThreadPoolFactory.getPool("strade_proc_pool");
//		
//		IPoolTask<String> task = (IPoolTask)SpringContextUtil.getBean("generalSptradeTaskService");
//		
//		task.init(GUIDGenerator.generateId(), pool, a);
//		try{
//			pool.excute(task);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		// 开启端口
		Map<String, BCfgdefFntsrvport> portMap = SystemCfgUtil.getSerProtInfById(GetComputerCon.getInstance().getIp());
		if(portMap != null){
			Set<String> keys = portMap.keySet();
			// 启动所有socket 服务
			for(String key : keys){
				BCfgdefFntsrvport portEntity = portMap.get(key);
				String beanName = portEntity.getBeannam();
				if(beanName == null || "".equals(beanName))
					beanName = SystemCfgUtil.getSysParmValByCode(SysParamCode.DEFAUT_SOCKET_SER_BEAN.getValue());
					
				GeneralSocketService service = (GeneralSocketService)SpringContextUtil.getBean(beanName);
				service.initCfg(portEntity);
				try {
					service.start();
					socketServiceMap.put(portEntity.getPort(), service);
				} catch (IBankControlException e) {
					logger.error(e);
				}
			}
		}
		
		// 启动所有队列监听 原交易/产品响应队列   子交易请求队列
		List<String> qKeys = queInfoCache.keys();
		for(String key : qKeys){
			BCfgdefQueue qEntity = (BCfgdefQueue)queInfoCache.get(key);
			
			// 队列类型
			String qType = qEntity.getQuetype();
			String trnType = qEntity.getTrntype();
			String topic = qEntity.getQuetopic();
			String groupId = qEntity.getQuegroup();
			
			// 启动原交易  产品响应队列消费者
			if(SOPtrnTypt.O.getValue().equals(trnType) || SOPtrnTypt.P.getValue().equals(trnType)){
				if(QueType.RES.getValue().equals(qType)){
					GeneralConsumerService cosumer =  (GeneralConsumerService)SpringContextUtil.getBean("generalOPConsumerService");
					try{
						cosumer.startConsumer(topic, groupId, 5);
					}catch(Exception e){
						logger.error("原交易消费者启动失败，主题：" + topic + ",组ID：" + groupId + ",数量：" + 1);
						e.printStackTrace();
					}
					
					consumerServiceMap.put(qEntity.getQuecod(), cosumer);
				}
			// 启动子交易请求队列消费者
			}else{
				if(QueType.REQ.getValue().equals(qType)){
					GeneralConsumerService cosumer =  (GeneralConsumerService)SpringContextUtil.getBean("generalSConsumerService");
					try{
						cosumer.startConsumer(topic, groupId, 10);
						consumerServiceMap.put(qEntity.getQuecod(), cosumer);
					}catch(Exception e){
						logger.error("子交易消费者启动失败，主题：" + topic + ",组ID：" + groupId + ",数量：" + 1);
						e.printStackTrace();
					}
				}
			}
		}
		
		// 启动日志消费者
		String logTopic = SystemCfgUtil.getSysParmValByCode(SystemConstant.SysParamCode.LOG_QUE_TOPIC.getValue());// 日志队列主题
		String logGroupId = SystemCfgUtil.getSysParmValByCode(SystemConstant.SysParamCode.LOG_QUE_GROUPID.getValue());// 日志队列组id
		
 		if(!StringUtil.NullToString(logTopic).equals("") && !StringUtil.NullToString(logGroupId).equals("")){
			GeneralConsumerService cosumer =  (GeneralConsumerService)SpringContextUtil.getBean("logRecordConsumerService");
			try{
				cosumer.startConsumer(logTopic, logGroupId, 2);
				consumerServiceMap.put("log_que_consumer", cosumer);
			}catch(Exception e){
				logger.error("日志消费者启动失败，主题：" + logTopic + ",组ID：" + logGroupId + ",数量：" + 2);
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 停止服务
	 */
	public void stop(){
		Set<String> keys = socketServiceMap.keySet();
		for(String key : keys){
			try {
				socketServiceMap.get(key).stop();
			} catch (IBankControlException e) {
				e.printStackTrace();
				logger.error("停止服务失败,端口号：" + key);
			}
		}
		
		Set<String> cKeys = consumerServiceMap.keySet();
		for(String key : keys){
			consumerServiceMap.get(key).stopConsumer();
		}
	}
	/*
	 * GETTER   AND   SETTER
	 */
	public BCfgdefQueueCache getQueInfoCache() {
		return queInfoCache;
	}
	public void setQueInfoCache(BCfgdefQueueCache queInfoCache) {
		this.queInfoCache = queInfoCache;
	}
	
}
