package com.sinoway.common.service.parse;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import com.sinoway.common.entity.GeneralMsgHeader;
import com.sinoway.common.exception.MsgHeaderParseException;

/**
 * 通用报文头解析接口
 * @author Liuzhen
 * @version 1.0
 * 2015-11-6
 */
public interface GeneralMsgHeaderService {

	/**
	 * 把接收到的报文头byte数组解析成实体
	 * @param bytes 报文头byte数组
	 * @return 解析完成的报文头实体
	 * @throws MsgHeaderParseException
	 */
	public GeneralMsgHeader  parseHeaderFromByte(byte[] bytes) throws MsgHeaderParseException;
	
	/**
	 * 从输入流中读取报文头信息
	 * @param in 输入流
	 * @return 解析好的报文头
	 * @throws MsgHeaderParseException
	 */
	public GeneralMsgHeader  reciveMsgHeader(InputStream in) throws MsgHeaderParseException;
	
	/**
	 * 将报文头转换成byte数组
	 * @param bytes
	 * @return
	 * @throws MsgHeaderParseException
	 */
	public byte[] transHeaderToByte(GeneralMsgHeader header)throws MsgHeaderParseException;
	
	/**
	 * 从request header中解析报文头
	 * @param request
	 * @return
	 */
	public GeneralMsgHeader reciveMsgHeader(HttpServletRequest request) throws MsgHeaderParseException;
}
