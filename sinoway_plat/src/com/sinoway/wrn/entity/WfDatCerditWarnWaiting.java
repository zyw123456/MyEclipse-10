package com.sinoway.wrn.entity;

public class WfDatCerditWarnWaiting {
	private String id;
	private String prsnnam;
	private String prsncod;
	private String section;//监控区间
	private String loantyp;
	private String module;//监控模块
	private String sta;
	private String loanlmt;
	private String loanamt;
	private String loansrtdte;
	private String loanenddte;
	private String peoplecode;
	private String parntcode;
	private String prdcod;
	private String prdnam;
	private String telno;
	private String repaydte;
	private String repayamt;
	private String repaytyp;
	
	
	
	
	
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public String getRepaydte() {
		return repaydte;
	}
	public void setRepaydte(String repaydte) {
		this.repaydte = repaydte;
	}
	public String getRepayamt() {
		return repayamt;
	}
	public void setRepayamt(String repayamt) {
		this.repayamt = repayamt;
	}
	public String getRepaytyp() {
		return repaytyp;
	}
	public void setRepaytyp(String repaytyp) {
		this.repaytyp = repaytyp;
	}
	public String getPeoplecode() {
		return peoplecode;
	}
	public void setPeoplecode(String peoplecode) {
		this.peoplecode = peoplecode;
	}
	public String getParntcode() {
		return parntcode;
	}
	public void setParntcode(String parntcode) {
		this.parntcode = parntcode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPrdcod() {
		return prdcod;
	}
	public void setPrdcod(String prdcod) {
		this.prdcod = prdcod;
	}
	public String getPrdnam() {
		return prdnam;
	}
	public void setPrdnam(String prdnam) {
		this.prdnam = prdnam;
	}
	public String getLoanlmt() {
		return loanlmt;
	}
	public void setLoanlmt(String loanlmt) {
		this.loanlmt = loanlmt;
	}
	public String getLoanamt() {
		return loanamt;
	}
	public void setLoanamt(String loanamt) {
		this.loanamt = loanamt;
	}
	public String getLoansrtdte() {
		return loansrtdte;
	}
	public void setLoansrtdte(String loansrtdte) {
		this.loansrtdte = loansrtdte;
	}
	public String getLoanenddte() {
		return loanenddte;
	}
	public void setLoanenddte(String loanenddte) {
		this.loanenddte = loanenddte;
	}
	public String getPrsnnam() {
		return prsnnam;
	}
	public void setPrsnnam(String prsnnam) {
		this.prsnnam = prsnnam;
	}
	public String getPrsncod() {
		return prsncod;
	}
	public void setPrsncod(String prsncod) {
		this.prsncod = prsncod;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getLoantyp() {
		return loantyp;
	}
	public void setLoantyp(String loantyp) {
		this.loantyp = loantyp;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getSta() {
		return sta;
	}
	public void setSta(String sta) {
		this.sta = sta;
	}
	public WfDatCerditWarnWaiting(String warnid, String prsnnam,
			String prsncod, String section, String loantyp, String module,
			String sta) {
		super();
		this.id = warnid;
		this.prsnnam = prsnnam;
		this.prsncod = prsncod;
		this.section = section;
		this.loantyp = loantyp;
		this.module = module;
		this.sta = sta;
	}
	public WfDatCerditWarnWaiting() {
		super();
	}
	
}
