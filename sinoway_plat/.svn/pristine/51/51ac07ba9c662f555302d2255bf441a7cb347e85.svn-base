<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>密码修改</title>
	<link rel="stylesheet" href="../windforce/sinoway/common/css/main.css">
	<link rel="stylesheet" href="../windforce/sinoway/common/css/bootstrap.min.css">
	
	<script type="text/javascript"
		src='<%=request.getContextPath()%>/common/js/jquery/jquery-1.7.1.js'></script>
	<script type="text/javascript"
		src='<%=request.getContextPath()%>/common/js/jquery/jquery-ui-1.8.18.custom.js'></script>
	<script type="text/javascript"
		src='<%=request.getContextPath()%>/common/js/jquery/jquery.json-2.4.js'></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/windforce/common/js/My97DatePicker/WdatePicker.js"></script>

	<script type="text/javascript"
		src='<%=request.getContextPath()%>/sinoway/acc/js/accAdd.js'></script>
	<script type="text/javascript"
		src='<%=request.getContextPath()%>/common/js/wf/uiCheck.js'></script>
	<script type="text/javascript"
		src='<%=request.getContextPath()%>/sinoway/acc/js/pwdEdit.js'></script>
</script> 
	<style type="text/css">
		body{
		background-color: #eeeeee;
		width: 94%;
		}
	</style>
</head>
	
<body>
<div class="report_right" >
	<div class="report_center" > 
	  <div class="show_info" align="center" style="margin-top:50px;">
		<form  class="form-inline" name="updatepwd" action="updatepwd.action" method="post" id="form1" autocomplete = "off">
		</br>
		</br>
		   <div class="col-md-4 col-sm-4 col-md-offset-3" align="left">
	   			<div class="input-group" style="display:!important;" id="block1">
	  				<div class="input-group-addon" >原登录密码&nbsp;&nbsp;&nbsp;</div>
	  				<input type="password" style="display: none;">
		            <input type="password" class="form-control" id="recentpasswordrecord" name="wfCfgdefPwd.recentpasswordrecord" value="" onkeyup="odlPwd()"  onpaste="return false"/>
	  			</div> 
	  		</div>
	  		
	  		<div class="col-md-3 col-sm-3 text-left" style="padding-top:28px; ">
	  		<span style="display: inline;float:;color: red;" id="oldPwd"></span>
	  		</div>
	  		
	  		<div class="col-md-4 col-sm-4 col-md-offset-3" align="left">
	  			<div class="input-group" style="display:!important;" id="block2";>
	  				<div class="input-group-addon" >新登录密码&nbsp;&nbsp;&nbsp;</div>   <!--  onselect="change()" -->
	 	            <input type="password" class="form-control" id="pwd" name="wfCfgdefPwd.pwd" value="" onkeyup="newPwdOne()"   onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;" onpaste="return false"/> <!--<div  display:inline><font id="tishi" style="display: none;">长度不可以小于6</font><font id="shuzi" style="display: none;">不可以全是数字</font><font id="noEmpt" style="display: none;">新密码不可以为空</font> </div> -->
	  			</div >
	  		</div>
	  		
	  		<div class="col-md-3 col-sm-3 text-left" style="padding-top:28px; ">
	  		    <span style="display: inline;float:;color: red;" id="pwdWarn"></span>
	  		</div>
	  		
	  		<div class="col-md-4 col-sm-4 col-md-offset-3" align="left">
	  			<div class="input-group" style="display:!important;" id="block3" >
	  				<div class="input-group-addon" >确认新密码&nbsp;&nbsp;&nbsp;</div>
		            <input type="password" class="form-control" id="pwdTwo" name="pwdTwo" value="" onkeyup="newPwdTwo()"   onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;" onpaste="return false"/>
	  			</div>
	  		</div>
	  		
	  		<div class="col-md-3 col-sm-3 text-left" style="padding-top:28px; ">
	  			 <span style="display: inline;float:;color: red;" id="pwdWarnTwo"></span>
<!-- 	  			<font id="tishiTHrd" style="display: none;">两次输入密码不一致</font> -->
	  		</div>
	  		
			<div class="col-md-3 col-sm-3 col-md-offset-4 form_submit" >
			         <div id="round2" onclick="prefact()" onmouseover="this.style.cursor='hand'">
			             <a >提交</a>
			         </div>
			 </div>
		   </div> 
		</form>
	  </div>
</div>
</body>
 <script type="text/javascript">
 	var left1 = document.getElementById("block1").offsetLeft;
	var left2 = document.getElementById("block2").offsetLeft;
	var left3 = document.getElementById("block3").offsetLeft;
	document.getElementById("block2").style.left = left1 -left2;
  	document.getElementById("block3").style.left = left1 -left3;
 </script>
</html>
