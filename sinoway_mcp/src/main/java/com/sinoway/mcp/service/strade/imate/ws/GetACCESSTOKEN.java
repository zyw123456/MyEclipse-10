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
 *         &lt;element name="AppID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AppSecret" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "appID", "appSecret", "key" })
@XmlRootElement(name = "GetACCESS_TOKEN")
public class GetACCESSTOKEN {

	@XmlElement(name = "AppID")
	protected String appID;
	@XmlElement(name = "AppSecret")
	protected String appSecret;
	@XmlElement(name = "Key")
	protected String key;

	/**
	 * Gets the value of the appID property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAppID() {
		return appID;
	}

	/**
	 * Sets the value of the appID property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAppID(String value) {
		this.appID = value;
	}

	/**
	 * Gets the value of the appSecret property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAppSecret() {
		return appSecret;
	}

	/**
	 * Sets the value of the appSecret property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAppSecret(String value) {
		this.appSecret = value;
	}

	/**
	 * Gets the value of the key property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the value of the key property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setKey(String value) {
		this.key = value;
	}

}
