<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	    <base href="<%=basePath%>">
	    <title>天警云</title>
	    <link rel="stylesheet" href="../windforce/sinoway/common/css/main.css">
		<link rel="stylesheet" href="../windforce/sinoway/common/css/bootstrap.min.css">
		<script type="text/javascript"
			src='<%=request.getContextPath()%>/common/js/jquery/jquery-1.7.1.js'></script>
		<script type="text/javascript"
			src='<%=request.getContextPath()%>/common/js/jquery/jquery-ui-1.8.18.custom.js'></script>
		<script type="text/javascript"
			src='<%=request.getContextPath()%>/common/js/jquery/jquery.json-2.4.js'></script>
		<script type="text/javascript"
		src='<%=request.getContextPath()%>/sinoway/stm/js/table.js'></script>
		<script type="text/javascript"
			src='<%=request.getContextPath()%>/sinoway/stm/js/wrnManager.js'></script>
		<style type="text/css">
			body{
			background-color: #eeeeee;
			width: 94%;
			}
		</style>
	</head>
	<body>
	    <div class="report_right" >
  			<br>
			<img src="../windforce/sinoway/common/images/u34.png" style="left:3.5%;top:6.6%;width:89%;height:8%;position: absolute;z-index: 1">
			<img src="../windforce/sinoway/common/images/u145.png" style="left:3.6%;top:7.5%;height:6%;width:165px;position: absolute;z-index: 2;">
			<span style="left:3.8%;top:8.4%;position:absolute;color:#FFFFFF;z-index: 3;font-size:16px;" align="center">&nbsp;&nbsp;天警云模块策略制定</span>
			<br><br><br>
			<div style="left:3.5%;position: absolute;width:88%;">
				<table border="1px #ooo" id="wrnTab" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td align="center">创建时间</td>
						<td align="center">模板名称</td>
						<td align="center">包含内容</td>
						<td align="center">应用于</td>
						<td align="center">操作</td>
					</tr>
					<tr>
						<td align="center">----</td>
						<td align="center">----</td>
						<td align="center">----</td>
						<td align="center">----</td>
						<td align="center"><a id="addNewPrd">新建</a></td>
					</tr>
				</table>
				<br/>
				<br/>
				<div class="disArea" style="display: none;" align="center"><lable>自定义模块名称:<input type="text" id="prdnam"/>&nbsp;<input type="button" id="saveOrUpdatePrd" value="保存"/>&nbsp;<input type="button" id="closeTab" value="关闭"/></lable></div>
				<div class="disArea" style="display: none;" align="center"><lable>请勾选模块预警策略</lable></div>
				<div class="disArea" style="display: none;" id ="trnTables"></div>
				<div class="po_disArea" style="display: none;"><lable>分配人员:<input type="button" id="upStep" value="上一步"/>&nbsp;<input type="button" id="saveOrUpdatePrd" value="保存"/>&nbsp;<input type="button" id="closePoTab" value="关闭"/></lable></div>
				<br/>
				<br/>
				<table class="po_disArea" border="1px #ooo" id="poTable"  cellpadding="0" cellspacing="0"
					width="100%" style="display: none;">
					<tr><td colspan="2" align="center">机构人员信息</td></tr>
					<tr><td align="center">团队</td><td align="center">人员</td></tr>
					<tr>
						<td align="center" id="orgs">
						</td>
						<td align="center" id="peoples">
						</td>
					</tr>
				</table>
  			</div>
  	 	</div>
  </body>
</html>
