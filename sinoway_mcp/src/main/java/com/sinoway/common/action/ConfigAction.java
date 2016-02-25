package com.sinoway.common.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sinoway.common.constant.Constant;
import com.sinoway.common.constant.SystemConstant;
import com.sinoway.common.service.OrganService;
import com.sinoway.common.service.ProductService;
import com.sinoway.common.service.UserService;
import com.sinoway.common.util.McpLogger;
/**
 * 前置转换报文action
 * @author zhangyanwei
 *
 */
public class ConfigAction extends ActionSupport{
  
	public McpLogger logger = McpLogger.getLogger(getClass());
	private static final long serialVersionUID = 1L;

	@Autowired
	private OrganService organService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	
	
	/**
	 * 将json串转发送给核心
	 * @author zhangyanwei
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	public String changeForPubJson() throws UnsupportedEncodingException, IOException{
		//获取request
	    ActionContext context=ActionContext.getContext();  
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);  
			String result = ""; 
			String repStr = null;
			OutputStreamWriter outs = null;
			HttpServletResponse response = null;
			JSONObject jo = null;
			try {
				
			request.setCharacterEncoding(SystemConstant.DEFAULT_CHARSET);
			
	        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),SystemConstant.DEFAULT_CHARSET));  
	     
	        String line = "";  
	        StringBuffer buf = new StringBuffer();  
	        while ( (line = br.readLine()) != null ) {  
	            buf.append(line);  
	        }  
	        result = buf.toString();  
	 
		    //将字符串转换成JSON对象
	        jo =(JSONObject) JSONObject.parse(result);
	    	logger.debug("前置json解析完之后的串--------------------------------"+jo.toJSONString());
		
			
			if(jo.get("msgcode").toString().equals(Constant.MsgCode.MSGCODE_ADDPRD.getValue())){
				//新增产品
				repStr = (String) productService.addprdInfo(result);
			}else if(jo.get("msgcode").toString().equals(Constant.MsgCode.MSGCODE_UPDATEPRD.getValue())){
				//修改产品
				repStr = (String) productService.editprdInfo(result);
			}else if (jo.get("msgcode").toString().equals(Constant.MsgCode.MSGCODE_DELPRD.getValue())){
				//删除产品
				repStr = (String) productService.editprdInfo(result);
			}else if(jo.get("msgcode").toString().equals(Constant.MsgCode.MSGCODE_ADDCHILDACC.getValue())){
				//新增用户
				repStr = (String) userService.addusrInfo(result);
			}else if(jo.get("msgcode").toString().equals(Constant.MsgCode.MSGCODE_UPDATECHILDACC.getValue())){
				//修改用户
				repStr = (String) userService.editusrInfo(result);
			}else if (jo.get("msgcode").toString().equals(Constant.MsgCode.MSGCODE_DELETECHILDACC.getValue())){
				//删除用户
				repStr = (String) userService.editusrInfo(result);
			}else if(jo.get("msgcode").toString().equals(Constant.MsgCode.MSGCODE_ADDORGTEAM.getValue())){
				//新增机构
				repStr = (String) organService.addOrganInfo(result);
			}else if(jo.get("msgcode").toString().equals(Constant.MsgCode.MSGCODE_UPDATEORGTEAM.getValue())){
				//修改机构
				repStr = (String) organService.editOrganInfo(result);
			}else if (jo.get("msgcode").toString().equals(Constant.MsgCode.MSGCODE_DELETEORGTEAM.getValue())){
				//删除机构
				repStr = (String) organService.editOrganInfo(result);
			}else if(jo.get("msgcode").toString().equals(Constant.MsgCode.MSGCODE_UPDATEPWD.getValue())){
				//密码修改
				repStr = (String) userService.editusrInfo(result);
			}
		
		    //报文返回响应到客户端

	        response = (HttpServletResponse)context.get(ServletActionContext.HTTP_RESPONSE);  
		    response.setHeader("cache-control", "no-cache");    
	        response.setContentType("text/html");  
		    outs= new OutputStreamWriter(response.getOutputStream(),SystemConstant.DEFAULT_CHARSET);
			outs.write(repStr);
			outs.flush();
			
			} catch (Throwable e) {
				JSONObject backobj = new JSONObject();
				backobj.put("msgcode", jo.get("msgcode").toString());
				backobj.put("states", "0"); // 状态 0 -失败 1-表示成功
				backobj.put("result", "请求报文出现异常"); // 返回的描述
				repStr = backobj.toJSONString();
				response = (HttpServletResponse) context
						.get(ServletActionContext.HTTP_RESPONSE);
				response.setHeader("cache-control", "no-cache");
				response.setContentType("text/html");
				outs = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
				outs.write(	repStr);
				outs.flush();
				e.printStackTrace();
			}
			
		  return null;
	}
	
	

	

		
		
}
