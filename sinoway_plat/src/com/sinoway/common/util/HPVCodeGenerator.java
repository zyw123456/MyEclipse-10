/**   
* @Title: HashCreater.java 
* @Package com.sinoway.common.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author Leo May the source be with you!   
* @date 2015-12-16 下午7:09:34 
* @version V1.0   
*/
package com.sinoway.common.util;

import java.util.Date;

/** 
 * @ClassName: HPVCodeGenerator 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Leo May the source be with you! 
 * @date 2015-12-16 下午7:09:34 
 *  
 */
public class HPVCodeGenerator {

	public static String getVCode() {
		return "V" + Math.random() + DateUtil.dateFormatYMDHMSS.format(new Date()).toString();
	}
}
