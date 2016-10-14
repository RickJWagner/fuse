package com.sundar.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.namespace.QName;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.converter.dozer.DozerBeanMapperConfiguration;
import org.apache.camel.converter.dozer.DozerTypeConverterLoader;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.message.Message;

import com.predic8.crm._1.CustomerType;
import com.predic8.wsdl.crm.crmservice._1.CRMServicePT;
import com.sundar.examples.interceptors.RequestInterceptor;
import com.sundar.examples.interceptors.ResponseInterceptor;
import com.sundar.examples.model.SoapCustomer;
import com.sundar.examples.model.SoapCustomers;
import com.sundar.examples.strategies.ListAggregationStrategy;

@Startup
@ContextName("cxfwsContext")
@ApplicationScoped
public class CxfWsRouteBuilder extends RouteBuilder{
	
	@Inject
	RequestInterceptor requestInterceptor;
	@Inject
	ResponseInterceptor responseInterceptor;

	@Override
	public void configure() throws Exception {
		
		ActiveMQComponent activeMQComponent = new ActiveMQComponent();
		activeMQComponent.setBrokerURL("tcp://localhost:61616");
		activeMQComponent.setUserName("admin");
		activeMQComponent.setPassword("admin");
		getContext().addComponent("activemq", activeMQComponent);
		
		CxfEndpoint endpoint =new  CxfEndpoint();
		endpoint.setAddress("http://www.predic8.com:8080/crm/CustomerService");
		endpoint.setServiceName(new QName("http://predic8.com/wsdl/crm/CRMService/1/","CustomerService"));
		endpoint.setPortName(new QName("http://predic8.com/wsdl/crm/CRMService/1/","CRMServicePTPort"));
		endpoint.setServiceClass(CRMServicePT.class);
		endpoint.setCamelContext(getContext());
		List<Interceptor<? extends Message>> outInterceptors = new ArrayList<Interceptor<?extends Message>>();
		outInterceptors.add(requestInterceptor);
		
		List<Interceptor<? extends Message>> inInterceptors = new ArrayList<Interceptor<?extends Message>>();
		inInterceptors.add(responseInterceptor);
		endpoint.setOutInterceptors(inInterceptors);
		
		
		/*
		 * Type Converter configuration for Dozer for converting the Java beans
		 */
		DozerBeanMapperConfiguration configuration = new DozerBeanMapperConfiguration();
		configuration.setMappingFiles(Arrays.asList(new String[] { "arrayMapper.xml" }));
		new DozerTypeConverterLoader(getContext(), configuration);

		
		

		/*
		 * Camel route bean
		 */
		from("direct:getUser").log("log:soapResponseWithEndpoint?showAll=true&multiline=true").process(new Processor() {

			public void process(Exchange arg0) throws Exception {

				arg0.getOut().setBody(arg0.getIn().getBody(String.class));
				arg0.getOut().setHeader(CxfConstants.OPERATION_NAME, "get");
			}
		}).to(endpoint).convertBodyTo(SoapCustomer.class).to("log:converted?showAll=true&multiline=true");

		from("direct:createUser").to("log:soapRequestWithEndpoint?showAll=true&multiline=true")
				.wireTap("direct:activemq")
				.convertBodyTo(CustomerType.class).process(new Processor() {
					public void process(Exchange arg0) throws Exception {
						arg0.getOut().setBody(arg0.getIn().getBody(CustomerType.class));
						arg0.getOut().setHeader(CxfConstants.OPERATION_NAME, "create");
					}
				}).to(endpoint).to("log:soapResponseWithEndpoint?showAll=true&multiline=true").end();
		
		from("direct:getAll").log("log:soapResponseWithEndpoint?showAll=true&multiline=true").process(new Processor() {

			public void process(Exchange arg0) throws Exception {

				arg0.getOut().setBody(new Object[0]);
				arg0.getOut().setHeader(CxfConstants.OPERATION_NAME, "getAll");
			}
		}).to(endpoint).process(new Processor() {
			
			public void process(Exchange arg0) throws Exception {
				System.out.println(arg0.getIn().getBody().getClass().getCanonicalName());
				int size = arg0.getIn().getBody(ArrayList.class).size();
				arg0.getOut().setBody(arg0.getIn().getBody(ArrayList.class).get(0));
				arg0.setProperty("completionSize", size);
			}
		}).split(body(),new ListAggregationStrategy()).convertBodyTo(SoapCustomer.class)
		
		.end();
		//Sending to Activemq
		
		from("direct:activemq").convertBodyTo(String.class).to("activemq:queue:RestRequestQueue").end();
		
		//Receiving from activem
		
		from("activemq:queue:RestRequestQueue").log("from mq ${body}");
	}
	

}
