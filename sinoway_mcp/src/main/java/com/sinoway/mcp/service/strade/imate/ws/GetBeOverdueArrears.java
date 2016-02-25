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
 *         &lt;element name="StarTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EndTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "", propOrder = { "phone", "starTime", "endTime", "accesstoken" })
@XmlRootElement(name = "Get_Be_Overdue_Arrears")
public class GetBeOverdueArrears {

	@XmlElement(name = "Phone")
	protected String phone;
	@XmlElement(name = "StarTime")
	protected String starTime;
	@XmlElement(name = "EndTime")
	protected String endTime;
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
	 * Gets the value of the starTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStarTime() {
		return starTime;
	}

	/**
	 * Sets the value of the starTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStarTime(String value) {
		this.starTime = value;
	}

	/**
	 * Gets the value of the endTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * Sets the value of the endTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEndTime(String value) {
		this.endTime = value;
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