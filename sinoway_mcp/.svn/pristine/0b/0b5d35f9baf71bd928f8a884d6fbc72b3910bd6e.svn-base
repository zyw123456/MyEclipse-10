package com.sinoway.common.service.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1",20001);
			
			InputStream in = socket.getInputStream();
			
			String a = "000012340000000100000002000301234567890123456789012345678901";
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			pw.print(a);
			pw.flush();
			pw.close();
			int len = 0;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			while( (len = in.read(buffer)) != -1){
				
				bos.write(buffer,0,len);
			}
			
			System.out.println(new String(bos.toByteArray()));
			in.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
