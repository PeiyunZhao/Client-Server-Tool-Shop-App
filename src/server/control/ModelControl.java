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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import server.model.*;


public class ModelControl implements Runnable{

	private BufferedReader socketIn;
	private PrintWriter socketOut;
	private DatabaseControl databaseControl;

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
		String msg1 = "";
		String msg2 = "";
		String response1 = "";
		String response2 = "";
		while(true){
			try {
//				System.out.println("reading from socket....");		//for debugging		
				msg1 = socketIn.readLine();
				
				
				response2=msg1.toUpperCase();
				
//				System.out.println("print to socket....");	//for debugging	
				
				socketOut.println(response2);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
