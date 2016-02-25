$(document).ready(function() {
	parseNaviMenuToObj(naviMenu);
});

function secondMenuClick(n,currA){
	if(menuObj.secondMenu[n].id=="CBB9A3ECE4D7491DAAAA171F507B64AC"){
		$("#antiFraudCloud",window.parent.bottom.document).hide();
		$("#strategyManage",window.parent.bottom.document).show();
	}
	if(menuObj.secondMenu[n].id=="A6D36C5D8458494AA6D033C3D52ED1C2"){
		$("#antiFraudCloud",window.parent.bottom.document).show();
		$("#strategyManage",window.parent.bottom.document).hide();
	}
	
	if(menuObj.secondMenu[n].id=="D46E94CBCA8044348D15F3E9E72CCC4C"){
		$("#personalRpt",window.parent.bottom.document).hide();
		$("#strategyManage",window.parent.bottom.document).show();
	}
	if(menuObj.secondMenu[n].id=="C07338EC52D146ACB1522A8872574AE7"){
		$("#personalRpt",window.parent.bottom.document).show();
		$("#strategyManage",window.parent.bottom.document).hide();
	}
	window.parent.right.location.href=menuObj.secondMenu[n].targetUrl;
}

function thirdMenuClick(n){
	
}

//登出系统
function logout() {
	if (window.confirm('您确认退出系统?', '提示信息')) {
		$.getJSON(ctx + "/logoutCheck.action?_t=" + new Date().getTime(), function(data) {// 检查当前用户是否可以正常退出
			if (data.logoutRule == "agree") {// 同意，直接退出！
				window.parent.parent.parent.onbeforeunload =  function() {
					
				};
				window.parent.parent.parent.location.href = ctx + "/j_spring_security_logout";
			} else if (data.logoutRule == "warn") {// 警告，提示用户是否退出
				if (window.confirm(data.msg, '提示信息')) {
					window.parent.parent.parent.onbeforeunload =  function() {
						
					};
					window.parent.parent.parent.location.href = ctx + "/j_spring_security_logout";
				}
			} else {// 拒绝退出，给出拒绝理由
				alert(data.msg);
			}
		});
	}
}


