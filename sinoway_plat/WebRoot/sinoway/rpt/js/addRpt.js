
function showTranserText(prdcod,prdnam,prttyp){ 
	$("#prdcod").val(prdcod);
	$("#prdnam").val(prdnam);
	$("#prttyp").val(prttyp);
	$("a[name=rad").attr("class","Custom_te");
	$("#"+prdcod+"").removeClass("Custom_te");
	var prdcod = prdcod;
	var data = {fraudObjStr : JSON.stringify({
		prdcod : prdcod
	})}	
	$.ajax({
		url :  ctx+"/findPageElecod.action",
		type : "post",
		data : data,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : false,
		dataType : "json",
		success : function(data, textStatus) {	
			var dataMap = JSON.parse(data.fraudObjStr);
			data = dataMap; 
			if (data != "") {
				if (data.map != null) {	
					var jsonData = JSON.stringify(data.map.LIST); 
					var obj = data.map.LIST; 
					var htmlVar = "";
					var num = 0;
					for(var i=0;i<obj.length;i++){	
						var isnullVar = "";
						if(obj[i].isnull != isnullyes){
							isnullVar = "*";
						}
							
							if(obj[i].datatype == datetypechar){    							
								htmlVar += " <div class='col-md-4 col-sm-4 form-group'>" +
										   " <div class='input-group'><div class='input-group-addon'>"+isnullVar+ obj[i].elenam + "</div><input type='text' onblur=javascript:strvierif('"+obj[i].elecod+"');  class='form-control' id="+obj[i].elecod+" name ="+obj[i].elecod+" /></div>" +
										   " </div> <div class='col-md-2 col-sm-2 form-group tishi' style='color:red;' id='html_"+obj[i].elecod+"'></div>";
							}else if(obj[i].datatype == datetypetelno){
								htmlVar +=" <div class='col-md-4 col-sm-4 form-group'>" + 
										  " <div class='input-group'><div class='input-group-addon'>"+isnullVar+ obj[i].elenam + "</div><input type='text' onblur='javascript:telnovierif();' class='form-control' id="+obj[i].elecod+" name ="+obj[i].elecod+"/></div>" +
										  " </div> <div class='col-md-2 col-sm-2 form-group tishi' style='color:red;' id='html_"+obj[i].elecod+"'></div>";
							}else if(obj[i].datatype == datetypeprsncod){
								htmlVar += " <div class='col-md-4 col-sm-4 form-group'>" + 
										   "<div class='input-group'><div class='input-group-addon'>"+isnullVar+ obj[i].elenam + "</div><input type='text' onblur='javascript:verifidcard();' class='form-control' id="+obj[i].elecod+" name ="+obj[i].elecod+"/></div>" +
										   "</div> <div class='col-md-2 col-sm-2 form-group tishi' style='color:red;' id='html_"+obj[i].elecod+"'></div>";
							}else if(obj[i].datatype == datetypepass){
								htmlVar += " <div class='col-md-4 col-sm-4 form-group'>" + 
										   "<div class='input-group'><div class='input-group-addon'>"+isnullVar+ obj[i].elenam + "</div><input type='password' onblur=javascript:passvierif('"+obj[i].elecod+"');  class='form-control' id="+obj[i].elecod+" name ="+obj[i].elecod+"/></div>" +
										   "</div> <div class='col-md-2 col-sm-2 form-group tishi' style='color:red;' id='html_"+obj[i].elecod+"'></div>";
							}else if(obj[i].datatype == datetypeoddfile){
								htmlVar += " <div class='col-md-12 col-sm-12'>"+
							               " <div class='row'>"+
							               " <div class='col-md-6 col-sm-6 form-group'>" + 
										   "<div class='input-group'><div class='input-group-addon'>"+isnullVar+ obj[i].elenam + "</div><input type='file'  id='file' name='file' accept='.xml' class='form-control' /></div>" +
										   "<input type='hidden' id="+obj[i].elecod +" name ="+obj[i].elecod +" />" +
										   "</div> <div class='col-md-1 col-sm-1 form-group text-left tishi'> <input type='button' class='btn btn-primary' value='上传' onclick='ajaxFileUpload();' /></div>   <div class='col-md-5 col-sm-5 form-group text-left tishi'> (支持xml格式的文件上传)&nbsp;&nbsp;<font id='html_fileinfo' style='color:red;'></font></div>        </div>                </div>";
							}else if(obj[i].datatype == veremail){
								htmlVar += " <div class='col-md-4 col-sm-4 form-group'>" + 
									       "<div class='input-group'><div class='input-group-addon'>"+isnullVar+ obj[i].elenam + "</div><input type='text' onblur='javascript:checkemail();' class='form-control' id="+obj[i].elecod+" name ="+obj[i].elecod+"/></div>" +
										   "</div> <div class='col-md-2 col-sm-2 form-group tishi' style='color:red;' id='html_"+obj[i].elecod+"'></div>";
							}else if(obj[i].datatype == datetypebank){
								htmlVar += " <div class='col-md-4 col-sm-4 form-group'>" + 
										   "<div class='input-group'><div class='input-group-addon'>"+isnullVar+ obj[i].elenam + "</div><input type='text' onblur=javascript:verifbank();  class='form-control' id="+obj[i].elecod+" name ="+obj[i].elecod+" /></div>" +
										   "</div> <div class='col-md-2 col-sm-2 form-group tishi' ><font style='color:red;' id='html_"+obj[i].elecod+"'></font><font id='html_bank"+obj[i].elecod+"' style='font-size:14px;'></font></div>";
							}else if(obj[i].datatype == datetypepassport){
								htmlVar += " <div class='col-md-4 col-sm-4 form-group'>" +
								   " <div class='input-group'><div class='input-group-addon'>"+isnullVar+ obj[i].elenam + "</div><input type='text' onblur=javascript:checknumber();  class='form-control' id="+obj[i].elecod+" name ="+obj[i].elecod+" /></div>" +
								   " </div> <div class='col-md-2 col-sm-2 form-group tishi' style='color:red;' id='html_"+obj[i].elecod+"'></div>";
							}else{
								if(typeof(obj[i].elenam) == "undefined"){	
									$("#html_"+jsonObj[i].elecod+"").html("此原交易没有要素！");
								}else{
									htmlVar += " <div class='col-md-4 col-sm-4 form-group'>" + 
											   "<div class='input-group'><div class='input-group-addon'>"+isnullVar+ obj[i].elenam + "</div><input type='text' class='form-control' id="+obj[i].elecod+" name ="+obj[i].elecod+"/></div>" +
											   "</div> <div class='col-md-2 col-sm-2 form-group tishi' style='color:red;' id='html_"+obj[i].elecod+"'></div>";	
								}
							}
						
					}   //<input class='infofile' type='checkbox'  id='isverif' name='isverif' onclick='verifUploadInfo();'/><font size='4px'>上传信息是否需要验证</font>
					    htmlVar += "<div class='col-md-11 col-sm-11 col-sm-offset-1 form-group'><font id='upinfo' color='red'></font></div> <input type='hidden' id='jsondata' name='jsondata' value='"+jsonData+"' /></div>";
					$("#formhtml").html(htmlVar);
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#html_"+jsonObj[i].elecod+"").html("加载信息erro:" + textStatus + errorThrown);
		},
		complete : function(XMLHttpRequest, textStatus) {
		}
	});
	changeHeight();
}
//验证上传信息
function verifUploadInfo(){
	if($("input[type='checkbox']").is(':checked')){
		var jsondata = $("#jsondata").val();
		if(jsondata == "0"){
			return;
		}
		var jsonObj  = JSON.parse(jsondata);
		var dataVar = "";
		for(var i=0;i<jsonObj.length;i++){
			var elename = $("#"+jsonObj[i].elecod+"").val();
			   
				if(jsonObj[i].datatype == datetypechar){ 
					var stvi = strvierif(jsonObj[i].elecod); 
					if(stvi == "false"){
						return;
					}
				}else if(jsonObj[i].datatype == datetypetelno){
					var tvi = telnovierif();  
					if(tvi == "false"){
						return;
					}
				}else if(jsonObj[i].datatype == datetypepass){
					var pvi = passvierif();
					if(pvi == "false"){
						return;
					}
				}else if(jsonObj[i].datatype == datetypeprsncod){
					var verid = verifidcard();
					if(verid == "false"){
						return;
					}
				}else if(jsonObj[i].datatype == veremail){
					var chma = checkemail(); 
					if(chma == "false"){
						return;
					}
				}else if(jsonObj[i].datatype == datetypebank){
					
					var rebank = verifbank();
					if(rebank == "false"){
						return;
					} 
					$("#html_"+jsonObj[i].elecod+"").html("");
				}
				if(jsonObj[i].datatype == datetypeoddfile){
					dataVar = dataVar;
				}else{
					dataVar += '\"'+jsonObj[i].elecod+'\":\"'+$("#"+jsonObj[i].elecod+"").val()+'\",';
				}
			
		}
		var jsonPar = jsondata;
		var data ={
						datavar:dataVar,
						jsonpar:jsonPar
				 }
		
		$.ajax({
			url : ctx+"/verifUploadInfo.action",
			type : "post",
			data : data,
			beforeSend : function(XMLHttpRequest) {
				XMLHttpRequest.setRequestHeader("RequestType", "ajax");
			},
			async : true,
			dataType : "json",
			success : function(data) {
				if (data != "") {
					var obj = JSON.parse(data.wfDate); 
					$("#upinfo").html(obj.retPam);					
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//$("#html_"+jsonObj[i].elecod+"").html("加载信息erro:" + textStatus + errorThrown);
			},
			complete : function(XMLHttpRequest, textStatus) {
			}
		});
	}
}
//报告库新增
function rptAdd(){
		var cbcreportstype = "0";
		var jsondata = $("#jsondata").val();
		if(jsondata == "0"){
			return;
		}
		var jsonObj  = JSON.parse(jsondata);
		var dataVar = "";
		for(var i=0;i<jsonObj.length;i++){
			var elename = $("#"+jsonObj[i].elecod+"").val();
			
			if(jsonObj[i].datatype == datetypechar){
				var isstr = strvierif(jsonObj[i].elecod);
				if(isstr == "false"){
					return;
				}
			}else if(jsonObj[i].datatype == datetypetelno){
				var istel = telnovierif();
				if(istel == "false"){
					return;
				}
			}else if(jsonObj[i].datatype == datetypepass){
				var ispass = passvierif(jsonObj[i].elecod);
				if(ispass == "false"){
					return;
				}
			}else if(jsonObj[i].datatype == datetypeoddfile){
					if(elename == ""){
						$("#html_fileinfo").html("请上传"+jsonObj[i].elenam+"！");
						return;
					}  
					$("#html_"+jsonObj[i].elecod+"").html("");
			}else if(jsonObj[i].datatype == datetypeprsncod){
				var isidcard = verifidcard();
				if(isidcard == "false"){
					return;
				}
			}else if(jsonObj[i].datatype == veremail){
				var chma = checkemail(); 
				if(chma == "false"){
					return;
				}
				$("#html_"+jsonObj[i].elecod+"").html("");
			}else if(jsonObj[i].datatype == datetypebank){
				
				var rebank = verifbank();
				if(rebank == "false"){
					return;
				} 
				$("#html_"+jsonObj[i].elecod+"").html("");
			}else if(jsonObj[i].datatype == datetypepassport){
				
				var repassport = checknumber(); 
				if(repassport == false){
					return;
				} 
				$("#html_"+jsonObj[i].elecod+"").html("");
			}
				
				if(jsonObj[i].datatype == datetypeoddfile){
					cbcreportstype = "1";
					dataVar += '\"'+jsonObj[i].elecod+'\":\"\",'
				}else{
					dataVar += '\"'+jsonObj[i].elecod+'\":\"'+$("#"+jsonObj[i].elecod+"").val()+'\",'
				}
		}
		var jsonPar = jsondata;
		var prdcod = $('#prdcod').val(); 
		var prdnam = $('#prdnam').val();
		var data ={};
		if(cbcreportstype == "1"){
			data = {wfDate : JSON.stringify({
				   	datavar:dataVar,
					jsonpar:jsonPar,
					newrptid:$("#newrptid").val(),
					prdcod:prdcod,
					prdnam:prdnam,
					appcod:appcod,
					rptdte:$("#rptdte").val(),
					rpttim:$("#rpttim").val(),
					cbcreports:$("#cbcreports").val(),
					datcmitori:$("#datcmitoriid").val(),
					prttyp:$("#prttyp").val()
			})}
		}else{
			 data = {wfDate : JSON.stringify({
				 	datavar:dataVar,
					jsonpar:jsonPar,
					newrptid:$("#newrptid").val(),
					prdcod:prdcod,
					prdnam:prdnam,
					appcod:appcod,
					rptdte:$("#rptdte").val(),
					rpttim:$("#rpttim").val(),
					datcmitori:$("#datcmitoriid").val(),
					prttyp:$("#prttyp").val()
			})}
		}
		
		$.ajax({
			url : ctx+"/addRptAction.action",
			type : "post",
			data : data,
			beforeSend : function(XMLHttpRequest) {
				XMLHttpRequest.setRequestHeader("RequestType", "ajax");
			},
			async : true,
			dataType : "json",
			success : function(data) {
				if (data != "") {
					if (data.wfDate != null) {
						var obj = JSON.parse(data.wfDate); 
						if(obj.retPam[0] == resuccess){
							alert("提交成功！");
							location=location;
						}else{
							$("#html_"+jsonObj[i].elecod+"").html("报告新增失败！");
						}
					}
					
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
	//			$("#html_"+jsonObj[i].elecod+"").html("加载信息erro:" + textStatus + errorThrown);
			},
			complete : function(XMLHttpRequest, textStatus) {
			}
		});
}
//验证身份信息
function verifRpt(prsnnam,prsncod){
	var data = {
			wfDate : JSON.stringify({
				prsnnam:prsnnam,
				prsncod:prsncod
			})
			
	};
	$.ajax({
		url : ctx+"/verifRpt.action",
		type : "post",
		data : data,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		dataType : "json",
		success : function(data, textStatus) {		
			if (data != "") {
				if (data.wfDate != null) {
					var obj = JSON.parse(data.wfDate);
					return obj.retPam[0];
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		//	$("#html_"+jsonObj[i].elecod+"").html("加载信息erro:" + textStatus + errorThrown);
		},
		complete : function(XMLHttpRequest, textStatus) {
		}
	});
}
$(function(){
	var data = {wfDate : JSON.stringify({
			appcod:appcod
		})}
	$.ajax({
		url : ctx+"/findCredItRpt.action",
		type : "post",
		data : data,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : false,
		dataType : "json",
		success : function(data, textStatus) {					
			if (data != "") {
				if (data.wfDate != null) { 
					var obj = JSON.parse(data.wfDate); 
					var showStr = "";
					$("#ulhtml").html("<input type='hidden' id='prttyp'/><input type='hidden' id='prdcod'/><input type='hidden' id='prdnam'/>");
					if(obj.trns.length == 0){
						showStr +=  "<li align='center'>现在还没有策略。</li>"; 
					}else{
						$("#sumbtn").html('<button type="button" class="btn btn-primary" onclick="rptAdd();">提交</button>');
					}
					for(var i=0;i<obj.trns.length;i++){
						 var str=obj.trns[i]; 
						 var classs = "class='Custom_te'"; 
						 if(isdefult == str[3]){
							 classs = ""; 
							 showTranserText(str[0],""+str[1]+"",""+str[2]+"");
						 }
						showStr +=  "<li><a id='"+str[0]+"' name='rad' href=javascript:showTranserText('"+str[0]+"','"+ str[1]+"','"+str[2]+"'); "+classs+" >"+str[1]+"</a></li>";
					}
					$("#ulhtml").append(showStr);
					
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		//	$("#html_"+jsonObj[i].elecod+"").html("加载信息erro:" + textStatus + errorThrown);
		},
		complete : function(XMLHttpRequest, textStatus) {
		}
	});
	
})
//文件上传
function ajaxFileUpload() {  
	//判断上传文件格式
    var fil = $("#file").val();
    var fileType = fil.substring(fil.lastIndexOf(".")+1);  
    if($("#cbcreports").val() == ""){
	    if($("#file").val() == ""){
	    	 $("#html_fileinfo").html("请选择文件！");  
	         return; 
	    }
    }
    if($("#cbcreports").val() != ""){
   	 $("#html_fileinfo").html("文件已上传成功！");  
        return; 
    }
    if(fileType!="xml"){  
      $("#html_fileinfo").html("上传文件格式错误");  
      return;  
    }  
    $.ajaxFileUpload( {  
        url : ctx+'/rtpFileUpload.action',//用于文件上传的服务器端请求地址  
        secureuri : false,//一般设置为false  
        fileElementId : 'file',//文件上传控件的id属性  
        dataType : 'json',//返回值类型 一般设置为json  
        success : function(data, status) //服务器成功响应处理函数  
        {  
        	var obj = JSON.parse(data.wfDate); 
            //从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量  
        	if(obj.retPam != "1"){
        		$("#html_fileinfo").html("上传失败!");
        	}else{
        		$("#newrptid").val(obj.rptid);
          		$("#cbcreports").val(obj.fileStream);
          		$("#rptdte").val(obj.rptdte);
          		$("#rpttim").val(obj.rpttim);
        		$("#html_fileinfo").html("文件上传成功！");  
        	}
        },  
        error : function(data, status, e)//服务器响应失败处理函数  
        {  
         //   $("#html_"+jsonObj[i].elecod+"").html(e);  
        }  
    })  
    return false;  
} 
function chkEmail(strEmail) { 
	if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(strEmail)) { 
	return false; 
	} 
	else { 
	return true; 
	} 
}	
//姓名验证
function verifprsnnam(val){
	 
	 reg = /^[\u4E00-\u9FA5]{2,9}$/;
	 if(!reg.test(val)){
		 return false;
	 }else{
		 return true;
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
                  tip = "身份证号格式错误";
                  pass =false;
              }
          }
      }
      if(!pass) $("#html_idcard").html(tip);
      return pass;
  }
     
   