var lengthMap = {};
/**
 * 页面初始化生产表格数据
 * @param tdId
 * @param appcod
 */
function initTable(tdId,appcod){
		var data = {frontObjStr : JSON.stringify({appcod:appcod})};
		$.ajax({
			url : "/windforce/findCurUserPrdsAction.action",
			type : "post",
			data : data,
			beforeSend : function(XMLHttpRequest) {
				XMLHttpRequest.setRequestHeader("RequestType", "ajax");
			},
			async : false,
			dataType : "json",
			success : function(data, textStatus) {
				var rowsData = null;
				if (data != "") {
					if (data.frontObjStr != null) {
						var ret = JSON.parse(data.frontObjStr);
						rowsData = ret.obj;
						//console.log(rowsData);
						var showStr = "";
						for(var i=0;i<rowsData.length;i++){
							if(rowsData[i].isdefult == '1'){
								var dispStr = "";
								var length = 0;
								var maxLength =0;
								for(var j=0;j<rowsData[i].trns.length;j++){
									if(j % 2 !=0){
										dispStr += rowsData[i].trns[j].trnnam +"<br>";
										length +=rowsData[i].trns[j].trnnam.length;
										if(length > maxLength){
											maxLength = length;
										}
										lengthMap[rowsData[i].prdcod]=maxLength;
									}else{
										length = 0;
										dispStr += rowsData[i].trns[j].trnnam +"&nbsp;&nbsp;&nbsp;";
									    length +=rowsData[i].trns[j].trnnam.length + 3;
									    if(length > maxLength){
											maxLength = length;
										}
										lengthMap[rowsData[i].prdcod]=maxLength;
									}
									//dispStr +="<tr align='center'><td>"+rowsData[i].trns[j].trnnam +"</td></tr>";
								}
								showStr +="<li id='"+rowsData[i].prdcod+"li'>" +
										"<input type='checkbox' class='prdcod' value='" +rowsData[i].prdcod+"'>"+
										"<span class='"+tdId+"' id='"+rowsData[i].prdcod+"'>"+rowsData[i].prdnam+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>";
								 $("#dispDiv").append("<div class='innerDiv' id='"+rowsData[i].prdcod+"Div'>"+dispStr+"</div>") ;        
							}
						}
						for(var i=0;i<rowsData.length;i++){
							if(rowsData[i].isdefult != '1'){
								var dispStr = "";
								var length = 0;
								var maxLength =0;
								for(var j=0;j<rowsData[i].trns.length;j++){
									if(j % 2 !=0){
										dispStr += rowsData[i].trns[j].trnnam +"<br>";
										length +=rowsData[i].trns[j].trnnam.length;
										if(length > maxLength){
											maxLength = length;
										}
										lengthMap[rowsData[i].prdcod]=maxLength;
									}else{
										length = 0;
										dispStr += rowsData[i].trns[j].trnnam +"&nbsp;&nbsp;&nbsp;";
									    length +=rowsData[i].trns[j].trnnam.length + 3;
									    if(length > maxLength){
											maxLength = length;
										}
										lengthMap[rowsData[i].prdcod]=maxLength;
									}
									//dispStr +="<tr align='center'><td>"+rowsData[i].trns[j].trnnam +"</td></tr>";
								}
								showStr +="<li id='"+rowsData[i].prdcod+"li'><input type='checkbox' class='prdcod' value='" +rowsData[i].prdcod+"'>"
								+"<span class='"+tdId+"' id='"+rowsData[i].prdcod+"'>"+rowsData[i].prdnam+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>";
								 $("#dispDiv").append("<div class='innerDiv' id='"+rowsData[i].prdcod+"Div'>"+dispStr+"</div>") ;            
							}
						}
						$("#"+tdId).html("<ul>"+showStr+"</ul>");
					}
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("加载信息erro:" + textStatus + errorThrown);
			},
			complete : function(XMLHttpRequest, textStatus) {
			}
		});
	}
/**
 * 显示团队下拉列表，并使id为orgId的机构默认选中
 */
function showTeams(orgId) {
	$("option").remove(".teamoption");
	$.ajax({
		url : "queryCurOrganize.action",
		type : "post",
		data : "",
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		success : function(data, textStatus) {
				if (data !=null) {
					var obj = JSON.parse(data);
					if(obj != null){		
						for(var i=0;i<obj.length;i++){
							if(obj[i].sid == orgId){
								$("#team").append("<option class='teamoption' value='" +obj[i].sid+"' selected>"+obj[i].orgName +"</option>");
							}else{
								$("#team").append("<option  class='teamoption' value='" +obj[i].sid+"'>"+obj[i].orgName +"</option>");
							}
						}
						$("#team").append("<option  class='teamoption' value='新建'>新建团队</option>");
					}
				}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("加载信息erro:" + textStatus + errorThrown);
		},
		complete : function(XMLHttpRequest, textStatus) {
		}
	});
}
/**
 * 创建子账户，提交请求
 */
function add(){
	 var organizeinfo = $("select option:selected").val();
	 var frontObjStr = JSON.stringify(
             {peoplecode:$.trim($("#peoplecode").val()),prdcod:selectedItems.join(','),sta:'1'}
			);
	 var peopleStr = JSON.stringify({peopleCode:$.trim($("#peoplecode").val()),peopleName:$.trim($("#peoplename").val()),organizeInfo:organizeinfo});
    var data = {frontObjStr:frontObjStr,peopleStr:peopleStr};
	$.ajax({
		url : "setPermission.action",
		type : "post",
		data : data,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		success : function(data, textStatus) {
				if (data !=null) {
					if(data==1){
						alert("新增子账号成功!");
						window.location.href="sinoway/acc/accChildList.jsp";
					}
					if(data==0){
						alert("新增子账号失败!");
					}
					if(data==2){
						alert("核心连接失败，子账户新增失败！");
					}
				}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("加载信息erro:" + textStatus + errorThrown);
		},
		complete : function(XMLHttpRequest, textStatus) {
		}
	});
}
/**
 * 数据校验
 */
function check(){
	var reg = /^[a-zA-Z\u4e00-\u9fa5]{1,8}$/;//汉字和字母   
	var account =/^[\u4e00-\u9fa5_a-zA-Z0-9_]{1,10}$/;//数字和字母
	var peoplecode = $.trim($("#peoplecode").val());
	if(peoplecode.length == 0){
		alert("请输入使用人账号!")
		return ;
	}
	//校验新增账户号是否重复
	if(checkpcode()){
		alert('你输入的账号已存在');
		return;
	}
    peoplecode = $.trim($("#peoplecode").val());
    peoplename = $.trim($("#peoplename").val());
    selectedItems = new Array();   
	$("input[class=prdcod]:checked").each(function() {selectedItems.push($(this).val());});   
	if (selectedItems.length == 0) {
		alert("请选择产品!"); 
		return ;
	}else if(peoplecode.length == 0 || peoplename.length == 0){
    	alert("请输入用户信息!");
    	return;
    }else if(peoplecode.length >10){
    	alert("用户账号不超过10位!")
    	return;
    }else if(!reg.test(peoplename)){
    	alert("用户名为8位的汉字或字母!")
    	return;
    }else if(!account.test(peoplecode)){
    	alert("账号必须为数字和英文的组合!");
    	return;
    }
//    else if(/[\u4e00-\u9fa5]/g.test(peoplecode)){
//    	alert("账号必须为数字和英文的组合!");
//    	return;
//    }
    else if($("select option:selected").val() == 0 ||$("select option:selected").val() == '新建'){
    	alert("请选择团队!");
    }else{
        add();
    }
}
/**
 * 显示新建团队输入框
 */
function addTeam(){
	if($("select option:selected").val() == '新建'){
		$("#newteam").css('display','inline');
		$("#newTeam").val("");
	}else{
		$("#newteam").css('display','none');
	}
}
/**
 * 显示详情
 */
function showDisp(prdcod){
	$("#"+prdcod + "Div").css("visibility","visible");
	$("#"+prdcod + "Div").css("width",lengthMap[prdcod]*12);
	$("#dispDiv").css("width",lengthMap[prdcod]*12);
	$("#dispDiv").css("visibility","visible");
	$("#dispDiv").css("left",$("li[id="+prdcod+"li]").offset().left);
	$("#dispDiv").css("top",$("li[id="+prdcod+"li]").offset().top + 20);
	
}
/**
 * 隐藏详情
 */
function hideDisp(prdcod){
	$("#"+prdcod + "Div").css("visibility","hidden");
	$("#dispDiv").css("visibility","hidden");
}
/**
 * 绑定鼠标移入移出事件
 */
function bindActive(tdId){
	$("span[class="+tdId+"]").bind({
		mouseenter : function(e){
			showDisp($(this).attr("id"));
		},
		mouseleave : function(e){
			hideDisp($(this).attr("id"));
		}
	});
	
}
/**
 * 解除绑定鼠标移入移出事件
 */
function unbindActive(tdId){
	$("span[class="+tdId+"]").unbind("mouseenter");
	$("span[class="+tdId+"]").unbind("mouseleave");
}
/**
 * 页面初始化
 */
$(document).ready(function() {
		initTable("rpt","001");
		initTable("fad","002");
		initTable("wrn","003");
		showTeams("");
		//点击显示详情多选框，触发绑定或解除绑定事件
		$("input:checkbox[class=disp]").click(function(){
			if($(this).attr("checked")){
				bindActive($(this).val());
			}else{
				unbindActive($(this).val());
			}
		});
});
/**
 * 新增团队
 */
function saveTame(){
	var reg = /^[0-9a-zA-Z\u4e00-\u9fa5]{1,10}$/;//中文/字母/数字
	var addNewTeam = $.trim($("#newTeam").val());
    if(checkTeamfalg()){
      alert('您输入的团队名称重复');
      return ;
    } else if(addNewTeam.length == 0){
		alert("请输入团队名称!")
		return;
	}else if(!reg.test(addNewTeam)){
		alert("请输入10位以内的汉字、字母、数字")
		return;
	} else{	
		$.ajax({
			url : "saveNewTeam.action",
			type : "post",
			data :{orgName:addNewTeam},
			beforeSend : function(XMLHttpRequest) {
				XMLHttpRequest.setRequestHeader("RequestType", "ajax");
			},
			async : true,
			success : function(data,textStatus) {
				if(data==2){
					alert("核心接口连接失败，新增团队失败！");
				}else if(data==0){
					alert("新增团队失败!");
				}else {
					$("#newteam").css('display','none');
					var map = JSON.parse(data);
					showTeams(map["orgId"]);
					alert("新增团队成功！");
				}
//				if (data !=0) {
//					$("#newteam").css('display','none');
//					var map = JSON.parse(data);
//					showTeams(map["orgId"]);
//					alert("新增团队成功！");
//				}else if(data==0){
//					alert("新增团队失败!");
//				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("加载信息erro:" + textStatus + errorThrown);
			},
			complete : function(XMLHttpRequest, textStatus) {
			}
		});
	}
}


/**
 * 校验使用人账号是否存在
 */
function checkpcode(){
	var peoplecode = $.trim($("#peoplecode").val());
	var contain = false;
	if(peoplecode.length != 0){
			
		$.ajax({
			url : "checkPeopleRepeat.action",
			type : "post",
			data :{peoplecode:peoplecode},
			beforeSend : function(XMLHttpRequest) {
				XMLHttpRequest.setRequestHeader("RequestType", "ajax");
			},
			async : false,
			success : function(data,status, xhr) {
					var obj = JSON.parse(data);
					if(obj == '0'){
					  contain = true;
					}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("加载信息erro:" + textStatus + errorThrown);
			},
			complete : function(XMLHttpRequest, textStatus) {
			}
		});
	}
	return contain;
}

/**
 * 校验团队名称是否重重
 * @returns {Boolean}
 */
function checkTeamfalg (){
	var newTeam = $.trim($("#newTeam").val());
	var contain = false;
	if(newTeam.length == 0){
		alert("请输入团队名称!")
	}else{	
		$.ajax({
			url : "checkTeamRepeat.action",
			type : "post",
			data :{newTeam:newTeam},
			beforeSend : function(XMLHttpRequest) {
				XMLHttpRequest.setRequestHeader("RequestType", "ajax");
			},
			async : false,
			success : function(data,status, xhr) {
					var obj = JSON.parse(data);
					if(obj == '0'){
					  contain = true;
					}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("加载信息erro:" + textStatus + errorThrown);
			},
			complete : function(XMLHttpRequest, textStatus) {
			}
		});
	}
	return contain;
}