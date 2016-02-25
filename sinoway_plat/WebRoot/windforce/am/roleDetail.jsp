<%@page import="com.yzj.wf.core.model.po.common.PODefine"%>
<%@page import="com.yzj.wf.core.model.am.common.AMDefine"%>
<%@page import="com.yzj.wf.core.model.am.RoleGroupInfo"%>
<%@page import="com.yzj.wf.core.model.am.RoleInfo"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="com.yzj.wf.core.model.po.OrgType"%>
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
  角色信息详细              
@date          2012/06/17         
@author       蒋正秋           
@version       1.0                
--%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>角色管理页面</title>
<link type="text/css" rel="stylesheet"
	href="<%=path%>/windforce/common/css/ztree_ext.css" />
<%@ include file="/common/wf_import.jsp"%>
<%@ include file="/windforce/common/jsp/wfInternalImport.jsp" %>
<script type="text/javascript"
	src="<%=path%>/common/js/jquery/ztree/jquery.ztree.all-3.1.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$.getJSON("roles_buildPowerTree.action?_t="
			+ new Date().getTime(), function(json) {
		var setting = {
			callback : {
				onMouseDown : zTreeOnMouseDown
			/*给所有节点添加了鼠标左键按下的监听*/
			}
		};
		var zNodes = json;

		$.fn.zTree.init($("#tree_rolequery"), setting, zNodes);
		function zTreeOnMouseDown(event, treeId, treeNode) {

		};
	});
	var obj = top.document.getElementById("rightindex");
	var height = $(obj).css("height");
		height = height.substr(0, height.length - 2);
	var novHeight = height -5;
	$("#role_nov_nr").css("height", novHeight + "px");	
		novHeight = novHeight -120 -25;
		if(novHeight>300){
			novHeight = 300;
		}
	$("#role_nov_nr .uoon").css("height", (novHeight-5) + "px");
	$("#role_nov_nr .uoon .ztree").css("height", (novHeight -15) + "px");
});
	var isrefresh = '<s:property value="refreshTree"/>';
	if (isrefresh == "true") {
		top.refreshRoleList();
	}
</script>
</head>
<body style="background-color: transparent;overflow:hidden;" class="defaulFontColor">
	<div id="role_nov_nr" style="width: 100%;;overflow-y:auto;">
		<table width="96%" border="0" align="center" style="table-layout:fixed;">
			<tr style="height:15px;">
				<td class="leftTd">角色名：</td>
				<td class="rightTd">
					<s:property value="detail.roleName" />
				</td>
			</tr>
			<tr style="height:15px;">
				<td class="leftTd">描述：</td>
				<td class="rightTd">
					<s:property value="detail.memo" />
				</td>
			</tr>
			<tr style="height:15px;">
				<td class="leftTd">岗位：</td>
				<td class="rightTd">
					<label>
						<s:if test="detail.roleGroupInfos.size() < 1">暂无</s:if>
						<s:if test="detail.roleGroupInfos.size() > 0">
							<s:iterator id="roleGroup" value="detail.roleGroupInfos">
								<s:if test="#roleGroup.value.roleGroupState ==  0">
									<s:property value="#roleGroup.value.roleGroupName" />&nbsp;&nbsp;
     								</s:if>
							</s:iterator>
						</s:if>
					</label>
				</td>
			</tr>
			<tr style="height:15px;">
				<td class="leftTd">互斥角色：</td>
				<td class="rightTd">
					<label>
						<s:if test="detail.mutexRoleInfos.size() < 1">暂无</s:if>
						<s:if test="detail.mutexRoleInfos.size() > 0">
							<s:iterator value="detail.mutexRoleInfos" id="mutex">
     						  &nbsp;<a href="<%=path%>/roles_roleDetail.action?detailSid=<s:property value='#mutex.value.sid'/>">
									<s:property value="#mutex.value.roleName" />
								</a>
							</s:iterator>
						</s:if>
					</label>
				</td>
			</tr>
			<tr style="height:15px;">
				<td height="10" colspan="2"></td>
			</tr>
			<tr style="height:15px;">
				<td colspan="2">资源列表:</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="uoon" style="overflow: auto;position: relative; height:250px;">
						<s:if test="detail.powerInfos.size() < 1">
						该角色暂时无资源！
						</s:if>
						<s:if test="detail.powerInfos.size() > 0">
							<ul id="tree_rolequery" class="ztree">
							</ul>
						</s:if>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		top.stopProcess();
	</script>
</body>
</html>


