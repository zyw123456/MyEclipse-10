package com.sinoway.rpt.action;



import java.util.List;

import net.sf.json.JSONObject;

import com.sinoway.common.util.JsonBinder;
import com.sinoway.common.util.datatables.entity.TableReqParams;
import com.sinoway.rpt.entity.TransString;
import com.sinoway.rpt.entity.WfDatCreditrptUtil;
import com.sinoway.rpt.service.IWfDatCreditrptService;
import com.yzj.wf.base.action.BaseAction;
import com.yzj.wf.common.WFException;

@SuppressWarnings("serial")
public class WfDatCreditrptAction extends BaseAction{
	private IWfDatCreditrptService wfatCreditrptService;
	private String frontObjStr;
	private String reqParams; // datatable请求参数
	private String resData;   //datatable 相应参数
	
	/**
	 * @Description 模糊查询加分页
	 * @return
	 */
	public String query(){
		TableReqParams params = tranStrToObj(reqParams);
		List list = wfatCreditrptService.query(params,this.getCurrentPeople());
		int count  = wfatCreditrptService.count(params,this.getCurrentPeople());
		JSONObject  json = new JSONObject ();
		json.put("draw",params.getDraw());
		json.put("recordsTotal", count);
		json.put("recordsFiltered", count);
		json.put("data", list);
		resData = JsonBinder.toJson(json);
		return SUCCESS;
	}
	/**
	 * 批量删除
	 * @return
	 */
	public String delete(){
		WfDatCreditrptUtil trnobj = transStrToObj();
		try {
			String[] idArray = trnobj.getIds().split(",");
			wfatCreditrptService.delete(idArray);
		} catch (WFException e) {
			e.printStackTrace();
		}
		return this.query();
	}
	
	private TableReqParams tranStrToObj(String reqParams){
		return JsonBinder.fromJson(reqParams, TableReqParams.class);
	}
	
	public void setWfatCreditrptService(IWfDatCreditrptService wfatCreditrptService) {
		this.wfatCreditrptService = wfatCreditrptService;
	}
	
	private WfDatCreditrptUtil transStrToObj(){
		return JsonBinder.fromJson(frontObjStr, WfDatCreditrptUtil.class);
	}
	private void tranObjToStr(WfDatCreditrptUtil trnObj){
		frontObjStr=JsonBinder.toJson(trnObj);
	}
	public String getFrontObjStr() {
		return frontObjStr;
	}

	public void setFrontObjStr(String frontObjStr) {
		this.frontObjStr = frontObjStr;
	}

	public void setReqParams(String reqParams) {
		this.reqParams = reqParams;
	}
	public String getResData() {
		return resData;
	}
	
	

}