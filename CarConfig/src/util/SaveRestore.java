package util;

import java.io.*;

import Car_Config.Automotive;

public class SaveRestore {
	
	//Serialization
	public void serializeAuto(Automotive auto,String filename){
		try{
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
			os.writeObject(auto);
			os.close();
		}catch(IOException ex) {
			ex.printStackTrace();			
		}
		auto = null;
	}
	//Deserialization
	public Automotive deserializeAuto(String filename){
		try{
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));
			Automotive auto = (Automotive) is.readObject();
			is.close();
			return auto;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}		
		
	}

}
