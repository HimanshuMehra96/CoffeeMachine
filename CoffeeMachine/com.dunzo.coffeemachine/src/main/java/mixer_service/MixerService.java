package main.java.mixer_service;

import main.java.exception_handling.BeverageRecipeNotAvailableException;
import main.java.exception_handling.IngredientNotAvailableException;
import main.java.exception_handling.IngredientNotSufficientException;
import main.java.model.BeverageRecipe;
import main.java.model.Ingredient;
import main.java.storage.Storage;

//Service for mixing ingredients for serving beverages
public class MixerService {
	private Storage storage;

	public MixerService(Storage storage) {
		this.storage = storage;
	}
	
	public Boolean makeBeverage(String beverageName)
			throws IngredientNotAvailableException, BeverageRecipeNotAvailableException,
			IngredientNotSufficientException {
		
		BeverageRecipe beverageRecipe = storage.getBeverageRecipeUsingName(beverageName);
		if(checkForSufficientIngredients(beverageRecipe)) {
			useIngredients(beverageRecipe);
			return true;
		}
		return false;
	}
	
	private Boolean checkForSufficientIngredients(BeverageRecipe beverageRecipe)
			throws IngredientNotAvailableException, IngredientNotSufficientException {
		
		for(Ingredient ingredient: beverageRecipe.getIngredients()) {
			Ingredient ingredientFromStore = storage.getIngredientFromStore(ingredient.getName());
			if(ingredientFromStore.getQuantity()<ingredient.getQuantity()) {
				throw new IngredientNotSufficientException(ingredientFromStore);
			}
		}
		return true;
	}
	
	private void useIngredients(BeverageRecipe beverageRecipe)
			throws IngredientNotSufficientException, IngredientNotAvailableException {
		for(Ingredient ingredient: beverageRecipe.getIngredients()) {
			storage.useIngredientFromStore(ingredient);
		}
	}
}
