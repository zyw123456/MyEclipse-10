package com.sinoway.acc.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.sinoway.acc.entity.WfCfgdefCompinfo;
import com.sinoway.acc.entity.WfCfgdefPwd;
import com.sinoway.acc.service.IAccountService;
import com.sinoway.common.util.DateUtil;
import com.sinoway.common.util.GUIDGenerator;
import com.sinoway.common.util.JsonBinder;
import com.sinoway.fad.action.FraudAction;
import com.sinoway.rpt.entity.WfDatCreditrptUtil;
import com.yzj.wf.base.action.BaseAction;
import com.yzj.wf.common.WFLogger;
import com.yzj.wf.core.model.po.OrganizeInfo;
import com.yzj.wf.core.model.po.PeopleInfo;
import com.yzj.wf.core.model.po.common.POException;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;
import com.yzj.wf.core.service.po.IPOService;

/**
 * 账号管理-公司基本信息
 * @author zhangyanwei
 *
 */
public class AccountInfoAction  extends BaseAction{
	private static final long serialVersionUID = 481077790366808497L;
	private IAccountService accountService;
	private IPOService poService;
	private String frontObjStr;
	private String sid;
	private WfCfgdefPwd wfCfgdefPwd;
	private String oldPwd;//原始密码
	private String newPwd;//新密码
	private String confimPwd;//确认密码
	private String pwdTwo;
	private List list;
	private String flag;
	private List<OrganizeInfo> orgList;
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	WFLogger logger = WFLogger.getLogger(FraudAction.class);
	private WfCfgdefCompinfo wfCfgdefCompinfo;
	/** 
	* @Title: 修改密码
	* @param  WfCfgdefPwd 人员信息
	* @return void   
	* @throws 
	*/
	
	public String updatepwd(){
		WfCfgdefPwd frotObj = transStrToObj();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		String data  = "";
		try {
			out = response.getWriter();
			if(frotObj.getRecentpasswordrecord().indexOf(" ")!=-1){
				data = "原密码不可以包含空格！";
				out.println(data);//原始密码不可以包含空格
			}else{
				if(frotObj.getPwd().indexOf(" ")!=-1){
					data = "新密码不可以包含空格！";
					out.println(data);//新密码不可以包含空格
				}else{
					if(frotObj.getConfimPwd().indexOf(" ")!=-1){
						data = "确认密码不可以包含空格！";
						out.println(data);//确认密码不可以包含空格！
					}else{
						if("".equals(frotObj.getRecentpasswordrecord())||null==frotObj.getRecentpasswordrecord()){
							data = "原始密码为空";
							out.println(data);//原始密码为空
						}else{
							if("".equals(frotObj.getPwd())||null==frotObj.getPwd()){
								data = "新密码不可为空！";
								out.println(data);
							}else{
								if("".equals(frotObj.getConfimPwd())||null==frotObj.getConfimPwd()){
									data = "确认密码不可为空！";
									out.println(data);
								}else{
									String encryptPwd = new Md5PasswordEncoder().encodePassword(frotObj.getRecentpasswordrecord(),this.getCurrentPeopleSid());
									if(!this.getCurrentPeople().getPwd().equals(encryptPwd)){
										data = "输入密码和初始密码不一致!";
										out.println(data);//原始密码不一致
									}else{
										if(!frotObj.getPwd().equals(frotObj.getConfimPwd())){
											data = "两次输入新密码不一致！";
											out.println(data);
										}else{
											if(frotObj.getRecentpasswordrecord().equals(frotObj.getPwd())){
												data = "新密码不可以与原密码一致！";
												out.println(data);
											}else{
												logger.debug("进入修改密码method");
												XPeopleInfo newPeople;
												try {
													newPeople = poService.changePassword(this.getCurrentPeopleSid(), frotObj.getRecentpasswordrecord(), frotObj.getPwd());
													HttpServletRequest request = ServletActionContext.getRequest();
													Object userObj = request.getSession().getAttribute("XPEOPLEINFO");
													XPeopleInfo xPeople = (XPeopleInfo) userObj;
													xPeople.setPwd(newPeople.getPwd());
													xPeople.setLastChangepwdTime(new Date());
													data ="密码修改成功";
													out.println(data);//密码修改成功
												} catch (POException e) {
													// TODO Auto-generated catch block
													data = e.getMessage();
													out.println(data);//:(如) 新密码不能与最近6次密码修改中设置的密码相同！
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
	* @Title: addCredItRpt 
	* @Description: (报文拼接) 
	* @return String     
	* @throws 于辉
	*/
	public String rtpJson(WfCfgdefPwd trnObj){
		
		StringBuffer headBodyJson = new StringBuffer();
		headBodyJson.append("{header:{");
		headBodyJson.append("\"msgcode\":" + "\"12345678\",");						//报文类型
		headBodyJson.append("\"oprator\":" + "\"\",");  							//操作人
		headBodyJson.append("\"usercod\":" + "\"" + trnObj.getPeoplecode() + "\","); 	//用户编码
		headBodyJson.append("\"oldpwd\":" + "\"" + trnObj.getRecentpasswordrecord() + "\","); 	//原密码
		headBodyJson.append("\"newpwd\":" + "\"" + trnObj.getPwd() + "\"}}");	//新密码
		//报文生成xml文件
		String rqd = createXml(headBodyJson.toString(),trnObj.getPeoplecode());
		return rqd;
	}
	
	/** 
	* @Title: createXml 
	* @Description: (创建请求文件) 
	* @return JSON     
	* @throws zhangyanwei
	*/
	public String createXml(String jsonCon,String rptId) {  
		String cde = DateUtil.getCurrentDate().replaceAll("-", "");
		String cts = DateUtil.getCurrentTimeMillis().replaceAll(":", "").replaceAll(" ", "");
		String year = cde.substring(0, 4);
		String mon  = cde.substring(4, 6);
		String day  = cde.substring(6, 8);
		String hour = cts.substring(0, 2);
		String min  = cts.substring(2, 4);
		String rpId = rptId;
		
		String pathF = "D:\\"+year+"\\"+mon+"\\"+day+"\\"+hour+"\\"+min+"\\"+rpId;
		String fileN = "\\"+rpId+"-req.txt";
		String resultP = pathF + fileN;
		File file = new File(pathF);
		if(!file.exists() && !file.isDirectory()){
			file.mkdirs();
		}
    	BufferedWriter fw = null;
		try {
			fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultP, true), "UTF-8")); // 指定编码格式，以免读取时中文字符异常
			fw.append(jsonCon);
			fw.flush(); // 全部写入缓存中的内容
		} catch (Exception e) {
			e.printStackTrace();
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
 * 查询当前公司信息	
 * @return
 */
   public String findCurrentCompInfo(){
	   String compId;
	   List newlist = new ArrayList();
	   flag = "1";//表示是子账户不可以修改
	   String parentcode = this.getCurrentPeople().getParntCode();
	   if("".equals(parentcode)||"null".equals(parentcode)||parentcode==null){
		   flag = "0";//0主账户登录有修改权限		   
		    compId = this.getCurrentPeople().getOrganizeSid();
		   if(!"".equals(compId)&&compId!=null){
			   newlist = accountService.findCurrentCompInfo(compId);
			   }
	   }else{
		 String team = this.getCurrentPeople().getOrganizeSid();  
		  orgList = accountService.findOrganiznfoById(team);
		 if(orgList!=null){
			 Map<String,String> map = new HashMap<String,String>();
			 map=	(Map<String, String>) orgList.get(0);
			 compId=map.get("PARENTORG");
			 newlist = accountService.findCurrentCompInfo(compId);
		 }
	   } 
	   newlist.add(flag);
	   frontObjStr = JsonBinder.toJson(newlist);
	   HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter out = response.getWriter();
			out.println(frontObjStr);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return SUCCESS;
   }
   /**
    * 修改当前公司信息
    * @return
    */
   public String updateCompInfo(){
	   WfCfgdefCompinfo wfCfgdefCompinfo = transStrToCom(); 
	   if("".equals(wfCfgdefCompinfo.getId())||wfCfgdefCompinfo.getId()==null){
		   wfCfgdefCompinfo.setId(this.getCurrentPeople().getOrganizeSid());
	   }
	  
	   accountService.updateCurrentCompInfo(wfCfgdefCompinfo);
	   frontObjStr = JsonBinder.toJson("保存成功");
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println(frontObjStr);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	   return SUCCESS;
   }
	private WfCfgdefPwd transStrToObj(){
		return JsonBinder.fromJson(frontObjStr, WfCfgdefPwd.class);
	}
	private WfCfgdefCompinfo transStrToCom(){
		return JsonBinder.fromJson(frontObjStr, WfCfgdefCompinfo.class);
	}
	
	
	public void setFrontObjStr(String frontObjStr) {
		this.frontObjStr = frontObjStr;
	}

	public String getFrontObjStr() {
		return frontObjStr;
	}

	
	public IAccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}


	public WfCfgdefCompinfo getWfCfgdefCompinfo() {
		return wfCfgdefCompinfo;
	}

	public void setWfCfgdefCompinfo(WfCfgdefCompinfo wfCfgdefCompinfo) {
		this.wfCfgdefCompinfo = wfCfgdefCompinfo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public WfCfgdefPwd getWfCfgdefPwd() {
		return wfCfgdefPwd;
	}

	public void setWfCfgdefPwd(WfCfgdefPwd wfCfgdefPwd) {
		this.wfCfgdefPwd = wfCfgdefPwd;
	}


	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getPwdTwo() {
		return pwdTwo;
	}

	public void setPwdTwo(String pwdTwo) {
		this.pwdTwo = pwdTwo;
	}

	

	public String getOldPwd() {
		return oldPwd;
	}

	public void setPoService(IPOService poService) {
		this.poService = poService;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getConfimPwd() {
		return confimPwd;
	}

	public void setConfimPwd(String confimPwd) {
		this.confimPwd = confimPwd;
	}
	

}
