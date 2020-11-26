package server.model;

import java.util.*;

public class Order {
	
	private int orderID;
	private Date date;

	private LinkedHashSet <OrderLine> orderlines;
	
	public Order(int id, Tool tool) {
		orderlines= new LinkedHashSet <OrderLine> ();
		this.orderID=id;
		orderlines.add(new OrderLine(id, tool));
	}
	
	public void addOrderLine(Tool tool) {
		orderlines.add(new OrderLine(orderID,tool));
	}

	public int getOrderID() {
		return orderID;
	}
	
	public void setDate(Date d) {
		date=d;
	}
	
	/**
	 * completes Order based on id
	 * 
	 * @param id Order id as string
	 * @return completion message
	 */
	
	public String completeOrder() {
		if(!orderlines.isEmpty()) {
			for (OrderLine o : orderlines) {
				o.setComplete(true);
			}
			return "Success! Order Completed!";
		}
		else {
			return "Error: No OrderLines!";
		}
	}

	public LinkedHashSet <OrderLine> getOrderLines() {
		return orderlines;
	}


}
