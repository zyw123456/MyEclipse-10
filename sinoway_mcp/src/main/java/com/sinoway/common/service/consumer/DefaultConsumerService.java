package com.sinoway.common.service.consumer;

import com.sinoway.common.util.McpLogger;
import com.sinoway.mcp.queue.consumer.MessageConsumerBase;

/**
 * 通用消费者类
 * @author Liuzhen
 * @version 1.0
 * 2015-11-24
 */
public  abstract class DefaultConsumerService extends MessageConsumerBase implements GeneralConsumerService {
	private McpLogger logger = McpLogger.getLogger(getClass());

	private String topic;
	private String groupId;
	public DefaultConsumerService(String topic, String groupId) {
		super(topic, groupId); 
		this.topic = topic;
		this.groupId = groupId;
	}
	public DefaultConsumerService() {
		super();
	}
	/**
	 * 消息处理方法
	 */
	@Override
	public void doMsg(String msg){
		try {
			excuteMsg(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public abstract void excuteMsg (String msg) throws Exception;
	@Override
	public void startConsumer(String topic, String groupId, int num) throws Exception{
		this.topic = topic;
		this.groupId = groupId;
		logger.info("启动队列消费者......，主题：" + topic + "，GroupId：" + groupId + "，数量：" + num);
		init(topic,groupId);
		run(num);
		logger.info("队列消费者启动完成，主题：" + topic + "，GroupId：" + groupId + "，数量：" + num);
	}
	@Override
	public void stopConsumer() {
		logger.info("关闭队列消费者......，主题：" + topic + "，GroupId：" + groupId);
		shutdownAll();
		logger.info("启动队列消费者......，主题：" + topic + "，GroupId：" + groupId);
	}
}
