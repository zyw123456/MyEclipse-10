<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>">
    <title>预警详情</title>
	
	<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-1.7.1.js'></script>
	<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-ui-1.8.18.custom.js'></script>
	<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery.json-2.4.js'></script>
	
  </head>
 
  <body>
  	<h2>预警详情查看</h2>
    <br>
    <div>
	  		<div colspan="6" id="trnList" >
       		</div>
  	</div>
  	<script type="text/javascript">
  		var warnid = <%=request.getParameter("warnid") %>;
  	</script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/sinoway/wrn/js/wrnDetail.js"></script>
  </body>
  
</html>
