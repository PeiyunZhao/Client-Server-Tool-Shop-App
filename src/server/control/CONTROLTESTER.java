package server.control;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.time.LocalDate;

import server.model.Tool;
import server.model.Customer;


public class CONTROLTESTER {

	public static void main(String[] args) {
		BufferedReader sin = null;
		PrintWriter sout = null;
		
		ModelControl mc = new ModelControl(sin,sout);
//		System.out.println(mc.getModel().printAllTools());
//		System.out.println(mc.getModel().printAllSuppliers());
//		System.out.println(mc.getModel().printAllCustomers());
//		System.out.println(mc.getModel().printAllOrders());	
		
		Customer c=mc.getModel().getCustomers().searchID(1);
		System.out.println(mc.toJSON(c));
//		
//		tool=mc.getModel().getInventory().searchID(1009);
//		System.out.println(mc.toJSON(tool));
//		System.out.println("{\"name\":\"Wing Bats\",\"price\":11.25,\"supplierID\":8003,\"quantity\":11,\"reorder\":0,\"id\":1004}\n" + 
//				"{\"name\":\"Barney Bits\",\"price\":23.59,\"supplierID\":8006,\"quantity\":21,\"reorder\":0,\"power\":\"12V\",\"id\":1009}");
	}
}

