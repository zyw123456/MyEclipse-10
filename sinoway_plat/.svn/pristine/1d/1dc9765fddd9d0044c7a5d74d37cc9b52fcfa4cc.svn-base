package com.sinoway.common.handler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.sinoway.common.entity.GeneralRptMsgHeader;
import com.sinoway.common.entity.GeneralRptResMsg;
import com.sinoway.common.entity.GeneralRptResMsgBody;
import com.sinoway.common.util.Constant;
import com.sinoway.common.util.Constant.HttpStatus;
import com.sinoway.common.util.Constant.MsgType;
import com.sinoway.common.util.DateUtil;
import com.sinoway.common.util.IOStreamUtil;
import com.sinoway.common.util.JRNGenerator;
import com.sinoway.common.util.MessageStorageUtil;
import com.sinoway.rpt.entity.WfDatCreditrpt;
import com.sinoway.rpt.service.impl.WfDatCreditrptService;
import com.yzj.wf.base.util.SpringContextHelper;
import com.yzj.wf.common.WFException;
import com.yzj.wf.common.WFLogger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 接收核心系统发起的报文
 * 
 * @author miles
 *
 */
public class HttpHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final WFLogger logger = WFLogger.getLogger(HttpHandler.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HttpHandler() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doHandler(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doHandler(request, response);
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 统一接收核心发起的报文接口,将数据写回相应的文件目录下
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws WFException 
	 * @throws Exception 
	 */
	private void doHandler(HttpServletRequest request, HttpServletResponse response) throws IOException, WFException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Charset", "utf-8");
		response.setContentType("text/html;charset=UTF-8");
logger.debug("平台Servlet类开始....");		
		InputStream ins = request.getInputStream();
		
		String message = IOStreamUtil.InputStream2String(ins, "UTF-8");
		logger.debug("平台接收的报文信息:" + message);
		
		
		String chnlcod = request.getParameter("chnlcod");
		String prdcod = request.getParameter("prdcod");
		String trncod = request.getParameter("trncod");
		String isbatch = request.getParameter("isbatch");
		String msgtype = request.getParameter("msgtype");
		
		
		String chkcod = request.getHeader("checkCod");
		
		//返回响应信息
		String res = null;
		
		// 将消息转换为字符串
//		BasicHttpEntity content = new BasicHttpEntity();
//		content.setContent(ins);
//		
//		byte[] data = EntityUtils.toByteArray(content);
//		EntityUtils.consumeQuietly(content);
		

//logger.debug("开始校验平台接收的报文.....");
//		if(!chkcod.equals(HttpParamUtil.generateCheckCodeByMD5(chnlcod, Constant.getSecretKey(), message))){
//logger.error("平台校验报文失败: 报文校验不匹配");
//			throw new VerifiedException("平台校验报文失败: 报文校验不匹配!");
//		}
//logger.debug("校验平台接收的报文成功");
		
		GeneralRptMsgHeader rptheader = new GeneralRptMsgHeader();
		try{
			if (StringUtils.isNotBlank(message)) {
	logger.debug("开始解析报文....");
				// 将json流解析为json对象
				JSONObject object = JSONObject.fromObject(message);
				if (null != object.get("header")) {
					JSONObject header = object.getJSONObject("header");
					rptheader = (GeneralRptMsgHeader) JSONObject.toBean(header, GeneralRptMsgHeader.class);

					String rptid = null;
					String httpType = MsgType.MESSAGE_TYPE_RESPONSE.getCode();
					String fntjrn = rptheader.getFnttrnjrn();
					// 拼接前置的日期和时间
					String timeField = null;
					String fileName = rptid +"-"+ httpType;
					//获取报告Service 
					WfDatCreditrptService creditrptService = (WfDatCreditrptService) SpringContextHelper
							.getBean("rptService");
					
					String filePath = null;
					WfDatCreditrpt creditrpt = null;
					String fileDir = null;
					String reqAddr = null;
					//首先查表记录
					if(StringUtils.isNotBlank(fntjrn)){
						creditrpt = creditrptService.getWfDatCreditrptByFntjrn(fntjrn);
						rptid = creditrpt.getRptid();
					}else{
						logger.error("前置流水号为空!");
						//前置流水号为空
						GeneralRptResMsgBody body = new GeneralRptResMsgBody();
						GeneralRptResMsg resMsg = new GeneralRptResMsg(rptheader, body);
						rptheader.setStatus(HttpStatus.STATIS_FAIL.getCode());
						rptheader.setResult("前置流水号为空!");
						res = JSONObject.fromObject(resMsg).toString();
						
						logger.debug("平台返回核心系统的响应报文" + res);
						PrintWriter pw = response.getWriter();
						// 发送返回响应报文
						pw.write(res);
						pw.flush();
						pw.close();
						return;
					}
							
					//若报告号没有则创建一个新的
					if (null == creditrpt || StringUtils.isBlank(creditrpt.getRptid())) {
						creditrpt = new WfDatCreditrpt();
						try {
							creditrpt.setRptid(JRNGenerator.generateJrn("P", "1", "00000001"));
							rptid = creditrpt.getRptid();
							timeField = DateUtil.dateFormatYMDHMSS.format(new Date());
							fileDir = generateFileDir(Constant.getDefaultMessageFileFolder(), timeField, rptid);
						} catch (Exception e) {
							logger.error("生成新流水号失败, JRNGenerator未生成新的流水号");
							e.printStackTrace();
						}
						creditrpt.setFntjrn(fntjrn);
					}else{
						reqAddr = creditrpt.getReqadrr();
						//判断是否为流转报告
						if(StringUtils.isNotBlank(creditrpt.getRedorptid())){
							logger.debug("根据流转报告ID查找原报告对象 ID:" + creditrpt.getRedorptid());
							String exMessage = null;
							String reqMessage = null;
							WfDatCreditrpt redorpt = creditrptService.findRedorptByRptId(creditrpt.getRedorptid());
							if(null != redorpt){
								String rptaddr = redorpt.getRtpadrr();
								if(StringUtils.isNotBlank(rptaddr)){
									logger.debug("读取流转报告的地址:" + rptaddr);
									File redoFile = new File(rptaddr);
									exMessage = FileUtils.readFileToString(redoFile, Constant.getDefaultEncoding());
								}
								if(StringUtils.isNotBlank(reqAddr)){
									logger.debug("读取请求报告的地址:" + reqAddr);
									fileDir =reqAddr.substring(0,reqAddr.lastIndexOf(File.separator));
									reqMessage = FileUtils.readFileToString(new File(reqAddr), Constant.getDefaultEncoding());
								}else{
									logger.error("请求地址为空,或者路径有误");
								}
								//拼接报文
								message = this.messageConcact(exMessage, message, reqMessage);
							}else{
								logger.error("未找到流转报告");
							}
							logger.debug("生成流转报告报文: " + message);
						}else{
							//截取请求报文地址,得到响应报文的路径
							if(StringUtils.isNotBlank(reqAddr) && -1 < reqAddr.lastIndexOf(File.separator)){
								fileDir = reqAddr.substring(0,reqAddr.lastIndexOf(File.separator));
							}else{
								logger.error("请求地址为空,或者路径有误");
							}
						}
						
						logger.debug("生成文件路径:" + fileDir + fileName);
						// 响应报文写入文件
						fileName = rptid +"-"+ httpType;
						filePath = MessageStorageUtil.writeMessageToFile(fileDir,  fileName, message);
						
						
					}
					creditrpt.setRptmoddte(DateUtil.getCurrentDate8Len());
					creditrpt.setRptmodtim(DateUtil.getCurrentTimeHMSS());
					creditrpt.setRtpadrr(filePath);// 将地址写回库表
					creditrpt.setRptsta(Constant.RptStatus.STATIS_QUERYSUCCESS.getCode());
					creditrptService.saveOrUpdateWfDatCreditrptByfntjrn(creditrpt);
					rptheader.setStatus(Constant.ResponseStatus.RESPONSE_STATUS_SUCCESS.getValue());
				}else{
					logger.error("接收报文格式有误");
				}
			} else {
				logger.error("No message received...");
				
				rptheader.setResult("收到报文消息为空");
				rptheader.setStatus(Constant.ResponseStatus.RESPONSE_STATUS_FAILURE.getValue());
			}
		}catch (Exception e){
			e.printStackTrace();
			logger.error("平台接收报文出错!");
		}finally {
			// 生成返回报文体的对象
			GeneralRptResMsgBody body = new GeneralRptResMsgBody();
			GeneralRptResMsg resMsg = new GeneralRptResMsg(rptheader, body);
			res = JSONObject.fromObject(resMsg).toString();
			logger.debug("平台返回核心系统的响应报文" + res);
			PrintWriter pw = response.getWriter();
			// 发送返回响应报文
			pw.write(res);
			pw.flush();
			pw.close();
		}
		
	}
	
	

	/**
	 * 生成路径
	 * @param timeField
	 * @param httpType
	 * @param rptid
	 * @return
	 * @throws ParseException 
	 */
	private String generateFileDir(String contextPath, String timeField, String rptid){
		Date date;
		try {
			date = DateUtils.parseDate(timeField, "yyyyMMddHHmmssSSS");
			StringBuffer sb = new StringBuffer();
			sb.append(contextPath);
			sb.append(File.separator);
			sb.append(DateUtil.fileDateFormat.format(date));
			sb.append(File.separator);
			sb.append(rptid);
			sb.append(File.separator);
			return sb.toString();
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("日期解析错误: 无法将日期解析为yyyyMMddHHmmssSSS格式");
			return null;
		}
		
	}
	
	/**
	 * 报文拼接,适用于接收报告流转返回的报文
	 * @param exMessage
	 * @param currMessage
	 * @param reqMessage
	 * @return
	 */
	private String messageConcact(String exMessage, String currMessage, String reqMessage){
		
		try{
			Object body = JSONObject.fromObject(reqMessage).get("body");
			
			String trncods = (String) JSONObject.fromObject(body).get("trncods");
			
			JSONObject currJsonObj = JSONObject.fromObject(currMessage);
			
			JSONObject exarr = (JSONObject) JSONObject.fromObject(exMessage).get("body");
			//2016-02-17去除一层循环遍历
//			JSONArray exarr = (JSONArray) JSONObject.fromObject(exMessage).get("body");
//			JSONObject exjsonobj = exarr.getJSONObject(0);
			//剔除不属于产品的原交易
			Map<String, Object> map = new HashMap<String, Object>();
			if(StringUtils.isNotBlank(trncods)){
				if(exarr.size()>0){
					Iterator entries = exarr.entrySet().iterator();  
					while (entries.hasNext()) {  
					    Map.Entry entry = (Map.Entry) entries.next(); 
					    String key = (String)entry.getKey();
					    if(trncods.indexOf(key)!= -1){
					    	map.put(key, entry.getValue());
					    }
					}
				}
			}
			
			JSONObject currbody =  (JSONObject) JSONObject.fromObject(currMessage).get("body");
			//2016-02-17去除一层循环遍历
//			JSONObject currbodyContent = (JSONObject) currbody.get(0);
//			currbodyContent.accumulateAll(map);
//			currbody.element(0, currbodyContent);
			currbody.accumulateAll(map);
			currJsonObj.element("body", currbody);
			return currJsonObj.toString();
		}catch (Exception e){
			logger.error("报文拼接异常!");
			e.printStackTrace();
			return null;
		}
		
		
	}
public static void main(String[] args) {
	String a = "asdd1ads1a";
	System.out.println(a.lastIndexOf("1"));
	System.out.println(a.substring(0,a.lastIndexOf("1")));
}
	
}
