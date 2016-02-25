package com.sinoway.cache.memcache.test;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable{
	private String name;
	private int id;
	private List<Integer> phones;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Integer> getPhones() {
		return phones;
	}
	public void setPhones(List<Integer> phones) {
		this.phones = phones;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", phones=" + phones + "]";
	}
	
}
