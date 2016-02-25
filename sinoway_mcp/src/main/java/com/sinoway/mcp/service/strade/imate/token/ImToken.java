package com.sinoway.mcp.service.strade.imate.token;




/**
 * 电信token实体
 * @author jintao
 *
 * @date 2016-1-16 上午11:21:40
 */

public class ImToken {  
  
    private static ImToken instance = null;  
    private String tokenid = "-1";
    private long time;
    //单例模式
    private ImToken() {
    }  
    
    //防止多线程下创建对象问题
    private static synchronized void syncInit() {  
        if (instance == null) {  
            instance = new ImToken();  
        }  
    }  
  
    public static ImToken getInstance() {  
        if (instance == null) {  
            syncInit();  
        }
        
        return instance;  
    }

	public String getTokenid() {
		return tokenid;
	}

	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public static void setInstance(ImToken instance) {
		ImToken.instance = instance;
	}  
  
   
}  

