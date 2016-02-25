<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>页脚</title>
	<link rel="stylesheet" href="sinoway/common/css/main.css">
	<link rel="stylesheet" href="sinoway/common/css/bootstrap.min.css">
	
	<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-1.7.1.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-ui-1.8.18.custom.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery.json-2.4.js'></script>
  </head>
  
  <body>
  <div style="width:100%;height:100">
      <%-- 首页         --%>
  	  <div class="bottom" id="firstPage" >
<!-- 		<span style="display: none" id="versionNumberHb"></span> -->
	       <p>
	        	平台使用流量
	        <img alt="" src="sinoway/common/images/day_1.png"/>
	        <span>今日流量</span>
	        <i id="todayFlow"></i>
	        <img  src="sinoway/common/images/day1-3.png" alt=""/>
	        <span>近三日流量&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<i id="yesterdayData" style="width: 70PX"></i>
			<i id="berfareYesterdayData" style="width: 70PX"></i>
			<i id="threeDaysAgoData" style="width: 70PX;"></i>
	       </p>
      </div>
      <%-- 系统管理      --%>
      <div class="bottom" id="sysManage" style="display:none;">
      
      </div>
      <%-- 日志管理      --%>
      <div class="bottom" id="logManage" style="display:none;">
	      
      </div>
      <%-- 策略管理      --%>
      <div class="bottom" id="strategyManage" style="display:none;">
	      
      </div>
      <%-- 个人征信报告      --%>
      <div class="bottom" id="personalRpt" style="display:none;">
<!--        <span style="display: none" id="versionNumberPct"></span>  -->
      	 <p>
		      	平台使用流量
	            <img alt="" src="sinoway/common/images/compartment_user.png"/>
	            <strong id="platformUploadRpt"></strong>
	            <span>平台上传报告</span>
	          
	            <img alt="" src="sinoway/common/images/os_user.png"/>
	            <strong id="interfaceUploadRpt"></strong>
	            <span>接口上传报告</span>
	          
	            <img alt="" src="sinoway/common/images/user_3.png"/>
	            <strong id="publicUploadRpt"></strong>
	            <span>公众号上传报告</span>
          </p>
      </div>
      
      <%-- 反欺诈云      --%>
      <div class="bottom" id="antiFraudCloud" style="display:none;">
<!--       <span style="display: none" id="versionNumberAf"></span> -->
	     <p>
		      	今日流量统计
	            <img alt="" src="sinoway/common/images/compartment_user.png"/>
	            <strong id="porvingTerraceFlow"></strong>
	            <span>验证平台使用流量</span>
	            
	            <img alt="" src="sinoway/common/images/os_user.png"/>
	            <strong id="individualAbnormalFlow"></strong>
	            <span>个人异常使用流量</span>
	            <span style="display: none;" id="versionNumberFad">20</span>
          </p>
      </div>
  	  <%-- 天警云      --%>
      <div class="bottom" id="dayWarnCloud" style="display:none;">
	      
      </div>
      <%-- 账户管理      --%>
      <div class="bottom" id="accountManage" style="display:none;">
	      
      </div>
      </div>
  </body>
</html>
