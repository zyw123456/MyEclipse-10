
var rootPath = "d:/yinzhijie";

//jqgrid相关
$.extend(true, $.jgrid.defaults, {
	jsonReader: {  
        root:"pageBean.data",        // 数据行（默认为：rows）  
        page: "pageBean.pageNo",    // 当前页  
        total: "pageBean.totalPages",  // 总页数  
        records: "pageBean.totalRecords",  // 总记录数  
        repeatitems : false     // 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设  
    },  
    prmNames: {page:"queryBean.pageNo",rows:"queryBean.pageSize", sort: "queryBean.orderBy",order: "queryBean.order", search:"_search", nd:"nd", id:"id",oper:"oper",editoper:"edit",addoper:"add",deloper:"del", subgridid:"id", npage: null, totalrows:"totalrows"},
    mtype: "POST",
    rowNum:10,
   	rowList:[10,20,30],
   	tree_root_level: 1,
   	viewrecords: true,
   	datatype: "json",
   	width: $(window).width() - 5,
	height: "100%",
	hidegrid: false,
	autoLoad: true,
	loadComplete: function(res) {
		if(res && res.responseMessage && res.responseMessage.success) {
			//成功
			if(!this.p.treeGrid) {
				$(this).data("data", res.pageBean.data);
			} else {
				var data = $(this).data("data");
				if(!data) {
					data = {};
					$(this).data("data", data);
				}
				var _this = this;
				$.each(res.pageBean.data, function(i, d) {
					data[d[_this.p.idField]] = d;
				});
			}
			
			//无数据处理
			if(!this.p.treeGrid && res.pageBean.data.length == 0) {
				var cols = $(this).find("tr:first td").length;
				$(this).append("<tr><td colspan='" + cols + "' align='center' style='text-align:center;font-style:italic; color: gray;padding: 4px;'>无数据</td></tr>");
			}
		} else {
			//失败
			//$.error("读取失败: " + res.responseMessage.message, res.responseMessage.stackTrace);
		}
   	},
   	loadError: function(response) {
   		//$.error("内部错误: " + response.status + response.responseText);
   	},
   	beforeRequest: function() {
   		this.p.jsonReader.id = this.p.idField;
   		this.p.prmNames.id = this.p.idField;
   		if(!this.p.pager) {
   			var postData = $(this).jqGrid("getGridParam", "postData");  
   			postData["queryBean.pageSize"] = 0;
   		}
   		if(!this.p.autoLoad && !this.searched) {
   			return false;
   		}
   	},
	beforeProcessing: function(res, st, xhr) {
		//对tree特殊处理
//		if(res.responseMessage.success && this.p.treeGrid) {
		if(this.p.treeGrid) {
			var data = res.pageBean.data;
			var _this = this;
			$.each(data, function(i, d) {
				d[_this.p.treeReader.leaf_field] = (d[_this.p.treeReader.leaf_field] == 1 ? true : false);
				d[_this.p.treeReader.level_field] = res[_this.p.treeReader.level_field] + 1;
			});
			
		}
   	}
});

$.extend(true, $.fn.jqGrid, {
	expandNode : function(rc) {
		return this.each(function(){
			if(!this.grid || !this.p.treeGrid) {return;}
			var expanded = this.p.treeReader.expanded_field,
			parent = this.p.treeReader.parent_id_field,
			loaded = this.p.treeReader.loaded,
			level = this.p.treeReader.level_field,
			lft = this.p.treeReader.left_field,
			rgt = this.p.treeReader.right_field;

			if(!rc[expanded]) {
				var id = $.jgrid.getAccessor(rc,this.p.localReader.id);
				var rc1 = $("#"+$.jgrid.jqID(id),this.grid.bDiv)[0];
				var position = this.p._index[id];
				if( $(this).jqGrid("isNodeLoaded",this.p.data[position]) ) {
					rc[expanded] = true;
					$("div.treeclick",rc1).removeClass(this.p.treeIcons.plus+" tree-plus").addClass(this.p.treeIcons.minus+" tree-minus");
				} else if (!this.grid.hDiv.loading) {
					rc[expanded] = true;
					$("div.treeclick",rc1).removeClass(this.p.treeIcons.plus+" tree-plus").addClass(this.p.treeIcons.minus+" tree-minus");
					this.p.treeANode = rc1.rowIndex;
					this.p.datatype = this.p.treedatatype;
					if(this.p.treeGridModel == 'nested') {
						$(this).jqGrid("setGridParam",{postData:{nodeid:id,n_left:rc[lft],n_right:rc[rgt],n_level:rc[level]}});
					} else {
						var postData = {};
						postData[this.p.idField] = id;
						postData[level] = rc[level];
						$(this).jqGrid("setGridParam",{postData:postData} );
					}
					$(this).trigger("reloadGrid");
					rc[loaded] = true;
					if(this.p.treeGridModel == 'nested') {
						$(this).jqGrid("setGridParam",{postData:{nodeid:'',n_left:'',n_right:'',n_level:''}});
					} else {
						$(this).jqGrid("setGridParam",{postData:{nodeid:'',parentid:'',n_level:''}}); 
					}
				}
			}
		});
	},
	search: function(formId) {
		// 构建查询需要的参数
		var data = $(formId).serializeArray();
		var appendTime = {};
		$(formId).find("[appendTime]").each(function() {
			appendTime[$(this).attr("name")] = true;
		});;
		var json = {};
		$.each(data, function(i, d) {
			if(d.value != "" && appendTime[d.name]) {
				json["queryBean.params." + d.name] = d.value + " 23:59:59";
			} else {
				json["queryBean.params." + d.name] = d.value;
			}
		});
	      
	    // 获得当前postData选项的值  
	    var postData = this.jqGrid("getGridParam", "postData");  
	      
	    // 将查询参数融入postData选项对象  
	    $.extend(postData, json);  
		
		this.jqGrid("setGridParam", {  
	        search: true    // 将jqGrid的search选项设为true  
	    }).trigger("reloadGrid", [{page:1}]);   // 重新载入Grid表格，以使上述设置生效  
	},
	getData: function(rowid) {
		var data = $(this).data("data");
		if(data instanceof Array) {
			return $(this).data("data")[rowid - 1];
		} else {
			return $(this).data("data")[rowid];
		}
	},
	reloadChildren: function(rowid) {
		var p = this[0].p;
		var pos = p._index[rowid];
		var node = p.data[pos];
		var children = this.jqGrid("getNodeChildren", node);
		var _this = this;
		if(children.length == 0) {
			this.find("#" + rowid + " div[class*='treeclick']").removeClass("tree-leaf ui-icon-radio-off").addClass("ui-icon-triangle-1-e tree-plus");
			this.find("#" + rowid + " :checkbox[name=delete]").attr("disabled", "disabled");
			node.isLeaf = false;
			node.expanded = true;
		}
		$.each(children, function(i, d) {
			_this.jqGrid("delTreeNode", d._id_);
		});
		node.loaded = false;
		node.expanded = false;
		this.jqGrid("expandRow", node);
		this.jqGrid("expandNode", node);
	},
	deleteNode: function(rowid) {
		var p = this[0].p;
		var pos = p._index[rowid];
		var node = p.data[pos];
		var parent = this.jqGrid("getNodeParent", node);
		if(this.jqGrid("getNodeChildren", parent).length <= 1) {
			this.find("#" + parent._id_ + " div[class*='treeclick']").removeClass("ui-icon-triangle-1-e tree-plus").addClass("tree-leaf ui-icon-radio-off");
			this.find("#" + parent._id_ + " :checkbox[name=delete]").removeAttr("disabled");
			parent.isLeaf = true;
			parent.expanded = false;
		}
		this.jqGrid("delTreeNode", rowid);
	},
	getTopRowId: function() {
		return this.find("tr[id]:first").attr("id");
	}
});



$.extend(true, $.jgrid.nav, {
	//excel: false,
	excelicon:"ui-icon-search",
	exceltext: "导出excel",
	refreshtext: "刷新"
});


var FN_NAVGRID = $.fn.navGrid;
//扩展导航条, 增加导出excel按钮
$.fn.navGrid = function(elem, o, pEdit,pAdd,pDel,pSearch, pView) {
	var _this = this;
	var ret = FN_NAVGRID.call($(this), elem, o, pEdit,pAdd,pDel,pSearch, pView);
	if(o && o.excel) {
		o = $.extend(true, $.jgrid.nav, o);
		tbd = $("<td class='ui-pg-button ui-corner-all'></td>");
		pEdit = pEdit || {};
		$(tbd).append("<div class='ui-pg-div'><span class='ui-icon "+o.excelicon+"'></span>"+o.exceltext+"</div>");
		$("tr",$(elem).find("table[class*=navtable]")).append(tbd);
		$(tbd,$(elem).find("table[class*=navtable]"))
		.attr({"title":o.exceltext || "",id: pEdit.id || "edit_"+new Date().getTime()})
		.click(function(){
			if (!$(this).hasClass('ui-state-disabled')) {
				window.open(o.excel + "?" + $.param(_this.jqGrid("getGridParam", "postData")));
			}
			return false;
		}).hover(
			function () {
				if (!$(this).hasClass('ui-state-disabled')) {
					$(this).addClass("ui-state-hover");
				}
			},
			function () {$(this).removeClass("ui-state-hover");}
		);
		tbd = null;
	}
	return ret;
};

$.extend(true, $.fn.fmatter, {
	actions: function(cellval,opts) {
		var op ={keys:false, editbutton:true, delbutton:true, editformbutton: false};
		if(!$.fmatter.isUndefined(opts.colModel.formatoptions)) {
			op = $.extend(op,opts.colModel.formatoptions);
		}
		var rowid = opts.rowId, str="",ocl;
		if(typeof(rowid) =='undefined' || $.fmatter.isEmpty(rowid)) {return "";}
		if(op.editformbutton){
			ocl = "onclick=" + op.onedit + "('"+rowid+"','"+opts.gid+"','formedit',"+opts.pos+"); onmouseover=jQuery(this).addClass('ui-state-hover'); onmouseout=jQuery(this).removeClass('ui-state-hover'); ";
			str =str+ "<div title='"+$.jgrid.nav.edittitle+"' style='float:left;cursor:pointer;' class='ui-pg-div ui-inline-edit' "+ocl+"><span class='ui-icon ui-icon-pencil'></span></div>";
		} else if(op.editbutton){
			ocl = "onclick=" + op.onedit + "('"+rowid+"','"+opts.gid+"','edit',"+opts.pos+"); onmouseover=jQuery(this).addClass('ui-state-hover'); onmouseout=jQuery(this).removeClass('ui-state-hover') ";
			str =str+ "<div title='"+$.jgrid.nav.edittitle+"' style='float:left;cursor:pointer;' class='ui-pg-div ui-inline-edit' "+ocl+"><span class='ui-icon ui-icon-pencil'></span></div>";
		}
		if(op.delbutton) {
			ocl = "onclick=jQuery.fn.fmatter.rowactions_del('"+rowid+"','"+opts.gid+"','del',"+opts.pos+"); onmouseover=jQuery(this).addClass('ui-state-hover'); onmouseout=jQuery(this).removeClass('ui-state-hover'); ";
			str = str+"<div title='"+$.jgrid.nav.deltitle+"' style='float:left;margin-left:5px;' class='ui-pg-div ui-inline-del' "+ocl+"><span class='ui-icon ui-icon-trash'></span></div>";
		}
		ocl = "onclick=jQuery.fn.fmatter.rowactions('"+rowid+"','"+opts.gid+"','save',"+opts.pos+"); onmouseover=jQuery(this).addClass('ui-state-hover'); onmouseout=jQuery(this).removeClass('ui-state-hover'); ";
		str = str+"<div title='"+$.jgrid.edit.bSubmit+"' style='float:left;display:none' class='ui-pg-div ui-inline-save' "+ocl+"><span class='ui-icon ui-icon-disk'></span></div>";
		ocl = "onclick=jQuery.fn.fmatter.rowactions('"+rowid+"','"+opts.gid+"','cancel',"+opts.pos+"); onmouseover=jQuery(this).addClass('ui-state-hover'); onmouseout=jQuery(this).removeClass('ui-state-hover'); ";
		str = str+"<div title='"+$.jgrid.edit.bCancel+"' style='float:left;display:none;margin-left:5px;' class='ui-pg-div ui-inline-cancel' "+ocl+"><span class='ui-icon ui-icon-cancel'></span></div>";
		return "<div style='margin-left:8px;'>" + str + "</div>";
	},
	tobr: function(cellval,opts) {
		if(!cellval) {
			return "";
		}
		return cellval.replace(/\n/g, "<br>");
	},
	organization: function(cellval,opts) {
		return Organization.getOrgNameBySid(cellval);
	},
	parentArea: function(cellval,opts) {
		var _parentArea = Area.getFormatArea(cellval);
		return _parentArea;
	},
	formatOrganizationProvince: function(cellval,opts) {
		var _organizationProvince = organizationProvince.getFormatOrganizationProvince(cellval);
		return _organizationProvince;
	},
	formatOrganizationType: function(cellval,opts) {
		var _organizationType = organizationType.getFormatOrganizationType(cellval);
		return _organizationType;
	},
	formatPersonnel: function(cellval,opts) {
		var _personnel = Personnel.getFormatPersonnel(cellval);
		return _personnel;
	},
	param: function(cellval,opts) {
		return getTypeName(opts.colModel.table, cellval);
	}
});

jQuery.fn.fmatter.rowactions_del = function(rowid, gid, mode, pos) {
	var id = $("#" + gid).jqGrid("getData", rowid)[$("#" + gid)[0].p.delOptions.id];
	jQuery.fn.fmatter.rowactions(id, gid, mode, pos);
};

$.extend(true, $.jgrid.del, {
	top: $(window).height() / 2 - 100,
	left: $(window).width() / 2 - 120,
	width: 240,
	afterComplete: function(d) {
		var data = {};
		try {
			data = eval("(" + d.responseText + ")");
		} catch(e) {
			$.error("删除失败: 内部出错.");
			return;
		}
		if(data.responseMessage.success) {
			$.success("删除成功");
		} else {
			$.error("删除失败: " + data.responseMessage.message);
		}
	},
   	serializeDelData: function(d) {
   		var data = {};
   		data[this.p.delOptions.id] = d["id"];
   		return data;
   	}
});


$.extend(true, $.jgrid.nav, {
	edit:false,add:false,del:false,search:false,refresh: true
});


//button post扩展, 提交ajax会锁定按钮
$.fn.post = function(url, data, callback) {
	var _this = this;
	$(_this).closest("button").button("disable");
	$.ajax({
	   type: "POST",
	   url: url,
	   data: data,
	   complete: function() {
		   $(_this).closest("button").button("enable");
	   },
	   success: function(data){
		   callback(data);
	   }
	});
};

//button ajax扩展, 提交ajax会锁定按钮
$.fn.ajax = function(json) {
	var _this = this;
	$(_this).closest("button").button("disable");
	
	var t_complete = json.complete;
	json.complete = function(xhr, ts) {
		$(_this).closest("button").button("enable");
		t_complete(xhr, ts);
	};
	
	$.ajax(json);
};

$.success = function(msg) {
	var alertDiv = top.$('<div name="alertMessage" class="alert alert-success" style="width: 0; white-space:nowrap; position: absolute; top: 0px; z-index: 9999; display: none;"><button type="button" class="close" data-dismiss="alert">×</button>' + msg + '</div>');
	top.$("div[name=alertMessage]").fadeOut("slow");
	top.$("body").append(alertDiv);
//	$("div[name=alertMessage]").fadeOut("slow");
//	$("body").append(alertDiv);
	alertDiv.css("left", $(top).width() / 2 - alertDiv.width() / 2);
//	alertDiv.css("left", $(window).width() / 2 - alertDiv.width() / 2);
	alertDiv.fadeIn("fast").delay(5000).fadeOut("slow");
	alertDiv.width(alertDiv.width() + 80);
};

$.warn = function(msg, delay) {
	delay = delay || 5000;
	var alertDiv = top.$('<div name="alertMessage" class="alert" style="width: 0; white-space:nowrap; position: absolute; top: 0px; z-index: 9999; display: none;"><button type="button" class="close" data-dismiss="alert">×</button>' + msg + '</div>');
	top.$("div[name=alertMessage]").fadeOut("slow");
	top.$("body").append(alertDiv);
//	$("div[name=alertMessage]").fadeOut("slow");
//	$("body").append(alertDiv);
	alertDiv.css("left", $(top).width() / 2 - alertDiv.width() / 2);
//	alertDiv.css("left", $(window).width() / 2 - alertDiv.width() / 2);
	if(delay > 0) {
		alertDiv.fadeIn("fast").delay(delay).fadeOut("slow");
	} else {
		alertDiv.fadeIn("fast");
	}
	alertDiv.width(alertDiv.width() + 80);
};

$.error = function(msg, stackTrace) {
	var alertDiv = top.$('<div name="alertMessage" class="alert alert-error" style="width: 0; white-space:nowrap; position: absolute; top: 0px; z-index: 9999; display: none;"><button type="button" class="close" data-dismiss="alert">×</button>' + msg + '</div>');
	
	if(stackTrace) {
		var trace = top.$("<a href='#'>[跟踪]</a>");
		trace.click(function() {
			var win = top.open("", "", "width=800,height=600,location=0,top=50,left=100");
			win.document.write("<html><head><title>跟踪异常</title></head><body style='padding:0;'><textarea style='width:100%; height:100%; margin:0;'>" + stackTrace + "</textarea></body></html>");
		});
		alertDiv.append(trace);
	}
	top.$("div[name=alertMessage]").fadeOut("slow");
	top.$("body").append(alertDiv);
	
	if(alertDiv.width() > $(top).width() - 150) {
		alertDiv.width($(top).width() - 80);
		alertDiv.css("white-space", "normal");
	} else {
		alertDiv.width(alertDiv.width() + 80);
	}
	
	alertDiv.css("left", $(top).width() / 2 - alertDiv.width() / 2);
	alertDiv.fadeIn("fast");
};


//将data填充进form
$.fn.fillForm = function(data) {
	var args = arguments;
	$(this).find("input:text,input:hidden,select,textarea").each(function() {
		if(!$(this).is("[notfill]")){
			var value = null, name = $(this).attr("name");
			if(name != null) {
				if(name.indexOf(",") != -1) {
					name = name.split(",");
				}
				for(var i = 0; i < args.length; i++) {
					if(args[i]) {
						if(name instanceof Array) {
							for(var j = 0; j < name.length; j++) {
								var names = name[j].split(".");
								var t = args[i];
								for(var k = 0; k < names.length; k++) {
									if(t) {
										t = t[names[k]];
									}
								}
								value = t;
								if(value != null) {
									break;
								}
							}
						} else {
							var names = name.split(".");
							var t = args[i];
							for(var k = 0; k < names.length; k++) {
								if(t) {
									t = t[names[k]];
								}
							}
							value = t;
						}
						
						if(value != null) {
							break;
						}
					}
				}
				if(value != null) {
					if($(this).is("[organization]")) {
						value = Organization.getOrgNameBySid(value);
					} else if($(this).is("[param]")) {
						value = getTypeName($(this).attr("param"), value);
					}
					$(this).val(value);
				}
				
				if($(this).is("select") || $(this).attr("willchange")) {
					$(this).change();
				}
			}
		}
	});
};




//序列化表单成json
$.fn.serializeForm = function() {
	var json = $(this).serializeArray();
	var data = {};
	$.each(json, function(i, d) {
		if(d.name.indexOf("notserialize") == -1) {
			var names = d.name.split(",");
			$.each(names, function(index, name) {
				data[name] = d.value;
			});
		}
	});
	return data;
};

var LoginPeople = {
	getOrgSid: function() {
		return top.loginPeopleInfo.orgSid;
	},
	getPeopleSid: function() {
		return top.loginPeopleInfo.peopleSid;
	},
	getPeopleCode: function() {
		return top.loginPeopleInfo.peopleCode;
	},
	getPeopleName: function() {
		return top.loginPeopleInfo.peopleName;
	},
	getOrganizationNo:function(){
		return top.loginPeopleInfo.orgNo;
	},
	getOrgType: function(){
		return top.loginPeopleInfo.orgType;
	},
	getPersonnelGenre:function(){
		return  top.loginPeopleInfo.personnelGenre
	},
	getHeadLevel:function(){
		return top.loginPeopleInfo.headLevel;
	},
	getPersonnelCard:function(){
		return  top.loginPeopleInfo.personnelCard;
	},
	getAreaSid:function(){
		return  top.loginPeopleInfo.areaSid;
	}
};

$.getContextPath = function() {
	return top.ctx;
};

$.onunload = function(func) {
	window.onunload = func;
	window._onunload = func;
};


/**       
 * 对Date的扩展，将 Date 转化为指定格式的String       
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符       
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)       
 * eg:       
 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423       
 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04       
 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04       
 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04       
 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18       
 */          
Date.prototype.pattern=function(fmt) {           
    var o = {           
    "M+" : this.getMonth()+1, //月份           
    "d+" : this.getDate(), //日           
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时           
    "H+" : this.getHours(), //小时           
    "m+" : this.getMinutes(), //分           
    "s+" : this.getSeconds(), //秒           
    "q+" : Math.floor((this.getMonth()+3)/3), //季度           
    "S" : this.getMilliseconds() //毫秒           
    };           
    var week = {           
    "0" : "/u65e5",           
    "1" : "/u4e00",           
    "2" : "/u4e8c",           
    "3" : "/u4e09",           
    "4" : "/u56db",           
    "5" : "/u4e94",           
    "6" : "/u516d"          
    };           
    if(/(y+)/.test(fmt)){           
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));           
    }           
    if(/(E+)/.test(fmt)){           
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);           
    }           
    for(var k in o){           
        if(new RegExp("("+ k +")").test(fmt)){           
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));           
        }           
    }           
    return fmt;           
};

//$._ajax = $.ajax;
//$.ajax = function(url, settings) {
//	if (typeof url === "object" ) {
//		settings = url;
//	} else {
//		settings.url = url;
//	}
//	var success = settings.success;
//	var error = settings.error;
//	$.extend(true, settings, {
//		error: function(xhr, ajaxOptions, thrownError) {
//			if(!(error === false)) {
//				try {
//					var json = eval("(" + xhr.responseText + ")");
//					if(xhr.status == 403) {
//						alert(json.responseMessage.message + "\n" + "点【确定】退出系统");
//						top.logout();
//						return;
//					} else {
//						$.error(json.responseMessage.message);
//					}
//				} catch (e) {
//					$.error("服务器异常<br>Http错误: " + xhr.status + " " + xhr.statusText + "<br>ajaxOptions: " + ajaxOptions + "<br>thrownError:"+thrownError + "<br>" +xhr.responseText);
//				}
//				if(error) {
//					error.call(this, xhr, ajaxOptions, thrownError);
//				}
//			}
//		}
//	});
//	return $._ajax(settings);
//};