/**
 * 原始密码校验
 * @returns
 */
function odlPwd(){
	var reg = /^\w{6,32}$/;//32位数字字母下划线
	var recentpasswordrecord = $("#recentpasswordrecord").val();
	if(recentpasswordrecord!=""&&recentpasswordrecord!=null){
		if(!reg.test(recentpasswordrecord)){
			$("#oldPwd").html("请输入6~32位数字或字母或下划线！");
			return false;
		}else{
			$("#oldPwd").html("");
			return  true;
		}
	}else{
		return false;
	}
	return true;
}
/**
 * 第一次输入的新密码校验
 * @returns
 */
function newPwdOne(){
	$(document).keyup(function(e){
	var key =  e.which;
	if(key==32){
		$("#pwdWarnTwo").html("");
		$("#pwdWarn").html("密码不可是空格！");	
		return false;
	}
	})
	var pwdTwo = $("#pwdTwo").val();
	var pwd = $("#pwd").val();
	var reg = /^\w{6,32}$/;//32位数字字母下划线
	if(pwdTwo!=""&&pwdTwo!=null){
		if(newPwdTwo()){
			if(pwd!=pwdTwo){
				$("#pwdWarnTwo").html("两次输入密码不一致");
			}else{
				$("#pwdWarnTwo").html("");
			}
		}
	}
	if(pwd!=""&&pwd!=null){
		if(pwd.length<6){
			$("#pwdWarn").html("请输入6~32位数字或字母或下划线！");
			return false;
		}else{
			$("#pwdWarn").html("");
			if(!reg.test(pwd)){
				$("#pwdWarn").html("请输入6~32位数字或字母或下划线！");
				return false;
			}
		}
		if(pwd.length>32){
			$("#pwdWarn").html("请输入6~32位数字或字母！");
			return false;
		}
	}
		
	return true;
}
/**
 * 第二次输入的新密码的校验
 * @returns
 */
function newPwdTwo(){
	$(document).keyup(function(e){
		var key =  e.which;
		if(key==32){
			$("#pwdWarn").html("");
			$("#pwdWarnTwo").html("密码不可是空格！");	
			return false;
		}
		})
	//var flagTwo;
	var pwdTwo = $("#pwdTwo").val();
	var pwd = $("#pwd").val();
	var reg = /^\w{6,32}$/;//32位数字字母下划线
	if(pwd!=""&&pwd!=null){
		if(pwdTwo!=""&&pwdTwo!=null){
			if(!reg.test(pwdTwo)){
				$("#pwdWarnTwo").html("请输入6~32位数字或字母或下划线！");
				return false;
			}else{
				$("#pwdWarnTwo").html("");
				if(pwdTwo!=pwd){
					$("#pwdWarnTwo").html("两次输入密码不一致");
					return false;	
				}else{
					$("#pwdWarnTwo").html("");
				}
			}
		}
	}else{
		if(pwdTwo.length<6){
			$("#pwdWarnTwo").html("请输入6~32位数字或字母或下划线！");
			return false;
		}else{
			$("#pwdWarnTwo").html("");
			if(!reg.test(pwdTwo)){
				$("#pwdWarnTwo").html("请输入6~32位数字或字母或下划线！");
				return false;
			}
		}
		if(pwdTwo.length>32){
			$("#pwdWarnTwo").html("请输入6~32位数字或字母！");
			return false;
		}
	}
	return true;
}
/**
 * 提交时总的校验
 * @returns
 */
function prefact(){
	 var recentpasswordrecord = $("#recentpasswordrecord").val();
	 var pwdTwo = $("#pwdTwo").val();
	 var pwd = $("#pwd").val();
	 if(recentpasswordrecord!=""&&recentpasswordrecord!=null){
	   if(odlPwd()){
		if(pwd!=""&&pwd!=null){
			if(newPwdOne()){
				if(pwdTwo!=""&&pwdTwo!=null){
					if(newPwdTwo()){
						if(recentpasswordrecord==pwd){
							alert("新密码不可以与原密码一致！");
						}else{							
							goToUpdate();
						}
					}
				}else{
					alert("确认密码不可为空！");
				}
			}
		}else{
			alert("新密码不可为空！");
		}
	 }
  }else{
	  alert("原始密码不可以空"); 
  }
}


function goToUpdate(){
	var recentpasswordrecord = $("#recentpasswordrecord").val();
	var pwd = $("#pwd").val();
	var pwdTwo = $("#pwdTwo").val();
	var data = {
		frontObjStr : JSON.stringify({
			pwd : pwd,
			recentpasswordrecord : recentpasswordrecord,
			confimPwd :pwdTwo
		})
	};
	$.ajax({
		url : "updatepwd.action",
		type : "post",
		data : data,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		success : function(data, textStatus) {
			if (data != null) {
				alert(data);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("加载信息erro:" + textStatus + errorThrown);
		},
		complete : function(XMLHttpRequest, textStatus) {
		}
	});
}