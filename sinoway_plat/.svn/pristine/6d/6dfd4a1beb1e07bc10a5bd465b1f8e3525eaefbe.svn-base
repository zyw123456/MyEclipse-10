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
<script src="datatables/js/table-constant.js"></script>
<script src="datatables/js/myTable.js"></script>
<table id="myTable" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th><input type="checkbox" name="cb-check-all">
			</th>
			<th>姓名</th>
			<th>职位</th>
			<th>
				<select onchange="search()" id='bloodType'>
					<option selected value="O">=请选择血型=</option>
					<option value="A">A类型</option>
					<option value="B">B类型</option>
				</select>
			</th>
			<th>入职时间</th>
			<th>操作</th>
		</tr>
	</thead>
</table>