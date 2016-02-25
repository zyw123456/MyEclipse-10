<%--
  机构添加页面                 
@date          2012/04/25         
@author        蒋正秋  ,chenhuang
@version       1.0                
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/windforce/common/jsp/wfInternalImport.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>添加页面</title>
<script type="text/javascript">
	/**
	 * 添加机构
	 */
	function onSaveOrg() {
		var orgName = $("#orgName").val();
		var orgNo = $("#orgNo").val();
		var orgType = $("#orgType").val();
		var parentOrgCode = $("#parentOrgCode").val();
		var manageType = $("#manageType").val() == true ? 1 : 0;

		if (top.isNull(orgName)) {
			top.wfAlert("机构名称不能为空!");
			return false;
		}
		if (!top.isGeneralName(orgName)) {
			top.wfAlert("机构名称含有非法字符!");
			return false;
		}
		if (top.isNull(orgNo)) {
			top.wfAlert("机构代码不能为空!");
			return false;
		}
		if (!top.isAlphaAndDigits(orgNo)) {
			top.wfAlert("机构代码只能是字母或数字!");
			return false;
		}
		var sid = '<s:property value="sid"/>';
		var operAuth = new top.OperAuth();
		operAuth.operType = "addOrganization";
		operAuth.authSuccess = function(authUserCode) {
			var dataParaments = {
				"orgInfo.orgName" : orgName,
				"orgInfo.orgNo" : orgNo,
				"orgInfo.orgType" : orgType,
				"orgInfo.parentOrg" : parentOrgCode,
				"orgInfo.manageType" : manageType,
				"orgInfo.sid" : sid,
				"authUserCode" : authUserCode
			};
			top.startProcess("正在提交机构信息,请稍等...");
			// 这里提交用ajax目的是为了不刷新页面，好控制iframe层次的显示与隐藏
			$.post('${ctx}/addOrganize.action?_t=' + new Date().getTime(),
					dataParaments, function(data) {
						// TODO action 写验证，验证通过就跳转，不通过就弹出消息
						// 手动刷新左菜单
						//top.refreshLeft();
						// 添加完后隐藏右栏
						if (data == "1") {
							top.changeProcessTitle("新增机构完毕,正在刷新界面...");
							// 添加成功设置返回值
							top.closeShowPage();
							top.refreshLeft();
							top.relDetail(sid);
							//window.returnValue = "success";
							//window.close();
						} else if (data == "2") {
							top.stopProcess();
							top.wfAlert("添加失败，请联系管理员！");
						} else {
							top.stopProcess();
							top.wfAlert(data);
						}
					});
		};
		operAuth.auth();
	}

	function setParentOrg(flag) {
		// 获取下拉框对象
		var parentOrgs = $("#parentOrgCode");
		var currTypeId = $("#orgType").val();
		// added by chenhuang 20130124 但点击清空按钮时，重置上级机构列表
		if (flag) {
			currTypeId = '<s:property value="orgType"/>';
		}
		parentOrgs.length = 0;
		// 通过ajax过滤父机构
		$.getJSON('${ctx}/getParentOrg.action?_t=' + new Date().getTime()
				+ '&typeId=' + currTypeId,
				function(data) {
					var isIE = navigator.userAgent.toUpperCase()
							.indexOf("MSIE") == -1 ? false : true;
					// 动态填充下拉框数据
					for (org in data) {
						if (isIE) {
							parentOrgs.add(new Option(data[org].orgName,
									data[org].orgNo));
						} else {
							parentOrgs.add(new Option(data[org].orgName,
									data[org].orgNo), null);
						}
					}
					parentOrgs.val('<s:property value="parentCode"/>');
					if (parentOrgs.value == null
							|| parentOrgs.value.length == 0)
						if (parentOrgs.length > 0) {
							parentOrgs.options[0].selected = true;
						}

				});
	}
</script>
</head>
<body onload="setParentOrg();">
	<div id="login_bg" style="filter:alpha(opacity:75);opacity:0.75;"></div>
	<div id="login_nov">
		<table>
			<tr>
				<td width="20%" align="center" class="font_colors06">机构名称</td>
				<td width="30%">
					<input type="text" id="orgName" class="mws-textinput" maxlength="50" />
				</td>
				<td width="20%" align="center" class="mws-textinput">机构代码</td>
				<td width="30%">
					<input type="text" id="orgNo" class="code_transform" maxlength="16" />
				</td>
			</tr>
			<tr>
				<td align="center" class="font_colors06">上级机构</td>
				<td>
					<input type="text" disabled="true" id="parentOrgCode"
						value="<s:property  value='parentCode'/>" />
					<!-- 	<s:select list="parentOrgList" id="parentOrgCode" name="parentOrgCode" listKey="orgNo"
						listValue="orgName" style="width:155px;" disabled="true"></s:select>  -->
				</td>
				<td align="center" class="font_colors06">机构类型</td>
				<td>
					<s:select list="initTypeList" id="orgType" name="orgType" listKey="sid"
						listValue="orgTypeName" style="width:155px;"></s:select>
				</td>
			</tr>
			<tr style="display: none">
				<td align="center">&nbsp;</td>
				<td colspan="3" class="font_colors06">
					<input id="manageType" name="manageType" value="1" type="checkbox" />
					自主管理
				</td>
			</tr>
			<tr>
				<td align="center">&nbsp;</td>
				<td colspan="3" align="right">
					<input type="button" onclick="onSaveOrg();" value="保存" class="submit_but04" />
					<input type="reset" value="关闭" onclick="top.closeShowPage();"
						class="submit_but04" />
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center"></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		top.stopProcess();
	</script>
</body>
</html>


