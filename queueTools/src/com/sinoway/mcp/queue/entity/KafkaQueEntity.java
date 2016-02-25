package com.sinoway.mcp.queue.entity;

/**
 * kafka 队列实体
 * @author Liuzhen
 * @verison 1.0
 * 2015-11-17
 */
public class KafkaQueEntity extends QueueEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -252406429664087850L;
	private String message = null;
	private String topic = null;
	private String groupId = null;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
}
