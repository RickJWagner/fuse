package com.example.switchyard;


public interface OrderService {
	public Order newOrder (Order order);
	public Order getOrder (Long orderId);
}
