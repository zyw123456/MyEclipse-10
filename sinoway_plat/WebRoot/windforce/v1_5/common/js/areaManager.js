
/**
 * 区域类型缓存信息
 */
var areaTypeCache = new Map();


/**
 * 页面初始化
 */
function areaManageInit(){
	//新建人员信息弹出框
	$("#newAreaDLG").dialog({autoOpen: false,caption:"新建区域信息",resizable: false,height: 245,width: 490,modal: true,buttons: {
			"新建": function() {//0
				if($("#newArea_item").validationEngine("validate")){	//表单验证成功
					if($("#Add_areaMemo").val().length > 50){
						alert("区域备注信息限50个字以内.");
						return;
					}
					if($.trim($("#Add_parentAreaCode").val()) == ""){
						alert("请选择对应的上级区域.");
						return;
					}
					if($.trim($("#Add_areaType").val()) == ""){
						alert("请选择对应的区域类型.");
						return;
					}
					$(".ui-dialog-buttonpane button").eq("0").attr("disabled",true);//禁用按钮
					$.post(ctx+"/areaAction_judegeArea.action",$("#newArea_item").serialize(),function(data) {//区域信息验证
						if(data.responseMessage.state == "normal"){
							$.post(ctx+"/areaAction_addArea.action", $("#newArea_item").serialize(),function(data) {
								$(".ui-dialog-buttonpane button").eq("0").attr("disabled",false);//启用按钮
								if(data.responseMessage.state == "normal"){
									$.success("新建区域信息成功.");
									$("#newAreaDLG").dialog("close");
									initAreaTree();
								}else{
									alert(data.responseMessage.data);
								}
							});
						}else{
							$(".ui-dialog-buttonpane button").eq("0").attr("disabled",false);//启用按钮
							alert(data.responseMessage.data);
						}
					});
				}
			},"清空": function() {//1
				$("#newArea_item")[0].reset();
				$("#Add_areaType").html("");
			},"关闭": function() {//2
				$("#newAreaDLG").dialog("close");
			}
		},
		close: function() {
			$(".ui-dialog-buttonpane button").eq("0").attr("disabled",false);//启用按钮
			$("#newArea_item")[0].reset();
			$("#Add_areaType").html("");
			$("#newArea_item").validationEngine("hideAll");
		},open:function(){
			if(null == $("#item_areaSid").val() || $("#item_areaSid").val() == ""){
				return;
			}
		}
	});
	
	//查看信息弹出框
	$("#checkAreaDLG").dialog({autoOpen: false,caption:"查看区域信息", resizable: false,height: 225,width: 490,modal: true,close: function() { 
		$("#checkArea_item")[0].reset();
		$("#areaDataList").trigger("reloadGrid");
	}});
	
	//修改信息弹出框
	$("#modifyAreaDLG").dialog({autoOpen: false,caption:"修改区域信息", resizable: false,height: 245,width: 490,modal: true,buttons: {
		"修改": function() {//3
			if($("#modifyArea_item").validationEngine("validate")){	//表单验证成功
				if($("#mod_areaMemo").val().length > 50){
					alert("区域备注信息限50个字以内.");
					return;
				}
				if($.trim($("#mod_parentAreaCode").val()) == ""){
					alert("请选择对应的上级区域.");
					return;
				}
				if($.trim($("#mod_areaType").val()) == ""){
					alert("请选择对应的区域类型.");
					return;
				}
				$(".ui-dialog-buttonpane button").eq("3").attr("disabled",true);//禁用按钮
				$.post(ctx+"/areaAction_judegeModifyArea.action",$("#modifyArea_item").serialize(),function(data) {//区域信息验证
					if(data.responseMessage.state == "normal"){
						$.post(ctx+"/areaAction_modifyArea.action", $("#modifyArea_item").serialize(),function(data) {
							$(".ui-dialog-buttonpane button").eq("3").attr("disabled",false);//启用按钮
							if(data.responseMessage.state == "normal"){
								$.success("修改区域信息成功.");
								$("#modifyAreaDLG").dialog("close");
								initAreaTree();
								$("#areaDataList").trigger("reloadGrid");
								 $("#areaDataList").trigger("reloadGrid");
							}else{
								alert(data.responseMessage.data);
							}
						});
					}else{
						$(".ui-dialog-buttonpane button").eq("3").attr("disabled",false);//启用按钮
						alert(data.responseMessage.data);
					}
				});
			}
		},"关闭": function() {//4
				$("#modifyAreaDLG").dialog("close");
				$("#modifyArea_item").validationEngine("hideAll");
			}
		},close: function() {
			$(".ui-dialog-buttonpane button").eq("3").attr("disabled",false);//启用按钮
			$("#modifyArea_item").validationEngine("hideAll");
			$("#modifyArea_item")[0].reset();
			$("#Add_areaType").html("");
			$("#areaDataList").trigger("reloadGrid");
		},open:function(){}
	});
	
	//删除信息弹出框
	$("#deleteAreaDLG").dialog({autoOpen: false,caption:"删除区域信息", resizable: false,height: 150,width: 380,modal: true,buttons: {
		"删除": function() {//5
			$(".ui-dialog-buttonpane button").eq("5").attr("disabled",true);//禁用按钮
			$.post(ctx+"/areaAction_deletesArea.action", $("#deleteArea_item").serialize(),function(data) {
				$(".ui-dialog-buttonpane button").eq("5").attr("disabled",false);//启用按钮
				if(data.responseMessage.state == "normal"){
					$.success("删除区域信息成功.");
					$("#deleteAreaDLG").dialog("close");
					initAreaTree();
					 $("#areaDataList").trigger("reloadGrid");
					
				}else{
					alert(data.responseMessage.data);
					$("#deleteAreaDLG").dialog("close");
					initAreaTree();
					 $("#areaDataList").trigger("reloadGrid");
				}
			});
		},"关闭": function() {//6
			$("#deleteAreaDLG").dialog("close");
		}
		},close: function() {
			$(".ui-dialog-buttonpane button").eq("5").attr("disabled",false);//启用按钮
			$("#deleteArea_item")[0].reset();
			$("#areaDataList").trigger("reloadGrid");
		},open:function(){}
	});
	
	//新建用户提交数据验证
	$("#newArea_item").validationEngine({showOnMouseOver:true,validationEventTrigger:"keyup blur",promptPosition : "centerRight", autoPositionUpdate : true,onValidationComplete: function() {}});
	//修改用户提交数据验证
	$("#modifyArea_item").validationEngine({showOnMouseOver:true,validationEventTrigger:"keyup blur",promptPosition : "centerRight", autoPositionUpdate : true,onValidationComplete: function() {}});
	//数据导出
	$("#reportAreaExcel").click(function(){$(this).reportExcel($("#areaList_Item"),ctx+"/areaFormAction!report.action");});
	initAreaTree();//区域树
	initAreaType();//初始化查询下拉单
	jqGridInit();//表格查询
}

function jqGridInit(){
	var pageHeaderHeight = $(".pageHeader").css("height");
	var pageContentWidth = $(".pageContent").width() -2;
	pageHeaderHeight = pageHeaderHeight.substr(0,pageHeaderHeight.length - 2);
	var tableHeight = document.documentElement.clientHeight -pageHeaderHeight + 6 - 50*2 ;
	//区域信息列表
	$("#areaDataList").jqGrid({
		height:tableHeight,
		width:pageContentWidth,	
		url: ctx+"/areaAction_list.action",
		multiselect: true,
		rowNum: 20,
		rowList: [20,50,100],
		colNames:["","区域代码","区域名称","区域类型","上级区域","操作"],
	   	colModel:[
	   	   	{name:"areaSid",index:"areaSid",width:"0px",hidden:true},
	   		{name:"areaCode",index:"areaCode",width:"80px",align:"center",sortable:false,formatter:function(value, options, rData){
	   			return "<a href='#' onclick=\"createCheckAreaDiag('"+rData.areaSid+"');\">"+ value+"</a>";
	   		}},
	   		{name:"areaName",index:"areaName", align:"center",sortable:false},
	   		{name:"areaType",index:"areaType",align:"center",sortable:false,formatter:function(value, options, rData){
	   			if(value == 0){
	   				return "中国行政区";
	   			}
	   			var _areaType = areaTypeCache.get(value);
	   			return _areaType.areaTypeName;
	   		}},
	   		{name:"parentArea",index:"parentArea",align:"center",sortable:false,formatter:"parentArea"},
	   		{name:"tag4",index:"tag4",align:"center",sortable:false,formatter:function(value, options, rData){
	   			var content = "<img src='"+ctx+"/windforce/v1_5/common/css/images/show-icon.gif' style='cursor:pointer;margin-left:3px;' alt='查看区域信息'   title='查看区域信息' onclick=\"createCheckAreaDiag('"+rData.areaSid+"');\"/>" +
				"<img src='"+ctx+"/windforce/v1_5/common/css/images/edit-icon.gif' style='cursor:pointer;margin-left:3px;'  alt='修改区域信息'  title='修改区域信息' onclick=\"createModifyAreaDiag('"+rData.areaSid+"');\" /> " +
					"<img src='"+ctx+"/windforce/v1_5/common/css/images/hr.gif' style='cursor:pointer;margin-left:3px;' alt='删除区域信息'   title='删除区域信息' onclick=\"createDeleteAreaDiag('"+rData.areaSid+"');\"/>";
	   			return content;
	   		}}
	   	],
	   	pager: "#areaDataListpager",
	    gridComplete: function() {
            var rowIds = jQuery("#areaDataList").jqGrid("getDataIDs");
            for(var k=0; k<rowIds.length; k++) {
               var curRowData = jQuery("#areaDataList").jqGrid('getRowData', rowIds[k]);
               var curChk = $("#"+rowIds[k]+"",jQuery("#areaDataList")).find(":checkbox");
               curChk.attr('name', 'checkboxname');   //给每一个checkbox赋名字
               curChk.attr('value', curRowData['areaSid']);   //给checkbox赋值
            } 
        }
	});
}


/**
 * 新建区域弹处选择区域信息对话框
 */
function checkParentAreaForAddArea(){
	$("#newArea_item").validationEngine("hideAll");
	$("#Add_parentAreaCode").radioAreaTree(function(event, treeId, treeNode){
		$("#Add_parentAreaCode").val(treeNode.areaName+"("+treeNode.areaCode+")");
		$("#Add_parentArea").val(treeNode.areaSid);
		var areaType = treeNode.areaType;
		var areaTypeContent = "";
		$.post(ctx+"/areaAction_findChildAreaTypesByAreaTypeSid.action",{areaTypeSid:areaType},function(data) {
			var areaTypeContent = "";
			$.each(data.areaTypes, function(index, areaType) {
				if(areaType.areaTypeName != "ROOT"){
					areaTypeContent+="<option value='"+areaType.sid+"'>"+areaType.areaTypeName+"</option>";
				}
			});
			$("#Add_areaType").html(areaTypeContent);
		});
	});
}

/**
 * 修改区域信息弹处选择区域信息对话框
 */
function checkParentAreaForModArea(){
	$("#modifyArea_item").validationEngine("hideAll");
	$("#mod_parentAreaCode").radioAreaTree(function(event, treeId, treeNode){
		$("#mod_parentAreaCode").val(treeNode.areaName+"("+treeNode.areaCode+")");
		$("#mod_parentArea").val(treeNode.areaSid);
		var areaType = treeNode.areaType;
		var areaTypeContent = "";
		$.post(ctx+"/areaAction_findChildAreaTypesByAreaTypeSid.action",{areaTypeSid:areaType},function(data) {
			var areaTypeContent = "";
			$.each(data.areaTypes, function(index, areaType) {
				if(areaType.areaTypeName != "ROOT"){
					areaTypeContent+="<option value='"+areaType.sid+"'>"+areaType.areaTypeName+"</option>";
				}
			});
			$("#mod_areaType").html(areaTypeContent);
		});
	});
}

/**
 * 初始化区域类型下拉单
 * 
 */
function initAreaType(){
	$.post(ctx+"/areaAction_findAllAreaTypes.action",function(data) {
		var areaTypeContent = "<option value=''></option>";
		$.each(data.areaTypes, function(index, areaType) {
			areaTypeCache.put(areaType.sid, areaType);
			if(areaType.areaTypeName != "ROOT"){
				areaTypeContent+="<option value='"+areaType.sid+"'>"+areaType.areaTypeName+"</option>";
			}
		});
		$("#areaTypechildList").html(areaTypeContent);
	});
}

/**
 * 初始化区域导航树
 */
function initAreaTree(){
	$("#areaTreeDemo").areaTree("normal",function(event, treeId, treeNode){
		if(treeNode.areaCode == "000000"){
			return;
		}
		$("#areaList_Item")[0].reset(); //查询表单
		$("#item_areaCode").val(treeNode.areaCode);
		$("#areaDataList").jqGrid("search", "#areaList_Item");
	},null);
}

/**
 * 条件查询
 */
function queryAreasForTerm(){
	$("#areaDataList").jqGrid("search", "#areaList_Item");
}


/**
 * 新建人员信息对话框
 */
function createAddAreaDiag(){
	$("#newAreaDLG").dialog("open");
}

/**
 * 清除按钮事件
 */
function clearQueryAreasForTerm(){
	$("#areaList_Item")[0].reset(); //查询表单
}

/**
 * 查看信息对话框
 */
function createCheckAreaDiag(areaSid){
	$.post(ctx+"/areaAction_findAreaByareaSid.action",{areaSid:areaSid},function(data) {
		if(data.responseMessage.state == "normal"){
			var area = data.area;
			$("#chk_areaCode").val(area.areaCode);
			$("#chk_areaName").val(area.areaName);
			var _parent = area.parentArea;
			var parent =  _parent.split("-");
			if(parent[1] == "ROOT(999999)"){
				$("#chk_parentArea").val(parent[0]);
				$("#chk_parentAreaCode").val("无上级区域");
			}else{
				$("#chk_parentArea").val(parent[0]);
				$("#chk_parentAreaCode").val(parent[1]);
			}
			var _areaType = area.areaType;
			var areaType=_areaType.split("-");
			var areaTypeContent =  "";
			if(areaType[1] == "ROOT"){
				areaTypeContent =  "<option value='"+areaType[0]+"'>中国行政区</option>";
			}else{
				areaTypeContent =  "<option value='"+areaType[0]+"'>"+areaType[1]+"</option>";
			}
			$("#chk_areaType").html(areaTypeContent);
			$("#chk_areaMemo").val(area.areaMemo == null ? "" : area.areaMemo);
			$("#checkAreaDLG").dialog("open");
		}else{
			alert(data.responseMessage.data);
		}
	});
	
}

/**
 * 修改人员信息对话框
 */
function createModifyAreaDiag(areaSid){
	$.post(ctx+"/areaAction_findAreaByareaSid.action",{areaSid:areaSid},function(data) {
		if(data.responseMessage.state == "normal"){
			var childAreaTypes = data.areaTypes;
			var area = data.area;
			if(area.areaSid == "000000"){
				alert("修改区域信息异常：中国行政区域信息不可以修改.");
				return;
			}
			$("#mod_areaSid").val(area.areaSid);
			$("#mod_areaCode").val(area.areaCode);
			$("#mod_areaName").val(area.areaName);
			var _parent = area.parentArea;
			var parent =  _parent.split("-");
			$("#mod_parentArea").val(parent[0]);
			$("#mod_parentAreaCode").val(parent[1]);
			var areaTypeContent =  "";
			var areaType = area.areaType;
			var areaTypeSid = areaType.split("-")[0];
			$.each(childAreaTypes, function(index, tempAreaType) {
				if(tempAreaType.sid == areaTypeSid){
					areaTypeContent +=  "<option selected='selected' value='"+tempAreaType.sid+"'>"+tempAreaType.areaTypeName+"</option>";
				}else{
					areaTypeContent +=  "<option value='"+tempAreaType.sid+"'>"+tempAreaType.areaTypeName+"</option>";
				}
			});
			$("#mod_areaType").html(areaTypeContent);
			$("#mod_areaMemo").val(area.areaMemo == null ? "" : area.areaMemo);
			$("#modifyAreaDLG").dialog("open");
		}else{
			alert(data.responseMessage.data);
		}
	});
}

/**
 * 删除人员信息对话框
 */
function createDeleteAreaDiag(deleteType){
	var checkedVals = new Array();
	if(deleteType == 0){
		$(":checkbox[name=checkboxname][checked]").each(function(){
			checkedVals.push($(this).val());
		});
		if(checkedVals.length < 1){
			alert("请选择需要删除区域信息.");
			return;
		}
	}else{
		checkedVals.push(deleteType);
	}
	$("#delete_areaSids").val(checkedVals);
	$("#deleteAreaDLG").dialog("open");
}

