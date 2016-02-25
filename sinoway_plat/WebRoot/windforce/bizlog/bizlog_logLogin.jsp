<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
	<title>用户登录日志</title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="Content-Language" content="zh-CN" />
	<meta name="Copyright" content="Shenzhen Infotech Technologies CO.,LTD" />
	
	<link rel="stylesheet" href="${ctx }/windforce/v1_5/common/css/style_sys.css"
		type="text/css" />
	<link href="${ctx }/windforce/v1_5/common/css/jquery-ui/cupertino/jquery-ui.css"
		rel="stylesheet" type="text/css"></link>
	<link href="${ctx }/windforce/v1_5/common/css/jqgrid/ui.jqgrid.css"
		rel="stylesheet" type="text/css"></link>
	<link href="${ctx }/windforce/v1_5/common/css/icon/icon.css" rel="stylesheet"
		type="text/css"></link>
	<link href="${ctx }/windforce/v1_5/common/css/ztree/zTreeStyle.css"
		rel="stylesheet" type="text/css"></link>
		
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
	
	<script src="${ctx }/windforce/common/js/commonLayout.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="${ctx }/common/js/framework/util.js"></script>
		
	<script src="${ctx }/windforce/v1_5/common/js/ztree/jquery.ztree.all-3.4.min.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		 src="${ctx }/common/js/framework/organization.js"></script>
	<script type="text/javascript"
		src="${ctx }/common/js/framework/framework.js"></script>
	<script  type="text/javascript" 
		src="${ctx }/common/js/framework/org.js"></script>
	<script type="text/javascript"
		src="${ctx}/windforce/common/js/bizlog/bizConstants.js"></script>
	<script type="text/javascript"
		src="${ctx}/windforce/common/js/bizlog/bizlog_logLogin.js"></script>
	<!-- 日期控件 -->
	<script type="text/javascript"
		src="${ctx }/windforce/common/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript"
		src="${ctx}/windforce/common/js/dateUtil.js"></script>
		
	<script>
		var ctx = "${ctx}";
		var basePath = "<%=basePath%>";
		$(function() {
			 $(":button").button();
		});
	</script>
	
	
	</head>
	
	<body>
		<div class="pageHeader">
			<form id="queryForm" method="post">
				<div class="searchBar">
					<table class="searchContent">
						<tr>
							<td>　操作类型：<select id="operateType" style="width:126px;" name="operateType">
									<option value="" selected="selected">全部</option>
									<option value="-1">登录</option>
									<option value="5">登出</option>
								</select>
							</td>
							<td>操作人代码：<input style="width:120px;" name="operatorCode"></input></td>
							<td>
								机构:
								<input type="text"  style="width:250px;" id="organizationSid_Item" readonly="readonly"/>
								<input type="hidden"  id="operatorOrgNo" name="operatorOrgNo"/>
								<img src="${ctx }/windforce/common/images/wt_grid.png" alt="选择机构代码" style="width: 13px;height:13px;padding-left: 3px;cursor: hand;" onclick="checkOrganizationItem('operatorOrgNo')"/>
							</td>
						</tr>
						<tr>
							<td>　开始日期：<input name="ge_operateDate" id="ge_operateDate" style="width:120px;" 
									onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'});"
									onMouseOut="dateLimitMax('le_operateDate','ge_operateDate');"></input></td>
							<td>　结束日期：<input name="le_operateDate" id="le_operateDate" style="width:120px;" 
									onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'});"
									onMouseOut="dateLimitMin('ge_operateDate','le_operateDate');"></input></td>
						</tr>
					</table>
					<div class="subBar">
						<ul>
							<li>
								<div>
									<input type="button" id="queryBtn" onclick="queryData();" value="查询" />&nbsp;
									<input type="button" id="resetBtn" onclick="resetMethod();" value="重置" />
								</div>
							</li>
						</ul>
					</div>
				</div>
			</form>
		</div>
		
		<div class="pageContent">
			<!-- 数据表格 -->
			<table id="logLoginList" style="font-size:14px;"></table>
			<!-- 分页 -->
			<div id="logLoginPager"></div>
		</div>
	</body>
</html>
