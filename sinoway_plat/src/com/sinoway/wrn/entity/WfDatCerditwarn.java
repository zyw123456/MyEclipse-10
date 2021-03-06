package com.sinoway.wrn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
// default package
// Generated 2015-12-11 16:04:03 by Hibernate Tools 3.4.0.CR1
import javax.persistence.Table;

/**
 * WfDatCerditwarn generated by hbm2java
 */
public class WfDatCerditwarn implements java.io.Serializable {

	private String warnid;
	private String trnbtchid;
	private String prsnnam;
	private String credtyp;
	private String credtno;
	private String prsncod;
	private String loantyp;
	private String loanamt;
	private String loanlmt;
	private String loansrtdte;
	private String loanenddte;
	private String warntyp;
	private String rptnam;
	private String prdcod;
	private String prdnam;
	private String datcmitori;
	private String warndte;
	private String warntim;
	private String sta;
	private String warnmoddte;
	private String warnmodtim;
	private String peoplecode;
	private String orgno;
	private String parntcode;
	private String warnadrr;
	
	private String usrid;
	private String p_usrid;
	private String corgno;
	private String cporgno;
	

	public WfDatCerditwarn() {
	}

	public WfDatCerditwarn(String warnid, String trnbtchid, String prsnnam, String prsncod) {
		this.warnid = warnid;
		this.trnbtchid = trnbtchid;
		this.prsnnam = prsnnam;
		this.prsncod = prsncod;
	}

	

	public WfDatCerditwarn(String warnid, String trnbtchid, String prsnnam,
			String credtyp, String credtno, String prsncod, String loantyp,
			String loanamt, String loanlmt, String loansrtdte,
			String loanenddte, String warntyp, String rptnam, String prdcod,
			String prdnam, String datcmitori, String warndte, String warntim,
			String sta, String warnmoddte, String warnmodtim,
			String peoplecode, String orgno, String parntcode, String warnadrr,
			String usrid, String p_usrid, String corgno, String cporgno) {
		super();
		this.warnid = warnid;
		this.trnbtchid = trnbtchid;
		this.prsnnam = prsnnam;
		this.credtyp = credtyp;
		this.credtno = credtno;
		this.prsncod = prsncod;
		this.loantyp = loantyp;
		this.loanamt = loanamt;
		this.loanlmt = loanlmt;
		this.loansrtdte = loansrtdte;
		this.loanenddte = loanenddte;
		this.warntyp = warntyp;
		this.rptnam = rptnam;
		this.prdcod = prdcod;
		this.prdnam = prdnam;
		this.datcmitori = datcmitori;
		this.warndte = warndte;
		this.warntim = warntim;
		this.sta = sta;
		this.warnmoddte = warnmoddte;
		this.warnmodtim = warnmodtim;
		this.peoplecode = peoplecode;
		this.orgno = orgno;
		this.parntcode = parntcode;
		this.warnadrr = warnadrr;
		this.usrid = usrid;
		this.p_usrid = p_usrid;
		this.corgno = corgno;
		this.cporgno = cporgno;
	}

	public String getWarnid() {
		return this.warnid;
	}

	
	public void setWarnid(String warnid) {
		this.warnid = warnid;
	}

	public String getTrnbtchid() {
		return this.trnbtchid;
	}

	public void setTrnbtchid(String trnbtchid) {
		this.trnbtchid = trnbtchid;
	}

	@Column(name="PRSNNAM")
	public String getPrsnnam() {
		return this.prsnnam;
	}

	public void setPrsnnam(String prsnnam) {
		this.prsnnam = prsnnam;
	}

	public String getCredtyp() {
		return this.credtyp;
	}

	public void setCredtyp(String credtyp) {
		this.credtyp = credtyp;
	}

	public String getCredtno() {
		return this.credtno;
	}

	public void setCredtno(String credtno) {
		this.credtno = credtno;
	}

	public String getPrsncod() {
		return this.prsncod;
	}

	public void setPrsncod(String prsncod) {
		this.prsncod = prsncod;
	}

	public String getLoantyp() {
		return this.loantyp;
	}

	public void setLoantyp(String loantyp) {
		this.loantyp = loantyp;
	}

	public String getLoanamt() {
		return this.loanamt;
	}

	public void setLoanamt(String loanamt) {
		this.loanamt = loanamt;
	}

	public String getLoanlmt() {
		return this.loanlmt;
	}

	public void setLoanlmt(String loanlmt) {
		this.loanlmt = loanlmt;
	}

	public String getLoansrtdte() {
		return this.loansrtdte;
	}

	public void setLoansrtdte(String loansrtdte) {
		this.loansrtdte = loansrtdte;
	}

	public String getLoanenddte() {
		return this.loanenddte;
	}

	public void setLoanenddte(String loanenddte) {
		this.loanenddte = loanenddte;
	}

	public String getWarntyp() {
		return this.warntyp;
	}

	public void setWarntyp(String warntyp) {
		this.warntyp = warntyp;
	}

	public String getRptnam() {
		return this.rptnam;
	}

	public void setRptnam(String rptnam) {
		this.rptnam = rptnam;
	}

	public String getPrdcod() {
		return this.prdcod;
	}

	public void setPrdcod(String prdcod) {
		this.prdcod = prdcod;
	}

	public String getPrdnam() {
		return this.prdnam;
	}

	public void setPrdnam(String prdnam) {
		this.prdnam = prdnam;
	}

	public String getDatcmitori() {
		return this.datcmitori;
	}

	public void setDatcmitori(String datcmitori) {
		this.datcmitori = datcmitori;
	}

	public String getWarndte() {
		return this.warndte;
	}

	public void setWarndte(String warndte) {
		this.warndte = warndte;
	}

	public String getWarntim() {
		return this.warntim;
	}

	public void setWarntim(String warntim) {
		this.warntim = warntim;
	}

	public String getSta() {
		return this.sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	public String getWarnmoddte() {
		return this.warnmoddte;
	}

	public void setWarnmoddte(String warnmoddte) {
		this.warnmoddte = warnmoddte;
	}

	public String getWarnmodtim() {
		return this.warnmodtim;
	}

	public void setWarnmodtim(String warnmodtim) {
		this.warnmodtim = warnmodtim;
	}


	public String getOrgno() {
		return this.orgno;
	}

	public void setOrgno(String orgno) {
		this.orgno = orgno;
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

	public String getWarnadrr() {
		return this.warnadrr;
	}

	public void setWarnadrr(String warnadrr) {
		this.warnadrr = warnadrr;
	}

	public String getUsrid() {
		return usrid;
	}

	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}

	public String getP_usrid() {
		return p_usrid;
	}

	public void setP_usrid(String p_usrid) {
		this.p_usrid = p_usrid;
	}

	public String getCorgno() {
		return corgno;
	}

	public void setCorgno(String corgno) {
		this.corgno = corgno;
	}

	public String getCporgno() {
		return cporgno;
	}

	public void setCporgno(String cporgno) {
		this.cporgno = cporgno;
	}

	
	
}
