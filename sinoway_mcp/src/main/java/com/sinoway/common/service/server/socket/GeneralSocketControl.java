package com.sinoway.common.service.server.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import com.sinoway.base.entity.BCfgdefFntsrvport;
import com.sinoway.common.constant.ServerConstant.TradePoolType;
import com.sinoway.common.frame.SpringContextUtil;
import com.sinoway.common.pool.McpThreadPool;
import com.sinoway.common.pool.ThreadPoolFactory;
import com.sinoway.common.pool.task.GeneralSocketOPtradeTaskService;
import com.sinoway.common.util.GUIDGenerator;
import com.sinoway.common.util.McpLogger;
import com.yzj.wf.com.ibank.common.server.IBankControlException;

/**
 * 通用socket控制服务
 * @author Liuzhen
 * @version 1.0
 *2015-11-7
 */
public class GeneralSocketControl implements GeneralSocketService{
	private McpLogger logger = McpLogger.getLogger(getClass());
	private BCfgdefFntsrvport cfg = null;// 配置
	private String port = null;
	private String connLorS;// 长短连接标示  0-长连接 1-短连接
	private Boolean isStop = true; // 控制器默认停止运行
	private ServerSocket serverSocket = null;
    private Map<String,String> poolIdMap = null;
    private int coreTNum = 20;
    private int maxTNum = 20;
    private int cacheNum = 20;
    private long keepAlive = 60*1000;
    
	public void start() throws IBankControlException {
		
		synchronized (isStop) {
			init();
			isStop = false;
		}
		
		logger.info("开始启动服务组件......，端口号:" + port);
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(Integer.parseInt(port)));
			logger.info("服务组件启动成功，端口号:" + port);

		} catch (NumberFormatException e) {
		
			logger.error("服务组件启动失败: 端口号不是数字，端口号：" + port);
			throw new IBankControlException("服务启动失败: 端口号不是数字，端口号：" + port);
		
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("服务组件启动失败:I/O异常，端口号：" + port, e);
			throw new IBankControlException("服务启动失败:I/O异常，端口号：" + port, e);
		}catch(Exception e){
			logger.error("服务组件组件启动失败，端口号：" + port,e);
			throw new IBankControlException("服务组件启动失败:未知异常，端口号：" + port,e);
		}
		
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				while (!isStop) {
					final Socket socket;
					McpThreadPool pool = null;
					try {
						socket = serverSocket.accept();
						logger.info("接收到连接请求，端口号：" + port);
						// 设置超时时间
						if(cfg.getTmoutlen() != null && !"".equals(cfg.getTmoutlen()))
							socket.setSoTimeout(Integer.parseInt(cfg.getTmoutlen()));
						
						pool = ThreadPoolFactory.getPool(poolIdMap.get(TradePoolType.otrade_proc_pool.getValue()));
						if(pool == null){
							pool = ThreadPoolFactory.newPool(TradePoolType.otrade_proc_pool.getValue(), coreTNum, maxTNum, cacheNum, keepAlive);
						}
						
					} catch (IOException e) {
						logger.error("服务处理异常: I/O异常，端口号：" + port, e);
						continue;
					}catch( NumberFormatException e){
						logger.error("服务处理异常: 超时时间不合法，端口号：" + port, e);
						continue;
					} catch (Exception e) {
						logger.error("服务处理异常: " +e.getMessage()+ "，端口号：" + port, e);
						continue;
					}
					
					GeneralSocketOPtradeTaskService<Socket> task = (GeneralSocketOPtradeTaskService)SpringContextUtil.getBean(GeneralSocketOPtradeTaskService.class);
					task.init(GUIDGenerator.generateId(), pool, socket);
					try{
						pool.excute(task);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			
		});
		t.start();
	}
	
	/**
	 * 关闭服务
	 */
	public void stop() throws IBankControlException {
		logger.info("正在关闭服务组件......，端口号：" + port);
		if (!isStop) {
//			server.stop();
			isStop = true;
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					logger.error("I/O operate exception:", e);
					throw new IBankControlException("I/O operate exception", e);
				}
				serverSocket = null;
			}
		}
		logger.info("关闭服务组件完成，端口号：" + port);
	}

	/**
	 * 由配置文件初始化相关参数和服务标识
	 * 
	 * @throws IBankControlException
	 *             IBankControlException异常
	 */
	private void init() throws IBankControlException {
		
		if (cfg == null) {
			throw new IBankControlException("服务启动失败：服务配置不能为空");
		}
		this.port = cfg.getPort();
		if(port == null || "".equals(port.trim()))
			throw new IBankControlException("服务启动失败：端口号不能为空");
		this.connLorS = cfg.getContype();
		if(port == null || "".equals(port.trim()))
			throw new IBankControlException("服务启动失败：连接类型不能为空");
		

//		// 查找服务组件
//		server = (IBankServer) serviceFactory.getService(IBankServer.class, TradeService.IBANKSERVER.getValue());
//		if (server == null) {
//			throw new IBankControlException("服务启动失败：服务组件不存在，端口号：" + port);
//		}

	}
	
	public void initCfg(BCfgdefFntsrvport entity) {
		this.cfg = entity;
	}
	
	/*
	 *   GETTER   OR   SETTER
	 */
	
	public String getPort() {
		return port;
	}
	
	public String getConnLorS() {
		return connLorS;
	}

	public void setConnLorS(String connLorS) {
		this.connLorS = connLorS;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Map<String, String> getPoolIdMap() {
		return poolIdMap;
	}

	public void setPoolIdMap(Map<String, String> poolIdMap) {
		this.poolIdMap = poolIdMap;
	}
	
}
