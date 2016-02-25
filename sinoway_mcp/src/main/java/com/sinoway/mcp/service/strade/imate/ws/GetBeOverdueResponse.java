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
 *         &lt;element name="Get_Be_OverdueResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getBeOverdueResult" })
@XmlRootElement(name = "Get_Be_OverdueResponse")
public class GetBeOverdueResponse {

	@XmlElement(name = "Get_Be_OverdueResult")
	protected String getBeOverdueResult;

	/**
	 * Gets the value of the getBeOverdueResult property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGetBeOverdueResult() {
		return getBeOverdueResult;
	}

	/**
	 * Sets the value of the getBeOverdueResult property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGetBeOverdueResult(String value) {
		this.getBeOverdueResult = value;
	}

}