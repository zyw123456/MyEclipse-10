<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/wf_import.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<%
	Boolean enableSupervisePeople = (Boolean)request.getAttribute(
			"enableSupervisePeople");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>转授权查询和列表界面</title>
<%@ include file="/windforce/common/jsp/wfInternalImport.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/common/css/jquery-ui-1.8.18.base.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/windforce/common/css/am_transfer.css" />
<link href="${ctx }/windforce/v1_5/common/css/style_sys.css"
		rel="stylesheet" type="text/css" />
<link href="${ctx }/windforce/v1_5/common/css/jquery-ui/cupertino/jquery-ui.css"
		rel="stylesheet" type="text/css"></link>
<script src="${ctx }/windforce/v1_5/common/js/jquery-ui/jquery-ui.min.js"
		type="text/javascript"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
	var enableSupervisePeople = "<%=enableSupervisePeople%>";
	$(document).ready(function(){
		$(":button").button();
		var deskbodyWidth = top.document.getElementById("rightindex").style.width;
			deskbodyWidth = deskbodyWidth.substr(0, deskbodyWidth.length - 2);
		var deskbodyHeight = top.document.getElementById("rightindex").style.height;
			deskbodyHeight = deskbodyHeight.substr(0, deskbodyHeight.length - 2);
		var tpiMainDiv = $("#tpiMainDiv");
		tpiMainDiv.css("width", deskbodyWidth + "px");
		tpiMainDiv.css("height", deskbodyHeight + "px");
	});
</script>
<script type="text/javascript" src="${ctx}/common/js/jquery/jquery-ui-1.8.18.custom.js"></script>
<script type="text/javascript" src="${ctx}/windforce/common/js/am/transfer/list.js"></script>
</head>
<body class="body_background" style="width:auto;height:auto;overflow:hidden;">
	<div id="tpiMainDiv" style="overflow:auto;">
		<div id="searchDiv">
			<form action="${ctx}/transferPowerInfoAction_showListPage.action" id="searchForm">
				<table width="99%">
					<tr>
						<td>
							<label>预约开始日期</label>
							<input type="text" id="search_startDate" class="tpi_input" name="startDate" readonly="readonly"
								value="<s:property value='startDate'/>" />
							<label>—</label>
							<input type="text" id="search_endDate" class="tpi_input" name="endDate" readonly="readonly"
								value="<s:property value='endDate'/>" />
						</td>
					</tr>
					<tr>
						<td align="right">
							<input type="button" id="searchBtn" class="btn" value="查询" />
							<input type="button" id="resetBtn" class="btn" value="重置" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div>
			<button id="reserveBtn" class="btn">预约</button>
			<button id="cancelBtn" class="btn">撤销</button>
			<button id="takeBackBtn" class="btn">提前收回</button>
		</div>
		<table width="99%" align="left" cellpadding="0" cellspacing="0" id="listTable">
			<tr style="line-height:26px;">
				<th width="5%" class="font_colors07"></th>
				<th width="17%" class="font_colors07">申请书编号</th>
				<th width="17%" class="font_colors07">接收柜员</th>
				<s:if test="enableSupervisePeople==true">
					<th width="17%" class="font_colors07">监交柜员</th>
				</s:if>
				<th width="17%" class="font_colors07">开始时间</th>
				<th width="17%" class="font_colors07">结束时间</th>
				<th width="10%" class="font_colors07">状态</th>
			</tr>
			<tbody style="font-size: 13px;">
				<s:if test="transferPowerInfos==null||transferPowerInfos.isEmpty()||transferPowerInfos.size()==0">
					<tr style="line-height:20px;" bgcolor="#CBD6E0">
						<td colspan="7" class="tablefont">无记录</td>
					</tr>
				</s:if>
				<s:else>
					<s:iterator var="transferPowerInfo" value="transferPowerInfos" status="index">
							<tr style="line-height:20px;" 
								 <s:if test="#index.index%2 ==0">bgcolor="#CBD6E0"</s:if>
								 <s:if test="#index.index%2 ==1">bgcolor="#FFFFFF"</s:if>>
								<td width="5%">
									<input type="checkbox" class="selectCheckBox" value='<s:property value="#transferPowerInfo.id"/>' />
								</td>
								<td width="17%" class="tablefont">
									<s:property value="#transferPowerInfo.applicationFormNo" />
								</td>
								<td width="17%" class="tablefont">
									<s:property value="#transferPowerInfo.revicePeopleName" />
									(
									<s:property value="#transferPowerInfo.revicePeopleNo" />
									)
								</td>
								<s:if test="enableSupervisePeople==true">
									<td width="17%" class="tablefont">
										<s:property value="#transferPowerInfo.supervisePeopleName" />
										(
										<s:property value="#transferPowerInfo.supervisePeopleNo" />
										)
									</td>
								</s:if>
								<td width="17%" class="tablefont">
									<s:property value="#transferPowerInfo.startDate" />
									<s:property value="#transferPowerInfo.startTime" />
								</td>
								<td width="17%" class="tablefont">
									<s:property value="#transferPowerInfo.endDate" />
									<s:property value="#transferPowerInfo.endTime" />
								</td>
								<td width="10%" class="tablefont">
									<s:if test="#transferPowerInfo.status == 0">
										预约
									</s:if>
									<s:if test="#transferPowerInfo.status == 1">
										进行中	
									</s:if>
									<s:if test="#transferPowerInfo.status == 2">
										正常结束
									</s:if>
									<s:if test="#transferPowerInfo.status == 3">
										提前收回
									</s:if>
									<s:if test="#transferPowerInfo.status == 4">
										撤销
									</s:if>
								</td>
							</tr>
					</s:iterator>
				</s:else>
			</tbody>
		</table>
	</div>
</body>
</html>