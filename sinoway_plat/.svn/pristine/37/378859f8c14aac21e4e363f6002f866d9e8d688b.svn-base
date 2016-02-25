DELETE FROM am_rolegrouprole;
DELETE FROM am_rolegrouppeople;
DELETE FROM am_rolepower;
DELETE FROM am_powerinfo;
DELETE FROM po_peopleinfo;
DELETE FROM po_organizeinfo;
DELETE FROM po_orgtyperelate;
DELETE FROM po_orgtype;
DELETE FROM mm_moduleinfo;
DELETE FROM am_roleinfo;
DELETE FROM am_rolegroupinfo;
DELETE FROM cache_mark;
DELETE FROM pam_system_universal; 

INSERT INTO po_orgtype (sid,orgtypename, orgtypestate, memo, tag) VALUES ('49E4BD36442189FAA2EF4CC1EF2F_004', '网点', 0, '网点', '');
INSERT INTO po_orgtype (sid,orgtypename,orgtypestate,memo,tag) VALUES ('65A2D56EDB3E4C14AF8858AF2C80188C','总行',0,'总行','');
INSERT INTO po_orgtype (sid,orgtypename,orgtypestate,memo,tag) VALUES ('D51E99BE443892600D643F09F63F_001','省级分行',0,'省级分行','');
INSERT INTO po_orgtype (sid,orgtypename,orgtypestate,memo,tag) VALUES ('D5AFC14640D3A4A0496D7E46D0E7_003','营业部',0,'营业部','');
INSERT INTO po_orgtype (sid,orgtypename,orgtypestate,memo,tag) VALUES ('DB009F5340209EF5DDEE83AEC206_002','支行',0,'支行','');

INSERT INTO po_orgtyperelate (parentorgtype, childorgtype, countlimit) VALUES ('65A2D56EDB3E4C14AF8858AF2C80188C','D51E99BE443892600D643F09F63F_001',-1);
INSERT INTO po_orgtyperelate (parentorgtype, childorgtype, countlimit) VALUES ('D51E99BE443892600D643F09F63F_001','D5AFC14640D3A4A0496D7E46D0E7_003',1);
INSERT INTO po_orgtyperelate (parentorgtype, childorgtype, countlimit) VALUES ('D51E99BE443892600D643F09F63F_001','DB009F5340209EF5DDEE83AEC206_002',-1);
INSERT INTO po_orgtyperelate (parentorgtype, childorgtype, countlimit) VALUES ('DB009F5340209EF5DDEE83AEC206_002','49E4BD36442189FAA2EF4CC1EF2F_004', -1);

INSERT INTO po_organizeinfo (sid,orgname,orgno,orgtype,orgstate,parentorg,managetype,includechild,memo,tag,orgLevel,orgPath,orgProvince,orgArea,manageOrgFlag,bizOrgFlag) 
VALUES ('00000000000000000000000000000000','总行', 'root','65A2D56EDB3E4C14AF8858AF2C80188C',0,null,0, 1,'总行','', 0,'|root|',11,'000000',0,0);


INSERT INTO po_peopleinfo (sid,peoplecode,peoplename,peoplegender,pwd,peoplestate,organizeinfo,defaultdesktop,memo,tag,defaultconfig,lastchangepwdtime,passwordErrCount,recentPasswordRecord) 
VALUES ('7B92AE0FC4B04DB48F1AFBDB22CD7188','super','超级管理员',0,'62b7cfe4e47c0d6198eed1e1ddd7ae40',0,'00000000000000000000000000000000',0, '超级管理员',' ',' ',null,0,' ');

INSERT INTO mm_moduleinfo (sid, parent_sid,name,entrypoint_url,entrypoint_type,showname,hotkeystr,order_id,module_level,tag,state,istaskview,isNavigation)
VALUES ('00000000000000000000000000000000',NULL,'银之杰公共平台','http://[server]:[port]/[context]/','url','银之杰公共平台','001',1,0,'',1,0,0);
INSERT INTO mm_moduleinfo (sid, parent_sid,name,entrypoint_url,entrypoint_type,showname,hotkeystr,order_id,module_level,tag,state,istaskview,isNavigation)
VALUES ('000000000system_manage_module_id','00000000000000000000000000000000','系统管理','http://[server]:[port]/[context]/windforce/am/','url','系统管理','004',2,1,'',1,0,1);
INSERT INTO mm_moduleinfo (sid, parent_sid,name,entrypoint_url,entrypoint_type,showname,hotkeystr,order_id,module_level,tag,state,istaskview,isNavigation)
VALUES ('00000org_people_manage_module_id','000000000system_manage_module_id','机构人员管理','http://[server]:[port]/[context]/windforce/po/index.jsp?cud=1111111111111','url','机构人员管理','002',7,2,'',1,0,1);
INSERT INTO mm_moduleinfo (sid, parent_sid,name,entrypoint_url,entrypoint_type,showname,hotkeystr,order_id,module_level,tag,state,istaskview,isNavigation)
VALUES ('000000000module_manage_module_id','000000000system_manage_module_id','模块管理','http://[server]:[port]/[context]/windforce/mm/moduleManager.jsp?cud=111','url','模块管理','003',4,2,'',1,0,1);
INSERT INTO mm_moduleinfo (sid, parent_sid,name,entrypoint_url,entrypoint_type,showname,hotkeystr,order_id,module_level,tag,state,istaskview,isNavigation)
VALUES ('00000000000role_manage_module_id','000000000system_manage_module_id','角色管理','http://[server]:[port]/[context]/roles_initRole.action?cud=1111','url','角色管理','005',5,2,'',1,0,1);
INSERT INTO mm_moduleinfo (sid, parent_sid,name,entrypoint_url,entrypoint_type,showname,hotkeystr,order_id,module_level,tag,state,istaskview,isNavigation)
VALUES ('00000role_group_manage_module_id','000000000system_manage_module_id','岗位管理','http://[server]:[port]/[context]/rolegroup_initRoleGroup.action?cud=1111','url','岗位管理','006',6,2,'',1,0,1);
INSERT INTO mm_moduleinfo (sid, parent_sid,name,entrypoint_url,entrypoint_type,showname,hotkeystr,order_id,module_level,tag,state,istaskview,isNavigation)
VALUES ('000000000000log_manage_module_id','00000000000000000000000000000000','日志管理','http://[server]:[port]/[context]/windforce/log/','url','日志管理','004',3,1,'',1,0,1);

INSERT INTO am_powerinfo (sid,powername,powerstate,parentpower,resourceid,orderid,memo,tag) 
VALUES ('00000000000000000000000000000000','银之杰公共平台',0,NULL,'00000000000000000000000000000000',1,'银之杰公共平台','');
INSERT INTO am_powerinfo (sid,powername,powerstate,parentpower,resourceid,orderid,memo,tag)
VALUES ('0000000000system_manage_power_id','系统管理',0,'00000000000000000000000000000000','000000000system_manage_module_id',2,'系统管理','');
INSERT INTO am_powerinfo (sid,powername,powerstate,parentpower,resourceid,orderid,memo,tag)
VALUES ('000000org_people_manage_power_id','机构人员管理',0,'0000000000system_manage_power_id','00000org_people_manage_module_id',7,'机构人员管理','');
INSERT INTO am_powerinfo (sid,powername,powerstate,parentpower,resourceid,orderid,memo,tag)
VALUES ('0000000000module_manage_power_id','模块管理',0,'0000000000system_manage_power_id','000000000module_manage_module_id',4,'模块管理','');
INSERT INTO am_powerinfo (sid,powername,powerstate,parentpower,resourceid,orderid,memo,tag)
VALUES ('000000000000role_manage_power_id','角色管理',0,'0000000000system_manage_power_id','00000000000role_manage_module_id',5,'角色管理','');
INSERT INTO am_powerinfo (sid,powername,powerstate,parentpower,resourceid,orderid,memo,tag)
VALUES ('000000role_group_manage_power_id','岗位管理',0,'0000000000system_manage_power_id','00000role_group_manage_module_id',6,'岗位管理','');
INSERT INTO am_powerinfo (sid,powername,powerstate,parentpower,resourceid,orderid,memo,tag)
VALUES ('0000000000000log_manage_power_id','日志管理',0,'00000000000000000000000000000000','000000000000log_manage_module_id',3,'日志管理','');

INSERT INTO am_roleinfo (sid,rolename,rolestate,orderid,memo,tag)
VALUES ('superrole','超级管理员',0,'0','内置账户[不能删除]','');

INSERT INTO am_rolegroupinfo (sid,rolegroupname,rolegroupstate,orderid,memo,tag)
VALUES ('supergroup','超级管理员组',0,'5','超级管理员组','');

INSERT INTO am_rolegrouprole (rolegroupsid, rolesid) VALUES ('supergroup', 'superrole');

INSERT INTO am_rolegrouppeople (peoplesid, rolegroupsid) VALUES ('7B92AE0FC4B04DB48F1AFBDB22CD7188', 'supergroup');

INSERT INTO am_rolepower (rolesid, powersid) VALUES ('superrole', '00000000000000000000000000000000');
INSERT INTO am_rolepower (rolesid, powersid) VALUES ('superrole', '0000000000system_manage_power_id');
INSERT INTO am_rolepower (rolesid, powersid) VALUES ('superrole', '0000000000module_manage_power_id');
INSERT INTO am_rolepower (rolesid, powersid) VALUES ('superrole', '000000000000role_manage_power_id');
INSERT INTO am_rolepower (rolesid, powersid) VALUES ('superrole', '000000role_group_manage_power_id');
INSERT INTO am_rolepower (rolesid, powersid) VALUES ('superrole', '000000org_people_manage_power_id');

--原init_cache.sql数据--
insert into cache_mark (clusterNode,cacheName,available) values('serverA','ModuleInfoCache',1);
insert into cache_mark (clusterNode,cacheName,available) values('serverA','PowerInfoCache',1);
insert into cache_mark (clusterNode,cacheName,available) values('serverA','RoleGroupInfoCache',1);
insert into cache_mark (clusterNode,cacheName,available) values('serverA','RoleInfoCache',1);
insert into cache_mark (clusterNode,cacheName,available) values('serverA','OrgTypeCache',1);
insert into cache_mark (clusterNode,cacheName,available) values('serverA','OrganizeInfoCache',1);
insert into cache_mark (clusterNode,cacheName,available) values('serverA','PeopleInfoCache',1);
insert into cache_mark (clusterNode,cacheName,available) values('serverA','RoleGroupPeopleCache',1);
insert into cache_mark (clusterNode,cacheName,available) values('serverB','ModuleInfoCache',1);
insert into cache_mark (clusterNode,cacheName,available) values('serverB','PowerInfoCache',1);
insert into cache_mark (clusterNode,cacheName,available) values('serverB','RoleGroupInfoCache',1);
insert into cache_mark (clusterNode,cacheName,available) values('serverB','RoleInfoCache',1);
insert into cache_mark (clusterNode,cacheName,available) values('serverB','OrgTypeCache',1);
insert into cache_mark (clusterNode,cacheName,available) values('serverB','OrganizeInfoCache',1);
insert into cache_mark (clusterNode,cacheName,available) values('serverB','PeopleInfoCache',1);
insert into cache_mark (clusterNode,cacheName,available) values('serverB','RoleGroupPeopleCache',1);

--以下为1.8版本新增数据--
--转授权--
INSERT INTO mm_moduleinfo (sid,parent_sid,name,entrypoint_url,entrypoint_type,showname,isNavigation,istaskview,hotkeystr,order_id,module_level,tag,state) 
VALUES ('00000000000000transfer_module_id', '000000000system_manage_module_id', '转授权', 'http://[server]:[port]/[context]/transferPowerInfoAction_showListPage.action', 'url', '转授权', 1, 0, '1008', 11, 2, null, 1);

INSERT INTO am_powerinfo (sid,powername,powerstate,parentpower,resourceid,orderid,memo,tag) 
VALUES ('000000000000000transfer_power_id', '转授权', '0', '0000000000system_manage_power_id', '00000000000000transfer_module_id', 11, '转授权', null);
INSERT INTO am_powerinfo (sid,powername,powerstate,parentpower,resourceid,orderid,memo,tag) 
VALUES ('00000000000get_transfer_power_id', '接受转授权', '0', '0000000000system_manage_power_id', '4BDAEB988B7D44148D06E53A1BFEBE38', 12, '接受转授权', null);

INSERT INTO am_roleinfo (sid,rolename,rolestate,orderid,memo,tag) 
VALUES ('0000000000000000transfer_role_id', '转授权', '0', '3', '有转授权且不对该角色权限执行转授权', null);

INSERT INTO am_rolegroupinfo (sid,rolegroupname,rolegroupstate,orderid,memo,tag) 
VALUES ('000000000000000transfer_group_id', '转授权', '0', '8', '有转授权且不对该岗位权限执行转授权', null);

INSERT INTO am_rolegrouprole (rolegroupsid,rolesid) 
VALUES ('000000000000000transfer_group_id', '0000000000000000transfer_role_id');

INSERT INTO am_rolepower (rolesid,powersid) VALUES ('0000000000000000transfer_role_id', '00000000000000000000000000000000');
INSERT INTO am_rolepower (rolesid,powersid) VALUES ('0000000000000000transfer_role_id', '0000000000system_manage_power_id');
INSERT INTO am_rolepower (rolesid,powersid) VALUES ('0000000000000000transfer_role_id', '000000000000000transfer_power_id');

--人员流程审批--
INSERT INTO mm_moduleinfo (sid,parent_sid,name,entrypoint_url,entrypoint_type,showname,isNavigation,istaskview,hotkeystr,order_id,module_level,tag,state) 
VALUES ('00000000return_to_edit_module_id', '000000000system_manage_module_id', '退回修改', 'http://[server]:[port]/[context]/approvePeopleAction_initReturnPage.action', 'url', '退回修改', 1, 0, '1010', 8, 2, null, 1);
INSERT INTO mm_moduleinfo (sid,parent_sid,name,entrypoint_url,entrypoint_type,showname,isNavigation,istaskview,hotkeystr,order_id,module_level,tag,state) 
VALUES ('00000000approve_reject_module_id', '000000000system_manage_module_id', '审批拒绝', 'http://[server]:[port]/[context]/approvePeopleAction_initRejectPage.action', 'url', '审批拒绝', 1, 0, '1011', 9, 2, null, 1);
INSERT INTO mm_moduleinfo (sid,parent_sid,name,entrypoint_url,entrypoint_type,showname,isNavigation,istaskview,hotkeystr,order_id,module_level,tag,state) 
VALUES ('00000000approve_people_module_id', '000000000system_manage_module_id', '人员审批', 'http://[server]:[port]/[context]/approvePeopleAction_initApprovePage.action', 'url', '人员审批', 1, 0, '1009', 10, 2, null, 1);

INSERT INTO am_powerinfo (sid,powername,powerstate,parentpower,resourceid,orderid,memo,tag) 
VALUES ('000000000approve_people_power_id', '人员审批', '0', '0000000000system_manage_power_id', '00000000approve_people_module_id', 8, '人员审批', null);
INSERT INTO am_powerinfo (sid,powername,powerstate,parentpower,resourceid,orderid,memo,tag) 
VALUES ('000000000approve_reject_power_id', '审批拒绝', '0', '0000000000system_manage_power_id', '00000000approve_reject_module_id', 9, '审批拒绝', null);
INSERT INTO am_powerinfo (sid,powername,powerstate,parentpower,resourceid,orderid,memo,tag) 
VALUES ('000000000return_to_edit_power_id', '退回修改', '0', '0000000000system_manage_power_id', '00000000return_to_edit_module_id', 10, '退回修改', null);

--日志--
INSERT INTO mm_moduleinfo (sid,parent_sid,name,entrypoint_url,entrypoint_type,showname,isNavigation,istaskview,hotkeystr,order_id,module_level,tag,state) 
VALUES ('0000000000000login_log_module_id', '000000000000log_manage_module_id', '用户登录日志', 'http://[server]:[port]/[context]/logLogin_initPage.action', 'url', '用户登录日志', 1, 0, '1012', 13, 2, null, 1);
INSERT INTO mm_moduleinfo (sid,parent_sid,name,entrypoint_url,entrypoint_type,showname,isNavigation,istaskview,hotkeystr,order_id,module_level,tag,state) 
VALUES ('00000000org_manage_log_module_id', '000000000000log_manage_module_id', '机构管理日志', 'http://[server]:[port]/[context]/logOrgManage_initPage.action', 'url', '机构管理日志', 1, 0, '1013', 14, 2, null, 1);
INSERT INTO mm_moduleinfo (sid,parent_sid,name,entrypoint_url,entrypoint_type,showname,isNavigation,istaskview,hotkeystr,order_id,module_level,tag,state) 
VALUES ('00000people_manage_log_module_id', '000000000000log_manage_module_id', '人员管理日志', 'http://[server]:[port]/[context]/logPeopleManage_initPage.action', 'url', '人员管理日志', 1, 0, '1014', 15, 2, null, 1);
INSERT INTO mm_moduleinfo (sid,parent_sid,name,entrypoint_url,entrypoint_type,showname,isNavigation,istaskview,hotkeystr,order_id,module_level,tag,state) 
VALUES ('0000000000transfer_log_module_id', '000000000000log_manage_module_id', '转授权日志', 'http://[server]:[port]/[context]/logTransferPowerInfo_initPage.action', 'url', '转授权日志', 1, 0, '1015', 16, 2, null, 1);

INSERT INTO am_powerinfo (sid,powername,powerstate,parentpower,resourceid,orderid,memo,tag) 
VALUES ('00000000000000login_log_power_id', '用户登录日志', '0', '0000000000000log_manage_power_id', '0000000000000login_log_module_id', 13, '用户登录日志', null);
INSERT INTO am_powerinfo (sid,powername,powerstate,parentpower,resourceid,orderid,memo,tag) 
VALUES ('000000000org_manage_log_power_id', '机构管理日志', '0', '0000000000000log_manage_power_id', '00000000org_manage_log_module_id', 14, '机构管理日志', null);
INSERT INTO am_powerinfo (sid,powername,powerstate,parentpower,resourceid,orderid,memo,tag) 
VALUES ('000000people_manage_log_power_id', '人员管理日志', '0', '0000000000000log_manage_power_id', '00000people_manage_log_module_id', 15, '人员管理日志', null);
INSERT INTO am_powerinfo (sid,powername,powerstate,parentpower,resourceid,orderid,memo,tag) 
VALUES ('00000000000transfer_log_power_id', '转授权日志', '0', '0000000000000log_manage_power_id', '0000000000transfer_log_module_id', 16, '转授权日志', null);

---用户类型参数-
INSERT INTO pam_system_universal (paramId, parentParamId, paramCategory, paramname, paramvalue, orderId) VALUES ('0', '0', 'peopleType', '0', '一般用户', 1);
INSERT INTO pam_system_universal (paramId, parentParamId, paramCategory, paramname, paramvalue, orderId) VALUES ('1', '0', 'peopleType', '1', '管理用户', 2);

