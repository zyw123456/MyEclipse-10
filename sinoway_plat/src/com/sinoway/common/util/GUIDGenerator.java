package com.sinoway.common.util;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * GUID生成器，采用单例实现
 * 	长度32位流水号
 * 	生成规则：12位年月日时分秒+8位服务器编码+8位IP标识+4位顺序号
 * @author THINK
 */
public class GUIDGenerator {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
	private short counter = (short) 0;
	
	/**
	 * 私有构造函数
	 */
	private GUIDGenerator() {
	}
	
	private static GUIDGenerator instance = new GUIDGenerator();
	
	private final int IP;
	{
		int ipadd;
		try {
			ipadd = toInt(InetAddress.getLocalHost().getAddress());
		} catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}

	private final String IPString = format(IP);
	
	private final int JVM = (int) (System.currentTimeMillis() >>> 8);

	private final String JVMString = format(JVM);
	
	private String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}
	
	private String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}
	
	private int getJVM() {
		return JVM;
	}

	private short getCount() {
		synchronized (GUIDGenerator.class) {
			counter++;
			if (counter < 0) {
				counter = 0;
			}
			return counter;
		}
	}

	/**
	 * IP地址使局域网内唯一，如果你用网卡物理号则全球唯一
	 */
	private int getIP() {
		return IP;
	}
	
	public static String generateId(){
		return instance.generateIdStringBuffer().toString();
	}

	private StringBuffer generateIdStringBuffer() {
//		System.out.println(sdf.format(new Date()).length());
//		System.out.println(sdf.format(new Date()));
//		System.out.println(JVMString.length());
//		System.out.println(format(IP).length());
//		System.out.println(format(getCount()).length());
		return new StringBuffer(32).append(sdf.format(new Date())).append(JVMString).append(format(IP)).append(format(getCount()));
	}
	
	private int toInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}
	
	public static void main(String[] args) {
		Long long1 = System.currentTimeMillis();
//		for(int i=0;i<100;i++){
			System.out.println(GUIDGenerator.generateId().length());
//		}
		System.out.println(System.currentTimeMillis()-long1);
	}
	
}
