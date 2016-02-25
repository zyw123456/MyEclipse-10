package com.sinoway.common.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sinoway.common.constant.Constant.HttpStatus;
import com.sinoway.common.constant.Constant.MsgCode;
import com.sinoway.common.service.cfg.CommonOrganService;
import com.sinoway.common.service.cfg.CommonProductService;
import com.sinoway.common.service.cfg.CommonUserService;

/**
 * 核心的pub接口
 */
public class CommonConfigAction extends ActionSupport {
	Logger logger = LogManager.getLogger(CommonConfigAction.class.getName());
	private static final long serialVersionUID = 1L;

	@Autowired
	private CommonOrganService commonOrganService;

	@Autowired
	private CommonProductService commonProductService;

	@Autowired
	private CommonUserService commonUserService;

	/**
	 * 产品策略新增
	 * 
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public String insertProductInfo() throws UnsupportedEncodingException,
			IOException {
		HttpServletResponse response = null;
		OutputStreamWriter outs = null;
		JSONObject backobj = new JSONObject();
		JSONObject jo = null;
		// 获取request
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		String result = "";
		try {
			request.setCharacterEncoding("UTF-8");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			String line = "";
			StringBuffer buf = new StringBuffer();
			while ((line = br.readLine()) != null) {
				buf.append(line);
			}
			result = buf.toString();

			// 将字符串转换成JSON对象
			jo = (JSONObject) JSONObject.parse(result);
			logger.info("前置接收的报文--"+jo.toJSONString());
			// 用于拼接返回报文
            StringBuffer msg = new StringBuffer();
           
			if (jo.get("msgcode").toString().equals(MsgCode.MSGCODE_ADDPRD.getValue())) {
				backobj = (JSONObject) commonProductService.addprdInfo(jo);
			} else if (jo.get("msgcode").toString().equals(MsgCode.MSGCODE_UPDATEPRD.getValue())) { // 产品策略修改
				backobj = (JSONObject) commonProductService.editprdInfo(jo);
			} else if (jo.get("msgcode").toString().equals(MsgCode.MSGCODE_DELPRD.getValue())) {// 产品策略删除
				backobj = (JSONObject) commonProductService.delprdInfo(jo);
			} else if (jo.get("msgcode").toString().equals(MsgCode.MSGCODE_ADDCHILDACC.getValue())) {// 用户新增
				backobj = (JSONObject) commonUserService.addusrInfo(jo);
			} else if (jo.get("msgcode").toString().equals(MsgCode.MSGCODE_UPDATECHILDACC.getValue())) {// 用户修改
				backobj = (JSONObject) commonUserService.editusrInfo(jo);
			} else if (jo.get("msgcode").toString().equals(MsgCode.MSGCODE_DELETECHILDACC.getValue())) {// 用户删除
				backobj = (JSONObject) commonUserService.delusrInfo(jo);
			} else if (jo.get("msgcode").toString().equals(MsgCode.MSGCODE_ADDORGTEAM.getValue())) { // 机构新增
				backobj = (JSONObject) commonOrganService.addOrganInfo(jo);
			} else if (jo.get("msgcode").toString().equals(MsgCode.MSGCODE_UPDATEORGTEAM.getValue())) {// 机构修改
				backobj = (JSONObject) commonOrganService.editOrganInfo(jo);
			} else if (jo.get("msgcode").toString().equals(MsgCode.MSGCODE_DELETEORGTEAM.getValue())) {// 机构删除
				backobj = (JSONObject) commonOrganService.delOrganInfo(jo);
			} 
	
			// 报文返回响应到前置

			response = (HttpServletResponse) context
					.get(ServletActionContext.HTTP_RESPONSE);
			response.setHeader("cache-control", "no-cache");
			response.setContentType("text/html");
			outs = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
			outs.write(backobj.toString());
			outs.flush();
		} catch (Throwable e) {
			logger.error("核心处理响应报文出错--"+backobj.toJSONString());
			backobj.put("msgcode", jo.get("msgcode").toString());
			backobj.put("states", HttpStatus.STATIS_FAIL.getCode()); // 状态 0 -失败 1-表示成功
			backobj.put("result", "请求报文出现异常"); // 返回的描述
			response = (HttpServletResponse) context
					.get(ServletActionContext.HTTP_RESPONSE);
			response.setHeader("cache-control", "no-cache");
			response.setContentType("text/html");
			outs = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
			outs.write(backobj.toString());
			outs.flush();
			e.printStackTrace();
		}

		return null;
	}
         
}
