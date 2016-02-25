<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人征信报告查看</title>
<%@ include file="/common/wf_import.jsp"%>
<%@ include file="/windforce/common/jsp/wfInternalImport.jsp"%>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery-ui-1.8.18.custom.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath()%>/common/js/jquery/jquery.json-2.4.js'></script>
<script type="text/javascript"
	src='${ctx}/sinoway/rpt/js/rptDetail.js'></script>
<script type="text/javascript">
    var rptid = <%=request.getParameter("rptid") %>;
    var ctx   = "${ctx}";
</script>
	
<style>
.left_l {
	float: left;
	width: 50%;
	height: auto;
	text-align: center
}

.left_l p {
	text-align: left;
	color: #999;
	font-size: 14px;
}

.left_r {
	float: right;
	width: 50%;
	height: auto;
	padding-top: 100px
}

table {
	width: 100%;
	border-collapse: collapse;
	font-size: 14px;
}

tr {
	line-height: 30px;
	border-left: 4px solid #0070c0;
}

td {
	border: 1px dashed #ccc;
	padding: 0 10px;
}

.tdd {
	color: #999
}

.know_b {
	padding: 0 10px;
}

.main p {
	line-height: 25px;
	font-size: 14px;
}

h5 {
	font-size: 14px;
	font-weight: bold;
	height: 20px;
	margin: 30px 0 10px 0
}

h6 {
	font-size: 16px;
	font-weight: bold;
	height: 20px;
	margin: 40px 0 10px 0
}

.know_c {
	border-bottom: 1px solid #999;
	width: 90%;
	display: block;
	height: 9px;
	float: right;
}
.know_ab {
	border-bottom: 1px solid #999;
	width: 85%;
	display: block;
	height: 9px;
	float: right;
}
h6 .know_c {
	border-bottom: 2px solid #0070c0;
}

.main tr {
	border: none
}

.main td {
	border: 1px solid #ccc
}
</style>
</head>
<body>
	<div id="infoHtml2"></div>
	
	
	<div id="right">
		<div style="padding: 10px; width: 90%; margin: 0 auto">
			<div class="left_l">
				<img src="
data:image/png;base64,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"/>
				<p>北京华道征信有限公司</p>
				<p>个人信用报告部门</p>
				<p>地址：北京市朝阳区光华路4号东方梅地亚中心C座1210室</p>
				<p>联系电话：400-003-4020</p>
				<p>联系E-Mail: service@sinowaycredit.com</p>
			</div>
			<div class="left_r">
				<form>
					<table>
	
						<tr>
							<td>公布时间：</td>
							<td id="rptdte"></td>
						</tr>

						<tr>
							<td>编号索引：</td>
							<td id="rptid"></td>
						</tr>

						<tr>
							<td>姓名：</td>
							<td  class="atvalue"></td>
						</tr>
						<tr>
							<td>身份证号：</td>
							<td id="prsncod"></td>
						</tr>
		
					</table>
				</form>
			</div>
		</div>

<div id="infoHtml" class="main" style="padding:10px; width:90%; margin:0 auto; clear:both;">
   <p>尊敬的<a class="atvalue">安*</a>先生／女士：</p>
   <p class="know_b">以下是您的个人信用报告，请详细阅读以了解您的信用状况。北京华道征信有限公司所提供的此份信用报告能够帮助您处理自己的财务等信用相关问题。这些问题或将包括您获取贷款、尽早发现身份被盗、其他不为本人所知的信贷诈骗等。这将有助于您实现本人的信用相关目标，例如购房、购车、预支消费等。</p>
   <p class="know_b">北京华道征信有限公司作为独立的第三方，专注于搭建信用相关机构与消费者之间客观与可靠的桥梁，使各种信用相关服务提供者能够提供关于您的共用资料。北京华道征信有限公司在严格遵守中华人民共和国的各项法律法规的基础上收集、持有及使用您的资料。有关您资料收集、持有及使用的具体信息请浏览网址www.sinowaycredit.com。北京华道征信有限公司绝不参与任何与该份报告相关的，信息提供者和/或使用者对于该份报告所有者的任何决策过程。</p>
   <p class="know_b">倘若您对此报告有任何疑问或异议，请致电以上联系电话。致电时请使用以上编号索引以供查询。</p>
   <p class="know_b">此致敬礼！</p>
   
  
        
    
	

	</div>
</body>
</html>