package server.model;

import java.util.*;

public class OrderList {

	private LinkedHashSet <Order> orders;
	
	public OrderList() {
		orders= new LinkedHashSet <Order> ();
	}
	
	public void addOrder(Order o) {
		orders.add(o);
	}
	/**
	 * generates a brand new id that has never been used before
	 * @return 5 digit id as string
	 */
	public String newID() {
		while(true) {
			String id =""; 
			for(int i=0;i<5;i++)
				id+=Integer.toString(new Random().nextInt(10));
			
			if(searchID(id) == null)
				return id;
		}
	}
	
	/**
	 * creates new order based on a single Order
	 * 
	 * @param i Order to be ordered
	 * @return order details as string
	 */
	public String newOrder(Tool i) {
		//create temporary list of Order to create new order
		LinkedHashSet <Tool> tempList= new LinkedHashSet <Tool> ();
		tempList.add(i);
		
		Order o= new Order(newID(),tempList);
		
		orders.add(o);
		return o.toString();
	}
	
	/**
	 * searches for Order based on id
	 * @param id Order id as string
	 * @return Order if found null if not
	 */
	public Order searchID(String id) {
		
		for (Order o: orders) {
			if (o.getOrderID().equals(id)) return o;
		}
		return null;
	}
	
	/**
	 * completes Order based on id
	 * 
	 * @param id Order id as string
	 * @return completion message
	 */
	public String completeOrder(String id) {
		Order o = searchID(id); 
		if(o != null) {
			
			o.setComplete(true);
			return "Success! Order Completed!";
		}
		else {
			return "Error: Order Not Found!";
		}
	}
}
