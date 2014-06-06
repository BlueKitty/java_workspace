package Car_Config;
import java.io.*;

public class OptionSet implements Serializable{
	private Option opt[];
	private String name;

//------constructors------
	//default constructor
	public OptionSet() {
		opt = null;
		name = null;
	}
	//constructor with name
	public OptionSet(String n) {
		opt = null;
		name =n;
	}
    //constructor with OptionSet name and size
	public OptionSet(String n, int size){
		opt = new Option[size];
		name =n;
		for(int i = 0; i < size; i++) {
			opt[i] = new Option();
		}
	}
//------Getters-------
	//getName
	protected String getName() {
		return name;
	}
	//getOption with index
	protected Option getOptionI(int i){
		if(i<0 || i>=opt.length){
			return null;
		}
		return opt[i];
	}
	//find Option with name
	protected int findOption(String n){
		for(int i=0; i< opt.length; i++){
			if(opt[i].getName() == n){
				return i;
			}
		}
		return -1;
	}
//------Setters------
	//setName
	protected void setName(String n){
		name = n;
	}
	//setOption with index
	protected void setOptionI(int i, String name, float price){
		if(i<0 || i>= opt.length){
			System.out.println("Index is out of boundary.");
			return;
		}
		opt[i].setName(name);
		opt[i].setPrice(price);
	}
	//add one Option to the end
	protected void addOption(String name, float price){
		int i = opt.length;
		Option tempOpt[] = new Option[opt.length + 1];
		System.arraycopy(opt, 0, tempOpt, 0, i);
		tempOpt[i].setName(name);
		tempOpt[i].setPrice(price);
		opt = tempOpt;
	}
	//delete an Option with index
	protected void deleteOption(String name) {
		if(findOption(name) == -1){
			System.out.println("Not Found");
		}else{
			int i =  findOption(name);
			Option temp_opt[] = new Option[opt.length - 1];
			System.arraycopy(opt, 0, temp_opt, 0, i);
			System.arraycopy(opt, i+1, temp_opt, i, opt.length-i-1);
			opt = temp_opt;
		}
		
	}
	//Update (find and set)
	protected void updateOption( String name, String newname, float newprice){
		if(findOption(name) == -1){
			System.out.println("Not Found");
		}else{
			int i = findOption(name);
			setOptionI(i,newname,newprice);
		}
	}
	//print 
	protected void printOptionSet(){
		System.out.println("OptionSet: " + name);
		if(opt == null){
			System.out.println("This OptionSet doesn't have Options.");
		}else{
			for( int i=0; i < opt.length; i++){
				System.out.println("  "+ opt[i].stringOption());
			}
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
