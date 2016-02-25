package com.sinoway.common.frame;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("springContextUtil")
public class SpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext; 
	
	public void setApplicationContext(ApplicationContext ac)
			throws BeansException {
		applicationContext = ac;
	}
	
	 /**
	  * 获取bean
	  * @param name
	  * @return
	  * @throws BeansException
	  */
	 public synchronized  static Object getBean(String name) throws BeansException {
		 System.out.println(applicationContext);
		 Object obj = applicationContext.getBean(name);
		 return obj;
	 
	 }
	 
	 /**
	  * 获取bean
	  * @param name
	  * @return
	  * @throws BeansException
	  */
	 public static Object getBean(Class cls) throws BeansException {
		  
		 return applicationContext.getBean(cls);
	 
	 }

}
