package test;
import Car_Config.Automotive;
import util.*;
public class Driver {
	public static void main(String[] args){
		ReadSource readfile = new ReadSource();
		SaveRestore rwfile = new SaveRestore();
		Automotive FordZIW = readfile.buildAutoObject("/Users/xiaolanzhou/Developer/java_workspace/CarConfig/test 2.txt");
		//print attributes before serialization
		FordZIW.print();
		System.out.println("//---------------------------------------------------//");
		rwfile.serializeAuto(FordZIW, "auto.ser");
		Automotive newFordZIW = rwfile.deserializeAuto("auto.ser");
		newFordZIW.print();
	}

}