package Coin_Toss;

import java.util.Random;

public class Coin {
	
	private String sideUp;
	
	public Coin() {
	    Random generator = new Random();
	    int sideRand = generator.nextInt(2);
	    if (sideRand == 1) {
	    	sideUp = "heads";
	    } else {
	    	sideUp = "tails";
	    }  
	}
	
	public void toss() {
		Random generator = new Random();
		int tossSide = generator.nextInt(2);
		if (tossSide == 1) {
			sideUp = "heads";
		} else { 
			sideUp = "tails";
		}
	}
	
	public String getSideUp() {
		return sideUp;
	}
	
	public static void main( String args[]) {
		
		Coin coin = new Coin(); // constructor, created an object
		System.out.println("initial side is " + coin.getSideUp());
		
		int countHeads = 0;
		int countTails = 0;
		
		for (int i = 0; i < 20; i++) {
			coin.toss();
			if(coin.getSideUp() == "heads") {
				System.out.println("Toss " + i + " " + coin.getSideUp() + " is facing up!");
				countHeads++;
			} else {
				System.out.println("Toss " + i + " " + coin.getSideUp() + " is facing up!");
				countTails++;
			}
		}
		System.out.println(countHeads);
		System.out.println(countTails);
	}

}
