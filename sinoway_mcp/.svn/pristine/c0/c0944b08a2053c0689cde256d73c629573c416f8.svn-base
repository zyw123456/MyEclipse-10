package com.sinoway.common.service.init;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sinoway.common.util.McpLogger;
import com.sinoway.common.util.SystemOperateUtil;
import com.yzj.wf.com.ibank.common.IBankDefine.TradeService;
import com.yzj.wf.com.ibank.common.server.IBankServer;
import com.yzj.wf.com.ibank.common.server.IBankServerException;
import com.yzj.wf.com.ibank.servicefactory.ServiceFactory;

public class StartServlet extends HttpServlet {
	private McpLogger logger = McpLogger.getLogger(getClass());
	
	public StartServlet() {
		super();
	}

	
	public void destroy() {
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		StartService startService = (StartService)ac.getBean("startService");
//		ServiceFactory sc = (ServiceFactory)ac.getBean("serviceFactory");
//        IBankServer server = (IBankServer) sc.getService(IBankServer.class, TradeService.IBANKSERVER.getValue());
//        try {
//			server.start();
//		} catch (IBankServerException e) {
//			e.printStackTrace();
//		}
		logger.info("开始停止服务.......");
		// 启动服务
		startService.stop();
		
		logger.info("停止服务启动完成");
		super.destroy(); 
	
	}
	public void init() throws ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		StartService startService = (StartService)ac.getBean("startService");
		SystemOperateUtil systemOpUtil = (SystemOperateUtil)ac.getBean("SystemOperateUtil");
		
		logger.info("开始启动系统操作工具.......");
		// 启动服务
		systemOpUtil.init();
		
		logger.info("启动系统操作工具完成");
		
		logger.info("开始启动服务.......");
		// 启动服务
		startService.init();
		
		logger.info("启动服务启动完成");
		
		
		
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}


}
