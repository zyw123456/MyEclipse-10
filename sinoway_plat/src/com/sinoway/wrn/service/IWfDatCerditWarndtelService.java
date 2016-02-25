package com.sinoway.wrn.service;

import java.util.List;
import java.util.Map;

public interface IWfDatCerditWarndtelService {
	/**
	 * 多条件查询征信预警产品明细（天警云首页显示）
	 * @param queryMap
	 * @return
	 */
	 public List findAllWfDatCerditWarndtel(Map queryMap);

}
