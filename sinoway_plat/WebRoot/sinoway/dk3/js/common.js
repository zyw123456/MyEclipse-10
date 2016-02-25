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

