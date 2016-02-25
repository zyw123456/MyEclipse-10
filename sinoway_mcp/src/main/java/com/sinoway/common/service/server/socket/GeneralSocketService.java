package com.sinoway.common.service.server.socket;

import com.sinoway.base.entity.BCfgdefFntsrvport;
import com.yzj.wf.com.ibank.common.server.IBankControl;

/**
 * 通用socket控制服务接口
 * @author Liuzhen
 * @version 1.0
 * 2015-11-19
 */
public interface  GeneralSocketService extends IBankControl  {

	/**
	 * 初始化端口配置信息
	 * @param entity
	 */
	public void initCfg(BCfgdefFntsrvport entity);
}
