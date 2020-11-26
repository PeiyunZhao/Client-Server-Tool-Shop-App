package server.model;

public class Tool {
	
	private int toolID;
	private String name; 
	private double price;
	private int supplierID;
	private int quantity;
	
	//positive reorder indicates quantity that needs to be ordered
	//negative reorder indicates quantity that has been ordered 
	private int reorder=0;
	
	public Tool(int id, String name, int quantity, double price, int sup ) {
		this.setID(id);
		this.setName(name);
		this.setPrice(price);
		this.setSupplierID(sup);
		this.setQuantity(quantity);
		
	}

	//getters and setters
	
	/**
	 * return tool details as string
	 * @return tool details as String
	 */
	public String toString(){
		String out = "ID: "+toolID+",\t"+ name +",  $"+Double.toString(price)
				+",  Stock: "+quantity+
				",  SupplierID: "+supplierID+"\n";
		return out;
	}
	
	public int getID() {
		return toolID;
	}
	public void setID(int toolID) {
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
	public int getSupplierID() {
		return supplierID;
	}
	public void setSupplierID(int sup) {
		this.supplierID = sup;
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
