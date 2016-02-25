$(function(){ 
	findMonitorNameList('');
	//findWCPrdtrnList();
})
//查询监控人员名单
function findMonitorNameList(loantyp){
      var str=document.getElementsByName("trncod");
      var objarray=str.length;
      var trncod="";
      for (i=0;i<objarray;i++)
      {
        if(str[i].checked == true)
        {
        	trncod+=str[i].value+",";
        }
      }
      trncod=trncod.substring(0,trncod.length-1);
      if (typeof(loantyp) == "[object HTMLSelectElement]") { 
    	  loantyp = "";
	  }  
	var data = {
			prsnnam:$("#prsnnam").val(),
			prsncod:$("#prsncod").val(),
			loansrtdte:$("#loansrtdte").val(),
			loanenddte:$("#loanenddte").val(),
			trncod:trncod,
			loantyp:loantyp
	};
	$.ajax({
		url : "/windforce/findMonitorNameList.action",
		type : "post",
		data : data,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		dataType : "json",
		success : function(data, textStatus) {			
			if (data != "") {
				if (data.list != null) {
					var dl = data.list[0];
					var showStr = "<tr><td>姓名</td><td>身份证号</td><td>监控区间</td><td><select onchange='findMonitorNameList(this.value);' id='loadtyp'><option value=''>贷款类型</option><option value='0'>消费贷款</option><option value='1'>汽车贷款</option><option value='2'>购房贷款</option></select></td><td>监控模块</td><td>预警次数</td><td>操作</td></tr>";
					for(var i=0;i<data.list.length;i++){
						var dataList = data.list[i];
						showStr += " <tr><td>"+dataList[0]+"</td><td>"+dataList[1]+"</td><td>"+dataList[2]+"</td><td>"+dataList[3]+"</td><td>"+dataList[7]+"</td><td>"+dataList[4]+"</td><td>" +
								"<a href='sinoway/wrn/wrnMonitorEdit.jsp?id="+dataList[5]+"&prdcod="+dataList[6]+"&prsnnam="+dataList[0]+"&prsncod="+dataList[1]+"' >修改</a>&nbsp;&nbsp;" +
								"<a href='sinoway/wrn/wrnMonitorStop.jsp?id="+dataList[5]+"&prsncod="+dataList[1]+"&prsnnam="+dataList[0]+"&loansrtdte="+dataList[2]+"&loantyp="+dataList[3]+"&prdnam="+dataList[7]+"&prdcod="+dataList[6]+"' >终止</a></td></tr>";
					}
					$("#tabfl").html(showStr);
					$("#loadtyp").val(loantyp);
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
//查询模板原交易
function findWCPrdtrnList(){

	$.ajax({
		url : "/windforce/findWCPrdtrnList.action",
		type : "post",
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		dataType : "json",
		success : function(monitordata, textStatus) {			
			if (monitordata != "") {
				if (monitordata.list != null) { 
				var monitorStr = "";
					for(var i=0;i<monitordata.list.length;i++){ 
						var monitordataList = monitordata.list[i];
						monitorStr += " <input name='trncod' type='checkbox' value="+monitordataList[0]+" />"+monitordataList[1];
					}
					$("#jkmb").html(monitorStr);
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