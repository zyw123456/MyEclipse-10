package com.sinoway.mcp.queue.service;

import java.util.ArrayList;
import java.util.List;
import kafka.producer.KeyedMessage;

import com.sinoway.mcp.queue.consumer.MessageConsumerBase;
import com.sinoway.mcp.queue.entity.KafkaQueEntity;
import com.sinoway.mcp.queue.entity.QueueEntity;
import com.sinoway.mcp.queue.exception.QueueOperatException;
import com.sinoway.mcp.queue.producer.MessageProducer;

public class KafkaQueOperator implements IQueueOperator {

	public void offer(QueueEntity entity) throws QueueOperatException {
		if(entity == null)
			throw new QueueOperatException("向队列生产消息异常：消息不能为空");
		
		KafkaQueEntity kEntity = null; 
		try{
			
			kEntity = (KafkaQueEntity)entity;
		
		}catch(Exception e){
			throw new QueueOperatException("向队列生产消息异常：不能强转成kafka实体",e);
		}
			
		
		String msg = kEntity.getMessage();
		String topic = kEntity.getTopic();
		
		if(msg == null || "".equals(msg))
			throw new QueueOperatException("向队列生产消息异常：要生产的消息不能为空");
		if(topic == null || "".equals(topic))
			throw new QueueOperatException("向队列生产消息异常：队列主题不能为空");
		
		try{
			
			MessageProducer  p = new MessageProducer();
			
			p.sendData(msg, topic);
		
		}catch(Exception e){
			throw new QueueOperatException("向队列生产消息异常：生产消息出现异常",e);
		}
		
	}

	@Override
	public void offer(List entitys) throws QueueOperatException {
		if(entitys == null)
			throw new QueueOperatException("向队列生产消息异常：list不能为空");
		
		List<KeyedMessage<String, String>> dataList = new ArrayList<KeyedMessage<String, String>>();
		
		for(int i = 0; i < entitys.size(); i++){
			
			KafkaQueEntity kEntity = null; 
			try{
				
				kEntity = (KafkaQueEntity)entitys.get(i);
			
			}catch(Exception e){
				throw new QueueOperatException("向队列生产消息异常：不能强转成kafka实体",e);
			}
			
			String msg = kEntity.getMessage();
			String topic = kEntity.getTopic();
			
			if(msg == null || "".equals(msg))
				throw new QueueOperatException("向队列生产消息异常：要生产的消息不能为空");
			if(topic == null || "".equals(topic))
				throw new QueueOperatException("向队列生产消息异常：队列主题不能为空");
			
			KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic,msg);
			
			dataList.add(data);
			
		}
		
		try{
			if(dataList.size() > 0){
				
				MessageProducer  p = new MessageProducer();
				p.sendDatas(dataList);
			}
		
		}catch(Exception e){
			throw new QueueOperatException("向队列生产消息异常：生产消息出现异常",e);
		}
	}

	@Override
	public QueueEntity poll(QueueEntity entity) throws QueueOperatException {
		if(entity == null)
			throw new QueueOperatException("从队列中消费消息错误：entity不能为空");
		
		KafkaQueEntity kEntity = null; 
		try{
			
			kEntity = (KafkaQueEntity)entity;
		
		}catch(Exception e){
			throw new QueueOperatException("从队列中消费消息错误：不能强转成kafka实体",e);
		}
		
		String groupId = kEntity.getGroupId();
		String topic = kEntity.getTopic();
		
		if(groupId == null || "".equals(groupId))
			throw new QueueOperatException("从队列中消费消息错误：groupId不能为空");
		if(topic == null || "".equals(topic))
			throw new QueueOperatException("从队列中消费消息错误：队列主题不能为空");
		
		MessageConsumerBase consumer = new MessageConsumerBase(topic, groupId);
		
		String msg = consumer.getMsg();
		consumer.shutdownConsumer();
		consumer = null;
		
		kEntity.setMessage(msg);
		
		return kEntity;
	}

	@Override
	public QueueEntity pollBlock(QueueEntity entity)
			throws QueueOperatException {
		if(entity == null)
			throw new QueueOperatException("从队列中消费消息错误：entity不能为空");
		
		KafkaQueEntity kEntity = null; 
		try{
			
			kEntity = (KafkaQueEntity)entity;
		
		}catch(Exception e){
			throw new QueueOperatException("从队列中消费消息错误：不能强转成kafka实体",e);
		}
		
		String groupId = kEntity.getGroupId();
		String topic = kEntity.getTopic();
		
		if(groupId == null || "".equals(groupId))
			throw new QueueOperatException("从队列中消费消息错误：groupId不能为空");
		if(topic == null || "".equals(topic))
			throw new QueueOperatException("从队列中消费消息错误：队列主题不能为空");
		
		MessageConsumerBase consumer = new MessageConsumerBase(topic, groupId);
		
		String msg = consumer.getMsgBlock();
		
		kEntity.setMessage(msg);
		
		return kEntity;
	}
	
	/**
	 * 启动多个消费者
	 * @param num 数量
	 * @param consumer 消费者实例
	 * @throws
	 */
	public void stratConsumers(int num,MessageConsumerBase consumer) throws QueueOperatException{
		if(num == 0)
			throw new QueueOperatException("启动消费者错误：数量不能为0");
		
		if(consumer == null)
			throw new QueueOperatException("启动消费者错误：消费者不能为空");
		
		try{
			consumer.run(num);
		}catch(Exception e){
			throw new QueueOperatException("启动消费者错误：启动失败",e);
		}
		
	}

}
