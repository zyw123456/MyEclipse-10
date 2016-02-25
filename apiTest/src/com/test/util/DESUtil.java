package com.test.util;


import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DESUtil {

	
	
	public static String encode(String key,byte[] data) throws Exception{
		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory keyFactory =
		SecretKeyFactory.getInstance("DES");
		//key的长度不能够小于8位字节
		Key secretKey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		IvParameterSpec iv=new IvParameterSpec("12345678".getBytes());//向量
		AlgorithmParameterSpec paramSpec = iv;
		cipher.init(Cipher.ENCRYPT_MODE, secretKey,paramSpec);
		byte[] bytes = cipher.doFinal(data);
		return Base64.encode(bytes);
		}
	
	public static byte[] decode(String key, byte[] data) throws Exception {
		try {
			SecureRandom sr = new SecureRandom();
			DESKeySpec dks = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// key 的长度不能够小于 8 位字节
			Key secretKey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
			AlgorithmParameterSpec paramSpec = iv;
			cipher.init(Cipher.DECRYPT_MODE,  secretKey,paramSpec);
			return cipher.doFinal(data);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
}
