package com.sinoway.mcp.service.strade.simple;

import com.alibaba.fastjson.JSONObject;
import com.sinoway.base.entity.BCfgdefFnttrnaddr;
import com.sinoway.common.constant.ServerConstant.ResSta;
import com.sinoway.common.constant.SystemConstant.STradeResFlg;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.exception.STradeProcessException;
import com.sinoway.common.service.server.GeneralSTradeProcService;

public class SimpleStradeService implements  GeneralSTradeProcService {
	@Override
	public GeneralBusEntity busLaunch(GeneralBusEntity entity)
			throws STradeProcessException {
		
		
		try {
			JSONObject json = new JSONObject();
			json.put("a", "1");
			json.put("b", "2");
			entity.setCoreMsg(json.toJSONString().getBytes("utf-8"));
			entity.setUpMsg("test up Message".getBytes("utf-8"));
			entity.setDownMsg("test down Message".getBytes("utf-8"));
			entity.setResSta(ResSta.S.getValue());
			entity.setErrMsg("");
			entity.getCoreHeader().setStatus(ResSta.S.getValue());
			entity.getCoreHeader().setResult("");
			entity.getStrdflw().setRespflg(STradeResFlg.SUCCESS.getValue());
			
		} catch (Exception e) {
			
		}
		return entity;
	}

	@Override
	public GeneralBusEntity resRecive(GeneralBusEntity entity)
			throws STradeProcessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralBusEntity getRes(GeneralBusEntity entity)
			throws STradeProcessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initCfg(BCfgdefFnttrnaddr entity) {
		// TODO Auto-generated method stub
		
	}

}
