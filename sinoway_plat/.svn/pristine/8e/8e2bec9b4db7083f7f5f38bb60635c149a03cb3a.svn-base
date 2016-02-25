$(function() {
	$("#searchBtn").on("click", function() {
		search();
	});
	$("#resetBtn").on("click", function() {
		reset();
	});
	$("#reserveBtn").on("click", function() {
		showReservePage();
	});
	$("#cancelBtn").on("click", function() {
		cannel();
	});
	$("#takeBackBtn").on("click", function() {
		takeBack();
	});
	$("#search_startDate").datepicker({
		dateFormat : "yy-mm-dd",
		changeMonth : true,
		changeYear : true,
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
	$("#search_endDate").datepicker({
		dateFormat : "yy-mm-dd",
		changeMonth : true,
		changeYear : true,
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
	//$("#ui-datepicker-div").css('font-size', '1em');// 改变大小
});
// 查询
function search(event) {
	var startDate = $("#search_startDate").val();
	var endDate = $("#search_endDate").val();
	if (null != startDate && "" != startDate && null != endDate
			&& "" != endDate && startDate > endDate) {
		top.wfAlert("查询条件的开始时间不能大于结束时间");
		return;
	}
	$("#searchForm").submit();
}
// 重置
function reset() {
	$("#search_startDate").val("");
	$("#search_endDate").val("");
}
// 显示预约界面
function showReservePage() {
	if(enableSupervisePeople=="true"){
		top.showPage(ctx + "/transferPowerInfoAction_showReservePage.action", null,
				"预约", 420, 308);
	} else {
		top.showPage(ctx + "/transferPowerInfoAction_showReservePage.action", null,
				"预约", 420, 258);
	}
	
}
// 取消
function cannel() {
	var checkBoxs = $("input:checkbox[class=selectCheckBox]:checked");
	var length = checkBoxs.length;
	if (length != 1) {
		top.wfAlert("请选择一个进行操作。");
		return;
	}
	if (confirm("确定要撤销吗？")) {
		checkBoxs.each(function(i) {
			var id = $(this).val();
			var url = ctx + "/transferPowerInfoAction_cancel.action";
			var param = {
				"transferPowerInfo.id" : id
			};
			$.post(url, param, function(data) {
				var obj = $.evalJSON(data);
				top.wfAlert(obj.data.msg);
				refresh();
				return;
			});
		});
	}
}
// 提前收回
function takeBack() {
	var checkBoxs = $("input:checkbox[class=selectCheckBox]:checked");
	var length = checkBoxs.length;
	if (length != 1) {
		top.wfAlert("请选择一个进行操作。");
		return;
	}
	if (confirm("确定要提前收回吗？")) {
		checkBoxs.each(function(i) {
			var id = $(this).val();
			var url = ctx + "/transferPowerInfoAction_takeBack.action";
			var param = {
				"transferPowerInfo.id" : id
			};
			$.post(url, param, function(data) {
				var obj = $.evalJSON(data);
				top.wfAlert(obj.data.msg);
				refresh();
				return;
			});
		});
	}
}
// 刷新主界面数据
function refresh() {
	window.location = ctx + "/transferPowerInfoAction_showListPage.action";
}