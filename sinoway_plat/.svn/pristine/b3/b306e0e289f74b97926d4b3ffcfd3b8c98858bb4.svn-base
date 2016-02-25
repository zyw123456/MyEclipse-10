$(document).ready(function(){
	searchTrnNameList();
})

function searchWrnList(current,orderby,loantype) {
	currentPage = current;
	var prsnnam = document.getElementById("prsnnam").value;
	var prsncod = document.getElementById("prsncod").value;
	var warntim = document.getElementById("warntim").value;
	var olist = document.getElementsByName("trnnam");
	var trnnam = "";
	for ( var i = 0; i < olist.length; i++) {
		if (olist[i].checked) {
			trnnam = trnnam + olist[i].value + ",";
		}
	}
	trnnam=trnnam.substring(0,trnnam.length-1);
	$.ajax({
				url : '/windforce/searchAbnormalWarns.action',
				data : {'prsnnam':prsnnam,'prsncod':prsncod,'warntim':warntim,'trnnam':trnnam,'currentPage':currentPage,'recordCount':recordCount,'pageSize':pageSize,'orderby':orderby,'loantype':loantype},
				type : 'post',
				async : true,
				dataType : "json",
				success : function(data) {
					var showStr = "<tr style='font-weight: bold;'><td>姓名</td><td>身份证号</td><td>预警时间 <span style='font-size:10px;' onclick='javascript:searchWrnList("+currentPage+",1"+","+loantype+");'>正序</span>/<span style='font-size:10px;' onclick='javascript:searchWrnList("+currentPage+",2"+","+loantype+");'>倒序</span></td>"
					+"<td><select id='loantype' onchange='javascript:searchLoanList("+currentPage+","+orderby+");'>"
					+"<option value='-1'>贷款类型</option>"
					+"<option value='0'>消费类贷款</option>"
					+"<option value='1'>汽车贷款</option>"
					+"<option value='2'>购房贷款</option></td><td>监控模块</td><td>操作</td></tr>";
					
					currentPage = data.pageModel.currentPage;
					recordCount = data.pageModel.recordCount;
					pageCount = data.pageModel.pageCount;
					if (data != "" && data != null) {
						if(data.list!=null&&data.list.length!= 0){
							for ( var i = 0; i < data.list.length; i++) {
								var dataList = data.list[i];
								var loantypestr="";
								if(dataList[6]=="0")
									loantypestr="消费类贷款";
								if(dataList[6]=="1")
									loantypestr="汽车贷款";
								if(dataList[6]=="2")
									loantypestr="购房贷款";
								showStr += "<tr><td>"+dataList[2]+"</td><td>"+dataList[3]+"</td><td>"+dataList[4]+dataList[5]+"</td><td>"+loantypestr+"</td><td>"+dataList[7]+"</td><td onclick='javascript:searchWarnDetail("+dataList[1]+");'>查看</td></tr>";
							}
						}
					}
					showStr += "<tr><td width='100%' bgcolor='#eeeeee' colspan='6' align='center'> "
					+ "记录总数"+recordCount+"条 当前页/总页数"+ currentPage
					+ "/"+pageCount+"  每页显示"+pageSize+"条 "
					+ "<a onclick='searchWrnList(1,"+orderby+","+loantype+");'>首页</a> "
					+ "<a onclick='searchWrnList("+(currentPage - 1)+","+orderby+","+loantype+");'>上页</a> "
					+ "<a onclick='searchWrnList("+(currentPage + 1)+","+orderby+","+loantype+");'>下页</a> "
					+ "<a onclick='searchWrnList("+pageCount+","+orderby+","+loantype+");'>末页</a> "
					+ "</td></tr>"
					$("#resultTable").html(showStr);
					document.getElementById("loantype").value =loantype;
				},
				error : function(err) {
					alert("err:" + err);
				}
			});
}

function searchTrnNameList() {
	$.ajax({
				url : '/windforce/queryTrnNameList.action',
				data : {},
				type : 'post',
				async : true,
				dataType : "json",
				success : function(data) {
					var showStr = "<span style='font-weight:bold;'>监控模块</span>";
					if (data != "" && data != null) {
						if (data.list != null && data.list.length != 0) {
							for ( var i = 0; i < data.list.length; i++) {
								var dataList = data.list[i];
								showStr+= "<span><input type='checkbox' name='trnnam' value='"+dataList[1]+"'>"+dataList[0]+"</span>";
							}
						}
					}
					$("#trnList").html(showStr);
				},
				error : function(err) {
					alert("err:" + err);
				}
			});
}

function searchLoanList(currentPage,orderby){
	var obj = document.getElementById("loantype"); 
	var ltvalue = $('#loantype option:selected').val();
	searchWrnList(currentPage,orderby,ltvalue);
}

function searchWarnDetail(warnid){
	//url= <=%basePath%>+"wrn/wrnDetail.jsp?warnid="+warnid;\
	urls = "http://127.0.0.1:8080/windforce/sinoway/wrn/";
	url =urls + "wrnDetail.jsp?warnid="+warnid;
	var awidth=600;
	var aheight=500;
	var atop=(screen.availHeight - aheight)/2;
	var aleft=(screen.availWidth - awidth)/2;
	var param0 = "scrollbars=yes,status=0,menubar=0,resizable=2,location=0"; 
	var params = "top=" + atop + ",left=" + aleft + ",width=" + awidth + ",height=" + aheight + "," + param0 ;
	win=window.open(url,"wrnDetail",params);
	win.focus();
}