<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.sinoway.common.util.Constant"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<c:set var="datetypechar" value="<%=Constant.DataType.DATATYPE_CHAR.getCode() %>"></c:set>
<c:set var="datetypetelno" value="<%=Constant.DataType.DATATYPE_TELNO.getCode() %>"></c:set>
<c:set var="datetypeprsncod" value="<%=Constant.DataType.DATATYPE_PRSNCOD.getCode() %>"></c:set>
<c:set var="datetypepass" value="<%=Constant.DataType.DATATYPE_PASS.getCode() %>"></c:set>
<c:set var="datetypeoddfile" value="<%=Constant.DataType.DATATYPE_ODDFILE.getCode() %>"></c:set>
<c:set var="datetypebank" value="<%=Constant.DataType.DATATYPE_BANK.getCode() %>"></c:set>
<c:set var="isnullyes" value="<%=Constant.IsNull.ISNULL_YES.getCode() %>"></c:set>
<c:set var="prsnCredit" value="<%=Constant.RptTyp.RPTTYP_PRSN_CREDIT.getCode() %>"></c:set>
<c:set var="fraud" value="<%=Constant.RptTyp.RPTTYP_FRAUD.getCode() %>"></c:set>
<c:set var="datcmitori" value="<%=Constant.DatCmitori.DatCmitori_PLANT.getCode() %>"></c:set>
<c:set var="appcod" value="<%=Constant.AppCod.APPINFOCODEE_RPT.getCode() %>"></c:set>
<c:set var="isdefult" value="<%=Constant.IsDefult.ISDEFULT_YES.getCode() %>"></c:set>
<c:set var="veremail" value="<%=Constant.DataType.DATATYPE_EMAIL.getCode() %>"></c:set>
<c:set var="resuccess" value="<%=Constant.ResultStatus.RESULTSTATUS_SUCCESS.getCode() %>"></c:set>
<c:set var="datetypepassport" value="<%=Constant.DataType.DATATYPE_PASSPORT.getCode() %>"></c:set>
<c:set var="issbcyes" value="<%=Constant.IsSbc.ISSBC_YES.getCode() %>"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人征信报告上传</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
      
    <link rel="stylesheet" href="sinoway/common/css/bootstrap.min.css">
    <link rel="stylesheet" href="sinoway/common/css/rptmain.css">
  <script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-1.7.1.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-ui-1.8.18.custom.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery.json-2.4.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/sinoway/rpt/js/addRpt.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/sinoway/rpt/js/changeHeight.js'></script>
<script type="text/javascript"
		src='<%=request.getContextPath()%>/common/js/ajaxfileupload/ajaxfileupload.js'></script>
<script type="text/javascript"	src='<%=path%>/sinoway/common/rptlib/js/verifrpt.js'></script>
  <script type="text/javascript">
  var ctx ="${ctx}";
  var datetypechar = "${datetypechar}";
  var datetypetelno = "${datetypetelno}";
  var datetypeprsncod = "${datetypeprsncod}";
  var datetypepass = "${datetypepass}";
  var datetypeoddfile = "${datetypeoddfile}"; 
  var isnullyes = "${isnullyes}";
  var appcod = "${appcod}";
  var datcmitori = "${datcmitori}"; 
  var isdefult = "${isdefult}";
  var veremail = "${veremail}";
  var datetypebank = "${datetypebank}";
  var datetypepassport = "${datetypepassport}";
  var issbcyes = "${issbcyes}";
  var resuccess = "${resuccess}";
  </script>
  </head>
  <style type="text/css">
		body{
		background-color: #eeeeee;
		width: 94%;
		}
	</style>
  <body>
  	<div class="report_right" >		
	<input type="hidden" id="newrptid" />
	<input type="hidden" id="rptdte" />
	<input type="hidden" id="rpttim" />
	<input type="hidden" id="datcmitoriid" name="datcmitori" value="${datcmitori}">
   
       <div id="userreport_upload" >
           
           <div class="">
              <div>
                <form  id="formhtml">
                	      
				
              
               
                </form>
                 
              </div>
        
              <div class="template_btm">
                <div style=" width:80%; margin:0 auto;">
                 <div class="col-md-12 col-sm-12 col-sm-offset-0 form-group"> <hr/> </div>
                 <div class="col-md-11 col-sm-11 col-sm-offset-1 form-group">
                   
                  <h3>选择报告模板</h3>
                 </div>
                 <ul id="ulhtml">
                 </ul>
                 <div style="clear:both;"></div>
                </div> 
              </div>
              <br/>
              <div  style="margin:0 37%; width:18%;" id="sumbtn">
             
              <div>
           </div>
       </div>
    <br/>   <div style="clear:both;color: red;" align="center" id="verifinfo"></div>
       
     </div>

    </div>
  
      </div> 			
    
  </body>
</html>
