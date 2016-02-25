/**
 * 页面初始化
 */
$(document).ready(function() {
	//登录成功,默认展示首页
	$("#rightheight").load("sinoway/dk3/blank.jsp",function(){
		console.log("这是一个空白页面...");
	});
	//解析各级菜单
	parseNaviMenuToObj(naviMenu);
	//动态生成一级菜单
	generateFirstMenu();
	//控制左右高度一致
	configHeight();
	//注册点击事件
	$("li").click(
			function () {
				$("li").removeClass("active");
				$(this).addClass("active");
			}
	);

});
/**
 * 控制左右高度一致
 */
function configHeight(){
	var conheight =  $(window).height() - $(".top").height() - $(".foot").parent().height() - 40;
    $("#rightheight").css("min-height",conheight);
    $("#leftheight").css("min-height",conheight);
}
/**
 * 动态生成一级菜单
 */
function generateFirstMenu(){
	var html = '';
	if(null != menuObj&&null!=menuObj.firstMenu){
		for(var i=0;i<menuObj.firstMenu.length;i++){
			//如果是首页,添加选中样式
			if(menuObj.firstMenu[i].id =='19C561823349405389B751998A1C4D91'){
				html +="<li class='active' ><a href='javascript:void(0);' onclick ='firstMenuClick("+i+");'>"+menuObj.firstMenu[i].name+"</a></li>";
			}else{
				html += "<li ><a href='javascript:void(0);' onclick ='firstMenuClick("+i+");'>"+menuObj.firstMenu[i].name+"</a></li>";
			}
		}
		$("#firstMenu").html(html);
	}
}
/**
 * 点击一级菜单
 */
function firstMenuClick(n){
	var childrenId = menuObj.firstMenu[n].children;
	//如果是首页
	if(menuObj.firstMenu[n].id=='19C561823349405389B751998A1C4D91'){
		
	}else{
		var html = "";
		for(var i=0;i<childrenId.length;i++){
			for(var j=0;j<menuObj.secondMenu.length;j++){
				if(childrenId[i]._reference == menuObj.secondMenu[j].id){
					if(menuObj.secondMenu[j].id == "A6D36C5D8458494AA6D033C3D52ED1C2"||menuObj.secondMenu[j].id=="C07338EC52D146ACB1522A8872574AE7"||menuObj.secondMenu[j].id=="32749A3B2B194011A717D6F9F1AA0B6F"){
						html += "<a  class='list-group-item' href='javascript:void(0);' onclick ='secondMenuClick("+j+")'>"+menuObj.secondMenu[j].name+"</a>";
					}else{
						html += "<a class='list-group-item' href='javascript:void(0);' onclick ='secondMenuClick("+j+")'>"+menuObj.secondMenu[j].name+"</a>";
					}
				}
			}
		}
		//进行二级菜单的展现
		$("#leftheight").html(html);	
	}
	//跳转页面
	$("#rightheight").load("sinoway/dk3/right3.jsp");
}

