/**
 * 
 */
package com.yzj.ibank.server;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.yzj.wf.com.ibank.standard.server.IBankSocketControl;

/**
 * 创建于:2012-5-26<br>
 * 版权所有(C) 2012 深圳市银之杰科技股份有限公司<br>
 * 普通报文测试服务端
 * 
 * @author WangXue
 * @version 1.0.0
 */
public class IBankServerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String[] local = { "config/bean-com.xml" };
			ApplicationContext appContext = new FileSystemXmlApplicationContext(local);
			IBankSocketControl socketControl = (IBankSocketControl) appContext.getBean("SocketControl");
			socketControl.start();
		} catch (BeansException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
