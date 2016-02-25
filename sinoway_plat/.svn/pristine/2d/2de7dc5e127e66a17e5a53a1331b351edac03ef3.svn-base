package com.sinoway.test.action;

import java.util.List;

import net.sf.json.JSONObject;

import com.sinoway.common.util.JsonBinder;
import com.sinoway.rpt.entity.WfDatCreditrptUtil;
import com.sinoway.test.service.ITestDemoService;
import com.yzj.wf.base.action.BaseAction;
import com.yzj.wf.common.WFLogger;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;
/**
 * 测试demo
 * @author zhangyanwei
 *
 */
public class TestAction extends BaseAction{
	
	private static final long serialVersionUID = 481077790366808497L;
	WFLogger logger = WFLogger.getLogger(TestAction.class);
	private ITestDemoService testDemoService;
	private String wfDate;
	
	public String findCreditrptList(){
		System.out.println("======");
		try{
			WfDatCreditrptUtil trnObj = new WfDatCreditrptUtil();
			List<WfDatCreditrptUtil> list =	testDemoService.findCreditrptList();
			trnObj.setTrns(list);
			tranObjToStr(trnObj);
			logger.info("++++++++++查询报告列表"+wfDate);
		}catch(Exception e){
			logger.error("查询报告列表失败");
			e.printStackTrace();
			
		}
		
		
		return SUCCESS;
	}
	private void tranObjToStr(WfDatCreditrptUtil trnObj){
		wfDate=JsonBinder.toJson(trnObj);
	}
	public ITestDemoService getITestDemoService() {
		return testDemoService;
	}
	public void setTestDemoService(ITestDemoService testDemoService) {
		this.testDemoService = testDemoService;
	}
	public String getWfDate() {
		return wfDate;
	}
	public void setWfDate(String wfDate) {
		this.wfDate = wfDate;
	}
	

}
