package Net;
import java.io.*;
import java.net.*;
import java.util.Iterator;

import Adapter.BuildAuto;
import Model.Automobile;
public class BuildCarModelOption {

	@SuppressWarnings("null")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InetAddress inet = null;
		try {
			inet = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(inet.getHostAddress());
		
		BuildAuto buildauto = new BuildAuto();
		ServerSocket serverSocRead = null;
		ServerSocket serverSocWrite = null;
		try {
			serverSocRead = new ServerSocket(4444);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				serverSocRead.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.err.println("Couldn't listen on port: 4444.");
			System.exit(1);
		}
		
		try {
			serverSocWrite = new ServerSocket(5555);
		} catch (IOException e) {
			try {
				serverSocWrite.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.err.println("Couldn't listen on port: 5555.");
			System.exit(1);
		}
		
		ReadThread inThread = null;
		WriteThread outThread = null;
		int readCounter = 0;
		int writeCounter = 0;
		
		while (true) {
			if(readCounter == 0) {
				inThread = new ReadThread(serverSocRead, buildauto);
				inThread.start();
				readCounter += 1;
			}
			if(!inThread.isAlive()) {
				readCounter -= 1;
			}
			if(writeCounter == 0) {
				outThread = new WriteThread(serverSocWrite, buildauto);
				outThread.start();
				writeCounter += 1;
			}
			if(!outThread.isAlive()) {
				writeCounter -= 1;
			}
			
		}
	}

}

class ReadThread extends Thread {
	private ServerSocket readServer;
	private BuildAuto autoList;
	private Automobile curAuto;
	
	public ReadThread(ServerSocket ss, BuildAuto auto) {
		readServer = ss;
		autoList = auto;
		curAuto = new Automobile();
	}
	
	public synchronized void run() {
		Socket clientSocket = null;
		ObjectInputStream in = null;
		try{
			clientSocket = readServer.accept();
			in = new ObjectInputStream(clientSocket.getInputStream());
		}catch (IOException e) {
			System.out.println("Get input from client Error.");
			e.printStackTrace();
		}
		
		synchronized (BuildAuto.class) {
			try {
				curAuto = (Automobile) in.readObject();
				autoList.addAuto(curAuto);
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				out.println("Received");
				out.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Error happens when getting the object from client.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
}

class WriteThread extends Thread {
	private ServerSocket ss;
	private BuildAuto autoList;
	
	public WriteThread(ServerSocket serverSoc, BuildAuto buildauto) {
		ss = serverSoc;
		autoList = buildauto;
	}
	
	public synchronized void run() {
		Socket clientSocketGet = null;
		ObjectOutputStream out = null;
		try {
			clientSocketGet = ss.accept();
			System.out.println("Successfully connect with port 5555");
			synchronized (BuildAuto.class) {
				Iterator<String> iter = autoList.autoList();
				out = new ObjectOutputStream(clientSocketGet.getOutputStream());
				while(iter.hasNext()) {
					Automobile tempOut = autoList.getAuto(iter.next());
					out.writeObject(tempOut);
				}
				PrintWriter outConfig = new PrintWriter(clientSocketGet.getOutputStream(), true);
				outConfig.println("OutputAll");
				outConfig.close();
			}
		} catch(IOException e) {
			System.out.println("Accept failed");
			System.exit(1);
		}
	}
}
