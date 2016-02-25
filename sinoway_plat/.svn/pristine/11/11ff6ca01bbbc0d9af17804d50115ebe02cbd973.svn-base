package com.sinoway.acc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.sinoway.acc.entity.PeopleShowInfo;
import com.sinoway.acc.service.IPoOrganizeinfoService;
import com.sinoway.common.entity.HttpCommonEntity;
import com.sinoway.common.entity.HttpResponseCommonEntity;
import com.sinoway.common.service.IHttpProviderService;
import com.sinoway.common.util.Constant;
import com.sinoway.common.util.Constant.HttpStatus;
import com.sinoway.common.util.GUIDGenerator;
import com.sinoway.common.util.JsonBinder;
import com.yzj.wf.base.action.BaseAction;
import com.yzj.wf.common.WFException;
import com.yzj.wf.core.model.po.OrganizeInfo;
import com.yzj.wf.core.model.po.PeopleInfo;
import com.yzj.wf.core.model.po.RoleGroupPeople;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;
import com.yzj.wf.plat.entity.WfCfgdefPrdinfo;
import com.yzj.wf.plat.entity.WfCfgrefCompusrprd;
import com.yzj.wf.po.util.PasswordEncoder;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class PoOrganizeinfoAction extends BaseAction {
	
	private IPoOrganizeinfoService poOrganizeInfoService;
	private String peoId;
	private String orgId;
	private String frontObjStr;
	private String peopleStr;
	private String orgName;
	private IHttpProviderService httpProviderService;
	private List<OrganizeInfo> orgList;
	private String dataStr;
	private List list;
	private String peoplecode;
	private String newTeam;
	
	
	public String getNewTeam() {
		return newTeam;
	}
	public void setNewTeam(String newTeam) {
		this.newTeam = newTeam;
	}
	public String getDataStr() {
		return dataStr;
	}
	public String getPeoplecode() {
		return peoplecode;
	}
	public void setPeoplecode(String peoplecode) {
		this.peoplecode = peoplecode;
	}
	public void setDataStr(String dataStr) {
		this.dataStr = dataStr;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public IPoOrganizeinfoService getPoOrganizeInfoService() {
		return poOrganizeInfoService;
    }
	public void setPoOrganizeInfoService(
			IPoOrganizeinfoService poOrganizeInfoService) {
		this.poOrganizeInfoService = poOrganizeInfoService;
	}
	public IHttpProviderService getHttpProviderService() {
		return httpProviderService;
	}
	public void setHttpProviderService(IHttpProviderService httpProviderService) {
		this.httpProviderService = httpProviderService;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getPeopleStr() {
		return peopleStr;
	}
	public void setPeopleStr(String peopleStr) {
		this.peopleStr = peopleStr;
	}
	public String getFrontObjStr() {
		return frontObjStr;
	}
	public void setFrontObjStr(String frontObjStr) {
		this.frontObjStr = frontObjStr;
	}
	public String getPeoId() {
		return peoId;
	}
	public void setPeoId(String peoId) {
		this.peoId = peoId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	
	public List<OrganizeInfo> getOrgList() {
		return orgList;
	}
	public void setOrgList(List<OrganizeInfo> orgList) {
		this.orgList = orgList;
	}
	
	/**
	 * 查询登录父账号下的所有子账号
	 * @return
	 */
    @SuppressWarnings({ "static-access" })
	public String queryCurChildren(){
    	List<PeopleShowInfo> peoList = poOrganizeInfoService.queryChildren(this.getCurrentPeopleCode());
    	frontObjStr = JsonBinder.toJson(peoList);
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
	 * 查询登录父账号下的所有团队
	 * @return
	 */
    @SuppressWarnings("static-access")
	public String queryCurOrganize(){
    	if(dataStr !=null && !"".equals(dataStr)){
    		List<OrganizeInfo> orgList =poOrganizeInfoService.fuzzyQueryOrganize(this.getCurrentPeople(), dataStr);
    		frontObjStr = JsonBinder.toJson(orgList);
    	}else{
    		List<OrganizeInfo> orgList = poOrganizeInfoService.queryOrganize(this.getCurrentPeople());
    		frontObjStr = JsonBinder.toJson(orgList);		
    	}
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
   	 * 根据团队id查团队下的人员
   	 * @return
   	 */
  	@SuppressWarnings("static-access")
	public String getPeopleByOrgId(){
  		List<PeopleShowInfo> peoList = poOrganizeInfoService.getPeopleByOrgId(orgId);
  		frontObjStr = JsonBinder.toJson(peoList);
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
	 * 查询登录父账号下的所有产品
	 * @return
	 */
  	@SuppressWarnings("static-access")
	public String queryCurPrds(){
  	    List<WfCfgdefPrdinfo> list = poOrganizeInfoService.queryPrds(this.getCurrentPeopleCode());
  		frontObjStr = JsonBinder.toJson(list);
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
	 * 给子账户赋权限,新增用户
	 * @return
	 */

	@SuppressWarnings("static-access")
	public String setPermission() {
		PrintWriter out  =null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
    		response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			WfCfgrefCompusrprd comprd = JsonBinder.getMapper().readValue(frontObjStr, WfCfgrefCompusrprd.class);
			PeopleInfo p = JsonBinder.getMapper().readValue(peopleStr, PeopleInfo.class);
			String[] prdcods = comprd.getPrdcod().split(",");
			List<WfCfgrefCompusrprd> comprdList = new ArrayList<WfCfgrefCompusrprd>();
			for (int i = 0; i < prdcods.length; i++) {
				WfCfgrefCompusrprd newComprd = new WfCfgrefCompusrprd();//企业用户与产品对照关系
				newComprd.setId(GUIDGenerator.generateId());
				newComprd.setPeoplecode(comprd.getPeoplecode());
				newComprd.setSta(comprd.getSta());
				newComprd.setPrdcod(prdcods[i]);
				newComprd.setIsdisp("1");
				newComprd.setUsrtype(Constant.UsrType.USRTYPE_ENTERPRISE.getValue());
				comprdList.add(newComprd);
			}
			p.setSid(GUIDGenerator.generateId());
			p.setParntCode(this.getCurrentPeopleCode());
			p.setPeopleGender((byte)0);
			p.setPeopleState((short)0);
			p.setDefaultDesktop((byte)0);
			p.setPasswordErrCount(0);
			p.setPeopleType(1);
			p.setPwd(new PasswordEncoder().encodePassword(Constant.getPwdDefault(), p.getSid()));
					   //发送人员请求
			JSONObject peopleObject = this.sendMessage(this.addChildMessage(p, comprdList));
				if(peopleObject !=null){
					if(Constant.ResponseStatus.RESPONSE_STATUS_SUCCESS.getValue().equals(peopleObject.get("states"))){	
						p.setUsrId((String)peopleObject.get("subusrid"));//将核心发过来的用户编号关联进去
						RoleGroupPeople rgp = new RoleGroupPeople();
						rgp.setPeopleInfo(p);
						//rgp.setRoleGroupInfo(poOrganizeInfoService.getRoleGroupById(Constant.getRolegroupOrdinary()));
						poOrganizeInfoService.addNewRoleGroupPeople(Constant.getRolegroupOrdinary(), rgp, comprdList, p);
						out.println(1);
//						poOrganizeInfoService.addRoleGroupPeople(rgp);
//						poOrganizeInfoService.setPermission(comprdList);
//						poOrganizeInfoService.addPeople(p);
					}else{
						out.println(0);
					}
			    }else{
						out.println(0);
			     }
	
		}  catch (WFException e) {
			out.println(0);
			e.printStackTrace();
		} catch (Exception e) {//核心连接失败
			out.println(2);
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
		return this.SUCCESS;
  	}
  	/**
	 * 获得用户所拥有的产品
	 * @return
	 */
  	@SuppressWarnings("static-access")
	public String getPrdsByPeocode(){
  		PeopleInfo people = poOrganizeInfoService.getPeopleById(peoId);
  		List<WfCfgrefCompusrprd> comList = poOrganizeInfoService.getCompusrprd(people.getPeopleCode());
  		frontObjStr = JsonBinder.toJson(comList);
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
  		if("look".equals(dataStr)){
  			return this.INPUT;
  		}else{
  			return this.SUCCESS;
  		}
  	}
  	/**
	 * 预修改账号获得用户所拥有的产品
	 * @return
	 */
  	@SuppressWarnings("static-access")
	public String updatePrdsByPeocode(){
  		PeopleInfo people = poOrganizeInfoService.getPeopleById(peoId);
  		OrganizeInfo organize = poOrganizeInfoService.getOrganize(people.getOrganizeInfo());
  		ServletActionContext.getRequest().setAttribute("organize", organize);
  		ServletActionContext.getRequest().setAttribute("people", people);
  		if("look".equals(dataStr)){
  			return this.INPUT;
  		}else{
  			return this.SUCCESS;
  		}
  	}
  	/**
	 * 修改子账户权限,修改用户
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String updatePermission() {
		PrintWriter out  =null;
		try {
			WfCfgrefCompusrprd comprd = JsonBinder.getMapper().readValue(frontObjStr, WfCfgrefCompusrprd.class);
			PeopleInfo p = JsonBinder.getMapper().readValue(peopleStr, PeopleInfo.class);
			String[] prdcods = comprd.getPrdcod().split(",");
			List<WfCfgrefCompusrprd> comprdList = new ArrayList<WfCfgrefCompusrprd>();
			for (int i = 0; i < prdcods.length; i++) {
				WfCfgrefCompusrprd newComprd = new WfCfgrefCompusrprd();
				newComprd.setId(GUIDGenerator.generateId());
				newComprd.setPeoplecode(comprd.getPeoplecode());
				newComprd.setSta(comprd.getSta());
				newComprd.setPrdcod(prdcods[i]);
				newComprd.setIsdisp("1");
				newComprd.setUsrtype(Constant.UsrType.USRTYPE_ENTERPRISE.getValue());
				comprdList.add(newComprd);
			}
			PeopleInfo oldPerson = poOrganizeInfoService.getPeopleById(p.getSid());//没有移入同一个service是因为此信息需要发给核心请求
			String peopleCode = oldPerson.getPeopleCode();
			//oldPerson.setPeopleCode(p.getPeopleCode());
			oldPerson.setOrganizeInfo(p.getOrganizeInfo());
			oldPerson.setPeopleName(p.getPeopleName());
    		HttpServletResponse response = ServletActionContext.getResponse();
    		response.setCharacterEncoding("utf-8");
			 out = response.getWriter();
			JSONObject peopleObject = this.sendMessage(this.updateChildMessage(oldPerson, comprdList));
			if(peopleObject != null){	
				if(Constant.ResponseStatus.RESPONSE_STATUS_SUCCESS.getValue().equals(peopleObject.get("states"))){	
						//poOrganizeInfoService.deletePrdsByUpdate(peopleCode);
						//poOrganizeInfoService.setPermission(comprdList);
						//poOrganizeInfoService.addPeople(oldPerson);
					poOrganizeInfoService.updateRoleGroupPeopleInfo(peopleCode, comprdList, oldPerson);
					out.println(1);
				}else{
								out.println(0);
					}
				}else{
							out.println(0);
				}
			
		} catch (WFException e) {
			out.println(0);
			e.printStackTrace();
		} catch (Exception e) {//核心连接失败
			out.println(2);
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
		return this.SUCCESS;
  	}
	/**
	 * 根据id删除用户
	 * @return
	 */
  	@SuppressWarnings("static-access")
	public String deletePeople(){
  		PeopleInfo p = poOrganizeInfoService.getPeopleById(peoId);//此处没有并入同一个service是因为需要把该信息发给核心请求
  		PrintWriter out=null;
  		try {
    		HttpServletResponse response = ServletActionContext.getResponse();
    		response.setCharacterEncoding("utf-8");
			 out = response.getWriter();
			JSONObject peopleObject = this.sendMessage(this.deleteChildMessage(p));
			if(peopleObject !=null){
				if(Constant.ResponseStatus.RESPONSE_STATUS_SUCCESS.getValue().equals(peopleObject.get("states"))){
					//poOrganizeInfoService.deletePeople(peoId);
					RoleGroupPeople rgp = new RoleGroupPeople();
					rgp.setPeopleInfo(p);
				//	rgp.setRoleGroupInfo(poOrganizeInfoService.getRoleGroupById(Constant.getRolegroupOrdinary()));
				//	poOrganizeInfoService.deleteRoleGroupPeople(rgp);
				//	poOrganizeInfoService.deletePrdsByUpdate(p.getPeopleCode());
					poOrganizeInfoService.deleteRoleGroupPeopleInfo(peoId, Constant.getRolegroupOrdinary(), rgp, peoplecode);
					out.println(1);
				}else{
					out.println(0);
				}
			}else{
				out.println(0);
			}
			
  		} catch (WFException e) {
			out.println(0);
			e.printStackTrace();
		} catch (Exception e) {//核心连接失败
			out.println(2);
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();	
		}
  		return this.SUCCESS;
  	}
  	/**
  	 * 新增子账户人员接口报文拼接
  	 */
  	public String addChildMessage(PeopleInfo p,List<WfCfgrefCompusrprd> list){
  		JSONObject o = new JSONObject();
  		o.put("msgcode", Constant.MsgCode.MSGCODE_ADDCHILDACC.getValue());
  		o.put("operator", this.getCurrentPeopleCode());
  		o.put("usrnam",p.getPeopleCode());
  		o.put("usrid", this.getCurrentPeople().getUsrId());
  		o.put("usrtype",Constant.UsrType.USRTYPE_ENTERPRISE.getValue());
  		o.put("usrpass", p.getPwd());
  		o.put("orgno",poOrganizeInfoService.getOrganize(p.getOrganizeInfo()).getCorgNo());
  		StringBuffer prdcods = new StringBuffer();
  		for (int i = 0; i < list.size(); i++) {
  			prdcods.append(list.get(i).getPrdcod());
  			if(i <list.size()-1){
  				prdcods.append(",");
  			}
		}
  		o.put("prdcods",prdcods.toString());
  		return o.toString();
  	}
  	/**
  	 *修改子账户人员接口报文拼接
  	 */
  	public String updateChildMessage(PeopleInfo p,List<WfCfgrefCompusrprd> list){
  		JSONObject o = new JSONObject();
  		o.put("msgcode", Constant.MsgCode.MSGCODE_UPDATECHILDACC.getValue());
  		o.put("operator", this.getCurrentPeopleCode());
  		o.put("usrnam",p.getPeopleCode());
  		o.put("subusrid", p.getUsrId());
  		o.put("orgno",poOrganizeInfoService.getOrganize(p.getOrganizeInfo()).getCorgNo());
  		StringBuffer prdcods = new StringBuffer();
  		for (int i = 0; i < list.size(); i++) {
  			prdcods.append(list.get(0).getPrdcod());
  			if(i <list.size()-1){
  				prdcods.append(",");
  			}
		}
  		o.put("prdcods",prdcods.toString());
  		return o.toString();
  		
  	}
	/**
  	 *删除子账户人员接口报文拼接
  	 */
  	public String deleteChildMessage(PeopleInfo p){
  		JSONObject o = new JSONObject();
  		o.put("msgcode", Constant.MsgCode.MSGCODE_DELETECHILDACC.getValue());
  		o.put("operator", this.getCurrentPeopleCode());
  		o.put("subusrid", p.getUsrId());
  		return o.toString();
  	}
  	/**
  	 *团队新增接口报文拼接
  	 */
  	public String addOrgTeamMessage(OrganizeInfo org){
  		JSONObject o = new JSONObject();
  		o.put("msgcode", Constant.MsgCode.MSGCODE_ADDORGTEAM.getValue());
  		o.put("operator", this.getCurrentPeopleCode());
  		o.put("orgnam", org.getOrgName());
  		XPeopleInfo p = this.getCurrentPeople();
  		o.put("porgno", p.getCorgNo());
  		o.put("orgtyp", "7");
  		return o.toString();
  	}
  	/**
  	 *向核心系统发送报文并接受响应报文
  	 */
  	public JSONObject sendMessage  (String jsonMessage) throws Exception{
  		 HttpCommonEntity entity = new HttpCommonEntity(Constant.getConfigUrl());
	     try {
			entity.setParams( httpProviderService.parseStringToListParam(jsonMessage));
			entity = this.httpProviderService.httpCommunicate(entity);
			//entity.getResponse().getReponse().close();
		} catch (Exception e) {
			e.printStackTrace();
		throw e;
		}
	     HttpResponseCommonEntity resEntity = entity.getResponse();
		 String message = (String) resEntity.getReturnObj();
		 System.out.println(message);
		 if("".equals(message)|| null == message){
			 return null;
		 }
		 JSONObject object  = JSONObject.fromObject(message);
		 return object;
  	}
  	/**
  	 * 为子账户新增团队
  	 * @return
  	 * @throws IOException 
  	 */
  	public String saveNewTeam() {
  		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
  		OrganizeInfo o = new OrganizeInfo();
  		o.setOrgName(orgName);
  		String orgNo = GUIDGenerator.generateId();
  		o.setOrgNo(orgNo);
  		String sid = GUIDGenerator.generateId();
  		o.setSid(sid);
  		o.setOrgPath("|root|" +this.getCurrentPeople().getOrgNo()+"|"+ orgNo + "|");
  		o.setOrgType(Constant.getOrgtypeTeam());
  		o.setOrgState((short)0);
  		o.setParentOrg(this.getCurrentPeople().getOrganizeSid());
  		o.setManageType((byte)1);
  		o.setIncludeChild((byte)1);
  		o.setBizOrgFlag(false);
  		o.setOrgLevel(2);
  		o.setManageOrgFlag(false);
  		PrintWriter out=null;
		try {
			out = response.getWriter();
			//先发送新增机构请求
		   JSONObject orgObject = this.sendMessage(this.addOrgTeamMessage(o));
		   if(orgObject != null){
			   if(Constant.ResponseStatus.RESPONSE_STATUS_SUCCESS.getValue().equals(orgObject.get("states"))){
				   o.setCorgNo((String)orgObject.get("orgno"));//将核心发过来的机构编号关联进去
				   	poOrganizeInfoService.addOrganize(o);
				   	Map<String, String> map = new HashMap<String, String>();
				   	map.put("orgId", o.getSid());
				   	frontObjStr = JsonBinder.toJson(map);
					out.println(frontObjStr);
			   }else{
				   out.println(0);
			   }
		   }else{
			   out.println(0);
		   }
  		
		} catch (Exception e) {
			out.println(2);
			e.printStackTrace();
		} finally{
			out.flush();
			out.close();
		}
  		return SUCCESS;
  	}
  	public String queryAllTeam(){
  		orgList =poOrganizeInfoService.queryOrganize(this.getCurrentPeople());
  		String listStr = JsonBinder.toJson(orgList);
  		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter out = response.getWriter();
			out.println(listStr);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		return SUCCESS;
  	}
  	/**
  	 * 根据当前登录人下的所有子账户的模糊查询
  	 * @return
  	 */
  	public String fuzzyQueryChildren(){
		String parntCode = this.getCurrentPeopleCode();
		list = poOrganizeInfoService.querfuzzyQueryChildren(parntCode, dataStr);
		String listStr = JsonBinder.toJson(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter out = response.getWriter();
			out.println(listStr);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		ajaxResponseMessage(listStr);
		return SUCCESS;
	}
  	/**
  	 * 通过产品编码查询原交易码
  	 * @return
  	 */
  	public String findWfcfgerfPrdById(){
  		//System.out.println(dataStr);
  		list = poOrganizeInfoService.findWfcfgerfPrdById(dataStr);
  		String listStr  = JsonBinder.toJson(list);
  		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter out = response.getWriter();
			out.println(listStr);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		return SUCCESS;
  	}
  	/**
  	 * 根据id查询使用人信息
  	 * @return
  	 */
  	public String findPeopleInfoById(){
  		list = poOrganizeInfoService.findPeopleInfoById(peoId);
  		String listStr  = JsonBinder.toJson(list);
  		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(listStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
			
		}
  		return SUCCESS;
  	}
	/**
  	 * 根据peopelecode 校验输入的 peoplecode是否重复
  	 * @return
  	 */
  	public String checkPeopleRepeat(){
  	 PrintWriter out = null;
  	 try{
  		list = poOrganizeInfoService.findPeopleInfoByPeoplecode(peoplecode);
  		//String listStr  = JsonBinder.toJson(list);
  		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		out = response.getWriter();
		if(list.size() > 0){
		 out.println(HttpStatus.STATIS_FAIL.getCode());
		
		}else{
		 out.println(HttpStatus.STATIS_SUCCESS.getCode());	
		}
  	 }catch(Exception e){
  		e.printStackTrace();
  	}finally{
  		out.flush();
		out.close();
  	}
  		return null;
  	}
  	
  	/**
  	 * 校验团队名称是否重复
  	 * @return
  	 */
  	public String checkTeamRepeat(){
  		 PrintWriter out = null;
  	  	 try{
  	  		list = poOrganizeInfoService.findPeopleInfoByNewTeam(newTeam);
  	  		//String listStr  = JsonBinder.toJson(list);
  	  		HttpServletResponse response = ServletActionContext.getResponse();
  			response.setCharacterEncoding("utf-8");
  			out = response.getWriter();
  			if(list.size() > 0){
  			 out.println(HttpStatus.STATIS_FAIL.getCode());
  			}else{
  			 out.println(HttpStatus.STATIS_SUCCESS.getCode());	
  			}
  	  	 }catch(Exception e){
  	  		e.printStackTrace();
  	  	}finally{
  	  		out.flush();
  			out.close();
  	  	}
  	  	   return null;
  	  	}
  	

}
