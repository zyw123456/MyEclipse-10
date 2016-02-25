package com.sinoway.mcp.queue.config;

import java.io.InputStream;
import java.util.Properties;

/**
 * 生产者 消费者配置文件加载
 * @author Liuzhen
 * 2015-11-17
 */
public class Configration {

	// 消费者属性
	private  Properties consumerProp = null;
	
	// 生产者属性
	private  Properties producerProp = null;
	
	private static final Configration instance = new Configration(); 
	
	public static Configration getInstance(){
		return instance;
	}
	
	private Configration(){
		init();
	}
	
	private void init(){
		// 加载消费者配置文件
		if(consumerProp == null){
			
			try{
				consumerProp = new Properties();
				InputStream in = this.getClass().getResourceAsStream("/consumer.properties");
				consumerProp.load(in);
				in.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}
		// 加载生产者配置文件
		if(producerProp == null){
			try{
				producerProp = new Properties();
				
				InputStream in = this.getClass().getResourceAsStream("/producer.properties");
				
				producerProp.load(in);
				
				in.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public  Properties getConsumerProp() {
		return consumerProp;
	}

	public  Properties getProducerProp() {
		return producerProp;
	}
	
}
