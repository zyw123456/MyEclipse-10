//@ sourceURL=stmIndex.js
var rpt_appcod = "001";//报告策略
var fad_appcod = "002";//反欺诈报告
var wrn_appcod = "003";//天警云
/**
 * 页面初始化
 */
$(document).ready(function() {
	var initDivId = "";
	isStmIndex = true;
	appcod = rpt_appcod;
	initDivId = "rptDiv";
	initTable(initDivId,appcod);
	appcod = fad_appcod;
	initDivId = "fadDiv";
	initTable(initDivId,appcod);
});
/**
 * 生成一个产品div
 * @param divId 模块divid
 * @param index 产品数组下标
 */
function addPrdDivWithData(divId,index){
	var divbody = "";
	divbody += "<div class='col-md-12 col-sm-12 bg-eee top-margin-10 r bg_shu-2'><div class='col-md-2 col-sm-2 p '>"+prds[index].prdnam
			+ "</div><div class='col-md-9 col-sm-9   padding-12 col-md-offset-2  '>";
	for(var i=0;i<prds[index].trns.length;i++){
		divbody += "<label class=' label label-primary btnlab'>" +prds[index].trns[i].trnnam + "</label> ";
	}
	divbody +="</div></div>"
	if(divId == "rptDiv"){
		$("#fadDiv").prev().append(divbody);
	}else{
		$("#"+divId).append(divbody);
	}
}