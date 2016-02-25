package com.sinoway.common.util;
import com.sinoway.common.constant.Constant.MsgUrl;
import com.sinoway.common.entity.HttpCommonEntity;
import com.yzj.wf.common.WFLogger;




	/**
	 * HttpService 云平台配置接口调用实现
	 * @author zhangyanwei
	 *
	 */

	public class HttpSendMessageUtil {

		
		private static HttpSendMessageUtil instance = null;
		private HttpSendMessageUtil(){}
		public  static   HttpSendMessageUtil getInstance(){
			if(instance == null){
				instance = new HttpSendMessageUtil();
			}
			return instance;
		}
		
		private static final WFLogger logger = WFLogger.getLogger(HttpProviderServiceImpl.class);
	
		/**
		 *  将json发送给核心
		 * @author zhangyanwei
		 * @param jsonMessage
		 * @return json
		 */
		public String  sendMessage(String jsonMessage){
			HttpProviderServiceImpl service = new HttpProviderServiceImpl();
	  		 HttpCommonEntity entity = new HttpCommonEntity();
		     entity.setUrl(MsgUrl.MESSAGE_URL.getValue());
		     try {
				entity.setParams(service.parseStringToListParam(jsonMessage));
				entity = service.httpCommunicate(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 String message = (String) entity.getResponse();
			 System.out.println(message);
			 
			 return message;
	  	}
	
	
	
	
	
	}
	
	
	
	
	
	

