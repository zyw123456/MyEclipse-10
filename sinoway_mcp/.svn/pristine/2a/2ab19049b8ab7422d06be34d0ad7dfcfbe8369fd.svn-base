package com.sinoway.common.service.server.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.web.context.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sinoway.common.constant.MsgTransfConstant.MsgTemplateType;
import com.sinoway.common.constant.ServerConstant.ResSta;
import com.sinoway.common.constant.ServerConstant.TradePoolType;
import com.sinoway.common.constant.SystemConstant.BusProcSta;
import com.sinoway.common.entity.CoreMsgHeader;
import com.sinoway.common.entity.DesEntity;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.entity.GeneralMsgHeader;
import com.sinoway.common.exception.MsgHeaderParseException;
import com.sinoway.common.exception.TradeAuthException;
import com.sinoway.common.exception.TradeProcException;
import com.sinoway.common.frame.SpringContextUtil;
import com.sinoway.common.pool.McpThreadPool;
import com.sinoway.common.pool.ThreadPoolFactory;
import com.sinoway.common.pool.task.GeneralHttpOPtradeTaskService;
import com.sinoway.common.pool.task.GeneralSocketOPtradeTaskService;
import com.sinoway.common.service.parse.GeneralMsgHeaderService;
import com.sinoway.common.service.processer.GeneralProcesserService;
import com.sinoway.common.service.server.authen.AuthenTemplateFactory;
import com.sinoway.common.service.template.GeneralTemplateService;
import com.sinoway.common.util.ByteUtil;
import com.sinoway.common.util.DateUtil;
import com.sinoway.common.util.GUIDGenerator;
import com.sinoway.common.util.McpLogger;
import com.sinoway.common.util.StringUtil;
import com.sinoway.common.util.SystemCfgUtil;
import com.sinoway.common.util.SystemOperateUtil;
import com.yzj.wf.com.ibank.common.server.ThreadPoolException;

/**
 * http服务
 * @author jintao
 *
 */
public class GeneralHttpControl extends ActionSupport  implements ServletRequestAware, ServletResponseAware, ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private ServletContext servletContext;
	private HttpServletResponse response;
    private Map<String,String> poolIdMap = null;
    private int coreTNum = 20;
    private int maxTNum = 20;
    private int cacheNum = 20;
    private long keepAlive = 60*1000;
	
	public void execute_work() throws ThreadPoolException{
		McpThreadPool pool = ThreadPoolFactory.getPool(poolIdMap.get(TradePoolType.otrade_proc_pool.getValue()));
		if(pool == null){
			pool = ThreadPoolFactory.newPool(TradePoolType.otrade_proc_pool.getValue(), coreTNum, maxTNum, cacheNum, keepAlive);
		};
		Map map = new HashMap();
		map.put("request", request);
		map.put("response", response);
		GeneralHttpOPtradeTaskService<String,Map> task = (GeneralHttpOPtradeTaskService<String,Map>)SpringContextUtil.getBean(GeneralHttpOPtradeTaskService.class);
		task.init(GUIDGenerator.generateId(), pool, map);
		Future<String> f =  null;
		try{
			f = pool.submit(task);
//			Thread.sleep(3*1000);
			String result = f.get(60 * 1000, TimeUnit.MILLISECONDS);
		}catch(Exception e){
			// 取消任务
			if(f != null)
				f.cancel(true);
			
			e.printStackTrace();
		}
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	@Override
	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;
		
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response=arg0;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
		
	}
	public Map<String, String> getPoolIdMap() {
		return poolIdMap;
	}

	public void setPoolIdMap(Map<String, String> poolIdMap) {
		this.poolIdMap = poolIdMap;
	}

}
