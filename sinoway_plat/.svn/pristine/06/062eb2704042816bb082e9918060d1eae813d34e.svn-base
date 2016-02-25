<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<script type="text/javascript">
	var currentPage = 1;
	var recordCount = 0;
	var pageSize = 10;
	var pageCount = 0;
</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>">
    <title>异常预警</title>
    
	 <script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-1.7.1.js'></script>
	<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-ui-1.8.18.custom.js'></script>
	<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery.json-2.4.js'></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sinoway/wrn/js/wrnList.js"></script>
	<!-- 日期控件 -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/windforce/common/js/My97DatePicker/WdatePicker.js"></script>
		
  </head>
  <body>
       <table  width="100%;" height="20%;" >
       		<tr>
       			<td width="90%;">
	       			<span style="font-weight:bold;">姓&nbsp;&nbsp;名：</span>
	       			<input type="text" id="prsnnam">
	       			<span style="font-weight:bold;">身份证号：</span>
	       			<input type="text" id="prsncod">
	       			<span style="font-weight:bold;">预警时间：</span>
	       			<input type="text" id="warntim" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMMddHHmmss',maxDate:'%y-%M-%d'});"> 
       			</td>
       		</tr>
       		<tr>
       			<td colspan="6" id="trnList"> 
       			</td>
       		</tr>
       		<tr>
       			<td>
       				<input type="button" value="搜索" onclick="javascript:searchWrnList(1,2,-1);">
       			</td>
       		</tr>
       </table>
       
       <table  width="100%;" height="80%;" id="resultTable">
       		
       </table>
  </body>
 
</html>
