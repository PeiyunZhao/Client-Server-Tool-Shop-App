package server.model;
import java.util.Date;
import java.util.LinkedHashSet;

public class Order {
	
	private LinkedHashSet <Tool> orderTools; 
	private Date date;
	private String orderID;
	private boolean complete = false;

	public Order(String orderID, LinkedHashSet <Tool> tools) {
		
		orderTools= new LinkedHashSet <Tool>();
		
		this.orderID=orderID;
		date= new Date();
		
		for (Tool i : tools) {
			//a negative reorder integer indicates that the positive amount has been ordered.
			if(i.getReorder()>0) {
				i.setReorder(-i.getReorder());
				orderTools.add(i);
			}
			else if (i.getQuantity()<40&&i.getReorder()==0) {
				i.setReorder(i.getQuantity()-50);
				orderTools.add(i);
			}
		}
	}
	
	/**
	 * outputs order details and list of tools to be ordered
	 * 
	 * @output order placed
	 */
	public String toString() {
		
		String out= "Order ID:\t\t\t"+ orderID+
				"\nDate:\t\t\t"+ date.toString();
		
		if (orderTools.size()==0) {
			return out+"\nNo tools! Order made by Mistake! ";
		}
		
		for (Tool i : orderTools) {
			out+="\n\nTool Description:\t\t"+i.getName()+
					"\nAmount Ordered:\t\t"+Integer.toString(-i.getReorder())+
					"\nSupplier:\t\t\t "+i.getSup().getName();
		}
		return out;
	}
	
	//setters and getters
	
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	
	public LinkedHashSet<Tool> getTools() {
		return orderTools;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isComplete() {
		return complete;
	}

	/**
	 * completes order and increases tool quantity by amount ordered.
	 * 
	 * @param complete completes order if set to true
	 */
	public void setComplete(boolean complete) {
		if (complete) {
			for (Tool i : orderTools) {
				i.setQuantity(i.getQuantity()-i.getReorder());
				i.setReorder(0);
			}
			this.complete = complete;
		}
	}
}
