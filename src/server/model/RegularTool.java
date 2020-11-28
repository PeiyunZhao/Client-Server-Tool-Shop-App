package server.model;

public class RegularTool extends Tool{

	public RegularTool(int id, String name, int quantity, double price, int sup, int reorder) {
		super(id, name, quantity, price, sup,reorder);
		// TODO Auto-generated constructor stub
	}

	/**
	 * return tool details as string
	 * @return tool details as String
	 */
	@Override
	public String toString() {
		String out = "ID: "+super.getID()+",\t"+ super.getName() +",  $"+Double.toString(super.getPrice())
		+",  Stock: "+super.getQuantity()+
		",  SupplierID: "+super.getSupplierID()+"\n";
		return out;

	}
	
	
	

}
