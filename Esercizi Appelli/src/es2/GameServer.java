package es2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer {
	
	static final int MAX_THREADS = 3;
	static final int PORT = 5000;

	public GameServer() {
	}
	
	public static void main(String[] args) throws IOException {
		ServerSocket server = null;
		try {
			server = new ServerSocket(5000);
		} catch (IOException e) { e.printStackTrace(); } 
		
		ArrayList<Socket> sockets = new ArrayList<Socket>();
		
		int i = 0;
		while(i < MAX_THREADS) {
			try {
				Socket tempSocket = server.accept();
				sockets.add(tempSocket);
				new ServerThread(new Scacchiera(), i, tempSocket);
			} catch (IOException e) { e.printStackTrace(); }
			i++;
		}
		
	
	}
			
	
}
