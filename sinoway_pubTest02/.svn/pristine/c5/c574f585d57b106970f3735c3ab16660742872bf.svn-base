package com.sinoway.base.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.sinoway.base.entity.BCfgdefRoleinfo;
import com.sinoway.base.service.BCfgdefRoleinfoService;
import com.sinoway.common.util.GUIDGenerator;

public class BCfgdefRoleinfoAction extends ActionSupport{
	private static final long serialVersionUID = -2997407109829726090L;
	@Autowired
	private BCfgdefRoleinfoService bcfgdefRoleinfoService;
	private BCfgdefRoleinfo roleInfo;
	private List<BCfgdefRoleinfo> roleInfoList;
	
	public String execute() throws Exception {  
		System.out.println("------WIN-----");
		return null;
    }  
	
	
	public String add(){
		System.out.println(roleInfo);
		if("".equals(roleInfo.getRolecod())){
			roleInfo.setRolecod(null);
		}
		if("".equals(roleInfo.getRolenam())){
			roleInfo.setRolecod(null);
		}
		
		//List<BCfgdefRoleinfo> roles=bcfgdefRoleinfoService.findByObj(roleInfo);
		//System.out.println(roles.size());
		//roleInfo.setId(GUIDGenerator.generateId());
		//System.out.println(roleInfo);
		//bcfgdefRoleinfoService.save(roleInfo);
		return "addRoleInfo";
	}
	
	
	
	public BCfgdefRoleinfo getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(BCfgdefRoleinfo roleInfo) {
		this.roleInfo = roleInfo;
	}

	public List<BCfgdefRoleinfo> getRoleInfoList() {
		return roleInfoList;
	}
	public void setRoleInfoList(List<BCfgdefRoleinfo> roleInfoList) {
		this.roleInfoList = roleInfoList;
	}
	
}
