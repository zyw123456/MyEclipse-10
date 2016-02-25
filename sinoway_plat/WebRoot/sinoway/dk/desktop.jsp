<%@page import="com.yzj.wf.core.model.po.wrapper.XPeopleInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
/* String needChangePassword = request.getAttribute("needChangePassword").toString(); */
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>华道征信首页</title>
	<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-1.7.1.js'></script>
	<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery.json-2.4.js'></script>
	<script type="text/javascript">
		<%-- var needChangePassword = "<%=needChangePassword%>"; --%>
	</script>
	<script type="text/javascript"
	src='<%=request.getContextPath()%>/sinoway/dk/js/common.js'></script>
	<script type="text/javascript"
	src='<%=request.getContextPath()%>/sinoway/dk/js/desktop.js'></script>
  </head>
  	<frameset rows="200px,*,100px" border="0" id="mainlist">
  		<frame name="top"  scrolling="no" src="sinoway/dk/top.jsp" />
  			<frameset cols="20%,*" border="0" id="midFrame">
	  			<frame name="left" scrolling="no" src="sinoway/dk/left.jsp" />
	  			<frame name="right" id="rightFrame" scrolling="no" src="sinoway/dk/right.jsp" />
  			</frameset>
  		<frame name="bottom" scrolling="no" src="sinoway/dk/bottom.jsp" />
  	</frameset>
</html>
