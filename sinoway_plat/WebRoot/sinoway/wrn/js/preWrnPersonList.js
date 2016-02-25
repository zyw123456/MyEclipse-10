function sheng(){
	url = "queryWarnWaitingPeople.action";
	order="up";
	data={order:order};
	show();
}   
function jiang(){
	url = "queryWarnWaitingPeople.action";
	order="down";
	data={order:order};
	show();
}
$(function(){
	    order="";
	    loantyp="0";
    	data = {order:order,loantyp:loantyp};
    	url = "queryWarnWaitingPeople.action";
    	show();
	});
function sel(){
	url = "queryWarnWaitingPeople.action";
	loantyp=$("select option:selected").val();
	data={loantyp:loantyp};
	show();
}
function show(){
	objArray = new Array();
	$.ajax({
		url : url,
		type : "post",
		data : data,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		success : function(data, textStatus) {
				if (data !=null) {
					if(data == 0){
						alert("操作失败!");
					}else{
						var tbody = "";
						var frontObj = JSON.parse(data);
						if(frontObj != null){
							for(var i=0;i<frontObj.length;i++){
								objArray[i] = frontObj[i];
								tbody += "<tr class='tr1' align='center' height='40'><td>"+frontObj[i].prsnnam+ "</td><td>"+frontObj[i].prsncod+"</td><td>"+
								frontObj[i].section+"</td><td>"+frontObj[i].loantyp+"</td><td>" + frontObj[i].module + "</td><td>";
								if(frontObj[i].sta == '0'){
									tbody += "<a href='javascript:supplement("+i +")'>补充信息</a></td>";
								}else{
									tbody += "<a href='javascript:startMonitor("+i +")'>开始监控</a></td>";
								}
							}
							$("tr").remove(".tr1");
							$("#tb1").append(tbody);
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
function hideDiv(){
	$(".popupcontent").css("visibility","hidden");
	$(".statusbar").css("visibility","hidden");
	$("#monitorModule").css("visibility","hidden");
}
function supplement(n){
	$("#suppleDiv").css("visibility","visible");
	$("#suppleClose").css("visibility","visible");
	$("#prsnnam").val(objArray[n].prsnnam);
	$("#prsncod").val(objArray[n].prsncod);
	$("#telno").val(objArray[n].telno);
	$("#repaydte").val(objArray[n].repaydte);
	$("#repayamt").val(objArray[n].repayamt);
	if(objArray[n].loantyp =='消费类贷款'){
		$("option[id=buy]").attr("selected",true);
	}else if(objArray[n].loantyp =='购房贷款'){
		$("option[id=house]").attr("selected",true);
	}else if(objArray[n].loantyp =='汽车贷款'){
		$("option[id=car]").attr("selected",true);
	}
	if(objArray[n].repaytyp =='month'){
		$("option[value=month]").attr("selected",true);
	}else if(objArray[n].repaytyp =='quarter'){
		$("option[value=quarter]").attr("selected",true);
	}else if(objArray[n].repaytyp =='year'){
		$("option[value=year]").attr("selected",true);
	}
	$("#loanamt").val(objArray[n].loanamt);
	$("#loanlmt").val(objArray[n].loanlmt);
	$("#loansrtdte").val(objArray[n].loansrtdte);
	$("#loanenddte").val(objArray[n].loanenddte);
	$("#warnid").val(objArray[n].id);
}
function suppleSave(){
	if($("select[id=loantyp] option:selected").val()=='0'){
		alert("请选择贷款类型!");
	}else if($("select[id=repaytyp] option:selected").val()=='0'){
		alert("请选择还款方式!");
	}else{		
		$("#suppleDiv").css("visibility","hidden");
		$("#suppleClose").css("visibility","hidden");
		var frontObjStr = JSON.stringify({prsnnam:$.trim($("#prsnnam").val()),prsncod:$.trim($("#prsncod").val()),loantyp:$("select[id=loantyp] option:selected").val(),
			loanamt:$.trim($("#loanamt").val()),loanlmt:$.trim($("#loanlmt").val()),loansrtdte:$.trim($("#loansrtdte").val()),loanenddte:$.trim($("#loanenddte").val()),
			id:$("#warnid").val(),repaydte:$.trim($("#repaydte").val()),repayamt:$.trim($("#repayamt").val()),telno:$.trim($("#telno").val()),repaytyp:$("select[id=repaytyp] option:selected").val()});
		data={frontObjStr:frontObjStr,order:order,loantyp:loantyp};
		url="suppleWarnWaitingPeople.action";
		show();
	}
}
function startMonitor(n){
	$("#startDiv").css("visibility","visible");
	$("#startClose").css("visibility","visible");
	$("tr").remove(".startTr");
	$("#myTb").append("<tr class='startTr' align='center'><td>"+objArray[n].prsnnam+"</td><td>"+objArray[n].prsncod+"</td><td>"+objArray[n].section+"</td><td>"+objArray[n].loantyp
			+"</td><td id='moduleTd'>"+objArray[n].module+"<input type='button' value='修改' onclick='showPrds("+n+")'></td></tr>");
	$("#warnid").val(objArray[n].id);
	
}
function confirmMonitor(){
	$("#startDiv").css("visibility","hidden");
	$("#startClose").css("visibility","hidden");
	data={frontObjStr:JSON.stringify({id:$("#warnid").val()}),order:order,loantyp:loantyp};
	url="startMonitor.action";
	show();
}
function showPrds(n){
	$("#monitorModule").css("visibility","visible");
	data={frontObjStr:JSON.stringify({peoplecode:objArray[n].peoplecode})};
	url="queryWarnPrds.action";
	$.ajax({
		url : url,
		type : "post",
		data : data,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		success : function(data, textStatus) {
				if (data !=null) {
					var frontObj = JSON.parse(data);
				    if(frontObj != null){
				    	var pbody = "";
				    	for(var i=0;i<frontObj.length;i++){
				    		if(frontObj[i].isdefult == '1'){
				    			if(frontObj[i].prdcod == objArray[n].prdcod){
				    				pbody += frontObj[i].prdnam+"<input type='radio' name='wrnprd' checked value='"+frontObj[i].prdcod+"'>&nbsp;";
				    				pbody += "<input type='hidden' id='"+frontObj[i].prdcod+"' value='"+frontObj[i].prdnam+"'>";
				    			}else{
				    				pbody += frontObj[i].prdnam+"<input type='radio' name='wrnprd' value='"+frontObj[i].prdcod+"'>&nbsp;";
				    				pbody += "<input type='hidden' id='"+frontObj[i].prdcod+"' value='"+frontObj[i].prdnam+"'>";
				    			}
				    		}
				    	}
				    	for(var i=0;i<frontObj.length;i++){
				    		if(frontObj[i].isdefult != '1'){
				    			if(frontObj[i].prdcod == objArray[n].prdcod){
				    				pbody += frontObj[i].prdnam+"<input type='radio' name='wrnprd' checked value='"+frontObj[i].prdcod+"'>&nbsp;";
				    				pbody += "<input type='hidden' id='"+frontObj[i].prdcod+"' value='"+frontObj[i].prdnam+"'>";
				    			}else{
				    				pbody += frontObj[i].prdnam+"<input type='radio' name='wrnprd' value='"+frontObj[i].prdcod+"'>&nbsp;";
				    				pbody += "<input type='hidden' id='"+frontObj[i].prdcod+"' value='"+frontObj[i].prdnam+"'>";
				    			}
				    		}
				    	}
				    	pbody +="<input type='button' value='保存' onclick='changePrd("+n+")'>";
				    	$("#moniMod").html(pbody);
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
function changePrd(n){
	$("#monitorModule").css("visibility","hidden");
	var id = $("input:radio:checked").val();
	data={frontObjStr:JSON.stringify({id:objArray[n].id,prdcod:$("input:radio:checked").val(),prdnam:$("#"+id).val()})};
	url="changeWarnPrds.action";
	$.ajax({
		url : url,
		type : "post",
		data : data,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		success : function(data, textStatus) {
				if (data !=null) {
					if(data == 0){
						alert("操作失败!");
					}else{
						alert(data);
						$("#moduleTd").html($("#"+id).val()+"<input type='button' value='修改' onclick='showPrds("+n+")'>");
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