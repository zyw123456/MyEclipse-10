
package com.test.yimei;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Get_Be_Overdue_Platform_DetailResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getBeOverduePlatformDetailResult"
})
@XmlRootElement(name = "Get_Be_Overdue_Platform_DetailResponse")
public class GetBeOverduePlatformDetailResponse {

    @XmlElement(name = "Get_Be_Overdue_Platform_DetailResult")
    protected String getBeOverduePlatformDetailResult;

    /**
     * 获取getBeOverduePlatformDetailResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetBeOverduePlatformDetailResult() {
        return getBeOverduePlatformDetailResult;
    }

    /**
     * 设置getBeOverduePlatformDetailResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetBeOverduePlatformDetailResult(String value) {
        this.getBeOverduePlatformDetailResult = value;
    }

}
