package com.sinoway.mcp.queue.producer;

import java.util.List;
import java.util.Properties;

import com.sinoway.mcp.queue.config.Configration;
import com.sinoway.mcp.queue.exception.QueueOperatException;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * kafka 消息生产者
 * @author Liuzhen
 * @version 1.0
 * 2015-11-17
 */
public class MessageProducer {

	private Producer<String,String> producer;
	
	public MessageProducer() {
		
        Properties props = Configration.getInstance().getProducerProp();  
   
        ProducerConfig config = new ProducerConfig(props);  
   
        producer = new Producer<String, String>(config);  

	}
	
	/**
	 * 生产一个消息
	 * @param msg
	 * @param topic 消息主题
	 * @throws QueueOperatException
	 */
	public  void sendData(String msg,String topic) throws QueueOperatException {
		
		try{
			KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, msg);
			
			producer.send(data);
			
			producer.close();
			
			producer = null;
		}catch (Exception e){
			throw new QueueOperatException("向kafka中生产消息错误",e);
		}finally{
			if(producer != null)
				producer.close();
		}

	}
	
	/**
	 * 生产一组消息
	 * @param dataList
	 * @throws QueueOperatException
	 */
	public  void sendDatas(List<KeyedMessage<String, String>> dataList) throws QueueOperatException {
		
		try{
			
			producer.send(dataList);
			
			producer.close();
			
			producer = null;
			
		}catch (Exception e){
			throw new QueueOperatException("向kafka中生产消息错误",e);
		}finally{
			if(producer != null)
				producer.close();
		}

	}
}
