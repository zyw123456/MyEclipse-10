/* Formatted on 2015/3/10 16:52:21 (QP5 v5.240.12305.39476) */
DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM All_Sequences
    WHERE UPPER (sequence_name) = 'PO_CL_LI';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'DROP SEQUENCE PO_CL_LI';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM All_Sequences
    WHERE UPPER (sequence_name) = 'PARAMGROUP_ID';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'DROP SEQUENCE PARAMGROUP_ID';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM all_indexes
    WHERE UPPER (index_name) = 'IDX_PO_PEOPLECODE';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop index idx_po_peoplecode';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM all_indexes
    WHERE UPPER (index_name) = 'IDX_PO_CL_PS';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop index idx_po_cl_ps';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM all_indexes
    WHERE UPPER (index_name) = 'IDX_PO_CL_SI';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop index idx_po_cl_si';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM all_indexes
    WHERE UPPER (index_name) = 'IDX_MM_MI_URL';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop index idx_mm_mi_url';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM all_indexes
    WHERE UPPER (index_name) = 'IDX_AM_PI_PP';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop index idx_am_pi_pp';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM all_indexes
    WHERE UPPER (index_name) = 'IDX_AM_PI_RI';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop index idx_am_pi_ri';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM all_indexes
    WHERE UPPER (index_name) = 'IDX_PO_OI_OT';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop index idx_po_oi_ot';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM all_indexes
    WHERE UPPER (index_name) = 'IDX_PO_OI_PO';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop index idx_po_oi_po';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM all_indexes
    WHERE UPPER (index_name) = 'IDX_PO_LO_OD';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop index idx_po_lo_od';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'PO_ORGTYPE';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table PO_ORGTYPE cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'PO_PEOPLEINFO';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table PO_PEOPLEINFO cascade constraints';
   END IF;
END;
/


DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'POAM_OPERATELOGINFO';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table poam_operateLogInfo cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'PO_CURRENTLOGIN';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table po_currentlogin cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'PARAM_MODULEFLOW';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table Param_ModuleFlow cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'MM_MODULEINFO';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table mm_moduleinfo cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'AM_POWERINFO';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table am_powerinfo cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'AM_POWERMUTEX';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table am_powermutex cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'AM_ROLEGROUPINFO';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table am_rolegroupinfo cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'AM_ROLEGROUPMUTEX';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table am_rolegroupmutex cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'AM_ROLEINFO';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table am_roleinfo cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'AM_ROLEMUTEX';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table am_rolemutex cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'AM_ROLEGROUPORGTYPE';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table am_rolegrouporgtype cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'AM_ROLEGROUPPEOPLE';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table am_rolegrouppeople cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'AM_ROLEGROUPROLE';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table am_rolegrouprole cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'AM_ROLEPOWER';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table am_rolepower cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'PO_ORGANIZEINFO';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table po_organizeinfo cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'PO_ORGTYPERELATE';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table po_orgtyperelate cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'POAM_OPERATELOGINFO';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table poam_operateloginfo cascade constraints';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM All_Sequences
    WHERE UPPER (sequence_name) = 'TASKLOG_ID';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'DROP SEQUENCE TASKLOG_ID';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'BPM_LWE_LOG';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table BPM_LWE_LOG cascade constraints';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM All_Sequences
    WHERE UPPER (sequence_name) = 'PARAM_ID';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'DROP SEQUENCE PARAM_ID';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM All_Sequences
    WHERE UPPER (sequence_name) = 'PAMLOG_SEQ_LOGID';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'DROP SEQUENCE PAMLOG_SEQ_LOGID';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM All_Sequences
    WHERE UPPER (sequence_name) = 'BIZLOG_SEQ_LOGID';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'DROP SEQUENCE BIZLOG_SEQ_LOGID';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM all_indexes
    WHERE UPPER (index_name) = 'ACCNOINDEX';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop index accNoIndex';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM all_indexes
    WHERE UPPER (index_name) = 'OPERATEDATEINDEX';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop index operateDateIndex';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM all_indexes
    WHERE UPPER (index_name) = 'SYSIDBIZORGINDEX';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop index sysIdBizOrgIndex';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'SIMPLELOGINFO';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table SimpleLogInfo cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'PAMLOGINFO';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table PamLogInfo cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'PAM_BASEPARAM';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table pam_baseparam cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'PAM_VERSION';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table PAM_VERSION cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'PO_AREA';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table po_area cascade constraints';
   END IF;
END;
/


DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'PO_AREATYPE';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table po_areatype cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'PO_AREATYPERELATES';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table po_areatyperelates cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'PAM_SYSTEM_UNIVERSAL';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table pam_system_universal cascade constraints';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM All_Sequences
    WHERE UPPER (sequence_name) = 'SEQ_PAM_SYSTEM_UNIVERSAL_ID';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'DROP SEQUENCE SEQ_PAM_SYSTEM_UNIVERSAL_ID';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'CACHE_MARK';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop table cache_mark cascade constraints';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM all_indexes
    WHERE UPPER (index_name) = 'IDX_AM_TPFL_OD';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop index idx_am_tpfl_od';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM all_indexes
    WHERE UPPER (index_name) = 'IDX_AM_TPFL_OON';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop index idx_am_tpfl_oon';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM all_indexes
    WHERE UPPER (index_name) = 'IDX_AM_TPFL_SI';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'drop index idx_am_tpfl_si';
   END IF;
END;
/

DECLARE
   cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO cnt
     FROM All_Sequences
    WHERE UPPER (sequence_name) = 'SEQ_AM_TPFL_ID';

   IF cnt > 0
   THEN
      EXECUTE IMMEDIATE 'DROP SEQUENCE seq_am_tpfl_id';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'LOG_TRANSFER_POWER_INFO';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE
         'drop table log_transfer_power_info cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'AM_TRANSFER_POWER_INFO';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE
         'drop table am_transfer_power_info cascade constraints';
   END IF;
END;
/

/* 1.8版本 */
DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'Log_Login';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE
         'drop table Log_Login cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'Log_OrgManage';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE
         'drop table Log_OrgManage cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'Log_PeopleManage';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE
         'drop table Log_PeopleManage cascade constraints';
   END IF;
END;
/

DECLARE
   v_cnt   NUMBER;
BEGIN
   SELECT COUNT (*)
     INTO v_cnt
     FROM user_tables
    WHERE UPPER (table_name) = 'wt_orgworkingtime';

   IF v_cnt > 0
   THEN
      EXECUTE IMMEDIATE
         'drop table wt_orgworkingtime cascade constraints';
   END IF;
END;
/