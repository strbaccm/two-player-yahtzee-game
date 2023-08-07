package projekat_ORS4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
	private int SERVER_PORT;
	private ServerSocket serverSocket = null;
	private ArrayList<ArrayList<Socket>> clients = new ArrayList<ArrayList<Socket>>();
	private ArrayList<ClientThread> threads = new ArrayList<>();
	
	public Server() throws IOException{
		serverSocket = new ServerSocket(SERVER_PORT);
		System.out.println("Server started on port " + SERVER_PORT);
		execute();
	}
	
	public void execute() {
		while(true) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
				ClientThread client = new ClientThread(socket, this);
				client.start();
				threads.add(client);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	

}
