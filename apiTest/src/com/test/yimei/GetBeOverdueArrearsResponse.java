
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
 *         &lt;element name="Get_Be_Overdue_ArrearsResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getBeOverdueArrearsResult"
})
@XmlRootElement(name = "Get_Be_Overdue_ArrearsResponse")
public class GetBeOverdueArrearsResponse {

    @XmlElement(name = "Get_Be_Overdue_ArrearsResult")
    protected String getBeOverdueArrearsResult;

    /**
     * 获取getBeOverdueArrearsResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetBeOverdueArrearsResult() {
        return getBeOverdueArrearsResult;
    }

    /**
     * 设置getBeOverdueArrearsResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetBeOverdueArrearsResult(String value) {
        this.getBeOverdueArrearsResult = value;
    }

}
