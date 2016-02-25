	$(document).ready(function(){
		searchTrnList();
	});
	
	//交易码名称数组
	var trnName=[];
	//交易码编码数组
	var trnCode=[];
	
  	// 查询原交易编码，名称列表
  	function searchTrnList() {
  			$.ajax({
  						url : '/windforce/queryTrnList.action',
  						data : {'warnid':warnid},
  						type : 'post',
  						async : true,
  						dataType : "json",
  						success : function(data) {
  							var showStr="";
  							if (data != "" && data != null) {
  								if (data.list != null && data.list.length != 0) {
  									for ( var i = 0; i < data.list.length; i++) {
  										var dataList = data.list[i];
  										trnName[i]=dataList[1];
  										trnCode[i]=dataList[0];
  										showStr+= "<div><h3>"+dataList[1]+"</h3>";
  									    //每种交易表头编码
  										var trnTitle=[];
  										//表头长度
  										var k=0;
  										showStr+= "截止"+getCurrentDateTime()+"监控人员已累计被查询"+dataList[2]+"次，具体被查询详情如下：<br><br>"; 
  										// 表头输出
  										if(data.trnEleList != null && data.trnEleList.length != 0){
  											for(var j=0;j<data.trnEleList.length;j++){
  												var eleList = data.trnEleList[j];
  												if(eleList[0]==dataList[0]){
  													showStr+="<span width='100px;'>"+eleList[2]+"      </span>";
  													trnTitle[k]=eleList[1];
  													k++;
  												}
  											}
  										}
  										showStr+="<br>"
  										var contentstr="";
  										// 内容输出
  										$.ajax({
	  					  						url : '/windforce/queryWarnDetailContent.action',
	  					  						data : {'warnid':warnid,'trnCod':dataList[0]},
	  					  						type : 'post',
	  					  						async : false,
	  					  						dataType : "json",
	  					  						success : function(data) {
	  					  							var contentList= data.contentList;
	  					  							for(var k= 0;k<contentList.length;k++){
	  					  							contentstr+="<br>";
		  					  							var content =contentList[k];
		  					  						    // 首先把字符串转成 JSONArray对象
		  					  							var arr = eval('('+content+')');
		  					  							for(var j=0;j<trnTitle.length;j++){
		  					  								var key=trnTitle[j];
		  					  							  //遍历Json串获取其属性  
		  					  							    for(var item in arr){
		  					  							       if(item==key){  
		  					  							    	   //item 表示Json串中的属性，如'name'
		  					  							    	   //jValue所对应的value
		  					  							    	   var jValue=arr[item];
		  					  							    	   contentstr+= "<span>"+jValue+"</span>     ";
		  					  							        }
		  					  							    }
		  					  							}
	  					  							}
	  					  						  showStr+=contentstr;
	  					  						},
	  					  						error : function(err) {
	  					  							alert("err:" + err);
	  					  						}
  											});
  										showStr+="<br></div>";
  									}
  								}
  							}
  							showStr+="<div align='center;' width='500px;'><input type='button' value='确定' onclick='javascript:window.close();'></div>";
  							$("#trnList").html(showStr);
  						},
  						error : function(err) {
  							alert("err:" + err);
  						}
  					});
  	}
  	


  	function getCurrentDateTime() {
		var dateTime = new Date();
		var hh = dateTime.getHours();
		var mm = dateTime.getMinutes();
		var ss = dateTime.getSeconds();
		var yy = dateTime.getFullYear();
		var MM = dateTime.getMonth() + 1; 
		var dd=dateTime.getDate();
		var datetime=yy+"年"+MM+"月"+dd+"日 "+hh+"时"+mm+"分"+ss+"秒";
		return datetime;
  	}
