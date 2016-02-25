package com.sinoway.common.constant;

/**
 * 报文解析时用到的常量
 * @author Liuzhen
 * @version 1.0
 * 2015-11-19
 */
public class MsgTransfConstant {

	/**
	 * MsgType报文类别
	 */
	public enum MsgType {
		/**
		 * 报文为JSON格式
		 */
		JSON("0"),
		
		/**
		 * 报文为xml格式
		 */
		XML("1");
		
		private String value;
		
		private MsgType(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
	/**
	 * MsgType报文类别
	 */
	public enum MsgTemplateType {
		/**
		 * 请求报文
		 */
		REQ("1"),
		/**
		 * 响应报文
		 */
		RES("2"),
		/**
		 * 异步本地即时响应报文
		 */
		ASYNSRES("3"),
		/**
		 * 异步客户端即时响应报文
		 */
		ASYNCRES("4"),
		/**
		 * 结果获取报文
		 */
		ASYNRESREQ("5"),
		
		/**
		 * 重发报文
		 */
		RESEND("8"),
		
		/**
		 * 校验报文  长连接使用
		 */
		CHECK("9");
		
		private String value;
		
		private MsgTemplateType(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}
	}
}
