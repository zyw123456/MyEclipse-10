package com.sinoway.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sun.corba.se.impl.orbutil.threadpool.TimeoutException;

/**
 * md5验证工具类
 * @author jintao
 *
 * @date 2016-1-14 上午11:01:47
 */
public class MD5Utils {

	private  static MessageDigest msgDigest = null;
	
	/**
	 * MD5 加密算法
	 */
	public static final String MD5 = "MD5";
	
	/**
	 * SHA1 加密算法
	 */
	public static final String SHA1 = "SHA1";
	
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
	
	public static String str2MD5(String str) throws Exception{
		String upperSign  = null;
        try {
             msgDigest = MessageDigest.getInstance("MD5");
             msgDigest.update(str.toString().getBytes("utf-8"));
             byte[] bytes = msgDigest.digest();
             upperSign = bin2hex(bytes).toLowerCase();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return upperSign;
 }
	
	 public static String bin2hex(byte[] bs) {
	        char[] digital = "0123456789ABCDEF".toCharArray();
	        StringBuffer sb = new StringBuffer("");
	        int bit;
	        for (int i = 0; i < bs.length; i++) {
	            bit = (bs[i] & 0x0f0) >> 4;
	            sb.append(digital[bit]);
	            bit = bs[i] & 0x0f;
	            sb.append(digital[bit]);
	        }
	        return sb.toString();
	    }
	 
	 /**
	  * 标准md5
	  * 
	  * @param s
	  * @return
	  */
	 public final static String MD5(String s) {
	        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       

	        try {
	            byte[] btInput = s.getBytes();
	            // 获得MD5摘要算法的 MessageDigest 对象
	            MessageDigest mdInst = MessageDigest.getInstance("MD5");
	            // 使用指定的字节更新摘要
	            mdInst.update(btInput);
	            // 获得密文
	            byte[] md = mdInst.digest();
	            // 把密文转换成十六进制的字符串形式
	            int j = md.length;
	            char str[] = new char[j * 2];
	            int k = 0;
	            for (int i = 0; i < j; i++) {
	                byte byte0 = md[i];
	                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	                str[k++] = hexDigits[byte0 & 0xf];
	            }
	            return new String(str);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
}
