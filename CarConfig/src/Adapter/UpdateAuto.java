package Adapter;

import ExceptionHandler.NotFoundException;

public interface UpdateAuto {
	public void updateOptionSetName(String Modelname, String OptionSetname, String newName) throws NotFoundException;
	public void updateOptionPrice(String Modelname, String Optionname, String Option, float newprice);

}
