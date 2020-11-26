package server.model;

public class Supplier {
	
	private int supID;
	private String name;
	private String contact; 
	private String address;
	private String phone;
	
	/**
	 * class representing supplier of items
	 * 
	 * @param id supplier ID used for tracking and identifying
	 * @param name name of supplier company
	 * @param address supplier location
	 * @param contact name of contact with supplier
	 */
	public Supplier(int id, String name, String address ,String contact,String phone) {
		this.setSupID(id);
		this.setName(name);
		this.setContact(contact);
		this.setAddress(address);
		this.setPhone(phone);
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSupID() {
		return supID;
	}

	public void setSupID(int supID) {
		this.supID = supID;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
