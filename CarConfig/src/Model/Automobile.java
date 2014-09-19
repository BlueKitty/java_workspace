package Model;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedHashMap;

import ExceptionHandler.*;
import Model.OptionSet.Option;

public class Automobile implements Serializable{
	
	private String make;
	private String model;
	private LinkedHashMap<String, OptionSet> opset = new LinkedHashMap<String, OptionSet>();
	private Option choice;
	private int baseprice;
	
	//--------constructors---------
	public Automobile() {
		make ="Focus";
		model ="Wagon ZTW";
		baseprice = 18445;
		choice = null;
	}
	
	public Automobile(String make, String model){
		this.make = make;
		this.model = model;
		baseprice = 18445;
		choice = null;
	}
	
	public Automobile(String make, String model, int baseprice){
		this.make = make;
		this.model = model;
		this.baseprice = baseprice;
		choice = null;
	}
	//------Make getter and setter------
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	//------Model getter and setter------
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	//------getOptionChoice------
	public String getOptionChoice(String setName) throws NotFoundException {
		if(opset.get(setName) == null) {
			System.out.println("OptionSet Name Not Found");
			throw new NotFoundException();
		}else{
			if(opset.get(setName).getOptionChoice() == null){
				return "No Choice";
			}
			return opset.get(setName).getOptionChoice().getName();
		}
	}
	//------getOptionChoicePrice-----
	public float getOptionChoicePrice(String setName) throws NotFoundException{
		if(opset.get(setName) == null) {
			System.out.println("OptionSet Name Not Found");
			throw new NotFoundException();
		}else{
			return opset.get(setName).getOptionChoice().getPrice();
		}
	}
	//------setOptionChoice------
	public void setOptionChoice(String setName, String optionName) throws NotFoundException{
		if(opset.get(setName) == null) {
			System.out.println("OptionSet Name Not Found");
			throw new NotFoundException();
		}else{
			opset.get(setName).setOptionChoice(optionName);
		}
		
	}
	//------getTotalPrice------
	public int getTotalPrice(){
		int price = baseprice;
		for(String setName: opset.keySet()){
			try{
			     price += (int) getOptionChoicePrice(setName);
			}catch(NotFoundException ex){
				ex.printStackTrace();
			}
		}
		return price;
	}
	//---------------Getters---------------------
	public Iterator<String> optionSetNamesIterator() {
		return opset.keySet().iterator();
	}
	public LinkedHashMap<String,OptionSet> getOptMap(){
		return opset;
	}
	public OptionSet getOptionSet(String setName) {
		return opset.get(setName);
	}
	public String getOptionSetOptionName(String setName, int i) {
		return opset.get(setName).getOptionI(i).getName();
	}
	public int getOptionsetLength(String setName) {
		return opset.get(setName).getOptionSetSize();
	}
	
	public int getBaseprice() {
		return baseprice;
	}
	//------------Find Option with name---------------------
	public Option findOption(String n) throws NotFoundException{
		for(OptionSet ops: opset.values()){
			if(ops.findOption(n) != null){
				return ops.findOption(n);
			}
		}
		System.out.printf("%s option does not exist.%n", n);
		throw new NotFoundException();
	}
	//-----------Find Option with name in context of OptionSet()-----------
	public Option findOptionwithSet(String nameOptionSet, String nameOption) throws NotFoundException {
		if(opset.get(nameOptionSet) == null){
			System.out.printf("OptionSet %s Not Found %n", nameOptionSet);
			throw new NotFoundException();
		}else{
				return opset.get(nameOptionSet).findOption(nameOption);
		}
	}
//------Setters------
	public void setBasePrice(int price){
		baseprice = price;
	}
	//Set values of Option in context of OptionSet
	
	public void setOption(String OpsetName, int i, String name, float price){
		if(opset.get(OpsetName) == null){
			System.out.println("OptionSet Not Found");
			return;
		}else{
			opset.get(OpsetName).setOptionI(i, name, price);
		}
	}
//------Delete------
	//delete OptionSet according to name
	public void deleteOpset(String OpsetName){
		if(opset.get(OpsetName) == null){
			System.out.println("OptionSet Not Found");
			return;
		}else{
			opset.remove(OpsetName);
		}
	}
	//Delete Option in context of OptionSet
	public void deleteOption(String OpsetName, String OpName){
		if(opset.get(OpsetName) == null){
			System.out.println("OptionSet Not Found");
			return;
		}else{
			try{
				opset.get(OpsetName).deleteOption(OpName);
			}catch(NotFoundException ex){
				ex.printStackTrace();
			}
		}
	}
	
//------Update------
	//update values of OptionSet(find and set)
	public void updateOpsetName(String OpsetName, String newOptsetName){
		if(opset.get(OpsetName) == null){
			System.out.println("OptionSet Name Not Found");
			return;
		}else{
			OptionSet temp_opset = opset.get(OpsetName);
			temp_opset.setName(newOptsetName);
			opset.remove(OpsetName);
			opset.put(newOptsetName, temp_opset);
		}
	}
	//update values of Options in context of OptionSet
	public void updateOption(String OpsetName, String OpName, String name, float price){
		try{
			findOptionwithSet(OpsetName, OpName).setPrice(price);
			findOptionwithSet(OpsetName, OpName).setName(name);			
		}catch(NotFoundException ex){
			ex.printStackTrace();
		}		
	}
	//update price of Option
	public void updateOptionPrice(String OpsetName, String OpName, float price){
		try{
			findOptionwithSet(OpsetName, OpName).setPrice(price);		
		}catch(NotFoundException ex){
			ex.printStackTrace();
		}		
	}
	//print
	public void print(){
		System.out.println("Model: " + make + " " + model);
		System.out.println("Baseprice: " + Integer.toString(baseprice));
		for(OptionSet ops: opset.values()){
			ops.printOptionSet();
		}
	}
	
	public void printOptionSet(String setName) {
		OptionSet opsetPrint = getOptionSet(setName);
		opsetPrint.printOptionSet();	
	}
	
	
}

