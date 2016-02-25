package com.sinoway.fad.parser;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.sinoway.common.entity.GeneralRptMsgHeader;
import com.sinoway.common.entity.GeneralRptResMsg;
import com.sinoway.common.entity.GeneralRptResMsgBody;
import com.sinoway.fad.entity.WfDatFraudrpt;
import com.sinoway.fad.message.FraudRptMsg;
import com.sinoway.fad.message.FraudRptMsgBody;
import com.sinoway.fad.message.WFDatFraudMsg;
import com.sinoway.rpt.entity.WfDatCreditrpt;
import com.yzj.wf.common.WFLogger;

import net.sf.json.JSONObject;

/**
 * 拼装和解析反欺诈报文
 * @author miles
 *
 */
public class PackageAndAnalyzeMessage {

	private static final WFLogger logger =  WFLogger.getLogger(PackageAndAnalyzeMessage.class);
	/**
	 * 拼装反欺诈报文请求成功结果消息
	 * @return
	 */
	public static Object analyzeRptResMessage(String message){
		JSONObject object = JSONObject.fromObject(message);
		GeneralRptResMsg resMsg = new GeneralRptResMsg();
logger.debug("解析报文并返回报文对象");
		//解析报文头
		if(object.get("header") != null){
			JSONObject header = object.getJSONObject("header");
			GeneralRptMsgHeader rptheader = (GeneralRptMsgHeader) JSONObject.toBean(header, GeneralRptMsgHeader.class);
			resMsg.setHeader(rptheader);
		}
		//分析返回报文体
		if(object.get("body") != null){
			JSONObject header = object.getJSONObject("body");
			GeneralRptResMsgBody rptbody = (GeneralRptResMsgBody) JSONObject.toBean(header, GeneralRptResMsgBody.class);
			resMsg.setBody(rptbody);
		}
		
		return resMsg;
		
	}

	
	/**
	 * 拼装反欺诈查询请求报文
	 * @param creditrpt
	 * @param list
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static FraudRptMsg packageFraudReqJsonMessage(String  trncods, WfDatFraudrpt fraudrpt) throws IllegalAccessException, InvocationTargetException {
		FraudRptMsg msg = new FraudRptMsg();
		
		//拼装
		GeneralRptMsgHeader header = packageRptMsgHeader(fraudrpt);
		msg.setHeader(header);
		
		WFDatFraudMsg tradeinfo = new WFDatFraudMsg();
		BeanUtils.copyProperties(tradeinfo, fraudrpt);
		FraudRptMsgBody body = new FraudRptMsgBody(trncods, tradeinfo);
		msg.setBody(body);
		
		return msg;
	}
	
	
	/**
	 * 封装一个请求的报文信息
	 * @param creditrpt
	 * @return
	 */
	private static GeneralRptMsgHeader packageRptMsgHeader(WfDatCreditrpt creditrpt){
		GeneralRptMsgHeader header = new GeneralRptMsgHeader();
		header.setClntjrn(creditrpt.getRptid());
		header.setClnttrndte(creditrpt.getRptdte());
		header.setClnttrntime(creditrpt.getRpttim());
		header.setFnttrnjrn(creditrpt.getFntjrn());
		header.setOrgno(creditrpt.getCorgno());
		header.setSubusrid(creditrpt.getUsrid());
		header.setUsrid(creditrpt.getP_usrid());
		return header;
		
	}
}
