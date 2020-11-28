package server.control;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.time.LocalDate;


public class CONTROLTESTER {

	public static void main(String[] args) {
		BufferedReader sin = null;
		PrintWriter sout = null;
		
		ModelControl mc = new ModelControl(sin,sout);
//		System.out.println(mc.getModel().printAllTools());
//		System.out.println(mc.getModel().printAllSuppliers());
//		System.out.println(mc.getModel().printAllCustomers());
		System.out.println(mc.getModel().printAllOrders());	
	}
}

