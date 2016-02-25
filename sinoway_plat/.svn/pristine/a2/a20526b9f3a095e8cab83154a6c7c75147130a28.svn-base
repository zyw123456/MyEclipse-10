var lengthMap = {};
/**
 * 绑定用户信息
 */
function bindPeopleMsg(frontObjStr,comStr,tdId){
	if(frontObjStr!=null && comStr!=null){
		var ret = JSON.parse(frontObjStr);
		rowsData = ret.obj;
		//console.log(rowsData);
		var comList = JSON.parse(comStr);
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
				}
				showStr +="<li  id='"+rowsData[i].prdcod+"li'><input type='checkbox' class='prdcod' disabled ";
				for(var k=0;k<comList.length;k++){
					if(comList[k].prdcod == rowsData[i].prdcod){
						showStr +="checked";
					}
				}
				showStr +="  value='" +rowsData[i].prdcod+"'><span class='"+tdId+"' id='"+rowsData[i].prdcod+"'>"+rowsData[i].prdnam+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>";
				 $("#dispDiv").append("<div class='innerDiv' id='"+rowsData[i].prdcod+"Div'>"+dispStr+"</div>") ;
				//$("#"+tdId+"Div").append("<div class='innerDiv' id='"+rowsData[i].prdcod+"Div'><table>"+dispStr+"</table></div>") ;        
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
				}
				showStr +="<li id='"+rowsData[i].prdcod+"li'><input type='checkbox' class='prdcod' disabled ";
				for(var k=0;k<comList.length;k++){
					if(comList[k].prdcod == rowsData[i].prdcod){
						showStr +="checked";
					}
				}
				showStr +="  value='" +rowsData[i].prdcod+"'><span class='"+tdId+"' id='"+rowsData[i].prdcod+"'>"+rowsData[i].prdnam+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>";
				 $("#dispDiv").append("<div class='innerDiv' id='"+rowsData[i].prdcod+"Div'>"+dispStr+"</div>") ;
				//$("#"+tdId+"Div").append("<div class='innerDiv' id='"+rowsData[i].prdcod+"Div'><table>"+dispStr+"</table></div>") ;            
			}
		}
		$("#"+tdId).html("<ul>"+showStr+"</ul>");
		
	}
}
/**
 * 页面初始化生产表格数据
 * @param tableId
 * @param appcod
 */
function initTable(tdId,appcod){
		var data = {frontObjStr : JSON.stringify({appcod:appcod})};
		var frontObjStr = null;
		var comStr = null;
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
				 rowsData = null;
				if (data != "") {
					if (data.frontObjStr != null) {
						frontObjStr =data.frontObjStr;
					}
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("加载信息erro:" + textStatus + errorThrown);
			},
			complete : function(XMLHttpRequest, textStatus) {
			}
		});
		$.ajax({
			url : "getPrdsByPeocode.action",
			type : "post",
			data : {peoId:$("#peoId").val(),dataStr:"look"},
			beforeSend : function(XMLHttpRequest) {
				XMLHttpRequest.setRequestHeader("RequestType", "ajax");
			},
			async : false,
			success : function(data, textStatus) {
				if (data != "") {
					comStr = data;
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("加载信息erro:" + textStatus + errorThrown);
			},
			complete : function(XMLHttpRequest, textStatus) {
			}
		});
		bindPeopleMsg(frontObjStr,comStr,tdId);
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
		//点击显示详情多选框，触发绑定或解除绑定事件
		$("input:checkbox[class=disp]").click(function(){
			if($(this).attr("checked")){
				bindActive($(this).val());
			}else{
				unbindActive($(this).val());
			}
		});
});
