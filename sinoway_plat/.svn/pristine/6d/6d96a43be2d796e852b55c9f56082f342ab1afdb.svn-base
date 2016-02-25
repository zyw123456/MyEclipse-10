$(document).ready(function() {
	parseNaviMenuToObj(naviMenu);
	//动态生成一级菜单
	var html = '';
	var topIndex = null;
	var clickFirst = null;
	var clickSecond = null;
	if(null != menuObj&&null!=menuObj.firstMenu){
		for(var i=0;i<menuObj.firstMenu.length;i++){
			if(menuObj.firstMenu[i].id =='19C561823349405389B751998A1C4D91'){
				topIndex = i;
				html +="<li class='active' id='first'><a href='javascript:void(0);' onclick ='firstMenuClick("+i+");showBottom("+i+");''>"+menuObj.firstMenu[i].name+"</a></li>";
			}else{
				html += "<li><a href='javascript:void(0);' onclick ='firstMenuClick("+i+");showBottom("+i+");''>"+menuObj.firstMenu[i].name+"</a></li>";
			}
		}
		$("#firstMenu").append(html);
	}
	//登录成功默认展示首页
	if(topIndex != null){
		firstMenuClick(topIndex);
	}
	$("li").click(
			function () {
				$("li[id=first]").removeClass("active");
				if(clickFirst != null){
					clickFirst.removeClass("active");
				}
				$(this).addClass("active");
				clickFirst = $(this);
			}
	);

});
/**
 * 动态展示底部元素
 * @param n
 */
function showBottom(n){
	$("#firstPage",window.parent.bottom.document).hide();
	$("#sysManage",window.parent.bottom.document).hide();
	$("#logManage",window.parent.bottom.document).hide();
	$("#strategyManage",window.parent.bottom.document).hide();
	$("#personalRpt",window.parent.bottom.document).hide();
	$("#antiFraudCloud",window.parent.bottom.document).hide();
	$("#dayWarnCloud",window.parent.bottom.document).hide();
	$("#accountManage",window.parent.bottom.document).hide();
	if(menuObj.firstMenu[n].id=='19C561823349405389B751998A1C4D91'){
		$("#firstPage",window.parent.bottom.document).show();
	}
	if(menuObj.firstMenu[n].id=='000000000system_manage_module_id'){
		$("#sysManage",window.parent.bottom.document).show();
	}
	if(menuObj.firstMenu[n].id=='000000000000log_manage_module_id'){
		$("#logManage",window.parent.bottom.document).show();
	}
	if(menuObj.firstMenu[n].id=='C906E75F07174D81BC9EF7A63C985615'){
		$("#strategyManage",window.parent.bottom.document).show();
	}
	if(menuObj.firstMenu[n].id=='74F943AF510A4E848DC9C56B775FBC7D'){
		$("#personalRpt",window.parent.bottom.document).show();
	}
	if(menuObj.firstMenu[n].id=='A996D7F10B0D42B6A33502F64C5C6695'){
		$("#antiFraudCloud",window.parent.bottom.document).show();
	}
	if(menuObj.firstMenu[n].id=='C6EB566AFA5E444EA2A366388D7AC456'){
		$("#dayWarnCloud",window.parent.bottom.document).show();
	}
	if(menuObj.firstMenu[n].id=='24CE05CCC09B4F3AA12FC78ABA5B6B57'){
		$("#accountManage",window.parent.bottom.document).show();
	}
}

function firstMenuClick(n){
	
	var childrenId = menuObj.firstMenu[n].children;
	//如果是首页
	if(menuObj.firstMenu[n].id=='19C561823349405389B751998A1C4D91'){
		$("#midFrame",window.parent.document).attr("cols","*,100%");
		window.parent.parent.parent.document.body.style.height="1140px";
	}else{
		$("#midFrame",window.parent.document).attr("cols","20%,*");
		window.parent.parent.parent.document.body.style.height="900px";
		var secondMenu = [];
		var html = "";
		for(var i=0;i<childrenId.length;i++){
			for(var j=0;j<menuObj.secondMenu.length;j++){
				if(childrenId[i]._reference == menuObj.secondMenu[j].id){
					if(menuObj.secondMenu[j].id == "A6D36C5D8458494AA6D033C3D52ED1C2"||menuObj.secondMenu[j].id=="C07338EC52D146ACB1522A8872574AE7"||menuObj.secondMenu[j].id=="32749A3B2B194011A717D6F9F1AA0B6F"){
						html += "<h2 ><a id='first' class='hactive' href='javascript:void(0);' onclick ='secondMenuClick("+j+")'>"+menuObj.secondMenu[j].name+"</a></h2>";
					}else{
						html += "<h2><a href='javascript:void(0);' onclick ='secondMenuClick("+j+")'>"+menuObj.secondMenu[j].name+"</a></h2>";
					}
				}
			}
		}
		//如果是账户管理
		if(menuObj.firstMenu[n].id=='24CE05CCC09B4F3AA12FC78ABA5B6B57'){
			//增加退出登录按钮
			var logout = '退出系统';
			html += "<h2><a href='javascript:void(0);' onclick ='logout()'>"+logout+"</a></h2>";
		}
		//进行二级菜单的展现
		$("#secondMenu",window.parent.left.document).html(html);
		bindDivStyle();
	}
	//跳转页面
	var url = menuObj.firstMenu[n].targetUrl;
	var endUrl = url.substr(url.length-4);
	if(endUrl == ".jsp"){	
		window.parent.right.location.href=menuObj.firstMenu[n].targetUrl;
	}else{
		window.parent.right.location.href="sinoway/dk/right.jsp";
	}
	}
function bindDivStyle(){
	var clickSecond = null;
	$("a", window.parent.left.document).unbind("click");
	$("a", window.parent.left.document).bind("click", function(e){
		$("a", window.parent.left.document).removeClass("hactive");
		if(clickSecond != null){
			clickSecond.removeClass("hactive");
		}
			$(this).addClass("hactive");
			clickSecond = $(this);
	});
	
}

