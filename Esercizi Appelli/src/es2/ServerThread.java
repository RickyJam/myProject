package es2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread{

	private Scacchiera scacchiera;
	private int serverID;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ServerThread(Scacchiera s, int serverID, Socket socket) {
		this.setScacchiera(s);
		this.setServerID(serverID);
		this.setSocket(socket);
		try {
			this.setOut(new ObjectOutputStream(socket.getOutputStream()));
			this.setIn(new ObjectInputStream(socket.getInputStream()));
		} catch (IOException e) {	e.printStackTrace();	}
		start();
	}

	
	public void run() {
			try {
			boolean finito=false;
			Mossa m = null;
			
			do {
				//fase 1, mossa del giocatore
				m = (Mossa) in.readObject();
				System.out.println("SERVER>" + serverID + " il giocatore ha pensato la mossa:");
				scacchiera.mossa(m);
				
				//fase 2, controllo risultato mossa
				if(scacchiera.finita()){
					System.out.println("SERVER>" + serverID + " il giocatore ha concluso la partita!");
					finito=true;
					out.writeObject("END");
				} else {
					//fase 3, mossa del computer
					out.writeObject("CONTINUE");
					System.out.println("SERVER>" + serverID + " Il computer sta pensando la mossa:");
					m = pensaMossa();
					scacchiera.mossa(m);
					
					//fase 4, controllo mossa computer
					if(scacchiera.finita()){
						System.out.println("SERVER>" + serverID + " il giocatore ha concluso la partita!");
						finito=true;
						out.writeObject("END");
					} else {
						out.writeObject("CONTINUE");
					}
				}
			} while (!finito);
		}
		catch (Exception e) {	e.printStackTrace();	}
		
	}
	
	
	
	
	private Mossa pensaMossa(){
		// qui bisognerebbe pensare una mossa intelligente ...
		return new Mossa("cavallo", 1, 3);
	}
	
	
	
//	GETTER & SETTER
	public Scacchiera getScacchiera() {
		return scacchiera;
	}

	public void setScacchiera(Scacchiera scacchiera) {
		this.scacchiera = scacchiera;
	}

	public int getServerID() {
		return serverID;
	}


	public void setServerID(int serverID) {
		this.serverID = serverID;
	}


	public Socket getSocket() {
		return socket;
	}


	public void setSocket(Socket socket) {
		this.socket = socket;
	}


	public ObjectInputStream getIn() {
		return in;
	}


	public void setIn(ObjectInputStream in) {
		this.in = in;
	}


	public ObjectOutputStream getOut() {
		return out;
	}


	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}
}
