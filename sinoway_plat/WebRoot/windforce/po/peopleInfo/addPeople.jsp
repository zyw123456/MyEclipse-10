


<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>



<%--
  机构人员添加页面                 
@date          2012/04/25         
@author        蒋正秋              
@version       1.0                
--%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>添加用户页面</title>

<%@ include file="/windforce/common/jsp/wfInternalImport.jsp" %>

<script type="text/javascript">
function onSavePeople(){
var userCode = document.forms[0].elements["userCode"].value;
var userName = document.forms[0].elements["userName"].value;
var phone = document.forms[0].elements["phone"].value;
var email = document.forms[0].elements["email"].value;
var userOrg = document.forms[0].elements["userOrg"].value;
var peopleType = $("#peopleType").val();//用户类型
if(top.isNull(userName)){
	top.wfAlert("用户姓名不能为空!");
	return false;
}
if(!top.isGeneralName(userName)){
	top.wfAlert("用户姓名含有非法字符!");
	return false;
}
if(top.isNull(userCode)){
	top.wfAlert("用户代码不能为空!");
	return false;
}
if(!top.isAlphaAndDigits(userCode)){
	top.wfAlert("用户代码只能是字母或数字!");
	return false;
}
if((!top.isNull(phone)) && (!top.isDigits(phone))){
	top.wfAlert("电话号码只能输入数字!");
	return false;
}
if(top.enableRandomPwdForResetPwd && (top.isNull(email))){
	top.wfAlert("电子邮箱不能为空!");
	return false;
}

if((!top.isNull(email))&&(!top.isEmail(email))){
	top.wfAlert("电子邮箱不符合规则!");
	return false;
}

// 循环遍历角色组复选框，将选中的值拼接成以逗号分割的字符串
var tags = document.getElementsByTagName("input");
var roleGroups = "";
for(var i = 0 ; i < tags.length ; i++)
{
if(tags[i].type == "checkbox" && tags[i].checked == true)
{
  roleGroups += tags[i].value + ",";
}
}
// 去掉最后一个逗号
if(roleGroups != "")
{
roleGroups = roleGroups.substring(0,roleGroups.length-1);
}

var sexValue=document.getElementById("sex").value;
var operAuth=new top.OperAuth();
operAuth.operType="addPeople";
operAuth.authSuccess=function(authUserCode){
var dataParaments = {
	"peopleInfo.peopleCode" : userCode ,
	"peopleInfo.peopleName" : userName ,
	"orgNo" : userOrg ,
	"userRoleGroups" : roleGroups ,
	"peopleInfo.peopleGender" : sexValue,
	"authUserCode":authUserCode,
	"peopleInfo.phone":phone,
	"peopleInfo.email":email,
	"peopleInfo.peopleType":peopleType
	};
	top.startProcess("正在提交人员信息,请稍等...");
    // 这里提交用ajax目的是为了不刷新页面，好控制iframe层次的显示与隐藏
    $.post('<%=path%>/doAddUser.action?_t='+ new Date().getTime(), dataParaments, function(data) {   
	   // 添加完后隐藏右栏
	   if(data == "1")
	   {
	   //alert("添加成功！");
	    // 刷新用户列表页面
	   // top.refreshUserList();
	   // 设置返回值，告诉父页面
	/*    window.returnValue = "success";
	   window.close(); */
	  top.closeShowPage();
	   var orgId = "<s:property value='orgSid'/>";
	   // 刷新用户列表页面
	   top.changeProcessTitle("人员新增完毕,正在刷新界面...");
        top.frameForward("<%=path%>/organizeDetail.action?sid="+orgId,orgId);
	   } else if(data == "2")
	   {
	   top.wfAlert("添加失败，请联系管理员！");
	   top.stopProcess();
	   }
	   else {
	   top.wfAlert(data);
	    top.stopProcess();
	   }
	  
	   });
	  };
	operAuth.auth();
}
function setRoleGroup(flag)
{
var orgId = document.forms[0].elements["userOrg"].value;
//added by chenhuang 20130123,当点击清空按钮时，需重角色组列表
if(flag){
	orgId = "<s:property value='userOrg'/>";
}
$.post("<%=path%>/getOrgRoleGroup.action?_t="+ new Date().getTime(), "orgId=" + orgId, function(
				data) {
			$("#roleGroups").html(data);
		});
	}
	
function tab(nextElementId) {
	var e = event ? event : window.event;
			if (e.keyCode == 13) {
		document.getElementById(nextElementId).focus();
	}
}
$(document).ready(function(){
	//初始化用户类型参数
	var peopleTypeData = top.getSystemParam('peopleType');
	$.each(peopleTypeData,function(index,value){
		 $("#peopleType").append("<option value='"+value.paramName+"'>"+value.paramValue+"</option>");  //添加一项option
	});
});
</script>
</head>
<body style="font-size:12px;background-color: #EBEBE6;">
	<form class="mws-form" name="myform"
		action="<%=path%>/doAddUser.action">
		<div id="login_bg"></div>
		<div id="login_nov">
			<table >
				<tr>
					<td width="20%" align="center" class="font_colors06">用户姓名</td>
					<td width="30%"><input type="text" name="userName" onkeydown="tab('peopleCode')"
						class="mws-textinput" maxlength="50" /></td>
					<td width="20%" align="center" class="font_colors06">用户代码</td>
					<td width="30%"><input type="text" name="userCode" id="peopleCode" 
						class="code_transform" maxlength="20" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="center" class="font_colors06">电话号码</td>
					<td width="30%"><input type="text" name="phone"
						class="mws-textinput" maxlength="20" /></td>
					<td width="20%" align="center" class="font_colors06">电子邮箱</td>
					<td width="30%"><input type="text" name="email"
						class="code_transform" maxlength="64" />
					</td>
				</tr>
				<tr>
					<td align="center" class="font_colors06">所属机构</td>
					<td><label> <s:select onchange="setRoleGroup();" list="#session.parentOrgList" name="userOrg" listKey="orgNo"
								listValue="orgName" style="width:155px;" value="currOrganizeInfo.orgNo" disabled="true"></s:select> </label></td>
					<td align="center" class="font_colors06">用户性别</td>
					<td class="font_colors06">
					<select id="sex" name="sex" style="width:155px">
					<option value="0">男</option>
					<option value="1">女</option>
					</select>
					</td>
				</tr>
				<tr>
					<td align="center" class="font_colors06">用户类型</td>
					<td>
						<select id="peopleType" style="width:155px">
						</select>
					</td>
					<td></td>
					<td>
					</td>
				</tr>
				<tr>

					<td align="center" valign="top" class="font_colors06">岗位列表</td>
					<td  id="roleGroups" colspan="3" valign="middle"
						class="font_colors06" height="10px;" style="padding-right:6px;padding-top:7px;padding-left:5px">
						<div 
							style="height: 130px;overflow-y: auto;background:#FFFFFF; padding-top: 15px; text-align:left; vertical-align: left;">
							<s:iterator value="roleGroupList" var="roleGroup" status="index">
								<s:if test="excludeGroupMap.get(#roleGroup.sid) != null && excludeGroupMap.get(#roleGroup.sid)">
									<span style="display: none">
								</s:if>
								<s:else>
									<span>
								</s:else>
								<input type="checkbox" name="userRoles"
									value="<s:property value='#roleGroup.sid'/>" />
								<s:property value='#roleGroup.roleGroupName' />
							    </span>
							</s:iterator>
						</div>
					</td>
				</tr>

				<tr>
					<td align="center">&nbsp;</td>
					<td colspan="3" align="right">
					<input type="button" onclick="onSavePeople();"
						value="保存" class="submit_but04" /> 
					<input type="reset" value="关闭"  
						onclick="closeShowPage();" class="submit_but04" />&nbsp;</td>
				</tr>
			</table>
		</div>
	</form>
	<script type="text/javascript">
		top.stopProcess();
	</script>
</body>
</html>


