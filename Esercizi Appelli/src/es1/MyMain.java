package es1;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MyMain {

	private static int port = 1099;
	private static String host = null;
	
	public MyMain() {
		
	}
	
	public static void main(String[] args) {
		
		MCD euclMCD = null;
		Registry registro;
		
		try {
			registro = LocateRegistry.getRegistry(host, port);
			euclMCD = (MCD) registro.lookup("MCD");
		} catch (RemoteException e) 	{	e.printStackTrace(); 
		} catch (NotBoundException e) 	{	e.printStackTrace();	}
		
		int x, y;
		
		
		
		try {
			
			x=18; y=3;
			System.out.println("MCD("+x+","+y+")=" + euclMCD.mcd(x,y));
			x=18; y=6;
			System.out.println("MCD("+x+","+y+")=" + euclMCD.mcd(x,y));
			x=18; y=7;
			System.out.println("MCD("+x+","+y+")=" + euclMCD.mcd(x,y));
			x=18765; y=345435;
			System.out.println("MCD("+x+","+y+")=" + euclMCD.mcd(x,y));
			
		} catch (Exception e) { e.printStackTrace(); }
		
	}
	
}