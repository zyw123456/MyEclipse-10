
$(function(){
	queryRptDetail();
});
function queryRptDetail(){ 
	var data = {fraudObjStr : JSON.stringify({
		rptid:rptid
		})}
	$.ajax({
		url: ctx+'/findFraudByrptid.action',
		type: 'post',
		async : true,
		dataType : "json",
		data: data,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		success: function(data,status) {
			if (data != "") {
				if (data != null) { 
					var res = data.data.res;
					if(res == normal){
						var creditrpt = data.data.creditrpt;
						$("#rptdte").html(creditrpt.rptdte);
						$("#rptid").html(creditrpt.rptid);
						$(".atvalue").html(creditrpt.prsnnam);
						$("#prsncod").html(creditrpt.prsncod);

						var rtpJson = data.data.RTPJSON;
						var modelJson = data.data.JSONARRY;
						//响应报文JSON	
						var rtpJsonPar = JSON.parse(rtpJson); 
						var jsonbody = rtpJsonPar.body;
						//模板JSON
						var modelJsonPar = modelJson;  
						var infoHtml = "";
						//遍历模板json字符串TRADE
						for(var i=0;i<modelJsonPar.length;i++){
							//第一级获取id，name，moudles值
							var trncod = modelJsonPar[i].id;
							var name   = modelJsonPar[i].name;
							var moudles   = modelJsonPar[i].moudles; 
							// infoHtml +=" <h6>"+ name+"<span class='know_c'></span></h6>"; 

							var isyes = 0;
							for(var mou=0;mou<moudles.length;mou++){
								//添加第一级表头
								var moudlefrom = moudles[mou]['from'];
								var trnInfo = jsonbody[""+trncod+""];
								if(isyes == 0){
									if(typeof(trnInfo) != "undefined"){
										isyes = isyes + 1;
										$("#infoHtml").append(" <h6>"+ name+"<span class='know_c'></span></h6>");
									}
								}
								//第二级获取到moudels下的子名称和type与items
								var mouid = moudles[mou]['id']; 
								var subname = moudles[mou]['name']; 

								var type = moudles[mou]['type'];
								var items = moudles[mou]['items'];
								var columnums = moudles[mou]['columnums'];
								var moudlefrom = moudles[mou]['from'];

								var trnInfo = jsonbody[""+trncod+""];

								//获取到响应报文list数据
								var array= getArrayJson(trnInfo,moudlefrom);
								if(typeof(array) != "undefined"){ 
									$("#infoHtml").append(" <h5>"+subname+"<span class='know_c'></span></h5> ");
								}


								//如果是list类型
								if(type == "1"){
									var taphtml = "";
									//判断是否配置from
									if(typeof(moudlefrom) == "undefined" || moudlefrom == ""){
										continue;
									}else{
										for(var ite=0;ite<items.length;ite++){
											var width  = items[ite]["width"];
											var widthHtml = "";
											if(typeof(width) == "undefined" || width == ""){
												widthHtml = "";
											}else{
												widthHtml = "width='"+width+"'";
											}
											var classc  = items[ite]["htmlclass"];
											var classHtml = "";
											if(typeof(width) == "undefined" || width == ""){
												classHtml = "";
											}else{
												classHtml = "class='"+classc+"'";
											}
											taphtml +="<td  class='tdd' "+widthHtml+" "+classHtml+">"+items[ite]["name"]+"</td>";
										}
										var buffHtml = "";

											var trnInfo = jsonbody[""+trncod+""];
											//获取到响应报文list数据
											var array= getArrayJson(trnInfo,moudlefrom);
											if(typeof(array) != "undefined"){
												var rownum = 0;
												var isrow  = 0;
												//计算出List为空的值有多少列数
												for(var arr=0;arr<array.length;arr++){
													buffHtml +="<tr>";
													for(var ite=0;ite<items.length;ite++){
														var itmefrom  = items[ite]["from"];
														if(typeof(itmefrom) == "undefined" || itmefrom==""){
															continue;
														}else{
															var itfromvalue = array[arr][""+itmefrom+""];
															if(itfromvalue == ""){
																rownum = rownum + 1;

															}else{
																isrow = 1;
															}
															// console.log(array[arr][""+itmefrom+""]);

														}
													}
												}
												//判断List值是否为空，并赋值到页面上，添加class、width
												var isone = 0;
												for(var arr=0;arr<array.length;arr++){
													buffHtml +="<tr>";
													for(var ite=0;ite<items.length;ite++){
														var itmefrom  = items[ite]["from"];
														var width  = items[ite]["width"];
														if(typeof(itmefrom) == "undefined" || itmefrom==""){
															continue;
														}else{

															var widthHtml = "";
															if(typeof(width) == "undefined"){
																widthHtml = "";
															}else{
																widthHtml = "width='"+width+"'";
															}

															var classc  = items[ite]["htmlclass"];
															var classHtml = "";
															if(typeof(width) == "undefined" || width == ""){
																classHtml = "";
															}else{
																classHtml = "class='"+classc+"'";
															} 
															var itfromvalue = array[arr][""+itmefrom+""];
															if(itfromvalue == ""){
																itfromvalue = "无";
															}
															// console.log(array[arr][""+itmefrom+""]);
															//判断此列是否为空，如果全部为空进行跨列（rownum）上提供列数
															if(isrow == 0){
																if(isone == 0){
																	isone = isone + 1;
																	buffHtml += "<td "+widthHtml+" "+classHtml+"  colspan="+rownum+" align='center'>"+itfromvalue+"</td>";
																}else{
																	buffHtml += "";
																}



															}else{
																buffHtml += "<td "+widthHtml+" "+classHtml+"  >"+itfromvalue+"</td>";
															}

														}
													}
													buffHtml +="</tr>";
													infoHtml +="<br/>";
												} 
											}
										if(buffHtml != ""){
											$("#infoHtml").append(" <form><table><tr>"+taphtml+"</tr>"+buffHtml+"</table> </form>");
										}
									}
								}

								//如果是对象类型
								if(type == "0"){
									infoHtml +="<table border='1px;'>";
									var tablehtml="";


									//获取到items下的属性
									if(typeof(moudlefrom) == "undefined" || moudlefrom == ""){

										//判断列数量是否为空
										if(typeof(columnums) == "undefined" || columnums == "" || columnums == "0"){
											for(var it=0;it<items.length;it++){

												var itemfrom = items[it]['from'];
												var itemname = items[it]['name'];
												var width  = items[it]["width"];
													var trnInfo = jsonbody[""+trncod+""];
													var array= getArrayJson(trnInfo,itemfrom);
													if(typeof(array) != "undefined"){ 
														infoHtml +=itemname+":"+array+"<br/>";

														var widthHtml = "";
														if(typeof(width) == "undefined" || width == ""){
															widthHtml = "";
														}else{
															widthHtml = "width='"+width+"'";
														}

														var classc  = items[it]["class"];
														var classHtml = "";
														if(typeof(width) == "undefined" || width == ""){
															classHtml = "";
														}else{
															classHtml = "class='"+classc+"'";
														}
														tablehtml +="<td "+widthHtml+" "+classHtml+">"+itemname+" </td><td "+classHtml+" "+widthHtml+">"+array+" </td>";
													} 
											}
										}else{
											var numcom = 0;
											for(var it=0;it<items.length;it++){
												var itemfrom = items[it]['from'];
												var itemname = items[it]['name'];
													var trnInfo = jsonbody[""+trncod+""];
													var array= getArrayJson(trnInfo,itemfrom);
													if(typeof(array) != "undefined"){ 
														var width  = items[it]["width"];
														var widthHtml = "";
														if(typeof(width) == "undefined" || width == ""){
															widthHtml = "";
														}else{
															widthHtml = "width='"+width+"'";
														}

														var classc  = items[it]["class"];
														var classHtml = "";
														if(typeof(width) == "undefined" || width == ""){
															classHtml = "";
														}else{
															classHtml = "class='"+classc+"'";
														}
														if(array == ""){
															array = "无";
														}
														infoHtml +=itemname+":"+array+"<br/>";
													}
													tablehtml +="<td "+widthHtml+" "+classHtml+">"+itemname+" </td><td "+classHtml+">"+array+" </td>";
												numcom = numcom + 1;
												if(numcom == columnums){   
													numcom = 0;
													tablehtml ='<tr>'+tablehtml+'</tr>';// alert(tablehtml)
												}
											}
										}


									}else{
										//判断列数量是否为空
										if(typeof(columnums) == "undefined" || columnums == "" || columnums == "0"){
												//获取到每个原交易下所有的值
												var trnInfo = jsonbody[""+trncod+""];
												var array= getArrayJson(trnInfo,moudlefrom);
												if(typeof(array) != "undefined"){
													for(var it=0;it<items.length;it++){
														var itemfrom = items[it]['from'];
														var itemname = items[it]['name'];
														var width  = items[it]["width"];
														var widthHtml = "";
														if(typeof(width) == "undefined" || width == ""){
															widthHtml = "";
														}else{
															widthHtml = "width='"+width+"'";
														}

														var classc  = items[it]["class"];
														var classHtml = "";
														if(typeof(width) == "undefined" || width == ""){
															classHtml = "";
														}else{
															classHtml = "class='"+classc+"'";
														}
														var itfrom = array[""+itemfrom+""];
														if(itfrom == ""){
															itfrom = "无";
														}
														tablehtml +='<td '+widthHtml+' '+classHtml+'>'+itemname+' </td><td '+widthHtml+' '+classHtml+'>'+itfrom+' </td>';
													}
												}
										}else{

												var numcom = 0;
												//获取到每个原交易下所有的值
												var trnInfo = jsonbody[""+trncod+""];
												var array= getArrayJson(trnInfo,moudlefrom);
												if(typeof(array) != "undefined"){
													for(var it=0;it<items.length;it++){
														var itemfrom = items[it]['from'];
														var itemname = items[it]['name'];
														var width  = items[it]["width"];
														var widthHtml = "";
														if(typeof(width) == "undefined" || width == ""){
															widthHtml = "";
														}else{
															widthHtml = "width='"+width+"'";
														}

														var classc  = items[it]["class"];
														var classHtml = "";
														if(typeof(width) == "undefined" || width == ""){
															classHtml = "";
														}else{
															classHtml = "class='"+classc+"'";
														}
														var itfrom = array[""+itemfrom+""];
														if(itfrom == ""){
															itfrom = "无";
														}
														tablehtml +='<td '+widthHtml+' '+classHtml+'>'+itemname+' </td><td '+widthHtml+' '+classHtml+'>'+itfrom+' </td>';
														numcom = numcom + 1;
														if(numcom == columnums){   
															numcom = 0;
															tablehtml ='<tr>'+tablehtml+'</tr>';// alert(tablehtml)
														}
													}
												}
										}
									}
									$("#infoHtml").append("  <form>	<table><tr>"+tablehtml+"	</tr> </form>	</table>");
								}
							}
							infoHtml +="<br/>";
						}
						$("#infoHtml").append("<h6>"+
								"报告说明<span class='know_c'></span>"+
								"</h6>"+
								"<p class='know_b'>"+
								"1.本报告由北京华道征信有限公司（以下简称\"华道征信\"）出具，依据公布时间华道数据库中的相关数据记录生成。除个人查询纪录外，其他信息均来自您所授权的相关机构，数据的真实性和准确性以相关机构的报送纪录为依据。如您对报告中的任何信息存有异议，可向华道征信提出异议申请，联系电话400-003-4020，电子邮件<span"+
								"style='color: #06F; text-decoration: underline'>service@sinowaycredit.com</span>。华道征信在核实您的身份后，按华道征信的《信息主体异议管理办法》在20日内对您的异议进行答复。"+
								"</p>"+
								"<p class='know_b'>2.华道征信承诺在信息汇总、加工、整理的全过程中保持客观、中立的立场。本报告仅供您和取得您授权的机构参考，华道征信不参与任何与报告相关的决策过程，对任何机构针对本报告做出的任何决策结果不承担责任。</p>"+
								"<p class='know_b'>3.本报告不展示5年前的人不良信息。超过5年的个人不良信息华道征信将会从数据库中予以删除。</p>"+
								"<p class='know_b'>4.请您妥善查看、使用、处理和保管此报告。因您自身原因造成的个人信息泄露，以及由此给您带来的损失及不良后果，华道征信将不承担任何责任。</p>"+
						"</div>");
					
					}else{
						alert(res);
					}
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("加载信息erro:" + textStatus + errorThrown);
		},
		complete : function(XMLHttpRequest, textStatus) {
		}
	});

}


function getArrayJson( x,path){
	if(x == null || x == '')
		return;
	var strs = path.split('\|');

	var temp = x;
	if(strs.length > 0){

		if(strs.length == 1){
			return x[strs[0]];
		}
		for(var i = 0; i < strs.length;i++){
			temp = temp[strs[i]];
			if(i == strs.length - 1){
				return temp;
			}
			if(isType(temp,"jsonobject")){
				continue;
			}else{
				return;
			}
		}
	}
}

function isType(json,type){
	if(type == 'jsonobject'){
		if( typeof json == 'object' && typeof json.length == 'undefined'){
			return true;
		}
		return false;
	}else if(type == 'jsonarray'){

		if( typeof json == 'object' && typeof json.length != 'undefined'){
			return true;
		}
		return false;
	}else if(type == 'other'){
		if(typeof json != 'object'){
			return true;
		}
		return false;
	}


}
