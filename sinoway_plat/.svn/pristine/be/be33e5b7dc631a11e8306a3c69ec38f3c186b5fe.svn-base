<%@page import="com.yzj.wf.cache.common.PageCacheDefine"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">

<title>银之杰Windforce基础平台跳转页面</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="robots" content="all" />
<%@ include file="/windforce/common/jsp/wfInternalImport.jsp" %>
<%@ include file="/common/wf_import.jsp"%>
<script type="text/javascript" src="<%=path%>/common/js/wf/wfCache_v1.0.js"></script>
<!-- <script type="text/javascript" src="<%=path%>/windforce/common/js/dk.js"></script> -->
<style type="text/css">
body {
	min-width: 1024px;
	width:auto !important;
	width:1024px;
}
</style>
<script type="text/javascript">
serverUrl="<%=basePath%>";
//加载页面缓存
WFCacheManager.loadPageCache();
	
	// 点击单个机构信息
function frameForward(url,sid){
window.parent.frames["workFrame"].document.getElementById("main_right").src  =url;
if(window.parent.frames["workFrame"].document.getElementById("main_right").src.indexOf('editOrganize_editInit.action') > 0)
{
editOrg("<%=path %>/editOrganize_editInit.action?orgNo="+sid);
}
}

// 添加完机构后刷新左菜单
function refreshLeft()
{
window.parent.frames["workFrame"].document.getElementById("left").src = "<%=path %>/windforce/po/organizeinfo/organize_left.jsp";
}
// 点击修改机构按钮
function editOrg(url)
{
window.parent.frames["workFrame"].document.getElementById("right").src = url;
expendRight();
}
// 点击删除机构按钮
function delOrg(url)
{
 $.post(url, null, function(data) {
    if(data == "删除成功!")
    {
    changeProcessTitle("删除完毕,正在刷新界面...");
    // 刷新左页面
    window.parent.frames["workFrame"].document.getElementById("left").src = "<%=path %>/windforce/po/organizeinfo/organize_left.jsp";
    window.parent.frames["workFrame"].document.getElementById("main_right").src =  "<%=path %>/organizeDetail.action?sid=";
    }else{
    wfAlert(data);
    stopProcess();
    }
 });
}
function relDetail(currId)
{
window.parent.frames["workFrame"].document.getElementById("main_right").src = "<%=path %>/organizeDetail.action?sid="+currId;
}
	// 点击删除人员按钮
function delPeople(url,orgId)
{
 $.post(url, null, function(data) {
    wfAlert(data);
    top.changeProcessTitle("正在刷新界面...");
    // 刷新用户列表
    window.parent.frames["workFrame"].document.getElementById("main_right").src =  "<%=path %>/organizeDetail.action?sid="+orgId;
  
 });
}
// 点击用户详细
function right_peopleDetail(url){

window.parent.frames["workFrame"].document.getElementById("right").src = url;
expendRight();
}

function refreshRoleList()
{
workFrame.window.refreshRole();
}
function refreshRoleGroupList()
{
 workFrame.window.refreshRoleGroup();
}
function userDetail(userCode){
showPage("<%=path %>/userDetail.action","userNo="+userCode,"用户信息",500,250,null);
}
$(document).ready(function(){
	//setIndexMainHeight();
	//设定外层元素的宽、高,ifream标签区域等同
	var width = $(window).width();
	var height = $(window).height();
    $('#indexmain').css('width',width+'px');
    $('#indexmain').css('height',height+'px');
    var url = "<s:property value='mainPageUrl' />";
	var ifreamStr = '<iframe id="workFrame1" name="workFrame1" allowtransparency=true frameborder="0"'
				+' src="'+url+'"' 
				+' style="width:100%;height: 100%;overflow: hidden; border: 0px;text-align: center;"> </iframe>';
	$("#indexmain").empty().append(ifreamStr);
});
$(window).resize(function() {
    setIndexMainHeight();
});

function setIndexMainHeight(){
	$height = $(window).height();
    $('#indexmain').css('height',$height+'px');
}
</script>
</head>
<body class="body_background" style="overflow: hidden;">
	<div>
		<div id="indexmain">
<!-- 			<iframe id="workFrame1" name="workFrame1" allowtransparency=true frameborder="0" -->
<!-- 				src="<s:property value='mainPageUrl' />"  -->
<!-- 				style="width:100%;height: 100%;overflow: hidden; border: 0px;text-align: center;"> </iframe> -->
		</div>
	</div>
</body>
</html>
