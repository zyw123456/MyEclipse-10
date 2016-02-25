var isIndexJsp = true;
var rpt_appcod = "001";//报告策略
var fad_appcod = "002";//反欺诈报告
var wrn_appcod = "003";//天警云
var appcod = "";
var initTableId = "";
$(document).ready(function() {
	changeHeight();
	//console.log("------------------"+$(".report_right").offsetHeight());
	//console.log("------------------"+document.getElementById("report_right").offsetHeight);
	//console.log(document.body.scrollHeight);
	appcod = rpt_appcod;
	initTableId = "rptTab";
	initTable(initTableId,appcod);
	initPage();
	appcod = fad_appcod;
	initTableId = "fadTab";
	initTable(initTableId,appcod);
	//console.log("-----------------"+$(".report_right").offsetHeight());
	//console.log("------------------"+document.getElementById("report_right").offsetHeight);
	//console.log(document.body.scrollHeight);
//	appcod = wrn_appcod;
//	initTableId = "wrnTab";
//	initTable(initTableId,appcod);
	appcod = "";
	changeHeight();
	
});
function changeHeight(){
	window.parent.parent.parent.document.body.style.height = $("#rptTab").height()+ $("#fadTab").height() + 700;
	//window.parent.parent.parent.document.body.style.height = document.body.scrollHeight + 400;
	/*console.log("------------------"+document.getElementById("report_right").offsetHeight);
	console.log("*"+document.body.scrollHeight);
	console.log(document.body.style.height);
	console.log(document.body.clientHeight);*/
}

