var prds = null; //全局产品信息,已封装关联对象属性
var updatePrdcod = null;//当前操作的产品编码
var defaultPrd = null; //默认产品
var prdusrs = [];
var usrcodes = [];
var oldUsrcodes = [];
function addTr(tab, row, trHtml){
     //获取table最后一行 $("#tab tr:last")
     //获取table第一行 $("#tab tr").eq(0)
     //获取table倒数第二行 $("#tab tr").eq(-2)
     var $tr=$("#"+tab+" tr").eq(row);
     if($tr.size()==0){
       // alert("指定的table id或行数不存在！");
        return;
     }
     $tr.after(trHtml);
  }
   
  function delTr(id){
     //获取选中的复选框，然后循环遍历删除
     var ckbs=$("#"+id);
     if(ckbs.size()==0){
        alert("要删除指定行，需选中要删除的行！");
        return;
     }
     ckbs.each(function(){
    	 $(this).parent().remove();
     });
  }
   
  /**
   * 向展示产品的table中插入数据
   * @param tab
   * @param row
   * @param data
   */
  function addPrdTrWithData(tab, row ,data){
	  var trHtml="<tr align='center'><td width='18%'>"+data.row1+"</td>"+
	  "<td width='18%'>"+data.row2+"</td>"+
	  "<td width='30%'>"+data.row3+"</td>"+
	  "<td width='18%' style='display: none;'>"+data.row4+"</td>";
	  if(typeof(isIndexJsp)=="undefined"){
		  trHtml += "<td width='10%' id='"+data.row5+"'>";
	  }
	  if("1" == data.row6){
		  //当为默认策略时,不显示删除,修改按钮
		  trHtml += "</tr>"
	  }else{
		 trHtml += "<input type='button' value='修改' onclick='updatePrd(this.parentNode.id)'/>  <input type='button' value='删除' onclick = 'delPrd(this.parentNode.id)'/></td></tr>";
	  }
	  addTr(tab, row, trHtml);
  }
   
  function delPrd(prdcod){
	  if(!confirm("你确定要删除该产品吗?")){
		  return;
	  }
	  var data = {frontObjStr : JSON.stringify({prdcod:prdcod,appcod:appcod})};
		$.ajax({
			url : "/windforce/delPrdAction.action",
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
						
						
						delTr(prdcod);
						updatePrdcod = null;
						//关闭表格
						//$("#closeTab").click();
						//$("#closePoTab").click();
						//移除全局变量中该产品信息
						for(var i=0;i<prds.length;i++){
							if(prdcod == prds[i].prdcod){
								prds.splice(i,1);
								prdcod = null;
								break;
							}
						}
						alert('删除策略成功');
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
  }
  
  /**
   * 修改产品
   * @param prdcod
   */
  function updatePrd(prdcod){
	  //打开table
	  $("#addNewPrd").click();
	  //清除poTab内的人员信息
	 
	  updatePrdcod = prdcod;
	  var prdnam = null;
	  var trns = null;
	  var po = null;
	  for(var i=0;i<prds.length;i++){
		  if(prdcod == prds[i].prdcod){
			  prdnam = prds[i].prdnam;
			  trns = prds[i].trns;
			  po = prds[i].po;
			  break;
		  }
	  }
	  //显示当前的产品名称
	  $("#prdnam").val(prdnam);
	  if(null == trns){
		  
	  }else{
		  $(".trn").each(function(){
			  for(var i=0;i<trns.length;i++){
				  if(this.id == trns[i].trncod){
					  //点击修改时,页面元素的prddid随之改变
					  $(this).val(trns[i].prddid);
					  $(this).attr("checked",true);
				  }
			  }
		  });
	  }
	  for(var i=0;i<po.length;i++){
		  oldUsrcodes.push(po[i].peopleCode);
		  prdusrs.push(po[i]);
	  }
	  if(null == trns){
		  
	  }else{
		  //向交易码表格中填入监测数据
		  if(appcod == "003"){
			  for(var i=0;i<trns.length;i++){
				  $("#"+trns[i].trncod+"_dayexpcnt").val(trns[i].dayexpcnt);
				  $("#"+trns[i].trncod+"_monexpcnt").val(trns[i].monexpcnt);
				  $("#"+trns[i].trncod+"_mon3expcnt").val(trns[i].mon3expcnt);
			  }
		  }
	  }
  }
  
  //传入数据,返回结构数组;每一行对应什么内容
  function parse(rowsdata,appcod){
  	var trns = rowsdata.trns;
  	var trnInfo = "";
  	var appendInfo = "";
  	for(var i=0;i<trns.length;i++){
  		if(appcod == "003"){
  			if(null == trns[i].dayexpcnt){
  				trns[i].dayexpcnt = 0;
  			}
  			if(null == trns[i].monexpcnt){
  				trns[i].monexpcnt = 0;
  			}
  			appendInfo = "日累计 "+trns[i].dayexpcnt+" 条,"+"月累计 "+trns[i].monexpcnt+" 条  ";
  		}
  		trnInfo+=trns[i].trnnam+" "+appendInfo+"<br/>";
  	}
  	//对团队人员进行遍历
  	var po = rowsdata.po;
  	var row4 = "";
  	for(var i=0;i<po.length;i++){
  		row4 += (po[i].orgName+" "+po[i].peopleName)+"<br/>";
  	}
  	var parseData = {"row1":rowsdata.cretday,"row2":rowsdata.prdnam,"row3":trnInfo,"row4":row4,"row5":rowsdata.prdcod,"row6":rowsdata.isdefult};
  	return parseData;
  }
  
  /**
   * 页面初始化生成表格数据
   * @param tableId
   * @param appcod
   */
  function initTable(tableId,appcod){
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
						//TODO 进行日期排序
						prds = ret.obj;
						for(var i=0;i<rowsData.length;i++){
							if("1"==rowsData[i].isdefult){
								defaultPrd = rowsData[i];
							}
							addPrdTrWithData(tableId,-2,parse(rowsData[i],appcod));
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
  * 报告,反欺诈新建点击时生成的表格
  * @param tableId
  */
  function initModulTable(tableParam){
	  var prd = defaultPrd;
	  //查看当前共拆分为几个table,按照交易特性区分每一个tab中所应展示的内容
	  for(var i=0;i<tableParam.length;i++){
		  var trns = [];
		  for(var j=0;j<prd.trns.length;j++){
			  if(prd.trns[j].trnnature == tableParam[i].modulNature){
				  trns.push(prd.trns[j]);
			  }
		  }
			  drawTableByTrns(tableParam[i].tableId,trns);
	  }
  }
  
  
  /**
   * 天警云产品新建时初始化页面
   */
  function initTrnTable(){
	  var trns = defaultPrd.trns;
	  var tabHtml = "";
	  for(var i=0;i<trns.length;i++){
		  tabHtml += "<table class='disArea' border='1px #ooo' id='"+trns[i].trncod+"'  cellpadding='0' cellspacing='0'"
				+"width='50%' style='float: left;display: none;'>"
				+"<tr><td colspan='2' align='center'>"+trns[i].trnnam+"</td></tr>"
				+"<tr><td align='center'>单日累计异常数据：</td><td align='center'><input type='text' id='"+trns[i].trncod+"_dayexpcnt' class='trnParam'>条</td></tr>"
				+"<tr><td align='center'>当月累计异常数据：</td><td align='center'><input type='text' id='"+trns[i].trncod+"_monexpcnt' class='trnParam'>条</td></tr>"
				+"<tr><td align='center'>近三个月累计异常数据：</td><td align='center'><input type='text' id='"+trns[i].trncod+"_mon3expcnt' class='trnParam'>条</td></tr>"
				+"</table>";
	  }
	  $("#trnTables").prepend(tabHtml);
	  $(".disArea").show();
  }
  
  /**
   * 按照交易码集合进行插入元素
   * @param tabId
   * @param trns
   */
  function  drawTableByTrns(tabId,trns){
	  var trHtml = "";
	  for(var i=0;i<trns.length;i++){
		  if(i%2==0){
			  trHtml = "<tr  align='center'>";
		  }
		  trHtml+=addTrnTrWithData(trns[i]);
		  //当交易码个数为奇数个时
		  if(i%2==0 && i==(trns.length -1)){
			  trHtml+="<td></td></tr>";
			  addTr(tabId, -1, trHtml);
		  }
		  if(i%2!=0){
			  trHtml+="</tr>";
			  addTr(tabId, -1, trHtml);
		  }
	  }
  }
  
  
  /**
   * 向产品明细的table中插入数据
   * @param tab
   * @param row
   * @param data
   */
  function addTrnTrWithData(data){
	  var trHtml="<td width='48%'><input type='checkbox' class='trn' id='"+data.trncod+"' name='"+data.trnnam+"' value = '"+data.prddid+"' alt='"+data.trnnature+"'><lable>"+data.trnnam+"</lable></td>";
	  return trHtml;
  }
  

  /**
   * 向td中加入所有的团队
   * @param orgs
   */
  function addOrgTdWithData(orgs){
	  //清除当前td中的内容
	  $("#orgs").html("");
	  if(null==orgs){
		  return;
	  }
	 var html = "";
	  var html = "<div style='width:;height:170;float:center; overflow-y:auto;border-width: 5px;overflow-x:hidden;'>";
	  $.each(orgs,function(n,org){
		 // html+="<input type='button' id='"+org.sid+"' value='"+org.orgName+"' onclick='getPeoples(this.id)'/>"+"<br/>";
		  html+="<div style='width:180px;height:30px;background:#eeeeee;-moz-border-radius: 4px;-webkit-border-radius: 4px;cursor: pointer' onclick='getPeoples(this.id)'    id='"+org.sid+"' >"+org.orgName+"</div>" +
		  		"<div style='height:10px;' onmouseover='this.style.cursor=&quot;hand&quot;'></div>"
		  
	  });
	  html+="</div>"
	  $("#orgs").append(html);
  }
  
  /**
   * 向td中加入当前的人员
   * @param peoples
   */
  function addPeopleTdWithData(peoples){
	  //清除当前td中的内容
	  $("#peoples").html("");
	  var html="";
	  var html = "<div style='width:;height:170;float:right; overflow-y:auto;border-width: 5px;overflow-x:hidden; margin: auto;'>";
	  $.each(peoples,function(n,people){
		  var ck ;
		  if("-1"!=$.inArray(people.peopleCode,usrcodes) ||"-1"!=$.inArray(people.peopleCode,oldUsrcodes)  ){
			  ck = "checked = 'checked'";
		  }else{
			  ck = "";
		  }
		//  html+="<input type='checkbox' class='peopleCode' id='"+people.peopleCode+"'"+ck+" name = '"+people.usrId+"' value='"+people.organizeInfo+"' alt='"+people.orgName+"'/><lable>"+people.peopleName+"</lable><br/>";
		  html+="<div style='width:380;background:;'align='left'><input  type='checkbox' class='peopleCode' id='"+people.peopleCode+"'"+ck+" name = '"+people.usrId+"' value='"+people.organizeInfo+"' alt='"+people.orgName+"'/><lable>"+people.peopleName+"</lable></div>";
	  });
	 html+="</div>"
	  $("#peoples").append(html);
	  //注册事件
	  $(".peopleCode").click(function(){
		  if($(this).attr("checked")){
			 //从prdusrs中添加一条记录
			  var people = {peopleCode:'',peopleName:'',usrId:'',organizeInfo:'',orgName:''};
			  people.peopleCode = this.id;
			  people.usrId = this.name;
			  people.peopleName = $(this).next().html();
			  people.organizeInfo =  $(this).val();
			  people.orgName = $(this).attr("alt");
			  prdusrs.push(people);
			  usrcodes.push(people.peopleCode);
		  }else{
			 //从prdusrs中移除一条记录
			  var people = null;
			  for(var i=0;i<prdusrs.length;i++){
				  if(this.id == prdusrs[i].peopleCode){
					  prdusrs.splice(i,1);
					  usrcodes.splice(i,1);
					  break;
				  }
			  }
			  for(var i=0;i<oldUsrcodes.length;i++){
				  if(this.id == oldUsrcodes[i]){		  
					  oldUsrcodes.splice(i,1);
				  }
			  }
			  
		  }
	  });
  }
  
  /**
   * 根据机构id获取当前机构的人员信息
   * @param sid
   */
  function getPeoples(orgsid){
	  var data = {frontObjStr : JSON.stringify({orgSid:orgsid})};
	  $.ajax({
		  url : "/windforce/queryPeoplesByOrgId.action",
		  type : "post",
		  data : data,
		  beforeSend : function(XMLHttpRequest) {
			  XMLHttpRequest.setRequestHeader("RequestType", "ajax");
		  },
		  async : true,
		  dataType : "json",
		  success : function(data, textStatus) {
			  if(data != ""){
				  var ret = JSON.parse(data.frontObjStr);
				  var peoples = ret.peoples;
				  //获取当前产品的机构人员信息
				 
				  addPeopleTdWithData(peoples);
			  }
		  },
		  error : function(XMLHttpRequest, textStatus, errorThrown) {
			  updatePrdcod = null;
			  alert("加载信息erro:" + textStatus + errorThrown);
		  },
		  complete : function(XMLHttpRequest, textStatus) {
		  }
	  });
  }
  /**
   * 判断反欺诈云原交易所属模块
   */
  
  function judgeModule(){
	  var prd = defaultPrd;
	  var verTrnFlag = false;
	  var fadTrnFlag = false;
	  var trns = [];
	  for(var j=0;j<prd.trns.length;j++){
		  if(prd.trns[j].trnnature == '002'){
			  verTrnFlag = true;
		  }
		  if(prd.trns[j].trnnature == '003'){
			  fadTrnFlag = true;
		  }
	  }
	  if(verTrnFlag&&fadTrnFlag){
		  $("#veriTable").show();
		  $("#exceTable").show();
	  }else if(fadTrnFlag){
		  $("#exceTable").show();
	  }else if(verTrnFlag){
		  $("#veriTable").show();
	  }
  }
  
  
  $(document).ready(function() {
	  var isInit = "close";//判断是否为第一次点击新建按钮,用以决定是否生产表格
	  $("#addNewPrd").click(function(){
		  $("#prdnam").val("");
		  updatePrdcod = null;
		  prdusrs = [];
		  usrcodes = [];
		  oldUsrcodes=[];
		  $(".trn").each(function(){
			  //元素的prddid随之清除
			  $(this).val("");
			  $(this).attr("checked",false);
		  });
		  $(".trnParam").each(function(){
			  $(this).val("");
		  });
		  if(isInit == "isOpen"){
			  return;
		  }
		  if(isInit == "close"||isInit == "closeButHasClick"){
			  $(".disArea").show();
			  judgeModule();
			  $(".po_disArea").hide();
			  if(isInit != "closeButHasClick"){
				  if(appcod == "003"){
					  initTrnTable();
				  }else{
					  initModulTable(tableParam);
				  }
			  }else{
				  
			  }
			  isInit = "isOpen";
		  }
	  });

	  $("#closeTab").click(function(){
		  $(".disArea").hide();
		  $("#veriTable").hide();
		  $("#exceTable").hide();
		  isInit = "closeButHasClick";
	  });

	  $("#closePoTab").click(function(){
		  $(".po_disArea").hide();
		  isInit = "closeButHasClick";
	  });
	  
	  $("#saveOrUpdatePrd").click(function(){
		  //获得产品名称
		  var prdnam = $("#prdnam").val();
		  var trns = [];
		  var trn ;
		  //获取当前的trns集合;天警云产品特殊处理
		  if(appcod == "003"){
			  //trns拼接
			  var trncod = null; 
			  for(var i=0;i<defaultPrd.trns.length;i++){
				  trncod = defaultPrd.trns[i].trncod;
				  var dayexpcnt = $("#"+trncod+"_dayexpcnt").val();
				  var monexpcnt = $("#"+trncod+"_monexpcnt").val();
				  var mon3expcnt = $("#"+trncod+"_mon3expcnt").val();
				  if(null == dayexpcnt || "" == $.trim(dayexpcnt)||null == monexpcnt || "" == $.trim(monexpcnt)||null == mon3expcnt || "" == $.trim(mon3expcnt)){
					  continue;
				  }else{
					  trn = {prddid:'',trncod:'',trnnam:'',dayexpcnt:'',monexpcnt:'',mon3expcnt:''};
					  if(null == updatePrdcod){
						  trn.prddid = '';
					  }else{
						  //当前产品的prddID
						  trn.prddid = null;
						  var curTrns = null;
						  for(var j=0;j<prds.length;j++){
							  if(updatePrdcod == prds[j].prdcod){
								  curTrns = prds[j].trns;
								  for(var k=0;k<prds[j].trns.length;k++){
									  if(trncod == prds[j].trns[k].trncod){
										  trn.prddid = prds[j].trns[k].prddid;
										  break;
									  }
								  }
								  break;
							  }
						  }
					  }
					  trn.trncod = trncod;
					  trn.trnnam = defaultPrd.trns[i].trnnam;
					  trn.dayexpcnt = $.trim(dayexpcnt);
					  trn.monexpcnt = $.trim(monexpcnt);
					  trn.mon3expcnt = $.trim(mon3expcnt);
					  trns.push(trn);
				  }
			  }
		  }else{
			  $(".trn").each(function(){
				  if($(this).attr("checked")){
					  trn = {trncod:'',prddid:'',trnnam:'',trnnature:''};
					  trn.trncod = this.id;
					  trn.prddid = $(this).val();
					  trn.trnnam = this.name;
					  trn.trnnature = this.alt;
					  trns.push(trn);
				  }
			  });
		  }
		  //获取当前已选中的子账户信息
		  var peoples = prdusrs; 
		  var prdtyp = "003";
		 for(var i =0;i<trns.length;i++){
			 if(trns[i].trnnature == '003'){
				 prdtyp = "002";
				 break;
			 }
		 }
		 var reg = /^[0-9a-zA-Z\u4e00-\u9fa5]{1,20}$/;//汉字、数字、字母
		 if(!reg.test(prdnam)){
			 alert("请输入产品名称位20位以内的汉字、数字、字母！");
			 return ;
		 }
		  var data = {frontObjStr : JSON.stringify({appcod:appcod,prdcod:updatePrdcod,prdnam:prdnam,trns:trns,peoples:peoples,prdtyp:prdtyp})};
		  $.ajax({
			  url : "/windforce/saveOrUpdatePrdAction.action",
			  type : "post",
			  data : data,
			  beforeSend : function(XMLHttpRequest) {
				  XMLHttpRequest.setRequestHeader("RequestType", "ajax");
			  },
			  async : true,
			  dataType : "json",
			  success : function(data, textStatus) {
				  var rowsData = null;
				  if (data != "") {
					  var ret = JSON.parse(data.frontObjStr);
					  var errcod = ret.errcod;
					  if (data.frontObjStr != null) {
						  if(null == updatePrdcod){
							  //保存产品
							
						
								  rowsData = ret.obj;
								  if(null != ret.obj){
									  for(var i=0;i<rowsData.length;i++){
										  prds.push(rowsData[i]);
										  addPrdTrWithData(initTableId,-2,parse(rowsData[i],appcod));
									  }
								  }
								 if(errcod == '1'){
									alert('新增产品策略成功'); 
								 }else{
									alert('新增产品策略失败\n'+ret.errmsg);  
								 }
							  
						  }else{
							  //修改产品
							  //3.修改全局变量的值
							if(errcod == '1'){
								
							  for(var i=0;i<prds.length;i++){
								  if(updatePrdcod == prds[i].prdcod){
									  prds[i].prdnam = prdnam;
									  prds[i].trns = trns;
									  prds[i].po = peoples;
									  //1.修改的产品删除该页面元素
									  delTr(updatePrdcod);
									  //2.修改的产品变更该页面元素
									  addPrdTrWithData(initTableId,-2,parse(prds[i],appcod));
									  break;
								  }
							  }
							  alert('修改产品策略成功'); 
							 }else{
							  alert('修改产品策略失败\n'+ret.errmsg);  
							 }
							  updatePrdcod = null;
						  }
					  }
				  }
				
			  },
			  error : function(XMLHttpRequest, textStatus, errorThrown) {
				  updatePrdcod = null;
				  alert("加载信息erro:" + textStatus + errorThrown);
			  },
			  complete : function(XMLHttpRequest, textStatus) {
			  }
		  });
		  
		  //关闭表格显示
		  $("#closeTab").click();
		  $("#closePoTab").click();
	  });
	  
	  //点击下一步,显示机构人员信息
	  $("#nextStep").click(function(){
		  //获得产品名称(在点击下一步时判断输入的产品名是否合法)
		  var prdnam = $("#prdnam").val();
		  var reg = /^[0-9a-zA-Z\u4e00-\u9fa5]{1,20}$/;//汉字、数字、字母
			 if(!reg.test(prdnam)){
				 alert("请输入产品名称位20位以内的汉字、数字、字母！");
				 return ;
			 }
		  $("#peoples").html("");
		//获得产品名称
		  var prdnam = $("#prdnam").val();
		  if(null == $.trim(prdnam) || "" == $.trim(prdnam)){
			  alert("请输入产品名称...");
			  return;
		  }
		  //获取当前的trns集合;天警云产品特殊处理
		  if(appcod == "003"){
			  //输入校验
			  $(".trnParam").each(function(){
				  var trnParam = $(this).val();
				  if(null != trnParam && "" != $.trim(trnParam)&&isNaN(parseInt(trnParam))){
					  alert("条目数输入必须为数字!");
					  return;
				  }
			  });
		  }
		  //校验是否勾选交易码
		  var contain = false;
		  $(".trn").each(function(){
			  if($(this).attr("checked")){
				  contain = true;
			  }
		  });
		  if(!contain){
			  alert("请勾选交易码!");
			  return;
		  }
		  var data = {frontObjStr : null};
		  $.ajax({
			  url : "/windforce/queryChildOrganizes.action",
			  type : "post",
			  data : data,
			  beforeSend : function(XMLHttpRequest) {
				  XMLHttpRequest.setRequestHeader("RequestType", "ajax");
			  },
			  async : true,
			  dataType : "json",
			  success : function(data, textStatus) {
				  if (data != "") {
					  if (data.frontObjStr != null) {
						 $(".disArea").hide();
						 $("#veriTable").hide();
						  $("#exceTable").hide();
						 $(".po_disArea").show();
						 var ret = JSON.parse(data.frontObjStr)
						 var orgs = ret.organizeInfos;
						 addOrgTdWithData(orgs);
					  }
				  }
			  },
			  error : function(XMLHttpRequest, textStatus, errorThrown) {
				  alert("加载信息erro:" + textStatus + errorThrown);
			  },
			  complete : function(XMLHttpRequest, textStatus) {
			  }
		  });
	  });
	  
	  //点击上一步
	  $("#upStep").click(function(){
		  $(".po_disArea").hide();
		  $(".disArea").show();
		 judgeModule();
	  });
  });
