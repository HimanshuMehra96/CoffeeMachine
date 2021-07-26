package main.java.exception_handling;

import main.java.model.Ingredient;

public class IngredientNotSufficientException extends Exception{

	public IngredientNotSufficientException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IngredientNotSufficientException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public IngredientNotSufficientException(Ingredient ingredient) {
		super("item " + ingredient.getName() + " is not sufficent");
	}

}
