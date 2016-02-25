var flag =0;
var idArrayPeople;
var isTeam = false;
var delOrgId = null;
var isFuzzyQuery = false;
/**
 * 展示子账户列表
 */
function showPeople() {
	isFuzzyQuery = false;
	//$("#myTb2 tr").eq(1).nextAll().remove(); 
	$("#myTb2").empty();
	 idArray = new Array();
	$.ajax({
		url : "queryCurChildren.action",
		type : "post",
		data : "",
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		success : function(data, textStatus) {
				if (data != null) {
					var obj = JSON.parse(data);
					var tbBody = "";
					if(obj != null){
						for(var i=0;i<obj.length;i++){
							idArray[i] = obj[i];
							tbBody += "<tr align='center' height='30' class='peoTr'><td><div style='width:182px;height:25px;background:#eeeeee;-moz-border-radius: 4px;-webkit-border-radius: 4px;'><a  href='javascript:showPeopleDetail("+i+")'>" + obj[i].peopleCode+ "</a></div></td></tr>";
						}
						$("tr").remove(".peoTr");
						//$("#myTb").append(tbBody);
						$("#newTable").append(tbBody);
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
 * 展示团队列表
 */
function showTeam() {
	//$("#myTb2 tr").eq(1).nextAll().remove(); 
	$("#myTb2").empty();
	flag = 1;
	idArrayTeam = new Array();
	var queryData = $("#queryData").val();
	$.ajax({
		url : "queryCurOrganize.action",
		type : "post",
		//data : "",
		data : {dataStr:queryData},
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		success : function(data, textStatus) {
				if (data !=null) {
					var obj = JSON.parse(data);
					var tbBody = "";
					if(obj != null){
						for(var i=0;i<obj.length;i++){
							idArrayTeam[i] = obj[i].sid;
							tbBody += "<tr align='center' height='30' class='peoTr'><td><div style='width:182px;height:25px;background:#eeeeee;-moz-border-radius: 4px;-webkit-border-radius: 4px;'><a  href='javascript:showTeamDetail("+i+")'>" + obj[i].orgName+ "</a></div></td></tr>";
						}
						$("tr").remove(".peoTr");
						//$("#myTb").append(tbBody);
						$("#newTable").append(tbBody);
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
 * 展示子账户列表下人员详情
 */
function showPeopleDetail(obj){
	var tbBody = "";
	
	tbBody += "<tr align='center' height='30' class='person'><td align='center' width='144'>" + idArray[obj].peopleName+ "</td><td align='center' width='144'>"+idArray[obj].orgName
				+ "</td><td align='center' width='144'>"+idArray[obj].peopleState+"</td><td align='center' width='144'><a href='updatePrdsByPeocode.action?peoId="+idArray[obj].sid+"&dataStr=look'>查看</a>&nbsp;" +
			"<a href='updatePrdsByPeocode.action?peoId="+idArray[obj].sid+"'>修改</a>&nbsp;" +
				"<a href='javascript:showPopup("+obj+")'>删除</a></td></tr>";
	$("tr").remove(".person");
	$("#myTb2").append(tbBody);
}
/**
 * 展示团队列表下人员详情
 */
function showTeamDetail(obj){
	idArrayTeamDetail = new Array();
	index = obj;
	$.ajax({
		url : "getPeopleByOrgId.action",
		type : "post",
		data : {orgId:idArrayTeam[obj]},
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		success : function(data, textStatus) {
				if (data!= null) {
					var obj = JSON.parse(data);
					var tbBody = "";
					if(obj != null){				
						for(var i=0;i<obj.length;i++){
							idArrayTeamDetail[i] = obj[i];
							tbBody += "<tr align='center' height='30' class='person'><td align='center' width='144'>" + obj[i].peopleName+ "</td><td align='center' width='144'>"+obj[i].orgName
							+ "</td><td align='center' width='144'>"+obj[i].peopleState+"</td><td align='center' width='144'><a href='updatePrdsByPeocode.action?peoId="+obj[i].sid+"&dataStr=look'>查看</a>&nbsp;" +
							"<a  href='updatePrdsByPeocode.action?peoId="+obj[i].sid+"'>修改</a>&nbsp;" +
							"<a  href='javascript:showPopupTeam("+i+","+index+")'>删除</a></td></tr>";
						}
						$("tr").remove(".person");
						$("#myTb2").append(tbBody);
						$("#myTb2").each(function(){

							$(this).find('tr:even').css("background","#fff");

							$(this).find('tr:odd').css("background","#eee");

							});


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
 * 页面初始化
 */
$(document).ready(function() {
		showPeople();
});
/**
 * 显示子账户下的删除人员的弹框提示
 */
function showPopup(obj){ 
	isTeam = false;
	$("#popupcontent").css("visibility","visible");
	$("#statusbar").css("visibility","visible");
	$("#sid").val(idArray[obj].sid);
	$("tr").remove(".del");
	$("#myTb3").append("<tr class='del'><td>"+idArray[obj].peopleName+"</td><td>"+idArray[obj].orgName+"</td><td>"+idArray[obj].peopleState+"</td></tr>");
} 
/**
 * 显示团队下的删除人员的弹框提示
 */
function showPopupTeam(obj,orgIndex){ 
	isTeam = true;
	$("#popupcontent").css("visibility","visible");
	$("#statusbar").css("visibility","visible");
	$("#sid").val(idArrayTeamDetail[obj].sid);
	delOrgId = orgIndex;
	$("tr").remove(".del");
	$("#myTb3").append("<tr class='del'><td>"+idArrayTeamDetail[obj].peopleName+"</td><td>"+idArrayTeamDetail[obj].orgName+"</td><td>"+idArrayTeamDetail[obj].peopleState+"</td></tr>");
} 
/**
 * 显示子账户下的删除人员的弹框提示(模糊查询)
 */
function showDeletePepole(obj){
	isTeam = false;
	$("#popupcontent").css("visibility","visible");
	$("#statusbar").css("visibility","visible");
	$("#sid").val(idArrayPeopleDetail[obj].sid);
	$("tr").remove(".del");
	$("#myTb3").append("<tr class='del'><td>"+idArrayPeopleDetail[obj].peopleName+"</td><td>"+idArrayPeopleDetail[obj].orgName+"</td><td>"+idArrayPeopleDetail[obj].peopleState+"</td></tr>");
}
/**
 * 关闭弹框提示
 */
function hidePopup(){ 
	var popUp = document.getElementById("popupcontent"); 
	popUp.style.visibility = "hidden"; 
	$("#statusbar").css("visibility","hidden");
	} 
/**
 * 删除子账户
 */
function del(){
	hidePopup();
	$.ajax({
		url : "deletePeople.action",
		type : "post",
		data : {peoId:$("#sid").val()},
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		success : function(data, textStatus) {
				if (data != null) {
					if(data == 1){	
						alert("删除成功!");
						if(isTeam){
							showTeamDetail(delOrgId);
						}else if(isFuzzyQuery){
							fuzzyFindPeople();
							$("tr").remove(".person");
						}else{
							showPeople();
							$("tr").remove(".person");
						}
					}else if(data==0){
						alert("删除失败!");
					}else if(data==2){
						alert("核心连接失败，删除失败！");
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
function fuzzyFindPeople(){
	//$("#myTb2 tr").eq(1).nextAll().remove(); 
	//$("#table1 tr:not(:first)").remove(); 
	//$("#myTb2  tr:not(:first)").html("");
	//$("#myTb2  tr:not(:first)").empty("");
	isFuzzyQuery = true;
	$("#myTb2").empty();
	 idArrayPeople = new Array();
	flag = 0;
	var queryData = $("#queryData").val();
		$.ajax({
			url : "fuzzyQueryChildren.action",
			type : "post",
			data : {dataStr:queryData},
			beforeSend : function(XMLHttpRequest) {
				XMLHttpRequest.setRequestHeader("RequestType", "ajax");
			},
			async : true,
			success : function(data, textStatus) {
					if (data != null) {
						var obj = JSON.parse(data);
						var tbBody = "";
						if(obj != null){
							for(var i=0;i<obj.length;i++){
								idArrayPeople[i] = obj[i].SID;
								tbBody += "<tr align='center' height='30' class='peoTr'><td ><div style='width:182px;height:25px;background:#eeeeee;-moz-border-radius: 4px;-webkit-border-radius: 4px;'><a  href='javascript:showPeopleInfo("+i+")'>" + obj[i].PEOPLECODE+ "</a></div></td></tr>";
							//	tbBody += "<tr align='center' height='30' class='peoTr'><td ><a  href='javascript:showPeopleDetail("+i+")'>" + obj[i].PEOPLECODE+ "</a></td></tr>";
							}
							$("tr").remove(".peoTr");

							$("#newTable").append(tbBody);
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
function showPeopleInfo(obj){
	idArrayPeopleDetail = new Array();
	index = obj;
	$.ajax({
		url : "findPeopleInfoById.action",
		type : "post",
		data : {peoId:idArrayPeople[obj]},
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		success : function(data, textStatus) {
				if (data!= null) {
					var obj = JSON.parse(data);
					var tbBody = "";
					if(obj != null){				
						for(var i=0;i<obj.length;i++){
							idArrayPeopleDetail[i] = obj[i];
							tbBody += "<tr align='center' height='30' class='person'><td align='center' width='144'>" + obj[i].peopleName+ "</td><td align='center' width='144'>"+obj[i].orgName
							+ "</td><td align='center' width='144'>"+obj[i].peopleState+"</td><td align='center' width='145'><a href='updatePrdsByPeocode.action?peoId="+obj[i].sid+"&dataStr=look'>查看</a>&nbsp;" +
							"<a  href='updatePrdsByPeocode.action?peoId="+obj[i].sid+"'>修改</a>&nbsp;" +
							"<a  href='javascript:showDeletePepole("+i+")'>删除</a></td></tr>";
						}
						$("tr").remove(".person");
						$("#myTb2").append(tbBody);
					}
				}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("加载信息erro:" + textStatus + errorThrown);
		},
		complete : function(XMLHttpRequest, textStatus) {
		}
	});
//	var tbBody = "";
//	tbBody += "<tr align='center' height='30' class='person'><td>" + idArray[obj].peopleName+ "</td><td>"+idArray[obj].orgName
//	+ "</td><td>"+idArray[obj].peopleState+"</td><td><a href='getPrdsByPeocode.action?peoId="+idArray[obj].sid+"'>查看</a>&nbsp;" +
//	"<a href='updatePrdsByPeocode.action?peoId="+idArray[obj].sid+"&dataStr="+idArray[obj].orgName+"'>修改</a>&nbsp;" +
//	"<a href='javascript:showPopup("+obj+")'>删除</a></td></tr>";
//	$("tr").remove(".person");
//	$("#myTb2").append(tbBody);

}
/**
 * 判断输入的条件是查询的使用人还是使用团队
 */
function JudgeGoMehod(){
	if(flag==0){
		fuzzyFindPeople();
	}else if(flag==1){
		showTeam();	
	}
	
}