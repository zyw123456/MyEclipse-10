<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'acc-add.jsp' starting page</title>
    <script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-1.7.1.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-ui-1.8.18.custom.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery.json-2.4.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/sinoway/acc/js/accChildUpdate.js'></script>
    <link rel="stylesheet" href="sinoway/acc/css/accChild.css">
  </head>
 
  
  <body>
    	<table align="center" height="100">
    		<tr align="center"><td>修改权限</td></tr>
    		<tr><td>使用人账号：<input type="text" size="15" id="peoplecode" value="${people.peopleCode }" readonly>&nbsp;&nbsp;使用人姓名：<input type="text" size="15" id="peoplename" value="${people.peopleName }">&nbsp;&nbsp;
    		团队名称：<select id="team" onchange="addTeam()" ><option value='0' >请选择</option>
    		</select> &nbsp;&nbsp;<span id="newteam" style="display:none">团队名称：<input type="text" size="15" id="newTeam" name="addNewTeam" >&nbsp;&nbsp;<input type="button"  value="保存"  onclick="saveTame()"></span></td></tr>
    	</table>
    	<br/>
    	<br/>
    	<table border="1" cellspacing="0" align="center" width="600" height="100">
  		<tr><td width="20%" align="center">反欺诈云<br><br><input type="checkbox" class="disp" value='fad'/>显示详情</td><td id="fad"></td></tr> 
    	</table>
    	<!-- <div id="fadDiv"></div>
    	<br/>
    	<table border="1" cellspacing="0" align="center" width="600" height="100">
		<tr><td width="20%" align="center">天警云<br><br><input type="checkbox" class="disp" value='wrn'/>显示详情</td><td id="wrn"></td></tr>
    	</table>
    	<div id="wrnDiv"></div> -->
    	<br/>
    	<table border="1" cellspacing="0" align="center" width="600" height="100">
    		 <tr><td width="20%" align="center">报告策略<br><br><input type="checkbox" class="disp" value='rpt'/>显示详情</td><td id="rpt"></td></tr> 
    	</table>
    	<br/>
    	<!-- <div id="rptDiv"></div> -->
    	<div align="center"><input type="button" value="确认保存" onclick="check()"/>&nbsp;&nbsp;<input type="button" value="取消" onclick="history.back()"/> </div>
    	<div id="dispDiv"></div>
    	<input type="hidden" id="peoId" value="${people.sid }">
    	<input type="hidden" id="orgId" value="${organize.sid }">
  </body>
</html>
