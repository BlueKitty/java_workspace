package test2;

import Adapter.*;
import ExceptionHandler.NotFoundException;
//import Model.*;
import ExceptionHandler.TooManyOptions;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			BuildAuto buildauto = new BuildAuto();
			buildauto.BuildAuto("/Users/xiaolanzhou/Developer/java_workspace/CarConfig/test 2.txt");
			//System.out.println(buildauto.auto.getMake());
			//System.out.println(buildauto.auto.getModel());
			buildauto.printAuto("Ford Focus Wagon ZTW");
			buildauto.updateOptionSetName("Ford Focus Wagon ZTW", "Color", "newColor");
			buildauto.printAuto("Ford Focus Wagon ZTW");
			//buildauto.updateOptionPrice("Ford Focus Wagon ZTW", "Transmission", "Automatic", 100);
			//buildauto.printAuto("Ford Focus Wagon ZTW");
		} catch (TooManyOptions e){
			e.printStackTrace();
		} catch (NotFoundException e){
			e.printStackTrace();
		}
		

	}

}
