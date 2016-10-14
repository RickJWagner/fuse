package com.sundar.examples.strategies;

import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.sundar.examples.model.SoapCustomer;

public class ListAggregationStrategy implements AggregationStrategy{
	
	public ListAggregationStrategy(){
	
		super();
	}

	 public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
         Message newIn = newExchange.getIn();
         SoapCustomer newBody = newIn.getBody(SoapCustomer.class);
         ArrayList list = null;
         if (oldExchange == null) {
        	 System.out.println("Old Exchange is null : "+newBody);
        	 	list = new ArrayList();
                 list.add(newBody);
                 newIn.setBody(list);
                 newExchange.getOut().setBody(list);
                 return newExchange;
         } else {
                 Message in = oldExchange.getIn();
                 System.out.println("New Exchange not null : "+newBody);
                 list = in.getBody(ArrayList.class);
                 list.add(newBody);
                 return oldExchange;
         }
 }


}
