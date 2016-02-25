var menuObj ={
		firstMenu:[],//一级菜单
		secondMenu:[],//二级菜单
		thirdMenu:[]//三级菜单
};

function parseNaviMenuToObj(naviMenu){
	for(var i=0;i<naviMenu.length;i++){
		if(1 == naviMenu[i].moduleLevel){
			menuObj.firstMenu.push(naviMenu[i]);
		}else if(2 == naviMenu[i].moduleLevel){
			menuObj.secondMenu.push(naviMenu[i]);
		}else if(3 == naviMenu[i].moduleLevel){
			menuObj.thirdMenu.push(naviMenu[i]);
		}
	}
	return menuObj;
}

function bindDivStyle(){
	
	$("li", window.parent.left.document).unbind("hover");
	$("li", window.parent.left.document).bind({
		mouseenter : function(e){
			$(this).addClass("active");
		},
		mouseleave : function(e){
			$(this).removeClass("active");
		}
	});
}

//修改默认密码
function changeDefaultPassword(sid) {
	showPage_lockPage("windforce/dk/modifyDefualtPwd.jsp?userSid=" + sid, "", "你的密码为默认密码，请修改!", 260, 132, null,
			function() {
				//去除非法退出的效果
				window.location.href = "j_spring_security_logout";
			});
}
