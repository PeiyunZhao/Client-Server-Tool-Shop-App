package server.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;

import org.json.JSONObject;

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
				
				String arg=msg1.split(" ")[1];
				String[] lis=msg1.split(" ");
				for(int i=2; i<lis.length;i++) {
					arg+=" "+lis[i];
				}
				
				//decodes message to formulate response "ISN hammer"
				execute(msg1.split(" ")[0],arg);
				
//				System.out.println("print to socket....");	//for debugging	

			} catch (IOException e) {
				e.printStackTrace();
			}catch (NullPointerException e) {
				return;
			}
		}
	}
	
	private void execute(String func, String arg) {
		
		if(func.equals("ILA")) {
			//Inventory List All Tools
			
			response2=inventoryListAll(arg);
			
			socketOut.println(response2);
			
		} else if(func.equals("ISI")) {
			//Inventory Search ID
			
			response2 =inventorySearchId(arg);
			socketOut.println(response2);
			
		} else if(func.equals("ISN")) {
			//Inventory Search Name
			
			response2 =inventorySearchName(arg);
			socketOut.println(response2);
					
		}  else if(func.equals("IDQ")) {
			//Inventory Decrease Quantity
			
			response2 =inventoryDecreaseQuantity(arg);
			socketOut.println(response2);
			
		}else if(func.equals("IDI")) {
			//Inventory Delete ID
			
			response2 =inventoryDeleteId(arg);
			socketOut.println(response2);
			
		} else if(func.equals("IUI")) {
			//Inventory Update ID
			
			response2 =inventoryUpdateId(arg);
			socketOut.println(response2);
			
		}else if(func.equals("CSI")) {
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
		
		if (tool==null) return "Tool Not Found";

		return toJSON(tool);
	}
	
	public String inventorySearchName(String arg){
		// func "ISN"
		
		refresh();
		response1="Searching for Tool Name :"+arg;
		socketOut.println(response1);
		Tool tool = model.getInventory().searchName(arg);
		
		if (tool!=null) return toJSON(tool);
			
		else return "Tool Not Found";

		
	}
	
	public String inventoryDecreaseQuantity(String arg){
		// func "IDQ"
		
		refresh();
		try {
			msg2=socketIn.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		response1="Decreasing Quantity for Tool ID "+arg+" by : "+msg2;
		socketOut.println(response1);
		
		int id=Integer.parseInt(arg);
		int n=Integer.parseInt(msg2);
		Tool tool = model.getInventory().searchID(id);

		String str="";
		if (tool!=null) {
			
			int res = model.getInventory().decrease(id, n);
			if (res==-1){
				str="processing error, invalid quantity input!";
			}else if(res==0) {
				str="Tool Quantity Updated, Stock left: "+(tool.getQuantity()-n);
				databaseControl.updateToolReorder(id);//to be implemented in DatabaseControl
			}else {
				str="Tool Ordered, Order ID: "+res;

				databaseControl.updateToolReorder(id); //to be implemented in DatabaseControl
				databaseControl.addOrder(res);
			}
			
			
			return str;
			
		}else {
			str="Tool ID not found, No Update.";
			return str;
		}
		
	}
	
	public String inventoryUpdateId(String arg){
		// func "IUI"
		
		refresh();
		response1="Updating Inventory: ID :"+arg;
		socketOut.println(response1);
		try {
			msg1=socketIn.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JSONObject t = new JSONObject(msg1);
		Tool tool=null;
		if(t.has("power")) {
			tool = new ElectricalTool(t.getInt("id"), t.getString("name"), t.getInt("quantity"), 
					t.getDouble("price"), t.getInt("supplierID"), 0,t.getString("power"));
			databaseControl.removeTool(tool.getID());
			databaseControl.addTool(tool,"E");
		}else {
			tool= new RegularTool(t.getInt("id"), t.getString("name"), t.getInt("quantity"), 
					t.getDouble("price"), t.getInt("supplierID"), 0);
			databaseControl.removeTool(tool.getID());
			databaseControl.addTool(tool,"R");
		}
		
		return "Updated tool ID: "+tool.getID();
		
	}
	
	public String inventoryDeleteId(String arg){
		// func "IDI"
		
		refresh();
		response1="Deleting Inventory: ID :"+arg;
		socketOut.println(response1);
		
		databaseControl.removeTool(Integer.parseInt(arg));
			
		return "Deleted tool ID: "+arg;
		
	}
	
	public String customerSearchId(String arg){
		// func "CSI"
		
		refresh();
		response1="Searching for Customer ID :"+arg;
		socketOut.println(response1);
		
		Customer customer = model.getCustomers().searchID(Integer.parseInt(arg));

		if (customer!=null) return toJSON(customer);
		
		else return "Customer Not Found";
	}
	
	public String customerSearchName(String arg){
		// func "CSN"
		
		refresh();
		response1="Searching for Customer Name :"+arg;
		socketOut.println(response1);
		
		LinkedHashSet<Customer> customers = model.getCustomers().searchName(arg);

		if (customers.isEmpty()) return "Customer Not Found";
		
		return toJSON(customers);
	}
	
	public String customerSearchType(String arg){
		// func "CST"
		
		refresh();
		response1="Searching for Customer Type :"+arg;
		socketOut.println(response1);
		
		LinkedHashSet<Customer> customers = model.getCustomers().searchType(arg);

		if (customers.isEmpty()) return "Customer Not Found";
		
		return toJSON(customers);
	}
	
	public String customerUpdateId(String arg){
		// func "CUI"
		
		refresh();
		response1="Updating Customer: ID :"+arg;
		socketOut.println(response1);
		try {
			msg1=socketIn.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Customer c= new Gson().fromJson(msg1,Customer.class);

		databaseControl.removeCustomer(c.getCustomerID());
		databaseControl.addCustomer(c);
		
		
		Customer temp = model.getCustomers().deleteCustomerID(Integer.parseInt(arg));
		
		String str="";
		
		if (temp!=null) {
		str="Customer profile updated, ID: "+arg;
		}
		else {
		str= "Customer profile added, ID: "+arg;
		} 
		return str;
		
	}

	public String customerDeleteId(String arg){
		// func "CDI"
		
		refresh();
		response1="Deleting Customer ID :"+arg;
		socketOut.println(response1);
		
		Customer temp = model.getCustomers().deleteCustomerID(Integer.parseInt(arg));
		
		String str="";
		if (temp!=null) {
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
	
	public String toJSON(Object o) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
}
