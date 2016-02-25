
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	List<Boolean> roleCrudList = (List<Boolean>) session
			.getAttribute("ROLE_CRUD");
%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td width="10%" align="center" bgcolor="#4d657f" class="font_colors07"></td>
		<td width="45%" height="26" align="center" class="font_colors07">角色名</td>
		<td width="45%" align="center" class="font_colors07">操作</td>

	</tr>
	<s:if test="roleList.size() == 0">
		<tr>
			<td colspan="3" align="center" bgcolor="#F4DDDF" height="22">无相关角色!</td>
		</tr>
	</s:if>
	<s:else>
		<s:iterator id="role" value="roleList" status="index">
			<tr id="grid<s:property value="#index.index%2 ==0 ? 'Even' : 'Odd'" />">
				<td align="center" class="border_bottom01">
					&nbsp;
					<%
						if (roleCrudList.get(2)) {
					%>
					<s:if test="#role.sid != 'superrole'">
						<input type="checkbox" name="ids" value='<s:property value="#role.sid"/>' />
					</s:if>
					<%
						}
					%>
				</td>
				<td height="28" align="center" class="border_bottom01">
					&nbsp;
					<a
						href="<%=path%>/roles_roleDetail.action?detailSid=<s:property value='#role.sid'/>"
						target="roleOperate" onclick="top.startProcess('正在获取角色信息,请稍等...');">
						<s:property value="#role.roleName" />
					</a>
				</td>

				<td width="15%" align="left" class="border_bottom01">
					<%
						if (roleCrudList.get(1)) {
					%>
					<input type="button"
						onclick="modifyRole('<s:property value='#role.sid'/>')"
						class="submit_but45_24" value="修改" />
					<%
						}
								if (roleCrudList.get(2)) {
					%>
					<s:if test="#role.sid != 'superrole'">
						<input type="button"
							onclick="doDel('one','<s:property value='#role.sid'/>');"
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


