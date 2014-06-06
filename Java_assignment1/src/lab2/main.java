package lab2;

public class main {

	public static void main(String[] args) {

		Student lab2 [] = new Student[40];
		lab2 = Util.readFile("/Users/xiaolanzhou/Developer/java_workspace/Java_assignment1/src/lab2/test.txt", lab2);
		Statistics statlab2 = new Statistics();
		statlab2.findlow(lab2);
		statlab2.findhigh(lab2);
		statlab2.findavg(lab2);
		statlab2.printHigh();
		statlab2.printLow();
		statlab2.printAvg();
	
	}

}
