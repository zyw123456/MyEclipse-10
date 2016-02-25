package com.sinoway.common.util;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.yzj.wf.common.WFLogger;

/**
 * 保存报文消息目录
 * @author miles
 *
 */
public class MessageStorageUtil {
	
	private static final WFLogger logger = WFLogger.getLogger(MessageStorageUtil.class);
	 
	
	/**
	 * 根据默认的消息目录生成消息路径
	 * @return
	 */
	public String genrateDefaultFileDir(){
		Calendar cdate = Calendar.getInstance();
		
		StringBuffer filedir = new StringBuffer();
		filedir.append(Constant.getDefaultMessageFileFolder());
		filedir.append(File.separator);
		filedir.append(cdate.get(Calendar.YEAR));
		filedir.append(File.separator);
		filedir.append(cdate.get(Calendar.MONTH)+1);
		filedir.append(File.separator);
		filedir.append(cdate.get(Calendar.DATE));
		filedir.append(File.separator);
		filedir.append(cdate.get(Calendar.HOUR));
		filedir.append(File.separator);
		filedir.append(cdate.get(Calendar.MINUTE));
		filedir.append(File.separator);
		
		File file  = new File(filedir.toString());
		if(!file.exists()){
			file.mkdirs();
		}
		return file.getAbsolutePath();
	}

	
	/**
	 * 通用报告消息写入文件
	 * context路径/年/月/日/时/分/报文编号-req
	 * context路径/年/月/日/时/分/报文编号-res
	 * @param timefield 年月日时分秒字符串
	 * @param rptid	报告ID
	 * @param httpType 请求类型res和req
	 * @param content 报告内容
	 * @return 返回报文路径
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public static String writeMessageToFile(String timefield, String rptid, String httpType, String content) throws ParseException {
		Date date = DateUtils.parseDate(timefield, "yyyyMMddHHmmssSSS");
		StringBuffer filedir = new StringBuffer();
		
		filedir.append(Constant.getDefaultMessageFileFolder());
		filedir.append(File.separator);
		filedir.append(DateUtil.fileDateFormat.format(date));
		//生成路径
		File fileDir = new File(filedir.toString());
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		
		//生成文件
		File file = new File(filedir.toString(), rptid+"-"+httpType);
		
		try {
			//文件写入
			FileUtils.writeStringToFile(file, content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return file.getAbsolutePath();
	}
	
	
	/**
	 * 通用报告消息写入文件
	 * context路径/年/月/日/时/分/报文编号-req
	 * context路径/年/月/日/时/分/报文编号-res
	 * @param filedir 文件路径
	 * @param filename 文件名称
	 * @param content  内容
	 * @return 返回报文路径
	 * @throws IOException 
	 */
	public static String writeMessageToFile(String filedir, String filename, String content) {
		//生成路径
		File fileDir = new File(filedir);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		
		//生成文件
		File file = new File(filedir, filename);
		
		try {
			//文件写入
			FileUtils.writeStringToFile(file, content,Constant.getDefaultEncoding());
		} catch (IOException e) {
			e.printStackTrace();
		}
logger.debug("文件写入完成: "+ file.getAbsolutePath());	
		return file.getAbsolutePath();
	}
	
	
	
	/**
	 * 读取文件的信息
	 * @param fileName
	 * @return
	 * @throws IOException 
	 */
	public static String readMessageFromFile(String fileName) throws IOException{
		File file = new File(fileName);
		
		return FileUtils.readFileToString(file);
	}
	

}
