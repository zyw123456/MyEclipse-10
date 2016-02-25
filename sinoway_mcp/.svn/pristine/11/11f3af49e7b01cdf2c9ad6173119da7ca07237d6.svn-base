package com.sinoway.common.constant;

/**
 * 服务端常量
 * @author Liuzhen
 * @version 1.0
 * 2015-11-19
 */
public class ServerConstant {
	/**
	 * 产品原交易队列线程池参数
	 */
	public enum OPtradePoolPam {
		
		/**
		 * 核心线程数
		 */
	    coreTNum(20),
		
		/**
		 *最大线程数
		 */
	    maxTNum(20),
	    /**
		 *缓存线程数
		 */
	    cacheNum(20),
	    
	    /**
	     * 空闲时间
	     */
	    keepAlive(60*1000);


		
		private long value;
		
		private OPtradePoolPam(long value) {
			this.value = value;
		}
		
		public long getValue() {
			return this.value;
		}
	}
	
	/**
	 * 子交易队列线程池参数
	 */
	public enum StradePoolPam {
		
		/**
		 * 核心线程数
		 */
	    coreTNum(20),
		
		/**
		 *最大线程数
		 */
	    maxTNum(20),
	    /**
		 *缓存线程数
		 */
	    cacheNum(20),
	    
	    /**
	     * 空闲时间
	     */
	    keepAlive(60*1000);


		
		private long value;
		
		private StradePoolPam(long value) {
			this.value = value;
		}
		
		public long getValue() {
			return this.value;
		}
	}
	
	/**
	 * 原交易 产品响应线程池
	 */
	public enum OPtradeResPoolPam {
		
		/**
		 * 核心线程数
		 */
	    coreTNum(20),
		
		/**
		 *最大线程数
		 */
	    maxTNum(20),
	    /**
		 *缓存线程数
		 */
	    cacheNum(20),
	    
	    /**
	     * 空闲时间
	     */
	    keepAlive(60*1000);


		
		private long value;
		
		private OPtradeResPoolPam(long value) {
			this.value = value;
		}
		
		public long getValue() {
			return this.value;
		}
	}
	
	/**
	 * 长短连接标识
	 */
	public enum TradePoolType {
		/**
		 * 产品 原交易处理线程池
		 */
		otrade_proc_pool("otrade_proc_pool"),
		
		/**
		 * 产品原交易响应处理线程池
		 */
		otrade_res_proc_pool("otrade_res_proc_pool"),
		
		/**
		 * 子交易处理线程池 
		 */
		strade_proc_pool("strade_proc_pool");
		
		private String value;
		
		private TradePoolType(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
	/**
	 * 长短连接标识
	 */
	public enum ConnType {
		/**
		 * 长连接 
		 */
		LEN("0"),
		
		/**
		 * 短连接 
		 */
		SHORT("1");
		
		private String value;
		
		private ConnType(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
	/**
	 * 响应状态
	 */
	public enum ResSta {
		/**
		 * 成功 
		 */
		S("1"),
		
		/**
		 * 失败 
		 */
		F("0");
		
		private String value;
		
		private ResSta(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
	/**
	 * 服务bean
	 */
	public enum ServiceBean {
		/**
		 * 业务处理 
		 */
		PROCESSER("ibankprocess_service"),
		
		/**
		 * 子交易业务处理bean
		 */
		STRADEPROCESSER("ptrade_processer_service"),
		/**
		 * 报文解析转换
		 */
		PARSETRANFER("msg_parsetransfer_service"),
		
		/**
		 * 异步Socket响应服务
		 */
		ASNYRES_SCOKET_SERVICE("asnyres_scoket_service"),
		
		/**
		 * 异步http响应bean
		 */
		ASNYRES_HTTP_SERVICE("asnyres_http_service"),
		
		/**
		 * 异步https响应bean
		 */
		ASNYRES_HTTPS_SERVICE("asnyres_https_service"),
		
		/**
		 * 异步webservice响应bean
		 */
		ASNYRES_WEBSERVICE_SERVICE("asnyres_webservice_service");
		
		private String value;
		
		private ServiceBean(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
	
}
