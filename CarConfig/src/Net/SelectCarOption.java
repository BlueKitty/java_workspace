package Net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.*;

import Adapter.BuildAuto;
import ExceptionHandler.NotFoundException;
import Model.Automobile;
import Model.OptionSet;

public class SelectCarOption {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Socket clientSocketGet = null;
		InetAddress inet = InetAddress.getLocalHost();
		try {
			clientSocketGet = new Socket(inet.getHostAddress().toString(), 5555);
		} catch(UnknownHostException e) {
			System.err.println("Unknown Host");
			System.exit(1);
		} catch(IOException e1) {
			System.err.println("Couldn't get I/O for the connection.");
			System.exit(1);
		}
		
		BuildAuto models = new BuildAuto();
		ObjectInputStream in = null;
		Automobile tempIn = null;
		try {
			in = new ObjectInputStream(clientSocketGet.getInputStream());
			tempIn = (Automobile) in.readObject();
			models.addAuto(tempIn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Iterator<String> nameIter = models.autoList();
		ArrayList<String> names = new ArrayList<String>();
		System.out.println("The available models are:");
		while (nameIter.hasNext()) {
			names.add(nameIter.next());
			System.out.println(names.size() - 1 + ": " + names.get(names.size() - 1));
		}
		//choose a model
		System.out.println("Please choose a model:");
		Scanner sc = new Scanner(System.in);
		Automobile chosenCar = models.getAuto(names.get(sc.nextInt()));
		chosenCar.print();
		//choose the option
		Iterator<String> opIter = chosenCar.optionSetNamesIterator();
		while(opIter.hasNext()) {
			String curOpSet = opIter.next();
			ArrayList<String> optionNames = new ArrayList<String>();
			System.out.println("OptionSet: " + curOpSet);
			//OptionSet curOpSetObject = chosenCar.getOptionSet(curOpSet);
			for(int i = 0; i < chosenCar.getOptionsetLength(curOpSet); i++) {								
				System.out.println(i + " : " + chosenCar.getOptionSetOptionName(curOpSet, i));
				optionNames.add(chosenCar.getOptionSetOptionName(curOpSet, i));
			}
			System.out.println("Please choose one option.");
			Scanner sc1 = new Scanner(System.in);
			try {
				chosenCar.setOptionChoice(curOpSet, optionNames.get(sc1.nextInt()));
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			optionNames.clear();
		}
		
		//Print out the option choice and price
		opIter = chosenCar.optionSetNamesIterator();
		System.out.println("Print Car Configuration:");
		System.out.println(chosenCar.getMake() + ' ' + chosenCar.getModel());
		while(opIter.hasNext()) {
			String curOpSet = opIter.next();
			try {
				System.out.println(curOpSet + " | " + chosenCar.getOptionChoice(curOpSet));
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		System.out.println("Final Price: " + chosenCar.getTotalPrice());
	}

}
