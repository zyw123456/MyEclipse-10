package com.sinoway.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 数据源连接参数配置类
 * 
 * @Copyright: Copyright (c) 2013
 * @Company: 深圳市银之杰科技股份有限公司
 * @author TANYONG * 
 */
public class Configuration {
	/**
	 * 记录日志
	 */
	private static Logger log = LoggerFactory.getLogger(Configuration.class);

	/**
	 * 配置类实例	 */
	private static Configuration instance;
	
	/**
	 * 构造方法	 * 
	 * 从取配置文件，获取连接URL和name
	 */
	private Configuration() {
		init();
	}

	
	public void init() {

	}

	/**
	 * 获取配置实例
	 * 
	 * @return instance 配置实例
	 */
	public static synchronized Configuration getInstance() {
		if (instance == null) {
			instance = new Configuration();
		}
		return instance;
	}

	/**
	 * 获取属性值，如果该值不存在则设置默认值。	 * @param key 需要获取的属性值	 * @param defaultValue 默认值
	 * @return defaultValue 例：PRE_FETCH_SIZE =
	 *         Configuration.getIntProperty("core.taskq.obtain.batchsize", 默认值)
	 */
	public static String getProperty(String key, String defaultValue) {
		String sRet = defaultValue;
		try{
			sRet = FileConfiguration.instance().getProperty(key);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return sRet;
	}
	
	public static int getIntProperty(String key, int defaultValue) {
		int iRet = defaultValue;
		try{
			iRet = Integer.valueOf(FileConfiguration.instance().getProperty(key)).intValue();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return iRet;
	}
	
	public static boolean getBooleanValue(String key, boolean defaultValue) {
		boolean bRet = defaultValue;
		try {
			bRet = Boolean.valueOf(FileConfiguration.instance().getProperty(key)).booleanValue();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return bRet;
	}

	public static long getLongValue(String key, long defaultValue)
	{
		long lRet = defaultValue;
		try {
			lRet = Long.valueOf(FileConfiguration.instance().getProperty(key)).longValue();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return lRet;
	}
}
