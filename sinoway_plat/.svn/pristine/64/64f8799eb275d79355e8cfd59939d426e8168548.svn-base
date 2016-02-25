function accAdd(){
		var telno = $("#phnone").val();//联系人电话
		var compno = $("#compno").val();//身份证号
		var compnam = $("#compnam").val();//公司名称
		var prsnnam = $("#prsnnam").val();//联系人姓名
		var strdte =$("#strdte").val();   //平台使用期限(开始时间)
		var enddte =$("#enddte").val();   //平台使用期限(结束时间)
		//手机号验证
		var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
		if (!reg.test(telno)) {
			  alert("手机号格式不正确！");
			  return;
		}
		//姓名非空
		if(prsnnam == ""){
			alert("请输入姓名！");
			return;
		}
}

function testAjax(){
	
	$.ajax({
		url : "/windforce/testajax.action",
		type : "post",
		data : {sid:"10000"},
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		dataType : "json",
		success : function(data, textStatus) {		
		
			if (data != null) {
				var obj = JSON.parse(data);
				alert(obj.length);
				var tbBody = "";
				for(var i=0;i<obj.length;i++){
					tbBody += "<tr align='center' height='30' class='person'><td>" + obj[i].compno+ "</td><td>"+obj[i].compnam
					+ "</td><td>"+obj[i].compaddr+"</td><td><a href=''>查看</a>&nbsp;" +
							"<a href=''>修改</a>&nbsp;" +
							"<a href=''>删除</a></td></tr>";
				}
				$("tr").remove(".person");
				$("#mb").append(tbBody);
			}
		},	
		complete : function(XMLHttpRequest, textStatus) {
		}
	});
}