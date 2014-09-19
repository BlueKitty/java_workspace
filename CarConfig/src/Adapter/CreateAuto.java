package Adapter;

import ExceptionHandler.TooManyOptions;

public interface CreateAuto {
	public void BuildAuto(String filename) throws TooManyOptions;
	public void printAuto(String Modelname);
}
