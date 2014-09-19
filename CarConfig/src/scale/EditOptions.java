package scale;

import ExceptionHandler.NotFoundException;
import Model.Automobile;
import Model.OptionSet;

public class EditOptions extends Thread {
	private Automobile auto;
	private String oldSetName;
	private String newSetName;
	private String oldOptionName;
	private String newOptionName;
	private float newOptionPrice;
	private String newChoice;
	
	//constructor
	public EditOptions(Automobile auto, String threadName){
		this.auto = auto;
		super.setName(threadName);		
	}
	//setters
	public void setAuto(Automobile auto){
		this.auto = auto;
	}
	public void setOldNewSetName(String oldSetName, String newSetName){
		this.oldSetName = oldSetName;
		this.newSetName = newSetName;
	}
	public void setOldNewOptionName(String oldSetName, String oldOptionName, String newOptionName, float newOptionPrice){
		this.oldSetName = oldSetName;
		this.oldOptionName = oldOptionName;
		this.newOptionName = newOptionName;
		this.newOptionPrice = newOptionPrice;
	}

	public void setNewChoice(String newChoice) {
		this.newChoice = newChoice;
	}
		
	//Methods
	public synchronized void editOptionSetChoice(){
		try{
		synchronized(Automobile.class){
			System.out.println("Edit OptionSet Choice by thread: " + super.getName());
			System.out.println("Choice changes from " + auto.getOptionChoice(oldSetName) + " to : ");
			auto.setOptionChoice(oldSetName, newChoice);
			System.out.println(auto.getOptionChoice(oldSetName));						
		}
		}catch(NotFoundException e){
			e.printStackTrace();
		}
	}
	
	public synchronized void editOption() {
		synchronized(Automobile.class){
			System.out.println("Edit Option by thread: " + super.getName() );
			auto.printOptionSet(oldSetName);
			auto.updateOption(oldSetName, oldOptionName, newOptionName, newOptionPrice);
			auto.printOptionSet(oldSetName);
		}
	}
	//run()
	public void run(){
		
		try{
			Thread.sleep(100);
		}catch(InterruptedException ex) { ex.printStackTrace(); }
		
		editOptionSetChoice();
		editOption();
				
	}
	

}
