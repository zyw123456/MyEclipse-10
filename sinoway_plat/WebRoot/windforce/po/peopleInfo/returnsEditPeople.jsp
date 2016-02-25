<%--
  机构人员退回修改页面                 
@date          2013-9-23       
@author       chenhuang       
@version       1.0                
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
<title>修改用户页面</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/windforce/common/css/core.css" />
<%@ include file="/common/wf_import.jsp"%>

<script type="text/javascript">
function onSavePeople() {
	var sexValue = document.getElementById("peopleInfo.peopleGender").value;
	var userCode = document.forms[0].elements["peopleInfo.peopleCode"].value;
	var userName = document.forms[0].elements["peopleInfo.peopleName"].value;
	var userOrg = document.forms[0].elements["orgNo"].value;
	var sid = document.forms[0].elements["hidSid"].value;
	if (top.isNull(userName)) {
		top.wfAlert("人员名称不能为空!");
		return false;
	}
	if (!top.isGeneralName(userName)) {
		top.wfAlert("人员名称含有非法字符!");
		return false;
	}
	if (top.isNull(userCode)) {
		top.wfAlert("用户代码不能为空!");
		return false;
	}
	if (!top.isAlphaAndDigits(userCode)) {
		top.wfAlert("用户代码只能是字母或数字!");
		return false;
	}
	// 循环遍历角色组复选框，将选中的值拼接成以逗号分割的字符串
	var tags = document.getElementsByTagName("input");
	var roleGroups = "";
	for (var i = 0; i < tags.length; i++) {
		if (tags[i].type == "checkbox" && tags[i].checked == true) {
			roleGroups += tags[i].value + ",";
		}
	}
	// 去掉最后一个逗号
	if (roleGroups != "") {
		roleGroups = roleGroups.substring(0, roleGroups.length - 1);
	}

	var orgId = '<s:property value="orgSid"/>';
	var dataParaments = {
		"peopleInfo.peopleCode" : userCode,
		"peopleInfo.peopleName" : userName,
		"orgNo" : userOrg,
		"userRoleGroups" : roleGroups,
		"peopleInfo.sid" : sid,
		"peopleInfo.peopleGender" : sexValue
	};
	$.post('<%=path%>/approvePeopleAction_saveEditPeople.action', dataParaments, function(data) {
		var dataObj = $.parseJSON(data);
		if(dataObj.status == "0000"){
			top.wfAlert(dataObj.ajaxData);
			closeShowPage();
			//重新加载退回修改页面
			window.parent.document.getElementById("workFrame").src = "<%=path%>/approvePeopleAction_initReturnPage.action";
		} else {
			top.wfAlert(dataObj.ajaxData);
		}
	});
}

function setParentOrg() {
	// 获取下拉框对象
	var parentOrgs = document.forms[0].elements["parentCode"];
	var currTypeId = document.forms[0].elements["orgType"].value;
	parentOrgs.length = 0;
	// 通过ajax过滤父机构
	$.getJSON('<%=path%>/getParentOrg.action?typeId=' + currTypeId, function(data) {
		// 动态填充下拉框数据
		for (org in data) {
			parentOrgs.add(new Option(data[org].orgName, data[org].orgNo));
		}
	});
}

// 替换角色组
function setRoleGroup() {
	var orgId = document.forms[0].elements["orgNo"].value;
	$.post('<%=path%>/getOrgRoleGroup.action', "orgId=" + orgId, function(data) {
		$("#roleGroups").html(data);
	});
}
</script>
</head>
<body style="font-size:12px;background-color: #EBEBE6;">
	<form name="myform">
	<div id="login_bg" style="filter:alpha(opacity:75);opacity:0.75;"></div>
	<div id="login_nov">
		<table>
			<tr>
				<td width="20%" align="center" class="font_colors06">用户姓名</td>
				<td width="30%">
					<label> <input type="text" name="peopleInfo.peopleName" value="<s:property value='peopleInfo.peopleName'/>"
							maxlength="50" /> </label>
				</td>
				<td width="20%" align="center" class="font_colors06">用户代码</td>
				<td width="30%">
					<input type="text" name="peopleInfo.peopleCode" value="<s:property value='peopleInfo.peopleCode'/>" maxlength="20"
						class="code_transform" />
				</td>
			</tr>
			<tr>
				<td align="center" class="font_colors06">所属机构</td>
				<td>
					<label> <s:select onchange="setRoleGroup();" list="#session.parentOrgList" name="orgNo" listKey="orgNo"
							listValue="orgName" style="width:150px;" disabled="true"></s:select> </label>
				</td>
				<td align="center" class="font_colors06">性别</td>
				<td class="font_colors06">
					<select name="peopleInfo.peopleGender" id="peopleInfo.peopleGender" style="width:155px">
						<option value="0">男</option>
						<option value="1">女</option>
					</select>
				</td>
			</tr>
			<tr>

				<td align="center" valign="top" class="font_colors06">岗位列表</td>
				<td colspan="3" id="roleGroups" valign="middle" class="font_colors06" height="10px;"
					style="padding-right:6px;padding-top:7px;padding-left:5px">
					<div
						style="height: 130px;overflow-y: auto;background:#FFFFFF; padding-top: 15px; text-align:left; vertical-align: left;">
						<s:iterator value="roleGroupList" var="roleGroup" status="index">
							<s:if test="excludeGroupMap.get(#roleGroup.sid) != null && excludeGroupMap.get(#roleGroup.sid)">
								<span style="display: none">
							</s:if>
							<s:else>
								<span>
							</s:else>
							<s:set id="isContain" value="false"></s:set>
							<!--  将用户所属角色初始化选中 -->
							<s:iterator value="peopleRoleGroupList" var="roleGroupMap" status="index">
								<s:if test="#roleGroupMap.sid == #roleGroup.sid">
									<s:set id="isContain" value="true"></s:set>
								</s:if>
							</s:iterator>
							<s:if test="#isContain == true">
								<input type="checkbox" name="userRoles" checked="checked" value="<s:property value='#roleGroup.sid'/>" />
								<s:property value='#roleGroup.roleGroupName' />
							</s:if>
							<s:if test="#isContain == false">
								<input type="checkbox" name="userRoles" value="<s:property value='#roleGroup.sid'/>" />
								<s:property value='#roleGroup.roleGroupName' />
							</s:if>
							<s:set id="isContain" value="false"></s:set>
							<s:if test="#index.index != 0 && #index.index%2 == 0">
								<br />
							</s:if>
							</span>
						</s:iterator>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">&nbsp;</td>
				<td colspan="3" align="right">
					<input type="hidden" value="<s:property value='peopleInfo.sid'/>" name="hidSid" />
					<input type="button" onclick="onSavePeople();" value="保存" class="submit_but04" />
					<input type="reset" value="关闭" onclick="closeShowPage();" class="submit_but04" />
					&nbsp;
				</td>
			</tr>
		</table>
	</div>
	</form>
	<script type="text/javascript">
		var sex = '<s:property value="peopleInfo.peopleGender"/>';
		var temp = document.getElementById("peopleInfo.peopleGender");
		temp.value = sex;
		top.stopProcess();
	</script>
</body>
</html>


