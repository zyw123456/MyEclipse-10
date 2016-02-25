package com.sinoway.cache.memcache.utils;

import org.apache.commons.lang3.StringUtils;

public class MemcachConnectConfig {
	
	public MemcachConnectConfig() {
		
//		
//		Properties p = new Properties();
//		
//		try {
//			URL url = MemcachConnectConfig.class.getClassLoader().getResource("");
//			String path= url.getPath().substring(1);
//			//读取memcache-default.properties配置文件
//			File filepath =new File(path,"cache-default.properties");
//			if (!filepath.exists()) {//linux
//				filepath=new File("/"+filepath);
//			}
//			
//			FileInputStream in= new FileInputStream(filepath);
//			p.load(in);
//		} catch (Exception e) {
//			System.err.println("memcache配置文件初始化失败：-------无法读取配置文件cache-default.properties");
//			e.printStackTrace();
//		}
//		
//		//加载memcache服务器列表
//		String hostList = p.getProperty("memeched_hostList").trim();
//		if (StringUtils.isNoneBlank(hostList)) {
//			this.hostList = hostList.split(",");
//		}
//		
//		//加载权重配置
//		String weights = p.getProperty("memeched_weights");
//		if (StringUtils.isNoneBlank("weights")) {
//			Integer[] w=new Integer[this.hostList.length];
//			String[] split = weights.split(",");
//			if (split.length==this.hostList.length) {//如果权重数和服务器列表数一致，以weights配置为准
//				for (int i = 0; i < split.length; i++) {
//					try {
//						w[i]=Integer.parseInt(split[i].trim());
//					} catch (Exception e) {
//						throw new IllegalArgumentException("初始化memcached配置错误：-----权重参数必须是正确的整数！");
//					}
//				}
//				this.weights=w;
//			}else{//如果权重数和服务器列表数不一致，强制设置所有服务器权重都为3
//				for (int i = 0; i < w.length; i++) {
//					w[i]=3;
//				}
//				this.weights=w;
//			}
//		}
//		
//		//加载Nagle
//		String nagle = p.getProperty("memeched_nagle").trim();
//		if (nagle.trim().equalsIgnoreCase("false")) {
//			this.nagle=false;
//		}else{
//			this.nagle=true;
//		}
//		
//		//加载初始化链接数
//		String initConn = p.getProperty("memeched_initConn").trim();
//		if (StringUtils.isNumeric(initConn)&&Integer.parseInt(initConn)>0) {
//			this.initConn=Integer.parseInt(initConn);
//		}else{
//			throw new IllegalArgumentException("初始化memcached配置错误：-----初始化链接数必须是正确的大于零的整数！");
//		}
//		
//		//加载链接池中最小保持链接数
//		String minConn = p.getProperty("memeched_minConn").trim();
//		if (StringUtils.isNumeric(minConn)&&Integer.parseInt(initConn)>0) {
//			this.minConn=Integer.parseInt(minConn);
//		}else{
//			throw new IllegalArgumentException("初始化memcached配置错误：-----链接池中最小保持链接数必须是正确的大于零整数！");
//		}
//		
//		//加载链接池最大链接数
//		String maxConn = p.getProperty("memeched_maxConn").trim();
//		if (StringUtils.isNumeric(maxConn)&&Integer.parseInt(maxConn)>this.minConn) {
//			this.maxConn=Integer.parseInt(maxConn);
//		}else{
//			throw new IllegalArgumentException("初始化memcached配置错误：-----链接池中最大保持链接数必须是正确的大于最小链接数的整数！");
//		}
//		
//		//加载一个连接最大空闲时间，单位ms
//		String maxIdle = p.getProperty("memeched_maxIdle").trim();
//		if (StringUtils.isNumeric(maxConn)&&Integer.parseInt(maxIdle)>0) {
//			this.maxIdle=Integer.parseInt(maxIdle);
//		}else{
//			throw new IllegalArgumentException("初始化memcached配置错误：-----连接最大空闲时间必须是正确的正整数！");
//		}
//		
//		//加载主线程睡眠时间，单位m
//		String maintSleep = p.getProperty("memeched_maintSleep").trim();
//		if (StringUtils.isNumeric(maintSleep)&&Integer.parseInt(maintSleep)>0) {
//			this.maintSleep=Integer.parseInt(maintSleep);
//		}else{
//			throw new IllegalArgumentException("初始化memcached配置错误：-----主线程睡眠时间必须是正确的正整数！");
//		}
//		
//		//加载读取 超时时间，单位m
//				String socketTO = p.getProperty("memeched_socketTO").trim();
//				if (StringUtils.isNumeric(socketTO)&&Integer.parseInt(socketTO)>0) {
//					this.socketTO=Integer.parseInt(socketTO);
//				}else{
//					throw new IllegalArgumentException("初始化memcached配置错误：-----读取 超时时间必须是正确的正整数！");
//				}
//				
//		//链接等待超时 
//				String socketConnectTO = p.getProperty("memeched_socketConnectTO").trim();
//				if (StringUtils.isNumeric(socketConnectTO)&&Integer.parseInt(socketTO)>=0) {
//					this.socketConnectTO=Integer.parseInt(socketConnectTO);
//				}else{
//					throw new IllegalArgumentException("初始化memcached配置错误：-----链接等待超时间必须是正确的正整数！");
//				}
//				
//		//加载设置hash算法
//			String hashingAlg = p.getProperty("memeched_hashingAlg").trim();
//			if (StringUtils.isNumeric(hashingAlg)&&Integer.parseInt(hashingAlg)>=0&&Integer.parseInt(hashingAlg)<=3) {
//				this.hashingAlg=Integer.parseInt(hashingAlg);
//			}else{
//					throw new IllegalArgumentException("初始化memcached配置错误：-----链接等待超时间必须是正确的正整数(0,1,2,3)！");
//			}
		
	}
	/**
	 * memcache服务器列表
	 */
	public  String[] hostList;
	public String hostListStr;
	/**
	 * 服务器权重，指定memcached服务器负载量
	 */
	public Integer[] weights;
	public String weightStr;
	/**
	 * 设置是否使用Nagle算法，因为我们的通讯数据量通常都比较大（相对TCP控制数据）而且要求响应及时，因此该值需要设置为false（默认是false）
	 */
	public boolean nagle=false;
	/**
	 * 初始化链接数(默认5)
	 */
	public int initConn=25;
	/**
	 * 链接池中最小保持链接数(默认25)
	 */
	public int minConn=25;
	/**
	 * 链接池最大链接数(默认200)
	 */
	public int maxConn=1024;
	/**
	 * 一个连接最大空闲时间，单位ms (默认1000*60*45)
	 */
	public int maxIdle=2700000;
	/**
	 * 连接池维护线程的睡眠时间，单位m (默认180)
	 */
	public int maintSleep=180;
	/**
	 * 读取 超时时间，单位m (默认180)
	 */
	public int socketTO=180;
	/**
	 * 链接等待超时 (默认180)
	 */
	public int socketConnectTO=180;
	/**
	 * 设置hash算法
	 * 0:使用String.hashCode()获得hash code,该方法依赖JDK，可能和其他客户端不兼容
	 * 1: 使用original 兼容hash算法，兼容其他客户端
	 * 2:使用CRC32兼容hash算法，兼容其他客户端，性能优于original算法
	 * 3:使用MD5 hash算法
	 */
	public int hashingAlg=3;
	
	
	public String[] getHostList() {
		if (StringUtils.isNotBlank(hostListStr)) {
			String[] split = hostListStr.split(",");
			
			if (split!=null&&split.length>0) {
				this.hostList=split;
			}
		}
		return hostList;
	}
	
	public void setHostList(String[] hostList) {
		this.hostList = hostList;
	}

	public Integer[] getWeights() {
		if (StringUtils.isNotBlank(weightStr)) {
			String[] split = weightStr.split(",");
			if (split!=null&&split.length>0) {
				Integer[] tem = new Integer[split.length];
				boolean check=true;
				for (int i = 0; i < split.length; i++) {
					if (StringUtils.isNumeric(split[i])) {
						tem[i]=Integer.parseInt(split[i]);
					}else{
						check=false;
						break;
					}	
				}
				if (check&&tem.length>0) {
					this.weights=tem;
				}
				
			}
		}
		return weights;
	}
	public void setWeights(Integer[] weights) {
		this.weights = weights;
	}

	public int getInitConn() {
		return initConn;
	}
	
	public void setInitConn(int initConn) {
		this.initConn = initConn;
	}
	
	public int getMinConn() {
		return minConn;
	}
	
	public void setMinConn(int minConn) {
		this.minConn = minConn;
	}
	
	public int getMaxConn() {
		return maxConn;
	}
	
	public void setMaxConn(int maxConn) {
		this.maxConn = maxConn;
	}
	
	public int getMaxIdle() {
		return maxIdle;
	}
	
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}
	
	public int getMaintSleep() {
		return maintSleep;
	}
	
	public void setMaintSleep(int maintSleep) {
		this.maintSleep = maintSleep;
	}
	public int getSocketTO() {
		return socketTO;
	}
	
	public void setSocketTO(int socketTO) {
		this.socketTO = socketTO;
	}
	
	public int getSocketConnectTO() {
		return socketConnectTO;
	}
	
	public void setSocketConnectTO(int socketConnectTO) {
		this.socketConnectTO = socketConnectTO;
	}
	
	public int getHashingAlg() {
		return hashingAlg;
	}
	
	public void setHashingAlg(int hashingAlg) {
		this.hashingAlg = hashingAlg;
	}
	
	public boolean isNagle() {
		return nagle;
	}
	
	public void setNagle(boolean nagle) {
		this.nagle = nagle;
	}

	public String getHostListStr() {
		return hostListStr;
	}

	public void setHostListStr(String hostListStr) {
		this.hostListStr = hostListStr;
		if (StringUtils.isNotBlank(hostListStr)) {
			String[] split = hostListStr.split(",");
			
			if (split!=null&&split.length>0) {
				this.hostList=split;
			}
		}
	}

	public String getWeightStr() {
		return weightStr;
	}

	public void setWeightStr(String weightStr) {
		this.weightStr = weightStr;
		if (StringUtils.isNotBlank(weightStr)) {
			String[] split = weightStr.split(",");
			if (split!=null&&split.length>0) {
				Integer[] tem = new Integer[split.length];
				boolean check=true;
				for (int i = 0; i < split.length; i++) {
					if (StringUtils.isNumeric(split[i])) {
						tem[i]=Integer.parseInt(split[i]);
					}else{
						check=false;
						break;
					}	
				}
				if (check&&tem.length>0) {
					this.weights=tem;
				}
				
			}
		}
	}
	
	

}
