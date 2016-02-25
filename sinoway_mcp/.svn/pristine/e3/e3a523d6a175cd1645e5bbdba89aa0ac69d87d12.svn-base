package com.sinoway.common.util;

import java.util.List;

import com.sinoway.common.entity.HttpCommonEntity;
import com.sinoway.common.entity.HttpDataEntity;
import com.sinoway.common.exception.HttpException;

/**
 * 统一平台接口调用
 * @author miles
 *
 */
public interface IHttpProviderService {

	/**
	 * http 通用向核心发起请求交互方法,返回实体类HttpCommonEntity
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public HttpCommonEntity httpCommunicate(HttpCommonEntity entity) throws Exception;

	
	/**
	 * 适用于同步接口调用,生成默认List类型的HttpDataEntity
	 * @param jsonStr
	 * @return
	 * @throws HttpException 
	 */
	public List<HttpDataEntity> parseStringToListParam(String jsonStr) throws HttpException;
	
	
}
