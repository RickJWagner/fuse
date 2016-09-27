package com.redhat.helloworld1.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/order")
public interface OrderResource {

    @POST
    @Path("/")
    @Produces({"text/xml"})
    public Order newOrder();

    @GET
    @Path("{orderId}")
    @Produces({"text/xml"})
    public Order getOrder(@PathParam("orderId") Long orderId);

}