
package com.test.yimei;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Get_Be_Overdue_PlatformResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getBeOverduePlatformResult"
})
@XmlRootElement(name = "Get_Be_Overdue_PlatformResponse")
public class GetBeOverduePlatformResponse {

    @XmlElement(name = "Get_Be_Overdue_PlatformResult")
    protected String getBeOverduePlatformResult;

    /**
     * ��ȡgetBeOverduePlatformResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetBeOverduePlatformResult() {
        return getBeOverduePlatformResult;
    }

    /**
     * ����getBeOverduePlatformResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetBeOverduePlatformResult(String value) {
        this.getBeOverduePlatformResult = value;
    }

}