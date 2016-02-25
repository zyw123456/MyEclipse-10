package com.sinoway.common.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sinoway.common.constant.ServerConstant.ResSta;
import com.sinoway.mcp.entity.FDatMetatrnflw;
import com.sinoway.mcp.entity.FDatPrdinfoflw;
import com.sinoway.mcp.entity.FDatPrdtrnflw;

/**
 * 通用交易处理实体
 * @author Liuzhen
 * @version 1.0
 * 2015-11-19
 */
public class GeneralBusEntity {

	// 交易报文头
	private GeneralMsgHeader header = null;
	// 产品交易流水
	private FDatPrdinfoflw prdflw = null;
	// 原交易流水
	private FDatPrdtrnflw  otrdflw = null;
	// 子交易流水
	private FDatMetatrnflw strdflw = null;
	// 对方给我的数据 
	private byte[] downMsg = null;
	// 我给对方的数据
	private byte[] upMsg = null;
	
	private byte[] coreMsg = null;// 核心报文
	// 是否记录流水
	private boolean isRecordFlw = true;
	// 交易类型
	private String trnType = null; 
	
	// 响应状态
	private String resSta = ResSta.S.getValue();
	
	// 异常信息
	private String errMsg = null;
	
	// 交易日期
	private String trnDate = null;
	// 交易时间
	private String trnTime = null;
	// 交易日期
	private Date trnddate = null;
	// 发送ip
	private String fromIp = null;

	private String frntJrn = null;// 前置流水号

	private String procSta = null;// 处理转态 0-失败 1-成功
	
	private String procReslut = null;//处理结果信息
	
	private Trade trade = null;// 交易配置模板节点
	
	private Product product = null;// 产品配置模板节点
	
	private CoreMsgHeader coreHeader = null;//核心报文头
	
	private DesEntity desKeyInf = null;// 密钥信息
	
	private boolean isTransferHeader = true;
	
	private String coreLogId = null;
	private String coreReqMsg = null;
	private List<byte[]> reqList = new ArrayList<byte[]>(); // 请求信息list
	private List<byte[]> resList = new ArrayList<byte[]>(); // 响应信息list
	private List<FDatPrdinfoflw> pList = new ArrayList<FDatPrdinfoflw>();// 产品流水实体list
	private List<FDatPrdtrnflw> oList = new ArrayList<FDatPrdtrnflw>();//   原交易流水实体list
	private String logId = null; // 日志Id
	private String tmoutFlg = "0"; // 超时处理flg

	
	/*
	 *   GETTER    AND   SETTER
	 */
	public GeneralMsgHeader getHeader() {
		return header;
	}
	public void setHeader(GeneralMsgHeader header) {
		this.header = header;
	}
	public FDatPrdinfoflw getPrdflw() {
		return prdflw;
	}
	public void setPrdflw(FDatPrdinfoflw prdflw) {
		this.prdflw = prdflw;
	}
	public FDatPrdtrnflw getOtrdflw() {
		return otrdflw;
	}
	public void setOtrdflw(FDatPrdtrnflw otrdflw) {
		this.otrdflw = otrdflw;
	}
	public FDatMetatrnflw getStrdflw() {
		return strdflw;
	}
	public void setStrdflw(FDatMetatrnflw strdflw) {
		this.strdflw = strdflw;
	}
	public byte[] getDownMsg() {
		return downMsg;
	}
	public void setDownMsg(byte[] downMsg) {
		this.downMsg = downMsg;
	}
	public byte[] getUpMsg() {
		return upMsg;
	}
	public void setUpMsg(byte[] upMsg) {
		this.upMsg = upMsg;
	}
	public boolean isRecordFlw() {
		return isRecordFlw;
	}
	public void setRecordFlw(boolean isRecordFlw) {
		this.isRecordFlw = isRecordFlw;
	}
	public String getTrnType() {
		return trnType;
	}
	public void setTrnType(String trnType) {
		this.trnType = trnType;
	}
	public String getResSta() {
		return resSta;
	}
	public void setResSta(String resSta) {
		this.resSta = resSta;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getTrnDate() {
		return trnDate;
	}
	public void setTrnDate(String trnDate) {
		this.trnDate = trnDate;
	}
	public String getTrnTime() {
		return trnTime;
	}
	public void setTrnTime(String trnTime) {
		this.trnTime = trnTime;
	}
	public String getFromIp() {
		return fromIp;
	}
	public void setFromIp(String fromIp) {
		this.fromIp = fromIp;
	}
	public CoreMsgHeader getCoreHeader() {
		return coreHeader;
	}
	public void setCoreHeader(CoreMsgHeader coreHeader) {
		this.coreHeader = coreHeader;
	}
	public Date getTrnddate() {
		return trnddate;
	}
	public void setTrnddate(Date trnddate) {
		this.trnddate = trnddate;
	}
	public String getFrntJrn() {
		return frntJrn;
	}
	public void setFrntJrn(String frntJrn) {
		this.frntJrn = frntJrn;
	}
	public String getProcSta() {
		return procSta;
	}
	public void setProcSta(String procSta) {
		this.procSta = procSta;
	}
	public String getProcReslut() {
		return procReslut;
	}
	public void setProcReslut(String procReslut) {
		this.procReslut = procReslut;
	}
	public byte[] getCoreMsg() {
		return coreMsg;
	}
	public void setCoreMsg(byte[] coreMsg) {
		this.coreMsg = coreMsg;
	}
	public Trade getTrade() {
		return trade;
	}
	public void setTrade(Trade trade) {
		this.trade = trade;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public DesEntity getDesKeyInf() {
		return desKeyInf;
	}
	public void setDesKeyInf(DesEntity desKeyInf) {
		this.desKeyInf = desKeyInf;
	}
	public List<byte[]> getReqList() {
		return reqList;
	}
	public void setReqList(List<byte[]> reqList) {
		this.reqList = reqList;
	}
	public List<byte[]> getResList() {
		return resList;
	}
	public void setResList(List<byte[]> resList) {
		this.resList = resList;
	}
	public List<FDatPrdinfoflw> getpList() {
		return pList;
	}
	public void setpList(List<FDatPrdinfoflw> pList) {
		this.pList = pList;
	}
	public List<FDatPrdtrnflw> getoList() {
		return oList;
	}
	public void setoList(List<FDatPrdtrnflw> oList) {
		this.oList = oList;
	}
	public boolean isTransferHeader() {
		return isTransferHeader;
	}
	public void setTransferHeader(boolean isTransferHeader) {
		this.isTransferHeader = isTransferHeader;
	}
	public String getCoreLogId() {
		return coreLogId;
	}
	public void setCoreLogId(String coreLogId) {
		this.coreLogId = coreLogId;
	}
	public String getCoreReqMsg() {
		return coreReqMsg;
	}
	public void setCoreReqMsg(String coreReqMsg) {
		this.coreReqMsg = coreReqMsg;
	}
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getTmoutFlg() {
		return tmoutFlg;
	}
	public void setTmoutFlg(String tmoutFlg) {
		this.tmoutFlg = tmoutFlg;
	}
	
}
