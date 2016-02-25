package com.sinoway.common.util.datatables.entity;

import java.util.List;


public class TableResData {
	private int draw;
	private List pageData;
	
	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public List getPageData() {
		return pageData;
	}

	public void setPageData(List pageData) {
		this.pageData = pageData;
	}
	
}
