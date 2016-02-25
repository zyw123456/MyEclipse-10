
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
<title>角色管理页面</title>

<%@ include file="/common/wf_import.jsp"%>
<%@ include file="/windforce/common/jsp/wfInternalImport.jsp" %>

<script type="text/javascript">
function doDel(operType,operId)
{
var dparameter = "";
var delIds = document.getElementsByName("ids");
for(var i = 0 ; i < delIds.length;i++)
{
if(delIds[i].checked)
{
dparameter+=delIds[i].value+",";
}
}
if(dparameter == "" && operType != "one")
{
top.wfAlert("请选择要删除的角色！");
return;
}else{
if(confirm("确定要删除吗？"))
{

if(operType == "one")
{
dparameter = operId;
}else 
{

dparameter = dparameter.substring(0,dparameter.length - 1);
}
var operAuth=new top.OperAuth();
operAuth.operType="deleteRole";
operAuth.authSuccess=function(){
 top.startProcess("正在提交删除请求,请稍等...");
 $.post('<%=path%>/roles_delRoles.action?_t='+ new Date().getTime(), "delIds="+dparameter, function(data) {
 top.wfAlert(data);
 if(data == "删除成功！")
 {
 top.changeProcessTitle("删除完毕,正在刷新界面...");
 $.post('<%=path%>/roles_ajaxRoleList.action?_t='+ new Date().getTime(), null, function(data) {
           $("#refreshData").html(data);
 });
 document.getElementById("roleOperate").src="<%=path%>/roles_roleDetail.action?detailSid=";
 }else{
  top.stopProcess();
 }
 });
 };
operAuth.auth();
 }
 }
}
 
 //新增角色
function addRole(){
top.startProcess("正在初始化角色新增界面,请稍等...");
document.getElementById("roleOperate").src="<%=path%>/roles_initAddRole.action";
}

//修改角色
function modifyRole(sid){
top.startProcess("正在初始化模块修改界面,请稍等...");
document.getElementById("roleOperate").src="<%=path%>/roles_initUpdate.action?editId="+sid;
}
// 刷新角色列表
function refreshRole()
{
 $.post('<%=path%>/roles_ajaxRoleList.action?_t='+ new Date().getTime(), null, function(data) {
           $("#refreshData").html(data);
 });
}
//搜索角色
function searchRole(){
	var searchKey = document.getElementById("role_search_key").value;
	if(searchKey == "请输入角色名"){
		searchKey = "";
	}
	if(searchKey != null && "" != searchKey){
		if (!top.isGeneralName(searchKey)) {
			alert("角色名称含有非法字符！");
			return ;
		} 
	}
	$.post('<%=path%>/roles_searchRole.action', {"roleInfo.roleName" : searchKey}, function(data) {
           $("#refreshData").html(data);
 	});
}

function searchOn(){
var el = document.getElementById("role_search_key");
  if (el.value != "")
  {
    el.value = "";
    el.style.color = "";
    }
};
  function searchBlur(){
  var el = document.getElementById("role_search_key");
  if (el.value == "")
  {
    el.value = "请输入角色名";
    el.style.color = "gray";
  }
}

function exportRoles(){
	if(confirm('您确认导出角色权限对照表?','提示信息')){
		window.location.href =  "<%=path%>/roles_exportRoles.action";
	}
}
$(document).ready(function() {
	var obj = top.document.getElementById("rightindex");
	var height = $(obj).css("height");
		height = height.substr(0, height.length - 2) -5;
	var width = $(obj).css("width");
		width = width.substr(0, width.length - 2);
	$("#role_body").css("height", height + "px");
	$("#role_body").css("width", width + "px");
	
	width = width - 360;
	$("#amMainDiv").css("height",height+"px");
	$("#roleOperate").css("height", height + "px");
	$("#roleOperate").css("width", (width) + "px");	
});
</script>
</head>
<body id="role_body" style="background-color: transparent;overflow:hidden;">
	<%
		String crud = request.getParameter("cud");
			List<Boolean> roleCrudList = URLSecurity.loadCRUD(crud, 4);
			session.setAttribute("ROLE_CRUD", roleCrudList);
	%>
	<input id="moudleName" type="hidden" value="角色管理" />
	<div id="amMainDiv">
		<div id="am_left" style="position:absolute; width: 340px;height:100%;overflow-y:auto;">
			<table border="0" width="100%">
				<tr>
					<td align="left" width="155px">
						<%
							if (roleCrudList.get(0)) {
						%>
						<input type="button" onclick="addRole()" class="submit_but45_24"
							value="新增" />
						<%
							}
																																																			if (roleCrudList.get(2)) {
						%>
						<input type="button" onclick="doDel('all','');" class="submit_but45_24"
							value="删除" />
						<%
							}
																																																			if (roleCrudList.get(3)) {
						%>
						<input type="button" onclick="exportRoles();" class="submit_but45_24"
							value="导出" />
						<%
							}
						%>
					</td>
					<td align="right">
						<input type="text" id="role_search_key" style="color: gray;width:85px;"
							value="请输入角色名" onblur="searchBlur()" onfocus="searchOn()" maxlength="20" />
						<input type="button" onclick="searchRole();" class="submit_but45_24"
							value="搜索" />
					</td>
				</tr>
			</table>
			<div id="refreshData" style="overflow: auto;">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr align="center">
						<td width="10%" class="font_colors07"></td>
						<td width="50%" height="26" align="center" class="font_colors07">角色名</td>
						<%--<td width="15%" align="center" class="font_colors07">是否可用</td>--%>
						<td width="40%" align="center" class="font_colors07">操作</td>
					</tr>
					<s:iterator id="role" value="roleList" status="index">
						<tr id="grid<s:property value="#index.index%2 ==0 ? 'Even' : 'Odd'" />" style="background-color:#FFFFFF;">
							<td align="center" class="border_bottom01">
								&nbsp;
								<%
									if (roleCrudList.get(2)) {
								%>
								<s:if test="#role.sid != 'superrole'">
									<input type="checkbox" name="ids"
										value='<s:property value="#role.sid"/>' />
								</s:if>
								<%
									}
								%>
							</td>
							<td height="28" align="center" class="border_bottom01">
								&nbsp;
								<a
									href="<%=path%>/roles_roleDetail.action?detailSid=<s:property value='#role.sid'/>"
									target="roleOperate" onclick="top.startProcess('正在获取角色信息,请稍等...');">
									<s:property value="#role.roleName" />
								</a>
							</td><%--
							<td align="center" class="border_bottom01">&nbsp;是</td>
							--%><td align="left" class="border_bottom01">
								<%
									if (roleCrudList.get(1)) {
								%>
								<input type="button"
									onclick="modifyRole('<s:property value='#role.sid'/>')"
									class="submit_but45_24" value="修改" />
								<%
									}
																																																																												if (roleCrudList.get(2)) {
								%>
								<s:if test="#role.sid != 'superrole'">
									<input type="button"
										onclick="doDel('one','<s:property value='#role.sid'/>');"
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
				frameborder="0" src="<%=path%>/roles_roleDetail.action" scrolling="no"
				style="width:auto;"
				frameborder="0"></iframe>
		</div>
	</div>
</body>
</html>


