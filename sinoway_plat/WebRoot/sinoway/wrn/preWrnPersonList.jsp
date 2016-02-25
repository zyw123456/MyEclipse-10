<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'wrnMonitorWaitingList.jsp' starting page</title>
    
	 <script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-1.7.1.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-ui-1.8.18.custom.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery.json-2.4.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/sinoway/wrn/js/preWrnPersonList.js'></script>
		<script type="text/javascript"
	src="<%=request.getContextPath()%>/windforce/common/js/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
    .popupcontent{ 
		position: absolute; 
		visibility: hidden; 
		overflow: hidden; 
		border:1px solid #CCC; 
		background-color:#F9F9F9; 
		border:1px solid #333; 
		padding:5px; 
		top :100px; 
	    left: 300px; 
	    width :700px; 
	   height :400px
    } 
    .statusbar{
	   position:relative;
	   left:640px;
		visibility : hidden 
    }
    #startDiv{
   		 position: absolute; 
		visibility: hidden; 
		overflow: hidden; 
		border:1px solid #CCC; 
		background-color:#F9F9F9; 
		border:1px solid #333; 
		padding:5px; 
		top :100px; 
	    left: 400px; 
	    width :660px; 
	  	 height :350px
    }
     #startClose{
	   position:relative;
	   left:620px;
		visibility : hidden 
    }
    </style>  
  </head>
  
  <body>
    <table align="center" width="800" id="tb1">
    <tr height="60">
    	<th>姓名</th>
	    <th>身份证号</th>
	    <th>监控区间<input type="button" value="升" onclick="sheng()"/>&nbsp;<input type="button" value="降" onclick="jiang()"/></th>
	    <th><select onchange="sel()">
	     	<option value='0'>贷款类型</option>
	     	<option value='buy'>消费类贷款</option>
	     	<option value='car'>汽车贷款</option>
	     	<option value='house'>购房贷款</option>
	    </select></th>
	    <th>监控模块</th>
	    <th>操作</th>
	</tr>
    </table>
    <div class="popupcontent" id="suppleDiv">
    	<div class="statusbar" id="suppleClose"><input type="button" onclick="hideDiv()" value="关闭"></div>
    	<h3 align="center">天警云补充信息</h3>
    	<h5>个人基本信息</h5>
    	姓名:<input size="15" id="prsnnam">&nbsp;&nbsp;身份证号:<input size="20" id="prsncod">&nbsp;&nbsp;手机号:<input size="20" id="telno">
    	<hr>
    	<h5>贷后信息</h5>
    	贷款类型:<select id='loantyp'>
	     	<option value='0'>贷款类型</option>
	     	<option value='1' id='buy'>消费类贷款</option>
	     	<option value='2' id='car'>汽车贷款</option>
	     	<option value='3' id='house'>购房贷款</option>
	    </select>&nbsp;&nbsp;贷款金额:<input size="15" id="loanamt">万元&nbsp;&nbsp;贷款期限:<input size="15" id="loanlmt">月<br><br>
    	放款时间:<input size="15" id="loansrtdte" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMMdd'});"  onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'loanenddte\')}'});">&nbsp;&nbsp;到期时间:<input size="15" id="loanenddte" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMMdd'});"  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'loansrtdte\')}'});"><br><br>
    	还款方式:<select style="width:124px" id="repaytyp">
	    	<option value="0">请选择</option>
	    	<option value="month">按月还款</option>
	    	<option value="quarter">按季还款</option>
	    	<option value="year">按年还款</option>
    	</select>&nbsp;&nbsp;还款日期:<input size="15" id="repaydte">月&nbsp;&nbsp;&nbsp;&nbsp;还款金额:<input size="15" id="repayamt">元/月
    	<input type="hidden" id="warnid"><br>
    	<p align="center"><input type="button" value="保存" onclick="suppleSave()"></p>
    </div>
    <div class="popupcontent" id="startDiv">
    	<div class="statusbar" id="startClose"><input type="button" onclick="hideDiv()" value="关闭"></div>
    	<h3 align="center">开始监控</h3>
    	<h5>请您确认是否需要开启对以下人员的监控</h5>
    	<table id="myTb" width="640">
    		<tr><th>姓名</th><th>身份证号</th><th>监控区间</th><th>贷款类型</th><th>监控模块</th></tr>
    	</table>
    	<div id="monitorModule" style="visibility:hidden;height:100px">
    	<br>
    	  &nbsp;&nbsp;&nbsp;请选择监控模板<br>
    	  <p id="moniMod" align="center"></p>
    	</div>
    	<p align="center" id="startButton"><input type="button" onclick="confirmMonitor()" value="确认开启"></p>
    </div>
  </body>
</html>
