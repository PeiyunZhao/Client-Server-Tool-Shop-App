package server.model;

public class Tool {
	
	private String toolID;
	private String name; 
	private double price;
	private Supplier sup;
	private int quantity;
	
	//positive reorder indicates quantity that needs to be ordered
	//negative reorder indicates quantity that has been ordered 
	private int reorder=0;
	
	public Tool(String id, String name, int quantity, double price, Supplier sup ) {
		this.setToolID(id);
		this.setName(name);
		this.setPrice(price);
		this.setSup(sup);
		this.setQuantity(quantity);
		
	}

	//getters and setters
	
	/**
	 * return tool details as string
	 * @return tool details as String
	 */
	public String toString(){
		String out = name +",\t$"+Double.toString(price)+",\tInstock: "+Integer.toString(quantity)
				+ "\nTool ID: "+toolID+",\tSupplier ID:"+sup.getSupID()+"\n";
		return out;
	}
	
	public String getToolID() {
		return toolID;
	}
	public void setToolID(String toolID) {
		this.toolID = toolID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Supplier getSup() {
		return sup;
	}
	public void setSup(Supplier sup) {
		this.sup = sup;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void decrease(int n) {
		this.quantity -=n;
	}

	public int getReorder() {
		return reorder;
	}

	public void setReorder(int reorder) {
		this.reorder = reorder;
	}

}
