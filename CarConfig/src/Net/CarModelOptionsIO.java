package Net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

import Adapter.BuildAuto;
import ExceptionHandler.TooManyOptions;
//import Model.Automobile;

public class CarModelOptionsIO {
	//client socket
	public static void main(String args[]) throws IOException {
		Socket clientEdit = null;
		BuildAuto buildauto = new BuildAuto();
		InetAddress inet = InetAddress.getLocalHost();
		try{
			clientEdit = new Socket(inet.getHostAddress().toString(), 4444);
		}catch (UnknownHostException e){
			System.err.println("Don't know about host.");
			System.exit(1);
		}catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection.");
			System.exit(1);
		}
		
		try {
			buildauto.BuildAuto("/Users/xiaolanzhou/Developer/java_workspace/CarConfig/test 2.txt");
		} catch (TooManyOptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(clientEdit.getOutputStream());
			Iterator<String> iter = buildauto.autoList();
			out.writeObject(buildauto.getAuto(iter.next()));
			BufferedReader in = new BufferedReader(new InputStreamReader(clientEdit.getInputStream()));
			if(in.readLine().equals("Received")) {
				System.out.println("Model received by server.");
			}else {
				System.out.println("Model not received by server.");
			}
		}catch (IOException e) {
			System.err.println("Output failed.");
			System.exit(1);
		}		
	}
}
