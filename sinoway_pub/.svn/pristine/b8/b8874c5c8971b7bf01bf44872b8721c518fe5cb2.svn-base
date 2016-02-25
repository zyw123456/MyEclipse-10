package com.sinoway.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinoway.common.entity.BCfgdefTrnjrn;
import com.sinoway.common.exception.GeneratorException;
import com.sinoway.common.service.BCfgdefTrnjrnService;

/**
 * JRN流水号生成器，采用单例实现 长度30位流水号 生成规则：位系统码 1位类别码 8位交易码 8位日期 4随机码 8位顺序码 采用单例实现
 * 长度24位机构流水号 生成规则: 1位系统码 1位类别码 8位日期 6位随机码 8位顺序号
 * 其中产品码,0和1开始的编码为预留字段,新增的产品从20000001开始
 * @author JMJ
 */

public class JRNGenerator {

//	 @Autowired
	private BCfgdefTrnjrnService bCfgdefTrnjrnService;

	private SimpleDateFormat sdfdte = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");

	private static Map<String, int[]> trnMap = new HashMap<String, int[]>();

	public static final String PRD_DEFAULT_SYSCOD = "X";
	
	private static final int JRN_INCREASE_STEP = 10000;
	
	private static final int PRD_INCREASE_STEP = 100;
	
	private static final int PRD_START_COUNT = 20000001;

	private final int JVM = (int) (System.currentTimeMillis() >>> 8);

	private static int countFalse = 0;
	
	private final String JVMString = formatJVMCount(JVM);
	
	private JRNGenerator() {
		bCfgdefTrnjrnService = (BCfgdefTrnjrnService) com.sinoway.common.frame.SpringContextUtil
				.getBean("bCfgdefTrnjrnService");
		
	}
	
	/**
	 * 私有构造函数
	 */
	private static class JRNGeneratorHolder{
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

	private static String formatCount(int intval) {
		String formatted = Integer.toString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	private synchronized int getPrdCount(String sysCod) throws GeneratorException {
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

	
	private int[] queryPrdCount(String sysCod) throws GeneratorException {
		String currDte = sdfdte.format(new Date());
		int[] resInt = new int[2];

		if (trnMap.containsKey(sysCod )) {
			resInt = trnMap.get(sysCod);
		}
		
		BCfgdefTrnjrn bCfgdefTrnjrn = new BCfgdefTrnjrn();

		bCfgdefTrnjrn.setSyscod(sysCod);
		List tmpList = bCfgdefTrnjrnService.find(bCfgdefTrnjrn);

		if (tmpList != null && tmpList.size() > 0) {
			String resCount = "";
			bCfgdefTrnjrn = (BCfgdefTrnjrn) tmpList.get(0);
			resCount = bCfgdefTrnjrn.getJrnval();
			resInt[0] = Integer.parseInt(resCount);
			resInt[1] = Integer.parseInt(resCount) + PRD_INCREASE_STEP;
			bCfgdefTrnjrn.setJrnval(String.valueOf(resInt[1]));
			int res = bCfgdefTrnjrnService.updateByConditions(bCfgdefTrnjrn, resCount);
			if (res == 0) {// 未更新到说明同一条记录已经被更新需要再执行一次
				countFalse ++;
				if(countFalse <= 5){	//若未更新成功则重复执行
					resInt = queryPrdCount(sysCod);
				}else{
					throw new GeneratorException("记录未更新完成,更新方法执行多次");
				}
			}
		} else {
			String id = GUIDGenerator.generateId();
			bCfgdefTrnjrn.setId(id);
			bCfgdefTrnjrn.setJrnval((int)(PRD_START_COUNT+PRD_INCREASE_STEP)+"");
			resInt[0] = PRD_START_COUNT;
			resInt[1] = PRD_INCREASE_STEP;
			bCfgdefTrnjrn.setSta("1");
			bCfgdefTrnjrn.setJrndte(currDte);
			bCfgdefTrnjrnService.save(bCfgdefTrnjrn);
		}
		return resInt;
	}

	private synchronized int getCount(String sysCod) throws GeneratorException {
		String currDte = sdfdte.format(new Date());
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

	private int[] queryCount(String sysCod) throws GeneratorException {
		String currDte = sdfdte.format(new Date());
		int[] resInt = new int[2];

		if (trnMap.containsKey(sysCod + "_" + currDte)) {
			resInt = trnMap.get(sysCod + "_" + currDte);
		}
		
		BCfgdefTrnjrn bCfgdefTrnjrn = new BCfgdefTrnjrn();

		bCfgdefTrnjrn.setSyscod(sysCod);
		bCfgdefTrnjrn.setJrndte(currDte);
		List tmpList = bCfgdefTrnjrnService.find(bCfgdefTrnjrn);

		if (tmpList != null && tmpList.size() > 0) {
			String resCount = "";
			bCfgdefTrnjrn = (BCfgdefTrnjrn) tmpList.get(0);
			resCount = bCfgdefTrnjrn.getJrnval();
			resInt[0] = Integer.parseInt(resCount);
			resInt[1] = Integer.parseInt(resCount) + JRN_INCREASE_STEP;
			bCfgdefTrnjrn.setJrnval(String.valueOf(resInt[1]));
			int res = bCfgdefTrnjrnService.updateByConditions(bCfgdefTrnjrn, resCount);
			if (res == 0) {// 未更新到说明同一条记录已经被更新需要再执行一次
				countFalse ++;
				if(countFalse <= 5){	//若未更新成功则重复执行
					resInt = queryCount(sysCod);
				}else{
					throw new GeneratorException("记录未更新完成,更新方法执行多次");
				}
			}
		} else {
			String id = GUIDGenerator.generateId();
			bCfgdefTrnjrn.setId(id);
			bCfgdefTrnjrn.setJrnval(JRN_INCREASE_STEP+"");
			resInt[0] = 0;
			resInt[1] = JRN_INCREASE_STEP;
			bCfgdefTrnjrn.setSta("1");
			bCfgdefTrnjrnService.save(bCfgdefTrnjrn);
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
	 * @throws GeneratorException 
	 */
	public static String generateJrn(String sysCod, String sysTyp, String trnCod) throws GeneratorException {
		return getInstance().generateIdStringBuffer(sysCod, sysTyp, trnCod).toString();
	}

	/**
	 * 生成24为流水号
	 * 
	 * @param sysCod
	 * @param sysTyp
	 * @return
	 * @throws GeneratorException 
	 */
	public static String generate24Jrn(String sysCod, String sysTyp) throws GeneratorException {
		return getInstance().generate24StringBuffer(sysCod, sysTyp).toString();
	}

	/**
	 * 生成产品编码8位
	 * 
	 * @return
	 * @throws GeneratorException 
	 */
	public static String generatePrdCod() throws GeneratorException {
		return getInstance().generatePrdCod(PRD_DEFAULT_SYSCOD).toString();

	}

	/**
	 * 生成产品码
	 * 
	 * @param prdDefaultSyscod
	 * @return
	 * @throws GeneratorException 
	 */
	private StringBuffer generatePrdCod(String prdDefaultSyscod) throws GeneratorException {
		return new StringBuffer(8).append(formatCount(getPrdCount(prdDefaultSyscod)));
	}

	/**
	 * 生成24位
	 * 
	 * @param sysCod
	 * @param sysTyp
	 * @param trnCod
	 * @return
	 * @throws GeneratorException 
	 */
	private StringBuffer generate24StringBuffer(String sysCod, String sysTyp) throws GeneratorException {
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
	 * @throws GeneratorException 
	 */
	private StringBuffer generateIdStringBuffer(String sysCod, String sysTyp, String trnCod) throws GeneratorException {
		return new StringBuffer(30).append(sysCod).append(sysTyp).append(trnCod).append(sdfdte.format(new Date()))
				.append(JVMString.substring(0, 4)).append(formatCount(getCount(sysCod)));
	}

	public BCfgdefTrnjrnService getBCfgdefTrnjrnService() {
		return bCfgdefTrnjrnService;
	}

	public void setBCfgdefTrnjrnService(BCfgdefTrnjrnService cfgdefTrnjrnService) {
		bCfgdefTrnjrnService = cfgdefTrnjrnService;
	}

}
