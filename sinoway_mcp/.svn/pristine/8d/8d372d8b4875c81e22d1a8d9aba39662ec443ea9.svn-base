package com.sinoway.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import com.sinoway.common.exception.ByteOperatException;

/**
 * 字节通用操作工具
 * @author Liuzhen
 * @version 1.0
 * 2015-11-10
 */
public class ByteUtil {

	private final static int BUFFER_SIZE = 512;// 缓冲字节数组长度
	/**
	 * 从输入流中读取固定大小的字节数组
	 * @param in
	 * @param len
	 * @return
	 * @throws TestException
	 */
	public static byte[] readFixBytesFromInput(InputStream in,int len) throws ByteOperatException{
		if(len == 0)
			throw new ByteOperatException("从输入流中读取指定字节数组错误: 长度不能为0");
		if(in == null)
			throw new ByteOperatException("从输入流中读取指定字节数组错误: 输入流不能为空");
		
		byte[] bs = new byte[len];
		
		// 缓冲字节数组
		byte[] buffer = new byte[BUFFER_SIZE];
		
		// 偏移量
		int offset = 0;
		
		// 剩余长度
		int left = len;
		
		// 读取长度
		int readed = 0;
		
		while(left > 0){
			try {
				readed = in.read(buffer, 0, Math.min(BUFFER_SIZE, left));
				
				if(readed == -1)
					break;
				
				System.arraycopy(buffer, 0, bs, offset, readed);
				
			} catch (IOException e) {
				e.printStackTrace();
				throw new ByteOperatException("从输入流中读取指定字节数组错误: 读取数据错误");

			}catch(Exception e){
				e.printStackTrace();
				throw new ByteOperatException("从输入流中读取指定字节数组错误: 读取数据未知异常 ");
			}finally{
				offset = offset + readed;
				left = left - readed;
			}
		}
		
		
		
		return bs;
	} 
	
	/**
	 * 字节数组保存成文件
	 * @param filePath
	 * @param bytes
	 * @return
	 * @throws TestException
	 */
	public static boolean byteArrayToFile(String filePath,byte[] bytes) throws ByteOperatException{
		
		FileOutputStream fos  = null;
		File file = null;
		try {
			if(bytes != null && filePath != null && !"".equals(filePath)){
				file = new File(filePath);
				String pPath = file.getParent();
				File pFile = new File(pPath);
				if(!pFile.exists()){
					pFile.mkdirs();
				}
				pFile = null;
				if(!file.exists()){
					file.createNewFile();
				}
				
				fos  = new FileOutputStream(file);
				
				fos.write(bytes);
				
				fos.flush();
				
				return true;
				
			}
		} catch (IOException e) {
			throw new ByteOperatException("字节数组保存成文件失败:I/O异常",e);
		}catch (Exception e) {
			throw new ByteOperatException("字节数组保存成文件失败:未知异常",e);
		}finally{
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			file = null;
			fos = null;
		}
		
		return false;
	}
	
	/**
	 * inputstream 转换byte数组
	 * @param inStream
	 * @return
	 * @throws IOException
	 */
    public static final byte[] input2byte(InputStream inStream)  
            throws IOException {  
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
        byte[] buff = new byte[100];  
        int rc = 0;  
        while ((rc = inStream.read(buff)) != -1) {  
            swapStream.write(buff, 0, rc);  
        }  
        byte[] in2b = swapStream.toByteArray();  
        return in2b;  
    }  
	
}
