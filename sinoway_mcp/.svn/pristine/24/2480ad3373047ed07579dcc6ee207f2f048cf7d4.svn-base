package com.sinoway.common.parse;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class TestHttp {
	
	
	
 public void  testHttpGet(){
    CloseableHttpClient  client = HttpClients.createDefault();
    
	List<NameValuePair>  palist = new ArrayList<NameValuePair>();
	
	
	palist.add(new BasicNameValuePair("key1","value1"));
	
	String  key = null;
	
	String value = null;
	
	Object  type = null;
	  if(palist.size()>0){
		  
		  palist.add(new BasicNameValuePair(key, value))  ;
		  
	  }
	
	  String url =null;
     try {
		URI    uri = new URIBuilder(url).setParameters(palist).build();
		
		HttpGet   get =  new HttpGet(uri);
		
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
	
	
	
	
	
	
	
	
	
 }

}
