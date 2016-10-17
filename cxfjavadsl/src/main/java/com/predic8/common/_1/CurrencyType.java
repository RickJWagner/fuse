
package com.predic8.common._1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CurrencyType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CurrencyType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="EUR"/>
 *     &lt;enumeration value="USD"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CurrencyType")
@XmlEnum
public enum CurrencyType {

    EUR,
    USD;

    public String value() {
        return name();
    }

    public static CurrencyType fromValue(String v) {
        return valueOf(v);
    }

}
