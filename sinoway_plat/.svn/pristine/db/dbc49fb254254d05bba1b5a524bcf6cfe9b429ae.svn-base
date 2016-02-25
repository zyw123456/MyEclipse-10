


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
<title>机构撤并界面</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/windforce/common/css/ztree_ext.css" ></link>
<style type="text/css">
	.split_line{
		width:1px;
		height:380px;
		border-left: 1px solid #4b9ac9;
		background-color: #00649c;
	}
</style>
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
	
	function summbitMigratePeopleByMergeOrg(){
		var zTree = $.fn.zTree.getZTreeObj("migratePeopleTree");
		var nodes = zTree.getChangeCheckedNodes();
		var count = nodes.length;
		if(count == 0){
			top.wfAlert("请选择待调动人员!");
			return;
		}
		if(isNull(targetOrgId)){
			top.wfAlert("请选择迁移的新机构!");
			return;
		}
		if(originOrgId == targetOrgId){
			top.wfAlert("机构撤并目标机构与原始机构相同,不能执行机构撤并操作!");
			return;
		}
		var migratePeoples = "";
		for (var i=0; i < count; i++) {
			if(nodes[i].type != "org" && nodes[i].checked){
				if(migratePeoples == ""){
					migratePeoples = nodes[i].key;
				}else{
					migratePeoples += "," + nodes[i].key;
				}
			}
		}
		if(window.confirm('您确认执行机构撤并操作?','提示信息')){
			var operAuth=new top.OperAuth();
			operAuth.operType="orgMerge";
			operAuth.authSuccess=function(authUserCode){
				$.post("<%=path%>/peopleAndOrgMigrateAction_summbitOrgMergeCheck.action", 
				{
					originOrgId:originOrgId,
					targetOrgId:targetOrgId,
					migratePeoples:migratePeoples
				},
				function(data){
					var dataObj = $.parseJSON(data);
					if(dataObj.status == '0000'){
						$.post("<%=path%>/peopleAndOrgMigrateAction_summbitOrgMerge.action", 
							{
								originOrgId:originOrgId,
								targetOrgId:targetOrgId,
								migratePeoples:migratePeoples,
								authUserCode:authUserCode
							},
							function(data){
							var dataObj = $.parseJSON(data);
							if(dataObj.status == '0000'){
								top.wfAlert("机构撤并操作成功!");
								closeShowPage();
								top.relDetail(originOrgId);
							}else if(dataObj.status == '0001'){
								top.wfAlert(dataObj.ajaxData);
								closeShowPage();
								top.relDetail(originOrgId);
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
		$.post("<%=path%>/peopleAndOrgMigrateAction_initMigratePeopleData.action", {originOrgId:originOrgId},function(data){
			var dataObj = $.parseJSON(data);
			if(dataObj.status == '0000'){
				$.fn.zTree.init($("#migratePeopleTree"), psetting, $.parseJSON(dataObj.ajaxData));//初始化人员树
				$.getJSON("<%=path%>/getOrganizeList.action?_t="+ new Date().getTime(), function(jsonData){
					$.fn.zTree.init($("#orgTree"), osetting, jsonData);//初始化机构树
				});
			}else{
				top.wfAlert(dataObj.ajaxData);
			}
		});
		
	});
</script>
</head>
<body>
	<table>
		<tr>
			<td width="290px" height="380px" valign="top">
				<label>请选择撤并人员</label>
				<div style="overflow: auto; width: 280px;height: 360px">
					<ul id="migratePeopleTree" class="ztree"></ul>
				</div>
			</td>
			<td width="20px"> <div class = "split_line"></div></td>
			<td width="290px" height="380px" valign="top">
				<label>请选择迁移机构</label>
				<div style="overflow: auto; width: 280px;height: 360px">
					<ul id="orgTree" class="ztree"></ul>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				<input type="button" onclick="summbitMigratePeopleByMergeOrg();" value="保存" class="submit_but05" />
				<input type="button" onclick="closeShowPage();" value="关闭" class="submit_but05" />
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		top.stopProcess();
	</script>
</body>
</html>


