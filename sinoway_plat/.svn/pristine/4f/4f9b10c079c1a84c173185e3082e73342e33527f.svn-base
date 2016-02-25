package com.sinoway.common.util;

import java.security.MessageDigest;

/**
 * 通用字符处理工具类
 * @author LiuZhen
 * @version 1.0
 * 2015-10-30
 */
public class StringUtil {
	/**
	 * MD5 加密算法
	 */
	public static final String MD5 = "MD5";
	
	/**
	 * SHA1 加密算法
	 */
	public static final String SHA1 = "SHA1";
	
	/**
	 * null转换空
	 * @param str
	 * @return
	 */
	public static String NullToString(String str){
		
		if(str == null){
			return "";
		}
		return str;
	}
	
	/**
	 * 去前后空格
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		if(str == null){
			return "";
		}
		
		return str.trim();
		
	}
	
	/**
	 * 采用hash算法获得信息摘要，可采用的算法有MD5算法和SHA1算法
	 * 
	 * @param unencodedPassword
	 *            待加密的字符串
	 * @param algorithm
	 *            加密算法，只能写MD5或者SHA1
	 * @return <li>null 失败</li> <li>非空 加密后的字符串</li>
	 */
	public static String hash(byte[] unencodedPassword, String algorithm) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {

			return null;
		}

		md.reset();

		md.update(unencodedPassword);

		byte[] encodedPassword = md.digest();

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}

			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}

		return buf.toString();
	}

	/**
	 * 采用hash算法获得信息摘要，可采用的算法有MD5算法和SHA1算法
	 * 
	 * @param password
	 *            待加密的字符串
	 * @param algorithm
	 *            加密算法，只能写MD5或者SHA1
	 * @return <li>null 失败</li> <li>非空 加密后的字符串</li>
	 */
	public static String hash(String str, String algorithm) {
		return hash(str.getBytes(), algorithm);
	}
	public static void main(String[] args) {
		System.out.println(StringUtil.hash("00000000000000000000","MD5"));
	}
}
