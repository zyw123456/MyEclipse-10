package com.sinoway.common.service.socket;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import com.alibaba.fastjson.JSONObject;
import com.sinoway.common.util.MD5Utils;
import com.sinoway.common.util.StringUtil;


public class OSend {

	public static void main(String[] args) {
		sendHttp();
	}
	public static void sendHttp(){
		JSONObject b = new JSONObject();
		JSONObject header = new JSONObject();
		JSONObject body = new JSONObject();
		
		body.put("prsnnam", "刘振");
		body.put("idcard", "23012519890808311X");

		header.put("usrid", "SQCW000001");
		header.put("datori", "001");
		header.put("orgno", "SQCW0000000000000001");
		header.put("subusrid", "SQCW000001");
		header.put("clnttrndte", "20151121");
		header.put("clnttrntime", "144510100");
		header.put("clntjrn", "aaaaa");
		
		
		
		JSONObject json = new JSONObject();
		json.put("header", header);
		json.put("body", body);
		System.out.println(json.toString());
		try {
			String chcCod = MD5Utils.hash("50000001" + "12345678" + json.toJSONString(), "MD5");
			String u = "http://127.0.0.1:8080/sinoway_mcp/httpControl?";
			u = u + "&chnlcod=50000001&trncod=P0000001&isbatch=0&msgtype=1";
			URL url = new URL(u);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("content-type", "text/html");  
			conn.addRequestProperty("checkcod", chcCod);
			OutputStream os = conn.getOutputStream();
			os.write(json.toString().getBytes("utf-8"));
			os.flush();
			InputStream in = conn.getInputStream();
			
			int len = 0;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
			byte[] buffer = new byte[100];
			while((len = in.read(buffer))!= -1){
				bos.write(buffer, 0, len);
			}
			
			byte[] c = bos.toByteArray();
			System.out.println(new String(c));
			System.out.println(conn.getHeaderField("checkcod"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void sendSocket(){
		try {
			
			String a = "00000193100000010000000010000001016221abe96fc89db9d2ead93145c19f46";
			String chnlCOd = "50000001";
			String trdCod= "P0000001";
			String prdCod = "00000000";
			String isBatch = "0";
			String chcCod = "";
			String msgType = "1";
			String msgLen = "";
			JSONObject b = new JSONObject();
			JSONObject header = new JSONObject();
			JSONObject body = new JSONObject();
			
			body.put("prsnnam", "刘振");
			body.put("idcard", "23012519890808311X");
			
			header.put("usrid", "liuzhen");
			header.put("daiori", "1");
			header.put("orgno", "sinoway");
			header.put("subusrid", "sLiuzhen");
			header.put("clnttrndte", "20151121");
			header.put("clnttrntime", "144510100");
			header.put("clntjrn", "aaaaa");
			
			
			
			JSONObject json = new JSONObject();
			json.put("header", header);
			json.put("body", body);
			System.out.println(json.toString());
			int len1 = json.toString().getBytes("utf-8").length;
			
			
			String sLen = len1 + "";
			int x = 8 - sLen.length();
			for(int i = 0; i <  x; i++){
				sLen = "0" + sLen;
			}
			System.out.println(sLen);
			
			chcCod = MD5Utils.hash("50000001" + "12345678" + json.toJSONString(), "MD5");
			
			System.out.println(chcCod);
			
			String h = sLen + trdCod +  prdCod + chnlCOd + isBatch + msgType + chcCod;
					
			System.out.println(h);
			
			Socket socket = new Socket("127.0.0.1",30000);
			InputStream in = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			os.write(h.getBytes());
			os.flush();
			os.write(json.toString().getBytes("utf-8"));
			os.flush();
			int len = 0;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
			byte[] buffer = new byte[100];
			while((len = in.read(buffer))!= -1){
				bos.write(buffer, 0, len);
			}
			
			byte[] c = bos.toByteArray();
			System.out.println(new String(c));
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
