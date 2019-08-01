package es2;

import java.io.IOException;

public class MultiClient {
	
	static final int MAX_THREADS = 3;	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		int i=0;
		
		while (i<MAX_THREADS) {
			new Giocatore(i);
			//Thread.sleep(100);
			i++;
		}
		
		System.out.println("Finito");
	}
}