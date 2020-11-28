package server.model;

import java.time.LocalDate;
import java.util.LinkedHashSet;

public class Order {
	
	private int orderID;
	private LocalDate date;

	private LinkedHashSet <OrderLine> orderlines;
	
	public Order(int id, LocalDate date) {
		orderlines= new LinkedHashSet <OrderLine> ();
		this.orderID=id;
		setDate(date);
	}
	
	
	public Order(int id, Tool tool) {
		orderlines= new LinkedHashSet <OrderLine> ();
		this.orderID=id;
		orderlines.add(new OrderLine(id, tool));
		setDate(LocalDate.now());
	}
	
	public boolean ifToolInOrder(Tool t) {
		int id=t.getID();
		
		for (OrderLine ol : orderlines) {
			if(id ==ol.getTool().getID())
				return true;
		}
		return false;
	}
	

	public void addOrderLine(Tool tool) {
		orderlines.add(new OrderLine(orderID,tool));
	}

	public int getOrderID() {
		return orderID;
	}
	
	public void setDate(LocalDate d) {
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
	
	public String toString() {
		String out = "" ;
		out += "Order: "+orderID+"  Date: "+date.toString()+"\n" ;
		for(OrderLine ol : orderlines) {
			out += ol.toString();
		}
		return out;
		
	}

	public LinkedHashSet <OrderLine> getOrderLines() {
		return orderlines;
	}


}
