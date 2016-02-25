<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String naviMenu = session.getAttribute("_naviMenu").toString();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'middle.jsp' starting page</title>
   
	<link rel="stylesheet" href="sinoway/common/css/main.css">
	<link rel="stylesheet" href="sinoway/common/css/bootstrap.min.css">
	<script type="text/javascript">
		var naviMenu = <%=naviMenu%>;
		var ctx = "<%=path%>";
	</script>
	<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-1.7.1.js'></script>

	<script type="text/javascript"
	src='<%=request.getContextPath()%>/sinoway/dk/js/common.js'></script>
	<script type="text/javascript"
	src='<%=request.getContextPath()%>/sinoway/dk/js/left.js'></script>
	<style type="text/css">
		body{
		background-color: #eeeeee;
		}
	</style>
  </head>
  
  <body>
   	 <div id="secondMenu" class="report_left">
       
     </div> 
  </body>
</html>
