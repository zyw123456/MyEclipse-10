package com.sinoway.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import sun.misc.BASE64Decoder;

import com.sinoway.common.exception.DomParseException;
import com.yzj.wf.common.WFLogger;

public class FileConstant {
	private static final WFLogger logger = WFLogger.getLogger(VerificationForm.class);
	 /**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     * @param filePath
	 * @throws DomParseException 
     */	
	public static String getFileConstant(String filePath) throws DomParseException{
	    String jsonAddr = "";
	    if(null == filePath){
	    	logger.error("解析响应报文：相应报文地址为空！");
        	return Constant.ResultStatus.RESULTSTATUS_FAIL.getCode();
	    }
        try {
	        String encoding=Constant.getDefaultEncoding();
	        File file=new File(filePath);
	        if(file.isFile() && file.exists()){ //判断文件是否存在
	            InputStreamReader read = new InputStreamReader(
	            new FileInputStream(file),encoding);//考虑到编码格式
	            BufferedReader bufferedReader = new BufferedReader(read);
	            String lineTxt = "";
	            while((lineTxt = bufferedReader.readLine()) != null){
	            	jsonAddr =jsonAddr+ lineTxt;
	            }
	            read.close();
	        }else{
	        	logger.error("解析响应报文：读取文件内容出错！");
	        	return Constant.ResultStatus.RESULTSTATUS_FAIL.getCode();
	        }
        } catch (Exception e) {
        	logger.error("解析响应报文：找不到指定的文件！");
        	e.printStackTrace();
        	return Constant.ResultStatus.RESULTSTATUS_FAIL.getCode();
        }
        return jsonAddr;
	}
	/** 
	* @Title: createReqFile 
	* @Description: (创建请求文件) 
	* @author  于辉
	*/
	public static String createReqFile(String jsonCon,String rptId,String cde,String cts) {  
		String year = cde.substring(0, 4);
		String mon  = cde.substring(4, 6);
		String day  = cde.substring(6, 8);
		String hour = cts.substring(0, 2);
		String min  = cts.substring(2, 4);
		String rpId = rptId;
		
		String pathF = Constant.getDefaultMessageFileFolder()+year+"\\"+mon+"\\"+day+"\\"+hour+"\\"+min+"\\"+rpId;
		String fileN = "\\"+rpId+"-req";
		String resultP = pathF + fileN;
		File file = new File(pathF);
		if(!file.exists() && !file.isDirectory()){
			file.mkdirs();
		}
    	BufferedWriter fw = null;
		try {
			fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultP, true), Constant.getDefaultEncoding())); // 指定编码格式，以免读取时中文字符异常
			fw.append(jsonCon.replaceAll("\\\\n", ""));
			fw.flush(); // 全部写入缓存中的内容
		} catch (Exception e) {
			logger.error("报文文件写入失败");
			e.printStackTrace();
			return null;
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        return resultP;
    }  
	/**

	*

	 * @return WebRoot目录的绝对路径

	*/

	public static String getWebRootAbsolutePath() {
		String path = LoadModel.class.getResource("/").getPath().replaceAll("classes", "template");
		path = path.substring(1, path.length());
		return path+"rptTemplate.xml";
	}
	/** 
	* @Title: base64ToIo 
	* @Description: (base64编码转回文件) 
	* @return JSON     
	* @author 于辉
	*/
	public void base64ToIo(String strBase64) throws IOException {
		String cde = DateUtil.getCurrentDate().replaceAll("-", "");
		String cts = DateUtil.getCurrentTimeMillis().replaceAll(":", "").replaceAll(" ", "");
        String string = strBase64;
        String fileName = "d:/"+cde+cts+".xml"; //生成的新文件
        try {
            // 解码，然后将字节转换为文件
            byte[] bytes = new BASE64Decoder().decodeBuffer(string);   //将字符串转换为byte数组
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            byte[] buffer = new byte[1024];
            FileOutputStream out = new FileOutputStream(fileName);
            int bytesum = 0;
            int byteread = 0;
            while ((byteread = in.read(buffer)) != -1) {
            	
                bytesum += byteread;
                out.write(buffer, 0, byteread); //文件写操作
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
