/**
 * 桌面子系统相关js方法 银之杰科技股份有限公司 2012-1-24
 * 
 * @author 陈林江
 * @version 1.0.0
 */
var showNotice = true; // 是否在离开界面时弹出提示
var windowWidth = 1279; // 浏览器宽度
var windowHeight = 685; // 浏览器高度
var topAreaHeight = 35; // 上面区域的高度
var leftSize_min = 12;
var leftSize_max = 190;
var leftSize = leftSize_max; // 左侧菜单栏宽度
var leftAreaWidht = 190;// 左边区域的宽度

function getStyle(o, k) {
	var _k = k.replace(/-[a-z]/g, function(a) {
		return a.substr(1, 1).toUpperCase();
	});
	var v = o.style[_k];
	if (!v) {
		if (document.defaultView && document.defaultView.getComputedStyle) {
			var c = document.defaultView.getComputedStyle(o, null);
			v = c ? c.getPropertyValue(_k) : null;
		} else if (o.currentStyle) {
			v = o.currentStyle[_k];
		}
	}
	return v;
}

function hideLeft() {
	var objLeft = document.getElementById("left_loginov");
	var objLogin = document.getElementById("left_login");
	var currLeft = parseInt(getStyle(objLeft, "left"));
	if (currLeft > -187) {
		objLeft.style.left = (currLeft - 8) + "px";
		objLogin.style.left = (currLeft - 8) + "px";
		window.setTimeout("hideLeft()", 5);
	}
}

function showLeft() {
	var objLeft = document.getElementById("left_loginov");
	var objLogin = document.getElementById("left_login");
	var currLeft = parseInt(getStyle(objLeft, "left"));
	if (currLeft < 0) {
		objLeft.style.left = (currLeft + 8) + "px";
		objLogin.style.left = (currLeft + 8) + "px";
		window.setTimeout("showLeft()", 5);
	}
}

function setMenu() {
	var hideDiv = $("#lefttog");// 隐藏按钮的div
	var isHide = $("#lefttog").attr("title");// 从title中获取隐藏的标识位
	// 收缩菜单栏
	if (isHide == "hide") {
		leftSize = leftSize_min;
		hideDiv.attr("class", "hideAfter");
		hideDiv.css("width", "17px");
		hideDiv.css("height", "88px");
		hideDiv.css("paddingLeft", "17px");
		// 修改title的值
		hideDiv.attr("title", "show");
		hideLeft();

		// 右侧拉宽
		document.getElementById("workFrame").style.width = getWindowWidth()
				- getLeftSize() + "px";
		document.getElementById("workFrame").style.height = getWindowHeight()
				- getTopSize() + "px";
		document.getElementById("workFrame").style.marginLeft = leftSize_min
				+ "px";
		// 展开菜单栏
	}
	// 显示菜单栏
	if (isHide == "show") {
		leftSize = leftSize_max;
		hideDiv.attr("class", "hideBefore");
		hideDiv.css("width", "9px");
		hideDiv.css("height", "104px");
		hideDiv.css("paddingLeft", "0px");
		// 修改title的值
		hideDiv.attr("title", "hide");
		showLeft();
		document.getElementById("workFrame").style.marginLeft = getLeftSize()
				+ "px";
		document.getElementById("workFrame").style.height = getWindowHeight()
				- getTopSize() + "px";
		document.getElementById("workFrame").style.width = (getWindowWidth() - getLeftSize())
				+ "px";

	}
}
$(document)
		.ready(
				function() {
					bindFirstMenu();
					var previewCacheSpace = WFCache
							.getCacheSpace("WF_CORE_CACHE_SPACE");
					var defaultCache = WFCache.getPageCache(previewCacheSpace);
					var isBlockTaskMenu = defaultCache
							.get("IS_BLOCK_TASK_MENU");
					document.getElementById("left_login").style.height = getLeftAreaHeight()
							+ "px";
					if (isBlockTaskMenu == "unblock") {
						document.getElementById("login_cai").style.height = (getWindowHeight()
								- getTopSize() - 15)
								* 0.65 + "px";
						document.getElementById("login_ren").style.height = (getWindowHeight()
								- getTopSize() - 15)
								* 0.3 + "px";
						autoSearch(); // 界面打开时就进行一次任务栏的刷新
						window.setInterval(autoSearch, 10000);
					} else {
						document.getElementById("login_cai").style.height = (getWindowHeight()
								- getTopSize() - 30)
								+ "px";
						document.getElementById("login_ren").style.display = "none";
					}
					document.getElementById("nov_nr").style.height = getWindowHeight()
							+ "px";

				});
function bindFirstMenu() {
	$("#li.firstMenu").unbind("click");
	$("li.firstMenu")
			.click(
					function() {
						if (typeof ($(this).next("li").get(0)) != "undefined"
								&& $(this).next("li:first").attr("class") != "firstMenu") {
							$(this).next("li:first").slideToggle();

						} else {
							initFrame($(this).attr("name"));
							workFrame.location.href = $(this).attr("id");
						}

					});
	$("#li.cc02 a").unbind("click");
	$("li.cc02 a").click(function() {
		var currClass = $(this).parent().attr("class");
		// 为了控制点击子节点时不能触发父节点点击事件
		$(this).parent().find("ul:first").slideToggle();
		if (currClass == "cc02") {
			$(this).parent().attr("class", "cc01");
		} else if (currClass == "cc01") {
			$(this).parent().attr("class", "cc02");
		} else if (currClass == "cc03") {
			$(this).parent().attr("class", "cc04");
		} else if (currClass == "cc04") {
			$(this).parent().attr("class", "cc03");
		}
	});
	// 首次收缩二级菜单
//	$("#autoSearchParent ul").each(function() {
//		if ($(this).attr("class") == "login_cai_child") {
//			$(this).find("li.cc02 a").trigger("click")
//		}
//	});
}

/**
 * 点击左侧的可跳转的模块时触发的事件
 */
function initFrame(moduleName) {
	var tempModuleName = moduleName;
	if (moduleName.length > 10) {
		tempModuleName = moduleName.substring(0, 6) + "...";
	}
	document.getElementById("moduleDesc").innerText = "当前模块:  "
			+ tempModuleName;
	document.getElementById("moduleDesc").title = moduleName;
	// startProcess("正在初始化界面,请稍等...");
}
// 双击任务栏
function dbClick() {
	var login_ren = document.getElementById("login_ren");
	var login_cai = document.getElementById("login_cai");
	var taskul = document.getElementById("taskul");

	if (login_ren.offsetHeight < 250) {
		login_ren.style.height = (getWindowHeight() - getTopSize() - 15) * 0.40
				+ "px";
		login_cai.style.height = (getWindowHeight() - getTopSize() - 15) * 0.55
				+ "px";
		taskul.style.height = (getWindowHeight() - getTopSize() - 15) * 0.40
				- 39 + "px";
	} else {
		login_ren.style.height = (getWindowHeight() - getTopSize() - 15) * 0.20
				+ "px";
		login_cai.style.height = (getWindowHeight() - getTopSize() - 15) * 0.75
				+ "px";
		taskul.style.height = (getWindowHeight() - getTopSize() - 15) * 0.20
				- 39 + "px";
	}
}

var isKeyDown = false;
var preY = 0;

function mouseDown() {
	// 若为IE6，则不执行该代码
	if (!(($.browser.msie) && ($.browser.version == "6.0"))) {
		var evt = window.event;
		if (evt.button != 2) {
			return;
		}
		var midLine = document.getElementById("taskMenuImgDiv");
		midLine.style.cursor = "pointer";
		preY = evt.screenY;
		isKeyDown = true;
	}
}

function mouseOver() {
	var midLine = document.getElementById("taskMenuImgDiv");
	midLine.style.cursor = "n-resize";
}
function mouseMove() {
	var evt = window.event;
	if (!isKeyDown) {
		return;
	}
	if (evt.button != 2) {
		return;
	}
	var login_ren = document.getElementById("login_ren");
	var login_cai = document.getElementById("login_cai");
	var taskul = document.getElementById("taskul");
	var increY = evt.screenY - preY; // 纵坐标增长量
	if (login_ren.offsetHeight > 400 && increY < 0) {
		return;
	}
	if (login_ren.offsetHeight < 150 && increY > 0) {
		return;
	}
	login_ren.style.height = login_ren.offsetHeight - increY + "px";
	taskul.style.height = taskul.offsetHeight - increY + "px";
	login_cai.style.height = 610 - login_ren.offsetHeight + increY + "px";
	preY = evt.screenY;
	if (evt.stopPropagation) {
		evt.stopPropagation();
	} else {
		evt.cancelBubble = true;
	}
}

function mouseOut() {
	var midLine = document.getElementById("taskMenuImgDiv");
	midLine.style.cursor = "pointer";
	isKeyDown = false;
}

function mouseUp() {
	var midLine = document.getElementById("taskMenuImgDiv");
	midLine.style.cursor = "n-resize";
	isKeyDown = false;
}

// 自动检索导航信息
function autoSearch(url) {
	var temp = document.getElementById("autoComplete");
	if (temp == null) {
		return;
	}
	var currValue = temp.value;
	if (currValue == null || currValue == "" || currValue == "搜索交易代码") {
		$.getJSON("navigationAutoSearchAction.action?_t="
				+ new Date().getTime(), "hostKey=", function(data) {
			$("#taskul").html("");
			$("#taskul").html(data["taskView"]);
		});
	} else {
		$.getJSON("navigationAutoSearchAction.action?_t="
				+ new Date().getTime(), "hostKey=" + currValue, function(data) {
			$("#taskul").html("");
			$("#taskul").html(data["taskView"]);
		});
	}

}
// 自动检索导航信息
function autoSearchMenu(url) {
	var currValue = document.getElementById("autoComplete").value;
	var wfCoreCacheSpace = WFCache.getCacheSpace("WF_CORE_CACHE_SPACE");
	var wfCoreCache = WFCache.getPageCache(previewCacheSpace);

	var dkCacheSpace = WFCache.getCacheSpace("WF_DK_CACHE_SPACE");
	var dkCache = WFCache.getPageCache(dkCacheSpace);
	if (wfCoreCache.get("ENABLE_HOTKEY_FUN")) {
		if (currValue == null || currValue == "" || currValue == "搜索交易代码") {
			alert("请输入交易代码!");
			$("#autoComplete").focus();
		} else {
			var tempObj = dkCache.get(currValue);
			if (tempObj) {
				initFrame(tempObj.name);
				workFrame.location.href = tempObj.url;
			} else {
				alert("交易代码不存在或者您没有权限操作该模块!");
				$("#autoComplete").focus();
			}
		}
	} else {
		if (currValue == null || currValue == "" || currValue == "搜索交易代码") {
			$.getJSON(url, "hostKey=", function(data) {
				$("#autoSearchParent").html("");
				$("#autoSearchParent").html(data["navigation"]);
				bindFirstMenu();
			});
		} else {

			$.getJSON(url, "hostKey=" + currValue, function(data) {
				$("#autoSearchParent").html("");
				$("#autoSearchParent").html(data["navigation"]);
				bindFirstMenu();
			});
		}
	}
}

function lockIndexPage() {
	showPage_lockPage("windforce/dk/freePage.jsp", "", "请输入密码进行解锁", 200, 60,
			null, function() {
				WFUnload.clear();
				window.location.href = "j_spring_security_logout";
			});
}

function changeDefaultPassword(sid) {
	showPage_lockPage("windforce/dk/modifyDefualtPwd.jsp?userSid=" + sid, "",
			"你的密码为默认密码，请修改!", 260, 132, null, function() {
				WFUnload.clear();
				window.location.href = "j_spring_security_logout";
			});
}

function changeOutDatePassword(sid) {
	showPage_lockPage("windforce/dk/modifyDefualtPwd.jsp?userSid=" + sid, "",
			"你的密码已过期，请修改!", 260, 132, null, function() {
				WFUnload.clear();
				window.location.href = "j_spring_security_logout";
			});
}

function reLoad() {
	showNotice = false;// 通过调用内部方法进行的界面刷新不弹出提示框
	document.location.href = "login.action";
}
// 弹出屏蔽层
function showd(url, width, height) {
	showDialogInfo(url, width, height);
}
// 刷新右侧iframe
function refreshRight(url) {
	workFrame.location.href = url;
}
// 敲回车搜索
function onFunctionKey(evt) {
	evt = (evt) ? evt : ((window.event) ? window.event : "");
	keyCode = evt.keyCode ? evt.keyCode
			: (evt.which ? evt.which : evt.charCode);
	if (keyCode == 13) {
		searchBlur();
		autoSearchMenu('navigationAutoSearchAction.action?_t='
				+ new Date().getTime());
	}
}

// 带参数的弹出屏蔽层
function showWindowForUrl(action, taskName, proDefKey, curPage, width, height) {
	showWindowDialogForUrl(action, taskName, proDefKey, curPage, width, height);
}
function onscrollTo() {

	window.scrollTo(0, 85);
}

var screenWidth = getScreenWidth();// 屏幕的宽度
var screenHeight = getScreenHeight();// 屏幕的高度

/**
 * 可见区域宽度
 */
function getWindowWidth() {
	var width = window.screen.availWidth;
	// alert(navigator.userAgent);
	// IE6
	if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
		width = width - 30;
	}
	// IE8
	if (navigator.userAgent.indexOf("MSIE 8.0") > 0) {
		if (screenWidth == 1024 && screenHeight == 768) { // 分辨率1024*768
			width = width - 4;
		} else {
			width = width - 4;
		}
	}
	return width;
}

/**
 * 可见区域高度，根据浏览器的不同的调整
 */
function getWindowHeight() {
	var height = document.documentElement.clientHeight;
	// IE6
	if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
		height = height - 27;
	}
	// IE8
	if (navigator.userAgent.indexOf("MSIE 8.0") > 0) {
		// alert("ie8");
		if (screenWidth == 1024 && screenHeight == 768) { // 分辨率1024*768
			// alert("1024*768");
			height = height - 5;
		} else {
			height = height - 5;
		}
	}
	return height;
}

/**
 * 可见区域高度
 * 
 * @returns 可见区域高度
 */
function getClientHeight() {
	return getWindowHeight();
	// return document.documentElement.clientHeight;
}

/**
 * 得到左边区域的长度
 * 
 * @returns {Number} 左边区域的长度
 */
function getLeftAreaHeight() {
	var height = document.documentElement.clientHeight - getTopSize();
	// IE6
	if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
		height = height;
	}
	// IE8
	if (navigator.userAgent.indexOf("MSIE 8.0") > 0) {
		height = height + 16;
	}
	return height;
}

/**
 * 屏幕分辨率的宽度
 * 
 * @returns 屏幕分辨率的宽度
 */
function getScreenWidth() {
	return window.screen.width;
}

/**
 * 屏幕分辨率的高度
 * 
 * @returns 屏幕分辨率的高度
 */
function getScreenHeight() {
	return window.screen.height;
}

function getTopSize() {
	return topAreaHeight;
}
function getLeftSize() {
	return leftSize;
}