package com.sinoway.common.pool.task;


import com.sinoway.common.pool.McpThreadPool;

/**
 * 线程池任务接口
 * @author liuzhen
 * @version 1.0
 * 2016-1-26
 */
public interface IPoolTask<T>{

	/**
	 * 初始化
	 */
	public void init(String id,McpThreadPool pool,T param);

	/**
	 *  线程ID
	 * @return
	 */
	public String getThreadId();

	/**
	 * 线程开始时间
	 * @return
	 */
	public long getStartTim();

	/**
	 * 关闭线程
	 * @throws Exception
	 */
	public void stop() throws Exception;
	
	/**
	 * 获取当前线程
	 * @return
	 */
	public  Thread getThread();
	
	/**
	 * 获取线程运行标识
	 * @return
	 */
	public  boolean getRunFlg();
}
