<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'reportManager.jsp' starting page</title>

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
<!-- <script type="text/javascript" -->
<!-- 	src='<%=request.getContextPath()%>/sinoway/app/js/appManager.js'></script> -->
  <script type="text/javascript" src='<%=request.getContextPath()%>/sinoway/app/js/appManager1.js'></script>	  


<script type="text/javascript"
	src='<%=request.getContextPath()%>/sinoway/app/js/appManager.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/sinoway/app/js/appManager.js'></script>
	

</head>
<script type="text/javascript">
// var info;
 function findTrncodByAppcod2(){
	var appcode = $("#appcode").val(); 
	/* $.ajax({
 	type:"POST",
 	url:"/windforce/appfindTrncodAction.action",
 	data:dataStr,
 	success:function(msg){
 	info=eval(msg);
 	}
 	}); */
	 window.location.href="/windforce/appfindTrncodAction.action?appcode="+appcode;
	}  
// 	$.ajax({
// 		url : "/windforce/appfindTrncodAction.action",
// 		type : "post",
// 		data : data,
// 		beforeSend : function(XMLHttpRequest) {
// 			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
// 		},
// 		async : true,
// 		dataType : "json",
// 		success : function(data, textStatus) {
// 			if (data != "") {
// 				if (data.frontObjStr != null) {
// 				alert("ok");
//					console.log(data.frontObjStr);
// 					var obj = JSON.parse(data.frontObjStr);
// 					var showStr = "";
// 					for(var i=0;i<obj.trns.length;i++){
// 						showStr += obj.trns[i].TRNCOD +" "+ obj.trns[i].TRNNAM+" ";
// 					}
// 					$("#trns").val(showStr);
// 				}
// 			}
// 		},

// 	});
</script>
<body>
应用编码:<input  type="text" id="appcode" name="appcode"/><input type="button" id="test" onclick="findTrncodByAppcod()" value="查询"/><br/>
对应交易码集合:<input type="text" id="trns"/>

<table id="table"></table>

<c:if test="${list.size() >0 }">
<table>
	<tr><td>应用编码</td><td>原交易编码</td></tr>
	<c:forEach items="list" var="l" varStatus="i">
	<tr><td>${list[i.index].APPCOD }</td><td>${list[i.index].TRNCOD }</td></tr>
</c:forEach>
</table>

</c:if>

<div id="myPagination"></div>

</body>
</html>
