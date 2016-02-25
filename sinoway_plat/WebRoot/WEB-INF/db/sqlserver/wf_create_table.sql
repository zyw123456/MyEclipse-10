CREATE TABLE po_orgtype
(
   sid            VARCHAR (32) NOT NULL,
   orgtypename    VARCHAR (100) NOT NULL,
   orgtypestate   SMALLINT DEFAULT (0) NOT NULL,
   memo           VARCHAR (512),
   tag            VARCHAR (512)
);

ALTER TABLE po_orgtype ADD CONSTRAINT pk_po_orgtype PRIMARY KEY(sid);

CREATE TABLE po_peopleinfo
(
   sid                    VARCHAR (32) NOT NULL,
   peoplecode             VARCHAR (20) NOT NULL,
   peoplename             VARCHAR (100) NOT NULL,
   peoplegender           TINYINT DEFAULT (0),
   phone                  VARCHAR (20),
   email                  VARCHAR (64),
   pwd                    VARCHAR (64) NOT NULL,
   peoplestate            SMALLINT DEFAULT (0) NOT NULL,
   organizeinfo           VARCHAR (32) NOT NULL,
   defaultdesktop         TINYINT NOT NULL,
   memo                   VARCHAR (512),
   tag                    VARCHAR (512),
   defaultconfig          VARCHAR (32),
   lastchangepwdtime      DATETIME,
   passwordErrCount       INT,
   recentPasswordRecord   VARCHAR (512),
   peopleType 			  INT,
   peopleIdCard           VARCHAR(20),
   peopleLevel			  INT
);

ALTER TABLE po_peopleinfo ADD CONSTRAINT pk_po_peopleinfo PRIMARY KEY (sid);

CREATE INDEX idx_po_peoplecode
   ON po_peopleinfo (peoplecode ASC);

CREATE TABLE poam_operateLogInfo
(
   sid                 VARCHAR (32) NOT NULL,
   manageType          INT NOT NULL,
   manageObjectType    INT NOT NULL,
   manageObjectSid     VARCHAR (32),
   peopleCode          VARCHAR (20),
   peopleName          VARCHAR (100),
   bankNo              VARCHAR (32),
   roleName            VARCHAR (100),
   roleMemo            VARCHAR (512),
   orgTypeName         VARCHAR (100),
   orgTypeMemo         VARCHAR (512),
   operateMemo         VARCHAR (1024),
   operatePeopleSid    VARCHAR (32) NOT NULL,
   operatePeopleCode   VARCHAR (20) NOT NULL,
   operatePeopleName   VARCHAR (100) NOT NULL,
   operateDate         DATETIME NOT NULL
);

ALTER TABLE poam_operateLogInfo ADD CONSTRAINT pk_poam_operateLog PRIMARY KEY (sid);

CREATE INDEX idx_po_lo_od
   ON poam_operateLogInfo (operateDate DESC);


CREATE TABLE po_currentlogin
(
   loginid     NUMERIC (19, 0) IDENTITY (1, 1) NOT NULL,
   peoplesid   VARCHAR (32) NOT NULL,
   loginip     VARCHAR (20) NOT NULL,
   sessionid   VARCHAR (128) NOT NULL,
   logindate   VARCHAR (10) NOT NULL,
   logintime   VARCHAR (10) NOT NULL
);

ALTER TABLE po_currentlogin ADD CONSTRAINT pk_po_currentlogin PRIMARY KEY (loginid);



CREATE INDEX idx_po_cl_ps
   ON po_currentlogin (peoplesid ASC);



CREATE INDEX idx_po_cl_si
   ON po_currentlogin (sessionid ASC);



CREATE TABLE Param_ModuleFlow
(
   autoid NUMERIC (19, 0) IDENTITY (1,1) NOT NULL,
   ModuleID             VARCHAR (32),
   AppId                VARCHAR (32),
   ProcessDefKey        VARCHAR (32),
   ProcessDefShowname   VARCHAR (32),
   TaskName             VARCHAR (32),
   TaskShowname         VARCHAR (32)
);

ALTER TABLE Param_ModuleFlow ADD CONSTRAINT pk_Param_ModuleFlow PRIMARY KEY (autoid);



CREATE TABLE mm_moduleinfo
(
   sid               VARCHAR (32) NOT NULL,
   parent_sid        VARCHAR (32),
   name              VARCHAR (50),
   entrypoint_url    VARCHAR (200) NOT NULL,
   entrypoint_type   VARCHAR (20),
   showname          VARCHAR (200),
   isNavigation      INT DEFAULT (0) NOT NULL,
   istaskview        INT DEFAULT (0) NOT NULL,
   hotkeystr         VARCHAR (10),
   order_id          INT NOT NULL,
   module_level      INT,
   tag               VARCHAR (64),
   state             INT
);

ALTER TABLE mm_moduleinfo ADD CONSTRAINT pk_mm_moduleinfo PRIMARY KEY (sid);



CREATE INDEX idx_mm_mi_url
   ON mm_moduleinfo (entrypoint_url ASC);

ALTER TABLE mm_moduleinfo ADD CONSTRAINT FK_mm_mi_mi FOREIGN KEY (parent_sid) REFERENCES mm_moduleinfo (sid) ON UPDATE NO ACTION ON DELETE NO ACTION;


CREATE TABLE am_powerinfo
(
   sid           VARCHAR (32) NOT NULL,
   powername     VARCHAR (100) NOT NULL,
   powerstate    SMALLINT DEFAULT (0) NOT NULL,
   parentpower   VARCHAR (32),
   resourceid    VARCHAR (64) NOT NULL,
   orderid       SMALLINT NOT NULL,
   memo          VARCHAR (512),
   tag           VARCHAR (512)
);

ALTER TABLE am_powerinfo ADD CONSTRAINT pk_am_powerinfo PRIMARY KEY (sid);



CREATE INDEX idx_am_pi_pp
   ON am_powerinfo (parentpower ASC);



CREATE INDEX idx_am_pi_ri
   ON am_powerinfo (resourceid ASC);

ALTER TABLE am_powerinfo ADD CONSTRAINT fk_am_pi_pi FOREIGN KEY (parentpower) REFERENCES am_powerinfo (sid) ON UPDATE NO ACTION ON DELETE NO ACTION;



CREATE TABLE am_powermutex
(
   powersid1   VARCHAR (32) NOT NULL,
   powersid2   VARCHAR (32) NOT NULL
);

ALTER TABLE am_powermutex ADD CONSTRAINT pk_am_powermutex PRIMARY KEY (powersid1, powersid2);
ALTER TABLE am_powermutex ADD CONSTRAINT fk_am_pi_pm2 FOREIGN KEY (powersid2) REFERENCES am_powerinfo (sid) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE am_powermutex ADD CONSTRAINT fk_am_pi_pm1 FOREIGN KEY (powersid1) REFERENCES am_powerinfo (sid) ON UPDATE NO ACTION ON DELETE NO ACTION;



CREATE TABLE am_rolegroupinfo
(
   sid              VARCHAR (32) NOT NULL,
   rolegroupname    VARCHAR (100) NOT NULL,
   rolegroupstate   SMALLINT DEFAULT (0) NOT NULL,
   orderid          SMALLINT NOT NULL,
   memo             VARCHAR (512),
   tag              VARCHAR (512)
);

ALTER TABLE am_rolegroupinfo ADD CONSTRAINT pk_am_rolegroupinfo PRIMARY KEY (sid);


CREATE TABLE am_rolegroupmutex
(
   rolegroupsid1   VARCHAR (32) NOT NULL,
   rolegroupsid2   VARCHAR (32) NOT NULL
);

ALTER TABLE am_rolegroupmutex ADD CONSTRAINT pk_am_rolegroupmute PRIMARY KEY (rolegroupsid1, rolegroupsid2);
ALTER TABLE am_rolegroupmutex ADD CONSTRAINT fk_am_rgi_rgm1 FOREIGN KEY (rolegroupsid1) REFERENCES am_rolegroupinfo (sid) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE am_rolegroupmutex ADD CONSTRAINT fk_am_rgi_rgm2 FOREIGN KEY (rolegroupsid2) REFERENCES am_rolegroupinfo (sid) ON UPDATE NO ACTION ON DELETE NO ACTION;


CREATE TABLE am_roleinfo
(
   sid         VARCHAR (32) NOT NULL,
   rolename    VARCHAR (100) NOT NULL,
   rolestate   SMALLINT DEFAULT (0) NOT NULL,
   orderid     SMALLINT NOT NULL,
   memo        VARCHAR (512),
   tag         VARCHAR (512)
);

ALTER TABLE am_roleinfo ADD CONSTRAINT pk_am_roleinfo PRIMARY KEY (sid);



CREATE TABLE am_rolemutex
(
   rolesid1   VARCHAR (32) NOT NULL,
   rolesid2   VARCHAR (32) NOT NULL
);

ALTER TABLE am_rolemutex ADD CONSTRAINT pk_am_rolemutex PRIMARY KEY (rolesid1, rolesid2);
ALTER TABLE am_rolemutex ADD CONSTRAINT fk_am_ri_rm2 FOREIGN KEY (rolesid2) REFERENCES am_roleinfo (sid) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE am_rolemutex ADD CONSTRAINT fk_am_ri_rm1 FOREIGN KEY (rolesid1) REFERENCES am_roleinfo (sid) ON UPDATE NO ACTION ON DELETE NO ACTION;



CREATE TABLE am_rolegrouporgtype
(
   orgtypesid     VARCHAR (32) NOT NULL,
   rolegroupsid   VARCHAR (32) NOT NULL
);

ALTER TABLE am_rolegrouporgtype ADD CONSTRAINT pk_am_rolegrouporgt PRIMARY KEY (orgtypesid, rolegroupsid);
ALTER TABLE am_rolegrouporgtype ADD CONSTRAINT fk_am_rgi_ot FOREIGN KEY (rolegroupsid) REFERENCES am_rolegroupinfo (sid) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE am_rolegrouporgtype ADD CONSTRAINT fk_po_ot_rgi FOREIGN KEY (orgtypesid) REFERENCES po_orgtype (sid) ON UPDATE NO ACTION ON DELETE NO ACTION;


CREATE TABLE am_rolegrouppeople
(
   peoplesid      VARCHAR (32) NOT NULL,
   rolegroupsid   VARCHAR (32) NOT NULL
);

ALTER TABLE am_rolegrouppeople ADD CONSTRAINT pk_am_rolegrouppeop PRIMARY KEY (peoplesid, rolegroupsid);
ALTER TABLE am_rolegrouppeople ADD CONSTRAINT fk_po_rgi_pi FOREIGN KEY (rolegroupsid) REFERENCES am_rolegroupinfo (sid) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE am_rolegrouppeople ADD CONSTRAINT fk_po_pi_rgi FOREIGN KEY (peoplesid) REFERENCES po_peopleinfo (sid) ON UPDATE NO ACTION ON DELETE NO ACTION;



CREATE TABLE am_rolegrouprole
(
   rolegroupsid   VARCHAR (32) NOT NULL,
   rolesid        VARCHAR (32) NOT NULL
);

ALTER TABLE am_rolegrouprole ADD CONSTRAINT pk_am_rolegrouprole PRIMARY KEY (rolesid, rolegroupsid);
ALTER TABLE am_rolegrouprole ADD CONSTRAINT fk_am_rgi_ri FOREIGN KEY (rolegroupsid) REFERENCES am_rolegroupinfo (sid) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE am_rolegrouprole ADD CONSTRAINT fk_am_ri_rgi FOREIGN KEY (rolesid) REFERENCES am_roleinfo (sid) ON UPDATE NO ACTION ON DELETE NO ACTION;


CREATE TABLE am_rolepower
(
   rolesid    VARCHAR (32) NOT NULL,
   powersid   VARCHAR (32) NOT NULL
);

ALTER TABLE am_rolepower ADD CONSTRAINT pk_am_rolepower PRIMARY KEY (powersid, rolesid);
ALTER TABLE am_rolepower ADD CONSTRAINT fk_am_pi_ri FOREIGN KEY (powersid) REFERENCES am_powerinfo (sid) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE am_rolepower ADD CONSTRAINT fk_am_ri_pi FOREIGN KEY (rolesid) REFERENCES am_roleinfo (sid) ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE TABLE po_organizeinfo
(
   sid            VARCHAR (32) NOT NULL,
   orgname        VARCHAR (100) NOT NULL,
   orgno          VARCHAR (32) NOT NULL,
   orgtype        VARCHAR (32) NOT NULL,
   orgstate       SMALLINT DEFAULT (0) NOT NULL,
   parentorg      VARCHAR (32),
   managetype     TINYINT DEFAULT (1) NOT NULL,
   includechild   TINYINT DEFAULT (1) NOT NULL,
   memo           VARCHAR (512),
   tag            VARCHAR (512),
   orgLevel       INT,
   orgPath        VARCHAR (128),
   manageOrgFlag  TINYINT DEFAULT (0),
   bizOrgFlag     TINYINT DEFAULT (0),
   orgProvince    VARCHAR(10),
   orgArea        VARCHAR(32)
);

ALTER TABLE po_organizeinfo ADD CONSTRAINT pk_po_organizeinfo PRIMARY KEY (sid);


CREATE INDEX idx_po_oi_ot
   ON po_organizeinfo (orgtype ASC);



CREATE INDEX idx_po_oi_po
   ON po_organizeinfo (parentorg ASC);

ALTER TABLE po_organizeinfo ADD CONSTRAINT fk_po_ot_oi FOREIGN KEY (orgtype) REFERENCES po_orgtype (sid) ON UPDATE NO ACTION ON DELETE NO ACTION;



CREATE TABLE po_orgtyperelate
(
   parentorgtype   VARCHAR (32) NOT NULL,
   childorgtype    VARCHAR (32) NOT NULL,
   countlimit      INT DEFAULT ( (-1)) NOT NULL
);

ALTER TABLE po_orgtyperelate ADD CONSTRAINT pk_po_orgtyperelate PRIMARY KEY (parentorgtype, childorgtype);
ALTER TABLE po_orgtyperelate ADD CONSTRAINT fk_po_ot_otrp FOREIGN KEY (parentorgtype) REFERENCES po_orgtype (sid) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE po_orgtyperelate ADD CONSTRAINT fk_po_ot_otrc FOREIGN KEY (childorgtype) REFERENCES po_orgtype (sid) ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE TABLE BPM_LWE_LOG 
(
	ID NUMERIC (19, 0) IDENTITY (1,1) NOT NULL,
	TASKID NUMERIC(19,0), 
	PROCESSDEFID VARCHAR(32), 
	SOURCENODEID VARCHAR(32), 
	DESTNODEID VARCHAR(32), 
	PEOPLECODE VARCHAR(32), 
	DATE_ VARCHAR(10), 
	TIME_ VARCHAR(8)
);

ALTER TABLE BPM_LWE_LOG ADD CONSTRAINT pk_bpm_lwe_logid PRIMARY KEY (ID);

CREATE TABLE SimpleLogInfo (
	LogId numeric(19, 0) IDENTITY(1, 1) NOT NULL,
	SysId varchar(32) NULL,
	BizId varchar(32) NULL,
	OperateType varchar(32) NULL,
	OperatorCode varchar(32) NULL,
	OperateDate varchar(32) NULL,
	OperateTime varchar(32) NULL,
	OperatorOrgNo varchar(32) NULL,
	Memo varchar(1024) NULL,
	AccNo varchar(32) NULL,
	Credit varchar(32) NULL
);

ALTER TABLE SimpleLogInfo ADD CONSTRAINT pk_bizlog_sl_logid PRIMARY KEY (LogId);

CREATE INDEX accNoIndex ON SimpleLogInfo (AccNo);

CREATE INDEX operateDateIndex ON SimpleLogInfo (OperateDate);

CREATE INDEX sysIdBizOrgIndex ON SimpleLogInfo (SysId,BizId,OperatorOrgNo);

CREATE TABLE PamLogInfo 
(
	LogId numeric(19,0) identity(1,1) NOT NULL, 
	SysId varchar(32), 
	BizId varchar(32), 
	OperateType varchar(32), 
	OperatorCode varchar(32), 
	OperateDate varchar(32), 
	OperateTime varchar(32), 
	OperatorOrgNo varchar(32), 
	Memo varchar(1024), 
	AccNo varchar(32), 
	Credit float(53), 
	groupname varchar(32), 
	paramvalue varchar(32),
	paramname varchar(32)
);

ALTER TABLE PamLogInfo ADD CONSTRAINT pk_pam_pl_logid PRIMARY KEY (LogId);

CREATE TABLE pam_baseparam 
(
	autoid numeric(19,0) identity(1,1) NOT NULL, 
	paramname varchar(32), 
	paramtype varchar(32), 
	paramvalue varchar(32), 
	encrypttype varchar(32), 
	isneedcache tinyint, 
	isneedautorefresh tinyint, 
	tag varchar(64), 
	paramflag int, 
	orderId int, 
	activetime varchar(32), 
	deactivetime varchar(32), 
	lastupdatedtime varchar(32), 
	updateoperator varchar(10)
);
ALTER TABLE pam_baseparam ADD CONSTRAINT pk_pam_pb_autoid PRIMARY KEY (autoid);


CREATE TABLE PAM_VERSION
(
  PARAMGROUPNAME  VARCHAR(32)             NOT NULL,
  VERSION         numeric(10)
);

ALTER TABLE PAM_VERSION ADD CONSTRAINT pk_pam_version PRIMARY KEY (PARAMGROUPNAME);

--创建区域表
CREATE TABLE po_area (
	areaSid VARCHAR(32)  NOT NULL, 
	areaCode VARCHAR(10),
	areaName VARCHAR(50), 
	parentArea VARCHAR(32),
	areaType VARCHAR(32), 
	areaStatus INT DEFAULT 0, 
	isParent TINYINT, 
	areaMemo VARCHAR(100), 
	isOpen VARCHAR(2),
	tag1 VARCHAR(50),
	tag2 VARCHAR(50),
	tag3 VARCHAR(50),
	tag4 VARCHAR(50),
	PRIMARY KEY (areaSid)
);
--区域类型表
CREATE TABLE po_areatype (
	sid VARCHAR(32)  NOT NULL,
	areaTypeCode VARCHAR(10), 
	areaTypeName VARCHAR(50), 
	areaTypeStatus INT DEFAULT 0, 
	areaTypeMemo VARCHAR(100), 
	tag1 VARCHAR(50), 
	tag2 VARCHAR(50), 
	tag3 VARCHAR(50), 
	tag4 VARCHAR(50), 
	PRIMARY KEY (sid)
);

--区域类型关联表
CREATE TABLE po_areatyperelates (
	parentAreaType VARCHAR(32)  NOT NULL, 
	childAreaType VARCHAR(32)  NOT NULL, 
	countLimit INT DEFAULT 0 NOT NULL, 
	PRIMARY KEY (parentAreaType, childAreaType)
);
ALTER TABLE po_areatyperelates ADD CONSTRAINT fk_po_atr_at_cat FOREIGN KEY (childAreaType) REFERENCES po_areatype (sid);
ALTER TABLE po_areatyperelates ADD CONSTRAINT fk_po_atr_at_pat FOREIGN KEY (parentAreaType) REFERENCES po_areatype (sid);

--系统参数表
CREATE TABLE pam_system_universal (
    autoid NUMERIC(19,0) NOT NULL IDENTITY,
    paramId VARCHAR(64)  NOT NULL,
    parentParamId VARCHAR(64) ,
    paramCategory VARCHAR(32)  NOT NULL,
    paramname VARCHAR(32)  NOT NULL,
    paramvalue VARCHAR(32) NOT NULL,
    orderId INT,
    PRIMARY KEY (autoid)
);

CREATE TABLE cache_mark (
    clusterNode VARCHAR(32) NOT NULL,
    cacheName VARCHAR(32) NOT NULL,
    available INT,
    PRIMARY KEY (clusterNode, cacheName)
);

--转授权
CREATE TABLE 
	am_transfer_power_info 
	(
		id VARCHAR(32) COLLATE Chinese_PRC_CI_AS NOT NULL, 
		START_DATE VARCHAR(10) COLLATE Chinese_PRC_CI_AS NOT NULL, 
		START_TIME VARCHAR(8) COLLATE Chinese_PRC_CI_AS NOT NULL, 
		END_DATE VARCHAR(10) COLLATE Chinese_PRC_CI_AS NOT NULL, 
		END_TIME VARCHAR(8) COLLATE Chinese_PRC_CI_AS NOT NULL, 
		TRANSFER_PEOPLE_ID VARCHAR(32) COLLATE Chinese_PRC_CI_AS NOT NULL, 
		TRANSFER_PEOPLE_NO VARCHAR(20) COLLATE Chinese_PRC_CI_AS NOT NULL, 
		TRANSFER_PEOPLE_NAME VARCHAR(100) COLLATE Chinese_PRC_CI_AS NOT NULL, 
		TRANSFER_PEOPLE_ORG_SID VARCHAR(32) COLLATE Chinese_PRC_CI_AS NOT NULL, 
		REVICE_PEOPLE_ID VARCHAR(32) COLLATE Chinese_PRC_CI_AS, 
		REVICE_PEOPLE_NO VARCHAR(20) COLLATE Chinese_PRC_CI_AS NOT NULL, 
		REVICE_PEOPLE_NAME VARCHAR(100) COLLATE Chinese_PRC_CI_AS, 
		REVICE_PEOPLE_ORG_SID VARCHAR(32) COLLATE Chinese_PRC_CI_AS, 
		SUPERVISE_PEOPLE_ID VARCHAR(32) COLLATE Chinese_PRC_CI_AS, 
		SUPERVISE_PEOPLE_NO VARCHAR(20) COLLATE Chinese_PRC_CI_AS, 
		SUPERVISE_PEOPLE_NAME VARCHAR(100) COLLATE Chinese_PRC_CI_AS, 
		SUPERVISE_PEOPLE_ORG_SID VARCHAR(32) COLLATE Chinese_PRC_CI_AS, 
		APPLICATION_FORM_NO VARCHAR(32) COLLATE Chinese_PRC_CI_AS NOT NULL, 
		STATUS INT NOT NULL, 
		APPLY_DATE VARCHAR(10) COLLATE Chinese_PRC_CI_AS NOT NULL, 
		APPLY_TIME VARCHAR(8) COLLATE Chinese_PRC_CI_AS NOT NULL, 
		UPDATE_DATE VARCHAR(10) COLLATE Chinese_PRC_CI_AS, 
		UPDATE_TIME VARCHAR(8) COLLATE Chinese_PRC_CI_AS, 
		IS_ALL INT DEFAULT 0 NOT NULL, 
		TPRG_SIDS VARCHAR(512) COLLATE Chinese_PRC_CI_AS, 
		RPORG_SIDS VARCHAR(512) COLLATE Chinese_PRC_CI_AS, 
		MEMO VARCHAR(128) COLLATE Chinese_PRC_CI_AS, 
		PRIMARY KEY (id)
	);

CREATE TABLE
    log_transfer_power_info
    (
        LogId NUMERIC(19,0) NOT NULL IDENTITY,
        SysId VARCHAR(32),
        OperateType VARCHAR(32),
        OperatorCode VARCHAR(32),
        OperateDate VARCHAR(32),
        OperateTime VARCHAR(32),
        OperatorOrgNo VARCHAR(32),
        Memo VARCHAR(1024),	
		operatorName varchar(100) NULL ,
		operatorOrgName varchar(100) NULL, 
        PRIMARY KEY (LogId)
    );
    
CREATE INDEX idx_am_tpfl_od ON log_transfer_power_info (OperateDate);
CREATE INDEX idx_am_tpfl_oon ON log_transfer_power_info (OperatorOrgNo);
CREATE INDEX idx_am_tpfl_si ON log_transfer_power_info (SysId);

--1.8版本 新增登录、机构管理、人员管理日志--
CREATE TABLE 
	Log_Login 
	(
		LogId NUMERIC(19,0) NOT NULL IDENTITY,
		SysId varchar(32) NULL ,
		BizId varchar(32) NULL ,
		OperateType varchar(32) NULL ,
		OperatorCode varchar(32) NULL ,
		OperateDate varchar(32) NULL ,
		OperateTime varchar(32) NULL ,
		OperatorOrgNo varchar(32) NULL ,
		Memo varchar(1024) NULL ,
		AccNo varchar(32) NULL ,
		Credit varchar(32) NULL ,
		ManageObjectSid varchar(32) NULL,	
		operatorName varchar(100) NULL ,
		operatorOrgName varchar(100) NULL, 
		PRIMARY KEY (LogId) 
	);

CREATE INDEX ll_sysIdBizOrgIndex ON Log_Login (SysId,BizId,OperatorOrgNo); 
CREATE INDEX ll_operateDateIndex ON Log_Login (OperateDate); 
CREATE INDEX ll_accNoIndex ON Log_Login (AccNo); 

CREATE TABLE 
	Log_OrgManage 
	(
		LogId NUMERIC(19,0) NOT NULL IDENTITY,
		SysId varchar(32) NULL ,
		BizId varchar(32) NULL ,
		OperateType varchar(32) NULL ,
		OperatorCode varchar(32) NULL ,
		OperateDate varchar(32) NULL ,
		OperateTime varchar(32) NULL ,
		OperatorOrgNo varchar(32) NULL ,
		Memo varchar(1024) NULL ,
		AccNo varchar(32) NULL ,
		Credit varchar(32) NULL ,
		ManageObjectSid varchar(32) NULL ,
		OperatedCode varchar(32) NULL ,
		OperatedName varchar(100) NULL ,
		AuthCode varchar(32) NULL ,	
		operatorName varchar(100) NULL ,
		operatorOrgName varchar(100) NULL, 
		PRIMARY KEY (LogId)  
	)

CREATE INDEX lo_operateDateIndex ON Log_OrgManage (OperateDate); 
CREATE INDEX lo_sysIdBizOrgIndex ON Log_OrgManage (SysId,BizId,OperatorOrgNo); 
CREATE INDEX lo_accNoIndex ON Log_OrgManage (AccNo);

CREATE TABLE 
	Log_PeopleManage 
	(
		LogId NUMERIC(19,0) NOT NULL IDENTITY,
		SysId varchar(32) NULL ,
		BizId varchar(32) NULL ,
		OperateType varchar(32) NULL ,
		OperatorCode varchar(32) NULL ,
		OperateDate varchar(32) NULL ,
		OperateTime varchar(32) NULL ,
		OperatorOrgNo varchar(32) NULL ,
		Memo varchar(1024) NULL ,
		AccNo varchar(32) NULL ,
		Credit varchar(32) NULL ,
		ManageObjectSid varchar(32) NULL ,
		OperatedCode varchar(32) NULL ,
		OperatedName varchar(100) NULL ,
		AuthCode varchar(32) NULL,	
		operatorName varchar(100) NULL ,
		operatorOrgName varchar(100) NULL, 
		PRIMARY KEY (LogId)   
	)

CREATE INDEX lp_sysIdBizOrgIndex ON Log_PeopleManage (SysId,BizId,OperatorOrgNo); 
CREATE INDEX lp_operateDateIndex ON Log_PeopleManage (OperateDate); 
CREATE INDEX lp_accNoIndex ON Log_PeopleManage (AccNo); 

CREATE TABLE 
	wt_orgworkingtime 
	(
		autoId numeric(19) NOT NULL IDENTITY(1,1) ,
		organizationSid varchar(32) NULL ,
		weekMon varchar(10) NULL ,
		monStartTime varchar(23) NULL ,
		monEndTime varchar(23) NULL ,
		weekTues varchar(10) NULL ,
		tuesStartTime varchar(23) NULL ,
		tuesEndTime varchar(23) NULL ,
		weekWed varchar(10) NULL ,
		wedStartTime varchar(23) NULL ,
		wedEndTime varchar(23) NULL ,
		weekThurs varchar(10) NULL ,
		thursStartTime varchar(23) NULL ,
		thursEndTime varchar(23) NULL ,
		weekFrid varchar(10) NULL ,
		tridStartTime varchar(23) NULL ,
		tridEndTime varchar(23) NULL ,
		weekSat varchar(10) NULL ,
		satStartTime varchar(23) NULL ,
		satEndTime varchar(23) NULL ,
		weekSun varchar(10) NULL ,
		sunStartTime varchar(23) NULL ,
		sunEndTime varchar(23) NULL ,
		initOrganization varchar(32) NULL ,
		initPersonnel varchar(32) NULL ,
		initTime varchar(23) NULL,
		PRIMARY KEY (autoId)
	)   