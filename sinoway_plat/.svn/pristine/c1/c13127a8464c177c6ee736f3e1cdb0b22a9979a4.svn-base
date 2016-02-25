//@ sourceURL=home.js
/**
 * Created by dell on 2016/1/25.
 */
$('[data-pie-chart--large]').easyPieChart({
    animate: 1000,
    barColor: '#38a0b9',
    lineWidth: 20,
    scaleLength: 0,
    size: 210,
    trackColor: '#fff',
    //shadowOffsetX:'5',
    //shadowOffsetY:'5',
    //shadowBlur:'5',
    //shadowColor:'rgb(0,0,0,.5)'
});
$('[data-pie-chart--small]').easyPieChart({
    animate: 1000,
    barColor: '#38a0b9',
    lineWidth: 20,
    scaleLength: 0,
    size: 210,
    trackColor: '#fff'
});
/**
 * 初始化方法，加载平台统计数据
 */
function initData(){
	$.ajax({
		url : ctx + "/getInitData.action",
		type : "post",
		data : null,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		dataType:"json",
		success : function(data, textStatus) {
			if (data != "") {
				var frontObj = JSON.parse(data);
				if(frontObj != null){
					if(null != frontObj.errorCode){
						alert(frontObj.errorCode);
					}else{
						$("#platMonitor").html(frontObj.frontMap["platMonitor"]);
						$("#platAlarm").html(frontObj.frontMap["platAlarm"]);
						if(0 != frontObj.frontMap["platMonitor"]){
							var platPercent = accDiv(frontObj.frontMap["platAlarm"],frontObj.frontMap["platMonitor"])*100;
							showPlatChart(platPercent);
						}
						$("#accMonitor").html(frontObj.frontMap["accMonitor"]);
						$("#accAlarm").html(frontObj.frontMap["accAlarm"]);
						if(0 != frontObj.frontMap["accMonitor"]){
							var accPercent = accDiv(frontObj.frontMap["accAlarm"],frontObj.frontMap["accMonitor"])*100;
							showAccChart(accPercent);
						}
						//showPlatChart(50);
						//var tbody = "<tr class='tabthead'><td>报告时间</td><td>报告号</td><td>姓名</td><td>身份证号</td><td>报告类型</td></tr>";
						tbody="";
						if(null != frontObj.rptlist){
							for(var i=0;i<frontObj.rptlist.length;i++){
								tbody += "<tr class='myTr2'><td>"+frontObj.rptlist[i].rptmodtim+"</td><td>"+frontObj.rptlist[i].rptid+"</td><td>"+frontObj.rptlist[i].prsnnam+"</td><td>"
								+frontObj.rptlist[i].prsncod+"</td><td>"+frontObj.rptlist[i].rpttyp+"</td></tr>";
							}
							for(var i=0;i<10-frontObj.rptlist.length;i++){
								tbody +="<tr class='myTr2'><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
							}
							
						}
						$("tr").remove(".myTr2");
						//$("#mytable2 tbody").html(tbody);
						$("#mytable2").append(tbody);
						tbody = "";
						if(null != frontObj.warnlist){
							for(var i=0;i<frontObj.warnlist.length;i++){
								tbody += "<tr class='myTr'><td>"+frontObj.warnlist[i].warnTim+"</br><span>"+frontObj.warnlist[i].trnNam+"  信息可靠度"+frontObj.warnlist[i].reality+"</span></td></tr>";
							}
							for(var i=0;i<6-frontObj.warnlist.length;i++){
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
/**
 * 初始化方法，加载平台监视统计数据
 */
function initPlatAlarmNo() {
	
	$.ajax({
		url : ctx + "/getPlatAlarmNo.action",
		type : "post",
		data : null,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		dataType:"json",
		success : function(data, textStatus) {
			if (data != "") {
				var frontObj = JSON.parse(data);
				if(frontObj != null){
					if(null != frontObj.errorCode){
						alert(frontObj.errorCode);
					}else{
						if(null !=frontObj.frontMap){
							$("#platMonitor").html(frontObj.frontMap["platMonitor"]);
							$("#platAlarm").html(frontObj.frontMap["platAlarm"]);
							if("0" != frontObj.frontMap["platMonitor"] && null != frontObj.frontMap["platMonitor"]){
								var platPercent = accDiv(frontObj.frontMap["platAlarm"],frontObj.frontMap["platMonitor"])*100;
								showPlatChart(platPercent);
							}
							//showPlatChart(50);
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
/**
 * 初始化方法，加载账号监视统计数据
 */
function initAccAlarmNo(){
	$.ajax({
		url : ctx + "/getAccAlarmNo.action",
		type : "post",
		data : null,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async :true,
		dataType:"json",
		success : function(data, textStatus) {
			if (data != "") {
				var frontObj = JSON.parse(data);
				if(frontObj != null){
					if(null != frontObj.errorCode){
						alert(frontObj.errorCode);
					}else{
						if(null !=frontObj.frontMap){
							$("#accMonitor").html(frontObj.frontMap["accMonitor"]);
							$("#accAlarm").html(frontObj.frontMap["accAlarm"]);
							if("0" != frontObj.frontMap["accMonitor"] && null != frontObj.frontMap["accMonitor"] ){
								var accPercent = accDiv(frontObj.frontMap["accAlarm"],frontObj.frontMap["accMonitor"])*100;
								showAccChart(accPercent);
							}
							//showAccChart(40);
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
/**
 * 初始化方法，加载平台实时报告信息
 */
function initTopAccRpt(){
	$.ajax({
		url : ctx + "/getTopAccRpt.action",
		type : "post",
		data : null,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		dataType:"json",
		success : function(data, textStatus) {
			if (data != "") {
				var frontObj = JSON.parse(data);
				if(frontObj != null){
					if(null != frontObj.errorCode){
						alert(frontObj.errorCode);
					}else{
						var tbody = "";
						if(null != frontObj.rptlist){
							for(var i=0;i<frontObj.rptlist.length;i++){
								tbody += "<tr class='myTr2'><td>"+frontObj.rptlist[i].rptmodtim+"</td><td>"+frontObj.rptlist[i].rptid+"</td><td>"+frontObj.rptlist[i].prsnnam+"</td><td>"
								+frontObj.rptlist[i].prsncod+"</td><td>"+frontObj.rptlist[i].rpttyp+"</td></tr>";
							}
							for(var i=0;i<10-frontObj.rptlist.length;i++){
								tbody +="<tr class='myTr2'><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
							}
						}
						/*$("tr").remove(".myTr2");
						$("#mytable2").append(tbody);*/
						$("#rptDiv").html(tbody);
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
/**
 * 初始化方法，加载平台实时预警信息
 */
function initTopPlatAlarmDetail(){
	$.ajax({
		url : ctx + "/getTopPlatAlarmDetail.action",
		type : "post",
		data : null,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		dataType:"json",
		success : function(data, textStatus) {
			if (data != "") {
				var frontObj = JSON.parse(data);
				if(frontObj != null){
					if(null != frontObj.errorCode){
						alert(frontObj.errorCode);
					}else{
						var tbody = "";
						if(null != frontObj.warnlist){
							for(var i=0;i<frontObj.warnlist.length;i++){
								tbody += "<tr class='myTr'><td>"+frontObj.warnlist[i].warnTim+"</br><span>"+frontObj.warnlist[i].trnNam+"  信息可靠度"+frontObj.warnlist[i].reality+"</span></td></tr>";
							}
							for(var i=0;i<6-frontObj.warnlist.length;i++){
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
/**
 * 除法函数,用来得到精确的除法结果 
 * @param arg1,arg2 被除数和除数
 * @returns arg1除以arg2的精确结果
 */
function accDiv(arg1,arg2) {
	with (Math) {
		r1 = Number(arg1 + ".00");
		r2 = Number(arg2+ ".00");
		return r1 / r2;
	}
}
/**
 * 页面初始化
 */
$(document).ready(function() {
	/*initAccAlarmNo();
	initPlatAlarmNo();
	initTopAccRpt();
	initTopPlatAlarmDetail();*/
	initData();
	//setInterval("init()",1000*5);
});