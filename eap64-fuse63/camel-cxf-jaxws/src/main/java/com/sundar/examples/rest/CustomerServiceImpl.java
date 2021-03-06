package com.sundar.examples.rest;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.apache.camel.CamelContext;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.cdi.ContextName;

import com.sundar.examples.model.SoapCustomer;
import com.sundar.examples.model.SoapCustomers;

public class CustomerServiceImpl implements CustomerService {

	@Inject
	@ContextName("cxfwsContext")
	private CamelContext myproxyContext;

	@Produce(uri = "direct:getUsers")
	private ProducerTemplate mygetProxy;

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

	public Response createUser(SoapCustomer customer) {
		ProducerTemplate producerTemplate = myproxyContext.createProducerTemplate();
		String requestBody = null;
		try {
			requestBody = producerTemplate.requestBody("direct:createUser", customer, String.class);
			System.out.println("\n\n\nrequestbody" + requestBody);
			if ("Error".equalsIgnoreCase(requestBody)) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
		} catch (Exception e) {
			e.printStackTrace();

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

		return Response.ok(requestBody).build();
	}
	public Response getAll() {
		ProducerTemplate producerTemplate = myproxyContext.createProducerTemplate();
		SoapCustomers customers = new SoapCustomers();
		ArrayList requestBody = null;
		try {
			requestBody = producerTemplate.requestBody("direct:getAll", new Object[0], ArrayList.class);
			System.out.println("\n\n\nrequestbody" + requestBody);
			
			customers.getSoapCustomer().addAll(requestBody);
		} catch (Exception e) {
			e.printStackTrace();

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

		return Response.ok(customers).build();
	}
}
