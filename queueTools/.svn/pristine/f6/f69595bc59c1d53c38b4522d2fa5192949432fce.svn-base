package com.sinoway.mcp.queue.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sinoway.mcp.queue.config.Configration;
import com.sinoway.mcp.queue.exception.QueueOperatException;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
/**
 * 通用kafka队列消费者基类
 * @author Liuzhen
 * @version 1.0
 * 2015-11-17
 */
public class MessageConsumerBase {

	private  ConsumerConnector consumer;  
    private  String topic = null;
    private  String groupId = null;
    private  ExecutorService executor;  
    public MessageConsumerBase(){
    	
    }
    public MessageConsumerBase(String topic,String groupId) {  
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(  
                createConsumerConfig(groupId));  
        this.groupId = groupId;
        this.topic = topic;  
    }  
    
    /**
     * 初始化方法
     * @param topic
     * @param groupId
     */
    public void init(String topic,String groupId){
    	 consumer = kafka.consumer.Consumer.createJavaConsumerConnector(  
                 createConsumerConfig(groupId));  
         this.groupId = groupId;
         this.topic = topic;  
    }
   
    /**
     * 关闭消费者
     */
    public void shutdownConsumer() {  
        if (consumer != null) consumer.shutdown();  
          
    }  
    
    /**
     * 关闭线程池
     */
    public void shutdownExcutor(){
    	if (executor != null) executor.shutdown();
    }
    
    public void shutdownAll(){
    	shutdownConsumer();
    	shutdownExcutor();
    }
   
    /**
     * 根据数量启动a_numThreads个消费者
     * @param a_numThreads
     */
    public void run(int a_numThreads) {  
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();  
        topicCountMap.put(topic, new Integer(a_numThreads));  
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);  
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);  
   
        // 启动所有线程  
        executor = Executors.newFixedThreadPool(a_numThreads);  
   
        // 开始消费消息   
        for (final KafkaStream stream : streams) {  
        	
        	ConsumerBase c = new ConsumerBase(stream, this);

        	executor.submit(c);  
        
        }  
    }  
    
    /**
     * 获取一个消息 非阻塞
     * @return 为获取到时返回null
     * @throws QueueOperatException
     */
    public String getMsg() throws  QueueOperatException{
    	try{
    		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();  
            topicCountMap.put(topic, new Integer(1));  
            Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);  
            List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);  
            if(streams != null && streams.size() > 0){
            	KafkaStream<byte[], byte[]> stream = streams.get(0);
            	
            	ConsumerIterator<byte[], byte[]> it = stream.iterator();  
            	
            	String msg = new String(it.next().message());
        		
        		return msg;
            }
            return null;
    	}catch(Exception e){
    		throw new QueueOperatException("获取队列消息失败",e);
    	}
    	
    }
    
    /**
     * 获取一个消息 阻塞 
     * @return 未获取到消息返回null
     * @throws QueueOperatException
     */
    public String getMsgBlock() throws  QueueOperatException{
    	try{
        	Map<String, Integer> topicCountMap = new HashMap<String, Integer>();  
            topicCountMap.put(topic, new Integer(1));  
            Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);  
            List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);  
            if(streams != null && streams.size() > 0){
            	KafkaStream<byte[], byte[]> stream = streams.get(0);
            	ConsumerIterator<byte[], byte[]> it = stream.iterator();  
            	while(it.hasNext()){
            		String msg = new String(it.next().message());
            		return msg;
            	}
            }
            return null;
	    }catch(Exception e){
			throw new QueueOperatException("获取队列消息失败",e);
		}

    }
    
    /**
     * 消费消息的回调方法
     * @param msg
     */
    protected  void doMsg(String msg){
    	
    } 
   
    /**
     * 初始化配置信息
     * @param groupId
     * @return
     */
    private ConsumerConfig createConsumerConfig(String groupId) {  
      
    	Properties props = Configration.getInstance().getConsumerProp();
        
        props.put("group.id", groupId);  
    
        return new ConsumerConfig(props);  
    }  
}
