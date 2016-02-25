package com.sinoway.common.service.init;

import java.util.HashMap;
import java.util.Map;

/**
 * 初始化service
 * @author Liuzhen
 * @version 1.0
 * 2015-11-18
 */
public class CfgInfoInitService {

	// 服务器配置信息
	private Map<String,Object> serInfo = new HashMap<String,Object>();
	// 内部--外部交易码对照
	private Map<String,String> ioTrCodInfo = new HashMap<String,String>();
	// 外部--内部交易码对照
	private Map<String,String> oiTrCodInfo = new HashMap<String,String>();
	// 队列配置信息
	private Map<String,String> queInfo = new HashMap<String,String>();
	// 渠道交易配置信息
	private Map<String,String> chlTrdInfo = new HashMap<String,String>();
	// 渠道产品配置信息
	private Map<String,String> chlPrdInfo = new HashMap<String,String>();
	// 渠道配置信息
	private Map<String,String> chlInfo = new HashMap<String,String>();
	// 交易配置信息
	private Map<String,String> trdInfo = new HashMap<String,String>();
	// 地址配置信息
	private Map<String,String> addrInfo = new HashMap<String,String>();
	// 系统配置参数
	private Map<String,String> sysParmInfo = new HashMap<String,String>();
	// 异常配置参数信息
	private Map<String,String> erroInfo = new HashMap<String,String>();

	/**
	 * 初始化方法
	 */
	public void initAll(){
		System.out.println("asd");
	}
	
	public void reloadAll(){
		
	}
	
	public void clearAll(){
		
	}
	
	public void init(String key){
		
	}
	
	public void  reload(String key){
		
	}
	

	public Map<String, Object> getSerInfo() {
		return serInfo;
	}

	public Map<String, String> getIoTrCodInfo() {
		return ioTrCodInfo;
	}

	public Map<String, String> getOiTrCodInfo() {
		return oiTrCodInfo;
	}

	public Map<String, String> getQueInfo() {
		return queInfo;
	}

	public Map<String, String> getChlTrdInfo() {
		return chlTrdInfo;
	}

	public Map<String, String> getChlPrdInfo() {
		return chlPrdInfo;
	}

	public Map<String, String> getChlInfo() {
		return chlInfo;
	}

	public Map<String, String> getTrdInfo() {
		return trdInfo;
	}

	public Map<String, String> getAddrInfo() {
		return addrInfo;
	}

	public Map<String, String> getSysParmInfo() {
		return sysParmInfo;
	}

	public Map<String, String> getErroInfo() {
		return erroInfo;
	}
	
	
	
}
