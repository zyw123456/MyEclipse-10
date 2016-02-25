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
	src='<%=request.getContextPath()%>/sinoway/wrn/js/wrnMonitorStop.js'></script>
 
  </head>
  
  <body>
 		
 		 	<% String strPtname = request.getParameter("prsnnam");
		 	   String prsnnam = new String(strPtname.getBytes("ISO-8859-1"), "UTF-8");   
		 	   String sty = request.getParameter("loantyp");
		 	   String loantyp = new String(sty.getBytes("ISO-8859-1"), "UTF-8");   
		 	     String pd = request.getParameter("prdnam");
		 	   String prdnam = new String(pd.getBytes("ISO-8859-1"), "UTF-8");
		 	   String prdcod = request.getParameter("prdcod");  
 	    %>
 	    <input id="prdcod" value="<%=prdcod %>" type="hidden" />
    姓名：	<input id="prsnnam" value="<%=prsnnam 	%>" />  <br/>
    身份证：	<input id="prsncod" value="<%=request.getParameter("prsncod") %>" />  <br/>
    监控区间：<input id="loansrtdte" value="<%=request.getParameter("loansrtdte") %>" />  <br/>
    贷款类型：<input id="loantyp" value="<%=loantyp %>" /> <br/>
    监控模块：<input id="prdnam" value="<%=prdnam %>" /> <br/>
    	<input id="id" value="<%=request.getParameter("id") %>" type="hidden"/>
 		<input type="button" value="确认终止" onclick="updaWarMonitor();" />
  </body>
</html>
