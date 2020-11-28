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
					int reorder= tools.getInt("Reorder");
					Tool tool= new RegularTool( id, name, quantity, price, sid,reorder);
//					System.out.print(tool);
					modelControl.getModel().importTool(tool);
					
				}else if (tools.getString("Ttype").equals("E")) {
					int id=tools.getInt("ToolID");
					String name = tools.getString("Tname");
					int quantity= tools.getInt("Quantity");
					double price= tools.getDouble("Price");
					int sid= tools.getInt("SupplierID");
					int reorder= tools.getInt("Reorder");
					String power=tools.getString("PowerType");
					Tool tool= new ElectricalTool( id, name, quantity, price, sid,reorder, power);
//					System.out.print(tool);
					modelControl.getModel().importTool(tool);
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
				modelControl.getModel().importOrder(order);
				
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
				Tool tool = modelControl.getModel().getInventory().searchID(Tid);
//				System.out.print(tool);
				OrderLine orderline= new OrderLine(Oid, tool);
				modelControl.getModel().importOrderLine(orderline);
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
					modelControl.getModel().importSupplier(supplier);
				}else if (suppliers.getString("Stype").equals("I")) {
					int id=suppliers.getInt("SupplierID");
					String name = suppliers.getString("Sname");
					String address = suppliers.getString("Address");
					String contact = suppliers.getString("Cname");
					String phone = suppliers.getString("Phone");
					double importTax=suppliers.getDouble("ImportTax");
					Supplier supplier= new InternationalSupplier( id, name,address, contact, phone,importTax);
					modelControl.getModel().importSupplier(supplier);
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
				modelControl.getModel().importCustomer(customer);
				
			}
			customers.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeTool(int id) {

		String sql = "DELETE FROM TOOL WHERE (ToolID = ?)";
		try {
			PreparedStatement stmt=jdbc_connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void addCustomer(int id) {
		String sql="INSERT INTO CLIENT "+
				"(`ClientID`, `Lname`, `Fname`, `Ctype`, `Phone`, `Address`, `PostalCode`) "+
				"VALUES (?, ?, ?, ?, ?, ?, ?);";
		try {
			Customer c=modelControl.getModel().getCustomers().searchID(id);
			PreparedStatement stmt=jdbc_connection.prepareStatement(sql);
			stmt.setInt(1,c.getCustomerID());
			stmt.setString(2,c.getLname());
			stmt.setString(3,c.getFname());
			stmt.setString(4,c.getType());
			stmt.setString(5,c.getPhone());
			stmt.setString(6,c.getAddress());
			stmt.setString(7,c.getPostalCode());
			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void removeCustomer(int id) {
		String sql = "DELETE FROM CLIENT WHERE (ClientlID = ?)";
		try {
			PreparedStatement stmt=jdbc_connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}	

	public void addOrder(int id) {
		String sql="INSERT INTO ORDERLINE (`OrderID`, `ToolID`, `SupplierID`, `Quantity`) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement stmt=jdbc_connection.prepareStatement(sql);
			Order o = modelControl.getModel().getInventory().getOrderList().searchID(id);
			for(OrderLine ol : o.getOrderLines()) {
				stmt.setInt(1, ol.getToolID());
				stmt.setInt(2, ol.getToolID());
				stmt.setInt(3, ol.getTool().getSupplierID());
				stmt.setInt(4, ol.getQuantity());
				stmt.executeUpdate();
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}


	public void updateToolReorder(int id) {

		String sql= "UPDATE TOOL SET `Reorder` = ? WHERE (`ToolID` = ?);";
		Tool tool=modelControl.getModel().getInventory().searchID(id);
		
		try {
			PreparedStatement stmt=jdbc_connection.prepareStatement(sql);
			stmt.setInt(1, tool.getReorder());
			stmt.setInt(2,id);
			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void loadFromDatabase() {
		//		System.out.println("Load Tools");
		loadToolList();
		//		System.out.println("Load Suppliers");
		loadSupplierList();
		//		System.out.println("Load Customers");
		loadCustomerList();
		//		System.out.println("Load Orders");
		loadOrderList();
		
	}









}
