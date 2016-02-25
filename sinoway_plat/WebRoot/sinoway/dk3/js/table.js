//@ sourceURL=table.js
var prds = null; //每一个模块下的产品数组
var defaultPrd = null;//每一个模块下的固定产品
var isStmIndex = null;//是否是策略管理首页
var peoples = null;//子账户数组
var peopleSelect = [];//右侧展现的人员账号数组
var teams = null;//团队数组
var updatePrd = null;//被修改产品
var delPrdcod = null;//被删除产品产品编码
var searchPeo = true;//查询人员标识
var isSearch = false;//是否查询标识
var showPrds = [];//datatable数据源数组
/**
 * 控制应用于人员或团队时左右两侧高度一致
 */
function controlHeight(){
	var leftHeight = $("#left").height();
	var rightHeight =  $("#right-content").height();
	if(leftHeight> rightHeight){
		$("#right-content").height(leftHeight);
	}else{
		$("#left").height(rightHeight);
	}
}
/**
 * 封装datatable数据源
 * @param index  prds数组下标
 */
function dataSource(index){
	var showPrd = [];
	showPrd.push(prds[index].cretday);
	showPrd.push(prds[index].prdnam);
	var trnStr = "";
	for(var j = 0;j<prds[index].trns.length;j++){
		trnStr += "<span>"+prds[index].trns[j].trnnam + "</span>&nbsp;";
	}
	showPrd.push(trnStr);
	var showStr = "";
	if(prds[index].isdefult == '1'){
		showStr=" ";
	}else{
		showStr = "<a href='javascript:void(0)' onclick='preUpdatePrd("+prds[index].prdcod+")'>修改</a>&nbsp;&nbsp;&nbsp;" +
		"<a href='javascript:void(0);' data-toggle='modal' data-target='#myModal1' onclick='delPrd("+prds[index].prdcod+")'>删除</a>";
	}
	showPrd.push(showStr);
	showPrds.push(showPrd);
}
/**
 * 调用datatable
 */
function dataTable(){
	var table = $('#dataTable').DataTable( {
		"processing" : false,	//隐藏加载提示,自行处理
		"searching" : false,	//禁用表格内搜索
		"lengthChange": false,	//是否允许改变每页显示数目
		"paging":false,//关闭分页
		"ordering": false,//关闭排序
		"data":showPrds
    } );
}
/**
 * 查询人员或团队
 */
function searchPeoOrTeam(){
	isSearch = true;
	if(searchPeo){
		showPeoples();
	}else{
		showTeams();
	}
}
/**
 * 点击个人按钮
 */
$("#peoButton").click(function(){
	isSearch = false;
	searchPeo = true;
	showPeoples();
});
/**
 * 点击团队按钮
 */
$("#teamButton").click(function(){
	isSearch = false;
	searchPeo = false;
	showTeams();
});
/**
 * 删除产品
 * @param prdcod  要删除产品的产品编码
 */
function delPrd(prdcod){
	$("#delDiv").show();
	delPrdcod = prdcod;
}
/**
 * 点击确定删除按钮
 */
$("#delPrd").click(function(){
	var data = {frontObjStr : JSON.stringify({prdcod:delPrdcod,appcod:appcod})};
	$.ajax({
		url : ctx + "/delPrdAction.action",
		type : "post",
		data : data,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		dataType : "json",
		success : function(data, textStatus) {
			if (data != "") {
			  var ret = JSON.parse(data.frontObjStr);
			  var errcod = ret.errcod;					
				if (data.frontObjStr != null) {
				  if(errcod == '1'){
					alert('删除策略成功');
					//如果处于修改状态，并且修改的产品正是已经删除的产品
					if(prdcod == updatePrd.prdcod){
						  //关闭新建或修改产品框
						  $("#firststep").hide();
						  $("#nextstep").hide();
					}
				 }else{
					alert('删除策略失败\n'+ret.errmsg);
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
	//关闭确认删除框
	$("#delDiv").hide();
});
/**
 * 点击关闭删除按钮
 */
$("#closeDel").click(function(){
	$("#delDiv").hide();
});
/**
 * 预修改产品
 * @param prdcod  要修改产品的产品编码
 */
function preUpdatePrd(prdcod){
	if($("#nextstep").is(":visible")||$("#firststep").is(":visible")){
		alert("请先保存或取消!");
	}else{
		//清空右侧展现的人员账号数组
		peopleSelect = [];
		//找到要修改的产品
		for(var i=0;i<prds.length;i++){
			if(prdcod == prds[i].prdcod){
				updatePrd = prds[i];
				break;
			}
		}
		//显示当前的产品名称
		$("#prdnam").val(updatePrd.prdnam);
		showTrns();
		$("#firststep").show();
		
	}
}
/**
 * 新增或修改策略
 */
function saveOrUpdatePrd(){
	var prdnam = $("#prdnam").val();
	var trns = [];
	var trn ;
	//获取选择的原交易信息
	$("input[class=trn]:checked").each(function(){
		trn = {trncod:'',prddid:'',trnnam:'',trnnature:''};
		trn.trncod = this.id;
		trn.prddid = $(this).val();
		trn.trnnam = this.name;
		trn.trnnature = this.alt;
		trns.push(trn);
	});
	//确定产品类型
	 var prdtyp = "003";
	 for(var i =0;i<trns.length;i++){
		 if(trns[i].trnnature == '003'){
			 prdtyp = "002";
			 break;
		 }
	 }
	 //获取使用人员
	 var prdUsrs = [];
	 $("input[class=people]:checked").each(function(){
		 var people = {peopleCode:'',peopleName:'',usrId:'',organizeInfo:'',orgName:''};
		 people.peopleCode = this.id;
		 people.usrId = this.name;
		 people.organizeInfo =  $(this).val();
		 people.orgName = $(this).attr("alt");
		 prdUsrs.push(people);
	 });
	 //将请求数据传到后台
	 var updatePrdcod = null;
	 if(null != updatePrd){
		 updatePrdcod = updatePrd.prdcod;
	 }
	 var data = {frontObjStr : JSON.stringify({appcod:appcod,prdcod:updatePrdcod,prdnam:prdnam,trns:trns,peoples:prdUsrs,prdtyp:prdtyp})};
	  $.ajax({
		  url : ctx +　"/saveOrUpdatePrdAction.action",
		  type : "post",
		  data : data,
		  beforeSend : function(XMLHttpRequest) {
			  XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		  },
		  async : true,
		  dataType : "json",
		  success : function(data, textStatus) {
			  var prds = null;
			  if (data != "") {
				  var ret = JSON.parse(data.frontObjStr);
				  var errcod = ret.errcod;
				  if (data.frontObjStr != null) {
					  if(null == updatePrd){
						  //保存产品
						  if(errcod == '1'){
								alert('新增产品策略成功'); 
								//TODO  刷新页面
						  }else{
								alert('新增产品策略失败\n'+ret.errmsg);  
						  }
					  }else{
						  //修改产品
						 if(errcod == '1'){
							 alert('修改产品策略成功'); 
						  //TODO  刷新页面
						 }else{
							 alert('修改产品策略失败\n'+ret.errmsg);  
						 }
						 updatePrd =null;
					  }
				  }
			  }
			
		  },
		  error : function(XMLHttpRequest, textStatus, errorThrown) {
			  alert("加载信息erro:" + textStatus + errorThrown);
			  updatePrd =null;
		  },
		  complete : function(XMLHttpRequest, textStatus) {
		  }
	  });
	  //关闭新建或修改产品框
	  $("#firststep").hide();
	  $("#nextstep").hide();
}
/**
 * 展示原交易信息
 */
function showTrns(){
	var veriDivbody = "";
	var exceDivbody = "";
	for(var j=0;j<defaultPrd.trns.length;j++){
		 if(defaultPrd.trns[j].trnnature == '002'){
			  //是否包含验证模块
			  var veriTrnFlag = true;
			  veriDivbody += "<label class='col-md-4 col-sm-4 font-s-14 checkbox-padding'><input type='checkbox' class='trn' ";
			  //修改产品
			  if(null!=updatePrd){
				  for(var i=0;i<updatePrd.trns.length;i++){
					  if(updatePrd.trns[i].trncod == defaultPrd.trns[j].trncod){
						  veriDivbody += " checked ";
						  break;
					  }
				  }
			  }
			  veriDivbody += "id='" +defaultPrd.trns[j].trncod+"' name='"+defaultPrd.trns[j].trnnam+"' value = '"+defaultPrd.trns[j].prddid+"' alt='"+defaultPrd.trns[j].trnnature+"'>" 
				+defaultPrd.trns[j].trnnam +"</label>";
		  }
		  if(defaultPrd.trns[j].trnnature == '003'){
			//是否包含异常查询模块
			 var exceTrnFlag = true;
			 exceDivbody += "<label class='col-md-4 col-sm-4 font-s-14 checkbox-padding'><input type='checkbox' class='trn' ";
			 //修改产品
			 if(null!=updatePrd){
				  for(var i=0;i<updatePrd.trns.length;i++){
					  if(updatePrd.trns[i].trncod == defaultPrd.trns[j].trncod){
						  exceDivbody += " checked ";
						  break;
					  }
				  }
			  }
			 exceDivbody += "id='" +defaultPrd.trns[j].trncod+"' name='"+defaultPrd.trns[j].trnnam+"' value = '"+defaultPrd.trns[j].prddid+"' alt='"+defaultPrd.trns[j].trnnature+"'>" 
			 +defaultPrd.trns[j].trnnam +"</label>";
		  }
	}
	if(veriTrnFlag){
		$("#veriNature .checkbox").html(veriDivbody);
	}else{
		$("#veriNature").hide();
	}
	if(exceTrnFlag){
		$("#exceNature .checkbox").html(exceDivbody);
	}else{
		$("#exceNature").hide();
	}
	
}
/**
 * 页面初始化生成表格数据
 * @param divId  模块divId
 * @param appcod 应用码
 */
function initTable(divId,appcod){
		var data = {frontObjStr : JSON.stringify({appcod:appcod})};
		$.ajax({
			url : ctx +"/findCurUserPrdsAction.action",
			type : "post",
			data : data,
			beforeSend : function(XMLHttpRequest) {
				XMLHttpRequest.setRequestHeader("RequestType", "ajax");
			},
			async : false,
			dataType : "json",
			success : function(data, textStatus) {
				if (data != "") {
					if (data.frontObjStr != null) {
						var ret = JSON.parse(data.frontObjStr);
						prds = ret.obj;
						//判断是否是策略管理首页
						if(isStmIndex){
							//固定模板在最前面
							for(var i=0;i<prds.length;i++){
								if("1"==prds[i].isdefult){
									addPrdDivWithData(divId,i);
									break;
								}
							}
							for(var i=0;i<prds.length;i++){
								if("1" !=prds[i].isdefult){
									addPrdDivWithData(divId,i);
								}
							}
							
						}else{	
							//清空showPrds和showPrd数组
							showPrds = [];
							for(var i=0;i<prds.length;i++){
								if("1"==prds[i].isdefult){
									defaultPrd = prds[i];
								}
								//封装dataTable数据源
								dataSource(i);
							}
						    //展现datatable
							dataTable();
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
	}
/**
 * 点击取消
 */
$(".cancel").click(function(){
    $("#firststep").hide();
    $("#nextstep").hide();
    
});
/**
 * 点击保存
 */
$("#saveOrUpdate").click(function(){
	saveOrUpdatePrd();
});
/**
 * 点击下一步
 */
$("#next").click(function(){
	//判断策略名称是否为空，是否选择原交易
	if(judgeTrns()){	
		$("#firststep").hide();
		$("#nextstep").show();
		//修改产品
		if(null!= updatePrd){
			var body = "";
			for(var i =0;i<updatePrd.po.length;i++){
				body += "<label class='col-md-4 col-sm-4 font-s-14 checkbox-padding'><input type='checkbox' checked class='people'" + 
				        " id='"+updatePrd.po[i].peopleCode+"' name = '"+updatePrd.po[i].usrId+ 
								"' value='"+updatePrd.po[i].organizeInfo+"' alt='"+updatePrd.po[i].orgName+"'>"
						+updatePrd.po[i].peopleName+"</label>";
				peopleSelect.push(updatePrd.po[i]);
			}
			$("#right-content").append(body);
		}
		showPeoples();
		showTeams();
	};
});
/**
 * 展现人员列表
 */
function showPeoples(){
	var url = null;
	var data = null;
	if(isSearch){
		var queryData = $("#queryData").val();
		url = ctx + "/fuzzyQueryChildren.action";
		data = {dataStr:queryData};
	}else{
		url = ctx +"/queryCurChildren.action";	
	}
	$.ajax({
		url :url,
		type : "post",
		data : data,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		success : function(data, textStatus) {
				if (data != null) {
					peoples = JSON.parse(data);
					var peoBody = "";
					if(peoples != null){
						for(var i=0;i<peoples.length;i++){
							peoBody += "<li class='list-group-item tabs'><a href='javascript:void(0)' " +
									"aria-controls='fengkong' data-toggle='tab' onclick='addPeople("+i +")'>";
							if(isSearch){
								peoBody += peoples[i].PEOPLENAME;
							}else{
								peoBody +=peoples[i].peopleName;
							}
							peoBody += "</a></li>";
						}
						$("#peoples").html(peoBody);
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
 * 展现团队列表
 */
function showTeams(){
	var queryData = $("#queryData").val();
	var data = null;
	if(isSearch){
		data = {dataStr:queryData};
	}
	$.ajax({
		url : ctx + "/queryCurOrganize.action",
		type : "post",
		data : data,
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		success : function(data, textStatus) {
				if (data !=null) {
					teams = JSON.parse(data);
					var teamBody = "";
					if(teams != null){
						for(var i=0;i<teams.length;i++){
							teamBody += "<li class='list-group-item tabs'><a href='javascript:void(0)' " +
							"aria-controls='fengkong' data-toggle='tab' onclick='addTeam("+i +")'>" + teams[i].orgName + "</a></li>";
						}
						$("#teams").html(teamBody);
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
 * 点击某一人员，加入已选择框中
 * @param index 人员数组下标
 */
function addPeople(index){
	//判断点击的人员是否已经在右侧出现过
	if("-1"==$.inArray(peoples[index].peopleCode,peopleSelect)){
		var body = "";
		body += "<label class='col-md-4 col-sm-4 font-s-14 checkbox-padding'><input type='checkbox' checked class='people'" + 
		        " id='"+peoples[index].peopleCode+"' name = '"+peoples[index].usrId+ 
						"' value='"+peoples[index].organizeInfo+"' alt='"+peoples[index].orgName+"'>"
				+peoples[index].peopleName+"</label>";
		$("#right-content").append(body);
		peopleSelect.push(peoples[index].peopleCode);
	}else{
		//若出现过，要使现在的状态是为选中
		var peopleBox = $("input[class=people]");
		for(var i = 0;i <peopleBox.length;i++){
			if(peopleBox[i].id == peoples[index].peopleCode){
				peopleBox[i].checked = true;
			}
			
		}
	}
}
/**
 * 点击某一团队，团队下所有人员加入已选择框中
 * @param index 团队数组下标
 */
function addTeam(index){
	$.ajax({
		url : ctx + "/getPeopleByOrgId.action",
		type : "post",
		data : {orgId:teams[index].sid},
		beforeSend : function(XMLHttpRequest) {
			XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		},
		async : true,
		success : function(data, textStatus) {
				if (data!= null) {
					var teamPeoples = JSON.parse(data);
					var body = "";
					if(teamPeoples != null){				
						for(var i=0;i<teamPeoples.length;i++){
							//判断点击的人员是否已经在右侧出现过
							if("-1"==$.inArray(teamPeoples[i].peopleCode,peopleSelect)){	
								body += "<label class='col-md-4 col-sm-4 font-s-14 checkbox-padding'><input type='checkbox' checked class='people' "+
								" id='"+teamPeoples[i].peopleCode+"' name = '"+teamPeoples[i].usrId+"' value='"+teamPeoples[i].organizeInfo+"' alt='"+teamPeoples[i].orgName+"'>"+teamPeoples[i].peopleName+"</label>";
								peopleSelect.push(teamPeoples[i].peopleCode);
							}else{
								//若出现过，要使现在的状态是为选中
								var peopleBox = $("input[class=people]");
								for(var j = 0;j <peopleBox.length;j++){
									if(peopleBox[j].id == teamPeoples[i].peopleCode){
										peopleBox[j].checked = true;
									}
								}
							}
						}
						$("#right-content").append(body);
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
 * 点击新建，展示原交易信息
 */
$("#newcreat").click(function(){
	if($("#nextstep").is(":visible")){
		alert("请先保存或取消!");
	}else{
		//清空策略名称输入框
		$("#prdnam").val("");
		//清空已选择框
		$("#right-content").html("");
		//清空右侧展现的人员账号数组
		peopleSelect = [];
		$("#firststep").show();
		//清空被修改产品
		updatePrd = null;
		showTrns();
	}
});
/**
 * 判断策略名称是否为空，是否选择原交易
 * @return 是否通过验证，进行下一步
 */
function judgeTrns(){
	  //获得产品名称,判断输入的产品名是否合法
	  var prdnam = $("#prdnam").val();
	  if(null == $.trim(prdnam) || "" == $.trim(prdnam)){
		  alert("请输入产品名称...");
		  return false;
	  }
	  var reg = /^[0-9a-zA-Z\u4e00-\u9fa5]{1,20}$/;//汉字、数字、字母
	  if(!reg.test(prdnam)){
		  alert("请输入产品名称位20位以内的汉字、数字、字母！");
		  return false;
	  }
	  //校验是否勾选交易码
	  var contain = false;
	  var trns = $(".trn");
	  for(var i=0;i<trns.length;i++){
		  if(trns[i].checked){
			  contain = true;
		  }
	  }
	  if(!contain){
		  alert("请勾选交易码!");
		  return false;
	  }else{
		  return true;
	  }
}