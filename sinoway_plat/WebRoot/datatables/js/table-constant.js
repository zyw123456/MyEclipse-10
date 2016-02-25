/*常量*/
var CONSTANT = {
		DATA_TABLES : {
			DEFAULT_OPTION : { //DataTables初始化选项
				"language": {
		            "lengthMenu": "_MENU_ 条记录每页",
		            "zeroRecords": "没有找到记录",
		            "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
		            "infoEmpty": "无记录",
		            "infoFiltered": "(从 _MAX_ 条记录过滤)",
		            "paginate": {
		                "previous": " < ",
		                "next": ">"
		            }
		        },
		        "processing" : false,	//隐藏加载提示,自行处理
		        "serverSide": true,		//启用服务器模式
		        "searching" : false,	//禁用表格内搜索
		        "lengthChange": false	//是否允许改变每页显示数目
			},
			CELL_CHECKBOX : {	//复选框单元格
				"className" : "cell-checkbox",
				"orderable" : false,
				"data": null,
				"width" : "40px",
				"render" : function(data, type, row, meta) {
					return '<input type="checkbox" >';
				}
			}
		}
};