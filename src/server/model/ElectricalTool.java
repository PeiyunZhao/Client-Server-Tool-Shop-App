package server.model;

public class ElectricalTool extends Tool{

	private String power;
	public ElectricalTool(int id, String name, int quantity, double price, int sup,String power) {
		super(id, name, quantity, price, sup);
		this.setPower(power);
	}
	
	/**
	 * return tool details as string
	 * @return tool details as String
	 */
	public String toString(){
		String out = "ID: "+super.getID()+",\t"+ super.getName() +",  $"+Double.toString(super.getPrice())
		+",  Stock: "+super.getQuantity()+
		",  SupplierID: "+super.getSupplierID()+",  Power:"+power+"\n";
		return out;
	}
	
	public String getPower() {
		return power;
	}
	
	public void setPower(String power) {
		this.power = power;
	}

}
