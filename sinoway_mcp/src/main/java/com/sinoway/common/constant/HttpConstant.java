package com.sinoway.common.constant;


/**
 * Http通讯的常量
 * @author miles
 *
 */
public class HttpConstant {

	
	/**
	 * Http 方法常量:post和get两个方法
	 * @author miles
	 *
	 */
	public enum HttpMethod{
		
		/**
		 * Http post 方法
		 */
		HTTP_METHOD_POST("post"),
		
		/**
		 * Http get 方法
		 */
		HTTP_METHOD_GET("get");
		
		private HttpMethod(String code){
			this.code = code;
		}
		
		private String code;
		
		public String getCode(){
			return code;
		}
		
		
	}
	
	
	
	/**
	 * 参数类型常量
	 * @author miles
	 *
	 */
	public enum HttpParamType{
		/**
		 * 参数类型:普通参数
		 */
		PARAMETER_TYPE_PARAMETERS("0"),
		
		/**
		 * 参数类型: 文件流
		 */
		PARAMTER_TYPE_STREAM("1");
		
		private String code;
		
		private HttpParamType(String code){
			this.code = code;
		}
		
		public String getCode(){
			return code;
		}
		
		
	}
	
	public enum HttpOvertime{
		
		/**
		 * http连接超时时间
		 */
		HTTP_OVERTIME_CONNTIMEOUT(10000),
		
		/**
		 * http传输超时时间
		 */
		HTTP_OVERTIME_SOCKETTIMEOUT(60000);
		
		
		private int code;
		
		private HttpOvertime(int code){
			this.code=code;
		}
		
		public int getCode(){
			return code;
		}
		
		
	}
	
	
	
}
