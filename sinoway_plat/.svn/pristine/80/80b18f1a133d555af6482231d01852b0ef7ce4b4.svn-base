<%@page import="com.sinoway.common.util.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<c:set var="datetypechar" value="<%=Constant.DataType.DATATYPE_CHAR.getCode() %>"></c:set>
<c:set var="datetypetelno" value="<%=Constant.DataType.DATATYPE_TELNO.getCode() %>"></c:set>
<c:set var="datetypeprsncod" value="<%=Constant.DataType.DATATYPE_PRSNCOD.getCode() %>"></c:set>
<c:set var="datetypepass" value="<%=Constant.DataType.DATATYPE_PASS.getCode() %>"></c:set>
<c:set var="datetypeoddfile" value="<%=Constant.DataType.DATATYPE_ODDFILE.getCode() %>"></c:set>
<c:set var="datetypepassport" value="<%=Constant.DataType.DATATYPE_PASSPORT.getCode() %>"></c:set>
<c:set var="isnullyes" value="<%=Constant.IsNull.ISNULL_YES.getCode() %>"></c:set>
<c:set var="prsnCredit" value="<%=Constant.AppCod.APPINFOCODEE_RPT.getCode() %>"></c:set>
<c:set var="prsnCreditValue" value="<%=Constant.AppCod.APPINFOCODEE_RPT.getValue() %>"></c:set>
<c:set var="fraud" value="<%=Constant.AppCod.APPINFOCODEE_FAD.getCode() %>"></c:set>
<c:set var="fraudValue" value="<%=Constant.AppCod.APPINFOCODEE_FAD.getValue() %>"></c:set>
<c:set var="isdefult" value="<%=Constant.IsDefult.ISDEFULT_YES.getCode() %>"></c:set>
<c:set var="veremail" value="<%=Constant.DataType.DATATYPE_EMAIL.getCode() %>"></c:set>
<c:set var="datetypebank" value="<%=Constant.DataType.DATATYPE_BANK.getCode() %>"></c:set>
<c:set var="resuccess" value="<%=Constant.ResultStatus.RESULTSTATUS_SUCCESS.getCode() %>"></c:set>
<c:set var="datcmitori" value="<%=Constant.DatCmitori.DatCmitori_PLANT.getCode() %>"></c:set>
<c:set var="issbcyes" value="<%=Constant.IsSbc.ISSBC_YES.getCode() %>"></c:set>
<c:set var="normal" value="<%=Constant.getNormal() %>"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-1.7.1.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-ui-1.8.18.custom.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery.json-2.4.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/sinoway/fad/js/fadTransfer.js'></script>
	<script type="text/javascript"
	src='<%=request.getContextPath()%>/sinoway/fad/js/transHeight.js'></script>
<script type="text/javascript"
		src='<%=request.getContextPath()%>/common/js/ajaxfileupload/ajaxfileupload.js'></script>
<script type="text/javascript"
	src='<%=path%>/sinoway/common/js/verifrpt.js'></script>
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
  	var fraudValue = "${fraudValue}";
  	var prsnCreditValue = "${prsnCreditValue}";
  	var fraud =  "${fraud}";
  	var datetypebank = "${datetypebank}";
  	var resuccess = "${resuccess}";
  	var datcmitori = "${datcmitori}";
  	var issbcyes = "${issbcyes}";
  	var normal = "${normal}";
</script>
<link rel="stylesheet" href="../common/css/rptmain.css">
<link rel="stylesheet" href="../common/css/bootstrap.min.css">
<style type="text/css">
		body{
		background-color: #eeeeee;
		width: 94%;
		}
	</style>
  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新报告库上传</title>
</head>

<body>
<div class="report_right" >
<h1 align="center">报告流转</h1>
<%  String sty = request.getParameter("prsnnam");
	String prsnnam = new String(sty.getBytes("ISO-8859-1"), "UTF-8");    %>
<h4>报告号：<span id="rptid"><%=request.getParameter("rptid") %> </span>&nbsp;&nbsp;
姓名：<span id="prsnnam"><%=prsnnam  %></span> &nbsp;&nbsp;
身份证号：<span id="prsncod"><%=request.getParameter("prsncod") %></span> 
</h4>
<input type="hidden" id="appcod" />
<input type="hidden" id="newrptid" />
<input type="hidden" id="rptdte" />
<input type="hidden" id="rpttim" />
<input type="hidden" id="oldprdcod" value="<%=request.getParameter("prdcod") %>"/>
<input type="hidden" id="reqaddr" value="<%=request.getParameter("reqaddr") %>"/>
<input type="hidden" id="rtpadrr" value="<%=request.getParameter("rtpadrr") %>"/>
 				<div class="template_btm">
                <div style=" width:80%; margin:0 auto;"> 
                 <h4>现有已完成验证报告的生成，请您选择报告流转的模块</h4>
                 <ul>
                    <li><a name="appcodcss" href="##" id="${fraud }" onclick="showPrdCod('${fraud }');">${fraudValue }</a></li>
                    <li><a name="appcodcss" class="Custom_te" href="##" id="${prsnCredit }" onclick="showPrdCod('${prsnCredit }');">${prsnCreditValue }</a></li>
                 </ul>
                 <div style="clear:both;"></div>
                </div> 
              </div>



 			  <div class="template_btm" id="uldiv">
                <div style=" width:80%; margin:0 auto;"> 
                 <h4 id="h4"></h4>
                 <ul id="ulhtml">
                    
                 </ul>
                 <div style="clear:both;"></div>
                </div> 
              </div>
                <hr/>
              <div>
                <form  id="formhtml">
            
             	</form>
              </div>
              <br/>
              <div  style="margin:10% 30%; width:18%;" id="btn">
              
           </div>
       <br/>
       <div style="clear:both;color: red;" align="center" id="verifinfo"></div>

	</div>
</body>
</html>