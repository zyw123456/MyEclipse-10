<%--
  修改机构              
@date          2014/11/29         
@author        李水野
@version       1.5               
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/windforce/common/jsp/wfInternalImport.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改机构</title>
<link rel="stylesheet" href="${ctx }/windforce/v1_5/common/css/style_sys.css"
	type="text/css" />

<link href="${ctx }/windforce/v1_5/common/css/jquery-ui/cupertino/jquery-ui.css"
	rel="stylesheet" type="text/css"></link>
<link href="${ctx }/windforce/v1_5/common/css/jqgrid/ui.jqgrid.css"
	rel="stylesheet" type="text/css"></link>
<link
	href="${ctx }/windforce/v1_5/common/css/jquery-validationEngine/validationEngine.jquery.css"
	rel="stylesheet" type="text/css"></link>
<link href="${ctx }/windforce/v1_5/common/css/ztree/zTreeStyle.css"
	rel="stylesheet" type="text/css"></link>
<link href="${ctx }/windforce/v1_5/common/css/icon/icon.css" rel="stylesheet"
	type="text/css"></link>

<script src="${ctx }/windforce/v1_5/common/js/jquery/jquery-1.7.2.js"
	type="text/javascript"></script>
<script src="${ctx }/windforce/v1_5/common/js/jquery/jquery.bgiframe.min.js"
	type="text/javascript"></script>
<script src="${ctx }/windforce/v1_5/common/js/jquery-ui/jquery-ui.min.js"
	type="text/javascript"></script>
<!-- jqGrid -->
<script src="${ctx }/windforce/v1_5/common/js/jqgrid/jquery.jqGrid.min.js"
	type="text/javascript"></script>
<script src="${ctx }/windforce/v1_5/common/js/jqgrid/grid.locale-cn.js"
	type="text/javascript"></script>
<script
	src="${ctx }/windforce/v1_5/common/js/jquery-validationEngine/jquery.validationEngine.js"
	type="text/javascript"></script>
<script
	src="${ctx }/windforce/v1_5/common/js/jquery-validationEngine/jquery.validationEngine-cn.js"
	type="text/javascript"></script>
<script src="${ctx }/windforce/v1_5/common/js/ztree/jquery.ztree.all-3.4.min.js"
	type="text/javascript"></script>
<script src="${ctx }/common/js/framework/area.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${ctx }/common/js/framework/util.js"></script>
<script type="text/javascript"
	src="${ctx }/common/js/framework/framework.js"></script>
<script type="text/javascript"
	src="${ctx }/common/js/framework/report.js"></script>
<script type="text/javascript"
	src="${ctx }/windforce/v1_5/common/js/areaManager.js"></script>

<script type="text/javascript">
	function onSaveOrg() {
		var organizeName = $("#orgName").val();
		var orgNo = $("#orgNo").val();
		var orgType = $("#orgType").val();
		var parentCode = $("#parentOrgNo").val();
		var manageType = $("#manageType").checked == true ? 1 : 0;
		var orgProvince = $("#orgProvince").val();//省市代码
		var manageOrgFlag = $("#manageOrgFlag").attr("checked") == "checked"?true:false;
		var bizOrgFlag = $("#bizOrgFlag").attr("checked") == "checked"?true:false;
		var orgArea = $("#mod_parentArea").val();//区域代码

		if (top.isNull(organizeName)) {
			top.wfAlert("机构名称不能为空!");
			return false;
		}
		if (!top.isGeneralName(organizeName)) {
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
		if(top.isNull(orgProvince)){
			top.wfAlert("省市代码不能为空!");
			return false;
		}
		if(top.isNull(orgArea)){
			top.wfAlert("所属区域不能为空!");
			return false;
		}
		//added by chenhuang 2013-1-23 当机构类型被修改时，提示用户是否确认操作
		var oldOrgType = '<s:property value="orgInfo.orgType"/>';
		if (oldOrgType != orgType) {
			var orgTypeObj = document.forms[0].elements["orgInfo.orgType"];
			var newOrgTypeName = orgTypeObj.options[orgTypeObj.selectedIndex].text;
			if (!(window.confirm("您确认将该机构机构类型修改为‘" + newOrgTypeName + "’?",
					"提示信息"))) {
				return false;
			}
		}
		var operAuth = new top.OperAuth();
		operAuth.operType = "editOrganization";
		operAuth.authSuccess = function(authUserCode) {
			var dataParaments = {
				"orgInfo.orgName" : organizeName,
				"orgInfo.orgNo" : orgNo,
				"orgInfo.orgType" : orgType,
				"parentOrgNo" : parentCode,
				"orgInfo.manageType" : manageType,
				"orgInfo.orgProvince" : orgProvince,
				"orgInfo.manageOrgFlag" : manageOrgFlag,
				"orgInfo.bizOrgFlag" : bizOrgFlag,
				"orgInfo.orgArea" : orgArea,
				"authUserCode" : authUserCode
			};
			top.startProcess("正在提交机构修改,请稍等...");
			// 这里提交用ajax目的是为了不刷新页面，好控制iframe层次的显示与隐藏
			$.post(top.ctx + "/editOrganize_doEdit.action?_t="
					+ new Date().getTime(), dataParaments, function(data) {
				// TODO action 写验证，验证通过就跳转，不通过就弹出消息
				// 添加完后隐藏右栏
				if (data == "1") {
					// 修改成功设置
					/* window.returnValue = orgNo;
					window.close(); */
					var sid = '<s:property value="sid"/>';
					top.closeShowPage();
					top.refreshLeft();
					top.relDetail(sid);
				} else if (data == "2") {
					top.stopProcess();
					top.wfAlert("修改失败，请联系管理员！");
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
		var parentOrgs = $("#parentOrgNo");
		var currTypeId = $("#orgType").val();
		parentOrgs.length = 0;
		var isIE = navigator.userAgent.toUpperCase().indexOf("MSIE") == -1 ? false
				: true;
		// 通过ajax过滤父机构
		$.getJSON(top.ctx + "/getParentOrg.action?_t=" + new Date().getTime()
				+ '&typeId=' + currTypeId, null, function(data) {

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
			if (flag) {
				parentOrgs.value = '<s:property value="parentOrgNo"/>';
			}
		});
	}
	$(function(){
		//初始化省市参数
		var provinceData = top.getSystemParam('ProvinceCode');
		$.each(provinceData,function(index,value){
			 $("#orgProvince").append("<option value='"+value.paramName+"'>"+value.paramValue+"</option>");  //添加一项option
		});
		var currOP = "<s:property value='orgInfo.orgProvince' />";
		$("#orgProvince option[value='"+currOP+"']").attr("selected", true);
	})
</script>
</head>
<body style="font-size:12px;background-color:#E8EEEB;overflow: hidden;">
	<form class="mws-form" name="myform" action="#">
		<div id="login_bg" style="filter:alpha(opacity:75);opacity:0.75;"></div>
		<div id="login_nov">
			<table>
				<tr>
					<td width="20%" align="center" class="font_colors06">机构名称</td>
					<td width="30%">
						<input type="text" id="orgName" name="orgInfo.orgName"
							value="<s:property value='orgInfo.orgName'/>" class="mws-textinput"
							maxlength="50" />
					</td>
					<td width="20%" align="center" class="font_colors06">机构代码</td>
					<td width="30%">
						<input type="text" id="orgNo" name="orgInfo.orgNo"
							value="<s:property value='orgInfo.orgNo' />" class="code_transform"
							maxlength="16" />
					</td>
				</tr>
				<tr>
					<td align="center" class="font_colors06">上级机构</td>
					<td>
						<input type="text" disabled="true" id="parentOrgName"
							value="<s:property  value='parentOrgInfo.orgName'/>"
							class="code_transform" />
						<input type="hidden" id="parentOrgNo"
							value="<s:property  value='parentOrgInfo.orgNo'/>" />
					</td>
					<td align="center" class="font_colors06">机构等级</td>
					<td>
						<label>
							<s:if test="enableEditOrgTypeFlag">
								<s:select id="orgType" list="initTypeList" style="width:155px;"
									name="orgInfo.orgType" listKey="sid"
									listValue="orgTypeName"></s:select>
							</s:if>
							<s:else>
								<s:select id="orgType" list="initTypeList" style="width:155px;"
									name="orgInfo.orgType" listKey="sid"
									listValue="orgTypeName" disabled="true"></s:select>
							</s:else>
							
						</label>
					</td>
				</tr>
				<tr>
					<td align="center" class="font_colors06">省市代码</td>
					<td>
						<select id="orgProvince" style="width:155px;">
						</select>
					</td>
					<td align="center" class="font_colors06">所属区域</td>
					<td>
						<input type="text" id="mod_parentAreaCode"
							onfocus="checkParentAreaForModArea()"
							value="<s:property value='area.areaName'/>" />
						<input type="hidden" name="orgInfo.orgArea" id="mod_parentArea"
							value="<s:property value='orgInfo.orgArea' />" />
					</td>
				</tr>
				<tr>
					<td align="center" class="font_colors06">机构类型</td>
					<td colspan="3" class="font_colors06">
						<s:if test="orgInfo.manageOrgFlag">
							<input type="checkbox" name="orgTypeFlag" id="manageOrgFlag" checked="checked"/>管理机构&nbsp;&nbsp;&nbsp;&nbsp; 
						</s:if>
						<s:else>
							<input type="checkbox" name="orgTypeFlag" id="manageOrgFlag"/>管理机构&nbsp;&nbsp;&nbsp;&nbsp; 
						</s:else>
						<s:if test="orgInfo.bizOrgFlag">
							<input type="checkbox" name="orgTypeFlag" id="bizOrgFlag" checked="checked"/>营业机构
						</s:if>
						<s:else>
							<input type="checkbox" name="orgTypeFlag" id="bizOrgFlag"/>营业机构
						</s:else>
					</td>
				</tr>
				<tr style="display: none">
					<td align="center">&nbsp;</td>
					<td colspan="3" class="font_colors06">
						<s:if test="orgInfo.manageType == 1">
							<input name="orgInfo.manageType" value="1" type="checkbox"
								checked="checked" />
						</s:if>
						<s:if test="orgInfo.manageType == 0">
							<input name="orgInfo.manageType" value="1" type="checkbox" />
						</s:if>
						自主管理
					</td>
				</tr>
				<tr>
					<td align="center">&nbsp;</td>
					<td colspan="3" align="right">
						<input class="submit_but04" type="button" onclick="onSaveOrg();"
							value="保存" />
						<input type="reset" class="submit_but04" value="重置"
							/>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<label></label>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
<script type="text/javascript">
	//added by chenhuang 20130123
	//初始化上级机构类型
	//setParentOrg(true);
	top.stopProcess();
</script>
</html>


