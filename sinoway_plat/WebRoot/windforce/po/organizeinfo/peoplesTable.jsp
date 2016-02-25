
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	List<Boolean> poCrudList = (List<Boolean>) session.getAttribute("PO_CRUD");
%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table id="trAutoComplete" style="width:100%;height:auto;border-collapse:collapse;border:0px;padding:0;align:left;">
	<tr style="height: 26px;">
		<td width="5%" align="center" class="font_colors07">序号</td>
		<s:if test="peopleInfoView.peopleName">
			<td width="12%" align="center" class="font_colors07">用户姓名</td>
		</s:if>
		<s:if test="peopleInfoView.peopleCode">
			<td width="12%" align="center" class="font_colors07">用户代码</td>
		</s:if>
		<s:if test="peopleInfoView.organizeInfo">
			<td width="12%" align="center" class="font_colors07">所属机构</td>
		</s:if>
		<s:if test="peopleInfoView.organizeName">
			<td width="12%" align="center" class="font_colors07">机构名称</td>
		</s:if>
		<s:if test="peopleInfoView.roleGroupInfos">
			<td width="12%" align="center" class="font_colors07">岗位信息</td>
		</s:if>
		<s:if test="peopleInfoView.peopleGender">
			<td width="10%" align="center" class="font_colors07">性别</td>
		</s:if>
		<s:if test="peopleInfoView.peopleState">
			<td width="10%" align="center" class="font_colors07">状态</td>
		</s:if>
		<%
			if(poCrudList.get(4) || poCrudList.get(5) || poCrudList.get(6)||poCrudList.get(11)||poCrudList.get(12)){
		%>
		<td width="15%" align="center" class="font_colors07">操作</td>
		<%
			}
		%>
	</tr>
	<s:iterator value="userList" var="userInfo" status="index">
		<!--  class="gradeX"  这句话别删除，用于自动搜索使用 -->
		<tr class="gradeX grid<s:property value="#index.index%2 ==0 ? 'Even' : 'Odd'" />">
			<td align="center" onclick="setUserDetail(this);">${index.index +1 }</td>
			<s:if test="peopleInfoView.peopleName">
				<td align="center" onclick="setUserDetail(this);" style="color: blue;">
					<a onclick="javascript:top.userDetail('<s:property value='#userInfo.peopleCode'/>');"> <s:property
							value="#userInfo.peopleName" /> </a>
				</td>
			</s:if>
			<s:if test="peopleInfoView.peopleCode">
				<td align="center" onclick="setUserDetail(this);">
					<s:property value="#userInfo.peopleCode" />
				</td>
			</s:if>
			<s:if test="peopleInfoView.organizeInfo">
				<td align="center" onclick="setUserDetail(this);">
					<s:property value="#session.ORG_SID_NO_MAP.get(#userInfo.organizeInfo)" />
				</td>
			</s:if>
			<s:if test="peopleInfoView.organizeName">
				<td align="center" onclick="setUserDetail(this);">
					<s:property value="#userInfo.orgInfo.orgName" />
				</td>
			</s:if>
			<s:if test="peopleInfoView.roleGroupInfos">
				<td align="center" onclick="setUserDetail(this);">
					<s:iterator value="#userInfo.roleGroupInfos" var="roleGroupInfos" status="roleGroupIndex">
						<s:if test="#roleGroupIndex.index == 0">
							<s:property value="#roleGroupInfos.roleGroupName" />
						</s:if>
						<s:else>,<s:property value="#roleGroupInfos.roleGroupName" /></s:else>
					</s:iterator>
				</td>
			</s:if>
			<s:if test="peopleInfoView.peopleGender">
				<td align="center" class="center" onclick="setUserDetail(this);">
					<s:if test="#userInfo.peopleGender == 0">男</s:if>
					<s:if test="#userInfo.peopleGender == 1">女</s:if>
				</td>
			</s:if>
			<s:if test="peopleInfoView.peopleState">
				<td align="center" class="center" onclick="setUserDetail(this);">
					<s:if test="#userInfo.peopleState == -2">锁定</s:if>
					<s:if test="#userInfo.peopleState == -1">删除</s:if>
					<s:if test="#userInfo.peopleState == 0">正常</s:if>
					<s:if test="#userInfo.peopleState == 1">停用</s:if>
					<s:if test="#userInfo.peopleState == 2">等待审批</s:if>
					<s:if test="#userInfo.peopleState == 3">审批拒绝</s:if>
					<s:if test="#userInfo.peopleState == 4">退回修改</s:if>
				</td>
			</s:if>
			<td align="center">
				<s:if test="poAuthConfig.enableHeadManageBranchGeneralPeople">
					<s:if test="poAuthConfig.enableModifiedOwnInfo || (#session.XPEOPLEINFO.sid != #userInfo.sid)">
						<s:if test="(#userInfo.sid !='7B92AE0FC4B04DB48F1AFBDB22CD7188'
									&& #userInfo.peopleState!=2 && #userInfo.peopleState!=3 && #userInfo.peopleState!=4)">
							<%
								if(poCrudList.get(4)){
							%>
							<a class="operateLink" onclick="editUser('<s:property value='#userInfo.sid'/>');">修改</a> /
						<%
								} if(poCrudList.get(5)){
							%>
							<a class="operateLink"
								onclick="delPeople('<s:property value='#userInfo.sid'/>','<s:property value='#userInfo.organizeInfo.orgNo'/>')">删除</a>/ 
						<%
								} if(poCrudList.get(11)){
							%>
							<a class="operateLink" onclick="startUsingPeople('<s:property value='#userInfo.sid'/>')">启用</a>/
						<%
								} if(poCrudList.get(12)){
							%>
							<a class="operateLink" onclick="disusePeople('<s:property value='#userInfo.sid'/>')">停用</a>/
						<%
								}
							%>
						</s:if>
						<%
							if(poCrudList.get(6)){
						%>
						<a class="operateLink" onclick="resetPwd('<s:property value='#userInfo.sid'/>','<s:property value='#userInfo.email'/>')">重置密码</a>
						<%
							}
						%>
					</s:if>
				</s:if>
				<s:else>
					<s:if
						test="(#session.XPEOPLEINFO.organizeSid == '00000000000000000000000000000000') && ((#userInfo.organizeInfo == '00000000000000000000000000000000') || #userInfo.adminFlag)">
						<s:if test="poAuthConfig.enableModifiedOwnInfo || (#session.XPEOPLEINFO.sid != #userInfo.sid)">
							<s:if test="(#userInfo.sid !='7B92AE0FC4B04DB48F1AFBDB22CD7188'
										&& #userInfo.peopleState!=2 && #userInfo.peopleState!=3 && #userInfo.peopleState!=4)">
								<%
									if(poCrudList.get(4)){
								%>
								<a class="operateLink" onclick="editUser('<s:property value='#userInfo.sid'/>');">修改</a> /
								<%
									} if(poCrudList.get(5)){
								%>
								<a class="operateLink"
									onclick="delPeople('<s:property value='#userInfo.sid'/>','<s:property value='#userInfo.organizeInfo.orgNo'/>')">删除</a>/ 
								<%
									} if(poCrudList.get(11)){
								%>
								<a class="operateLink" onclick="startUsingPeople('<s:property value='#userInfo.sid'/>')">启用</a>/
								<%
									} if(poCrudList.get(12)){
								%>
								<a class="operateLink" onclick="disusePeople('<s:property value='#userInfo.sid'/>')">停用</a>/
								<%
									}
								%>
							</s:if>
							<%
								if(poCrudList.get(6)){
							%>
							<a class="operateLink" onclick="resetPwd('<s:property value='#userInfo.sid'/>','<s:property value='#userInfo.email'/>')">重置密码</a>
							<%
								}
							%>
						</s:if>
					</s:if>
					<s:elseif test="(#session.XPEOPLEINFO.organizeSid != '00000000000000000000000000000000') && !(#userInfo.adminFlag)">
						<s:if test="poAuthConfig.enableModifiedOwnInfo || (#session.XPEOPLEINFO.sid != #userInfo.sid)">
							<s:if test="(#userInfo.sid !='7B92AE0FC4B04DB48F1AFBDB22CD7188'
										&& #userInfo.peopleState!=2 && #userInfo.peopleState!=3 && #userInfo.peopleState!=4)">
								<%
									if(poCrudList.get(4)){
								%>
								<a class="operateLink" onclick="editUser('<s:property value='#userInfo.sid'/>');">修改</a> /
								<%
									} if(poCrudList.get(5)){
								%>
								<a class="operateLink"
									onclick="delPeople('<s:property value='#userInfo.sid'/>','<s:property value='#userInfo.organizeInfo.orgNo'/>')">删除</a>/ 
								<%
									} if(poCrudList.get(11)){
								%>
								<a class="operateLink" onclick="startUsingPeople('<s:property value='#userInfo.sid'/>')">启用</a>/
								<%
									} if(poCrudList.get(12)){
								%>
								<a class="operateLink" onclick="disusePeople('<s:property value='#userInfo.sid'/>')">停用</a>/
								<%
									}
								%>
							</s:if>
							<%
								if(poCrudList.get(6)){
							%>
							<a class="operateLink" onclick="resetPwd('<s:property value='#userInfo.sid'/>','<s:property value='#userInfo.email'/>')">重置密码</a>
							<%
								}
							%>
						</s:if>
					</s:elseif>
				</s:else>
			</td>
		</tr>
	</s:iterator>
</table>

