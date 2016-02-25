$(function (){
	//该方法获得了datatable的api并自定义了ajax请求
	g_userManage.tableUser = $('#myTable').dataTable($.extend({
		"ajax" : function(data, callback, settings) {
			//手动控制遮罩 避免用户多次点击引发的问题
			//$('#div-table-container').spinModal();
			//封装请求参数
			var param = g_userManage.getQueryCondition(data);
			console.log(param);
			$.ajax({
		            type: "POST",
		            url: "/windforce/test.action",
		            data: {test : JSON.stringify(param)},
		            dataType: "json",
		            success: function(result) {
		            	result = JSON.parse(result.returnMessage.replace(/'/g, '"'));
		            	//异常判断与处理
		            	if (result.errorCode) {
		            		//$.dialog.alert("查询失败。错误码："+result.errorCode);
		            		return;
		            	}
		            	//封装返回数据
		            	var returnData = {};
		            	returnData.draw = result.draw;//自行返回draw参数,最好由后台返回
		            	returnData.recordsTotal = result.total;
		            	returnData.recordsFiltered = result.total;
		            	returnData.data = result.pageData;
		            	//调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
		            	//此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
		            	callback(returnData);
		            },
		            error: function(XMLHttpRequest, textStatus, errorThrown) {
		            	//ajax请求错误...处理
		            	console.log("出现错误...");
		            }
		        });
		},
		"order": [],//取消默认排序查询,否则复选框一列会出现小箭头
        //对所有行的回调处理,以一行为操作单元
		"columns": [
            CONSTANT.DATA_TABLES.CELL_CHECKBOX,
            {"data": "name"},
            {"data": "position"},
			{
				"data" : "status",
				"width" : "80px",
				"render" : function(data,type, row, meta) {
					return '<i class="fa fa-male"></i> '+(data==1?"在线":"离线");
				}
			},
            {"data": "start_date"},
            {
				"className" : "cell-operation",
				"data": null,
				"defaultContent":"",
				"orderable" : false,
				"width" : "120px"
			}
        ],
        
        //对回调数据每一行进行的操作
        "createdRow": function ( row, data, index ) {
        	//行渲染回调,在这里可以对该行dom元素进行任何操作
        	$('td', row).eq(3).addClass(data.status?"text-success":"text-error");
            var btnEdit = $('<button type="button" class="btn btn-small btn-primary btn-edit">修改</button>');
            var btnDel = $('<button type="button" class="btn btn-small btn-danger btn-del">删除</button>');
            $('td', row).eq(5).append(btnEdit).append(btnDel);
        },
        //全局刻画完毕后执行的回调函数
        "drawCallback": function( settings ) {
        	//默认选中第一行
        	$("#myTable tbody tr").eq(0).click();
        }
	}, CONSTANT.DATA_TABLES.DEFAULT_OPTION)).api();//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象

	$("#btn-add").click(function(){
		g_userManage.addItemInit();
	});
	
	$("#btn-del").click(function(){
		var arrItemId = [];
        $("#myTable tbody :checkbox:checked").each(function(i) {
        	var item = g_userManage.tableUser.row($(this).closest('tr')).data();
        	arrItemId.push(item);
        });
		g_userManage.deleteItem(arrItemId);
	});
	
	$("#btn-simple-search").click(function(){
		g_userManage.fuzzySearch = true;
		//重新加载
		g_userManage.tableUser.ajax.reload();
	});
	
	$("#btn-advanced-search").click(function(){
		g_userManage.fuzzySearch = false;
		g_userManage.tableUser.ajax.reload();
	});
	
	$("#btn-save-add").click(function(){
		g_userManage.addItemSubmit();
	});
	
	$("#btn-save-edit").click(function(){
		g_userManage.editItemSubmit();
	});
	
	$("#myTable tbody").on("click","tr",function(event) {
		var targetNodeName = event.target.nodeName.toLowerCase();
		if (targetNodeName === "button"||targetNodeName === "input") {
			return;
		}
		$(this).addClass("active").siblings().removeClass("active");
		//获取该行对应的数据
		var item = g_userManage.tableUser.row($(this).closest('tr')).data();
		g_userManage.currentItem = item;
		g_userManage.showItemDetail(item);
    });
	
	$("#myTable").on("change",":checkbox[name='cb-check-all']",function() {
		$("#myTable :checkbox").prop("checked",$(this).prop("checked"));
    });
	
	$("#myTable tbody").on("change",":checkbox",function() {
		var checked = $("#myTable tbody :checkbox:checked");
		if (!checked.length) {
			$("#myTable :checkbox[name='cb-check-all']").prop("checked",false);
		}else if($("#myTable tbody :checkbox").length == checked.length) {
			$("#myTable :checkbox[name='cb-check-all']").prop("checked",true);
		}
    });
	
	$("#myTable").on("click",".btn-edit",function() {
        var item = g_userManage.tableUser.row($(this).closest('tr')).data();
//        $.dialog.tips('编辑单项数据:'+item.name);
		$(this).closest('tr').addClass("active").siblings().removeClass("active");
		g_userManage.currentItem = item;
		g_userManage.editItemInit(item);
    }).on("click",".btn-del",function() {
		var item = g_userManage.tableUser.row($(this).closest('tr')).data();
		$(this).closest('tr').addClass("active").siblings().removeClass("active");
		g_userManage.deleteItem([item]);
    });
	
	$("#toggle-advanced-search").click(function(){
		$("i",this).toggleClass("fa-angle-double-down fa-angle-double-up");
		$("#div-advanced-search").slideToggle("fast");
	});
	
	$("#btn-info-content-collapse").click(function(){
		$("i",this).toggleClass("fa-minus fa-plus");
		$("span",this).toggle();
		$("#user-view .info-content").slideToggle("fast");
	});
	
	$("#btn-view-edit").click(function(){
		g_userManage.editItemInit(g_userManage.currentItem);
	});
	
	$(".btn-cancel").click(function(){
		g_userManage.showItemDetail(g_userManage.currentItem);
	});
});

var g_userManage = {
	tableUser : null,
	currentItem : null,
	getQueryCondition : function(data) {
		var param = {};
		//自行处理排序参数  单列排序
		if (data.order&&data.order.length&&data.order[0]) {
			switch (data.order[0].column) {
			case 1:
				param.orderColumn = "name";
				break;
			case 2:
				param.orderColumn = "position";
				break;
			case 3:
				param.orderColumn = "status";
				break;
			case 4:
				param.orderColumn = "start_date";
				break;
			default:
				param.orderColumn = "name";
				break;
			}
			param.orderDir = data.order[0].dir;
		}
		//自行处理查询参数 对param进行封装
		param.fuzzySearch = g_userManage.fuzzySearch;
		if (g_userManage.fuzzySearch) {
			param.fuzzy = $("#fuzzy-search").val();
		}else{
			param.name = $("#name-search").val();
			param.position = $("#position-search").val();
			param.office = $("#office-search").val();
			param.extn = $("#extn-search").val();
			param.status = $("#status-search").val();
			param.role = $("#role-search").val();
		}
		//自行处理分页参数  分页页码和每页大小
		param.startIndex = data.start;
		param.pageSize = data.length;
		
		return param;
	},
	deleteItem : function(selectedItems) {
		var message;
		if (selectedItems&&selectedItems.length) {
			if (selectedItems.length == 1) {
				message = "确定要删除 '"+selectedItems[0].name+"' 吗?";
				
			}else{
				message = "确定要删除选中的"+selectedItems.length+"项记录吗?";
			}
			$.dialog.confirmDanger(message, function(){
				$.dialog.tips('执行删除操作');
			});
		}else{
			$.dialog.tips('请先选中要操作的行');
		}
	}
};