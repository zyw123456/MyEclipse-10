function searchProductList() {	
	$.ajax({
		url : "/windforce/loadPrdcod.action",
		type : "post",
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		dataType : "json",
		success : function(data, textStatus) {	
			if (data != "") {
				if (data.list != "") {
					var obj = data.list; 
					var showStr = "";
					for(var i=1;i<=obj.length;i++){
						var str=obj; 
						showStr += 
							" <input type='radio' checked='checked' id="+str[i-1][1]+" name='prdcod'  value="+str[i-1][0]+" /><label for="+str[i-1][1]+">"+str[i-1][1]+ "</label>&nbsp;&nbsp;";																
					}
					$("#mb").html(showStr);
				}else{
					alert(data.errCode);
					var errp ="<strong style='color: red'>您的策略可能被主账号清空啦！！</strong>";
					$("#mb").html(errp);
				}
			}},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("加载信息erro:" + textStatus + errorThrown);
			},
			complete : function(XMLHttpRequest, textStatus) {
			}
	});
}
//字段的非空验证
function checkNull(elem){
	if (elem.value.replace(/(^s*)|(s*$)/g, "").length ==0||elem.value==-1) 
	{ 
		if(elem.id=="prsnnam"){
			document.getElementById("t_prsnnam").innerHTML="";
			document.getElementById("t_prsnnam").innerHTML="<strong style='color: red'>不能为空</strong>";
			document.getElementsByName("prsnnam")[0].style.borderColor="red";
		}
		if(elem.id=="prsncod"){		
			document.getElementById("t_prsncod").innerHTML="";
			document.getElementById("t_prsncod").innerHTML="<strong style='color: red'>不能为空</strong>";
			document.getElementsByName("prsncod")[0].style.borderColor="red";
		}
		if(elem.id=="telno"){		
			document.getElementById("t_tel").innerHTML="";
			document.getElementById("t_tel").innerHTML="<strong style='color: red'>不能为空</strong>";
			document.getElementsByName("telno")[0].style.borderColor="red";
		}
		if(elem.id=="loantyp"){	
			document.getElementById("t_loantyp").innerHTML="";
			document.getElementById("t_loantyp").innerHTML="<strong style='color: red'>不能为空</strong>";
			document.getElementsByName("loantyp")[0].style.borderColor="red";
		}
		if(elem.id=="loanamt"){	
			document.getElementById("t_loanamt").innerHTML="";
			document.getElementById("t_loanamt").innerHTML="<strong style='color: red'>不能为空</strong>";
			document.getElementsByName("loanamt")[0].style.borderColor="red";
		}
		if(elem.id=="loanlmt"){	
			document.getElementById("t_loanlmt").innerHTML="";
			document.getElementById("t_loanlmt").innerHTML="<strong style='color: red'>不能为空</strong>";
			document.getElementsByName("loanlmt")[0].style.borderColor="red";
		}
		if(elem.id=="loansrtdte"){
			document.getElementById("t_loansrtdte").innerHTML="";
			document.getElementById("t_loansrtdte").innerHTML="<strong style='color: red'>不能为空</strong>";
			document.getElementsByName("loansrtdte")[0].style.borderColor="red";
		}
		if(elem.id=="loanenddte"){

			document.getElementById("t_loanenddte").innerHTML="";
			document.getElementById("t_loanenddte").innerHTML="<strong style='color: red'>不能为空</strong>";
			document.getElementsByName("loanenddte")[0].style.borderColor="red";
		}
		if(elem.id=="repaytyp"){

			document.getElementById("t_repaytyp").innerHTML="";
			document.getElementById("t_repaytyp").innerHTML="<strong style='color: red'>不能为空</strong>";
			document.getElementsByName("repaytyp")[0].style.borderColor="red";
		}
		if(elem.id=="repaydte"){

			document.getElementById("t_repaydte").innerHTML="";
			document.getElementById("t_repaydte").innerHTML="<strong style='color: red'>不能为空</strong>";
			document.getElementsByName("repaydte")[0].style.borderColor="red";
		}

		if(elem.id=="repayamt"){
			document.getElementById("t_repayamt").innerHTML="";
			document.getElementById("t_repayamt").innerHTML="<strong style='color: red'>不能为空</strong>";
			document.getElementsByName("repayamt")[0].style.borderColor="red";
		}

		if(elem.id=="telno"){
			phoneNumber_checkValidate(elem);
		}		
	}
	else if(elem.value.replace(/(^s*)|(s*$)/g, "").length !=0||elem.value!=-1){
		if(elem.id=="prsnnam"){
			document.getElementById("t_prsnnam").innerHTML="可用";
			document.getElementsByName("prsnnam")[0].style.borderColor="green";
		}
		if(elem.id=="prsncod"){
			var vval=document.getElementsByName(elem.id)[0].value
			var ttm=IdentityCodeValid(vval);
			if(ttm){				
				document.getElementById("t_prsncod").innerHTML="可用";
				document.getElementsByName("prsncod")[0].style.borderColor="green";
			}else{
				document.getElementById("t_prsncod").innerHTML="<strong style='color: red'>身份证输入有误！！</strong>";
				document.getElementsByName("prsncod")[0].style.borderColor="red";
			}

		}
		if(elem.id=="loantyp"){
			document.getElementById("t_loantyp").innerHTML="可用";
			document.getElementsByName("loantyp")[0].style.borderColor="green";
		}
		if(elem.id=="loanamt"){
			var a=/^[0-9]*(\.[0-9]{1,2})?$/;
			if(!a.test($("#loanamt").val())){
				alert("请输入正确的金额！");
				document.getElementById("t_loanamt").innerHTML="";
				document.getElementById("t_loanamt").innerHTML="不可用";
				document.getElementsByName("loanamt")[0].style.borderColor="red";
			}
			else{
				document.getElementById("t_loanamt").innerHTML="";
				document.getElementById("t_loanamt").innerHTML="可用";
				document.getElementsByName("loanamt")[0].style.borderColor="green";
			}
		}
		if(elem.id=="loanlmt"){

			document.getElementById("t_loanlmt").innerHTML="可用";
			document.getElementsByName("loanlmt")[0].style.borderColor="green";
		}
		if(elem.id=="loansrtdte"){
			document.getElementById("t_loansrtdte").innerHTML="可用";
			document.getElementsByName("loansrtdte")[0].style.borderColor="green";
		}
		if(elem.id=="loanenddte"){
			document.getElementById("t_loanenddte").innerHTML="可用";
			document.getElementsByName("loanenddte")[0].style.borderColor="green";
		}
		if(elem.id=="repaytyp"){
			document.getElementById("t_repaytyp").innerHTML="";
			document.getElementById("t_repaytyp").innerHTML="可用";
			document.getElementsByName("repaytyp")[0].style.borderColor="green";
		}
		if(elem.id=="repaydte"){
			document.getElementById("t_repaydte").innerHTML="可用";
			document.getElementsByName("repaydte")[0].style.borderColor="green";
		}

		if(elem.id=="repayamt"){
			var a=/^[0-9]*(\.[0-9]{1,2})?$/;
			if(!a.test($("#repayamt").val())){
				alert("请输入正确的金额！");
				document.getElementById("t_repayamt").innerHTML="";
				document.getElementById("t_repayamt").innerHTML="不可用";
				document.getElementsByName("repayamt")[0].style.borderColor="red";
			}
			else{
				document.getElementById("t_repayamt").innerHTML="可用";
				document.getElementsByName("repayamt")[0].style.borderColor="green";
			}
		}

		if(elem.id=="telno"){
			phoneNumber_checkValidate(elem);
		}	
	}

}

//手机号码验证
function phoneNumber_checkValidate(phoneNum){
	var phoneNumber= document.getElementsByName(phoneNum.id)[0].value
//	手机号码验证规则：1+3、4、5、6、7、8+九位随机数；
	var reg = /^1[3|4|5|7|8][0-9]\d{8}$/;
	if (reg.test(phoneNumber)) {
		document.getElementsByName("telno")[0].style.borderColor="green";
		document.getElementById("t_tel").innerHTML="可用";
	}else{
		document.getElementById("t_tel").innerHTML="<strong style='color: red'>请填写正确的号码</strong>";
		document.getElementsByName("telno")[0].style.borderColor="red";
	}
}



//表单的非空验证
function checkForm(){
	if(
			document.getElementById('prsnnam').value==''||
			document.getElementById('prsncod').value==''||
			document.getElementById('telno').value==''||
			document.getElementById('loantyp').value==''||
			document.getElementById('loanamt').value==''||
			document.getElementById('loanlmt').value==''||
			document.getElementById('loansrtdte').value==''||
			document.getElementById('loanenddte').value==''||
			document.getElementById('repaytyp').value==''||
			document.getElementById('repaydte').value==''||
			document.getElementById('repayamt').value==''||
			document.getElementById('repaytyp').value=='-1'
	) {
		alert('信息必须输入完整！');
		return false;
	}
	else{	
//		alert("true");
		return true;
//		document.getElementById('myform').submit();
	}
}
//AJAX提交表单数据
function submitForm(){
	var fuun=checkForm();
	if(fuun){
		$.ajax({
			type:"POST",
			url:"/windforce/addWarnPerson.action",
			async:true,
			dataType: "json",
			data:{
				prsnnam : $("#prsnnam").val(),
				prsncod : $("#prsncod").val(),
				loantyp : $("#loantyp").val(),
				loanamt : $("#loanamt").val(),
				loanlmt : $("#loanlmt").val(),
				telno   :  $("#telno").val(),
				repaydte :$("#repaydte").val(),
				repaytyp :$("#repaytyp").val(),
				repayamt :$("#repayamt").val(),
				prdcod :  $('#mb input[name="prdcod"]:checked ').val(),
				loansrtdte :$("#loansrtdte").val(),
				loanenddte :$("#loanenddte").val()
			},
			success: function(data) {
				if (data != "") {
					if (data.bugeCode ==null) {
						alert("新增预警人员成功");
					}else{
						alert(data.bugeCode);
					}
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("加载信息erro:" + textStatus + errorThrown);
			},
			complete : function(XMLHttpRequest, textStatus) {
			}
		});
	}
}