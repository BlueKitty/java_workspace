package Model;
import java.io.*;
import java.util.*;

import ExceptionHandler.NotFoundException;

public class OptionSet implements Serializable{
	private ArrayList<Option> opt = new ArrayList<Option>();
	private String name;
	private Option choice;

//------constructors------
	//default constructor
	public OptionSet() {
		name = null;
		choice = null;
	}
	
	//constructor with name
	public OptionSet(String n) {
		name =n;
		choice=null;
	}

//------Getters-------
	//getName
	protected String getName() {
		return name;
	}
	//getOpset length
	protected int getOptionSetSize() {
		return opt.size();
	}
	//getOption with index
	protected Option getOptionI(int i){
		if(i<0 || i>=opt.size()){
			return null;
		}
		return opt.get(i);
	}
	//find Option with name
	protected Option findOption(String n){
		for(Option op: opt){
			if(op.getName().equals(n)){
				return op;
			}
		}
		return null;
	}
//------Setters------
	//setName
	protected void setName(String n){
		name = n;
	}
	//setOption with index
	protected void setOptionI(int i, String name, float price){
		if(i>=opt.size()){
			opt.add(new Option(name, price));
			return;
		}
		opt.get(i).setName(name);
		opt.get(i).setPrice(price);
	}
	//add one Option to the end
	protected void addOption(String name, float price){
		opt.add(new Option(name, price));
	}
	//delete an Option with index
	protected void deleteOption(String name) throws NotFoundException {
		if(findOption(name) == null){
			System.out.println("Option Name Not Found");
			throw new NotFoundException();
		}else{
			opt.remove(findOption(name));
		}		
	}
	//Update (find and set)
	protected void updateOption( String name, String newname, float newprice) throws NotFoundException{
		if(findOption(name) == null){
			System.out.println("Option Name Not Found");
			throw new NotFoundException();
		}else{			
			findOption(name).setPrice(newprice);
			findOption(name).setName(newname);
		}
	}
	//Update OptionPrice
	protected void updateOptionPrice( String name,  float newprice) throws NotFoundException{
		if(findOption(name) == null){
			System.out.println("Option Name Not Found");
			throw new NotFoundException();
		}else{
			findOption(name).setPrice(newprice);
		}
	}	
	//print 
	protected void printOptionSet(){
		System.out.println("OptionSet: " + name);
		if(opt == null){
			System.out.println("This OptionSet doesn't have Options.");
		}else{
			for(Option op: opt){
				System.out.println("  "+ op.stringOption());
			}
		}
	}
//------Option Choice getter and setter------
	protected Option getOptionChoice() {
		if(choice == null){
			return null;
		}
		return choice;
	}
	protected void setOptionChoice(String optionName) throws NotFoundException{
		if(findOption(optionName) == null){
			System.out.println("Option Name Not Found");
			throw new NotFoundException();
		}else{
			choice = findOption(optionName);
		}
	}
	
//------Class Option------	
	public class Option implements Serializable {
		private String name;
		private float price;
//------constructors------
		//default constructor
		public Option() {
			name = null;
			price = 0;
		}
		//constructor with name
		public Option(String name) {
			this.name = name;
			price = 0;
		}
		//constructor with name and price
		public Option(String name, float price) {
			this.name = name;
			this.price = price;
		}
//------Setters------
		protected void setName(String name ){
			this.name = name;			
		}
		protected void setPrice(float price){
			this.price = price;
		}
//------Getters------
		protected String getName() {
			return name;
		}
		protected float getPrice() {
			return price;
		}
//toString
		protected String stringOption(){
			return "Option: " + name + " " + Float.toString(price);
		}
	}

}

