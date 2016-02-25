<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link rel="stylesheet" href="sinoway/dk2/style/css/jquery.dataTables.css">
<link rel="stylesheet" href="sinoway/dk2/style/table.css">

<div class="col-md-12 col-sm-12">
        <h4 class="text-left" >天警云模块策略</h4>
        <table  id="manager4" class="display" cellspacing="0" width="100%">
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
                <th><a href="javascript:void(0);" data-toggle="modal" data-target="#myModal"> 新建</a> </th>
            </tr>
            </tfoot>
            <tbody>
            <tr>
                <td style="line-height: 16px;">20151012</td>
                <td style="line-height: 16px;">标准模板</td>
                <td style="line-height: 16px;">
                    <p class="p-all"><span>消费类监测</span> <span>日积累5条</span> <span>月积累20条</span></p>
                    <p class="p-all"><span>公共类监测</span> <span>日积累5条</span> <span>月积累20条</span></p>
                    <p class="p-all"><span>查询类监测</span> <span>日积累5条</span> <span>月积累20条</span></p>
                </td>
                <td style="line-height:16px;"><a  href="##">修改</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void (0);" data-toggle="modal" data-target="#myModal1">删除</a></td>
            </tr>
            <tr>
                <td style="line-height: 16px;">20151012</td>
                <td style="line-height: 16px;">标准模板</td>
                <td style="line-height: 16px;">
                    <p class="p-all"><span>消费类监测</span> <span>日积累5条</span> <span>月积累20条</span></p>
                    <p class="p-all"><span>金融类监测</span> <span>日积累5条</span> <span>月积累20条</span></p>
                </td>
                <td style="line-height:16px;"><a  href="##">修改</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void (0);" data-toggle="modal" data-target="#myModal1">删除</a></td>
            </tr>
            </tbody>
        </table>
    </div>

<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="del">确定</button>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class=" modal fade " id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">请勾选报告服务内容</h4>
                </div>
                <div class="modal-body">

                    <div class="col-md-8 col-sm-8 col-md-offset-2 form-group">
                        <div class="input-group">
                            <span class="input-group-addon">自定义报告名称：</span>
                            <input class="form-control required"  type="text" placeholder="新建模板">
                        </div>
                    </div>

                    <div class="col-md-6 col-sm-6 bor-1" >
                        <div class="col-md-12 col-sm-12 yanzheng">金融类数据监测</div>

                        <div class="col-md-12 col-sm-12 top-margin-10">
                            <div class="input-group">
                                <span class="input-group-addon group-addon-bargound">单日积累异常数据</span>
                                <input class="form-control required"  type="text" placeholder="5">
                                <span class="input-group-addon group-addon-bargound">条</span>
                            </div>
                        </div>

                        <div class="col-md-12 col-sm-12 top-margin-10">
                            <div class="input-group">
                                <span class="input-group-addon group-addon-bargound">当月累计异常数据</span>
                                <input class="form-control required"  type="text" placeholder="5">
                                <span class="input-group-addon group-addon-bargound">条</span>
                            </div>
                        </div>

                        <div class="col-md-12 col-sm-12 top-margin-10">
                            <div class="input-group">
                                <span class="input-group-addon group-addon-bargound">近三个月累计异常数据</span>
                                <input class="form-control required"  type="text" placeholder="5">
                                <span class="input-group-addon group-addon-bargound">条</span>
                            </div>
                        </div>

                    </div>

                    <div class="col-md-6 col-sm-6 bor-1 bor-3" >
                        <div class="col-md-12 col-sm-12 yanzheng ">消费类数据监测</div>

                        <div class="col-md-12 col-sm-12 top-margin-10">
                            <div class="input-group">
                                <span class="input-group-addon group-addon-bargound">单日积累异常数据</span>
                                <input class="form-control required"  type="text" placeholder="5">
                                <span class="input-group-addon group-addon-bargound">条</span>
                            </div>
                        </div>

                        <div class="col-md-12 col-sm-12 top-margin-10">
                            <div class="input-group">
                                <span class="input-group-addon group-addon-bargound">当月累计异常数据</span>
                                <input class="form-control required"  type="text" placeholder="5">
                                <span class="input-group-addon group-addon-bargound">条</span>
                            </div>
                        </div>

                        <div class="col-md-12 col-sm-12 top-margin-10">
                            <div class="input-group">
                                <span class="input-group-addon group-addon-bargound">近三个月累计异常数据</span>
                                <input class="form-control required"  type="text" placeholder="5">
                                <span class="input-group-addon group-addon-bargound">条</span>
                            </div>
                        </div>

                    </div>

                    <div class="col-md-6 col-sm-6 bor-1 top-margin-10" >
                        <div class="col-md-12 col-sm-12 yanzheng">个人被查询类数据监测</div>

                        <div class="col-md-12 col-sm-12 top-margin-10">
                            <div class="input-group">
                                <span class="input-group-addon group-addon-bargound">单日积累异常数据</span>
                                <input class="form-control required"  type="text" placeholder="5">
                                <span class="input-group-addon group-addon-bargound">条</span>
                            </div>
                        </div>

                        <div class="col-md-12 col-sm-12 top-margin-10">
                            <div class="input-group">
                                <span class="input-group-addon group-addon-bargound">当月累计异常数据</span>
                                <input class="form-control required"  type="text" placeholder="5">
                                <span class="input-group-addon group-addon-bargound">条</span>
                            </div>
                        </div>

                        <div class="col-md-12 col-sm-12 top-margin-10">
                            <div class="input-group">
                                <span class="input-group-addon group-addon-bargound">近三个月累计异常数据</span>
                                <input class="form-control required"  type="text" placeholder="5">
                                <span class="input-group-addon group-addon-bargound">条</span>
                            </div>
                        </div>

                    </div>

                    <div class="col-md-6 col-sm-6 bor-1 bor-3 top-margin-10" >
                        <div class="col-md-12 col-sm-12 yanzheng ">公共信息类数据监测</div>

                        <div class="col-md-12 col-sm-12 top-margin-10">
                            <div class="input-group">
                                <span class="input-group-addon group-addon-bargound">单日积累异常数据</span>
                                <input class="form-control required"  type="text" placeholder="5">
                                <span class="input-group-addon group-addon-bargound">条</span>
                            </div>
                        </div>

                        <div class="col-md-12 col-sm-12 top-margin-10">
                            <div class="input-group">
                                <span class="input-group-addon group-addon-bargound">当月累计异常数据</span>
                                <input class="form-control required"  type="text" placeholder="5">
                                <span class="input-group-addon group-addon-bargound">条</span>
                            </div>
                        </div>

                        <div class="col-md-12 col-sm-12 top-margin-10">
                            <div class="input-group">
                                <span class="input-group-addon group-addon-bargound">近三个月累计异常数据</span>
                                <input class="form-control required"  type="text" placeholder="5">
                                <span class="input-group-addon group-addon-bargound">条</span>
                            </div>
                        </div>

                    </div>

                    <div class="clearfix"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary">保存</button>
                </div>
            </div>
        </div>
    </div>
<script src="sinoway/dk2/js/datatables/jquery-1.12.0.min.js"></script>
<script src="sinoway/dk2/js/bootstrap.min.js"></script>
<script src="sinoway/dk2/js/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" class="init">
    $(document).ready(function(){
        var table = $('#manager4').DataTable( {
            searching:false,
            paging:false,
            pageLength: 7,
            "ordering": false,
            "lengthChange": false,
            "language": {
                "lengthMenu": "_MENU_ 条记录每页",
                "zeroRecords": "没有找到记录",
                "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
                "infoEmpty": "无记录",
                "infoFiltered": "(从 _MAX_ 条记录过滤)",
                "paginate": {
                    "previous": " < ",
                    "next": ">"
                }
            },
        } );
        $('#del').click(function () {
            table.row().remove().draw(false);
            $(".modal").css("display","none")
            $(".modal-backdrop").css("display","none")
        });
//        var conheight =  $(window).height() - $(".top").height() - $(".foot").parent().height() - 40;
//        var heightleft = $("#leftheight").height();
//        var heightright = $("#rightheight").height();
//        $("#rightheight").css("min-height" ,conheight)
//        $("#leftheight").css("min-height",conheight)
//        if( heightleft > heightright){
//            $("#rightheight").height(heightleft)
//            $("#leftheight").height(heightleft)
//        }else{
//            $("#leftheight").height(heightright)
//            $("#rightheight").height(heightright)
//        };
    });
</script>