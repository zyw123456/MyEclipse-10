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
                    <div class="pie-char-content"><h1 id="myc1con"></h1></div>
                </div>
            </div>
            <div class="col-md-6 col-sm-6 text-left chart-padding">
                <h3 class="font-color-gray">总监控</h3>
                <h3 class="font-color-grag1" id="platMonitor">0</h3>
                <h3 class="font-color-gray">监控预警</h3>
                <h3 class="font-color-grag1" id="platAlarm">0</h3>
            </div>
        </div>
        <div class="col-md-6 col-sm-6">
            <div class="col-md-6 col-sm-6 text-center">
                <h2>账号监视统计</h2>
                <div id="mychart2" class="pie-chart" data-pie-chart--small data-percent="">
                    <div class="pie-char-content"><h1 id="myc2con"></h1></div>
                </div>
            </div>
            <div class="col-md-6 col-sm-6 text-left chart-padding">
                <h3 class="font-color-gray">委托监控</h3>
                <h3 class="font-color-grag1" id="accMonitor">0</h3>
                <h3 class="font-color-gray">监控预警</h3>
                <h3 class="font-color-grag1" id="accAlarm">0</h3>
            </div>
        </div>
    </div>
    <div class="row datas_tab">
        <div class="container">
            <table class="col-md-4 col-sm-4"  id="mytable">
                <thead>
                <tr>
                <th>
                    <img alt="" src="sinoway/dk3/images/jinggao.png"/>
                    <span>平台实时预警信息</span>
                </th>
                </tr></thead>
                <div>
                <tr class='myTr'><td><br><span>&nbsp;</span></td></tr>
                <tr class='myTr'><td><br><span>&nbsp;</span></td></tr>
                <tr class='myTr'><td><br><span>&nbsp;</span></td></tr>
                <tr class='myTr'><td><br><span>&nbsp;</span></td></tr>
                <tr class='myTr'><td><br><span>&nbsp;</span></td></tr>
                <tr class='myTr'><td><br><span>&nbsp;</span></td></tr>
                </div>
            </table>

            <table class="col-md-7 col-sm-7 col-sm-offset-1" id="mytable2">
                <thead>
                    <tr>
                        <th colspan="5"> <img alt="" src="sinoway/dk3/images/info.png"/>
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
                <tr class='myTr2'><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr class='myTr2'><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr class='myTr2'><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr class='myTr2'><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr class='myTr2'><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr class='myTr2'><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr class='myTr2'><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr class='myTr2'><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr class='myTr2'><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr class='myTr2'><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                </tbody>

            </table>

            <div style="clear:both;"></div>
        </div>

    </div>
<script src="sinoway/dk3/js/datatables/jquery-1.12.0.min.js"></script>
<script src="sinoway/dk3/js/jquery.easypiechart.js"></script>
<script src="sinoway/dk3/js/home.js"></script>
<script>
    var ctx = "<%=path%>";
     	$('#mychart1').data('easyPieChart').update(40);
        $("#myc1con").html('40' + "<small>%</small>");
        $('#mychart2').data('easyPieChart').update(60);
		$("#myc2con").html('60' + "<small>%</small>");
    function showPlatChart(platPercent){
		$('#mychart1').data('easyPieChart').update(platPercent);
		$("#myc1con").html(platPercent + "<small>%</small>");
    }
    function showAccChart(accPercent){
		$('#mychart2').data('easyPieChart').update(accPercent);
		$("#myc2con").html(accPercent + "<small>%</small>");
    }
</script>