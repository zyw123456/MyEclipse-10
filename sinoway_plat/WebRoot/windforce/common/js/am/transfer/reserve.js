$(function() {
	$("#saveBtn").on("click", function() {
		save();
	});
	$("#resetBtn").on("click", function() {
		reset();
	});
	$("#startDate").datepicker({
		dateFormat : "yy-mm-dd",
		changeMonth : true,
		changeYear : true,
		minDate:new Date(),
		closeText: '关闭',  
        prevText: '上一月',  
        nextText: '下一月',  
        currentText: '今天',  
        monthNames: ['一月','二月','三月','四月','五月','六月',  
        '七月','八月','九月','十月','十一月','十二月'],  
        monthNamesShort: ['一月','二月','三月','四月','五月','六月',  
        '七月','八月','九月','十月','十一月','十二月'],  
        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
        dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
        dayNamesMin: ['日','一','二','三','四','五','六'],  
        weekHeader: '周',    
        firstDay: 1,  
        isRTL: false,  
        showMonthAfterYear: true,  
        yearSuffix: ''
	});
	$("#endDate").datepicker({
		dateFormat : "yy-mm-dd",
		changeMonth : true,
		changeYear : true,
		minDate:new Date(),
		closeText: '关闭',  
        prevText: '上一月',  
        nextText: '下一月',  
        currentText: '今天',  
        monthNames: ['一月','二月','三月','四月','五月','六月',  
        '七月','八月','九月','十月','十一月','十二月'],  
        monthNamesShort: ['一月','二月','三月','四月','五月','六月',  
        '七月','八月','九月','十月','十一月','十二月'],  
        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
        dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
        dayNamesMin: ['日','一','二','三','四','五','六'],  
        weekHeader: '周',   
        firstDay: 1,  
        isRTL: false,  
        showMonthAfterYear: true,  
        yearSuffix: ''
	});
});
// 保存预约信息
function save() {
	var applicationFormNo = $("#applicationFormNo").val();
	var startDate = $("#startDate").val();
	if (null == startDate || "" == startDate) {
		showMsg("开始日期不能为空。");
		return;
	}
	var startTime = $("#startTimeHour").val() + ":"
			+ $("#startTimeMinute").val() + ":00";
	var endDate = $("#endDate").val();
	if (null == endDate || "" == endDate) {
		showMsg("结束日期不能为空。");
		return;
	}
	var endTime = $("#endTimeHour").val() + ":" + $("#endTimeMinute").val()
			+ ":00";
	var revicePeopleNo = $("#revicePeopleNo").val();
	if (null == revicePeopleNo || "" == revicePeopleNo) {
		showMsg("接收柜员号不能为空。");
		return;
	}
	var revicePeoplePassword = $("#revicePeoplePassword").val();
	if (null == revicePeoplePassword || "" == revicePeoplePassword) {
		showMsg("接收柜员密码不能为空。");
		return;
	}
	var supervisePeopleNo = null;
	var supervisePeoplePassword = null;
	if(enableSupervisePeople=="true"){
		supervisePeopleNo = $("#supervisePeopleNo").val();
		if (null == supervisePeopleNo || "" == supervisePeopleNo) {
			showMsg("监交柜员号不能为空。");
			return;
		}
		supervisePeoplePassword = $("#supervisePeoplePassword").val();
		if (null == supervisePeoplePassword || "" == supervisePeoplePassword) {
			showMsg("监交柜员密码不能为空。");
			return;
		}
	}
	var param = {
		"transferPowerInfo.applicationFormNo" : applicationFormNo,
		"transferPowerInfo.startDate" : startDate,
		"transferPowerInfo.startTime" : startTime,
		"transferPowerInfo.endDate" : endDate,
		"transferPowerInfo.endTime" : endTime,
		"transferPowerInfo.revicePeopleNo" : revicePeopleNo,
		"transferPowerInfo.revicePeoplePwd" : revicePeoplePassword,
		"transferPowerInfo.supervisePeopleNo" : supervisePeopleNo,
		"transferPowerInfo.supervisePeoplePwd" : supervisePeoplePassword
	};
	$.post(ctx + "/transferPowerInfoAction_reserve.action", param, function(
			data) {
		var obj = $.evalJSON(data);
		showMsg(obj.data.msg);
		if (obj.data.ok == true) {
			refresh();
		}
		return;
	});
}
// 重置
function reset() {
	$("#startDate").val("");
	$("#startTimeHour").val("08");
	$("#startTimeMinute").val("30");
	$("#endDate").val("");
	$("#endTimeHour").val("08");
	$("#endTimeMinute").val("30");
	if(document.getElementById("revicePeopleNo").options.length>0){
		document.getElementById("revicePeopleNo").selectedIndex = 0;
	} else {
		$("#revicePeopleNo").val("");
	} 
	$("#revicePeoplePassword").val("");
	if(document.getElementById("supervisePeopleNo").options.length>0){
		document.getElementById("supervisePeopleNo").selectedIndex = 0;
	} else {
		$("#supervisePeopleNo").val("");
	} 
	$("#supervisePeoplePassword").val("");
}
// 显示信息
function showMsg(msg) {
	$("#msg").html(msg);
}
// 刷新主界面数据
function refresh() {
	// 此刷行方法在IE和Chrome通用
	document.getElementById("workFrame").src = ctx
			+ "/transferPowerInfoAction_showListPage.action";
}