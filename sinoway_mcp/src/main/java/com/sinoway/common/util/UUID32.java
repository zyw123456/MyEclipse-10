package com.sinoway.common.util;

import java.util.UUID;

/**
 * UUID生成器
 * @author Liuzhen
 * @version 1.0
 * 2015-11-3
 */
public class UUID32 {
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
