<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

    <div class="container chart-con-padding">
        <div class="col-md-6 col-sm-6">
            <div class="col-md-6 col-sm-6 text-center">
                <h2>平台监视统计</h2>
                <div id="mychart1" class="pie-chart" data-pie-chart--large data-percent="">
                    <div class="pie-char-content"><h1 id="myc1con">25.42<small>%</small></h1></div>
                </div>
            </div>
            <div class="col-md-6 col-sm-6 text-left chart-padding">
                <h3 class="font-color-gray">总监控</h3>
                <h3 class="font-color-grag1">356736373883</h3>
                <h3 class="font-color-gray">监控预警</h3>
                <h3 class="font-color-grag1">356736373883</h3>
            </div>
        </div>
        <div class="col-md-6 col-sm-6">
            <div class="col-md-6 col-sm-6 text-center">
                <h2>账号监视统计</h2>
                <div id="mychart2" class="pie-chart" data-pie-chart--small data-percent="55.42">
                    <div class="pie-char-content"><h1>55.42<small>%</small></h1></div>
                </div>
            </div>
            <div class="col-md-6 col-sm-6 text-left chart-padding">
                <h3 class="font-color-gray">委托监控</h3>
                <h3 class="font-color-grag1">356736373883</h3>
                <h3 class="font-color-gray">监控预警</h3>
                <h3 class="font-color-grag1">356736373883</h3>
            </div>
        </div>
    </div>
    <div class="row datas_tab">
        <div class="container">
            <table class="col-md-4 col-sm-4"  id="mytable">
                <thead>
                <tr>
                <th>
                    <img alt="" src="sinoway/dk2/images/jinggao.png"/>
                    <span>平台实时预警信息</span>
                </th>
                </tr></thead>
                <tr>
                    <td>
                        2015.10.12   13:12:24<br/>
                        <span>金融股类信息预警  信息可靠度40%</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        2015.10.12   13:12:24<br/>
                        <span>金融股类信息预警  信息可靠度40%</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        2015.10.12   13:12:24<br/>
                        <span>金融股类信息预警  信息可靠度40%</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        2015.10.12   13:12:24<br/>
                        <span>金融股类信息预警  信息可靠度40%</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        2015.10.12   13:12:24<br/>
                        <span>金融股类信息预警  信息可靠度40%</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        2015.10.12   13:12:24<br/>
                        <span>金融股类信息预警  信息可靠度40%</span>
                    </td>
                </tr>
            </table>

            <table class="col-md-7 col-sm-7 col-sm-offset-1" id="mytable2">
                <thead>
                    <tr>
                        <th colspan="5"> <img alt="" src="sinoway/dk2/images/info.png"/>
                            <span>平时实时报告信息</span>
                        </th>
                    </tr>
                </thead>
                <tbody class="max-td">
                <tr class="tabthead">
                    <td>报告时间</td>
                    <td>报告号</td>
                    <td>姓名</td>
                    <td>身份证号</td>
                    <td>报告类型</td>
                </tr>
                <tr >
                    <td>20151012 13:12:24</td>
                    <td>201510111234567</td>
                    <td>天使不哭</td>
                    <td>110xxxxxxxxxxx2345</td>
                    <td>验证报告</td>
                </tr>
                <tr >
                    <td>20151012 13:12:24</td>
                    <td>201510111234567</td>
                    <td>天使不哭</td>
                    <td>110xxxxxxxxxxx2345</td>
                    <td>验证报告</td>
                </tr>
                <tr >
                    <td>20151012 13:12:24</td>
                    <td>201510111234567</td>
                    <td>天使不哭</td>
                    <td>110xxxxxxxxxxx2345</td>
                    <td>验证报告</td>
                </tr>
                <tr >
                    <td>20151012 13:12:24</td>
                    <td>201510111234567</td>
                    <td>天使不哭</td>
                    <td>110xxxxxxxxxxx2345</td>
                    <td>验证报告</td>
                </tr>
                <tr >
                    <td>20151012 13:12:24</td>
                    <td>201510111234567</td>
                    <td>天使不哭</td>
                    <td>110xxxxxxxxxxx2345</td>
                    <td>验证报告</td>
                </tr>
                <tr >
                    <td>20151012 13:12:24</td>
                    <td>201510111234567</td>
                    <td>天使不哭</td>
                    <td>110xxxxxxxxxxx2345</td>
                    <td>验证报告</td>
                </tr>
                <tr >
                    <td>20151012 13:12:24</td>
                    <td>201510111234567</td>
                    <td>天使不哭</td>
                    <td>110xxxxxxxxxxx2345</td>
                    <td>验证报告</td>
                </tr>
                <tr >
                    <td>20151012 13:12:24</td>
                    <td>201510111234567</td>
                    <td>天使不哭</td>
                    <td>110xxxxxxxxxxx2345</td>
                    <td>验证报告</td>
                </tr>
                <tr >
                    <td>20151012 13:12:24</td>
                    <td>201510111234567</td>
                    <td>天使不哭</td>
                    <td>110xxxxxxxxxxx2345</td>
                    <td>验证报告</td>
                </tr>
                <tr >
                    <td>20151012 13:12:24</td>
                    <td>201510111234567</td>
                    <td>天使不哭</td>
                    <td>110xxxxxxxxxxx2345</td>
                    <td>验证报告</td>
                </tr>
                </tbody>

            </table>

            <div style="clear:both;"></div>
        </div>

    </div>
<script src="sinoway/dk2/js/jquery.min.js"></script>
<script src="sinoway/dk2/js/jquery.easypiechart.js"></script>
<script src="sinoway/dk2/js/home.js"></script>
<script>
    $('#mychart1').data('easyPieChart').update(40);
    $("#myc1con").html('40' + "<small>%</small>")
</script>