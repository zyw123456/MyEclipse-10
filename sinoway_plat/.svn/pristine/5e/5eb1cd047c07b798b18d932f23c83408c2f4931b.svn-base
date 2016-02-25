<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String errCode = request.getAttribute("errCode").toString();
	String errMsg = request.getAttribute("errMsg").toString();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">

<title>系统异常处理界面</title>
<link type="text/css" rel="stylesheet"
	href="<%=path%>/sunflower/v1/common/css/tdk/dialog.css" /></link>
<link type="text/css" rel="stylesheet"
	href="<%=path%>/sunflower/v1/common/css/tdk/tdk.css"></link>
	<script>
	
		try{
		parent.closeCurrDialog();	
		}catch(e)
		{	
		} 
	</script>
</head>

<body style="text-align:center;vertical-align:middle;">
<div style="padding-top:20%;">
	<table style="text-align:center;margin-left:auto;margin-right:auto;">
		<tr>
			<td><span class="exception"></span></td>
			
		</tr>
		<tr><td style="color:orange;font-size:32px;font-weight:800;">抱歉,系统异常!</td></tr>
		<tr><td style="color:orange;font-size:26px;font-weight:300;"><%=errCode%>:<%=errMsg%></td></tr>
	</table>
	<%-- <div>
		
	</div>
	<%=errCode%>
	:
	<%=errMsg%> --%>
	</div>
</body>
</html>
