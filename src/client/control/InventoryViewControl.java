package client.control;

//import client.model.*;
import client.view.GUIInventoryViewer;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class InventoryViewControl {

	private ClientControl clientControl;
	
	public InventoryViewControl() {
		setClientControl(new ClientControl ("localhost", 6666));
	}
	
	public ClientControl getClientControl() {
		return clientControl;
	}

	public void setClientControl(ClientControl clientControl) {
		this.clientControl = clientControl;
	}


	public void sendMessage(String txt) {
		clientControl.getSocketOut().println(txt);
	}
	
	public String retrieveResponse() {
		try {
			return clientControl.getSocketIn().readLine();
		} catch (IOException e) {
			return null;
		}
	}
	
	public void sendTool(String tool, String type) {
		//JSON string examples
		//{"name":"Wing Bats","price":11.25,"supplierID":8003,"quantity":11,"reorder":0,"id":1004}
		//{"name":"Barney Bits","price":23.59,"supplierID":8006,"quantity":21,"reorder":0,"power":"12V","id":1009}
		String[] list= tool.split(",");
		if (type.equals("R")) {
			String rout="{\"name\":\""+ list[1]+ "\",\"price\":"+list[2]+",\"supplierID\":"+list[4]+",\"quantity\":"+list[3]+",\"reorder\":0,\"id\":"+list[0]+"}"; 
			sendMessage(rout);
		}else {
			String eout="{\"name\":\""+ list[1]+ "\",\"price\":"+list[2]+",\"supplierID\":"+list[5]+",\"quantity\":"+list[3]+",\"power\":\""+list[4]+"\",\"reorder\":0,\"id\":"+list[0]+"}"; 
			sendMessage(eout);
		}
	}

	public String retrieveTool() {
		try {
			String str= clientControl.getSocketIn().readLine();
			if (str.equals("Tool Not Found")) return str;
			
			JSONObject tool= new JSONObject(str);
			String out= "";
			
			out+=Integer.toString(tool.getInt("id"))+",";
			out+=tool.getString("name")+",";
			out+=Double.toString(tool.getDouble("price"))+",";
			out+=Integer.toString(tool.getInt("quantity"))+",";
			
			if(tool.has("power")) out+=tool.getString("power")+",";
			
			out+=Integer.toString(tool.getInt("supplierID"));
			
			return out;
			
		} catch (IOException e) {
			return null;
		}
	}
	
	public String retrieveTools(){
		try {
			
			String str= clientControl.getSocketIn().readLine();
			JSONArray tools = new JSONArray(str);
			String out="";
			
			for (int i = 0; i < tools.length(); i++) {
				//tool.keySet()
				//supplierID  quantity  price  name  reorder  power  id 
				JSONObject tool = tools.getJSONObject(i);
				out+="ID: "+Integer.toString(tool.getInt("id"))+"  ";
				out+="Name: "+tool.getString("name")+"  ";
				out+=", $"+Double.toString(tool.getDouble("price"))+"  ";
				out+=", "+Integer.toString(tool.getInt("quantity"))+"  ";
				
				if(tool.has("power")) out+="Power: "+tool.getString("power")+"  ";
				
				out+="Supplier: "+Integer.toString(tool.getInt("supplierID"))+"  ";
				out+="\n";
			}
			
			return out;
		} catch (IOException e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InventoryViewControl iViewControl=new InventoryViewControl();
		GUIInventoryViewer inventoryGUI = new GUIInventoryViewer(iViewControl);
	}
}
