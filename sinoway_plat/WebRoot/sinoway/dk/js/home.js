
function init() {
	
	$.ajax({
		url : ctx + "/getPlatAlarmNo.action",
		type : "post",
		data : '',
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : false,
		success : function(data, textStatus) {
			if (data != "") {
				var frontObj = JSON.parse(data);
				if(frontObj != null){
					if(null != frontObj.errorCode){
						alert(frontObj.errorCode);
					}else{
					
						$("#platMonitor").html(frontObj.frontMap["platMonitor"]);
						$("#platAlarm").html(frontObj.frontMap["platAlarm"]);
					
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
	

	$.ajax({
		url : ctx + "/getAccAlarmNo.action",
		type : "post",
		data : null,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async :false,
		success : function(data, textStatus) {
			if (data != "") {
				var frontObj = JSON.parse(data);
				if(frontObj != null){
					if(null != frontObj.errorCode){
						alert(frontObj.errorCode);
					}else{
						$("#accMonitor").html(frontObj.frontMap["accMonitor"]);
						$("#accAlarm").html(frontObj.frontMap["accAlarm"]);
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
	$.ajax({
		url : ctx + "/getTopAccRpt.action",
		type : "post",
		data :null,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : false,
		success : function(data, textStatus) {
			if (data != "") {
				var frontObj = JSON.parse(data);
				if(frontObj != null){
					if(null != frontObj.errorCode){
						alert(frontObj.errorCode);
					}else{
						if(null != frontObj.rptlist){
							var tbody = "";
							for(var i=0;i<frontObj.rptlist.length;i++){
								tbody += "<tr class='myTr2'><td>"+frontObj.rptlist[i].rptmodtim+"</td><td>"+frontObj.rptlist[i].rptid+"</td><td>"+frontObj.rptlist[i].prsnnam+"</td><td>"
								+frontObj.rptlist[i].prsncod+"</td><td>"+frontObj.rptlist[i].rpttyp+"</td></tr>";
							}
							for(var i=0;i<10-frontObj.rptlist.length;i++){
								tbody +="<tr class='myTr2'><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
							}
							$("tr").remove(".myTr2");
							$("#mytable2").append(tbody);
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
	
	
	$.ajax({
		url : ctx + "/getTopPlatAlarmDetail.action",
		type : "post",
		data : null,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : false,
		success : function(data, textStatus) {
			if (data != "") {
				var frontObj = JSON.parse(data);
				if(frontObj != null){
					if(null != frontObj.errorCode){
						alert(frontObj.errorCode);
					}else{
						if(null != frontObj.warnlist){
							var tbody = "";
							for(var i=0;i<frontObj.warnlist.length;i++){
								tbody += "<tr class='myTr'><td>"+frontObj.warnlist[i].warnTim+"</br><span>"+frontObj.warnlist[i].trnNam+"  信息可靠度"+frontObj.warnlist[i].reality+"</span></td></tr>";
							}
							for(var i=0;i<6-frontObj.warnlist.length;i++){
								tbody +="<tr class='myTr'><td><br><span>&nbsp;</span></td></tr>";
							}
						}else{
							for(var i=0;i<6;i++){
								tbody +="<tr class='myTr'><td><br><span>&nbsp;</span></td></tr>";
							}
						}
						$("tr").remove(".myTr");
						$("#mytable").append(tbody);
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

$(document).ready(function() {
	$("#mainlist",window.parent.parent.document).attr("rows","200px,*,100px");
	init();
	contFlow();
	
});
/**
 * 首页底部的流量统计
 */
function contFlow(){
	$.ajax({
		url : "hpCache_homePageFlowStatistics.action",
		type : "post",
		data:"",
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : false,
		success : function(data, textStatus) {
			if (data != null) {
				var obj = JSON.parse(data);
				var todayFlow = obj.data.todayFlow;
				 window.parent.bottom.$("#todayFlow").html(todayFlow);
				 window.parent.bottom.$("#yesterdayData").html(obj.data.yesterdayData);
				 window.parent.bottom.$("#berfareYesterdayData").html(obj.data.berfareYesterdayData);
				 window.parent.bottom.$("#threeDaysAgoData").html(obj.data.threeDaysAgoData);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("加载信息erro:" + textStatus + errorThrown);
		},
		complete : function(XMLHttpRequest, textStatus) {
		}
	});
}