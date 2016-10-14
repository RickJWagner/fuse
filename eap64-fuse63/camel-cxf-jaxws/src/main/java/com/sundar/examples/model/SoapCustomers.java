package com.sundar.examples.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "customers")
public class SoapCustomers {
	 @XmlElement(name="customer")
	protected List<SoapCustomer> soapCustomer;
	public List<SoapCustomer> getSoapCustomer() {
		if (soapCustomer == null) {
			soapCustomer = new ArrayList<SoapCustomer>();
		}
		return this.soapCustomer;
	}
	
}
