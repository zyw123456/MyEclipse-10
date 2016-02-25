<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>云警告首页</title>
    
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
 <script type="text/javascript" src="<%=request.getContextPath()%>/sinoway/wrn/js/cloudWrnHomePage.js"></script> 
<!-- <script type="text/javascript"> -->
<!-- $(function(){ -->
<!-- 	alert("ok"); -->
<!-- }); -->
<!-- </script> -->
  </head>
  
  <body>
    <table width="1000px" >
    	<tr align='center'>
     		<td width="300px" >监控时间    <img src="sinoway/wrn/images/01.png"  onclick="monitoringSort(1)"> <img src="sinoway/wrn/images/02.png"  onclick="monitoringSort(2)"></td></td> 
		<!--	<td width="300px" >监控时间   <input type="button" value="升序" id="sort1" onclick="ascendingSort()"> <input type="button" value="降序" id="sort2" onclick="descendingSort()"></td>  -->
    		<td width="50px" >姓名</td>
    		<td width="250px" >身份证号</td>
    		<td width="150px" ><select id="dataType" onchange="findWfDatCerditByDataType()"><option>预警数据类别</option><option value="金融">金融类数据预警</option><option value="电商">电商类数据预警</option><option value="查询">查询类数据类别</option><option value="公共">公共类数据类别</option></select></td>
    		<td width="100px" ><select id="dataUrl" onchange="findWfDatCerditByDataType()"><option>数据来源</option><option value="网">网络爬取</option><option value="平">平台披露</option><option value="央">央行报告</option><option value="法">法院公开名单</option></select></td>
<!--     		<td width="200px">数据可信度 <input type="button" value="高到低" id="reliabilitySort1" onclick="relAscendingSort()"> <input type="button" value="低到高" id="relReliabilitySort2" onclick="descendingSort()"></td></td> -->
			<td width="200px">数据可信度 <img src="sinoway/wrn/images/01.png" id="reliabilitySort1" onclick="reliabilitySort(1)"> <img src="sinoway/wrn/images/02.png"  onclick="reliabilitySort(2)"></td></td>
			
    	</tr>
    </table>
    <table width="1000px" id="listTable">
    </table>
<!--     <input type="button" onclick="test()" value="按钮"> -->
  </body>
</html>
