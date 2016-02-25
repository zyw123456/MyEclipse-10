<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<title>账户管理--子账户设置--新增新权限</title>
	<link rel="stylesheet" href="../windforce/sinoway/common/css/main.css">
	<link rel="stylesheet"
		href="../windforce/sinoway/common/css/bootstrap.min.css">
	
	<script type="text/javascript"
		src='<%=request.getContextPath()%>/common/js/jquery/jquery-1.7.1.js'></script>
	<script type="text/javascript"
		src='<%=request.getContextPath()%>/common/js/jquery/jquery-ui-1.8.18.custom.js'></script>
	<script type="text/javascript"
		src='<%=request.getContextPath()%>/common/js/jquery/jquery.json-2.4.js'></script>
	
	<script type="text/javascript"
		src='<%=request.getContextPath()%>/sinoway/acc/js/accChildAdd.js'></script>
	<link rel="stylesheet" href="sinoway/acc/css/accChild.css">
	<style type="text/css">
		body{
		background-color: #eeeeee;
		width: 94%;
		}
	</style>
</head>
<body>
	 <div class="report_right" >
		<div class="page_style" align="center">
	    	<img src="../windforce/sinoway/common/images/u34.png" style="left:18%;top:40px;width:60%;height:45px;position: absolute;z-index: 1">
			<img src="../windforce/sinoway/common/images/u145.png" style="left:18%;top:45px;height:35px;width:10%;position: absolute;z-index: 2;">
			<span style="left:20%;top:50px;position:absolute;color:#FFFFFF;z-index: 3;font-size:16px;">新建权限</span>
	    	<br><br><br>
	    	<table align="center" height="100"  width="1000">
				<tr align="center">
					<td>
						使用人账号：
						<input type="text" size="10" id="peoplecode">&nbsp;&nbsp;
						使用人姓名：
						<input type="text" size="10" id="peoplename">&nbsp;&nbsp; 
						团队名称：
						<select id="team" onchange="addTeam()">
							<option value='0'>请选择</option>
						</select> &nbsp;&nbsp;
					
						<span id="newteam" style="display:none">
							团队名称：
							<input type="text" size="10" id="newTeam" name="addNewTeam">&nbsp;&nbsp;
							<input type="button" value="保存" onclick="saveTame()">
						</span>
					</td>
				</tr>
			</table>
			<table border="1" cellspacing="0" align="center" width="600"
				height="100">
				<tr>
					<td width="20%" align="center">反欺诈云<br>
					<br>
					<input type="checkbox" class="disp" value='fad' />显示详情</td>
					<td id="fad"></td>
				</tr>
			</table>
			<!--  <div id="fadDiv"></div>
			<br />
			<table border="1" cellspacing="0" align="center" width="600"
				height="100">
				<tr>
					<td width="20%" align="center">天警云<br>
					<br>
					<input type="checkbox" class="disp" value='wrn' />显示详情</td>
					<td id="wrn"></td>
				</tr>
			</table>
			<div id="wrnDiv"></div>-->
			<br />
			<table border="1" cellspacing="0" align="center" width="600"
				height="100">
				<tr>
					<td width="20%" align="center">报告策略<br>
					<br>
					<input type="checkbox" class="disp" value='rpt' />显示详情</td>
					<td id="rpt"></td>
				</tr>
			</table>
			<br />
			<!--  <div id="rptDiv"></div>-->
			<div align="center">
				<input type="button" value="确认保存" onclick="check()" />&nbsp;&nbsp;<input
					type="button" value="取消" onclick="history.back()" />
			</div>
			<div id="dispDiv"></div>
	    </div>
	</div>
</body>
</html>
