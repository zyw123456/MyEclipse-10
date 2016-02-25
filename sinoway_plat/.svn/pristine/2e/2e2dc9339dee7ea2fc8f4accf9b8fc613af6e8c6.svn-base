<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'warnMonitor.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-1.7.1.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-ui-1.8.18.custom.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery.json-2.4.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/sinoway/wrn/js/wrnPersonList.js'></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/windforce/common/js/My97DatePicker/WdatePicker.js"></script>
  </head>
  <body>
  <div style="width: 1000px;" align="center">
  &nbsp;&nbsp;姓名：    <input type="text" id="prsnnam" name="prsnnam" />
  身份证：<input type="text" id="prsncod" name="prsncod"/>
监控区间：<input type="text" id="loansrtdte" name="loansrtdte" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMMdd',maxDate:'%y-%M-%d'});" /> 
	  至 <input type="text" id="loanenddte" name="loanenddte" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMMdd',maxDate:'%y-%M-%d'});" /><br/><br/>
监控模块：
		<c:forEach  items="${list }" var="item" varStatus="status">
			
			<input name='trncod' type='checkbox' value="${item[0] }" />${item[1] }
		</c:forEach>
<span id="jkmb"></span><br/><br/>
<input type="button" value="搜索" onclick="findMonitorNameList();" />
    <table  id="tabfl" border="1" style="border: 1px solid; border-collapse: collapse;" align="center" width="700">
    	
    </table>
   </div>
  </body>
</html>
