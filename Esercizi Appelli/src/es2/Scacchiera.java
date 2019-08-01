package es2;

public class Scacchiera {
	
	private boolean isFinita=false;
	
	public Scacchiera(){
		isFinita=false;
	}

	public void mossa(Mossa m){
		System.out.println("Scacchiera: giocatore muove");
		isFinita=(Math.random()<0.1);
	}
	
	boolean finita(){
		return isFinita;
	}
	
}