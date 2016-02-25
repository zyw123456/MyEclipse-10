package com.sinoway.common.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.sinoway.common.entity.GeneralRptMsgHeader;
import com.sinoway.common.entity.GeneralRptResMsg;
import com.sinoway.common.entity.GeneralRptResMsgBody;
import com.sinoway.common.util.DateUtil;
import com.sinoway.common.util.IOStreamUtil;
import com.sinoway.common.util.JRNGenerator;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.WFLogger;

import net.sf.json.JSONObject;

/**
 *  模拟核心系统的Servlet方法
 * Servlet implementation class McpHttpServlet
 */
public class McpHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final WFLogger logger = WFLogger.getLogger(McpHttpServlet.class);   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public McpHttpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doServlet(request, response);
		} catch (WFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doServlet(request, response);
		} catch (WFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 模拟核心的Servlet方法
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	private void doServlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String chnlcod = request.getParameter("chnlcod");
		String prdcod = request.getParameter("prdcod");
		String ourtrncod = request.getParameter("ourtrncod");
		String isbatch = request.getParameter("isbatch");
		String msgtype = request.getParameter("msgtype");
		String checkcod = request.getHeader("checkcod");
		
		InputStream is = request.getInputStream();
		System.out.println("核心系统收到参数:");
		System.out.println("chnlcod:" + chnlcod);
		System.out.println("prdcod:"+prdcod);
		System.out.println("ourtrncod"+ourtrncod);
		System.out.println("isbatch"+isbatch);
		System.out.println("msgtype"+msgtype);
		System.out.println("checkcod:"+checkcod);
		
		//将流转换为字符串
		String message = IOStreamUtil.Inputstr2Str_byteArr(is, "UTF-8");
		
logger.debug("核心系统收到平台发送报文:" + message);		
		//解析表头
		JSONObject object = JSONObject.fromObject(message);
		GeneralRptResMsg resMsg = new GeneralRptResMsg();
		//解析报文头
		GeneralRptMsgHeader rptheader = null;
		
		PrintWriter out = response.getWriter();
		if(object.get("header") != null){
			JSONObject header = object.getJSONObject("header");
			rptheader = (GeneralRptMsgHeader) JSONObject.toBean(header, GeneralRptMsgHeader.class);
			resMsg.setHeader(rptheader);
			//模拟测试将数据返回
			response.setContentType("text/xml");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("checkcod", checkcod);
			if(StringUtils.isBlank(rptheader.getFnttrnjrn())){
				//生成前置流水
				String fnttrnjrn = JRNGenerator.generateJrn("F", "1", "10000000");
	logger.debug("核心系统生成前置流水号:" + fnttrnjrn);			
				rptheader.setFnttrnjrn(fnttrnjrn);
				Date date = new Date();
				rptheader.setFrnttrndte(DateUtil.dateFormat8Len.format(date));
				rptheader.setFrnttrntim(DateUtil.dateFromatHMSS.format(date));
			}
			rptheader.setStatus("1");
			GeneralRptResMsgBody body = new GeneralRptResMsgBody();
			GeneralRptResMsg msg = new GeneralRptResMsg(rptheader,body);
			String json = JSONObject.fromObject(msg).toString();
	logger.debug("核心系统返回平台的响应报文:" + json);	
			out.println(json);	
			out.flush();
			out.close();
	logger.debug("核心系统调用结束");
		}else{
			String resMessage = "{\"msgcode\":\"3\",\"operator\":\"super\",\"prdcod\":\""+JRNGenerator.generatePrdCod()+"\",\"states\":\"1\",\"result\":\"SUCCESS\"}";
			out.println(resMessage);	
			out.flush();
			out.close();
		}

		
	}

}
