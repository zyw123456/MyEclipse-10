//页面初始化	
	$(document).ready(function() {
		//获取left框架的高度并设置给form表单
		
		//查询数据
		$("#queryButton").click(function(){ 
			show();
			//强行改成600
			
		});
		
		//删除报告
		$("#deleteButton").click(function(){
			deleteFraudRpt();
		});
		
		//下拉报告类型列表查询后台数据
		$("#reportType").change(function(){
			show();
		});
		//下拉数据来源列表查询后台数据
		$("#datcmitoriid").change(function(){
			show();
		});
		
		//全选按钮功能
		$("#checkall").click(function() {  
            if (this.checked) {  
                $("input[name='rptids']:checkbox").each(function() { //遍历所有的name为prtid的 checkbox  
                            $(this).attr("checked", true);  
                        })  
            } else {   //反之 取消全选   
                $("input[name='rptids']:checkbox").each(function() { //遍历所有的name为prtid的 checkbox  
                            $(this).attr("checked", false);  
                        })  
            }  
        });  
		show();
		countFlow();
	});

	//时间排序功能
	function orderByTime(orderby){
		$("#moddatOrdByid").val(orderby);
		show();
	}
	

	//初始化和查询反欺诈云报告列表信息
	function show(){
		var moddatOrdBy = $("#moddatOrdByid").val();
		var pageNoStr   = $("#pageNoStr").val();
		var prsnnam		= $("#prsnnam").val();
		var prsncod	 	= $("#prsncod").val();
		var rptid		= $("#rptid").val();
		var rptmoddtefrom = $("#rptmoddtefrom").val();
		var rptmoddteto = $("#rptmoddteto").val();
		var rpttyp  = $("#reportType").val();
		var datcmitori = $("#datcmitoriid").val();
		var data = {fraudObjStr : JSON.stringify({
					moddatOrdBy : moddatOrdBy,
					pageNoStr	: pageNoStr,
					prsnnam		: prsnnam,
					prsncod		: prsncod,
					rptid		: rptid,
					rptmoddtefrom : rptmoddtefrom,
					rptmoddteto	: rptmoddteto,
					rpttyp		: rpttyp,
					datcmitori	: datcmitori
					})}
	    $.ajax({
	        url: ctx+'/fraud_findListByConditions.action',
	        type: 'post',
	        async : false,
			dataType : "json",
	        data: data,
			beforeSend : function(XMLHttpRequest) {
				XMLHttpRequest.setRequestHeader("RequestType", "ajax");
			},
	        success: function(data,textStatus,xhr) {
	        	var datalist = JSON.parse(data.fraudObjStr);
	        	data = datalist.list;
	        	$("#tab").html('');
	        	$("#kkpager").html("");
	        	if (data != "") {
	        		if (data != null) {
	    		        for(var i=0;i<data.length;i++){
	    		        	var tr=$("<tr></tr>");
	    		        	var tdselect = $('<td></td>').appendTo(tr);
	    		        	$('<input />', {
	    		        			type: 'checkbox',
	    		        			id: 'cb'+data[i].rptid,
	    		        			name:'rptids', 
	    		        			value: data[i].rptid}).appendTo(tdselect);
	    		        	$('<td>' +data[i].rptmoddte+'&nbsp;'+data[i].rptmodtim +'</td>').appendTo(tr);
	    		        	$('<td>' +data[i].rptid +'</td>').appendTo(tr);
	    		        	$('<td>'+data[i].prsnnam+'</td>').appendTo(tr);
	    		        	//身份证展示
	    		        	var prsncod = data[i].prsncod;
	    		        	if(prsncod.length > 14){
	    		        		var len = prsncod.length;
	    		        		var prsncodTemp = prsncod.substring(0,3) + "***********" + prsncod.substring(len-4);
	    		        		$('<td>'+prsncodTemp+'</td>').appendTo(tr)
	    		        	}else{
	    		        		$('<td>'+prsncod+'</td>').appendTo(tr)
	    		        	}
	    		        	
	    		        	$('<td>'+data[i].rptnam+'</td>').appendTo(tr);
	    		        	var datcmitori = data[i].datcmitori;
	    		        	if ('1' == datcmitori) {
								$('<td>平台提交</td>').appendTo(tr);
							} else if ('2' == datcmitori) {
								$('<td>底层接口</td>').appendTo(tr);
							} else if ('3' == datcmitori) {
								$('<td>微信</td>').appendTo(tr);
							} else {
								$('<td>APP</td>').appendTo(tr);
							}
	    		        	var href = "href=javascript:querydetail('"+data[i].rptid+"');";
	    		        	var buttonHtml = "";
	    		        	if(rptstatus == data[i].rptsta){
	    		        		buttonHtml +='<a '+href+' >查看</a>&nbsp;&nbsp;&nbsp;&nbsp;';

	    		        	} 
	    		        	var TransferHref = "href=\""+ctx+"/sinoway/fad/fadTransfer.jsp?rptid="+data[i].rptid+"&prsnnam="+data[i].prsnnam+"&prdcod="+data[i].prdcod+"&reqaddr="+data[i].reqadrr+"&rtpadrr="+data[i].rtpadrr+"&prsncod="+data[i].prsncod+"\"";
	    		        	if(data[i].rpttyp != personal){
		    		        	if(rptstatus == data[i].rptsta){
		    		        		buttonHtml +='<a '+TransferHref+' target="right">报告流转</a>';
		    		        	}
	    		        	}

	    		        	$('<td>'+buttonHtml+'</td>').appendTo(tr);
	    		        	tr.appendTo($("#tab"));
	    		        }
	    		        var Ptr = $("#tab > tr");
	    		        for (i=1;i<Ptr.length+1;i++) { 
	    		        	Ptr[i-1].style.backgroundColor = (i%2>0)?"#eee":"#fff"; 
	    		        }
	    		        bindChangeColor();
	    		        //分页代码
	    		        var pageStr = xhr.getResponseHeader("Content-Range");
	    		        var pageObj = pageStr.split("-");
				    	if(data.length == 0){
				    		kkpager.html("");
				    	}else{
				    		kkpager.generPageHtml({
				    			pno : pageObj[0],
				    			//总页码
				    			total : pageObj[1],
				    			//总数据条数
				    			totalRecords : pageObj[2],
				    			isShowTotalPage 	: false, //是否显示总页数
				    			isShowCurrPage		: false,//是否显示当前页
				    			isShowTotalRecords 	: false, //是否显示总记录数
				    			isGoPage 			: false,	//是否显示页码跳转输入框
				    			mode : 'click',//默认值是link，可选link或者click
				    			click : function(n){
				    				// do something
				    				//手动选中按钮
				    				selectPage(n);
				    				return false;
				    			}
				    		},true);
				    	}
	    			}
				}
	        	/*//屏幕总高度
	        	var screenHeight = $(document).height();
	        	// 页面框架总高度
	        	var frameHeight = $("#mainframe",window.parent.parent.document).height();
	        	//左边框架高度
	        	var leftHeight = $("#secondMenu",window.parent.left.document).height();
	        	//右边框架高度
	        	var rightHeight = $("#body1").height();
	        	
	        	if(rightHeight<leftHeight)
	        		$("#body1").height(leftHeight);//设置右边框架高度
	        	else {
	        		//设置主页面框架高度
		        	$("#mainframe",window.parent.parent.document).height(rightHeight+360);
		            //设置左边框架高度
		        	$("#secondMenu",window.parent.left.document).height(rightHeight);
	        	}*/
	         
	         },
	         error : function(XMLHttpRequest, textStatus, errorThrown) {
	 			alert("加载信息erro:" + textStatus + errorThrown);
	 		 },
	 		 complete : function(XMLHttpRequest, textStatus) {
	 		 }
		});
	    //changeHeight();
	    
	}
	
	//分页查询
	function selectPage(n){
		$("#pageNoStr").val(n);
		show();
	}
	
    function querydetail(rptid){
    	window.open('fadDetail.jsp?rptid="'+rptid+'"', '报告查看', 'height="100%", width="100%", top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')  
    }
    //新增反欺诈报告页面
    function deleteFraudRpt(){
    	//获取选中报告的值
    	var selectedItems = new Array();   
    	$("input[name='rptids']:checked").each(function() {selectedItems.push($(this).val());});

    	if (selectedItems.length == 0) {
	    		alert("请选择要删除的报告");   
	    		return false;
    	} 
    	var data = {fraudObjStr : JSON.stringify({
    		rptids : selectedItems.join(',')
			})}
    	
    	if(confirm("确定要删除报告？")){
   	    	$.ajax({
   		        url: ctx+'/fraud_deleteByRptids.action',
   		        type: 'post',
   		        async : true,
   				dataType : "json",
   		        data: data,
   				beforeSend : function(XMLHttpRequest) {
   					XMLHttpRequest.setRequestHeader("RequestType", "ajax");
   				},
   		        success: function(data,status, xhr) {
   		        	var datalist = JSON.parse(data.fraudObjStr);
   		        	data = datalist.retMsg;
   		        	if (data != "") {
   						if (data != null) {
   							if(data == 'normal'){
   								$('#queryForm')[0].reset();
   								//刷新列表
   								show();  
   							}
   						}
   					}
   		         },
   		         error : function(XMLHttpRequest, textStatus, errorThrown) {
   		 			alert("加载信息erro:" + textStatus + errorThrown);
   		 		 },
   		 		 complete : function(XMLHttpRequest, textStatus) {
   		 		 }
   			});
   	 }else{
   		return false;
   	 }
    	
    };

   function fadQuery() {
	  $("a[id=first]",window.parent.left.document).removeClass("hactive");
	  $("a[id!=first]",window.parent.left.document).addClass("hactive");
	  location.href=ctx +"/sinoway/fad/addFad.jsp";
   }
   /**
    * 统计流量
    */
   function countFlow(){
	  // var versionNumberAf = window.parent.bottom.$("#versionNumberAf").html();//反欺诈云底部的流量统计版本号
		$.ajax({
			url : ctx+"/hpCache_antiFraudFlowStatistics.action",
			type : "post",
			data:"",
			//data : {versionNumber:versionNumberAf},
			beforeSend : function(XMLHttpRequest) {
				XMLHttpRequest.setRequestHeader("RequestType", "ajax");
			},
			async : true,
			success : function(data, textStatus) {
				if (data != null) {
					var obj = JSON.parse(data);
					var porvingTerraceFlow = obj.data.porvingTerraceFlow;
					var individualAbnormalFlow = obj.data.individualAbnormalFlow;
				//	var versionNub =obj.data.versionNo;
					 window.parent.bottom.$("#porvingTerraceFlow").html(porvingTerraceFlow);
					 window.parent.bottom.$("#individualAbnormalFlow").html(individualAbnormalFlow);
					// window.parent.bottom.$("#versionNumberAf").html(versionNub);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("加载信息erro:" + textStatus + errorThrown);
			},
			complete : function(XMLHttpRequest, textStatus) {
			}
		});
	  
	 
   }