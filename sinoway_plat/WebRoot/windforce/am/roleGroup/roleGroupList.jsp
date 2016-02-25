
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	List<Boolean> roleGroupCrudList = (List<Boolean>) session
			.getAttribute("ROLE_GROUP_CRUD");
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<table id="refreshData" border="0" width="100%" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="10%" align="center" bgcolor="#4d657f" class="font_colors07"></td>
		<td width="50%" height="26" align="center" bgcolor="#4d657f"
			class="font_colors07">岗位名</td>
		<td width="25%" align="center" bgcolor="#4d657f" class="font_colors07">
			操作</td>

	</tr>
	<s:if test="allRoleGroupList.size() == 0">
		<tr>
			<td colspan="3" align="center" bgcolor="#F4DDDF" height="22">无相关岗位!</td>
		</tr>
	</s:if>
	<s:else>
		<s:iterator id="roleGroup" value="allRoleGroupList" status="index">
			<tr id="grid<s:property value="#index.index%2 ==0 ? 'Even' : 'Odd'" />">
				<td align="center" class="border_bottom01">
					&nbsp;
					<%
						if (roleGroupCrudList.get(2)) {
					%>
					<s:if test="#roleGroup.sid != 'supergroup'">
						<input type="checkbox" name="ids"
							value='<s:property value="#roleGroup.sid"/>' />
					</s:if>
					<%
						}
					%>
				</td>
				<td height="28" align="center" class="border_bottom01">
					&nbsp;
					<a
						href="<%=path%>/rolegroup_toDetail.action?detailSid=<s:property value='#roleGroup.sid'/>"
						target="roleOperate" onclick="top.startProcess('正在获取岗位信息,请稍等...');">
						<s:property value="#roleGroup.roleGroupName" />
					</a>
				</td>

				<td width="15%" align="left" class="border_bottom01">
					<%
						if (roleGroupCrudList.get(1)) {
					%>
					<input type="button"
						onclick="modifyRoleGroup('<s:property value='#roleGroup.sid'/>');"
						class="submit_but45_24" value="修改" />
					<%
						}
								if (roleGroupCrudList.get(2)) {
					%>
					<s:if test="#roleGroup.sid != 'supergroup'">
						<input type="button"
							onclick="doDel('one','<s:property value='#roleGroup.sid'/>');"
							class="submit_but45_24" value="删除" />
					</s:if>
					<%
						}
					%>
				</td>
			</tr>
		</s:iterator>
	</s:else>
</table>



