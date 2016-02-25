$(document).ready(function() {
	var myTable = $('#myTable').dataTable($.extend({
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
		//取消默认排序查询,否则复选框一列会出现小箭头
		"order": [],
        //TODO 对对应列所需要呈现的对象属性进行映射
		"columns": [
            CONSTANT.DATA_TABLES.CELL_CHECKBOX,
            {"data": "name"},
            {"data": "position"},
            {
            	"data": "blood",
            	"orderable" : false,//不排序
            	"width" : "80px",
				"render" : function(data,type, row, meta) {
					console.log(data);
					return '<i class="fa fa-male"></i> '+(data=="1"?"A血型":"B血型");
				}
            },
            {"data" : "date"},
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
        	$('td', row).eq(3).addClass(data.date?"text-success":"text-error");
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
	
	//搜索
	search = function(){
		myTable.ajax.reload();
	};
	
	//30秒表格数据一刷新
//	setInterval( function () {
//		myTable.ajax.reload();
//	}, 30000 );
});

function getQueryCondition(data){
	var param = {};
	//查询次数的标识符
	param.draw = data.draw;
	//自行处理排序参数  单列排序  TODO 
	if (data.order&&data.order.length&&data.order[0]) {
		switch (data.order[0].column) {
		case 1:
			param.orderColumn = "name";
			break;
		case 2:
			param.orderColumn = "position";
			break;
		case 3:
			param.orderColumn = "blood";
			break;
		case 4:
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