package com.sinoway.fad.entity;

import com.sinoway.rpt.entity.WfDatCreditrpt;

/**
 * 反欺诈报告实体类
 * 
 * @author miles
 *
 */
public class WfDatFraudrpt extends WfDatCreditrpt {

	private static final long serialVersionUID = 1712968421784574589L;

	public WfDatFraudrpt() {
		super();
	}

	
	
	public WfDatFraudrpt(String rptid, String redorptid, String fntjrn, String prsnnam, String credtyp, String credtno,
			String prsncod, String telno, String rpttyp, String rptnam, String prdcod, String prdnam, String datcmitori,
			String rptdte, String rpttim, String rptsta, String rptmoddte, String rptmodtim, String peoplecode,
			String orgno, String parntcode, String reqadrr, String rtpadrr, String usrid, String p_usrid, String corgno,
			String cporgno) {
		super(rptid, redorptid, fntjrn, prsnnam, credtyp, credtno, prsncod, telno, rpttyp, rptnam, prdcod, prdnam, datcmitori,
				rptdte, rpttim, rptsta, rptmoddte, rptmodtim, peoplecode, orgno, parntcode, reqadrr, rtpadrr, usrid, p_usrid,
				corgno, cporgno);
	}




	public WfDatFraudrpt(String rptid, String prsnnam, String prsncod) {
		super(rptid, prsnnam, prsncod);
	}

	public WfDatFraudrpt(String pname, String icardno, String tel, String serpwd, String bcardno, String bank,
			String company, String compaddr, String worktel, String tbacc, String tbpwd, String jdacc, String jdpwd) {
		super();
		this.pname = pname;
		this.icardno = icardno;
		this.tel = tel;
		this.serpwd = serpwd;
		this.bcardno = bcardno;
		this.bank = bank;
		this.company = company;
		this.compaddr = compaddr;
		this.worktel = worktel;
		this.tbacc = tbacc;
		this.tbpwd = tbpwd;
		this.jdacc = jdacc;
		this.jdpwd = jdpwd;
	}

	private String pname;

	private String icardno;

	private String tel;

	private String serpwd;

	private String bcardno;

	private String bank;

	private String company;

	private String compaddr;

	private String worktel;

	private String tbacc;

	private String tbpwd;

	private String jdacc;

	private String jdpwd;

	public String getPname() {
		return getPrsnnam();
	}

	public void setPname(String pname) {
		this.pname = getPrsnnam();
	}

	public String getIcardno() {
		return this.getPrsncod();
	}

	public void setIcardno(String icardno) {
		this.icardno = this.getPrsncod();
	}

	public String getTel() {
		return this.getTelno();
	}

	public void setTel(String tel) {
		this.tel = this.getTelno();
	}

	public String getSerpwd() {
		return serpwd;
	}

	public void setSerpwd(String serpwd) {
		this.serpwd = serpwd;
	}

	public String getBcardno() {
		return bcardno;
	}

	public void setBcardno(String bcardno) {
		this.bcardno = bcardno;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompaddr() {
		return compaddr;
	}

	public void setCompaddr(String compaddr) {
		this.compaddr = compaddr;
	}

	public String getWorktel() {
		return worktel;
	}

	public void setWorktel(String worktel) {
		this.worktel = worktel;
	}

	public String getTbacc() {
		return tbacc;
	}

	public void setTbacc(String tbacc) {
		this.tbacc = tbacc;
	}

	public String getTbpwd() {
		return tbpwd;
	}

	public void setTbpwd(String tbpwd) {
		this.tbpwd = tbpwd;
	}

	public String getJdacc() {
		return jdacc;
	}

	public void setJdacc(String jdacc) {
		this.jdacc = jdacc;
	}

	public String getJdpwd() {
		return jdpwd;
	}

	public void setJdpwd(String jdpwd) {
		this.jdpwd = jdpwd;
	}

	public String toString() {
		return "WfDatFraudrpt [pname=" + pname + ", icardno=" + icardno + ", tel=" + tel + ", serpwd=" + serpwd
				+ ", bcardno=" + bcardno + ", bank=" + bank + ", company=" + company + ", compaddr=" + compaddr
				+ ", worktel=" + worktel + ", tbacc=" + tbacc + ", tbpwd=" + tbpwd + ", jdacc=" + jdacc + ", jdpwd="
				+ jdpwd + "]";
	}

}
