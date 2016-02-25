<%--
  修改人员页面                 
@date          2014/12/02         
@author        lishuiye              
@version       1.5                
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/windforce/common/jsp/wfInternalImport.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改人员页面</title>
<script type="text/javascript">
	function onSavePeople() {
		var sexValue = document.getElementById("peopleInfo.peopleGender").value;
		var userCode = document.forms[0].elements["peopleInfo.peopleCode"].value;
		var userName = document.forms[0].elements["peopleInfo.peopleName"].value;
		var phone = document.forms[0].elements["peopleInfo.phone"].value;
		var email = document.forms[0].elements["peopleInfo.email"].value;
		var userOrg = document.forms[0].elements["orgNo"].value;
		var sid = document.forms[0].elements["hidSid"].value;
		var peopleType = $("#peopleType").val();//用户类型
		var peopleLevel = $("#peopleLevel").val();//主管级别
		var peopleIdCard = $("#peopleIdCard").val();//身份证号
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
		if ((!top.isNull(phone)) && (!top.isDigits(phone))) {
			top.wfAlert("电话号码只能输入数字!");
			return false;
		}
		if (top.enableRandomPwdForResetPwd && (top.isNull(email))) {
			top.wfAlert("电子邮箱不能为空!");
			return false;
		}

		if ((top.isNull(peopleType))) {
			top.wfAlert("用户类型不能为空!");
			return false;
		}
		
		if ((top.isNull(peopleLevel))) {
			top.wfAlert("主管级别不能为空!");
			return false;
		}
		
		if ((!top.isNull(email)) && (!top.isEmail(email))) {
			top.wfAlert("电子邮箱不符合规则!");
			return false;
		}
		if (top.isNull(peopleIdCard)) {
			top.wfAlert("身份证号不能为空!");
			return false;
		}
		if ((!top.isNull(peopleIdCard)) && (!top.isIdCardNo(peopleIdCard))) {
			top.wfAlert("身份证号不符合规则!");
			return false;
		}
		// 循环遍历角色组复选框，将选中的值拼接成以逗号分割的字符串
		var tags = document.getElementsByTagName("input");
		var roleGroups = "";
		for ( var i = 0; i < tags.length; i++) {
			if (tags[i].type == "checkbox" && tags[i].checked == true) {
				roleGroups += tags[i].value + ",";
			}
		}
		// 去掉最后一个逗号
		if (roleGroups != "") {
			roleGroups = roleGroups.substring(0, roleGroups.length - 1);
		}

		var orgId = '<s:property value="orgSid"/>';
		var operAuth = new top.OperAuth();
		operAuth.operType = "editPeople";
		operAuth.authSuccess = function(authUserCode) {
			var dataParaments = {
				"peopleInfo.peopleCode" : userCode,
				"peopleInfo.peopleName" : userName,
				"orgNo" : userOrg,
				"userRoleGroups" : roleGroups,
				"peopleInfo.sid" : sid,
				"peopleInfo.peopleGender" : sexValue,
				"authUserCode" : authUserCode,
				"peopleInfo.phone" : phone,
				"peopleInfo.email" : email,
				"peopleInfo.peopleType" : peopleType,
				"peopleInfo.peopleLevel" : peopleLevel,
				"peopleInfo.peopleIdCard" : peopleIdCard
			};
			top.startProcess("正在提交人员信息,请稍等...");
			// 这里提交用ajax目的是为了不刷新页面，好控制iframe层次的显示与隐藏
			$.post(top.ctx + "/editUser_editPeople.action?_t="
					+ new Date().getTime(), dataParaments, function(data) {
				// 添加完后隐藏右栏
				if (data == "1") {
					// 刷新用户列表页面
					top.closeShowPage();
					// 刷新用户列表页面
					top.changeProcessTitle("人员信息提交完毕,正在刷新界面...");
					top.frameForward(top.ctx + "/organizeDetail.action?sid="
							+ orgId, orgId);
				} else {
					top.stopProcess();
					top.wfAlert(data);
				}

			});
		};
		operAuth.auth();
	}
	function setParentOrg() {
		// 获取下拉框对象
		var parentOrgs = document.forms[0].elements["parentCode"];
		var currTypeId = document.forms[0].elements["orgType"].value;
		parentOrgs.length = 0;
		// 通过ajax过滤父机构
		$.getJSON(top.ctx + "/getParentOrg.action?_t=" + new Date().getTime()
				+ '&typeId=' + currTypeId, function(data) {
			// 动态填充下拉框数据
			for (org in data) {
				parentOrgs.add(new Option(data[org].orgName, data[org].orgNo));
			}
		});
	}
	// 替换角色组
	function setRoleGroup() {
		var orgId = document.forms[0].elements["orgNo"].value;
		$.post(top.ctx + "/getOrgRoleGroup.action?_t=" + new Date().getTime(),
				"orgId=" + orgId, function(data) {
					$("#roleGroups").html(data);
				});
	}
</script>
</head>
<body style="font-size:12px;background-color: #EBEBE6;">
	<form name="myform" action="#">
		<div id="login_bg" style="filter:alpha(opacity:75);opacity:0.75;"></div>
		<div id="login_nov">
			<table>
				<tr>
					<td width="20%" align="center" class="font_colors06">用户姓名</td>
					<td width="30%">
						<label>
							<input type="text" name="peopleInfo.peopleName"
								value="<s:property value='peopleInfo.peopleName'/>" maxlength="50" />
						</label>
					</td>
					<td width="20%" align="center" class="font_colors06">用户代码</td>
					<td width="30%">
						<input type="text" name="peopleInfo.peopleCode"
							value="<s:property value='peopleInfo.peopleCode'/>" maxlength="20"
							class="code_transform" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="center" class="font_colors06">电话号码</td>
					<td width="30%">
						<label>
							<input type="text" name="peopleInfo.phone"
								value="<s:property value='peopleInfo.phone'/>" maxlength="20" />
						</label>
					</td>
					<td width="20%" align="center" class="font_colors06">电子邮箱</td>
					<td width="30%">
						<input type="text" name="peopleInfo.email"
							value="<s:property value='peopleInfo.email'/>" maxlength="64"
							class="code_transform" />
					</td>
				</tr>
				<tr>
					<td align="center" class="font_colors06">所属机构</td>
					<td>
						<label>
							<s:select onchange="setRoleGroup();" list="#session.parentOrgList"
								name="orgNo" listKey="orgNo" listValue="orgName" style="width:150px;"
								disabled="true"></s:select>
						</label>
					</td>
					<td align="center" class="font_colors06">性别</td>
					<td class="font_colors06">
						<select name="peopleInfo.peopleGender" id="peopleInfo.peopleGender"
							style="width:155px">
							<option value="0">男</option>
							<option value="1">女</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" class="font_colors06">用户类型</td>
					<td>
						<select id="peopleType" style="width:155px">
							<s:if test="peopleInfo.peopleType == 0">
								<option value="0" selected="true">一般用户</option>
								<option value="1">管理用户</option>
							</s:if>
							<s:elseif test="peopleInfo.peopleType == 1">
								<option value="0">一般用户</option>
								<option value="1" selected="true">管理用户</option>
							</s:elseif>
							<s:else>
								<option value="" selected="true"></option>
								<option value="0">一般用户</option>
								<option value="1">管理用户</option>
							</s:else>
						</select>
					</td>
					<td align="center" class="font_colors06">主管级别</td>
					<td class="font_colors06">
						<select id="peopleLevel" name="peopleLevel" style="width:155px">
							<s:if test="peopleInfo.peopleLevel == 0">
								<option value="0" selected="true">非主管</option>
								<option value="3">主管</option>
							</s:if>
							<s:elseif test="peopleInfo.peopleLevel == 3">
								<option value="0">非主管</option>
								<option value="3" selected="true">主管</option>
							</s:elseif>
							<s:else>
								<option value="" selected="true"></option>
								<option value="0">非主管</option>
								<option value="3">主管</option>
							</s:else>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" class="font_colors06">身份证号</td>
					<td>
						<input type="text" id="peopleIdCard"
							value="<s:property value='peopleInfo.peopleIdCard'/>" />
					</td>
				</tr>
				<tr>
					<td align="center" valign="top" class="font_colors06">岗位列表</td>
					<td colspan="3" id="roleGroups" valign="middle" class="font_colors06"
						height="10px;" style="padding-right:6px;padding-top:7px;padding-left:5px">
						<div
							style="height: 130px;overflow-y: auto;background:#FFFFFF; padding-top: 15px; text-align:left; vertical-align: left;">
							<s:iterator value="roleGroupList" var="roleGroup" status="index">
								<s:if
									test="excludeGroupMap.get(#roleGroup.sid) != null && excludeGroupMap.get(#roleGroup.sid)">
									<span style="display: none">
								</s:if>
								<s:else>
									<span>
								</s:else>
								<s:set id="isContain" value="false"></s:set>
								<!--  将用户所属角色初始化选中 -->
								<s:iterator value="peopleRoleGroupList" var="roleGroupMap"
									status="index">
									<s:if test="#roleGroupMap.sid == #roleGroup.sid">
										<s:set id="isContain" value="true"></s:set>
									</s:if>
								</s:iterator>
								<s:if test="#isContain == true">
									<input type="checkbox" name="userRoles" checked="checked"
										value="<s:property value='#roleGroup.sid'/>" />
									<s:property value='#roleGroup.roleGroupName' />
								</s:if>
								<s:if test="#isContain == false">
									<input type="checkbox" name="userRoles"
										value="<s:property value='#roleGroup.sid'/>" />
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
						<input type="hidden" value="<s:property value='peopleInfo.sid'/>"
							name="hidSid" />
						<input type="button" onclick="onSavePeople();" value="保存"
							class="submit_but04" />
						<input type="reset" value="关闭" onclick="closeShowPage();"
							class="submit_but04" />
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


