<%@page import="com.yzj.wf.am.security.common.AMSecurityDefine"%>
<%@page import="com.yzj.wf.core.model.po.wrapper.XPeopleInfo"%>
<%@page import="com.yzj.wf.cache.common.PageCacheDefine"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String naviMenu = session.getAttribute("_naviMenu").toString();
	String taskMenu = session.getAttribute("_taskMenu").toString();

	XPeopleInfo xp = (XPeopleInfo) session.getAttribute("XPEOPLEINFO");
	Date lastModified = xp.getLastChangepwdTime();
	long lastModifiedTime = 0;
	if (lastModified != null) {
		lastModifiedTime = lastModified.getTime();
	}

	String currPeopleSid = xp.getSid();
	
	Boolean lockScreen = xp.getLockScreen();

	String needChangePassword = request.getAttribute(
			"needChangePassword").toString();
			
	Boolean needAuthorization = xp.getNeedAuthorization();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>银之杰基础平台</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<%@ include file="/windforce/common/jsp/wfInternalImport.jsp" %>
<%@ include file="/common/wf_import.jsp"%>
<link type="text/css" rel="stylesheet" href="<%=path%>/windforce/common/css/desktop.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/windforce/common/theme/default/css/default.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/common/css/alert/bootstrap.min.css"/>

<link type="text/css" rel="stylesheet" href="<%=path%>/common/css/ztree/zTreeStyle.css"></link>
<script type="text/javascript" src="<%=path%>/common/js/jquery/jquery-layout/jquery.layout.min.js"></script>
<script type="text/javascript" src="<%=path%>/common/js/jquery/jquery-ui/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=path%>/common/js/jquery/ztree/jquery.ztree.all-3.1.js"></script>

<script type="text/javascript" src="<%=path%>/common/js/wf/wfCache_v1.0.js"></script>
<script type="text/javascript" src="<%=path%>/windforce/common/js/desktop.js"></script>
<script type="text/javascript" src="<%=path%>/common/js/alert/bootstrap-alert.js"></script> 
<%@ include file="headextend.jsp"%>
<script type="text/javascript">
	var showNotice = true; // 是否在离开界面时弹出提示
	var WFUnload = {};
	WFUnload.MSG_UNLOAD = "非安全操作，可能会导致数据丢失!";
	WFUnload.set = function(msg) {
		window.onbeforeunload = function(e) {
			if (showNotice) {
				var ev = e || window.event;
				ev.returnValue = msg;
				return msg;
			}
		};
	};
	WFUnload.clear = function() {
		window.onbeforeunload = function() {
		};
	};
	WFUnload.set(WFUnload.MSG_UNLOAD);
	var myLayout;
</script>
<script type="text/javascript">
		var ctx = "<%=path%>";
		var naviMenu = <%=naviMenu%>;
		var taskMenu = <%=taskMenu%>;
		var needChangePassword = "<%=needChangePassword%>";
		var lastModifiedTime = "<%=lastModifiedTime%>";
		var todayTime = <%=System.currentTimeMillis()%>;
		serverUrl="<%=basePath%>";
		
		var currPeopleSid = "<%=currPeopleSid%>";
		var needAuthorization = "<%=needAuthorization%>";
		var _lockScreen = <%=lockScreen%>;
		
		var IE6 = false;
		var IE = false;
		
		var loginPeopleInfo = {
				orgSid: "<%=xp.getOrganizeSid()%>",
				orgType: "<%=xp.getOrgType()%>",
				orgName: "<%=xp.getOrgName()%>",
				orgNo: "<%=xp.getOrgNo()%>",
				peopleSid: "<%=xp.getSid()%>",
				peopleCode: "<%=xp.getPeopleCode()%>",
				peopleName: "<%=xp.getPeopleName()%>",
				personnelGenre: "<%=xp.getPeopleType()%>",
				personnelCard: "<%=xp.getPeopleIdCard()%>",
				headLevel: "<%=xp.getPeopleLevel()%>",
				areaSid: "<%=xp.getOrgArea()%>"
			};
			
		function getOrgTypeNameByOrgTypeNo(orgTypeNo){
			if("1" == orgTypeNo){
				return "网点";
			}
			if("2" == orgTypeNo){
				return "支行";
			}
			if("3" == orgTypeNo){
				return "二级分行";
			}
		    if("4" == orgTypeNo){
				return "一级分行";
		    }
			if("5" == orgTypeNo){
				return "总行";
			}
			 
		}
		
			
</script>

<script type="text/javascript" src="<%=path%>/windforce/common/js/dk_fun.js"></script>
</head>
<body id="deskbody" class="body_background" style="width:auto;height:auto;">
	<div id="desktop" style="position:relative;"> 
		<div id="preLoader"></div>
		<!--顶部区域-->
		<div class="ui-layout-north nov_bigsmall" id="top">
			<div class="nav_menu_logo">
				<img id="desktop_logo" align="top" src="" />
			</div>
			<div class="nav_menu_nav font_colors0">
				<a onclick="reLoad();" style="margin-left:5px;cursor: pointer;">
					<!-- <img class="imgpos" id="desktop_home_page" align="middle" src="" alt="系统首页" /> -->
					系统首页
				</a>
				&nbsp;
				<font color="gray" size="3px">|</font>
				<span class="peopleCodeSpan">
					柜员代码:<%=xp.getPeopleCode()%>&nbsp;
					<font color="gray" size="3px">|</font>
					&nbsp;
				</span>
				<span class="peopleNameSpan">
					柜员名称:<%=xp.getPeopleName()%>
					&nbsp;
					<font color="gray" size="3px">|</font>
					&nbsp;
				</span>
				<span class="orgNoSpan">
					所属机构:<%=xp.getOrgNo()%>
					&nbsp;
					<font color="gray" size="3px">|</font>
					&nbsp;
				</span>
				<span id="currModuleDesc"></span>
			</div>
			<div class="nav_menu_right font_colors0">
				<a onclick="logout();"  style="cursor: pointer;">
					<!-- <img class="imgpos" id="desktop_logout" align="middle" src="" alt="退出系统" /> -->退出系统
				</a>
				&nbsp;
				<font color="gray" size="3px">|</font>
				<a onclick="modifyPwd();" id="block_modify_pwd_fun"  style="cursor: pointer;">
					<!--<img class="imgpos" id="desktop_change_pwd" align="middle" src="" alt="修改密码" /> -->修改密码
				</a>
				&nbsp;
				<font color="gray" size="3px">|</font>
				<a onclick="lockIndexPage();"  style="cursor: pointer;">
					<!--<img class="imgpos" id="desktop_lock" align="middle" src="" alt="锁屏" /> -->锁屏
				</a>
				&nbsp;
			</div>
		</div>
		<!--左边菜单显示区域-->
		<div id="leftindex" class="ui-layout-west">
			<div>
				<div>
					 <br />
					<div class="left_search" style="padding-left:0px;">
						<input onkeydown="onFunctionKey(event);" type="text" class="left_search_text" id="autoComplete"
							onblur="searchBlur()" onfocus="searchOn()" value="搜索交易代码" />
						<input type="button" class="left_search_btn"
							onclick="autoSearchMenuData('<%=path%>/navigationAutoSearchAction.action');" />
					</div>
				</div>
				<div id="product_biz_tree" class="leftbiztree">
					<div id="menuTree" class="ztree"></div>
				</div>
			</div>
			<div id="product_taks_menu" class="lefttaskmenu">
				<div class="title">
					<a href="#" onclick="setTaskHeight();return false;">
						<img id="task_div_switch" src="">
					</a>
					我的任务
				</div>
				<div id="taskMenu" class="taskdiv" style="border:0px;">
					<ul class='taskdiv_ul'>
					</ul> 
				</div>
			</div>
		</div>
		<div id="rightindex"  class="ui-layout-center"  style="overflow:hidden;">
			<iframe id="workFrame" name="workFrame" allowtransparency=true frameborder="0" src="windforce/dk/blank.jsp"
				style="border: 0px;text-align: center;width:100%;height:100%;overflow:hidden;"></iframe>
		</div>
		<!--底部区域-->
		<div id="footer" class="ui-layout-south" align="center">
			<span style="float:center;">北京华道征信有限公司</span>
		</div>
		<%@ include file="extend.jsp"%>
	</div>
</body>
</html>