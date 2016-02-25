var tree = null;

var Area = {
	getAreaByAreaSid : function(sid) {
		if (!sid || sid == "") {	return {};}
		var area = "";
		$.ajax({type : "POST",url : $.getContextPath()+ "/areaAction_findFormatAreaByAreaSid.action",data : {areaSid : sid},dataType : "json",async : false,success : function(data) {
			if (data.area) {
				area = data.area;
			}
		}});
		return area;
	},
	getFormatArea : function(sid) {
		if (!sid || sid == "") { return {}; }
		var _area = "";
		$.ajax({type : "POST",url : $.getContextPath()+ "/areaAction_findFormatAreaByAreaSid.action",data : {areaSid : sid},dataType : "json",async : false,success : function(data) {
			if (data.area) {
				var area = data.area;
				if(area.areaSid == "999999"){
					return ""
				}
				_area = area.areaName + "(" + area.areaCode + ")";
			}
		}});
		return _area;
	}
};

//区域JS
(function($) {	
	var random = null;//随机数
	$.fn.radioAreaTree  = function(onCheckCallBack){
		random = new Date().getTime() + "" +Math.floor(100000000*Math.random()+1000000000);
		var radioSetting = {
				check: {
					enable: true,chkStyle: "radio",radioType: "all"
				},async: {
					enable: true,// 启用异步加载
					url:top.ctx+"/areaAction_findChildAreasByAreaSid.action", // 异步请求地址
					autoParam:["areaSid"], // 需要传递的参数,为你在ztree中定义的参数名称
					dataFilter: function(treeId, parentNode, data){
						var array = [];
						var _pId = parentNode.areaSid,_id = null, _name = null,_isParent = null,_areaCode = null, _parentSid = null,_areaType = null;
						$.each(data.areas, function(index, area) {
						    _id = area.areaSid;  
						     _name = area.areaName;  
						    _isParent = area.isParent;
						    _areaCode = area.areaCode;
						    _parentSid = area.parentArea;
						    _areaType = area.areaType;
							array[index] = {parentOrg:_pId, areaSid:_id, areaName:_name, isParent:_isParent,areaCode:_areaCode,parentArea:_parentSid,areaType:_areaType};
						});
						return array;  
					 }
				 },view: {
				    dblClickExpand: false,showLine: true,selectedMulti: false
				},data : {
					simpleData: {
					    enable: true,
						idKey : "areaSid",
						pIdKey : "parentArea",
						rootPId : null
					},key : {
						name: "areaName"
					}
				}, callback: {
					onCheck: function(e, treeId, treeNode) {
						if(onCheckCallBack){
							$("#checkAreaDLG_"+random).dialog("close");
							onCheckCallBack(e, treeId, treeNode);
						}
					}
				}
			 };
		
			$(this).after("<div id='checkAreaDLG_"+random+"' title='选择区域信息' style='padding:  0 0 0 0;background-color:#fff;width:100%;height:100%;border: 1px solid #b8d0d6;'>" +
					"<form  id='checkArea_item_"+random+"'><ul id='_areaTree_"+random+"' class='ztree' ></ul></form></div>"); 
			var areaSid = top.loginPeopleInfo.areaSid;
//			if(top.loginPeopleInfo.orgSid == "B1F0FE3AA0104A1FBAD3F85CD19C7F05"){//如果是总行,则显示全国的列表
//				areaSid = "000000";
//			}
			
			$.ajax({type:"POST",url: top.ctx + "/areaAction_findRootAreasByAreaSid.action",data: {areaSid:areaSid},dataType:"json",async:false,success:function(data){
				if(data.areas){
					tree = $.fn.zTree.init($("#_areaTree_"+random),radioSetting , data.areas);
				}
			}});
			
		//查看信息弹出框
		$("#checkAreaDLG_"+random).dialog({autoOpen: false,caption:"查看区域信息", resizable: false,height: 470,width: 400,modal: true,close: function() {}});
		
		$("#checkAreaDLG_"+random).dialog("open");
	} ;
	
	/**
	 * 区域树normal
	 * treeType: 树的类型 normal : 正常类型  checkbox ：多选按钮形式  radio：单选an
	 * onClickCallBack:
	 * onChangeCallBack:
	 */
	$.fn.areaTree = function(treeType,onClickCallBack,onCheckCallBack) {
		random = new Date().getTime() + "" +Math.floor(100000000*Math.random()+1000000000);
		/**
		 * 正常普通样式
		 */	
		var normalSetting = {
				async: {
					enable: true,// 启用异步加载
					url:$.getContextPath()+"/areaAction_findChildAreasByAreaSid.action", // 异步请求地址
					autoParam:["areaSid"], // 需要传递的参数,为你在ztree中定义的参数名称
					dataFilter: function(treeId, parentNode, data){
						var array = [];
						var _pId = parentNode.areaSid,_id = null,_name = null,_isParent = null, _areaCode = null,_parentSid = null,_areaType = null;
						$.each(data.areas, function(index, area) {
						    _id = area.areaSid;  
						     _name = area.areaName;  
						    _isParent = area.isParent;
						    _areaCode = area.areaCode;
						    _areaType = area.areaType;
						    _parentSid = area.parentArea;
							array[index] = {parentOrg:_pId, areaSid:_id, areaName:_name, isParent:_isParent,areaCode:_areaCode,areaType:_areaType,parentArea:_parentSid}; 
						});
						return array;  
					 }
				 },view: {
				    dblClickExpand: false,
					showLine: true,
					selectedMulti: false
				},data : {
					simpleData: {
					    enable: true,
						idKey : "areaSid",
						pIdKey : "parentArea",
						rootPId : null
					},key : {
						name: "areaName"
					}
				}, callback: {
					onClick: function(e, treeId, treeNode) {
						if(onClickCallBack){
							onClickCallBack(e, treeId, treeNode);
						}
					}
				}
		};
		
		/**
		 * 多选按钮
		 */
		var checkBoxSetting = {
				check: {
					checked : true,
					enable: true,
					chkboxType: { "Y": "ps", "N": "ps" }
				},async: {
					enable: true,// 启用异步加载
					url:$.getContextPath()+"/areaAction_findChildAreasByAreaSid.action", // 异步请求地址
					autoParam:["areaSid"], // 需要传递的参数,为你在ztree中定义的参数名称
					dataFilter: function(treeId, parentNode, data){
						 var array = [];
						var _pId = parentNode.areaSid, _id = null,_name = null,_isParent = null,_areaCode = null,_parentSid = null,_areaType = null;
						$.each(data.areas, function(index, area) {
						    _id = area.areaSid;  
						     _name = area.areaName;  
						    _isParent = area.isParent;
						    _areaCode = area.areaCode;
						    _areaType = area.areaType;
						    _parentSid = area.parentArea;
							array[index] = {parentOrg:_pId, areaSid:_id, areaName:_name, isParent:_isParent,areaCode:_areaCode,areaType:_areaType,parentArea:_parentSid}; 
						});
						return array;  
					 }
				 },view: {
				    dblClickExpand: false,
					showLine: true,
					selectedMulti: false
				},data : {
					simpleData: {
					    enable: true,
						idKey : "areaSid",
						pIdKey : "parentArea",
						rootPId : null
					},key : {
						name: "areaName"
					}
				}, callback: {
					onCheck: function(e, treeId, treeNode) {
						if(onCheckCallBack){
							onCheckCallBack(e, treeId, treeNode);
						}
					}
				}
		};
		
		/**
		 * 单选按钮样式
		 */
		var radioSetting = {
				check: {
					enable: true,
					chkStyle: "radio",
					radioType: "all"
				},
				async: {
					enable: true,// 启用异步加载
					url:$.getContextPath()+"/areaAction_findChildAreasByAreaSid.action", // 异步请求地址
					autoParam:["areaSid"], // 需要传递的参数,为你在ztree中定义的参数名称
					dataFilter: function(treeId, parentNode, data){
						var array = [];
						var _pId = parentNode.areaSid,_id = null,_name = null, _isParent = null,_areaCode = null,_parentSid = null,_areaType = null;
						$.each(data.areas, function(index, area) {
						    _id = area.areaSid;  
						     _name = area.areaName;  
						    _isParent = area.isParent;
						    _areaCode = area.areaCode;
						    _areaType = area.areaType;
						    _parentSid = area.parentArea;
							array[index] = {parentOrg:_pId, areaSid:_id, areaName:_name, isParent:_isParent,areaCode:_areaCode,areaType:_areaType,parentArea:_parentSid}; 
						});
						return array;  
					 }
				 },view: {
				    dblClickExpand: false,
					showLine: true,
					selectedMulti: false
				},data : {
					simpleData: {
					    enable: true,
						idKey : "areaSid",
						pIdKey : "parentOrg",
						rootPId : null
					},key : {
						name: "areaName"
					}
				}, callback: {
					onCheck: function(e, treeId, treeNode) {
						if(onCheckCallBack){
							onCheckCallBack(e, treeId, treeNode);
						}
					}
				}
			 };
		
		$(this).html("<ul id='_areaTree_"+random+"' class='ztree' ></ul>"); 
		var areaSid = top.loginPeopleInfo.areaSid;
//		if(top.loginPeopleInfo.orgSid == "B1F0FE3AA0104A1FBAD3F85CD19C7F05"){//如果是总行,则显示全国的列表
//			areaSid = "000000";
//		}
		$.ajax({type:"POST",url: $.getContextPath() + "/areaAction_findRootAreasByAreaSid.action",data: {areaSid:areaSid},dataType:"json",async:false,success:function(data){
				if(data.areas){
					if(treeType == "normal"){//普通默认样式样式
						tree = $.fn.zTree.init($("#_areaTree_"+random),normalSetting , data.areas);
					}else if(treeType == "checkbox"){
						tree = $.fn.zTree.init($("#_areaTree_"+random),checkBoxSetting , data.areas);
					}else if(treeType == "radio"){
						tree = $.fn.zTree.init($("#_areaTree_"+random),radioSetting , data.areas);
					}else{
						tree = $.fn.zTree.init($("#_areaTree_"+random),normalSetting , data.areas);
					}
				}
			}
		});
	};
})(jQuery);

