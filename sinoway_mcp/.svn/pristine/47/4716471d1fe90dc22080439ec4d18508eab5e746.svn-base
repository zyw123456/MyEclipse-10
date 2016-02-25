package com.sinoway.mcp.service.strade.sjutang.Util;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import com.sinoway.common.constant.SystemConstant;
import com.sinoway.common.util.Base64;


/**
 * 数据堂 接口工具类
 * @author zhangyanwei
 *
 */
public class SjtUtil {

	
	 Cipher ecipher;
	 Cipher dcipher;
	  byte[] salt = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35,(byte) 0xE3, (byte) 0x03 };
	  
	  /**
	   * 构造方法
	   * @param passPhrase 将用户的apikey作为密钥传入
	   * @throws Exception
	   */
	  public SjtUtil(String passPhrase) throws Exception {
	    int iterationCount = 2;
	    KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
	    SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
	    ecipher = Cipher.getInstance(key.getAlgorithm());
	    dcipher = Cipher.getInstance(key.getAlgorithm());
	    AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
	    ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
	    dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
	  }
	  
	  /**
	   * 加密
	   * @param str 要加密的字符串
	   * @return
	   * @throws Exception
	   */
	  public String encrypt(String str) throws Exception {
		str = new String(str.getBytes(), SystemConstant.DEFAULT_CHARSET);
		return Base64.encode(ecipher.doFinal(str.getBytes()));
	  }
	  /**
	   * 解密
	   * @param str 要解密的字符串
	   * @return
	   * @throws Exception
	   */
	  public String decrypt(String str) throws Exception {
		return new String(dcipher.doFinal(Base64.decode(str)),SystemConstant.DEFAULT_CHARSET);
	  }
	
	  
	  public static void main(String[] args) throws Exception {
		  SjtUtil desEncrypter = new SjtUtil("3e99ebcde110f60c221bc736643efb7");
		 //str为加密前的参数字符串
	      String str = "key=230102196505074119";
		  System.out.println(desEncrypter.encrypt(str));
	}

	
}
