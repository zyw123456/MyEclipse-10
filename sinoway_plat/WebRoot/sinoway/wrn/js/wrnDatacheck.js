function checkNull(elem){
	var theelem=elem;
	if (elem.value.replace(/(^s*)|(s*$)/g, "").length ==0||elem.value==-1) 
	{ 
		if(elem.id=="prsnnam"){
			alert('名字不能为空');
			document.getElementsByName("prsnnam")[0].focus();
		}
		if(elem.id=="prsncod"){
			alert('身份证号不能为空');
			document.getElementsByName("prsncod")[0].focus();
			}
		if(elem.id=="tel"){
			alert('电话号码不能为空');
			document.getElementsByName("tel")[0].focus();
		}
		if(elem.id=="loantyp"){
			alert('贷款类型不能为空');
			document.getElementsByName("loantyp")[0].focus();
			}
		if(elem.id=="loanamt"){
			alert('贷款金额不能为空');
			document.getElementsByName("loanamt")[0].focus();
		}
		if(elem.id=="loanlmt"){
			alert('贷款期限不能为空');
			document.getElementsByName("loanlmt")[0].focus();
			}
		if(elem.id=="loansrtdte"){
			alert('贷款开始时间不能为空');
			document.getElementsByName("loansrtdte")[0].focus();
			}
		if(elem.id=="loansenddte"){
			alert('贷款结束时间不能为空');
			document.getElementsByName("loanenddte")[0].focus();
			}
		if(elem.id=="repaytyp"){
			alert('还款方式不能为空');
			document.getElementsByName("repaytyp")[0].focus();
			}
		if(elem.id=="repaydte"){
			alert('还款日期不能为空');
			document.getElementsByName("repaydte")[0].focus();
			}
		if(elem.id=="repayamt"){
			alert('还款金额不能为空');
		document.getElementsByName("repayamt")[0].focus();
		}		
	}
	if(elem.id=="tel"){
		phoneNumber_checkValidate(elem);
	}
}

function phoneNumber_checkValidate(phoneNum){
	var phoneNumber= document.getElementsByName(phoneNum.id)[0].value
//	手机号码验证规则：1+3、4、5、6、7、8+九位随机数；
	 var reg = /^1[3|4|5|7|8][0-9]\d{8}$/;
	 if (reg.test(phoneNumber)) {
	      document.getElementsByName("tel")[0].style.borderColor="green";
	 }else{
	      alert("手机号码有误~");
	      document.getElementsByName("tel")[0].style.borderColor="red";
	 }
}


