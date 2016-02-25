package com.sinoway.common.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.sinoway.common.pool.task.IPoolCallableTask;
import com.sinoway.common.pool.task.IPoolRunnableTask;
import com.sinoway.common.pool.task.IPoolTask;
import com.sinoway.common.util.McpLogger;


/**
 * 线程池
 * @author Liuzhen
 * @version 1.0
 * 2016-1-26
 */
public class McpThreadPool {
	protected McpLogger logger = McpLogger.getLogger(getClass());
	private ThreadPoolExecutor executor = null; // 线程池对象
	private List<IPoolTask> taskList = null;// 任务项
	private ThreadPoolMonitor moitor = null; // 监控器
	private long tmout = 120*1000;// 默认超时时间
	private boolean isStop = false;// 是否停止
	private String poolId = "";// 线程池ID
	private int coreTNum = 10;// 核心线程数量
	private int maxTNum = 10;// 最大线程数量
	private long keepAlive = 10000L;// 空闲线程保存时间
	private int cacheNum = 30;// 缓存任务数量
	
	public McpThreadPool(){
	
	}
	
	/**
	 * 初始化线程池
	 */
	public void init(String poolId,int coreTNum,int maxTNum,int cacheNum,long keepAlive){
		
		logger.info("开始启动线程池:" + poolId);
		this.poolId = poolId;
		this.coreTNum = (coreTNum == 0 ? this.coreTNum : coreTNum);
		this.maxTNum = (maxTNum == 0 ? this.maxTNum : maxTNum);
		this.cacheNum = (cacheNum == 0 ? this.cacheNum : cacheNum);
		this.keepAlive =  (keepAlive == 0 ? this.keepAlive : keepAlive);
		executor = new ThreadPoolExecutor(coreTNum, maxTNum,
				keepAlive, TimeUnit.MILLISECONDS,
               new ArrayBlockingQueue<Runnable>(cacheNum),new ThreadPoolExecutor.CallerRunsPolicy());
		moitor = new ThreadPoolMonitor(this);
		taskList = new Vector<IPoolTask>();
		moitor.start();
		
		logger.info("线程池启动完成:" + poolId);
	}
	
	/**
	 * 提交任务 带监控时间
	 * @param task
	 */
	public void excute(IPoolRunnableTask task){
		synchronized (taskList) {
			if(executor != null)
				executor.execute(task);
			if(taskList != null)
				taskList.add(task);
		}
	}
	
	/**
	 * 提交任务不监控超时
	 * @param task
	 */
	public void exWitOutTmout(IPoolRunnableTask task) {
		synchronized (taskList) {
			executor.execute(task);
			if(executor != null)
				executor.execute(task);
		}
	}
	
	/**
	 * 提交任务
	 * @param task
	 * @return
	 */
	public Future submit(IPoolRunnableTask task){
		synchronized (taskList) {
			if(executor != null){
				Future f = executor.submit(task);
				taskList.add(task);
				return f;
			}
			return null;
		}
		
	}
	
	/**
	 * 提交任务
	 * @param task
	 * @return
	 */
	public Future submit(IPoolCallableTask task){
		synchronized (taskList) {
			if(executor != null){
				Future f = executor.submit(task);
				taskList.add(task);
				return f;
			}
			return null;
		}
		
	}
	
	/**
	 * 
	 * 移除 任务
	 * @param task
	 */
	public void removeTask(IPoolTask task){
		synchronized (taskList) {
			if(taskList != null)
				taskList.remove(task);
		}
		
	}
	
	/**
	 * 关闭线程池
	 */
	public void stop(){
		if(!isStop){
			if(moitor != null)
				moitor.stop();
			if(executor != null){
				executor.shutdownNow();
				taskList = null;
				isStop = true;				
			}
		}
		
	}
	
	public List<IPoolTask> getTasks(){
		return taskList;
	}
	
	public long getTmOut(){
		return tmout;
	}
	
	public String getPoolId() {
		return poolId;
	}

	public void setPoolId(String poolId) {
		this.poolId = poolId;
	}
	public  ThreadPoolExecutor getPool(){
		return executor;
	}



	/**
	 * 线程超时监控类
	 * @author liuzhen
	 * @version 1.0
	 * 2016-1-26
	 */
	public class ThreadPoolMonitor implements Runnable{
		private McpThreadPool pool = null;
		private boolean runflg = false;
		private long sleepTm = 120 * 1000;
		
		public ThreadPoolMonitor(McpThreadPool pool){
			this.pool = pool;
		}
		
		/**
		 * 启动监控器
		 */
		public void start(){
			
			if(!runflg){
				logger.info("开始启动线程池" + poolId + "的监控器.......");
				this.runflg = true;
				Thread t = new Thread(this);
				t.start();
				logger.info("启动线程池" + poolId + "的监控器完成");
			}
			
		}
		/**
		 * 关闭监控器
		 */
		public void stop(){
			this.runflg = false;
		}

		@Override
		public void run() {
			while(runflg){
				try{
					if(pool == null)
						runflg = false;
					
//					logger.info(":::::::::::::::::::::::" + pool.getPool().getPoolSize());
//					logger.info("开始检测线程池：" + pool.getPoolId() + "的超时任务......");
					// 获取所有监控任务
					List<IPoolTask> list = pool.getTasks();
					// 待关闭任务
					List<IPoolTask> removeList = new ArrayList<IPoolTask>();
					synchronized (list) {
						if(list != null && list.size() > 0){
							for(IPoolTask task : list){
								if(!task.getRunFlg()){
									removeList.add(task);
									continue;
								}
								// 超时时间
								long tmout = pool.getTmOut();
								// 任务开始时间
								long startTim = task.getStartTim();
								// 系统当前时间
								long curTim = System.currentTimeMillis();
								long t = curTim - startTim; 
								if(t > tmout){
									removeList.add(task);
								}else{
									continue;
								}
							}
							
							for(IPoolTask task : removeList){
								Thread t = task.getThread();
								if(!task.getRunFlg()){
									list.remove(task);
									continue;
								}
								if(t != null && t.isAlive() ){
									try{
										logger.info(task.getThreadId() + "：超时，尝试关闭......");
										t.stop();
										list.remove(task);
										logger.info(task.getThreadId() + "：超时，已经关闭");
									}catch(Throwable e){
									}
								}
								
							}
							removeList = null;
							
						}
					}
//					logger.info("线程池：" + pool.getPoolId() + "的超时任务检测完成......");
					Thread.sleep(sleepTm);
				}catch(Throwable e){
					e.printStackTrace();
				}
			}
		}
	}
	
}
