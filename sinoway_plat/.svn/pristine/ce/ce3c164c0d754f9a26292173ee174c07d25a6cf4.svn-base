var appcod = "001";//报告策略
var initTableId = "rptTab";
var tableParam = [{
		tableId:'rptInfoTable',//表名
		modulNature:'001'//模块对应的交易特性
}];

$(document).ready(function() {
	initTable(initTableId,appcod);
	setInterval("changeHeight()",200);
});

function changeHeight(){
	var maxHeight = $("#poTable").height()>$("#rptinfoTable").height()?$("#poTable").height():$("#rptinfoTable").height();
	if($("div[class=disArea]").is(":visible")||$("div[class=po_disArea]").is(":visible")){
		if($("#rptTab").height() + maxHeight + 600 <900){
			window.parent.parent.parent.document.body.style.height = 900;
		}else{
			window.parent.parent.parent.document.body.style.height = $("#rptTab").height() + maxHeight + 600;
		}
	}else{
		if($("#rptTab").height() + 500 <900){
			window.parent.parent.parent.document.body.style.height = 900;
		}else{
			window.parent.parent.parent.document.body.style.height = $("#rptTab").height() + 500;
		}
	}
	//window.parent.parent.parent.document.body.style.height = document.body.scrollHeight;
		
}