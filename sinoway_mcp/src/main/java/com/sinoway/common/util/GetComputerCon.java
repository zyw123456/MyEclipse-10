package com.sinoway.common.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 获取机器配置信息
 * @author liuzhen
 *
 */
public class GetComputerCon {
	// 机器ID
	private  String localIp =null;
	
	private String loaclMac = null;
	
	private static  GetComputerCon cpuCon =null;

	private GetComputerCon(){
		init();
	}
	
	public static GetComputerCon  getInstance(){
		if(cpuCon == null){
			cpuCon = new GetComputerCon();
		}
		
		return cpuCon;
	}
	/**
	 * 初始化
	 * 
	 */
	private void init(){
		localIp = getLocalIp();
		loaclMac = getLoaclMac();
	}
	
	/**
	 * 获取机器Mac地址
	 * @return
	 */
	private String getLoaclMac(){
		return "";
	}
	/**
	 * 获取机器IP
	 * @return
	 */
	
	@SuppressWarnings({ "unchecked" })
	private String getLocalIp() {
		
		 Enumeration allNetInterfaces = null;  
		    try {  
		        allNetInterfaces = NetworkInterface.getNetworkInterfaces();  
		    } catch (SocketException e) {  
		  
		        e.printStackTrace();  
		    }  
		    InetAddress ip = null;  
		    while (allNetInterfaces.hasMoreElements()) {  
		        NetworkInterface netInterface = (NetworkInterface) allNetInterfaces  
		                .nextElement();  
		        Enumeration addresses = netInterface.getInetAddresses();  
		        while (addresses.hasMoreElements()) {  
		            ip = (InetAddress) addresses.nextElement();  
		            if (ip != null && ip instanceof Inet4Address  
		                    && ip.getHostAddress().indexOf(".") != -1 && (!ip.isLoopbackAddress())) {  
		                localIp = ip.getHostAddress();
		            }  
		        }  
		    }
			 return "127.0.0.1";
	}
	
	//reload方法
	public static void reLoadGetComputerCon(){
		
		cpuCon = new GetComputerCon();
		
	}

	public void setLocalIp(String localIp) {
		this.localIp = localIp;
	}

	public void setLoaclMac(String loaclMac) {
		this.loaclMac = loaclMac;
	}
	
	public String getMac(){
		return this.loaclMac;
	}
	public String getIp(){
		return this.localIp;
	}
}
