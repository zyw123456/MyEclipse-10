/**
 * 公共布局
 * 
 * @author 李水野
 * @date 2013-11-19
 */

var topAreaHeight = 35; // 上面区域的高度
var leftAreaWidht = 190;// 左边区域的宽度
var footAreaHeight = 15;// 底部区域高度

var screenWidth = getScreenWidth();// 屏幕的宽度
var screenHeight = getScreenHeight();// 屏幕的高度

var testSwitch = false;// 测试开关

var minHeight = 500;
var minWidth = 1000;
/**
 * 可见区域宽度
 */
function getVisiableAreaWidth() {
	var width = window.screen.availWidth;
	if (testSwitch) {
		alert(navigator.userAgent);
	}
	// IE6
	if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
		width = width - 30;
	}
	//	IE7
	if (navigator.userAgent.indexOf("MSIE 7.0") > 0) {
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
function getVisiableAreaHeight() {
	var height = document.documentElement.clientHeight;
	if (testSwitch) {
		alert(navigator.userAgent);
	}
	// IE6
	if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
		if (testSwitch) {
			alert("ie6");
		}
		if (screenWidth == 1024 && screenHeight == 768) { // 分辨率1024*768
			if (testSwitch) {
				alert("1024*768");
			}
			height = height + 15;
		} else if (screenWidth == 1366 && screenHeight == 768) {// 分辨率1366*768
			if (testSwitch) {
				alert("1366*768");
			}
			height = height + 15;
		} else {
			height = height;
		}
	}
	// IE8
	if (navigator.userAgent.indexOf("MSIE 8.0") > 0) {
		if (testSwitch) {
			alert("ie8");
		}
		if (screenWidth == 1024 && screenHeight == 768) { // 分辨率1024*768
			if (testSwitch) {
				alert("1024*768");
			}
			height = height - 5;
		} else if (screenWidth == 1366 && screenHeight == 768) {// 分辨率1366*768
			if (testSwitch) {
				alert("1366*768");
			}
			height = height -10;
		} else {
			height = height;
		}
	}
	if (testSwitch) {
		alert("window height : " + height);
	}
	return height;
}

/**
 * 得到左边区域的宽度
 * 
 * @returns {Number} 左边区域的宽度
 */
function getLeftAreaWidht() {
	return leftAreaWidht;
}

/**
 * 得到左边区域的长度
 * 
 * @returns {Number} 左边区域的长度
 */
function getLeftAreaHeight() {
	var height = document.documentElement.clientHeight - topAreaHeight - footAreaHeight;
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
 * 得到上面区域的宽度
 * 
 * @returns 上面区域的宽度
 */
function getTopAreaWidth() {
	return getVisiableAreaWidth();
}

/**
 * 得到上面区域的高度
 * 
 * @returns 上面区域的高度
 */
function getTopAreaHeight() {
	return topAreaHeight;
}

/**
 * 得到底部区域的高度
 * 
 * @returns 底部区域的高度
 */
function getFootAreaHeight() {
	return footAreaHeight;
}

/**
 * 得到中间区域的宽度
 * 6为左侧拉动条宽度
 * @returns {Number} 中间区域的宽度
 */
function getCenterAreaWidth() {
	return (getVisiableAreaWidth() - getLeftAreaWidht()) - 6;
}

/**
 * 得到中间区域的高度
 * 12为上、下拉动条高度之和
 * @returns {Number} 中间区域的高度
 */
function getCenterAreaHeight() {
	return (getVisiableAreaHeight()) - 12;
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
/**
 * 可见区域宽度
 */
function getVisiableAreaWidthForDesk() {
	var width = document.documentElement.clientWidth;
	// IE6
	if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
		if(screenWidth<=1024){
			width = 1024-20;
		}else{
			if(width<=1024){
				width = 1024;
			}else{
				width = width;
			}
		}
	}
	//	IE7
	if (navigator.userAgent.indexOf("MSIE 7.0") > 0) {
		if(screenWidth<=1024){
			width = 1020;
		}else{
			if(width<=1024){
				width = 1024;
			}else{
				width = width;
			}
		}
	}
	// IE8
	if (navigator.userAgent.indexOf("MSIE 8.0") > 0) {
		if(screenWidth<=1024){
			width = 1020;
		}else{
			if(width<=1024){
				width = 1024;
			}else{
				width = width;
			}
		}
	}
	return width;
}

/**
 * 可见区域高度，根据浏览器的不同的调整
 */
function getVisiableAreaHeightForDesk() {
	var height = document.documentElement.clientHeight;
	// IE6
	if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
		if (testSwitch) {
			alert("ie6");
		}
		if (screenWidth == 1024 && screenHeight == 768) { // 分辨率1024*768
			if (testSwitch) {
				alert("1024*768");
			}
			height = height;
		} else if (screenWidth == 1366 && screenHeight == 768) {// 分辨率1366*768
			if (testSwitch) {
				alert("1366*768");
			}
			height = height;
		} else {
			height = height;
		}
	}
	// IE8
	if (navigator.userAgent.indexOf("MSIE 8.0") > 0) {
		if (testSwitch) {
			alert("ie8");
		}
		if (screenWidth == 1024 && screenHeight == 768) { // 分辨率1024*768
			if (testSwitch) {
				alert("1024*768");
			}
			height = height;
		} else if (screenWidth == 1366 && screenHeight == 768) {// 分辨率1366*768
			if (testSwitch) {
				alert("1366*768");
			}
			height = height;
		} else {
			height = height;
		}
	}
	if (testSwitch) {
		alert("window height : " + height);
	}
	if(height<minHeight){
		height = minHeight;
	}
	return height;
}
/**
 *计算最外层框架元素(rightindex)高度，即用于获取中心区域可见高度
 *
 */
function getOuterCenterAreaHeight(){
	var centerAreaHeight = top.document.getElementById("rightindex").style.height;
	centerAreaHeight = centerAreaHeight.substr(0, centerAreaHeight.length - 2);
	return centerAreaHeight;
}