/**
 * @author chenhuang
 * @date 2014-11-14
 */

// 主题名称
var theme = "default";
var isNoTask = false;// 是否屏蔽任务菜单
var headHeight = 40;
var footHeight = 30;
var bodyWidth = 0;
var bodyHeight = 0;

$(document).ready(function() {
	bodyWidth = getVisiableAreaWidthForDesk();
	bodyHeight = getVisiableAreaHeightForDesk();
	$("#desktop").css("width",bodyWidth+"px");
	$("#desktop").css("height",bodyHeight+"px");
	//实现桌面布局样式
	myLayout = $('#desktop').layout({
		west__size:190,	//定义west区域宽度
		north_size:35,
		south_size:15,
		fit:true,
		togglerTip_open:"关闭",  
	    togglerTip_closed:"打开",
	    north__closable:false,
	    south__closable:false
		//west__closable:false//可以被关闭
	});
	
	$("body").attr("role","application");
	// 初始化等待界面(已取消原有动画效果)
	$("#preLoader").hide();
	initImageData();
	initNaviMenu();// 初始化系统菜单

	// 是否屏蔽任务菜单
	if (isBlockTaskMenu == "unblock") {
		// 不屏蔽
		initTreeHeight(isNoTask);
		// 初始化任务菜单
		refreshTaskMenu(taskMenu);

		// 刷新任务菜单
		autoSearchTaskData();
		// 加入定时刷新功能
		window.setInterval(autoSearchTaskData, 10000);
	} else {
		// 屏蔽
		isNoTask = true;
		document.getElementById("product_taks_menu").style.display = "none";
		initTreeHeight(isNoTask);
	}

	// 屏蔽修改密码功能
	if (blockModifyPwdFun) {
		document.getElementById("block_modify_pwd_fun").style.display = "none";
	}
	
	// 检查是否需授权
	if(needAuthorization=="true"){
		checkAuthorization();
	}else{
		// 检查密码是否过期或存在一般锁屏操作
		if (needChangePassword == 1) {
			changeDefaultPassword(currPeopleSid);
		} else if (pwdGoingExpiredReminder < 0) {
			changeOutDatePassword(currPeopleSid);
		} else if (pwdGoingExpiredReminder != 0) {
			wfAlert("您的密码将于" + pwdGoingExpiredReminder + "天后过期，请及时修改!");
		}
	}
	if(_lockScreen){
		lockIndexPage();
	}
	// 登录检查
	loginCheck();
});

var isLockScreen = false;
var windowWidth = $(window).width();
var windowHeight = $(window).height();
/**
 * 当窗口缩放时触发
 */
$(window).resize(function() {
	var windowWidth_now = $(window).width();
	var windowHeight_now = $(window).height();
	if(windowWidth_now!=windowWidth || windowHeight_now!=windowHeight){
		windowWidth = windowWidth_now;
		windowHeight = windowHeight_now;
		var wfbgDiv = document.getElementById("wfbgDiv");
		var wfbgDiv_process = document.getElementById("wfbgDiv_process");
		var wfbgDiv_showPage = document.getElementById("wfbgDiv_showPage");
		var wfbgDiv_lockPage = document.getElementById("wfbgDiv_lockPage");
		if(wfbgDiv==null && wfbgDiv_process==null 
				&& wfbgDiv_showPage==null && wfbgDiv_lockPage==null && isLockScreen!=true){
			resizeForDesktop();
		}else{
			isLockScreen = true;
		}
	}
});

function resizeForDesktop(){
	bodyWidth = getVisiableAreaWidthForDesk();
	bodyHeight = getVisiableAreaHeightForDesk();
	$("#desktop").css("width", bodyWidth+"px");
	$("#desktop").css("height",bodyHeight+"px");
	resetTreeHeight();
	isLockScreen = false;
}

/**
 * 初始化菜单树高度
 */
function initTreeHeight(isNoTaskMenu) {
	$height = bodyHeight;
	$queryDiv = $('div.querydiv');
	$queryDiv.show();
	if (isNoTaskMenu) {
		$('#product_biz_tree').css('height', $height - 120 + 'px');
	} else {
		$('#product_biz_tree').css('height', $height - 335 + 'px');
	}
}

function initImageData() {
	$('#task_div_switch').attr("src", ctx + '/windforce/common/theme/' + theme + '/image/arrow_down.gif');
	$('#desktop_logo').attr("src", ctx + '/windforce/common/theme/' + theme + '/image/deskLogo.jpg');
	$('#desktop_home_page').attr("src", ctx + '/windforce/common/theme/' + theme + '/image/homePage.png');
	$('#desktop_logout').attr("src", ctx + '/windforce/common/theme/' + theme + '/image/logout.png');
	$('#desktop_change_pwd').attr("src", ctx + '/windforce/common/theme/' + theme + '/image/changePwd.png');
	$('#desktop_lock').attr("src", ctx + '/windforce/common/theme/' + theme + '/image/lock.png');
}

/**
 * 设置任务栏高度
 */
function setTaskHeight() {
	$taskDiv = $('div.taskdiv');
	if ($taskDiv.is(':visible')) {
		$('#product_biz_tree').css('height', $height - 155 + 'px');
		$('#task_div_switch').attr("src", ctx + '/windforce/common/theme/' + theme + '/image/arrow_up.gif');
		$taskDiv.hide();
	} else {
		$('#product_biz_tree').css('height', $height - 335 + 'px');
		$('#task_div_switch').attr("src", ctx + '/windforce/common/theme/' + theme + '/image/arrow_down.gif');
		$taskDiv.show();
	}
}

/**
 * 重置树高度
 */
function resetTreeHeight() {
	$height = bodyHeight;
	if (isNoTask) {
		$('#product_biz_tree').css('height', $height - 120 + 'px');
	} else {
		$taskDiv = $('div.taskdiv');
		if ($taskDiv.is(':visible')) {
			$('#product_biz_tree').css('height', $height - 335 + 'px');
		} else {
			$('#product_biz_tree').css('height', $height - 155 + 'px');
		}
	}
}

/**
 * 获取浏览器可见高度
 */
function getWindowHeight() {
	var winH = $(document.body).height();
	return winH;
}

/**
 * 获取浏览器可见宽度
 */
function getWindowWidth() {
	var winW = $(document.body).width();
	return winW;
}

/**
 * 获取head/footer高度
 */
function getTopSize() {
	return headHeight + footHeight;
}

/**
 * 初始化菜单数据(由dijit.tree换为ztree)
 */
function initNaviMenu() {
	var array = [];
	var _pId = null,_id = null,_name = null, _url = null,_showname = null,_haveChildren = null,_isRoot=null,_type=null;
	$.each(naviMenu, function(index, item) {
	    _id = item.id;  
	    _pId = item.parent;
	    _name = "<span class='defaulFontColor'>"+item.name+"</span>";  
	    _url = item.targetUrl;
	    _showname = item.showname;
	    _type = item.type;
	    if(item.children==null){
	    	_haveChildren = false;
	    }else{
	    	_haveChildren = true;
	    }
	    if(item.parent=="00000000000000000000000000000000"){
	    	_isRoot = true;
	    }else{
	    	_isRoot = false;
	    }
		array[index] = {id:_id, parent:_pId, name:_name, targetUrl:_url,showname:_showname,title:item.name,haveChildren:_haveChildren,isRoot:_isRoot,type:_type}; 
	});
	var setting = {  
		    data: {
				simpleData: {
					enable: true,
					idKey:"id",
					pIdKey: "parent"
				}
			},view: {
			    dblClickExpand: false,
				showLine: false,
				showTitle:false,
				selectedMulti: false,
				nameIsHTML: true
			},
		    callback: {
				onClick: function(event, treeId, treeNode){
					if(treeNode.haveChildren == false){
						workFrame.location.href = treeNode.targetUrl;
						initFrameModuleName(treeNode.showname);
					}else{
						var treeObj = $.fn.zTree.getZTreeObj("menuTree");
						treeObj.expandNode(treeNode, null, true, true);
					}
					
				}
			}
		};
	
	$.fn.zTree.init($("#menuTree"),setting, array);
}

/**
 * 刷新任务菜单 allTaskView.append("
 */
function refreshTaskMenu(taskData) {
	var htmlData = "<ul class='taskdiv_ul'>";
	$.each(taskData,function(i,entry) {
			htmlData += "<li style='width:150px;'>";
			htmlData += ("<a target='workFrame' title='" + entry.name + "' href='" + entry.entrypoint_url
					+ "' onclick=initFrameModuleName('" + entry.name + "')>" + entry.showname + "</a>");
			htmlData += "</li>";
	});
	htmlData += "</ul>";
	$("#taskMenu").empty().append(htmlData)
}

function initFrameModuleName(name) {
	var tempModuleName = name;
	if (name.length > 10) {
		tempModuleName = name.substring(0, 6) + "...";
	}
	$("#currModuleDesc").text("当前模块:" + tempModuleName);
	$("#currModuleDesc").attr("title",name);
}

var isWestOpen = true;
function westOpen(){
	isWestOpen = true;
}
function westClosed(){
	isWestOpen = false;
}
