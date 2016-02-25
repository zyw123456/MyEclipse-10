function show() {
	var appcod = $("#appcode").val();
	var data = {
			frontObjStr : JSON.stringify({
				appcod:appcod
			})
	};
	$.ajax({
		url : "/windforce/appManagerAction.action",
		type : "post",
		data : data,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		dataType : "json",
		success : function(data, textStatus) {
			if (data != "") {
				if (data.frontObjStr != null) {
//					console.log(data.frontObjStr);
					var obj = JSON.parse(data.frontObjStr);
					var showStr = "";
					for(var i=0;i<obj.trns.length;i++){
						showStr += obj.trns[i].TRNCOD +" "+ obj.trns[i].TRNNAM+" ";
					}
					$("#trns").val(showStr);
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

$(document).ready(function() {
	$("#test").click(function(){
		show();
	});
});