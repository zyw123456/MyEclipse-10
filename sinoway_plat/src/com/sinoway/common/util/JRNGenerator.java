package com.sinoway.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinoway.common.entity.WfCfgdefJrnrpt;
import com.sinoway.common.exception.GeneratorException;
import com.sinoway.common.service.JrnrptService;
import com.yzj.wf.base.util.SpringContextHelper;
import com.yzj.wf.common.WFException;


/**
 * 
  * JRN流水号生成器， 	长度30位流水号 生成规则：位系统码 1位类别码 8位交易码 8位日期 4随机码 8位顺序码 采用单例实现
 * 					长度24位机构流水号 生成规则: 1位系统码 1位类别码 8位日期 6位随机码 8位顺序号
 * 					长度8位产品编码  生成规则 8位顺序号: 从20000001开始,其中0和1开始为预留字段
 * @author JMJ
 */

public class JRNGenerator {
	
	private static JrnrptService jrnrptService;
	private SimpleDateFormat sdfdte = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");

	private static Map<String, int[]> trnMap = new HashMap<String, int[]>();


	private final int JVM = (int) (System.currentTimeMillis() >>> 8);

	private static int failCount = 0;
	
	private final String JVMString = formatJVMCount(JVM);
	
	private JRNGenerator() {
		jrnrptService = (JrnrptService) SpringContextHelper
				.getBean("jrnrptService");
		
	}
	
	private static class JRNGeneratorHolder{
		/**
		 * 私有构造函数
		 */
		private static final JRNGenerator INSTANCE = new JRNGenerator();

	}

	public static final JRNGenerator getInstance() {
		return JRNGeneratorHolder.INSTANCE;
	}

	private int getJVM() {
		return JVM;
	}

	/**
	 * 生成随机码
	 * 
	 * @param jvmval
	 * @return
	 */
	private String formatJVMCount(int jvmval) {
		String formatted = Integer.toHexString(jvmval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	private String formatCount(int intval) {
		String formatted = Integer.toString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}
	
	
	
	private synchronized int getPrdCount(String sysCod) throws GeneratorException, WFException {
		int[] tem = new int[2];
		if (trnMap.containsKey(sysCod)) {
			tem = trnMap.get(sysCod);
			int curcount = tem[0];
			int lstcount = tem[1];
			if (curcount == 0) {// 开始
				tem = queryPrdCount(sysCod);
				trnMap.put(sysCod, tem);
			}
			if (lstcount == curcount) {// 当前值等于最后的值
				tem = queryPrdCount(sysCod);
				curcount++;
				tem[0] = curcount;
				trnMap.put(sysCod, tem);
			}else{
				tem[0] = ++curcount;
				trnMap.put(sysCod, tem);
			}
		}else {
			tem = queryPrdCount(sysCod);
			trnMap.put(sysCod, tem);
		}
		return tem[0];
	}

	
	private int[] queryPrdCount(String sysCod) throws GeneratorException, WFException {
		int[] resInt = new int[2];

		if (trnMap.containsKey(sysCod )) {
			resInt = trnMap.get(sysCod);
		}
		
		WfCfgdefJrnrpt wfCfgdefJrnrpt = new WfCfgdefJrnrpt();

		wfCfgdefJrnrpt.setSyscod(sysCod);
		List tmpList = jrnrptService.find(wfCfgdefJrnrpt);

		if (tmpList != null && tmpList.size() > 0) {
			String resCount = "";
			wfCfgdefJrnrpt = (WfCfgdefJrnrpt) tmpList.get(0);
			resCount = wfCfgdefJrnrpt.getJrnval();
			resInt[0] = Integer.parseInt(resCount);
			resInt[1] = Integer.parseInt(resCount) + Constant.getPrdIncreaseStep();
			wfCfgdefJrnrpt.setJrnval(String.valueOf(resInt[1]));
			int res = jrnrptService.updateByConditions(wfCfgdefJrnrpt, resCount);
			if (res == 0) {// 未更新到说明同一条记录已经被更新需要再执行一次
				failCount ++;
				if(failCount <= 5){	//若未更新成功则重复执行
					resInt = queryPrdCount(sysCod);
				}else{
					throw new GeneratorException("记录未更新完成,更新方法执行多次");
				}
			}
		} else {
			String id = GUIDGenerator.generateId();
			wfCfgdefJrnrpt.setId(id);
			wfCfgdefJrnrpt.setJrnval(Constant.getPrdIncreaseStep()+"");
			resInt[0] = 0;
			resInt[1] = Constant.getPrdIncreaseStep();
			wfCfgdefJrnrpt.setSta(Constant.Sta.STA_NORMAl.getCode());
			jrnrptService.save(wfCfgdefJrnrpt);
		}
		return resInt;
	}
	
	

	private synchronized int getCount(String sysCod) throws Exception {
		String currDte = DateUtil.getCurrentDate8Len();
		int[] tem = new int[2];
		if (trnMap.containsKey(sysCod + "_" + currDte)) {
			tem = trnMap.get(sysCod + "_" + currDte);
			int curcount = tem[0];
			int lstcount = tem[1];
			if (curcount == 0) {// 开始
				tem = queryCount(sysCod);
				trnMap.put(sysCod + "_" + currDte, tem);
			}
			if (lstcount == curcount) {// 当前值等于最后的值
				tem = queryCount(sysCod);
				curcount++;
				tem[0] = curcount;
				trnMap.put(sysCod + "_" + currDte, tem);
			}else{
				tem[0] = ++curcount;
				trnMap.put(sysCod + "_" + currDte, tem);
			}
		} else {
			tem = queryCount(sysCod);
			String prevDte = sdf.format(DateUtil.getPrevDay(new Date()));
			trnMap.put(sysCod + "_" + currDte, tem);
			trnMap.remove(sysCod + "_" + prevDte);// 删除昨天的
		}
		return tem[0];
	}

	private int[] queryCount(String sysCod) throws Exception {
		String currDte = DateUtil.getCurrentDate8Len();
		int[] resInt = new int[2];

		if (trnMap.containsKey(sysCod + "_" + currDte)) {
			resInt = trnMap.get(sysCod + "_" + currDte);
		}
		
		WfCfgdefJrnrpt wfCfgdefTrnjrn = new WfCfgdefJrnrpt();

		wfCfgdefTrnjrn.setSyscod(sysCod);
		wfCfgdefTrnjrn.setJrndte(currDte);
		List tmpList = jrnrptService.find(wfCfgdefTrnjrn);

		if (tmpList != null && tmpList.size() > 0) {
			String resCount = "";
			wfCfgdefTrnjrn = (WfCfgdefJrnrpt) tmpList.get(0);
			resCount = wfCfgdefTrnjrn.getJrnval();
			resInt[0] = Integer.parseInt(resCount);
			resInt[1] = Integer.parseInt(resCount) + Constant.getJrnIncreaseStep();
			wfCfgdefTrnjrn.setJrnval(String.valueOf(resInt[1]));
			int res = jrnrptService.updateByConditions(wfCfgdefTrnjrn, resCount);
			if (res == 0) {// 未更新到说明同一条记录已经被更新需要再执行一次
				if(failCount <= 5){
					resInt = queryCount(sysCod);
				}else {
					failCount = 0;
					throw new Exception("更新数据失败,无符合条件"); 
				}
			}
			failCount = 0;
		} else {
			String id = GUIDGenerator.generateId();
			wfCfgdefTrnjrn.setId(id);
			wfCfgdefTrnjrn.setJrnval(Constant.getJrnIncreaseStep() + "");
			resInt[0] = 0;
			resInt[1] = Constant.getJrnIncreaseStep();
			wfCfgdefTrnjrn.setSta(Constant.Sta.STA_NORMAl.getCode());
			jrnrptService.save(wfCfgdefTrnjrn);
		}
		return resInt;
	}

	/**
	 * 
	 * @param sysCod
	 *            1位 系统编码："C","F","A"
	 * @param sysTyp
	 *            1位 系统内部编码：各自定义
	 * @param trnCod
	 *            8位 交易编码： "10001001"
	 * @return
	 * @throws Exception 
	 */
	public static String generateJrn(String sysCod, String sysTyp, String trnCod) throws Exception {
		return getInstance().generateIdStringBuffer(sysCod, sysTyp, trnCod).toString();
	}

	/**
	 * 生成24为流水号
	 * 
	 * @param sysCod
	 * @param sysTyp
	 * @return
	 * @throws Exception 
	 */
	public static String generate24Jrn(String sysCod, String sysTyp) throws Exception {
		return getInstance().generate24StringBuffer(sysCod, sysTyp).toString();
	}

	/**
	 * 生成产品编码8位
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static String generatePrdCod() throws Exception {
		return getInstance().generatePrdCod(Constant.getPrdDefaultSyscod()).toString();

	}

	/**
	 * 生成产品码
	 * 
	 * @param prdDefaultSyscod
	 * @return
	 * @throws Exception 
	 */
	private StringBuffer generatePrdCod(String prdDefaultSyscod) throws Exception {
		return new StringBuffer(8).append(formatCount(getPrdCount(prdDefaultSyscod)));
	}

	/**
	 * 生成24位
	 * 
	 * @param sysCod
	 * @param sysTyp
	 * @param trnCod
	 * @return
	 * @throws Exception 
	 */
	private StringBuffer generate24StringBuffer(String sysCod, String sysTyp) throws Exception {
		return new StringBuffer(24).append(sysCod).append(sysTyp).append(sdfdte.format(new Date()))
				.append(JVMString.substring(0, 6)).append(formatCount(getCount(sysCod)));
	}

	/**
	 * 生成30位
	 * 
	 * @param sysCod
	 * @param sysTyp
	 * @param trnCod
	 * @return
	 * @throws Exception 
	 */
	private StringBuffer generateIdStringBuffer(String sysCod, String sysTyp, String trnCod) throws Exception {
		return new StringBuffer(30).append(sysCod).append(sysTyp).append(trnCod).append(sdfdte.format(new Date()))
				.append(JVMString.substring(0, 4)).append(formatCount(getCount(sysCod)));
	}


	public static void main(String[] args) {
//		Long long1 = System.currentTimeMillis();
//		String sysCod="C";
//		String sysTyp="101";
//		String trnCod="10100001";
//		
//		for(int i=0;i<100;i++){
//			System.out.println("流水号："+JRNGenerator.getInstance().generateJrn(sysCod, sysTyp, trnCod));
//		}
//		System.out.println(System.currentTimeMillis()-long1);
	}
	
	
}