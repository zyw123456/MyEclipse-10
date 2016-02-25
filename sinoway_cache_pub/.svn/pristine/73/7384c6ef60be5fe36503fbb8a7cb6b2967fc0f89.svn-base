

import com.sinoway.cache.memcache.MemcacheImpl;
import com.sinoway.cache.memcache.utils.MemcachConnectConfig;
import com.sinoway.cache.redis.RedisClusterImpl;

public class TestBufa extends Thread {
	private static int in=0;
	private static int on=0;
	
	public void run(){
		
		final MemcachConnectConfig config=new MemcachConnectConfig();
		String[] hostList={"10.1.2.1:11251","10.1.2.2:11251","10.1.2.3:11251"};
		config.setHostList(hostList);
		final MemcacheImpl client= new MemcacheImpl();
		client.setMemcacheConfig(config);
		long start = System.currentTimeMillis();
//		try {
//			Thread.sleep(0);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		while(true){
			try{
				Object object = client.get("key");
				System.out.println(object);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		
		for(int i=0;i<100;i++){
			Thread t = new TestBufa();
			t.start();
			
		}

//		final MemcachConnectConfig config=new MemcachConnectConfig();
//		final MemcacheImpl client= new MemcacheImpl();
//		client.setMemcacheConfig(config);
//		long start = System.currentTimeMillis();
//		for (int i = 0; i < 100; i++) {
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					synchronized (this) {
//						System.out.println(client.get("key"));
//					}
//					
//				}
//			}).start();
//		}
		
//		long end = System.currentTimeMillis();
//		
//		long start2 = System.currentTimeMillis();
//		for (int i = 0; i < 10000; i++) {
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					synchronized (this) {
//						ICache client= new RedisClusterImpl();
//						client.get("key");
//					}
//					
//				}
//			}).start();
//		}
//		
//		long end2 = System.currentTimeMillis();
		
//		System.out.println("redis:"+(end-start));
//		System.out.println("memcache:"+(end2-start2));
//		
//		System.out.println("end");
		
	}
}
