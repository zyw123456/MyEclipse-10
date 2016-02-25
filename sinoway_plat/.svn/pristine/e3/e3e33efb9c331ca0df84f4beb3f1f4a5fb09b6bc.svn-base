$(document).ready(function() {
	console.log("进行布局...");
	var myTable = $('#myTable').dataTable({
		"processing" : false,	//隐藏加载提示,自行处理
		"searching" : false,	//禁用表格内搜索
		"lengthChange": false,	//是否允许改变每页显示数目
		"paging":false,//关闭分页
		"ordering": false,//关闭排序
		"columns": [
            {"data": "name"},
            {"data": "position"},
            {
            	"data": "blood",
            	"width" : "80px",
				"render" : function(data,type, row, meta) {
					console.log(data);
					return '<i class="fa fa-male"></i> '+(data=="1"?"A血型":"B血型");
				}
            },
            {
				"className" : "cell-operation",//样式
				"data": null,
				"defaultContent":"",//默认的内容
				"width" : "120px"
			}
        ],
		"serverSide": true,	//启用服务器模式
		"ajax" : function(data, callback, settings) {
			//封装请求参数
			var param = getQueryCondition(data);
			console.log(param);
			$.ajax({
		            type: "POST",
		            url: "/windforce/test.action",
		            data: {reqParams : JSON.stringify(param)},
		            dataType: "json",
		            success: function(data) {
		            	//将返回的字符串转为标志的json数据格式
		            	//var result = JSON.parse(data.resdata.replace(/'/g, '"'));
		            	//封装返回数据
		            	var returnData = {};
		            	returnData.draw = 1;//自行返回draw参数,最好由后台返回
		            	returnData.recordsTotal = 20;//总数
		            	returnData.recordsFiltered = 20;//总数
		            	//returnData.data = result.pageData;
		            	returnData= "{\"draw\":1,\"recordsTotal\":30,\"recordsFiltered\":30,\"data\":[{\"name\":\"Angelica\",\"position\":\"SystemArchitect\",\"blood\":\"1\",\"date\":\"9thOct09\"}]}";
		            	console.log(returnData);
		            	//调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
		            	//此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
		            	callback(JSON.parse(returnData));
		            },
		            error: function(XMLHttpRequest, textStatus, errorThrown) {
		            	console.log("error...");
		            }
		        });
		},
		 //对回调数据每一行进行的操作
        "createdRow": function ( row, data, index ) {
        	//行渲染回调,在这里可以对该行dom元素进行任何操作
            var btnEdit = $('<button type="button" class="btn btn-small btn-primary btn-edit">修改</button>');
            var btnDel = $('<button type="button" class="btn btn-small btn-danger btn-del">删除</button>');
            $('td', row).eq(3).append(btnEdit).append(btnDel);
        }
	}).api();	//是否允许改变每页显示数目

	function getQueryCondition(data){
		var param = {};
		//查询次数的标识符
		param.draw = data.draw;
		//自行处理排序参数  单列排序  TODO 
		if (data.order&&data.order.length&&data.order[0]) {
			switch (data.order[0].column) {
			case 0:
				param.orderColumn = "name";
				break;
			case 1:
				param.orderColumn = "position";
				break;
			case 2:
				param.orderColumn = "blood";
				break;
			case 3:
				param.orderColumn = "date";
				break;
			default:
				param.orderColumn = "name";
				break;
			}
			param.orderDir = data.order[0].dir;
		}
		
		//自行处理分页参数  分页页码和每页大小
		param.startIndex = data.start;
		param.pageSize = data.length;
		
		//自行处理 自定义搜索框的搜索条件和列头中下拉列表的条件
		param.searchParams = [];
		var sParam = {name:'name',value:'xiehao'};
		var sParam1 = {name:'sex',value:'男'};
		//下拉框
		var sParam2 = {name:'blood',value:$("#bloodType").val()}
		param.searchParams.push(sParam);
		param.searchParams.push(sParam1);
		param.searchParams.push(sParam2);
		
		return param;
	}

	
});