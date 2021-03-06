package lab2;

import java.lang.reflect.Array;

public class Statistics {
	int [] lowscores = new int [5];
	int [] highscores = new int [5];
	float [] avgscores = new float [5];
	
	void findlow(Student [] a) {
		// This method will find lowest score and store it in an array names lowscores
		int length_array = Array.getLength(a);
		for( int j=0; j<5; j++) {
			lowscores[j] = a[0].getScore(j);
			for( int i=0; i<length_array; i++) {
				if(a[i] == null) {
					break;
				}
				else if (a[i].getScore(j) < lowscores[j] ) {
					lowscores[j] = a[i].getScore(j);
				}			
		    }
		}
	}
	
	void findhigh(Student [] a) {
		//This method will find highest score and store it in an array names highscores
		int length_array = Array.getLength(a);
		for( int j=0; j<5; j++) {
			highscores[j] = a[0].getScore(j);
			for( int i=0; i<length_array; i++) {
				if(a[i] == null) {
					break;
				}
				else if (a[i].getScore(j) > highscores[j] ) {
					highscores[j] = a[i].getScore(j);
				}			
		    }
		}
	}
	
	void findavg(Student [] a) {
		//This method will find avg score for each quiz and store it in an array names avgscores
		int length_array = Array.getLength(a);
		for( int j=0; j<5; j++) {
			int sumscores = 0;
			int stu_num = length_array;
			
			for( int i=0; i<length_array; i++) {
				if(a[i] == null) {
					stu_num = i;
					break;
				}
				else {
					sumscores += a[i].getScore(j);
				}
			
		    }
			
			avgscores[j] = (float) ((double)sumscores/(double)stu_num);
		}
	}
	
	//Add methods to print values of instance variables
	public void printHigh() {
		//print high scores
		System.out.print(String.format( "%-12s", "High Score" ));
		for( int i=0; i<5; i++) {
			System.out.format("%6d", highscores[i]);
		}
		System.out.println();		
	}
	
	public void printLow() {
		//print low scores
		System.out.print(String.format( "%-12s", "Low Score" ));
		for( int i=0; i<5; i++) {
			System.out.format("%6d", lowscores[i]);
		}
		System.out.println();		
	}
	
	public void printAvg() {
		//print avg scores
		System.out.print(String.format( "%-12s", "Average" ));
		for( int i=0; i<5; i++) {
			System.out.format("%6.1f", avgscores[i]);
		}
		System.out.println();		
	}

}
