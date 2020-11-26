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


public class ModelControl implements Runnable{

	private BufferedReader socketIn;
	private PrintWriter socketOut;
	private DatabaseControl databaseControl;
	private Inventory inventory;
	private SupplierList suppliers;

	public ModelControl() {
		setDatabaseControl(new DatabaseControl(this));
		inventory= new Inventory();
		suppliers = new SupplierList();
		run();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
	}
	
	public void addTool(Tool t) {
		inventory.addTool(t);
	}
	
	public void addSupplier(Supplier s) {
		suppliers.addSupplier(s);
	}
	
	public void addOrder(OrderLine ol) {
		inventory.addOrderLine(ol);
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
}
