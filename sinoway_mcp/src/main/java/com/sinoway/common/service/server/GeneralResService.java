package com.sinoway.common.service.server;

import com.sinoway.base.entity.BCfgdefFnttrnaddr;
import com.sinoway.common.entity.GeneralBusEntity;

/**
 * 通用响应服务
 * @author Liuzhen
 * @version 1.0
 * 2015-11-24
 */
public interface GeneralResService {

	/**
	 * 向客户端响应结果
	 * @param entity
	 * @return
	 */
	public GeneralBusEntity excuteToClient(GeneralBusEntity entity) throws Exception;
	
	/**
	 * 初始化
	 * @param entity
	 */
	public void initCfg(BCfgdefFnttrnaddr entity);
}
