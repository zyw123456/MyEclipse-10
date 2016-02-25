<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
    <title>印章管理系统-机构工作时间设置</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
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
	<script type="text/javascript"
		 src="${ctx }/common/js/framework/organization.js"></script>
	<script type="text/javascript"
		src="${ctx }/common/js/framework/framework.js"></script>
	<script  type="text/javascript" 
		src="${ctx }/common/js/framework/org.js"></script>
	<!-- 日期控件 -->
	<script type="text/javascript"
		src="${ctx }/windforce/common/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript"
		src="${ctx}/windforce/common/js/dateUtil.js"></script>

	<script type="text/javascript" src="${ctx }/windforce/common/js/wt/orgWorkingTime.js"></script>
	<script type="text/javascript" >
		var ctx = "${ctx }";
		var orgTypeList = null;
		$(function() {
			 $(":button").button();
			 var iContentH = $(window).height()  - 40;
			 $("#sidebar").height(iContentH + 32);
			 $(".accordion").height(iContentH);
			 initOrgTree();//机构树
			 workingTimeInit();
		});
	</script>
  </head>
  
 <body>
 	<table cellpadding="0" cellspacing="0" class="contentTable">
 	 	<tr>
 			<td class="navleft" >
	 			<div id="sidebar">
					<div class="toggleCollapse">
						<h2>机构列表</h2>
					</div>
					<div class="accordion" >
						<div class="accordionContent" id="orgTreeItem"></div>
					</div>
				</div>
 			</td>
 			<td  class="contentTablesplit"  ></td>
 			<td>
				<div class="pageHeader">
						<form method="post" id="WorkingTimeForm">
							<div class="searchBar">
								<table class="searchContent">
									<tr>
										<td>
											机构:
											<input type="text"  style="width:250px;" id="organizationSid_Item" readonly="readonly"/>
											<input type="hidden"  id="organizationSid" name="organizationSid"/>
											<img src="${ctx }/windforce/common/images/wt_grid.png" alt="选择机构代码" style="width: 13px;height:13px;padding-left: 3px;cursor: hand;" onclick="checkOrganizationItem('organizationSid')"/>
										</td>
										<td>
											下级机构:
											<select  style="width:90px;" id="childOrganizations" name="childOrganizations">
												<option value="1">不显示</option>
												<option value="2">显示</option>
											</select>
										</td>
								</tr>
							</table>
							<div class="subBar">
								<ul>
									<li><div class="buttonActive"><div class="buttonContent"><input type="button" onclick="queryWorkingTimeForTerm()" value="查询"/></div></div></li>
									<li><div class="buttonActive"><div class="buttonContent"><input type="button" onclick="clearWorkingTimeForTerm()" value="清除"/></div></div></li>
								</ul>
							</div>
						</div>
						</form>
				</div>
					
				<div class="pageContent">  
					<div class="panelBar">
						<ul class="toolBar">
							<li class="line">line</li>
							<li><a class="add" href="#"   onclick="createAddWorkingTimeDLG()"><span>批量设置</span></a></li>
							<li class="line">line</li>
							<li><a  class="delete" href="#"    onclick="createClearWorkingTimeDLG()"><span>批量清除</span></a></li>
							<li class="line">line</li>
							<!-- <li><a  class="edit" href="#"    onclick="createModWorkingTimeDLG()"><span>修改机构工作时间</span></a></li>
							<li class="line">line</li> -->
						</ul>
					</div>
					<!-- 数据表格 -->
					<table id="orgWorkingTimeList" ></table>
					<!-- 分页 -->
					<div id="orgWorkingTimeListPager" ></div>
				</div>
 			</td>
 		</tr>
 	</table>
 	
    <!-- 设置机构工作时间 -->
	<div id="AddWorkingTimeDLG" title="设置机构工作时间" style="padding:  0 0 0 0;display:none;">
		<form action=""  id="orgFormItem" style="margin-top:3px;margin-left: 4px;">
			机构:
			<input type="text"  style="width:250px;" id="addOrganizationSid_Item" readonly="readonly"/>
			<input type="hidden"  id="addOrganizationSid" name="organizationSid"/>
			<img src="${ctx }/windforce/common/images/wt_grid.png" alt="选择机构代码" style="width: 13px;height:13px;padding-left: 3px;cursor: hand;" onclick="checkOrganizationItem('addOrganizationSid')"/>
			<input type="button" onclick="checkChildOrganizations()" value="查询"/>
		</form>
		<div style="height:3px;"></div>
		<!-- 数据表格 -->
		<table id="AddWorkingTimeOrgItemList" ></table>
		<!-- 分页 -->
		<div id="AddWorkingTimeOrgItemListPager" ></div>
		
		<!-- 新建机构工作时间 -->
		<form action="" id="AddWorkingTime_term">
			<input type="hidden" id="setOrganizationSid" name="organizationSids"/>
			<table style="width:100%;"  class="t-application" >
				<tr >
					<td>
						星期一:
						<input type="text"  name="orgWorkingTime.monStartTime" id="monStartTime"  value="07:00:00"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text" name="orgWorkingTime.monEndTime" id="monEndTime"  value="19:00:00"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;" />
					</td>
					<td >
						星期二:
						<input type="text" name="orgWorkingTime.tuesStartTime" id="tuesStartTime"  value="07:00:00"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text" name="orgWorkingTime.tuesEndTime" id="tuesEndTime"   value="19:00:00"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;" />
					</td>
				</tr>
							
				<tr >
					<td>
						星期三:
						<input type="text"  name="orgWorkingTime.wedStartTime" id="wedStartTime" value="07:00:00"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text"  name="orgWorkingTime.wedEndTime"  id="wedEndTime"  value="19:00:00"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;" />
					</td>
					
					<td>
						星期四:
						<input type="text"  name="orgWorkingTime.thursStartTime"  id="thursStartTime"  value="07:00:00" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text"  name="orgWorkingTime.thursEndTime" id="thursEndTime"    value="19:00:00" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;" />
					</td>
				</tr>
				
				<tr >
					<td>
						星期五:
						<input type="text" name="orgWorkingTime.tridStartTime" id="fridStartTime" value="07:00:00" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;"/>
						 <span style="font-size: 12pt;">-</span> 
						<input type="text" name="orgWorkingTime.tridEndTime" id="fridEndTime"    value="19:00:00" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;" />
					</td>
					<td >
						星期六:
						<input type="text" name="orgWorkingTime.satStartTime" id="satStartTime"  value="07:00:00" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text" name="orgWorkingTime.satEndTime" id="satEndTime"   value="19:00:00" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;" />
					</td>
				</tr>
							
				<tr >
					<td colspan="2">
						星期日:
						<input type="text" name="orgWorkingTime.sunStartTime" id="sunStartTime" value="07:00:00" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text" name="orgWorkingTime.sunEndTime" id="sunEndTime"    value="19:00:00" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;" />
					</td>
				</tr>
			</table>
		</form>
 	</div>
 	
 	 <!-- 修改机构工作时间 -->
	<div id="ModWorkingTimeDLG" title="修改机构工作时间" style="padding:  0 0 0 0;display:none;">
		<form action=""  id="ModOrgFormItem" style="margin-top:3px;margin-left: 4px;">
			<input type="hidden" name="organizationSids" id="modOrganizationSids"/>
		</form>
		<!-- 数据表格 -->
		<table id="ModWorkingTimeOrgItemList" ></table>
		<!-- 分页 -->
		<div id="ModWorkingTimeOrgItemListPager" ></div>
		<form action=""  id="ModWorkingTimeFormItem" >
			<input type="hidden" id="ModOrganizationSid" name="organizationSids"/>
			<table style="width:100%;"  class="t-application" >
				<tr >
					<td>
						星期一:
						<input type="text"  name="orgWorkingTime.monStartTime" id="ModmonStartTime"  value="07:00:00"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text" name="orgWorkingTime.monEndTime" id="ModmonEndTime"  value="19:00:00"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;" />
					</td>
					<td >
						星期二:
						<input type="text" name="orgWorkingTime.tuesStartTime" id="ModtuesStartTime"  value="07:00:00"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text" name="orgWorkingTime.tuesEndTime" id="ModtuesEndTime"   value="19:00:00"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;" />
					</td>
				</tr>
							
				<tr >
					<td>
						星期三:
						<input type="text"  name="orgWorkingTime.wedStartTime" id="ModwedStartTime" value="07:00:00"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text"  name="orgWorkingTime.wedEndTime"  id="ModwedEndTime"  value="19:00:00"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;" />
					</td>
					
					<td>
						星期四:
						<input type="text"  name="orgWorkingTime.thursStartTime"  id="ModthursStartTime"  value="07:00:00" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text"  name="orgWorkingTime.thursEndTime" id="ModthursEndTime"    value="19:00:00" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;" />
					</td>
				</tr>
				
				<tr >
					<td>
						星期五:
						<input type="text" name="orgWorkingTime.tridStartTime" id="ModfridStartTime" value="07:00:00" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;"/>
						 <span style="font-size: 12pt;">-</span> 
						<input type="text" name="orgWorkingTime.tridEndTime" id="ModfridEndTime"    value="19:00:00" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;" />
					</td>
					<td >
						星期六:
						<input type="text" name="orgWorkingTime.satStartTime" id="ModsatStartTime"  value="07:00:00" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text" name="orgWorkingTime.satEndTime" id="ModsatEndTime"   value="19:00:00" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;" />
					</td>
				</tr>
							
				<tr >
					<td colspan="2">
						星期日:
						<input type="text" name="orgWorkingTime.sunStartTime" id="ModsunStartTime" value="07:00:00" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text" name="orgWorkingTime.sunEndTime" id="ModsunEndTime"    value="19:00:00" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:90px;" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- 查看机构工作时间 -->
	<div id="CHKWorkingTimeDLG" title="查看机构工作时间" style="padding:  0 0 0 0;display:none;">
		<form action=""  id="CHKWorkingTimeFormItem"  style="margin-top: 3px;">
			<table style="width:100%;"  class="t-application" >
				<tr >
					<td>
						&nbsp;&nbsp;&nbsp;机构：
						<input type="text"  style="width:260px;" id="chkOrganizationSid_Item" readonly="readonly"/>
					</td>
				</tr>
				
				<tr >
					<td>
						星期一：
						<input type="text"   id="chkmonStartTime"  style="width:120px;" readonly="readonly"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text" id="chkmonEndTime"   style="width:120px;" readonly="readonly" />
					</td>
				</tr>
					
				<tr>
					<td >
						星期二：
						<input type="text" id="chktuesStartTime" style="width:120px;" readonly="readonly"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text"  id="chktuesEndTime"  style="width:120px;" readonly="readonly" />
					</td>
				</tr>
							
				<tr >
					<td>
						星期三：
						<input type="text"  id="chkwedStartTime" style="width:120px;" readonly="readonly"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text"    id="chkwedEndTime" style="width:120px;" readonly="readonly" />
					</td>
				</tr>
					
				<tr>	
					<td>
						星期四：
						<input type="text"   id="chkthursStartTime"  style="width:120px;" readonly="readonly"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text"  id="chkthursEndTime"   style="width:120px;" readonly="readonly" />
					</td>
				</tr>
				
				<tr >
					<td>
						星期五：
						<input type="text"  id="chkfridStartTime"style="width:120px;" readonly="readonly"/>
						 <span style="font-size: 12pt;">-</span> 
						<input type="text"  id="chkfridEndTime"  style="width:120px;" readonly="readonly" />
					</td>
				</tr>
					
				<tr>	
					<td >
						星期六：
						<input type="text"  id="chksatStartTime" style="width:120px;" readonly="readonly"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text"  id="chksatEndTime"   style="width:120px;" readonly="readonly" />
					</td>
				</tr>
							
				<tr >
					<td colspan="2">
						星期日：
						<input type="text" id="chksunStartTime"  style="width:120px;" readonly="readonly"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text" id="chksunEndTime"    style="width:120px;" readonly="readonly" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- 修改机构工作时间 -->
	<div id="MODSIGWorkingTimeDLG" title="修改机构工作时间" style="padding:  0 0 0 0;display:none;">
		<form action=""  id="ModSigWorkingTimeFormItem" style="margin-top: 3px;">
			<table style="width:100%;"  class="t-application" >
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;机构：
						<input type="text"  style="width:260px;" id="ModSigOrganizationSid_Item" readonly="readonly"/>
						<input type="hidden" id="ModSigAutoId" name="orgWorkingTime.autoId"/>
						<input type="hidden" id="ModSigOrganizationSid" name="orgWorkingTime.organizationSid"/>
					</td>
				</tr>
				<tr>
					<td>
						星期一：
						<input type="text"  name="orgWorkingTime.monStartTime" id="ModSigmonStartTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:120px;"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text" name="orgWorkingTime.monEndTime" id="ModSigmonEndTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})"style="width:120px;" />
					</td>
				</tr>
							
				<tr>
					<td >
						星期二：
						<input type="text" name="orgWorkingTime.tuesStartTime" id="ModSigtuesStartTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})"  style="width:120px;"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text" name="orgWorkingTime.tuesEndTime" id="ModSigtuesEndTime"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:120px;" />
					</td>
				</tr>
							
				<tr>
					<td>
						星期三：
						<input type="text"  name="orgWorkingTime.wedStartTime" id="ModSigwedStartTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:120px;"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text"  name="orgWorkingTime.wedEndTime"  id="ModSigwedEndTime"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:120px;" />
					</td>
				</tr>
							
				<tr>
					<td>
						星期四：
						<input type="text"  name="orgWorkingTime.thursStartTime"  id="ModSigthursStartTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})"  style="width:120px;"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text"  name="orgWorkingTime.thursEndTime" id="ModSigthursEndTime"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:120px;" />
					</td>
				</tr>
				
				<tr>
					<td>
						星期五：
						<input type="text" name="orgWorkingTime.tridStartTime" id="ModSigfridStartTime"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:120px;"/>
						 <span style="font-size: 12pt;">-</span> 
						<input type="text" name="orgWorkingTime.tridEndTime" id="ModSigfridEndTime"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})"  style="width:120px;" />
					</td>
				</tr>
							
				<tr>
					<td >
						星期六：
						<input type="text" name="orgWorkingTime.satStartTime" id="ModSigsatStartTime"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})"   style="width:120px;"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text" name="orgWorkingTime.satEndTime" id="ModSigsatEndTime"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:120px;" />
					</td>
				</tr>
							
				<tr>
					<td colspan="2">
						星期日：
						<input type="text" name="orgWorkingTime.sunStartTime" id="ModSigsunStartTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:120px;"/>
						<span style="font-size: 12pt;">-</span>
						<input type="text" name="orgWorkingTime.sunEndTime" id="ModSigsunEndTime"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss'})" style="width:120px;" />
					</td>
				</tr>
			</table>
		</form>
	</div>
  </body>
</html>
