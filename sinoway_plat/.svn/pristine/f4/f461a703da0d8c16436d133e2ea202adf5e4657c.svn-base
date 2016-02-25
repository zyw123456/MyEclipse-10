
<%@page import="com.yzj.wf.common.util.URLSecurity"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%--
  角色管理页面                
@date          2012/06/17         
@author       蒋正秋           
@version       1.0                
--%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>岗位管理页面</title>

<%@ include file="/common/wf_import.jsp"%>
<%@ include file="/windforce/common/jsp/wfInternalImport.jsp" %>
<script type="text/javascript">
function doDel(operType,operId)
{
var dparameter = "";
var isCheck = "false";
if(operType == "one")
{
dparameter = operId;
}else 
{

var delIds = document.getElementsByName("ids");
for(var i = 0 ; i < delIds.length;i++)
{
if(delIds[i].checked)
{
isCheck = "true";
dparameter+=delIds[i].value+",";
}
}

dparameter = dparameter.substring(0,dparameter.length - 1);
}
if(isCheck == "true" || operType == "one")
{
if(confirm("确定要删除吗？"))
{

var operAuth=new top.OperAuth();
operAuth.operType="deleteRoleGroup";
operAuth.authSuccess=function(){
top.startProcess("正在提交删除请求,请稍等...");
 $.post('<%=path%>/rolegroup_delRoles.action?_t='+ new Date().getTime(), "delIds="+dparameter, function(data) {
  top.wfAlert(data);
 if(data == "删除成功！")
 {
 top.changeProcessTitle("删除完毕,正在刷新界面...");
 $.post('<%=path%>/rolegroup_ajaxRoleList.action?_t='+ new Date().getTime(), null, function(data) {
 $("#refreshData").html(data);
 });
 document.getElementById("roleOperate").src="<%=path%>/rolegroup_toDetail.action?detailSid=";
 }else{
 top.stopProcess();
 }
 });
 };
operAuth.auth();
 }
 }else{
 top.wfAlert("请选择要删除的岗位！");
 }
}

function addRoleGroup(){
top.startProcess("正在初始化新增岗位界面,请稍等...");
document.getElementById("roleOperate").src="<%=path%>/rolegroup_initAdd.action";
}
function modifyRoleGroup(sid){
top.startProcess("正在初始化岗位编辑界面,请稍等...");
document.getElementById("roleOperate").src="<%=path%>/rolegroup_initUpdate.action?editId="+sid;
}

// 刷新角色组列表
function refreshRoleGroup()
{
 $.post('<%=path%>/rolegroup_ajaxRoleList.action?_t='+ new Date().getTime(), null, function(data) {
//document.getElementById("refreshData").innerHTML = data;
$("#refreshData").html(data);
 });
}

//搜索岗位
function searchRoleGroup(){
	var el = document.getElementById("role_group_search_key");
	var searchKey = el.value;
	if(searchKey == "请输入岗位名"){
		searchKey = "";
	}
	if(searchKey !=null && "" != searchKey){
		if (!top.isGeneralName(searchKey)) {
			alert("岗位名称含有非法字符！");
			return ;
		} 
	}
	$.post('<%=path%>/rolegroup_searchRoleGroup.action', {"gourpInfo.roleGroupName" : searchKey}, function(data) {
           $("#refreshData").html(data);
 	});
}

function searchOn(){
var el = document.getElementById("role_group_search_key");
  if (el.value != "")
  {
    el.value = "";
    el.style.color = "";
    }
};
  function searchBlur(){
  var el = document.getElementById("role_group_search_key");
  if (el.value == "")
  {
    el.value = "请输入岗位名";
    el.style.color = "gray";
  }
}

function exportRoleGroups(){
	if(confirm('您确认导出岗位角色对照表?','提示信息')){
		window.location.href =  "<%=path%>/rolegroup_exportRoleGroups.action";
	}
}
$(document).ready(function() {
	var obj = top.document.getElementById("rightindex");
	var height = $(obj).css("height");
		height = height.substr(0, height.length - 2) -5;
	var width = $(obj).css("width");
		width = width.substr(0, width.length - 2);
	$("#role_group_body").css("height", height + "px");
	$("#role_group_body").css("width", width + "px");
	
	width = width - 360;
	$("#roleOperate").css("height", height + "px");
	$("#roleOperate").css("width", (width) + "px");	
});
</script>
</head>
<body id="role_group_body" style="background-color: transparent;overflow:hidden;">
	<%
		String crud = request.getParameter("cud");
		List<Boolean> roleGroupCrudList = URLSecurity.loadCRUD(crud, 4);
		session.setAttribute("ROLE_GROUP_CRUD", roleGroupCrudList);
	%>
	<input id="moudleName" type="hidden" value="岗位管理" />
	<div>
		<div id="am_left" style="position:absolute; width: 340px;height:100%;overflow-y:auto;">
			<table width="100%">
				<tr>
					<td align="left" width="155px">
						<%
							if (roleGroupCrudList.get(0)) {
						%>
						<input type="button" onclick="addRoleGroup();" class="submit_but45_24"
							value="新增" />
						<%
							}
							if (roleGroupCrudList.get(2)) {
						%>
						<input type="button" onclick="doDel('all','');" class="submit_but45_24"
							value="删除" />
						<%
							}
							if (roleGroupCrudList.get(3)) {
						%>
						<input type="button" onclick="exportRoleGroups();" class="submit_but45_24"
							value="导出" />
						<%
							}
						%>
					</td>
					<td align="right">
						<input type="text" id="role_group_search_key"
							style="color: gray;width:85px;" value="请输入岗位名" onblur="searchBlur()"
							onfocus="searchOn()" maxlength="20" />
						<input type="button" onclick="searchRoleGroup();" class="submit_but45_24"
							value="搜索" />
					</td>
				</tr>
			</table>
			<div id="refreshData">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="10%" align="center" class="font_colors07"></td>
						<td width="50%" height="26" align="center" class="font_colors07">岗位名</td>
						<%--    <td width="15%" align="center"  class="font_colors07">状态</td>--%>
						<td width="40%" align="center" class="font_colors07">操作</td>

					</tr>
					<s:iterator id="roleGroup" value="allRoleGroupList" status="index">
						<tr id="grid<s:property value="#index.index%2 ==0 ? 'Even' : 'Odd'" />" style="background-color:#FFFFFF;">
							<td align="center" class="border_bottom01">
								&nbsp;
								<%
									if (roleGroupCrudList.get(2)) {
								%>
								<s:if test="#roleGroup.sid != 'supergroup'">
									<input type="checkbox" name="ids"
										value='<s:property value="#roleGroup.sid"/>' />
								</s:if>
								<%
									}
								%>
							</td>
							<td height="28" align="center" class="border_bottom01">
								&nbsp;
								<a
									href="<%=path%>/rolegroup_toDetail.action?detailSid=<s:property value='#roleGroup.sid'/>"
									target="roleOperate" onclick="top.startProcess('正在获取岗位信息,请稍等...');">
									<s:property value="#roleGroup.roleGroupName" />
								</a>
							</td>
							<%--    <td align="center" class="border_bottom01" >&nbsp;<s:if test="#roleGroup.roleGroupState == 0">正常</s:if><s:if test="#roleGroup.roleGroupState == 1">冻结</s:if></td>--%>

							<td width="15%" align="left" class="border_bottom01">
								<%
									if (roleGroupCrudList.get(1)) {
								%>
								<input type="button"
									onclick="modifyRoleGroup('<s:property value='#roleGroup.sid'/>');"
									class="submit_but45_24" value="修改" />
								<%
									}
										if (roleGroupCrudList.get(2)) {
								%>
								<s:if test="#roleGroup.sid != 'supergroup'">
									<input type="button"
										onclick="doDel('one','<s:property value='#roleGroup.sid'/>');"
										class="submit_but45_24" value="删除" />
								</s:if>
								<%
									}
								%>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<div id="main" style="margin-left:360px;margin-top:5px;height:auto;">
			<iframe id="roleOperate" name="roleOperate" allowtransparency=true
				frameborder="0" src="<%=path%>/rolegroup_toDetail.action?detailSid="
				style="width: auto;height:100%;"
				frameborder="0"></iframe>
		</div>
	</div>
</body>
</html>


