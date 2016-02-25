<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	response.setHeader("_YZJ_WF_LOGIN_PAGE_", "_YZJ_WF_LOGIN_PAGE_");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/common/wf_import.jsp"%>
<%@ include file="/windforce/common/jsp/wfInternalImport.jsp" %>
<script type="text/javascript">

/**
 * 界面初始化
 */
$(document).ready(function(){
	$("#j_username").focus();
	//针对浏览器1024*768分辨率重新定位图片位置
	var width = window.screen.width;
	if(width<=1024){
		if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
			$("#login_title").css("left","492px");
			$("#login_body").css("left","492px");
		}else if(navigator.userAgent.indexOf("MSIE 7.0") > 0){
			$("#login_title").css("left","490px");
			$("#login_body").css("left","490px");
		}else{
			$("#login_title").css("left","508px");
			$("#login_body").css("left","508px");
		}
		
	}
	if(width<=1024){
		if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
			$("#topAreaDiv").css("width","1004px");
		}else if(navigator.userAgent.indexOf("MSIE 7.0") > 0){
			$("#topAreaDiv").css("width","1002px");
		}else{
			$("#topAreaDiv").css("width","1020px");
		}
	}else{
		if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
			$("#topAreaDiv").css("width",(width-20)+"px");
		}else if(navigator.userAgent.indexOf("MSIE 7.0") > 0){
			$("#topAreaDiv").css("width",(width-22)+"px");
		}else{
			$("#topAreaDiv").css("width",(width-4)+"px");
		}
	}
});

setloginPage();
function setloginPage()
{
if(top.location!=this.location)
{
top.location = this.location;
}
}
function modifyPwd()
{

 var winHeight = 300;
	var winWidth = 450;
	var winTop = (window.screen.height - winHeight) / 2;
	var winLeft = (window.screen.width - winWidth) / 2;
	var sFeatures = "dialogHeight:" + winHeight + "px;dialogWidth:" + winWidth
			+ "px;dialogLeft:" + winLeft + "px;dialogTop:" + winTop + "px;";
			var returnValue = window.showModalDialog("<%=path%>/windforce/po/peopleInfo/modifyPwd.jsp?userNo="+""+"&time="
			+ (new Date()).getTime() , null, sFeatures);
			if(returnValue == "success")
			{
				alert("密码已更改！");
			}
}

//监听回车
function onKey(){
	if(window.event.keyCode == 13){
		if(document.activeElement.id == "j_username"){//如果焦点在用户名，按回车焦点设为密码输入框
			document.getElementById("j_password").focus();
			return false;
		} else {//焦点在其他位置，均提交
			checkMultipleLogin();
		}
	}
}
//提交表单
function submitLoginForm(){
	document.forms["loginForm"].submit();
}

function checkMultipleLogin(){
	var usernameInput =  document.getElementById("j_username");
	var passwordInput = document.getElementById("j_password");
	var loginInfo = document.getElementById("login_info");
	var username = usernameInput.value;
	var password = passwordInput.value;
	
	if(username == "") {
		loginInfo.innerHTML = "账号不能为空，请输入。";
		usernameInput.focus();
		return;
	}
	//判断输入长度
	if (username.length > 20 ) {
		loginInfo.innerHTML = "账号长度不能超过20位，请重输。";
		usernameInput.value = "";
		usernameInput.focus();
		return;
	}
	if(!top.isHalf(username)){
		loginInfo.innerHTML = "账号应为半角字符，请重输。";
		usernameInput.value = "";
		usernameInput.focus();
		return;
	}
	if(!top.isAlphaAndDigits(username)){
		loginInfo.innerHTML = "账户只能是字母或数字，请重输。";
		usernameInput.value = "";
		return;
	}
	if (password == "") {
		loginInfo.innerHTML = "密码不能为空，请输入。";
		passwordInput.focus();
		return;
	}
	//判断输入长度
	if (password.length > 32) {
		loginInfo.innerHTML = "密码长度不能超过32位，请重输。";
		passwordInput.value = "";
		passwordInput.focus();
		return;	
	}
	loginInfo.innerHTML = "正在检验当前浏览器上是否存在已登录的用户...";
	$.post("<%=path%>/getPreUser.action?_t="+ new Date().getTime(),"",
		function(preUser) {
			if (preUser != "" && username != preUser) {
				if (preUser.length > 50) {
					loginInfo.innerHTML = "查询出现异常";
					return;
				}
				loginInfo.innerHTML = "该浏览器上已存在已经登录的其他用户("
						+ preUser + ")!";
				usernameInput.focus();
			} else {
				submitLoginForm();
			}
		});
	}
</script>
</head>
<body style="width:100%;overflow: auto;" onkeyup="onKey()" id="loginBody">
	<form name="loginForm" id="loginForm"
		action="${pageContext.request.contextPath }/j_spring_security_check"
		method="post">
		<div id="login_title" class="login_title">
			Windforce 基础框架
		</div>
		<div id="login_body" class="login_index">
			<input type="hidden" name="loginType" value="pwdLogin" id="loginType" />
			<input type="hidden" name="systemModule" value="0" id="systemModule" />
			<table width="800" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td height="384" colspan="7">&nbsp;</td>
				</tr>
				<tr>
					<td height="20" colspan="7" style="color:red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
						id="login_info">${SPRING_SECURITY_LAST_EXCEPTION}</span>
					</td>
				</tr>
				<tr>
					<td width="50" align="center" class="font_red">账号</td>
					<td width="150" align="center"><input name="j_username"
						id="j_username" type="text" class="login_text01" value=""/>
					</td>
					<td width="50" align="center" class="font_red">密码</td>
					<td width="150" align="center"><input type="password"
						name="j_password" id="j_password" class="login_text01" value=""/>
					</td>
					<td width="100" align="center"><input name="login_button"
						type="button" class="submit_login" id="login_button" value=""
						onclick="checkMultipleLogin()" />
					</td>
					<td width="90" align="center" class="font_red"><label
						style="visibility: hidden"> <input type="checkbox"
							name="checkbox" id="checkbox" /> </label></td>
					<td width="190" class="font_red"></td>
				</tr>
			</table>
		</div>
		<div id="topAreaDiv">
			<div id="logoDiv"></div>
			<%--<img src="<%=path%>/common/images/login_logo.jpg" />
		--%></div>
	</form>
	<!-- 清空异常提示信息 -->
	<%
		session.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", "");
	%>
</body>
</html>