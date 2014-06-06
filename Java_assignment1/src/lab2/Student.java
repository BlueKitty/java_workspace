package lab2;

public class Student {
	private int SID;
	private int scores[] = new int[5];
	//write public get and set methods for SID and scores
	public void setSID( int i) {
		SID = i;
	}
	public int getSID() {
		return SID;
	}
	public void setScore(int i, int score) {
		scores[i] = score;
	}
	public int getScore(int i) {
		return scores[i];
	}
	//add methods to print values of instance variables
	public void printSID() {
		System.out.format("%4d",SID);
	}
	public void printScoreI(int i) {
		System.out.format("%4d", scores[i]);
	}

}
