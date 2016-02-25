package com.sinoway.common.service.server.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import com.sinoway.base.entity.BCfgdefFnttrnaddr;
import com.sinoway.common.constant.ServerConstant.ConnType;
import com.sinoway.common.constant.ServerConstant.ResSta;
import com.sinoway.common.constant.SystemConstant;
import com.sinoway.common.constant.SystemConstant.AddrProtocl;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.entity.GeneralMsgHeader;
import com.sinoway.common.service.parse.GeneralMsgHeaderService;
import com.sinoway.common.service.server.GeneralResService;
import com.sinoway.common.util.ByteUtil;
import com.sinoway.common.util.GUIDGenerator;
import com.sinoway.common.util.McpLogger;

/**
 * 通用socket响应服务
 * @author Liuzhen
 * @version 1.0
 * 2015-11-24
 */
public class GeneralSocketResService implements GeneralResService {

	private McpLogger logger = McpLogger.getLogger(getClass());
	private GeneralMsgHeaderService headerService = null;
	private BCfgdefFnttrnaddr entity = null;// 地址实体
	private String logId = "";
	@Override
	public GeneralBusEntity excuteToClient(GeneralBusEntity busEntity) throws Exception {
		try{
			logId = GUIDGenerator.generateId();// 日志ID
			logger.info(logId + "：开始向客户端响应结果，流水号:" + busEntity.getFrntJrn() + "......");
			if(entity == null){
				logger.error(logId + "：向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，响应地址为空");
				throw new Exception("向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，响应地址为空");
				// TODO
			}
			String protocol = entity.getProtocol();// 交易协议
			String conType = entity.getConectflg();// 长短连接标示
			String url = entity.getTrnaddr();// 交易地址
			if(url == null || "".equals(url)){
				logger.error(logId + "：向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，交易地址不能为空");
				throw new Exception("向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，交易地址不能为空");
				// TODO
			}
			if(!AddrProtocl.SOCKET.getValue().equals(protocol)){
				logger.error(logId + "：向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，响应协议与处理bean不匹配");
				throw new Exception("向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，响应协议与处理bean不匹配");
				// TODO
			}
			// 短连接
			if(ConnType.SHORT.getValue().equals(conType)){
				logger.info(logId + "：向客户端响应结果，流水号:" + busEntity.getFrntJrn() + "，使用Socket，短连接，客户端地址：" + url);
				String[] urls = url.split(":");
				if(urls.length != 2){
					logger.error(logId + "：向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，地址格式不合法");
					throw new Exception("向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，地址格式不合法");
					// TODO
				}
					
				String ip = urls[0];
				int port = 0;
				try{
					port = Integer.parseInt(urls[1]);
				}catch(Exception e){
					logger.error(logId + "：向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，响应端口号不合法",e);
					throw new Exception("向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，响应端口号不合法",e);
					// TODO
				}
				Socket socket = null;
				InputStream is = null;
				OutputStream os = null;
				try{
					logger.info(logId + "：向客户端响应结果，流水号:" + busEntity.getFrntJrn() + "，与客户端建立连接......");
					// 建立连接
					socket = new Socket(ip,port);
					is = socket.getInputStream();
					os = socket.getOutputStream();
					logger.info(logId + "：向客户端响应结果，流水号:" + busEntity.getFrntJrn() + "，与客户端建立连接成功");

					byte[] heads = null;
					try{
						heads = headerService.transHeaderToByte(busEntity.getHeader());
					}catch(Exception e){
						logger.error(logId + "：向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，报文头转换错误",e);
						throw new Exception("向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，报文头转换错误",e);
						// TODO
					}
					logger.info(logId + "：向客户端响应结果，流水号:" + busEntity.getFrntJrn() + "，向客户端发送报文头");
					os.write(heads);
					os.flush();
					logger.info(logId + "：向客户端响应结果，流水号:" + busEntity.getFrntJrn() + "，向客户端发送报文体");
					os.write(busEntity.getUpMsg());
					os.flush();
					// 接收报文头信息
					GeneralMsgHeader  header = null;
					
					try{
						logger.info(logId + "：向客户端响应结果，流水号:" + busEntity.getFrntJrn() + "，接收客户端返回报文头");
						header = headerService.reciveMsgHeader(is);
						logger.info(logId + "：向客户端响应结果，流水号:" + busEntity.getFrntJrn() + "，接收客户端返回报文头成功，内容：" + new String(header.getBytes()));
					}catch(Exception e){
						busEntity.setResSta(ResSta.F.getValue());
						busEntity.setErrMsg("向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，接收报文异常");
						logger.error(logId + "：向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，接收报文异常",e);
						throw new Exception("向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，接收报文头异常",e);
						// TODO
					}
					
					// 接收报文体
					int msgLen = header.getMsglen();
					if(msgLen == 0 ){
						busEntity.setResSta(ResSta.F.getValue());
						busEntity.setErrMsg("向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，响应报文体长度为0");
						logger.error(logId + "：向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，响应报文体长度为0");
						throw new Exception("向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，响应报文体长度为0");
						// TODO
					}
					byte[] msgBody =  null;
					try{
						
						logger.info(logId + "：向客户端响应结果，流水号:" + busEntity.getFrntJrn() + "，接收客户端返回报文体");
						msgBody = ByteUtil.readFixBytesFromInput(is, msgLen);
						logger.info(logId + "：向客户端响应结果，流水号:" + busEntity.getFrntJrn() + "，接收客户端返回报文体成功，内容：" + new String(msgBody,SystemConstant.DEFAULT_CHARSET));
						busEntity.setResSta(ResSta.S.getValue());
						busEntity.setDownMsg(msgBody);
					}catch(Exception e){
						busEntity.setResSta(ResSta.F.getValue());
						busEntity.setErrMsg("向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，接收报文异常");
						logger.error(logId + "：向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，接收报文异常");
						throw new Exception("向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，接收报文异常");
						// TODO
					}
				}catch(Exception e){
					busEntity.setResSta(ResSta.F.getValue());
					busEntity.setErrMsg("向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，响应异常");
					logger.error(logId + "：向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，响应异常",e);
					throw new Exception("向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，响应异常",e);
					// TODO
				}finally{
					if(is != null ){
						is.close();
						is = null;
					}
					if(os != null ){
						os.close();
						os = null;
					}
					if(socket != null ){
						socket.close();
						socket = null;
					}
					
				}
				
			// 长连接
			}else if(ConnType.LEN.getValue().equals(conType)){
				
			}else{
				logger.error(logId + "：向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，不能识别连接类型");
				throw new Exception("向客户端响应结果处理错误，流水号:" + busEntity.getFrntJrn() + "，不能识别连接类型");
				// TODO
			}
			
				
		}catch(Exception e){
			
		}
		
		return busEntity;
	}

	@Override
	public void initCfg(BCfgdefFnttrnaddr entity) {

		this.entity = entity;
	}

	/*
	 * GETTER   AND   SETTER
	 */
	public GeneralMsgHeaderService getHeaderService() {
		return headerService;
	}

	public void setHeaderService(GeneralMsgHeaderService headerService) {
		this.headerService = headerService;
	}
	
	

}
