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

<title>页顶</title>
<link rel="stylesheet" href="sinoway/common/css/main.css">
<link rel="stylesheet" href="sinoway/common/css/bootstrap.min.css">
<script type="text/javascript">
		var naviMenu = <%=naviMenu%>;
	</script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-1.7.1.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery.json-2.4.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/wf/uiTools.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/sinoway/dk/js/common.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/sinoway/dk/js/top.js'></script>
</head>

<body>
	<div id="logo">
		<img alt="logo" src="sinoway/common/images/login_home.png" />
		<ul class="nav nav-pills nav-justified" id='firstMenu'>

		</ul>
	</div>
</body>
</html>
