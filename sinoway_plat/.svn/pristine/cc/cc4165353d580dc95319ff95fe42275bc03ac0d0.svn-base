<%@ taglib prefix="cp" uri="/custompage-tags"%>
<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="com.yzj.wf.core.model.po.wrapper.XPeopleInfo"%>
<%@page import="java.lang.String"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>TEST</title>
<cp:head />
<script type="text/javascript" src="../windforce/common/js/param_simple.js"></script>
</head>
<%
XPeopleInfo xp = (XPeopleInfo)session.getAttribute("XPEOPLEINFO");
String pam_baseparam = (String)session.getAttribute("group");
%>
<body>
	<cp:condition showname="参数查询">
		<cp:text showname="参数名称"   ref="paramName" maxlength="64"></cp:text>
		<cp:text showname="当前值"    ref="paramValue" maxlength="64"></cp:text>
		<cp:text showname="启用时间"    ref="activeTime"  pattern="yy-mm-dd" format="beginDate"></cp:text>
		<cp:text showname="失效时间"    ref="deactiveTime"  pattern="yy-mm-dd" format="endDate"></cp:text>
		<cp:select showname="参数状态"  ref="paramFlag" list="{'0':'生效','-1':'失效'}"  headerKey="" headerValue=""></cp:select>
	</cp:condition>
	<cp:content showname="参数管理">
		<%
		if(null != request.getParameter("edit") && request.getParameter("edit").equals("true")){ %>
		<cp:operate showname="增加" opertype="add" action="saveParam.action?group=${param.group}" preaction="initNewObj.action?group=${param.group}"></cp:operate>   <!-- preaction="initNewObj.action?group=pam_baseparam" -->
		<%}%>
		<cp:pagetable sortfield="paramName" sorttype="asc" sortable="false" rownum="20" action="fillTableContext.action?group=${param.group}" autoquery="true">
		<cp:pagecolumn showname="参数序号"  width="120px"  ref="id"></cp:pagecolumn>
		<cp:pagecolumn showname="参数名称"  width="120px"  ref="paramName"></cp:pagecolumn>
		<cp:pagecolumn showname="当前值"  width="120px"  ref="paramValue"></cp:pagecolumn>
		<cp:pagecolumn showname="数据库值"  width="120px"  ref="paramValue"></cp:pagecolumn>		
		<cp:pagecolumn showname="参数类型"  ref="paramType"  requireType="select" list="{'String':'字符串型','int':'数字类型','boolean':'布尔类型'}"></cp:pagecolumn>
		<cp:pagecolumn showname="是否加密"  width="120px"  ref="encryptType"  requireType="select"  list="{'1':'加密','':'不加密'}"></cp:pagecolumn>
		<!-- <cp:pagecolumn showname="缓存"  	width="120px"  ref="isNeedCache"  requireType="select"  list="{'1':'需要缓存','0':'无需缓存'}"></cp:pagecolumn>
		<cp:pagecolumn showname="生效时机"  width="120px"  ref="isNeedAutoRefresh"  requireType="select"  list="{'1':'立即生效','0':'重启生效'}"></cp:pagecolumn> 
		<cp:pagecolumn showname="标签"  	width="120px"  ref="tag"></cp:pagecolumn>-->
		<cp:pagecolumn showname="参数状态"  width="120px"  ref="paramFlag"  requireType="select"  list="{'0':'生效','-1':'失效'}"></cp:pagecolumn>

		<cp:pagecolumn showname="启用时间"  width="120px"  ref="activeTime" ></cp:pagecolumn>
		<cp:pagecolumn showname="失效时间"  width="120px"  ref="deactiveTime" ></cp:pagecolumn>
		<cp:pagecolumn showname="修改时间"  width="120px"  ref="lastUpdatedTime" ></cp:pagecolumn>
		<cp:pagecolumn showname="修改人员"  width="120px"  ref="updateOperator"></cp:pagecolumn>
		<cp:pagecolumn showname="排序字段"  width="120px"  ref="orderId"></cp:pagecolumn>
		<%
		if(null != request.getParameter("edit") && request.getParameter("edit").equals("true")){ %>
			<cp:pagecolumn showname="操作" 	width="120px" >
				<cp:operate showname="修改" opertype="modify" action="updateParam.action?group=${param.group}"></cp:operate>
				<cp:operate showname="删除" opertype="delete" action="deleteParam.action?group=${param.group}"></cp:operate>
			</cp:pagecolumn>
		<%}%>
		
		</cp:pagetable>
		<cp:navigation/>
	</cp:content>
	
	<cp:operatediv opertype="add">
		<cp:text showname="参数序号"    ref="id" disabled="true"></cp:text>
		<cp:text showname="参数名称"   requiredLabel="true"  ref="paramName" format="nameCheck" pattern="${param.group}"></cp:text>
		<cp:select showname="参数类型"    ref="paramType" format="combCheckType" list="{'String':'字符串型','int':'数字类型','boolean':'布尔类型'}"></cp:select>
		<cp:text showname="当前值"    ref="paramValue"  format="combCheck" maxlength="64"></cp:text>
		<cp:select showname="加密类型"    ref="encryptType" list="{'1':'加密','':'不加密'}"></cp:select>
		<!-- <cp:select showname="缓存"  	 ref="isNeedCache" list="{'1':'需要缓存','0':'无需缓存'}"></cp:select>
		<cp:select showname="刷新缓存"  ref="isNeedAutoRefresh" list="{'1':'立即生效','0':'重启生效'}"></cp:select>
		<cp:text showname="标签"  	 ref="tag"></cp:text> -->
		<cp:select showname="参数状态"  ref="paramFlag" list="{'0':'生效','-1':'失效'}"></cp:select>
		<cp:text showname="启用时间"    ref="activeTime"  pattern="yy-mm-dd" format="beginDate"></cp:text>
		<cp:text showname="失效时间"    ref="deactiveTime"  pattern="yy-mm-dd" format="endDate"></cp:text>
		<cp:text showname="修改时间"    ref="lastUpdatedTime" format="date" pattern="yy-mm-dd" disabled="true"></cp:text>
		<cp:text showname="修改人员"    ref="updateOperator" disabled="true" ></cp:text>
		<cp:text showname="排序字段"    ref="orderId"  format="combCheck" maxlength="64"></cp:text>
	</cp:operatediv>
	
	<cp:operatediv opertype="modify">
		<cp:text showname="参数序号"    ref="id" disabled="true"></cp:text>
		<cp:text showname="参数名称"    ref="paramName" disabled="true" maxlength="64"></cp:text>
		<cp:select showname="参数类型"    ref="paramType" format="combCheckType" list="{'String':'字符串型','int':'数字类型','boolean':'布尔类型'}"></cp:select>
		<cp:text showname="当前值"    ref="paramValue"  format="combCheck" maxlength="64"></cp:text>
		<cp:select showname="加密类型"    ref="encryptType" list="{'1':'加密','':'不加密'}"></cp:select>
		<!-- <cp:select showname="缓存"  	 ref="isNeedCache" list="{'1':'需要缓存','0':'无需缓存'}"></cp:select>
		<cp:select showname="刷新缓存"  ref="isNeedAutoRefresh" list="{'1':'立即刷新','0':'暂不刷新'}"></cp:select>
		<cp:text showname="标签"  	 ref="tag"></cp:text> -->
		<cp:select showname="参数状态"  ref="paramFlag" list="{'0':'生效','-1':'失效'}"></cp:select>
		<cp:text showname="启用时间"    ref="activeTime" pattern="yy-mm-dd" format="beginDate"></cp:text>
		<cp:text showname="失效时间"    ref="deactiveTime" pattern="yy-mm-dd" format="endDate"></cp:text>
		<cp:text showname="修改时间"    ref="lastUpdatedTime" format="date" pattern="yy-mm-dd" disabled="true"></cp:text>
		<cp:text showname="修改人员"    ref="updateOperator" disabled="true" value="asd"></cp:text>
		<cp:text showname="排序字段"    ref="orderId"  format="combCheck" maxlength="64"></cp:text>
	</cp:operatediv>
	
</body>
</html>