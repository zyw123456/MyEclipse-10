//护照验证
function checknumber(){
	
	var jsondata = $("#jsondata").val();
	if(jsondata == "0"){
		return "false";
	}   
	var jsonObj  = JSON.parse(jsondata);
	var dataVar = "";
	for(var i=0;i<jsonObj.length;i++){
		var elename = $("#"+jsonObj[i].elecod+"").val();
		 if(jsonObj[i].datatype == datetypepassport){
			 if(jsonObj[i].issbc == issbcyes){
					var reissbc = verifissbc(elename);
					if(reissbc == true){
						$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"要包含全角！");
						return "false";
					}
				}else{
					var reissbc = verifissbc(elename);
					if(reissbc == false){
						$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"不能包含全角！");
						return "false";
					}
				}
			if(jsonObj[i].isnull == isnullyes){
				if(elename != ""){
					if (elename.length < jsonObj[i].minlen || elename.length > jsonObj[i].maxlen) {
						$("#html_"+jsonObj[i].elecod+"").html("长度必须在"+jsonObj[i].minlen+"到"+jsonObj[i].maxlen+"之间！");
						$("input[name='isverif']").removeAttr("checked");
						return "false";
					}
					var str=elename;
					//在JavaScript中，正则表达式只能使用"/"开头和结束，不能使用双引号
					var objExp= /^[0-9a-zA-Z]+$/;
					if(objExp.test(str) == true){
						$("#html_"+jsonObj[i].elecod+"").html("");
						return "true";
					}else{
						$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"只能是数字与英文！");
					   return "false";
					}
				}
			}else{
				if(elename == ""){   
					$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"不能为空！");
					$("input[name='isverif']").removeAttr("checked");
					return "false";
				}else{
					if (elename.length < jsonObj[i].minlen || elename.length > jsonObj[i].maxlen) {
						$("#html_"+jsonObj[i].elecod+"").html("长度必须在"+jsonObj[i].minlen+"到"+jsonObj[i].maxlen+"之间！");
						$("input[name='isverif']").removeAttr("checked");
						return "false";
					}
					var str=elename;
					//在JavaScript中，正则表达式只能使用"/"开头和结束，不能使用双引号
					var objExp=/^[0-9a-zA-Z]+$/;
					if(objExp.test(str) == true){
						$("#html_"+jsonObj[i].elecod+"").html("");
						return "true";
					}else{
						$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"只能是数字与英文！");
					   return "false";
					}
				}
			}
			
		}
	}
	return "true";
}
//验证银行卡号
function verifbank(){
	var jsondata = $("#jsondata").val();
	if(jsondata == "0"){
		return "false";
	}   
	var jsonObj  = JSON.parse(jsondata);
	var dataVar = "";
	
	for(var i=0;i<jsonObj.length;i++){
		var velename = $("#"+jsonObj[i].elecod+"").val();
		var elename = velename;
		if(jsonObj[i].datatype == datetypebank){
			if(jsonObj[i].issbc == issbcyes){
				var reissbc = verifissbc(elename);
				if(reissbc == true){
					$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"要包含全角！");
					return "false";
				}
			}else{
				var reissbc = verifissbc(elename);
				if(reissbc == false){
					$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"不能包含全角！");
					return "false";
				}
			}
			$("#html_"+jsonObj[i].elecod+"").html("");
			$("#html_bank"+jsonObj[i].elecod+"").html("");
			if(jsonObj[i].isnull == isnullyes){
				var istype = "";
				//验证银行卡号
				if(elename != ""){
					if(/\s/.test(elename)){
						$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"不能有空格！");
						return "false";
				    }
					if (elename.length < 16 || elename.length > 19) {
						$("#html_"+jsonObj[i].elecod+"").html("卡号长度必须16到19之间!");
						$("input[name='isverif']").removeAttr("checked");
						return "false";
					}
					var num = /^\d*$/;  //全数字
					if (!num.exec(elename)) {
						$("#html_"+jsonObj[i].elecod+"").html("卡号必须全为数字!");
						$("input[name='isverif']").removeAttr("checked");
						return "false";
					}
					var data = {wfDate : JSON.stringify({
								bankCode:elename
						})}
					$.ajax({
						url :  ctx+"/queryBank.action",
						type : "post",
						data : data,
						beforeSend : function(XMLHttpRequest) {
							XMLHttpRequest.setRequestHeader("RequestType", "ajax");
						},
						async : false,
						dataType : "json",
						success : function(data, textStatus) {		
								data = JSON.parse(data.wfDate);
							if (data != "") {
								var bnk = data.bankName; 
								if(bnk == "没有记录的卡号"){
								//	$("#html_bank"+jsonObj[i].elecod+"").html(bnk);
									istype = "1";
								}else{
									$("#html_bank"+jsonObj[i].elecod+"").html("所属银行："+bnk);
									$("#html_"+jsonObj[i].elecod+"").html("");
									istype = "1";
								}
							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
						//	$("#html_"+jsonObj[i].elecod+"").html("加载信息erro:" + textStatus + errorThrown);
						},
						complete : function(XMLHttpRequest, textStatus) {
						}
					});
					if(istype = "1"){
						return "true";
					}else{
						return "false";
					}
				}else{
					$("#html_"+jsonObj[i].elecod+"").html("");
					return "true";
				
				}
			}else{
				if(elename == ""){
					$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"不能为空！");
					$("input[name='isverif']").removeAttr("checked");
					return "false";
				}else{ 
					if(/\s/.test(elename)){
						$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"不能有空格！");
						return "false";
				    }
					if (elename.length < 16 || elename.length > 19) {
						$("#html_"+jsonObj[i].elecod+"").html("卡号长度必须16到19之间!");
						$("input[name='isverif']").removeAttr("checked");
						return "false";
					}
					var num = /^\d*$/;  //全数字
					if (!num.exec(elename)) {
						$("#html_"+jsonObj[i].elecod+"").html("卡号必须全为数字!");
						$("input[name='isverif']").removeAttr("checked");
						return "false";
					}
					var data = {wfDate : JSON.stringify({
											bankCode:elename
								})}
					$.ajax({
						url :  ctx+"/queryBank.action",
						type : "post",
						data : data,
						beforeSend : function(XMLHttpRequest) {
							XMLHttpRequest.setRequestHeader("RequestType", "ajax");
						},
						async : false,
						dataType : "json",
						success : function(data, textStatus) {	
								data = JSON.parse(data.wfDate);
							if (data != "") {
								var bnk = data.bankName; 
								if(bnk == "没有记录的卡号"){ 
									//$("#html_bank"+jsonObj[i].elecod+"").html(bnk); 
									istype = "1";
								}else{
									$("#html_bank"+jsonObj[i].elecod+"").html("<font size='2px'>所属银行:"+bnk+"</font>");
									$("#html_"+jsonObj[i].elecod+"").html("");
									istype = "1";
								}
							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
						//	$("#html_"+jsonObj[i].elecod+"").html("加载信息erro:" + textStatus + errorThrown);
						},
						complete : function(XMLHttpRequest, textStatus) {
						}
					}); 
					$("#html_"+jsonObj[i].elecod+"").html("");
					if(istype == "1"){
						return "true";
					}else{
						return "false";
					}
				}
			}
		}
	}
	
	
}
//对电子邮件的验证
function checkemail(){
	var jsondata = $("#jsondata").val();
	if(jsondata == "0"){
		return "false";
	}   
	var jsonObj  = JSON.parse(jsondata);
	var dataVar = "";
	for(var i=0;i<jsonObj.length;i++){
		var temp = $("#"+jsonObj[i].elecod+"").val();
		 if(jsonObj[i].datatype == veremail){
			 if(jsonObj[i].issbc == issbcyes){
				var reissbc = verifissbc(elename);
				if(reissbc == true){
					$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"要包含全角！");
					return "false";
				}
				}else{
					var reissbc = verifissbc(elename);
					if(reissbc == false){
						$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"不能包含全角！");
						return "false";
					}
				}
			 	var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
				if(jsonObj[i].isnull == isnullyes){
					if(temp != ""){
						if(!myreg.test(temp)){
							$("#html_"+jsonObj[i].elecod+"").html('请输入有效的邮箱！');
							$("input[name='isverif']").removeAttr("checked");
							return "false";
						}
					}
				}else{
					if(temp !=""){
						if(!myreg.test(temp)){
							$("#html_"+jsonObj[i].elecod+"").html('请输入有效的邮箱！');
							$("input[name='isverif']").removeAttr("checked");
							return "false";
						}
					}else{
						$("#html_"+jsonObj[i].elecod+"").html('请输入E_mail！');
						return "false";
					}
				}
				$("#html_"+jsonObj[i].elecod+"").html("");
		 }
	}
	return "true";
} 
//验证身份证
function verifidcard(){
	var jsondata = $("#jsondata").val();
	if(jsondata == "0"){
		return "false";
	}   
	var jsonObj  = JSON.parse(jsondata);
	var dataVar = "";
	for(var i=0;i<jsonObj.length;i++){
		var elename = $("#"+jsonObj[i].elecod+"").val();
		 if(jsonObj[i].datatype == datetypeprsncod){
		   if(jsonObj[i].issbc == issbcyes){
				var reissbc = verifissbc(elename);
				if(reissbc == true){
					$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"要包含全角！");
					return "false";
				}
			}else{
				var reissbc = verifissbc(elename);
				if(reissbc == false){
					$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"不能包含全角！");
					return "false";
				}
			}
			if(jsonObj[i].isnull == isnullyes){
				if(elename != ""){
					var prsncod =	IdentityCodeValid(elename);
					if (prsncod == false) {
						$("input[name='isverif']").removeAttr("checked");
						return "false";
					}
				}
			}else{
				if(elename == ""){   
					$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"不能为空！");
					$("input[name='isverif']").removeAttr("checked");
					return "false";
				}else{
					var prsncod =	IdentityCodeValid(elename);
					if (prsncod == false) {
						$("input[name='isverif']").removeAttr("checked");
						return "false";
					}
				}
			}
			$("#html_"+jsonObj[i].elecod+"").html("");
		}
	}
	
}

//验证密码
function passvierif(elecod){
	var jsondata = $("#jsondata").val();
	if(jsondata == "0"){
		return "false";
	}
	var jsonObj  = JSON.parse(jsondata);
	var dataVar = "";
	for(var i=0;i<jsonObj.length;i++){
		var elename = $("#"+jsonObj[i].elecod+"").val();
		  if(elecod == jsonObj[i].elecod){
			if(jsonObj[i].datatype == datetypepass){
				if(jsonObj[i].issbc == issbcyes){
					var reissbc = verifissbc(elename);
					if(reissbc == true){
						$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"要包含全角！");
						return "false";
					}
				}else{
					var reissbc = verifissbc(elename);
					if(reissbc == false){
						$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"不能包含全角！");
						return "false";
					}
				}
				if(jsonObj[i].isnull == isnullyes){
					if(elename != ""){
						if (elename.length < jsonObj[i].minlen || elename.length > jsonObj[i].maxlen) {
							$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"长度必须在"+jsonObj[i].minlen+"到"+jsonObj[i].maxlen+"之间！");
							$("input[name='isverif']").removeAttr("checked");
							return "false";
						}
					}
				}else{
					if(elename == ""){   
						$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"不能为空！");
						$("input[name='isverif']").removeAttr("checked");
						return "false";
					}else{
						if (elename.length < jsonObj[i].minlen || elename.length > jsonObj[i].maxlen) {
							$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"长度必须在"+jsonObj[i].minlen+"到"+jsonObj[i].maxlen+"之间！");
							$("input[name='isverif']").removeAttr("checked");
							return "false";
						}
					}
				}
				$("#html_"+jsonObj[i].elecod+"").html("");
			}
		  }
	}

}  
//验证手机号
function telnovierif(){
	var jsondata = $("#jsondata").val();
	if(jsondata == "0"){
		return "false";
	}
	var jsonObj  = JSON.parse(jsondata);
	var dataVar = "";
	for(var i=0;i<jsonObj.length;i++){
		var elename = $("#"+jsonObj[i].elecod+"").val();
		
		 if(jsonObj[i].datatype == datetypetelno){
			 if(jsonObj[i].issbc == issbcyes){
					var reissbc = verifissbc(elename);
					if(reissbc == true){
						$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"要包含全角！");
						return "false";
					}
				}else{
					var reissbc = verifissbc(elename);
					if(reissbc == false){
						$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"不能包含全角！");
						return "false";
					}
			  }
			  if(jsonObj[i].isnull == isnullyes){
				  if(elename != ""){
					//手机号验证
					var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
					if (!reg.test(elename)) {
						  $("#html_"+jsonObj[i].elecod+"").html("手机号格式不正确！");
						  $("input[name='isverif']").removeAttr("checked");
						  return "false";
					}
				  }
				}else{
					if(elename == ""){
						$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"不能为空！");
						$("input[name='isverif']").removeAttr("checked");
						return "false";
					}else{
						//手机号验证
						var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
						if (!reg.test(elename)) {
							$("#html_"+jsonObj[i].elecod+"").html("手机号格式不正确！");
							  $("input[name='isverif']").removeAttr("checked");
							  return "false";
						}
					}
				}
				$("#html_"+jsonObj[i].elecod+"").html("");
			}
	}
} 
//验证字符
function strvierif(elecod){
	var jsondata = $("#jsondata").val();
	if(jsondata == "0"){
		return "false";
	}
	var jsonObj  = JSON.parse(jsondata);
	var dataVar = "";
	for(var i=0;i<jsonObj.length;i++){
		var elename = $("#"+jsonObj[i].elecod+"").val();
		   if(elecod == jsonObj[i].elecod){
			if(jsonObj[i].datatype == datetypechar){
				if(jsonObj[i].issbc == issbcyes){
					var reissbc = verifissbc(elename);
					if(reissbc == true){
						$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"要包含全角！");
						return "false";
					}
				}else{
					var reissbc = verifissbc(elename);
					if(reissbc == false){
						$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"不能包含全角！");
						return "false";
					}
				}
				if(jsonObj[i].isnull == isnullyes){
					if(elename != ""){
						if(/\s/.test(elename)){
							$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"不能有空格！");
							return "false";
					    }
						if (elename.length < jsonObj[i].minlen || elename.length > jsonObj[i].maxlen) {
							$("#html_"+jsonObj[i].elecod+"").html("长度必须在"+jsonObj[i].minlen+"到"+jsonObj[i].maxlen+"之间！");
							$("input[name='isverif']").removeAttr("checked");
							return "false";
						}
					}
				}else{
					if(elename == ""){
						$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"不能为空！");
						$("input[name='isverif']").removeAttr("checked");
						return "false";
					}else{
						if(/\s/.test(elename)){
							$("#html_"+jsonObj[i].elecod+"").html(""+jsonObj[i].elenam+"不能有空格！");
							return "false";
					    }
						if (elename.length < jsonObj[i].minlen || elename.length > jsonObj[i].maxlen) {
							$("#html_"+jsonObj[i].elecod+"").html("长度必须在"+jsonObj[i].minlen+"到"+jsonObj[i].maxlen+"之间！");
							$("input[name='isverif']").removeAttr("checked");
							return "false";
						}
					}
				}
				$("#html_"+jsonObj[i].elecod+"").html("");
			}
		 }
	}
	
}
//验证是否包含全角
function verifissbc(str)//True 没有全角，False有全角
{
    for (var i = 0; i < str.length; i++)
    {
        strCode = str.charCodeAt(i);
        if ((strCode > 65248) || (strCode == 12288))
        {
            return false;
        }
    }
    return true;
}