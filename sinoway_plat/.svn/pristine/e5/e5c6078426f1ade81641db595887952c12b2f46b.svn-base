<%--
审批拒绝人员列表
@date         2013-9-18         
@author       陈皇              
@version      1.0                
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>人员复核页面</title>
<%@ include file="/common/wf_import.jsp"%>
<%@ include file="/windforce/common/jsp/wfInternalImport.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		var deskbodyWidth = top.document.getElementById("rightindex").style.width;
			deskbodyWidth = deskbodyWidth.substr(0, deskbodyWidth.length - 2);
		var poIndexDiv = $("#poIndexDiv");
		poIndexDiv.css("width", deskbodyWidth + "px");
	});
</script>
</head>
<body class="body_background" style="width:auto;height:auto;overflow:hidden;">
	<div id="poIndexDiv" style="height:auto;">
		<h2 style="margin-top:14px;margin-bottom:14px;padding-left:10px;height:12px;font-size: 14px; font-weight: bold;">
			审批拒绝人员列表(共
			<s:property value="peopleInfoList.size()" />
			条)
		</h2>
		<table width="99%" align="left" cellpadding="0" cellspacing="0">
			<tr>
				<td width="7%" height="26" align="center" class="font_colors07">序号</td>
				<td width="15%" align="center" class="font_colors07">用户姓名</td>
				<td width="15%" align="center" class="font_colors07">用户代码</td>
				<td width="7%" align="center" class="font_colors07">性别</td>
				<td width="25%" align="center" class="font_colors07">所属机构</td>
				<td width="30%" align="center" class="font_colors07">所属岗位</td>
			</tr>
			<tbody style="font-size: 13px">
				<s:if test="peopleInfoList.size() ==0">
					<tr style="line-height:20px;" bgcolor="#CBD6E0">
						<td colspan="8" align="center">没有审批拒绝人员</td>
					</tr>
				</s:if>
				<s:else>
					<s:iterator value="peopleInfoList" var="peopleInfo" status="index">
						<tr style="line-height:20px;"
							<s:if test="#index.index%2 ==0">bgcolor="#CBD6E0"</s:if>
							<s:if test="#index.index%2 ==1">bgcolor="#FFFFFF"</s:if>>
							<td align="center">
								<s:property value="#index.index + 1" />
							</td>
							<td align="center">
								<s:property value="#peopleInfo.peopleName" />
							</td>
							<td align="center">
								<s:property value="#peopleInfo.peopleCode" />
							</td>
							<td align="center">
								<s:if test="#peopleInfo.peopleGender == 0">男</s:if>
								<s:if test="#peopleInfo.peopleGender == 1">女</s:if>
							</td>
							<td align="center">
								<s:property value="#peopleInfo.orgNo" />
							</td>
							<td align="center">
								<s:iterator value="peopleRoleGroupMap.get(#peopleInfo.sid)" var="roleGroupName" status="index">
									<s:property value="#roleGroupName" />
									<s:if test="!#index.Last">,</s:if>
									<s:if test="(#index.index+1)%6 ==0">
										<br />
									</s:if>
								</s:iterator>
							</td>
						</tr>
					</s:iterator>
				</s:else>
			</tbody>
		</table>
	</div>
</body>
</html>