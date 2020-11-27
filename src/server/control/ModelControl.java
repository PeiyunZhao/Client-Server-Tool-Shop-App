package server.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import server.model.*;


public class ModelControl{

	private BufferedReader socketIn;
	private PrintWriter socketOut;
	private DatabaseControl databaseControl;
	private Inventory inventory;
	private SupplierList suppliers;
	private CustomerList customers;

	public ModelControl(BufferedReader Sin, PrintWriter Sout) {
		socketIn=Sin;
		socketOut=Sout;
		setDatabaseControl(new DatabaseControl(this));
		inventory= new Inventory();
		suppliers = new SupplierList();
		customers = new CustomerList();
		run();
	}
	
	public void run() {
		databaseControl.loadFromDatabase();
		
		while(true && socketIn !=null && socketOut!=null){
			
		}
	}
	
	public void importTool(Tool t) {
		inventory.addTool(t);
	}
	
	public void importSupplier(Supplier s) {
		suppliers.addSupplier(s);
	}
	
	public void importOrderLine(OrderLine ol) {
		inventory.addOrderLine(ol);
	}
	
	public void importOrder(Order o) {
		inventory.addOrder(o);
	}
	public void importCustomer(Customer c) {
		customers.addCustomer(c);
	}

	public DatabaseControl getDatabaseControl() {
		return databaseControl;
	}

	private void setDatabaseControl(DatabaseControl databaseControl) {
		this.databaseControl = databaseControl;
	}
	
	public String printAllTools() {
		return inventory.printAllTools();
	}

	public String printAllSuppliers() {
		return suppliers.printAllSuppliers();
	}
	
	public String printAllCustomers() {
		return customers.printAllCustomers();
	}
}
