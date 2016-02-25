<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <!--<link rel="icon" href="favicon.ico" type="image/x-icon" />-->
    <link rel="stylesheet" href="sinoway/dk2/style/bootstrap.css">
    <link rel="stylesheet" href="sinoway/dk2/style/common.css">
    <link rel="stylesheet" href="sinoway/dk2/style/mains.css">
    <!--[if lte IE 9]>
    <script src="js/respond.js"></script>
    <script src="js/html5shiv.js"></script>
    <![endif]-->
    <title>测试样式</title>  
</head>
  
 <body>
<header class="container-fluid top">
    <div class="container logo_top">
        <img src="sinoway/common/images/login_home.png">
    </div>
    <div class="container">
        <div class="row">
	        <ul class="nav nav-pills nav-justified" id="firstMenu">
	            <li><a href="javascript:void(0)" class="indexmenu">首页</a></li>
	            <li ><a href="javascript:void(0)" class="splitmenu">个人报告</a></li>
	            <li ><a href="javascript:void(0)" class="splitmenu">反欺诈云</a></li>
	            <li><a href="javascript:void(0)" class="splitmenu">天警云</a></li>
	            <li class="active"><a href="javascript:void(0)" class="splitmenu">策略管理</a></li>
	            <li><a href="javascript:void(0)" class="splitmenu">账号管理</a></li>
	        </ul>
        </div>
    </div>
</header>
<div class="container top-margin-20" id="content-split">
    <div class="row bg-color-content">
        <div class="col-md-2 col-sm-2 list-group" id="leftheight" >
            <a href="javascript:void(0)" class="list-group-item" id="menu1">策略列表</a>
            <a href="javascript:void(0)" class="list-group-item" id="menu2">报告模块</a>
            <a href="javascript:void(0)" class="list-group-item" id="menu3">反欺诈云模块</a>
            <a href="javascript:void(0)" class="list-group-item" id="menu4">天警云模块</a>
            <a href="javascript:void(0)" class="list-group-item" onclick="changeUrl()">二维码管理</a>
        </div>
        <div class="col-md-10 col-sm-10 bg-color-white right-con-shadow" id="rightheight">
        </div>
	</div>
</div>
<div class="container-fluid top-margin-20" id="content-all">
</div>
<footer class="container-fluid bg-color-gray" id="foot">

</footer>
<script src="sinoway/dk2/js/jquery.min.js"></script>
<script>
    $(document).ready(function(){
    	$("#rightheight").load("sinoway/dk2/blank.jsp",function(){
    		console.log("这是一个空白页面...");
    	});
        var conheight =  $(window).height() - $(".top").height() - $(".foot").parent().height() - 40;
        var heightleft = $("#leftheight").height();
        var heightright = $("#rightheight").height();
        var realheight;
        realheight = heightleft > heightright?heightleft:heightright;
        if(conheight > realheight){
            $("#rightheight").css("min-height" ,conheight);
            $("#leftheight").css("min-height",conheight);
        }
    });
    $(".indexmenu").click(function(){
        $(this).parent().siblings("li").removeClass("active");
       	$(this).parent().addClass("active");
    	$("#content-split").hide();
    	$("#content-all").show();
    	$("#content-all").load("sinoway/dk2/home.jsp");
    	$("#foot").load("sinoway/dk2/foot1.jsp");
    });
    $(".splitmenu").click(function(){
        $(this).parent().siblings("li").removeClass("active");
       	$(this).parent().addClass("active");
    	$("#content-split").show();
    	$("#content-all").hide();
    	//$("#foot").addClass("top-margin-20");
    	$("#foot").load("sinoway/dk2/foot2.jsp");
    	//$("#content-all").load("sinoway/dk2/home.jsp");
    });
    $("#menu1").click(function(){
       	$(this).siblings("a").removeClass("active");
       	$(this).addClass("active");
    	$("#rightheight").load("sinoway/dk2/right.jsp");
    });
    $("#menu2").click(function(){
    	$(this).siblings("a").removeClass("active");
        $(this).addClass("active");
    	$("#rightheight").load("sinoway/dk2/right1.jsp");
    });
    $("#menu3").click(function(){
    	$(this).siblings("a").removeClass("active");
        $(this).addClass("active");
    	$("#rightheight").load("sinoway/dk2/right3.jsp");
    });
    $("#menu4").click(function(){
    	$(this).siblings("a").removeClass("active");
        $(this).addClass("active");
    	$("#rightheight").load("sinoway/dk2/userreport.jsp");
    });
//     function changeUrl2(){
//    	$("#rightheight").load("sinoway/dk2/right1.jsp");
//    	$(this).addClass("active");
//   	debugger;
//    }
</script>
</body>
</html>