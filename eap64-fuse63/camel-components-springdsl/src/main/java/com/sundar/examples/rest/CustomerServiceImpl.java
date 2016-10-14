package com.sundar.examples.rest;

import javax.annotation.Resource;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.cdi.ContextName;

import com.sundar.examples.model.SoapCustomer;

public class CustomerServiceImpl implements CustomerService {
	
	@Resource(mappedName="java:jboss/camel/context/cxfws-camel-context")
	CamelContext myproxyContext;

	

	@Override
	public Response getUser(String id, String name) {
		

		/*
		 * Creating a producer template and sending message to the relavant
		 * routes
		 */
		ProducerTemplate producerTemplate = myproxyContext.createProducerTemplate();
		SoapCustomer requestBody = null;
		try {
			requestBody = producerTemplate.requestBody("direct:getUser", id, SoapCustomer.class);

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

		return Response.ok(requestBody).build();
	}

	@Override
	public Response createUser(SoapCustomer customer) {
		
		
		
		ProducerTemplate producerTemplate = myproxyContext.createProducerTemplate();
		String requestBody = null;
		try {
			
			requestBody = producerTemplate.requestBody("direct:createUser", customer, String.class);
			
			if ("Error".equalsIgnoreCase(requestBody)) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
		} catch (Exception e) {
			e.printStackTrace();

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

		return Response.ok(requestBody).build();
	}

}
