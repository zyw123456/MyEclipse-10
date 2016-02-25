package com.sinoway.cache.redis.utils;

import java.util.Set;

import redis.clients.jedis.HostAndPort;

public class RedisConnectConfig {
	public RedisConnectConfig(){
//		Properties p = new Properties();
//		FileInputStream in;
//		try {
//			URL url = MemcachConnectConfig.class.getClassLoader().getResource("");
//			String path= url.getPath().substring(1);
//			//读取memcache-default.properties配置文件
//			File filepath =new File(path,"cache-default.properties");
//			if (!filepath.exists()) {//linux
//				filepath=new File("/"+filepath);
//			}
//			in = new FileInputStream(filepath);
//			p.load(in);
//		} catch (Exception e) {
//			System.err.println("memcache配置文件初始化失败：-------无法读取配置文件cache-default.properties");
//			e.printStackTrace();
//		}
//		//加载服务器地址
//		String hostList = p.getProperty("redis_hostList");
//		if (StringUtils.isNoneBlank(hostList)) {
//			String[] split = hostList.split(",");
//			if (split.length>0) {
//				Set<HostAndPort> nodes=new HashSet<HostAndPort>();
//				for (int i = 0; i < split.length; i++) {
//					String[] node = split[i].split(":");
//					if (StringUtils.isNoneBlank(node[0])&&StringUtils.isNumeric(node[1])) {
//						nodes.add(new HostAndPort(node[0], Integer.parseInt(node[1])));
//					}else{
//						throw new IllegalArgumentException("初始化redis集群节点配置错误：-----格式必须为 ip:端口 如：127.0.0.1:7000");
//					}
//				}
//				if (!nodes.isEmpty()) {
//					this.nodes=nodes;
//				}
//			}
//		}
//		
//		//加载日期默认有效期
//		String dataDefaultTime = p.getProperty("redis_dataDefaultTime");
//		if (StringUtils.isNumeric(dataDefaultTime)) {
//			this.dataDefaultTime=Integer.parseInt(dataDefaultTime);
//		}
	}
	
	/**
	 * 指定默认Redis连接节点
	 */
	private Set<HostAndPort> nodes;
	/**
	 * 默认数据有效期
	 */
	private String dataDefaultTime="2592000";
	
	private String hostLists;
	
	public Set<HostAndPort> getNodes() {
		return nodes;
	}

	public void setNodes(Set<HostAndPort> nodes) {
		this.nodes = nodes;
	}
	
	public void setHostLists(String hostLists) {
		this.hostLists = hostLists;
	}

	public String getHostLists() {
		return hostLists;
	}

	public String getDataDefaultTime() {
		return dataDefaultTime;
	}

	public void setDataDefaultTime(String dataDefaultTime) {
		this.dataDefaultTime = dataDefaultTime;
	}

	@Override
	public String toString() {
		return "RedisConnectConfig [nodes=" + nodes + ", dataDefaultTime="
				+ dataDefaultTime + ", hostLists=" + hostLists + "]";
	}


	
}
