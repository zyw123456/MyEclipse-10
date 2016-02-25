<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String naviMenu = session.getAttribute("_naviMenu").toString();
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <!--<link rel="icon" href="favicon.ico" type="image/x-icon" />-->
    <link rel="stylesheet" href="sinoway/dk3/style/bootstrap.css">
    <link rel="stylesheet" href="sinoway/dk3/style/common.css">
    <link rel="stylesheet" href="sinoway/dk3/style/mains.css">
    <link rel="stylesheet" href="sinoway/dk3/style/css/jquery.dataTables.css">
    
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
	        </ul>
        </div>
    </div>
</header>
<div class="container top-margin-20" id="content-split" style="display:none;">
    <div class="row bg-color-content">
        <div class="col-md-2 col-sm-2 list-group" id="leftheight" >
        </div>
        <div class="col-md-10 col-sm-10 bg-color-white right-con-shadow" id="rightheight">
        </div>
	</div>
</div>
<div class="container-fluid top-margin-20" id="content-all" >
</div>
<footer class="container-fluid bg-color-gray" id="foot">

</footer>
<script src="sinoway/dk3/js/jquery.min.js"></script>
<script src="sinoway/dk3/js/desk.js"></script>
<script src="sinoway/dk3/js/common.js"></script>
<script>
        var naviMenu = <%=naviMenu%>;
        var ctx = "<%=path%>";
</script>
</body>
</html>