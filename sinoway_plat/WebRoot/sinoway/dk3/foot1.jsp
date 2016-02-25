<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <div class="container foot">
        <div class="col-md-2 col-sm-2 font-height-center">
                <span>平台使用流量</span>
        </div>
        <div class="col-md-5 col-sm-5">
            <div class="col-md-3 col-sm-3 text-center">
                <img src="sinoway/dk3/images/day_1.png">
                <h5>今日流量</h5>
            </div>
            <div class="col-md-8 col-sm-8 font-height-center">
                215468
            </div>
        </div>
        <div class="col-md-5 col-sm-5">
            <div class="col-md-3 col-sm-3 text-center">
                <img src="sinoway/dk3/images/day1-3.png">
                <h5>近三日流量</h5>
            </div>
            <div class="col-md-8 col-sm-8 text-left foot-rel" >
                <div id="mybarchart" class="barpos" style="width: 100px; height: 60px;">

                </div>
            </div>
        </div>
    </div>
<script src="sinoway/dk3/js/echarts/echarts.js"></script>
<script src="sinoway/dk3/js/datatables/jquery-1.12.0.min.js"></script>
<script>
    $(document).ready(function() {
        var footheight = $(".foot").parent().height() - 30;
        $(".font-height-center").height(footheight + "px");
        $(".font-height-center").css("line-height",footheight + "px")
    });
    var myChart = echarts.init(document.getElementById('mybarchart'));
    option = {
        tooltip: {
            trigger: 'item'
        },
        grid:{
            x:0,
            y:20,
            x2:0,
            y2:0
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                show: true,
                data: ['1', '2', '3'],
                axisLine:{
                    show:true,
                    lineStyle:{
                        color:'#fff'
                    }
                },
                axisTick:{
                    show:false
                },
                splitLine:{
                    show:false
                },
                axisLabel:{
                    margin:4,
                    textStyle:{
                        color:'#fff'
                    }
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                show: false
            }
        ],
        series: [
            {
                type: 'bar',
                itemStyle: {
                    normal: {
                        color:'#fff',
                        label: {
                            show: true,
                            position: 'top',
                            // formatter: '{b}\n{c}'
                        }
                    }
                },
                data: [12,21,10]

            }
        ]
    };
    // 为echarts对象加载数据
    myChart.setOption(option);
   </script>