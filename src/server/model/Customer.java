package server.model;

public class Customer {
	
	private int customerID;
	private String fname;
	private String lname;
	private String type;
	private String address;
	private String postalCode;
	private String phone;
	
	/**
	 * class representing supplier of items
	 * 
	 * @param id supplier ID used for tracking and identifying
	 * @param name name of supplier company
	 * @param address supplier location
	 * @param contact name of contact with supplier
	 */
	public Customer(int id, String fname, String lname, String type, String phone, String address ,String postalCode) {
		this.setCustomerID(id);
		this.setAddress(address);
		this.setPhone(phone);
		this.setFname(fname);
		this.setLname(lname);
		this.setType(type);
		this.setPostalCode(postalCode);
	}
	
	public String toString() {
		String out="";
		out+=	"ID: "+customerID+",\t"+
				fname+" "+lname+", "+address+", "+
				postalCode+", "+phone+"  "+type+"\n";
				
		return out;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


	

}
