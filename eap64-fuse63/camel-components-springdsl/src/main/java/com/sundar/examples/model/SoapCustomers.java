package com.sundar.examples.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="customers")
public class SoapCustomers {

	List<SoapCustomer> customers;
	 public List<SoapCustomer> getCustomer() {
	        if (customers == null) {
	        	customers = new ArrayList<SoapCustomer>();
	        }
	        return this.customers;
	    }
	 public void setCustomers(ArrayList<SoapCustomer> customers){
		 this.customers=customers;
	 }
}
