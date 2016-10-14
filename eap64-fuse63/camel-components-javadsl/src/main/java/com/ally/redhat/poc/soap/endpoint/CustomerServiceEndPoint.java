package com.ally.redhat.poc.soap.endpoint;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.namespace.QName;

import org.apache.camel.CamelContext;
import org.apache.camel.cdi.ContextName;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.ally.redhat.poc.rest.interceptors.RequestInterceptor;
import com.ally.redhat.poc.rest.interceptors.ResponseInterceptor;
import com.predic8.wsdl.crm.crmservice._1.CRMServicePT;

@Named("endpointBean")
public class CustomerServiceEndPoint {

	public CustomerServiceEndPoint() {

	}
	@Inject
	RequestInterceptor requestInterceptor ;
	@Inject
	ResponseInterceptor responseInterceptor;
	/*
	 * Create the service proxy and adds headers for the given soap service
	 */
	public CRMServicePT getProxy() {
		JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();

		// bean.getOutInterceptors().add(new HeaderInterceptor());

		bean.getOutInterceptors().add(requestInterceptor);
		bean.getInInterceptors().add(responseInterceptor);
		bean.setAddress("http://localhost:8080/mywebservice1-0.0.1-SNAPSHOT/CustomerService");
		bean.setServiceClass(com.predic8.wsdl.crm.crmservice._1.CRMServicePT.class);
		CRMServicePT service = (CRMServicePT) bean.create();
		Client proxy = ClientProxy.getClient(service);

		// Creating SOAP headers to the web service request

		// Create a list for holding all SOAP headers
		List<Header> headersList = new ArrayList<Header>();
		try {
			Header testSoapHeader1 = new Header(new QName("http://www.predic8.com:8080/crm/CustomerService", "user"),
					"authuser", new JAXBDataBinding(String.class));
			Header testSoapHeader2 = new Header(
					new QName("http://www.predic8.com:8080/crm/CustomerService", "soapheader2"), "authuserpassword",
					new JAXBDataBinding(String.class));

			headersList.add(testSoapHeader1);
			headersList.add(testSoapHeader2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Add SOAP headers to the web service request
		proxy.getRequestContext().put(Header.HEADER_LIST, headersList);

		return service;
	}

}
