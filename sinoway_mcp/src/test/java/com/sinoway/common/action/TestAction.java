package com.sinoway.common.action;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.sinoway.common.entity.GeneralMsgHeader;
import com.sinoway.common.service.parse.GeneralMsgHeaderServiceImpl;
import com.sinoway.common.util.ByteUtil;
import com.sinoway.common.util.DateUtil;
import com.sinoway.common.util.MD5Utils;
import com.sinoway.common.util.StringUtil;
import com.sinoway.common.util.UUID32;
import com.sinoway.mcp.entity.Clntest;
import com.sinoway.mcp.service.ClntestService;


public class TestAction extends ActionSupport {
	ClntestService clntestService = null;
	public String search(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String idcard = request.getParameter("idcard");
		String name = request.getParameter("idname");
		String flwCode = UUID32.getUUID();
		
		System.out.println(idcard);
		System.out.println(name);
		JSONObject b = new JSONObject();
		JSONObject header = new JSONObject();
		JSONObject body = new JSONObject();
		
		b.put("o", idcard);
		b.put("p", name);
		
		Date date = new Date();
		String trnDate = DateUtil.dateToString(date, "yyyyMMdd");
		// 置交易时间
		String trnTm = DateUtil.dateToString(date, "HHmmssSSS");
		
		header.put("usrid", "liuzhen");
		header.put("orgno", "sinoway");
		header.put("subusrid", "sLiuzhen");
		header.put("clnttrndte", trnDate);
		header.put("clnttrntime", trnTm);
		header.put("clntjrn", flwCode);
		
		body.put("a",b);
		JSONObject json = new JSONObject();
		json.put("header", header);
		json.put("body", body);
		
		GeneralMsgHeaderServiceImpl aa = new GeneralMsgHeaderServiceImpl();
		aa.setMsgLen(66);
		// 设置报文头
		GeneralMsgHeader  msgHeader = new GeneralMsgHeader ();
		
		try {
			msgHeader.setCheckcod(MD5Utils.hash(json.toString().getBytes("utf-8"), "MD5"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		msgHeader.setChnlcod("10000001");
		msgHeader.setOuttrncod("10000001");
		msgHeader.setIsbatch("0");
		msgHeader.setMsgtype("1");
		try {
			msgHeader.setMsglen(json.toString().getBytes("utf-8").length);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		msgHeader.setPrdcod("00000000");
		
		try {
			byte[] hs = aa.transHeaderToByte(msgHeader);
			byte[] bs = json.toString().getBytes("utf-8");
			
			Socket socket = new Socket("127.0.0.1",30000);
			InputStream in = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			os.write(hs);
			os.flush();
			os.write(bs);
			os.flush();
			
			GeneralMsgHeader header1 = aa.reciveMsgHeader(in);
			
			int len = header1.getMsglen();
			
			byte[] bss = ByteUtil.readFixBytesFromInput(in, len);
			
			JSONObject jsonObject = (JSONObject) JSON.parse(new String(bss));
			JSONObject jBody = (JSONObject) jsonObject.get("body");
			String fntJrn = jBody.getString("frnjrn");
			System.out.println(fntJrn);
			Clntest entity = new Clntest();
			entity.setClntjrn(flwCode);
			entity.setFntjrn(fntJrn);
			entity.setIdcard(idcard);
			entity.setName(name);
			entity.setTrndate(trnDate);
			entity.setTrntm(trnTm);
			clntestService.save(entity);
			request.setAttribute("clnjrn", flwCode);
			request.setAttribute("fntjrn", fntJrn);
		} catch (Exception  e) {
			e.printStackTrace();
		}
		return "sucess";
	}
	
	public String search1(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String flwCode = request.getParameter("flwCode");
		Clntest clntest = new Clntest();
		clntest.setFntjrn(flwCode);
		List list = clntestService.find(clntest);
		if(list != null && list.size() > 0)
			clntest = (Clntest)list.get(0);
		
		request.setAttribute("clntest", clntest);
		
		return "sucess";

	}
	public ClntestService getClntestService() {
		return clntestService;
	}
	public void setClntestService(ClntestService clntestService) {
		this.clntestService = clntestService;
	}
	
	
}
