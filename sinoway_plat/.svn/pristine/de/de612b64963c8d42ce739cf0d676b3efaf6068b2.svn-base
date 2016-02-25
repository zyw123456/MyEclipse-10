<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>华道征信云服务平台</title>
    <style type="text/css">
    html{overflow-x:hidden}
    </style>
	<script type="text/javascript">
	var showNotice = true;
	var msg = "非安全退出,可能造成数据丢失!";
	window.onbeforeunload = function(e) {
	if (showNotice) {
		var ev = e || window.event;
		ev.returnValue = msg;
		return msg;
	}
	};
	</script>
  </head>
  
  <body style=" margin:0px">
    <iframe id="mainframe" src="sinoway/dk/desktop.jsp" scrolling="yes" frameborder="0" style="width:100%; height:100%;border-width:0px" > </iframe>
  </body>
</html>
