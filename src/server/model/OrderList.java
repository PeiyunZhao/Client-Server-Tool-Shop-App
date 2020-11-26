package server.model;

import java.util.*;

public class OrderList {
	
	private LinkedHashSet <Order> orders;

	public OrderList() {
		orders= new LinkedHashSet <Order> ();
	}
	
	public void addOrderLine(OrderLine ol) {
		int id=ol.getOrderID();
		Order a= searchID(id);
		if (a==null)
			orders.add(new Order(id, ol.getTool()));
		else
			a.addOrderLine(ol.getTool());
	}
	/**
	 * generates a brand new id that has never been used before
	 * @return 5 digit id as string
	 */
	public int newID() {
		while(true) {
			
			int id=new Random().nextInt(10000);
			
			if(searchID(id) == null) {
				return id;
			}
				
		}
	}
	
	/**
	 * creates new order based on a single Order
	 * 
	 * @param i Order to be ordered
	 * @return order details as string
	 */
	public int newOrder(Tool tool) {
		
		Order o= new Order(newID(),tool);
		
		orders.add(o);
		return o.getOrderID();
	}
	
	/**
	 * searches for Order based on id
	 * @param id Order id as string
	 * @return Order if found null if not
	 */
	public Order searchID(int id) {
		
		for (Order o: orders) {
			if (o.getOrderID()==(id)) return o;
		}
		return null;
	}
	
	/**
	 * completes Order based on id
	 * 
	 * @param id Order id as string
	 * @return completion message
	 */
	public String completeOrder(int id) {
		Order o = searchID(id); 
		if(o != null) {
			o.completeOrder();
			return "Success! Order Completed!";
		}
		else {
			return "Error: Order Not Found!";
		}
	}
	
	public int size(){
		return orders.size();
	}
	
}
