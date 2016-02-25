package com.sinoway.base.test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.sinoway.base.entity.BCfgdefRoleinfo;
import com.sinoway.base.service.BCfgdefRoleinfoService;
import com.sinoway.common.util.GUIDGenerator;

public class TestRoleMapper {
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new FileSystemXmlApplicationContext("conf/spring/applicationContext.xml");
		BCfgdefRoleinfoService rs = (BCfgdefRoleinfoService)context.getBean("bCfgdefRoleinfoService");
		BCfgdefRoleinfo role = new BCfgdefRoleinfo();
		role.setId(GUIDGenerator.generateId());
		role.setRolecod("12345678");
		role.setRolenam("第o个角色");
		BCfgdefRoleinfo role1 = new BCfgdefRoleinfo();
		role1.setId(GUIDGenerator.generateId());
		role1.setRolecod("12345678");
		role1.setRolenam("第一个角色");
		BCfgdefRoleinfo role2 = new BCfgdefRoleinfo();
		role2.setId(GUIDGenerator.generateId());
		role2.setRolecod("12345678");
		role2.setRolenam("第二个角色");
		BCfgdefRoleinfo role3 = new BCfgdefRoleinfo();
		role3.setId(GUIDGenerator.generateId());
		role3.setRolecod("12345678");
		role3.setRolenam("第三个角色");
		BCfgdefRoleinfo role4 = new BCfgdefRoleinfo();
		role4.setId(GUIDGenerator.generateId());
		role4.setRolecod("12345678");
		role4.setRolenam("第四个角色");
		BCfgdefRoleinfo role5 = new BCfgdefRoleinfo();
		role5.setId(GUIDGenerator.generateId());
		role5.setRolecod("12345678");
		role5.setRolenam("第五个角色");
		BCfgdefRoleinfo role6 = new BCfgdefRoleinfo();
		role6.setId(GUIDGenerator.generateId());
		role6.setRolecod("12345678");
		role6.setRolenam("第六个角色");
		Map map = new HashMap();
		map.put("id", GUIDGenerator.generateId());
		map.put("rolecod", "12345678");
		map.put("rolenam", "第七个角色");
		Map map2 = new HashMap();
		map2.put("id", GUIDGenerator.generateId());
		map2.put("rolecod", "12345678");
		map2.put("rolenam", "第八个角色");
		Map map3 = new HashMap();
		//map3.put("id", GUIDGenerator.generateId());
		map3.put("rolecod", "12345678");
		map3.put("rolenam", "第五个角色");
		List entityList = new ArrayList();
		List mapList = new ArrayList();
		//System.out.println(rs.count(map));
		rs.del((Map)null);
		//1.增加对象 通过对象新增,通过map新增,通过对象集合新增
		rs.save(role);
		entityList.add(role1);
		entityList.add(role2);
		entityList.add(role3);
		entityList.add(role4);
		entityList.add(role5);
		entityList.add(role6);
		rs.save(entityList);
		rs.save(map);
		//TODO 不支持List<Map>的批量保存 
		//mapList.add(map2);
		//mapList.add(map3);
		//rs.save(mapList);
		//2.修改对象 通过对象修改一个对象  通过map修改一个对象  通过对象或者map修改部分对象的某些字段为相同值
		role1.setRolecod("88888888");
		role1.setRolenam("修改第1个角色");
		rs.update(role1);
		map.put("rolecod", "88888888");
		map.put("rolenam", "修改第7个角色");
		rs.update(map);
		//TODO 不支持List批量对象的修改
		//rs.update(list);
		//3.删除对象 通过一个对象删除一个对象 通过map删除一个对象  通过对象或者map删除部分对象 通过list删除对象集
//		rs.del(role2);
//		rs.del(map3);
//		List delList = new ArrayList();
//		delList.add(role3);
//		delList.add(role4);
//		rs.del(delList);
//		Map delMap = new HashMap();
//		delMap.put("rolecod", "12345678");
//		rs.del(delMap);
		//4.查找结果集 通过一个对象查找一个对象 通过一个map查找一个对象 通过对象或者map查找部分对象   +分页
		// 若是需要返回map结果集 一:修改mapper的返回类型  二:List的泛型修改为Map
		BCfgdefRoleinfo role7 = new BCfgdefRoleinfo();
		role7.setRolecod("12345678");
		List li = rs.find(role7);
		System.out.println(rs.count(role7)+"-----");
		rs.del(role7);
		
		System.out.println(li.size()+" ****"); 
		Map findMap = new HashMap();  
		findMap.put("rolecod", "88888888");
		li = rs.find(findMap);
		System.out.println(rs.count(findMap)+"-----");
		rs.del(findMap);
		System.out.println(li.size()+" ****");
		li = rs.findByPage(role6, 1, 1);
		System.out.println(li.size()+" ****");
		li = rs.findByPage(map, 1, 1);
		System.out.println(li.size()+" ****");
		
	}
}
