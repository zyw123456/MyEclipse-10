package com.sinoway.common.util;


public class Constant {
	private final static String MODELPATH = "D:\\";
	//渠道号
	private final static String CHNLCODE = "50000001";
	//报文存放路径
	private final static String DEFAULT_MESSAGE_FILE_FOLDER = "D:\\";
	//央行个人征信报告上传路径
	private final static String MSG_UPLOAD_PATH = "E:\\";
	//企业普通用户组
	private final static String ROLEGROUP_ORDINARY = "806D87A083A04883A331F4F5A1B80E88";
	//团队
	private final static String ORGTYPE_TEAM = "D5AFC14640D3A4A0496D7E46D0E7_003";
	//默认密码
	private final static String PWD_DEFAULT = "888888";
	//核心秘钥
	private final static String SECRET_KEY  = "12345678";
	//人员信息验证原交易
	private final static String TRNCODS = "P0000005";
	//配置接口地址
	//private final static String CONFIG_URL = "http://10.0.1.158:8080/sinoway_mcp/changeForPubJson.action";
	private final static String CONFIG_URL = "http://10.0.1.84:8081/sinoway_mcp/changeForPubJson.action";
	//报告接口地址
	//private final static String MESSAGE_URL = "http://10.0.1.158:8080/sinoway_mcp/httpControl";
	private final static String MESSAGE_URL = "http://127.0.0.1:8080/windforce/McpHttpServlet";
	//前N条记录最大值
	private final static int TOP_N_MAX_RESULT = 10;

	//平台预警前N条数最大值
	private static final int max_PlatAlarmCount = 10;
	
	//主账户预警前N条最大值
	private static final int max_AccAlarmCount = 10;
	
	//平台报告前N条最大值
	private static final int max_PlatRptCount = 10;
	//产品编码默认值
	private static final String PRD_DEFAULT_SYSCOD = "X";
	//产品默认重启增加数值
	private static final int PRD_INCREASE_STEP = 100;
	//产品初始化开始的值
	private static final int PRD_START_COUNT = 20000001;
	//流水号默认添加的数值
	private static final int JRN_INCREASE_STEP = 10000;
	//流量点数增长值
	private static final int USAGE_POINT_INCREMENT = 1;
	//默认编码
	private static final String DEFAULT_ENCODING ="UTF-8";
	//数据库姓名字符长度
	private static final int PRSNNAMLENGTH = 32;
	//数据库身份证号字符长度
	private static final int PRSNCODLENGTH = 32;
	//数据库手机号字符长度
	private static final int TELNOLENGTH = 30;
	//js判断返回状态
	private static final String NORMAL = "normal";
	/**
	 * 是否全角
	 * @author yuhui
	 *
	 */
	public enum  IsSbc{
		/**
		 * 是
		 */
		ISSBC_YES("1"),
		/**
		 * 否
		 */
		ISSBC_NO("002");
			
		private IsSbc(String code){
			this.code = code;
		}
		
		private String code;
		
		public String getCode(){
			return code;
		}
		
	}
	/**
	 * 产品类型
	 * @author yuhui
	 *
	 */
	public enum  PrdTyp{
		/**
		 * 个人征信报告
		 */
		PRDTYP_RPT("001"),
		/**
		 * 反欺诈报告
		 */
		PRDTYP_FAD("002"),
		/**
		 * 验证报告
		 */
		PRDTYP_VERIF("003");
			
		private PrdTyp(String code){
			this.code = code;
		}
		
		private String code;
		
		public String getCode(){
			return code;
		}
		
	}
	/**
	 * 产品状态
	 * @author yuhui
	 *
	 */
	public enum  Sta{
		/**
		 * 正常
		 */
		STA_NORMAl("1"),
		/**
		 * 删除
		 */
		STA_DEL("0");
			
		private Sta(String code){
			this.code = code;
		}
		
		private String code;
		
		public String getCode(){
			return code;
		}
		
	}
	/**
	 * 产品是否默认选中
	 * @author yuhui
	 *
	 */
	public enum  IsDefult{
		/**
		 * 是
		 */
		ISDEFULT_YES("1"),
		/**
		 * 否
		 */
		ISDEFULT_NO("0");
			
		private IsDefult(String code){
			this.code = code;
		}
		
		private String code;
		
		public String getCode(){
			return code;
		}
		
	}
	/**
	 * 返回状态
	 * @author yuhui
	 *
	 */
	public enum  ResultStatus{
		/**
		 * 成功
		 */
		RESULTSTATUS_SUCCESS("1"),
		/**
		 * 失败
		 */
		RESULTSTATUS_FAIL("2"),
		/**
		 * 其他
		 */
		RESULTSTATUS_OTHER("3"),
		/**
		 * 调用核心失败
		 */
		RESULTSTATUS_INTERFACEFAIL("0");
		private ResultStatus(String code){
			this.code = code;
		}
		
		private String code;
		
		public String getCode(){
			return code;
		}
		
	}
	/**
	 * 排序
	 * @author yuhui
	 *
	 */
	public enum  OrderBy{
		/**
		 * 升序
		 */
		ORDERBY_ASC("asc"),
		/**
		 * 降序
		 */
		ORDERBY_DESC("desc");
			
		private OrderBy(String code){
			this.code = code;
		}
		
		private String code;
		
		public String getCode(){
			return code;
		}
		
	}
	
	/**
	 * 是否为空
	 * @author yuhui
	 *
	 */
	public enum  IsNull{
		/**
		 * 是
		 */
		ISNULL_YES("1"),
		/**
		 * 否
		 */
		ISNULL_NO("0");
			
		private IsNull(String code){
			this.code = code;
		}
		
		private String code;
		
		public String getCode(){
			return code;
		}
		
	}
	/**
	 * 数据类型
	 * @author yuhui
	 *
	 */
	public enum  DataType{
		
		/**
		 * 字符
		 */
		DATATYPE_CHAR("str"),		
		/**
		 * 数字
		 */
		DATATYPE_NUMBER("num"),
		/**
		 * 金额
		 */
		DATATYPE_MONEY("money"),
		/**
		 * 日期
		 */
		DATATYPE_DATE("date"),
		/**
		 * 手机号
		 */
		DATATYPE_TELNO("tel"),
		/**
		 * 邮箱
		 */
		DATATYPE_EMAIL("email"),
		/**
		 * 身份证号
		 */
		DATATYPE_PRSNCOD("idcard"),
		/**
		 * 密码
		 */
		DATATYPE_PASS("pass"),
		/**
		 * 下拉
		 */
		DATATYPE_SELECT("select"),
		/**
		 * 单选
		 */
		DATATYPE_RADIO("radio"),
		/**
		 * 复选
		 */
		DATATYPE_CHECKBOX("check"),
		/**
		 * 单文件
		 */
		DATATYPE_ODDFILE("file"),
	
		/**
		 * 列表
		 */
		DATATYPE_LIST("list"),
		/**
		 * 银行
		 */
		DATATYPE_BANK("bank"),
		/**
		 * 护照
		 */
		DATATYPE_PASSPORT("passport"),
		/**
		 * 其他
		 */
		DATATYPE_OTHER("other");
			
		private DataType(String code){
			this.code = code;
		}
		
		private String code;
		
		public String getCode(){
			return code;
		}
		
	}
	/**
	 * 所属模块
	 * @author yuhui
	 *
	 */
	public enum  BelongModule{
		
		/**
		 * 报告信息项模块
		 */
		BELONGMODULE_RPT("001"),
		
		/**
		 * 验证模块
		 */
		BELONGMODULE_FAD_VERIF("002"),
		/**
		 * 异常查询模块
		 */
		BELONGMODULE_FAD_ERROR("003"),
		/**
		 * 天警云模块
		 */
		BELONGMODULE_WRN("004");
		
		private BelongModule(String code){
			this.code = code;
		}
		
		private String code;
		
		public String getCode(){
			return code;
		}
		
	}
	/**
	 * 应用编号
	 * @author yuhui
	 *
	 */
	public enum AppCod{
		
		/**
		 * 个人征信报告
		 */
		APPINFOCODEE_RPT("001","个人征信报告"),
		
		/**
		 * 反欺诈
		 */
		APPINFOCODEE_FAD("002","反欺诈报告"),
		/**
		 * 天警云
		 */
		APPINFOCODEE_WRN("004","天警云");
		private String code;
		private String value;
		private AppCod(String code,String value){
			this.code = code;
			this.value = value;
		}
		public String getValue() {
			return value;
		}
		
		
		public String getCode(){
			return code;
		}
		
	}
	/**
	 * 贷款类型
	 * @author yuhui
	 *
	 */
	public enum LoanType{
		
		/**
		 * 消费贷款
		 */
		LOAN_CONSUMWE("0"),
		
		/**
		 * 汽车贷款
		 */
		LOAN_CAR("1"),
		/**
		 * 购房贷款
		 */
		LOAN_PURCHASE("2");
		
		private LoanType(String code){
			this.code = code;
		}
		
		private String code;
		
		public String getCode(){
			return code;
		}
		
		
	}
	/**
	 * 报告状态
	 * @author yuhui
	 *
	 */
	public enum RptStatus{
		
		/**
		 * 删除
		 */
		STATIS_DELETE("0"),
		
		/**
		 * 查询中
		 */
		STATIS_QUERYIN("1"),
		/**
		 * 查询已完成
		 */
		STATIS_QUERYSUCCESS("2"),
		/**
		 * 超时
		 */
		STATIS_TIMEOUT("3"),
		/**
		 * 异常
		 */
		STATIS_ERROR("4");
		
		
		private RptStatus(String code){
			this.code = code;
		}
		
		private String code;
		
		public String getCode(){
			return code;
		}
		
		
	}
	
	/**
	 * 响应状态
	 * @author yuhui
	 *
	 */
	public enum HttpStatus{
		
		/**
		 * 响应成功
		 */
		STATIS_SUCCESS("1"),
		
		/**
		 * 响应失败
		 */
		STATIS_FAIL("0");
		
		private HttpStatus(String code){
			this.code = code;
		}
		
		private String code;
		
		public String getCode(){
			return code;
		}
		
		
	}
	/**
	 * Http 方法常量:post和get两个方法
	 * @author miles
	 *
	 */
	public enum HttpMethod{
		
		/**
		 * Http post 方法
		 */
		HTTP_METHOD_POST("post"),
		
		/**
		 * Http get 方法
		 */
		HTTP_METHOD_GET("get");
		
		private HttpMethod(String code){
			this.code = code;
		}
		
		private String code;
		
		public String getCode(){
			return code;
		}
		
		
	}
	
	
	
	/**
	 * 参数类型常量
	 * @author miles
	 *
	 */
	public enum HttpParamType{
		/**
		 * 参数类型:普通参数
		 */
		PARAMETER_TYPE_PARAMETERS("0"),
		
		/**
		 * 参数类型: 文件流
		 */
		PARAMTER_TYPE_STREAM("1"),
		/**
		 * 参数类型: 请求头
		 */
		PARAMETER_TYPE_HEADER("2");
		
		private String code;
		
		private HttpParamType(String code){
			this.code = code;
		}
		
		public String getCode(){
			return code;
		}
		
		
	}
	/**
	 * 天警云是否终止监控
	 * @author miles
	 *
	 */
	public enum IsStopIt{
		/**
		 * 终止监控
		 */
		STOPIT("0"),
		/**
		 * 开始监控
		 */
		START("1");
		
		private String value;
		
		private IsStopIt(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
	}
	
	/**
	 * 天警云 监控人员状态
	 * @author yuhui
	 *
	 */
	public enum WrnMonitorStatus{
		/**
		 * 待补充资料
		 */
		WRNSTATUS_TOBEADD("0"),
		/**
		 * 正在监控中
		 */
		WRNSTATUS_MONITOR("1"),
		/**
		 * 暂停监控
		 */
		WRNSTATUS_SUSPENDMONITOR("2"),
		/**
		 * 停止监控
		 */
		WRNSTATUS_STOPMONITOR("3"),
		/**
		 * 数据异常
		 */
		WRNSTATUS_ERRORDATA("4");
		
		private String value;
		
		private WrnMonitorStatus(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
	}
	
	/**
	 * 报文响应的状态
	 * @author miles
	 *
	 */
	public enum ResponseStatus{
		/**
		 * 响应状态成功
		 */
		RESPONSE_STATUS_SUCCESS("1"),
		/**
		 * 响应状态失败
		 */
		RESPONSE_STATUS_FAILURE("0");
		
		private String value;
		
		private ResponseStatus(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
	}
	
	/**
	 * Http请求是否批量
	 * @author 于辉
	 * 
	 */
	public enum IsBatch{
		IS_YES("是","1"),
		IS_NO("否","0");
		
		private String code;
		
		private String value;
		
		private IsBatch(String code, String value){
			this.code = code;
			this.value = value;
		}

		public String getCode(){
			return code;
		}
		
		public String getValue() {
			return value;
		}
	}
	/**
	 * 报文操作类型
	 * @author xiehao
	 *
	 */
	public enum MsgCode{
		MSGCODE_ADDPRD("addPrd","001"),
		MSGCODE_DELPRD("delPrd","002"),
		MSGCODE_UPDATEPRD("updatePrd","003"),//最后一行常量的末尾为分号,其他为逗号
		MSGCODE_ADDCHILDACC("addChildAcc","004"),//新增子账号
		MSGCODE_UPDATECHILDACC("updateChildAcc","005"),//修改子账号
		MSGCODE_DELETECHILDACC("deleteChildAcc","006"),//删除子账号
		MSGCODE_ADDORGTEAM("addOrgTeam","007"),//团队新增
		MSGCODE_UPDATEORGTEAM("updateOrgTeam","008"),//团队修改
		MSGCODE_DELETEORGTEAM("deleteOrgTeam","009"),//团队删除
		MSGCODE_UPDATEPWD("updatepwd","0010");       //密码修改
		private String code;
		
		private String value;
		
		private MsgCode(String code, String value){
			this.code = code;
			this.value = value;
			
		}

		public String getCode(){
			return code;
		}
		
		public String getValue() {
			return value;
		}
	}
	
	
	/**
	 * 用户类型
	 * @author yuhui
	 *
	 */
	public enum UsrType{
		USRTYPE_ENTERPRISE("USRTYPE_ENTERPRISE","200");//企业用户
		
		
		private String code;
		
		private String value;
		
		private UsrType(String code, String value){
			this.code = code;
			this.value = value;
			
		}

		public String getCode(){
			return code;
		}
		
		public String getValue() {
			return value;
		}
	}
	
	
	/**
	 * Http请求的类型常量字段用于保存
	 * @author miles
	 *
	 */
	public enum MsgType{
		/**
		 * 请求报文
		 */
		MESSAGE_TYPE_REQUEST("req","1"),
		/**
		 * 响应报文
		 */
		MESSAGE_TYPE_RESPONSE("res","2"),
		/**
		 * 交易请求即时响应报文 
		 */
		MESSAGE_TYPE_REQ_INSTANT_RESPONSE("req_inst","3"),
		
		/**
		 * 交易响应即时响应报文
		 */
		MESSAGE_TYPE_RES_INSTANT_RESPONSE("res_inst","4"),
		/**
		 * 结果获取报文
		 */
		MESSAGE_TYPE_RESULT("reslut","5"),
		/**
		 * 重发报文
		 */
		MESSAGE_TYPE_RESEND("resend","8");
		
		private String code;
		
		private String value;
		
		private MsgType(String code, String value){
			this.code = code;
			this.value = value;
			
		}

		public String getCode(){
			return code;
		}
		
		public String getValue() {
			return value;
		}
		
	}

	
	/**
	 * 征信报告类型
	 * @author miles
	 *
	 */
	public enum RptTyp{
		RPTTYP_FRAUD("002","反欺诈报告"),
		RPTTYP_VERIFIED("003","验证报告"),
		RPTTYP_PRSN_CREDIT("001","个人征信报告");
		
		
		private String code;
		
		private String value;
		
		private RptTyp(String code, String value){
			this.code = code;
			this.value = value;
		}

		public String getCode() {
			return code;
		}

		public String getValue() {
			return value;
		}
	}
	
	/**
	 * 数据上传来源
	 * @author xiehao
	 *
	 */
	public enum DatCmitori{
		DatCmitori_PLANT("1","平台提交"),
		DatCmitori_INTERFACE("2","底层接口"),
		DatCmitori_WECHAT("3","微信"),
		DatCmitori_APP("4","APP");
		
		private String code;
		
		private String value;
		
		private DatCmitori(String code, String value){
			this.code = code;
			this.value = value;
		}

		public String getCode() {
			return code;
		}

		public String getValue() {
			return value;
		}
	}
	
	/**
	 * 证件类型
	 * @author miles
	 *
	 */
	public enum CredTyp{
		CREDTYP_PRSN("1001","身份证");
		
		private String code;
		
		private String value;
		
		private CredTyp(String code, String value){
			this.code = code;
			this.value = value;
		}

		public String getCode() {
			return code;
		}

		public String getValue() {
			return value;
		}
		
		
	}
	public static String getChnlcode() {
		return CHNLCODE;
	}
	public static String getMsgUploadPath() {
		return MSG_UPLOAD_PATH;
	}
	public static String getDefaultMessageFileFolder() {
		return DEFAULT_MESSAGE_FILE_FOLDER;
	}
	public static String getRolegroupOrdinary() {
		return ROLEGROUP_ORDINARY;
	}
	public static String getOrgtypeTeam() {
		return ORGTYPE_TEAM;
	}
	public static String getPwdDefault() {
		return PWD_DEFAULT;
	}
	
	public static String getSecretKey() {
		return SECRET_KEY;
	}
	public static String getModelpath() {
		return MODELPATH;
	}
	public static String getTrncods() {
		return TRNCODS;
	}
	public static String getConfigUrl() {
		return CONFIG_URL;
	}
	public static String getMessageUrl() {
		return MESSAGE_URL;
	}
	public static int getTopNMaxResult() {
		return TOP_N_MAX_RESULT;
	}
	public static int getMaxPlatalarmcount() {
		return max_PlatAlarmCount;
	}
	public static int getMaxAccalarmcount() {
		return max_AccAlarmCount;
	}
	public static int getMaxPlatrptcount() {
		return max_PlatRptCount;
	}
	public static String getPrdDefaultSyscod() {
		return PRD_DEFAULT_SYSCOD;
	}
	public static int getPrdIncreaseStep() {
		return PRD_INCREASE_STEP;
	}
	public static int getPrdStartCount() {
		return PRD_START_COUNT;
	}
	public static String getDefaultEncoding() {
		return DEFAULT_ENCODING;
	}
	public static int getJrnIncreaseStep() {
		return JRN_INCREASE_STEP;
	}
	public static int getUsagePointIncrement() {
		return USAGE_POINT_INCREMENT;
	}
	public static int getPrsnnamlength() {
		return PRSNNAMLENGTH;
	}
	public static int getPrsncodlength() {
		return PRSNCODLENGTH;
	}
	public static int getTelnolength() {
		return TELNOLENGTH;
	}
	public static String getNormal() {
		return NORMAL;
	}
	
}
