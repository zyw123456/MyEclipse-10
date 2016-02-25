package com.sinoway.common.util;

import java.io.UnsupportedEncodingException;


import com.yzj.wf.common.WFLogger;


public class VerificationForm {
	private static final WFLogger logger = WFLogger.getLogger(VerificationForm.class);
	
    /**
     * 验证姓名长度
     * @param prsnnam
     * @return
     */
    public static String VerIfPrsnNam(String prsnnam){
    	try {
			byte[] pranleng = prsnnam.getBytes(Constant.getDefaultEncoding());
			if(pranleng.length > Constant.getPrsnnamlength()){
	    		return "后台验证：姓名字符长度超过定义的"+Constant.getPrsnnamlength()+"位";
	    	}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error("验证姓名字符长度失败！");
			e.printStackTrace();
		}
    	
    	return Constant.ResultStatus.RESULTSTATUS_SUCCESS.getCode();
    } 
    /**
     * 验证手机号长度
     * @param telno
     * @return
     */
    public static String VerIfTelNo(String telno){
    	
		try {
			byte[] telleng = telno.getBytes(Constant.getDefaultEncoding());
			if(telleng.length > Constant.getTelnolength()){
	    		return "后台验证：手机号字符长度超过定义的"+Constant.getTelnolength()+"位";
	    	} 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error("验证手机号字符长度失败！");
			e.printStackTrace();
		}
    	
    	return Constant.ResultStatus.RESULTSTATUS_SUCCESS.getCode();
    } 
    /**
     * 验证身份证号长度
     * @param prsncod
     * @return
     */
    public static String VerIfPrsnCod(String prsncod){
    	
		try {
			byte[] 	prsnleng = prsncod.getBytes(Constant.getDefaultEncoding());
			if(prsnleng.length > Constant.getPrsncodlength()){
	    		return "后台验证：身份证号字符长度超过定义的"+Constant.getPrsncodlength()+"位";
	    	}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error("验证身份证号字符长度失败！");
			e.printStackTrace();
		}
    	return Constant.ResultStatus.RESULTSTATUS_SUCCESS.getCode();
    }
}
