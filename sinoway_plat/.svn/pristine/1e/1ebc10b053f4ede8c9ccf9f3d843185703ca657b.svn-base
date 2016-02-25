<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="col-md-12 col-sm-12">
	<div class="row">
		<h4 class="text-left">反欺诈云模块策略</h4>
		<table id="dataTable" class="display" cellspacing="0" width="100%">
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
			<!-- <tbody>
				<tr>
					<td>20151012</td>
					<td>标准模板</td>
					<td><span>身份验证</span> <span>学历验证</span> <span>金融类监测</span> <span>消费类监测</span>
					</td>
					<td><a href="javascript:void(0)" onclick="preUpdatePrd('20001509')">修改</a>&nbsp;&nbsp;&nbsp;<a
						href="javascript:void (0);" data-toggle="modal"
						data-target="#myModal1">删除</a>
					</td>
				</tr>
				<tr>

					<td>20151012</td>
					<td>自定义模板</td>
					<td><span>身份验证</span> <span>职业验证</span> <span>查询类监测</span>
					</td>
					<td><a href="javascript:void(0)" onclick="preUpdatePrd('20001522')">修改</a>&nbsp;&nbsp;&nbsp;<a
						href="javascript:void (0);" data-toggle="modal"
						data-target="#myModal1">删除</a>
					</td>
				</tr>
			</tbody> -->
		</table>
	</div>
</div>
<div class="col-md-12 col-sm-12 top-margin-20 bg-color-gray2"
	id="firststep">
	<div class="col-md-6 col-sm-6 form-group top-margin-20">
		<div class="input-group">
			<span class="input-group-addon">自定义报告名称</span> <input type="text"
				value="" placeholder="" class="form-control" id="prdnam">
		</div>
	</div>
	<div class="col-md-12 col-sm-12">
		<h5 class="text-center">请勾选报告服务内容</h5>
	</div>
	<div class="col-md-12 col-sm-12">
		<div class="row border-b-2-ccc" id="veriNature">
			<div class="col-md-3 col-sm-3">
				<div class="row">
					<span class="span-button">&nbsp;&nbsp;&nbsp;&nbsp;验证模块&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</div>
			</div>
			<div class="col-md-9 col-sm-9">
				<div class="row checkbox">
				</div>
			</div>
		</div>
		<div class="row top-margin-20" id="exceNature">
			<div class="col-md-3 col-sm-3">
				<div class="row">
					<span class="span-button">异常查询模块</span>
				</div>
			</div>
			<div class="col-md-9 col-sm-9">
				<div class="row checkbox">
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-3 col-sm-3 col-md-offset-3">
			<a href="javascript:void (0);" class="usual-botton" id="next">下一步</a>
		</div>
		<div class="col-md-3 col-sm-3 col-md-offset-3">
			<a href="javascript:void (0);" class="usual-botton cancel"
				id="cancel">取&nbsp;&nbsp;消</a>
		</div>
	</div>
</div>
<div class="col-md-12 col-sm-12 top-margin-20 bg-color-gray2"
	id="nextstep">
	<div class="col-md-6 col-sm-6 form-group top-margin-20">
		<div class="input-group">
			<span class="input-group-addon">模板应用于</span> <img
				src="sinoway/dk3/images/search.png" class="img-search" onclick="searchPeoOrTeam()"> <input type="text"
				value="" placeholder="" class="form-control" id="queryData">
		</div>
	</div>
	<div class="row col-md-12 col-sm-12 bottom-margin-20">
		<div class="row">
			<div class="col-md-4 col-sm-4" id="myTab">
				<ul class="nav nav-justified bg-color-ccc tab-li font-s-14">
					<li><a href="#person" aria-controls="person" data-toggle="tab" id="peoButton">
							<span class="glyphicon glyphicon-user"></span> 个人 </a></li>
					<li><a href="#group" aria-controls="group" data-toggle="tab" id="teamButton">
							<i class="icon-group"></i> 团队 </a></li>
				</ul>
				<div class="col-sm-12 bg-color-white" id="left">
					<div class="tab-content" id="left-content">
						<div class="tab-pane active" id="person" role="tabpanel"
							id="myTabs2">
							<ul class="list-group" id="peoples">
							</ul>
						</div>
						<div class="tab-pane" id="group" role="tabpanel">
							<ul class="list-group" id="teams">
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-8 col-sm-8">
				<ul class="nav bg-color-ccc tab-li font-s-14 next-title">
					<li><a href="javascript:void (0)"> 已选择 </a></li>
				</ul>
				<div
					class="col-sm-12 tab-content bg-color-white checkbox next-content"
					id="right-content">
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-3 col-md-offset-3">
			<a href="javascript:void (0);" class="usual-botton" id="saveOrUpdate">保&nbsp;&nbsp;存</a>
		</div>
		<div class="col-md-3">
			<a href="javascript:void (0);" class="usual-botton cancel"
				id="cancel1">取&nbsp;&nbsp;消</a>
		</div>
	</div>
</div>
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" id="delDiv">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel1">是否要删除</h4>
            </div>
            <div class="modal-body text-center">
                <span>确定要删除吗？</span>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="closeDel">关闭</button>
                <button type="button" class="btn btn-primary" id="delPrd">确定</button>
            </div>
        </div>
    </div>
</div>

<script src="sinoway/dk3/js/jquery.min.js"></script>
<script src="sinoway/dk3/js/datatables/jquery-1.12.0.min.js"></script>
<script src="sinoway/dk3/js/bootstrap.min.js"></script>
<script src="sinoway/dk3/js/datatables/jquery.dataTables.js"></script>
<script src="sinoway/dk3/js/table.js"></script>
<script type="text/javascript" language="javascript" class="init">
    var ctx = "<%=path%>";
    $(document).ready(function(){
        //初始化表格数据
		isStmIndex = false;
		var appcod = "002";
		initTable("", appcod);
    });
</script>