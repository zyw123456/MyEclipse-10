//终止监控人员
function updaWarMonitor(){

		var id = $("#id").val();
		var data = {
				id :id,
				prsncod:$("#prsncod").val(),
				oldprdcod:$("#prdcod").val(),
				prdnam:$("#prsnnam").val()
		};
		$.ajax({
			url : "/windforce/stopMonitorName.action",
			type : "post",
			data : data,
			beforeSend : function(XMLHttpRequest) {
				XMLHttpRequest.setRequestHeader("RequestType", "ajax");
			},
			async : true,
			dataType : "json",
			success : function(data, textStatus) {			
				if (data != "") {
					alert("终止成功！");
					location.href="/windforce/findWCPrdtrnList.action";
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("加载信息erro:" + textStatus + errorThrown);
			},
			complete : function(XMLHttpRequest, textStatus) {
			}
		});
}

