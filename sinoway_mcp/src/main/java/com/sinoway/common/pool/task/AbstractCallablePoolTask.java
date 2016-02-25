package com.sinoway.common.pool.task;

import com.sinoway.common.pool.McpThreadPool;
import com.sinoway.common.util.McpLogger;

/**
 * 线程池任务抽象类
 * @author Liuzhen
 * @version 1.0
 * 2016-1-26
 */
public abstract class AbstractCallablePoolTask<E,T> implements IPoolCallableTask<E,T> {
	protected McpLogger logger = McpLogger.getLogger(getClass());
	protected String threadId = "";
	protected long startTim = 0;
	protected Thread t = null;
	protected McpThreadPool  pool = null;
	protected T param = null;
	protected boolean runFlg = true;
	
	public AbstractCallablePoolTask(){
	}
	
	public void init(String id,McpThreadPool pool,T param){
		this.param = param;
		this.threadId = id;
		this.pool = pool;
	}
	@Override
	public E call(){
		startTim = System.currentTimeMillis();
		E result = null;
		t = Thread.currentThread();
//		logger.info(threadId + "开始执行");
		try {
			result = excuteTask(param);
			runFlg = false;
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(threadId + "运行过程中出现异常");
		}
		return result;
//		pool.removeTask(this);
//		logger.info(threadId + "开始执行结束");
	}
	
	public abstract E excuteTask(T param) throws Exception;
	public void stop() throws Exception{
		if(t.isAlive())
			t.interrupt();
	}

	
	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	public long getStartTim() {
		return startTim;
	}

	public void setStartTim(long startTim) {
		this.startTim = startTim;
	}

	public Thread getThread() {
		return t;
	}


	public void setThread(Thread t) {
		this.t = t;
	}

	public McpThreadPool getPool() {
		return pool;
	}

	public void setPool(McpThreadPool pool) {
		this.pool = pool;
	}

	public T getParam() {
		return param;
	}

	public void setParam(T param) {
		this.param = param;
	}
	public  boolean getRunFlg(){
		return runFlg;
	}
}
