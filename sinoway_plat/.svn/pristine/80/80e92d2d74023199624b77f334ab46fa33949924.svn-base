<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>  
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <title>新增监控名单</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link href="resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="data/styles.css" type="text/css" rel="stylesheet"/>
    <link href="files/新增监控名单/styles.css" type="text/css" rel="stylesheet"/>   
    <style> span{color:#aaa;}</style> 
    <script type="text/javascript"
	src='<%=request.getContextPath()%>/sinoway/rpt/js/addRpt.js'></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/sinoway/wrn/js/addWrnPerson.js"></script>
  <script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-1.7.1.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-ui-1.8.18.custom.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery.json-2.4.js'></script>
  </head>
    <!-- 日期控件 -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/windforce/common/js/My97DatePicker/WdatePicker.js"></script>
  </head>
  <body onload="searchProductList()" >
	<div>
	<form action="/windforce/addWarnPerson.action" method="post" id="myform" name="myform">
	<h2>上传新监控人员名单</h2>
	<h3>个人基本信息：</h3><br>
	姓&nbsp;名：<input type="text" id="prsnnam" name="prsnnam" onblur="checkNull(prsnnam)"><span id="t_prsnnam">请输入姓名</span>&nbsp;&nbsp;
	<!-- 身份证验证 -->
	身份证号：<input type="text" id="prsncod" name="prsncod" onblur="checkNull(prsncod)"><span id="t_prsncod">请输身份证号码</span><br><br>
	手机号：<input type="text" id="telno" name="telno"  onblur="checkNull(telno)"><span id="t_tel">请输入电话号码</span>
	<br><br>
	<hr>
	<h3>贷后信息</h3>
	贷款类型：<input type="text" id="loantyp" name="loantyp" onblur="checkNull(loantyp)" >&nbsp;&nbsp;<span id="t_loantyp">请输入贷款类型</span>
	贷款金额：<input type="text" id="loanamt" name="loanamt" onblur="checkNull(loanamt)"><strong>万元</strong>&nbsp;&nbsp;<span id="t_loanamt">请输入贷款金额</span>
	贷款期限：<input type="text" id="loanlmt" name="loanlmt" onblur="checkNull(loanlmt)"><strong>月</strong> <span id="t_loanlmt">请输入贷款期限</span><br><br>	
	放贷时间：<input name="loansrtdte" id="loansrtdte" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMMdd',maxDate:'%y-%M-%d'});" onblur="checkNull(loansrtdte)"><span id="t_loansrtdte">请选择放贷时间</span></input>&nbsp;&nbsp;
	到期时间：<input name="loanenddte" id="loanenddte" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMMdd',maxDate:'%y-%M-%d'});" onblur="checkNull(loanenddte)"><span id="t_loanenddte">请选择贷款结束</span></input><br>
	<br><br>
	还款方式： <select id=repaytyp name="repaytyp" onblur="checkNull(repaytyp)">
          <option value="-1">请选择</option>
          <option value="0">按月还款</option>
          <option value="1">按季还款</option>
          <option value="2">按年还款</option>
        </select>&nbsp;&nbsp;&nbsp;<span id="t_repaytyp">请选择还款方式</span>&nbsp;&nbsp;&nbsp;&nbsp;
        还款日期：<input type="text" id="repaydte" name="repaydte" onblur="checkNull(repaydte)" ><strong>月</strong> &nbsp;&nbsp; <span id="t_repaydte">请输入还款日期</span>
        还款金额：<input type="text" id="repayamt" name="repayamt" onblur="checkNull(repayamt)"><strong>元/月</strong><span id="t_repayamt">请输入还款金额</span><br>
      <hr>
     <h3>监控模块选择 </h3>
     <div id="mb">
 <br><br>
    </div><br><br><hr>
    <div id="mydiv">
    <input type="button" name="save" id="button" value="保存" onclick="submitForm()">
   </div> 
     </div> 
     </form> 
  </body>
</html>
