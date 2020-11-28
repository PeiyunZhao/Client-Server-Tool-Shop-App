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

	public Customer deleteCustomerID(int parseInt) {
		Customer customer= null;
		for(Customer c:customers) {
			if(parseInt == c.getCustomerID()) {
				customer=c;
				customers.remove(c);
				return customer;
			}
		}
		return null;
	}

	public LinkedHashSet<Customer> searchType(String arg) {
		
		LinkedHashSet<Customer> results = new LinkedHashSet<Customer>();
		for(Customer c : customers) {
			
			if(c.getType().equals(arg))  results.add(c);
			
		}
		return results;
	}

	public LinkedHashSet<Customer> searchName(String arg) {
		
		LinkedHashSet<Customer> results = new LinkedHashSet<Customer>();
		for(Customer c : customers) {
			
			if(c.getFname().equals(arg) || c.getLname().equals(arg))  results.add(c);
			
		}
		return results;
	}
	
}
