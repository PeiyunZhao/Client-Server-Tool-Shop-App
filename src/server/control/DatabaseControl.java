/**
 * class used to connect to local database
 */
package server.control;

import java.sql.*;
import java.time.LocalDate;  
import server.model.*;

public class DatabaseControl {
	private ModelControl modelControl;
	private Connection jdbc_connection;
	private Statement statement;
	private String databaseName = "TOOLSHOP", tableName = "TOOL", dataFile = "tool.csv";
	private String connectionInfo = "jdbc:mysql://localhost:3306/TOOLSHOP",  
			  login          = "root",
			  password       = "Rosewoodd1211";

	public DatabaseControl(ModelControl mc) {
		try {
			modelControl=mc;
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
			
		} catch (SQLException e ) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadToolList() {
		try {
			String sql = "SELECT * FROM (TOOL AS T LEFT OUTER JOIN ELECTRICAL AS E ON T.ToolID=E.ToolID);";
			statement = jdbc_connection.createStatement();
			ResultSet tools = statement.executeQuery(sql);

			while(tools.next())
			{
				if (tools.getString("Ttype").equals("R")) {
					int id=tools.getInt("ToolID");
					String name = tools.getString("Tname");
					int quantity= tools.getInt("Quantity");
					double price= tools.getDouble("Price");
					int sid= tools.getInt("SupplierID");
					Tool tool= new RegularTool( id, name, quantity, price, sid);
//					System.out.print(tool);
					modelControl.importTool(tool);
					
				}else if (tools.getString("Ttype").equals("E")) {
					int id=tools.getInt("ToolID");
					String name = tools.getString("Tname");
					int quantity= tools.getInt("Quantity");
					double price= tools.getDouble("Price");
					int sid= tools.getInt("SupplierID");
					String power=tools.getString("PowerType");
					Tool tool= new ElectricalTool( id, name, quantity, price, sid, power);
//					System.out.print(tool);
					modelControl.importTool(tool);
				}
				
			}
			tools.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void loadOrderList(){
		try {
			String sql = "SELECT * FROM ORDERS";
			statement = jdbc_connection.createStatement();
			ResultSet orders = statement.executeQuery(sql);
		
			while(orders.next())
			{
				int id=orders.getInt("OrderID");
				LocalDate date = orders.getDate("Odate").toLocalDate();
				Order order= new Order(id, date);
				modelControl.importOrder(order);
			}
			orders.close();
			
			String sql2 = "SELECT * FROM ORDERLINE";
			statement = jdbc_connection.createStatement();
			ResultSet orderlines = statement.executeQuery(sql2);
			orders.close();
			
			while(orderlines.next())
			{
				int Oid=orderlines.getInt("OrderID");
				int Tid=orderlines.getInt("ToolID");
				int Sid = orderlines.getInt("SupplierID");
				int quantity= orderlines.getInt("Quantity");
				OrderLine orderline= new OrderLine(Oid, Tid, Sid, quantity);
				modelControl.importOrderLine(orderline);
			}
			orderlines.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void loadSupplierList() {
		
		try {
			String sql = "SELECT * FROM (SUPPLIER AS S LEFT OUTER JOIN INTERNATIONAL AS I ON S.SupplierID=I.SupplierID);";
			statement = jdbc_connection.createStatement();
			ResultSet suppliers = statement.executeQuery(sql);
	
			while(suppliers.next())
			{
				if (suppliers.getString("Stype").equals("L")) {
					int id=suppliers.getInt("SupplierID");
					String name = suppliers.getString("Sname");
					String address = suppliers.getString("Address");
					String contact = suppliers.getString("Cname");
					String phone = suppliers.getString("Phone");
					Supplier supplier= new LocalSupplier( id, name,address, contact, phone);
					modelControl.importSupplier(supplier);
				}else if (suppliers.getString("Stype").equals("I")) {
					int id=suppliers.getInt("SupplierID");
					String name = suppliers.getString("Sname");
					String address = suppliers.getString("Address");
					String contact = suppliers.getString("Cname");
					String phone = suppliers.getString("Phone");
					double importTax=suppliers.getDouble("ImportTax");
					Supplier supplier= new InternationalSupplier( id, name,address, contact, phone,importTax);
					modelControl.importSupplier(supplier);
				}
				
			}
			suppliers.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void loadCustomerList() {
		
		try {
			String sql = "SELECT * FROM CLIENT;";
			statement = jdbc_connection.createStatement();
			ResultSet customers = statement.executeQuery(sql);
	
			while(customers.next())
			{
				int id=customers.getInt("ClientID");
				String fname = customers.getString("Fname");
				String lname = customers.getString("Lname");
				String type = customers.getString("Ctype");
				String phone = customers.getString("Phone");
				String address = customers.getString("Address");
				String postalCode = customers.getString("PostalCode");
				Customer customer= new Customer(id, fname, lname, type, phone, address ,postalCode);
				modelControl.importCustomer(customer);
				
			}
			customers.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void loadFromDatabase() {
//		System.out.println("Load Tools");
		loadToolList();
//		System.out.println("Load Orders");
		loadOrderList();
//		System.out.println("Load Suppliers");
		loadSupplierList();
		System.out.println("Load Customers");
		loadCustomerList();
		
	}

}
