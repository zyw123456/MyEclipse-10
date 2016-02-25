package com.sinoway.common.service.cfg.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinoway.base.entity.BCfgdefPrdinfo;
import com.sinoway.base.entity.BCfgrefCompusrprd;
import com.sinoway.base.entity.BCfgrefPrddetil;
import com.sinoway.base.service.BCfgdefPrdinfoService;
import com.sinoway.base.service.BCfgrefCompusrprdService;
import com.sinoway.base.service.BCfgrefPrddetilService;
import com.sinoway.common.action.CommonConfigAction;
import com.sinoway.common.constant.Constant.HttpStatus;
import com.sinoway.common.constant.Constant.MsgCode;
import com.sinoway.common.service.cfg.CommonProductService;
import com.sinoway.common.util.DateUtil;
import com.sinoway.common.util.GUIDGenerator;
import com.sinoway.common.util.JRNGenerator;
@Transactional(propagation=Propagation.REQUIRED,rollbackFor =Exception.class)
@Service("commonDefProductService")
public class CommonDefProductService  implements CommonProductService{
	Logger logger = LogManager.getLogger(CommonDefProductService.class.getName());
	@Autowired
	private BCfgdefPrdinfoService bCfgdefPrdinfoService;
	
	@Autowired
	private BCfgrefPrddetilService bCfgrefPrddetilService;
	
	@Autowired
	private BCfgrefCompusrprdService bCfgrefCompusrprdService;
	
	public Object addprdInfo(Object prdInfo) throws Exception {

		JSONObject jo = (JSONObject) prdInfo;
		JSONObject backobj  =  new JSONObject();
		StringBuffer msg = new StringBuffer();
	try{
		// 产品策略新增
		 //对字段长度进行校验
	   if(jo.get("appcod").toString().length() > 3){
		 msg.append("所属应用变码超长\n");  
	   }
	   if(jo.get("prdnam").toString().length() >64){
		 msg.append("产品名称超长\n");  
	   }
	   logger.info("产品策略新增报文>> "+jo.toJSONString() );
		BCfgdefPrdinfo bCfgdefPrdinfo = new BCfgdefPrdinfo();
		bCfgdefPrdinfo.setAppcod(jo.get("appcod").toString() ==null ? "" : jo.get("appcod").toString());
		bCfgdefPrdinfo.setPrdnam(jo.get("prdnam").toString() ==null ? "" : jo.get("prdnam").toString());
		bCfgdefPrdinfo.setCretday(DateUtil.dateToString(new Date(),
				"yyyyMMdd"));
		bCfgdefPrdinfo.setId(GUIDGenerator.generateId());
		// 生成产品码
		bCfgdefPrdinfo.setPrdcod(JRNGenerator.generatePrdCod());
		// 校验产品码是否已经存在
		List list = this.searchProductByPrdcod(bCfgdefPrdinfo);

		if (list.size() > 0) {
			backobj.put("states", HttpStatus.STATIS_FAIL.getCode()); // 状态 0 -失败 1-表示成功
			msg.append("产品编码已存在\n");
			backobj.put("msgcode", "001"); // 报文类型
			backobj.put("operator", jo.get("operator")); // 操作人
			backobj.put("prdcod", bCfgdefPrdinfo.getPrdcod()); // 产品码
		} else {
			// 判断如果是天警云 按复杂报文进行处理
			if (jo.get("appcod").toString().equals(MsgCode.MSGCODE_UPDATEPRD.getValue())) {
				JSONArray j = (JSONArray) JSONArray.parse(jo.get(
						"trncods").toString());
				for (int i = 0; i < j.size(); i++) {
					JSONObject trancodobj = j.getJSONObject(i);
					;
					BCfgrefPrddetil detail = new BCfgrefPrddetil();
					detail.setId(GUIDGenerator.generateId());
					detail.setDayexpcnt(trancodobj
							.getString("dayexpcnt"));
					detail.setMon3expcnt(trancodobj
							.getString("mon3expcnt"));
					detail.setMonexpcnt(trancodobj
							.getString("monexpcnt"));
					detail.setPrdcod(bCfgdefPrdinfo.getPrdcod());
					detail.setSta(HttpStatus.STATIS_SUCCESS.getCode());
					detail.setTrncod(trancodobj.getString("trncod"));
					detail.setPrdnam(jo.get("prdnam").toString());
					Object obj = this.addPrddetil(detail);
				}
			} else {
				String trancods[] = jo.get("trncods").toString()
						.split(",");

				for (int i = 0; i < trancods.length; i++) {
					BCfgrefPrddetil detail = new BCfgrefPrddetil();
					detail.setId(GUIDGenerator.generateId());
					detail.setPrdcod(bCfgdefPrdinfo.getPrdcod());
					detail.setSta(HttpStatus.STATIS_SUCCESS.getCode());
					detail.setTrncod(trancods[i]);
					detail.setPrdnam(jo.get("prdnam").toString());
					Object obj = this.addPrddetil(detail);
				}
			}
			if (!jo.get("usrids").toString().equals("")
					|| jo.get("usrids").toString() != null) {
				String usrids[] = ((jo.get("usrids").toString() + "," + jo
						.get("operator").toString()).split(",")); // 子账户id集合
				for (int i = 0; i < usrids.length; i++) {
					BCfgrefCompusrprd prd = new BCfgrefCompusrprd();
					prd.setId(GUIDGenerator.generateId());
					prd.setPrdcod(bCfgdefPrdinfo.getPrdcod());
					prd.setSta(HttpStatus.STATIS_SUCCESS.getCode());
					prd.setUsrid(usrids[i]);
					Object obj  = bCfgrefCompusrprdService.insert("BCfgrefCompusrprd.insertByEntity",prd);
				}
			}
			Object obj = bCfgdefPrdinfoService.insert("BCfgdefPrdinfo.insertByEntity", bCfgdefPrdinfo);

			if (obj.toString().equals(HttpStatus.STATIS_SUCCESS.getCode())) {
				msg.append( "返回成功报文\n");
				backobj.put("states", HttpStatus.STATIS_SUCCESS.getCode()); // 状态 0 -失败 1-表示成功
				backobj.put("result", msg ); // 返回的描述
			} else {
				msg.append( "返回失败报文\n");
				backobj.put("states", HttpStatus.STATIS_FAIL.getCode()); // 状态 0 -失败 1-表示成功
				backobj.put("result", msg
						); // 返回的描述
			}
			backobj.put("msgcode", MsgCode.MSGCODE_ADDPRD.getValue()); // 报文类型
			backobj.put("operator", jo.get("operator")); // 操作人
			backobj.put("prdcod", bCfgdefPrdinfo.getPrdcod()); // 产品码
		}
		backobj.put("result", msg.toString()); // 返回的描述
	   	}catch(Exception e){
	   	 logger.error("产品策略新增报文出现异常>> "+jo.toJSONString() );
	   		e.printStackTrace();
	   		throw e;
	   	}
		return backobj;
	}

	public Object editprdInfo(Object prdInfo) throws Exception {
		
		JSONObject jo = (JSONObject) prdInfo;
		JSONObject backobj  =  new JSONObject();
		StringBuffer msg = new StringBuffer();
		logger.info("产品策略修改报文>> "+jo.toJSONString() );
	try{
		 if(jo.get("appcod").toString().length() > 3){
			 msg.append("所属应用变码超长\n");  
		  }
		   if(jo.get("prdnam").toString().length() >64){
			 msg.append("产品名称超长\n");  
		  }
		   if(jo.get("prdcod").toString().length() >8){
			 msg.append("产品编码超长\n");  
		   }
		BCfgdefPrdinfo bCfgdefPrdinfo = new BCfgdefPrdinfo();
		bCfgdefPrdinfo.setAppcod(jo.get("appcod").toString()== null ? "" : jo.get("appcod").toString());
		bCfgdefPrdinfo.setPrdnam(jo.get("prdnam").toString()== null ? "" : jo.get("prdnam").toString());
		bCfgdefPrdinfo.setCretday(DateUtil.dateToString(new Date(),
				"yyyyMMdd"));
		bCfgdefPrdinfo.setPrdcod(jo.get("prdcod").toString() == null ? "" : jo.get("prdcod").toString());
		Object obj = bCfgdefPrdinfoService.update("BCfgdefPrdinfo.updateByEntity", bCfgdefPrdinfo);

		// 判断如果是天警云 按复杂报文进行处理
		if (jo.get("appcod").toString().equals(MsgCode.MSGCODE_UPDATEPRD.getValue())) {
			JSONArray json = (JSONArray) JSONArray.parse(jo.get(
					"trncods").toString());
			for (int i = 0; i < json.size(); i++) {
				JSONObject trancodobj = json.getJSONObject(i);
				BCfgrefPrddetil detail = new BCfgrefPrddetil();
				detail.setId(GUIDGenerator.generateId());
				detail.setDayexpcnt(trancodobj.getString("dayexpcnt"));
				detail.setMon3expcnt(trancodobj.getString("mon3expcnt"));
				detail.setMonexpcnt(trancodobj.getString("monexpcnt"));
				detail.setPrdcod(bCfgdefPrdinfo.getPrdcod());
				detail.setSta(HttpStatus.STATIS_SUCCESS.getCode());
				detail.setTrncod(trancodobj.getString("trncod"));
				detail.setPrdnam(jo.get("prdnam").toString());
			Object	objop = this.editPrddetil(detail);
			}
		} else {

			String trancods[] = jo.get("trncods").toString().split(",");
			for (int i = 0; i < trancods.length; i++) {
				BCfgrefPrddetil detail = new BCfgrefPrddetil();
				detail.setId(GUIDGenerator.generateId());
				detail.setPrdcod(bCfgdefPrdinfo.getPrdcod());
				detail.setSta(HttpStatus.STATIS_SUCCESS.getCode());
				detail.setTrncod(trancods[i]);
				detail.setPrdnam(jo.get("prdnam").toString());
			Object	objop = this.editPrddetil(detail);
			}
		}

		backobj.put("msgcode", MsgCode.MSGCODE_UPDATEPRD.getValue()); // 报文类型
		backobj.put("operator", jo.get("operator")); // 操作人
		backobj.put("prdcod", jo.get("prdcod").toString() == null ? "" :jo.get("prdcod").toString()); // 产品码

		if (obj.toString().equals(HttpStatus.STATIS_SUCCESS.getCode())) {
			backobj.put("states", HttpStatus.STATIS_SUCCESS.getCode()); // 状态 0 -失败 1-表示成功
			 msg.append("返回成功报文"); // 返回的描述
		} else {
			backobj.put("states", HttpStatus.STATIS_FAIL.getCode()); // 状态 0 -失败 1-表示成功
			 msg.append("返回失败报文"); // 返回的描述
		}
		backobj.put("result", msg.toString()); // 返回的描述
	 }catch(Exception e){
		 logger.error("产品策略修改出现异常>> "+jo.toJSONString() );
		 e.printStackTrace();
		 throw e;
	 }
		
		return backobj;
	}

	@Override
	public Object delprdInfo(Object prdInfo) throws Exception {
		JSONObject jo = (JSONObject) prdInfo;
		JSONObject backobj  =  new JSONObject();
		StringBuffer msg = new StringBuffer();
		 if(jo.get("prdcod").toString().length() >8){
			 msg.append("产品编码超长\n");  
		}
		 logger.info("删除策略报文" + jo.toJSONString());
	 try{
		BCfgdefPrdinfo bCfgdefPrdinfo = new BCfgdefPrdinfo();
		// 根据产品码去删除产品表的记录
		bCfgdefPrdinfo.setPrdcod(jo.get("prdcod").toString());
		bCfgdefPrdinfo.setSta(HttpStatus.STATIS_FAIL.getCode());
		Object obj = bCfgdefPrdinfoService.update("BCfgdefPrdinfo.updateByEntity", bCfgdefPrdinfo);

		// 判断如果是天警云 按复杂报文进行处理
		if (jo.get("appcod").toString().equals(MsgCode.MSGCODE_DELPRD.getValue())) {

			BCfgrefPrddetil detail = new BCfgrefPrddetil();
			detail.setSta(HttpStatus.STATIS_FAIL.getCode());
			detail.setPrdcod(jo.get("prdcod").toString());
			Object objop = this.deletePrddetil(detail);

		} else {

			BCfgrefPrddetil detail = new BCfgrefPrddetil();
			detail.setSta(HttpStatus.STATIS_FAIL.getCode());
			detail.setPrdcod(jo.get("prdcod").toString());
			Object objop = this.deletePrddetil(detail);
		}
		backobj.put("msgcode",MsgCode.MSGCODE_DELPRD.getValue()); // 报文类型
		backobj.put("operator", jo.get("operator")); // 操作人
		backobj.put("prdcod", MsgCode.MSGCODE_DELPRD.getValue()); // 产品码

		if (obj.toString().equals(HttpStatus.STATIS_SUCCESS.getCode())) {
			backobj.put("states", HttpStatus.STATIS_SUCCESS.getCode()); // 状态 0 -失败 1-表示成功
			 msg.append("返回策略删除成功报文"); // 返回的描述
		} else {
			backobj.put("states", HttpStatus.STATIS_FAIL.getCode()); // 状态 0 -失败 1-表示成功
			 msg.append("返回策略删除失败报文"); // 返回的描述
		}
		backobj.put("result", msg.toString()); // 返回的描述
	 }catch(Exception e){
		 logger.error("产品策略删除出现异常>> "+jo.toJSONString());
		 e.printStackTrace();
		 throw e;
		
	}
		return backobj;
	}

	@Override
	public Object addPrddetil(Object bCfgdefPrdinfo) throws Exception {
		return bCfgrefPrddetilService.insert("BCfgrefPrddetil.insertByEntity", bCfgdefPrdinfo);
	}

	@Override
	public Object editPrddetil(Object bCfgdefPrdinfo) throws Exception {
		return bCfgrefPrddetilService.update("BCfgrefPrddetil.updateByEntity", bCfgdefPrdinfo);
	}
	
	public Object deletePrddetil(Object bCfgdefPrdinfo) throws Exception {
		return bCfgrefPrddetilService.update("BCfgrefPrddetil.deleteByEntityForPrdcod", bCfgdefPrdinfo);
	}

	public List searchProductByPrdcod(BCfgdefPrdinfo bCfgdefPrdinfo) {
		return bCfgdefPrdinfoService.find(bCfgdefPrdinfo);
	}



}
