<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录授权</title>
<script type="text/javascript">
	var errCount=0;
	function savePwd(){
	    var username = $("#username").val();
        var password = $("#password").val();
		if(username!=""&&password!=""){
			$.post("<%=path%>/authorize.action?_t="+ new Date().getTime(),"username="+username+"&password="+password,function(data){
				if(data=="true"){
				 	closeShowPage_lockPage();
				}else if(data!=""){
					 errCount++;
					 if(errCount>=3){
						 wfAlert("密码连续输入错误3次，将退出系统!",false,null,function(){
							 WFUnload.clear();
							 window.location.href ="${pageContext.request.contextPath }/j_spring_security_logout";
						 });
					}else{
						wfAlert(data,false,null,function(){
							document.getElementById("password").focus();
						});
					}
				}else{
					 wfAlert("当前用户已经被登出!",false,null,function(){
						 WFUnload.clear();
						 window.location.href ="${pageContext.request.contextPath }/j_spring_security_logout";
					 });
				}
			});
		}
	}
</script>
</head>
<body>
	<div align="left" style="position:absolute;left:0px;">
		用户名&nbsp;&nbsp;&nbsp;<input type="text"
			id="username" name="username" style="width:170px;" /> <br />
		&nbsp;&nbsp;密码:&nbsp;&nbsp;&nbsp;<input type="password" 
			id="password" name="password" 
			maxlength="32" style="width:170px;" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<br />
		<div style="left:205px;margin-top:-25px;position: relative;">
			<input type="button" value="确定" onclick="savePwd()" />
		</div>
	</div>
</body>
</html>