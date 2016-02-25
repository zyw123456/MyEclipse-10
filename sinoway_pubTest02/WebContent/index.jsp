<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
	<script type="text/javascript"
	src='${ctx}/jquery-1.7.1.js'></script>
	<script type="text/javascript"
	src='${ctx}/WEB-INF/jquery.json-2.4.js'></script>
	<script type="text/javascript"
	src='${ctx}/index.js'></script>
	
	
	
  </head>
  
  <body id ="body">
     <center>  
        <h1>添加角色</h1>  
       	<form action="add_roleInfo" method="post">
       	
       	<fieldset>
       	<ul style="margin:0px;list-style:none;">
       		<li>角色名称:<input  id="test1" type="text" name="roleInfo.rolenam"/></li>
       		<li>角色编码:<input id="test2"  type="text" name="roleInfo.rolecod"/></li>       		
       		<li><input type="submit" value="添加"></li>
       	</ul>      
       	
       	
       	<table>
		  <tr><td >Header 1</td></tr>
		  <tr><td>Value 1</td></tr>
		  <tr><td>Value 2</td></tr>
		  
		  <td><input type="checkbox" name="newsletter" value="Hot Fuzz" />
<input type="checkbox" name="newsletter" value="Cold Fusion" />
<input type="checkbox" name="accept" value="Evil Plans" /></td>
		</table>
       	
       	<div id="infoHtml"></div> 	
       	
       	<p id="p">etreytrutyiy6u</p>
       	
       	<tr><td><input id="BtnFirst"type="button"value="Click Me"/></td></tr>
       	
       	
       	</fieldset>
       	</form> 
    </center>  
    
  </body>
</html>
