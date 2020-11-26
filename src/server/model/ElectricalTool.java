package server.model;

public class ElectricalTool extends Tool{

	private String power;
	public ElectricalTool(int id, String name, int quantity, double price, int sup,String power) {
		super(id, name, quantity, price, sup);
		this.setPower(power);
	}
	
	public String toString(){
		String out = super.getName() +",\t$"+super.getPrice()+",\tInstock: "+super.getQuantity()+",\tPower: "+power
				+ "\nTool ID: "+super.getID()+",\tSupplier ID:"+super.getSupplierID()+"\n";
		return out;
	}
	
	public String getPower() {
		return power;
	}
	
	public void setPower(String power) {
		this.power = power;
	}

}
