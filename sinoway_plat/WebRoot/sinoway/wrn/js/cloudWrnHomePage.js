$(function(){
	findAllWfDatCerditWarndtel();
});
var controlTime ="";
var dataRealty="";
var flag =0;
function findAllWfDatCerditWarndtel(){
	 

//	if(controlTime==""){
//		controlTime =0;
//	}
//	if(dataRealty==""){
//		dataRealty=0;
//	}
	if(flag==0){
		if(controlTime==""){
			controlTime=2;
		}
		dataRealty="";
	}
	if(flag==1){
		controlTime ="";
	}
	var warnDataType =$("#dataType").val();
	//alert(warnDataType);
	var dataUrl =$("#dataUrl").val();
	//var dataRealty
	if(warnDataType=="预警数据类别"){
		warnDataType="";
	}
	if(dataUrl=="数据来源"){
		dataUrl="";
	}
	//alert("ok!!!!!!!!!!");
	$.ajax({
		url : "/windforce/findAllWfDatCerditWarndtel.action",
		type : "post",
		data :{controlTime:controlTime,warnDataType:warnDataType,dataUrl:dataUrl,dataRealty:dataRealty},
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		success : function(data, textStatus) {
			//alert("ok");
				if (data != null) {
					var obj = JSON.parse(data);
					//alert(obj);
					var tbBody = "";
					if(obj != null){
						$("#listTable tbody").html("");
						for(var i=0;i<obj.length;i++){
							//alert(obj[i].WARNTIM);
							//tbBoyd +="<tr><td width='200px'>"+obj[i].WARNTIM+"</td></tr>"
							tbBody += "<tr align='center'><td width='300px'>"+obj[i].WARNDTE+"  "+ obj[i].WARNTIM+ "</td>"+"<td width='50px'>" + obj[i].PRSNNAM+ "</td>"+"<td width='250px'>" + obj[i].CREDTNO+ "</td>"+"<td width='200px' align='left'>" + obj[i].TRNCOD+ "</td>"+"</td>"+"<td width='100px' align='left'>" + obj[i].DATORI+ "</td>"+"<td width='100px'>" + obj[i].REALITY+ "</td>"+"</tr>";
						}
					// $("tr").remove(".peoTr");

						$("#listTable").append(tbBody);
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
function findWfDatCerditByDataType(){
	findAllWfDatCerditWarndtel();
}
/*function ascendingSort(){
	controlTime = $("#sort1").val();
	findAllWfDatCerditWarndtel();
}
function descendingSort(){
	controlTime = $("#sort2").val();
	findAllWfDatCerditWarndtel();
}*/
function monitoringSort(str){
//	if(str==1){
//	//	controlTime ="升序";
//	}
//	if(str==2){
//		controlTime="降序";
//	}
	flag = 0;
	controlTime = str;
	findAllWfDatCerditWarndtel();
}
function reliabilitySort(str){
//	if(str==1){
//		dataRealty ="低到高";
//	}
//	if(str==2){
//		dataRealty="高到低";
//	}
	flag=1;
	dataRealty = str;
	findAllWfDatCerditWarndtel();
}

