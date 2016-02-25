function searchProductList() {	
	$.ajax({
		url : "/windforce/loadPrdcod.action",
		type : "post",
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		dataType : "json",
		success : function(data, textStatus) {	
			if (data != "") {
				if (data.list != null) {
					var obj = data.list; 
					var showStr = "";
					for(var i=1;i<=obj.length;i++){
						 var str=obj; 
						showStr += 
						" <input type='radio' checked='checked' id="+str[i-1][1]+" name='prdcod'  value="+str[i-1][0]+" /><label for="+str[i-1][1]+">"+str[i-1][1]+ "</label>&nbsp;&nbsp;";																
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
}
