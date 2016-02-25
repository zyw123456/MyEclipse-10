/**
 * @author chenhuang
 * @date 2014-11-14
 */

// 加载页面缓存
WFCacheManager.loadPageCache();

// 检查是否需要提示修改密码
var previewCacheSpace = WFCache.getCacheSpace("WF_CORE_CACHE_SPACE");
var defaultCache = WFCache.getPageCache(previewCacheSpace);
var passwordActiveInterval = parseInt(defaultCache.get("PASSWORD_ACTIVE_INTERVAL"));
var passwordInfoInterval = parseInt(defaultCache.get("PASSWORD_INFO_INTERVAL"));
var blockModifyPwdFun = defaultCache.get("BLOCK_MODIFY_PWD_FUN");
var pwdStrengthLevel = parseInt(defaultCache.get("PWD_STRENGTH_LEVEL")); // 密码安全级别,默认为3;
var enableRandomPwdForResetPwd = defaultCache.get("enableRandomPwdForResetPwd");// 重置密码是否邮件发送随机密码
var isBlockTaskMenu = defaultCache.get("IS_BLOCK_TASK_MENU");
var pwdGoingExpiredReminder = 0;

if (lastModifiedTime != 0) {
	// 计算今天距离上次修改密码过去了多少天,该数字应该不会大于30，因为大于30根本登录不成功
	var day = parseInt((todayTime - lastModifiedTime) / (24 * 60 * 60 * 1000));
	if ((passwordActiveInterval - day) < (passwordInfoInterval + 1)) {
		pwdGoingExpiredReminder = passwordActiveInterval - day;
	}
}
// 登出系统
function logout() {
	if (window.confirm('您确认退出系统?', '提示信息')) {
		$.getJSON(ctx + "/logoutCheck.action?_t=" + new Date().getTime(), function(data) {// 检查当前用户是否可以正常退出
			if (data.logoutRule == "agree") {// 同意，直接退出！
				WFUnload.clear();
				window.location.href = ctx + "/j_spring_security_logout";
			} else if (data.logoutRule == "warn") {// 警告，提示用户是否退出
				if (window.confirm(data.msg, '提示信息')) {
					WFUnload.clear();
					window.location.href = ctx + "/j_spring_security_logout";
				}
			} else {// 拒绝退出，给出拒绝理由
				wfAlert(data.msg);
			}
		});
	}
}
// 登录检查
function loginCheck() {
	$.getJSON(ctx + "/loginCheck.action?_t=" + new Date().getTime(), function(data) {// 检查当前用户是否可以正常登录
		if (data.loginRule == "exception") {// 有警告提示信息！
			wfAlert(data.msg);
		}
	});
}

// 系统首页
function reLoad() {
	showNotice = false;// 通过调用内部方法进行的界面刷新不弹出提示框
	document.location.href = "login.action";
}

// 锁屏
function lockIndexPage() {
	showPage_lockPage("windforce/dk/freePage.jsp", "", "请输入密码进行解锁", 220, 60, null, function() {
		WFUnload.clear();
		window.location.href = "j_spring_security_logout";
	});
}

// 修改默认密码
function changeDefaultPassword(sid) {
	showPage_lockPage("windforce/dk/modifyDefualtPwd.jsp?userSid=" + sid, "", "你的密码为默认密码，请修改!", 260, 132, null,
			function() {
				WFUnload.clear();
				window.location.href = "j_spring_security_logout";
			});
}

// 修改过期密码
function changeOutDatePassword(sid) {
	showPage_lockPage("windforce/dk/modifyDefualtPwd.jsp?userSid=" + sid, "", "你的密码已过期，请修改!", 260, 132, null,
			function() {
				WFUnload.clear();
				window.location.href = "j_spring_security_logout";
			});
}

//授权校验
function checkAuthorization(){
	showPage_lockPage("windforce/dk/authorization.jsp", "", "请输入授权人员信息!", 260, 110, null,
			function() {
				WFUnload.clear();
				window.location.href = "j_spring_security_logout";
			});
}

function autoSearchMenuData(url) {
	var currValue = document.getElementById("autoComplete").value;
	var wfCoreCacheSpace = WFCache.getCacheSpace("WF_CORE_CACHE_SPACE");
	var wfCoreCache = WFCache.getPageCache(previewCacheSpace);
	var dkCacheSpace = WFCache.getCacheSpace("WF_DK_CACHE_SPACE");
	var dkCache = WFCache.getPageCache(dkCacheSpace);
	if (wfCoreCache.get("ENABLE_HOTKEY_FUN") == "true") {
		if (currValue == null || currValue == "" || currValue == "搜索交易代码") {
			alert("请输入交易代码!");
			$("#autoComplete").focus();
		} else {
			var tempObj = dkCache.get(currValue);
			if (tempObj) {
				initFrameModuleName(tempObj.name);
				workFrame.location.href = tempObj.url;
			} else {
				alert("交易代码不存在或者您没有权限操作该模块!");
				$("#autoComplete").focus();
			}
		}
	} else {
		if (currValue == null || currValue == "" || currValue == "搜索交易代码") {
			$.getJSON(url, "hostKey=", function(resp) {
				refreshTree(resp["navigation"]);
			});
		} else {
			$.getJSON(url, "hostKey=" + currValue, function(resp) {
				refreshTree(resp["navigation"]);
			});
		}
	}
}

function refreshTree(menuData) {
	var array = [];
	var _pId = null,_id = null,_name = null, _url = null,_showname = null,_haveChildren = null,_isRoot=null,_type=null;
	$.each(menuData, function(index, item) {
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

function autoSearchTaskData() {
	var temp = document.getElementById("autoComplete");
	if (temp == null) {
		return;
	}
	var currValue = temp.value;
	if (currValue == null || currValue == "" || currValue == "搜索交易代码") {
		$.getJSON("navigationAutoSearchAction.action?_t=" + new Date().getTime(), "hostKey=", function(data) {
			refreshTaskMenu(data["taskView"]);
		});
	} else {
		$.getJSON("navigationAutoSearchAction.action?_t=" + new Date().getTime(), "hostKey=" + currValue,
				function(data) {
					refreshTaskMenu(data["taskView"]);
				});
	}
}

function searchOn() {
	var el = document.getElementById("autoComplete");
	if (el.value == "搜索交易代码") {
		el.value = "";
		el.style.color = "";
	}
}

function searchBlur() {
	var el = document.getElementById("autoComplete");
	if (el.value == "") {
		el.value = "搜索交易代码";
		el.style.color = "gray";
	}
}

function onFunctionKey(evt) {
	evt = (evt) ? evt : ((window.event) ? window.event : "");
	keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which : evt.charCode);
	if (keyCode == 13) {
		searchBlur();
		autoSearchMenuData('navigationAutoSearchAction.action?_t=' + new Date().getTime());
	}
}
/** ************************************迁移原有代码******************************************** */
// 点击单个机构信息
function frameForward(url, sid) {
	window.parent.frames["workFrame"].document.getElementById("main_right").src = url;
	if (window.parent.frames["workFrame"].document.getElementById("main_right").src
			.indexOf('editOrganize_editInit.action') > 0) {
		editOrg(ctx + "/editOrganize_editInit.action?_t=" + new Date().getTime() + "&orgNo=" + sid);
	}
}

// 添加完机构后刷新左菜单
function refreshLeft() {
	window.parent.frames["workFrame"].document.getElementById("poTreeFrame").src = ctx
			+ "/organizeAction_showOrgnizeIndexPage.action";
}
// 点击修改机构按钮
function editOrg(url) {
	window.parent.frames["workFrame"].document.getElementById("right").src = url;
	expendRight();
}
// 点击删除机构按钮
function delOrg(url) {
	$.post(url, null, function(data) {
		if (data == "删除成功!") {
			changeProcessTitle("删除完毕,正在刷新界面...");
			// 刷新左页面
			window.parent.frames["workFrame"].document.getElementById("poTreeFrame").src = ctx
					+ "/organizeAction_showOrgnizeIndexPage.action";
			window.parent.frames["workFrame"].document.getElementById("main_right").src = ctx
					+ "/organizeDetail.action?sid=";
		} else {
			wfAlert(data);
			stopProcess();
		}
	});
}
function relDetail(currId) {
	window.parent.frames["workFrame"].document.getElementById("main_right").src = ctx + "/organizeDetail.action?sid="
			+ currId;
}

// 点击删除人员按钮
function delPeople(url, orgId) {
	$.post(url, null, function(data) {
		wfAlert(data);
		top.changeProcessTitle("正在刷新界面...");
		// 刷新用户列表
		window.parent.frames["workFrame"].document.getElementById("main_right").src = ctx
				+ "/organizeDetail.action?sid=" + orgId;
	});
}
// 点击用户详细
function right_peopleDetail(url) {
	window.parent.frames["workFrame"].document.getElementById("right").src = url;
	expendRight();
}

function refreshRoleList() {
	workFrame.window.refreshRole();
}
function refreshRoleGroupList() {
	workFrame.window.refreshRoleGroup();
}
function userDetail(userCode) {
	showPage(ctx + "/userDetail.action?_t=" + new Date().getTime(), "userNo=" + userCode, "用户信息", 500, 250, null);
}

//显示人员信息详情界面（供1.5版本使用）
function showPeopleDetailPage(userCode){
	showPage(ctx + "/userDetail.action?_t=" + new Date().getTime(), "userNo=" + userCode, "用户信息", 500, 350, null);
}

// 修改密码
function modifyPwd() {
	var currId = currPeopleSid;
	if (currId != "" && currId != null) {
		top.showPage(ctx + "/windforce/po/peopleInfo/modifyPwd.jsp?&time=" + (new Date()).getTime(),"userNo=" + currId
					+ "&pwdStrengthLevel=" + pwdStrengthLevel,"修改密码",450,240);
	}
}

// 参数管理
function paramframeForward(url) {
	window.parent.frames["workFrame"].document.getElementById("paramDetail").src = url;
}

function onloadTree() {
	document.getElementById('workFrame').contentWindow.onloadTree();
}
/** ******************************************************************************** */
