package com.sinoway;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Test {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://127.0.0.1:8080/TestSer1/TestServlet");
			
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(1000);
			conn.setReadTimeout(5000);
			InputStream is = conn.getInputStream();
			
			while(is.read() != -1 ){
				
				
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(":::::" + e.getMessage());
		}
	}
}
