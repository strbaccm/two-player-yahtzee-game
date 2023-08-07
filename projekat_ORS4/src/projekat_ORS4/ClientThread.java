package projekat_ORS4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread{
	private Socket client;
	private String username;
	private Server server;
	private BufferedReader input;
	private PrintWriter output;
	
	public ClientThread(Socket client, Server server) {
		this.client = client;
		this.server = server;
		try {
			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			output = new PrintWriter(client.getOutputStream(), true);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	

	public void run() {
		
	}
	
	public Socket getSocket() {
		return client;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void closeAll() {
		try {
			input.close();
			output.close();
			client.close();
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
