package main.java.exception_handling;

public class BeverageRecipeNotAvailableException extends Exception{

	public BeverageRecipeNotAvailableException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BeverageRecipeNotAvailableException() {
		super("Recipe for given beverage is unavailable");
	}

}
