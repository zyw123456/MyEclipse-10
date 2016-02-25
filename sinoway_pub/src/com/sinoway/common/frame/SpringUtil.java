package com.sinoway.common.frame;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Spring IOC 注入工具类
 * @author THINK
 */
public class SpringUtil {
	
    private static ApplicationContext ac = null;
    
    private SpringUtil(){
    	ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
    }
    
    private static SpringUtil instance = new SpringUtil();
    
    public static Object getBean(String name){
        return ac.getBean(name);
    }
    
    public static Object getBean(Class cls){
    	return ac.getBean(cls);
    }

	public static SpringUtil getInstance() {
		return instance;
	}

}
