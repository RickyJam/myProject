package es1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MCDImpl extends UnicastRemoteObject implements MCD {
	
	private static final long serialVersionUID = 1L;
	
	private static int port = 1099;
	
	public MCDImpl() throws RemoteException{

	}
	
	public static void main(String[] args) {
		try {
			Registry registro = LocateRegistry.createRegistry(port);
		
			MCDImpl mcd = new MCDImpl();
			registro.rebind("MCD", mcd);
			System.out.println("server ready");
			
		} catch (RemoteException e) { e.printStackTrace(); }
	}
	
	public int mcd(int n, int m) {
		int r;
		
		while(m != 0) {
			r = n % m;
			n = m;
			m = r;
		}
		
		return n;
	
	}
}