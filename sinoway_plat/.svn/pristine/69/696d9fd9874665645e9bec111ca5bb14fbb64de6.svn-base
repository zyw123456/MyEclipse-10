<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="ctx" value="<%=request.getContextPath()%>"></c:set>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	response.setHeader("_YZJ_WF_LOGIN_PAGE_", "_YZJ_WF_LOGIN_PAGE_");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>华道征信云平台</title>

<script type="text/javascript">
	var ctx = "${ctx}";
</script>
<link rel="stylesheet" href="${ctx }/sinoway/common/css/main.css">
<script type="text/javascript"
	src="${ctx }/common/js/jquery/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="${ctx }/common/js/jquery/jquery.json-2.4.js"></script>
<script type="text/javascript" src="${ctx }/common/js/wf/uiCheck.js"></script>
<script type="text/javascript">
	/**
	 * 界面初始化
	 */
	$(document).ready(function() {
		$("#j_username").focus();
		$(document).keydown(function(e) {
			var curkey = e.which;
			if (curkey == 13) {
				if (document.activeElement.id == "j_username") {//如果焦点在用户名，按回车焦点设为密码输入框
					document.getElementById("j_password").focus();
					return false;
				} else {//焦点在其他位置，均提交
					checkMultipleLogin();
				}
			}
		});
		
		
		
		$('#nopasslable').toggle(function () {
		     $("input[name='_spring_security_remember_me']").attr("checked", 'true');
		 }, function () {
		     $("input[name='_spring_security_remember_me']").removeAttr("checked");
		 });
	});

	setloginPage();
	function setloginPage() {
		if (top.location != this.location) {
			top.location = this.location;
		}
	}
	//检查输入
	function checkMultipleLogin() {
		var usernameInput = document.getElementById("j_username");
		var passwordInput = document.getElementById("j_password");
		var loginInfo = document.getElementById("login_info");
		var username = usernameInput.value;
		var password = passwordInput.value;
		if (username == "") {
			loginInfo.innerHTML = "账号不能为空，请输入。";
			usernameInput.focus();
			return;
		}
		//判断输入长度
		if (username.length > 20) {
			loginInfo.innerHTML = "账号长度不能超过20位，请重输。";
			usernameInput.value = "";
			usernameInput.focus();
			return;
		}
		if (!top.isHalf(username)) {
			loginInfo.innerHTML = "账号应为半角字符，请重输。";
			usernameInput.value = "";
			usernameInput.focus();
			return;
		}
		if (!top.isAlphaAndDigits(username)) {
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
// 		loginInfo.innerHTML = "正在检验当前浏览器上是否存在已登录的用户...";
<%-- 		$.post("<%=path%>/getPreUser.action?_t=" + new Date().getTime(), "", --%>
// 				function(preUser) {
// 					if (preUser != "" && username != preUser) {
// 						if (preUser.length > 50) {
// 							loginInfo.innerHTML = "查询出现异常";
// 							return;
// 						}
// 						loginInfo.innerHTML = "该浏览器上已存在已经登录的其他用户(" + preUser
// 								+ ")!";
// 						usernameInput.focus();
// 					} else {
// 						submitLoginForm();
// 					}
// 				});
	submitLoginForm();
	}

	function submitLoginForm() {
		document.forms["loginForm"].submit();
	}
	
	
		
</script>
</head>
<body>
	<form name="loginForm" id="loginForm"
		action="${pageContext.request.contextPath }/j_spring_security_check"
		method="post">
		<input type="hidden" name="loginType" value="pwdLogin" id="loginType" />
		<input type="hidden" name="systemModule" value="0" id="systemModule" />

		<div id="login">
			<div id="nav">
				<img alt="华道征信" src="${ctx }/sinoway/common/images/logos.png" />
			</div>
			<div id="center">
				<img class="bgLeft" src="${ctx }/sinoway/common/images/left_bg.png">
				<img class="bgRight"
					src="${ctx }/sinoway/common/images/right_bg.png">
				<div class="form">
					<h1>北京华道征信有限公司</h1>
					<h2>企业会员登录</h2>
					<div id="upd">
						<span><img src="${ctx }/sinoway/common/images/user.png" /></span><input
							class="usertex" type="text" id="j_username" name="j_username"
							style="border: none" />
					</div>
					<div id="upds">
						<span><img src="${ctx }/sinoway/common/images/pass.png" /></span><input
							class="usertex" type="password" id="j_password" name="j_password"
							style="border: none" />
					</div>
					<div class="nopass">
						<input type="checkbox" id="_spring_security_remember_me"
							name="_spring_security_remember_me"  height="100%" /> <span id="nopasslable"> 一周内自动登录</span>
						<a href="#">忘记密码？</a>
					</div>
					<br /> <br />
					<p class="qingbu">(请不要在公共场合电脑上勾选此选项)</p>
					<p id="login_info" align="center">${SPRING_SECURITY_LAST_EXCEPTION}</p>
					<div id="round" onclick="checkMultipleLogin()">

						<a>登录</a>
					</div>
				</div>
			</div>

			<address></address>
		</div>
	</form>
	<!-- 清空异常提示信息 -->
	<%
		session.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", "");
	%>
</body>
</html>