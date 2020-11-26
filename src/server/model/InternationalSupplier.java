package server.model;

public class InternationalSupplier extends Supplier{

	private double importTax;
	public InternationalSupplier(int id, String name, String address, String contact,String phone,double inTax) {
		super(id, name, address, contact,phone);
		// TODO Auto-generated constructor stub
		setImportTax(inTax);
	}
	public double getImportTax() {
		return importTax;
	}
	public void setImportTax(double importTax) {
		this.importTax = importTax;
	}
	
	public String toString() {
		String out= "ID: "+super.getSupID()+",\t"+ super.getName() +", "+super.getAddress()+
		",\n\t\tContact: " +super.getContact()+
		", Phone: "+super.getPhone()+"  International Import Tax: "+getImportTax()+"\n";
		return out;
	}
	

}
