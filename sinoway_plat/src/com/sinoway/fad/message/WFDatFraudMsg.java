package com.sinoway.fad.message;

/**
 * 反欺诈报文实体
 * @author miles
 *
 */
public class WFDatFraudMsg {

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
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getIcardno() {
		return icardno;
	}

	public void setIcardno(String icardno) {
		this.icardno = icardno;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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
		return "WFDatFraudMsg [pname=" + pname + ", icardno=" + icardno + ", tel=" + tel + ", serpwd=" + serpwd
				+ ", bcardno=" + bcardno + ", bank=" + bank + ", company=" + company + ", compaddr=" + compaddr
				+ ", worktel=" + worktel + ", tbacc=" + tbacc + ", tbpwd=" + tbpwd + ", jdacc=" + jdacc + ", jdpwd="
				+ jdpwd + "]";
	}
	
}
