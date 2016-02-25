<%@page import="com.yzj.wf.common.util.URLSecurity"%>
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
<base href="<%=basePath%>" />
<title>机构权限管理子系统</title>

<%@ include file="/common/wf_import.jsp"%>
<%@ include file="/windforce/common/jsp/wfInternalImport.jsp" %>
<script type="text/javascript">
// 初始化下拉框数据
$.ajax({
	type : "get",
	url : "<%=path%>/initAddOrganize_initAddOrganize.action?_t="+ new Date().getTime(),
	cache : false,
	async : false,
	success : function(xmlobj) {
	}
});
// 点击单个机构信息
function frameForward(url,sid){
main_right.location.href =url; // main_right 是指主页面下的iframe id.
if(right.location.href.indexOf('editOrganize_editInit.action') > 0)
{
editOrg("<%=path%>/editOrganize_editInit.action?_t="+ new Date().getTime() + "&orgNo="+sid);
}
}
// 点击添加机构按钮
function right_addOrg(url){

right.location.href = url;
expendRight();
}
// 添加完机构后刷新左菜单
function refreshLeft()
{
left.location.href = "<%=path%>/organizeAction_showOrgnizeIndexPage.action";
}
// 点击修改机构按钮
function editOrg(url)
{
right.location.href = url;
expendRight();
}
// 点击删除机构按钮
function delOrg(url)
{
 $.post(url, null, function(data) {
    top.wfAlert(data);
    if(data == "删除成功!")
    {
    // 刷新左页面
    left.location.href = "<%=path%>/organizeAction_showOrgnizeIndexPage.action";
    main_right.location.href =  "<%=path%>/organizeDetail.action?sid=";
    }
 });
}
function relDetail(currId)
{
main_right.location.href = "<%=path%>/organizeDetail.action?sid="+currId;
//main_right.location.href = "<%=path%>/organizeDetail.action?sid="+currId;
}

// 点击添加用户按钮
function right_addPeople(url){

right.location.href = url;
expendRight();
}
// 属性用户列表
function refreshUserList()
{
main_right.location.href = "<%=path%>/userList.action";
}
// 点击修改用户按钮
function editUser(url){
expendRight();
right.location.href = url;
}


	// 点击删除人员按钮
function delPeople(url,orId)
{
 $.post(url, null, function(data) {
    top.wfAlert(data);
    // 刷新用户列表
    main_right.location.href =  "<%=path%>/userList.action?orgId="
					+ orId;
			drawRight();
		});
	}
	// 点击用户详细
	function right_peopleDetail(url) {

		right.location.href = url;
		expendRight();
	}
	$(document).ready(
			function() {
				var poTreeDivWidht = 285;//左侧机构树的宽度

				var centerAreaWidth = getCenterAreaWidth();
				//区域高度修改为根据外层框架workFrame高度计算
				//var centerAreaHeight = getCenterAreaHeight();
				var centerAreaHeight = getOuterCenterAreaHeight();
				var poIndexDiv = $("#poIndexDiv");
				poIndexDiv.css("width", centerAreaWidth + "px");
				poIndexDiv.css("height", centerAreaHeight + "px");
				poIndexDiv.css("overflow", "hidden");
				var poTreeDiv = $("#ry_noc");
				poTreeDiv.css("width", poTreeDivWidht + "px");
				poTreeDiv.css("height", centerAreaHeight + "px");
				var poDetailDiv = $("#ry_boc");
				poDetailDiv.css("height", centerAreaHeight + "px");
				poDetailDiv.css("width", (document.getElementById("poIndexDiv").offsetWidth - poTreeDivWidht-5)
						 + "px");
			});

	function getNumberFromPx(str) {
		return str.substring(0, str.length - 2);
	}
</script>
</head>
<body class="body_background" style="overflow: hidden;">
	<%
		String crud = request.getParameter("cud");
		List<Boolean> poCrudList = URLSecurity.loadCRUD(crud, 13);
		session.setAttribute("PO_CRUD", poCrudList);
	%>
	<div id="poIndexDiv">
		<input id="moudleName" type="hidden" value="机构人员管理" />
		<div id="ry_noc">
			<iframe frameborder="0" name="left" id="poTreeFrame"
				src="<%=path%>/organizeAction_showOrgnizeIndexPage.action"
				style="width:100%; height:100%;background-color: #EBEBE6;overflow: hidden;"></iframe>
		</div>
		<div id="ry_boc">
			<iframe frameborder="0" id="main_right" name="main_right"
				src="<%=path%>/organizeDetail.action?"
				style="width:100%; height:100%;background-color: transparent;overflow: hidden;"></iframe>
		</div>
	</div>
</body>
<script type="text/javascript">
	/*document.getElementById("ry_noc").style.height = top.getWindowHeight()
			- top.getTopSize() - 10 + "px";
	document.getElementById("ry_boc").style.height = top.getWindowHeight()
			- top.getTopSize() - 10 + "px";*/
</script>
</html>