package com.sinoway.common.config;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 加载配置文件
 * @author THINK
 */
public class FileConfiguration {
	
	private static Logger log = LoggerFactory.getLogger(FileConfiguration.class);
	
	private static FileConfiguration fileConfiguration;
	
	public static FileConfiguration instance(){
		if(fileConfiguration == null){
			initFileConfiguration();
		}
		return fileConfiguration ;
	}
	
	private synchronized static void initFileConfiguration(){
		if(fileConfiguration == null){
			fileConfiguration = new FileConfiguration();
		}
	}
	
	private FileConfiguration()
	{
		init();
	}
	
	/**
	 * JDK Properties表
	 */

	private  Properties config = null;
	
	private String configPath = "/logback.xml";
	/**
	 * 初始化，加载homePath路径下的所有配置文件
	 */
	private void init() {
		config = new Properties();
		
		
		String path = System.getProperty("user.dir")+File.separator+"conf"+configPath;
		File configdir = new File(path);
		if(!configdir.exists()) {
			URL url = this.getClass().getClassLoader().getResource(configPath);
			if(null != url) {
				path = url.getPath();
			} else {
				System.out.println("*********ERROR******************:configPath:'/logback.xml' not found!");
			}
			
		}
		System.out.println("Properties path=--------------------------------"+path);
		initFiles(path);
		
//		String path3 = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
//		System.out.println("Properties path3=--------------------------------"+path3);
//		PropertyConfigurator.configure(path);
		
	}
	
	/**
	 * 加载配置文件
	 * @param path
	 */
	private void initFiles(String path) {
		
		path = path.replace(configPath, "");
		File configdir = new File(path);
		
		log.debug(configdir.getAbsolutePath());
		if (configdir.exists() && configdir.isDirectory()) {
			for (File comm : configdir.listFiles()) {
				if (comm.isFile() && (comm.getName().endsWith("conf") || comm.getName().endsWith(".properties"))) {
					try {
						FileInputStream fin = new FileInputStream(comm);
						InputStream in = new BufferedInputStream(fin);
						config.load(in);
						in.close();
						fin.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}else{
			try {
				throw new Exception("初始化配置文件失败 initFiles() 传递的配置文件路径不存在path="+path);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} 

	/**
	 * 获取XML属性
	 * @param key
	 *            属性值
	 * @return 读取属性到配置文件
	 */
	public  String getXMLProperty(String key) {
		if (config == null) {
			init();
		}
		if (key == null || config == null) {
			return null;
		}
		return config.getProperty(key);
	}

	/**
	 * 从配置文件获取属性值
	 * @param key
	 *            要获取的值
	 * @param defaultV
	 *            查找不到时的默认值
	 * @return 属性值
	 */
	public  String getProperty(String key, String defaultV) {
		if (config == null) {
			init();
		}
		if (key == null || config == null) {
			return defaultV;
		}
		if (config.containsKey(key))
		{
			return config.getProperty(key);
		}
		return defaultV;
	}

	/**
	 * 从配置文件获取属性值

	 * 
	 * 
	 * 
	 * @param key
	 *            要获取的值

	 * 
	 * @return 属性值

	 * 
	 */
	public  String getProperty(String key) {
		if (config == null) {
			init();
		}
		if (key == null || config == null) {
			return null;
		}
		if (config.containsKey(key))
		{
			return config.getProperty(key);
		}
		return null;
	}

	/**
	 * 获取XML文件属性

	 * 
	 * @param key
	 *            需要获取的属性

	 * 
	 * @param defaultV
	 *            默认值

	 * 
	 * @return 属性值

	 * 
	 */
	public  String getXMLProperty(String key, String defaultV) {
		if (config == null) {
			init();
		}
		if (key == null || config == null) {
			return defaultV;
		}
		if (config.containsKey(key))
		{
			return config.getProperty(key);
		}
		return defaultV;
	}

	/**
	 * 获取布尔型属性

	 * 
	 * @param key
	 *            需要获取的值

	 * 
	 * @param defaultValue
	 *            默认值

	 * 
	 * @return 布尔型值

	 * 
	 */
	public  boolean getBooleanProperty(String key, boolean defaultValue) {
		if (config == null) {
			init();
		}
		if (key == null || config == null) {
			return defaultValue;
		}
		String value = config.getProperty(key);
		if (value == null) {
			return defaultValue;
		}
		return Boolean.parseBoolean(value);
	}
	
	

	/**
	 * 获取整型属性值

	 * 
	 * @param key
	 *            所需获取的属性值

	 * 
	 * @param defaultValue
	 *            默认值

	 * 
	 * @return 属性值

	 * 
	 */

	public  int getIntProperty(String key, int defaultValue) {
		if (config == null) {
			init();
		}
		if (key == null || config == null) {
			return defaultValue;
		}

		String value = config.getProperty(key);
		if (value == null) {
			return defaultValue;
		}
		return Integer.parseInt(value);
	}

	/**
	 * 获取本地用户使用的语言
	 * 
	 * @return 本地语言
	 */
	public static Locale getLocale() {
		return Locale.getDefault();
	}
}
