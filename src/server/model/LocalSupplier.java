package server.model;

public class LocalSupplier extends Supplier{

	public LocalSupplier(int id, String name, String address, String contact, String phone) {
		super(id, name, address, contact, phone);
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		String out= "ID: "+super.getSupID()+",\t"+ super.getName() +", "+super.getAddress()+
				",\n\t\tContact: " +super.getContact()+
				", Phone: "+super.getPhone()+"\n";
				return out;
	}
	

}
