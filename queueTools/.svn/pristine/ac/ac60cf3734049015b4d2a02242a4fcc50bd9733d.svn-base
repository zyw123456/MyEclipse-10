package com.sinoway.mcp.queue.consumer;

import kafka.consumer.ConsumerIterator;  
import kafka.consumer.KafkaStream;  
import kafka.message.MessageAndMetadata;
   
public class ConsumerBase extends Thread{  
	private KafkaStream stream;  
    private MessageConsumerBase consumer;  
    
    public ConsumerBase(KafkaStream stream, MessageConsumerBase consumer) {  
    	this.consumer= consumer;  
        this.stream = stream;  
    }  
   
    public void run() {  
        ConsumerIterator<byte[], byte[]> it = stream.iterator();  
        while (it.hasNext()){
        	try{
        		MessageAndMetadata<byte[], byte[]>  aa = it.next();
        		if(aa != null){
        			byte[]  bs = aa.message();
        			if(bs != null && bs.length > 0){
        				String msg = new String(bs,"utf-8");
                    	consumer.doMsg(msg);
        			}
        				
        		}
        		
        		
            	
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	  
        }  
    }  
}  
