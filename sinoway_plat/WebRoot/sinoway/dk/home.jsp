<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'home.jsp' starting page</title>
    <link rel="stylesheet" href="sinoway/common/css/main.css">
	<link rel="stylesheet" href="sinoway/common/css/bootstrap.min.css">
	<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-1.7.1.js'></script>
	<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery.json-2.4.js'></script>
	<script type="text/javascript"
	src='<%=request.getContextPath()%>/sinoway/dk/js/home.js'></script>
  </head>
  
  <body>
  <div id="centers">
  	<div class="count">
       <div class="counts">
        <h2>平台监视统计</h2>
        <dl>
          <dt><img alt="" src="sinoway/common/images/shanxing_1.png"/></dt>
        </dl>
        <dl>
          <dt><span>总监控</span></dt>
          <dd id="platMonitor"></dd>
          <dt><span>监控预警</span></dt>
          <dd id="platAlarm"></dd>
        </dl>
        <div style="clear:both;"></div>
      </div>
       <div class="counts">
        <h2>账号监视统计</h2>
        <dl>
          <dt><img alt="" src="sinoway/common/images/shanxing_2.png"/></dt>
        </dl>
        <dl>
          <dt><span>委托监控</span></dt>
          <dd id="accMonitor"></dd>
          <dt><span>监控预警</span></dt>
          <dd id="accAlarm"></dd>
        </dl>
        <div style="clear:both;"></div>
      </div>
       <div style=" clear:both;"></div>
      </div>
      <div class="datas_tab">
        <table width="28%"  id="mytable" >
            <th>
            <img alt="" src="sinoway/common/images/jinggao.png"/>
            <span>平台实时预警信息</span>
            </th>
        </table>
        
        <table id="mytable2" width="60%">
          <thead>
            <th colspan="5" > <img alt="" src="sinoway/common/images/info.png"/>
           <span>平台实时报告信息</span>
            </th>
          </thead>
          <tbody>
            <tr class="tabthead" >
              <td>报告时间</td>
              <td>报告号</td>
              <td>姓名</td>
              <td>身份证号</td>
              <td>报告类型</td>
              <td>
               
               <span style="display: none" id="plat_alarmNo_v"></span>
               <span style="display: none" id="acc_alarmNo_v"></span>
               <span style="display: none" id="acc_report_v"></span>
               <span style="display: none" id="plat_alarmDetail_v"></span>
              </td>
            </tr>
          </tbody>
          
        </table>
        
        <div style="clear:both;"></div>
      </div>
	</div>
  </body>
</html>
<script>
    var ctx = "<%=path%>";
</script>