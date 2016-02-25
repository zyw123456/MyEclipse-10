//修改监控人员信息
function updaWarMonitor(){

		var id = $("#id").val();
		var prdcod = $('#mb input[name="prdcod"]:checked').val();
		var prsncod = $("#prsncod").val();
		var oldprdcod = $("#oldprdcod").val();
		var data = {
				id :id,
				prdcod: prdcod,
				prdnam:$("#"+prdcod+"").val(),
				prsncod:prsncod,
				oldprdcod:oldprdcod
		};
		$.ajax({
			url : "/windforce/updateMonitorName.action",
			type : "post",
			data : data,
			beforeSend : function(XMLHttpRequest) {
				XMLHttpRequest.setRequestHeader("RequestType", "ajax");
			},
			async : true,
			dataType : "json",
			success : function(data, textStatus) {	
				var retMsg = data.retMsg;
				if (data.retMsg == "1") {
					alert("修改成功！");
					location.href="/windforce/findWCPrdtrnList.action";
				}else{
					alert(retMsg);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("加载信息erro:" + textStatus + errorThrown);
			},
			complete : function(XMLHttpRequest, textStatus) {
			}
		});
}

//查询产品信息
$(function(){
	var data = {
			wfDate : JSON.stringify({
				orgno:"1"
			})
			
	};
	$.ajax({
		url : "/windforce/findAction.action",
		type : "post",
		data : data,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		dataType : "json",
		success : function(data, textStatus) {					
			if (data != "") {
				if (data.wfDate != null) {
					var obj = JSON.parse(data.wfDate); 
					var showStr = "";
					var prdcod = $("#oldprdcod").val();
					for(var i=0;i<obj.trns.length;i++){
						 var str=obj.trns[i]; 
						 if(str[0] == prdcod){
							 showStr +=  str[1]+" <input type='radio' checked='checked'  id='prdcod' name='prdcod'  value="+str[0]+" />" + 
							   " <input type='hidden' id="+str[0]+"  value='"+ str[1]+"' /> ";
						 }else{
							 showStr +=  str[1]+" <input type='radio'  id='prdcod' name='prdcod'  value="+str[0]+" />" + 
							   " <input type='hidden' id="+str[0]+"  value='"+ str[1]+"' /> ";
						 }
					}
					$("#mb").html(showStr);
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("加载信息erro:" + textStatus + errorThrown);
		},
		complete : function(XMLHttpRequest, textStatus) {
		}
	});
})