package com.sinoway.common.service.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinoway.common.entity.GeneralMsgHeader;
import com.sinoway.common.service.parse.GeneralMsgHeaderServiceImpl;
import com.sinoway.common.util.ByteUtil;
import com.sinoway.mcp.entity.Clntest;
import com.sinoway.mcp.service.ClntestService;

public class Server {
	ClntestService clntestService = null;

	public void init(){
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					ServerSocket server = new ServerSocket(40000);
					while(true){
						try{
							GeneralMsgHeaderServiceImpl s = new GeneralMsgHeaderServiceImpl();
							s.setMsgLen(66);
							final Socket socket = server.accept();
							InputStream in = socket.getInputStream();
							OutputStream os = socket.getOutputStream();
							
							GeneralMsgHeader header = s.reciveMsgHeader(in);
							System.out.println("客户端接收到响应结果：报文头：" + new String(header.getBytes()));
							int len = header.getMsglen();
							
							byte[] b = ByteUtil.readFixBytesFromInput(in, len);
							String str = new String(b,"utf-8");
							System.out.println("客户端接收到响应结果：报文体：" + str);
							JSONObject j = (JSONObject)JSON.parse(str);
							JSONObject body = j.getJSONObject("body");
							JSONObject jheader = j.getJSONObject("header");
							Clntest cln = new Clntest();
							cln.setClntjrn(jheader.getString("clntjrn"));
							cln.setFntjrn(jheader.getString("F2prd0000115113014023800007001"));
							cln.setAge(body.getString("age"));
							cln.setBirthday(body.getString("birthday"));
							cln.setCertorg(body.getString("certorg"));
							cln.setCerttype(body.getString("certtype"));
							cln.setSex(body.getString("sex"));
							cln.setTellphone(body.getString("tellphone"));
							clntestService.update(cln);
							JSONObject json = new JSONObject();
							json.put("status", "1");
							String a = "0000001410000001000000001000000102632b18e2c549fc9603424f9e5d152913";
							os.write(a.getBytes());
							os.flush();
							os.write(json.toString().getBytes("utf-8"));
							os.flush();
							in.close();
							os.close();
							socket.close();
							
						}catch(Exception e){
							e.printStackTrace();
						}
						
						
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		t.start();
	}
	
	
	
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(40000);
			while(true){
				try{
					GeneralMsgHeaderServiceImpl s = new GeneralMsgHeaderServiceImpl();
					s.setMsgLen(66);
					final Socket socket = server.accept();
					InputStream in = socket.getInputStream();
					OutputStream os = socket.getOutputStream();
					
					GeneralMsgHeader header = s.reciveMsgHeader(in);
					System.out.println(new String(header.getBytes()));
					int len = header.getMsglen();
					
					byte[] b = ByteUtil.readFixBytesFromInput(in, len);
					
					System.out.println(new String(b,"utf-8"));
					JSONObject json = new JSONObject();
					json.put("status", "1");
					String a = "0000001410000001000000001000000102632b18e2c549fc9603424f9e5d152913";
					os.write(a.getBytes());
					os.flush();
					os.write(json.toString().getBytes("utf-8"));
					os.flush();
					in.close();
					os.close();
					socket.close();
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public ClntestService getClntestService() {
		return clntestService;
	}



	public void setClntestService(ClntestService clntestService) {
		this.clntestService = clntestService;
	}

}
