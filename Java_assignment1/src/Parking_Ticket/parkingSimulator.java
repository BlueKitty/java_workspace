package Parking_Ticket;

import java.util.ArrayList;
import java.util.Random;

class ParkedCar {
	private String carMake, carModel, carColor, licenseNum;
	private int minsParked;
	
	//ParkedCar constructor
	public ParkedCar(){
		ArrayList<String> makeList = new ArrayList<String>();
		makeList.add("BMW");
		makeList.add("Audi");
		makeList.add("Toyota");
		makeList.add("Honda");
		makeList.add("Acura");
		Random generator1 = new Random();
		int makeIndex = generator1.nextInt(5);
		carMake = makeList.get(makeIndex);
		
		
		ArrayList<String> modelList = new ArrayList<String>();
		modelList.add("1.0");
		modelList.add("2.0");
		modelList.add("3.0");
		modelList.add("4.0");
		modelList.add("5.0");
		Random generator2 = new Random();
		int modelIndex = generator2.nextInt(5);
		carModel = modelList.get(modelIndex);
		
		ArrayList<String> colorList = new ArrayList<String>();
		colorList.add("Red");
		colorList.add("White");
		colorList.add("Black");
		colorList.add("Blue");
		colorList.add("Yellow");
		Random generator3 = new Random();
		int colorIndex = generator3.nextInt(5);
		carColor = colorList.get(colorIndex);
		
		
		ArrayList<String> licenseNumList = new ArrayList<String>();
		licenseNumList.add("A0012CD");
		licenseNumList.add("NY00357");
		licenseNumList.add("CAL400D");
		licenseNumList.add("BC0A011");
		licenseNumList.add("ABCDEFG");
		Random generator4 = new Random();
		int licenseNumIndex = generator4.nextInt(5);
		licenseNum = licenseNumList.get(licenseNumIndex);
		
		//Random generator5 = new Random();
		//minsParked = generator5.nextInt(121);			
		
	}
	
	public void setMinsParked( int i) {
		minsParked = i;
	}
	
	public String getCarMake() {
		return carMake;
	}
	
	public String getCarModel() {
		return carModel;
	}
	
	
	public String getCarColor() {
		return carColor;
	}
	
	public String getLicenseNum() {
		return licenseNum;
	}
	
	public int getMinsParked() {
		return minsParked;
	}
	
}


class ParkingMeter {
	private int timePurchased;
	
	//public ParkingMeter(){
		//ArrayList<Integer> timeList = new ArrayList<Integer>();
		//timeList.add(12);
		//timeList.add(24);
		//timeList.add(36);
		//timeList.add(48);
		//timeList.add(60);
		
		//Random generator = new Random();
		//int timeIndex = generator.nextInt(5);
		//timePurchased = timeList.get(timeIndex);
	//}
	
	public void setTimePurchased(int i) {
		timePurchased = i;
	}
	public int getTimePurchased() {
		return timePurchased;
	}
}

class ParkingTicket {
	private String make, model, color, licenseNum;
	private int fine;
	private String name, badge;
	
	public void setMake(String str) {
		make = str;
	}
	
	public String getMake() {
		return make;
	}
	
	public void setModel(String str) {
		model = str;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setColor(String str) {
		color = str;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setLicenseNum(String str) {
		licenseNum = str;
	}
	
	public String getLicenseNum() {
		return licenseNum;
	}
	
	//deal with the fine
	
	public void setFine(int minsOver) {
		if (minsOver == 0) {
			fine = 0;
		} else if (minsOver > 0) {
			double hours = (double) minsOver /60.0;
			int hrs = (int) Math.ceil(hours);
			fine = 25 + (hrs - 1) * 10 ;		
		}
				
	}
	
	public int getFine() {
		return fine;
	}
	
	public void setName(String str) {
		name = str;
	}
	
	public String getName() {
		return name;
	}
	
	public void setBadge(String str) {
		badge = str;
	}
	
	public String getBadge() {
		return badge;
	}
	
	public void issueTicket() {
		System.out.println("****************************************");
		System.out.println("*Car Make: " + make);
		System.out.println("*Car Color: " + color);
		System.out.println("*Car Model: " + model);
		System.out.println("*Car LicenseNumber: " + licenseNum);
		System.out.println("*Fine: " + fine);
		System.out.println("*Officer Name: " + name);
		System.out.println("*Officer Badge Number: " + badge);
		System.out.println("****************************************");
	}
	
}

class PoliceOfficer {
	private String name, badgeNum;
	
	public PoliceOfficer(){
		
		ArrayList<String> nameList = new ArrayList<String>();
		nameList.add("Mike");
		nameList.add("Tom");
		
		ArrayList<String> badgeNumList = new ArrayList<String>();
		badgeNumList.add("M1234");
		badgeNumList.add("T5678");
		
		Random generator = new Random();
		int officerIndex = generator.nextInt(2);
		name = nameList.get(officerIndex);
		badgeNum = badgeNumList.get(officerIndex);
	}
	
	public String getName(){
		return name;
	}
	public String getBadgeNum(){
		return badgeNum;
	}
	
	public int examine( ParkedCar car, ParkingMeter meter ){
		int minsparked = car.getMinsParked();
		int minspurchased = meter.getTimePurchased();
		
		if(minsparked  > minspurchased){
			return  (minsparked - minspurchased);
		} else {
			return 0;
		}
	}
	
}

//Testing
class Test {
	public static void main( String args[] ) {
	//test parkedvehicle in with in parking time purchased
		ParkedCar Car1 = new ParkedCar();
		int minsParkedCar1 = 600;
		Car1.setMinsParked(minsParkedCar1);
		
		ParkingMeter Meter1 = new ParkingMeter();
		int timePurchasedMeter1 = 30;
		Meter1.setTimePurchased(timePurchasedMeter1);
		
		PoliceOfficer Officer1 = new PoliceOfficer();
		int Timediff1 = Officer1.examine(Car1, Meter1);
		if(Timediff1 != 0) {
			ParkingTicket Ticket1 = new ParkingTicket();
			Ticket1.setMake( Car1.getCarMake());
			Ticket1.setColor(Car1.getCarColor());
			Ticket1.setModel(Car1.getCarModel());
			Ticket1.setLicenseNum(Car1.getLicenseNum());
			Ticket1.setFine(Timediff1);
			Ticket1.setName(Officer1.getName());
			Ticket1.setBadge(Officer1.getBadgeNum());
			Ticket1.issueTicket();
		} else {
			System.out.println("No Ticket");
		}
		
	}
}