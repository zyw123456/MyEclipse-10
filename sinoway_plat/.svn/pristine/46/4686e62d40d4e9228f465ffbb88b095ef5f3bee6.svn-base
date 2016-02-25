/**
 * 机构工作时间
 * 
 */
function workingTimeInit(){
	$.post(ctx+"/wf/ext/po/extOrganizationAction_queryOrgTypeForLevel.action",function(data){//新增
		if(data.responseMessage.state == "normal"){
			orgTypeList = data.orgTypesList;
		}
	});
	//设置机构工作时间弹出框
	$("#CHKWorkingTimeDLG").dialog({autoOpen: false,caption:"查看机构工作时间", resizable: false,height: 315,width: 400,modal: true,close: function() {$("#CHKWorkingTimeFormItem")[0].reset();}});
	
	//修改机构工作时间弹出框
	$("#MODSIGWorkingTimeDLG").dialog({autoOpen: false,caption:"修改机构工作时间", resizable: false,height: 355,width: 420,modal: true,buttons: {
		"修改": function() {
			var monStartTime = $("#ModSigmonStartTime").val();
			var monEndTime = $("#ModSigmonEndTime").val();
			if(monStartTime == "" && monEndTime != ""){
				alert("修改机构工作时间异常：周一的机构工作时间的开始时间不能为空.");
				return ;
			}
			if(monStartTime != "" && monEndTime == ""){
				alert("修改机构工作时间异常：周一的机构工作时间的结束时间不能为空.");
				return ;
			}
			if(monStartTime > monEndTime){
				alert("修改机构工作时间异常：周一机构工作时间的开始时间不能大于结束时间.");
				return ;
			}
			
			//
			var tuesStartTime = $("#ModSigtuesStartTime").val();
			var tuesEndTime = $("#ModSigtuesEndTime").val();
			if(tuesStartTime == "" && tuesEndTime != ""){
				alert("修改机构工作时间异常：周二的机构工作时间的开始时间不能为空.");
				return ;
			}
			if(tuesStartTime != "" && tuesEndTime == ""){
				alert("修改机构工作时间异常：周二的机构工作时间的结束时间不能为空.");
				return ;
			}
			if(tuesStartTime > tuesEndTime){
				alert("修改机构工作时间异常：周二机构工作时间的开始时间不能大于结束时间.");
				return ;
			}
			//
			var wedStartTime = $("#ModSigwedStartTime").val();
			var wedEndTime = $("#ModSigwedEndTime").val();
			if(wedStartTime == "" && wedEndTime != ""){
				alert("修改机构工作时间异常：周三的机构工作时间的开始时间不能为空.");
				return ;
			}
			if(wedStartTime != "" && wedEndTime == ""){
				alert("修改机构工作时间异常：周三的机构工作时间的结束时间不能为空.");
				return ;
			}
			if(wedStartTime >wedEndTime){
				alert("修改机构工作时间异常：周三机构工作时间的开始时间不能大于结束时间.");
				return ;
			}
			
			//
			var thursStartTime = $("#ModSigthursStartTime").val();
			var thursEndTime = $("#ModSigthursEndTime").val();
			if(thursStartTime == "" && thursEndTime != ""){
				alert("修改机构工作时间异常：周四的机构工作时间的开始时间不能为空.");
				return ;
			}
			if(thursStartTime != "" && thursEndTime == ""){
				alert("修改机构工作时间异常：周四的机构工作时间的结束时间不能为空.");
				return ;
			}
			if(thursStartTime > thursEndTime){
				alert("修改机构工作时间异常：周四机构工作时间的开始时间不能大于结束时间.");
				return ;
			}
			
			//
			var fridStartTime = $("#ModSigfridStartTime").val();
			var fridEndTime = $("#ModSigfridEndTime").val();
			if(fridStartTime == "" && fridEndTime != ""){
				alert("修改机构工作时间异常：周五的机构工作时间的开始时间不能为空.");
				return ;
			}
			if(fridStartTime != "" && fridEndTime == ""){
				alert("修改机构工作时间异常：周五的机构工作时间的结束时间不能为空.");
				return ;
			}
			if(fridStartTime > fridEndTime){
				alert("修改机构工作时间异常：周五机构工作时间的开始时间不能大于结束时间.");
				return ;
			}
			
			//
			var satStartTime = $("#ModSigsatStartTime").val();
			var satEndTime = $("#ModSigsatEndTime").val();
			if(satStartTime == "" && satEndTime != ""){
				alert("修改机构工作时间异常：周六的机构工作时间的开始时间不能为空.");
				return ;
			}
			if(satStartTime != "" && satEndTime == ""){
				alert("修改机构工作时间异常：周六的机构工作时间的结束时间不能为空.");
				return ;
			}	
			if(satStartTime > satEndTime){
				alert("修改机构工作时间异常：周六机构工作时间的开始时间不能大于结束时间.");
				return ;
			}
			
			//
			var sunStartTime = $("#ModSigsunStartTime").val();
			var sunEndTime = $("#ModSigsunEndTime").val();
			if(sunStartTime == "" && sunEndTime != ""){
				alert("修改机构工作时间异常：周日的机构工作时间的开始时间不能为空.");
				return ;
			}
			if(sunStartTime != "" && sunEndTime == ""){
				alert("修改机构工作时间异常：周日的机构工作时间的结束时间不能为空.");
				return ;
			}
			if(sunStartTime > sunEndTime){
				alert("修改机构工作时间异常：周日机构工作时间的开始时间不能大于结束时间.");
				return ;
			}
			
			var params = $("#ModSigWorkingTimeFormItem").serialize();
			$.post(ctx+"/orgWorkingTimeAction_modSigWorkingTime.action",params,function(data){//新增
				if(null == data || null == data.result){
					alert("修改机构工作时间异常：设置机构工作时间过程出现数据异常.");
				}else if("SUCCESS" == data.result){
					$.success("操作成功");
					$("#orgWorkingTimeList").jqGrid("search", "#WorkingTimeForm");
					$("#MODSIGWorkingTimeDLG").dialog("close");
				}else{
					alert(data.result);
				}
			});
		},"关闭": function() {
			$("#MODSIGWorkingTimeDLG").dialog("close");
		}},close: function() {
		$("#ModSigWorkingTimeFormItem")[0].reset();
	}});
	
	//修改机构工作时间弹出框
	$("#ModWorkingTimeDLG").dialog({autoOpen: false,caption:"修改机构工作时间", resizable: false,height: 450,width: 560,modal: true,buttons: {
			"设置": function() {
				var checkedVals = new Array();
				$(":checkbox[name=ModOrgname][checked]").each(function(){
					checkedVals.push($(this).val());
				});
				if(checkedVals.length < 1){
					alert("修改机构工作时间异常：请选择需要设置的设备所在的机构。");
					return;
				}
				
				$("#ModOrganizationSid").val(checkedVals);
				var monStartTime = $("#ModmonStartTime").val();
				var monEndTime = $("#ModmonEndTime").val();
				if(monStartTime == "" && monEndTime != ""){
					alert("修改机构工作时间异常：周一的机构工作时间的开始时间不能为空.");
					return ;
				}
				if(monStartTime != "" && monEndTime == ""){
					alert("修改机构工作时间异常：周一的机构工作时间的结束时间不能为空.");
					return ;
				}
				if(monStartTime > monEndTime){
					alert("修改机构工作时间异常：周一机构工作时间的开始时间不能大于结束时间.");
					return ;
				}
				
				//
				var tuesStartTime = $("#ModtuesStartTime").val();
				var tuesEndTime = $("#ModtuesEndTime").val();
				if(tuesStartTime == "" && tuesEndTime != ""){
					alert("修改机构工作时间异常：周二的机构工作时间的开始时间不能为空.");
					return ;
				}
				if(tuesStartTime != "" && tuesEndTime == ""){
					alert("修改机构工作时间异常：周二的机构工作时间的结束时间不能为空.");
					return ;
				}
				if(tuesStartTime > tuesEndTime){
					alert("修改机构工作时间异常：周二机构工作时间的开始时间不能大于结束时间.");
					return ;
				}
				
				//
				var wedStartTime = $("#ModwedStartTime").val();
				var wedEndTime = $("#ModwedEndTime").val();
				if(wedStartTime == "" && wedEndTime != ""){
					alert("修改机构工作时间异常：周三的机构工作时间的开始时间不能为空.");
					return ;
				}
				if(wedStartTime != "" && wedEndTime == ""){
					alert("修改机构工作时间异常：周三的机构工作时间的结束时间不能为空.");
					return ;
				}
				if(wedStartTime > wedEndTime){
					alert("修改机构工作时间异常：周三机构工作时间的开始时间不能大于结束时间.");
					return ;
				}
				
				//
				var thursStartTime = $("#ModthursStartTime").val();
				var thursEndTime = $("#ModthursEndTime").val();
				if(thursStartTime == "" && thursEndTime != ""){
					alert("修改机构工作时间异常：周四的机构工作时间的开始时间不能为空.");
					return ;
				}
				if(thursStartTime != "" && thursEndTime == ""){
					alert("修改机构工作时间异常：周四的机构工作时间的结束时间不能为空.");
					return ;
				}
				if(thursStartTime > thursEndTime){
					alert("修改机构工作时间异常：周四机构工作时间的开始时间不能大于结束时间.");
					return ;
				}
				
				//
				var fridStartTime = $("#ModfridStartTime").val();
				var fridEndTime = $("#ModfridEndTime").val();
				if(fridStartTime == "" && fridEndTime != ""){
					alert("修改机构工作时间异常：周五的机构工作时间的开始时间不能为空.");
					return ;
				}
				if(fridStartTime != "" && fridEndTime == ""){
					alert("修改机构工作时间异常：周五的机构工作时间的结束时间不能为空.");
					return ;
				}
				if(fridStartTime > fridEndTime){
					alert("修改机构工作时间异常：周五机构工作时间的开始时间不能大于结束时间.");
					return ;
				}
				
				//
				var satStartTime = $("#ModsatStartTime").val();
				var satEndTime = $("#ModsatEndTime").val();
				if(satStartTime == "" && satEndTime != ""){
					alert("修改机构工作时间异常：周六的机构工作时间的开始时间不能为空.");
					return ;
				}
				if(satStartTime != "" && satEndTime == ""){
					alert("修改机构工作时间异常：周六的机构工作时间的结束时间不能为空.");
					return ;
				}
				if(satStartTime > satEndTime){
					alert("修改机构工作时间异常：周六机构工作时间的开始时间不能大于结束时间.");
					return ;
				}
				
				//
				var sunStartTime = $("#ModsunStartTime").val();
				var sunEndTime = $("#ModsunEndTime").val();
				if(sunStartTime == "" && sunEndTime != ""){
					alert("修改机构工作时间异常：周日的机构工作时间的开始时间不能为空.");
					return ;
				}
				if(sunStartTime != "" && sunEndTime == ""){
					alert("修改机构工作时间异常：周日的机构工作时间的结束时间不能为空.");
					return ;
				}
				if(sunStartTime > sunEndTime){
					alert("修改机构工作时间异常：周日机构工作时间的开始时间不能大于结束时间.");
					return ;
				}
				
				var params = $("#ModWorkingTimeFormItem").serialize();
				$.post(ctx+"/orgWorkingTimeAction_modWorkingTime.action",params,function(data){//新增
					if(null == data || null == data.result){
						alert("修改机构工作时间异常：设置机构工作时间过程出现数据异常.");
					}else if("SUCCESS" == data.result){
						$.success("操作成功");
						$("#orgWorkingTimeList").jqGrid("search", "#WorkingTimeForm");
						$("#ModWorkingTimeDLG").dialog("close");
					}else{
						alert(data.result);
					}
				});
			},"关闭": function() {//2按钮
				$("#ModWorkingTimeDLG").dialog("close");
			}
		},close: function() {
			$("#ModWorkingTimeFormItem")[0].reset(); //查询表单
		},open:function(){
		}
	});
	
	//设置机构工作时间弹出框
	$("#AddWorkingTimeDLG").dialog({autoOpen: false,caption:"设置机构工作时间", resizable: false,height: 450,width: 560,modal: true,buttons: {
			"设置": function() {
				var checkedVals = new Array();
				$(":checkbox[name=checkboxname][checked]").each(function(){
					checkedVals.push($(this).val());
				});
				if(checkedVals.length < 1){
					alert("设置机构工作时间异常：请选择需要设置的设备所在的机构。");
					return;
				}
				$("#setOrganizationSid").val(checkedVals);
				var monStartTime = $("#monStartTime").val();
				var monEndTime = $("#monEndTime").val();
				if(monStartTime == "" && monEndTime != ""){
					alert("设置机构工作时间异常：周一的机构工作时间的开始时间不能为空.");
					return ;
				}
				if(monStartTime != "" && monEndTime == ""){
					alert("设置机构工作时间异常：周一的机构工作时间的结束时间不能为空.");
					return ;
				}
				if(monStartTime > monEndTime){
					alert("设置机构工作时间异常：周一机构工作时间的开始时间不能大于结束时间.");
					return ;
				}
				
				//
				var tuesStartTime = $("#tuesStartTime").val();
				var tuesEndTime = $("#tuesEndTime").val();
				if(tuesStartTime == "" && tuesEndTime != ""){
					alert("设置机构工作时间异常：周二的机构工作时间的开始时间不能为空.");
					return ;
				}
				if(tuesStartTime != "" && tuesEndTime == ""){
					alert("设置机构工作时间异常：周二的机构工作时间的结束时间不能为空.");
					return ;
				}
				if(tuesStartTime > tuesEndTime){
					alert("设置机构工作时间异常：周二机构工作时间的开始时间不能大于结束时间.");
					return ;
				}
				
				//
				var wedStartTime = $("#wedStartTime").val();
				var wedEndTime = $("#wedEndTime").val();
				if(wedStartTime == "" && wedEndTime != ""){
					alert("设置机构工作时间异常：周三的机构工作时间的开始时间不能为空.");
					return ;
				}
				if(wedStartTime != "" && wedEndTime == ""){
					alert("设置机构工作时间异常：周三的机构工作时间的结束时间不能为空.");
					return ;
				}
				if(wedStartTime > wedEndTime){
					alert("设置机构工作时间异常：周三机构工作时间的开始时间不能大于结束时间.");
					return ;
				}
				
				//
				var thursStartTime = $("#thursStartTime").val();
				var thursEndTime = $("#thursEndTime").val();
				if(thursStartTime == "" && thursEndTime != ""){
					alert("设置机构工作时间异常：周四的机构工作时间的开始时间不能为空.");
					return ;
				}
				if(thursStartTime != "" && thursEndTime == ""){
					alert("设置机构工作时间异常：周四的机构工作时间的结束时间不能为空.");
					return ;
				}
				if(thursStartTime > thursEndTime){
					alert("设置机构工作时间异常：周四机构工作时间的开始时间不能大于结束时间.");
					return ;
				}
				
				//
				var fridStartTime = $("#fridStartTime").val();
				var fridEndTime = $("#fridEndTime").val();
				if(fridStartTime == "" && fridEndTime != ""){
					alert("设置机构工作时间异常：周五的机构工作时间的开始时间不能为空.");
					return ;
				}
				if(fridStartTime != "" && fridEndTime == ""){
					alert("设置机构工作时间异常：周五的机构工作时间的结束时间不能为空.");
					return ;
				}
				if(fridStartTime > fridEndTime){
					alert("设置机构工作时间异常：周五机构工作时间的开始时间不能大于结束时间.");
					return ;
				}
 				
				//
				var satStartTime = $("#satStartTime").val();
				var satEndTime = $("#satEndTime").val();
				if(satStartTime == "" && satEndTime != ""){
					alert("设置机构工作时间异常：周六的机构工作时间的开始时间不能为空.");
					return ;
				}
				if(satStartTime != "" && satEndTime == ""){
					alert("设置机构工作时间异常：周六的机构工作时间的结束时间不能为空.");
					return ;
				}
				if(satStartTime > satEndTime){
					alert("设置机构工作时间异常：周六机构工作时间的开始时间不能大于结束时间.");
					return ;
				}
				
				//
				var sunStartTime = $("#sunStartTime").val();
				var sunEndTime = $("#sunEndTime").val();
				if(sunStartTime == "" && sunEndTime != ""){
					alert("设置机构工作时间异常：周日的机构工作时间的开始时间不能为空.");
					return ;
				}
				if(sunStartTime != "" && sunEndTime == ""){
					alert("设置机构工作时间异常：周日的机构工作时间的结束时间不能为空.");
					return ;
				}
				if(sunStartTime > sunEndTime){
					alert("设置机构工作时间异常：周日机构工作时间的开始时间不能大于结束时间.");
					return ;
				}
				
				var params = $("#AddWorkingTime_term").serialize();
				$.post(ctx+"/orgWorkingTimeAction_addWorkingTime.action",params,function(data){//新增
					if(null == data || null == data.result){
						alert("设置机构工作时间异常：设置机构工作时间过程出现数据异常.");
					}else if("SUCCESS" == data.result){
						$.success("操作成功");
						$("#orgWorkingTimeList").jqGrid("search", "#WorkingTimeForm");
						$("#AddWorkingTimeDLG").dialog("close");
					}else{
						alert(data.result);
					}
				});
			},"关闭": function() {//2按钮
				$("#AddWorkingTimeDLG").dialog("close");
			}
		},close: function() {
			$("#AddWorkingTime_term")[0].reset(); //查询表单
		},open:function(){
		}
	});
	//230是左侧机构树区域width
	var pageHeaderHeight = $(".pageHeader").css("height");
	var pageContentWidth = $(".pageContent").width() -2;
	pageHeaderHeight = pageHeaderHeight.substr(0,pageHeaderHeight.length - 2);
	var tableHeight = document.documentElement.clientHeight -pageHeaderHeight + 6 - 50*2 ;
	//人员信息列表
	$("#orgWorkingTimeList").jqGrid({
		width:pageContentWidth,
		height:tableHeight,
		url: ctx+"/orgWorkingTimeAction_workingTimeList.action",
		multiselect: true,
		rowNum: 20,
		rowList: [20,50,100], 
		colNames:["","机构","周一","周二","周三","周四","周五","周六","周日","操作"],	colModel:[
	   	 	{name:"autoId",index:"autoId",hidden:true,width:"0px"},
	   		{name:"organizationSid",index:"organizationSid",align:"center",width:100,sortable:false, formatter: "organization"},
	   		{name:"weekMon",index:"weekMon", align:"center",width:100,sortable:false,formatter:function(value, options, rData){
	   			if(null == $.trim(value) || ""== $.trim(value)){
	   				return "--";
	   			}else{
	   				return value;
	   			}
	   		}},
	   		{name:"weekTues",index:"weekTues", align:"center",width:100,sortable:false,formatter:function(value, options, rData){
	   			if(null == $.trim(value) || ""== $.trim(value)){
	   				return "--";
	   			}else{
	   				return value;
	   			}
	   		}},
	   		{name:"weekWed",index:"weekWed", align:"center",width:100,sortable:false,formatter:function(value, options, rData){
	   			if(null == $.trim(value) || ""== $.trim(value)){
	   				return "--";
	   			}else{
	   				return value;
	   			}
	   		}},
	   		{name:"weekThurs",index:"weekThurs", align:"center",width:100,sortable:false,formatter:function(value, options, rData){
	   			if(null == $.trim(value) || ""== $.trim(value)){
	   				return "--";
	   			}else{
	   				return value;
	   			}
	   		}},
	   		{name:"weekFrid",index:"weekFrid", align:"center",width:100,sortable:false,formatter:function(value, options, rData){
	   			if(null == $.trim(value) || ""== $.trim(value)){
	   				return "--";
	   			}else{
	   				return value;
	   			}
	   		}},
	   		{name:"weekSat",index:"weekSat", align:"center",width:100,sortable:false,formatter:function(value, options, rData){
	   			if(null == $.trim(value) || ""== $.trim(value)){
	   				return "--";
	   			}else{
	   				return value;
	   			}
	   		}},
	   		{name:"weekSun",index:"weekSun", align:"center",width:100,sortable:false,formatter:function(value, options, rData){
	   			if(null == $.trim(value) || ""== $.trim(value)){
	   				return "--";
	   			}else{
	   				return value;
	   			}
	   		}},
	   		{name:"tag4",index:"tag4", align:"center",width:100,sortable:false,formatter:function(value, options, rData){
	   			return "<img src='"+ctx+"/windforce/common/images/wt_search.png' style='cursor:pointer;margin-left:3px;'  alt='查看机构工作时间'  title='查看机构工作时间' onclick=\"createChkWorkingTimeDiag('"+rData.autoId+"');\" />" +
	   					  "<img src='"+ctx+"/windforce/common/images/wt_edit.gif' style='cursor:pointer;margin-left:3px;'  alt='机构工作时间修改'  title='机构工作时间修改' onclick=\"createModWorkingTimeDiag('"+rData.autoId+"');\" />" +
	   					  "<img src='"+ctx+"/windforce/common/images/wt_hr.gif' style='cursor:pointer;margin-left:3px;'  alt='机构工作时间清除'  title='机构工作时间清除' onclick=\"createClearWorkingTimeDiag('"+rData.autoId+"');\" />&nbsp;&nbsp;&nbsp;";
   		   }}
	   	],
	   	pager: "#orgWorkingTimeListPager",    
	   	gridComplete: function() {
            var rowIds = jQuery("#orgWorkingTimeList").jqGrid("getDataIDs");
            for(var k=0; k<rowIds.length; k++) {
               var curRowData = jQuery("#orgWorkingTimeList").jqGrid('getRowData', rowIds[k]);
               var curChk = $("#"+rowIds[k]+"", jQuery("#orgWorkingTimeList")).find(":checkbox");
               curChk.attr('name', 'orgWorkCHKBOX');   //给每一个checkbox赋名字
               curChk.attr('value', curRowData['autoId']);   //给checkbox赋值
            } 
        }
	});
	
	//机构信息列表
	$("#AddWorkingTimeOrgItemList").jqGrid({width:558,height:198,url: ctx+"/wf/ext/po/extOrganizationAction_queryDirectChildOrganizationsList.action",multiselect: true,rowNum: 50,rowList: [50,100,150], 
		colNames:["","机构代码","机构名称","机构级别"],	colModel:[
		     	        {name:"sid",index:"sid", hidden:true,width:"0px"},
		                {name:"orgNo",index:"organizationNo", align:"center",sortable:false},
		                {name:"orgName",index:"organizationName",align:"center",sortable:false},
		                {name:"orgType",index:"organizationType",  align:"center",sortable:false,
							formatter : function(value, options, rData) {
								if(orgTypeList!=null){
									return orgTypeList[rData.orgType];
								}
							}}
		              ], pager: "#AddWorkingTimeOrgItemListPager",    
		                gridComplete: function() {
		                          var rowIds = jQuery("#AddWorkingTimeOrgItemList").jqGrid("getDataIDs");
		                           for(var k=0; k<rowIds.length; k++) {
		                              var curRowData = jQuery("#AddWorkingTimeOrgItemList").jqGrid('getRowData', rowIds[k]);
		                              var _curChk = $("#"+rowIds[k]+"", jQuery("#AddWorkingTimeOrgItemList") ).find(":checkbox");
		                              _curChk.attr('name', 'checkboxname');   //给每一个checkbox赋名字
		                              _curChk.attr('value', curRowData['sid']);   //给checkbox赋值
		                        } 
		                }
	});
	
	//机构信息列表
	$("#ModWorkingTimeOrgItemList").jqGrid({width:558,height:225,url: ctx+"/wf/ext/po/extOrganizationAction_organizationsListByOrganizationSids.action",multiselect: true,rowNum: 50,rowList: [50,100,150], 
		colNames:["","机构代码","机构名称","机构级别"],	colModel:[
		                {name:"sid",index:"sid", hidden:true,width:"0px"},
		                {name:"orgNo",index:"organizationNo", align:"center",sortable:false},
		                {name:"orgName",index:"organizationName",align:"center",sortable:false},
		                {name:"orgType",index:"organizationType",  align:"center",sortable:false,formatter:"formatOrganizationType"}
		               ],pager: "#ModWorkingTimeOrgItemListPager",    
		                 gridComplete: function() {
		                  	  var rowIds = jQuery("#ModWorkingTimeOrgItemList").jqGrid("getDataIDs");
		                  	  for(var k=0; k<rowIds.length; k++) {
		                  		    $("#ModWorkingTimeOrgItemList").setSelection(k+1);//选中所有行
		                       		  var curRowData = jQuery("#ModWorkingTimeOrgItemList").jqGrid('getRowData', rowIds[k]);
		                       		  var _curChk = $("#"+rowIds[k]+"", jQuery("#ModWorkingTimeOrgItemList") ).find(":checkbox");
		                        	  _curChk.attr('name', 'ModOrgname');   //给每一个checkbox赋名字
		                        	  _curChk.attr('checked', 'checked');   //全选
		                           	 _curChk.attr('value', curRowData['sid']);   //给checkbox赋值
		                       } 
		              }
	});
}

/**
 * 初始化机构树
 */
function initOrgTree(){
	$("#orgTreeItem").orgTree("normal", top.loginPeopleInfo.orgSid,false,function(event, treeId, treeNode){
		var organizationSid = treeNode.sid;
		$("#WorkingTimeForm")[0].reset(); //查询表单
		$("#organizationSid_Item").val(treeNode.organizationName+"("+treeNode.organizationNo+")");
		$("#organizationSid").val(organizationSid);
		$("#WorkingTimeForm").validationEngine("hideAll");
		$("#orgWorkingTimeList").jqGrid("search", "#WorkingTimeForm");
	},null);
}

/**
 * 查看机构工作时间 
 * 
 * @param autoId
 */
function createChkWorkingTimeDiag(autoId){
	if(null == autoId){
		alert("查看机构工作时间异常：设置机构工作时间数据异常");
		return;
	}
	
	$.post(ctx+"/orgWorkingTimeAction_findWorkingTime.action",{autoId:autoId},function(data){//新增
		if(null == data || null == data.result || null == data.orgWorkingTime){
			alert("查看机构工作时间异常：设置机构工作时间数据异常.");
		}else if("SUCCESS" == data.result){
			var orgWorkingTime = data.orgWorkingTime;
			var  organization = Organization.getOrganization(orgWorkingTime.organizationSid);
			$("#chkOrganizationSid_Item").val(organization.organizationName)
			 $("#chkmonStartTime").val(orgWorkingTime.monStartTime);
			$("#chkmonEndTime").val(orgWorkingTime.monEndTime);
			
			$("#chktuesStartTime").val(orgWorkingTime.tuesStartTime);
			$("#chktuesEndTime").val(orgWorkingTime.tuesEndTime);
			
			$("#chkwedStartTime").val(orgWorkingTime.wedStartTime);
			$("#chkwedEndTime").val(orgWorkingTime.wedEndTime);
			
			$("#chkthursStartTime").val(orgWorkingTime.thursStartTime);
			$("#chkthursEndTime").val(orgWorkingTime.thursEndTime);
			
			$("#chkfridStartTime").val(orgWorkingTime.tridStartTime);
			$("#chkfridEndTime").val(orgWorkingTime.tridEndTime);
			
			$("#chksatStartTime").val(orgWorkingTime.satStartTime);
			$("#chksatEndTime").val(orgWorkingTime.satEndTime);
			
			$("#chksunStartTime").val(orgWorkingTime.sunStartTime);
			$("#chksunEndTime").val(orgWorkingTime.sunEndTime);
			$("#CHKWorkingTimeDLG").dialog("open");
		}else{
			alert(data.result);
		}
	});
	
}

/**
 * 修改机构工作时间
 * 
 * @param autoId
 */
function createModWorkingTimeDiag(autoId){
	if(null == autoId){
		alert("查看机构工作时间异常：设置机构工作时间数据异常");
		return;
	}
	$.post(ctx+"/orgWorkingTimeAction_findWorkingTime.action",{autoId:autoId},function(data){//新增
		if(null == data || null == data.result || null == data.orgWorkingTime){
			alert("查看机构工作时间异常：设置机构工作时间数据异常.");
		}else if("SUCCESS" == data.result){
			var orgWorkingTime = data.orgWorkingTime;
			$("#ModSigAutoId").val(orgWorkingTime.autoId)
			var  organization = Organization.getOrganization(orgWorkingTime.organizationSid);
			$("#ModSigOrganizationSid").val(orgWorkingTime.organizationSid)
			$("#ModSigOrganizationSid").val(orgWorkingTime.organizationSid)
			$("#ModSigOrganizationSid_Item").val(organization.organizationName)
			 $("#ModSigmonStartTime").val(orgWorkingTime.monStartTime);
			$("#ModSigmonEndTime").val(orgWorkingTime.monEndTime);
			$("#ModSigtuesStartTime").val(orgWorkingTime.tuesStartTime);
			$("#ModSigtuesEndTime").val(orgWorkingTime.tuesEndTime);
			$("#ModSigwedStartTime").val(orgWorkingTime.wedStartTime);
			$("#ModSigwedEndTime").val(orgWorkingTime.wedEndTime);
			$("#ModSigthursStartTime").val(orgWorkingTime.thursStartTime);
			$("#ModSigthursEndTime").val(orgWorkingTime.thursEndTime);
			$("#ModSigfridStartTime").val(orgWorkingTime.tridStartTime);
			$("#ModSigfridEndTime").val(orgWorkingTime.tridEndTime);
			$("#ModSigsatStartTime").val(orgWorkingTime.satStartTime);
			$("#ModSigsatEndTime").val(orgWorkingTime.satEndTime);
			$("#ModSigsunStartTime").val(orgWorkingTime.sunStartTime);
			$("#ModSigsunEndTime").val(orgWorkingTime.sunEndTime);
			$("#MODSIGWorkingTimeDLG").dialog("open");
		}else{
			alert(data.result);
		}
	});
	
}

/**
 * 清除机构工作时间
 * 
 * @param autoId
 */
function createClearWorkingTimeDiag(autoId){
	if(confirm("机构工作时间清除完成后，对应机构下的设备都不可使用。\n\n确认要清除机构工作时间吗？")){
		$.post(ctx+"/orgWorkingTimeAction_clearSigWorkingTime.action",{autoId:autoId},function(data){//新增
			if(null == data || null == data.result){
				alert("清除机构工作时间异常：清除机构工作时间过程出现数据异常.");
			}else if("SUCCESS" == data.result){
				$("#orgWorkingTimeList").jqGrid("search", "#WorkingTimeForm");
			}else{
				alert(data.result);
			}
		});
	}
}

/**
 * 设置机构工作时间对话框
 * 
 */
function createAddWorkingTimeDLG(){
	$("#addOrganizationSid_Item").val(top.loginPeopleInfo.orgName+"("+top.loginPeopleInfo.orgNo+")");
	$("#addOrganizationSid").val(top.loginPeopleInfo.orgSid);
	$("#AddWorkingTimeOrgItemList").jqGrid("search", "#orgFormItem");
	$("#AddWorkingTimeDLG").dialog("open");
}


/**
 * 批量修改机构工作时间对话框
 * 
 */
function createModWorkingTimeDLG(){
	var checkedVals = new Array();
	var orgWorkingTimes  = $("#orgWorkingTimeList").jqGrid("getGridParam","selarrrow");
	if(orgWorkingTimes.length == 0) {
		alert("修改机构工作时间异常：请选择需要修改的机构工作时间。");
		return;
	}
	$.each(orgWorkingTimes, function(i, orgWorkingTime) {
		if(orgWorkingTime != "") {
			checkedVals.push( $("#orgWorkingTimeList").jqGrid("getData", orgWorkingTime).organizationSid);
		}
	});
	
	if(checkedVals.length < 1){
		alert("修改机构工作时间异常：请选择需要修改的机构工作时间。");
		return;
	}
	
	$("#modOrganizationSids").val(checkedVals);
	$("#ModWorkingTimeOrgItemList").jqGrid("search", "#ModOrgFormItem");
	$("#ModWorkingTimeDLG").dialog("open");
}

/**
 * 下级机构查询
 */
function checkChildOrganizations(){
	$("#AddWorkingTimeOrgItemList").jqGrid("search", "#orgFormItem");
}

/**
 * 清除机构工作时间对话框
 * 
 */
function createClearWorkingTimeDLG(){
	var checkedVals = new Array();
	$(":checkbox[name=orgWorkCHKBOX][checked]").each(function(){
		checkedVals.push($(this).val());
	});
	if(checkedVals.length < 1){
		alert("清除机构工作时间异常：请选择需要清除的机构工作时间。");
		return;
	}
	if(confirm("机构工作时间清除完成后，对应机构下的设备都不可使用。\n\n确认要清除机构工作时间吗？")){
		$.post(ctx+"/orgWorkingTimeAction_clearWorkingTime.action",{organizationSids:checkedVals.toString()},function(data){//新增
			if(null == data || null == data.result){
				alert("清除机构工作时间异常：清除机构工作时间过程出现数据异常.");
			}else if("SUCCESS" == data.result){
				$("#orgWorkingTimeList").jqGrid("search", "#WorkingTimeForm");
			}else{
				alert(data.result);
			}
		});
	}
}

/**
 * 表单查询
 */
function queryWorkingTimeForTerm(){
	$("#orgWorkingTimeList").jqGrid("search", "#WorkingTimeForm");
}

/**
 * 清除表单
 */
function clearWorkingTimeForTerm(){
	$("#WorkingTimeForm")[0].reset(); //查询表单
}

/**
 * 选择机构
 */
function checkOrganizationItem(organizationNo){
	var organizationSid = top.loginPeopleInfo.orgSid;
	$("#organizationSid_Item").radioOrgTree(true,organizationSid,0,false,function(event, treeId, treeNode){
		if(treeNode){
			$("#"+organizationNo+"_Item").val(treeNode.organizationName+"("+treeNode.organizationNo+")");
			$("#"+organizationNo).val(treeNode.sid);
		}
	});
}