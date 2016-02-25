package com.sinoway.common.constant;


/**
 * 系统常量
 * @author Liuzhen
 * @version 1.0
 * 2015-11-19
 */
public class SystemConstant {
	public static final String     NOT_TRCODE = "00000000"; // 非交易码
	public static final String     NOT_PRDCODE = "00000000";// 非产品码
	public static final String     NOT_CHNLCODE = "00000000";// 非渠道编码
	public static final String     SYS_CODE = "F";// 系统编码
	public static final String     CORE_TRNCODE_KEY = "trncod";// 核心交易码key
	public static final String     CLIENT_TRNCODE_KEY = "trncods";// 客户端交易码key
	public static final String     CLIENT_TRNINFO_KEY = "trninfo";// 客户端交易信息key
	public static final String     DEFAULT_CHARSET = "utf-8"; 
	public static final String     CHARSET_GBK = "gbk";

	
	
	
	/**
	 * 系统参数编码
	 */
	public enum SysParamCode {
		
		/**
		 * socket服务默认调用bean
		 */
		DEFAUT_SOCKET_SER_BEAN("1000000000"),
		
		/**
		 * 日志队列topic
		 */
		LOG_QUE_TOPIC("1000000001"),
		
		/**
		 * 日志队列groupId
		 */
		LOG_QUE_GROUPID("1000000002"),
		
		/**
		 * 交易默认超时时长
		 */
		TRADE_DEF_TMOUT("1000000003"),
		/**
		 * 交易默认超时次数
		 */
		TRADE_DEF_TMNUM("1000000004"),
		/**
		 * 产品默认超时时长
		 */
		PRD_DEF_TMOUT("1000000005"),
		/**
		 * 产品默认超时次数
		 */
		PRD_DEF_TMNUM("1000000006"),
		
		/**
		 * 核心同步交易地址
		 */
		CORE_SYN_URL("1000000007");
		
		private String value;
		
		private SysParamCode(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	/**
	 * 日志类别
	 */
	public enum LogType {
		
		/**
		 * 交易日志
		 */
		TRADE("0"),
		/**
		 * 超时日志
		 */
		TIMEOUT("1"),
		/**
		 * 异常
		 */
		ERROR("2");
		
		private String value;
		
		private LogType(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
	/**
	 * 是  否
	 */
	public enum IsOrNot {
		
		/**
		 * 是
		 */
		IS("1"),
		/**
		 * 否
		 */
		NOT("0");
		private String value;
		
		private IsOrNot(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
	
	/**
	 * 处理方
	 */
	public enum ProcSide {
		
		/**
		 * 客户端
		 */
		CLIENT("0"),
		/**
		 * 核心
		 */
		CORE("1"),
		/**
		 * 本地
		 */
		LOCAL("2"),
		/**
		 * 供应商
		 */
		SUPPLY("3");
		
		private String value;
		
		private ProcSide(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
	/**
	 * 交互处理类型
	 */
	public enum InterOpType {
		
		/**
		 * 请求发起
		 */
		REQSEND("0"),
		/**
		 * 结果响应
		 */
		RESRES("1"),
		/**
		 * 结果获取
		 */
		RESGET("2");
		
		private String value;
		
		private InterOpType(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
	/**
	 * 结果响应方式
	 */
	public enum ResResType {
		/**
		 * 主动
		 */
		ACTIVE("0"),
		/**
		 * 被动
		 */
		PASSIVITY("1");
		
		private String value;
		
		private ResResType(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
	/**
	 * 交易地址协议
	 */
	public enum AddrProtocl {
		/**
		 * socket
		 */
		SOCKET("01"),
		/**
		 * HTTP
		 */
		HTTP("02"),
		
		/**
		 * webService
		 */
		WEBSERVICE("03"),
		
		/**
		 * HTTPS
		 */
		HTTPS("04");
		
		private String value;
		
		private AddrProtocl(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
	/**
	 * 交互模式
	 */
	public enum InterMode {
		/**
		 * 同步短链 
		 */
		SYNSHORT("0"),
		/**
		 * 异步短链
		 */
		ASYNSHORT("1"),
		
		/**
		 * 同步长链
		 */
		SYNLONG("2"),
		
		/**
		 * 异步长链 
		 */
		ASYNLONG("3");
		
		private String value;
		
		private InterMode(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
	/**
	 * 原子交易产品类别标识
	 */
	public enum SOPtrnTypt {
		/**
		 * 原交易 
		 */
		O("0"),
		/**
		 * 子交易 
		 */
		S("1"),
		
		/**
		 * 产品 
		 */
		P("2");
		
		private String value;
		
		private SOPtrnTypt(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
	/**
	 * 队列类型
	 */
	public enum QueType {
		/**
		 * 请求 
		 */
		REQ("0"),
		/**
		 * 响应
		 */
		RES("1");
		
		private String value;
		
		private QueType(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
	/**
	 *交易结果成功标识
	 */
	public enum TradeResFlg {

		/**
		 * 没有结果 
		 */
		NORES("0"),
		
		/**
		 * 核心已响应
		 */
		CORERES("1"),
		
		/**
		 * 成功 已响应客户端
		 */
		SUCESS("2"),
		/**
		 * 核心超时 
		 */
		COREOUT("3"),
		/**
		 * 响应 失败
		 */
		RESFAIL("4"),
		
		/**
		 * 异步接收响应失败 
		 */
		ASYNREVRESFAIL("5"),
		
		/**
		 * 其他异常 
		 */
		OTHERR("6");
		
		private String value;
		
		private TradeResFlg(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
	/**
	 * 子交易结果成功标识
	 */
	public enum STradeResFlg {

		/**
		 * 没有结果 
		 */
		NORES("0"),
		
		/**
		 * 已发送数据源
		 */
		SENSUP("1"),
		
		/**
		 * 数据源超时
		 */
		SUPTMOUT("2"),
		/**
		 * 处理成功
		 */
		SUCCESS("3"),
		/**
		 * 出现异常
		 */
		ERROR("4"),
		
		/**
		 * 供应商已响应
		 */
		SUPRES("5"),
		/**
		 * 超时处理
		 */
		TMOUTPROC("6");
		
		
		private String value;
		
		private STradeResFlg(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}

	/**
	 * 业务处理标示
	 */
	public enum BusProcSta {

		/**
		 * 失败 
		 */
		F("0"),
		/**
		 * 成功 
		 */
		S("1");
		
		private String value;
		
		private BusProcSta(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
	/**
	 * 交易日志标示
	 */
	public enum TradeLogSta {

		/**
		 * 失败 
		 */
		F("0"),
		/**
		 * 成功 
		 */
		S("1");
		
		private String value;
		
		private TradeLogSta(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
}
