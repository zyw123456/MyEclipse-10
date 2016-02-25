package com.sinoway.wrn.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.sinoway.common.util.JsonBinder;
import com.sinoway.wrn.service.IWfDatCerditWarndtelService;
import com.yzj.wf.base.action.BaseAction;
/**
 * 天警云信息处理Action
 * @author Administrator
 *
 */
public class WfDatCerditWarndtelAction extends BaseAction{
	private IWfDatCerditWarndtelService wfDatCerditWarndtelService;
	private String controlTime;//监控时间
	private String warnDataType;//警告数据类型
	private String dataUrl;//数据来源
	private String dataRealty;//数据可信度的
	private List list;
	private String dataStr;
	private int pageSize = 30;//当前页显示条数
	private Map<String,String> queryMap  = new HashMap<String, String>();
	/**
	 * 多条件查询征信预警产品明细（天警云首页显示）
	 * @return list
	 */
	public String findAllWfDatCerditWarndtel(){
		queryMap.put("controlTime", controlTime);
		queryMap.put("warnDataType", warnDataType);
		queryMap.put("dataUrl", dataUrl);
		queryMap.put("dataRealty", dataRealty);
		queryMap.put("pageSize",String.valueOf(pageSize));
		//String d = String.valueOf(pageSize);
		list = wfDatCerditWarndtelService.findAllWfDatCerditWarndtel(queryMap);
		dataStr = JsonBinder.toJson(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter out = response.getWriter();
			out.println(dataStr);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setWfDatCerditWarndtelService(
			IWfDatCerditWarndtelService wfDatCerditWarndtelService) {
		this.wfDatCerditWarndtelService = wfDatCerditWarndtelService;
	}

	public String getControlTime() {
		return controlTime;
	}

	public void setControlTime(String controlTime) {
		this.controlTime = controlTime;
	}

	public String getWarnDataType() {
		return warnDataType;
	}
	public void setWarnDataType(String warnDataType) {
		this.warnDataType = warnDataType;
	}
	public String getDataUrl() {
		return dataUrl;
	}
	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}
	public String getDataRealty() {
		return dataRealty;
	}
	public void setDataRealty(String dataRealty) {
		this.dataRealty = dataRealty;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}

	public String getDataStr() {
		return dataStr;
	}

	public void setDataStr(String dataStr) {
		this.dataStr = dataStr;
	}
	
}
