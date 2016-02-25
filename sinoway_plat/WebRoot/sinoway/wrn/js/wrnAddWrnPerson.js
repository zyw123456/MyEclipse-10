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
				if (data.list != null) {
					var obj = data.list; 
					var showStr = "";
					for(var i=1;i<=obj.length;i++){
						 var str=obj; 
						showStr += 
						" <input type='radio' checked='checked' id="+str[i-1][1]+" name='prdcod'  value="+str[i-1][0]+" /><label for="+str[i-1][1]+">"+str[i-1][1]+ "</label>&nbsp;&nbsp;";																
					}
					$("#mb").html(showStr);
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

//	字段的非空验证
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
		if(elem.id=="tel"){
		
			document.getElementById("t_tel").innerHTML="";
			document.getElementById("t_tel").innerHTML="<strong style='color: red'>不能为空</strong>";
			document.getElementsByName("tel")[0].style.borderColor="red";
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
		
		if(elem.id=="tel"){
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
			document.getElementById("t_loanlmt").innerHTML="可用";
			document.getElementsByName("loanamt")[0].style.borderColor="green";
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
			document.getElementById("t_repayamt").innerHTML="可用";
		document.getElementsByName("repayamt")[0].style.borderColor="green";
		}
		
		if(elem.id=="tel"){
			phoneNumber_checkValidate(elem);
		}	
		
}

//	手机号码验证
function phoneNumber_checkValidate(phoneNum){
	var phoneNumber= document.getElementsByName(phoneNum.id)[0].value
//	手机号码验证规则：1+3、4、5、6、7、8+九位随机数；
	 var reg = /^1[3|4|5|7|8][0-9]\d{8}$/;
	 if (reg.test(phoneNumber)) {
	      document.getElementsByName("tel")[0].style.borderColor="green";
	      document.getElementById("t_tel").innerHTML="可用";
	 }else{
		 document.getElementById("t_tel").innerHTML="<strong style='color: red'>请填写正确的号码</strong>";
	      document.getElementsByName("tel")[0].style.borderColor="red";
	 }
}
}

//	表单的非空验证
function checkForm(){
	 if(
			 document.getElementById('prsnnam').value==''||
			 document.getElementById('prsncod').value==''||
			 document.getElementById('tel').value==''||
			 document.getElementById('loantyp').value==''||
			 document.getElementById('loanamt').value==''||
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
	 }
	 else{
//		 AJAX提交表单数据
//		 
		 document.getElementById('myform').submit();
	 }
}


//身份证号合法性验证 
//支持15位和18位身份证号
//支持地址编码、出生日期、校验位验证
function IdentityCodeValid(code) { 
    var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
    var tip = "";
    var pass= true;
    
    if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
        tip = "身份证号格式错误";
        pass = false;
    }
    
   else if(!city[code.substr(0,2)]){
        tip = "地址编码错误";
        pass = false;
    }
    else{
        //18位身份证需要验证最后一位校验位
        if(code.length == 18){
            code = code.split('');
            //∑(ai×Wi)(mod 11)
            //加权因子
            var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
            //校验位
            var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
            var sum = 0;
            var ai = 0;
            var wi = 0;
            for (var i = 0; i < 17; i++)
            {
                ai = code[i];
                wi = factor[i];
                sum += ai * wi;
            }
            var last = parity[sum % 11];
            if(parity[sum % 11] != code[17]){
                tip = "校验位错误";
                pass =false;
            }
        }
    }
    if(!pass) alert(tip);
    return pass;
}