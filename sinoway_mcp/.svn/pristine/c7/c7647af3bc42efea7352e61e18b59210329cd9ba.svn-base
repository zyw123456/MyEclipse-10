package com.sinoway.common.starde;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinoway.common.frame.SpringContextUtil;
import com.sinoway.common.pool.McpThreadPool;
import com.sinoway.common.pool.ThreadPoolFactory;
import com.sinoway.common.pool.task.GeneralStradeTaskService;
import com.sinoway.common.pool.task.IPoolTask;
import com.sinoway.common.service.init.StartService;
import com.sinoway.common.util.GUIDGenerator;
import com.sinoway.common.util.JRNGenerator;
import com.sinoway.common.util.SystemOperateUtil;
import com.sinoway.mcp.queue.exception.QueueOperatException;
import com.sinoway.mcp.queue.producer.MessageProducer;

public class sSend {

	public static void main(String[] args) {
//		send2();
		send1();
	}
	public static void send2(){
		ApplicationContext ac= new FileSystemXmlApplicationContext("conf/spring/applicationContext.xml");
		
		StartService startService = (StartService)ac.getBean("startService");
		SystemOperateUtil systemOpUtil = (SystemOperateUtil)ac.getBean("SystemOperateUtil");
		// 启动服务
		systemOpUtil.init();
		
		// 启动服务
//		startService.init();
		
		String a = "{\"header\":{\"chnlcod\":\"T0000001\",\"subusrid\":\"1601252130275278fa3e8a8081de0002\",\"intrncod\":\"T0000001\",\"masttrntim\":\"150635119\",\"masttrndte\":\"20160126\",\"mastjrn\":\"CSM000000120160126527b00020025\",\"orgno\":\"OP201601255278fa00010000\",\"usrid\":\"SQCW000001\"},\"bodys\":[{\"prsnnam\":\"俞品元\",\"idcard\":\"320582198001200315\"}]}";
		JSONObject json = (JSONObject)JSON.parse(a);
		
		McpThreadPool pool = ThreadPoolFactory.getPool("strade_proc_pool");
		
		IPoolTask<String> task = (IPoolTask)SpringContextUtil.getBean(GeneralStradeTaskService.class);
		
		task.init(GUIDGenerator.generateId(), pool, json.toString());
		try{
//			pool.excute(task);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void send1(){
		for(int i = 0; i< 1; i++){
			
		
		JSONObject json = new JSONObject();
		JSONObject header = new JSONObject();
		
		
		
		header.put("intrncod", "T0000001");
		header.put("chnlcod", "T0000001");
		header.put("usrid", "liuzhen");
		header.put("orgno", "sinoway");
		header.put("subusrid", "sLiuzhen");
		header.put("masttrndte", "20151121");
		header.put("masttrntim", "144510100");
		header.put("mastjrn", "mastjrn" + i);
		
		JSONArray bodys = new JSONArray();
		JSONObject body = new JSONObject();
		body.put("prsnnam", "刘振");
		body.put("idcard", "23012519890808311X");
		body.put("mobile", "18501373321");
		
		
		bodys.add(body);
		
		json.put("header", header);
		json.put("bodys",bodys);
		
		System.out.println(json.toJSONString());
		
		MessageProducer m = new MessageProducer();
		try {
			m.sendData(json.toJSONString(), "req2");
		} catch (QueueOperatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
}
