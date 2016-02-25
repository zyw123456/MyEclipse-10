package com.sinoway.common.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.sinoway.cache.ICache;
import com.sinoway.common.util.McpLogger;

public class Test {

	public static void main(String[] args) {
		ApplicationContext ac = new FileSystemXmlApplicationContext("target/classes/spring/applicationContext.xml");
		final McpLogger logger = McpLogger.getLogger(Test.class);
		final ICache cache = (ICache)ac.getBean("iCache");
		for(int i = 0; i< 100;i++){
			final int j = i;
			Thread t = new Thread(){
				@Override
				public void run(){
					long t = 0;
					long a = 0;
					long temp = 0;
					while(true){
						try{
							a = System.currentTimeMillis();
							if(a == temp){
								System.out.println(a);
							}
							temp = a;
							boolean flg = cache.save("F1M000000920160128528700190021_res_" + j + "_" + t, "{\"header\":{\"chnlcod\":\"50000001\",\"subusrid\":\"16012811211852863f9c8a8081de0003\",\"masttrntim\":\"154949346\",\"masttrndte\":\"20160128\",\"status\":\"1\",\"mastjrn\":\"CCP000000320160128528700120004\",\"prdcod\":\"50000001\",\"orgno\":\"OP2016012852863f00000000\",\"prdjrn\":\"CP5000000120160128528700120000\",\"usrid\":\"SQCW000001\",\"fnttrnjrn\":\"F25000000120160128528700190000\"},\"bodys\":[{\"personalInf\":{\"birthday\":\"19800120\",\"prsnnam\":\"俞品元\",\"sex\":\"男\",\"cretOrg\":\"江苏省张家港市\",\"idcradChkRes\":\"一致\",\"idcard\":\"320582198001200315\"},\"professionInf\":[],\"trncod\":\"P0000001\",\"eduInf\":[]},{\"lealpersonInf\":[{\"cny\":\"\",\"compRegistNo\":\"320623000240021\",\"compNam\":\"如东润州机械有限公司\",\"compTyp\":\"有限责任公司(自然人独资)\",\"compRegistCaptl\":\"1180.000000\",\"compSta\":\"吊销\"},{\"cny\":\"\",\"compRegistNo\":\"320582000110718\",\"compNam\":\"张家港市建元机械制造有限公司\",\"compTyp\":\"有限责任公司(自然人投资或控股)\",\"compRegistCaptl\":\"50.000000\",\"compSta\":\"在营（开业）\"},{\"cny\":\"人民币\",\"compRegistNo\":\"3205823433538\",\"compNam\":\"张家港市杨舍东莱建元电脑店\",\"compTyp\":\"个体\",\"compRegistCaptl\":\"0.000000\",\"compSta\":\"注销\"}],\"trncod\":\"P0000004\",\"executiveInf\":[{\"cny\":\"\",\"compDuties\":\"执行董事兼总经理\",\"compRegistNo\":\"320623000240021\",\"compNam\":\"如东润州机械有限公司\",\"compTyp\":\"有限责任公司(自然人独资)\",\"compRegistCaptl\":\"1180.000000\",\"compSta\":\"吊销\"},{\"cny\":\"\",\"compDuties\":\"执行董事兼总经理\",\"compRegistNo\":\"320582000110718\",\"compNam\":\"张家港市建元机械制造有限公司\",\"compTyp\":\"有限责任公司(自然人投资或控股)\",\"compRegistCaptl\":\"50.000000\",\"compSta\":\"在营（开业）\"},{\"cny\":\"人民币\",\"compDuties\":\"\",\"compRegistNo\":\"3205823433538\",\"compNam\":\"张家港市杨舍东莱建元电脑店\",\"compTyp\":\"个体\",\"compRegistCaptl\":\"0.000000\",\"compSta\":\"注销\"}]},{\"loanOvrChkInf\":{\"loanOvrChkRes\":\"是\",\"loanOvrCert\":\"80%\"},\"trncod\":\"P0000002\",\"mobileChkInf\":{\"mobileChkRes\":\"一致\"},\"caseLawInf\":[{\"regstrDate\":\"2015/05/07\",\"caseLawCret\":\"90%\",\"caseNo\":\"(2015)张执字第01030号\",\"caseLawTyp\":\"执行公告\",\"caseLawMrk\":\"法院：张家港市人民法院\"},{\"regstrDate\":\"2015/05/07\",\"caseLawCret\":\"90%\",\"caseNo\":\"(2015)张执字第01029号\",\"caseLawTyp\":\"执行公告\",\"caseLawMrk\":\"法院：张家港市人民法院\"},{\"regstrDate\":\"2015/05/07\",\"caseLawCret\":\"90%\",\"caseNo\":\"(2015)张执字第01029号\",\"caseLawTyp\":\"失信公告\",\"caseLawMrk\":\"法院：张家港市人民法院,案号：(2015)张民初字第00428号\"}],\"bnkcrdChkInf\":{\"bnkcrdChkRes\":\"无记录\"},\"highRiskPsnChkInf\":{\"highRiskPsnRes\":\"否\",\"highRiskPsnCert\":\"40%\"}},{\"airFlyInf\":{\"airDomesticCnt\":\"0\",\"airCochClasCnt\":\"2\",\"airInternalCnt\":\"2\",\"airDelayTims\":\"0\",\"airDis1YearAvg\":\"100\",\"airFstClasCnt\":\"0\",\"airTickBefDayAvg\":\"1\",\"lstAirArvCity\":\"金边\",\"airPriceAvg\":\"10\",\"airFlyAllCnt\":\"2\",\"airBusinessCnt\":\"0\",\"airBusyMth\":\"201601\",\"airBusyMthCnt\":\"2\",\"lstAirFrmCity\":\"\",\"airFreqFrmCity\":\"1次,金边1次\",\"lstAirFlyDte\":\"20160119\",\"airFreqArvCity\":\"金边1次,1次\",\"airDelayTimsAvg\":\"0\",\"airFreePassCnt\":\"1\",\"airAllMileage\":\"0\",\"airCompRideMost\":\"BD,2次\"},\"trncod\":\"P0000003\"}]}",30);
							long s = System.currentTimeMillis() - a;
							if(!flg){
								logger.info("id:" + j + ",key:" + "F1M000000920160128528700190021_res_" + j + "_" + t +",result:" + flg + ",time:" + s);
							}
							t ++;
						}catch(Exception e){
							logger.info("id:" + j + ",key:" + "F1M000000920160128528700190021_res_" + j + "_" + t,e);
						}
						
					}
				}
			};
			t.start();
		}
	}
}
