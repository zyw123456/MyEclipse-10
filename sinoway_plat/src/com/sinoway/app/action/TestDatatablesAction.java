package com.sinoway.app.action;

import java.util.ArrayList;
import java.util.List;

import com.sinoway.common.util.JsonBinder;
import com.sinoway.common.util.datatables.entity.TableReqParams;
import com.sinoway.common.util.datatables.entity.TableResData;
import com.sinoway.common.util.datatables.entity.TableSearchParam;
import com.yzj.wf.base.action.BaseAction;

public class TestDatatablesAction extends BaseAction{
	private static final long serialVersionUID = -7619492280395102064L;
	private String reqParams;//
	private String resData;
	
	public String test(){
		System.out.println("...............");
		TableReqParams params = tranStrToObj(reqParams);
		System.out.println("查询次数"+params.getDraw());
		System.out.println("查询起始数"+params.getStartIndex());
		System.out.println("查询页大小"+params.getPageSize());
		System.out.println("排序字段 "+params.getOrderColumn()+" ;排序顺序 "+params.getOrderDir());
		for(TableSearchParam sp : params.getSearchParams()){
			System.out.println("  查询条件"+sp.getName()+":"+sp.getValue());
		}
		List pageData = queryPageDataByParams(params); //通过前台条件查询数据
		TableResData resdata = new TableResData();
		resdata.setDraw(params.getDraw());
		
		resdata.setPageData(pageData);
		resData = tranObjToStr(resdata);
 		return SUCCESS;
	}
	
	private List queryPageDataByParams(TableReqParams params){
		return  new ArrayList();
	}
	
	private String tranObjToStr(Object resdata){
		return JsonBinder.toJson(resdata);
	}
	
	private TableReqParams tranStrToObj(String reqParams){
		return JsonBinder.fromJson(reqParams, TableReqParams.class);
	}
	
	public String getResData() {
		return resData;
	}
	public void setReqParams(String reqParams) {
		this.reqParams = reqParams;
	}

}
