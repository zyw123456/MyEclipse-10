package com.sinoway.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

public class IOStreamUtil {

	final static int BUFFER_SIZE = 4096;

	final static String UTF8 = "UTF-8";

	/**
	 * 利用BufferedReader实现Inputstream转换成String <功能详细描述>
	 * 
	 * @param in
	 * @return String
	 */

	public static String Inputstr2Str_Reader(InputStream in, String encode) {

		String str = "";
		try {
			if (encode == null || encode.equals("")) {
				// 默认以utf-8形式
				encode = UTF8;
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, encode));
			StringBuffer sb = new StringBuffer();

			while ((str = reader.readLine()) != null) {
				sb.append(str).append("\n");
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return str;
	}

	/**
	 * 利用byte数组转换InputStream------->String <功能详细描述>
	 * 
	 * @param in
	 * @return
	 * @see [类、类#方法、类#成员]
	 */

	public static String Inputstr2Str_byteArr(InputStream in, String encode) {
		StringBuffer sb = new StringBuffer();
		byte[] b = new byte[1024];
		int len = 0;
		try {
			if (encode == null || encode.equals("")) {
				// 默认以utf-8形式
				encode = UTF8;
			}
			while ((len = in.read(b)) != -1) {
				sb.append(new String(b, 0, len, encode));
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";

	}

	/**
	 * 利用ByteArrayOutputStream：Inputstream------------>String <功能详细描述>
	 * 
	 * @param in
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String Inputstr2Str_ByteArrayOutputStream(InputStream in, String encode) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int len = 0;
		try {
			if (encode == null || encode.equals("")) {
				// 默认以utf-8形式
				encode = "utf-8";
			}
			while ((len = in.read(b)) > 0) {
				out.write(b, 0, len);
			}
			return out.toString(encode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 对象转数组
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] object2ByteArray(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return bytes;
	}

	
	
	/**
	 * 对象转数组
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] String2ByteArray(String obj,String encoding) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return bytes;
	}

	/**
	 * 数组转对象
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object byteArray2Object(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			obj = ois.readObject();
			ois.close();
			bis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return obj;
	}

	/**
	 * 将InputStream转换成String
	 * 
	 * @param in
	 *            InputStream
	 * @return String
	 * @throws Exception
	 * 
	 */
	public static String InputStream2String(InputStream in) throws Exception {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);

		data = null;
		String res = new String(outStream.toByteArray(), UTF8);
		outStream.close();
		return res;
	}

	/**
	 * 将InputStream转换成某种字符编码的String
	 * 
	 * @param in
	 * @param encoding
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	public static String InputStream2String(InputStream in, String encoding) throws IOException  {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);

		data = null;
		return new String(outStream.toByteArray(), encoding);
	}

	/**
	 * 利用ByteArrayInputStream：String------------------>InputStream <功能详细描述>
	 * 
	 * @param inStr
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static InputStream Str2Inputstr(String inStr) {
		try {
			return new ByteArrayInputStream(inStr.getBytes(UTF8));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 利用ByteArrayInputStream：String------------------>InputStream <功能详细描述>
	 * 
	 * @param inStr
	 * @param encoding
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static InputStream Str2Inputstr(String inStr, String encoding) {
		try {
			return new ByteArrayInputStream(inStr.getBytes(encoding));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
