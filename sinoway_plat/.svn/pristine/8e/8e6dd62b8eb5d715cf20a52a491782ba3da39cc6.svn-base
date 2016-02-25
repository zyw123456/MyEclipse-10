<%--
  机构人员信息详细页面                 
@date          2012/04/25         
@author        蒋正秋
@author        郭士宣    
@version       1.0                
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.yzj.wf.common.util.StringUtils"%>
<%@page import="com.yzj.wf.core.model.po.PeopleInfo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
	
	List<Boolean> poCrudList = (List<Boolean>)session.getAttribute("PO_CRUD");
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>机构人员信息详细页面</title>

<%@ include file="/common/wf_import.jsp"%>
<%@ include file="/windforce/common/jsp/wfInternalImport.jsp"%>
<script type="text/javascript" src="<%=path%>/common/js/pinyinConvert.js"></script>

<script type="text/javascript">
var tempValue = "";
function onAutoTextChange(searchType,firstChar)
{
var currValue = document.getElementById("autoComplete").value;

if(tempValue != currValue || searchType == "2")
{
<%// 临时变量，用户保存当前用户搜索结果
StringBuffer tempJsonData = new StringBuffer("");
List<PeopleInfo> showList = (List<PeopleInfo>)request.getAttribute("userList");
for(PeopleInfo people : showList)
{
tempJsonData.append("{\"peopleName\" :"+"\""+people.getPeopleName()+"\","
                    +"\"peopleCode\" :"+"\""+people.getPeopleCode()+"\","
                    +"\"peopleSid\" :"+"\""+people.getSid()+"\","
                    +"\"peopleGender\" :"+"\""+people.getPeopleGender()+"\"},");
}
String initData = "";
if(!StringUtils.isNullOrBlank(tempJsonData.toString()))
{
initData = tempJsonData.toString();
// 去掉最后一个逗号
initData = initData.substring(0,initData.length()-1);
}%>

// 拼装html语句
var jsonDatas = [<%=initData%>];
var i = 1;
var resultCount = 0;
var trColor = "#CBD6E0";
$("tr").detach(".gradeX");
for(people in jsonDatas)
{
if(i%2==0)
{
trColor = "#CBD6E0";
}else {
trColor = "#FFFFFF";
}
var isContain = false;
var startChar = currValue.substring(0,1);
var strLen = currValue.length;
if(searchType == "2")
{
strLen = 1;
} else if("本页搜索:姓名、拼音" == currValue)
{
strLen = 0;
startChar="";
}

for(var j = 0 ; j < CC2PY(jsonDatas[people]["peopleName"]).length-strLen+1 ; j++)
{
// 类型1为自动搜索
if(searchType == "1")
{ 
var py = CC2PY(jsonDatas[people]["peopleName"]);
 if(jsonDatas[people]["peopleName"].charAt(j) == startChar)
  {
  
   if(jsonDatas[people]["peopleName"].substring(j,j+strLen)==currValue )
   {
   isContain = true;
   }
  }
  if(py.substring(j,j+strLen).toUpperCase() == currValue.toUpperCase())
  {
   isContain = true;
  }
  }   else 
  {
  // 改变当前点击字母的背景色
  document.getElementById(firstChar).style.backGround = "#FFFFFF";
  var py = CC2PY(jsonDatas[people]["peopleName"].substring(0,1));
  // 用户名的首个字的拼音首字母相等，或者英文名的首字母相等时
  if(py.substring(0,1) == firstChar || jsonDatas[people]["peopleName"].substring(0,1).toUpperCase() == firstChar || firstChar== "ALL")
  {
  isContain = true;
  }
  }
}
// 如果是点击字母时则判断汉字开头的拼音首字母也要匹配
if(isContain)
{
var userName = jsonDatas[people]["peopleName"];
var userCode = jsonDatas[people]["peopleCode"];
var userSid = jsonDatas[people]["peopleSid"];
var email = jsonDatas[people]["email"];



var sex = jsonDatas[people]["peopleGender"] == 0 ? "男" : "女";
              var htmlData =              "<tr class=\"gradeX\" bgcolor=\""+trColor+"\" ondblclick=\"javascript:top.userDetail('"+userCode+"');\">"
                                    +"<td onclick=\"setUserDetail(this);\" align=\"center\">"+i+"</td>"
                                    +"<td onclick=\"setUserDetail(this);\" align=\"center\">"+userName+"</td>"
                                    +"<td onclick=\"setUserDetail(this);\" align=\"center\">"+userCode+"</td>"
                                    +"<td onclick=\"setUserDetail(this);\" align=\"center\">"
                                    +sex
                                    +" </td>"
                                    +"<td align=\"center\"><a name=\"addPeople\" id=\"addPeople\" style=\"color:#FF0000\" onclick=\"editUser('"+userSid+"');\">修改</a>" 
                                   +"/<a style=\"color:#FF0000\" onclick=\"delPeople('"+userSid+"','<s:property value='organizeInfo.orgNo'/>')\">删除</a>"
                                    +"/<a style=\"color:#FF0000\" onclick=\"resetPwd('"+userSid+","+email+"')\" >重置密码</a>"
                                    +"</td>"
                                     + "</tr>";
                                    i++;
                       
                                    resultCount++;
                                    $("#trAutoComplete").append(htmlData);
}
isContain = false;
}
$("#userCount").html(resultCount);
}
// 保留上一次的值
tempValue = currValue;
}



// 点击添加用户按钮
function addPeople(){
var orgId = "<s:property value='organizeInfo.sid'/>";
 top.startProcess("正在初始化人员新增界面,请稍等...");
 
 top.showPage("initAddUserGroup.action?_t="+ new Date().getTime(),"orgSid="+orgId,"新增人员",550,360);
}
// 点击修改按钮
function editUser(userId)
{
 top.startProcess("正在初始化人员修改界面,请稍等...");
 top.showPage("editUser_initEditPeople.action?_t="+ new Date().getTime(),"initUserId="+userId,"编辑人员",550,360);
}
// 点击删除按钮
function delPeople(sid,orgId)
{
// 当前选择的机构
orgId = "<s:property value='organizeInfo.sid'/>";
if(sid=="7B92AE0FC4B04DB48F1AFBDB22CD7188"){
top.wfAlert("超级管理员不能被删除");
return;
}
if(confirm("确定要删除该人员吗？"))
{
var operAuth=new top.OperAuth();
operAuth.operType="deletePeople";
operAuth.authSuccess=function(authUserCode){
top.startProcess("正在提交删除请求,请稍等...");
if (!authUserCode) {
	authUserCode = "";
}
top.delPeople("<%=path%>/doDelUser.action?_t="+ new Date().getTime() + "&sid="+sid + "&authUserCode="+authUserCode,orgId);
};
operAuth.auth();
}

}
// 水印提示
function searchOn(){
var el = document.getElementById("autoComplete");
  if (el.value == "本页搜索:姓名、拼音")
  {
    el.value = "";
    el.style.color = "";
    }
};
  function searchBlur(){
  var el = document.getElementById("autoComplete");
  if (el.value == "")
  {
    el.value = "本页搜索:姓名、拼音";
    el.style.color = "gray";
    }
}

// 单击用户列表设置背景颜色
function setUserDetail(td)
{
var trColor = "#CBD6E0";
var trs = $("#trAutoComplete tr");
for(var i = 0 ; i < trs.size();i++)
{
if(i%2==0)
{
trColor = "#CBD6E0";
}else {
trColor = "#FFFFFF";
}
$('#trAutoComplete tr').eq(i).css("backgroundColor",trColor);
$('#trAutoComplete tr').eq(i).css("color","");
}
td.parentNode.style.backgroundColor = "#6699FF";
td.parentNode.style.color = "#FFFFFF";
};

/**
 * 重置密码
 *@param peopleSID 用户SID
 */
function resetPwd(peopleSID,email){
	if(confirm("您确定要重置该用户密码?")){
		var operAuth = new top.OperAuth();
		operAuth.operType = "resetPwd";
		operAuth.authSuccess = function(authUserCode) {
			if(top.enableRandomPwdForResetPwd && (top.isNull(email))){
				top.wfAlert("电子邮箱为空，请先设置电子邮箱!");
			}else{
		 		$.post('<%=path%>/resetPwdAction.action?_t='+ new Date().getTime(), "userNo="+peopleSID, function(data) {
		 			top.wfAlert(data);
			 		window.location.reload();
		 		});
			}
		};
		operAuth.auth();
 	}
}
 
 function prePage(){
 if( document.getElementById("prePage").disabled==true){
 return;
 }
  top.startProcess("正在获取人员信息,请稍等...");
  var curPage=document.getElementById("curPage");
  $.post('<%=path%>/getPeoplesByPage.action?_t='+ new Date().getTime(), "curPage="+(parseInt(curPage.innerText)-1), function(data) {
           $("#peopleTable").html(data);
           curPage.innerText=parseInt(curPage.innerText)-1;
            changePageButton();
            top.stopProcess();
 });
 }
 
 function nextPage(){
 if( document.getElementById("nextPage").disabled==true){
 return;
 }
 top.startProcess("正在获取人员信息,请稍等...");
  var curPage=document.getElementById("curPage");
  $.post('<%=path%>/getPeoplesByPage.action?_t='+ new Date().getTime(), "curPage="+(parseInt(curPage.innerText)+1), function(data) {
           $("#peopleTable").html(data);
           curPage.innerText=parseInt(curPage.innerText)+1;
            changePageButton();
            top.stopProcess();
 });
 }
function firstPage(){
	if( document.getElementById("firstPage").disabled==true){
		return;
	}
 	top.startProcess("正在获取人员信息,请稍等...");
	var curPage=document.getElementById("curPage");
	$.post('<%=path%>/getPeoplesByPage.action?_t='+ new Date().getTime(), "curPage=1", function(data) {
           $("#peopleTable").html(data);
           curPage.innerText=1;
            changePageButton();
            top.stopProcess();
 	});
}
 function lastPage(){
  if( document.getElementById("lastPage").disabled==true){
 return;
 }
  top.startProcess("正在获取人员信息,请稍等...");
  var curPage=document.getElementById("curPage");
  $.post("<%=path%>/getPeoplesByPage.action?_t="+ new Date().getTime(), "curPage="+"<s:property
						value="totalPage"/>", function(data) {
           $("#peopleTable").html(data);
           curPage.innerText='<s:property value="totalPage"/>';
            changePageButton();
            top.stopProcess();
 });
 }
 
 function  changePageButton(){
   	var curPage=document.getElementById("curPage").innerText;
   	var totalPage='<s:property value="totalPage"/>';
   	document.getElementById("firstPage").disabled=true;
   	document.getElementById("firstPage").style.color="gray";
   	document.getElementById("prePage").disabled=true;
    document.getElementById("prePage").style.color="gray";
    document.getElementById("nextPage").disabled=true;
    document.getElementById("nextPage").style.color="gray";
    document.getElementById("lastPage").disabled=true;
    document.getElementById("lastPage").style.color="gray";
   if(curPage>1){
    document.getElementById("firstPage").disabled=false;
    document.getElementById("firstPage").style.color="#CC0033";
    document.getElementById("prePage").disabled=false;
     document.getElementById("prePage").style.color="#CC0033";
   }
   if(curPage<totalPage||curPage.length<totalPage.length){
    document.getElementById("nextPage").disabled=false;
    document.getElementById("nextPage").style.color="#CC0033";
    document.getElementById("lastPage").disabled=false;
    document.getElementById("lastPage").style.color="#CC0033";
   }
 }
 
 function changePage(){
 document.getElementById("showPageType").style.visibility="hidden";
  top.startProcess("正在切换界面,请稍等...");
  document.getElementById("changePageForm").submit();
 }
 function searchPeople(){
  var peopleName = document.getElementById("peopleName").value;
  var peopleCode = document.getElementById("peopleCode").value;
  if(!(top.isNull(peopleName)) && !(top.isGeneralName(peopleName))){
  	top.wfAlert("用户姓名含有非法字符!");
  	return false;
  } 
  if (!(top.isNull(peopleCode)) && !(top.isAlphaAndDigits(peopleCode))){
  	top.wfAlert("用户代码只能是字母或数字!");
  	return false;
  }
  if(document.getElementById("useLikeCheck").checked){
  	document.getElementById("useLike").value="1";
  }
  top.startProcess("正在获取人员信息,请稍等...");
  document.getElementById("queryForm").submit();
 }
 
 function changeImageSize(element,width,height){
	 
  element.style.width=width;
  element.style.height=height;
 }
 
 $(document).ready(function(){
	var obj = top.document.getElementById("rightindex");
	var height = $(obj).css("height");
		height = height.substr(0, height.length - 2);
	var width = $(obj).css("width");
		width = width.substr(0, width.length - 2);
	if(width<800){
		bodyWidth = 800;
	}
	width = width - 315;	
	$("#detailBody").css("width",width+"px");
	if(height<500){
		height = 500;
		$("#detailBody").css("height",height+"px");
	}
	height = height*99/100;
	
	var poDetailDiv = $("#poDetailDiv");
	poDetailDiv.css("widht",width + "px");
	poDetailDiv.css("height",height+ "px");

	var poDetaiDivHeight = getNumberFromPx($("#poDetailDiv").css("height"));//机构人员详情DIV的高度
	var orgDetailDivHeight = getNumberFromPx($("#orgDetailDiv").css("height"));//机构详情DIV的高度

	var peopleDetailDiv = $("#peopleDetailDiv");
	var peopleDetailDivHeight = poDetaiDivHeight - orgDetailDivHeight-5;	//5是两元素的上下外边距经验值
	peopleDetailDiv.css("height",peopleDetailDivHeight + "px");//设置人员详情DIV的高度
	
	var queryDivHeight = getNumberFromPx($("#queryDiv").css("height"));//
	var pageInfoDivHeight = getNumberFromPx($("#pageInfoDiv").css("height"));//
	
	var peopleTableDiv = $("#peopleTable");
	var peopleTableDivWight = peopleDetailDiv;
	var peopleTableDivHeight = peopleDetailDivHeight - 44 -queryDivHeight - pageInfoDivHeight - 15;
	peopleTableDiv.css("height",peopleTableDivHeight + "px");//设置人员表DIV的高度
	peopleTableDiv.css("width",peopleTableDivWight + "px");//设置人员表DIV的宽度
	
	changePageButton();
	var showPageType= document.getElementById("showPageType");
	showPageType.value="<s:property value='showPageType'/>";
	showPageType.style.visibility="visible";
	var useLike="<s:property value='useLike'/>";
	if(useLike=="1"){
	document.getElementById("useLikeCheck").checked=true;
	}
});
	
/**
  * 启用人员
  *@param peopleSID 人员SID
  *@param orgNO 机构编号
  */
function startUsingPeople(peopleSID){
	if(!confirm("您确定要要启用该用户吗?")){
		return;
	}
	var operAuth = new top.OperAuth();
	operAuth.operType = "startUsingPeople";
	operAuth.authSuccess = function(authUserCode) {
		top.startProcess("正在处理,请稍等...");
	 	var url = "<%=path%>/editUser_startUsingPeople.action";
	 	var params = {
	 		"peopleInfo.sid": peopleSID
	 	};
	 	$.post(url,params,function(msg){
	 		top.wfAlert(msg);
	 		window.location.reload();
		});
	};
	operAuth.auth();
}	
  
 /**
  * 停用人员
  *@param peopleSID 人员SID
  *@param orgNO 机构编号
  */
function disusePeople(peopleSID){
	if(!confirm("您确定要要停用该用户吗?")){
		return;
	}
	var operAuth = new top.OperAuth();
	operAuth.operType = "disusePeople";
	operAuth.authSuccess = function(authUserCode) {
		top.startProcess("正在处理,请稍等...");
 		var url = "<%=path%>/editUser_disusePeople.action";
		var params = {
			"peopleInfo.sid" : peopleSID
		};
		$.post(url, params, function(msg) {
			top.wfAlert(msg);
			window.location.reload();
		});
	};
	operAuth.auth();
}
 
function getNumberFromPx(str){
	return str.substring(0,str.length-2);
} 

</script>
</head>
<body id="detailBody" onload="top.stopProcess()" style="font-size:12px;background-color: transparent;overflow:auto;">
	<div id="poDetailDiv">
		<div id="orgDetailDiv" style="width: 100%;height:150px;">
			<h2 style="width:50%" class="poAreaHead">
				机构详细信息 (
				<s:if test="showPageType ==1">仅当前机构</s:if>
				<s:else>包含所有下级机构</s:else>
				)
			</h2>
			<form id="changePageForm" action="organizeDetail.action">
				<table width="95%" border="0" align="center" cellpadding="5" cellspacing="0">
					<tr>
						<td width="50%" class="border_bottom01">
							当前机构：
							<s:property value="organizeInfo.orgName" />
						</td>
						<td width="50%" class="border_bottom01">
							机构代码：
							<s:property value="organizeInfo.orgNo" />
						</td>
					</tr>
					<tr>
						<td width="50%" class="border_bottom01">
							省市代码：
							<s:if test="organizeInfo.orgProvince != null">
								<s:property value="organizeInfo.orgProvince" />
							</s:if>
						</td>
						<td width="50%" class="border_bottom01">
							所属区域：
							<s:if test="organizeInfo.orgArea != null">
								<s:property value="organizeInfo.orgArea" />
							</s:if>
						</td>
					</tr> 
					<tr>
						<td class="border_bottom01">
							机构级别：
							<s:property value="orgType.orgTypeName" />
						</td>
						<td class="border_bottom01">
							机构共有：
							<s:if test="showPageType ==1">
								<s:property value="childOrgs" />个直属</s:if>
							<s:else>
								<s:property value="childOrgs-1" />个</s:else>
							下级机构
						</td>
					</tr>
					<tr>
						<td class="border_bottom01" height="25px">
							用户统计：
							<s:property value="peopleCount" />
							个用户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<%
								if(poCrudList.get(3)){
							%>
							<div
								style="position:absolute; z-index: 200;margin-left:150px;margin-top:-23px;*margin-left:0px;*margin-top:-5px;">
								<img width="25px" height="25px" onmousemove="changeImageSize(this,'30px','30px')"
									onmouseout="changeImageSize(this,'25px','25px')" title="新增人员" onclick="addPeople();"
									src="<%=path%>/windforce/common/images/po_add.png"></img>
							</div>
							<%
								}
							%>
						</td>
						<td class="border_bottom01" valign="bottom">
							统计类型: <select name="showPageType" id="showPageType" style="visibility: hidden" onchange="changePage();">
								<option value="1">仅当前机构</option>
								<option value="2">包含所有下级机构</option>
							</select>
							<input type="hidden" name="sid" value="<s:property
					value='organizeInfo.sid' />" />
							<input style="display:none" type="text" name="autoKeyWords" onblur="searchBlur()" onfocus="searchOn()"
								onkeyup="onAutoTextChange('1','');" id="autoComplete" style="margin-left: 60px;color: gray;" value="本页搜索:姓名、拼音" />
							<img style="display:none" width="18px" height="18px" src="<%=path%>/windforce/common/images/po_search.png"
								style="margin-bottom: -4px"></img>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 人员信息 -->
		<div id="peopleDetailDiv" style="width: 98%;">
			<h2 class="poAreaHead">人员信息</h2>
			<div id="queryDiv" style="width:100%;height:30px;margin-left: 10px;">
				<form action="organizeDetail.action" id="queryForm" method="post">
					用户姓名:
					<input type="text" id="peopleName" name="peopleName" value="<s:property value='peopleName'/>" maxlength="50"
						style="width:55px" />
					用户代码:
					<input type="text" id="peopleCode" name="peopleCode" value="<s:property value='peopleCode'/>" maxlength="20"
						style="width:55px" />
					用户岗位：
					<select id="roleGroupSid" name="roleGroupSid" style="width:80px" >
						<s:if test="roleGroupSid != null">
							<option value="" >全部</option>
						</s:if>
						<s:else>
							<option value="" selected="selected">全部</option>
						</s:else>
						<s:iterator value="roleGroupInfos" var="roleGroupInfo">
							<s:if test="roleGroupSid == #roleGroupInfo.sid">
								<option value="<s:property value='#roleGroupInfo.sid'/>" selected="selected"><s:property value="#roleGroupInfo.roleGroupName" /> </option>
							</s:if>
							<s:else>
								<option value="<s:property value='#roleGroupInfo.sid'/>"><s:property value="#roleGroupInfo.roleGroupName" /> </option>
							</s:else>
						</s:iterator>
					</select>
					<input type="checkbox" id="useLikeCheck" name="useLikeCheck" value="<s:property value='useLike'/>" />
					模糊  &nbsp;<img width="16px" height="16px" onmousemove="changeImageSize(this,'20px','20px')"
						onmouseout="changeImageSize(this,'16px','16px')" onclick="searchPeople();" title="搜索"
						src="<%=path%>/windforce/common/images/po_search.png"></img>
					<input type="hidden" name="sid" value="<s:property value='organizeInfo.sid' />" />
					<input type="hidden" value="1" name="isClickButton" />
					<input type="hidden" value="0" id="useLike" name="useLike" />
				</form>
			</div>
			<div id="pageInfoDiv" style="position: relative;width:80%;height:20px;margin: 10px;">
				<a id="firstPage" onclick="firstPage()">首页</a> <a id="prePage" onclick="prePage()">上一页</a> <a id="nextPage"
					onclick="nextPage()">下一页</a> <a id="lastPage" onclick="lastPage()">尾页</a> <label>当前:第</label> <label id="curPage">1</label>
				<label>页</label> 每页
				<s:property value="pageSize" />
				条
			</div>
			<div id="peopleTable" style="position: relative; top:5px;left: 10px;width:100%;height:auto;">
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
									<a onclick="javascript:top.showPeopleDetailPage('<s:property value='#userInfo.peopleCode'/>');"> <s:property
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
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var peopleCode = document.getElementById("peopleCode");
	document.documentElement.onkeydown = function(evt) {
		var b = !!evt, oEvent = evt || window.event;
		if (oEvent.keyCode == 13) {
			var node = b ? oEvent.target : oEvent.srcElement;
			if (node.name != null && node.id == "peopleName") {
				peopleCode.focus();
			} else if (node.name != null && node.id == "peopleCode") {
				searchPeople();
			}
		}
	};
</script>
</html>