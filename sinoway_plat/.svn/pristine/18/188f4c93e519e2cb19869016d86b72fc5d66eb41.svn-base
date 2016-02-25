var appcod = "002";//反欺诈报告
var initTableId = "fadTab";
var tableParam = [{
	tableId:'veriTable',//表名
	modulNature:'002'//模块对应的交易特性
},{
	tableId:'exceTable',//表名
	modulNature:'003'//模块对应的交易特性
}];

$(document).ready(function() {
	initTable(initTableId,appcod);
	setInterval("changeHeight()",200);
});
function changeHeight(){
	
	var max = $("#veriTable").height()>$("#exceTable").height()?$("#veriTable").height():$("#exceTable").height();
	var maxHeight = $("#poTable").height()>max?$("#poTable").height():max;
	if($("div[class=disArea]").is(":visible")||$("div[class=po_disArea]").is(":visible")){
		if($("#fadTab").height() + maxHeight + 600 <900){
			window.parent.parent.parent.document.body.style.height = 900;
		}else{
			window.parent.parent.parent.document.body.style.height = $("#fadTab").height() + maxHeight + 600;
		}
	}else{
		if($("#fadTab").height() + 500 <900){
			window.parent.parent.parent.document.body.style.height = 900;
		}else{
			window.parent.parent.parent.document.body.style.height = $("#fadTab").height() + 500;
		}
	}
		
}