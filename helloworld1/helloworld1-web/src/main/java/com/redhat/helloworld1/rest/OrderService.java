package com.redhat.helloworld1.rest;


public interface OrderService {
	public Order newOrder (Order order);
	public Order getOrder (Long orderId);
}
