package server.model;

import java.util.*;

public class OrderList {
	
	private LinkedHashSet <Order> orders;
	private Inventory inventory;
	
	public OrderList(Inventory i) {
		orders= new LinkedHashSet <Order> ();
		setInventory(i);
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
	 * adds order o to orderList or merges with existing Order if order pre-exists in list
	 * 
	 * @param o order to be added
	 */
	
	public void addOrder(Order o) {
		
		Order ordersearch=searchID(o.getOrderID());
		
		if (ordersearch!=null) {
			for(OrderLine ol : o.getOrderLines()) {
				if(!ordersearch.ifToolInOrder(ol.getTool())) {
					ordersearch.addOrderLine(ol.getTool());
				}
			}
		}
		else {
			orders.add(o);
		}
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
	 * @return order 
	 */
	public Order newOrder(Tool tool) {
		
		Order o= new Order(newID(),tool);
		
		orders.add(o);
		return o;
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

	public String printAllOrders() {
		String out = "";
		for(Order o : orders) {
			out+=o.toString();
		}
		return out;
	}

	public Inventory getInventory() {
		return inventory;
	}
	
	public int length() {
		return orders.size();
	}
	

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
}
