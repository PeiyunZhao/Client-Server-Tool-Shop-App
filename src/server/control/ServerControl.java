package server.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerControl {
	private Socket aSocket;
	private ServerSocket serverSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private ModelControl mc;
	private ExecutorService pool;

	public ServerControl(int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pool = Executors.newCachedThreadPool();
		System.out.println("Server started successfully!");
	}
	
	public void runServer() {
		try {
			while (true) {
				aSocket = serverSocket.accept();
				System.out.println("Console at Server side says: Connection accepted by the server!");
				socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
				socketOut = new PrintWriter(aSocket.getOutputStream(), true);
				ModelControl modelControl= new ModelControl(socketIn, socketOut);
				pool.execute(modelControl);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.shutdown();
		try {
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {

		ServerControl myServer = new ServerControl(6666);
		
		myServer.runServer();

	}

}
