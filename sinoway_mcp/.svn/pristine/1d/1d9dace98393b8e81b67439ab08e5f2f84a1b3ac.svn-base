package com.sinoway.common.service.impl;
import org.springframework.stereotype.Service;
import com.sinoway.common.service.ProductService;
import com.sinoway.common.util.HttpSendMessageUtil;

@Service("defaultProductService")
public class DefaultProductService implements ProductService{
	HttpSendMessageUtil util = HttpSendMessageUtil.getInstance();
	
	String str = null;
	@Override
	public Object addprdInfo(Object prdInfo) throws Exception {
	
		return str = util.sendMessage(prdInfo.toString());
	}

	@Override
	public Object editprdInfo(Object prdInfo) throws Exception {
		
		return str = util.sendMessage(prdInfo.toString());
	}

	@Override
	public Object delprdInfo(Object prdInfo) throws Exception {
		
		return str = util.sendMessage(prdInfo.toString());
	}

}
