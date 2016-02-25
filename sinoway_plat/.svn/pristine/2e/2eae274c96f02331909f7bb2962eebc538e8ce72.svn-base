<%@page import="com.sinoway.common.util.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<c:set var="datetypechar" value="<%=Constant.DataType.DATATYPE_CHAR.getCode() %>"></c:set>
<c:set var="datetypetelno" value="<%=Constant.DataType.DATATYPE_TELNO.getCode() %>"></c:set>
<c:set var="datetypeprsncod" value="<%=Constant.DataType.DATATYPE_PRSNCOD.getCode() %>"></c:set>
<c:set var="datetypepass" value="<%=Constant.DataType.DATATYPE_PASS.getCode() %>"></c:set>
<c:set var="datetypeoddfile" value="<%=Constant.DataType.DATATYPE_ODDFILE.getCode() %>"></c:set>
<c:set var="datetypebank" value="<%=Constant.DataType.DATATYPE_BANK.getCode() %>"></c:set>
<c:set var="datetypepassport" value="<%=Constant.DataType.DATATYPE_PASSPORT.getCode() %>"></c:set>
<c:set var="isnullyes" value="<%=Constant.IsNull.ISNULL_YES.getCode() %>"></c:set>
<c:set var="prsnCredit" value="<%=Constant.RptTyp.RPTTYP_PRSN_CREDIT.getCode() %>"></c:set>
<c:set var="fraud" value="<%=Constant.RptTyp.RPTTYP_FRAUD.getCode() %>"></c:set>
<c:set var="appcod" value="<%=Constant.AppCod.APPINFOCODEE_FAD.getCode() %>"></c:set>
<c:set var="isdefult" value="<%=Constant.IsDefult.ISDEFULT_YES.getCode() %>"></c:set>
<c:set var="veremail" value="<%=Constant.DataType.DATATYPE_EMAIL.getCode() %>"></c:set>
<c:set var="resuccess" value="<%=Constant.ResultStatus.RESULTSTATUS_SUCCESS.getCode() %>"></c:set>
<c:set var="datcmitori" value="<%=Constant.DatCmitori.DatCmitori_PLANT.getCode() %>"></c:set>
<c:set var="issbcyes" value="<%=Constant.IsSbc.ISSBC_YES.getCode() %>"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新报告库上传</title>
<%@ include file="/common/wf_import.jsp"%>
<%@ include file="/windforce/common/jsp/wfInternalImport.jsp" %>

</head> 
<script type="text/javascript">
	var ctx = "${ctx}";
	var datetypechar = "${datetypechar}";
	var datetypetelno = "${datetypetelno}";
	var datetypeprsncod = "${datetypeprsncod}";
	var datetypepass = "${datetypepass}";
	var datetypeoddfile = "${datetypeoddfile}";
	var datetypepassport = "${datetypepassport}";
	var isnullyes = "${isnullyes}";
  	var isdefult = "${isdefult}";
  	var veremail = "${veremail}";
  	var datetypebank = "${datetypebank}";
  	var resuccess  = "${resuccess}";
  	var datcmitori = "${datcmitori}";
  	var issbcyes = "${issbcyes}";
</script>
    <link rel="stylesheet" href="../common/css/rptmain.css">
    <link rel="stylesheet" href="../common/css/bootstrap.min.css">
<script type="text/javascript"
	src='<%=path%>/common/js/jquery/jquery-ui-1.8.18.custom.js'></script>
<script type="text/javascript"
	src='<%=path%>/sinoway/fad/js/addFad.js'></script>
	<script type="text/javascript"
	src='<%=path%>/sinoway/rpt/js/changeHeight.js'></script>
<script type="text/javascript"
	src='<%=path%>/sinoway/common/js/verifrpt.js'></script>
<style type="text/css">
		body{
		background-color: #eeeeee;
		width: 94%;
		height: 80%;
		overflow:auto;
		}
	</style>
<body>
<div class="report_right" >
<div id="prdList"></div>
<span>请填写以下信息项</span>
<div id="htmlVar">

</div>
<input type="hidden" name="appcod" value="${appcod }">

<div id="resultDiv"></div>


              
            
              <div class="template_btm">
                <div style=" width:80%; margin:0 auto;"> 
                 <h4>选择报告模板</h4>
                 <ul id="ulhtml">
                    
                 </ul>
                 <div style="clear:both;"></div>
                </div> 
              </div>
                <hr/>
              <div style="width:94%;height:80%;overflow: auto;">
                <form class="" id="formhtml">
            
             	</form>
             	 <div  style="margin:20% 37%; width:18%;" id="sumbtn">
             
       		</div>
              </div>
              <br/><br/><br/>
             
       <br/>
       <div style="clear:both;color: red;" align="center" id="verifinfo"></div>
          
</div>

</body>
</html>