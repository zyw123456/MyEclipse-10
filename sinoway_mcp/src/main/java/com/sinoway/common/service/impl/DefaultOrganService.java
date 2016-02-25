package com.sinoway.common.service.impl;

import org.springframework.stereotype.Service;
import com.sinoway.common.service.OrganService;
import com.sinoway.common.util.HttpSendMessageUtil;

@Service("defaultOrganService")
public class DefaultOrganService implements OrganService{
	HttpSendMessageUtil util = HttpSendMessageUtil.getInstance();
	
	
	String str = null;
	@Override
	public Object addOrganInfo(Object OrganInfo) throws Exception {
		
		return str =util.sendMessage(OrganInfo.toString());
	}

	@Override
	public Object editOrganInfo(Object OrganInfo) throws Exception {
		
		return str =util.sendMessage(OrganInfo.toString());
	}

	@Override
	public Object delOrganInfo(Object OrganInfo) throws Exception {
		
		return str = util.sendMessage(OrganInfo.toString());
	}

	

}
