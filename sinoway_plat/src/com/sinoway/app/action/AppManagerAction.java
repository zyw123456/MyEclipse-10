package com.sinoway.app.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;


import com.sinoway.app.entity.WfcgrefApptrn;
import com.sinoway.app.service.IAppService;
import com.sinoway.common.util.JsonBinder;
import com.yzj.wf.base.action.BaseAction;
import com.sinoway.stm.entity.FrotTrnObjInfo;
import com.sinoway.stm.entity.FrotTrnObjInfo;
import com.yzj.wf.base.action.BaseAction;
import com.sinoway.stm.entity.FrotTrnObjInfo;
import com.yzj.wf.base.action.BaseAction;

/**
 * App应用管理action
 * @author xiehao
 *
 */
public class AppManagerAction extends BaseAction{
	private static final long serialVersionUID = 481077790366808497L;
	private IAppService appService;
	private String frontObjStr;
	private String appcode;//应用编码
	private List list;
	private WfcgrefApptrn wfapp;//征信应用与原交易
	public String findTrns(){
		System.out.println(456);
		FrotTrnObjInfo trnObj = transStrToObj();
		if(trnObj!=null){
			String appcod = trnObj.getAppcod();
			if(null != appcod){
				List<Map<String,String>> trns= (List<Map<String,String>>) appService.getPrdTrns(appcod);
				trnObj.setTrns(trns);
			}
			tranObjToStr(trnObj);
		}
		return SUCCESS;
	}
	/**
	 * 更具应用编码查询原交易编码
	 * @return 
	 * @throws IOException 
	 */
	public String appfindTrncodAction() throws IOException{
		System.out.println(appcode);
		//HttpServletResponse  response = ServletActionContext.getResponse();
		list=appService.findTrncodByAppcod(appcode);
//		Gson gson= new Gson();
//		String json = gson.toJson(list).toString();
//			PrintWriter out = response.getWriter();
//			out.write(json);
//			 out.flush();
//			 out.close();
		//trnObj.setTrns(list);
//		for(int i=0;i<list.size();i++){
//			String str = (String) list.get(i);
//			System.out.println(str);
//		}
//		
		return SUCCESS;
	}
	public String test(){
		System.out.println(123);
		return SUCCESS;
	}
	
	private FrotTrnObjInfo transStrToObj(){
		return JsonBinder.fromJson(frontObjStr, FrotTrnObjInfo.class);
	}
	
	private void tranObjToStr(FrotTrnObjInfo trnObj){
		frontObjStr=JsonBinder.toJson(trnObj);
	}

	public void setFrontObjStr(String frontObjStr) {
		this.frontObjStr = frontObjStr;
	}

	public String getFrontObjStr() {
		return frontObjStr;
	}

	public void setAppService(IAppService appService) {
		this.appService = appService;
	}
	public String getAppcode() {
		return appcode;
	}
	public void setAppcode(String appcode) {
		this.appcode = appcode;
	}
	
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public WfcgrefApptrn getWfapp() {
		return wfapp;
	}
	public void setWfapp(WfcgrefApptrn wfapp) {
		this.wfapp = wfapp;
	}
	
}
