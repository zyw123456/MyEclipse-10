<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<title>区域管理</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
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
<script src="${ctx }/windforce/common/js/commonLayout.js"
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
	var ctx = "${ctx }";
	$(function() {
		$(":button").button();
		var iContentH = $(window).height() - 40;
		$("#sidebar").height(iContentH + 32);
		$(".accordion").height(iContentH);
		areaManageInit();
	});
</script>
</head>

<body>
	<table cellpadding="0" cellspacing="0" class="contentTable">
		<tr>
			<td class="navleft">
				<div id="sidebar">
					<div class="toggleCollapse">
						<h2>中国行政区</h2>
					</div>
					<div class="accordion">
						<div class="accordionContent" id="areaTreeDemo"></div>
					</div>
				</div>
			</td>

<!-- 			<td class="contentTablesplit"></td> -->

			<td>
				<div class="pageHeader">
					<form method="post" id="areaList_Item">
						<div class="searchBar">
							<table class="searchContent">
								<tr>
									<td>
										区域代码:
										<input type="text" name="areaCode" style="width:115px;margin-left:6px;"
											id="item_areaCode" />
									</td>
									<td>
										区域名称:
										<input type="text" name="like_areaName" style="width:120px;" />
									</td>
									<td>
										下级区域:
										<select id="childList" name="childAreaFlag"
											style="width:80px; margin-left: 6px;">
											<option value="0">不显示</option>
											<option value="1">显示</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>
										区域类型:
										<select id="areaTypechildList" name="areaType"
											style="width:120px; margin-left: 6px;"></select>
									</td>
								</tr>
							</table>
							<div class="subBar">
								<ul>
									<li><div class="buttonActive">
											<div class="buttonContent">
												<input type="button" onclick="queryAreasForTerm()" value="查询" />
											</div>
										</div>
									</li>
									<li><div class="buttonActive">
											<div class="buttonContent">
												<input type="button" onclick="clearQueryAreasForTerm()" value="清除" />
											</div>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</form>
				</div>
				<div class="pageContent">
					<div class="panelBar">
						<ul class="toolBar">
							<li class="line">line</li>
							<li><a class="add" href="#" onclick="createAddAreaDiag()">
									<span>添加</span>
								</a></li>
							<li class="line">line</li>
							<li><a class="delete" href="#" onclick="createDeleteAreaDiag(0)">
									<span>删除</span>
								</a></li>
							<!-- 							<li><a class="icon" href="#" id="reportAreaExcel"><span>导出EXCEL</span> -->
							<!-- 							</a></li> -->
						</ul>
					</div>
					<!-- 数据表格 -->
					<table id="areaDataList"></table>
					<!-- 分页 -->
					<div id="areaDataListpager"></div>
				</div>
			</td>
		</tr>
	</table>

	<!-- 新建区域信息 -->
	<div id="newAreaDLG" title="新建区域信息" class="adddialog" style="display:none;">
		<form action="" id="newArea_item">
			<table>
				<tr>
					<td>
						区域代码：
						<input type="text" name="area.areaCode"
							class="validate[required,custom[onlyNumber] ,custom[areaCodeLenth] ]" />
						区域名称：
						<input type="text" name="area.areaName" class="validate[required]" />
					</td>
				</tr>
				<tr>
					<td>
						上级区域：
						<input type="text" id="Add_parentAreaCode"
							onfocus="checkParentAreaForAddArea()" />
						区域类型：
						<select name="area.areaType" id="Add_areaType" style="width:155px;"></select>
						<input type="hidden" name="area.parentArea" id="Add_parentArea" />
					</td>
				</tr>

				<tr>
					<td valign="top">
						<span>备注信息：</span>
						<textarea rows="5" cols="60" name="area.areaMemo" id="Add_areaMemo"
							class="validate[length[0,30]]"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<!-- 查看区域信息 -->
	<div id="checkAreaDLG" title="查看区域信息" style="padding:  0 0 0 0;display:none;"
		class="adddialog">
		<form action="" id="checkArea_item">
			<table>
				<tr>
					<td>
						区域代码：
						<input type="text" name="area.areaCode" readonly="readonly"
							id="chk_areaCode"
							class="validate[required,custom[onlyNumber] ,custom[areaCodeLenth] ]" />
						区域名称：
						<input type="text" name="area.areaName" readonly="readonly"
							id="chk_areaName" class="validate[required]" />
					</td>
				</tr>
				<tr>
					<td>
						上级区域：
						<input type="text" id="chk_parentAreaCode" readonly="readonly" />
						区域类型：
						<select name="area.areaType" id="chk_areaType" style="width:155px;"></select>
						<input type="hidden" name="area.parentArea" id="chk_parentArea" />
					</td>
				</tr>

				<tr>
					<td valign="top">
						<span>备注信息：</span>
						<textarea rows="5" cols="60" name="area.areaMemo" id="chk_areaMemo"
							readonly="readonly"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<!-- 修改区域信息 -->
	<div id="modifyAreaDLG" title="修改区域信息" style="padding:  0 0 0 0;display:none;"
		class="adddialog">
		<form action="" id="modifyArea_item">
			<table>
				<tr>
					<td>
						区域代码：
						<input type="text" name="area.areaCode" id="mod_areaCode"
							class="validate[required,custom[onlyNumber] ,custom[areaCodeLenth] ]" />
						区域名称：
						<input type="text" name="area.areaName" id="mod_areaName"
							class="validate[required]" />
						<input type="hidden" name="area.areaSid" id="mod_areaSid" />
					</td>
				</tr>
				<tr>
					<td>
						上级区域：
						<input type="text" id="mod_parentAreaCode"
							onfocus="checkParentAreaForModArea()" />
						区域类型：
						<select name="area.areaType" id="mod_areaType" style="width:155px;"></select>
						<input type="hidden" name="area.parentArea" id="mod_parentArea" />
					</td>
				</tr>

				<tr>
					<td valign="top">
						<span>备注信息：</span>
						<textarea rows="5" cols="60" name="area.areaMemo" id="mod_areaMemo"
							class="validate[length[0,30]]"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<!-- 删除区域信息 -->
	<div id="deleteAreaDLG" title="删除区域信息" style="display:none;">
		<form action="" id="deleteArea_item">
			确认要删除所选择的区域信息吗?
			<input type="hidden" value="" id="delete_areaSids" name="areaSid">
		</form>
	</div>
</body>
</html>
