package server.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerControl {
	private Socket aSocket;
	private ServerSocket serverSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private ModelControl mc;

	public ServerControl() {
		try {
			serverSocket = new ServerSocket(6666);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mc = new ModelControl(socketIn,socketOut);
	}

	public static void main(String[] args) throws IOException {

		ServerControl myServer = new ServerControl();

		// Establishing the connection
		try {
			
			myServer.aSocket = myServer.serverSocket.accept();
			System.out.println("Console at Server side says: Connection accepted by the server!");
			myServer.socketIn = new BufferedReader(new InputStreamReader(myServer.aSocket.getInputStream()));
			myServer.socketOut = new PrintWriter(myServer.aSocket.getOutputStream(), true);
			
			myServer.mc.run();

			myServer.socketIn.close();
			myServer.socketOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
