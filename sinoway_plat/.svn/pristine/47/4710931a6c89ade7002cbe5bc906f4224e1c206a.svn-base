<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>基本信息资料</title>
    <link rel="stylesheet" href="../windforce/sinoway/common/css/main.css">
    <link rel="stylesheet" href="../windforce/sinoway/common/css/bootstrap.min.css">
    <script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-1.7.1.js'></script>
	<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-ui-1.8.18.custom.js'></script>
	<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery.json-2.4.js'></script>
	
	<script type="text/javascript"
	src="<%=request.getContextPath()%>/windforce/common/js/My97DatePicker/WdatePicker.js"></script>
    <%--
    <script type="text/javascript" 
    	src="<%=request.getContextPath()%>/sinoway/rpt/js/rpt-base.js"></script>
    --%>
    <script type="text/javascript"
	src='<%=request.getContextPath()%>/sinoway/acc/js/compEdit.js'></script>
	<script type="text/javascript">
		  function parameterToaction(){
		   accAdd();
	       accTransotion.submit();
	      };
	</script>
	<style type="text/css">
		body{
		background-color: #eeeeee;
		width: 94%;
		}
	</style>
  </head>

  <body>
  <div class="report_right" >
  	<div class="report_center" > 
  		<div class="show_info" align="center" style="margin-top:50px;">
  			<form class="form-inline" name="accTransotion" action="updateCompInfo.action" method="post" id="form1" >
  			<div class="col-md-4 col-sm-4 col-md-offset-3" align="left">
  				<div class="input-group" style="display:!important;" id="block1">
	               <div class="input-group-addon" id="label1" >公司名称&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
	               <input type="text" class="form-control" id ="compnam" name="wfCfgdefCompinfo.compnam" value="" onkeyup="namePrefact()"/> <!--  <div  display:inline style="display: none;" id="compName">长度不可以大于20</div>  --> 
	               <input type="hidden" id ="id" name="wfCfgdefCompinfo.id" value=""/>
	               <input type="hidden" id ="sta" name="wfCfgdefCompinfo.sta" value=""/>
	               <input type="hidden" id ="compno" name="wfCfgdefCompinfo.compno" value=""/>
	               <input type="hidden" id ="comptyp" name="wfCfgdefCompinfo.comptyp" value=""/>
		           <input type="hidden" id ="flag" name="flag" value="${flag}"/>
                </div>
            </div>
            
            <div class="col-md-3 col-sm-3 text-left" style="padding-top:28px; ">
            <span style="display: inline;float:;color: red;" id="compName"></span>
	  		</div>
	  		
           <div class="col-md-4 col-sm-4 col-md-offset-3" align="center">
                <div class="input-group" style="display:!important;" id="block2">
	               <div class="input-group-addon" id="label2" >公司地址&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
	               <input type="text" class="form-control" id="compaddr" name="wfCfgdefCompinfo.compaddr" value="" onkeyup="addressPrefact()"/> <!--   <div  display:inline style="display: none;" id="address">长度不可以大于40</div> -->
	            </div>
	       </div>
	       
	        <div class="col-md-3 col-sm-3 text-left" style="padding-top:28px; ">  
	          <span style="display: inline;float:;color: red;" id="address"></span>
	        </div>
	        
	        <div class="col-md-4 col-sm-4 col-md-offset-3" align="left">
	            <div class="input-group" style="display:!important;" id="block3">
	               <div class="input-group-addon" id="label3" >联系人姓名&nbsp;&nbsp;&nbsp;</div>
	               <input type="text" class="form-control" id="prsnnam" name="wfCfgdefCompinfo.prsnnam" value="" onkeyup="peolpeNamePrefact()"/>  <!-- <div  display:inline style="display: none;" id="peopleName">长度不可以大于8</div>  -->
	            </div>
	        </div>
	        
	       <div class="col-md-3 col-sm-3 text-left" style="padding-top:28px; "> 
	          <span style="display: inline;float:;color: red;" id="peopleName"></span>
	        </div>
	        
	         <div class="col-md-4 col-sm-4 col-md-offset-3" align="left">
	            <div class="input-group" style="display:!important;" id="block4">
	               <div class="input-group-addon" id="label4" >联系人电话&nbsp;&nbsp;&nbsp;</div>
 	               <input type="text" class="form-control" id="phnone" name="wfCfgdefCompinfo.phnone" value="" onkeyup="telPrefact()" onmousedown=""/>   <!-- <div  display:inline style="display: none;" id="telphone">电话号码格式不正确</div> -->
	            </div>
	         </div>
	         
	        <div class="col-md-3 col-sm-3 text-left" style="padding-top:28px; "> 
	          <span style="display: inline;float:;color: red;" id="telphone"></span>
	        </div>
	        
	         <div class="col-md-4 col-sm-4 col-md-offset-3" align="left">
	            <div class="input-group" style="display:!important;" id="block5">
	               <div class="input-group-addon" id="label5">平台使用期限</div>
	              <input type="text" class="form-control sdf" id="strdte" name="wfCfgdefCompinfo.strdte" value="" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMMdd',maxDate:'%y-%M-%d'});" />  
	              <div class="input-group-addon">至</div>
                 <input type="text" class="form-control sdfs"  id="enddte" name="wfCfgdefCompinfo.enddte" value="" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMMdd',minDate:'%y-%M-%d'});"/>  
					</div>  
	       </div>
	       
	       <div class="col-md-3 col-sm-3 text-left" style="padding-top:28px; ">  
	        </div>
		  		<div class="col-md-3 col-sm-3 col-md-offset-4 form_submit"  >
		            <div id="round2"  onclick="submitInfo()" onmouseover="this.style.cursor='hand'">
		                <a>提交</a> 
		            </div>
		        </div>
  			</form>
  		</div>
  	</div>
  	</div>
  </body>
  <script type="text/javascript">
  	var divblock= document.getElementById("block5").offsetLeft;
  	var left1 = document.getElementById("block1").offsetLeft;
  	var left2 = document.getElementById("block2").offsetLeft;
  	var left3 = document.getElementById("block3").offsetLeft;
  	var left4 = document.getElementById("block4").offsetLeft;
  	document.getElementById("block1").style.left = divblock -left1 ;
  	document.getElementById("block2").style.left = divblock -left2;
  	document.getElementById("block3").style.left = divblock -left3;
  	document.getElementById("block4").style.left = divblock -left4;
  </script>
</html>
