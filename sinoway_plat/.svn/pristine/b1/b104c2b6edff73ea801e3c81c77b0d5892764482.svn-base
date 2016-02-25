<%--
  机构添加页面                 
@date          2012/04/25         
@author        蒋正秋  ,chenhuang
@version       1.0                
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>导入机构信息页面</title>
<%@ include file="/common/wf_import.jsp"%>
<%@ include file="/windforce/common/jsp/wfInternalImport.jsp"%>
<script type="text/javascript" src="${ctx }/windforce/common/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	
	function importFile() {
		var filename = jQuery.trim(jQuery("#importExcelFile").val()); 
		if (!filename || filename == ""){  
			top.wfAlert("请先选择文件"); 
			return false; 
		} 
		var fileType = filename.substr(filename.lastIndexOf(".")).toLowerCase(); 
		if(fileType!=".xls"){
			top.wfAlert("请使用Excel文件导入数据");
			return false;
		}
		top.startProcess("正在上传导入数据");
		$.ajaxFileUpload({
			url : ctx+"/importOrg_importOrg.action", //用于文件上传的服务器端请求地址
			secureuri : false,//一般设置为false
			fileElementId : 'importExcelFile',//文件上传空间的id属性
			dataType : 'json',//返回值类型 一般设置为json
			success : function(data, status){ //服务器成功响应处理函数
				top.stopProcess();
				if("normal" == data.state){
					top.wfAlert("导入数据成功。");
					top.closeShowPage();
				}else{
					top.wfAlert("导入数据异常,"+data.message);
				}
			},
			error : function(data, status, e){//服务器响应失败处理函数
				top.stopProcess();
				top.wfAlert("文件上传失败,请确认文件大小未超过5M");
			}
		});
		
	}
	
</script>
</head>
<body>
	<div id="login_bg" style="filter:alpha(opacity:75);opacity:0.75;"></div>
	<div id="login_nov">
		<table>
			<tr >
				<td style="height:40px;">
					&nbsp;&nbsp;文件导入:
       			 	<input type="file" id="importExcelFile" name="importExcelFile" size="40" style="height:24px;"/>
					<input type="button"  onclick="importFile()" value="开始导入" style="height:24px;margin-top:2px;"/>
				</td>
			</tr>
			<tr >
				<td style="text-align:left;">
					<span style="margin-left: 10px;">1.导入文件必须是Excel格式且按照给定的模板文件格式进行导入<a style="color:red; margin-left: 10px;"  target="_blank"  href="${ctx }/windforce/download/OrganizationList.xls" >模板文件下载</a></span><br/>
					<span style="margin-left: 10px;">2.机构信息必须完整，否则导入数据会有误差</span><br/>
					<span style="margin-left: 10px;">3.从模板文件导入的机构信息如有数据异常不会进行具体提示，请参照最终导入结果</span><br/>
					<span style="margin-left: 10px;">4.导入文件大小应不超过5M</span>
				</td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		top.stopProcess();
	</script>
</body>
</html>