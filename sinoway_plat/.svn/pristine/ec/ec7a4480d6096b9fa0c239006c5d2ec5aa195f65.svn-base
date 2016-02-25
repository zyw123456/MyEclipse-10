<%@page import="java.net.URLEncoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'warnMonitorEdit.jsp' starting page</title>
    
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
	src='<%=request.getContextPath()%>/sinoway/wrn/js/wrnMonitorEdit.js'></script>
 
  </head>
  
  <body>
 		<input id="id" value="<%=request.getParameter("id") %>" type="hidden"/>
 		<input id="oldprdcod" value="<%=request.getParameter("prdcod") %>" type="hidden"/>  
 		 	<% String strPtname = request.getParameter("prsnnam");
 	   String prsnnam = new String(strPtname.getBytes("ISO-8859-1"), "UTF-8");    %>
 	姓名：	<input id="prsnnam" value="<%=prsnnam 	%>" />  

 	身份证：	<input id="prsncod" value="<%=request.getParameter("prsncod") %>" />  <br/>
 		
 		<div id="mb"></div><br/>
 		<input type="button" value="保存" onclick="updaWarMonitor();" />
  </body>
</html>
