<%@page import="com.yzj.wf.am.security.common.AMSecurityDefine"%>
<%@page import="com.yzj.wf.core.model.po.wrapper.XPeopleInfo"%>
<%@page import="com.yzj.wf.cache.common.PageCacheDefine"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String needChangePassword = request.getAttribute(
			"needChangePassword").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="robots" content="all" />
<%@ include file="/common/wf_import.jsp"%>
<%@ include file="/windforce/common/jsp/wfInternalImport.jsp"%>
<script type="text/javascript"
	src="<%=path%>/common/js/wf/wfCache_v1.0.js"></script>
<script type="text/javascript" src="<%=path%>/windforce/common/js/dk.js"></script>

<title>WindForce</title>
<script type="text/javascript">
var WFUnload = {};
	WFUnload.MSG_UNLOAD = "非安全操作，可能会导致数据丢失!";
	WFUnload.set = function(msg) {
    window.onbeforeunload = function(e) {
    	if(showNotice){
	    	var ev = e || window.event;
	        ev.returnValue = msg;
	        return msg;
        }
    };
};
WFUnload.clear = function() {
    window.onbeforeunload = function() {};
};
WFUnload.set(WFUnload.MSG_UNLOAD);
</script>
<%
	XPeopleInfo xp = (XPeopleInfo) session.getAttribute("XPEOPLEINFO");
	
	Boolean lockScreen = xp.getLockScreen();
	
	Date lastModified = xp.getLastChangepwdTime();
	long lastModifiedTime = 0;
	if (lastModified != null) {
		lastModifiedTime = lastModified.getTime();
	}
%>
<!-- 缓存初始化 -->
<script type="text/javascript"> 
//加载页面缓存
WFCacheManager.loadPageCache();

//检查是否需要提示修改密码
var previewCacheSpace = WFCache.getCacheSpace("WF_CORE_CACHE_SPACE");
var defaultCache = WFCache.getPageCache(previewCacheSpace);
var passwordActiveInterval = parseInt(defaultCache.get("PASSWORD_ACTIVE_INTERVAL"));
var passwordInfoInterval =  parseInt(defaultCache.get("PASSWORD_INFO_INTERVAL"));
var blockModifyPwdFun = defaultCache.get("BLOCK_MODIFY_PWD_FUN");
var pwdStrengthLevel=parseInt(defaultCache.get("PWD_STRENGTH_LEVEL")); //密码安全级别,默认为3;
var enableRandomPwdForResetPwd = defaultCache.get("enableRandomPwdForResetPwd");//重置密码是否邮件发送随机密码
var pwdGoingExpiredReminder = 0;
if("<%=lastModifiedTime%>" != 0){
	var todayTime = <%=System.currentTimeMillis()%>;
	
	//计算今天距离上次修改密码过去了多少天,该数字应该不会大于30，因为大于30根本登录不成功
	var day = parseInt((todayTime - "<%=lastModifiedTime%>") / (24 * 60 * 60 * 1000));
	if((passwordActiveInterval - day) < (passwordInfoInterval + 1)){
		pwdGoingExpiredReminder = passwordActiveInterval - day;
	}
}
</script>
<script type="text/javascript">
serverUrl="<%=basePath%>";
function searchOn(){
var el = document.getElementById("autoComplete");
  	if (el.value == "搜索交易代码"){
    	el.value = "";
    	el.style.color = "";
    }
};
 function searchBlur(){
  	var el = document.getElementById("autoComplete");
  	if (el.value == ""){
    	el.value = "搜索交易代码";
    	el.style.color = "gray";
    }
}
// 点击单个机构信息
function frameForward(url,sid){
	window.parent.frames["workFrame"].document.getElementById("main_right").src  =url;
	if(window.parent.frames["workFrame"].document.getElementById("main_right").src.indexOf('editOrganize_editInit.action') > 0){
		editOrg("<%=path%>/editOrganize_editInit.action?_t="+ new Date().getTime() + "&orgNo="+sid);
	}
}

// 添加完机构后刷新左菜单
function refreshLeft(){
	window.parent.frames["workFrame"].document.getElementById("poTreeFrame").src = "<%=path%>/organizeAction_showOrgnizeIndexPage.action";
}
// 点击修改机构按钮
function editOrg(url){
	window.parent.frames["workFrame"].document.getElementById("right").src = url;
	expendRight();
	//window.parent.frames["workFrame"].document.getElementById("right").src = url;
}
// 点击删除机构按钮
function delOrg(url){
 	$.post(url, null, function(data) {
	    if(data == "删除成功!"){
		    changeProcessTitle("删除完毕,正在刷新界面...");
		    // 刷新左页面
		    window.parent.frames["workFrame"].document.getElementById("poTreeFrame").src = "<%=path%>/organizeAction_showOrgnizeIndexPage.action";
		    window.parent.frames["workFrame"].document.getElementById("main_right").src =  "<%=path%>/organizeDetail.action?sid=";
		}else{
		    wfAlert(data);
		    stopProcess();
	    }
 	});
}
function relDetail(currId){
	window.parent.frames["workFrame"].document.getElementById("main_right").src = "<%=path%>/organizeDetail.action?sid="+currId;
}

	// 点击删除人员按钮
function delPeople(url,orgId){
	 $.post(url, null, function(data) {
	    wfAlert(data);
	    top.changeProcessTitle("正在刷新界面...");
	    // 刷新用户列表
	    window.parent.frames["workFrame"].document.getElementById("main_right").src =  "<%=path%>/organizeDetail.action?sid="+orgId;
	 });
}
// 点击用户详细
function right_peopleDetail(url){
	window.parent.frames["workFrame"].document.getElementById("right").src = url;
	expendRight();
}

function refreshRoleList(){
	workFrame.window.refreshRole();
}
function refreshRoleGroupList(){
 	workFrame.window.refreshRoleGroup();
}
function userDetail(userCode){
	showPage("<%=path%>/userDetail.action?_t="+ new Date().getTime(),"userNo="+userCode,"用户信息",500,250,null);
	//window.open("<%=path%>/userDetail.action?userNo="+userCode,'userdetail','height=400,width=600,top=100,left=400,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
}
// 修改密码
function modifyPwd(){
	var currId = '<%=xp == null ? "" : xp.getSid()%>';
	if(currId != "" && currId != null){
 	var winHeight = 300;
	var winWidth = 450;
	var winTop = (window.screen.height - winHeight) / 2;
	var winLeft = (window.screen.width - winWidth) / 2;
	var sFeatures = "dialogHeight:" + winHeight + "px;dialogWidth:" + winWidth
			+ "px;dialogLeft:" + winLeft + "px;dialogTop:" + winTop + "px;";
			var returnValue = window.showModalDialog("<%=path%>/windforce/po/peopleInfo/modifyPwd.jsp?userNo="+currId+"&pwdStrengthLevel="+pwdStrengthLevel+"&time="
			+ (new Date()).getTime() , null, sFeatures);
			if(returnValue == "success") {
				wfAlert("密码已更改！");
			} else if(returnValue == "loginout"){
				wfAlert("登录已失效,请重新登录!");
				WFUnload.clear();
				window.location.href ="${pageContext.request.contextPath }/j_spring_security_logout";
			}
}
}

//参数管理
function paramframeForward(url){
	window.parent.frames["workFrame"].document.getElementById("paramDetail").src  =url;
}

//登出系统
function logout(){
	if(window.confirm('您确认退出系统?','提示信息')){
		$.getJSON("<%=path%>/logoutCheck.action?_t="+ new Date().getTime(), function(data) {//检查当前用户是否可以正常退出
			if(data.logoutRule == "agree"){//同意，直接退出！
				WFUnload.clear();
				window.location.href ="${pageContext.request.contextPath }/j_spring_security_logout";
			} else if(data.logoutRule == "warn"){//警告，提示用户是否退出
				if(window.confirm(data.msg,'提示信息')){
					WFUnload.clear();
					window.location.href ="${pageContext.request.contextPath }/j_spring_security_logout";
				}
			} else{//拒绝退出，给出拒绝理由
				wfAlert(data.msg);
			}
		});
	}
}

function changeImage(element,width,height){
	element.style.width=width;
	element.style.height=height;
}

$(document).ready(function(){
	if(blockModifyPwdFun){
		document.getElementById("block_modify_pwd_fun").style.display = "none";
	}
	document.getElementById("nov_bigsmall").style.width=(getWindowWidth() -5)+"px";
	document.getElementById("nov_nr").style.width=(getWindowWidth()-200)+"px";
	if("<%=needChangePassword%>"==1){
		changeDefaultPassword('<%=xp == null ? "" : xp.getSid()%>');
	}else if(pwdGoingExpiredReminder<0){
		changeOutDatePassword('<%=xp == null ? "" : xp.getSid()%>');
	} else if (pwdGoingExpiredReminder !=0) {
		wfAlert("您的密码将于"+pwdGoingExpiredReminder+"天后过期，请及时修改!");
	}
	if(<%=lockScreen%>){
		lockIndexPage();
	}
	loginCheck();
});

function loginCheck(){
	$.getJSON("<%=path%>/loginCheck.action?_t=" + new Date().getTime(),
				function(data) {//检查当前用户是否可以正常登录
					if (data.loginRule == "exception") {//有警告提示信息！
						wfAlert(data.msg);
					}
				});
	}
</script>
</head>
<body onmousemove="mouseMove()" class="body_background"
	style="min-width: 1024px">
	<div id="nov_bigsmall">
		<div id="logoDiv"></div>
		<div
			style="float:left; text-align:center; width:80px;height:24px;position: relative;top: 8px;">
			<a class="font_colors01" onclick="reLoad();"
				style="position: relative;top:2px;">系统首页</a>
		</div>
		<div 
			style="float:left; height: 24px;position: relative;top: 1px;white-space:nowrap;margin: 0px;"
			class="font_colors01">
			<span id="peopleCodeSpan">柜员代码：<%=((XPeopleInfo) request.getSession().getAttribute(
				AMSecurityDefine.XPEOPLEINFO)).getPeopleCode()%>&nbsp;&nbsp;&nbsp;</span>
			<span id="peopleNameSpan">柜员姓名：<%=((XPeopleInfo) request.getSession().getAttribute(
					AMSecurityDefine.XPEOPLEINFO)).getPeopleName()%>&nbsp;&nbsp;&nbsp;</span>
			<span id="orgNoSpan">机构号：<%=((XPeopleInfo) request.getSession().getAttribute(
				AMSecurityDefine.XPEOPLEINFO)).getOrgNo()%>&nbsp;&nbsp;&nbsp;</span>
		</div>
		<div id="moduleDesc"
			style="float:left;left:10px; text-align:left;width:300px; height:24px; position:relative;top:8px;"
			class="font_colors01"></div>
		<div
			style="float:right;right:80px;width:140px; height:24px; position:relative;top:8px; text-align:left;">
			<a class="font_colors01" href="#" onclick="logout();">退出系统</a>
			&nbsp;&nbsp;&nbsp; <a class="font_colors01" href="#"
				onclick="modifyPwd();" id="block_modify_pwd_fun">修改密码</a>
		</div>
		<div style="position:absolute;right:50px; margin: 0px;">
			<img onclick="lockIndexPage()"
				onmousemove="changeImage(this,'25px','25px')"
				onmouseout="changeImage(this,'21px','21px')" align="middle"
				title="锁屏" style="padding-top: 6px"
				src="<%=path%>/common/images/lock.png" width=21px height=21px />
		</div>
	</div>
	<div id="nov_nr" style="text-align: center;">
		<iframe id="workFrame" name="workFrame" allowtransparency=true
			frameborder="0" src="windforce/dk/blank.jsp" height="100%"
			style="overflow: hidden; border: 0px;text-align: center;width:100%;"></iframe>
	</div>


	<div id="left_login" style="filter:alpha(opacity:100);opacity:1;"></div>
	<div id="left_loginov" style="left: 1;">
		<!-- 系统菜单 -->
		<div id="login_cai">
			<div id="systemMenuDiv"></div>
			<span class="disblock" style="padding-left:10px;"> <input
				onkeydown="onFunctionKey(event);" type="text" class="input_text01"
				id="autoComplete" onblur="searchBlur()" onfocus="searchOn()"
				value="搜索交易代码" /> <input type="button" class="submit_but09"
				onclick="autoSearchMenu('<%=path%>/navigationAutoSearchAction.action');" />
			</span>
			<div class="cc_ul" id="cc_ul" style="height:83%">
				<ul
					style="height: 100%;overflow-y: auto;overflow-x:hidden;width: 160px;"
					id="autoSearchParent">
					<s:iterator value="idesktopObject" id="desktop">
       					${desktop.navigation } 
     				</s:iterator>
				</ul>
			</div>
		</div>
		<!-- 任务菜单 -->
		<div id="login_ren" style="height:250px">
			<div id="taskMenuImgDiv"></div>
			<ul class="link01" style="height:100%;overflow-y:auto;width: 160px;"
				id="taskul">
				<s:iterator value="idesktopObject" id="desktop">
       				${desktop.taskView } 
      			</s:iterator>
			</ul>
		</div>
		<!--隐藏菜单栏 ，其中title属性为隐藏标识位，值必须为"hide"，class属性的值必须为"before"  -->
		<div id="lefttog" title="hide" class="hideBefore" onclick="setMenu();"></div>
	</div>
	<script type="text/javascript">
		$("#workFrame").load(function() {
			initWorkFrame();
		});
		function onloadTree() {
			document.getElementById('workFrame').contentWindow.onloadTree();
		}
		function timeOutCallBack() {
			workFrame.location.href = "about:blank";
		}

		function initWorkFrame() {
			document.getElementById("workFrame").style.marginTop = getTopSize()
					+ "px";
			document.getElementById("workFrame").style.marginLeft = getLeftSize()
					+ "px";
			document.getElementById("workFrame").style.width = (getWindowWidth() - getLeftSize())
					+ "px";
			document.getElementById("workFrame").style.height = getWindowHeight()
					- getTopSize() + "px";
			if (workFrame.location.href.indexOf("dk/login") != -1) { //如果workframe被跳转到登录界面，不弹出是否离开界面的提示框
				showNotice = false;
				location.href = "login.action;";
			}
		}
	</script>
</body>
</html>
