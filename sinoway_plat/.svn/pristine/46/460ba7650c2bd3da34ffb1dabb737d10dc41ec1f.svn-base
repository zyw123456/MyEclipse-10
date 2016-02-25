<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.yzj.wf.core.model.am.common.AMDefine"%>
<%@page import="com.yzj.wf.core.model.am.RoleGroupInfo"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="com.yzj.wf.core.model.am.RoleInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%--
  角色信息详细              
@date          2012/06/17         
@author       蒋正秋           
@version       1.0                
--%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>岗位管理页面</title>
<%
	// 正常适用的角色
    Integer normalRoles = 0;
    // 冻结的角色
    Integer nogalRoles = 0;
    
    Map<String, RoleInfo> roles = ((RoleGroupInfo)ServletActionContext.getRequest().getAttribute("detailGroup")).getRoleInfos();

    if(roles != null)
    {
    Set<String> sets = roles.keySet();
    Iterator<String> itor = sets.iterator();
    while(itor.hasNext())
    {
     String key = itor.next();
   
        if(roles.get(key).getRoleState() == AMDefine.RoleInfoState.NORMAL.getValue())
        {
        normalRoles ++;
        }
        if(roles.get(key).getRoleState() == AMDefine.RoleInfoState.CONGEAL.getValue())
        {
        nogalRoles ++;
        }
    }
    }
%>
<link type="text/css" rel="stylesheet"
	href="<%=path%>/windforce/common/css/ztree_ext.css" />

<%@ include file="/common/wf_import.jsp"%>
<%@ include file="/windforce/common/jsp/wfInternalImport.jsp" %>
<script type="text/javascript"
	src="<%=path%>/common/js/jquery/ztree/jquery.ztree.all-3.1.js"></script>

<script type="text/javascript">
	var isrefresh = '<s:property value="refreshTree"/>';
	if (isrefresh == "true") {
		top.refreshRoleGroupList();
	}
	$(document).ready(function() {
		var obj = top.document.getElementById("rightindex");
		var height = $(obj).css("height");
			height = height.substr(0, height.length - 2);
		var novHeight = height -5;
		$("#nov_nr").css("height", novHeight + "px");	
	});
</script>
</head>
<body style="background-color: transparent;overflow:hidden;" class="defaulFontColor">
	<div id="nov_nr" style="width: 100%;overflow-y:auto;">
		
			<table width="96%" border="0" align="center">
				<tr>
					<td width="28%">
						岗位名：
						<s:property value="detailGroup.roleGroupName" />
					</td>
					<td width="55%">
						描述：
						<s:property value="detailGroup.memo" />
					</td>
				</tr>
				<tr>
					<td width="100%" colspan="2">
						共<%=nogalRoles+normalRoles%>
						个角色
					</td>
				</tr>
				<tr>
					<td colspan="2" height="10"></td>
				</tr>
				<tr>
					<td colspan="2" valign="top" align="left">
						角&nbsp;&nbsp;&nbsp;&nbsp;色&nbsp;&nbsp;&nbsp;&nbsp;<br />
						<div class="uoon" style="height: auto; width: 70%;">
							<table width="100%" border="0" align="left" cellpadding="0"
								cellspacing="0">
								<tr>

									<td width="50%" height="26" align="center" class="font_colors07">角色名</td>
									<%--    <td width="15%" align="center"  class="font_colors07">是否可用</td>--%>
									<td width="50%" align="center" class="font_colors07">描述</td>

								</tr>
								<s:iterator id="roleInfo" value="detailGroup.roleInfos" status="index">
									<s:if test="#roleInfo.value.roleState ==  0">
										<tr
											id="grid<s:property value="#index.index%2 ==0 ? 'Even' : 'Odd'" />">

											<td height="28" align="center" class="border_bottom01">
												&nbsp;
												<s:property value="#roleInfo.value.roleName" />
												<%-- <a href="<%=path %>/roles_roleDetail.action?detailSid=<s:property value='#role.sid'/>" target="roleOperate"></a> --%>
											</td>
											<%--    <td align="center" class="border_bottom01" >&nbsp;是</td>--%>
											<td align="center" class="border_bottom01">
												&nbsp;
												<s:property value="#roleInfo.value.memo" />
											</td>

										</tr>
									</s:if>
								</s:iterator>
							</table>

						</div>
					</td>
				</tr>
				<tr>
					<td height="10"></td>
				</tr>
				<tr>
					<td width="100%" colspan="2">
						适用机构类型&nbsp;&nbsp;
						<div class="uoon" style="height: auto;width:50%;">
							<s:if test="detailGroup.orgTypes.size() < 1">&nbsp;暂无</s:if>
							<s:if test="detailGroup.orgTypes.size() > 0">
								<s:iterator value="detailGroup.orgTypes" id="orgType">
      &nbsp;<s:property value="#orgType.value.orgTypeName" />
								</s:iterator>
							</s:if>

						</div>
					</td>
				</tr>
				<tr>
					<td height="10"></td>
				</tr>
				<tr>
					<td width="100%" colspan="2">
						当前互斥岗位列表&nbsp;&nbsp;
						<div class="uoon" style="height: auto; width:70%;">
							<s:if test="detailGroup.mutexRoleGouptInfos.size() > 0">
								<table width="100%" height="100%" border="0" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="45%" height="26" align="center" class="font_colors07">岗位名称</td>
										<td width="50%" align="center" class="font_colors07">描述</td>
									</tr>
									<s:iterator value="detailGroup.mutexRoleGouptInfos" id="mutex">
										<tr>

											<td height="28" align="center" class="border_bottom01">
												&nbsp;
												<s:property value="#mutex.value.roleGroupName" />
											</td>
											<td align="center" class="border_bottom01">
												&nbsp;
												<s:property value="#mutex.value.memo" />
											</td>

										</tr>

									</s:iterator>
								</table>
							</s:if>


							<s:if test="detailGroup.mutexRoleGouptInfos.size() < 1">&nbsp;暂无</s:if>


						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">



							<tr>
								<td colspan="2" valign="top"></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2" height="10"></td>
				</tr>
			</table>
		
	</div>
	<script type="text/javascript">
		top.stopProcess();
	</script>
</body>
</html>


