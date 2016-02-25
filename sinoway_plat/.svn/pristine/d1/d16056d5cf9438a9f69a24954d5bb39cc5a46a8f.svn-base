var v_orgSid;
var v_orgShowInfo;
var Organization = {
    // 获得所有下级机构
    getChildren : function(sid, includeSelf) {
        var children = [];
        $.ajax({
            type : "POST",
            url : top.ctx + "/wf/ext/po/extOrganizationAction_findAllChildOrganization.action",
            data : {
                sid : sid,
                result : includeSelf ? "1" : "0"
            },
            dataType : "json",
            async : false,
            success : function(data) {
                $.each(data.organizations, function(index, organization) {
                    organization.organizationName = organization.organizationName + "(" + organization.organizationNo + ")";
                });
                children = data.organizations;
            }
        });
        return children;
    },
    // 获得所有上级机构
    getAncestor : function(sid, includeSelf) {
        var ancestor = [];
        $.ajax({
            type : "POST",
            url : top.ctx + "/wf/ext/po/extOrganizationAction_findAllParentOrganization.action",
            data : {
                sid : sid,
                result : includeSelf ? "1" : "0"
            },
            dataType : "json",
            async : false,
            success : function(data) {
                $.each(data.organizations, function(index, organization) {
                    organization.organizationName = organization.organizationName + "(" + organization.organizationNo + ")";
                });
                ancestor = data.organizations;
            }
        });
        return ancestor;
    },
    // 获得所有机构
    getAll : function() {
        var all = [];
        $.ajax({
            type : "POST",
            url : top.ctx + "/wf/ext/po/extOrganizationAction_findAllOrganization.action",
            dataType : "json",
            async : false,
            success : function(data) {
                $.each(data.organizations, function(index, organization) {
                    organization.organizationName = organization.organizationName + "(" + organization.organizationNo + ")";
                });
                all = data.organizations;
            }
        });
        return all;
    },
    getOrganization : function(sid) {
        if (!sid || sid == "") {
            return {};
        }
    	var organization = null;
        $.ajax({
            type : "POST",
            url : top.ctx + "/wf/ext/po/extOrganizationAction_findOrganizationByOrganizationSid.action",
            data : {
                sid : sid
            },
            dataType : "json",
            async : false,
            success : function(data) {
                if (data.organization) {
                    organization = data.organization;
                    organization.organizationName = organization.organizationName + "(" + organization.organizationNo + ")";
                }
            }
        });
        return organization || {
            organizationName : "该机构可能已被删除",
            organizationNo : "该机构可能已被删除"
        };
    },
    getOrgNameBySid : function(sid) {
        var organizationName = Organization.getOrganization(sid).organizationName ? Organization.getOrganization(sid).organizationName : ""
        if (organizationName == "root(root)") {
            organizationName = "  ";
        }
        return organizationName;
    },
    getOrgNoBySid : function(sid) {
        return Organization.getOrganization(sid).organizationNo ? Organization.getOrganization(sid).organizationNo : "";
    },
    getParentOrganization : function(sid) {
        var organization = null;
        $.ajax({
            type : "POST",
            url : $.getContextPath() + "/wf/ext/po/extOrganizationAction_findParentOrganizationByOrganizationSid.action",
            data : {
                sid : sid
            },
            dataType : "json",
            async : false,
            success : function(data) {
                if (data.organization) {
                    organization = data.organization;
                }
            }
        });
        return organization;
    },
    getParentOrganizationSid : function(sid) {
        var organizationSid = Organization.getParentOrganization(sid).sid ? Organization.getParentOrganization(sid).sid : ""
        return organizationSid;
    }
};

(function($) {
    /*
     * targetOrgSidId: "存放选中的机构的sid",
     * level: all/children/ancestor,
     * orgSid: 顶级orgsid,
     * includeSelf: 是否显示顶级 true/false,
     * canCheckOrgtype: 配置哪些orgtype能在机构树勾选 , "1"/"12345",
     * changeCallback: 改变了orgsid回调函数, 回传orgsid
     */
    $.fn.organizationTree = function(targetOrgSidId, level, orgSid, includeSelf, canCheckOrgtype, changeCallback) {
        var orgData, expand = false;
        if (!level || level == "all") {
            orgData = Organization.getAll();

            if (!canCheckOrgtype) {
                canCheckOrgtype = "12345";
            }
            // 只能勾选网点 orgtype=1
            $.each(orgData, function(i, d) {
                if (canCheckOrgtype.indexOf(d.organizationType) == -1) {
                    d.nocheck = true;
                }
            });

        } else if (level == "children") {
            orgData = Organization.getChildren(orgSid, includeSelf);

            if (!canCheckOrgtype) {
                canCheckOrgtype = "1";
            }
            // 只能勾选网点 orgtype=1
            $.each(orgData, function(i, d) {
                if (canCheckOrgtype.indexOf(d.organizationType) == -1) {
                    d.nocheck = true;
                }
                if (d.organizationType == "4") {
                    v_orgSid = d.sid;
                    v_orgShowInfo = d.organizationName + "(" + d.organizationNo + ")";
                }
            });

            expand = true;
        } else if (level == "ancestor") {
            orgData = Organization.getAncestor(orgSid, includeSelf);

            if (!canCheckOrgtype) {
                canCheckOrgtype = "12345";
            }
            // 只能勾选网点 orgtype=1
            $.each(orgData, function(i, d) {
                if (canCheckOrgtype.indexOf(d.organizationType) == -1) {
                    d.nocheck = true;
                }
            });

            expand = true;
        }

        var targetOrgSidIdArray = targetOrgSidId.split(",");
        this.each(function(i, d) {
            var _this = this;
            var id = "" + new Date().getTime() + parseInt(Math.random() * 1000);
            $(_this).data("id", id);
            $(_this).data("targetOrgSidId", targetOrgSidIdArray[i]);
            var treeDom = $('<ul id="tree' + id + '" class="ztree" style="margin-top: 10px;border: 1px solid #617775;background: white;width: 200px;height: 200px;overflow-y: scroll;overflow-x: auto;"></ul>');
            var menuDom = $('<div id="menu' + id + '" style="display:none; position: absolute; z-index: 9999;"></div>').append(treeDom);
            $("body").append(menuDom);
            if ($.fn.bgiframe) {
                menuDom.bgiframe();
            }

            var tree = $.fn.zTree.init(treeDom, {
                check : {
                    enable : true,
                    chkStyle : "radio",
                    radioType : "all"
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
                        var zTree = $.fn.zTree.getZTreeObj(treeId);
                        //zTree.checkNode(treeNode, !treeNode.checked, null, true);
                        zTree.checkNode(treeNode, false, null);
                        zTree.checkNode(treeNode, true, null, true);
                        return false;
                    },
                    onCheck : function(e, treeId, treeNode) {
                        var zTree = $.fn.zTree.getZTreeObj(treeId);
                        $(_this).val(treeNode.organizationName).blur();
                        $("#" + targetOrgSidId).val(treeNode.sid);
                        if (changeCallback) {
                            changeCallback(treeNode.sid);
                        }
                        zTree.selectNode(treeNode, false);
                        hideMenu(treeId.replace("tree", "menu"));
                    }
                }
            }, orgData);
            if (expand) {
                tree.expandAll(true);
            }
            $(_this).data("tree", tree);
            $(_this).focus(showMenu);
            $("#tree" + id).find("ul[class*=level0]").css("overflow", "");

            $(_this).change(function(e) {
                if ($(_this).val() != "") {
                    var tree = $(_this).data("tree");
                    //输入orgno得到orgname
                    var nodes = tree.getNodesByParam("orgNo", $(_this).val());
                    if (nodes.length > 0) {
                        if (nodes[0].nocheck) {
                            alert("不能选择该机构");
                            $(_this).val("");
                            setTimeout(function() {
                                $(_this).focus();
                            }, 1);
                        } else {
                            tree.selectNode(nodes[0]);
                            tree.checkNode(nodes[0], true);
                            $("#" + targetOrgSidId).val(nodes[0].sid);
                            if (changeCallback) {
                                changeCallback(nodes[0].sid);
                            }
                            $(_this).val(nodes[0].orgName);
                            hideMenu("menu" + $(_this).data("id"));
                        }
                    } else {
                        alert("无该机构");
                        $(_this).val("");
                        setTimeout(function() {
                            $(_this).focus();
                        }, 1);
                    }
                } else {
                    $("#" + targetOrgSidId).val("");
                    if (changeCallback) {
                        changeCallback("");
                    }
                }
            });

        });

    };

    // 树相关
    function showMenu(event) {
        var menu = $("#menu" + $(event.target).data("id"));
        var targetOrgSidId = $(event.target).data("targetOrgSidId");
        if ($("#" + targetOrgSidId).val() == "") {
            var tree = $.fn.zTree.getZTreeObj("tree" + $(event.target).data("id"));
            var selectedNodes = tree.getSelectedNodes();
            if (selectedNodes.length > 0) {
                tree.checkNode(selectedNodes[0], false);
                tree.cancelSelectedNode(selectedNodes[0]);
            }
        }

        var orgOffset = $(event.target).offset();
        menu.css({
            left : orgOffset.left + "px",
            top : orgOffset.top + $(event.target).outerHeight() + "px"
        }).slideDown("fast");
        $("html").bind("mousedown", onBodyDown).data("menuId", "menu" + $(event.target).data("id"));

    }

    function hideMenu(menuId) {
        $("#" + menuId).fadeOut("fast");
        // $("#" + menuId).hide();
        $("html").unbind("mousedown", onBodyDown);
    }

    function onBodyDown(event) {
        if (!(event.target.id == $("html").data("menuId") || $(event.target).parents("#" + $("html").data("menuId")).length > 0)) {
            hideMenu($("html").data("menuId"));
        }
    }

})(jQuery);
