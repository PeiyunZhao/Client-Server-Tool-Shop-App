package client.control;

import client.view.GUIClientViewer;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;


public class ClientViewControl {



	private ClientControl clientControl;

	public ClientViewControl() {
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

	public void sendCustomer(String customer) {
		//JSON string examples

		String[] list= customer.split(",");
		String out="{\"customerID\":"+list[0]+",\"fname\":\""+list[1]+"\",\"lname\":\""+list[2]+"\",\"type\":\""+list[3]+
				"\",\"address\":\""+list[4]+"\",\"postalCode\":\""+list[5]+"\",\"phone\":\""+list[6]+"\"}";
		sendMessage(out);

	}

	public String retrieveCustomer() {
		try {
			String str= clientControl.getSocketIn().readLine();
			if (str.equals("Customer Not Found")) return str;
			
			JSONObject customer= new JSONObject(str);
			String out= "";

			out+=Integer.toString(customer.getInt("customerID"))+",";
			out+=customer.getString("fname")+",";
			out+=customer.getString("lname")+",";
			out+=customer.getString("type")+",";
			out+=customer.getString("address")+",";
			out+=customer.getString("postalCode")+",";
			out+=customer.getString("phone");
			return out;

		} catch (IOException e) {
			return null;
		}
	}

	public String retrieveCustomers(){
		try {

			String str= clientControl.getSocketIn().readLine();
			JSONArray customers = new JSONArray(str);
			String out="";

			for (int i = 0; i < customers.length(); i++) {
				//customer.keySet()
				//supplierID  quantity  price  name  reorder  power  id 
				JSONObject customer = customers.getJSONObject(i);
				out+="ID: "+Integer.toString(customer.getInt("customerID"))+"  ";
				out+="Name: "+customer.getString("fname")+", ";
				out+=" "+customer.getString("lname")+"  ";
				out+="Type: "+customer.getString("type")+"  ";
				out+="Address: "+customer.getString("address")+" ";
				out+=", "+customer.getString("postalCode")+" ";
				out+="Phone: "+customer.getString("phone")+"\n";
				
			}

			return out;
		} catch (IOException e) {
			return null;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientViewControl cViewControl=new ClientViewControl();
		GUIClientViewer clientGUI = new GUIClientViewer(cViewControl);
	}
}


