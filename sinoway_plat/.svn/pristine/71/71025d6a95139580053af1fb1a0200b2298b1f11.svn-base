package com.sinoway.common.entity;

public class PageModel {
	
	private int nowPage = 1;
	private int pageSize = 10;
	private int count = 0;
	private int totalPage = 0;
	
	public int getStart(){
		return (nowPage - 1) * pageSize;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(String page) {
		if(page != null && !"".equals(page)){
			nowPage = Integer.parseInt(page);
		}
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotalPage() {
		 totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		 return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
