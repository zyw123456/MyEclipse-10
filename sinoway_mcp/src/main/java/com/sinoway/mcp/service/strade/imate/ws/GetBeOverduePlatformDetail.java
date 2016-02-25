package com.sinoway.mcp.service.strade.imate.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cycle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ACCESS_TOKEN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "phone", "cycle", "accesstoken" })
@XmlRootElement(name = "Get_Be_Overdue_Platform_Detail")
public class GetBeOverduePlatformDetail {

	@XmlElement(name = "Phone")
	protected String phone;
	protected String cycle;
	@XmlElement(name = "ACCESS_TOKEN")
	protected String accesstoken;

	/**
	 * Gets the value of the phone property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the value of the phone property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPhone(String value) {
		this.phone = value;
	}

	/**
	 * Gets the value of the cycle property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCycle() {
		return cycle;
	}

	/**
	 * Sets the value of the cycle property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCycle(String value) {
		this.cycle = value;
	}

	/**
	 * Gets the value of the accesstoken property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getACCESSTOKEN() {
		return accesstoken;
	}

	/**
	 * Sets the value of the accesstoken property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setACCESSTOKEN(String value) {
		this.accesstoken = value;
	}

}
