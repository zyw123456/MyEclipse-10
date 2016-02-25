package com.sinoway.common.service.server.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import com.sinoway.base.entity.BCfgdefFnttrnaddr;
import com.sinoway.base.entity.BCfgdefFnttrninfo;
import com.sinoway.common.constant.ServerConstant.ResSta;
import com.sinoway.common.constant.SystemConstant;
import com.sinoway.common.entity.GeneralBusEntity;
import com.sinoway.common.service.parse.GeneralMsgHeaderService;
import com.sinoway.common.service.server.GeneralResService;
import com.sinoway.common.util.GUIDGenerator;
import com.sinoway.common.util.McpLogger;
import com.sinoway.common.util.SystemCfgUtil;

/**
 * 通用http响应服务
 * @author Liuzhen
 * @version 1.0
 * 2016-1-19
 */
public class GeneralHttpResService implements GeneralResService {

	private McpLogger logger = McpLogger.getLogger(getClass());
	private GeneralMsgHeaderService headerService = null;
	private BCfgdefFnttrnaddr entity = null;// 地址实体
	private String logId = "";
	
	@Override
	public GeneralBusEntity excuteToClient(GeneralBusEntity busEntity)
			throws Exception {
		
		try{
			logId = GUIDGenerator.generateId();// 日志ID
			
			logger.info(logId + "：开始向客户端响应结果，流水号:" + busEntity.getFrntJrn() + "......");
			
			String url = entity.getTrnaddr();// 交易地址
			
			logger.info(logId + "：向客户端响应结果，流水号:" + busEntity.getFrntJrn() + "，使用HTTP协议，客户端地址：" + url);
			
			// 获取交易配置
			BCfgdefFnttrninfo trdCfg = SystemCfgUtil.getTradeCfgByCode(busEntity.getCoreHeader().getIntrncod());
			int tmnum = 1;
			try{
				tmnum = Integer.parseInt(trdCfg.getResovernum());
			}catch(Exception e){
				
			}
			// 默认超时时间10秒
			int tmout = 10;
			try{
				tmout = Integer.parseInt(trdCfg.getResover());
			}catch(Exception e){
				
			}
			
			try{
				StringBuffer sbUrl = new StringBuffer(url);
				sbUrl.append("?chnlcod=").append(busEntity.getHeader().getChnlcod())
				.append("&prdcod=").append(busEntity.getHeader().getPrdcod())
				.append("&trncod=").append(busEntity.getHeader().getOuttrncod())
				.append("&isbatch=").append(busEntity.getHeader().getIsbatch())
				.append("&msgtype=").append(busEntity.getHeader().getMsgtype());
				URL connUrl = new URL(sbUrl.toString());
				HttpURLConnection conn = (HttpURLConnection)connUrl.openConnection();
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setUseCaches(false);
				conn.setRequestMethod("POST");
				conn.setConnectTimeout(10 * 1000);
				conn.setReadTimeout(tmout*1000);
				conn.setRequestProperty("Charset", SystemConstant.DEFAULT_CHARSET);
				conn.setRequestProperty("Content-Length", busEntity.getUpMsg().length + "");
				conn.setRequestProperty("Accept-Language", "en-US,en;q=0.8");  
		        conn.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");  
		        conn.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");  
		        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
		        conn.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");  
		        conn.setRequestProperty("checkcod", busEntity.getHeader().getCheckcod());  
				conn.connect();
				
				OutputStream os = conn.getOutputStream();
				byte[] upmsg=busEntity.getUpMsg();
				os.write(upmsg);
				os.flush();
				int resCode = conn.getResponseCode(); 
				if(resCode == 200){
					logger.info(logId + "：向客户端响应结果成功，开始接收客户端返回信息，流水号:" + busEntity.getFrntJrn() + "......");
					ByteArrayOutputStream bs = new ByteArrayOutputStream();
					InputStream in = conn.getInputStream();
					byte[] buffer = new byte[1024];
					int len = 0;
					while((len = in.read(buffer)) != -1){
						bs.write(buffer, 0, len);
					}
					byte[] downMsg = bs.toByteArray();
					logger.info(logId + "：向客户端响应结果成功，接收客户端返回信息完成，内容：" + new String(downMsg,SystemConstant.DEFAULT_CHARSET) + ",流水号:" + busEntity.getFrntJrn() + "......");
					
					busEntity.setDownMsg(downMsg);
					busEntity.setResSta(ResSta.S.getValue());
					String chkCode = conn.getHeaderField("");
//					
//					if(SystemOperateUtil.isMsgTampered(busEntity.getHeader().getChnlcod(), busEntity.getDesKeyInf().getKey(), downMsg, chkCode);){
//						
//					}
					
				}else{
					logger.error(logId + ":向客户端响应信息失败，响应异常，响应码：" + resCode + ",流水号：" + busEntity.getFrntJrn());
					busEntity.setResSta(ResSta.F.getValue());
					busEntity.setErrMsg("响应码：" + resCode);
					throw new Exception();
				}
				

				
			}catch( MalformedURLException e){
				logger.error(logId + ":向客户端响应信息失败，地址格式不正确：" + url + ",流水号：" + busEntity.getFrntJrn());
				busEntity.setResSta(ResSta.F.getValue());
				busEntity.setErrMsg(e.getMessage());
				e.printStackTrace();
				throw e;
			}catch(SocketTimeoutException e){
				logger.error(logId + ":向客户端响应信息失败，超时：" + url + ",流水号：" + busEntity.getFrntJrn());
				busEntity.setResSta(ResSta.F.getValue());
				busEntity.setErrMsg(e.getMessage());
				e.printStackTrace();
				throw e;
			}catch(IOException e){
				logger.error(logId + ":向客户端响应信息失败，I/O异常：" + url + ",流水号：" + busEntity.getFrntJrn());
				busEntity.setResSta(ResSta.F.getValue());
				busEntity.setErrMsg(e.getMessage());
				e.printStackTrace();
				throw e;
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		
		return busEntity;
	}

	@Override
	public void initCfg(BCfgdefFnttrnaddr entity) {
		this.entity = entity;
	}

	/*
	 *  GETTER  AND  SETTER
	 */
	public GeneralMsgHeaderService getHeaderService() {
		return headerService;
	}

	public void setHeaderService(GeneralMsgHeaderService headerService) {
		this.headerService = headerService;
	}

}
