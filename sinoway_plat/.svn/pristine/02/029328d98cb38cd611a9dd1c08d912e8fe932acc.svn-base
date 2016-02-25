<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<title>策略管理首页</title>
	<link rel="stylesheet" href="../windforce/sinoway/common/css/main.css">
	<link rel="stylesheet" href="../windforce/sinoway/common/css/bootstrap.min.css">
	<link type="text/css" rel="stylesheet" href="<%=path%>/sinoway/stm/css/table.css" />
	
	<script type="text/javascript"
		src='<%=request.getContextPath()%>/common/js/jquery/jquery-1.7.1.js'></script>
	<script type="text/javascript"
		src='<%=request.getContextPath()%>/common/js/jquery/jquery-ui-1.8.18.custom.js'></script>
	<script type="text/javascript"
		src='<%=request.getContextPath()%>/common/js/jquery/jquery.json-2.4.js'></script>
	<script type="text/javascript"
		src='<%=request.getContextPath()%>/sinoway/stm/js/table.js'></script>
	<script type="text/javascript"
		src='<%=request.getContextPath()%>/sinoway/stm/js/stmIndex.js'></script>
	<style type="text/css">
		body{
		background-color: #eeeeee;
		width: 94%;
		}
	</style>
<!-- 	 	<script type="text/javascript">  -->
<!--  	function test(str){  -->
<!--  	//var s = $("#test").val();  -->
<!--  	alert(str);  -->
<!--  }	  -->
<!--  	</script> -->
</head>
<body>
	   <div class="report_right" id="body1"> 
			<br>
			<img id="area01" src="../windforce/sinoway/common/images/u34.png" style="left:3.5%;top:35px;height:45px;position: absolute;z-index: 1">
			<img id="area02" src="../windforce/sinoway/common/images/u145.png" style="left:3.6%;top:39px;height:35px;width:110px;position: absolute;z-index: 2;">
			<span id="area03" style="left:3.8%;top:44px;position:absolute;color:#FFFFFF;z-index: 3;font-size:16px;" align="center" >&nbsp;&nbsp;&nbsp;报告策略</span>
			<span id="area04" style="left:3.8%;top:44px;position:absolute;color:#FFFFFF;z-index: 4;font-size:16px;width:86%;" align="right">
				<a href="<%=request.getContextPath()%>/sinoway/stm/rptManager.jsp">管理</a>&nbsp;
			</span>
			<br><br><br>
			<div style="left:3.5%;position: absolute;" id="area1">
				<table border="1px #ooo" id="rptTab" cellpadding="0" cellspacing="0"
						width="100%">
						<tr>
							<td align="center">创建时间</td>
							<td align="center">模板名称</td>
							<td align="center">包含内容</td>
							<td align="center" style="display: none;">应用于</td>
						</tr>
						<tr style="display: none">
							<td align="center">----</td>
							<td align="center">----</td>
							<td align="center">----</td>
							<td align="center" style="display: none;">----</td>
						</tr>
				</table>
			</div>
			<br/>
			<br/>
			<img id="area2" src="../windforce/sinoway/common/images/u34.png" style="left:3.5%;top:100px;width:89%;height:45px;position: absolute;z-index: 1">
			<img id="area3" src="../windforce/sinoway/common/images/u145.png" style="left:3.6%;top:104px;height:35px;width:120px;position: absolute;z-index: 2;">
			<span id="area4" style="left:3.8%;top:108px;position:absolute;color:#FFFFFF;z-index: 3;font-size:16px;" align="center" >&nbsp;&nbsp;&nbsp;反欺诈策略</span>
			<span id="area5" style="left:3.8%;top:108px;position:absolute;color:#FFFFFF;z-index: 4;font-size:16px;width:86%;" align="right">
				<a href="<%=request.getContextPath()%>/sinoway/stm/fadManager.jsp">管理</a>&nbsp;
			</span>
			<%--
			<img src="../windforce/sinoway/common/images/u34.png" style="left:40px;top:270px;width:93.5%;height:45px;position: absolute;z-index: 1">
			<img src="../windforce/sinoway/common/images/u145.png" style="left:42px;top:275px;height:35px;width:15%;position: absolute;z-index: 2;">
			<span style="left:45px;top:280px;position:absolute;color:#FFFFFF;z-index: 3;font-size:16px;">反欺诈策略</span>
			<span style="left:45px;top:280px;position:absolute;color:#FFFFFF;z-index: 4;font-size:16px;width:90%;" align="right">
				<a href="<%=request.getContextPath()%>/sinoway/stm/fadManager.jsp">管理</a>&nbsp;
			</span>
			--%>
			<br/><br/><br/><br/><br/><br/><br/>
			<div style="left:3.5%;position: absolute;width:88%;" id="area6">
				<table border="1px #ooo" id="fadTab" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td align="center">创建时间</td>
						<td align="center">模板名称</td>
						<td align="center">包含内容</td>
						<td align="center" style="display: none;">应用于</td>
					</tr>
					<tr style="display: none">
						<td align="center">----</td>
						<td align="center">----</td>
						<td align="center">----</td>
						<td align="center" style="display: none;">----</td>
					</tr>
				</table>
			</div>
			<br/>
			<br/>
			<br/>
			<br/>
	</div>
	<script type="text/javascript">
	 function initPage(){
		var body1 =  document.getElementById("body1");
		var area01 = document.getElementById("area01");
		var area0bottom = area01.offsetTop + area01.offsetHeight + 10;
		document.getElementById("area01").style.width = body1.offsetWidth*0.92;
		document.getElementById("area04").style.width = body1.offsetWidth*0.88;
		document.getElementById("area1").style.width = body1.offsetWidth*0.9;
		
		document.getElementById("area2").style.width = body1.offsetWidth*0.92;
		document.getElementById("area5").style.width = body1.offsetWidth*0.88;
		document.getElementById("area6").style.width = body1.offsetWidth*0.9;
		
		document.getElementById("area1").style.top = area0bottom + 5;
		var area1 = document.getElementById("area1");
		var area1bottom = area1.offsetTop + area1.offsetHeight + 10;
		document.getElementById("area2").style.top = area1bottom + 20;
		document.getElementById("area3").style.top = area1bottom + 25;
		document.getElementById("area4").style.top = area1bottom + 30;
		document.getElementById("area5").style.top = area1bottom + 30;
		document.getElementById("area6").style.top = area1bottom + 80;
	 }
	</script>
<!-- 	<div  onclick="test(this.id)" id="6666" style="width:40px ;height: 30px;background:#38a0b9 ;cursor: pointer;"  >ffff</div> -->
</body>
</html>
