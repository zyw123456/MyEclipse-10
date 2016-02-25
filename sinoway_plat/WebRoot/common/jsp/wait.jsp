<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String waitPath = request.getContextPath();
%>
<link type="text/css" rel="stylesheet"
	href="<%=waitPath%>/common/css/wait.css" />
<div id="pbar_main">
	<!-- 	<a href="javascript:showBg();">点击这里查看效果</a> -->
	<div id="pbar_fullbg"></div>
	<div id="pbar_dialog" class="pbar_dialog">
		<img src="<%=waitPath%>/common/images/ajax_bar.gif" />缓存更新中,请稍后...
	</div>
</div>
<script type="text/javascript">
	//显示灰色 jQuery 遮罩层
	(function showBg() {
		var bh = window.screen.height;
		var bw = window.screen.width;
		$("#pbar_fullbg").css({
			height : bh,
			width : bw,
			display : "block"
		});
		$("#pbar_dialog").show();
	}());

	//关闭灰色 jQuery 遮罩
	window.onload = function closeBg() {
		$("#pbar_fullbg,#pbar_dialog").hide();
	};
</script>