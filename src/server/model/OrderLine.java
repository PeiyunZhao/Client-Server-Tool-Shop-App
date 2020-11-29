package server.model;


public class OrderLine {

	private int toolID;
	private int orderID;
	private int supplierID;
	private int quantity;
	private Tool tool;
	private boolean complete = false;
	
	public OrderLine (int orderID,Tool tool) {
		
		this.tool=tool;
		this.toolID=tool.getID();
		this.orderID=orderID;
		this.supplierID=tool.getSupplierID();
		this.quantity=tool.getReorder();

	
	}
	
	/**
	 * outputs order details and list of tools to be ordered
	 * 
	 * @output order placed
	 */
	public String toString() {
		
		String out = "\tToolName:  "+tool.getName()+"\tQuantity:  "+quantity+
				"\tSupplier:  "+supplierID+"\n";
		
		return out;
	}
	
	//setters and getters
	
	public int getOrderID() {
		return orderID;
	}
	
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	
	public Tool getTool() {
		return tool;
	}
	
	public int getToolID() {
		return toolID;
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
			tool.setQuantity(tool.getQuantity()+tool.getReorder());
			tool.setReorder(0);
		}
		this.complete = complete;
	}

	public int getQuantity() {
		return quantity;
	}
	
}
