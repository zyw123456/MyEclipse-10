<%@page import="com.sinoway.common.util.Constant"%>
<%@page import="com.sinoway.common.util.Constant.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tlds/pageTag.tld" prefix="page"%> 
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<c:set var="personal" value="<%=Constant.RptTyp.RPTTYP_PRSN_CREDIT.getCode() %>"></c:set>
<c:set var="rptstatus" value="<%=Constant.RptStatus.STATIS_QUERYSUCCESS.getCode() %>"></c:set>
<c:set var="orderby" value="<%=Constant.OrderBy.ORDERBY_ASC.getCode() %>"></c:set>
<c:set var="datcmitori" value="<%=Constant.DatCmitori.DatCmitori_PLANT.getCode() %>"></c:set>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>反欺诈云列表</title>
<link rel="stylesheet" href="../common/css/main.css">
<link rel="stylesheet" href="../common/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/sinoway/rpt/css/kkpager_blue.css" />
	<script type="text/javascript"
		src='<%=request.getContextPath()%>/common/js/jquery/jquery-1.7.1.js'></script>
	<script type="text/javascript"
		src='<%=request.getContextPath()%>/common/js/jquery/jquery-ui-1.8.18.custom.js'></script>
	<script type="text/javascript"
		src='<%=request.getContextPath()%>/common/js/jquery/jquery.json-2.4.js'></script>
	<!-- 日期控件 -->
	<script type="text/javascript"
		src="<%=request.getContextPath() %>/windforce/common/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" 
    	src="<%=request.getContextPath()%>/sinoway/fad/js/fadList.js"></script>
    <script type="text/javascript" 
    src="<%=request.getContextPath()%>/sinoway/common/js/changeColor.js"></script>
     <script type="text/javascript" 
    src="<%=request.getContextPath()%>/sinoway/fad/js/changeHeight.js"></script>
    <script type="text/javascript" 
    	src="<%=request.getContextPath()%>/sinoway/rpt/js/kkpager.js"></script>
    <script type="text/javascript" 
    	src="<%=request.getContextPath()%>/sinoway/rpt/js/kkpager.min.js"></script>
    
	<style type="text/css">
		body{
		background-color: #eeeeee;
		width: 94%;
		}
	</style>	
	<script type="text/javascript">
		var ctx = "${ctx}";
		var personal = "${personal }";
		var rptstatus = "${rptstatus}";
		var orderby = "${orderby}";
		var datcmitori = "${datcmitori}";
	</script>
</head>
<body>
		<form class="form-inline report_right" id="queryForm"
			action="${ctx}/fraud_getListByConditions.action" method="post">
			<div class="query_report">
				<input type="hidden" name="moddatOrdBy"
					value="<%=OrderBy.ORDERBY_DESC.getCode() %>" id="moddatOrdByid">
				<input type="hidden" name="pageNoStr" value="1" id="pageNoStr">
				<div class="input-group">
					<div class="input-group-addon">姓&nbsp;&nbsp;&nbsp;名</div>
					<input type="text" class="form-control" name="prsnnam" id="prsnnam" />
					<div class="input-group-addon">身份证号</div>
					<input type="text" class="form-control" name="prsncod" id="prsncod" />
				</div>
				<div class="input-group">
					<div class="input-group-addon">报告号</div>
					<input type="text" class="form-control" name="rptid" id="rptid" />
					<div class="input-group-addon">更新日期</div>
					<input type="text" class="form-control sdf" name="rptmoddtefrom"
						id="rptmoddtefrom"
						onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'rptmoddteto\')||\'new Date()\'}',dateFmt:'yyyyMMdd'});"
						onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMMdd',maxDate:'%y%M%d'});" />
					<div class="input-group-addon">至</div>
					<input type="text" class="form-control sdfs" id="rptmoddteto"
						name="rptmoddteto"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'rptmoddtefrom\')}',maxDate:new Date(),dateFmt:'yyyyMMdd'});"
						onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMMdd',maxDate:'%y%M%d'});" />
				</div>
			</div>
			<div class="form_sou" id="queryButton">
				<div id="round2">
					<a href="#">搜索</a>
				</div>
			</div>
			<div style=" clear:both;"></div>
			<div id="tab_report">
				<table id="tb1">
					<thead>
						<tr>
							<th
								style="background-color:#EEEEEE; color:#999; font-size:16px; text-align:left; padding-left:20px;"
								class="ahha" colspan="8"><input
								style="vertical-align:middle;" type="checkbox" id="checkall"
								title="全选/取消" /> <img alt="" src="../common/images/remove.png"
								width="14" height="16" /> <a class="arome" href="##"
								id="deleteButton">删除报告</a> <!--  <a class="arome" href="${ctx}/sinoway/fad/addFad.jsp" >反欺诈查询</a>-->
								<a class="arome" href="javascript:void(0)" onclick="fadQuery()">反欺诈查询</a>
							</th>
						</tr>
						<tr>
							<th>全选</th>
							<th>报告更新时间 <span><div
										style="width:7;height:13;position:relative;float:left;left:88%;top:8px;">
										<div style="width:7;height:6">
											<a class="datas" href="javascript:void(0)"
												onclick="orderByTime('<%=OrderBy.ORDERBY_ASC.getCode() %>')"><img
												alt="" src="../common/images/data_top.png" />
											</a>
										</div>
										<div style="width:7;height:6;position:relative;top:4px;">
											<a class="datas" href="javascript:void(0)"
												onclick="orderByTime('<%=OrderBy.ORDERBY_DESC.getCode() %>')"><img
												alt="" src="../common/images/data_btm.png" />
											</a>
										</div>
									</div>
							</span></th>
							<th>报告编号</th>
							<th>姓名</th>
							<th>身份证号</th>
							<th><select id="reportType" name="rpttyp">
									<option value="">报告类型</option>
									<option value="<%=Constant.RptTyp.RPTTYP_FRAUD.getCode()%>"><%=Constant.RptTyp.RPTTYP_FRAUD.getValue() %></option>
									<option value="<%=Constant.RptTyp.RPTTYP_VERIFIED.getCode()%>"><%=Constant.RptTyp.RPTTYP_VERIFIED.getValue() %></option>
							</select></th>
							<th><select id="datcmitoriid" name="datcmitori">
									<option value="">数据来源</option>
									<option
										value="<%=Constant.DatCmitori.DatCmitori_PLANT.getCode() %>"><%=Constant.DatCmitori.DatCmitori_PLANT.getValue() %></option>
									<option
										value="<%=Constant.DatCmitori.DatCmitori_INTERFACE.getCode() %>"><%=Constant.DatCmitori.DatCmitori_INTERFACE.getValue() %></option>
									<option
										value="<%=Constant.DatCmitori.DatCmitori_WECHAT.getCode() %>"><%=Constant.DatCmitori.DatCmitori_WECHAT.getValue() %></option>
									<option
										value="<%=Constant.DatCmitori.DatCmitori_APP.getCode() %>"><%=Constant.DatCmitori.DatCmitori_APP.getValue() %></option>
							</select></th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tab">
					</tbody>
					<tfoot align="center">
						<tr>
							<td colspan="7">
								<div id="kkpager" class="grayr"></div></td>
						</tr>
					</tfoot>
				</table>
			</div>
		</form>
</body>

</html>