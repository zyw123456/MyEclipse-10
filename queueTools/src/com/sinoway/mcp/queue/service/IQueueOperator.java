package com.sinoway.mcp.queue.service;

import java.util.List;
import com.sinoway.mcp.queue.entity.QueueEntity;
import com.sinoway.mcp.queue.exception.QueueOperatException;

/**
 * 队列操作接口
 * @author Liuzhen
 *
 */
public interface IQueueOperator {

	/**
	 * 相队列中放入一个消息
	 * @param entity
	 * @throws QueueOperatException
	 */
	public void offer(QueueEntity entity) throws QueueOperatException;
	
	/**
	 * 相队列中放入多个消息
	 * @param entity
	 * @throws QueueOperatException
	 */
	public void offer(List entitys) throws QueueOperatException;
	
	/**
	 * 根据条件从队列中获取一个消息 非阻塞
	 * @param entity
	 * @return
	 * @throws QueueOperatException
	 */
	public QueueEntity poll(QueueEntity entity) throws QueueOperatException;
	
	/**
	 * 根据条件从队列中获取一个消息 阻塞
	 * @param entity
	 * @return
	 * @throws QueueOperatException
	 */
	public QueueEntity pollBlock(QueueEntity entity) throws QueueOperatException;
	
}
