package es2;

import java.io.Serializable;

public class Mossa implements Serializable {
	
	private static final long serialVersionUID = 1;
	public String pezzo;
	public int riga;
	public int colonna;
	
	Mossa(String s, int r, int c){
		pezzo=s;
		riga=r;
		colonna=c;
	}
}