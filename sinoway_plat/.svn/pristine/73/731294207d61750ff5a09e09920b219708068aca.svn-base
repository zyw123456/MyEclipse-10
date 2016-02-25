<%--
  机构信息树展现页面                 
@date          2012/04/25         
@author        蒋正秋              
@version       1.0                
--%>
<%@page import="com.yzj.wf.core.model.mm.common.MMDefine"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
	List<Boolean> moduleCrudList = (List<Boolean>)session.getAttribute("MODULE_CRUD");
%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理导航菜单</title>

<%@ include file="/common/wf_import.jsp"%>
<%@ include file="/windforce/common/jsp/wfInternalImport.jsp" %>

<script type="text/javascript">
isrefresh();
function isrefresh()
{
var isrefresh = '<s:property value="isrefreshTree"/>';

if(isrefresh == "refresh")
{
top.onloadTree();
}
}
// 添加子模块
function addChild()
{
top.startProcess("正在初始化模块新增界面,请稍等...");
window.location.href = "<%=path%>/modules_InitAdd.action?miSid="+'<s:property value="mi.sid"/>';
}
// 修改模块
function update()
{
if('<s:property value="mi.sid"/>'=='00000000000000000000000000000000'){
top.wfAlert("你好，根模块不能修改!");
return;
}
top.startProcess("正在初始化模块编辑界面,请稍等...");
window.location.href = "<%=path%>/modules_initUpdate.action?miSid="
				+ '<s:property value="mi.sid"/>';
	}
</script>
</head>
<body style="overflow: hidden;background-color: transparent;" onload="top.stopProcess();">
	<div id="nov_nr" style="overflow: hidden;">
		<div class="nov_moon"
			style="width: 100%;height: 100%;margin-top:0px;padding-left: 20px;">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top">
						<table style="margin-top: 10px;" border="0" cellpadding="0"
							cellspacing="0" id="moduleOperate">
							<tr>
								<td align="right">模块名称：</td>
								<td align="left">
									<s:property value="mi.name" />
								</td>
							</tr>
							<tr>
								<td align="right">上级模块：</td>
								<td align="left">
									<s:property value="mi.parentModuleInfo.showname" />
								</td>
							</tr>
							<tr>
								<td align="right">模块URL：</td>
								<td align="left">
									<s:property value="mi.entrypoint_url" />
								</td>
							</tr>
							<tr>
								<td align="right">显示名称：</td>
								<td align="left">
									<s:property value="mi.showname" />
								</td>
							</tr>
							<tr>
								<td align="right">交易代码：</td>
								<td align="left">
									<s:property value="mi.hotkeystr" />
								</td>
							</tr>
							<tr>
								<td align="right">显示方式：</td>
								<td align="left">
									<s:if test="mi.isNavigation==1">系统菜单</s:if>
									<s:if test="mi.isTaskView==1">&nbsp;任务列表</s:if>
								</td>
							</tr>
							<tr>
								<td align="right">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</td>
								<td align="left">
									<s:if test="mi.state==1">
								    运行
								    </s:if>
									<s:if test="mi.state==0">
								    停用
								    </s:if>
									<s:if test="mi.state==2">
								    调试
								    </s:if>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="left" height="50px">
									<%
										if (moduleCrudList.get(0)) {
									%>
									<input type="button" class="submit_but04" value="添加子模块"
										onclick="addChild();" />
									&nbsp;&nbsp;
									<%
										}
										if (moduleCrudList.get(1)) {
									%>
									<input type="button" class="submit_but05" value="修改"
										onclick="update();" />
									<%
										}
									%>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>

