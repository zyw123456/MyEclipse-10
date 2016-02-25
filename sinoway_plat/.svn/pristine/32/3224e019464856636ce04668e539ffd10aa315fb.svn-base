<%@ taglib prefix="cp" uri="/custompage-tags"%>
<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>TEST</title>
<cp:head />
<script type="text/javascript" src="windforce/common/js/param_simple.js"></script>
</head>
<body>
	<cp:content showname="参数管理">
		<cp:operate showname="增加" opertype="add" action="saveParam.action?group=URLParam"></cp:operate>   <!-- preaction="initNewObj.action?group=URLParam" -->
		<cp:pagetable sortfield="paramName" sorttype="asc" sortable="false" rownum="10" action="fillTableContext.action?group=URLParam" autoquery="true">
		<cp:pagecolumn showname="参数序号"  width="120px"  ref="id"></cp:pagecolumn>
		<cp:pagecolumn showname="参数名称"  width="120px"  ref="paramName"></cp:pagecolumn>
		<cp:pagecolumn showname="参数值"  width="120px"  ref="paramValue"></cp:pagecolumn>
		<cp:pagecolumn showname="参数类型"  ref="paramType"  requireType="select" list="{'1':'核心参数','0':'应用参数'}"></cp:pagecolumn>
		<cp:pagecolumn showname="加密类型"  width="120px"  ref="encryptType"  requireType="select"  list="{'des':'des','0':'其他加密方式'}"></cp:pagecolumn>
		<cp:pagecolumn showname="缓存"  	width="120px"  ref="isNeedCache"  requireType="select"  list="{'1':'需要缓存','0':'无需缓存'}"></cp:pagecolumn>
		<cp:pagecolumn showname="刷新缓存"  width="120px"  ref="isNeedAutoRefresh"  requireType="select"  list="{'1':'立即刷新','0':'暂不刷新'}"></cp:pagecolumn>
		<cp:pagecolumn showname="标签"  	width="120px"  ref="tag"></cp:pagecolumn>
		<cp:pagecolumn showname="参数状态"  width="120px"  ref="paramFlag"  requireType="select"  list="{'1':'生效','0':'失效'}"></cp:pagecolumn>
		<cp:pagecolumn showname="加密类型"  width="120px"  ref="orderId"></cp:pagecolumn>
		<cp:pagecolumn showname="启用时间"  width="120px"  ref="activeTime" ></cp:pagecolumn>
		<cp:pagecolumn showname="失效时间"  width="120px"  ref="deactiveTime" ></cp:pagecolumn>
		<cp:pagecolumn showname="修改时间"  width="120px"  ref="lastUpdatedTime" ></cp:pagecolumn>
		<cp:pagecolumn showname="修改人员"  width="120px"  ref="updateOperator"></cp:pagecolumn>
		<cp:pagecolumn showname="操作" 		width="120px" >
			<cp:operate showname="修改" opertype="modify" action="updateParam.action?group=URLParam"></cp:operate>
			<cp:operate showname="删除" opertype="delete" action="deleteParam.action?group=URLParam"></cp:operate>
		</cp:pagecolumn>
		</cp:pagetable>
		<cp:navigation/>
	</cp:content>
	
	<cp:operatediv opertype="add">
		<cp:text showname="参数序号"    ref="id" disabled="true"></cp:text>
		<cp:text showname="参数名称"    ref="paramName" maxlength="64"></cp:text>
		<cp:text showname="参数值"    ref="paramValue" maxlength="64"></cp:text>
		<cp:select showname="参数类型"    ref="paramType" list="{'1':'核心参数','0':'应用参数'}"></cp:select>
		<cp:select showname="加密类型"    ref="encryptType" list="{'des':'des','0':'其他加密方式'}"></cp:select>
		<cp:select showname="缓存"  	 ref="isNeedCache" list="{'1':'需要缓存','0':'无需缓存'}"></cp:select>
		<cp:select showname="刷新缓存"  ref="isNeedAutoRefresh" list="{'1':'立即刷新','0':'暂不刷新'}"></cp:select>
		<cp:text showname="标签"  	 ref="tag"></cp:text>
		<cp:select showname="参数状态"  ref="paramFlag" list="{'1':'生效','0':'失效'}"></cp:select>
		<!-- <cp:select showname="排序字段"  ref="orderId"  list="getAjaxData('getOrderSelect.action')"></cp:select> -->
		<cp:text showname="启用时间"    ref="activeTime" format="date" pattern="yy-mm-dd"></cp:text>
		<cp:text showname="失效时间"    ref="deactiveTime" format="date" pattern="yy-mm-dd"></cp:text>
		<cp:text showname="修改时间"    ref="lastUpdatedTime" format="date" pattern="yy-mm-dd"></cp:text>
		<cp:text showname="修改人员"    ref="updateOperator" disabled="true"></cp:text>
	</cp:operatediv>
	
	<cp:operatediv opertype="modify">
		<cp:text showname="参数序号"    ref="id" disabled="true"></cp:text>
		<cp:text showname="参数名称"    ref="paramName" maxlength="64"></cp:text>
		<cp:text showname="参数值"    ref="paramValue" maxlength="64"></cp:text>
		<cp:select showname="参数类型"    ref="paramType" list="{'1':'核心参数','0':'应用参数'}"></cp:select>
		<cp:select showname="加密类型"    ref="encryptType" list="{'des':'des','0':'其他加密方式'}"></cp:select>
		<cp:select showname="缓存"  	 ref="isNeedCache" list="{'1':'需要缓存','0':'无需缓存'}"></cp:select>
		<cp:select showname="刷新缓存"  ref="isNeedAutoRefresh" list="{'1':'立即刷新','0':'暂不刷新'}"></cp:select>
		<cp:text showname="标签"  	 ref="tag"></cp:text>
		<cp:select showname="参数状态"  ref="paramFlag" list="{'1':'生效','0':'失效'}"></cp:select>
		<!-- <cp:select showname="排序字段"  ref="orderId"  list="getAjaxData('getOrderSelect.action')"></cp:select> -->
		<cp:text showname="启用时间"    ref="activeTime" format="date" pattern="yy-mm-dd"></cp:text>
		<cp:text showname="失效时间"    ref="deactiveTime" format="date" pattern="yy-mm-dd"></cp:text>
		<cp:text showname="修改时间"    ref="lastUpdatedTime" format="date" pattern="yy-mm-dd"></cp:text>
		<cp:text showname="修改人员"    ref="updateOperator" disabled="true"></cp:text>
	</cp:operatediv>
	
</body>
</html>