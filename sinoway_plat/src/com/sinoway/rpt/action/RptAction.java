package com.sinoway.rpt.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.tools.ant.util.Base64Converter;

import sun.misc.BASE64Encoder;

import com.sinoway.common.entity.HttpCommonEntity;
import com.sinoway.common.entity.HttpResponseCommonEntity;
import com.sinoway.common.entity.WfCfgrefTrninele;
import com.sinoway.common.service.IHttpProviderService;
import com.sinoway.common.util.BankCardBin;
import com.sinoway.common.util.Constant;
import com.sinoway.common.util.DateUtil;
import com.sinoway.common.util.FileConstant;
import com.sinoway.common.util.HttpParamUtil;
import com.sinoway.common.util.JRNGenerator;
import com.sinoway.common.util.JsonBinder;
import com.sinoway.common.util.Constant.IsBatch;
import com.sinoway.common.util.Constant.MsgType;
import com.sinoway.common.util.Constant.RptStatus;
import com.sinoway.common.util.VerificationForm;
import com.sinoway.fad.service.impl.FraudService;
import com.sinoway.rpt.entity.WfDatCreditrpt;
import com.sinoway.rpt.entity.WfDatCreditrptUtil;
import com.sinoway.rpt.service.IRptService;
import com.sinoway.stm.service.IStmManagerService;
import com.yzj.wf.base.action.BaseAction;
import org.apache.commons.lang3.StringUtils;

import com.yzj.wf.common.WFLogger;
import com.yzj.wf.common.cache.Cache;
import com.yzj.wf.common.cache.CacheEnum;
import com.yzj.wf.common.cache.CacheFactory;
import com.yzj.wf.core.model.po.wrapper.XPeopleInfo;
import com.yzj.wf.plat.entity.WfCfgrefPrddetil;

/** 
 * @ClassName: RptAction 
 * @Description: (报告库管理) 
 * @author 于辉
 * @date 2015-12-16
 *  
 */
public class RptAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private WFLogger logger = WFLogger.getLogger(RptAction.class);
	private FraudService fraudService;
	@SuppressWarnings("unused")
	private IStmManagerService stmManagerService;
	private IRptService rptService;
	private IHttpProviderService httpProviderService;
	private String wfDate;
	Base64Converter baseCov = null;
	
	
	/** 
	* @Title: queryBank 
	* @Description:(根据卡bin查询银行) 
	* @param  
	* @return JSON     
	* @throws 于辉
	*/
	public String queryBank(){
		WfDatCreditrptUtil trnObj = transStrToObj();
		String bankName = BankCardBin.getNameOfBank(trnObj.getBankCode().substring(0, 6), 0);
		trnObj.setBankName(bankName);
		tranObjToStr(trnObj);
		return SUCCESS;
	}
	/** 
	* @Title: findCredItRpt 
	* @Description:(查询产品数据) 
	* @param  peopleCode 用户编号 
	* @return JSON     
	* @throws 于辉
	*/
	@SuppressWarnings("rawtypes")
	public String findCredItRpt(){
		WfDatCreditrptUtil trnObj = transStrToObj();
		XPeopleInfo cp= this.getCurrentPeople();
		List trns = rptService.findCreadItRpt(cp.getPeopleCode(),trnObj.getAppcod());
		trnObj.setTrns(trns);
		tranObjToStr(trnObj);
		return SUCCESS;
	}
	/** 
	* @Title: findCredItRpt 
	* @Description:(查询产品数据去掉原有产品) 
	* @param  peopleCode 用户编号 
	* @return JSON     
	* @throws 于辉
	*/
	@SuppressWarnings("rawtypes")
	public String findCredItRptOld(){
		WfDatCreditrptUtil trnObj = transStrToObj();
		XPeopleInfo cp= this.getCurrentPeople();
		List trns = rptService.findCreadItRptOld(cp.getPeopleCode(),trnObj.getAppcod(),trnObj.getOldprdcod());
		trnObj.setTrns(trns);
		tranObjToStr(trnObj);
		return SUCCESS;
	}
	/** 
	* @Title: verifUploadInfo 
	* @Description:(验证上传信息) 
	* @return JSON     
	 * @throws Exception 
	* @throws 于辉
	*/
	@SuppressWarnings("rawtypes")
	public String verifUploadInfo() throws Exception{
		WfDatCreditrptUtil trnObj = transStrToObj();
		XPeopleInfo cp= this.getCurrentPeople();
		if(StringUtils.isBlank(trnObj.getRptdte())){
			trnObj.setRptdte(DateUtil.getCurrentDate8Len());
		}
		if(StringUtils.isBlank(trnObj.getRpttim())){
			trnObj.setRpttim(DateUtil.getCurrentTimeHMSS());
		}
		JSONObject jsObj = new JSONObject();
		//header拼接		固定元素JSON
		JSONObject jheader = new JSONObject();
		jheader.put("clntjrn", trnObj.getNewrptid());				  	//云平台报告编号
		jheader.put("clnttrndte", trnObj.getRptdte());	//云平台处理日期
		jheader.put("clnttrntime", trnObj.getRpttim());	//云平台处理时间
		jheader.put("fnttrnjrn",  "");	//前置流水
		jheader.put("frnttrndte", "");	//云平台处理时间
		jheader.put("frnttrntim", "");	//云平台处理时间
		jheader.put("orgno", cp.getCorgNo()==null?"":cp.getCorgNo());		//核心机构号 
		jheader.put("subusrid", cp.getUsrId());						//父用户 
		jheader.put("usrid", (cp.getP_usrId() == null || "".equals(cp.getP_usrId() )) ? cp.getUsrId() : cp.getP_usrId());					 	//核心用户编码 
		jheader.put("datori", trnObj.getDatcmitori());
		//body拼接  动态元素JSON
		JSONObject jbody = new JSONObject();
	
		//获取页面元素
		JSONObject jo = JSONObject.fromObject("{"+trnObj.getDatavar().substring(0,trnObj.getDatavar().length()-1)+"}");
		JSONArray japar =JSONArray.fromObject(trnObj.getJsonpar());
		List listJson =(List) JSONArray.toCollection(japar,WfCfgrefTrninele.class);
		Iterator itJson = listJson.iterator();
		
		//原报告没有元素JSON拼接
		while (itJson.hasNext()) {
			WfCfgrefTrninele wct = (WfCfgrefTrninele)itJson.next();
			if(wct.getElecod().equals("prsnnam")){
				String resPrsn = VerificationForm.VerIfPrsnNam(jo.get(wct.getElecod()).toString());
				 if(!resPrsn.equals(Constant.ResultStatus.RESULTSTATUS_SUCCESS.getCode())){
					 logger.error(resPrsn);
					 trnObj.setRetPam(resPrsn);
					 tranObjToStr(trnObj);
					 return SUCCESS;
				 }
				trnObj.setPrsnnam(jo.get(wct.getElecod()).toString());
				jbody.put(wct.getElecod(), jo.get(wct.getElecod()));
			}else if(wct.getElecod().equals("idcard")){
				 String resPrsncod = VerificationForm.VerIfPrsnCod(jo.get(wct.getElecod()).toString());
				 if(!resPrsncod.equals(Constant.ResultStatus.RESULTSTATUS_SUCCESS.getCode())){
					 logger.error(resPrsncod);
					 trnObj.setRetPam(resPrsncod);
					 tranObjToStr(trnObj);
					 return SUCCESS;
				 }
				trnObj.setPrsncod(jo.get(wct.getElecod()).toString());
				jbody.put(wct.getElecod(), jo.get(wct.getElecod()));
			}
		}
	
		
		jsObj.put("header", jheader);
		jsObj.put("body", jbody);
		//请求核心接口
		//生成传参实体
		HttpCommonEntity entity = HttpParamUtil.generateRptRequest(null,Constant.getTrncods(),IsBatch.IS_NO.getValue(),MsgType.MESSAGE_TYPE_REQUEST.getValue(), jsObj.toString());
		//向核心发起HTTP请求,并接收响应报文
		entity = this.httpProviderService.httpCommunicate(entity);
		if(null == entity){
	    	 logger.error("请求核心接口核心返回为空！");
	    	 trnObj.setRetPam("请求核心接口核心返回为空！");
	    	 tranObjToStr(trnObj);
			 return SUCCESS;
	    }
		HttpResponseCommonEntity resEntity =  entity.getResponse();
		String message = (String) resEntity.getReturnObj();
		if(StringUtils.isBlank(message)){
			logger.error("请求核心接口核心返回报文为空！");
			trnObj.setRetPam("请求核心接口核心返回报文为空！");
			tranObjToStr(trnObj);
			return SUCCESS;
		}
		//拼接报文
		JSONObject jsonObj =JSONObject.fromObject(message);
		JSONObject header = JSONObject.fromObject(jsonObj.get("header"));
		String status = header.get("status").toString();
		if(status.equals(Constant.ResultStatus.RESULTSTATUS_SUCCESS.getCode())){
			trnObj.setRetPam("验证结果：成功");
		}else if(status.equals(Constant.ResultStatus.RESULTSTATUS_INTERFACEFAIL.getCode())){
			trnObj.setRetPam(header.getString("result"));
		}
		tranObjToStr(trnObj);
		return SUCCESS;
	}	
	/** 
	* @Title: addCredItRpt 
	* @Description: (新增征信报告) 
	* @return JSON     
	 * @throws Exception 
	* @throws 于辉
	*/
	@SuppressWarnings({ "null", "unchecked", "rawtypes" })
	public String addCredItRpt() throws Exception{
		WfDatCreditrptUtil trnObjRes = transStrToObj();
		XPeopleInfo cp= this.getCurrentPeople();
		WfDatCreditrpt trnObj = new WfDatCreditrpt();
		String pcode = (null == cp.getParntCode() || "".equals(cp.getParntCode()))?cp.getPeopleCode():cp.getParntCode();
		if(StringUtils.isBlank(trnObjRes.getNewrptid())){
			trnObjRes.setNewrptid(JRNGenerator.generateJrn("P", "1", "00000000"));
		}
		if(StringUtils.isBlank(trnObjRes.getRptdte())){
			trnObjRes.setRptdte(DateUtil.getCurrentDate8Len());
		}
		if(StringUtils.isBlank(trnObjRes.getRpttim())){
			trnObjRes.setRpttim(DateUtil.getCurrentTimeHMSS());
		}
		if(trnObjRes.getPrttyp().equals(Constant.PrdTyp.PRDTYP_RPT.getCode())){
			trnObj.setRpttyp(Constant.RptTyp.RPTTYP_PRSN_CREDIT.getCode());
			trnObj.setRptnam(Constant.RptTyp.RPTTYP_PRSN_CREDIT.getValue());
		}else if(trnObjRes.getPrttyp().equals(Constant.PrdTyp.PRDTYP_FAD.getCode())){
			trnObj.setRpttyp(Constant.RptTyp.RPTTYP_FRAUD.getCode());
			trnObj.setRptnam(Constant.RptTyp.RPTTYP_FRAUD.getValue());
		}else if(trnObjRes.getPrttyp().equals(Constant.PrdTyp.PRDTYP_VERIF.getCode())){
			trnObj.setRpttyp(Constant.RptTyp.RPTTYP_VERIFIED.getCode());
			trnObj.setRptnam(Constant.RptTyp.RPTTYP_VERIFIED.getValue());
		}
		JSONObject jsObj = new JSONObject();
		//header拼接		固定元素JSON
		JSONObject jheader = new JSONObject();
		jheader.put("clntjrn", trnObjRes.getNewrptid());				  	//云平台报告编号
		jheader.put("clnttrndte", trnObjRes.getRptdte());	//云平台处理日期
		jheader.put("clnttrntime", trnObjRes.getRpttim());	//云平台处理时间
		jheader.put("fnttrnjrn",  "");	//前置流水
		jheader.put("frnttrndte", "");	//云平台处理时间
		jheader.put("frnttrntim", "");	//云平台处理时间
		jheader.put("orgno", cp.getCorgNo()==null?"":cp.getCorgNo());		//核心机构号 
		jheader.put("subusrid", cp.getPeopleCode());						//父用户 
		jheader.put("userid", pcode==null?"":pcode);			//核心用户编码 
		jheader.put("datori", trnObjRes.getDatcmitori());
		//body拼接  动态元素JSON
		JSONObject jbody = new JSONObject();
		//得到产品原交易(缓存形式获取)
		Cache cache = CacheFactory.getCacheInstance(CacheEnum.PrdDetilCache.getValue());
		if(cache == null){
			cache.setAvailable(false);//下次从cache中获取对象时,会重新load数据
			cache = CacheFactory.getCacheInstance(CacheEnum.PrdDetilCache.getValue());
		}
		//获得所有的实体		
		List<WfCfgrefPrddetil> prdDetils= (List<WfCfgrefPrddetil>) cache.getRealValues();
		//获得本产品拥有的原交易
		List<String> trncods = new ArrayList<String>();
		for(WfCfgrefPrddetil prddtl : prdDetils){
			if (prddtl.getSta().equals("1") && prddtl.getPrdcod().equals(trnObjRes.getPrdcod())) {
				trncods.add(prddtl.getTrncod());
			}
		}
		
		//将原交易转换为
		String trncodsStr = StringUtils.join(trncods.toArray(),",");
		//拼接交易码
		jbody.put("trncods", trncodsStr);//交易码
		JSONObject jtrninfo = new JSONObject();
		//获取页面元素
		JSONObject jo = JSONObject.fromObject("{"+trnObjRes.getDatavar().substring(0,trnObjRes.getDatavar().length()-1)+"}");
		JSONArray japar =JSONArray.fromObject(trnObjRes.getJsonpar());
		List listJson =(List) JSONArray.toCollection(japar,WfCfgrefTrninele.class);
		Iterator itJson = listJson.iterator();
	
		//原报告没有元素JSON拼接
		while (itJson.hasNext()) {
			WfCfgrefTrninele wct = (WfCfgrefTrninele)itJson.next();
			if(wct.getElecod().equals("prsnnam")){
				String resPrsn = VerificationForm.VerIfPrsnNam(jo.get(wct.getElecod()).toString());
				 if(!resPrsn.equals(Constant.ResultStatus.RESULTSTATUS_SUCCESS.getCode())){
					 logger.error(resPrsn);
					 trnObjRes.setRetPam(resPrsn);
					 tranObjToStr(trnObjRes);
					 return SUCCESS;
				 }
				trnObj.setPrsnnam(jo.get(wct.getElecod()).toString());
				 jtrninfo.put(wct.getElecod(), jo.get(wct.getElecod()));
			}else if(wct.getElecod().equals("idcard")){
				 String resPrsncod = VerificationForm.VerIfPrsnCod(jo.get(wct.getElecod()).toString());
				 if(!resPrsncod.equals(Constant.ResultStatus.RESULTSTATUS_SUCCESS.getCode())){
					 logger.error(resPrsncod);
					 trnObjRes.setRetPam(resPrsncod);
					 tranObjToStr(trnObjRes);
					 return SUCCESS;
				 }
				trnObj.setPrsncod(jo.get(wct.getElecod()).toString());
				jtrninfo.put(wct.getElecod(), jo.get(wct.getElecod()));
			}else if(wct.getElecod().equals("mobile")){
				String resTelno = VerificationForm.VerIfTelNo(jo.get(wct.getElecod()).toString());
				 if(!resTelno.equals(Constant.ResultStatus.RESULTSTATUS_SUCCESS.getCode())){
					 logger.error(resTelno);
					 trnObjRes.setRetPam(resTelno);
					 tranObjToStr(trnObjRes);
					 return SUCCESS;
				 }
				trnObj.setTelno(jo.get(wct.getElecod()).toString());
				jtrninfo.put(wct.getElecod(), jo.get(wct.getElecod()));
			}else if(wct.getElecod().equals("cbcreports")){
				jtrninfo.put(wct.getElecod(), trnObjRes.getCbcreports());
			}else{
				jtrninfo.put(wct.getElecod(), jo.get(wct.getElecod()));
			}
		}
	
		jbody.put("trninfo", jtrninfo);
		jsObj.put("header", jheader);
		jsObj.put("body", jbody);
		trnObj.setRptid(trnObjRes.getNewrptid());
		trnObj.setPrdcod(trnObjRes.getPrdcod());
		trnObj.setPrdnam(trnObjRes.getPrdnam());
		
		trnObj.setParntcode(pcode);
		trnObj.setPeoplecode(cp.getPeopleCode());
		trnObj.setOrgno(cp.getOrgNo());//机构号
        trnObj.setUsrid(cp.getUsrId());//报告查询核心人员编码
		trnObj.setP_usrid((null == cp.getP_usrId()||"".equals(cp.getP_usrId()))?cp.getUsrId():cp.getP_usrId());//报告查询核心父人员编码
		trnObj.setCorgno(cp.getCorgNo());//报告查询核心机构号
		trnObj.setCporgno((null == cp.getCporgNo() || "".equals(cp.getCporgNo()))?cp.getCorgNo():cp.getCporgNo());//报告查询核心父机构号
	
		trnObj.setRptdte(trnObjRes.getRptdte());
		trnObj.setRpttim(trnObjRes.getRpttim());
		trnObj.setRptmoddte(trnObjRes.getRptdte());
		trnObj.setRptmodtim(trnObjRes.getRpttim());
		trnObj.setDatcmitori(trnObjRes.getDatcmitori());
		String credtype =Constant.CredTyp.CREDTYP_PRSN.getCode();
		trnObj.setCredtyp(credtype);
		//拼装请求报文
		String json =jsObj.toString();
		//报文生成xml文件
		String rqd = FileConstant.createReqFile(json,trnObjRes.getNewrptid(),trnObjRes.getRptdte(),trnObjRes.getRpttim());
		//获取报文存放路径
		trnObj.setReqadrr(rqd);
		//请求核心接口
		//生成传参实体
		HttpCommonEntity entity = HttpParamUtil.generateRptRequest(Constant.getChnlcode(),null,IsBatch.IS_NO.getValue(),MsgType.MESSAGE_TYPE_REQUEST.getValue(), json);
		//向核心发起HTTP请求,并接收响应报文
		entity = this.httpProviderService.httpCommunicate(entity);
		if(null == entity){
	    	 logger.error("请求核心接口核心返回为空！");
	    	 trnObjRes.setRetPam("请求核心接口核心返回为空！");
	    	 tranObjToStr(trnObjRes);
			 return SUCCESS;
	    }
		HttpResponseCommonEntity resEntity =  entity.getResponse();
		
		String message = (String) resEntity.getReturnObj();
		if(StringUtils.isBlank(message)){
			logger.error("请求核心接口核心返回报文为空！");
			trnObjRes.setRetPam("请求核心接口核心返回报文为空！");
			tranObjToStr(trnObjRes);
			return SUCCESS;
		}
		JSONObject retjo =JSONObject.fromObject(message);
		JSONObject herder = JSONObject.fromObject(retjo.get("header"));
		if(herder.getString("status").equals(Constant.ResultStatus.RESULTSTATUS_SUCCESS.getCode())){
			trnObj.setFntjrn(herder.getString("fnttrnjrn"));//获取前置流水号
			trnObj.setRptsta(RptStatus.STATIS_QUERYIN.getCode());
			this.fraudService.createReport(trnObj);
			trnObjRes.setRetPam(Constant.ResultStatus.RESULTSTATUS_SUCCESS.getCode());
		}else{
			trnObjRes.setRetPam(herder.getString("result"));	
		}
		tranObjToStr(trnObjRes);
		return SUCCESS;
	}
	
	/** 
	* @Title: rtpFileUpload 
	* @Description: (征信报告上传功能) 
	* @return JSON     
	 * @throws Exception 
	 * @throws 于辉
	*/
	public String rtpFileUpload() throws Exception{
		WfDatCreditrptUtil trnObj = transStrToObj();
		String rptId = JRNGenerator.generateJrn("P", "1", "00000000");
		String cde = DateUtil.getCurrentDate().replaceAll("-", "");
		String cts = DateUtil.getCurrentTimeMillis().replaceAll(":", "").replaceAll(" ", "");
		String year = cde.substring(0, 4);
		String mon  = cde.substring(4, 6);
		String day  = cde.substring(6, 8);
		String hour = cts.substring(0, 2);
		String min  = cts.substring(2, 4);
		String path = Constant.getMsgUploadPath() +year+"\\"+mon+"\\"+day+"\\"+hour+"\\"+min+"\\"+rptId;
		File file = new File(path);
		if(!file.exists() && !file.isDirectory()){
			file.mkdirs();
		}
        try {  
            File f = trnObj.getFile();  
            FileInputStream inputStream = new FileInputStream(f);  
            FileOutputStream outputStream = new FileOutputStream(path + "/"+ trnObj.getFileFileName());  
            byte[] buf = new byte[1024];
            int length = 0;  
            while ((length = inputStream.read(buf)) != -1) {  
                outputStream.write(buf, 0, length);  
            }  
            String fls = new BASE64Encoder().encode(buf);
            //base64转码
            trnObj.setFileStream(fls);
            trnObj.setRptid(rptId);
            trnObj.setRptdte(cde);
            trnObj.setRpttim(cts);
            trnObj.setRetPam(Constant.ResultStatus.RESULTSTATUS_SUCCESS.getCode());
            inputStream.close();  
            outputStream.flush();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        tranObjToStr(trnObj);
        return SUCCESS;  
	}
	

	private WfDatCreditrptUtil transStrToObj(){
		return JsonBinder.fromJson(wfDate, WfDatCreditrptUtil.class);
	}
	private void tranObjToStr(WfDatCreditrptUtil trnObj){
		wfDate=JsonBinder.toJson(trnObj);
	}
	
	public String getWfDate() {
		return wfDate;
	}

	public void setWfDate(String wfDate) {
		this.wfDate = wfDate;
	}

	public void setRptService(IRptService rptService) {
		this.rptService = rptService;
	}

	public void setFraudService(FraudService fraudService) {
		this.fraudService = fraudService;
	}
	public void setHttpProviderService(IHttpProviderService httpProviderService) {
		this.httpProviderService = httpProviderService;
	}

	

	public void setStmManagerService(IStmManagerService stmManagerService) {
		this.stmManagerService = stmManagerService;
	}
}
