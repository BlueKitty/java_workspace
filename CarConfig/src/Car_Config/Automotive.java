package Car_Config;
import java.io.*;
import Car_Config.OptionSet.Option;

public class Automotive implements Serializable{
	
	private String name;
	private OptionSet opset[] ;
	private int baseprice;
	//--------Four overloaded constructors---------
	public Automotive() {
		opset = new OptionSet[5];
		for( int i=0; i< opset.length; i++){
			opset[i] = new OptionSet();
		}
		name ="Focus Wagon ZTW";
		baseprice = 18445;
	}
	
	public Automotive(String n){
		opset = new OptionSet[5];
		for( int i=0; i< opset.length; i++){
			opset[i] = new OptionSet();
		}
		name = n;
		baseprice = 18445;
	}
	
	public Automotive(int size){
		opset = new OptionSet[size];
		for( int i=0; i< opset.length; i++){
			opset[i] = new OptionSet();
		}
		name = "Focus Wagon ZTW";
		
	}
	
	public Automotive(int size, String name, int baseprice){
		opset = new OptionSet[size];
		this.name = name;
		for( int i =0; i < opset.length; i++) {
			opset[i] = new OptionSet();
		}
		this.baseprice = baseprice;
	}
	//---------------Getters---------------------
	public String getName(){
		return name;
	}
	public OptionSet[] getOpset() {
		return opset;
	}
	public OptionSet getOptionset( int i ){
		if(i<0 || i >= opset.length){
			System.out.println("Index is out of bound");
			return null;
		}
		return opset[i];
	}
	public String getOptionsetName( int i ){
		if(i<0 || i >= opset.length){
			System.out.println("Index is out of bound");
			return null;
		}
		return opset[i].getName();
	}
	public int getBaseprice() {
		return baseprice;
	}
	
	//-------------Find-OptionSet with name-----------------
	public int findOpset(String n) {
		for(int i=0; i< opset.length; i++){		
			if(opset[i].getName() == n){
				return i;
			}
		}
		return -1;
	}
	//------------Find Option with name---------------------
	public Option findOption(String n) {
		for(int i=0; i< opset.length; i++){
			int j = opset[i].findOption(n);
			if( j != -1 ){
			return opset[i].getOptionI(j);
			}
		}
		return null;
	}
	//-----------Find Option with name in context of OptionSet()-----------
	public Option findOptionwithSet(String nameOptionSet, String nameOption) {
		int indexOpset = findOpset( nameOptionSet);
		if( indexOpset == -1) {
			System.out.println("OptionSet Not Found");
			return null;
		}else{
			int indexOp = opset[indexOpset].findOption(nameOption);
			if(indexOp == -1){
				System.out.println("Option Not Found");
				return null;
			}else{
				return opset[indexOpset].getOptionI(indexOp);
			}
		}
	}
//------Setters------
	public void setName(String n){
		name = n;
	}
	public void setBasePrice(int price){
		baseprice = price;
	}
	//set values of OptionSet with OptionSet index
	public void setOpSetName(int i, String n){
		if(i<0 || i >= opset.length){
			System.out.println("Index is out of bound");
			return;
		}else{
			opset[i].setName(n);
		}
	}
	//set OptionSet with name and size
	public void setOpSet(int i, String name,int size){
		if(i<0 || i >= opset.length){
			System.out.println("Index is out of bound");
			return;
		}else{
			opset[i] = new OptionSet(name,size);
		}
	}
	//Set values of Option in context of OptionSet
	public void setOption(String OpsetName, int i, String name, float price){
		int j = findOpset(OpsetName);
		if(j == -1){
			System.out.println("OptionSet Not Found");
			return;
		}else{
			opset[j].setOptionI(i, name, price);
		}
	}
//------Delete------
	//delete OptionSet according to name
	public void deleteOpset(String OpsetName){
		int j = findOpset(OpsetName);
		if(j == -1){
			System.out.println("OptionSet Not Found");
			return;
		}else{
			OptionSet tempOpset[] = new OptionSet[opset.length -1];
			System.arraycopy(opset, 0, tempOpset, 0, j);
			System.arraycopy(opset, j+1, tempOpset, j, opset.length-j-1);
			opset = tempOpset;		
		}
	}
	//Delete Option in context of OptionSet
	public void deleteOption(String OpsetName, String OpName){
		int j = findOpset(OpsetName);
		if( j == -1){
			System.out.println("OptionSet Not Found");
			return;
		}else{
				opset[j].deleteOption(OpName);
		}
	}
	
//------Update------
	//update values of OptionSet(find and set)
	public void updateOpsetName(String OpsetName, String newOptsetName){
		int i = findOpset(OpsetName);
		if(i == -1){
			System.out.println("OptionSet Not Found");
			return;
		}else{
			setOpSetName(i,newOptsetName);
		}
	}
	//update values of Options in context of OptionSet
	public void updateOption(String OpsetName, String OpName, String name, float price){
		int i = findOpset(OpsetName);
		if(i == -1){
			System.out.println("OptionSet Not Found");
			return;
		}else{
			opset[i].updateOption(OpName, name, price);
		}		
	}
	//print
	public void print(){
		System.out.println("Model: " + name);
		System.out.println("Baseprice: " + Integer.toString(baseprice));
		for(int i = 0; i < opset.length ; i++){
			opset[i].printOptionSet();
		}
	}
	
	
}
