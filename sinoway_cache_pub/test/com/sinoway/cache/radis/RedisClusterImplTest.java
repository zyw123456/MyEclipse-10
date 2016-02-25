package com.sinoway.cache.radis;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.sinoway.cache.ICache;
import com.sinoway.cache.redis.RedisClusterImpl;
import com.sinoway.cache.redis.utils.RedisConnectConfig;

public class RedisClusterImplTest {
	
	private final static Logger logger = LoggerFactory.getLogger(RedisClusterImplTest.class);
	RedisClusterImpl rci;
	
	@Before
	public void setUp() throws Exception {
		RedisConnectConfig rcc = new RedisConnectConfig();
		rcc.setDataDefaultTime("2592000");
		rcc.setHostLists("10.1.2.1:7000,10.1.2.2:7000,10.1.2.3:7000,10.1.2.1:7002,10.1.2.2:7002,10.1.2.3:7002");
		
		rci = new RedisClusterImpl(rcc);
	}

	@After
	public void tearDown() throws Exception {
		rci = null;
	}

	@Test
	public void testSaveStringString() {
		for(int i=0;i<10;i++){
			final int j = i;
			Thread th = new Thread(){
				public void run(){
					
					while(true){
						try{
							String key = "CORE_PARAM_CSM000001020160128528600090054_RES";
							boolean res = rci.save("CORE_"+System.currentTimeMillis(), key);
							System.out.println(j+":"+res);
							Thread.sleep(500);
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
			};
			th.start();
		}
	}

	@Test
	public void testSaveOrUpdateStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		System.out.println(rci.get("CORE_351454047140019"));
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testExist() {
		fail("Not yet implemented");
	}
	
	public static void main(String[] args) {
		
		RedisConnectConfig rcc = new RedisConnectConfig();
		rcc.setDataDefaultTime("2592000");
		rcc.setHostLists("10.1.2.1:7000,10.1.2.2:7000,10.1.2.3:7000,10.1.2.1:7002,10.1.2.2:7002,10.1.2.3:7002");
		
		final RedisClusterImpl rci = new RedisClusterImpl(rcc);
		
		for(int i=0;i<1;i++){
			final int j = i;
			Thread th = new Thread(){
				public void run(){
					int k = 1;
					while(true){
//						synchronized (this) {
							k++;
							long l = System.currentTimeMillis();
							System.out.println(l);
							try{
								boolean res = rci.save("CORE_"+j+l, "CORE_PARAM_CSM000001020160128528600090054_RES");
//								if(!res){
//									System.out.println(j+":"+"CORE_"+j+l);
//								}
								Thread.sleep(1);
							}catch(Exception e){
								e.printStackTrace();
							}
//						}
						
					}
				}
			};
			
			th.start();
		}
		
	}
	
	
  
	public static void main1(String[] args) {
		RedisConnectConfig rcc = new RedisConnectConfig();
		rcc.setDataDefaultTime("2592000");
		rcc.setHostLists("10.1.2.1:7000,10.1.2.2:7000,10.1.2.3:7000,10.1.2.1:7002,10.1.2.2:7002,10.1.2.3:7002");
		
		final RedisClusterImpl cache = new RedisClusterImpl(rcc);
		
		for(int i = 0; i< 100;i++){
			final int j = i;
			Thread t = new Thread(){
				@Override
				public void run(){
					long t = 0;
					while(true){
						try{
							long a = System.currentTimeMillis();
							boolean flg = cache.save("F1M000000920160128528700190022_res_" + j + "_" + t, "{\"header\":{\"chnlcod\":\"50000001\",\"subusrid\":\"16012811211852863f9c8a8081de0003\",\"masttrntim\":\"154949346\",\"masttrndte\":\"20160128\",\"status\":\"1\",\"mastjrn\":\"CCP000000320160128528700120004\",\"prdcod\":\"50000001\",\"orgno\":\"OP2016012852863f00000000\",\"prdjrn\":\"CP5000000120160128528700120000\",\"usrid\":\"SQCW000001\",\"fnttrnjrn\":\"F25000000120160128528700190000\"},\"bodys\":[{\"personalInf\":{\"birthday\":\"19800120\",\"prsnnam\":\"俞品元\",\"sex\":\"男\",\"cretOrg\":\"江苏省张家港市\",\"idcradChkRes\":\"一致\",\"idcard\":\"320582198001200315\"},\"professionInf\":[],\"trncod\":\"P0000001\",\"eduInf\":[]},{\"lealpersonInf\":[{\"cny\":\"\",\"compRegistNo\":\"320623000240021\",\"compNam\":\"如东润州机械有限公司\",\"compTyp\":\"有限责任公司(自然人独资)\",\"compRegistCaptl\":\"1180.000000\",\"compSta\":\"吊销\"},{\"cny\":\"\",\"compRegistNo\":\"320582000110718\",\"compNam\":\"张家港市建元机械制造有限公司\",\"compTyp\":\"有限责任公司(自然人投资或控股)\",\"compRegistCaptl\":\"50.000000\",\"compSta\":\"在营（开业）\"},{\"cny\":\"人民币\",\"compRegistNo\":\"3205823433538\",\"compNam\":\"张家港市杨舍东莱建元电脑店\",\"compTyp\":\"个体\",\"compRegistCaptl\":\"0.000000\",\"compSta\":\"注销\"}],\"trncod\":\"P0000004\",\"executiveInf\":[{\"cny\":\"\",\"compDuties\":\"执行董事兼总经理\",\"compRegistNo\":\"320623000240021\",\"compNam\":\"如东润州机械有限公司\",\"compTyp\":\"有限责任公司(自然人独资)\",\"compRegistCaptl\":\"1180.000000\",\"compSta\":\"吊销\"},{\"cny\":\"\",\"compDuties\":\"执行董事兼总经理\",\"compRegistNo\":\"320582000110718\",\"compNam\":\"张家港市建元机械制造有限公司\",\"compTyp\":\"有限责任公司(自然人投资或控股)\",\"compRegistCaptl\":\"50.000000\",\"compSta\":\"在营（开业）\"},{\"cny\":\"人民币\",\"compDuties\":\"\",\"compRegistNo\":\"3205823433538\",\"compNam\":\"张家港市杨舍东莱建元电脑店\",\"compTyp\":\"个体\",\"compRegistCaptl\":\"0.000000\",\"compSta\":\"注销\"}]},{\"loanOvrChkInf\":{\"loanOvrChkRes\":\"是\",\"loanOvrCert\":\"80%\"},\"trncod\":\"P0000002\",\"mobileChkInf\":{\"mobileChkRes\":\"一致\"},\"caseLawInf\":[{\"regstrDate\":\"2015/05/07\",\"caseLawCret\":\"90%\",\"caseNo\":\"(2015)张执字第01030号\",\"caseLawTyp\":\"执行公告\",\"caseLawMrk\":\"法院：张家港市人民法院\"},{\"regstrDate\":\"2015/05/07\",\"caseLawCret\":\"90%\",\"caseNo\":\"(2015)张执字第01029号\",\"caseLawTyp\":\"执行公告\",\"caseLawMrk\":\"法院：张家港市人民法院\"},{\"regstrDate\":\"2015/05/07\",\"caseLawCret\":\"90%\",\"caseNo\":\"(2015)张执字第01029号\",\"caseLawTyp\":\"失信公告\",\"caseLawMrk\":\"法院：张家港市人民法院,案号：(2015)张民初字第00428号\"}],\"bnkcrdChkInf\":{\"bnkcrdChkRes\":\"无记录\"},\"highRiskPsnChkInf\":{\"highRiskPsnRes\":\"否\",\"highRiskPsnCert\":\"40%\"}},{\"airFlyInf\":{\"airDomesticCnt\":\"0\",\"airCochClasCnt\":\"2\",\"airInternalCnt\":\"2\",\"airDelayTims\":\"0\",\"airDis1YearAvg\":\"100\",\"airFstClasCnt\":\"0\",\"airTickBefDayAvg\":\"1\",\"lstAirArvCity\":\"金边\",\"airPriceAvg\":\"10\",\"airFlyAllCnt\":\"2\",\"airBusinessCnt\":\"0\",\"airBusyMth\":\"201601\",\"airBusyMthCnt\":\"2\",\"lstAirFrmCity\":\"\",\"airFreqFrmCity\":\"1次,金边1次\",\"lstAirFlyDte\":\"20160119\",\"airFreqArvCity\":\"金边1次,1次\",\"airDelayTimsAvg\":\"0\",\"airFreePassCnt\":\"1\",\"airAllMileage\":\"0\",\"airCompRideMost\":\"BD,2次\"},\"trncod\":\"P0000003\"}]}",30);
							long s = System.currentTimeMillis() - a;
							if(!flg){
								logger.info("id:" + j + ",key:" + "F1M000000920160128528700190021_res_" + j + "_" + t +",result:" + flg + ",time:" + s);
								System.out.println("id:" + j + ",key:" + "F1M000000920160128528700190021_res_" + j + "_" + t +",result:" + flg + ",time:" + s);
							}else{
//								System.out.println("true");
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
