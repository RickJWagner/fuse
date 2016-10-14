package com.ally.redhat.poc.routes;

import java.util.Arrays;

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.converter.dozer.DozerBeanMapperConfiguration;
import org.apache.camel.converter.dozer.DozerTypeConverterLoader;

import com.ally.redhat.poc.soap.endpoint.CustomerServiceEndPoint;
import com.ally.redhat.poc.soap.model.SoapCustomer;
import com.predic8.crm._1.CustomerType;
import com.predic8.wsdl.crm.crmservice._1.CRMServicePT;

@Startup
@ApplicationScoped
@ContextName("restservicecontext")
public class CamelRoute extends RouteBuilder {

	
	@Inject
	CustomerServiceEndPoint endpoint;
	
	@Override
	public void configure() throws Exception {

		/*
		 * Catching the exception thrown in the SearchAllyContract method and
		 * log it to the console
		 */
		/*
		 * Type Converter configuration for Dozer for converting the Java beans
		 */
		DozerBeanMapperConfiguration configuration = new DozerBeanMapperConfiguration();
		configuration.setMappingFiles(Arrays.asList(new String[] { "dozerMapping.xml" }));
		new DozerTypeConverterLoader(getContext(), configuration);

		/*
		 * Activemq Component
		 */

		ActiveMQComponent activeMQComponent = new ActiveMQComponent();
		activeMQComponent.setBrokerURL("tcp://localhost:61616");
		activeMQComponent.setUserName("admin");
		activeMQComponent.setPassword("admin");
		getContext().addComponent("activemq", activeMQComponent);

		getContext().setTracing(false);
		
		
		/*
		 * Camel route bean
		 */
		from("direct:getUser").log("log:soapResponseWithEndpoint?showAll=true&multiline=true").process(new Processor() {

			@Override
			public void process(Exchange arg0) throws Exception {

				CustomerType customerType = endpoint.getProxy().get(arg0.getIn().getBody(String.class));
				arg0.getOut().setBody(customerType, CustomerType.class);
			}
		}).convertBodyTo(SoapCustomer.class).to("log:converted?showAll=true&multiline=true");

		from("direct:createUser").to("log:soapResponseWithEndpoint?showAll=true&multiline=true")
				.wireTap("direct:activemq")
				.convertBodyTo(CustomerType.class).process(new Processor() {

					@Override
					public void process(Exchange arg0) throws Exception {

						 endpoint.getProxy().create(arg0.getIn().getBody(CustomerType.class));
						arg0.getOut().setBody("DONE");
					}
				}).to("log:soapResponseWithEndpoint?showAll=true&multiline=true").end();
		
		//Sending to Activemq
		
		from("direct:activemq").convertBodyTo(String.class).to("activemq:queue:RestRequestQueue").end();
		
		//Receiving from activem
		
		from("activemq:queue:RestRequestQueue").log("from mq ${body}");
		
		
	}

}
