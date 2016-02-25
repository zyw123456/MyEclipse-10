


<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>机构迁移界面</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/windforce/common/css/ztree_ext.css" ></link>
<%@ include file="/windforce/common/jsp/wfInternalImport.jsp" %>
<script type="text/javascript" src="<%=path%>/common/js/jquery/ztree/jquery.ztree.all-3.1.js"></script>
<script type="text/javascript">
	var psetting = {
		check: {
			enable : true,
			chkStyle : "checkbox",
			chkboxType : {
			"Y" : "s",
			"N" : "ps"
			}
		},
        view:{
        	selectedMulti : false
        }
	};
	var osetting = {
	    callback: {
			onClick: function(event, treeId, treeNode){
				targetOrgId = treeNode.sid;
				targetOrgName = treeNode.name;
			}
	    },
        view:{
        	selectedMulti : false
        }
    };
        
	var originOrgId = "<s:property value='originOrgId'/>";
	var targetOrgId = "";
	var targetOrgName = "";
	
	function summbitMigrateOrg(){
		if(isNull(targetOrgId)){
			top.wfAlert("请选择迁移的新机构!");
			return;
		}
		if(originOrgId == targetOrgId){
			top.wfAlert("机构迁移目标机构与原始机构相同,不能执行机构迁移操作!");
			return;
		}
		if(window.confirm('您确认执行机构迁移操作?','提示信息')){
			var operAuth=new top.OperAuth();
			operAuth.operType="orgMigrate";
			operAuth.authSuccess=function(authUserCode){
				$.post("<%=path%>/peopleAndOrgMigrateAction_summbitMigrateOrgCheck.action", 
				{
					originOrgId:originOrgId,
					targetOrgId:targetOrgId
				},
				function(data){
					var dataObj = $.parseJSON(data);
					if(dataObj.status == '0000'){
						$.post("<%=path%>/peopleAndOrgMigrateAction_summbitMigrateOrg.action", 
							{
								originOrgId:originOrgId,
								targetOrgId:targetOrgId,
								authUserCode:authUserCode
							},
							function(data){
							var dataObj = $.parseJSON(data);
							if(dataObj.status == '0000'){
								top.wfAlert("机构迁移操作成功!");
								closeShowPage();
								top.refreshLeft();//刷新列表
							}else{
								top.wfAlert(dataObj.ajaxData);
							}
						});
					}else{
						top.wfAlert(dataObj.ajaxData);
					}
				});
			};
			operAuth.auth();
		}
	}
	
	$(document).ready(function() {
		$.getJSON("<%=path%>/getOrganizeList.action?_t="+ new Date().getTime(), function(jsonData){
			$.fn.zTree.init($("#orgTree"), osetting, jsonData);//初始化机构树
		});
	});
</script>
</head>
<body>
	<table>
		<tr>
			<td width="380px" height="380px" valign="top">
				<label>请选择迁移目标机构</label>
				<div style="overflow: auto; width: 380px;height: 360px">
					<ul id="orgTree" class="ztree"></ul>
				</div>
			</td>
		</tr>
		<tr>
			<td align="right">
				<input type="button" onclick="summbitMigrateOrg();" value="保存" class="submit_but05" />
				<input type="button" onclick="closeShowPage();" value="关闭" class="submit_but05" />
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		top.stopProcess();
	</script>
</body>
</html>


