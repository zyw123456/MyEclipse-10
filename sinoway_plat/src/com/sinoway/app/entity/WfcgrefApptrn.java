package com.sinoway.app.entity;

public class WfcgrefApptrn {
	private String id;
	private String appcod;
	private String appnam;
	private String trncod;
	private String trnnam;
	private String sta;
	public WfcgrefApptrn(String appcod,String trncod){
		this.appcod = appcod;
		this.trncod = trncod;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAppcod() {
		return appcod;
	}
	public void setAppcod(String appcod) {
		this.appcod = appcod;
	}
	public String getAppnam() {
		return appnam;
	}
	public void setAppnam(String appnam) {
		this.appnam = appnam;
	}
	public String getTrncod() {
		return trncod;
	}
	public void setTrncod(String trncod) {
		this.trncod = trncod;
	}
	public String getTrnnam() {
		return trnnam;
	}
	public void setTrnnam(String trnnam) {
		this.trnnam = trnnam;
	}
	public String getSta() {
		return sta;
	}
	public void setSta(String sta) {
		this.sta = sta;
	}

}
