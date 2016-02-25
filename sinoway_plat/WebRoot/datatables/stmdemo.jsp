<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<!-- JQuery -->
<script src="datatables/lib/jquery-1.11.3.min.js"></script>
<!-- DataTables -->
<script
	src="datatables/lib/dataTables-1.10.7/media/js/jquery.dataTables.js"></script>
<!-- table通用的constant属性 -->
<script src="datatables/js/stmTable.js"></script>
<table id="myTable" class="display" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>更新时间</th>
			<th>模板名称</th>
			<th>包含内容</th>
			<th>操作</th>
		</tr>
	</thead>
	<tfoot>
		<tr>
			<th>----</th>
			<th>----</th>
			<th>----</th>
			<th><a href="javascript:void(0);" id="newcreat"> 新建</a></th>
		</tr>
	</tfoot>
</table>