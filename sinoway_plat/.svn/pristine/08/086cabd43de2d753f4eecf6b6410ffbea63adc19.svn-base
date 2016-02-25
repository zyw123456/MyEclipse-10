/*****************************机构封装API开始*******************************/
(function($) {
    var random = null;
    //随机数
    /**
     *
     * checkType : 0 ： 选择网点 1：不选择网点
     * includeDel:是否包含已经撤消或删除的。 true:包含删除或撤，false：不包含删除或撤消
     */
    $.fn.radioOrgTree = function(checkLeafFlag, orgSid, checkType, includeDel, onCheckCallBack) {
        random = new Date().getTime() + "" + Math.floor(100000000 * Math.random() + 1000000000);
        var requestChildUrl = $.getContextPath() + "/wf/ext/po/extOrganizationAction_findChildOrganizationByOrganizationSid.action";
        //默认请求正常状态的机构
        var requestUrl = $.getContextPath() + "/wf/ext/po/extOrganizationAction_findOrganizationByOrganizationSid.action";
        //默认请求正常状态的机构
        /*	if(includeDel){
         requestChildUrl = $.getContextPath()+"/pms/organizationAction_findChildOrganizationByOrganizationSidIncludeCancel.action";//请求撤消状态的机构
         requestUrl = $.getContextPath()+"/pms/organizationAction_findOrganizationByOrganizationSidIncludeCancel.action";//请求撤消状态的机构
         }*/

        var radioSetting = {
            check : {
                enable : true,
                chkStyle : "radio",
                radioType : "all"
            },
            async : {
                enable : true, // 启用异步加载
                url : requestChildUrl, // 异步请求地址
                autoParam : ["sid"], // 需要传递的参数,为你在ztree中定义的参数名称
                dataFilter : function(treeId, parentNode, data) {
                    var array = [];
                    var _pId = parentNode.sid, _id = null, _organizationName = null, _manageOrgFlag = null, _bizOrgFlag = null, _isParent = null, _organizationNo = null, _organizationType = null, _organizationArea = null, _nocheck = false;
                    $.each(data.organizations, function(index, organization) {
                        _id = organization.sid;
                        _organizationName = organization.organizationName;
                        _manageOrgFlag = organization.manageOrgFlag;
                        _bizOrgFlag = organization.bizOrgFlag;
                        _isParent = organization.isParent;
                        _organizationNo = organization.organizationNo;
                        _organizationType = organization.organizationType;
                        if (checkType == 1) {
                            if (checkLeafFlag && _organizationType == "1") {
                                _nocheck = true;
                            }
                        }
                        _organizationArea = organization.organizationArea;
                        array[index] = {
                            sid : _id,
                            parentOrganization : _pId,
                            organizationNo : _organizationNo,
                            organizationName : _organizationName,
                            manageOrgFlag : _manageOrgFlag,
                            bizOrgFlag : _bizOrgFlag,
                            isParent : _isParent,
                            organizationType : _organizationType,
                            organizationArea : _organizationArea,
                            nocheck : _nocheck
                        };
                    });
                    return array;
                }
            },
            view : {
                dblClickExpand : false,
                showLine : true,
                selectedMulti : false
            },
            data : {
                simpleData : {
                    enable : true,
                    idKey : "sid",
                    pIdKey : "parentOrganization",
                    rootPId : null
                },
                key : {
                    name : "organizationName"
                }
            },
            callback : {
                onCheck : function(e, treeId, treeNode) {
                    if (onCheckCallBack) {
                        $("#checkOrgDLG_" + random).dialog("close");
                        onCheckCallBack(e, treeId, treeNode);
                    }
                }
            }
        };

        $(this).after("<div id='checkOrgDLG_" + random + "' title='选择机构信息' style='padding:  0 0 0 0;background-color:#fff;width:100%;height:100%;border: 1px solid #b8d0d6;'>" + "<form  id='checkOrg_item_" + random + "'><ul id='_orgTree_" + random + "' class='ztree' ></ul></form></div>");
        $.ajax({
            type : "POST",
            url : requestUrl,
            data : {
                sid : orgSid
            },
            dataType : "json",
            async : false,
            success : function(data) {
                if (data.organization) {
                    var organizations = new Array();
                    organizations.push(data.organization);
                    tree = $.fn.zTree.init($("#_orgTree_" + random), radioSetting, organizations);
                }
            }
        });

        //查看信息弹出框
        $("#checkOrgDLG_" + random).dialog({
            autoOpen : false,
            caption : "选择机构信息",
            resizable : false,
            height : 470,
            width : 400,
            modal : true,
            close : function() {
            }
        });
        $("#checkOrgDLG_" + random).dialog("open");
    };

    /**
     * 区域树normal
     * treeType: 树的类型 normal : 正常类型  checkbox ：多选按钮形式  radio：单选an
     * includeDel:是否包含已经撤消或删除的。 true:包含删除或撤，false：不包含删除或撤消
     * onClickCallBack:
     * onChangeCallBack:
     */
    $.fn.orgTree = function(treeType, orgSid, includeDel, onClickCallBack, onCheckCallBack) {
        random = new Date().getTime() + "" + Math.floor(100000000 * Math.random() + 1000000000);
        var requestChildUrl = $.getContextPath() + "/wf/ext/po/extOrganizationAction_findChildOrganizationByOrganizationSid.action";
        //默认请求正常状态的机构
        var requestUrl = $.getContextPath() + "/wf/ext/po/extOrganizationAction_findOrganizationByOrganizationSid.action";
        //默认请求正常状态的机构
        /*	if(includeDel){
        requestChildUrl = $.getContextPath()+"/pms/organizationAction_findChildOrganizationByOrganizationSidIncludeCancel.action";//请求撤消状态的机构
        requestUrl = $.getContextPath()+"/pms/organizationAction_findOrganizationByOrganizationSidIncludeCancel.action";//请求撤消状态的机构
        }*/

        /**
         * 正常普通样式
         */
        var normalSetting = {
            async : {
                enable : true, // 启用异步加载
                url : requestChildUrl, // 异步请求地址
                autoParam : ["sid"], // 需要传递的参数,为你在ztree中定义的参数名称
                dataFilter : function(treeId, parentNode, data) {
                    var array = [];
                    var _pId = parentNode.sid, _id = null, _organizationName = null, _manageOrgFlag = null, _bizOrgFlag = null, _isParent = null, _organizationNo = null, _organizationType = null, _organizationArea = null;
                    $.each(data.organizations, function(index, organization) {
                        _id = organization.sid;
                        _organizationName = organization.organizationName;
                        _manageOrgFlag = organization.manageOrgFlag;
                        _bizOrgFlag = organization.bizOrgFlag;
                        _isParent = organization.isParent;
                        _organizationNo = organization.organizationNo;
                        _organizationType = organization.organizationType;
                        _organizationArea = organization.organizationArea;
                        array[index] = {
                            sid : _id,
                            parentOrganization : _pId,
                            organizationNo : _organizationNo,
                            organizationName : _organizationName,
                            manageOrgFlag : _manageOrgFlag,
                            bizOrgFlag : _bizOrgFlag,
                            isParent : _isParent,
                            organizationType : _organizationType,
                            organizationArea : _organizationArea
                        };
                    });
                    return array;
                }
            },
            view : {
                dblClickExpand : false,
                showLine : true,
                selectedMulti : false
            },
            data : {
                simpleData : {
                    enable : true,
                    idKey : "sid",
                    pIdKey : "parentOrganization",
                    rootPId : null
                },
                key : {
                    name : "organizationName"
                }
            },
            callback : {
                onClick : function(e, treeId, treeNode) {
                    if (onClickCallBack) {
                        onClickCallBack(e, treeId, treeNode);
                    }
                }
            }
        };

        /**
         * 多选按钮
         */
        var checkBoxSetting = {
            check : {
                enable : true,
                chkboxType : {
                    "Y" : "ps",
                    "N" : "ps"
                }
            },
            async : {
                enable : true, // 启用异步加载
                url : requestChildUrl, // 异步请求地址
                autoParam : ["sid"], // 需要传递的参数,为你在ztree中定义的参数名称
                dataFilter : function(treeId, parentNode, data) {
                    var array = [];
                    var _pId = parentNode.sid, _id = null, _organizationName = null, _manageOrgFlag = null, _bizOrgFlag = null, _isParent = null, _organizationNo = null, _organizationType = null, _organizationArea = null;
                    $.each(data.organizations, function(index, organization) {
                        _id = organization.sid;
                        _organizationName = organization.organizationName;
                        _manageOrgFlag = organization.manageOrgFlag;
                        _bizOrgFlag = organization.bizOrgFlag;
                        _isParent = organization.isParent;
                        _organizationNo = organization.organizationNo;
                        _organizationType = organization.organizationType;
                        _organizationArea = organization.organizationArea;
                        array[index] = {
                            sid : _id,
                            parentOrganization : _pId,
                            organizationNo : _organizationNo,
                            organizationName : _organizationName,
                            manageOrgFlag : _manageOrgFlag,
                            bizOrgFlag : _bizOrgFlag,
                            isParent : _isParent,
                            organizationType : _organizationType,
                            organizationArea : _organizationArea
                        };
                    });
                    return array;
                }
            },
            view : {
                dblClickExpand : false,
                showLine : true,
                selectedMulti : false
            },
            data : {
                simpleData : {
                    enable : true,
                    idKey : "sid",
                    pIdKey : "parentOrganization",
                    rootPId : null
                },
                key : {
                    name : "organizationName"
                }
            },
            callback : {
                onCheck : function(e, treeId, treeNode) {
                    if (onCheckCallBack) {
                        onCheckCallBack(e, treeId, treeNode);
                    }
                }
            }
        };

        /**
         * 单选按钮样式
         */
        var radioSetting = {
            check : {
                enable : true,
                chkStyle : "radio",
                radioType : "all"
            },
            async : {
                enable : true, // 启用异步加载
                url : requestChildUrl, // 异步请求地址
                autoParam : ["sid"], // 需要传递的参数,为你在ztree中定义的参数名称
                dataFilter : function(treeId, parentNode, data) {
                    var array = [];
                    var _pId = parentNode.sid, _id = null, _organizationName = null, _manageOrgFlag = null, _bizOrgFlag = null, _isParent = null, _organizationNo = null, _organizationType = null, _organizationArea = null;
                    $.each(data.organizations, function(index, organization) {
                        _id = organization.sid;
                        _organizationName = organization.organizationName;
                        _manageOrgFlag = organization.manageOrgFlag;
                        _bizOrgFlag = organization.bizOrgFlag;
                        _isParent = organization.isParent;
                        _organizationNo = organization.organizationNo;
                        _organizationType = organization.organizationType;
                        _organizationArea = organization.organizationArea;
                        array[index] = {
                            sid : _id,
                            parentOrganization : _pId,
                            organizationNo : _organizationNo,
                            organizationName : _organizationName,
                            manageOrgFlag : _manageOrgFlag,
                            bizOrgFlag : _bizOrgFlag,
                            isParent : _isParent,
                            organizationType : _organizationType,
                            organizationArea : _organizationArea
                        };
                    });
                    return array;
                }
            },
            view : {
                dblClickExpand : false,
                showLine : true,
                selectedMulti : false
            },
            data : {
                simpleData : {
                    enable : true,
                    idKey : "sid",
                    pIdKey : "parentOrganization",
                    rootPId : null
                },
                key : {
                    name : "organizationName"
                }
            },
            callback : {
                onCheck : function(e, treeId, treeNode) {
                    if (onCheckCallBack) {
                        onCheckCallBack(e, treeId, treeNode);
                    }
                }
            }
        };

        $(this).html("<ul id='_orgTree_" + random + "' class='ztree' ></ul>");
        $.ajax({
            type : "POST",
            url : requestUrl,
            data : {
                sid : orgSid
            },
            dataType : "json",
            async : false,
            success : function(data) {
                if (data.organization) {
                    var organization = data.organization;
                    var organizations = new Array();
                    organizations.push(data.organization);
                    if (treeType == "normal") {//普通默认样式样式
                        tree = $.fn.zTree.init($("#_orgTree_" + random), normalSetting, organizations);
                    } else if (treeType == "checkbox") {
                        tree = $.fn.zTree.init($("#_orgTree_" + random), checkBoxSetting, organizations);
                    } else if (treeType == "radio") {
                        tree = $.fn.zTree.init($("#_orgTree_" + random), radioSetting, organizations);
                    } else {
                        tree = $.fn.zTree.init($("#_orgTree_" + random), normalSetting, organizations);
                    }
                }
            }
        });
    };
})(jQuery); 
/*****************************机构封装API结束*******************************/

/*****************************机构类型封装API开始(原：organizationType.js)*******************************/
var organizationType = {
		getOrganizationTypeBySid : function(organizationTypeSid) {
			if (!organizationTypeSid || organizationTypeSid == "") {	return {};}
			var organizationType = "";
			$.ajax({type : "POST",url : $.getContextPath()+ "/wf/ext/po/extOrganizationAction_findOrganizationTypeByOrganizationTypeSid.action",data : {organizationTypeSid : organizationTypeSid},dataType : "json",async : false,success : function(data) {
				if (data.organizationType) {
					organizationType = data.orgType;
				}
			}});
			return organizationType;
		},
		getFormatOrganizationType : function(organizationTypeSid) {
			if (!organizationTypeSid || organizationTypeSid == "") { return {}; }
			var _organizationType = "";
			$.ajax({type : "POST",url : $.getContextPath()+ "/wf/ext/po/extOrganizationAction_findOrganizationTypeByOrganizationTypeSid.action",data : {organizationTypeSid : organizationTypeSid},dataType : "json",async : false,success : function(data) {
				if (data.orgType) {
					var organizationType = data.orgType;
					_organizationType = orgType.orgTypeName;
				}
			}});
			return _organizationType;
		}
	};
/*****************************机构类型封装API结束(原：organizationType.js)*******************************/

/*****************************机构省市封装API开始(原：organizationProvince.js)*******************************/

var organizationProvince = {
	getOrganizationProvinceBySid : function(organizationProvinceSid) {
		if (!organizationProvinceSid || organizationProvinceSid == "") {	return {};}
		var _organizationProvince = "";
		$.ajax({type : "POST",url : $.getContextPath()+ "/pms/organizationAction_findOrganizationProvinceByOrganizationProvinceSid.action",data : {organizationProvinceSid : organizationProvinceSid},dataType : "json",async : false,success : function(data) {
			if (data.organizationProvince) {
				_organizationProvince = data.organizationProvince;
			}
		}});
		return _organizationProvince;
	},
	getFormatOrganizationProvince : function(organizationProvinceSid) {
		if (!organizationProvinceSid || organizationProvinceSid == "") { return {}; }
		var _organizationProvince = "";
		$.ajax({type : "POST",url : $.getContextPath()+ "/pms/organizationAction_findOrganizationProvinceByOrganizationProvinceSid.action",data : {organizationProvinceSid : organizationProvinceSid},dataType : "json",async : false,success : function(data) {
			if (data.organizationProvince) {
				var organizationProvince = data.organizationProvince;
				_organizationProvince = organizationProvince.organizationProvinceName + "(" + organizationProvince.organizationProvinceCode + ")";
			}
		}});
		return _organizationProvince;
	}
};

(function($) {
	var random = null;//随机数
	$.fn.organizationProvinceContent = function(onCheckCallBack) {
		random = new Date().getTime() + "" +Math.floor(100000000*Math.random()+1000000000);
		var currentOrganizationSid =  top.loginPeopleInfo.orgSid;
		if(null == currentOrganizationSid || "" == currentOrganizationSid){
			alert("数据异常,请重新登陆系统.");
			return;
		}
		$(this).after("<div id='checkProvinceDLG_"+random+"' title='省市代码列表' style='padding:  0 0 0 0;background-color:#fff;width:100%;height:100%;border: 1px solid #b8d0d6;'><table id='provinceList_"+random+"'></table></div>"); 
		//省级代码
		$("#provinceList_"+random).jqGrid({width:465,height:243,url: $.getContextPath()+"/pms/organizationAction_findAllOrganizationProvince.action",rowNum: 20, rowList: [20,50,100], colNames:["","","区域代码","区域名称"],colModel:[
		   	   	{name:"sid",index:"sid", hidden:true,width:"0px"},
		   	   	{name:"tag1",index:"tag1",align:'center',sortable:false,width:"10px",formatter:function(cellvalue, options, rowObject){
	                    return "<input type='radio' name='provinceSIDNode' value='"+rowObject.sid+"-"+rowObject.organizationProvinceCode+"-"+rowObject.organizationProvinceName+"' />";
		   	   	}},
		   		{name:"organizationProvinceCode",index:"organizationProvinceCode", width:100, align:"center",sortable:false},
		   		{name:"organizationProvinceName",index:"organizationProvinceName", align:"center",sortable:false},
		   	],onSelectRow: function(ids) {
	            $($(this)[0]).find("input[name='provinceSIDNode']")[ids - 1].checked = true;
	        }
		});
		$("#checkProvinceDLG_"+random).dialog({autoOpen: false,caption:"省市代码列表", resizable: false,height: 337,width: 470,modal: true,buttons: {
			"确认": function() {
				var provinceSid = $("input:radio[name='provinceSIDNode']:checked").val();
				if(null == provinceSid || "" == provinceSid){
					alert("请选择省市代码.");
					return;
				}
				var datas = provinceSid.split("-");
				if(null != datas && datas.length == 3){
					$("#checkProvinceDLG_"+random).dialog("close");
					onCheckCallBack(new roleData(datas[0],datas[1],datas[2]));
				}
			},"关闭":function(){
				$("#checkProvinceDLG_"+random).dialog("close");
			}
		},close: function() {
			var radios = document.getElementsByName("provinceSIDNode");
			 for(var i=0; i<radios.length; i++){  
				 radios[i].checked = false;
			 }
		}});
		$("#checkProvinceDLG_"+random).dialog("open");
	};
})(jQuery);

var roleData = function(sid,organizationProvinceCode,organizationProvinceName){
	this.sid = sid;
	this.organizationProvinceCode = organizationProvinceCode;
	this.organizationProvinceName = organizationProvinceName;
};

/*****************************机构省市封装API结束(原：organizationProvince.js)*******************************/