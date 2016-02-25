package com.sinoway.common.service.parse;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import com.sinoway.common.entity.GeneralMsgHeader;
import com.sinoway.common.exception.ByteOperatException;
import com.sinoway.common.exception.MsgHeaderParseException;
import com.sinoway.common.util.ByteUtil;

/**
 * 通用报文头解析 
 * @author Liuzhen
 * 2015-11-6
 *
 */
public class GeneralMsgHeaderServiceImpl implements GeneralMsgHeaderService {
	
	private  int msgLen; // 报文头长度

	@Override
	public GeneralMsgHeader parseHeaderFromByte(byte[] bytes)
			throws MsgHeaderParseException {
		if(bytes == null || bytes.length == 0)
			throw new MsgHeaderParseException("通用报文头解析失败： byte数组不能为空");
		int len = bytes.length;
		if(len != msgLen)
			throw new MsgHeaderParseException("通用报文头解析失败： byte数组长度不匹配，长度：" + msgLen + "，实际长度：" +  len);
		
		GeneralMsgHeader header = new GeneralMsgHeader();
		
		header.setBytes(bytes);
		
		String headerStr = new String(bytes);
	    // 报文体长度
		String bodyLenStr = headerStr.substring(0,8);
		int bodyLen = 0;
		try{
			bodyLen = Integer.parseInt(bodyLenStr);
			if(bodyLen == 0)
				throw new MsgHeaderParseException("通用报文头解析失败：报文体长度不能为0");
			
			header.setMsglen(bodyLen);
		}catch(Exception e){
			e.printStackTrace();
			throw new MsgHeaderParseException("通用报文头解析失败：获取报文体长度字段失败");
		}
		
		// 交易码
		String tradeCode = headerStr.substring(8,16);
		// 产品码
		String prdCode =  headerStr.substring(16,24);
		
		header.setOuttrncod(tradeCode);
		header.setPrdcod(prdCode);
		String isbatch = headerStr.substring(32,33);
		header.setIsbatch(isbatch);
		String msgtype = headerStr.substring(33,34);
		header.setMsgtype(msgtype);
		// 渠道号
		String chnlCode = headerStr.substring(24,32);
		header.setChnlcod(chnlCode);
		// 校验码
		String chCode = headerStr.substring(34,66);
		header.setCheckcod(chCode);
			
		return header;
	}

	@Override
	public GeneralMsgHeader reciveMsgHeader(InputStream in)
			throws MsgHeaderParseException {
		
		try {
			
			byte[] headerBytes = ByteUtil.readFixBytesFromInput(in, msgLen);
		
			return parseHeaderFromByte(headerBytes);
		
		} catch (ByteOperatException e) {
			e.printStackTrace();
			throw new MsgHeaderParseException("通用报文头解析失败：读取字节异常",e);
		} catch(Exception e){
			e.printStackTrace();
			throw new MsgHeaderParseException("通用报文头解析失败：未知异常",e);
		} 
	}
	
	@Override
	public byte[] transHeaderToByte(GeneralMsgHeader header)
			throws MsgHeaderParseException {
		if(header == null )
			throw new MsgHeaderParseException("报文头转换byte数组失败：header不能为空");
		
		byte[] bytes = new byte[msgLen];
		String bodyLen = header.getMsglen() + "";
		int len = 8 - bodyLen.length();
		if(len < 0 ){
			throw new MsgHeaderParseException("报文头转换byte数组失败：报文体长度不能超过8位");
		}
		for(int i = 0; i < len ; i++){
			bodyLen = "0" + bodyLen;
		}
		System.arraycopy(bodyLen.getBytes(), 0, bytes, 0, 8);
		String tradeCode = header.getOuttrncod();
		if(tradeCode.length() != 8)
			throw new MsgHeaderParseException("报文头转换byte数组失败：交易编码长度不为8");
		
		System.arraycopy(tradeCode.getBytes(), 0, bytes, 8, 8);
		String prdCode = header.getPrdcod();
		if(prdCode.length() != 8)
			throw new MsgHeaderParseException("报文头转换byte数组失败：产品编码长度不为8");
		
		System.arraycopy(prdCode.getBytes(), 0, bytes, 16, 8);
		String chnlCode = header.getChnlcod();
		
		if(chnlCode.length() != 8)
			throw new MsgHeaderParseException("报文头转换byte数组失败：渠道编码长度不为8");
		
		System.arraycopy(chnlCode.getBytes(), 0, bytes, 24, 8);

		String isbatch = header.getIsbatch();
		
		if(isbatch.length() != 1)
			throw new MsgHeaderParseException("报文头转换byte数组失败：isbatch长度不为1");
		
		System.arraycopy(isbatch.getBytes(), 0, bytes, 32, 1);

		String msgType = header.getMsgtype();
		
		if(msgType.length() != 1)
			throw new MsgHeaderParseException("报文头转换byte数组失败：msgType长度不为1");
		
		System.arraycopy(msgType.getBytes(), 0, bytes, 33, 1);

		String chkCode = header.getCheckcod();
		
		if(chkCode.length() != 32)
			throw new MsgHeaderParseException("报文头转换byte数组失败：校验码长度不为32");
		
		System.arraycopy(chkCode.getBytes(), 0, bytes, 34, 32);

		return bytes;
	}
	
	@Override
	public GeneralMsgHeader reciveMsgHeader(HttpServletRequest request) throws MsgHeaderParseException {
		GeneralMsgHeader generalMsgHeader = new GeneralMsgHeader();
		if(null == request)
			throw new MsgHeaderParseException("request不能为null");
		String chnlcod = request.getParameter("chnlcod");
		
		if(null == chnlcod || "".equals(chnlcod))
			throw new MsgHeaderParseException("从header中获取参数失败：渠道码不能为空");
		
		if(chnlcod.length()!=8)
			throw new MsgHeaderParseException("从header中获取参数失败：渠道码长度不为8");
		
		generalMsgHeader.setChnlcod(chnlcod);
		
		String isbatch = request.getParameter("isbatch");
		
		if(null  == isbatch)
			throw new MsgHeaderParseException("从header中获取参数失败：isbatch不能为空");
		
		if(isbatch.length() != 1)
			throw new MsgHeaderParseException("从header中获取参数失败：isbatch长度不为1");
		
		generalMsgHeader.setIsbatch(isbatch);
		
		String msgtype = request.getParameter("msgtype");
		
		if(null == msgtype)
			throw new MsgHeaderParseException("从header中获取参数失败：msgtype不能为空");
		
		if(msgtype.length() != 1)
			throw new MsgHeaderParseException("从header中获取参数失败：msgtype长度不为1");
		
		generalMsgHeader.setMsgtype(msgtype);
		
		String checkcod = request.getHeader("checkcod");
		
		if(null == checkcod)
			throw new MsgHeaderParseException("从header中获取参数失败：checkcod不能为空");
		
		if(checkcod.length() !=32)
			throw new MsgHeaderParseException("从header中获取参数失败：checkcod长度不为32位");
		
		generalMsgHeader.setCheckcod(checkcod);
		//当isbatch不为1时 必须的参数判断
			String prdcod = request.getParameter("prdcod");
//			
//			if(null == prdcod)
//				throw new MsgHeaderParseException("从header中获取参数失败：prdcod不能为空");
//			
//			if(prdcod.length() !=8)
//				throw new MsgHeaderParseException("从header中获取参数失败：prdcod长度不为8");
			if(prdcod != null && !"".equals(prdcod))
				generalMsgHeader.setPrdcod(prdcod);
			
			String trncod = request.getParameter("trncod");
//			
//			if(null == trncod)
//				throw new MsgHeaderParseException("从header中获取参数失败：trncod不能为空");
//			
//			if(trncod.length() != 8)
//				throw new MsgHeaderParseException("从header中获取参数失败：trncod长度不为8");
			if(trncod != null && !"".equals(trncod))
				generalMsgHeader.setOuttrncod(trncod);
		
		return generalMsgHeader;
	}

	/*
	 *  GETTER   AND   SETTER
	 */

	public int getMsgLen() {
		return msgLen;
	}

	public void setMsgLen(int msgLen) {
		this.msgLen = msgLen;
	}

}
