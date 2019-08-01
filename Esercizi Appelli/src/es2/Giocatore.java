package es2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ThreadLocalRandom;

class Giocatore extends Thread{

	static final int PORT = 5000;
	
	private int myID;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket socket;
	
	public Giocatore(int id) throws UnknownHostException, IOException {
	
		setMyID(id);
		socket = new Socket(InetAddress.getLocalHost(), PORT);
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
		
		this.start();
	
	}
	
	public void run() {
		boolean finito=false;
		Mossa mossa;
		try {
		do {
			//fase 1, mossa giocatore
			mossa=pensaMossa();
			out.writeObject(mossa);	
			System.out.println("giocatore ha pensato la mossa e l'ha eseguita!");
			
			//fase 2, controllo risultato mossa
			if(finita()){
				System.out.println("Il giocatore ha concluso la partita!");
				finito=true;
			} else {
				//fase 3, mossa del computer
				System.out.println("il computer esegue la mossa...");
				
				//fase 4, controllo mossa computer
				if(finita()){
					System.out.println("Il computer ha concluso la partita!");
					finito=true;
				}
			}
		} while(!finito);
		} catch (Exception e) {		e.printStackTrace();  	}
	}
	
	private Boolean finita() throws ClassNotFoundException, IOException{

		String finita = (String) in.readObject();
		if(finita.equals("END"))
			return true;
		else 
			return false;
	}
	
	
	private Mossa pensaMossa(){
		// qui bisognerebbe pensare una mossa intelligente ...
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(1));
		} catch (InterruptedException e) { e.printStackTrace(); }
		
		return new Mossa("cavallo", 1, 3);
	}

	public int getMyID() {
		return myID;
	}

	public void setMyID(int myID) {
		this.myID = myID;
	}
	
}