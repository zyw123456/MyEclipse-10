package com.sinoway.wrn.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.sinoway.common.entity.HttpCommonEntity;
import com.sinoway.common.entity.HttpResponseCommonEntity;
import com.sinoway.common.service.IHttpProviderService;
import com.sinoway.common.util.Constant;
import com.sinoway.common.util.HttpParamUtil;
import com.sinoway.common.util.JsonBinder;
import com.sinoway.common.util.Constant.IsBatch;
import com.sinoway.common.util.Constant.MsgType;
import com.sinoway.wrn.entity.WfDatCerditPrsn;
import com.sinoway.wrn.entity.WfDatCerditWarnWaiting;
import com.sinoway.wrn.service.IWaitingMonitorService;
import com.yzj.wf.base.action.BaseAction;
import com.yzj.wf.plat.entity.WfCfgdefPrdinfo;

@SuppressWarnings("serial")
public class WaitingMonitorAction extends BaseAction {
	private IWaitingMonitorService waitingMonitorService;
	private IHttpProviderService httpProviderService;
	private String order;
	private String loantyp;
	private String frontObjStr;
	

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getLoantyp() {
		return loantyp;
	}

	public void setLoantyp(String loantyp) {
		this.loantyp = loantyp;
	}

	public String getFrontObjStr() {
		return frontObjStr;
	}

	public void setFrontObjStr(String frontObjStr) {
		this.frontObjStr = frontObjStr;
	}

	public IHttpProviderService getHttpProviderService() {
		return httpProviderService;
	}

	public void setHttpProviderService(IHttpProviderService httpProviderService) {
		this.httpProviderService = httpProviderService;
	}

	public IWaitingMonitorService getWaitingMonitorService() {
		return waitingMonitorService;
	}

	public void setWaitingMonitorService(
			IWaitingMonitorService waitingMonitorService) {
		this.waitingMonitorService = waitingMonitorService;
	}
	/**
	 * 待监控人员名单查询
	 */
	@SuppressWarnings("static-access")
	public String queryWarnWaitingPeople(){
		List<WfDatCerditWarnWaiting> waitinglist = waitingMonitorService.queryWarnWaitingPeople(order, loantyp,this.getCurrentPeople());
		frontObjStr = JsonBinder.toJson(waitinglist);
		try {
    		HttpServletResponse response = ServletActionContext.getResponse();
    		response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println(frontObjStr);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
  		return this.SUCCESS;
	}
	/**
	 * 待监控人员补充信息
	 */
	@SuppressWarnings("static-access")
	public String suppleWarnWaitingPeople(){
		WfDatCerditPrsn warn = JsonBinder.fromJson(frontObjStr, WfDatCerditPrsn.class);
		WfDatCerditPrsn oldWarn = waitingMonitorService.getWfDatCerditwarnById(warn.getId());
		JSONObject object = this.sendMessage(this.addWarnMessage(warn), oldWarn.getPrdcod());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter out = response.getWriter();
			if(object !=null){
				JSONObject body = object.getJSONObject("body");
				if(Constant.ResponseStatus.RESPONSE_STATUS_SUCCESS.getValue().equals(body.get("status"))){
					waitingMonitorService.suppleWarnWaitingPeople(warn);
					List<WfDatCerditWarnWaiting> waitinglist = waitingMonitorService.queryWarnWaitingPeople(order, loantyp,this.getCurrentPeople());
					frontObjStr = JsonBinder.toJson(waitinglist);
					out.println(frontObjStr);
				}else{
					out.println(0);
				}
			}else{
				out.println(0);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
  		return this.SUCCESS;
	}
	/**
	 * 开启监控
	 */
	@SuppressWarnings("static-access")
	public String startMonitor(){
		WfDatCerditPrsn warn = JsonBinder.fromJson(frontObjStr, WfDatCerditPrsn.class);
		WfDatCerditPrsn oldWarn = waitingMonitorService.getWfDatCerditwarnById(warn.getId());
		JSONObject object =this.sendMessage(this.addWarnMessage(warn), oldWarn.getPrdcod());
		try {
    		HttpServletResponse response = ServletActionContext.getResponse();
    		response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			if(object !=null){
				JSONObject body = object.getJSONObject("body");
				if(Constant.ResponseStatus.RESPONSE_STATUS_SUCCESS.getValue().equals(body.get("status"))){
					waitingMonitorService.startMonitor(warn.getId());
					List<WfDatCerditWarnWaiting> waitinglist = waitingMonitorService.queryWarnWaitingPeople(order, loantyp,this.getCurrentPeople());
					frontObjStr = JsonBinder.toJson(waitinglist);
					System.out.println(frontObjStr);
					out.println(frontObjStr);
				}else{
					out.println(0);
				}
			}else{
				out.println(0);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
  		return this.SUCCESS;
	}
	/**
	 * 查询预警报告查询人员的预警产品
	 */
	@SuppressWarnings("static-access")
	public String queryWarnPrds(){
		WfDatCerditPrsn prsn = JsonBinder.fromJson(frontObjStr, WfDatCerditPrsn.class);
		List<WfCfgdefPrdinfo> prdlist = waitingMonitorService.queryWarnPrds(prsn);
		frontObjStr = JsonBinder.toJson(prdlist);
		try {
    		HttpServletResponse response = ServletActionContext.getResponse();
    		response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println(frontObjStr);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
  		return this.SUCCESS;
	}
	/**
	 * 更换预警报告查询人员的天警云模板
	 */
	@SuppressWarnings("static-access")
	public String changeWarnPrds(){
		WfDatCerditPrsn warn = JsonBinder.fromJson(frontObjStr, WfDatCerditPrsn.class);
		WfDatCerditPrsn oldWarn = waitingMonitorService.getWfDatCerditwarnById(warn.getId());
		JSONObject object = this.sendMessage(this.addWarnMessage(warn), oldWarn.getPrdcod());
		try {
    		HttpServletResponse response = ServletActionContext.getResponse();
    		response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			if(object !=null){
				JSONObject body = object.getJSONObject("body");
				if(Constant.ResponseStatus.RESPONSE_STATUS_SUCCESS.getValue().equals(body.get("status"))){
					waitingMonitorService.changeWarnPrds(warn);
					out.println("修改成功!");
				}else{
					out.println(0);
				}
			}else{
				out.println(0);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.SUCCESS;
	}
	/**
	 * 新增监控名单请求报文拼接
	 */
	public String addWarnMessage(WfDatCerditPrsn wdc){
		JSONObject obj = new JSONObject();
		JSONObject header = new JSONObject();
		JSONObject body=new JSONObject();
		JSONArray list = new JSONArray();
		header.put("clntjrn", "");
		header.put("clnttrndte", "");
		header.put("clnttrntime","");
		header.put("fnttrnjrn","");
		header.put("frnttrndte","");
		header.put("frnttrntim","");
		WfDatCerditPrsn oldWarn = waitingMonitorService.getWfDatCerditwarnById(wdc.getId());
		header.put("corgno",oldWarn.getCorgno());
		if(oldWarn.getUsrid() == null){
			header.put("usrid","");
		}else{
			header.put("usrid",oldWarn.getUsrid());
		}
		header.put("p_usrid",oldWarn.getP_usrid());
		JSONObject prsn = new JSONObject();
		prsn.put("prdcod", oldWarn.getPrdcod());
		prsn.put("pname", wdc.getPrsnnam());
		prsn.put("idcard", wdc.getPrsncod());
		prsn.put("tel", wdc.getTelno());
		prsn.put("loantyp", wdc.getLoantyp());
		prsn.put("loanamt", wdc.getLoanamt());
		prsn.put("loanperiod", "");
		prsn.put("mkloadtime", "");
		prsn.put("expire", "");
		prsn.put("repaytype", wdc.getRepaytyp());
		prsn.put("repaydate", wdc.getRepaydte());
		prsn.put("repaynum", wdc.getRepayamt());
		list.add(prsn);
		body.put("list", list);
		obj.put("header", header);
		obj.put("body", body);
		return obj.toString();
	}
	/**
	 * 开始监控请求报文拼接
	 */
	public String startWarn(WfDatCerditPrsn wdc){
		JSONObject obj = new JSONObject();
		JSONObject header = new JSONObject();
		JSONObject body=new JSONObject();
		header.put("clntjrn", "");
		header.put("clnttrndte", "");
		header.put("clnttrntime","");
		header.put("fnttrnjrn","");
		header.put("frnttrndte","");
		header.put("frnttrntim","");
		WfDatCerditPrsn oldWarn = waitingMonitorService.getWfDatCerditwarnById(wdc.getId());
		header.put("corgno",oldWarn.getCorgno());
		if(oldWarn.getUsrid() == null){
			header.put("usrid","");
		}else{
			header.put("usrid",oldWarn.getUsrid());
		}
		header.put("p_usrid",oldWarn.getP_usrid());
		body.put("pname",  oldWarn.getPrsnnam());
		body.put("idcard",  oldWarn.getPrsncod());
		body.put("prdcod", oldWarn.getPrdcod());
		body.put("optype", "1");
		obj.put("header", header);
		obj.put("body", body);
		return obj.toString();
	}
	/**
	 * 修改监控信息请求报文拼接
	 */
	public String updateWarn(WfDatCerditPrsn wdc){
		JSONObject obj = new JSONObject();
		JSONObject header = new JSONObject();
		JSONObject body=new JSONObject();
		header.put("clntjrn", "");
		header.put("clnttrndte", "");
		header.put("clnttrntime","");
		header.put("fnttrnjrn","");
		header.put("frnttrndte","");
		header.put("frnttrntim","");
		WfDatCerditPrsn oldWarn = waitingMonitorService.getWfDatCerditwarnById(wdc.getId());
		header.put("corgno",oldWarn.getCorgno());
		if(oldWarn.getUsrid() == null){
			header.put("usrid","");
		}else{
			header.put("usrid",oldWarn.getUsrid());
		}
		header.put("p_usrid",oldWarn.getP_usrid());
		body.put("pname",  oldWarn.getPrsnnam());
		body.put("idcard",  oldWarn.getPrsncod());
		body.put("prdcod", oldWarn.getPrdcod());
		body.put("newPrd", wdc.getPrdcod());
		obj.put("header", header);
		obj.put("body", body);
		return obj.toString();
	}
	/**
  	 *向核心系统发送报文并接受响应报文
  	 */
  	public JSONObject sendMessage(String jsonMessage,String prdcod){
  		HttpCommonEntity entity = new HttpCommonEntity(Constant.getConfigUrl());
	     try {
	    	 entity = HttpParamUtil.generateRptRequest(prdcod,null,IsBatch.IS_NO.getValue(),MsgType.MESSAGE_TYPE_REQUEST.getCode(), jsonMessage);
	    	 entity = this.httpProviderService.httpCommunicate(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    HttpResponseCommonEntity resEntity =  entity.getResponse();
		String message = (String) resEntity.getReturnObj();
		 System.out.println(message);
		 if("".equals(message) || null == message){
			 return null;
		 }
		 JSONObject object  = JSONObject.fromObject(message);
		 return object;
  	}
}
