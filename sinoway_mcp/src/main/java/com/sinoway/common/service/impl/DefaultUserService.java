package com.sinoway.common.service.impl;

import org.springframework.stereotype.Service;
import com.sinoway.common.service.UserService;
import com.sinoway.common.util.HttpSendMessageUtil;

@Service("defaultUserService")
public class DefaultUserService implements UserService {
	HttpSendMessageUtil util = HttpSendMessageUtil.getInstance();
	String str =null;
	
	@Override
	public Object addusrInfo(Object usrInfo) throws Exception {
		return str = util.sendMessage(usrInfo.toString());
	}

	@Override
	public Object editusrInfo(Object usrInfo) throws Exception {
		return str = util.sendMessage(usrInfo.toString());
	}

	@Override
	public Object delusrInfo(Object usrInfo) throws Exception {
		return str = util.sendMessage(usrInfo.toString());
	}

	
}
