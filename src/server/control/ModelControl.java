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
import java.util.LinkedHashSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import server.model.*;


public class ModelControl implements Runnable{

	private BufferedReader socketIn;
	private PrintWriter socketOut;
	private DatabaseControl databaseControl;
	private String msg1 = "";
	private String msg2 = "";
	private String response1 = "";
	private String response2 = "";
	
	private Model model;

	public ModelControl(BufferedReader Sin, PrintWriter Sout) {
		
		socketIn=Sin;
		socketOut=Sout;
		setDatabaseControl(new DatabaseControl(this));
		setModel(new Model(this));
		databaseControl.loadFromDatabase();
	}
	
	public void refresh() {
		setDatabaseControl(new DatabaseControl(this));
		setModel(new Model(this));
		databaseControl.loadFromDatabase();
	}
	
	@Override
	public void run() {
		databaseControl.loadFromDatabase();

		while(true){
			try {
//				System.out.println("reading from socket....");		//for debugging		
				msg1 = socketIn.readLine();
				
				if (msg1.split(" ")[0].toUpperCase().equals("QUIT")) {
					
					socketOut.println("Quitting...");
					return;
				}
				
				//decodes message to formulate response
				execute(msg1.split(" ")[0],msg1.split(" ")[1]);
				
//				System.out.println("print to socket....");	//for debugging	

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void execute(String func, String arg) {
		// TODO Auto-generated method stub
		if(func.equals("ILA")) {
			//Inventory List All Tools
			
			response2=inventoryListAll(arg);
			
			socketOut.println(response2);
			
		} else if(func.equals("ISI")) {
			//Inventory Search ID
			
			response2 =inventorySearchId(arg);
			socketOut.println(response2);
			
		} else if(func.equals("ISN")) {
			//Inventory Search name
			
			response2 =inventorySearchName(arg);
			socketOut.println(response2);
					
		} else if(func.equals("ICI")) {
			//Inventory Check Quantity
			
			response2 =inventoryCheckId(arg);
			socketOut.println(response2);
			
		} else if(func.equals("IDI")) {
			//Inventory Delete ID
			
			response2 =inventoryDecreaseId(arg);
			socketOut.println(response2);
			
		} else if(func.equals("CSI")) {
			//Customer Search ID
			
			response2 =customerSearchId(arg);
			socketOut.println(response2);
			
		} else if(func.equals("CSN")) {
			//Customer Search name
			
			response2 =customerSearchName(arg);
			socketOut.println(response2);
			
		} else if(func.equals("CST")) {
			//Customer Search Type

			response2 =customerSearchType(arg);
			socketOut.println(response2);
			
		} else if(func.equals("CUI")) {
			//Customer Update id

			response2 =customerUpdateId(arg);
			socketOut.println(response2);
			
		} else if(func.equals("CDI")) {
			//Customer Delete by ID

			response2 =customerDeleteId(arg);
			socketOut.println(response2);
			
		}else {
		
			response1="Function Read Error!: "+func+" "+arg;
			socketOut.println(response1);
			response2="Please try again!";
			socketOut.println(response2);
		}
	}

	public String inventoryListAll(String arg){
		// func "ILA"
		
		refresh();
		response1="Listing All Tools...";
		socketOut.println(response1);
		LinkedHashSet<Tool> tools = model.getInventory().getAllTools();
	
		return toJSON(tools);
	}
	
	public String inventorySearchId(String arg){
		// func "ISI"
		
		refresh();
		response1="Searching for Tool ID :"+arg;
		socketOut.println(response1);
		Tool tool = model.getInventory().searchID(Integer.parseInt(arg));

		return toJSON(tool);
	}
	
	public String inventorySearchName(String arg){
		// func "ISN"
		
		refresh();
		response1="Searching for Tool Name :"+arg;
		socketOut.println(response1);
		Tool tool = model.getInventory().searchName(arg);

		return toJSON(tool);
	}
	
	public String inventoryCheckId(String arg){
		// func "ICI"
		
		refresh();
		response1="Checking Inventory on Tool ID :"+arg;
		socketOut.println(response1);
		Tool tool = model.getInventory().searchID(Integer.parseInt(arg));

		return toJSON(tool);
	}
	
	public String inventoryDecreaseId(String arg){
		// func "IDI"
		
		refresh();
		try {
			msg2=socketIn.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		response1="Decreasing Tool ID "+arg+" by : "+msg2;
		socketOut.println(response1);
		
		int id=Integer.parseInt(arg);
		int quantity=Integer.parseInt(msg2);
		Tool tool = model.getInventory().searchID(id);

		String str="";
		if (tool!=null) {
			
			int res = model.getInventory().decrease(id, quantity);
			if (res==-1){
				str="processing error, invalid quantity input!";
			}else if(res==0) {
				str="Tool Quantity Updated, Stock left: "+(tool.getQuantity()-quantity);
//				databaseControl.UpdateTool(id);//to be implemented in DatabaseControl
			}else {
				str="Tool Ordered, Order ID: "+res;

//				databaseControl.UpdateTool(id); //to be implemented in DatabaseControl
//				databaseControl.addOrder(res);
			}
			
			
			return str;
			
		}else {
			str="Tool ID not found, No Tools Deleted.";
			return str;
		}
		
	}
	
	public String customerSearchId(String arg){
		// func "CSI"
		
		refresh();
		response1="Searching for Customer ID :"+arg;
		socketOut.println(response1);
		Customer customer = model.getCustomers().searchID(Integer.parseInt(arg));

		return toJSON(customer);
	}
	
	public String customerSearchName(String arg){
		// func "CSN"
		
		refresh();
		response1="Searching for Customer Name :"+arg;
		socketOut.println(response1);
		LinkedHashSet<Customer> customers = model.getCustomers().searchName(arg);

		return toJSON(customers);
	}
	
	public String customerSearchType(String arg){
		// func "CSN"
		
		refresh();
		response1="Searching for Customer Type :"+arg;
		socketOut.println(response1);
		LinkedHashSet<Customer> customers = model.getCustomers().searchType(arg);

		return toJSON(customers);
	}
	
	public String customerUpdateId(String arg){
		// func "CSI"
		
		refresh();
		
		try {
			msg1=socketIn.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		response1="Customer profile updated, ID :"+arg;
		socketOut.println(response1);
		
		Customer customer = model.getCustomers().searchID(Integer.parseInt(arg));
		return toJSON(customer);
	}
	
	public String customerAddId(String arg){
		// func "CDI"
		
		refresh();
		response1="Adding Customer ID :"+arg;
		
		try {
			msg1=socketIn.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Customer temp= new Gson().fromJson(msg1,Customer.class);
		
		String str="";
		
		Customer customer=model.getCustomers().searchID(Integer.parseInt(arg));
		
		if (customer!=null) {

			str= "ERROR: Customer ID "+arg+" exists";
		
		}else {
			model.getCustomers().addCustomer(temp);
//			databaseControl.addCustomer(Integer.parseInt(arg));//to be implemented in DatabaseControl
			str="Customer ID added.";
		}
		
		return str;
	}

	public String customerDeleteId(String arg){
		// func "CDI"
		
		refresh();
		response1="Deleting Customer ID :"+arg;
		Customer customer = model.getCustomers().deleteCustomerID(Integer.parseInt(arg));
		String str="";
		if (customer!=null) {
			databaseControl.removeCustomer(Integer.parseInt(arg));
			str= "Customer ID"+arg+" Deleted";
		}else {
			str="Customer ID not found, No profiles Deleted.";
		}
		
		return str;
	}
	
	public DatabaseControl getDatabaseControl() {
		return databaseControl;
	}

	private void setDatabaseControl(DatabaseControl databaseControl) {
		this.databaseControl = databaseControl;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	private String toJSON(Object o) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public String performOption(String func, String arg) {
		
		return "Undetermined Function Call";
	}
}
