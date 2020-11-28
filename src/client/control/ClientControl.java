package client.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

// TO BE CHANGED TO Client.model once finalized///////////////////////
import server.model.*;

public class ClientControl {
	
	private Socket aSocket;
	private PrintWriter socketOut; 
	private BufferedReader socketIn;
	private BufferedReader stdIn;
	
	public ClientControl (String serverName, int portNumber) {
		
		try {
			aSocket = new Socket (serverName, portNumber);
			//keyboard input stream
			stdIn = new BufferedReader (new InputStreamReader (System.in));
			socketIn = new BufferedReader (new InputStreamReader (aSocket.getInputStream()));
			socketOut = new PrintWriter (aSocket.getOutputStream(), true);
		}catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void communicate () {
		String msg1 = "";
		String msg2 = "";
		String response1 ="";
		String response2 = "";
		
		while (!msg1.split(" ")[0].toUpperCase().equals("QUIT")) {
			
			System.out.println("Enter function and argument or type ''QUIT 1'' to end:");
			
			try {
				msg1 = stdIn.readLine();
				socketOut.println(msg1);
//				String option = msg1.split(" ")[1];
//				
				response1 = socketIn.readLine();  //read item sent back from socket
				System.out.println("The response is: \n"+ response1); 
				response2 = socketIn.readLine(); // read message sent back from socket
				System.out.println("The response is: \n"+ response2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //reading the input from the user (i.e. the keyboard);
		}
		closeSocket ();
		
	}
	private void closeSocket () {
		
		try {
			stdIn.close();
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void main (String [] args) throws IOException {
		
		ClientControl aClient = new ClientControl ("localhost", 6666);
		aClient.communicate();
		
	}

}
