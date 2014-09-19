package Adapter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

import ExceptionHandler.*;
import Model.*;
public abstract class proxyAutomobile {
	
	//public static Automobile auto = new Automobile();
	private LinkedHashMap<String, Automobile> list_auto = new LinkedHashMap<String, Automobile>();
	
	public Iterator<String> autoList() {
		return list_auto.keySet().iterator();
	}
	public Automobile getAuto(String ModelName) {
		return list_auto.get(ModelName);
	}
	public void addAuto(Automobile auto) {
		String ModelName = auto.getMake() + ' ' + auto.getModel();
		list_auto.put(ModelName, auto);
	}
	
	public void updateOptionSetName(String Modelname, String OptionSetname,
			String newName) throws NotFoundException {
		// TODO Auto-generated method stub
		Automobile auto = list_auto.get(Modelname);
		if((auto.getMake() + " " + auto.getModel()).equals(Modelname)){
			auto.updateOpsetName(OptionSetname, newName);
		}else{
			System.out.println("Modelname does not match.");
			throw new NotFoundException();
		}
		
	}

	public void updateOptionPrice(String Modelname, String Optionname,
			String Option, float newprice) {
		// TODO Auto-generated method stub
		Automobile auto = list_auto.get(Modelname);
		if((auto.getMake() + " " + auto.getModel()).equals(Modelname)){
			auto.updateOptionPrice(Optionname, Option, newprice);
		}else{
			System.out.println("Modelname does not match");
		}
		
	}

	public void BuildAuto(String filename) throws TooManyOptions {
		// TODO Auto-generated method stub
			Automobile auto = new Automobile();
			try{
				FileReader file = new FileReader(filename);
				BufferedReader buff = new BufferedReader(file);
				String line = null;
				int OpCount = 0;
				int lineCount = 0;
				//ArrayList<Integer> opsetSizeList = new ArrayList<Integer>();
				String OpsetName = null;
				int OpsetSize = 0;
				while( (line = buff.readLine()) != null){
					if(lineCount == 0){
						System.out.println(line);
						StringTokenizer make_model = new StringTokenizer(line);						
						auto.setMake(make_model.nextToken());
						String temp_model = make_model.nextToken();
						while(make_model.hasMoreTokens()){
							temp_model = temp_model + " " + make_model.nextToken(); 
						}
						auto.setModel(temp_model);
						lineCount++;
					}else{
						if(line.contains("OptionSet:")){
							//Set up OptionSet Name and Size					
							StringTokenizer st = new StringTokenizer(line);
							if(st.nextToken().equals("OptionSet:")){
								OpsetName = st.nextToken();
								if(OpsetName.endsWith(",")){
									OpsetName = OpsetName.substring(0, OpsetName.length() - 1);
								}
								OpsetSize = Integer.parseInt(st.nextToken());								
								auto.getOptMap().put(OpsetName, new OptionSet(OpsetName));
								OpCount = 0;
								//opsetSizeList.add(OpsetSize);
							}else{
								System.out.println("Watch out. This isn't OptionSet info.");
							}
							
						}else{
							if(OpsetSize == OpCount){
								throw new TooManyOptions();
							}
							//Set up Option: name and price
							String[] parts = line.split(",");
							String OpName = parts[0];
							float OpPrice = Float.parseFloat(parts[1]);							
							auto.setOption(OpsetName, OpCount, OpName, OpPrice);
							OpCount++;						
						}
						
						lineCount++;
					}
				}
				
			buff.close();
			}catch (IOException e) {
				System.out.print("Error --" + e.toString());
			}
			String ModelName = auto.getMake() + ' ' + auto.getModel();
			list_auto.put(ModelName, auto);
	}

	public void printAuto(String Modelname) {
		// TODO Auto-generated method stub
		Automobile auto = list_auto.get(Modelname);
		if((auto.getMake() + " " + auto.getModel()).equals(Modelname)){
			auto.print();
		}else{
			System.out.println("Modelname does not exist.");
		}
		
	}
	
	public void print() {
		Iterator<String> newIter = list_auto.keySet().iterator();
		while(newIter.hasNext()) {
			list_auto.get(newIter.next()).print();
		}
	}

}
