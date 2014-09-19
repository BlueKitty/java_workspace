package ExceptionHandler;

public class NotFoundException extends Exception {
	public void whatNotFound(Object[] name){
		System.out.printf("%s is not found%n", name);
	}
}
