package com.sinoway.wrn.action;

import java.util.List;
import net.sf.json.JSONObject;
import com.sinoway.common.entity.HttpCommonEntity;
import com.sinoway.common.entity.HttpResponseCommonEntity;
import com.sinoway.common.service.IHttpProviderService;
import com.sinoway.common.util.Constant;
import com.sinoway.common.util.Constant.IsBatch;
import com.sinoway.common.util.Constant.MsgType;
import com.sinoway.common.util.HttpParamUtil;
import com.sinoway.wrn.entity.WfDatCerditPrsn;
import com.sinoway.wrn.service.IWarnMonitorService;
import com.yzj.wf.base.action.BaseAction;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;
/** 
 * @ClassName: WarnMonitorAction 
 * @Description: (天警云监控人员) 
 * @author 于辉
 * @date 2015-12-29
 *  
 */
public class WarnMonitorAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private IWarnMonitorService warnMService;
	private IHttpProviderService httpProviderService;
	private String id;
	private String prdcod;
	private String prdnam;
	private String prsnnam;
	private String prsncod;
	private String oldprdcod;
	private String loansrtdte;
	private String loanenddte;
	private String trncod;
	private List list;
	private String retMsg;
	private String loantyp;
	
	/** 
	* @Title: findMonitorNameList 
	* @Description:(查询监控人员名单) 
	* @return JSON     
	* @throws 于辉
	*/
	public String findMonitorNameList(){
		XPeopleInfo cp= this.getCurrentPeople();
		WfDatCerditPrsn wdcp = new WfDatCerditPrsn();
		wdcp.setPrsnnam(prsnnam);		
		wdcp.setPrsncod(prsncod);
		wdcp.setLoanenddte(loanenddte);
		wdcp.setLoansrtdte(loansrtdte);
		wdcp.setLoantyp(loantyp);
		List monitorList = warnMService.findMonitorNameList(wdcp,cp,trncod);
		setList(monitorList);
		return SUCCESS;
	}
	/** 
	* @Title: findWCPrdtrnList 
	* @Description:(查询监控模块原交易) 
	* @return JSON     
	* @throws 于辉
	*/
	public String findWCPrdtrnList(){
		List wCPrdtrnList = warnMService.findWCPrdtrnList();
		setList(wCPrdtrnList);
		return SUCCESS;
	}
	/** 
	* @Title: updateMonitorName 
	* @Description:(修改监控人员 ) 
	* @return JSON    
	* @throws 于辉
	*/
	public String updateMonitorName() throws Exception{
		XPeopleInfo cp= this.getCurrentPeople();
		WfDatCerditPrsn wdcp = new WfDatCerditPrsn();
		String parntCode = cp.getParntCode()==null?"":cp.getParntCode();
		wdcp.setId(id);
		wdcp.setPrsncod(prdcod);
		wdcp.setPrsnnam(prdnam);
		wdcp.setSta(Constant.WrnMonitorStatus.WRNSTATUS_SUSPENDMONITOR.getValue());
		
		wdcp.setPeoplecode(cp.getPeopleCode());//账号
		wdcp.setOrgno(cp.getOrgNo());	       //机构号
		wdcp.setParntcode(parntCode);		   //父账号
		wdcp.setUsrid(cp.getUsrId());		   //预警查询核心人员编码
		wdcp.setP_usrid(cp.getP_usrId());	   //预警查询核心父人员编码
		wdcp.setCorgno(cp.getCorgNo());		   //预警查询核心机构号
		wdcp.setCporgno(cp.getCporgNo());      //预警查询核心父机构号
	
		JSONObject json = new JSONObject();
		//header拼接		
		JSONObject jheader = new JSONObject();
		jheader.put("clntjrn", "");				  	//云平台报告编号
		jheader.put("clnttrndte", "");	//云平台处理日期
		jheader.put("clnttrntime", "");	//云平台处理时间
		jheader.put("fnttrnjrn",  "");	//前置流水
		jheader.put("frnttrndte", "");	//云平台处理时间
		jheader.put("frnttrntim", "");	//云平台处理时间
		jheader.put("orgno", cp.getCorgNo());		//核心机构号 
		jheader.put("p_usrid", parntCode);			//父用户 
		jheader.put("usrid", cp.getUsrId());		//核心用户编码 
		//body拼接
		JSONObject jbody = new JSONObject();
		jbody.put("pname", prdnam);					//姓名
		jbody.put("idcardno:", prsncod);			//身份证号
		jbody.put("prdcod", oldprdcod);				//原产品号
		jbody.put("newprd", prdcod);				//新产品号
		json.put("header", jheader);
		json.put("body", jbody);
		//调用核心接口
		HttpCommonEntity entity = HttpParamUtil.generateRptRequest(oldprdcod,null,IsBatch.IS_NO.getValue(),MsgType.MESSAGE_TYPE_REQUEST.getCode(), json.toString());
	    entity = httpProviderService.httpCommunicate(entity);		
	    HttpResponseCommonEntity resEntity =  entity.getResponse();
		String message = (String) resEntity.getReturnObj();
		JSONObject jo =JSONObject.fromObject(message);
		JSONObject body = JSONObject.fromObject(jo.get("body"));
		if(body.getString("status").equals("1")){
			String upflag = warnMService.updateMonitorName(wdcp);
			if(upflag.equals("1")){
				String addflag = warnMService.addMonitorName(wdcp);
				if(addflag.equals("1")){
					this.setRetMsg("1");
				}else{
					this.setRetMsg("调用核心接口成功，增加人员信息失败！");
				}
			}else{
				this.setRetMsg("调用核心接口成功，修改人员信息失败！");
			}		
		}else{
			this.setRetMsg("调用核心接口失败！");
		}
		return SUCCESS;
	}
	/** 
	* @Title: addMonitorName 
	* @Description:(终止监控人员 ) 
	* @return JSON
	* @throws 于辉
	*/
	public String stopMonitorName() throws Exception{
		XPeopleInfo cp= this.getCurrentPeople();
		WfDatCerditPrsn wdcp = new WfDatCerditPrsn();
		wdcp.setId(id);
		wdcp.setSta(Constant.WrnMonitorStatus.WRNSTATUS_STOPMONITOR.getValue());
		String pcode = cp.getParntCode()==null?"":cp.getParntCode();
		JSONObject json = new JSONObject();
		//header拼接		
		JSONObject jheader = new JSONObject();
		jheader.put("clntjrn", "");				  	//云平台报告编号
		jheader.put("clnttrndte", "");				//云平台处理日期
		jheader.put("clnttrntime", "");				//云平台处理时间
		jheader.put("fnttrnjrn",  "");				//前置流水
		jheader.put("frnttrndte", "");				//云平台处理时间
		jheader.put("frnttrntim", "");				//云平台处理时间
		jheader.put("orgno", cp.getCorgNo());		//核心机构号 
		jheader.put("p_usrid", pcode);				//父用户 
		jheader.put("usrid", cp.getUsrId());		//核心用户编码 
		//body拼接
		JSONObject jbody = new JSONObject();
		jbody.put("pname", prdnam);									//姓名
		jbody.put("idcardno:", prsncod);							//身份证号
		jbody.put("prdcod", oldprdcod);								//原产品号
		jbody.put("optype", Constant.IsStopIt.STOPIT.getValue());	//是否终止
		json.put("header", jheader);
		json.put("body", jbody);
		//调用核心接口
		HttpCommonEntity entity = HttpParamUtil.generateRptRequest(this.getOldprdcod(),null,IsBatch.IS_NO.getValue(),MsgType.MESSAGE_TYPE_REQUEST.getCode(), json.toString());
	    entity = this.httpProviderService.httpCommunicate(entity);		
	    HttpResponseCommonEntity resEntity =  entity.getResponse();
		String message = (String) resEntity.getReturnObj();
		JSONObject jo =JSONObject.fromObject(message);
		JSONObject body = JSONObject.fromObject(jo.get("body"));
		if(body.getString("status").equals("1")){
			String flag = warnMService.updateMonitorName(wdcp);
			if(flag.equals("1")){
				this.setRetMsg("1");
			}else{
				this.setRetMsg("调用核心接口成功，终止失败！");
			}			
		}else{
			this.setRetMsg("调用核心接口失败！");
		}
		return SUCCESS;
	}
	
	public String getLoansrtdte() {
		return loansrtdte;
	}
	public void setLoansrtdte(String loansrtdte) {
		this.loansrtdte = loansrtdte;
	}
	public String getLoanenddte() {
		return loanenddte;
	}
	public void setLoanenddte(String loanenddte) {
		this.loanenddte = loanenddte;
	}
	public String getOldprdcod() {
		return oldprdcod;
	}
	public void setOldprdcod(String oldprdcod) {
		this.oldprdcod = oldprdcod;
	}
	public String getPrsncod() {
		return prsncod;
	}
	public void setPrsncod(String prsncod) {
		this.prsncod = prsncod;
	}
	public String getPrsnnam() {
		return prsnnam;
	}
	public void setPrsnnam(String prsnnam) {
		this.prsnnam = prsnnam;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPrdcod() {
		return prdcod;
	}
	public void setPrdcod(String prdcod) {
		this.prdcod = prdcod;
	}
	public String getPrdnam() {
		return prdnam;
	}
	public void setPrdnam(String prdnam) {
		this.prdnam = prdnam;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public String getTrncod() {
		return trncod;
	}

	public void setTrncod(String trncod) {
		this.trncod = trncod;
	}
	public void setHttpProviderService(IHttpProviderService httpProviderService) {
		this.httpProviderService = httpProviderService;
	}
	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	public void setWarnMService(IWarnMonitorService warnMService) {
		this.warnMService = warnMService;
	}
	public String getLoantyp() {
		return loantyp;
	}
	public void setLoantyp(String loantyp) {
		this.loantyp = loantyp;
	}
}

