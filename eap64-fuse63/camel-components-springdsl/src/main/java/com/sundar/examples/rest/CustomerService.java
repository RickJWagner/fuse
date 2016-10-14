package com.sundar.examples.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.sundar.examples.model.SoapCustomer;

@Path("/ally")
public interface CustomerService {
		@GET
		@Path("/customer/search/{id}/{name}")
		@Produces("application/xml")
		public Response getUser(@PathParam("id") String id,@PathParam("name") String name);
		@POST
		@Path("/customer")
		@Produces("application/xml")
		public Response createUser(SoapCustomer customer);
		
}
