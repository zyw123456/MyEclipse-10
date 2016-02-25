package com.sinoway.common.que;

import com.sinoway.common.service.consumer.DefaultConsumerService;

public class Consumer extends DefaultConsumerService{

	@Override
	public void excuteMsg(String msg) throws Exception {
		System.out.println(":::::::::::::::::::::::::" + Thread.currentThread().getId());
		System.out.println(msg);
	}

	
	public static void main(String[] args) {
		Consumer com = new Consumer();
		
		try {
			com.startConsumer("res3", "group6", 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
