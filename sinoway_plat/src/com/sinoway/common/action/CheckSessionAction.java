package com.sinoway.common.action;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;

import com.yzj.wf.base.action.BaseAction;

public class CheckSessionAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5524648436751306723L;
	/**
	 * 拦截器，session是否过期
	 * @author 曲瑛璇
	 */
	public String checkSession(){
		PrintWriter out = null;
		try {
			out = ServletActionContext.getResponse().getWriter();
			if(ServletActionContext.getRequest().getSession(false)==null){
				out.println(0);
			}else{
				out.println(1);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			out.flush();
			out.close();
		}
		return CheckSessionAction.SUCCESS;
	}

}
