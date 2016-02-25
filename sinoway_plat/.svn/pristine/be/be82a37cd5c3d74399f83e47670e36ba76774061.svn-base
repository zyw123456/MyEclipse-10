package com.sinoway.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.sinoway.common.entity.HttpCommonEntity;
import com.sinoway.common.entity.HttpDataEntity;
import com.sinoway.common.util.Constant.HttpParamType;
import com.yzj.pam.common.ParamException;

/**
 * 提供向核心业务系统的发起请求类
 * 
 * @author miles
 *
 */
public class HttpParamUtil {

	/**
	 * 生成使用于报告的请求消息参数
	 * @param prdcod 产品编码 
	 * @param trncod 交易码
	 * @param isbatch 是否批量
	 * @param msgtype 消息类型
	 * @param reqaddr 请求消息地址
	 * @return
	 * @throws Exception
	 */
	public static HttpCommonEntity generateRptRequest(String prdcod,String trncod, String isbatch, String msgtype, String tradeinf) throws Exception{
		HttpCommonEntity entity = new HttpCommonEntity(Constant.getMessageUrl());
		List<HttpDataEntity> params = generateRptHttpReqeustParams(prdcod, trncod, isbatch, msgtype, tradeinf);
		entity.setParams(params);
		return entity;
	}
	
	


	/**
	 *  封装业务参数返回MAP
	 * @param prdcod
	 * @param trncod
	 * @param isbatch
	 * @param reqadrr
	 * @param msgtype
	 * @return
	 * @throws Exception 
	 */
	public static Map<String, Object> generateSendParamMap(String prdcod, String trncod, String isbatch,
			 String msgtype, String tradeinf) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("chnlcod", Constant.getChnlcode()); //渠道号
		//当isbatch值为1时,产品码和交易码都可以为空,当isbatch值为0时,产品码和交易码只能有一个为空
		if(StringUtils.isNotBlank(isbatch)){
			if("0".equals(isbatch)){
				if(StringUtils.isBlank(prdcod) && StringUtils.isBlank(trncod)){
					throw new ParamException("参数传递不符合要求: 产品码和交易码至少有一个不为空");
				}
			}
			paramMap.put("isbatch", isbatch);//isbatch
		}
		
		if(StringUtils.isNotBlank(prdcod)){//产品编码
			paramMap.put("prdcod", prdcod);
		}
		
		if(StringUtils.isNotBlank(trncod)){//交易码
			paramMap.put("trncod", trncod);
		}
		if(StringUtils.isNotBlank(msgtype)){//
			paramMap.put("msgtype", msgtype);
		}
		if(StringUtils.isNotBlank(tradeinf)){
			paramMap.put("tradeinf", tradeinf);
			String checkCode = generateCheckCodeByMD5(Constant.getChnlcode(),Constant.getSecretKey(), tradeinf);
			paramMap.put("checkcod", checkCode);
		}
		return paramMap;
	}
	
	
	
	
	
	
	/**
	 * 拼装返回HttpDataEntity 参数对象的list
	 * @param prdcod 产品码
	 * @param trncod 交易码
	 * @param isbatch  是否批量
	 * @param msgtype 消息类型
	 * @param tradeinfo 流
	 * @return
	 * @throws IOException
	 * @throws ParamException
	 */
	private static List<HttpDataEntity> generateRptHttpReqeustParams(String prdcod,String trncod, String isbatch, String msgtype, String tradeinfo) throws IOException, ParamException{
		List<HttpDataEntity> list = new ArrayList<HttpDataEntity>();
		
		list.add(new HttpDataEntity("chnlcod", Constant.getChnlcode(), HttpParamType.PARAMETER_TYPE_PARAMETERS.getCode()));//渠道号
		//当isbatch值为1时,产品码和交易码都可以为空,当isbatch值为0时,产品码和交易码只能有一个为空
		if(StringUtils.isNotBlank(isbatch)){
			if("0".equals(isbatch)){
				if(StringUtils.isBlank(prdcod) && StringUtils.isBlank(trncod)){
					throw new ParamException("参数传递不符合要求: 产品码和交易码至少有一个不为空");
				}
			}
			list.add(new HttpDataEntity("isbatch", isbatch, HttpParamType.PARAMETER_TYPE_PARAMETERS.getCode()));//isbatch
		}
		
		if(StringUtils.isNotBlank(prdcod)){//产品编码
			list.add(new HttpDataEntity("prdcod", prdcod, HttpParamType.PARAMETER_TYPE_PARAMETERS.getCode()));//isbatch
		}
		
		if(StringUtils.isNotBlank(trncod)){//交易码
			list.add(new HttpDataEntity("trncod", trncod, HttpParamType.PARAMETER_TYPE_PARAMETERS.getCode()));
		}
		if(StringUtils.isNotBlank(msgtype)){//消息类型
			list.add(new HttpDataEntity("msgtype", msgtype, HttpParamType.PARAMETER_TYPE_PARAMETERS.getCode()));
		}
		if(StringUtils.isNotBlank(tradeinfo)){
			list.add(new HttpDataEntity("tradeinf", tradeinfo, HttpParamType.PARAMTER_TYPE_STREAM.getCode()));
			String checkCode = generateCheckCodeByMD5(Constant.getChnlcode(),Constant.getSecretKey(), tradeinfo);
			list.add(new HttpDataEntity("checkcod", checkCode, HttpParamType.PARAMETER_TYPE_HEADER.getCode()));
		}
		return list;
	}
	
	
	
	
	/**
	 * 生成规则：渠道码+核心分配的密钥+tradeinf做MD5加密
	 * @param chnlcod 渠道码
	 * @param privateKey 核心分配密钥
	 * @param trdeinfo 字符串内容
	 * @return MD5校验值
	 * @throws IOException
	 */
	public static String generateCheckCodeByMD5(String chnlcod, String privateKey, String trdeinfo) throws IOException{
		StringBuffer str = new StringBuffer();
		str.append(chnlcod);
		str.append(privateKey);
		str.append(trdeinfo);
		return StringUtil.hash(str.toString().getBytes("utf-8"), "MD5");
	}
	
	
	
}
