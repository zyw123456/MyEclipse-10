//时间升序功能
function sheng(){
	url = "rptQuery.action";
	if(null == searchObj){
		rptStr = JSON.stringify({prsnnam:"",prsncod:"",rptid:"",rptmodsrtdte:"",rptmodenddte:"",datcmitori:"",timeOrder:'up'});
	}else{
		rptStr = JSON.stringify({prsnnam:searchObj.prsnnam,prsncod:searchObj.prsncod,rptid:searchObj.rptid,rptmodsrtdte:searchObj.rptmodsrtdte,rptmodenddte:searchObj.rptmodenddte,datcmitori:searchObj.datcmitori,timeOrder:'up'});
	}
	
	data={frontObjStr:JSON.stringify({rptStr:rptStr})};
	show();
} 
//时间降序功能
function jiang(){
	url = "rptQuery.action";
	if(null == searchObj){
		rptStr = JSON.stringify({prsnnam:"",prsncod:"",rptid:"",rptmodsrtdte:"",rptmodenddte:"",datcmitori:"",timeOrder:'down'});
	}else{
		rptStr = JSON.stringify({prsnnam:searchObj.prsnnam,prsncod:searchObj.prsncod,rptid:searchObj.rptid,rptmodsrtdte:searchObj.rptmodsrtdte,rptmodenddte:searchObj.rptmodenddte,datcmitori:searchObj.datcmitori,timeOrder:'down'});
	}
	
	data={frontObjStr:JSON.stringify({rptStr:rptStr})};
	show();
}
//页面初始化
$(function(){
	rptContFlow();
	alert('');
    	data = "";
    	searchObj = null;
    	searchParam = "";
    	url = "rptQuery.action";
    	show();
    	setInterval("changeHeight()",200);
    	//全选和全不选
		$("#xuan").click(function(){
			var bb=$("input:checkbox");
			if($("#xuan").attr("checked")){
				bb.each(function(i){
					$(this).attr("checked",true);
				});
			}else{
				bb.each(function(i){
					$(this).removeAttr("checked");
				});
			}
		})
		//确认删除
	  $("#dd").click(function(){
			if($("input:checked").size()>0){
							if(confirm("确定要删除报告？")){
								del();
							}
			}else{
				alert("请选择要删除的报告");
			}
		})
		$("#search").click(function(){
			search();
		});
	});
//模糊查询
function search(){
	$("select option:selected").removeAttr("selected");
	url = "rptQuery.action";
	var rptStr = JSON.stringify({prsnnam:$("#prsnnam").val(),prsncod:$("#prsncod").val(),rptid:$("#rptid").val(),rptmodsrtdte:$("#rptmodsrtdte").val(),rptmodenddte:$("#rptmodenddte").val()});
	data = {frontObjStr:JSON.stringify({rptStr:rptStr})};
	show();
}
//展示报告列表
function show(){ 
	var myTable = $('#tb1').dataTable($.extend({
		"ajax" : function(data, callback, settings) {
			//封装请求参数
			var param = getQueryCondition(data);
			$.ajax({
		            type: "POST",
		            url: url,
		            data: {reqParams : JSON.stringify(param)},
		            dataType: "json",
		            success: function(data) {
		            	alert(data.resData);
		            	//封装返回数据
		            	var returnData = data.resData;
		            	//调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
		            	//此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
		            	callback(JSON.parse(returnData));
		            },
		            error: function(XMLHttpRequest, textStatus, errorThrown) {
		            	console.log("error...");
		            }
		        });
		},
		//取消默认排序查询,否则复选框一列会出现小箭头
		"order": [],
        //TODO 对对应列所需要呈现的对象属性进行映射
		"columns": [
            CONSTANT.DATA_TABLES.CELL_CHECKBOX,
            {
				"className" : "cell-operation",//样式
				"data": "rptmodtim",
				"defaultContent":"",//默认的内容
				"orderable" : true,//不排序
				"width" : "120px"
			},
            {"data": "rptid"},
            {"data": "prsnnam"},
            {"data" : "prsncod"},
            {"data" : "datcmitori"},
            {
				"className" : "cell-operation",//样式
				"data": null,
				"defaultContent":"",//默认的内容
				"orderable" : false,//不排序
				"width" : "120px"
			}
           
        ],
        //对回调数据每一行进行的操作
        "createdRow": function ( row, data, index ) {
        	//行渲染回调,在这里可以对该行dom元素进行任何操作
    
            var btnEdit = $('<button type="button" class="btn btn-small btn-primary btn-edit">查看</button>');
            $('td', row).eq(6).append(btnEdit);
        }
	}, CONSTANT.DATA_TABLES.DEFAULT_OPTION)).api();//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象
	
	//搜索
	search = function(){
		myTable.ajax.reload();
	};
	
}

function getQueryCondition(data){
	alert('star   param');
	var param = {};
	//查询次数的标识符
	param.draw = data.draw;
	//自行处理排序参数  单列排序  TODO 
	if (data.order&&data.order.length&&data.order[0]) {
		switch (data.order[0].column) {
		case 1:
			param.orderColumn = "rptmodtim";
			break;
		case 2:
			param.orderColumn = "rptid";
			break;
		case 3:
			param.orderColumn = "prsnnam";
			break;
		case 4:
			param.orderColumn = "prsncod";
			break;
		case 5:
			param.orderColumn = "datcmitori";
			break;
		}
		param.orderDir = data.order[0].dir;
	}
	
	//自行处理分页参数  分页页码和每页大小
	param.startIndex = data.start;
	param.pageSize = data.length;
	
	//自行处理 自定义搜索框的搜索条件和列头中下拉列表的条件
	param.searchParams = [];
	var prsnnam = {name:'prsnnam',value:$("#prsnnam").val()};
	var prsncod = {name:'prsncod',value:$("#prsncod").val()};
	var rptid = {name:'rptid',value:$("#rptid").val()};
	var rptmodsrtdte = {name:'rptmodsrtdte',value:$("#rptmodsrtdte").val()};
	var rptmodenddte = {name:'rptmodenddte',value:$("#rptmodenddte").val()};
	param.searchParams.push(prsnnam);
	param.searchParams.push(prsncod);
	param.searchParams.push(rptid);
	param.searchParams.push(rptmodsrtdte);
	param.searchParams.push(rptmodenddte);
	return param;
}

function querydetail(rptid){
	var pageStr = JSON.stringify({rptid:rptid});
	window.open('sinoway/fad/fadDetail.jsp?rptid="'+rptid+'"', '报告查看', 'height="100%", width="100%", top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');  
}
function selectPage(n){
	url = "rptQuery.action";
	var pageStr = JSON.stringify({nowPage:n});
	data = {frontObjStr:JSON.stringify({pageStr:pageStr,rptStr:searchParam})};
	show();
}
//数据来源筛选
function sel(){
	//alert($("select option:selected").val());
	url = "rptQuery.action";
	if(null == searchObj){
		rptStr = JSON.stringify({prsnnam:"",prsncod:"",rptid:"",rptmodsrtdte:"",rptmodenddte:"",datcmitori:$("select option:selected").val()});
	}else{
		rptStr = JSON.stringify({prsnnam:searchObj.prsnnam,prsncod:searchObj.prsncod,rptid:searchObj.rptid,rptmodsrtdte:searchObj.rptmodsrtdte,rptmodenddte:searchObj.rptmodenddte,datcmitori:$("select option:selected").val()});
	}
	
	data={frontObjStr:JSON.stringify({rptStr:rptStr})};
	show();
}
//报告删除
function del(){
	var ids=new Array();
	url = "rptDelete.action";
	var j=0;
	var checkbox = $("input[name=box]");
	for(var i=0;i<checkbox.length;i++){
		if(checkbox[i].checked){
			ids[j] = checkbox[i].value;
			j++;
		}
	}
	data={frontObjStr:JSON.stringify({ids:ids.join(",")})};
	show();
}
function rptContFlow(){
	//var versionNumberPct = window.parent.bottom.$("#versionNumberPct").html();//个人征信报告底部流量统计版本号
	$.ajax({
		url : "hpCache_personalReportFlowStatistics.action",
		type : "post",
	//	data : {versionNumber:versionNumberPct},
		data:"",
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		success : function(data, textStatus) {
			if (data != null) {
				var obj = JSON.parse(data);
				var platformUploadRpt = obj.data.platformUploadRpt;
				var interfaceUploadRpt = obj.data.interfaceUploadRpt;
				var publicUploadRpt = obj.data.publicUploadRpt;
			//	var versionNub = obj.data.versionNo;
				 window.parent.bottom.$("#platformUploadRpt").html(platformUploadRpt);
				 window.parent.bottom.$("#interfaceUploadRpt").html(interfaceUploadRpt);
				 window.parent.bottom.$("#publicUploadRpt").html(publicUploadRpt);
				// window.parent.bottom.$("#versionNumberPct").html(versionNub);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("加载信息erro:" + textStatus + errorThrown);
		},
		complete : function(XMLHttpRequest, textStatus) {
		}
	});
}
