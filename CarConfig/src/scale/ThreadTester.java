package scale;

import Adapter.BuildAuto;
import ExceptionHandler.TooManyOptions;

public class ThreadTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		try {
			BuildAuto buildauto = new BuildAuto();
			buildauto.BuildAuto("/Users/xiaolanzhou/Developer/java_workspace/CarConfig/test 2.txt");
			buildauto.printAuto("Ford Focus Wagon ZTW");
			
			Thread threadOne = new EditOptions(BuildAuto.auto, "threadOne");
			Thread threadTwo = new EditOptions(BuildAuto.auto, "threadTwo");
			
			((EditOptions) threadOne).setNewChoice("Manual");
			((EditOptions) threadOne).setOldNewOptionName("Transmission", "Manual", "Manual", -100);
			
			((EditOptions) threadTwo).setNewChoice("Selected");
			((EditOptions) threadTwo).setOldNewOptionName("Power_Moonroof", "None", "Unselected", -50);			
			
			threadOne.start();
			threadTwo.start();
			
		} catch (TooManyOptions e){
			e.printStackTrace();
		} 

	}

}
