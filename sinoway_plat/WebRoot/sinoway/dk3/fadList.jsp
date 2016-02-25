<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="com.sinoway.common.util.Constant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="/WEB-INF/tlds/pageTag.tld" prefix="page"%>  
<base href="<%=basePath%>">
<form id="myForm">
	<div class="top-margin-20">
		<div class="col-md-4 col-sm-4 form-group">
			<div class="input-group">
				<span class="input-group-addon">姓名</span> <input
					class="form-control" id="prsnnam" name="prsnnam" type="text" placeholder="请输入姓名">
			</div>
		</div>
		<div class="col-md-1 col-sm-1 form-group"></div>
		<div class="col-md-4 col-sm-4 form-group">
			<div class="input-group">
				<span class="input-group-addon">身份证号</span> 
				<input class="form-control" id="prsncod" name="prsncod" type="text"
					placeholder="1552****5898">
			</div>
		</div>
		<div class="col-md-3 col-sm-3 form-group"></div>
		<div class="col-md-4 col-sm-4 form-group">
			<div class="input-group">
				<span class="input-group-addon">报告号</span> <input
					class="form-control" type="text"
					placeholder="1552****5898" id ="rptid">
			</div>
		</div>
		<div class="col-md-1 col-sm-1 form-group"></div>
		<div class="col-md-4 col-sm-4 form-group">
			<div class="input-group">
				<span class="input-group-addon">报告更新时间</span>
				 <input type="text" class="form-control sdf"  id="rptmodsrtdte"  onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'rptmodenddte\')||\'%y-%M-%d\'}'});" />
               	   <div class="input-group-addon">至</div>
               	   <input type="text" class="form-control sdfs" id="rptmodenddte" onclick="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'rptmodsrtdte\')}',dateFmt:'yyyyMMdd',maxDate:'%y-%M-%d'});"/>
			</div>
		</div>

		<div class="col-md-3 col-sm-3 form-group text-right">
			<button class="btn btn-primary" type="button">搜索</button>
		</div>

	</div>
</form>
<table id="reportdata" class="display" cellspacing="0" width="100%">
	<thead>
		
		<tr>
			<th></th>
			<th>报告更新时间</th>
			<th>报告号</th>
			<th>姓名</th>
			<th>身份证号</th>
			<th><select id="rpttyp" name="rpttyp">
									<option value="">报告类型</option>
									<option value="<%=Constant.RptTyp.RPTTYP_FRAUD.getCode()%>"><%=Constant.RptTyp.RPTTYP_FRAUD.getValue() %></option>
									<option value="<%=Constant.RptTyp.RPTTYP_VERIFIED.getCode()%>"><%=Constant.RptTyp.RPTTYP_VERIFIED.getValue() %></option>
							</select></th>
			<th>
			    <select id="datcmitori" onchange="sel()" >
        			<option value="">报告数据来源</option>
        			<option value="<%=Constant.DatCmitori.DatCmitori_APP.getCode() %>" ><%=Constant.DatCmitori.DatCmitori_APP.getValue() %></option>
        			<option value="<%=Constant.DatCmitori.DatCmitori_INTERFACE.getCode() %>" ><%=Constant.DatCmitori.DatCmitori_INTERFACE.getValue() %></option>
        			<option value="<%=Constant.DatCmitori.DatCmitori_PLANT.getCode() %>"><%=Constant.DatCmitori.DatCmitori_PLANT.getValue() %></option>
        			<option value="<%=Constant.DatCmitori.DatCmitori_WECHAT.getCode() %>"><%=Constant.DatCmitori.DatCmitori_WECHAT.getValue() %></option>
    			</select>
    		</th>
			<th style="text-align:left;">操作</th>

		</tr>
	</thead>
	
</table>
<script src="sinoway/dk3/js/datatables/jquery-1.12.0.min.js"></script>
<script src="sinoway/dk3/js/card.js"></script>
<script src="sinoway/dk3/js/jquery.validate.js"></script>
<script src="sinoway/dk3/js/additional-methods.js"></script>
<!-- datatable js库 -->
 <script src="datatables/lib/jquery-1.11.3.min.js"></script>
 <script src="datatables/lib/dataTables-1.10.7/media/js/jquery.dataTables.js"></script>
 <script src="datatables/js/table-constant.js"></script>
 <script src="<%=request.getContextPath()%>/sinoway/dk3/js/fadList.js"></script>
 <script type="text/javascript"
	src="<%=request.getContextPath()%>/windforce/common/js/My97DatePicker/WdatePicker.js"></script>
