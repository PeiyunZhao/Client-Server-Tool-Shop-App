package server.model;

import java.util.*;

public class CustomerList {
	
	private LinkedHashSet <Customer> customers;
	
	public CustomerList() {
		customers=new LinkedHashSet <Customer> ();
	}
	
	/**
	 * adds customer to customer list
	 * @param s customer
	 */
	public void addCustomer(Customer s) {
	customers.add(s);
	}

	/**
	 * finds customer with matching id
	 * 
	 * @param str customer id input as String
	 * @return customer
	 */
	public Customer searchID(int id) {
		
		for (Customer s: customers) {
			if (s.getCustomerID()==id)
				return s;
		}
		
		return null;
	}
	
	public String printAllCustomers() {
		String out= "";
		for (Customer s : customers) {
			out+=s.toString();
		}
		return out;
	}
	
	public int size() {
		return customers.size();
	}
}
