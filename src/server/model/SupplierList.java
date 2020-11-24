package server.model;

import java.util.*;

public class SupplierList {
	
	private LinkedHashSet <Supplier> suppliers;
	
	public SupplierList() {
		suppliers=new LinkedHashSet <Supplier> ();
	}
	
	/**
	 * adds supplier to supplier list
	 * @param s supplier
	 */
	public void addSupplier(Supplier s) {
	suppliers.add(s);
	}


	/**
	 * finds supplier with matching id
	 * 
	 * @param str supplier id input as String
	 * @return supplier
	 */
	public Supplier searchID(String str) {
		
		for (Supplier s: suppliers) {
			if (s.getSupID().equals(str))
				return s;
		}
		
		return null;
	}
}
