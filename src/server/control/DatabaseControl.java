/**
 * class used to connect to local database
 */
package server.control;

import java.sql.*;

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
				if (tools.getString("Ttype")=="R") {
					int id=tools.getInt("ToolID");
					String name = tools.getString("Tname");
					int quantity= tools.getInt("Quantity");
					double price= tools.getDouble("Price");
					int sid= tools.getInt("SupplierID");
					Tool tool= new Tool( id, name, quantity, price, sid);
					modelControl.addTool(tool);
				}else if (tools.getString("Ttype")=="E") {
					int id=tools.getInt("ToolID");
					String name = tools.getString("Tname");
					int quantity= tools.getInt("Quantity");
					double price= tools.getDouble("Price");
					int sid= tools.getInt("SupplierID");
					String power=tools.getString("PowerType");
					Tool tool= new ElectricalTool( id, name, quantity, price, sid,power);
					modelControl.addTool(tool);
				}
				
			}
			tools.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void loadOrderList(){
		
		
	}
	public void loadSupplierList() {
		
		try {
			String sql = "SELECT * FROM (SUPPLIER AS S LEFT OUTER JOIN INTERNATIONAL AS I ON S.SupplierID=I.SupplierID);";
			statement = jdbc_connection.createStatement();
			ResultSet suppliers = statement.executeQuery(sql);
	
			while(suppliers.next())
			{
				if (suppliers.getString("Stype")=="L") {
					int id=suppliers.getInt("SupplierID");
					String name = suppliers.getString("Sname");
					String address = suppliers.getString("Address");
					String contact = suppliers.getString("Cname");
					String phone = suppliers.getString("Phone");
					Supplier supplier= new Supplier( id, name,address, contact, phone);
					modelControl.addSupplier(supplier);
				}else if (suppliers.getString("Stype")=="I") {
					int id=suppliers.getInt("SupplierID");
					String name = suppliers.getString("Sname");
					String address = suppliers.getString("Address");
					String contact = suppliers.getString("Cname");
					String phone = suppliers.getString("Phone");
					double importTax=suppliers.getDouble("ImportTax");
					Supplier supplier= new InternationalSupplier( id, name,address, contact, phone,importTax);
					modelControl.addSupplier(supplier);
				}
				
			}
			suppliers.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
