package com.sinoway.cache.memcache;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MemcacheImplTest {
	
	MemcacheImpl cache;

	@Before
	public void setUp() throws Exception {
		cache = new MemcacheImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveStringString() {
		fail("Not yet implemented");
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
		for(int i=0;i<300;i++){
			final int j = i;
			Thread th = new Thread(){
				public void run(){
					String key = "CORE_PARAM_CSM000001020160128528600090054_RES";
					String str = cache.get(key);
					System.out.println(j+"_"+str);
				}
			};
			th.start();
		}
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testExist() {
		fail("Not yet implemented");
	}

}
