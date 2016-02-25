var zflag = true;//全表单信息验证
var checkobj = ""; 
function accAdd(){
		var telno = $("#phnone").val();//联系人电话
		var compno = $("#compno").val();//身份证号
		var compnam = $("#compnam").val();//公司名称
		var prsnnam = $("#prsnnam").val();//联系人姓名
		var strdte =$("#strdte").val();   //平台使用期限(开始时间)
		var enddte =$("#enddte").val();   //平台使用期限(结束时间)
		//手机号验证
		var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
		if (!reg.test(telno)) {
			  alert("手机号格式不正确！");
			  return;
		}
		//姓名非空
		if(prsnnam == ""){
			alert("请输入姓名！");
			return;
		}
}
$(function(){
	findCurrentInfo();
});
function findCurrentInfo(){
	$.ajax({
		url : "findCurrentCompInfo.action",
		type : "post",
		data:'',
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
//		dataType : "json",
		success : function(data, textStatus) {
				if (data != null) {
					var obj = JSON.parse(data);
					if(obj!=null){
						if(obj.length>1){
							 checkobj = obj[0];	
							for(var i=0;i<obj.length;i++){
								if(i==0){
								document.getElementById("id").value=obj[i].ID;
								document.getElementById("compnam").value=obj[i].COMPNAM;
								document.getElementById("compaddr").value=obj[i].COMPADDR;
								document.getElementById("prsnnam").value=obj[i].PRSNNAM;
								document.getElementById("phnone").value=obj[i].PHNONE;
								document.getElementById("strdte").value=obj[i].STRDTE;
								document.getElementById("enddte").value=obj[i].ENDDTE;
								document.getElementById("sta").value=obj[i].STA;
								document.getElementById("compno").value=obj[i].COMPNO;
								document.getElementById("comptyp").value=obj[i].COMPTYP;
								}else{
									document.getElementById("flag").value=obj[1];	
								}
							}
							var ds = $("#flag").val();
							if(ds==1){		
								power();
							}
						}else{
							document.getElementById("flag").value=obj[0];
							var ds = $("#flag").val();
							if(ds==1){		
								power();
							}
						}
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
function power(){
	document.getElementById("compnam").disabled="disabled";
	document.getElementById("compaddr").disabled="disabled";
	document.getElementById("prsnnam").disabled="disabled";
	document.getElementById("phnone").disabled="disabled";
	document.getElementById("strdte").disabled="disabled";
	document.getElementById("enddte").disabled="disabled";
	document.getElementById("round2").style.display = "none";
}

function submitInfo(){
	if(perfact()&&comper()){
		if(zflag ){
	 if(checkobj.COMPNAM != null){
	    var checkflag = checkpatams();
	    if(checkflag){
	    	var b =confirm("是否保存数据！")
			if(b){
			 goToUpdate();
			}	
	    }
	 }else{
		 var b =confirm("是否保存数据！")
			if(b){
			 goToUpdate();
			}	
	 }
	}
	}

}
/**
 * 校验提交时页面数据是否修改
 * @returns {Boolean}
 */
function checkpatams(){
	 if(checkobj.COMPNAM ==  $("#compnam").val() && checkobj.COMPADDR==$("#compaddr").val() 
			 && checkobj.PRSNNAM == $("#prsnnam").val() && checkobj.PHNONE ==  $("#phnone").val()
			 && checkobj.STRDTE == $("#strdte").val() && checkobj.ENDDTE == $("#enddte").val()){
		 alert('没有修改数据不能提交');
		 return false;
	 }else{
		 return true;
	 }
}

/**
 * 异步保存公司基本信息
 */
function goToUpdate(){
	var compnam = $("#compnam").val();
	var compaddr = $("#compaddr").val();
	var prsnnam = $("#prsnnam").val();
	var phnone = $("#phnone").val();
	var strdte = $("#strdte").val();
	var enddte = $("#enddte").val();
	
	var data = {
		frontObjStr : JSON.stringify({
			compnam : compnam,
			compaddr : compaddr,
			prsnnam : prsnnam,
			phnone : phnone,
			strdte : strdte,
			enddte : enddte
		})
	};
	$.ajax({
		url : "updateCompInfo.action",
		type : "post",
		data : data,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		success : function(data, textStatus) {
			if (data != null) {
				var obj = JSON.parse(data);
				alert("保存成功");
				window.location.reload(true);

				
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("加载信息erro:" + textStatus + errorThrown);
		},
		complete : function(XMLHttpRequest, textStatus) {
		}
	});
}
function perfact(){
	var flag =false;
	var flagOne = true;
	var falgTwo = true;
	var falgTrd = true;
	var falgFour = true;
	var compnam = $("#compnam").val();//公司名称
	var telno = $("#phnone").val();//联系人电话
	var starTime =$("#strdte").val();//开始时间
	var endTime =$("#enddte").val();//结束时间
	var compaddr =$("#compaddr").val();
	var prsnnam =$("#prsnnam").val();
	var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;//手机验证
	var tel= /^([0-9]{3,4}-)?[0-9]{7,8}$/;//座机验证
	if(""==compnam||null==compnam){
		alert("公司名不可以空！");
		flagOne = false;
		return false;
	}
	if(""==compaddr||null==compaddr){
		alert("公司地址不可以空");
		return false;
	}
	if(""==prsnnam||null==prsnnam){
		alert("公司联系人不可以空");
		return false;
	}
	if(""==telno||null==telno){
		alert("请填写联系方式！");
		falgTwo = false;
		return false;
	}else{
		if ((reg.test(telno))||(tel.test(telno))) {
		}else{
			 alert("电话号格式不正确！");
			  falgTwo = false;
			  return false;
		}
	}
	if(""==starTime||starTime==null){
		alert("平台使用开始时间不可以空！");
		falgTrd = false;
		return false;
	}
	if(""==endTime||endTime==null){
		alert("平台使用截止时间不可以空！");
		falgFour = false;
		return false;
	}
	if(flagOne&&falgTwo&&falgTrd&&falgFour){
		flag = true;
	}
	return flag;
}

function comper(){
	var falgTrd = true;
	var strdte =$("#strdte").val();   //平台使用期限(开始时间)
	var enddte =$("#enddte").val();   //平台使用期限(结束时间)
	if(""==strdte&&null==strdte&&""==enddte&&null==enddte){
	}else{
		if(strdte>enddte){
			alert("结束时间不可小于开始时间");
			falgTrd = false;
		}
	}
	return falgTrd;
}
/**
 * 电话号码验证
 */
function telPrefact(){
	var telno = $("#phnone").val();//联系人电话
	var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/; 
	var tel= /^([0-9]{3,4}-)?[0-9]{7,8}$/;//座机验证
	if(""!=telno&&null!=telno){
		
		if ((reg.test(telno))||(tel.test(telno))){
			$("#telphone").html("");
			zflag = true;
//			$("#telphone").html("电话号码格式不正确"); 
//			zflag = false;
		}else{
			$("#telphone").html("电话号码格式不正确"); 
			zflag = false;
			/*$("#telphone").html("");
			zflag = true;*/
		}
	}
}
/**
 * 地址长度验证
 */
function addressPrefact(){
	var reg = /^[\u4E00-\u9FA5A-Za-z0-9_]+$/;//汉字、字母、数字、下划线
	var address =$("#compaddr").val();
	for(var i = 0; i < address.length; i++){
		 strCode = address.charCodeAt(i);
		 if ((strCode > 65248) || (strCode == 12288)){
			 $("#address").html("不可以输入全角字符！");
			 zflag = false;
		   return false; 
		 }
	}
	if(typewriting(address)){
		if(""!=address&&address!=null){
			if(address.length>40){
				$("#address").html("长度不可以大于40");
				zflag = false;
			}else{
				$("#address").html("");
				if(!reg.test(address)){
					$("#address").html("请输入中文/字母/数字/_");
					zflag = false;
				}else{
					$("#address").html("");
					zflag = true;
				}
			}
		}
	}
	
}
/**
 * 公司名长度验证
 */
function namePrefact(){
	var reg = /^[0-9a-zA-Z\u4e00-\u9fa5]{3,20}$/;//中文/字母/数字
	var compName= $("#compnam").val();
	for(var i = 0; i < compName.length; i++){
		 strCode = compName.charCodeAt(i);
		 if ((strCode > 65248) || (strCode == 12288)){
			 $("#compName").html("不可以输入全角字符！");
			 zflag = false;
		   return false; 
		 }
	}
	//if(typewriting(compName,id)){
		if(""!=compName&&compName!=null){
			if(compName.length>20){
				$("#compName").html("长度不可以大于20");
			zflag = false;
			}else{
				$("#compName").html("");
				if(!reg.test(compName)){
					$("#compName").html("请输入中文/字母/数字");
					zflag = false;
				}else{
					$("#compName").html("");
					zflag = true;
				}
			}
		}
//	}
	
}
/**
 * 联系人姓名验证
 */
function peolpeNamePrefact(){
	var peopleName= $("#prsnnam").val();
	var reg = /^[a-zA-Z\u4e00-\u9fa5]{1,8}$/;//汉字和字母   
	for(var i = 0; i < peopleName.length; i++){
		 strCode = peopleName.charCodeAt(i);
		 if ((strCode > 65248) || (strCode == 12288)){
			 $("#peopleName").html("不可以输入全角字符！");
			 zflag = false;
		   return false; 
		 }
	}
	if(""!=peopleName&&peopleName!=null){
		if(peopleName.length>8){
			$("#peopleName").html("长度不可以大于8");
		zflag = false;
		}else{
			$("#peopleName").html("");
			if(!reg.test(peopleName)){
				$("#peopleName").html("请输入中文/字母");
				zflag = false;
			}else{
				$("#peopleName").html("");
				zflag = true;
			}

		}
	}
}
/**
 * 全角半角判断
 */
function typewriting(str,id){
	for (var i = 0; i < str.length; i++) {
	 strCode = str.charCodeAt(i);
	 if ((strCode > 65248) || (strCode == 12288)) {	
		
		 document.getElementById(id).html("不可以输入全角字符！");
		 zflag = false;
		 return false;
	 	}
	 }
	 return true;
}
