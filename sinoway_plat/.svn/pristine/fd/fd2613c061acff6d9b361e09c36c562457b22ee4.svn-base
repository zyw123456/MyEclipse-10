/**
 * 
 * 创建于:2014-9-11<br>
 * 版权所有(C) 2014 深圳市银之杰科技股份有限公司<br>
 * 人员登录日志查询
 * 
 * @author guoshixuan
 * @version 1.0
 */

/**
 * 初始化Grid数据
 */
$(function() {
	var loginPeople = top.loginPeopleInfo;
	$("#organizationSid_Item").val(loginPeople.orgName+"("+loginPeople.orgNo+")");
	$("#operatorOrgNo").val(loginPeople.orgNo);
	initPage();
});

function initPage(){
	var pageHeaderHeight = $(".pageHeader").css("height");
	var pageContentWidth = $(".pageContent").width() -2;
		pageHeaderHeight = pageHeaderHeight.substr(0,pageHeaderHeight.length - 2);
	var tableHeight = document.documentElement.clientHeight -pageHeaderHeight + 6 - 50*2 ;
	$("#logLoginList").jqGrid(
		{
			width : pageContentWidth,
			height : tableHeight,
			url : ctx + "/logLogin_queryList.action",
			multiselect : false,
			rowNum : 20,
			rownumbers:true,
			rowList : [ 20, 50, 100 ],
			colNames : ["操作类型", "操作人", "机构",
			          "操作时间", "备注" ],
			colModel : [
					{
						name : "operateType",
						index : "operateType",
						align : "center",
						sortable : false,
						formatter : function(value, options, rData) {
							return operateType[value];
						}
					},
					{
						name : "operatorCode",
						index : "operatorCode",
						align : "center",
						sortable : false,
						formatter : function(value, options, rData) {
							var operatorName = "";
							if(rData.operatorName!=null){
								operatorName += rData.operatorName;
							}
							if(rData.operatorCode!=null){
								operatorName += "("+rData.operatorCode+")";
							}
							return operatorName;
						}
					},
					{
						name : "operatorOrgNo",
						index : "operatorOrgNo",
						align : "center",
						sortable : false,
						formatter : function(value, options, rData) {
							var operatorOrgName = "";
							if(rData.operatorOrgName!=null){
								operatorOrgName += rData.operatorOrgName;
							}
							if(rData.operatorCode!=null){
								operatorOrgName += "("+rData.operatorOrgNo+")";
							}
							return operatorOrgName;
						}
					},
					{
						name : "operateDate",
						index : "operateDate",
						align : "center",
						width : 130,
						sortable : false,
						formatter : function(value, options, rData) {
							var operateTime = "";
							if(rData.operateDate!=null){
								operateTime += rData.operateDate;
							}
							if(rData.operateTime!=null){
								operateTime += " "+rData.operateTime;
							}
							return operateTime;
						}
					},
					{
						name : "memo",
						index : "memo",
						align : "center",
						sortable : false
					} ],
			pager : "#logLoginPager",
			caption : "用户登录日志信息列表"
		}).trigger("reloadGrid");
	$("#logLoginList").navGrid("#logLoginPager", {
		edit : false,
		add : false,
		del : false,
		search : false,
		refresh : true
	});
}

/**
 * 查询数据，执行查询
 */
function queryData() {
	$("#logLoginList").jqGrid("search", "#queryForm");
}

/**
 * 重置查询条件
 */
function resetMethod() {
	$("#queryForm")[0].reset();
}

/**
 * 选择机构
 */
function checkOrganizationItem(organizationNo){
	var organizationSid = top.loginPeopleInfo.orgSid;
	$("#organizationSid_Item").radioOrgTree(true,organizationSid,0,false,function(event, treeId, treeNode){
		if(treeNode){
			$("#organizationSid_Item").val(treeNode.organizationName+"("+treeNode.organizationNo+")");
			$("#"+organizationNo).val(treeNode.organizationNo);
		}
	});
}