function findTrncodByAppcod() {
	alert("开始");
	var appcod = $("#appcode").val();
	alert(appcod);
	/*var data = {
			WfcgrefApptrn : JSON.stringify({
				appcode:appcod
			})
	};
	alert(data);*/
	$.ajax({
		url : "/windforce/appfindTrncodAction.action",
		type : "post",
		//data : data,
		data :{ appcode: appcod},
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		dataType : "json",
		success : function(data, textStatus) {
			alert("ok");
			if (data != " ") {
				if (data.list != null) {
				//if (data.list.size() >0) {
//					console.log(data.frontObjStr);\
					alert(data.list);
					var obj = data.list;
					var showStr = "<tr><td>应用编码</td><td>原交易编码</td></tr>";
					for(var i=0;i<obj.length;i++){
						showStr += " <tr><td>"+obj[i].APPCOD+"</td><td>"+obj[i].TRNCOD+"</td><tr>"
						alert(obj[i].APPCOD);
						alert(obj[i].TRNCOD);
					}
					$("#table").html(showStr);
				}else{					
					alert("无需求所需数据");
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

//$(document).ready(function() {
//	$("#test").click(function(){
//		show();
//	});
//});